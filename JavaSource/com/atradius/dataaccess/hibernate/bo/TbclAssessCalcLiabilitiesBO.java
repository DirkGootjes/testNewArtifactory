package com.atradius.dataaccess.hibernate.bo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "TBCL_ASSESS_CALC_LIAB_UNI_TEMP")
public class TbclAssessCalcLiabilitiesBO implements Serializable, Cloneable{

	@EmbeddedId
	private AssessmentCompositeDetailsBO assessDetails;

	@Column(name = "CALC_AMT")
	private String calcAmt;

	@Column(name = "CALC_FREE_TEXT_UNI")
	private String calcFreeTextUni;

	@Column(name = "CALC_FREE_TEXT")
	private String calcFreeText;

	@Column(name = "CLCLC_CALC_CODE")
	private String clclcCalcCode;

	@Column(name = "FNC_CLCLC_DES")
	private String fncClclcDes;

	@Column(name = "USER_CALC_AMT")
	private Integer userCalcAmt;

	@Column(name = "ORSUS_ID")
	private Integer orsusId;

	@Column(name = "LAST_UPDATE_DAT")
	private Date lastUpdateDate;

	/**
	 * @return the assessDetails
	 */
	public AssessmentCompositeDetailsBO getAssessDetails() {
		return assessDetails;
	}

	/**
	 * @param assessDetails
	 *            the assessDetails to set
	 */
	public void setAssessDetails(AssessmentCompositeDetailsBO assessDetails) {
		this.assessDetails = assessDetails;
	}

	/**
	 * @return the calcAmt
	 */
	public String getCalcAmt() {
		return calcAmt;
	}

	/**
	 * @param calcAmt
	 *            the calcAmt to set
	 */
	public void setCalcAmt(String calcAmt) {
		this.calcAmt = calcAmt;
	}

	/**
	 * @return the calcFreeText
	 */
	public String getCalcFreeText() {
		return calcFreeText;
	}

	/**
	 * @param calcFreeText
	 *            the calcFreeText to set
	 */
	public void setCalcFreeText(String calcFreeText) {
		this.calcFreeText = calcFreeText;
	}

	/**
	 * @return the calcFreeTextUni
	 */
	public String getCalcFreeTextUni() {
		return calcFreeTextUni;
	}

	/**
	 * @param calcFreeTextUni
	 *            the calcFreeTextUni to set
	 */
	public void setCalcFreeTextUni(String calcFreeTextUni) {
		this.calcFreeTextUni = calcFreeTextUni;
	}

	/**
	 * @return the clclcCalcCode
	 */
	public String getClclcCalcCode() {
		return clclcCalcCode;
	}

	/**
	 * @param clclcCalcCode
	 *            the clclcCalcCode to set
	 */
	public void setClclcCalcCode(String clclcCalcCode) {
		this.clclcCalcCode = clclcCalcCode;
	}

	/**
	 * @return the lastUpdateDate
	 */
	public Date getLastUpdateDate() {
		return lastUpdateDate;
	}

	/**
	 * @param lastUpdateDate
	 *            the lastUpdateDate to set
	 */
	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}

	/**
	 * @return the orsusId
	 */
	public Integer getOrsusId() {
		return orsusId;
	}

	/**
	 * @param orsusId
	 *            the orsusId to set
	 */
	public void setOrsusId(Integer orsusId) {
		this.orsusId = orsusId;
	}

	/**
	 * @return the userCalcAmt
	 */
	public Integer getUserCalcAmt() {
		return userCalcAmt;
	}

	/**
	 * @param userCalcAmt
	 *            the userCalcAmt to set
	 */
	public void setUserCalcAmt(Integer userCalcAmt) {
		this.userCalcAmt = userCalcAmt;
	}

	public String getFncClclcDes() {
		return fncClclcDes;
	}

	public void setFncClclcDes(String fncClclcDes) {
		this.fncClclcDes = fncClclcDes;
	}
	public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
	
	/*public TbclAssessCalcLiabilitiesBO copyObject(){
		TbclAssessCalcLiabilitiesBO newBean = new TbclAssessCalcLiabilitiesBO();
		newBean.setCalcAmt(this.calcAmt);
		newBean.setCalcFreeText(this.calcFreeText)
		newBean.setCalcFreeTextUni(this.calcFreeTextUni);
		
		AssessmentCompositeDetailsBO assessDetails = new AssessmentCompositeDetailsBO();
		newBean.setAssessDetails(this.assessDetails);
		
		return newBean;
		
	}*/
	
}
