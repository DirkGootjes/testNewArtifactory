package com.atradius.beans;

public class PaymentConditionsBean {


	/**
	 * Stores Payment description 
	 */
	private String pcDescription = null;

	/**
	 * Error message
	 */
	private String errorMsg;

	/**
	 * sucess flag
	 */
	private boolean success;

	/**
	 * success message
	 */
	private String successMessage = null;

	
	
	public String getPcDescription() {
		return pcDescription;
	}

	public void setPcDescription(String pcDescription) {
		this.pcDescription = pcDescription;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getSuccessMessage() {
		return successMessage;
	}

	public void setSuccessMessage(String successMessage) {
		this.successMessage = successMessage;
	}

	/**Gets error message
	 * @return
	 */
	
	
}
