<!--
  * MaintainFreetext.jsp

  * Created on: 11 Oct 2013
  * Version   : 1.0
  * Author    : INATIW1
  *  
-->
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<title>UnicodeFreetextEditor</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="GENERATOR" content="Rational Application Developer">
<meta http-equiv='cache-control' content='no-cache'>
<meta http-equiv='expires' content='0'>
<meta http-equiv='pragma' content='no-cache'>


<script type="text/javascript"
	src="<c:out value='${contextPath}' />/dwr/interface/FreetextDetailsJS.js?version=<c:out value='${buildDate}' />"></script>
<script type="text/javascript"
	src="<c:out value='${contextPath}' />/js/freetext.js?version=<c:out value='${buildDate}' />"></script>

<body onload="javascript:loadFirstAtPageLoad('<c:out value='${formMode}'/>');">

<table width=100% cellspacing=0 cellpadding=0 border=0> 
	<tr>
		<td height="18" bgcolor="#e7e7e7" class="mainheader2">Unicode - Freetext Editor</td>
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
	<div align='left'>
		<table width=50% cellspacing=1 cellpadding=0 border=0 align='left'>
			<tr>
				<td height="18" width='35%' class='bodyfont2' nowrap>&nbsp;&nbsp;&nbsp;&nbsp;Freetext&nbsp;:&nbsp;</td>
			</tr>
			<tr>
				<td height="18" class="bodyfont2">&nbsp;&nbsp;&nbsp;&nbsp;
					<textarea id='unicode_text' onkeyup='resetIndex(this);getUnicode(this);resetSaveButton(this);resetCancelButton(this);' onclick='resetIndex(this)' onchange='resetSaveButton(this);resetCancelButton(this)'
					rows='5' cols='75' class='textarea_effect'
					style="text-align: left;white-space: normal"><c:out value='${Unicode_Freetext.variableText}'/></textarea></td>
			</tr>
			<tr>
				<td colspan=2>
					<table width='75%' align='left' border="0" cellpadding="5" cellspacing="0">
					<tr>
						<td align="left">
							<table border="0" cellpadding="0" cellspacing="0">
								<tr>
									<td width="24"><img
										src="<c:out value='${contextPath}' />/skins/gn/images/lft.gif"
										width="24"></td>
									<td	background="<c:out value='${contextPath}' />/skins/gn/images/but_bg.gif">
										<input id="save_btn" name="save_btn" type="button"
											class="superbutton" value='Save'
											onClick='javascript:processSave();' /></td>
									<td width="24"><img
										src="<c:out value='${contextPath}' />/skins/gn/images/rt.gif"></td>
								</tr>
							</table>
						</td>
						<td align="right">
							<table border="0" cellpadding="0" cellspacing="0">
								<tr>
									<td width="24"><img
										src="<c:out value='${contextPath}' />/skins/gn/images/lft.gif"
										width="24"></td>
									<td background="<c:out value='${contextPath}' />/skins/gn/images/but_bg.gif">
										<input id="cancel_btn" type="button" class="superbutton"
											value='Cancel' onClick="javascript:processReset();" /></td>
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
		</div>			
			<br>
			<script>
				resetCancelButton(document.getElementById('unicode_text').value);
				resetSaveButton(document.getElementById('unicode_text').value);
			</script>
	</c:otherwise>
</c:choose>
</body>	