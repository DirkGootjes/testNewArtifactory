package com.atradius.dataaccess.hibernate.bo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Embeddable
public class AssessmentCompositeDetailsBO implements Serializable, Cloneable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "BUCCE_ID")
	private Integer bucceId;

	@Column(name = "RUN_DAT")
	@Temporal(TemporalType.TIMESTAMP)
	private Date runDate;

	@Column(name = "ASSESS_TYP")
	private String assessType;

	@Column(name = "clclc_code")
	private String clclcCode;

	/**
	 * @return the clclcCode
	 */
	public String getClclcCode() {
		return clclcCode;
	}

	/**
	 * @param clclcCode
	 *            the clclcCode to set
	 */
	public void setClclcCode(String clclcCode) {
		this.clclcCode = clclcCode;
	}

	/**
	 * @return the assessType
	 */
	public String getAssessType() {
		return assessType;
	}

	/**
	 * @param assessType
	 *            the assessType to set
	 */
	public void setAssessType(String assessType) {
		this.assessType = assessType;
	}

	/**
	 * @return the bucceId
	 */
	public Integer getBucceId() {
		return bucceId;
	}

	/**
	 * @param bucceId
	 *            the bucceId to set
	 */
	public void setBucceId(Integer bucceId) {
		this.bucceId = bucceId;
	}

	/**
	 * @return the runDate
	 */
	public Date getRunDate() {
		return runDate;
	}

	/**
	 * @param runDate
	 *            the runDate to set
	 */
	public void setRunDate(Date runDate) {
		this.runDate = runDate;
	}

	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}
