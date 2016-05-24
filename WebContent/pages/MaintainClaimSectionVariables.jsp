<!--
  * MaintainClaimSectionVariables.jsp

  * Created on: 26 Aug 2014
  * Version   : 1.0
  * Author    : INVSAR1
  *  
-->
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="GENERATOR" content="Rational Application Developer">
<meta http-equiv='cache-control' content='no-cache'>
<meta http-equiv='expires' content='0'>
<meta http-equiv='pragma' content='no-cache'>

<link rel="stylesheet"
	href="<c:out value='${contextPath}' />/skins/gn/css/datepickr.css"
	type="text/css">
	<script type="text/javascript"
	src="<c:out value='${contextPath}' />/js/jquery.js"></script>
<script type="text/javascript" language="javascript"
	src="<c:out value='${contextPath}' />/dwr/interface/MaintainClaimSectionVariablesJS.js?version=<c:out value='${buildDate}' />"></script>
<script type="text/javascript"
	src="<c:out value='${contextPath}' />/js/claimsecvar.js?version=<c:out value='${buildDate}' />"></script>
<script type="text/javascript"
	src="<c:out value='${contextPath}' />/js/datepickr.js?version=<c:out value='${buildDate}' />"></script>

<script>
    function jspFunction(v,typ)
    {
    	if (typ == '') {
    		 document.getElementsByName(v)[0].disabled =true;
    	}
    	if(typ == 'D') {
			
    	} 
    	document.getElementsByName(v)[0].style.backgroundColor = "White";
        var val = document.getElementsByName(v)[0].value;
  
        document.getElementsByName("valueTextArea")[0].value = val;
    }
    function jspValidation(v,typ)
    {
        var val = document.getElementsByName(v)[0].value;
        if(typ == 'N') {
	        if(isNaN(val)==true) {
	        	alert('Please enter valid number!!!');
	       	  document.getElementsByName(v)[0].value='';
	       	  document.getElementsByName(v)[0].focus();
	        }
        } else if (typ == 'D') {
        	var pattern = new RegExp(/^(0?[1-9]|[12][0-9]|3[01])[\/\-\.](0?[1-9]|1[012])[\/\-\.](\d{4})$/);
    		var match = pattern.exec(val);
    		if (match == null){
    			alert('Please enter valid date(dd-mm-yyyy)!!!');
				document.getElementsByName(v)[0].value='';
    	  		document.getElementsByName(v)[0].focus();
    	   		document.getElementsByName("valueTextArea")[0].value = '';
    		} else {
    		    var year = match[3];
       	     	var month = match[2]*1;
      	      	var day = match[1]*1;
      	      	var date = new Date(year, month - 1, day); // because months starts from 0.
				if(!(date.getFullYear() == year && date.getMonth() == (month - 1) && date.getDate() == day)) {
					alert('Please enter valid date(dd-mm-yyyy)!!!');
					document.getElementsByName(v)[0].value='';
    	  			document.getElementsByName(v)[0].focus();
    	   			document.getElementsByName("valueTextArea")[0].value = '';
				}
    		}
        } else if (typ == 'C') {
        		if(val.length >3500){
        			alert('Please enter data less than 3500 characters');
        			document.getElementsByName(v)[0].value='';
    	  			document.getElementsByName(v)[0].focus();
    	   			document.getElementsByName("valueTextArea")[0].value = '';
        		}
        }
        
    }
    function toggleFiled(check,index,maxLength){
    
    	var section = document.getElementById("secType"+index).value;
    	var letter = document.getElementById("letter_type").value;
    	if(letter == "CLPLC001" && section == "COS") {
    		MaintainClaimSectionVariablesJS.checkCostSplit({
			  callback:function(result) {	
			   if(result.success == false){
				  	alert(result.errorMessage);
				  	check.checked = false;
			  	}
			  }
		});
    	}
    	
    	
    	
    	for(var i=0; i < maxLength; i++) {
    		if(section == document.getElementById("secType"+i).value) {
			    var sec = document.getElementById("chkSelected"+i);
    			var element = document.getElementById("value"+i);
    			if(check.checked){
    				element.disabled = false;
    				sec.checked = true;
    			}else{
	    			sec.checked = false;
	    			element.disabled = true;
	    			element.style.backgroundColor = "White";
    			}
    		}
    	}
    }
    var dateIndex = new Array();
    </script>
