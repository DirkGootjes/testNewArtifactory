/*********************************************************************/
/*                           FILE HEADER                             */
/*********************************************************************/
/*                                                                   */
/*  FileName: 		TextDetailsUni.java             		  	     */
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

public class TextDetailsUni {
	private int id;
	private String description;
	private String languageCode;
	/**
	 * @param id
	 * @param description
	 * @param logicalCode
	 */
	public TextDetailsUni(int id, String description, String languageCode) {
		super();
		this.id = id;
		this.description = description;
		this.languageCode = languageCode;
	}
	public TextDetailsUni() {
		super();
		// TODO Auto-generated constructor stub
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
}
