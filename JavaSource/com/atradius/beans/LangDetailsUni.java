/*********************************************************************/
/*                           FILE HEADER                             */
/*********************************************************************/
/*                                                                   */
/*  FileName: 		LangDetailsUni.java             	  	  		 */
/*  																 */
/*  $Author: INVSAR1 $									             */
/*																	 */
/*  $Revision: 1.2 $										         */
/*  																 */
/*  $Date: 2013/05/31 12:16:58 $                                     */
/*                                                                   */
/*  Description: 	This java bean stores the language details       */
/*				                       				                 */
/*********************************************************************/
/* Date        Name            Version             Comments          */
/*-------------------------------------------------------------------*/
/* 15/04/2013  INVSAR1      	1.0         Initial version created  */
/*********************************************************************/
package com.atradius.beans;


public class LangDetailsUni {
	private String name;
	
    private String languageCode;
   
    private char thousandInd;
  
    private char decimalInd;

	public LangDetailsUni() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the decimalInd
	 */
	public char getDecimalInd() {
		return decimalInd;
	}

	/**
	 * @param decimalInd the decimalInd to set
	 */
	public void setDecimalInd(char decimalInd) {
		this.decimalInd = decimalInd;
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
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the thousandInd
	 */
	public char getThousandInd() {
		return thousandInd;
	}

	/**
	 * @param thousandInd the thousandInd to set
	 */
	public void setThousandInd(char thousandInd) {
		this.thousandInd = thousandInd;
	}

	/**
	 * @param name
	 * @param languageCode
	 * @param thousandInd
	 * @param decimalInd
	 */
	public LangDetailsUni(String name, String languageCode, char thousandInd, char decimalInd) {
		super();
		this.name = name;
		this.languageCode = languageCode;
		this.thousandInd = thousandInd;
		this.decimalInd = decimalInd;
	}
}
