var lastIndex = -1;
var unicode_text_global = null;

function loadFirstAtPageLoad(formMode){

if(formMode == 'VIEW'){
	document.getElementById('unicode_text').disabled = true;
	document.getElementById('cancel_btn').disabled = true;
	document.getElementById('save_btn').disabled = true;
}

}

function processSave(){
	resetErrorAndInfo();
	var variableText = document.getElementById('unicode_text').value;
	if(variableText.length > 0)
	{		
		FreetextDetailsJS.saveFreetextDetails(variableText,{
			  callback:function(result) {
			    if(result.success){	   
				    var variableText =  result.variableText;
				    unicode_text_global = variableText;
				    document.getElementById('unicode_text').value = variableText;
				    getUnicode();
				    displayInfo(result.successMessage);
				    document.getElementById('cancel_btn').disabled = false;
				    document.getElementById('save_btn').disabled = true;
		    	}else{
				  	displayError(result.errorMsg);
			  	}
			  }
		});		
	}
	else
	{
		FreetextDetailsJS.deleteFreetextDetails({
			  callback:function(result) {
			  	if(result.success){
					  		document.getElementById('unicode_text').value = "";
					  		var variableText = document.getElementById('unicode_text').value;
					  		unicode_text_global = variableText;
					  		getUnicode();
						  	displayInfo(result.successMessage);
						  	document.getElementById('cancel_btn').disabled = true;
						  	document.getElementById('save_btn').disabled = true;
						  	lastIndex = -1;
					  	}else{
						  	displayError(result.errorMsg);
					  	}
			    
			  }
		});	
	}
}

function processReset(textDescription){
	resetErrorAndInfo();
		FreetextDetailsJS.resetFreetextDetails({
			  callback:function(result) {
			  	if(result.success){
			  				var variableText =  result.variableText;
					  		document.getElementById('unicode_text').value = variableText;
					  		var variableText = document.getElementById('unicode_text').value;
					  		unicode_text_global = variableText;
					  		getUnicode();
						  	displayInfo(result.successMessage);
						  	document.getElementById('cancel_btn').disabled = true;
						  	document.getElementById('save_btn').disabled = true;
						  	lastIndex = -1;
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

function resetCancelButton(textDescription){
	if(textDescription != ""){
	    document.getElementById('cancel_btn').disabled = false;
    }else{
    	document.getElementById('cancel_btn').disabled = true;
    }
}

function resetSaveButton(textDescription){
	if(textDescription != ""){	    
	    document.getElementById('save_btn').disabled = false;
    }else{
	    document.getElementById('save_btn').disabled = true;
    }
}

function resetIndex(element){
	if(element.id == 'unicode_text'){
		lastIndex = getCaret(element);
	}else{
		lastIndex = -1;
	}
}

function getCaret(el) { 
  if (el.selectionStart) { 
    return el.selectionStart; 
  } else if (document.selection) { 
    el.focus(); 

    var r = document.selection.createRange(); 
    if (r == null) { 
      return 0; 
    } 

    var re = el.createTextRange(),rc = re.duplicate(); 
    re.moveToBookmark(r.getBookmark()); 
    rc.setEndPoint('EndToStart', re); 

    return rc.text.length; 
  }  
  return 0; 
}

function getUnicode()
{
	variableText = document.getElementById('unicode_text').value; 

}
  
window.onbeforeunload = confirmExit;
function confirmExit()
{
	if (unicode_text_global != variableText)
    {
    	return "   You have not saved the changes you have made."
    }
}