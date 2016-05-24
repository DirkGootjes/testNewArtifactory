var lastIndex = -1;
var selectedLangs = new Array();
var selectedText= new Array();
var selectedLangForDelete= "";
var globalResult = new Array();
var globalLang = null;

function loadLangDetails(){
        resetErrorAndInfo();
		var choice = document.getElementById('sequence').value;	
		var choiceDetail = document.getElementById('choiceDetail');		
		choiceDetail.options[choice-1].selected = true;
		MultiChoiceConditionCodeDetailsJS.loadLangDetails(choice,{
			  callback:function(resultText) {
			  //changes done for defect 5873
			  for(i=0;i<(resultText.length);i++)
			  {
			  	if(resultText[i].langCode!=null){
			  		globalResult[i]=resultText[i];
			  	}
			  }
			  renderNewValuesText(globalResult);
			  removeOptions(document.getElementById("varSeq"));
			  removeOptions(document.getElementById("variableName"));
			  renderNewValuesVar(resultText);
	}
    });    
}

function loadFirstAtPageLoad(){
        resetErrorAndInfo();
		
		var choicenumber = document.getElementById('sequence');
		var choiceDetail = document.getElementById('choiceDetail');
		choicenumber.options[0].selected = true;	
		choiceDetail.options[0].selected = true;
		MultiChoiceConditionCodeDetailsJS.loadLangDetails(1,{
		
			  callback:function(resultText) {
			  //changes done for defect 5873
			  for(i=0;i<(resultText.length);i++)
			  {
			  	if(resultText[i].langCode!=null){
			  		globalResult[i]=resultText[i];
			  	}
			  }
			  
			  removeOptions(document.getElementById("varSeq"));
			  removeOptions(document.getElementById("variableName"));
			  renderNewValuesText(globalResult);
			  renderNewValuesVar(resultText);
		}
    });    
    document.getElementById('delete_btn').disabled = false;
    document.getElementById('save_btn').disabled = true;
}

function renderNewValuesText(resultText){
	var _div = document.getElementById("dynamic_div_text");
	_div.innerHTML = "";
	var tempHTML = "<table>";
	tempHTML = tempHTML+"<tr><td height='0' class='bodyfont2' nowrap width='2px'></td>"
	tempHTML = tempHTML+"<td><table style='border:2px solid black; width:2px' id='choiceLang'><tbody>";
	selectedLangs = new Array();
	selectedText= new Array();
	for(i=0;i<resultText.length;i++){
		
		tempHTML = tempHTML+"<tr><td height='0' class='bodyfont2' width='2px'></td><td height='18' class='bodyfont2' width='2px' align='left'>";
		tempHTML = tempHTML+"<input type=\"text\" class='textarea_effect1' id=\"unicodeLanguage_"+resultText[i].langCode+"\" readonly value=\""+resultText[i].langCode+"\" size=\" 2\""+"\" onfocus='processSelect(this.value);' align='left'";
		tempHTML=  tempHTML+"</td></tr>";
		selectedLangs[i] = resultText[i].langCode;		
	}
	tempHTML=  tempHTML+"</tbody></table></td><td height='0' class='bodyfont2' nowrap width='60'></td>";
	tempHTML=  tempHTML+"<td><table style='border:2px solid black; width:110px' id='choiceText'><tbody>";
	
	for(i=0;i<resultText.length;i++){		
		
		tempHTML=  tempHTML+"<tr><td height='18' class='bodyfont2' width='110'>";
		tempHTML = tempHTML+"<input type=\"text\" class='textarea_effect1' id=\"unicode_text_"+resultText[i].langCode+"\" value=\""+resultText[i].text+"\" size=\" 110\" onkeyup='resetSaveButton();' onfocus='javascript:scrollKey(\""+resultText[i].langCode+"\");' onchange='javascript:scrollKey(\""+resultText[i].langCode+"\");' align='left'";
		tempHTML=  tempHTML+"</td></tr>";
		selectedText[i] = resultText[i].text;
		
	}	
	tempHTML = tempHTML+"</tbody></table></td></tr><table>";
	_div.innerHTML = tempHTML;
	selectedLangForDelete="";
}


function scrollKey(langCode) {
	var eventCode = 0;
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
				            var posLang = document.getElementById('unicodeLanguage_'+globalResult[0].langCode);
			        		var posText = document.getElementById('unicode_text_'+globalResult[0].langCode);
				            posLang.focus();
				            posText.focus();				            
			        	}
			        	else{
				            var posLang = document.getElementById('unicodeLanguage_'+globalResult[currPos-1].langCode);
				            var posText = document.getElementById('unicode_text_'+globalResult[currPos-1].langCode);
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
			        		var posLang = document.getElementById('unicodeLanguage_'+globalResult[(rows.length-1)].langCode);
				            var posText = document.getElementById('unicode_text_'+globalResult[(rows.length-1)].langCode);
				            posLang.focus();
				            posText.focus();				            
			        	}
			        	else{
				            var posLang = document.getElementById('unicodeLanguage_'+globalResult[currPos+1].langCode);
				            var posText = document.getElementById('unicode_text_'+globalResult[currPos+1].langCode);
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
						var posLang = document.getElementById('unicodeLanguage_'+globalResult[currPos].langCode);
						var posText = document.getElementById('unicode_text_'+globalResult[currPos].langCode);	            
				 	}
				 	rows[i].onkeyup = function() {
				        var currPos = this.rowIndex;
				        var posLang = document.getElementById('unicodeLanguage_'+globalResult[currPos].langCode);
			            var posText = document.getElementById('unicode_text_'+globalResult[currPos].langCode);
				 	}
				 }
	        }
        }
	}
}

