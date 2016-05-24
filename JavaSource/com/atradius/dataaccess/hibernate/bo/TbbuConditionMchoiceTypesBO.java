package com.atradius.dataaccess.hibernate.bo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;


@Entity
@Table (name = "TBBU_CONDITION_MCHOICE_TYPES")
@IdClass(TbbuConditionMchoiceTypesBOPK.class)
public class TbbuConditionMchoiceTypesBO{
	@Id
	@Column ( name = "BUCDE_CODE" )
	private String bucdeCode;
	@Id
	@Column ( name = "SEQ" )
	private String sequence;
	
	@Column ( name = "ORSUS_ID" )
	private Integer orsusId;


	@Column ( name = "LAST_UPDATE_DAT" )
	private Date lastUpdateDate;

	
	@Column ( name = "DES" )
	private String description;

	

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
	 * @return the sequence
	 */
	public String getSequence() {
		return sequence;
	}

	/**
	 * @param sequence the sequence to set
	 */
	public void setSequence(String sequence) {
		this.sequence = sequence;
	}

	
	
}
