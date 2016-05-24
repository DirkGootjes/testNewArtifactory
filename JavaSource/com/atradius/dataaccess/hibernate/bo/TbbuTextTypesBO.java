/*********************************************************************/
/*                           FILE HEADER                             */
/*********************************************************************/
/*                                                                   */
/*  FileName: 		TbbuTextTypesBO.java             	  	         */
/*  																 */
/*  $Author: INRSHR1 $									             */
/*																	 */
/*  $Revision: 1.3 $										         */
/*  																 */
/*  $Date: 2013/05/23 10:36:54 $                                     */
/*                                                                   */
/*  Description: 	This class reprent the mappping with             */
/*				  	tbbu_text_types table					         */
/*********************************************************************/
/* Date        Name            Version             Comments          */
/*-------------------------------------------------------------------*/
/* 20/04/2013  INRSHR1      	1.0         Initial version created  */
/*********************************************************************/
// package
package com.atradius.dataaccess.hibernate.bo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "tbbu_text_types")
public class TbbuTextTypesBO {
	/**
	 * 
	 */
	public TbbuTextTypesBO() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Id
	@Column(name = "ID")
	private int id;

	@Column(name = "DES")
	private String description;

	@Column(name = "LOGICAL_CODE")
	private String logicalCode;

	@Column(name = "TEXT_TYPE",nullable = true)
	private Integer textType;

	@Column(updatable = false, insertable = false, name = "SEQ", nullable = true, length = 6)
	private Integer SEQ;
	
	@Transient
	private String textTypeDescription;

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return " id [" + id + "]  description [" + description
				+ "]  logicalCode [" + logicalCode + "]  textType [" + textType
				+ "]  SEQ [" + SEQ + "] ";

	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
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
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the languageCode
	 */
	public String getLogicalCode() {
		return logicalCode;
	}

	/**
	 * @param logicalCode
	 *            the languageCode to set
	 */
	public void setLogicalCode(String logicalCode) {
		this.logicalCode = logicalCode;
	}

	/**
	 * @return the textType
	 */
	public int getTextType() {
		if(textType != null)
			return textType;
		else
			return 0; 
	}

	/**
	 * @param textType
	 *            the textType to set
	 */
	public void setTextType(Integer textType) {
		if(textType !=null)
			this.textType = textType;
		else
			this.textType = 0;
	}

	/**
	 * @return the sEQ
	 */
	public Integer getSEQ() {
		return SEQ;
	}

	/**
	 * @param seq
	 *            the sEQ to set
	 */
	public void setSEQ(Integer seq) {
		SEQ = seq;
	}

	/**
	 * @return the textTypeDescription
	 */
	public String getTextTypeDescription() {
		return textTypeDescription;
	}

	/**
	 * @param textTypeDescription the textTypeDescription to set
	 */
	public void setTextTypeDescription(String textTypeDescription) {
		this.textTypeDescription = textTypeDescription;
	}

}
