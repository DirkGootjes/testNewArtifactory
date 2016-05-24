package com.atradius.dataaccess.hibernate.bo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TBDO_TEXT_DESCRIPTIONS_TEMP")
public class TbdoTextDescriptionsTempBO {
	
	private String changeNumber;
	private String opdTagName;
	private String currrentTagDesc;
	private String organization;
	private String langCd;
	private String status;
	private String tagVarText;
	
	@Column(updatable = false, insertable = false, name = "LANG_CODE", nullable = false)
	public String getLangCd() {
		return langCd;
	}
	public void setLangCd(final String langCd) {
		this.langCd = langCd;
	}
	
	@Id
	@Column(updatable = false, insertable = false, name = "CHANGE_NUM", nullable = false)
	public String getChangeNumber() {
		return changeNumber;
	}
	public void setChangeNumber(final String changeNumber) {
		this.changeNumber = changeNumber;
	}
	
	@Column(updatable = false, insertable = false, name = "ORG_NAME", nullable = false)
	public String getOrganization() {
		return organization;
	}
	public void setOrganization(final String organization) {
		this.organization = organization;
	}
	
	@Column(updatable = false, insertable = false, name = "STATUS", nullable = false)
	public String getStatus() {
		return status;
	}
	public void setStatus(final String status) {
		this.status = status;
	}
	
	@Column(updatable = false, insertable = false, name = "OPD_TAG", nullable = false)
	public String getOpdTagName() {
		return opdTagName;
	}
	public void setOpdTagName(final String opdTagName) {
		this.opdTagName = opdTagName;
	}
	
	@Column(updatable = true, insertable = true, name = "UNICODE_OPD_DESC", nullable = false)
	public String getTagVarText() {
		return tagVarText;
	}
	public void setTagVarText(final String tagVarText) {
		this.tagVarText = tagVarText;
	}
	
	@Column(updatable = false, insertable = false, name = "CUR_DESC", nullable = false)
	public String getCurrrentTagDesc() {
		return currrentTagDesc;
	}
	public void setCurrrentTagDesc(final String currrentTagDesc) {
		this.currrentTagDesc = currrentTagDesc;
	}
	
	

}
