var mainHeight;

var adjustMain = function (id) {
	var maxHeight = 2000;
	var main = document.getElementById(id);
	main.height = maxHeight;
	main.style.height = maxHeight + "px";
	window.scrollTo(0, maxHeight);
	scrollTop = document.body.scrollTop;
	if (navigator.userAgent.indexOf('Firefox') >= 0
		|| navigator.userAgent.indexOf("MSIE") >= 0) {
	    scrollTop = document.documentElement.scrollTop;
	}
	mainHeight = maxHeight - scrollTop;
	main.height = mainHeight;
	main.style.height = mainHeight + "px";
	main.src = src;
};

var getMainHeight = function () {
	return mainHeight;
};