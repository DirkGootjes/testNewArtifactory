function loadFirstAtPageLoad(formMode){

	if(formMode == 'VIEW'){
		document.getElementById('paymentDesc').readOnly = true;
		document.getElementById('save_btn').disabled = true;
	}

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
function processSavePaymentConditionDesc(){
	
	resetErrorAndInfo();
	
	var uniocde_value=document.getElementById('paymentDesc');
	
	if(uniocde_value){
		if(uniocde_value.length>4000){
		 alert('PC Description Unicode text length should not exceed 4000 characters.');
		 return;
		}
		}
	PaymentConditionsDescAJAXJS.savePaymentDesc(document.getElementById('paymentDesc').value,{	
			callback:function(result) {
				    if(result.success){	   
					    var pcDescDef =  result.pcDescription;				    
					    document.getElementById('paymentDesc').value = pcDescDef;				   
					    displayInfo(result.successMessage);				    
			    	}else{
					  	displayError(result.errorMsg);
				  	}
				  }
		});
	
}
function displayError(errorMsg){
	var errorDiv = document.getElementById('errorDiv');
	var errorTD = document.getElementById('errorTD');
	errorTD.innerHTML  = errorMsg;
	errorDiv.style.display = '';
	document.getElementById('bodyDiv').scrollTop = 0;
	window.scrollTo(0, 0);
}