<body>

<c:set var="isReadOnly" value=""/>

<c:if test="${sessionScope.readOnly eq 'Y'}">
	<c:set var="isReadOnly"  value="readonly" />
</c:if>


<table width=100% cellspacing=0 cellpadding=0 border=0>
	<tr>
		<td height="18" bgcolor="#e7e7e7" class="mainheader2">Create
		Letters - Unicode</td>
	</tr>
</table>
<div id='errorDiv' style='display:none'><br>
<table width=100% cellspacing=0 cellpadding=0 class='err'>
	<tr>
		<td id="errorTD"></td>
	</tr>
</table>
<br>
</div>
<div id='infoDiv' style='display:none'><br>
<table width=100% cellspacing=0 cellpadding=0 class='err1'>
	<tr>
		<td id="infoTD"></td>
	</tr>
</table>
<br>
</div>
<c:choose>
	<c:when test='${error_message != null}'>
		<br>
		<table width=100% cellspacing=0 cellpadding=0 class='err'>
			<tr>
				<td><c:out value='${error_message}' /></td>
			</tr>
		</table>
	</c:when>

	<c:otherwise>
		<table width=100% cellspacing=1 cellpadding=0 border=0>
			<tr>
				<td height="18" width='30%' class='bodyfont2' nowrap><b>Case
				No&nbsp;:&nbsp;</b><c:out value="${sessionScope.header.caseId}" /></td>
				<td height="18" width='70%' class='bodyfont2' nowrap colspan=2><b>Status&nbsp;:&nbsp;</b><c:out
					value="${sessionScope.header.caseType}" /></td>
			</tr>
			<tr>
				<td height="18" width='30%' class='bodyfont2' nowrap><b>Customer
				Id&nbsp;:&nbsp;</b><c:out value="${sessionScope.header.custId}" /></td>
				<td height="18" width='35%' class='bodyfont2' nowrap><b>Customer
				Name&nbsp;:&nbsp;</b><c:out value="${sessionScope.header.custName}" /></td>
				<td height="18" width='35%' class='bodyfont2' nowrap><b>Customer
				Lang.&nbsp;:&nbsp;</b><c:out value="${sessionScope.header.lang}" /></td>
			</tr>
		</table>
		<br>
		<br>
		<input type="hidden" id="caseId" value="${sessionScope.header.caseId}">

		<table width=100% cellspacing=2 cellpadding=0 border=0>
			<tr>
				<td height="18px" bgcolor="#e7e7e7" class="mainheader2" width="8%"
					nowrap>Print Date</td>
				<td height="18px" bgcolor="#e7e7e7" class="mainheader2" width="8%"
					nowrap>Sent To Print</td>
				<td height="18px" bgcolor="#e7e7e7" class="mainheader2" width="8%"
					nowrap>Letter Type</td>
				<td height="18px" bgcolor="#e7e7e7" class="mainheader2" width="35%"
					nowrap>Letter Description</td>
				<td height="18px" bgcolor="#e7e7e7" class="mainheader2" width="20%"
					nowrap>Print Type</td>
				<td height="18px" bgcolor="#e7e7e7" class="mainheader2" width="15%"
					nowrap>Our Contact</td>	
				<td height="18px" bgcolor="#e7e7e7" class="mainheader2" width="6%"
					nowrap>Via Br.</td>
			</tr>

			<tr>
				<td height="18px" class='bodyfont2' nowrap><c:out
					value="${sessionScope.prtDate}" /></td>
				<td height="18px" class='bodyfont2' nowrap><c:out
					value="${sessionScope.prtdDate}" /></td>
				<td height="18px" class='bodyfont2' nowrap><c:out
					value="${sessionScope.letterType}" /></td>
				<td height="18px" class='bodyfont2' nowrap><c:out
					value="${sessionScope.letterDes}" /></td>
				<td height="18px" class='bodyfont2' nowrap><c:out
					value="${sessionScope.printDes}" /></td>
				<td height="18px" class='bodyfont2' nowrap><c:out
					value="${sessionScope.atradiusContactId}" /></td>	
				<td height="18px" class='bodyfont2' nowrap><c:choose>
					<c:when
						test="${(sessionScope.viaBrokerFlag eq 'Y') or (sessionScope.viaBrokerFlag eq 'y')}">
						<input disabled="disabled" type="checkbox" checked="checked">
					</c:when>
					<c:otherwise>
						<input disabled="disabled" type="checkbox">
					</c:otherwise>
				</c:choose></td>
			</tr>
		</table>
		<br>
		<input type="hidden" id="letter_type" value="${sessionScope.letterType}" >
		<table width=100% cellspacing=1 cellpadding=0 border=0>
			<tr>
				<td height="18" width='30%' class='bodyfont2' nowrap><b>Section/Variables(+)</b></td>
			</tr>
		</table>
				
		<c:set var="totalRows" value="0" />
		<div >
		<table  cellspacing=0 cellpadding=2 border=0 id='scrollabletable'>
		<thead>
			<tr>
				<th height="18px" bgcolor="#666666" class="mainheader2" width='7%'><font color="white">Sel.</font></th>
				<th height="18px" bgcolor="#666666" class="mainheader2" width='10%'><font color="white">Section</font></th>
				<th height="18px" bgcolor="#666666" class="mainheader2" width='30%'><font color="white">Description</font></th>
				<th height="18px" bgcolor="#666666" class="mainheader2" width='7%'><font color="white">Req.</font></th>
				<th height="18px" bgcolor="#666666" class="mainheader2" width='20%'><font color="white">Variable Type</font></th>
				<th height="18px" bgcolor="#666666" class="mainheader2" width='20%'><font color="white">Value</font></th>
				<th height="18px" bgcolor="#666666" class="mainheader2" width='6%'><font color="white">Req.</font></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${sessionScope.result}" varStatus="i">
				<c:set var="secFlag" value="-1" />
				<c:set var="totalRows" value="${i.index}" />
				<c:set var="isSelected" value="N"/>
				
				<input id="clvteSeq${i.index}" type="hidden" value="${sessionScope.result[i.index].clvteSeq}">
				
				<c:set var="variableValue" value="" />
				<c:forEach var="temp" items="${sessionScope.selectedSec}" varStatus="j">
					<c:if test="${(sessionScope.selectedSec[j.index].clstcSectType eq sessionScope.result[i.index].clstcSectType) and (sessionScope.selectedSec[j.index].clvteSeq eq sessionScope.result[i.index].clvteSeq)}">
						<c:set var="secFlag" value="${j.index}" />
						<c:set var="variableValue" value="${temp.textUNI}" />
					</c:if>
				</c:forEach>
				
				<c:set var="bgcolor">
						<c:choose>
							<c:when test="${i.index%2 eq 0}">#FFFFFF</c:when>
							<c:otherwise>#e7e7e7</c:otherwise>
						</c:choose>
				</c:set>

				<tr id="row${i.index}">
					<td width='7%' align="left" height="18" class='bodyfont2'
						bgcolor="<c:out value='${bgcolor}' />" >

						
						<c:choose>
							<c:when
								test="${(sessionScope.result[i.index].mandatoryFlag eq 'Y') or (sessionScope.result[i.index].mandatoryFlag eq 'y')}">
								<input type="checkbox" id="chkSelected${i.index}" name="chkSelected" checked="checked"
									disabled="disabled" value="${i.index}">
								<input type="hidden" name="section_check_${i.index}" value="disabled" >
								<c:set var="isSelected" value="Y"/>	
							</c:when>
							<c:when
								test="${(secFlag ne -1) and ((sessionScope.result[i.index].mandatoryFlag ne 'Y') and (sessionScope.result[i.index].mandatoryFlag ne 'y'))}">
								<c:choose>
									<c:when test="${sessionScope.readOnly eq 'Y'}">
										<input type="checkbox" id="chkSelected${i.index}" name="chkSelected" checked="checked"
										disabled="disabled" value="${i.index}">
										<input type="hidden" name="section_check_${i.index}" value="disabled"  >	
									</c:when>
									<c:otherwise>
										<input type="checkbox" id="chkSelected${i.index}" name="chkSelected" checked="checked"
											value="${i.index}" onclick="toggleFiled(this,<c:out value='${i.index}' />,<c:out value='${fn:length(sessionScope.result)}' />)">
										<input type="hidden" name="section_check_${i.index}" value="checked" >	
									</c:otherwise>
								</c:choose>
								<c:set var="isSelected" value="Y"/>	
							</c:when>
							<c:otherwise>
								<c:choose>
									<c:when test="${sessionScope.readOnly eq 'Y'}">
										<input type="checkbox" id="chkSelected${i.index}" name="chkSelected" value="${i.index}" disabled="disabled">
										<input type="hidden" name="section_check_${i.index}" value="disabled"  >	
									</c:when>
									<c:otherwise>
										<input type="checkbox" id="chkSelected${i.index}" name="chkSelected" value="${i.index}" onclick="toggleFiled(this,<c:out value='${i.index}' />,<c:out value='${fn:length(sessionScope.result)}' />)">
										<input type="hidden" name="section_check_${i.index}" value="unchecked"  >	
									</c:otherwise>
								</c:choose>
							</c:otherwise>
						</c:choose>
						
					</td>
					<td width='10%' align="left" height="18" class='bodyfont2'
						bgcolor="<c:out value='${bgcolor}' />" >
						<input id="secType${i.index}" type="text" class='textfield_effect_small1' readonly="readonly" value="${sessionScope.result[i.index].clstcSectType}">
					</td>
					
					<td width='30%' align="left" height="18" class='bodyfont2'
						bgcolor="<c:out value='${bgcolor}' />" >
						<input readonly="readonly" type="text" class='textfield_effect' value="<c:out value='${sessionScope.result[i.index].des}' />" >
					</td>
					
					<td width='7%' align="left" height="18" class='bodyfont2'
						bgcolor="<c:out value='${bgcolor}' />"  >
						<c:choose>
							<c:when
								test="${(sessionScope.result[i.index].mandatoryFlag eq 'Y') or (sessionScope.result[i.index].mandatoryFlag eq 'y')}">
								<input disabled="disabled" type="checkbox" checked="checked">
							</c:when>
							<c:otherwise>
								<input disabled="disabled" type="checkbox">
							</c:otherwise>
						</c:choose>
					</td>
					
					<td width='20%' align="left" height="18" class='bodyfont2'
						bgcolor="<c:out value='${bgcolor}' />" >
						<input type="text"  class='textfield_effect_med' readonly="readonly" value="${sessionScope.result[i.index].varDes}">
					</td>
					
					<td width='20%' align="left" height="18" class='bodyfont2'
						bgcolor="<c:out value='${bgcolor}' />" >
						<textarea  class='textfield_effect_med' style="overflow: hidden;"
									id="value${i.index}" name="value${i.index}"
									onkeyup="jspFunction('value${i.index}','${sessionScope.result[i.index].varType}')"
									onclick="jspFunction('value${i.index}','${sessionScope.result[i.index].varType}')"
									onchange="jspValidation('value${i.index}','${sessionScope.result[i.index].varType}')"
									<c:out value='${isReadOnly}' /> 
									<c:if test="${isSelected eq 'N'}" >
										disabled="disabled"
									</c:if>
									><c:out value="${variableValue}" /></textarea>
						
						<input type="hidden" name="copy_value_${i.index}" value="<c:out value='${variableValue}' />" >
						
						<c:if test="${sessionScope.result[i.index].varType eq 'D'}">
							<c:if test="${sessionScope.readOnly ne 'Y'}">
							&nbsp;<img src="<c:out value='${contextPath}' />/skins/gn/images/cal.jpg"
								width="20" height="20" id="tvalue${i.index}" name="tvalue${i.index}">
							<script type="text/javascript"> 
								dateIndex[dateIndex.length] = "<c:out value='${i.index}' />";
							</script>
							</c:if>
						</c:if>
					</td>
					
					<td width='6%' align="left" height="18" class='bodyfont2'
						bgcolor="<c:out value='${bgcolor}' />" >
						<c:choose>
							<c:when
								test="${(sessionScope.result[i.index].varMandatoryFlag eq 'Y') or (sessionScope.result[i.index].varMandatoryFlag eq 'y')}">
								<input disabled="disabled" type="checkbox" name="reqVal"
									checked="checked">
							</c:when>
							<c:otherwise>
								<input disabled="disabled" name="reqVal" type="checkbox">
							</c:otherwise>
						</c:choose>
					</td>
					
				</tr>
			
			</c:forEach>
			<input type=hidden name="total_rows" value="<c:out value='${totalRows}' />">
			</tbody>
		</table>
		</div>
			
			<c:if test="${totalRows gt 8}" >
				<script type="text/javascript">
					 doNeedScollify = true;
				</script>
			</c:if>
			
			<script type="text/javascript"> 
				for(var i=0;i<dateIndex.length;i++){
					var textId = 'tvalue'+dateIndex[i];
					new datepickr(textId, { 
									'dateFormat': 'd-m-Y'
					});		
				}
			</script>
		
	</c:otherwise>
