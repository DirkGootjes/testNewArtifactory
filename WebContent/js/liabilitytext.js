window.history.forward(1);
window.onbeforeunload = confirmExit;
function processSave(){
	resetErrorAndInfo();
	var codes = new Array();
	var texts = new Array();
	var totalrows = document.getElementById('total_rows').value;
	var atLeastOneChange = false;
	
	for(var i=0;i<=totalrows;i++){
		var code = document.getElementById('hidden_code_value'+i).value;
		var text = document.getElementById('value'+i).value;
		var copy_text = document.getElementById('copy_value'+i).value;
		codes[i] = code;
		texts[i] = text;
		if(copy_text != text){
			atLeastOneChange = true
		}
	}
	if(!atLeastOneChange){
		displayInfo("No change to save!!!");
	}else{
		MaintainLiabilityTextJS.saveLiabilityText(codes,texts,{
			callback:function(result) {
				if(result.success){
					makeCopy();
					displayInfo(result.successMessage);
				}else{
					displayError(result.errorMsg);
				}
			}
		});
	}
}
function processSimpleSave(){
	var codes = new Array();
	var texts = new Array();
	var totalrows = document.getElementById('total_rows').value;

	for(var i=0;i<=totalrows;i++){
		var code = document.getElementById('hidden_code_value'+i).value;
		var text = document.getElementById('value'+i).value;
		codes[i] = code;
		texts[i] = text;
	}
	MaintainLiabilityTextJS.saveLiabilityText(codes,texts,{
		callback:function(result) {
			killNow();
		}
	});
}
function makeCopy(){
	var totalrows = document.getElementById('total_rows').value;
	for(var i=0;i<=totalrows;i++){
		var text = document.getElementById('value'+i).value;
		document.getElementById('copy_value'+i).value = text ;
	}
}
function confirmExit(){
	var totalrows = document.getElementById('total_rows').value;
	for(var i=0;i<=totalrows;i++){
		var text = document.getElementById('value'+i).value;
		var copy_text = document.getElementById('copy_value'+i).value;
		if(text != copy_text){
			return ("You have not saved the changes you have made at row "+(parseInt(i)+1));
		}
	}
	killSession();
}
function killSession(){
	$.ajax({     
		url : "SessionKiller",
		type: "POST",
		async:false
	}); 
}
function killNow(){
	window.open('', '_self', '');
	window.close();
}

function jspFunction(v) {
	var val = document.getElementsByName(v)[0].value;
	document.getElementsByName("valueTextArea")[0].value = val;
}
function displayError(errorMsg){
	var errorDiv = document.getElementById('errorDiv');
	var errorTD = document.getElementById('errorTD');
	errorTD.innerHTML  = errorMsg;
	errorDiv.style.display = '';
	document.getElementById('bodyDiv').scrollTop = 0;
	window.scrollTo(0, 0);
}
function displayInfo(info){
	var infoDiv = document.getElementById('infoDiv');
	var infoTD = document.getElementById('infoTD');
	infoTD.innerHTML  = info;
	infoDiv.style.display = '';
	document.getElementById('bodyDiv').scrollTop = 0;
	window.scrollTo(0, 0);
}
function resetErrorAndInfo(){

	var errorDiv = document.getElementById('errorDiv');
	var errorTD = document.getElementById('errorTD');
	errorTD.innerHTML  = '';
	errorDiv.style.display = 'none';
	
	var infoDiv = document.getElementById('infoDiv');
	var infoTD = document.getElementById('infoTD');
	infoTD.innerHTML  = '';
	infoDiv.style.display = 'none';
}


