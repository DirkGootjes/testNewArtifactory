/*********************************************************************/
/*                           FILE HEADER                             */
/*********************************************************************/
/*                                                                   */
/*  FileName: 		VwclTransLetterVariables2BO.java                 */
/*  																 */
/*  $Author: INVSAR1 $									             */
/*																	 */
/*  $Revision: 1.4 $										         */
/*  																 */
/*  $Date: 2014/10/08 06:52:39 $                                     */
/*                                                                   */
/*  Description: 	This class reprent the mappping with             */
/*				  	VWCL_TRANS_LETTER_VARIABLES2 view                */
/*				                   					                 */
/*********************************************************************/
/* Date        Name            Version             Comments          */
/*-------------------------------------------------------------------*/
/* 01/09/2014  INASHA2      	1.0         Initial version created  */
/*********************************************************************/
package com.atradius.dataaccess.hibernate.bo;

import java.sql.Clob;
import java.util.Date;

import com.atradius.util.logging.ILogger;
import com.atradius.util.logging.LoggerFactory;

public class VwclTransLetterVariables2BO {
	private static ILogger logger = LoggerFactory
	.getLogger(VwclTransLetterVariables2BO.class);
	
	private Integer id;
	private Integer bucceId;
	private String clcltLetterTyp;
	private Date printDate;
	private String clstcSectType;
	private String des;
	private String fncSelectFlag;
	private String mandatoryFlag;
	private Integer clvteSeq;
	private String varType;
	private String varDes;
	private Clob textUni;
	private String fncText;
	private String varMandatoryFlag;
	private Integer clstcSeq;
	public Integer getBucceId() {
		return bucceId;
	}
	public void setBucceId(Integer bucceId) {
		this.bucceId = bucceId;
	}
	public String getClcltLetterTyp() {
		return clcltLetterTyp;
	}
	public void setClcltLetterTyp(String clcltLetterTyp) {
		this.clcltLetterTyp = clcltLetterTyp;
	}
	public String getClstcSectType() {
		return clstcSectType;
	}
	public void setClstcSectType(String clstcSectType) {
		this.clstcSectType = clstcSectType;
	}
	public Integer getClstcSeq() {
		return clstcSeq;
	}
	public void setClstcSeq(Integer clstcSeq) {
		this.clstcSeq = clstcSeq;
	}
	public Integer getClvteSeq() {
		return clvteSeq;
	}
	public void setClvteSeq(Integer clvteSeq) {
		this.clvteSeq = clvteSeq;
	}
	public String getDes() {
		return des;
	}
	public void setDes(String des) {
		this.des = des;
	}
	public String getFncSelectFlag() {
		return fncSelectFlag;
	}
	public void setFncSelectFlag(String fncSelectFlag) {
		this.fncSelectFlag = fncSelectFlag;
	}
	public String getFncText() {
		return fncText;
	}
	public void setFncText(String fncText) {
		this.fncText = fncText;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getMandatoryFlag() {
		return mandatoryFlag;
	}
	public void setMandatoryFlag(String mandatoryFlag) {
		this.mandatoryFlag = mandatoryFlag;
	}
	public Date getPrintDate() {
		return printDate;
	}
	public void setPrintDate(Date printDate) {
		this.printDate = printDate;
	}
	public String getTextUni() {
		try {
			if(textUni != null && textUni.length() > 0) {
				return textUni.getSubString(1, (int)textUni.length());
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return null;
	}
	public void setTextUni(Clob textUni) {
		this.textUni = textUni;
	}
	public String getVarDes() {
		return varDes;
	}
	public void setVarDes(String varDes) {
		this.varDes = varDes;
	}
	public String getVarMandatoryFlag() {
		return varMandatoryFlag;
	}
	public void setVarMandatoryFlag(String varMandatoryFlag) {
		this.varMandatoryFlag = varMandatoryFlag;
	}
	public String getVarType() {
		return varType;
	}
	public void setVarType(String varType) {
		this.varType = varType;
	}
	
	
}
