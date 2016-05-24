var lastIndex = -1;
var selectedLangs = new Array();
var finalselectedLangs = new Array();
var selectedOldText= new Array();
var selectedText= new Array();
var selectedLangForDelete= "";
var globalResult=new Array();
var globalResultArray=new Array();
var lastResult = new Array();

function loadFirstAtPageLoad(){
        resetErrorAndInfo();			
		ConditionCodeDetailsJS.loadLangDetails({
			  callback:function(result) {			 
			  for(i=0;i<result.length;i++){			  	
			  	var lang = result[i].langCode;			  	
			  	selectedLangs[i] = result[i].langCode;
				selectedOldText[i] = document.getElementById('unicode_text_'+lang).value;
			  }
			  globalResultArray = result;
			  //for(i=0;i<selectedLangs.length-1;i++){
			   		finalselectedLangs=selectedLangs;
			  //}
			  globalResult=selectedOldText;
		}
    });
     document.getElementById('delete_btn').disabled = false;
     document.getElementById('save_btn').disabled = true;
}

function processSelect(langCode){
	selectedLangForDelete=langCode;	
	for (i=0;i<globalResultArray.length;i++){
		var langBox = document.getElementById('unicodeLanguage_'+globalResultArray[i].langCode);		
		if (langBox.value == langCode){
			changeTextBGColor(langCode);
		}
		else{
			langBox.style.backgroundColor = "#FFFFFF";
			langBox.style.color = "#000000";
		}
	}
	document.getElementById('unicode_text_'+langCode).focus();
	var txtBox=document.getElementById('unicode_text_'+langCode);
	txtBox.select(txtBox.value);
}

function changeTextBGColor(langCode){	
	var langBox = document.getElementById('unicodeLanguage_'+langCode);
	langBox.style.backgroundColor = "#333366";
	langBox.style.color = "#FFFFFF";
}

function scrollKey(langCode) {	
	var choiceText = document.getElementById('unicode_text_'+langCode);
	
	choiceText.onkeydown = function(event) {
		var event = window.event ? window.event : event;
	    if (true) {
	        if(event.keyCode == 38){
	        	var rows = document.getElementById('choiceText').getElementsByTagName('tbody')[0].getElementsByTagName('tr');
			    for (i = 0; i < rows.length; i++) {
			        rows[i].onkeyup = function() {
			        	var currPos = this.rowIndex;
			        	if(currPos == 0){
				            var posLang = document.getElementById('unicodeLanguage_'+selectedLangs[0]);
			        		var posText = document.getElementById('unicode_text_'+selectedLangs[0]);
				            posLang.focus();
				            posText.focus();				            
			        	}
			        	else{
				            var posLang = document.getElementById('unicodeLanguage_'+selectedLangs[currPos-1]);
				            var posText = document.getElementById('unicode_text_'+selectedLangs[currPos-1]);
				            posLang.focus();
				            posText.focus();
			            }
			        }
			    }
	        	
	        }
	        else if(event.keyCode == 40){
	        	var rows = document.getElementById('choiceText').getElementsByTagName('tbody')[0].getElementsByTagName('tr');
			    for (i = 0; i < rows.length; i++) {
			        rows[i].onkeyup = function() {
			        	var currPos = this.rowIndex;
			        	if(currPos == (rows.length-1)){			        		
			        		var posLang = document.getElementById('unicodeLanguage_'+selectedLangs[(rows.length-1)]);
				            var posText = document.getElementById('unicode_text_'+selectedLangs[(rows.length-1)]);
				            posLang.focus();
				            posText.focus();
			        	}
			        	else{
				            var posLang = document.getElementById('unicodeLanguage_'+selectedLangs[currPos+1]);
				            var posText = document.getElementById('unicode_text_'+selectedLangs[currPos+1]);
				            posLang.focus();
				            posText.focus();				            
			            }
			        }
			    }
	        }
	        else{
	        	var rows = document.getElementById('choiceText').getElementsByTagName('tbody')[0].getElementsByTagName('tr');
			    for (i = 0; i < rows.length; i++) {
					rows[i].onkeydown = function() {
						var currPos = this.rowIndex;
						var posLang = document.getElementById('unicodeLanguage_'+selectedLangs[currPos]);
						var posText = document.getElementById('unicode_text_'+selectedLangs[currPos]);		            
				 	}
				 	rows[i].onkeyup = function() {
				        var currPos = this.rowIndex;
				        var posLang = document.getElementById('unicodeLanguage_'+selectedLangs[currPos]);
			            var posText = document.getElementById('unicode_text_'+selectedLangs[currPos]);
				 	}
				 }
	        }
        }
	}
}

