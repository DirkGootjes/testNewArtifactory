package com.atradius.dataaccess.hibernate.bo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/** This BO class is used to holds the data from view VWPO_HEADER_POLICIES
 * @author INKPAG1
 *
 */
@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
@Table(name="VWPO_HEADER_POLICIES")
public class VwpoHeaderPoliciesBO {
	
	/**policyId
	 * 
	 */
	private String policyId;
	//it maps to ORCUR_ORNNN_ID
	/**customerId
	 * 
	 */
	private String customerId; 
	
	/**
	 * lang
	 */
	private String lang;
	
	/**
	 * customerName
	 */
	private String customerName;
	
	

	/** Gets teh customer id
	 * @return
	 */
	@Column(updatable = false, insertable = false, name = "ORCUR_ORNNN_ID", nullable = false)
	public String getCustomerId() {
		return customerId;
	}

	/** sets the customer id
	 * @param customerId
	 */
	public void setCustomerId(final String customerId) {
		this.customerId = customerId;
	}

	/** gets teh language
	 * @return
	 */
	@Column(updatable = false, insertable = false, name = "BULAE_LANG_CODE", nullable = false)
	public String getLang() {
		return lang;
	}

	/** sets teh language
	 * @param lang
	 */
	public void setLang(final String lang) {
		this.lang = lang;
	}
	/** Gets teh policy id
	 * @return
	 */
	@Id
	@Column(updatable = false, insertable = false, name = "ID", nullable = false)
	public String getPolicyId() {
		return policyId;
	}

	public void setPolicyId(final String policyId) {
		this.policyId = policyId;
	}

	/**Gets the customer name
	 * @return
	 */
	@Column(updatable = false, insertable = false, name = "FNC_D_ORNOL_SHORT_NAME", nullable = false)
	public String getCustomerName() {
		return customerName;
	}

	/**Sets teh customer name
	 * @param customerName
	 */
	public void setCustomerName(final String customerName) {
		this.customerName = customerName;
	}

	
	

}
