/*********************************************************************/
/*                           FILE HEADER                             */
/*********************************************************************/
/*                                                                   */
/*  FileName: 		TborOrgNamesUnicodeBO.java             	  	     */
/*  																 */
/*  $Author: INVSAR1 $									             */
/*																	 */
/*  $Revision: 1.6 $										         */
/*  																 */
/*  $Date: 2013/06/12 10:25:12 $                                     */
/*                                                                   */
/*  Description: 	This class reprent the mappping with             */
/*				  	tbor_org_names_uni table						 */
/*				                   					                 */
/*********************************************************************/
/* Date        Name            Version             Comments          */
/*-------------------------------------------------------------------*/
/* 20/04/2013  INRSHR1      	1.0         Initial version created  */
/*********************************************************************/
package com.atradius.dataaccess.hibernate.bo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name = "tbor_org_names_uni")
@IdClass(TborOrgNamesUnicodePK.class)
public class TborOrgNamesUnicodeBO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2904762103556478262L;

	private int oroneId;

	private String firstLineName;

	private String secondLineName;

	private String thirdLineName;

	private String langCode;

	private Date effectFromDate;

	private Date effectToDate;
	
	private int updatedBy;
	
	public TborOrgNamesUnicodeBO() {

	}

	@Id
	@Column(updatable = false, insertable = false, name = "orone_id", nullable = false, length = 8)
	public int getOroneId() {
		return oroneId;
	}

	public void setOroneId(int oroneId) {
		this.oroneId = oroneId;
	}

	@Column(updatable = true, insertable = true, name = "first_line_name", nullable = false, length = 35)
	public String getFirstLineName() {
		return firstLineName;
	}

	public void setFirstLineName(String firstLineName) {
		this.firstLineName = firstLineName;
	}

	@Column(updatable = true, insertable = true, name = "second_line_name", nullable = true, length = 35)
	public String getSecondLineName() {
		return secondLineName;
	}

	public void setSecondLineName(String secondLineName) {
		this.secondLineName = secondLineName;
	}

	@Column(updatable = true, insertable = true, name = "third_line_name", nullable = true, length = 35)
	public String getThirdLineName() {
		return thirdLineName;
	}

	public void setThirdLineName(String thirdLineName) {
		this.thirdLineName = thirdLineName;
	}
	
	@Column(updatable = false, insertable = false, name = "orsus_id", nullable = false, length = 8)
	public int getUpdatedBy() {
		return updatedBy;
	}
	
	public void setUpdatedBy(int updatedBy) {
		this.updatedBy = updatedBy;
	}

	/**
	 * @return the effectFromDate
	 */
	@Id
	@Column(updatable = true, insertable = true, name = "effect_from_dat", nullable = true)
	public Date getEffectFromDate() {
		return effectFromDate;
	}

	/**
	 * @param effectFromDate
	 *            the effectFromDate to set
	 */
	public void setEffectFromDate(Date effectFromDate) {
		this.effectFromDate = effectFromDate;
	}

	/**
	 * @return the effectToDate
	 */
	
	@Column(updatable = true, insertable = true, name = "effect_to_dat", nullable = true)
	public Date getEffectToDate() {
		return effectToDate;
	}

	/**
	 * @param effectToDate
	 *            the effectToDate to set
	 */
	public void setEffectToDate(Date effectToDate) {
		this.effectToDate = effectToDate;
	}

	/**
	 * @return the langCode
	 */
	@Id
	@Column(updatable = true, insertable = true, name = "bulae_lang_code", nullable = true, length = 8)
	public String getLangCode() {
		return langCode;
	}

	/**
	 * @param langCode
	 *            the langCode to set
	 */
	public void setLangCode(String langCode) {
		this.langCode = langCode;
	}

	public boolean equals(Object other) {
		if (this == other)
			return true;
		if (!(other instanceof TborOrgNamesUnicodeBO))
			return false;

		final TborOrgNamesUnicodeBO test = (TborOrgNamesUnicodeBO) other;

		if (test.getOroneId() != (getOroneId()))
			return false;

		return true;
	}

	public int hashCode() {
		int result;
		result = getOroneId()+getEffectFromDate().hashCode()+getLangCode().hashCode();
		return result;
	}

	@Override
	public String toString() {
		String returnStr = "oroneId [" + oroneId + "] firstLineName ["
				+ firstLineName + "] langCode [" + langCode
				+ "] effectFromDate [" + effectFromDate + "] effectToDate ["
				+ effectToDate + "]";

		return returnStr;
	}

}
