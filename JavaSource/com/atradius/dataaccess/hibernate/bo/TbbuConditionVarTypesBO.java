package com.atradius.dataaccess.hibernate.bo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table (name = "TBBU_CONDITION_VAR_TYPES")
@IdClass(TbbuConditionVarTypesBOPK.class)
public class TbbuConditionVarTypesBO {
	@Id
	@Column ( name = "BUCDE_CODE" )
	private String bucdeCode;
	

	@Column ( name = "ORSUS_ID" )
	private Integer orsusId;


	@Column ( name = "LAST_UPDATE_DAT" )
	private Date lastUpdateDate;

	
	@Column ( name = "BUVTE_TYP" )
	private String buvteType;
	
	@Id
	@Column ( name = "SEQ" )
	private Integer seq;
	
	
	@Column ( name = "LAST_UPDATE_SEQ" )
	private Integer lastUpdateSeq;


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
	 * @return the buvteType
	 */
	public String getBuvteType() {
		return buvteType;
	}


	/**
	 * @param buvteType the buvteType to set
	 */
	public void setBuvteType(String buvteType) {
		this.buvteType = buvteType;
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
	 * @return the lastUpdateSeq
	 */
	public Integer getLastUpdateSeq() {
		return lastUpdateSeq;
	}


	/**
	 * @param lastUpdateSeq the lastUpdateSeq to set
	 */
	public void setLastUpdateSeq(Integer lastUpdateSeq) {
		this.lastUpdateSeq = lastUpdateSeq;
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
}
