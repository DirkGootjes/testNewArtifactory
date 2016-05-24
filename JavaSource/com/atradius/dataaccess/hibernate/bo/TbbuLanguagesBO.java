/*********************************************************************/
/*                           FILE HEADER                             */
/*********************************************************************/
/*                                                                   */
/*  FileName: 		TbbuLanguagesBO.java             	  	         */
/*  																 */
/*  $Author: INASHA2 $									             */
/*																	 */
/*  $Revision: 1.5 $										         */
/*  																 */
/*  $Date: 2014/03/06 13:49:18 $                                     */
/*                                                                   */
/*  Description: 	This class reprent the mappping with             */
/*				  	tbbu_languages table							 */
/*				                   					                 */
/*********************************************************************/
/* Date        Name            Version             Comments          */
/*-------------------------------------------------------------------*/
/* 20/04/2013  INRSHR1      	1.0         Initial version created  */
/*********************************************************************/
package com.atradius.dataaccess.hibernate.bo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * this is used with hibernate to get data from the TBBU_LANGUAGES table depending on the NON_NCM_DETAILS
 * NOTE this is never updateable or insertable as the webservice should not be able to modify the underlying table
 */
@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
@Table(name="tbbu_languages")
public class TbbuLanguagesBO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1477605215376213771L;
	private String langCode;
	private String langName;
	private String nonLatinFlag;
	
	
	public TbbuLanguagesBO(){
		
	}
	
	public TbbuLanguagesBO(String langCode, String langName,String nonLatinFlag)
	{
		this.langCode = langCode;
		this.langName = langName;
		this.nonLatinFlag=nonLatinFlag;
	}
	
	@Column(updatable = false, insertable = false, name = "lang_name", nullable = false, length=35)
	public String getLangName()
	{
		return langName;
	}
	public void setLangName(String langName){
		this.langName = langName;
	}
	
	@Id
	@Column(updatable = false, insertable = false, name = "lang_code", nullable = false, length=2)
	public String getLangCode()
	{
		return langCode;
	}
	public void setLangCode(String langCode){
		this.langCode = langCode;
	}

	/**
	 * @return the nonLatinFlag
	 */
	@Column(updatable = false, insertable = false, name = "NON_LATIN_FLAG")
	public String getNonLatinFlag() {
		return nonLatinFlag;
	}

	/**
	 * @param nonLatinFlag the nonLatinFlag to set
	 */
	public void setNonLatinFlag(String nonLatinFlag) {
		this.nonLatinFlag = nonLatinFlag;
	}

}
