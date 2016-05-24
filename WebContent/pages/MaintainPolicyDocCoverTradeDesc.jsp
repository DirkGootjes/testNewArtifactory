<!--
  * MaintainPolicyDocCoverTradeDesc.jsp

  * Created on: 23 SEP 2014
  * Version   : 1.0
  * Author    : INKPAG1
  *
  * Change Details
  * Date        Name            Version             Comments          
  *-------------------------------------------------------------------
  *   
-->
<%@ taglib uri="/WEB-INF/tld/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<meta http-equiv='cache-control' content='no-cache'>
<meta http-equiv='expires' content='0'>
<meta http-equiv='pragma' content='no-cache'>
<script type="text/javascript"
	src="<c:out value='${contextPath}' />/dwr/interface/PolicyDocCoverTradedescJS.js?version=<c:out value='${buildDate}' />"></script>
<script type="text/javascript"
	src="<c:out value='${contextPath}' />/js/policyDocCoverTradeDesc.js?version=<c:out value='${buildDate}' />"></script>
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
	
		
		<table width=50%>
			<tr>
				<td>
					<table>
						<jsp:include page="policyInfoHeader.jsp"></jsp:include>			
						<tr><td colspan=2>&nbsp;</td></tr>
					</table>
					<table>
						<tr>
							<td>
								<table class="box_textarea_label">
									<tr>
										<td nowrap>
										&nbsp;&nbsp; Trade Description
										</td>
									</tr>
								</table>
							</td>
							
						</tr>
						<tr><td colspan=2></td></tr>
						<tr>
							<td>
								<table class="box_textarea_label">
								<tr>
									
									<td height="18" class="bodyfont2" nowrap>
										&nbsp;&nbsp; Description&nbsp;:&nbsp;<br/> <br/>
										&nbsp;&nbsp; 
										<textarea id='trade_desc_unicode_text' rows='5' cols='70' class='textarea_effect' style="text-align:left" >${tradeDesc}</textarea>
									</td>
								</tr>
								
								<tr>
									<td colspan=2>
										<table width='48%' align='right' border="0" cellpadding="0" cellspacing="0">
											<tr>
												<td>
													<input id="save_btn" name="save_btn" type="button"
														class="superbutton_pol_unicode" value='Save'
														onClick='javascript:processSavePolicyTradeDesc();' />
											
													<input name="reset_btn" type="button" class="superbutton_pol_unicode"
														value='Exit'
														onClick='javascript:closeWindow()' />
												</td>
											</tr>
										</table>
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
    var maxLength = 800;
    var length = this.value.length;
    if (length > maxLength) {
        //reassign substring of max length to text area value
        this.value = this.value.substring(0, maxLength);        
    }
});
</script>


