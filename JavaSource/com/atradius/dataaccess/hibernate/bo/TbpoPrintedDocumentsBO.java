package com.atradius.dataaccess.hibernate.bo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/** This BO class is used toget the policy requested leeter info
 * @author INKPAG1
 *
 */
@Entity
@Table(name = "tbpo_printed_documents_temp")
public class TbpoPrintedDocumentsBO {

	/**policyId
	 * 
	 */
	private String policyId;

	/**
	 * effect_date
	 */
	private Date printDate;

	/**
	 * descFreeText
	 */
	private String descFreeText;

	/**
	 * groupCode
	 */
	private String groupCode;
	
	/**
	 * groupCodeFuncDesc
	 */
	private String groupCodeFuncDesc;

	/** Gets the description free text
	 * @return
	 */
	@Column(updatable = true, insertable = true, name = "UNICODE_TEXT", nullable = false)
	public String getDescFreeText() {
		return descFreeText;
	}

	/** Sets the description free text
	 * @param descFreeText
	 */
	public void setDescFreeText(final String descFreeText) {
		this.descFreeText = descFreeText;
	}

	/** Gets the print date
	 * @return
	 */
	@Column(updatable = true, insertable = true, name = "PRINT_DAT", nullable = false)
	public Date getPrintDate() {
		return printDate;
	}

	/** Sets the print date
	 * @param effect_date
	 */
	public void setPrintDate(final Date printDate) {
		this.printDate = printDate;
	}
	/** Gets policy id
	 * @return
	 */
	@Id
	@Column(updatable = true, insertable = true, name = "BUPIY_ID", nullable = false)
	public String getPolicyId() {
		return policyId;
	}

	/**Sets policy ID
	 * @param policyId
	 */
	public void setPolicyId(final String policyId) {
		this.policyId = policyId;
	}
		
	/** Gets document group code
	 * @return
	 */
	@Column(updatable = false, insertable = false, name = "PODGP_CODE", nullable = false)
	public String getGroupCode() {
		return groupCode;
	}

	/** Sets document group code
	 * @param groupCode
	 */
	public void setGroupCode(final String groupCode) {
		this.groupCode = groupCode;
	}
	
	
	/** Gets document group code description
	 * @return
	 */
	@Column(updatable = false, insertable = false, name = "FNC_DES", nullable = false)
	public String getGroupCodeFuncDesc() {
		return groupCodeFuncDesc;
	}

	
	/** Sets document group code description
	 * @param groupCodeFuncDesc
	 */
	public void setGroupCodeFuncDesc(final String groupCodeFuncDesc) {
		this.groupCodeFuncDesc = groupCodeFuncDesc;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {

		return "TbpoPrintedDocumentsBO = [policyId=" + policyId + "; descFreeText=" + descFreeText + "; effect_date=" + printDate
				+ "; podgp_cd=" + groupCode + "; groupCodeFuncDesc="+groupCodeFuncDesc+" ]";
	}

}
