var state = 0;

function assignStrings(){
	console.log(state);
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
				$('input[type=submit]').click(function(e){
					e.preventDefault();
					loginAllower(true);
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

function loginAllower(login){
	if (login) {
		animateWrapper(0);
		setTimeout(function(){
			window.location.href = "dashboard/";
		}, 200);		
	}
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