</c:choose>
<br>


<table>
<tr>
<td>
	<table>
	<tr>
		<td height="25" class="bodyfont2" width='15%'><b>Variable Text<br>(view only)&nbsp;</b></td>
		<td height="18" class="bodyfont2"><textarea	class='textarea_effect' id='valueTextArea' readonly="readonly"
		rows='5' cols='150' name="valueTextArea" onfocus="this.blur()"></textarea></td>
	</tr>
	</table>
</td>
</tr>

<tr>
	<td height="25" colspan=2>&nbsp;</td>
</tr>

<tr>
	<td height="25" class="bodyfont2" width='15%' colspan=2>
	<table align='left' border="0" cellpadding="5" cellspacing="0">
		<tr>
			<c:if test="${sessionScope.readOnly ne 'Y'}">
			<td>
			<table border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="24"><img
						src="<c:out value='${contextPath}' />/skins/gn/images/lft.gif"
						width="24"></td>
					<td
						background="<c:out value='${contextPath}' />/skins/gn/images/but_bg.gif">
					<input id="save_btn" name="save_btn" type="button"
						class="superbutton" value='Save'
						onClick='javascript:saveUnicode();' /></td>
					<td width="24"><img
						src="<c:out value='${contextPath}' />/skins/gn/images/rt.gif"></td>
				</tr>
			</table>
			</td>
			</c:if>
			<td>
			<table border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="24"><img
						src="<c:out value='${contextPath}' />/skins/gn/images/lft.gif"
						width="24"></td>
					<td
						background="<c:out value='${contextPath}' />/skins/gn/images/but_bg.gif">
					<input id="back_btn" type="button" class="superbutton"
						value='Back' onClick="killNow()"
						title='Close the screen. Your will get an confirmation alert if you have not saved the changes' /></td>
					<td width="24"><img
						src="<c:out value='${contextPath}' />/skins/gn/images/rt.gif"></td>
				</tr>
			</table>
			</td>
		</tr>
	</table>
	</td>
</tr>
</table>

</body>
