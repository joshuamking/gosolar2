/////////////////////////////////////////// link ///////////////////////////////////////////
// var link = "http://8000e316.ngrok.io/";
// http://8000e316.ngrok.io/resetServerData
var link = "http://localhost:8000/";

/////////////////////////////////////////// end link ///////////////////////////////////////////

var tableHeader = "<tr><th>Class Name</th><th>Day/Time</th><th>Instructor</th><th>Options</th></tr>";

var tableString = tableHeader;

var emerTableHeader = "<tr><th>Name</th><th>Phone Number</th><th>Options</th></tr>";
var emerTableString = emerTableHeader;

var login = true;
var student_id = "";

var jsonLoad = "";

function onLoad() {
	if (login) {

	} else {

	}
}

function addClass(id, classname, classday, professor) {
	tableString += "<tr><td>" + classname + "</td><td>" + classday + "</td><td>" + professor + "</td><td><div class=\"removeClass box\" id=\"submit_remove" + id + "\">Remove</div></td></tr>";
}

function addEmergency(id, name, phonenumber) {
	emerTableString += "<tr><td>" + name + "</td><td>" + phonenumber + "</td><td><div class=\"emRemoveClass box\" id=\"emSubmit_remove" + id + "\">Remove</div></td></tr>";
}

function checkLogin(json) {
	if (getCookie("loginStatus").toLowerCase() == "false") {


	} else {

	}


}

function loadContent(loggedin) {
	if (loggedin) {
		$('#mainWrapper').load('includes/profile.html');
	}
}

function getParams() {
	var url = decodeURIComponent(window.location.href);
	var begstr = "userData=";
	var endstr = "&submit=Submit";
	var beg = url.indexOf(begstr);
	var end = url.indexOf(endstr);
	var json = url.substring((beg + begstr.length), end);
	populate(json);

}

function populate(json) {
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

function getCourses(id) {
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
		// console.log(status);
		if (status == null) {
			tableString = "<tr style=\"width: 100%\"><td style=\"width: 100%\" colspan=\"0\">No Classes Yet</td></tr>";

		} else if (result.length > 0) {
			// console.log(result[0].name);
			for (var i = 0; i < result.length; i++) {
				var course = result[i];
				var professor = "TBA";
				var days = "TBA";
				var time = "TBA";
				if (course.professor != null) {
					professor = (course.professor.firstName + " " + course.professor.lastName);
				}
				if (course.startTime != null && course.endTime != null) {
					time = course.startTime.hour + ":" + course.startTime.minute + " - " + course.endTime.hour + ":" + course.endTime.minute;
				}
				if (course.days != null) {
					days = course.days;
				}
				addClass(course.id, course.name, days + " / " + time, professor);
			}
		} else {
			tableString = "<tr style=\"width: 100%\"><td style=\"width: 100%\" colspan=\"0\">No Classes Yet</td></tr>";
		}
		$('#tableData').html(tableString);
		assignClassRemoval();

		// console.log(tableString);
	});



}

function getEmer(json) {
	var emerC = JSON.parse(json);
	emerC = emerC.emergencyContacts;
	if (emerC.length > 0) {
		for (var i = 0; i < emerC.length; i++) {
			addEmergency(emerC[i].id, emerC[i].name.replace("+", " "), emerC[i].phoneNumber);
		}
	} else {
		emerTableString = "<tr style=\"width: 100%\"><td style=\"width: 100%\" colspan=\"0\">No Contacts Yet</td></tr>";
	}

	$('#tableDataE').html(emerTableString);
	assignEmerRemoval();
}

function getTrans(json) {
	var transP = JSON.parse(json);
}

function assignClassRemoval(){
	$('.removeClass').click(function(){
		removeClass(this.id);	
	});
}

function assignEmerRemoval(){
	$('.emRemoveClass').click(function(){
		removeContact(this.id);
	});
}

function getEmerStudent(id){
	var settings = {
	"async": true,
	"crossDomain": true,
	"url": link + "student/" + id,
	"method": "GET",
	"headers": {}
	}

	$.ajax(settings).done(function (response) {
		jsonLoad = response;
		getEmer(JSON.stringify(response));
	});
}

function removeClass(classId) {
	// console.log('the id is ' + classId);
	var str = "submit_remove";
	classId = classId.substring((classId.indexOf(str) + str.length), classId.length);
	// console.log(classId);
	var settings = {
	"async": true,
	"crossDomain": true,
	"url": link + "student/" + student_id + "/unregisterForCourse/" + classId,
	"method": "GET",
	"headers": {}
	}

	$.ajax(settings).done(function (response, status) {
		// console.log(response);
		tableString = tableHeader;
		getCourses(student_id);
	});
}

function removeContact(classId){
	var str = "emSubmit_remove";
	classId = classId.substring((classId.indexOf(str) + str.length), classId.length);
	var settings = {
	"async": true,
	"crossDomain": true,
	"url": link + "student/" + student_id + "/removeEmergencyContact/" + classId,
	"method": "GET",
	"headers": {}
	}

	$.ajax(settings).done(function (response, status) {
		// console.log(response);
		emerTableString = emerTableHeader;
		getEmerStudent(student_id);
		
	});
}

function addClassBuilder(id, cname, time, day, description){

}

function addClickHandler(type){
	$('.addContainer').css('display', 'block');
	setTimeout(function(){
		$('.addContainer').animate({
			opacity: 1
		}, 200);
	}, 50);
	switch (type){
		case 'class':
			// console.log('class');
			$('.addContainer>.cancel').html("Done");
			var settings = {
				"async": true,
				"crossDomain": true,
				"url": link + "course/",
				"method": "GET",
				"headers": {}
			}

			$.ajax(settings).done(function (response) {
				// $('.addContainer>.content').html(JSON.stringify(response));
				// console.log(response);
				var json = JSON.stringify(response);
				json = JSON.parse(json);
				$('.addContainer>.content').load('includes/addClassMock.html');
			});
			break;
		case 'contact':
			// console.log('contact');
			$('.addContainer>.cancel').html("Cancel");
			$('.addContainer>.content').load('includes/addContactForm.html');
			break;
		default:
			$('.addContainer>.cancel').html("Done");
			break;
	}
}

$(document).ready(function () {
	loadContent(true);
	setTimeout(function () {
		getParams();

	}, 50);
	$('.cancel').click(function(){
		$('.addContainer').animate({
			opacity: 0
		}, 200);
		setTimeout(function(){
			$('.addContainer').css('display', 'none');
		}, 400);
	});
});