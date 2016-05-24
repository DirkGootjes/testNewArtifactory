/*********************************************************************/
/*                           FILE HEADER                             */
/*********************************************************************/
/*                                                                   */
/*  FileName: 		TborOrgAddressesUnicodeBO.java    	  	         */
/*  																 */
/*  $Author: INVSAR1 $									             */
/*																	 */
/*  $Revision: 1.8 $										         */
/*  																 */
/*  $Date: 2013/06/12 10:25:12 $                                     */
/*                                                                   */
/*  Description: 	This class reprent the mappping with             */
/*				  	tbor_org_addresses_uni table					 */
/*				                   					                 */
/*********************************************************************/
/* Date        Name            Version             Comments          */
/*-------------------------------------------------------------------*/
/* 20/04/2013  INRSHR1      	1.0         Initial version created  */
/*********************************************************************/
package com.atradius.dataaccess.hibernate.bo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name = "tbor_org_addresses_uni")
@IdClass(TborOrgAddressesUnicodePK.class)
public class TborOrgAddressesUnicodeBO implements Serializable {

	private static final long serialVersionUID = 4869449441266424733L;

	private int oroasId;

	private String langCode;

	private String firstLineStreetAddr;

	private String secondLineStreetAddr;

	private String thirdLineStreetAddr;

	private String regionName;

	private String cityName;

	private String postCode;

	private Date effectFromDate;

	private Date effectToDate;

	private String countryName;

	public TborOrgAddressesUnicodeBO() {

	}

	@Id
	@Column(updatable = true, insertable = true, name = "oroasId", nullable = false, length = 8)
	public int getOroasId() {
		return oroasId;
	}

	public void setOroasId(int oroasId) {
		this.oroasId = oroasId;
	}

	@Id
	@Column(updatable = true, insertable = true, name = "bulae_lang_code", nullable = false, length = 8)
	public String getLangCode() {
		return langCode;
	}

	public void setLangCode(String langCode) {
		this.langCode = langCode;
	}

	@Column(updatable = true, insertable = true, name = "first_line_street_addr", nullable = true, length = 35)
	public String getFirstLineStreetAddr() {
		return firstLineStreetAddr;
	}

	public void setFirstLineStreetAddr(String firstLineStreetAddr) {
		this.firstLineStreetAddr = firstLineStreetAddr;
	}

	@Column(updatable = true, insertable = true, name = "second_line_street_addr", nullable = true, length = 35)
	public String getSecondLineStreetAddr() {
		return secondLineStreetAddr;
	}

	public void setSecondLineStreetAddr(String secondLineStreetAddr) {
		this.secondLineStreetAddr = secondLineStreetAddr;
	}

	@Column(updatable = true, insertable = true, name = "third_line_street_addr", nullable = true, length = 35)
	public String getThirdLineStreetAddr() {
		return thirdLineStreetAddr;
	}

	public void setThirdLineStreetAddr(String thirdLineStreetAddr) {
		this.thirdLineStreetAddr = thirdLineStreetAddr;
	}

	@Column(updatable = true, insertable = true, name = "region_name", nullable = true, length = 20)
	public String getRegionName() {
		return regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}

	

	@Column(updatable = true, insertable = true, name = "city_name", nullable = true, length = 35)
	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	@Column(updatable = true, insertable = true, name = "post_code", nullable = true, length = 10)
	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}
	@Id
	@Column(updatable = true, insertable = true, name = "effect_from_dat", nullable = true)
	public Date getEffectFromDate() {
		return effectFromDate;
	}

	public void setEffectFromDate(Date effectFromDate) {
		this.effectFromDate = effectFromDate;
	}

	
	@Column(updatable = true, insertable = true, name = "effect_to_dat", nullable = true)
	public Date getEffectToDate() {
		return effectToDate;
	}

	public void setEffectToDate(Date effectToDate) {
		this.effectToDate = effectToDate;
	}

	

	@Column(updatable = true, insertable = true, name = "orcoy_name", nullable = true)
	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	@Override
	public String toString() {
		String returnStr = "oroasId [" + oroasId + "] firstLineStreetAddr ["
				+ firstLineStreetAddr + "] regionName [" + regionName
				+ "]cityName [" + cityName
				+ "]postCode [" + postCode + "]langCode [" + langCode
				+ "] effectFromDate [" + effectFromDate + "] effectToDate ["
				+ effectToDate + "] countryName ["
				+ countryName + "]";

		return returnStr;
	}

	public boolean equals(Object other) {
		if (this == other)
			return true;
		if (!(other instanceof TborOrgAddressesUnicodeBO))
			return false;

		final TborOrgAddressesUnicodeBO test = (TborOrgAddressesUnicodeBO) other;

		if (test.getOroasId() != (getOroasId()))
			return false;
		if (!test.getEffectToDate().equals(getEffectToDate()))
			return false;
		if (!test.getLangCode().equals(getLangCode()))
			return false;

		return true;
	}

	public int hashCode() {
		int result;
		result = getOroasId() + getEffectFromDate().hashCode()
				+ getLangCode().hashCode();
		return result;
	}

}
