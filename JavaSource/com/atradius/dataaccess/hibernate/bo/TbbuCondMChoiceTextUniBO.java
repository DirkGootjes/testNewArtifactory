package com.atradius.dataaccess.hibernate.bo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name = "TBBU_COND_MCHOICE_TEXT_UNI")
@javax.persistence.SequenceGenerator( 
name = "seq_mchoiceuni", sequenceName = "sqbucti_id" , allocationSize=1) 
public class TbbuCondMChoiceTextUniBO {

	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq_mchoiceuni" )
	@Column ( name = "ID" )
	private Integer id;
	
	@Column ( name = "BUCDE_CODE" )
	private String bucdeCode;
	

	@Column ( name = "ORSUS_ID" )
	private Integer orsusId;


	@Column ( name = "LAST_UPDATE_DAT" )
	private Date lastUpdateDate;

	
	@Column ( name = "TEXT" )
	private String text;
	
	
	@Column ( name = "BUMCT_SEQ" )
	private Integer bumctSequence;
	
	
	@Column ( name = "BULAE_LANG_CODE" )
	private String langCode;
	
	@Column(updatable = true, insertable = true, name = "EFFECT_TO_DAT", nullable = true)
	private Date effectToDate;
	
	
	@Column(updatable = true, insertable = true, name = "EFFECT_FROM_DAT", nullable = true)
	private Date effectFromDate;
	
	public TbbuCondMChoiceTextUniBO(){
	}
	public TbbuCondMChoiceTextUniBO(String bucdeCode, String text, String langCode ,Integer bumctSequence) {
		this.text = text;
		this.bucdeCode = bucdeCode;
		this.langCode = langCode;
		this.bumctSequence = bumctSequence;
		
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
	 * @return the bumctSequence
	 */
	public Integer getBumctSequence() {
		return bumctSequence;
	}


	/**
	 * @param bumctSequence the bumctSequence to set
	 */
	public void setBumctSequence(Integer bumctSequence) {
		this.bumctSequence = bumctSequence;
	}


	/**
	 * @return the effectFromDate
	 */
	public Date getEffectFromDate() {
		return effectFromDate;
	}


	/**
	 * @param effectFromDate the effectFromDate to set
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
	 * @param effectToDate the effectToDate to set
	 */
	public void setEffectToDate(Date effectToDate) {
		this.effectToDate = effectToDate;
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
	 * @return the langCode
	 */
	public String getLangCode() {
		return langCode;
	}


	/**
	 * @param langCode the langCode to set
	 */
	public void setLangCode(String langCode) {
		this.langCode = langCode;
	}


	/**
	 * @return the lastUpdateDate
	 */
	public Date getLastUpdateDate() {
		return lastUpdateDate;
	}


	/**
	 * @param lastUpdateDate the lastUpdateDate to set
	 */
	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}


	/**
	 * @return the orsusId
	 */
	public Integer getOrsusId() {
		return orsusId;
	}


	/**
	 * @param orsusId the orsusId to set
	 */
	public void setOrsusId(Integer orsusId) {
		this.orsusId = orsusId;
	}


	/**
	 * @return the text
	 */
	public String getText() {
		return text;
	}


	/**
	 * @param text the text to set
	 */
	public void setText(String text) {
		this.text = text;
	}
	
}
