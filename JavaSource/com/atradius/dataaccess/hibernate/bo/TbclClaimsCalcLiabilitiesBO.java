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
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "TBCL_CLAIMS_CALC_LIAB_UNI_TEMP")
public class TbclClaimsCalcLiabilitiesBO implements Serializable, Cloneable {

	private static final long serialVersionUID = 4L;

	@EmbeddedId
	private TbclClaimsCalcLiabilitiesKey primaryKey;

	@Column(name = "CLCPT_ID", insertable = false, updatable = false)
	private Long clcptId;

	@Column(name = "CLCLC_CODE", insertable = false, updatable = false)
	private String clclcCode;

	@Column(name = "FNC_CLCLC_DES")
	private String fncClclcDes;

	@Column(name = "CALC_AMT")
	private Double calcAmt;

	@Column(name = "ORSUS_ID")
	private Integer orsusId;

	@Column(name = "LAST_UPDATE_DAT")
	private Date lastUpdateDate;

	@Column(name = "CLCLC_CALC_CODE")
	private String clclcCalcCode;

	@Column(name = "USER_CALC_AMT")
	private Double userCalcAmt;

	@Column(name = "CALC_FREE_TEXT_UNI")
	private String calcFreeTextUni;

	public Double getCalcAmt() {
		return calcAmt;
	}

	public void setCalcAmt(Double calcAmt) {
		this.calcAmt = calcAmt;
	}

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

	public String getFncClclcDes() {
		return fncClclcDes;
	}

	public void setFncClclcDes(String fncClclcDes) {
		this.fncClclcDes = fncClclcDes;
	}

	public Date getLastUpdateDate() {
		return lastUpdateDate;
	}

	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}

	public Integer getOrsusId() {
		return orsusId;
	}

	public void setOrsusId(Integer orsusId) {
		this.orsusId = orsusId;
	}

	public TbclClaimsCalcLiabilitiesKey getPrimaryKey() {
		return primaryKey;
	}

	public void setPrimaryKey(TbclClaimsCalcLiabilitiesKey primaryKey) {
		this.primaryKey = primaryKey;
	}

	public Double getUserCalcAmt() {
		return userCalcAmt;
	}

	public void setUserCalcAmt(Double userCalcAmt) {
		this.userCalcAmt = userCalcAmt;
	}

	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

	@Override
	public String toString() {
		return new String("clcptId [" + clcptId + "] clclcCode [" + clclcCode
				+ "] fncClclcDes[" + fncClclcDes + "]" + "calcFreeTextUni ["
				+ calcFreeTextUni + "] ");
	}

}
