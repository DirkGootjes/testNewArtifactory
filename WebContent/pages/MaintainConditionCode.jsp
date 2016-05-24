<!--
  * MaintainConditionCode.jsp

  * Created on: 14 Nov 2013
  * Modified on: 30 Dec 2013
  * Version   : 1.0
  * Author    : INNBHA1
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
	src="<c:out value='${contextPath}' />/dwr/interface/ConditionCodeDetailsJS.js?">
</script>
<script type="text/javascript"
	src="<c:out value='${contextPath}' />/js/conditionCode.js?">
</script>
<body onload="javascript:loadFirstAtPageLoad();">
<div align="left"  style="border:2px solid black; width: 100%">		
	<table width=760px cellspacing=0 cellpadding=0>
		<tr>
			<td height="18" class="mainheader2">&nbsp;&nbsp;Unicode Text Translation</td>
		</tr>
	</table>
</div>
<div id='errorDiv' class = "errdiv">
	<table width=760px cellspacing=0 cellpadding=0 class='errtab'>
		<tr>
			<td id="errorTD"></td>
		</tr>
	</table>
</div>
<div id='infoDiv' class = "infodiv">
	<table width = 760px cellspacing=0 cellpadding=0 class="infotab">
		<tr>
			<td id="infoTD"></td>
		</tr>
	</table>
