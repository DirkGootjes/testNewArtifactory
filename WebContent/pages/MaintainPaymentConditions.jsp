<!--
  * MaintainPaymentConditions.jsp

  * Created on: 29 DEC 2015
  * Version   : 1.0
  * Author    : INHYOU1
  *
  * Change Details
  * Date        Name            Version             Comments          
  *-------------------------------------------------------------------
  *   
-->
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/WEB-INF/tld/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<meta http-equiv='cache-control' content='no-cache'>
<meta http-equiv='expires' content='0'>
<meta http-equiv='pragma' content='no-cache'>
<script type="text/javascript"
	src="<c:out value='${contextPath}' />/dwr/interface/PaymentConditionsDescAJAXJS.js?version=<c:out value='${buildDate}' />"></script>
<script type="text/javascript"
	src="<c:out value='${contextPath}' />/js/maintainPaymentConditions.js?version=<c:out value='${buildDate}' />"></script>
<script type="text/javascript"
	src="<c:out value='${contextPath}' />/js/jquery.js?version=<c:out value='${buildDate}' />"></script>
	
<body onload="javascript:loadFirstAtPageLoad('<c:out value='${formMode}'/>');">
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

		<div align='left'>
		<br>
		<br>
		<br>	
	<table width=60% cellspacing=1 cellpadding=0 border=0 align='left'>
		<tr>
			<td>	
				<table class="box_textarea_label">
					<tr>				
						<!-- <td  class='bodyfont2'>&nbsp;&nbsp;PC per&nbsp;&nbsp;</td>
						<td  class='bodyfont2'>&nbsp;&nbsp;PC per Typ&nbsp;&nbsp;</td>
						<td  class='bodyfont2'>&nbsp;&nbsp;Typ&nbsp;&nbsp;</td>
						<td  class='bodyfont2'>&nbsp;&nbsp;Description&nbsp;&nbsp;</td> -->
						
						<td class="bodyfont5" >PC per&nbsp;&nbsp;&nbsp;</td>
						<td class="bodyfont5" >&nbsp;PC per Typ&nbsp;</td>
						<td class="bodyfont5">Typ&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
						<td class="bodyfont5">&nbsp;Description&nbsp;&nbsp;&nbsp;&nbsp;</td>
						<td class="bodyfont5">&nbsp;Valid Flag&nbsp;&nbsp;&nbsp;&nbsp;</td>
						
											</tr>
				        
							<tr>
									 <%-- <td>
										&nbsp;&nbsp;<c:out value="${PCper}" />&nbsp;&nbsp;
									</td>
									<td>
										&nbsp;&nbsp;<c:out value="${PCperTyp}" />&nbsp;&nbsp;
									</td>
									<td>
										&nbsp;&nbsp;<c:out value="${Typ}" />&nbsp;&nbsp;
									</td>
									<td>
										&nbsp;&nbsp;<c:out value="${Description}" />&nbsp;&nbsp;
									</td> --%>
									<td class="table_row_border_font1" nowrap>${PCper} &nbsp;&nbsp;&nbsp;&nbsp;</td>
									<td class="table_row_border_font1" nowrap>${PCperTyp}
									</td>
									<td class="table_row_border_font1" nowrap>${Typ}&nbsp;&nbsp;&nbsp;</td>
									<td class="table_row_border_font1" nowrap>${Description}</td>
									
									<td>
										<c:choose>
											<c:when test="${ValidFlag eq 'Y' }">
												<input type="checkbox" align='middle' checked="checked" disabled="disabled" nowrap/>
											</c:when>
											<c:otherwise>
												<input type="checkbox" align='middle' disabled="disabled" nowrap />
											</c:otherwise>
										</c:choose>
									</td>
									
									
							</tr>		
							</table>
							
			</td>
			</tr>
			<tr>
			<td>
				<table width=100% >
					<tr><td colspan=2>&nbsp;</td></tr>
					
					<tr>
						 <td colspan=2> 
					 	<table bgcolor="silver" align="left" valign="middle" class="bodyfont3">    
								<tr>
									<td><u>Payment Condition Description</u></td>
								</tr>
							 </table> 
						 </td> 
						
					</tr>
					<tr><td colspan=2></td></tr>
					<tr><td colspan=2></td></tr>
					
					<tr>
						<td height="18" nowrap>
							 <table cellspacing=0 cellpadding=0  class="box_textarea_label"> 
							
								<tr>
										<td class="bodyfont5" class="box_textarea_label" >Lang Code&nbsp;&nbsp;&nbsp;</td>
										<td class="bodyfont5" class="box_textarea_label">Description&nbsp;&nbsp;&nbsp;</td>
										
								</tr>
								<tr><td colspan=4></td></tr>
								<tr>
																					
										<td  nowrap align="center" valign="middle">${LangCode}</td>												
										
										<td nowrap>
											<textarea id='paymentDesc' rows='7' cols='104' class='textarea_effect' style="text-align:left; overflow: auto;" >${PCDescription}</textarea>
										</td>
									</tr>
								</table>
							</td>
					</tr>
					<tr><td colspan=2></td></tr>
					<tr><td colspan=2></td></tr>
					<tr>
						<td colspan=2>
							<table  align='right' border="0" cellpadding="0" cellspacing="0">
								<tr>
									<td>
										<input id="save_btn" name="save_btn" type="button"
												class="superbutton_pol_unicode" value='Save'
												onClick='javascript:processSavePaymentConditionDesc();' />
											
										<input name="reset_btn" type="button" class="superbutton_pol_unicode"
												value='Exit'
												onClick='javascript:closeWindow();'/>
									</td>
								</tr>
							</table>
						</td>
					</tr>	
					
				</table>
			</td>
		</tr>
	</table>
		</div><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>	
	</c:otherwise>
</c:choose>
</body>
<script>
$("textarea").keyup(function(event) {
    var maxLength = 4000;
    var length = this.value.length;
    if (length > maxLength) {
        //reassign substring of max length to text area value
        this.value = this.value.substring(0, maxLength);
        //alert(maxLength + ' characters allowed, excess characters trimmed');
    }
});
</script>


