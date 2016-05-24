var browser = 'IE'; // internet explorer
function testCSS(prop) {
    return prop in document.documentElement.style;
}
if (testCSS('MozBoxSizing')){
	browser ='FF'; //firefox
}
else if (testCSS('WebkitTransform')){
	browser ='GC'; // google chrome
}

function logOff(){
	window.open('logout.do', '_self');
}
function registerTimer(){
	CommonJS.startTimer({
		callback:function(result) {},
		async:false
	});
	
}
function deRegisterTimer(){
	CommonJS.stopTimer({
		callback:function(result) {},
		async:false
	});
}
function LoadWindow(){
	if(document.all){
		top.window.moveTo(0,0);
		top.window.resizeTo(screen.availWidth,screen.availHeight);
		top.window.focus();
	}
}


function closeWindow(){
	 window.open('','_self','');
        window.close();
}