function processSave(){
resetErrorAndInfo();
selectedLangForDelete="";
	for(i=0;i<finalselectedLangs.length;i++){
		var lang = finalselectedLangs[i];
		var text = document.getElementById('unicode_text_'+lang).value;		
		selectedText[i] = text;	
	}
	
	if(finalselectedLangs.length >0 ){	
		ConditionCodeDetailsJS.save(finalselectedLangs,selectedText,selectedOldText,{
			callback:function(newresult)
			{
				if(newresult[1].errorMsg == null ||newresult[1].errorMsg == ""){
						      	for(i=0;i<newresult.length;i++){
									var lang = newresult[i].langCode;
									var oldText = newresult[i].text;
									selectedOldText[i] = oldText;
									var langBox = document.getElementById('unicodeLanguage_'+newresult[i].langCode);
									langBox.style.backgroundColor = "#FFFFFF";
									langBox.style.color = "#000000";
								}
								for(i=0;i<newresult.length;i++){			  	
									globalResult[i]=newresult[i].text;	
							
						  }	
						     	 displayInfo(newresult[1].successMessage);
						   	 }
				else{
				  if(newresult[1].errorMsg){
						   		           displayError(newresult[1].errorMsg);
						   		        }
					}
			}
		});			
	}
	else{
	   displayError("Please select a choice.");
	}
	
	document.getElementById('delete_btn').disabled = false;
    document.getElementById('save_btn').disabled = true;
	
}
function processDelete(){
	resetErrorAndInfo();
	if(selectedLangForDelete!= ""){
	var tempLang = selectedLangForDelete;
	var text = document.getElementById('unicode_text_'+selectedLangForDelete).value;
		ConditionCodeDetailsJS.deleteTextDetails(selectedLangForDelete,text,{
			  callback:function(resultDelete) {
			      	if(resultDelete[1].errorMsg == null ||resultDelete[1].errorMsg == ""){
			      	 for(i=0;i<resultDelete.length;i++){
						var lang = resultDelete[i].langCode;
						var oldText = resultDelete[i].text;
						selectedOldText[i] = oldText;
						var langBox = document.getElementById('unicodeLanguage_'+resultDelete[i].langCode);
						langBox.style.backgroundColor = "#FFFFFF";
						langBox.style.color = "#000000";
					}						 			     	 
			     	 // assigning new values retured after delete to selectedOldText
			     	 for(i=0;i<resultDelete.length;i++){
						var lang = resultDelete[i].langCode;						
						if(tempLang ==lang){
							document.getElementById('unicode_text_'+lang).value = resultDelete[i].text;
						}
						selectedOldText[i] = document.getElementById('unicode_text_'+lang).value;
						globalResult[i] = document.getElementById('unicode_text_'+lang).value;;
					}	
			     	 displayInfo(resultDelete[1].successMessage);
			   	 }
			   		 else{
			   		        if(resultDelete[1].errorMsg){
			   		           displayError(resultDelete[1].errorMsg);
						  	 
			   		        }
						  	
					  	}
			  }
		});
		selectedLangForDelete="";
	}
	else{
		displayError("Please select a language to delete the non-latin translation.");
	}
	document.getElementById('delete_btn').disabled = false;
    document.getElementById('save_btn').disabled = true;
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
function displayInfo(info){
	var infoDiv = document.getElementById('infoDiv');
	var infoTD = document.getElementById('infoTD');
	infoTD.innerHTML  = info;
	infoDiv.style.display = '';
	document.getElementById('bodyDiv').scrollTop = 0;
	window.scrollTo(0, 0);
}

function resetSaveButton(){	
	document.getElementById('save_btn').disabled = false;
}

window.onbeforeunload = confirmExit;
function confirmExit()
{  
    for(i=0;i<finalselectedLangs.length;i++){    	
		var lang = finalselectedLangs[i]		
		var currentText = document.getElementById('unicode_text_'+lang).value;
		if (globalResult[i] != currentText){		
		  return ("You have not saved the changes you have made for language: " +lang);
		}
	}
}