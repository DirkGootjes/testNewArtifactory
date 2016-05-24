<!--
  * policyInfoHeader.jsp

  * Created on: 23 SEP 2014
  * Version   : 1.0
  * Author    : INKPAG1
  *
  * Change Details
  * Date        Name            Version             Comments          
  *-------------------------------------------------------------------
  *   
-->

<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>

				
			<tr>
				<td class='bodyfont2' nowrap>Policy Id&nbsp;:</td>
				<td class='bodyfont2' align="right" nowrap><c:out value="${policy_details.policyId}" /></td>
				
				<td class='bodyfont2' nowrap>&nbsp;&nbsp;Customer Name&nbsp;:&nbsp;</td>
				<td  class='bodyfont2' align="right" nowrap><c:out	value="${policy_details.customerName}" /></td>
			</tr>
			<tr>
				<td class='bodyfont2' nowrap>Customer Id&nbsp;:&nbsp;</td>
				<td class='bodyfont2' nowrap><c:out	value="${policy_details.customerId}" /></td>
				
				<td class='bodyfont2' nowrap>&nbsp;&nbsp;Language&nbsp;:&nbsp;</td>
				<td class='bodyfont2' nowrap><c:out	value="${policy_details.lang}" /></td>
			</tr>
			
		