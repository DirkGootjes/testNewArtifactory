package com.atradius.dataaccess.hibernate.bo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/** This BO class is used to hold the policy cover trade data
 * @author INKPAG1
 *
 */
@Entity
@Table(name = "tbpo_pol_trade_descs_temp")
public class TbpoPolTradeDescsBO {
	/**
	 * policyId
	 */
	private String policyId;
	/**
	 * effect_from_date
	 */
	private Date effectFromDate;
	/**
	 * tradeDesc
	 */
	private String tradeDesc;
	
	/** Gets effect date.
	 * @return
	 */
	@Column(updatable = false, insertable = false, name = "Effect_from_dat", nullable = false)
	public Date getEffectFromDate() {
		return effectFromDate;
	}
	/** Sets effect date
	 * @param effect_from_date
	 */
	public void setEffectFromDate(final Date effectFromDate) {
		this.effectFromDate = effectFromDate;
	}
	/** Gets policy ID
	 * @return
	 */
	@Id
	@Column(updatable = false, insertable = false, name = "BUPIY_ID", nullable = false)
	public String getPolicyId() {
		return policyId;
	}
	/** Sets policy ID
	 * @param policyId
	 */
	public void setPolicyId(final String policyId) {
		this.policyId = policyId;
	}
	
	/** gets policy trade description
	 * @return
	 */
	@Column(updatable = false, insertable = false, name = "DES", nullable = false)
	public String getTradeDesc() {
		return tradeDesc;
	}
	/** Sets policy trade description
	 * @param tradeDesc
	 */
	public void setTradeDesc(final String tradeDesc) {
		this.tradeDesc = tradeDesc;
	}	

}
