/*********************************************************************/
/*                           FILE HEADER                             */
/*********************************************************************/
/*                                                                   */
/*  FileName: 		AJAXResult.java         		    	  	     */
/*  																 */
/*  $Author: INVSAR1 $									             */
/*																	 */
/*  $Revision: 1.3 $										         */
/*  																 */
/*  $Date: 2013/05/31 12:16:58 $                                     */
/*                                                                   */
/*  Description: 	This java bean used as AJAX response             */
/*				  						                             */
/*********************************************************************/
/* Date        Name            Version             Comments          */
/*-------------------------------------------------------------------*/
/* 04/04/2013  INVSAR1      	1.0         Initial version created  */
/*********************************************************************/
package com.atradius.beans;

import java.io.Serializable;

public class AJAXResult implements Serializable {

	private static final long serialVersionUID = -6702571186705014457L;
	
	private String errorCode = null;
	
	private String errorMessage = null;
	
	private boolean success;
	
	private String successMessage = null;
	
	private String secVariableValues[] = null;//Added by INGKHA1 for Defect 8006

	/**
	 * @return the errorCode
	 */
	public String getErrorCode() {
		return errorCode;
	}

	/**
	 * @param errorCode the errorCode to set
	 */
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	/**
	 * @return the errorMessage
	 */
	public String getErrorMessage() {
		return errorMessage;
	}

	/**
	 * @param errorMessage the errorMessage to set
	 */
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
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
	

	public String[] getSecVariableValues() {
		return secVariableValues;
	}

	public void setSecVariableValues(String[] secVariableValues) {
		this.secVariableValues = secVariableValues;
	}
	
	
}
