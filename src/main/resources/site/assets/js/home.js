/////////////////////////////////////////// link ///////////////////////////////////////////
// var link = "https://1644cae1.ngrok.io/";
var link = "http://localhost:8000/";
/////////////////////////////////////////// end link ///////////////////////////////////////////

var state = 0;

function assignStrings(){
	// console.log(state);
	switch(state){
		case 0:
			if($("*").hasClass('welcomeTitle')){
				state = 1;
				$('.welcomeTitle').html(welcomeTitle);
			}
			if($("*").hasClass('loginButton')){
				state = 1;
				$('.loginButton').html(loginButton);
				$('.loginButton').click(function(){
					displayWrapper(2);
				});
			}
		break;

		case 1:
			if($("*").hasClass('submit')){
				state = 2;
				$('.submit').html(loginButton);
				$('#submit').click(function(e){
					e.preventDefault();
					var formData = $("form:not(#hiddenForm)").serialize();
					var password = needleString("password=", formData);
					var username = needleString("username=", formData);
					// console.log(password);
					// console.log(username);
					validateLogin(username, password);
				});
			}
			if ($('*').hasClass('header')) {
				state = 2;
				$('.header').html(loginText);
			}
		break;
		default:
			if($("*").hasClass('welcomeTitle')){
				state = 1;
				$('.welcomeTitle').html(welcomeTitle);
			}
			if($("*").hasClass('loginButton')){
				state = 1;
				$('.loginButton').html(loginButton);
				$('.loginButton').click(function(){
					displayWrapper(2);
				});
			}
		break;
	}
}

function loadIntoContainer(load){
	switch(load){
		case 1: $('#mainWrapper').load("includes/welcome.html");
			break;
		case 2: $('#mainWrapper').load("includes/loginForm.html");
			break;
		default: $('#mainWrapper').load("includes/welcome.html");
			break;
	}
}

function displayWrapper(state){
	if (state == 1) {
		animateWrapper(1);
	}else{
		animateWrapper(0);
		setTimeout(function(){
			loadIntoContainer(state);
			setTimeout(function(){
				assignStrings();
				setTimeout(function(){
					$('#mainWrapper').animate({
						opacity: 1
					}, 200);
				}, 270);
			}, 220);
		}, 210);

	}
	
}



function jsonPost(){

}

function validateLogin(email, pass){
	var settings = {
	  "async": true,
	  "crossDomain": true,
	  "url": link + "login",
	  "method": "POST",
	  "headers": {
	    "content-type": "application/json"
	  },
	  "processData": false,
	  // "data": "{\n\t\"email\": \"pvenigandla2@student.gsu.edu\",\n\t\"password\": \"1234\"\n}"
	  "data": "{\n\t\"email\": \""+ unescape(email) +"\",\n\t\"password\": \""+ unescape(pass) +"\"\n}"
	}
	//   1234

	$.ajax(settings).done(function (response, status) {
		// console.log(response);
		console.log(status);
		// console.log(JSON.stringify(response));
		if (status != 403) {
			// console.log("response returned");
			loginAllower(JSON.stringify(response));
		}else{
			// console.log("false returned");
			loginAllower(false);
		}
	});
	// $.post('includes/loginForm.html', { email: uname, password: pword }, function(response, status){
		
	// 	// return "words";
	// });
}

function loginAllower(json){
	// console.log(json);
	if (json) {
		// console.log(true);
		// console.log("response returned");
		$('#hiddenFormInput').val(json);
		$('#hiddenSubmit').trigger('click');
		
	}else{
		// console.log(json);
		// console.log("false returned");
		$('#error').html("Incorrect username or password");
		// console.log(false);
	}
	// if (login) {
	// 	animateWrapper(0);

	// 	// console.log($("#bullshit").serialize());
	// 	setTimeout(function(){
	// 		window.location.href = "dashboard/";
	// 	}, 200);		
	// }
}







$(document).ready(function(){
	if (state == 0) {
		loadIntoContainer(1);
		setTimeout(function(){
			assignStrings();
			setTimeout(function(){
				displayWrapper(1);
			}, 100);
		}, 100);
	}	
});