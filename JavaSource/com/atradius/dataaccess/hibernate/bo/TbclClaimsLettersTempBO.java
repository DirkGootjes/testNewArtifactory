/*********************************************************************/
/*                           FILE HEADER                             */
/*********************************************************************/
/*                                                                   */
/*  FileName: 		TbclClaimsLettersTempBO.java             	     */
/*  																 */
/*  $Author: INVSAR1 $									             */
/*																	 */
/*  $Revision: 1.2 $										         */
/*  																 */
/*  $Date: 2014/09/23 16:17:28 $                                     */
/*                                                                   */
/*  Description: 	This class reprent the mappping with             */
/*				  	TBCL_CLAIMS_LETTERS_TEMP table*/
/*				                   					                 */
/*********************************************************************/
/* Date        Name            Version             Comments          */
/*-------------------------------------------------------------------*/
/* 01/09/2014  INASHA2      	1.0         Initial version created  */
/*********************************************************************/


package com.atradius.dataaccess.hibernate.bo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "TBCL_CLAIMS_LETTERS_TEMP")
public class TbclClaimsLettersTempBO implements Serializable {


	@EmbeddedId
	private TbclClaimsLetterCompositeBO tbclClaimsLetterCompositeBO;
	
	@Column(name = "VIA_BROKER_FLAG")
	private String viaBrokerFlag;
	
	@Column(name = "EXT_VIEW_FLAG")
	private String extViewFlag;
	
	@Column(name = "PRINT_TYP")
	private String printType;
	
	@Column(name = "ORSUS_ID")
	private Integer orsusId;
	
	@Column(name = "LAST_UPDATE_DAT")
	private Date lastUpdateDate;
	
	@Column(name = "PRINTED_DAT")
	private Date printedDate;
	
	@Column(name = "CLCPT_ID")
	private Integer clcptId;
	
	@Column(name = "CLCLN_BUCCE_ID")
	private Integer clclnBucceId;
	
	@Column(name = "CLCLN_RUN_DAT")
	private Date clclnRunDate;
	
	@Column(name = "CLRSE_ID")
	private Integer clrseId;
	
	@Column(name = "ATD_CONTACT_UID")
	private String atdContactUID;
	
	@Column(name = "OUTPUT_SYSTEM_IND")
	private String outputSystemInd;
	
	@Column(name = "OPUS_ACK_ID")
	private Integer opusAckId;
	
	@Column(name = "OPUS_PRINTED_DAT")
	private Date opusPrintedDate;
	
	public TbclClaimsLettersTempBO() {
	}
	
	
	
	public TbclClaimsLetterCompositeBO getTbclClaimsLetterCompositeBO() {
		return tbclClaimsLetterCompositeBO;
	}



	public void setTbclClaimsLetterCompositeBO(
			TbclClaimsLetterCompositeBO tbclClaimsLetterCompositeBO) {
		this.tbclClaimsLetterCompositeBO = tbclClaimsLetterCompositeBO;
	}



	public String getAtdContactUID() {
		return atdContactUID;
	}
	public void setAtdContactUID(String atdContactUID) {
		this.atdContactUID = atdContactUID;
	}
	
	public Integer getClclnBucceId() {
		return clclnBucceId;
	}
	public void setClclnBucceId(Integer clclnBucceId) {
		this.clclnBucceId = clclnBucceId;
	}
	public Date getClclnRunDate() {
		return clclnRunDate;
	}
	public void setClclnRunDate(Date clclnRunDate) {
		this.clclnRunDate = clclnRunDate;
	}
	
	public Integer getClcptId() {
		return clcptId;
	}
	public void setClcptId(Integer clcptId) {
		this.clcptId = clcptId;
	}
	public Integer getClrseId() {
		return clrseId;
	}
	public void setClrseId(Integer clrseId) {
		this.clrseId = clrseId;
	}
	public String getExtViewFlag() {
		return extViewFlag;
	}
	public void setExtViewFlag(String extViewFlag) {
		this.extViewFlag = extViewFlag;
	}
	public Date getLastUpdateDate() {
		return lastUpdateDate;
	}
	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}
	public Integer getOpusAckId() {
		return opusAckId;
	}
	public void setOpusAckId(Integer opusAckId) {
		this.opusAckId = opusAckId;
	}
	public Date getOpusPrintedDate() {
		return opusPrintedDate;
	}
	public void setOpusPrintedDate(Date opusPrintedDate) {
		this.opusPrintedDate = opusPrintedDate;
	}
	public Integer getOrsusId() {
		return orsusId;
	}
	public void setOrsusId(Integer orsusId) {
		this.orsusId = orsusId;
	}
	public String getOutputSystemInd() {
		return outputSystemInd;
	}
	public void setOutputSystemInd(String outputSystemInd) {
		this.outputSystemInd = outputSystemInd;
	}
	
	public Date getPrintedDate() {
		return printedDate;
	}
	public void setPrintedDate(Date printedDate) {
		this.printedDate = printedDate;
	}
	public String getPrintType() {
		return printType;
	}
	public void setPrintType(String printType) {
		this.printType = printType;
	}
	public String getViaBrokerFlag() {
		return viaBrokerFlag;
	}
	public void setViaBrokerFlag(String viaBrokerFlag) {
		this.viaBrokerFlag = viaBrokerFlag;
	}
	
	
	
	
	
}
