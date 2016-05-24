package com.atradius.dataaccess.hibernate.bo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name = "TBBU_CONDITION_VARIABLES_UNI")
public class TbbuConditionVariablesUniBO {
	
	@Id 
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SQBUCVU_ID")
	@SequenceGenerator(name="SQBUCVU_ID", sequenceName="SQBUCVU_ID")
	@Column(updatable = false, insertable = false, nullable = false, name = "ID")
	private Integer id;
	
	@Column(name = "BUCLT_ID")
	private Integer bucltId;
	
	@Column(name = "APPLY_AMT_TYP")
	private String applyAmountType;
	
	@Column(name = "BUCYE_SEQ")
	private Integer bucyeSequence;
	
	@Column(name = "TEXT", updatable = true, insertable = true, nullable = true)
	private String variableText;
	
	@Column(name = "BUCDE_CODE")
	private String bucdeCd;
	
	@Column(name = "BUMCT_SEQ")
	private Integer bumctSequence;
	
	@Column(name = "BUMCT_ORDER")
	private Integer bumctOrder;

	@Column(updatable = true, insertable = true, name = "EFFECT_TO_DAT", nullable = true)
	private Date effectToDate;
	
	@Column(updatable = true, insertable = true, name = "EFFECT_FROM_DAT", nullable = true)
	private Date effectFromDate;
	
	public TbbuConditionVariablesUniBO() {
	}


	/**
	 * @return the applyAmountType
	 */
	public String getApplyAmountType() {
		return applyAmountType;
	}


	/**
	 * @param applyAmountType the applyAmountType to set
	 */
	public void setApplyAmountType(String applyAmountType) {
		this.applyAmountType = applyAmountType;
	}


	/**
	 * @return the buclt_Id
	 */
	public Integer getBucltId() {
		return bucltId;
	}


	/**
	 * @param buclt_Id the buclt_Id to set
	 */
	public void setBucltId(Integer bucltId) {
		this.bucltId = bucltId;
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
	 * @return the variableText
	 */
	public String getVariableText() {
		return variableText;
	}


	/**
	 * @param variableText the variableText to set
	 */
	public void setVariableText(String variableText) {
		this.variableText = variableText;
	}


	/**
	 * @return the bUMCT_Order
	 */
	public Integer getBumctOrder() {
		return bumctOrder;
	}


	/**
	 * @param order the bUMCT_Order to set
	 */
	public void setBumctOrder(Integer order) {
		bumctOrder = order;
	}


	/**
	 * @return the bUMCT_Sequence
	 */
	public Integer getBumctSequence() {
		return bumctSequence;
	}


	/**
	 * @param sequence the bUMCT_Sequence to set
	 */
	public void setBumctSequence(Integer sequence) {
		bumctSequence = sequence;
	}


	/**
	 * @return the bucyeSequence
	 */
	public Integer getBucyeSequence() {
		return bucyeSequence;
	}


	/**
	 * @param bucyeSequence the bucyeSequence to set
	 */
	public void setBucyeSequence(Integer bucyeSequence) {
		this.bucyeSequence = bucyeSequence;
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
	 * @return the bucdeCd
	 */
	public String getBucdeCd() {
		return bucdeCd;
	}


	/**
	 * @param bucdeCd the bucdeCd to set
	 */
	public void setBucdeCd(String bucdeCd) {
		this.bucdeCd = bucdeCd;
	}


}
