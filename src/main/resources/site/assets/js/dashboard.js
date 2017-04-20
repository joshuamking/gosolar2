/////////////////////////////////////////// link ///////////////////////////////////////////
// var link = "https://1644cae1.ngrok.io/";
var link = "http://localhost:8000/";
/////////////////////////////////////////// end link ///////////////////////////////////////////

var tableHeader = "<tr><th>Class Name</th><th>Day/Time</th><th>Instructor</th><th>Options</th></tr>";

var tableString = tableHeader;

var emerTableHeader = "<tr><th>Name</th><th>Phone Number</th><th>Options</th></tr>";
var emerTableString = emerTableHeader;

var login = true;
var student_id = "";

var jsonLoad = "";

function onLoad (){
	if (login){

	}else{

	}
}

function addClass(id, classname, classday, professor){
	tableString += "<tr><td>"+classname+"</td><td>"+classday+"</td><td>"+professor+"</td><td><form action=\"\" method=\"post\"><input type=\"submit\" name=\"remove\" value=\"remove\" id=\"submit_remove"+id+"\"><div class=\"submitContainer\"><label for=\"submit_remove"+id+"\"><div class=\"box\">Remove</div></label></div></form></td></tr>";
}

function addEmergency(id, name, phonenumber){
	emerTableString += "<tr><td>"+name+"</td><td>"+phonenumber+"</td><td><form action=\"\" method=\"post\"><input type=\"submit\" name=\"remove\" value=\"remove\" id=\"emSubmit_remove"+id+"\"><div class=\"submitContainer\"><label for=\"emSubmit_remove"+id+"\"><div class=\"box\">Remove</div></label></div></form></td></tr>";
}

function checkLogin(json){
	if (getCookie("loginStatus").toLowerCase() == "false") {


	}else{

	}


}

function loadContent(loggedin){
	if (loggedin) {
		$('#mainWrapper').load('includes/profile.html');
	}
}

function getParams(){
	var url = decodeURIComponent(window.location.href);
	var begstr = "userData=";
	var endstr = "&submit=Submit";
	var beg = url.indexOf(begstr);
	var end = url.indexOf(endstr);
	var json = url.substring((beg + begstr.length), end);
	populate(json);

}

function populate(json){
	jsonLoad = json;
	var jsonP = JSON.parse(json);
	// console.log(json.lastName);
	student_id = jsonP.id;
	var userDetails = "body>div#mainContainer>div#mainWrapper>div.profile>div.userInfo>div.userDetails>span";
	$(userDetails + '.firstname').html(jsonP.firstName);
	$(userDetails + '.lastname').html(jsonP.lastName);
	if (jsonP.major) {
		$(userDetails + '.major').html(jsonP.major.replace("+", " "));
	}


	getCourses(jsonP.id);

	getEmer(json);

}

function getCourses(id){
	var settings = {
	  "async": true,
	  "crossDomain": true,
	  "url": link + "student/" + id + "/getCourses",
	  "method": "GET",
	  "headers": {}
	}

	$.ajax(settings).done(function (response, status) {
		var result = JSON.stringify(response);
		result = JSON.parse(result);
		// console.log("result length is " + result.length);
		// 
		console.log(status);
		if (status == 404) {
			tableString = "<tr style=\"width: 100%\"><td style=\"width: 100%\" colspan=\"0\">No Classes Yet</td></tr>";

		}else if (result.length > 0) {
			// console.log(result[0].name);
			for (var i = 0; i < result.length; i++) {
				var course = result[i];
				var professor = "TBA";
				var days = "TBA";
				if (course.professor != null) {
					professor = (course.professor.firstName + " " + course.professor.lastName);
				}
				if (course.days != null) {
					days = course.days;
				}
				addClass(course.id, course.name, days, professor);
			}
		} else {
			tableString = "<tr style=\"width: 100%\"><td style=\"width: 100%\" colspan=\"0\">No Classes Yet</td></tr>";
		}
		$('#tableData').html(tableString);

		// console.log(tableString);
	});
}

function getEmer(json){
	var emerC = JSON.parse(json);
	emerC = emerC.emergencyContacts;
	if (emerC.length > 0) {
		for (var i = 0; i < emerC.length; i++) {
			addEmergency(emerC[i].id, emerC[i].name.replace("+", " "), emerC[i].phoneNumber);
		}
	}else{
		emerTableString = "<tr style=\"width: 100%\"><td style=\"width: 100%\" colspan=\"0\">No Contacts Yet</td></tr>";
	}

	$('#tableDataE').html(emerTableString);
}

function getTrans(json){
	var transP = JSON.parse(json);
}



$(document).ready(function(){
	loadContent(true);
	setTimeout(function(){
		getParams();
	}, 50);
	// getParams();


});