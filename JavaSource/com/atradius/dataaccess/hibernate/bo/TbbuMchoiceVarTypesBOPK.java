package com.atradius.dataaccess.hibernate.bo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Id;

@Embeddable
public class TbbuMchoiceVarTypesBOPK implements Serializable {

	@Id
	@Column ( name = "BUCDE_CODE" )
	private String bucdeCode;
	
	@Id
	@Column ( name = "SEQ" )
	private Integer buvteSeq;
	
	@Id
	@Column ( name = "BUMCT_SEQ" )
	private Integer bumctSeq;

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
	 * @return the bumctSeq
	 */
	public Integer getBumctSeq() {
		return bumctSeq;
	}

	/**
	 * @param bumctSeq the bumctSeq to set
	 */
	public void setBumctSeq(Integer bumctSeq) {
		this.bumctSeq = bumctSeq;
	}

	/**
	 * @return the seq
	 */
	public Integer getBuvteSeq() {
		return buvteSeq;
	}

	/**
	 * @param seq the seq to set
	 */
	public void setBuvteSeq(Integer buvteSeq) {
		this.buvteSeq = buvteSeq;
	}
}
