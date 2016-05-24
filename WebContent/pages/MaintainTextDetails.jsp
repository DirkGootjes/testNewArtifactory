<!--
  * MaintainTextDetails.jsp

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
	src="<c:out value='${contextPath}' />/dwr/interface/TextDetailsJS.js?version=<c:out value='${buildDate}' />"></script>
<script type="text/javascript"
	src="<c:out value='${contextPath}' />/js/text.js?version=<c:out value='${buildDate}' />"></script>

<table width=100% cellspacing=0 cellpadding=0 border=0>
	<tr>
		<td height="18" bgcolor="#e7e7e7" class="mainheader2">Additional Translation Languages</td>
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
				<td height="18" width='35%' class='bodyfont2' nowrap>Description&nbsp;:&nbsp;</td>
				<td height="18"  class='bodyfont2' nowrap><c:out value="${text_details.description}" /></td>
				</tr><tr>
				<td height="18" width='35%' class='bodyfont2' nowrap>Logical Code&nbsp;:&nbsp;</td>
				<td height="18"  class='bodyfont2' nowrap><c:out	value="${text_details.logicalCode}" /></td>
				</tr><tr>
				<td height="18" width='35%' class='bodyfont2' nowrap>Invoice Text Type&nbsp;:&nbsp;</td>
				<td height="18" class='bodyfont2' nowrap><c:out	value="${text_details.textTypeDescription}" /></td>
			</tr>
			<tr><td colspan=2>&nbsp;</td></tr>
			<tr>
				<td height="18" class="bodyfont2" width='35%' nowrap>Language
				Code&nbsp;:&nbsp;</td>
				<td height="18" class="bodyfont2"><select id='unicodeLanguage'
					name="unicodeLanguage" class='textlist_effect'
					onchange="javascript:loadTextDetails('<c:out value='${unicodeLanguage.index}' />',this.value)">
					<c:forEach var="lang" items="${unicode_lang_details}"
						varStatus="counter">
						<option value="<c:out value='${lang.langCode}'  />"><c:out
							value='${lang.langCode}' /></option>
					</c:forEach>
				</select></td>
			</tr>
			<tr>
				<td height="18" class="bodyfont2" width='35%' nowrap>Language
				Name&nbsp;:&nbsp;</td>
				<td height="18" class="bodyfont2"><input type='text'
					id='language_name' size='35' readonly class='textfield_effect'
					value='<c:out value='${unicode_lang_details[0].langName}' />'></td>
			</tr>
			<tr>
				<td height="18" class="bodyfont2" width='35%'>Sequence&nbsp;:&nbsp;</td>
				<td height="18" class="bodyfont2"><input type='text' id='seq'
					value="${text_seq.seq}" readonly class='textfield_effect' style='width:25px'></td>
			</tr>
			<tr>
				<td height="18" class="bodyfontmiddle" width='35%'><font color=red>*&nbsp;</font>Text&nbsp;:&nbsp;</td>
				<td height="18" class="bodyfont2"><textarea id='unicode_text' onkeyup='resetIndex(this)' onclick='resetIndex(this)'
					rows='5' cols='50' class='textarea_effect'><c:out
					value='${selected_translation.description}' /></textarea></td>
			</tr>
			<tr>
				<td colspan=2 height="18" class="bodyfont2" nowrap><font
							color='red'>*</font>&nbsp;Mandatory Fields
				</td>
			</tr>
			<tr>
			<td colspan=2>
				<table width='60%' align='left' border="0" cellpadding="5" cellspacing="0">
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
									value='Delete' onClick="javascript:getConfirmation();" /></td>
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
									value='Insert Reference'
									onClick='javascript:processInsertReference();' /></td>
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
		</div><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
		
		
		<div id='deleteConfirm' style='display:none;align:left;width:100%'><br>
		<table border="0" cellpadding="0" cellspacing="0" width='50%'
			class='blackbox'>
			<tr>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td class="bodyfont2" colspan=2 align='center'>Are you sure you
				want to delete record?</td>
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
						<input id="yes_btn" type="button" class="superbutton"
							value='Yes' onClick="javascript:processDelete('YES');"  /></td>
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
						<input name="no_btn" type="button" class="superbutton"
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
		</div>
		<br>

		<script>
			resetDeleteButton(document.getElementById('unicode_text').value);
		</script>
		<input type='hidden' id='rtl_langs' value='<c:out value='${rtl_langs}' />'>
	</c:otherwise>
</c:choose>




