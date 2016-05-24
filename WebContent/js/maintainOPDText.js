function loadFirstAtPageLoad(formMode){

	if(formMode == 'VIEW'){
		document.getElementById('opdTagText').readOnly = true;
		document.getElementById('save_btn').disabled = true;
	}

}

function processSaveOPDTextDef(){
	resetErrorAndInfo();
	var uniocde_value=document.getElementById('opdTagText').value;
	if(uniocde_value){
		if(uniocde_value.length>4000){
		 alert('Trade Description Unicode text length should not exceed 4000 characters.');
		 return;
		}
		}
	 	MaintainOPDTextDefAJAXJS.saveOPDTagTextDef(document.getElementById('opdTagText').value,{	
			callback:function(result) {
				    if(result.success){	   
					    var opdTagTextDef =  result.opdTagTextDef;				    
					    document.getElementById('opdTagText').value = opdTagTextDef;				   
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

