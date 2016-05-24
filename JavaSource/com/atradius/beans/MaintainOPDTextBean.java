package com.atradius.beans;

public class MaintainOPDTextBean {
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
	
	/**
	 * Holds OPD taf text description
	 */
	private String opdTagTextDef;
	
	/**Gets error message
	 * @return
	 */
	public String getErrorMsg() {
		return errorMsg;
	}

	/**Sets Error message
	 * @param errorMsg
	 */
	public void setErrorMsg(final String errorMsg) {
		this.errorMsg = errorMsg;
	}

	/** If sucess, then return true else return fasle.
	 * @return
	 */
	public boolean isSuccess() {
		return success;
	}

	/** Set success flag
	 * @param success
	 */
	public void setSuccess(final boolean success) {
		this.success = success;
	}

	/**Gets success message
	 * @return
	 */
	public String getSuccessMessage() {
		return successMessage;
	}

	/** Sets success messages
	 * @param successMessage
	 */
	public void setSuccessMessage(final String successMessage) {
		this.successMessage = successMessage;
	}
	/** getOpdTagTextDef
	 * @return
	 */
	public String getOpdTagTextDef() {
		return opdTagTextDef;
	}
	/** setOpdTagTextDef
	 * @param opdTagTextDef
	 */
	public void setOpdTagTextDef(final String opdTagTextDef) {
		this.opdTagTextDef = opdTagTextDef;
	}
	
	
	
}
