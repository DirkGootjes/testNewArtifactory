package com.atradius.dataaccess.hibernate.bo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Id;

@Embeddable
public class TbbuConditionVarTypesBOPK implements Serializable{
	@Id
	@Column ( name = "BUCDE_CODE" )
	private String bucdeCode;
	
	@Id
	@Column ( name = "SEQ" )
	private Integer seq;

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
