/*********************************************************************/
/*                           FILE HEADER                             */
/*********************************************************************/
/*                                                                   */
/*  FileName: 		TbbuTextTranslationsBO.java                 	 */
/*  																 */
/*  $Author: INASHA2 $									             */
/*																	 */
/*  $Revision: 1.5 $										         */
/*  																 */
/*  $Date: 2014/03/06 13:49:18 $                                     */
/*                                                                   */
/*  Description: 	This class reprent the mappping with             */
/*				  	TBBU_TEXT_TRANSLATIONS table					 */
/*				                   					                 */
/*********************************************************************/
/* Date        Name            Version             Comments          */
/*-------------------------------------------------------------------*/
/* 30/05/2013  INRSHR1      	1.0         Initial version created  */
/*********************************************************************/
package com.atradius.dataaccess.hibernate.bo;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TBBU_TEXT_TRANSLATIONS")

public class TbbuTextTranslationsBO {
	@Id
	@Column(name = "ID")
	private int id;
	
	@Column(name = "BUTTE_ID" ,nullable = true)
	private Integer butteId;
	
	@Column(name = "TEXT")
	private String description;
	
	@Column(name = "BULAE_LANG_CODE")
	private String languageCode;
	
	@Column(name = "SEQ")
	private int seq;
    
	@Column(name = "BUCDE_CODE" ,nullable = true)
	private String bucdeCode;
	
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
	 * @return the butteId
	 */
	public int getButteId() {
		return butteId;
	}
	/**
	 * @param butteId the id to set
	 */
	@Column(name = "BUTTE_ID" ,nullable = true)
	public void setButteId(int butteId) {
		this.butteId = butteId;
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
	 * @return the seq
	 */
	public int getSeq() {
		return seq;
	}
	/**
	 * @param seq the seq to set
	 */
	public void setSeq(int seq) {
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
	
	
}
