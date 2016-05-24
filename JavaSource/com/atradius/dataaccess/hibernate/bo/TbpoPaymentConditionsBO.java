/*********************************************************************/
/*                           FILE HEADER                             */
/*********************************************************************/
/*                                                                   */
/*  FileName: 		TbpoPaymentConditionsBO.java             	  	     */
/*  																 */
/*  $Author: INHYOU1 $									             */
/*																	 */
/*  $Revision: 1.0 $										         */
/*  																 */
/*  $Date: 2016/01/08 10:25:12 $                                     */
/*                                                                   */
/*  Description: 	This class reprent the mappping with             */
/*				  	TBPO_PAY_COND_DESCS_TEMP table						 */
/*				                   					                 */
/*  																 */	
/*  																 */	
/*********************************************************************/
/* Date        Name            Version             Comments          */
/*-------------------------------------------------------------------*/
/* 11/01/2016  INHYOU1      	1.0         Initial version created  */
/*  																 */	
/*  																 */	
/*********************************************************************/
package com.atradius.dataaccess.hibernate.bo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name = "TBPO_PAY_COND_DESCS_TEMP")
@IdClass(TbpoPaymentConditionsPK.class) //Mapped using IDClass
public class TbpoPaymentConditionsBO implements Serializable {


	private static final long serialVersionUID = -2904762103556478262L;
	@Id
	@Column(updatable = false, insertable = true, name = "POPCE_PER", nullable = false)
	private int pcPer;

	@Id
	@Column(updatable = false, insertable = true, name = "POPCE_PER_TYP", nullable = false)
	private String pcPerTyp;

	@Id
	@Column(updatable = false, insertable = true, name = "POPCE_TYP", nullable = false)
	private String typ;

	@Column(updatable = true, insertable = true, name = "FNC_PAYMENT_CONDITION", nullable = false)
	private String description;

	@Column(updatable = true, insertable = true, name = "VALID_FLAG", nullable = false)
	private String validFlag;

	@Id
	@Column(updatable = false, insertable = true, name = "BULAE_LANG_CODE", nullable = false)
	private String langCode;

	/*This column contains unicode value*/
	@Column(updatable = true, insertable = true, name = "DES", nullable = false)
	private String pcDescription;

	@Column(updatable = true, insertable = true, name = "UPDATE_ALLOWED", nullable = false)
	private String updateAllowed;

	/**
	 * setters and getters
	 * 
	 * @return
	 */
	public int getPcPer() {
		return pcPer;
	}

	public void setPcPer(int pcPer) {
		this.pcPer = pcPer;
	}

	public String getPcPerTyp() {
		return pcPerTyp;
	}

	public void setPcPerTyp(String pcPerTyp) {
		this.pcPerTyp = pcPerTyp;
	}

	public String getTyp() {
		return typ;
	}

	public void setTyp(String typ) {
		this.typ = typ;
	}

	/*getter for description column*/
	public String getDescription() {
		return description;
	}

	/*setter for description column*/
	public void setDescription(String description) {
		this.description = description;
	}

	/*getter for validFlag column*/
	public String getValidFlag() {
		return validFlag;
	}

	/*setter for validFlag column*/
	public void setValidFlag(String validFlag) {
		this.validFlag = validFlag;
	}

	/*getter for language column*/
	public String getLangCode() {
		return langCode;
	}

	/*setter for language column*/
	public void setLangCode(String langCode) {
		this.langCode = langCode;
	}

	/*getter for unicode column*/
	public String getPcDescription() {
		return pcDescription;
	}

	/*setter for unicode column*/
	public void setPcDescription(String pcDescription) {
		this.pcDescription = pcDescription;
	}

	public String getUpdateAllowed() {
		return updateAllowed;
	}

	public void setUpdateAllowed(String updateAllowed) {
		this.updateAllowed = updateAllowed;
	}

	/*Overrides equal */
	@Override
	public boolean equals(Object other) {
		if (this == other)
			return true;
		if ( other.getClass() != this.getClass())
			return false;

		final TbpoPaymentConditionsBO test = (TbpoPaymentConditionsBO) other;

		if (test.getPcPer() != (getPcPer()))
			return false;

		return true;
	}
	/*Overrides hashcode*/
	@Override
	public int hashCode() {
		int result;
		result = getPcPer()+getPcPerTyp().hashCode()+getLangCode().hashCode();
		return result;
	}

	/* ToString */
	@Override
	public String toString() {
		String returnStr = "description [" + description + "] pcDescription ["
				+ pcDescription + "] langCode [" + langCode+ "]";

		return returnStr;
	}
}