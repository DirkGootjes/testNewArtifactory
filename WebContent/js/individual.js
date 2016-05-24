function processSave(){
	resetErrorAndInfo();
	var langCode = document.getElementById('unicodeLanguage').value;
	if(langCode != ""){
		var individualName = document.getElementById('unicode_indiv_name').value;
		IndividualDetailsJS.saveIndividualDetails(langCode,individualName,{
			  callback:function(result) {	
			   if(result.success){	  	   
				    var individualName =  result.indivName;
				    document.getElementById('unicode_indiv_name').value = individualName;
				    displayInfo(result.successMessage);
				    document.getElementById('delete_btn').disabled = false;
		    	}else{
				  	displayError(result.errorMsg);
			  	}
			  }
		});
	}else{
		displayError("Please select a language");
	}
}

function processDelete(answer){
	resetErrorAndInfo();
	if(answer=='YES'){
		var langCode = document.getElementById('unicodeLanguage').value;
		if(langCode!= ""){
			IndividualDetailsJS.deleteIndividualDetails(langCode,{
				  callback:function(result) {
				  	if(result.success){
				  		document.getElementById('unicode_indiv_name').value = "";
					  	displayInfo(result.successMessage);
					  	document.getElementById('delete_btn').disabled = true;
				  	}else{
					  	displayError(result.errorMsg);
				  	}
				    
				  }
			});
		}else{
				displayError("Please select a individual to be deleted.");
		}
	}
	var delete_div = document.getElementById('deleteConfirm');
	delete_div.style.display = 'none';
}
function getConfirmation(){
	resetErrorAndInfo();
	var selected_lang = document.getElementById('unicodeLanguage').value;
	if(selected_lang != ""){
		var delete_div = document.getElementById('deleteConfirm');
		delete_div.style.display = '';
		var bodydiv = document.getElementById('bodyDiv');
		bodydiv.scrollTop = bodydiv.scrollHeight;
		document.body.scrollTop = 1000;
	}else{
		displayError("Please select a individual detail to be deleted.");
	}
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
	var delete_div = document.getElementById('deleteConfirm');
	delete_div.style.display = 'none';
	
	var errorDiv = document.getElementById('errorDiv');
	var errorTD = document.getElementById('errorTD');
	errorTD.innerHTML  = '';
	errorDiv.style.display = 'none';
	
	var infoDiv = document.getElementById('infoDiv');
	var infoTD = document.getElementById('infoTD');
	infoTD.innerHTML  = '';
	infoDiv.style.display = 'none';
}
function resetDeleteButton(text){
	if(text != ""){
	    document.getElementById('delete_btn').disabled = false;
    }else{
    	document.getElementById('delete_btn').disabled = true;
    }
}