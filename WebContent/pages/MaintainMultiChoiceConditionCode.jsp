<!--
  * MaintainMultiChoiceConditionCode.jsp

  * Created on: 14 Nov 2013
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
	src="<c:out value='${contextPath}' />/dwr/interface/MultiChoiceConditionCodeDetailsJS.js">
</script>
<script type="text/javascript"
	src="<c:out value='${contextPath}' />/js/multiChoiceConditionCode.js">
</script>
<body onload="javascript:loadFirstAtPageLoad();">
<div align="left"  style="border:2px solid black; width: 100%">	
	<table width=747px cellspacing=0 cellpadding=0>
		<tr>
			<td height="18" class="mainheader2">&nbsp;&nbsp;Unicode Multiple Choice Translations</td>
		</tr>
	</table>
</div>
<div id='errorDiv' class = "errdiv">
	<table width=747px cellspacing=0 cellpadding=0 class='errtab'>
		<tr>
			<td id="errorTD"></td>
		</tr>
	</table>
</div>
<div id='infoDiv' class = "infodiv">
	<table width=747px cellspacing=0 cellpadding=0 class='infotab'>
		<tr>
			<td id="infoTD"></td>
		</tr>
	</table>
</div>
<c:choose>
	<c:when test='${error_message != null}'>
		<table width=747px cellspacing=0 cellpadding=0 class='errtab'>
			<tr>
				<td><c:out value='${error_message}' /></td>
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
							<td height="18" width="95px" nowrap class='bodyfont3' align="left" nowrap>&nbsp;&nbsp;Code&nbsp;&nbsp;</td>
							<td height="18" width=5%/>
							<td height="18" width="40px" class='bodyfont3' style="border:2px solid black;" nowrap>
								<c:out value="${conditioncode_details.code}"/>
							<td height="18" width=5%/>
							<td height="18" width="450px" class='bodyfont3' style="border:2px solid black" nowrap>
								<c:out value="${conditioncode_details.description}"/>
						</tr>			
					</table>
				</tr>			
				<tr>
					<table>
						<tr><td height="20"></td></tr>
						<tr>
							<td height="18" nowrap width="80px" class='bodyfont3' nowrap>&nbsp;&nbsp;Choice&nbsp;&nbsp;</td>
							<td height="18" nowrap width="45px"/>
							<td height="18" nowrap class='bodyfont3' nowrap width="545">Description</td>
						</tr>
					</table>
				</tr>
				<tr>
					<div align="left">
						<table width=100%>
							<tbody>
								<tr>
									<td height="18" nowrap width="1px"></td>
									<td height="18" nowrap width="6px" class='bodyfont2'>
										<select class="select" style="width:6x; border:0px; overflow:inherit; disabled" id='sequence'
											name="sequence" size="5" 
											onchange="javascript:loadLangDetails('<c:out value='${sequence.index}' />',this.value);">
											<c:forEach var="choice" items="${choice_details}" varStatus="counter" >
												<option value="<c:out value='${choice.sequence}'  />">
													<c:out value='${choice.sequence}' /></option>
											</c:forEach>
										</select>
									</td>
									<td height="18" nowrap width="45px"></td>
									<td height="18" nowrap class='bodyfont2' width="555">
										<select style="width:500px; border:0px" id='choiceDetail' 
											name="choiceDetail" size="5">
											<c:forEach var="text" items="${choice_details}" varStatus="counter">
												<option value="<c:out value='${text.description}' />">
													<c:out value='${text.description}' /></option>
											</c:forEach>
										</select>
									</td>
								</tr>
							</tbody>
						</table>
					</div>
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
					<div id='dynamic_div_text' style="overflow-y:scroll;height:100px;width:747px;overflow-x:hidden">
						<table>							
							<tr>
								<td height="18" class="bodyfont2" nowrap width="2px"></td>
								<td>								
									<table style="border:2px solid black; width:2px" id="choiceLang" >
										<tbody>
											<c:forEach var="choice" items="${nonlatin_text_details}" varStatus="counter">
												<tr>								
													<td height="18" class='bodyfont2' width="2px" align="left">
														<input id="unicodeLanguage_<c:out value='${choice.langCode}'  />"
															type="text" class="textarea_effect1" 
															readonly value="<c:out value='${choice.langCode}'  />"
															onfocus='javascript:processSelect(this.value);'
															maxlength="2" size="2" align="left">
													</td>
												</tr>
											</c:forEach>
										</tbody>
									</table>
								</td>
								<td height="18" class="bodyfont2" nowrap width="60"></td>
								<td>
									<table style="border:2px solid black; width:110px" id="choiceText">
										<tbody>
											<c:forEach var="choice" items="${nonlatin_text_details}" varStatus="counter">
												<tr>
													<td height="18" class='bodyfont2' width="110">
														<input id="unicode_text_<c:out value='${choice.langCode}'  />"
															type="text" onkeyup='resetSaveButton();'
															class="textarea_effect1" style="border:0"
															value="<c:out value='${choice.text}'  />"
															onfocus='javascript:scrollKey("<c:out value='${choice.langCode}'  />");'
															onchange='javascript:scrollKey("<c:out value='${choice.langCode}'  />");'
															 size="110" align="left">
													</td>
												</tr>
											</c:forEach>
										</tbody>
									</table>
								</td>
								<script>
									var index = eval("<c:out value='${counter.count}'/>");
									var selectedLangs = new Array();
									selectedLangs[index] = '<c:out value='${choice.langCode}' />';
								</script>
							</tr>
						</table>
					</div>				
				</tr>				
				<tr>
					<table>
						<tr><td height="20" colspan="2"></td></tr>
						<tr>
							<td height="18" class='bodyfont3' nowrap colspan="2">&nbsp;&nbsp;Variable Types&nbsp;&nbsp;</td>
						</tr>
						<tr>
							<td height="18" class='bodyfont3' nowrap width="124">&nbsp;&nbsp;Seq&nbsp;&nbsp;&nbsp;</td>
							<td height="18" class="bodyfont3" nowrap width="496">Code&nbsp;</td>
						</tr>
					</table>
					<div id='dynamic_div_var' align="left">
						<table>
							<tr>		
								<td height="18" width="50px" class='bodyfont2'>&nbsp;
									<select style="width:40px" id="varSeq" size="3">										
										<c:forEach var="seq" items="${condition_var_types}" varStatus="counter">
											<option value="<c:out value='${seq.buvteSeq}'  />">
												<c:out value="${seq.buvteSeq}" />
											</option>
										</c:forEach>
									</select>
								</td>
								<td height="0" width="70px"></td>
								<td height="18" width="80px" class='bodyfont2' nowrap>
									<select style="width:65px" id="variableName" size="3">
										<c:forEach var="buvte" items="${condition_var_types}" varStatus="counter">
											<option value="<c:out value='${buvte.buvteType}' />">
												<c:out value="${buvte.buvteType}" />
											</option>
										</c:forEach>
									</select>
								</td>
								<td height="18" width="60px" class="bodyfont2" nowrap></td>
								<td height="18" width="60px" class="bodyfont2" nowrap></td>
							</tr>					
						</table>
					</div>
				</tr>
				<tr>
					<table>
						<tr>
							<td>
								<table border="0" cellpadding="0" cellspacing="0">
									<tr>
										<td width="738"></td>
										<td width="11"><img
											src="<c:out value='${contextPath}' />/skins/gn/images/lft.gif"
											width="24"></td>
										<td
											background="<c:out value='${contextPath}' />/skins/gn/images/but_bg.gif">
										<input id="delete_btn" type="button" class="superbutton"
											value='Delete' onClick="javascript:processDelete();" /></td>
										<td width="24"><img
											src="<c:out value='${contextPath}' />/skins/gn/images/rt.gif"></td>
									</tr>
								</table>
								</td>
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
											onClick='javascript:processSave();' /></td>
										<td width="24"><img
											src="<c:out value='${contextPath}' />/skins/gn/images/rt.gif"></td>
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