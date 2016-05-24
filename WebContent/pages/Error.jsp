<!--
  * Error.jsp

  * Created on: 04 Apr 2013
  * Version   : 1.0
  * Author    : INVSAR1
  *
  * Change Details
  * Date        Name            Version             Comments          
  *-------------------------------------------------------------------
  *   
-->
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<meta http-equiv='cache-control' content='no-cache'>
<meta http-equiv='expires' content='0'>
<meta http-equiv='pragma' content='no-cache'>
<br>
<table width=100% cellspacing=0 cellpadding=0 class='err'>
	<tr>
		<td ><c:out value='${error_message}' /> </td>
	</tr>
</table>

