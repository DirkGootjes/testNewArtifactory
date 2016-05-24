package com.atradius.beans;

public class FreetextAJAXBean {
	
	private int bucltId;
	private String bucdeCd;
	private String applyAmountType;
	private String variableText;
	private int bucyeSequence;
	private int bumctSequence;
	private int bumctOrder;
	public String errorMsg;
	private boolean success;
	private String successMessage = null;
	/**
	 * @return the success
	 */
	public boolean isSuccess() {
		return success;
	}
	/**
	 * @param success the success to set
	 */
	public void setSuccess(boolean success) {
		this.success = success;
	}
	/**
	 * @return the successMessage
	 */
	public String getSuccessMessage() {
		return successMessage;
	}
	/**
	 * @param successMessage the successMessage to set
	 */
	public void setSuccessMessage(String successMessage) {
		this.successMessage = successMessage;
	}
	/**
	 * @return the errorMsg
	 */
	public String getErrorMsg() {
		return errorMsg;
	}
	/**
	 * @param errorMsg the errorMsg to set
	 */
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
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
	/**
	 * @return the bucltId
	 */
	public int getBucltId() {
		return bucltId;
	}
	/**
	 * @param bucltId the bucltId to set
	 */
	public void setBucltId(int bucltId) {
		this.bucltId = bucltId;
	}
	/**
	 * @return the bucyeSequence
	 */
	public int getBucyeSequence() {
		return bucyeSequence;
	}
	/**
	 * @param bucyeSequence the bucyeSequence to set
	 */
	public void setBucyeSequence(int bucyeSequence) {
		this.bucyeSequence = bucyeSequence;
	}
	/**
	 * @return the bumctOrder
	 */
	public int getBumctOrder() {
		return bumctOrder;
	}
	/**
	 * @param bumctOrder the bumctOrder to set
	 */
	public void setBumctOrder(int bumctOrder) {
		this.bumctOrder = bumctOrder;
	}
	/**
	 * @return the bumctSequence
	 */
	public int getBumctSequence() {
		return bumctSequence;
	}
	/**
	 * @param bumctSequence the bumctSequence to set
	 */
	public void setBumctSequence(int bumctSequence) {
		this.bumctSequence = bumctSequence;
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
	
}
