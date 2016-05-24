package com.atradius.dataaccess.hibernate.bo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;

/** This class is used to hold the Modula policy modules and variables information.
 * @author INKPAG1
 *
 */
@Entity
@Table(name = "tbpo_pol_mod_variables_temp")
public class TbpoPolModVariablesBO {
	
	/**
	 * Policy id
	 */
	private String policyId;
	
	/**
	 * Effect from date
	 */
	private Date effectFromDate;
	
	/**
	 *  Module variable code
	 */
	private String variableCode;
	
	/**
	 * Module variable code value
	 */
	private String variableValue;

	
	/** Gets effect from date
	 * @return
	 */
	@Column(updatable = false, insertable = false, name = "Effect_from_dat", nullable = false)
	@Temporal( javax.persistence.TemporalType.DATE )
	public Date getEffectFromDate() {
		return effectFromDate;
	}

	/** Sets effect from date
	 * @param effectFromDate
	 */
	public void setEffectFromDate(final Date effectFromDate) {
		this.effectFromDate = effectFromDate;
	}

	/** Gets policy id
	 * @return
	 */
	@Id
	@Column(updatable = false, insertable = false, name = "BUPIY_ID", nullable = false)
	public String getPolicyId() {
		return policyId;
	}

	/** sets policy id
	 * @param policyId
	 */
	public void setPolicyId(final String policyId) {
		this.policyId = policyId;
	}

	/**gets Variable code
	 * @return
	 */
	@Column(updatable = false, insertable = false, name = "Porve_code", nullable = false)
	public String getVariableCode() {
		return variableCode;
	}

	/** Sets variable code
	 * @param variableCode
	 */
	public void setVariableCode(final String variableCode) {
		this.variableCode = variableCode;
	}

	/**Gets variable value
	 * @return
	 */
	@Column(updatable = false, insertable = false, name = "Unicode_Value", nullable = false)
	public String getVariableValue() {
		return variableValue;
	}

	/** Sets variable code value
	 * @param variableValue
	 */
	public void setVariableValue(final String variableValue) {
		this.variableValue = variableValue;
	}
	
	

}
