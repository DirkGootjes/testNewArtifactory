<!--
  * MaintainContactDetails.jsp

  * Created on: 04 Apr 2013
  * Version   : 1.0
  * Author    : INVSAR1
  *
  * Change Details
  * Date        Name            Version             Comments          
  *-------------------------------------------------------------------
  *   
-->
<meta name="http-equiv" content="Content-type: text/html; charset=UTF-8" />
<%@ taglib uri="/WEB-INF/tld/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<meta http-equiv='cache-control' content='no-cache'>
<meta http-equiv='expires' content='0'>
<meta http-equiv='pragma' content='no-cache'>
<script type="text/javascript"
	src="<c:out value='${contextPath}' />/dwr/interface/ContactDetailsJS.js?version=<c:out value='${buildDate}' />"
	charset="utf-8"></script>
<script type="text/javascript"
	src="<c:out value='${contextPath}' />/js/contact.js?version=<c:out value='${buildDate}' />"
	charset="utf-8"></script>

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
		<table width=100% cellspacing=1 cellpadding=0 border=0>
			<tr>
				<td height="18" width='25%' class='bodyfont2' nowrap><b>Org
				No.&nbsp;:&nbsp;</b><c:out value="${org_details.orgId}" /></td>
				<td height="18" width='50%' class='bodyfont2' nowrap><b>Short
				Name&nbsp;:&nbsp;</b><c:out value="${org_details.orgName}" /></td>
				<td height="18" width='25%' class='bodyfont2' nowrap><b>Country&nbsp;:&nbsp;</b><c:out
					value="${org_details.orgCountry}" /></td>
			</tr>
		</table>
		<table id='contact_list' width=100% cellspacing=1 cellpadding=0
			border=0 class='blackbox'>
			<tr>
				<td height="18" bgcolor="#666666" class="mainheader2" width='5%'><font
					color="white">Select</font></td>
				<td height="18" bgcolor="#666666" class="mainheader2" width='30%'><font
					color="white">Name</font></td>
				<td height="18" bgcolor="#666666" class="mainheader2" width='10%'><font
					color="white">Type</font></td>
				<td height="18" bgcolor="#666666" class="mainheader2" width='20%'><font
					color="white">Position</font></td>
				<td height="18" bgcolor="#666666" class="mainheader2" width='20%'><font
					color="white">Tel No.</font></td>
				<td height="18" bgcolor="#666666" class="mainheader2" width='15%'><font
					color="white">Language</font></td>
			</tr>
			<c:forEach var='contact' items="${org_details.contactBeanList}"
				varStatus="counter">
				<tr>
					<c:set var="bgcolor">
						<c:choose>
							<c:when test="${counter.index%2 eq 0}">#FFFFFF</c:when>
							<c:otherwise>#e7e7e7</c:otherwise>
						</c:choose>
					</c:set>
					<td height="18" class='bodyfont2'
						bgcolor="<c:out value='${bgcolor}' />" nowrap><input
						type='radio' name='select_contact'
						value="<c:out value='${contact.index}' />"
						onclick="javascript:loadContactDetails('<c:out value='${contact.index}' />','<c:out value="${contact.langCode}" />')">
					</td>
					<td height="18" class='bodyfont2'
						bgcolor="<c:out value='${bgcolor}' />" nowrap><c:out
						value="${contact.contactName}" /></td>
					<td height="18" class='bodyfont2'
						bgcolor="<c:out value='${bgcolor}' />" nowrap><c:out
						value="${contact.contactType}" /></td>
					<td height="18" class='bodyfont2'
						bgcolor="<c:out value='${bgcolor}' />" nowrap><c:out
						value="${contact.position}" /></td>
					<td height="18" class='bodyfont2'
						bgcolor="<c:out value='${bgcolor}' />" nowrap><c:out
						value="${contact.telNo}" /></td>
					<td height="18" class='bodyfont2'
						bgcolor="<c:out value='${bgcolor}' />" nowrap><c:out
						value="${contact.langCode}" /></td>
				</tr>
			</c:forEach>
		</table>
		<br>
		<table width=100% cellspacing=5 cellpadding=0 border=0>
			<tr>
				<td height="18" bgcolor="#666666" class="mainheader2" width='40%'><font
					color="white">Contact Details (Read-Only)</font></td>
				<td height="18" width='10%'>&nbsp;</td>
				<td height="18" bgcolor="#666666" class="mainheader2" width='40%'><font
					color="white">Enter Local Version</font></td>
			</tr>
			<tr>
				<td width="40%" valign='top'>
				<table width=100% cellspacing=0 cellpadding=3 class='blackbox'>
					<tr>
						<td height="18" class="mainheader2" bgcolor="#e7e7e7" colspan=2>Individual
						Details</td>
					</tr>
					<tr>
						<td height="18" class="bodyfont2" width='25%'>Name</td>
						<td height="18" class="bodyfont2"><input type='text'
							id='latin_indiv_name' size='35' readonly class='textfield_effect'></td>
					</tr>
					<tr>
						<td height="18" class="bodyfont2" width='25%'>Salutation</td>
						<td height="18" class="bodyfont2"><input type='text'
							id='latin_salutat_name' size='35' readonly
							class='textfield_effect'></td>
					</tr>
					<tr>
						<td height="18" class="mainheader2" bgcolor="#e7e7e7" colspan=2>Organisation
						Name</td>
					</tr>
					<tr>
						<td height="18" class="bodyfont2" width='25%'>First line</td>
						<td height="18" class="bodyfont2"><input type='text'
							id='latin_org_name_f' size='35' readonly class='textfield_effect'></td>
					</tr>
					<tr>
						<td height="18" class="bodyfont2" width='25%'>Second line</td>
						<td height="18" class="bodyfont2"><input type='text'
							id='latin_org_name_s' size='35' readonly class='textfield_effect'></td>
					</tr>
					<tr>
						<td height="18" class="bodyfont2" width='25%'>Third line</td>
						<td height="18" class="bodyfont2"><input type='text'
							id='latin_org_name_t' size='35' readonly class='textfield_effect'></td>
					</tr>
					<tr>
						<td height="18" class="mainheader2" bgcolor="#e7e7e7" colspan=2>Address
						Details</td>
					</tr>
					<tr>
						<td height="18" class="bodyfont2" width='25%'>First line</td>
						<td height="18" class="bodyfont2"><input type='text'
							id='latin_org_address_f' size='35' readonly
							class='textfield_effect'></td>
					</tr>
					<tr>
						<td height="18" class="bodyfont2" width='25%'>Second line</td>
						<td height="18" class="bodyfont2"><input type='text'
							id='latin_org_address_s' size='35' readonly
							class='textfield_effect'></td>
					</tr>
					<tr>
						<td height="18" class="bodyfont2" width='25%'>Third line</td>
						<td height="18" class="bodyfont2"><input type='text'
							id='latin_org_address_t' size='35' readonly
							class='textfield_effect'></td>
					</tr>
					<tr>
						<td height="18" class="bodyfont2" width='25%'>City</td>
						<td height="18" class="bodyfont2"><input type='text'
							id='latin_org_city' size='35' readonly class='textfield_effect'></td>
					</tr>
					<tr>
						<td height="18" class="bodyfont2" width='25%'>Post Code</td>
						<td height="18" class="bodyfont2"><input type='text'
							id='latin_org_post' size='35' readonly class='textfield_effect'></td>
					</tr>
					<tr>
						<td height="18" class="bodyfont2" width='25%'>Region</td>
						<td height="18" class="bodyfont2"><input type='text'
							id='latin_org_region' size='35' readonly class='textfield_effect'></td>
					</tr>
					<tr>
						<td height="18" class="bodyfont2" width='25%'>Country</td>
						<td height="18" class="bodyfont2"><input type='text'
							id='latin_org_county' size='35' readonly class='textfield_effect_small'></td>
					</tr>
				</table>
				</td>
				<td height="18" width='10%' align="center">
				<table border="0" cellpadding="0" cellspacing="0">

					<tr>
						<td><input id="copy_btn" type="button" class="sqarebutton"
							value=' Copy >> ' disabled onClick='javascript:processCopy();'
							alt="Copy latin contents to unicode fields." /></td>
					</tr>

				</table>
				</td>
				<td width="40%" valign='top'>
				<table width=100% cellspacing=0 cellpadding=3 class='blackbox'>
					<tr>
						<td height="18" class="mainheader2" bgcolor="#e7e7e7" colspan=2>Individual
						Details</td>
					</tr>
					<tr>
						<td height="18" class="bodyfont2" width='25%'><font color=red>*&nbsp;</font>Name</td>
						<td height="18" class="bodyfont2"><input type='text'
							id='utf_indiv_name' size='35' class='textfield_effect'></td>
					</tr>
					<tr>
						<td height="18" class="bodyfont2" width='25%'>&nbsp;&nbsp;Salutation</td>
						<td height="18" class="bodyfont2"><input type='text'
							id='utf_salutat_name' size='35' class='textfield_effect'></td>
					</tr>
					<tr>
						<td height="18" class="mainheader2" bgcolor="#e7e7e7" colspan=2>Organisation
						Name</td>
					</tr>
					<tr>
						<td height="18" class="bodyfont2" width='25%'><font color=red>*&nbsp;</font>First
						line</td>
						<td height="18" class="bodyfont2"><input type='text'
							id='utf_org_name_f' size='35' class='textfield_effect'></td>
					</tr>
					<tr>
						<td height="18" class="bodyfont2" width='25%'>&nbsp;&nbsp;Second
						line</td>
						<td height="18" class="bodyfont2"><input type='text'
							id='utf_org_name_s' size='35' class='textfield_effect'></td>
					</tr>
					<tr>
						<td height="18" class="bodyfont2" width='25%'>&nbsp;&nbsp;Third
						line</td>
						<td height="18" class="bodyfont2"><input type='text'
							id='utf_org_name_t' size='35' class='textfield_effect'></td>
					</tr>

					<tr>
						<td height="18" class="mainheader2" bgcolor="#e7e7e7" colspan=2>Address
						Details</td>
					</tr>
					<tr>
						<td height="18" class="bodyfont2" width='25%'><font color=red>*&nbsp;</font>First
						line</td>
						<td height="18" class="bodyfont2"><input type='text'
							id='utf_org_address_f' size='35' class='textfield_effect'></td>
					</tr>
					<tr>
						<td height="18" class="bodyfont2" width='25%'>&nbsp;&nbsp;Second
						line</td>
						<td height="18" class="bodyfont2"><input type='text'
							id='utf_org_address_s' size='35' class='textfield_effect'></td>
					</tr>
					<tr>
						<td height="18" class="bodyfont2" width='25%'>&nbsp;&nbsp;Third
						line</td>
						<td height="18" class="bodyfont2"><input type='text'
							id='utf_org_address_t' size='35' class='textfield_effect'></td>
					</tr>
					<tr>
						<td height="18" class="bodyfont2" width='25%'><font color=red>*&nbsp;</font>City</td>
						<td height="18" class="bodyfont2"><input type='text'
							id='utf_org_city' size='35' class='textfield_effect'></td>
					</tr>
					<tr>
						<td height="18" class="bodyfont2" width='25%'><font color=red>*&nbsp;</font>Post
						Code</td>
						<td height="18" class="bodyfont2"><input type='text'
							id='utf_org_post' size='35' class='textfield_effect'></td>
					</tr>
					<tr>
						<td height="18" class="bodyfont2" width='25%'>&nbsp;&nbsp;Region</td>
						<td height="18" class="bodyfont2"><input type='text'
							id='utf_org_region' size='35' class='textfield_effect'></td>
					</tr>
					<tr>
						<td height="18" class="bodyfont2" width='25%'><font color=red>&nbsp;&nbsp;</font>Country</td>
						<td height="18" class="bodyfont2"><input type='text'
							id='utf_org_county' size='35' class='textfield_effect_small' readonly></td>
					</tr>
				</table>
				</td>
			</tr>
			<tr>
				<td height="18" width='40%'>&nbsp;</td>
				<td height="18" width='10%'>&nbsp;</td>
				<td height="18" width='40%'>
				<table width='100%' border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td width='35%' height="18" class="bodyfont2" nowrap><font
							color='red'>*</font>&nbsp;Mandatory Fields
						</td>
						<td align='right'>
						<table align='right' border="0" cellpadding="5"
							cellspacing="0">
							<tr>
								<td>
								<table border="0" cellpadding="0" cellspacing="0">
									<tr>
										<td width="24"><img
											src="<c:out value='${contextPath}' />/skins/gn/images/lft.gif"
											width="24"></td>
										<td
											background="<c:out value='${contextPath}' />/skins/gn/images/but_bg.gif">
										<input name="save_btn" type="button" class="superbutton"
											value='Save' onClick='javascript:processSave();' /></td>
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
											value='Delete' disabled
											onClick="javascript:getConfirmation();" /></td>
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

				</td>
			</tr>

		</table>


		<center>
		<div id='deleteConfirm' style='display:none'><br>
		<br>
		<table border="0" cellpadding="0" cellspacing="0" width='70%'
			class='blackbox'>
			<tr>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td class="bodyfont2" colspan=2 align='center'>Are you sure you
				want to delete this local version?</td>
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

		</div>
		</center>
		<input type='hidden' id='up_arw'
			value="<c:out value='${contextPath}' />/skins/gn/images/up.gif">
		<input type='hidden' id='dwn_arw'
			value="<c:out value='${contextPath}' />/skins/gn/images/down.gif">
		<input type='hidden' id='selected_lang' readonly
			class='textlist_effect' size='5'>
		<input type='hidden' id='rtl_langs'
			value='<c:out value='${rtl_langs}' />'>
	</c:otherwise>
</c:choose>
