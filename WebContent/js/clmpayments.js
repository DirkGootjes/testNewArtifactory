window.history.forward(1);
window.onbeforeunload = confirmExit;
function processSave(){
	resetErrorAndInfo();
	var beans = new Array();

	var totalrows = document.getElementById('total_rows').value;
	var atLeastOneChange = false;
	
	for(var i=0;i<=totalrows;i++){
		var _new_unicode = document.getElementById('unicode_value_'+i).value;
		var _old_unicode = document.getElementById('unicode_copy_value_'+i).value;
		var _clcpt_id = document.getElementById('hidden_payment_id_'+i).value;
		var _clclc_code = document.getElementById('hidden_clclcCode_'+i).value;
		var _clclc_calc_code = document.getElementById('hidden_clclcCalcCode_'+i).value;
		bean = {
			clcptId : _clcpt_id,
			clclcCode : _clclc_code,
			clclcCalcCode : _clclc_calc_code,
			calcFreeTextUni : _new_unicode
		}
		beans[beans.length] = bean;
		if(_old_unicode != _new_unicode){
			atLeastOneChange = true
		}
	}
	if(!atLeastOneChange){
		displayInfo("No change to save!!!");
	}else{
		MaintainClaimsPaymentJS.saveClaimsPaymentText(beans,{
			callback:function(result) {
				if(result.success){
					makeCopy();
					displayInfo(result.successMessage);
				}else{
					displayError(result.errorMessage);
				}
			}
		});
	}
}

function makeCopy(){
	var totalrows = document.getElementById('total_rows').value;
	for(var i=0;i<=totalrows;i++){
		var text = document.getElementById('unicode_value_'+i).value;
		document.getElementById('unicode_copy_value_'+i).value = text ;
	}
}
function confirmExit(){
	var totalrows = document.getElementById('total_rows').value;
	for(var i=0;i<=totalrows;i++){
		var text = document.getElementById('unicode_value_'+i).value;
		var copy_text = document.getElementById('unicode_copy_value_'+i).value;
		if(text != copy_text){
			return ("You have not saved the changes you have made at row "+(parseInt(i)+1));
		}
	}
	// clear http session
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

function jspFunction(id) {
	document.getElementById("valueTextArea").value = document.getElementById(id).value;
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


