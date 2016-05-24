/*********************************************************************/
/*                           FILE HEADER                             */
/*********************************************************************/
/*                                                                   */
/*  FileName: 		ContactAJAXBean.java	             	  	     */
/*  																 */
/*  $Author: INVSAR1 $									             */
/*																	 */
/*  $Revision: 1.6 $										         */
/*  																 */
/*  $Date: 2013/05/31 12:16:58 $                                     */
/*                                                                   */
/*  Description: 	This java bean stored AJAX response              */
/*				  	of contact details					              */
/*********************************************************************/
/* Date        Name            Version             Comments          */
/*-------------------------------------------------------------------*/
/* 04/04/2013  INVSAR1      	1.0         Initial version created  */
/*********************************************************************/
package com.atradius.beans;

public class ContactAJAXBean implements java.io.Serializable {
	
	private static final long serialVersionUID = -607151907963013779L;
	public ContactDetails latinContact;
	public ContactDetails unicodeContact;
	public String errorMsg;
	public boolean latinIndividualAvailabe;
	public boolean latinOrgNameAvailabe;
	public boolean latinOrgAddressAvailabe;
	
	public boolean unicodeIndividualAvailabe;
	public boolean unicodeOrgNameAvailabe;
	public boolean unicodeOrgAddressAvailabe;
	
	/**
	 * @return the latinContact
	 */
	public ContactDetails getLatinContact() {
		return latinContact;
	}
	/**
	 * @param latinContact the latinContact to set
	 */
	public void setLatinContact(ContactDetails latinContact) {
		this.latinContact = latinContact;
	}
	/**
	 * @return the unicodeContact
	 */
	public ContactDetails getUnicodeContact() {
		return unicodeContact;
	}
	/**
	 * @param unicodeContact the unicodeContact to set
	 */
	public void setUnicodeContact(ContactDetails unicodeContact) {
		this.unicodeContact = unicodeContact;
	}
	/**
	 * @return the errorMsg
	 */
	public String getErrorMsg() {
		return errorMsg;
	}
	/**
	 * @param errorMsg the errorMsg to set
	 */
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	public boolean isLatinIndividualAvailabe() {
		return latinIndividualAvailabe;
	}
	public void setLatinIndividualAvailabe(boolean latinIndividualAvailabe) {
		this.latinIndividualAvailabe = latinIndividualAvailabe;
	}
	public boolean isLatinOrgAddressAvailabe() {
		return latinOrgAddressAvailabe;
	}
	public void setLatinOrgAddressAvailabe(boolean latinOrgAddressAvailabe) {
		this.latinOrgAddressAvailabe = latinOrgAddressAvailabe;
	}
	public boolean isLatinOrgNameAvailabe() {
		return latinOrgNameAvailabe;
	}
	public void setLatinOrgNameAvailabe(boolean latinOrgNameAvailabe) {
		this.latinOrgNameAvailabe = latinOrgNameAvailabe;
	}
	public boolean isUnicodeIndividualAvailabe() {
		return unicodeIndividualAvailabe;
	}
	public void setUnicodeIndividualAvailabe(boolean unicodeIndividualAvailabe) {
		this.unicodeIndividualAvailabe = unicodeIndividualAvailabe;
	}
	public boolean isUnicodeOrgAddressAvailabe() {
		return unicodeOrgAddressAvailabe;
	}
	public void setUnicodeOrgAddressAvailabe(boolean unicodeOrgAddressAvailabe) {
		this.unicodeOrgAddressAvailabe = unicodeOrgAddressAvailabe;
	}
	public boolean isUnicodeOrgNameAvailabe() {
		return unicodeOrgNameAvailabe;
	}
	public void setUnicodeOrgNameAvailabe(boolean unicodeOrgNameAvailabe) {
		this.unicodeOrgNameAvailabe = unicodeOrgNameAvailabe;
	}
	
	
	
}
