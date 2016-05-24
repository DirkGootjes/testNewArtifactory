/************************************************************************/
/*                           FILE HEADER                            	*/
/************************************************************************/
/*                                                                   	*/
/*  FileName	: MaintainFreetextBean.java     		  	     	*/
/*  																 	*/
/*  $Author		: INATIW1 $									         	*/
/*																	 	*/
/*  $Revision	: 1.1 $										         	*/
/*  																 	*/
/*  $Date		: 2013/10/17 11:34:58 $                              	*/
/*                                                                  	*/
/*  Description	: This java bean stores value of variable type "TEXT"	*/
/*  in Condition code Main text            								*/
/*				                       				                 	*/
/************************************************************************/
/* Date        Name            Version             Comments          	*/
/*----------------------------------------------------------------------*/
/* 10/10/2013  INATIW1      	1.0         Initial version created  	*/
/************************************************************************/
package com.atradius.beans;

import java.io.Serializable;

public class MaintainFreetextBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private String bucdeCd;
	private int bucltId;	
	private String freetext;
	
	/**
	 * @param conditionCode
	 * @param freetext
	 * @param unicodeFlag
	 */
	public MaintainFreetextBean(String bucdeCd, String freetext, int bucltId) {
		super();
		this.bucdeCd = bucdeCd;
		this.freetext = freetext;
		this.bucltId = bucltId;
	}
	public MaintainFreetextBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @return the freetext
	 */
	public String getFreetext() {
		return freetext;
	}
	/**
	 * @param freetext the freetext to set
	 */
	public void setFreetext(String freetext) {
		this.freetext = freetext;
	}
	/**
	 * @return the serialVersionUID
	 */
	public static long getSerialVersionUID() {
		return serialVersionUID;
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
		

}
