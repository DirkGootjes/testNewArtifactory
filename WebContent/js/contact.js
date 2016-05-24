window.history.forward(1);
function hideOrShow(){
	 resetErrorAndInfo();
	 
	 var tab = document.getElementById('contact_list');
	 var img = document.getElementById('arrow');
	 var stl = tab.style.display;
	 if(stl == ''){
		 tab.style.display = 'none';
		 img.src = document.getElementById('dwn_arw').value;
		 
	 }else{
		 tab.style.display = '';
		 img.src = document.getElementById('up_arw').value;
	 }
}
function loadContactDetails(index,langCode){
	resetErrorAndInfo();
	index = parseInt(index);
	document.getElementById('selected_lang').value = langCode;
	toggleDirection(langCode);
	ContactDetailsJS.loadContactDetails(index,langCode,{
		  callback:function(result) {
		  	if(result != null && (result.errorMsg == null || result.errorMsg == "")){
			    var latinContact =  result.latinContact;
			    var isUnicodePresent = result.unicodeIndividualAvailabe || result.unicodeOrgNameAvailabe || result.unicodeOrgAddressAvailabe;
			    if(isUnicodePresent){
			    	document.getElementById('copy_btn').disabled = true;
			    	document.getElementById('delete_btn').disabled = false;
			    }else{
				    document.getElementById('copy_btn').disabled = false;
				    document.getElementById('delete_btn').disabled = true;
				}
				    
			    if(result.latinIndividualAvailabe){
			    	document.getElementById('latin_indiv_name').value = latinContact.indivName;
			    	document.getElementById('latin_salutat_name').value = latinContact.salutation;
			    }else{
			    	document.getElementById('latin_indiv_name').value = "";
			    	document.getElementById('latin_salutat_name').value = "";
			    }
			    document.getElementById('latin_org_name_f').value = latinContact.orgNameFirstLine;
			    document.getElementById('latin_org_name_s').value = latinContact.orgNameSecondLine;
			    document.getElementById('latin_org_name_t').value = latinContact.orgNameThirdLine;
			    document.getElementById('latin_org_address_f').value = latinContact.orgAddressFirstLine;
			    document.getElementById('latin_org_address_s').value = latinContact.orgAddressSecondLine;
			    document.getElementById('latin_org_address_t').value = latinContact.orgAddressThirdLine;
			    document.getElementById('latin_org_city').value = latinContact.orgCityName;
			    document.getElementById('latin_org_post').value = latinContact.orgPostCode;
			    document.getElementById('latin_org_region').value = latinContact.orgRegion;
			    document.getElementById('latin_org_county').value = latinContact.orgCountryName;
			    
			    
			    var unicodeContact = result.unicodeContact;
			    if(result.latinIndividualAvailabe){
			        document.getElementById('utf_indiv_name').disabled = false;
				    document.getElementById('utf_salutat_name').disabled = false;
				    document.getElementById('utf_indiv_name').setAttribute((document.all ? 'className' : 'class'),'textfield_effect'); 
				    document.getElementById('utf_salutat_name').setAttribute((document.all ? 'className' : 'class'),'textfield_effect'); 
				    document.getElementById('utf_indiv_name').value = unicodeContact.indivName;
				    document.getElementById('utf_salutat_name').value = unicodeContact.salutation;
			    }else{
				    document.getElementById('utf_indiv_name').value = "";
				    document.getElementById('utf_salutat_name').value = "";
				    document.getElementById('utf_indiv_name').disabled = true;
				    document.getElementById('utf_salutat_name').disabled = true;
				    document.getElementById('utf_indiv_name').setAttribute((document.all ? 'className' : 'class'),'textfield_effect_disabled'); 
				    document.getElementById('utf_salutat_name').setAttribute((document.all ? 'className' : 'class'),'textfield_effect_disabled');
			    }
			    document.getElementById('utf_org_name_f').value = unicodeContact.orgNameFirstLine;
			    document.getElementById('utf_org_name_s').value = unicodeContact.orgNameSecondLine;
			    document.getElementById('utf_org_name_t').value = unicodeContact.orgNameThirdLine;
			    document.getElementById('utf_org_address_f').value = unicodeContact.orgAddressFirstLine;
			    document.getElementById('utf_org_address_s').value = unicodeContact.orgAddressSecondLine;
			    document.getElementById('utf_org_address_t').value = unicodeContact.orgAddressThirdLine;
			    document.getElementById('utf_org_city').value = unicodeContact.orgCityName;
			    document.getElementById('utf_org_post').value = unicodeContact.orgPostCode;
			    document.getElementById('utf_org_region').value = unicodeContact.orgRegion;
			    document.getElementById('utf_org_county').value = latinContact.orgCountryName;// uneditable
			    
		    }else{
		    	displayError(result.errorMsg);
		    }
		  }
	});
}
function processCopy(){
	resetErrorAndInfo();
	if(!document.getElementById('latin_indiv_name').disabled)
		document.getElementById('utf_indiv_name').value = document.getElementById('latin_indiv_name').value;
	if(!document.getElementById('latin_salutat_name').disabled)
		document.getElementById('utf_salutat_name').value = document.getElementById('latin_salutat_name').value;
	document.getElementById('utf_org_name_f').value = document.getElementById('latin_org_name_f').value;
	document.getElementById('utf_org_name_s').value = document.getElementById('latin_org_name_s').value;
	document.getElementById('utf_org_name_t').value = document.getElementById('latin_org_name_t').value;
	document.getElementById('utf_org_address_f').value = document.getElementById('latin_org_address_f').value;
	document.getElementById('utf_org_address_s').value = document.getElementById('latin_org_address_s').value;
	document.getElementById('utf_org_address_t').value = document.getElementById('latin_org_address_t').value;
	document.getElementById('utf_org_city').value = document.getElementById('latin_org_city').value;
	document.getElementById('utf_org_post').value = document.getElementById('latin_org_post').value;
	document.getElementById('utf_org_region').value = document.getElementById('latin_org_region').value;
	document.getElementById('utf_org_county').value = document.getElementById('latin_org_county').value;
}
function processSave(){
	resetErrorAndInfo();
	var selected_lang = document.getElementById('selected_lang').value;
	if(selected_lang != ""){
		
		var indiv_name = document.getElementById('utf_indiv_name').value;
		var salutat_name =  document.getElementById('utf_salutat_name').value;
		var org_name_f = document.getElementById('utf_org_name_f').value;
		var org_name_s = document.getElementById('utf_org_name_s').value;
		var org_name_t = document.getElementById('utf_org_name_t').value;
		var org_address_f = document.getElementById('utf_org_address_f').value;
		var org_address_s = document.getElementById('utf_org_address_s').value;
		var org_address_t = document.getElementById('utf_org_address_t').value;
		var org_city = document.getElementById('utf_org_city').value;
		var org_post = document.getElementById('utf_org_post').value;
		var org_region = document.getElementById('utf_org_region').value;
		var org_county = document.getElementById('utf_org_county').value;
		
		
		
		var unicodeContact = {
								indivName:indiv_name,
								salutation:salutat_name,
								orgNameFirstLine:org_name_f,
								orgNameSecondLine:org_name_s,
								orgNameThirdLine:org_name_t,
								orgAddressFirstLine:org_address_f,
								orgAddressSecondLine:org_address_s,
								orgAddressThirdLine:org_address_t,
								orgCityName:org_city,
								orgPostCode:org_post,
								orgRegion:org_region,
								orgCountryName:org_county
							 }
		
		ContactDetailsJS.saveUnicodeContactDetails(unicodeContact,selected_lang,{
			  callback:function(result) {
			  	if(result.success){
				  	document.getElementById('copy_btn').disabled = true;
			    	document.getElementById('delete_btn').disabled = false;
			    	displayInfo(result.successMessage);
			  	}else{
				  	displayError(result.errorMessage);
			  	}
			  }
		});
	}else{
		displayError("Please select a contact.");
	}
}
function getConfirmation(){
	var selected_lang = document.getElementById('selected_lang').value;
	if(selected_lang != ""){
		var delete_div = document.getElementById('deleteConfirm');
		delete_div.style.display = '';
		var bodydiv = document.getElementById('bodyDiv');
		bodydiv.scrollTop = bodydiv.scrollHeight;
		document.body.scrollTop = 1000;
	}else{
		displayError("Please select a contact.");
	}
}
function processDelete(answer){
	resetErrorAndInfo();
	if(answer=='YES'){
		var selected_lang = document.getElementById('selected_lang').value;
		if(selected_lang != ""){
			ContactDetailsJS.deleteUnicodeContactDetails(selected_lang,{
			callback:function(result) {
				  	if(result.success){
				  		document.getElementById('utf_indiv_name').value = "";
						document.getElementById('utf_salutat_name').value = "";
						document.getElementById('utf_org_name_f').value = "";
						document.getElementById('utf_org_name_s').value = "";
						document.getElementById('utf_org_name_t').value = "";
						document.getElementById('utf_org_address_f').value = "";
						document.getElementById('utf_org_address_s').value = "";
						document.getElementById('utf_org_address_t').value = "";
						document.getElementById('utf_org_city').value = "";
						document.getElementById('utf_org_post').value = "";
						document.getElementById('utf_org_region').value = "";
						document.getElementById('utf_org_county').value = document.getElementById('latin_org_county').value;
						document.getElementById('copy_btn').disabled = false;
			    		document.getElementById('delete_btn').disabled = true;
					  	displayInfo(result.successMessage);
				  	}else{
					  	displayError(result.errorMessage);
				  	}
				  }
			});
		}else{
			displayError("Please select a contact.");
		}
	}
	var delete_div = document.getElementById('deleteConfirm');
	delete_div.style.display = 'none';
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
function toggleDirection(langCode){
	var rtl_langs = document.getElementById('rtl_langs').value;
	if(rtl_langs.indexOf(langCode)>=0){
		document.getElementById('utf_indiv_name').dir = "rtl";
		document.getElementById('utf_salutat_name').dir = "rtl";
		document.getElementById('utf_org_name_f').dir = "rtl";
		document.getElementById('utf_org_name_s').dir = "rtl";
		document.getElementById('utf_org_name_t').dir = "rtl";
		document.getElementById('utf_org_address_f').dir = "rtl";
		document.getElementById('utf_org_address_s').dir = "rtl";
		document.getElementById('utf_org_address_t').dir = "rtl";
		document.getElementById('utf_org_city').dir = "rtl";
		document.getElementById('utf_org_post').dir = "rtl";
		document.getElementById('utf_org_region').dir = "rtl";
		document.getElementById('utf_org_county').dir = "rtl";
	}else{
		document.getElementById('utf_indiv_name').dir = "ltr";
		document.getElementById('utf_salutat_name').dir = "ltr";
		document.getElementById('utf_org_name_f').dir = "ltr";
		document.getElementById('utf_org_name_s').dir = "ltr";
		document.getElementById('utf_org_name_t').dir = "ltr";
		document.getElementById('utf_org_address_f').dir = "ltr";
		document.getElementById('utf_org_address_s').dir = "ltr";
		document.getElementById('utf_org_address_t').dir = "ltr";
		document.getElementById('utf_org_city').dir = "ltr";
		document.getElementById('utf_org_post').dir = "ltr";
		document.getElementById('utf_org_region').dir = "ltr";
		document.getElementById('utf_org_county').dir = "ltr";
	}
}
