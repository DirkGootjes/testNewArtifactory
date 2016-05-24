/*********************************************************************/
/*                           FILE HEADER                             */
/*********************************************************************/
/*                                                                   */
/*  FileName: 		TbbuTextTranslationsUniBO.java                   */
/*  																 */
/*  $Author: INASHA2 $									             */
/*																	 */
/*  $Revision: 1.7 $										         */
/*  																 */
/*  $Date: 2014/03/06 13:49:18 $                                     */
/*                                                                   */
/*  Description: 	This class reprent the mappping with             */
/*				  	TBBU_TEXT_TRANSLATIONS_UNI table				 */
/*				                   					                 */
/*********************************************************************/
/* Date        Name            Version             Comments          */
/*-------------------------------------------------------------------*/
/* 20/04/2013  INRSHR1      	1.0         Initial version created  */
/*********************************************************************/
package com.atradius.dataaccess.hibernate.bo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TBBU_TEXT_TRANSLATIONS_UNI") //SQBUTTI_ID
@javax.persistence.SequenceGenerator( 
name = "seq_text_trans_uni", sequenceName = "SQBUTTI_ID" , allocationSize=1) 
public class TbbuTextTranslationsUniBO {
	@Id 
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq_text_trans_uni" )
	@Column(name = "ID")
	private Integer pkId;
	
	
	@Column(name = "BUTTE_ID")
	private Integer id;
	
	@Column(name = "TEXT")
	private String description;
	
	@Column(name = "BULAE_LANG_CODE")
	private String languageCode;
	
	@Column(updatable = true, insertable = true, name = "EFFECT_TO_DAT", nullable = true)
	private Date effectToDate;
	
	
	@Column(updatable = true, insertable = true, name = "EFFECT_FROM_DAT", nullable = true)
	private Date effectFromDate;
    
	@Column(name = "BUCDE_CODE")
	private String bucdeCode;
	
	@Column(name = "SEQ")
	private Integer seq;

	/**
	 * @return the seq
	 */
	public Integer getSeq() {
		return seq;
	}
	/**
	 * @param seq the seq to set
	 */
	public void setSeq(Integer seq) {
		this.seq = seq;
	}
	/**
	 * @return the bucdeCode
	 */
	public String getBucdeCode() {
		return bucdeCode;
	}
	/**
	 * @param bucdeCode the bucdeCode to set
	 */
	public void setBucdeCode(String bucdeCode) {
		this.bucdeCode = bucdeCode;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
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
	 * @param description
	 * @param languageCode
	 */
	/**
	 * @return the effectFromDate
	 */
	
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

	
	public TbbuTextTranslationsUniBO(){
	}
	public TbbuTextTranslationsUniBO(Integer textId, String translation, String languageCode) {
		this.id = textId;
		this.languageCode = languageCode;
		this.description = translation;
	}
	
	/**
	 * @return the pkId
	 */
	public Integer getPkId() {
		return pkId;
	}
	/**
	 * @param pkId the pkId to set
	 */
	public void setPkId(Integer pkId) {
		this.pkId = pkId;
	}
}
