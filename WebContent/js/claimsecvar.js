window.onbeforeunload = confirmExit;
var doNeedScollify = false;
$(document).ready(function(){
	if (doNeedScollify){
    	var tabObj = $('#scrollabletable');
        var uniqueId = scrolify(tabObj, 250); // 160 is height
     }
});
function scrolify(tblAsJQueryObject, height){
        var oTbl = tblAsJQueryObject;

        // for very large tables you can remove the four lines below
        // and wrap the table with <div> in the mark-up and assign
        // height and overflow property  
        var uniqueId = new Date().getTime();
        var oTblDiv = $("<div id='wrap_"+uniqueId+"'/>");
        oTblDiv.css('height', height);
        oTblDiv.css('overflow-y','auto');   
        oTblDiv.css('overflow-x','hidden');            
        oTbl.wrap(oTblDiv);

        // save original width
        oTbl.attr("data-item-original-width", oTbl.width());
        oTbl.find('thead tr td').each(function(){
            $(this).attr("data-item-original-width",$(this).width());
        }); 
        oTbl.find('tbody tr:eq(0) td').each(function(){
            $(this).attr("data-item-original-width",$(this).width());
        });                 


        // clone the original table
        var newTbl = oTbl.clone();

        // remove table header from original table
        oTbl.find('thead tr').remove();                 
        // remove table body from new table
        newTbl.find('tbody tr').remove();   

        oTbl.parent().parent().prepend(newTbl);
        newTbl.wrap("<div/>");

        // replace ORIGINAL COLUMN width                
        newTbl.width(newTbl.attr('data-item-original-width'));
        newTbl.find('thead tr td').each(function(){
            $(this).width($(this).attr("data-item-original-width"));
        });     
        oTbl.width(oTbl.attr('data-item-original-width'));      
        oTbl.find('tbody tr:eq(0) td').each(function(){
            $(this).width($(this).attr("data-item-original-width"));
        }); 
        return uniqueId;                
}

function saveUnicode(){
resetErrorAndInfo();
	var beanArray = new Array();
	var flag = 0;
	var bean;
	var chk_arr =  document.getElementsByName("chkSelected");
	var req_val_arr =  document.getElementsByName("reqVal");
	var chklength = chk_arr.length;
	for(var i=0; i < chklength; i++){
      if(chk_arr[i].checked){
       if(req_val_arr[i].checked) {
		if(document.getElementById("value"+i).value ==""){
				flag=1;
				document.getElementById("value"+i).style.backgroundColor = "Pink";
				
			}
       }
       if(flag == 1) {
       	 alert("Enter value for required fields!!!");
				break;
       }
      
		bean = {
			"secType":document.getElementById("secType"+i).value,
			"clvteSeq":document.getElementById("clvteSeq"+i).value,
			"value":document.getElementById("value"+i).value
		}
           beanArray.push(bean);
           
      }
	}  
	if(flag == 0) {
		MaintainClaimSectionVariablesJS.saveSectionVariables(beanArray,{
			  callback:function(result) {	
			   if(result.success){
				    displayInfo(result.successMessage);
				    //Added by INGKHAl FOR DEFECT 8006---start
				    var j = 0;
				    for(var i=0; i < chklength && j<result.secVariableValues.length; i++){
				    	if(chk_arr[i].checked){
				    		document.getElementById("value"+i).value = result.secVariableValues[j++];
				    	}
				    }
				    //Added by INGKHAl FOR DEFECT 8006---END
				    updateHidden();
		    	}else{
				  	displayError(result.errorMessage);
			  	}
			  }
		});
	}
	 
}
function updateHidden(){
	var totalrows = document.getElementById('total_rows').value;
	for(var i=0;i<=totalrows;i++){
		
		var section_text = document.getElementById('section_check_'+i).value;
		var chk_arr =  document.getElementsByName("chkSelected");
		
		if(section_text != "disabled"){
			if(chk_arr[i].checked){
				document.getElementById('section_check_'+i).value = "checked";
			}else if(!chk_arr[i].checked){
				document.getElementById('section_check_'+i).value = "unchecked";
				document.getElementById('value'+i).value = "";
			}
				
		}
		var text = document.getElementById('value'+i).value;
		document.getElementById('copy_value_'+i).value = text;
	}
}
function confirmExit(){
	var totalrows = document.getElementById('total_rows').value;
	for(var i=0;i<=totalrows;i++){
		var text = document.getElementById('value'+i).value;
		var copy_text = document.getElementById('copy_value_'+i).value;
		var section_text = document.getElementById('section_check_'+i).value;
		var chk_arr =  document.getElementsByName("chkSelected");
		if(text != copy_text){
			return ("You have not saved the changes you have made at row "+(parseInt(i)+1));
		}else if(section_text == "checked" && !chk_arr[i].checked){
			return ("You have not saved the changes you have made at row "+(parseInt(i)+1));
		}else if(section_text == "unchecked" && chk_arr[i].checked){
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