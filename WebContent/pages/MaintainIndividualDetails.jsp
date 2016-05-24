<!--
  * MaintainIndividualDetails.jsp

  * Created on: 04 Apr 2013
  * Version   : 1.0
  * Author    : INVSAR1
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
	src="<c:out value='${contextPath}' />/dwr/interface/IndividualDetailsJS.js?version=<c:out value='${buildDate}' />"></script>
<script type="text/javascript"
	src="<c:out value='${contextPath}' />/js/individual.js?version=<c:out value='${buildDate}' />"></script>
<table width=100% cellspacing=0 cellpadding=0 border=0>
	<tr>
		<td height="18" bgcolor="#e7e7e7" class="mainheader2">Maintain
		Local Version</td>
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
		<table width=40% cellspacing=1 cellpadding=0 border=0 >
			<tr>
				<td height="18" width='50%' class='bodyfont2' nowrap>&nbsp;&nbsp;User
				Id&nbsp;:&nbsp;</td>
				<td height="18" width='50%' class='bodyfont2' nowrap><c:out value="${individual_sys_user.indivUid}" /></td>
			</tr>
			<tr>
				<td height="18" width='55%' class='bodyfont2' nowrap>&nbsp;&nbsp;System
				User Id&nbsp;:&nbsp;</td>
				<td height="18" width='50%' class='bodyfont2' nowrap><c:out value="${individual_sys_user.id}" /></td>
			</tr>
			<tr>
				<td height="18" width='50%' class='bodyfont2' nowrap>&nbsp;&nbsp;Individual
				Id&nbsp;:&nbsp;</td>
				<td height="18" width='50%' class='bodyfont2' nowrap><c:out value="${individual_details.indivId}" /></td>
			</tr>
		
			<tr>
				<td height="18" width='50%' class='bodyfont2' nowrap>&nbsp;&nbsp;Individual Name&nbsp;:&nbsp;</td>
				<td height="18" width='50%' class='bodyfont2' nowrap><c:out value="${individual_details.indivName}" /></td>
			</tr>
			<tr>
				<td height="18" width='50%' class='bodyfont2' nowrap>&nbsp;&nbsp;Language&nbsp;:&nbsp;</td>
				<td height="18" width='50%' class='bodyfont2' nowrap><c:out value="${individual_details.bulaeLangCode}" /></td>
			</tr>
			<tr>
				<td height="18" width='50%' class='bodyfont2' nowrap><font color=red>*&nbsp;</font>Enter Local Version&nbsp;:&nbsp;</td>
				<td height="18" width='50%' class='bodyfont2' nowrap>
					<input type='text'
							id='unicode_indiv_name' size='35' class='textfield_effect'
							name='unicode_indiv_name' 
							value="<c:out value='${selected_indv_translation.indivName}' />">
				</td>
			</tr>
			
		</table>
		<br>

		
		<table align='left' border="0" cellpadding="5" cellspacing="0">
			<tr>
			<td height="18" class="bodyfont2" nowrap align='left'><font color='red'>*</font>&nbsp;Mandatory Fields
			</td>
			</tr>
			<tr>
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
				<td>
				<table border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td width="24"><img
							src="<c:out value='${contextPath}' />/skins/gn/images/lft.gif"
							width="24"></td>
						<td
							background="<c:out value='${contextPath}' />/skins/gn/images/but_bg.gif">
						<input id="delete_btn" type="button" class="superbutton"
							disabled value='Delete' onClick='javascript:getConfirmation();' /></td>
						<td width="24"><img
							src="<c:out value='${contextPath}' />/skins/gn/images/rt.gif"></td>
					</tr>
				</table>
				</td>

			</tr>
		</table>
		</div><br><br><br>
		<div id='deleteConfirm' style='display:none;align:left;width:100%'>
		<table border="0" cellpadding="0" cellspacing="0" width='50%'
			class='blackbox'>
			<tr>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td class="bodyfont2" colspan=2 align='center'>Are you sure
				you want to delete record?</td>
			</tr>
			<tr>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td align='right'>
				<table border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td width="24"><img
							src="<c:out value='${contextPath}' />/skins/gn/images/lft.gif"
							width="24"></td>
						<td
							background="<c:out value='${contextPath}' />/skins/gn/images/but_bg.gif">
						<input name="reset_btn" type="button" class="superbutton"
							value='Yes' onClick="javascript:processDelete('YES');" /></td>
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
						<input name="reset_btn" type="button" class="superbutton"
							value='No' onClick="javascript:processDelete('NO');" /></td>
						<td width="24"><img
							src="<c:out value='${contextPath}' />/skins/gn/images/rt.gif"></td>
					</tr>
				</table>
				</td>
			</tr>
			<tr>
				<td>&nbsp;</td>
			</tr>
		</table>
		</div><br>
		<script>
		resetDeleteButton(document.getElementById('unicode_indiv_name').value);
		if("<c:out value='${is_rtl}' />" == "true"  ){
			document.getElementById('unicode_indiv_name').dir = "rtl";
		}
		</script>
		<input type='hidden' id='unicodeLanguage' size='35' 
				value="<c:out value='${individual_details.bulaeLangCode}' />">
	</c:otherwise>
</c:choose>
