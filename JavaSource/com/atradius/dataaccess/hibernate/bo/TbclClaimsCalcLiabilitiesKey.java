/*********************************************************************/
/*                           FILE HEADER                             */
/*********************************************************************/
/*                                                                   */
/*  FileName:	TbclClaimsCalcLiabilitiesBO.java	     			 */
/*  																 */
/*  Author: INVSAR1										             */
/*																	 */
/*  Date: 26 Aug 2014				                                 */
/*                                                                   */
/*  Description: 													 */
/*********************************************************************/
/* Date        Name            Version             Comments          */
/*-------------------------------------------------------------------*/
/*26 Aug 2014  	INVSAR1      	1.0         Initial version created  */
/*********************************************************************/

package com.atradius.dataaccess.hibernate.bo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class TbclClaimsCalcLiabilitiesKey implements Serializable, Cloneable {
	private static final long serialVersionUID = 8L;

	@Column(name = "CLCPT_ID", insertable = false, updatable = false)
	private Long clcptId;

	@Column(name = "CLCLC_CODE", insertable = false, updatable = false)
	private String clclcCode;

	public Long getClcptId() {
		return clcptId;
	}

	public void setClcptId(Long clcptId) {
		this.clcptId = clcptId;
	}

	public String getClclcCode() {
		return clclcCode;
	}

	public void setClclcCode(String clclcCode) {
		this.clclcCode = clclcCode;
	}
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

}
