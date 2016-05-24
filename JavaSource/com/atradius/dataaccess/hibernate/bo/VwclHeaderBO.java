/*********************************************************************/
/*                           FILE HEADER                             */
/*********************************************************************/
/*                                                                   */
/*  FileName: 		VwclHeaderBO.java                 */
/*  																 */
/*  $Author: INVSAR1 $									             */
/*																	 */
/*  $Revision: 1.2 $										         */
/*  																 */
/*  $Date: 2014/09/23 16:17:27 $                                     */
/*                                                                   */
/*  Description: 	This class reprent the mappping with             */
/*				  	VWCL_HEADER view                */
/*				                   					                 */
/*********************************************************************/
/* Date        Name            Version             Comments          */
/*-------------------------------------------------------------------*/
/* 03/09/2014  INASHA2      	1.0         Initial version created  */
/*********************************************************************/

package com.atradius.dataaccess.hibernate.bo;

public class VwclHeaderBO {

	private Integer caseId;
	private String caseType;
	private Integer custId;
	private String custName;
	private String lang;
	public Integer getCaseId() {
		return caseId;
	}
	public void setCaseId(Integer caseId) {
		this.caseId = caseId;
	}
	public String getCaseType() {
		return caseType;
	}
	public void setCaseType(String caseType) {
		this.caseType = caseType;
	}
	public Integer getCustId() {
		return custId;
	}
	public void setCustId(Integer custId) {
		this.custId = custId;
	}
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public String getLang() {
		return lang;
	}
	public void setLang(String lang) {
		this.lang = lang;
	}
	
	
}
