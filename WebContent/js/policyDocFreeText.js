function loadFirstAtPageLoad(formMode){

	if(formMode == 'VIEW'){
		document.getElementById('unicode_text').readOnly = true;
		document.getElementById('save_btn').disabled = true;
		
	}

}

function processSavePolicyFreeText(){
	resetErrorAndInfo();
	var uniocde_value=document.getElementById('unicode_text').value;
	
	if(uniocde_value){ 
		if(uniocde_value.length>3900){
		 alert('Free text Unicode text length should not exceed 3900 characters.');
		 return;
		}
		}
	 	PolicyDocFreeTextJS.savePolicyDocFreeTextDetails(document.getElementById('unicode_text').value,{	
			callback:function(result) {
				    if(result.success){	   
					    var descFreeText =  result.descFreeText;				    
					    document.getElementById('unicode_text').value = descFreeText;				   
					    displayInfo(result.successMessage);				    
			    	}else{
					  	displayError(result.errorMsg);
				  	}
				  }
		});
	

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

