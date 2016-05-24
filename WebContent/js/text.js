var lastIndex = -1;
function loadTextDetails(){
	resetErrorAndInfo();
	var langCode = document.getElementById('unicodeLanguage').value;
	var rtl_langs = document.getElementById('rtl_langs').value;
	var text_area = document.getElementById('unicode_text');
	if(rtl_langs !=""){
		if(rtl_langs.indexOf(langCode)>=0){
			text_area.setAttribute((document.all ? 'className' : 'class'),'textarea_effect_right'); 
		}else{
			text_area.setAttribute((document.all ? 'className' : 'class'),'textarea_effect'); 
		}
	}
	TextDetailsJS.loadTextDetails(langCode,{
		  callback:function(result) {
		    var languageName =  result.languageName;
		    var textDescription =  result.textDescription;
		    document.getElementById('language_name').value = languageName;
		    document.getElementById('unicode_text').value = textDescription;
		    resetDeleteButton(document.getElementById('unicode_text').value);
		    lastIndex = -1;
		    document.getElementById('unicode_text').focus();
		  }
	});
}
function processSave(){
	resetErrorAndInfo();
	var langCode = document.getElementById('unicodeLanguage').value;
	if(langCode != ""){
	var textDescription = document.getElementById('unicode_text').value;
		TextDetailsJS.saveTextDetails(langCode,textDescription,{
			  callback:function(result) {
			    if(result.success){	   
				    var textDescription =  result.textDescription;
				    document.getElementById('unicode_text').value = textDescription;
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
		TextDetailsJS.deleteTextDetails(langCode,{
			  callback:function(result) {
			  	if(result.success){
					  		document.getElementById('unicode_text').value = "";
						  	displayInfo(result.successMessage);
						  	document.getElementById('delete_btn').disabled = true;
						  	lastIndex = -1;
					  	}else{
						  	displayError(result.errorMsg);
					  	}
			    
			  }
		});
	}else{
		displayError("Please select a text type.");
	}
}
var delete_div = document.getElementById('deleteConfirm');
	delete_div.style.display = 'none';
}
function processUpdate(){		 
	var langCode = document.getElementById('unicodeLanguage').value;
	var textDescription = document.getElementById('unicode_text').value;
	TextDetailsJS.updateTextDetails(langCode,textDescription,{
		  callback:function(result) {	   
		    var textDescription =  result.textDescription;
		    document.getElementById('unicode_text').value = textDescription;
		  }
	});
}
function processInsertReference(){
	resetErrorAndInfo();
	var textDescription = document.getElementById('unicode_text').value;
	var insertString="<Policy Payment Conditions>";
	if(lastIndex == -1){
	    document.getElementById('unicode_text').value = textDescription+insertString;
	}else{
		document.getElementById('unicode_text').value = textDescription.substring(0, lastIndex) +
		insertString + textDescription.substring(lastIndex, textDescription.length);
		document.getElementById('unicode_text').focus();
	}
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
		displayError("Please select a text detail to be deleted.");
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
function resetDeleteButton(textDescription){
	if(textDescription != ""){
	    document.getElementById('delete_btn').disabled = false;
    }else{
    	document.getElementById('delete_btn').disabled = true;
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
	