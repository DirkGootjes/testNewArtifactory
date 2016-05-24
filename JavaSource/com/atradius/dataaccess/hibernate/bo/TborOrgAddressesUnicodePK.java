/*********************************************************************/
/*                           FILE HEADER                             */
/*********************************************************************/
/*                                                                   */
/*  FileName: 		TborOrgAddressesUnicodePK.java                 	 */
/*  																 */
/*  $Author: INVSAR1 $									             */
/*																	 */
/*  $Revision: 1.5 $										         */
/*  																 */
/*  $Date: 2013/06/12 10:25:12 $                                     */
/*                                                                   */
/*  Description: 	Primary key class 					             */
/*				  	for TborOrgAddressesUnicodeBO					 */
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
public class TborOrgAddressesUnicodePK implements Serializable {

	private static final long serialVersionUID = 829646171237076429L;

	int oroasId;

	Date effectFromDate;

	String langCode;

	public TborOrgAddressesUnicodePK() {
	}

	public TborOrgAddressesUnicodePK(int oroasId,String langCode, Date effectFromDate) {
		this.oroasId = oroasId;
		this.langCode = langCode;
		this.effectFromDate = effectFromDate;
	}

	@Column(updatable = true, insertable = true, name = "oroas_id", nullable = false, length = 8)
	public int getOroasId() {
		return oroasId;
	}

	public void setOroasId(int oroasId) {
		this.oroasId = oroasId;
	}

	@Column(updatable = true, insertable = true, name = "effect_from_dat", nullable = true)
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
		if (!(other instanceof TborOrgAddressesUnicodePK))
			return false;

		final TborOrgAddressesUnicodePK test = (TborOrgAddressesUnicodePK) other;

		if (test.getOroasId() != (getOroasId()))
			return false;
		if (!test.getEffectFromDate().equals(getEffectFromDate()))
			return false;
		if (!test.getLangCode().equals(getLangCode()))
			return false;

		return true;
	}

	public int hashCode() {
		int result;
		result = getOroasId() + getEffectFromDate().hashCode()
				+ getLangCode().hashCode();
		return result;
	}

}