function processSelect(langCode){
	selectedLangForDelete=langCode;	
	for (i=0;i<globalResult.length;i++){
		var langBox = document.getElementById('unicodeLanguage_'+globalResult[i].langCode);		
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

function renderNewValuesVar(resultVariable){
	var _div = document.getElementById("dynamic_div_var");	
	
	var tempHTML = "<table><tr>";
	tempHTML = tempHTML+"<td height='18' width='50px' class='bodyfont2'>&nbsp;";
	tempHTML = tempHTML+"<select style='width:40px' id='varSeq' name='varSeq' size='3'>";
	
	for(i=0;i<resultVariable.length;i++)
	{
		var varSeq = document.getElementById('varSeq');
		if (resultVariable[i].buvteSeq > 0){
			tempHTML = tempHTML+"<option value=\""+addOptionItem(varSeq, resultVariable[i].buvteSeq);+"\"></option>";
		}
	}
	tempHTML = tempHTML+"</select></td>";
	tempHTML = tempHTML+"<td height='0' width='68px'></td>";
	tempHTML = tempHTML+"<td height='18' width='80px' class='bodyfont2' nowrap>";
	tempHTML = tempHTML+"<select style='width:65px' id='variableName' name='variableName' size='3'>";
	
	for(i=0;i<resultVariable.length;i++)
	{
		var variableName = document.getElementById("variableName");	
		if (resultVariable[i].buvteType != null){
			tempHTML = tempHTML+"<option value=\""+addOptionItem(variableName, resultVariable[i].buvteType);+"\"></option>";
		}
	}	
	tempHTML = tempHTML+"</select></td>";
	tempHTML = tempHTML+"<td height='18' width='60px' class='bodyfont2' nowrap></td>";
	tempHTML = tempHTML+"<td height='18' width='60px' class='bodyfont2' nowrap></td>";
	tempHTML = tempHTML+"</table></tr>";

}

function addOptionItem(optionSelect,optionText)    
{
        var option = document.createElement("option");
        option.value = optionText;
        option.text = optionText;
        optionSelect.options.add(option);
        return option;
}

function removeOptions(selectbox)
{
    var i;
    if (selectbox.options.length > 0)
    {
	    for(i=selectbox.options.length-1;i>=0;i--)
	    {
	        selectbox.remove(i);
	    }
	}
}


function processSave(){
resetErrorAndInfo();
selectedLangForDelete="";

	for(i=0;i<selectedLangs.length;i++){
		var lang = selectedLangs[i];
		var text = document.getElementById('unicode_text_'+lang).value;
		selectedText[i] = text;
	}
	var choice = document.getElementById('sequence').value;
	if(choice != ""){
	MultiChoiceConditionCodeDetailsJS.save(choice,selectedLangs,selectedText,{
			callback:function(newresult)
			{
				if(newresult[1].errorMsg == null ||newresult[1].errorMsg == "")
				{
				   	 renderNewValuesText(newresult);
				   	 globalResult=newresult;
				   	 displayInfo(newresult[1].successMessage);
				}
				else
				{
					if(newresult[1].errorMsg)
					{
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
	var resultDelete = new Array();
	var choice = document.getElementById('sequence').value;
	if(selectedLangForDelete!= ""){	
	var text = document.getElementById('unicode_text_'+selectedLangForDelete).value;
		MultiChoiceConditionCodeDetailsJS.deleteTextDetails(selectedLangForDelete,choice,text,{
			  callback:function(resultDeleteOld) {
			 
			  		var resultDeleteOldLen= (resultDeleteOld.length-1);			  		
			  		if(resultDeleteOld[resultDeleteOldLen].langCode == null){
			  			for(i=0;i<(resultDeleteOld.length-1);i++)
						{
							if(resultDeleteOld[i].langCode!=null){
								resultDelete[i]=resultDeleteOld[i];
							}
						}
			  		}else{
			  			resultDelete = resultDeleteOld;
			  		}			  		
			  
			      	if(resultDelete[1].errorMsg == null ||resultDelete[1].errorMsg == ""){
						renderNewValuesText(resultDelete);
						globalResult=resultDelete;
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

function resetSaveButton(){	
	document.getElementById('save_btn').disabled = false;
}

window.onbeforeunload = confirmExit;
function confirmExit()
{
    for(i=0;i<globalResult.length;i++)
    {
		var lang = globalResult[i].langCode;
		
		var currentText = document.getElementById('unicode_text_'+lang).value;
		if (globalResult[i].text != currentText ){
		  return ("You have not saved the changes you have made for language: "+lang);
		}
	}
}
