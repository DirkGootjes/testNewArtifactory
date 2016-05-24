/*********************************************************************/
/*                           FILE HEADER                             */
/*********************************************************************/
/*                                                                   */
/*  FileName: 		TbclTransLetterVarUniTempBO.java                 */
/*  																 */
/*  $Author: INVSAR1 $									             */
/*																	 */
/*  $Revision: 1.2 $										         */
/*  																 */
/*  $Date: 2014/09/23 16:17:28 $                                     */
/*                                                                   */
/*  Description: 	This class reprent the mappping with             */
/*				  	TBCL_TRANS_LETTER_VAR_UNI_TEMP table             */
/*				                   					                 */
/*********************************************************************/
/* Date        Name            Version             Comments          */
/*-------------------------------------------------------------------*/
/* 01/09/2014  INASHA2      	1.0         Initial version created  */
/*********************************************************************/

package com.atradius.dataaccess.hibernate.bo;

import java.sql.Clob;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "TBCL_TRANS_LETTER_VAR_UNI_TEMP")
public class TbclTransLetterVarUniTempBO {

	 @Id 
	 @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CLAIM_SEQ_ID")
	 @SequenceGenerator(name="CLAIM_SEQ_ID", sequenceName="CLAIM_SEQ_ID" , allocationSize = 1)
	 @Column(updatable = false, insertable = false, nullable = false, name = "ID")
	 private Integer id;
	 
	 @Column(name = "BUCCE_ID")
	 private Integer bucceId;
	 
	 @Column(name = "CLCLT_LETTER_TYP")
	 private String clcltLetterTyp;
	 
	 @Column(name = "PRINT_DAT")
	 private Date printDate;
	 
	 @Column(name = "CLSTC_SECT_TYP")
	 private String clstcSectType;
	 
	 @Column(name = "ORSUS_ID")
	 private Integer orsusId;
	 
	 @Column(name = "LAST_UPDATE_DAT")
	 private Date lastUpdateDate;
	 
	 @Column(name = "CLVTE_SEQ")
	 private Integer clvteSeq;
	 
	 @Column(name = "TEXT")
	 private String text;
	 
	 @Lob
	 @Column(name = "TEXT_UNI")
	 private Clob textUNI;
	
	 public TbclTransLetterVarUniTempBO() {
	}

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

	public Integer getClvteSeq() {
		return clvteSeq;
	}

	public void setClvteSeq(Integer clvteSeq) {
		this.clvteSeq = clvteSeq;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getLastUpdateDate() {
		return lastUpdateDate;
	}

	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}

	public Integer getOrsusId() {
		return orsusId;
	}

	public void setOrsusId(Integer orsusId) {
		this.orsusId = orsusId;
	}

	public Date getPrintDate() {
		return printDate;
	}

	public void setPrintDate(Date printDate) {
		this.printDate = printDate;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getTextUNI() {
		try {
			if(textUNI != null && textUNI.length() > 0) {
				return textUNI.getSubString(1, (int)textUNI.length());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public void setTextUNI(Clob textUNI) {
		this.textUNI = textUNI;
	}
	  
	
}
