<!--
  * MaintainClaimsPayment.jsp

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
	src="<c:out value='${contextPath}' />/dwr/interface/MaintainClaimsPaymentJS.js?version=<c:out value='${buildDate}' />"></script>
<script type="text/javascript"
	src="<c:out value='${contextPath}' />/js/clmpayments.js?version=<c:out value='${buildDate}' />"></script>
<body>

<table width=100% cellspacing=0 cellpadding=0 border=0>
	<tr>
		<td height="18" bgcolor="#e7e7e7" class="mainheader2">Claims
		Payment - Unicode</td>
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
			<c:set var="readonlyIndicator" value="Readonly" />
		</c:if>

		<table width=100% cellspacing=1 cellpadding=0 border=0>
			<tr>
				<td height="18" width='30%' class='bodyfont2' nowrap><b>Payment
				Id&nbsp;:&nbsp;</b> <c:out value="${sessionScope.clcptId}" /></td>
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
					color="white">Text&nbsp;(&nbsp;Partial&nbsp;)&nbsp;</font></td>
			</tr>
			<c:set var="counter" value="-1" />
			<c:forEach var='bean' items="${clm_payments}" varStatus="i">
				<tr>
					<c:set var="bgcolor">
						<c:choose>
							<c:when test="${i.index%2 eq 0}">#FFFFFF</c:when>
							<c:otherwise>#e7e7e7</c:otherwise>
						</c:choose>
					</c:set>

					<td align="left" height="18" class='bodyfont2'
						bgcolor="<c:out value='${bgcolor}' />" nowrap><c:out
						value="${bean.fncClclcDes}" /></td>
					<td align="left" height="18" class='bodyfont2'
						bgcolor="<c:out value='${bgcolor}' />" nowrap><c:out
						value="${bean.calcAmt}" /></td>
					<td align="left" height="18" class='bodyfont2'
						bgcolor="<c:out value='${bgcolor}' />"><input type="text"
						class='textfield_effect' id="unicode_value_${i.index}"
						onkeyup="jspFunction(this.id)" onclick="jspFunction(this.id)"
						value="<c:out value='${bean.calcFreeTextUni}'/>"
						<c:out value='${readonlyIndicator}' /> /></td>
					<input type="hidden" id="unicode_copy_value_${i.index}"
						value="<c:out value='${bean.calcFreeTextUni}'/>" />
					<input type="hidden" id="hidden_payment_id_${i.index}"
						value="<c:out value='${bean.clcptId}'/>">
					<input type="hidden" id="hidden_clclcCode_${i.index}"
						value="<c:out value='${bean.clclcCode}'/>">
					<input type="hidden" id="hidden_clclcCalcCode_${i.index}"
						value="<c:out value='${bean.clclcCalcCode}'/>">

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
						<td height="25" class="bodyfont2" width='15%'><b>Text<br>
						(Complete)&nbsp;</b></td>
						<td height="18" class="bodyfont2"><textarea
							class='textarea_effect' readonly="readonly" rows='5' cols='150'
							id="valueTextArea" onfocus="this.blur()"></textarea></td>
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
