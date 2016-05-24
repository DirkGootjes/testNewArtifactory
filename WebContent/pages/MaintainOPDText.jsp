<!--
  * MaintainOPDText.jsp

  * Created on: 23 SEP 2014
  * Version   : 1.0
  * Author    : INKPAG1
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
	src="<c:out value='${contextPath}' />/dwr/interface/MaintainOPDTextDefAJAXJS.js?version=<c:out value='${buildDate}' />"></script>
<script type="text/javascript"
	src="<c:out value='${contextPath}' />/js/maintainOPDText.js?version=<c:out value='${buildDate}' />"></script>
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
				<table>
					<tr>				
						<td  class='bodyfont2'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Current tag&nbsp;&nbsp;&nbsp;&nbsp;</td>
						<td>
							<table class="box_textarea_label">
							<tr>
									<td>
										&nbsp;<c:out value="${opdTag}" />&nbsp;
									</td>
							</tr>		
							</table>
						</td>
						
						<td  class='bodyfont2'>&nbsp;&nbsp;&nbsp;&nbsp;Description&nbsp;&nbsp;</td>
						<td>
							<table class="box_textarea_label">
							<tr>
									<td>
										&nbsp;<c:out value="${currOpdTagDesc}" />&nbsp;
									</td>
							</tr>		
							</table>
						</td>
									
					</tr>
				</table>
				<table>
					<tr><td colspan=2>&nbsp;</td></tr>
					
					<tr>
						<td colspan=2>
							<table class="box_textarea_label">
								<tr>
									<td>Maintain letter text definitions</td>
								</tr>
							</table>
						</td>
						
					</tr>
					<tr><td colspan=2></td></tr>
					<tr><td colspan=2></td></tr>
					<tr>
						<td height="18" nowrap>
							<table cellspacing=0 cellpadding=0>
								<tr>
										<td class="bodyfont2">Organization&nbsp;&nbsp;&nbsp;</td>
										<td class="bodyfont2">Language&nbsp;&nbsp;&nbsp;</td>
										<td class="bodyfont2">Status&nbsp;&nbsp;&nbsp;</td>
										<td class="bodyfont2">Text</td>
								</tr>
								<tr><td colspan=4></td></tr>
								<tr>
										<td class="table_row_border_font" nowrap>${organization}</td>
												
										<td class="table_row_border_font" nowrap>${langCd}</td>												
										<td class="table_row_border_font" nowrap>${status}</td>
										<td class="table_row_border_font" nowrap>
											<textarea id='opdTagText' rows='10' cols='50' class='textarea_effect' style="text-align:left" >${opdText}</textarea>
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
												onClick='javascript:processSaveOPDTextDef();' />
											
										<input name="reset_btn" type="button" class="superbutton_pol_unicode"
												value='Exit'
												onClick='javascript:closeWindow();' />
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


