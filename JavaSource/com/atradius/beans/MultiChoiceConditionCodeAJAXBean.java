package com.atradius.beans;

public class MultiChoiceConditionCodeAJAXBean {	

	private String langCode;
	private int choiceSeq;
	private String bucdeCd;
	private String text;
	private int buvteSeq;
	private String buvteType;
	public String errorMsg;
	private boolean success;
	private String successMessage = null;
	/**
	 * @return the bucdecd
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
	 * @return the languageCode
	 */
	public String getLangCode() {
		return langCode;
	}
	/**
	 * @param languageCode the languageCode to set
	 */
	public void setLangCode(String languageCode) {
		this.langCode = languageCode;
	}
	/**
	 * @return the choiceSeq
	 */
	public int getChoiceSeq() {
		return choiceSeq;
	}
	/**
	 * @param choiceSeq the choiceSeq to set
	 */
	public void setChoiceSeq(int seq) {
		this.choiceSeq = seq;
	}
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
	 * @return the textDescription
	 */
	public String getText() {
		return text;
	}
	/**
	 * @param textDescription the textDescription to set
	 */
	public void setText(String textDescription) {
		this.text = textDescription;
	}	
	/**
	 * @return the buvteSeq
	 */
	public int getBuvteSeq() {
		return buvteSeq;
	}
	/**
	 * @param buvteSeq the buvteSeq to set
	 */
	public void setBuvteSeq(int buvteSeq) {
		this.buvteSeq = buvteSeq;
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
}
