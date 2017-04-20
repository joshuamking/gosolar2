/////////////////////////////////////////// link ///////////////////////////////////////////
var link = "https://1644cae1.ngrok.io/";
/////////////////////////////////////////// end link ///////////////////////////////////////////

var tableHeader = "<tr><th>Class Name</th><th>Day/Time</th><th>Instructor</th><th>Options</th></tr>";

var tableString = tableHeader;

var login = true;
var student_id = "";



function onLoad (){
	if (login){

	}else{
		
	}
}

function addClass(id, classname, classday, professor){
	tableString += "<tr><td>"+classname+"</td><td>"+classday+"</td><td>"+professor+"</td><td><form action=\"\" method=\"post\"><input type=\"submit\" name=\"remove\" value=\"remove\" id=\"submit_remove"+id+"\"><input type=\"submit\" name=\"modify\" value=\"modify\" id=\"submit_modify"+id+"\"><div class=\"submitContainer\"><label for=\"submit_remove"+id+"\"><div class=\"box\">Remove</div></label><label for=\"submit_modify"+id+"\"><div class=\"box\">Modify</div></label></div></form></td></tr>";
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
	json = JSON.parse(json);
	// console.log(json.lastName);
	student_id = json.id;
	var userDetails = "body>div#mainContainer>div#mainWrapper>div.profile>div.userInfo>div.userDetails>span";
	$(userDetails + '.firstname').html(json.firstName);
	$(userDetails + '.lastname').html(json.lastName);
	$(userDetails + '.major').html(json.major.replace("+", " "));

	getCourses(json.id);

}

function getCourses(id){
	var settings = {
	  "async": true,
	  "crossDomain": true,
	  "url": link + "student/" + id + "/getCourses",
	  "method": "GET",
	  "headers": {}
	}

	$.ajax(settings).done(function (response) {
		var result = JSON.stringify(response);
		result = JSON.parse(result);
		// console.log("result length is " + result.length);
		// 
		if (result.length > 0) {
			// console.log(result[0].name);
			var professor = "";
			for (var i = 0; i < result.length; i++) {
				if (result[i].professor == null) {
					addClass(result[i].id, result[i].name, "Some Day/Time", "Bhola");
				}else{
					addClass(result[i].id, result[i].name, "Some Day/Time", (result[i].professor.firstName + " " + result[i].professor.lastName));
				}
				
			}
			$('#tableData').html(tableString);
		}else{
			// console.log("result is 0");
		}
		
	  // console.log(tableString);
	});
}



$(document).ready(function(){
	loadContent(true);
	setTimeout(function(){
		getParams();
	}, 50);
	// getParams();


});