/*********************************************************************/
/*                           FILE HEADER                             */
/*********************************************************************/
/*                                                                   */
/*  FileName: 		OrganisationDetailsBean.java             	  	     */
/*  																 */
/*  $Author: INVSAR1 $									             */
/*																	 */
/*  $Revision: 1.3 $										         */
/*  																 */
/*  $Date: 2013/05/31 12:16:58 $                                     */
/*                                                                   */
/*  Description: 	This java bean stored organisation details       */
/*				                       				                 */
/*********************************************************************/
/* Date        Name            Version             Comments          */
/*-------------------------------------------------------------------*/
/* 15/04/2013  INVSAR1      	1.0         Initial version created  */
/*********************************************************************/
package com.atradius.beans;

import java.io.Serializable;
import java.util.List;

public class OrganisationDetailsBean implements Serializable {
	
	private static final long serialVersionUID = 2801198147223036441L;
	
	private int orgId;
	private String orgCountry;
	private String orgName;
	private List<ContactBean> contactBeanList;
	/**
	 * @return the contactBeanList
	 */
	public List<ContactBean> getContactBeanList() {
		return contactBeanList;
	}
	/**
	 * @param contactBeanList the contactBeanList to set
	 */
	public void setContactBeanList(List<ContactBean> contactBeanList) {
		this.contactBeanList = contactBeanList;
	}
	/**
	 * @return the orgCountry
	 */
	public String getOrgCountry() {
		return orgCountry;
	}
	/**
	 * @param orgCountry the orgCountry to set
	 */
	public void setOrgCountry(String orgCountry) {
		this.orgCountry = orgCountry;
	}
	/**
	 * @return the orgId
	 */
	public int getOrgId() {
		return orgId;
	}
	/**
	 * @param orgId the orgId to set
	 */
	public void setOrgId(int orgId) {
		this.orgId = orgId;
	}
	/**
	 * @return the orgName
	 */
	public String getOrgName() {
		return orgName;
	}
	/**
	 * @param orgName the orgName to set
	 */
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	
}
