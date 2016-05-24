/*********************************************************************/
/*                           FILE HEADER                             */
/*********************************************************************/
/*                                                                   */
/*  FileName:	ClaimsPaymentTextBean.java				             */
/*  																 */
/*  Author: INVSAR1										             */
/*																	 */
/*  Date: 23 Sep 2014				                                 */
/*                                                                   */
/*  Description: 													 */
/*********************************************************************/
/* Date        Name            Version             Comments          */
/*-------------------------------------------------------------------*/
/*23 Sep 2014  	INVSAR1      	1.0         Initial version created  */
/*********************************************************************/

package com.atradius.beans;

public class ClaimsPaymentTextBean {
	private Long clcptId;

	private String clclcCode;

	private String calcFreeTextUni;

	private String clclcCalcCode;

	public String getCalcFreeTextUni() {
		return calcFreeTextUni;
	}

	public void setCalcFreeTextUni(String calcFreeTextUni) {
		this.calcFreeTextUni = calcFreeTextUni;
	}

	public String getClclcCalcCode() {
		return clclcCalcCode;
	}

	public void setClclcCalcCode(String clclcCalcCode) {
		this.clclcCalcCode = clclcCalcCode;
	}

	public String getClclcCode() {
		return clclcCode;
	}

	public void setClclcCode(String clclcCode) {
		this.clclcCode = clclcCode;
	}

	public Long getClcptId() {
		return clcptId;
	}

	public void setClcptId(Long clcptId) {
		this.clcptId = clcptId;
	}

}