</div>
<c:choose>
	<c:when test='${error_message != null}'>
		<table width=100% cellspacing=0 cellpadding=0 class='errtab'>
			<tr>
				<td style="width: 760px"><c:out value='${error_message}' /></td>
			</tr>
		</table>
	</c:when>
	<c:otherwise>
		<div align="left"  style="border:2px solid black; width: 100%">		
			<table width=100% cellspacing=0 cellpadding=0 border=1 bordercolor="000000" align='left'>
				<tr>
					<table>
						<tr><td height="5"></td></tr>
						<tr>
							<td height="18" width="95px" class='bodyfont3' align="left" nowrap>&nbsp;&nbsp;Code&nbsp;&nbsp;</td>
							<td height="18" width=5%/>
							<td height="18" width="40px" class='bodyfont3' style="border:2px solid black;" nowrap>
								<c:out value="${conditioncode_details.code}"/>
							<td height="18" width=5%/>
							<td height="18" width="500px" class='bodyfont3' style="border:2px solid black" nowrap>
								<c:out value="${conditioncode_details.description}"/>
						</tr>			
					</table>
				</tr>				
				<tr>
					<table>
						<tr><td height="20" colspan="2"></td></tr>
						<tr>
							<td height="18" class='bodyfont3' nowrap colspan="2">&nbsp;&nbsp;Condition Texts&nbsp;&nbsp;</td>
						</tr>									
						<tr>
							<td height="18" class='bodyfont3' nowrap width="124">&nbsp;&nbsp;Language&nbsp;&nbsp;&nbsp;</td>
							<td height="18" class="bodyfont3" nowrap width="496">Text&nbsp;</td>
						</tr>
					</table>				
				<tr>		
					<div id='dynamic_div' align="left" style="overflow-y:scroll;height:200px;width:760px;overflow-x:hidden;border:0">
						<table width="1103">
							<tr>
								<td height="18" class="bodyfont2" nowrap width="1px"></td>
								<td>								
									<table style="border:2px solid black; width:2px" id="choiceLang">
										<tbody>		
											<c:forEach var="choice" items="${nonlatin_text_details}" varStatus="counter">
												<tr>
													<td height="18" class="bodyfont2" width="2px" >
														<input 
															id="unicodeLanguage_<c:out value='${choice.languageCode}'  />"
															type="text" class="textarea_effect1" readonly style="border:0"
															value="<c:out value='${choice.languageCode}'  />"
															onfocus="javascript:processSelect(this.value)" maxlength="2"
															height="18" size="2">
													</td>
												</tr>
											</c:forEach>
										</tbody>
									</table>
								</td>
								<td height="18" class="bodyfont2" nowrap width="28"></td>
								<td>
									<table style="border:2px solid black; width:113px" id="choiceText">
										<tbody>
											<c:forEach var="choice" items="${nonlatin_text_details}" varStatus="counter">
												<tr>
													<td height="18" class='bodyfont2' width="113">
														<input style="border:0"
															id="unicode_text_<c:out value='${choice.languageCode}'  />"
															onfocus='scrollKey("<c:out value='${choice.languageCode}'  />");'
															onkeyup='resetSaveButton();'
															type="text" class="textarea_effect1" style="border:0"
															value="<c:out value='${choice.description}'  />" 
															height="18" size="113">
													</td>
												</tr>
											</c:forEach>
										</tbody>
									</table>
								</td>
							</tr>
						</table>
					</div>
				</tr>
				<tr>
					<table>
						<tr><td height="20" width="124"></td></tr>
						<tr>
							<td height="18" class='bodyfont3' nowrap width="14%">&nbsp;&nbsp;&nbsp;Variable Types&nbsp;&nbsp;</td>
						</tr>	
						<tr>
							<td height="18" class='bodyfont3' nowrap width="124">&nbsp;&nbsp;&nbsp;Seq</td>
							<td height="18" class="bodyfont3" nowrap width="520">Code</td>
					</tr>
						<tr>
							<td height="18" class='bodyfont2' nowrap width="124">&nbsp;&nbsp;
								<select style="width:30px;" id="sequence" name="sequence" size="3">
									<c:forEach var="varseq" items="${condition_var_types}" varStatus="counter">
										<option value="<c:out value='${varseq.seq}'  />">
											<c:out value="${varseq.seq}" />
										</option>
									</c:forEach>
								</select>
							</td>
							<td height="18" class='bodyfont2' nowrap width="520">
								<select style="width:50px" id="choiceDetail" name="choiceDetail" size="3">
									<c:forEach var="vartypes" items="${condition_var_types}" varStatus="counter">
										<option value="<c:out value='${vartypes.buvteType}' />">
											<c:out value="${vartypes.buvteType}" />
										</option>
									</c:forEach>
								</select>
							</td>
						<td height="18" width="60px" class="bodyfont2" nowrap></td>
						<td height="18" width="60px" class="bodyfont2" nowrap></td>
					</tr>					
					</table>
				</tr>
				<tr>
					<table>
						<tr>
							<td>
								<table border="0" cellpadding="0" cellspacing="0">
									<tr>
										<td width="600"></td>
										<td width="11"><img src="<c:out value='${contextPath}' />/skins/gn/images/lft.gif" width="24"></td>
										<td background="<c:out value='${contextPath}' />/skins/gn/images/but_bg.gif">
											<input id="delete_btn" type="button" class="superbutton"
												value='Delete' onClick="javascript:processDelete();" /></td>
										<td width="24"><img src="<c:out value='${contextPath}' />/skins/gn/images/rt.gif"></td>
									</tr>
								</table>
							</td>
							<td>
								<table border="0" cellpadding="0" cellspacing="0">
									<tr>
										<td width="24"><img src="<c:out value='${contextPath}' />/skins/gn/images/lft.gif" width="24"></td>
										<td background="<c:out value='${contextPath}' />/skins/gn/images/but_bg.gif">
											<input id="save_btn" name="save_btn" type="button"
												class="superbutton" value='Save' onClick='javascript:processSave();' /></td>
										<td width="24"><img src="<c:out value='${contextPath}' />/skins/gn/images/rt.gif"></td>
									</tr>
								</table>
							</td>
							<td height="30px"></td>						
						</tr>
					</table>
				</tr>
				<tr/>				
			</table>
		</div>		
		<input type='hidden' id='rtl_langs' value='<c:out value='${rtl_langs}' />'>
	</c:otherwise>
</c:choose>
</body>			
			
				
		
		
		
		




