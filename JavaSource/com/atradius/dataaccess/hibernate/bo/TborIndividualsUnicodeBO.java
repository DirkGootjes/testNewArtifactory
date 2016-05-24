/*********************************************************************/
/*                           FILE HEADER                             */
/*********************************************************************/
/*                                                                   */
/*  FileName: 		TborIndividualsUnicodeBO.java             	  	         */
/*  																 */
/*  $Author: INVSAR1 $									             */
/*																	 */
/*  $Revision: 1.7 $										         */
/*  																 */
/*  $Date: 2013/06/12 10:25:12 $                                     */
/*                                                                   */
/*  Description: 	This class reprent the mappping with             */
/*				  	tbor_individuals_uni table*/
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
@Table(name = "tbor_individuals_uni")
@IdClass(TborIndividualsUnicodePK.class)
public class TborIndividualsUnicodeBO implements Serializable {
	
	private static final long serialVersionUID = -2324480526874131028L;

	private int indivId;

	private String langCode;

	private String indivName;

	private Date effectFromDate;

	private Date effectToDate;

	private String salutatName;

	public TborIndividualsUnicodeBO() {

	}

	@Id
	@Column(updatable = false, insertable = false, name = "indiv_id", nullable = false, length = 14)
	public int getIndivId() {
		return indivId;
	}

	public void setIndivId(int indivId) {
		this.indivId = indivId;
	}
	
	@Column(updatable = true, insertable = true, name = "indiv_name", nullable = false, length = 35 )
	public String getIndivName() {
		return indivName;
	}

	public void setIndivName(String indivName) {
		this.indivName = indivName;
	}

	@Column(updatable = true, insertable = true, name = "salutat_name", nullable = true, length = 35)
	public String getSalutatName() {
		return salutatName;
	}

	public void setSalutatName(String salutatName) {
		this.salutatName = salutatName;
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

		if (!(other instanceof TborIndividualsUnicodeBO))
			return false;

		final TborIndividualsUnicodeBO test = (TborIndividualsUnicodeBO) other;

		if (test.getIndivId() != (getIndivId()))
			return false;

		return true;

	}

	public int hashCode() {
		int result;
		result = getIndivId()+getEffectFromDate().hashCode()+getLangCode().hashCode();
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		String returnStr = "indivId [" + indivId + "] indivName [" + indivName
				+ "] salutatName[" + salutatName + "] langCode [" + langCode
				+ "] effectFromDate [" + effectFromDate + "] effectToDate ["
				+ effectToDate + "]";

		return returnStr;
	}

	/**
	 * @param indivId
	 * @param langCode
	 * @param indivName
	 */
	public TborIndividualsUnicodeBO(int indivId, String langCode, String indivName) {
		super();
		this.indivId = indivId;
		this.langCode = langCode;
		this.indivName = indivName;
	}

}
