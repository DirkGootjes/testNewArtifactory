<!--
  * MaintainLiabilityText.jsp

  * Created on: 26 Aug 2014
  * Version   : 1.0
  * Author    : INVSAR1
  *  
-->
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="GENERATOR" content="Rational Application Developer">
<meta http-equiv='cache-control' content='no-cache'>
<meta http-equiv='expires' content='0'>
<meta http-equiv='pragma' content='no-cache'>

<script type="text/javascript"
	src="<c:out value='${contextPath}' />/js/jquery.js?version=<c:out value='${buildDate}' />"></script>	
<script type="text/javascript"
	src="<c:out value='${contextPath}' />/dwr/interface/MaintainLiabilityTextJS.js?version=<c:out value='${buildDate}' />"></script>
<script type="text/javascript"
	src="<c:out value='${contextPath}' />/js/liabilitytext.js?version=<c:out value='${buildDate}' />"></script>
<body>

<table width=100% cellspacing=0 cellpadding=0 border=0>
	<tr>
		<td height="18" bgcolor="#e7e7e7" class="mainheader2">
		<c:choose >
			<c:when test="${sessionScope.identifier eq 'debtrlc' }">
			Run Liability Check - Unicode
			</c:when>
			<c:when test="${sessionScope.identifier eq 'plcassessment' }">
			PLC Assessment - Unicode
			</c:when>
			<c:when test="${sessionScope.identifier eq 'costcalculation' }">
			Cost Assessment - Unicode
			</c:when>
			<c:otherwise>Liability Text - Unicode</c:otherwise>
		</c:choose>
		
		</td>
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
		
		<c:if test="${sessionScope.readOnly eq 'Y'}">
			<c:set var="readonlyIndicator" value="readonly" />
		</c:if>
		
		<table width=100% cellspacing=1 cellpadding=0 border=0>
			<tr>
				<td height="18" width='30%' class='bodyfont2' nowrap><b>Case
				No.&nbsp;:&nbsp;</b><c:out value="${sessionScope.header.caseId}" /></td>
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
		<div>
		<table width=100% width=100% cellspacing=3 cellpadding=3
			class='blackbox'>
			<tr>
				<td height="18" bgcolor="#666666" class="mainheader2" width='25%'><font
					color="white">Liability&nbsp;</font></td>
				<td height="18" bgcolor="#666666" class="mainheader2" width='25%'><font
					color="white">Amount&nbsp;</font></td>
				<td height="18" bgcolor="#666666" class="mainheader2" width='50%'><font
					color="white">Free Text&nbsp;</font></td>
			</tr>
			<c:set var="counter" value="-1" />
			<c:forEach var='assessment' items="${caseassessment_details_table}"
				varStatus="i">
				<tr>
					<c:set var="bgcolor">
						<c:choose>
							<c:when test="${i.index%2 eq 0}">#FFFFFF</c:when>
							<c:otherwise>#e7e7e7</c:otherwise>
						</c:choose>
					</c:set>

					<td align="left" height="18" class='bodyfont2'
						bgcolor="<c:out value='${bgcolor}' />" nowrap><c:out
						value="${assessment.value.fncClclcDes}" /></td>
					<td align="left" height="18" class='bodyfont2'
						bgcolor="<c:out value='${bgcolor}' />" nowrap><c:out
						value="${assessment.value.calcAmt}" /></td>
					<td align="left" height="18" class='bodyfont2'
						bgcolor="<c:out value='${bgcolor}' />"><input type="text"
						class='textfield_effect' name="value${i.index}"
						id="value${i.index}"
						onkeyup="jspFunction('value${i.index}')"
						onclick="jspFunction('value${i.index}')"
						value="<c:out value='${assessment.value.calcFreeTextUni}' />" <c:out value='${readonlyIndicator}' /> /> <input
						type="hidden" name="copy_value${i.index}"
						id="copy_value${i.index}"
						value="<c:out value='${assessment.value.calcFreeTextUni}' />" /></td>

					<input type="hidden" name="hidden_code_value${i.index}"
						id="hidden_code_value${i.index}"
						value="<c:out
						value='${assessment.value.assessDetails.clclcCode}'/>">
				</tr>
				<c:set var="counter">${i.index}</c:set>
			</c:forEach>
			<input type="hidden" id="total_rows" name="total_rows"
				value="${counter}">

		</table>
		</div>
		<br>
		<table>
			<tr>
				<td>
				<table>
					<tr>
						<td height="25" class="bodyfont2" width='15%'><b>Free
						Text<br>
						(Complete)&nbsp;</b></td>
						<td height="18" class="bodyfont2"><textarea
							class='textarea_effect' id='free_text' readonly="readonly"
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
									onClick='javascript:processSave();' /></td>
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
									value='Back' onClick="javascript:killNow()"
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
	</c:otherwise>
</c:choose>
</body>
