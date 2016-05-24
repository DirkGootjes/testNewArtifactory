function loadFirstAtPageLoad(formMode){

	if(formMode == 'VIEW'){
		document.getElementById('trade_desc_unicode_text').readOnly = true;
		document.getElementById('save_btn').disabled = true;
	}

}

function processSavePolicyTradeDesc(){
	resetErrorAndInfo();
	var uniocde_value=document.getElementById('trade_desc_unicode_text').value;
	if(uniocde_value){
		if(uniocde_value.length>800){
		 alert('Trade Description Unicode text length should not exceed 800 characters.');
		 return;
		}
		var oldDescFreetext=document.getElementById('trade_desc_unicode_text').value;
	 	PolicyDocCoverTradedescJS.saveCoverTradeDesc(document.getElementById('trade_desc_unicode_text').value,{	
			callback:function(result) {
				    if(result.success){	   
					    var tradeDesc =  result.tradeDesc;				    
					    document.getElementById('trade_desc_unicode_text').value = tradeDesc;				   
					    displayInfo(result.successMessage);				    
			    	}else{
					  	displayError(result.errorMsg);
				  	}
				  }
		});
	}else{
		displayError("Please enter value in trade description");
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

function displayError(errorMsg){
	var errorDiv = document.getElementById('errorDiv');
	var errorTD = document.getElementById('errorTD');
	errorTD.innerHTML  = errorMsg;
	errorDiv.style.display = '';
	document.getElementById('bodyDiv').scrollTop = 0;
	window.scrollTo(0, 0);
}

