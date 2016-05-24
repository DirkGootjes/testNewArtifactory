/*********************************************************************/
/*                           FILE HEADER                             */
/*********************************************************************/
/*                                                                   */
/*  FileName: 		ContactDetails.java             		  	     */
/*  																 */
/*  $Author: INVSAR1 $									             */
/*																	 */
/*  $Revision: 1.6 $										         */
/*  																 */
/*  $Date: 2013/05/31 12:16:58 $                                     */
/*                                                                   */
/*  Description: 	This java bean stored contact details            */
/*				                       				                 */
/*********************************************************************/
/* Date        Name            Version             Comments          */
/*-------------------------------------------------------------------*/
/* 15/04/2013  INVSAR1      	1.0         Initial version created  */
/*********************************************************************/
package com.atradius.beans;

public class ContactDetails implements java.io.Serializable {

	private static final long serialVersionUID = -6362667726515149512L;

	private String indivName;

	private int indivId;

	private String salutation;

	private int orgNameId;

	private String orgNameFirstLine;

	private String orgNameSecondLine;

	private String orgNameThirdLine;

	private int orgAddressId;

	private String orgAddressFirstLine;

	private String orgAddressSecondLine;

	private String orgAddressThirdLine;

	private String orgCityName;

	private String orgPostCode;

	private String orgRegion;

	private String orgCountryName;

	/**
	 * @return the indivId
	 */
	public int getIndivId() {
		return indivId;
	}

	/**
	 * @param indivId
	 *            the indivId to set
	 */
	public void setIndivId(int indivId) {
		this.indivId = indivId;
	}

	/**
	 * @return the indivName
	 */
	public String getIndivName() {
		return indivName;
	}

	/**
	 * @param indivName
	 *            the indivName to set
	 */
	public void setIndivName(String indivName) {
		this.indivName = indivName;
	}

	/**
	 * @return the salutation
	 */
	public String getSalutation() {
		return salutation;
	}

	/**
	 * @param salutation
	 *            the salutation to set
	 */
	public void setSalutation(String salutation) {
		this.salutation = salutation;
	}

	public String getOrgNameFirstLine() {
		return orgNameFirstLine;
	}

	public void setOrgNameFirstLine(String orgNameFirstLine) {
		this.orgNameFirstLine = orgNameFirstLine;
	}

	public String getOrgNameSecondLine() {
		return orgNameSecondLine;
	}

	public void setOrgNameSecondLine(String orgNameSecondLine) {
		this.orgNameSecondLine = orgNameSecondLine;
	}

	public String getOrgNameThirdLine() {
		return orgNameThirdLine;
	}

	public void setOrgNameThirdLine(String orgNameThirdLine) {
		this.orgNameThirdLine = orgNameThirdLine;
	}

	/**
	 * @return the orgNameId
	 */
	public int getOrgNameId() {
		return orgNameId;
	}

	/**
	 * @param orgNameId
	 *            the orgNameId to set
	 */
	public void setOrgNameId(int orgNameId) {
		this.orgNameId = orgNameId;
	}

	public String getOrgAddressFirstLine() {
		return orgAddressFirstLine;
	}

	public void setOrgAddressFirstLine(String orgAddressFirstLine) {
		this.orgAddressFirstLine = orgAddressFirstLine;
	}

	public String getOrgAddressSecondLine() {
		return orgAddressSecondLine;
	}

	public void setOrgAddressSecondLine(String orgAddressSecondLine) {
		this.orgAddressSecondLine = orgAddressSecondLine;
	}

	public String getOrgAddressThirdLine() {
		return orgAddressThirdLine;
	}

	public void setOrgAddressThirdLine(String orgAddressThirdLine) {
		this.orgAddressThirdLine = orgAddressThirdLine;
	}

	/**
	 * @return the orgAddressId
	 */
	public int getOrgAddressId() {
		return orgAddressId;
	}

	/**
	 * @param orgAddressId
	 *            the orgAddressId to set
	 */
	public void setOrgAddressId(int orgAddressId) {
		this.orgAddressId = orgAddressId;
	}

	
	/**
	 * @return the orgCountryName
	 */
	public String getOrgCountryName() {
		return orgCountryName;
	}

	/**
	 * @param orgCountryName
	 *            the orgCountryName to set
	 */
	public void setOrgCountryName(String orgCountryName) {
		this.orgCountryName = orgCountryName;
	}

	/**
	 * @return the orgPostCode
	 */
	public String getOrgPostCode() {
		return orgPostCode;
	}

	/**
	 * @param orgPostCode
	 *            the orgPostCode to set
	 */
	public void setOrgPostCode(String orgPostCode) {
		this.orgPostCode = orgPostCode;
	}

	/**
	 * @return the orgRegion
	 */
	public String getOrgRegion() {
		return orgRegion;
	}

	/**
	 * @param orgRegion
	 *            the orgRegion to set
	 */
	public void setOrgRegion(String orgRegion) {
		this.orgRegion = orgRegion;
	}

	
	/**
	 * @return the orgCityName
	 */
	public String getOrgCityName() {
		return orgCityName;
	}

	/**
	 * @param orgCityName
	 *            the orgCityName to set
	 */
	public void setOrgCityName(String orgCityName) {
		this.orgCityName = orgCityName;
	}

}
