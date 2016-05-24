/*********************************************************************/
/*                           FILE HEADER                             */
/*********************************************************************/
/*                                                                   */
/*  FileName: 		TborOrgNamesUnicodePK.java        	         	 */
/*  																 */
/*  $Author: INVSAR1 $									             */
/*																	 */
/*  $Revision: 1.5 $										         */
/*  																 */
/*  $Date: 2013/06/12 10:25:12 $                                     */
/*                                                                   */
/*  Description: 	Primary key class 					             */
/*				  	for TborOrgNamesUnicodeBO						 */
/*				                   					                 */
/*********************************************************************/
/* Date        Name            Version             Comments          */
/*-------------------------------------------------------------------*/
/* 20/04/2013  INRSHR1      	1.0         Initial version created  */
package com.atradius.dataaccess.hibernate.bo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class TborOrgNamesUnicodePK implements Serializable {

	private static final long serialVersionUID = 829646171237076429L;

	int oroneId;

	Date effectFromDate;
	
	String langCode;

	public TborOrgNamesUnicodePK() {
	}

	public TborOrgNamesUnicodePK(int oroneId, Date effectFromDate) {
		this.oroneId = oroneId;
		this.effectFromDate = effectFromDate;
	}

	@Column(updatable = false, insertable = false, name = "orone_id", nullable = false, length = 8)
	public int getOroneId() {
		return oroneId;
	}

	public void setOroneId(int oroneId) {
		this.oroneId = oroneId;
	}

	@Column(updatable = false, insertable = false, name = "effect_from_dat", nullable = true)
	public Date getEffectFromDate() {
		return effectFromDate;
	}
	
	public void setEffectFromDate(Date effectFromDate) {
		this.effectFromDate = effectFromDate;
	}
	
	@Column(updatable = true, insertable = true, name = "bulae_lang_code", nullable = false, length = 8)
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
		if (!(other instanceof TborOrgNamesUnicodePK))
			return false;

		final TborOrgNamesUnicodePK test = (TborOrgNamesUnicodePK) other;

		if (test.getOroneId() != (getOroneId()))
			return false;
		if (!test.getEffectFromDate().equals(getEffectFromDate()))
			return false;
		if (!test.getLangCode().equals(getLangCode()))
			return false;

		return true;
	}

	public int hashCode() {
		int result;
		result = getOroneId()+getEffectFromDate().hashCode()+getLangCode().hashCode();
		return result;
	}

}