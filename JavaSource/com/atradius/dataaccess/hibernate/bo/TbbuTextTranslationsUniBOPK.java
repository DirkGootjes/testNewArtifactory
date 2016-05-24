/*********************************************************************/
/*                           FILE HEADER                             */
/*********************************************************************/
/*                                                                   */
/*  FileName: 		TbbuTextTranslationsUniBOPK.java                 */
/*  																 */
/*  $Author: INASHA2 $									             */
/*																	 */
/*  $Revision: 1.6 $										         */
/*  																 */
/*  $Date: 2014/03/06 13:49:18 $                                     */
/*                                                                   */
/*  Description: 	Primary key class 					             */
/*				  	for TbbuTextTranslationsUniBO					 */
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

/**
 * @author INRSHR1
 *
 */
@Embeddable
public class TbbuTextTranslationsUniBOPK implements Serializable{
	@Column(name = "BUTTE_ID")
	private int id;
	@Column(name = "BULAE_LANG_CODE")
	private String languageCode;
	@Column(updatable = true, insertable = true, name = "effect_from_dat", nullable = false)
	private Date effectFromDate;
	
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the languageCode
	 */
	public String getLanguageCode() {
		return languageCode;
	}
	/**
	 * @param languageCode the languageCode to set
	 */
	public void setLanguageCode(String languageCode) {
		this.languageCode = languageCode;
	}
	/**
	 * @param id
	 * @param languageCode
	 */
	public TbbuTextTranslationsUniBOPK(int id, String languageCode) {
		super();
		this.id = id;
		this.languageCode = languageCode;
	}
	public TbbuTextTranslationsUniBOPK() {
		super();
	}
	/**
	 * @return the effectToDate
	 */
	public Date getEffectFromDate() {
		return effectFromDate;
	}
	/**
	 * @param effectToDate the effectToDate to set
	 */
	public void setEffectFromDate(Date effectFromDate) {
		this.effectFromDate = effectFromDate;
	}
	
	
}
