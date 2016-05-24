package com.atradius.beans;

/*********************************************************************/
/*                           FILE HEADER                             */
/*********************************************************************/
/*                                                                   */
/*  FileName: 		PolicyDocModuleVarBean.java             	  	  		 */
/*  																 */
/*  $Author: INKPAG1 $									             */
/*																	 */
/*  $Revision: 1.2 $										         */
/*  																 */
/*  $Date: 2014/10/01 10:49:22 $                                     */
/*                                                                   */
/*  Description: 	This java bean stores the Policy Module Variable */
/*						information                                  */
/*				                       				                 */
/*********************************************************************/
/* Date        Name            Version             Comments          */
/*-------------------------------------------------------------------*/
/* 25/09/2014  INKPAG1      	1.0         Initial version created  */
/*********************************************************************/
public class PolicyDocModuleVarBean {
	
	/**
	 * Holds module variables value
	 */
	private String varValue=null;
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
	/** getVarValue
	 * @return
	 */
	public String getVarValue() {
		return varValue;
	}
	/** setVarValue
	 * @param varValue
	 */
	public void setVarValue(final String varValue) {
		this.varValue = varValue;
	}
	
	
}
