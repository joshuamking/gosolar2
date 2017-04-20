/////////////////////////////////////////// functions ///////////////////////////////////////////

function animateWrapper(toggle){
	if (toggle == 1){
		$('#mainWrapper').animate({
			opacity: 1
		}, 200);
	}else{
		$('#mainWrapper').animate({
			opacity: 0
		}, 200);
	}
}

function getCookie(cname) {
    var name = cname + "=";
    var decodedCookie = decodeURIComponent(document.cookie);
    var ca = decodedCookie.split(';');
    for(var i = 0; i <ca.length; i++) {
        var c = ca[i];
        while (c.charAt(0) == ' ') {
            c = c.substring(1);
        }
        if (c.indexOf(name) == 0) {
            return c.substring(name.length, c.length);
        }
    }
    return "";
}

function needleString(needle, haystack){
	var spot = haystack.indexOf(needle);
	return haystack.substring((spot + needle.length), haystack.length);
}

/////////////////////////////////////////// end functions ///////////////////////////////////////////

