<!--
  * Error-404.jsp

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
<%@ taglib uri="/WEB-INF/tld/fmt.tld" prefix="fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<meta http-equiv='cache-control' content='no-cache'>
<meta http-equiv='expires' content='0'>
<meta http-equiv='pragma' content='no-cache'>
<html>
<head>
<c:set var="contextPath" value="${pageContext.request.contextPath}" scope="request" />

<title>Error 404</title>
<link rel="stylesheet"
	href="<c:out value='${contextPath}' />/skins/gn/css/corps.css"
	type="text/css">
<link rel="stylesheet"
	href="<c:out value='${contextPath}' />/skins/gn/css/style.css"
	type="text/css">
	
</head>
<body>
<center>
<div style="width:80%">
<table width=100% cellspacing=0 cellpadding=0 border=0>
	<tr>
		<td width='100%'><img
			src="<c:out value='${contextPath}' />/skins/gn/images/Atradius_logo_and_strapline.gif"
			vspace=0 width="155" height="48" align="left"></td>
	</tr>
	<tr>
		<td colspan='2'><img
			src="<c:out value='${contextPath}' />/skins/gn/images/dynamicline.gif"
			vspace=10 width=100% align="top"></td>
	</tr>
</table>
</div>

<div id='bodyDiv' class='container'>

<br>
<table width=100% cellspacing=0 cellpadding=0 class='err'>
	<tr>
		<td >Requested resource is not available</td>
	</tr>
</table>

</div>
<br>
<div style="width:80%">
<table width=100% cellspacing=0 cellpadding=0 border=0>
	<tr>
		<td height="18" bgcolor="#e7e7e7" class="bodyfont2"><font
			color='red'>*</font>&nbsp;Mandatory Fields</td>
		<td height="18" bgcolor="#e7e7e7" class='bodyfont2' align='right'>Build
		: <c:out value="${applicationScope['Implementation-Build']}" />&nbsp;&nbsp;&nbsp;Version
		: <c:out value="${applicationScope['Implementation-Version']}" /></td>
	</tr>

</table>
</div>
</center>

</body>
</html>
