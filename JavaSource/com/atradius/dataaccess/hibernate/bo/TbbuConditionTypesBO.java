package com.atradius.dataaccess.hibernate.bo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table (name = "TBBU_CONDITION_TYPES")
public class TbbuConditionTypesBO {
	
	@Id
	@Column ( name = "CODE" )
	private String code;
	
	@Column ( name = "TYP_IND" )
	private String typeInd;

	@Column ( name = "PRINT_FLAG" )
	private String printFlag;

	@Column ( name = "USAGE_TYP" )
	private String usageType;

	@Column ( name = "ORSUS_ID" )
	private Integer orsusId;

	@Column ( name = "VALID_FLAG" )
	private String validFlag;

	@Column ( name = "LAST_UPDATE_DAT" )
	private Date lastUpdateDate;

	@Column ( name = "LAST_UPDATE_SEQ" )
	private Integer lastUpdateSeq;
	
	@Column ( name = "BUYER_FLAG" )
	private String buyerFlag;

	@Column ( name = "MANUAL_FLAG" )
	private String manualFlag;

	@Column ( name = "DES" )
	private String description;

	@Column ( name = "SEVERITY_IND" )
	private String severityInd;

	@Column ( name = "BUCDE_NEW_CODE" )
	private String bucdeNewCode;

	@Column ( name = "INVALID_FROM_DAT" )
	private Date invalidFromDate;

	@Column ( name = "BUCDE_NEW_CODE_DAT" )
	private Date bucdeNewCodeDate;

	@Column ( name = "AUA_FLAG" )
	private String auaFlag;

	@Column ( name = "CAT_CODE" )
	private String catCode;

	@Column ( name = "MCHOICE_FLAG" )
	private String mchoiceFlag;

	@Column ( name = "PREFER_BUMCT_SEQ" )
	private Integer preferBumctSeq;

	
	public TbbuConditionTypesBO()  {
	}


	public String getAuaFlag() {
		return auaFlag;
	}


	public void setAuaFlag(String auaFlag) {
		this.auaFlag = auaFlag;
	}


	public String getBucdeNewCode() {
		return bucdeNewCode;
	}


	public void setBucdeNewCode(String bucdeNewCode) {
		this.bucdeNewCode = bucdeNewCode;
	}


	public Date getBucdeNewCodeDate() {
		return bucdeNewCodeDate;
	}


	public void setBucdeNewCodeDate(Date bucdeNewCodeDate) {
		this.bucdeNewCodeDate = bucdeNewCodeDate;
	}


	public String getBuyerFlag() {
		return buyerFlag;
	}


	public void setBuyerFlag(String buyerFlag) {
		this.buyerFlag = buyerFlag;
	}


	public String getCatCode() {
		return catCode;
	}


	public void setCatCode(String catCode) {
		this.catCode = catCode;
	}


	public String getCode() {
		return code;
	}


	public void setCode(String code) {
		this.code = code;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public Date getInvalidFromDate() {
		return invalidFromDate;
	}


	public void setInvalidFromDate(Date invalidFromDate) {
		this.invalidFromDate = invalidFromDate;
	}


	public Date getLastUpdateDate() {
		return lastUpdateDate;
	}


	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}


	public Integer getLastUpdateSeq() {
		return lastUpdateSeq;
	}


	public void setLastUpdateSeq(Integer lastUpdateSeq) {
		this.lastUpdateSeq = lastUpdateSeq;
	}


	public String getManualFlag() {
		return manualFlag;
	}


	public void setManualFlag(String manualFlag) {
		this.manualFlag = manualFlag;
	}


	public String getMchoiceFlag() {
		return mchoiceFlag;
	}


	public void setMchoiceFlag(String mchoiceFlag) {
		this.mchoiceFlag = mchoiceFlag;
	}


	public Integer getOrsusId() {
		return orsusId;
	}


	public void setOrsusId(Integer orsusId) {
		this.orsusId = orsusId;
	}


	public Integer getPreferBumctSeq() {
		return preferBumctSeq;
	}


	public void setPreferBumctSeq(Integer preferBumctSeq) {
		this.preferBumctSeq = preferBumctSeq;
	}


	public String getPrintFlag() {
		return printFlag;
	}


	public void setPrintFlag(String printFlag) {
		this.printFlag = printFlag;
	}


	public String getSeverityInd() {
		return severityInd;
	}


	public void setSeverityInd(String severityInd) {
		this.severityInd = severityInd;
	}


	public String getTypeInd() {
		return typeInd;
	}


	public void setTypeInd(String typeInd) {
		this.typeInd = typeInd;
	}


	public String getUsageType() {
		return usageType;
	}


	public void setUsageType(String usageType) {
		this.usageType = usageType;
	}


	public String getValidFlag() {
		return validFlag;
	}


	public void setValidFlag(String validFlag) {
		this.validFlag = validFlag;
	}


}
