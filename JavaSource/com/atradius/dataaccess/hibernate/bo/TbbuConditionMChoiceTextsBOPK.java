package com.atradius.dataaccess.hibernate.bo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class TbbuConditionMChoiceTextsBOPK implements Serializable{
	
	@Column ( name = "BUCDE_CODE" )
	private String bucdeCode;
	
	@Column ( name = "BUMCT_SEQ" )
	private Integer bumctSequence;
	
	@Column ( name = "BULAE_LANG_CODE" )
	private String langCode;

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
}
