/*********************************************************************/
/*                           FILE HEADER                             */
/*********************************************************************/
/*                                                                   */
/*  FileName: 		TextAJAXBean.java             			  	     */
/*  																 */
/*  $Author: INVSAR1 $									             */
/*																	 */
/*  $Revision: 1.4 $										         */
/*  																 */
/*  $Date: 2013/05/31 12:16:58 $                                     */
/*                                                                   */
/*  Description: 	This java bean stored text details               */
/*				                       				                 */
/*********************************************************************/
/* Date        Name            Version             Comments          */
/*-------------------------------------------------------------------*/
/* 15/04/2013  INVSAR1      	1.0         Initial version created  */
/*********************************************************************/
package com.atradius.beans;

public class TextAJAXBean {
private String languageName;
private String languageCode;
private int seq;
private int textId;
private String textDescription;
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
 * @return the languageCode
 */
public String getLanguageCode() {
	return languageCode;
}
/**
 * @param languageCode the languageCode to set
 */
public void setLanguageCode(String languageCode) {
	this.languageCode = languageCode;
}
/**
 * @return the languageName
 */
public String getLanguageName() {
	return languageName;
}
/**
 * @param languageName the languageName to set
 */
public void setLanguageName(String languageName) {
	this.languageName = languageName;
}
/**
 * @return the seq
 */
public int getSeq() {
	return seq;
}
/**
 * @param seq the seq to set
 */
public void setSeq(int seq) {
	this.seq = seq;
}
/**
 * @return the textDescription
 */
public String getTextDescription() {
	return textDescription;
}
/**
 * @param textDescription the textDescription to set
 */
public void setTextDescription(String textDescription) {
	this.textDescription = textDescription;
}
/**
 * @return the textId
 */
public int getTextId() {
	return textId;
}
/**
 * @param textId the textId to set
 */
public void setTextId(int textId) {
	this.textId = textId;
}
}
