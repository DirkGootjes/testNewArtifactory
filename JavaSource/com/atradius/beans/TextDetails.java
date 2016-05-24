/*********************************************************************/
/*                           FILE HEADER                             */
/*********************************************************************/
/*                                                                   */
/*  FileName: 		TextDetails.java             			  	     */
/*  																 */
/*  $Author: INVSAR1 $									             */
/*																	 */
/*  $Revision: 1.2 $										         */
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


/**
 * @author INRSHR1
 *
 */
public class TextDetails {
private int id;	
private String description;	
private String logicalCode;
private int textType;
public TextDetails(int id, String description, String logicalCode, int textType) {
	super();
	this.id = id;
	this.description = description;
	this.logicalCode = logicalCode;
	this.textType = textType;
}
/**
 * @return the description
 */
public String getDescription() {
	return description;
}
/**
 * @param description the description to set
 */
public void setDescription(String description) {
	this.description = description;
}
/**
 * @return the id
 */
public int getId() {
	return id;
}
/**
 * @param id the id to set
 */
public void setId(int id) {
	this.id = id;
}
/**
 * @return the logicalCode
 */
public String getLogicalCode() {
	return logicalCode;
}
/**
 * @param logicalCode the logicalCode to set
 */
public void setLogicalCode(String logicalCode) {
	this.logicalCode = logicalCode;
}
/**
 * @return the textType
 */
public int getTextType() {
	return textType;
}
/**
 * @param textType the textType to set
 */
public void setTextType(int textType) {
	this.textType = textType;
}

}
