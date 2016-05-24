/*********************************************************************/
/*                           FILE HEADER                             */
/*********************************************************************/
/*                                                                   */
/*  FileName: 		TborIndividualsUnicodePK.java     	  	         */
/*  																 */
/*  $Author: INVSAR1 $									             */
/*																	 */
/*  $Revision: 1.6 $										         */
/*  																 */
/*  $Date: 2013/06/12 10:25:12 $                                     */
/*                                                                   */
/*  Description: 	Primary key of the class TborIndividualsUnicodeBO*/
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
import javax.persistence.Embeddable;

@Embeddable
public class TborIndividualsUnicodePK implements Serializable {

	private static final long serialVersionUID = 829646171237076429L;

	int indivId;

	Date effectFromDate;
	
	String langCode;
	
	public TborIndividualsUnicodePK() {
	}

	public TborIndividualsUnicodePK(int indivId,String langCode, Date effectFromDate) {
		this.indivId = indivId;
		this.langCode = langCode;
		this.effectFromDate = effectFromDate;
	}

	@Column(updatable = false, insertable = false, name = "indiv_id", nullable = false, length = 8)
	public int getIndivId() {
		return indivId;
	}

	public void setIndivId(int indivId) {
		this.indivId = indivId;
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
		if (!(other instanceof TborIndividualsUnicodePK))
			return false;

		final TborIndividualsUnicodePK test = (TborIndividualsUnicodePK) other;

		if (test.getIndivId() != (getIndivId()))
			return false;
		if (test.getEffectFromDate() != (getEffectFromDate()))
			return false;

		return true;
	}

	public int hashCode() {
		int result;
		result = getIndivId()+getEffectFromDate().hashCode()+getLangCode().hashCode();
		return result;
	}

}