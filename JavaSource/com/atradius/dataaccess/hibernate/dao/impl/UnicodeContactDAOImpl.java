/*********************************************************************/
/*                           FILE HEADER                             */
/*********************************************************************/
/*                                                                   */
/*  FileName: 		UnicodeContactDAOImpl.java             	  	     */
/*  																 */
/*  $Author: INVSAR1 $									             */
/*																	 */
/*  $Revision: 1.23 $										         */
/*  																 */
/*  $Date: 2013/08/16 12:46:11 $                                     */
/*                                                                   */
/*  Description: 	This class performs database operation for       */
/*				     Unicode Contact details.                   	 */
/*********************************************************************/
/* Date        Name            Version             Comments          */
/*-------------------------------------------------------------------*/
/* 25/04/2013  INVSAR1      	1.0         Initial version created  */
/*********************************************************************/
package com.atradius.dataaccess.hibernate.dao.impl;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.atradius.beans.AJAXResult;
import com.atradius.beans.ContactAJAXBean;
import com.atradius.beans.ContactDetails;
import com.atradius.dataaccess.hibernate.HibernateUtil;
import com.atradius.dataaccess.hibernate.bo.TborContactPointsBO;
import com.atradius.dataaccess.hibernate.bo.TborIndividualsBO;
import com.atradius.dataaccess.hibernate.bo.TborIndividualsUnicodeBO;
import com.atradius.dataaccess.hibernate.bo.TborOrgAddressesBO;
import com.atradius.dataaccess.hibernate.bo.TborOrgAddressesUnicodeBO;
import com.atradius.dataaccess.hibernate.bo.TborOrgNamesBO;
import com.atradius.dataaccess.hibernate.bo.TborOrgNamesUnicodeBO;
import com.atradius.exception.DataAccessException;
import com.atradius.sessiondata.ApplicationConstants;
import com.atradius.util.logging.ILogger;
import com.atradius.util.logging.LoggerFactory;
import com.atradius.utils.CommonUtil;

public class UnicodeContactDAOImpl {
	private static ILogger logger = LoggerFactory
			.getLogger(UnicodeContactDAOImpl.class);

	/**
	 * @param index
	 * @param langCode
	 * @param session
	 * @return
	 */
	public ContactAJAXBean loadContactDetails(int index, String langCode,
			HttpSession session) {
		logger.enterMethod("loadContactDetails", index, langCode);
		ContactAJAXBean returnBean = new ContactAJAXBean();
		String errorMsg = null;
		try {
			List<TborContactPointsBO> contactList = (List<TborContactPointsBO>) session
					.getAttribute(ApplicationConstants.ATTR_CONTACT_LIST);
			if (contactList == null || contactList.size() <= 0) {
				errorMsg = ApplicationConstants.ERROR_NO_CONTACT;
				logger.error(errorMsg);
				returnBean.setErrorMsg(errorMsg);
				return returnBean;
			} else {
				TborContactPointsBO selContactPoint = contactList.get(index);

				HibernateUtil hibernateUtil = (HibernateUtil) session
						.getAttribute(ApplicationConstants.HIBERNATE_UTIL);
				Session hibernateSession = hibernateUtil.getSession();

				ContactDetails latinContact = new ContactDetails();
				ContactDetails unicodeContact = new ContactDetails();

				int indivId = 0;
				String indivName = "";
				String salutation = "";

				int unicodeIndivId = 0;
				String unicodeIndivName = "";
				String unicodeSalutation = "";

				// individual details
				TborIndividualsBO individual = selContactPoint
						.getContactIndividual();
				if (individual != null) {
					returnBean.setLatinIndividualAvailabe(true);

					indivId = individual.getIndivId();
					if (individual.getIndivName() != null)
						indivName = individual.getIndivName();
					if (individual.getSalutatName() != null)
						salutation = individual.getSalutatName();

					TborIndividualsUnicodeBO unicodeIndividual = TborIndividualsUnicodeDAOImpl
							.getUnicodeVersion(hibernateSession, indivId,
									langCode);

					unicodeIndivId = unicodeIndividual.getIndivId();
					if (unicodeIndivId != 0) {
						returnBean.setUnicodeIndividualAvailabe(true);
						if (unicodeIndividual.getIndivName() != null)
							unicodeIndivName = unicodeIndividual.getIndivName();
						if (unicodeIndividual.getSalutatName() != null)
							unicodeSalutation = unicodeIndividual
									.getSalutatName();
					} else {
						returnBean.setUnicodeIndividualAvailabe(false);
					}
				} else {
					returnBean.setLatinIndividualAvailabe(false);
					returnBean.setUnicodeIndividualAvailabe(false);
				}
				// Org names
				TborOrgNamesBO orgNames = selContactPoint
						.getContactNameAddresse().getOrgNames();
				int orgNameId = 0;
				int unicodeOrgNameId = 0;
				String orgFirstLineName = "";
				String orgSecondLineName = "";
				String orgThirdLineName = "";
				String unicodeOrgFirstLineName = "";
				String unicodeOrgSecondLineName = "";
				String unicodeOrgThirdLineName = "";
				if (orgNames != null) {

					returnBean.setLatinOrgNameAvailabe(true);
					orgNameId = orgNames.getId();
					if (orgNames.getFirstLineName() != null)
						orgFirstLineName = orgNames.getFirstLineName();
					if (orgNames.getSecondLineName() != null)
						orgSecondLineName = orgNames.getSecondLineName();
					if (orgNames.getThirdLineName() != null)
						orgThirdLineName = orgNames.getThirdLineName();

					TborOrgNamesUnicodeBO unicodeOrgNames = TborOrgNamesUnicodeDAOImpl
							.getUnicodeVersion(hibernateSession, orgNameId,
									langCode);
					unicodeOrgNameId = unicodeOrgNames.getOroneId();
					if (unicodeOrgNameId != 0) {
						returnBean.setUnicodeOrgNameAvailabe(true);
						if (unicodeOrgNames.getFirstLineName() != null)
							unicodeOrgFirstLineName = unicodeOrgNames
									.getFirstLineName();
						if (unicodeOrgNames.getSecondLineName() != null)
							unicodeOrgSecondLineName = unicodeOrgNames
									.getSecondLineName();
						if (unicodeOrgNames.getThirdLineName() != null)
							unicodeOrgThirdLineName = unicodeOrgNames
									.getThirdLineName();
					} else {
						returnBean.setUnicodeOrgNameAvailabe(false);
					}
				} else {
					returnBean.setLatinOrgNameAvailabe(false);
					returnBean.setUnicodeOrgNameAvailabe(false);
				}
				// Org Adress
				TborOrgAddressesBO orgAddresses = selContactPoint
						.getContactNameAddresse().getOrgAddresses();
				int orgAddressId = orgAddresses.getId();
				String orgAddressFirstLine = "";
				String orgAddressSecondLine = "";
				String orgAddressThirdLine = "";
				String orgCityName = "";
				String orgPostCode = "";
				String orgRegion = "";
				String orgCountryName = "";

				int unicodeOrgAddressId = 0;
				String unicodeOrgAddressFirstLine = "";
				String unicodeOrgAddressSecondLine = "";
				String unicodeOrgAddressThirdLine = "";
				String unicodeOrgCityName = "";
				String unicodeOrgPostCode = "";
				String unicodeOrgRegion = "";
				String unicodeOrgCountryName = "";

				if (orgAddressId != 0) {
					returnBean.setLatinOrgAddressAvailabe(true);
					if (orgAddresses.getFirstLineStreetAddr() != null)
						orgAddressFirstLine = orgAddresses
								.getFirstLineStreetAddr();
					if (orgAddresses.getSecondLineStreetAddr() != null)
						orgAddressSecondLine = orgAddresses
								.getSecondLineStreetAddr();
					if (orgAddresses.getThirdLineStreetAddr() != null)
						orgAddressThirdLine = orgAddresses
								.getThirdLineStreetAddr();
					if (orgAddresses.getCityName() != null)
						orgCityName = orgAddresses.getCityName();
					if (orgAddresses.getPostCode() != null)
						orgPostCode = orgAddresses.getPostCode();
					if (orgAddresses.getRegionName() != null)
						orgRegion = orgAddresses.getRegionName();
					if (orgAddresses.getOrgCountry() != null
							&& orgAddresses.getOrgCountry().getMainName() != null)
						orgCountryName = orgAddresses.getOrgCountry()
								.getCode();

					TborOrgAddressesUnicodeBO unicodeOrgAddresses = TborOrgAddressesUnicodeDAOImpl
							.getUnicodeVersion(hibernateSession, orgAddressId,
									langCode);
					unicodeOrgAddressId = unicodeOrgAddresses.getOroasId();
					if (unicodeOrgAddressId != 0) {
						returnBean.setUnicodeOrgAddressAvailabe(true);
						if (unicodeOrgAddresses.getFirstLineStreetAddr() != null)
							unicodeOrgAddressFirstLine = unicodeOrgAddresses
									.getFirstLineStreetAddr();
						if (unicodeOrgAddresses.getSecondLineStreetAddr() != null)
							unicodeOrgAddressSecondLine = unicodeOrgAddresses
									.getSecondLineStreetAddr();
						if (unicodeOrgAddresses.getThirdLineStreetAddr() != null)
							unicodeOrgAddressThirdLine = unicodeOrgAddresses
									.getThirdLineStreetAddr();
						if (unicodeOrgAddresses.getCityName() != null)
							unicodeOrgCityName = unicodeOrgAddresses
									.getCityName();
						if (unicodeOrgAddresses.getPostCode() != null)
							unicodeOrgPostCode = unicodeOrgAddresses
									.getPostCode();
						if (unicodeOrgAddresses.getRegionName() != null)
							unicodeOrgRegion = unicodeOrgAddresses
									.getRegionName();
						if (unicodeOrgAddresses.getCountryName() != null)
							unicodeOrgCountryName = unicodeOrgAddresses
									.getCountryName();

					} else {
						returnBean.setUnicodeOrgAddressAvailabe(false);
					}
				} else {
					returnBean.setLatinOrgAddressAvailabe(false);
					returnBean.setUnicodeOrgAddressAvailabe(false);
				}
				// populate the individual latin / utf details

				latinContact.setIndivName(indivName);
				latinContact.setSalutation(salutation);
				latinContact.setIndivId(indivId);

				unicodeContact.setIndivName(unicodeIndivName);
				unicodeContact.setSalutation(unicodeSalutation);
				unicodeContact.setIndivId(unicodeIndivId);

				// populate the org name latin / utf details
				latinContact.setOrgNameId(orgNameId);
				latinContact.setOrgNameFirstLine(orgFirstLineName);
				latinContact.setOrgNameSecondLine(orgSecondLineName);
				latinContact.setOrgNameThirdLine(orgThirdLineName);

				unicodeContact.setOrgNameId(unicodeOrgNameId);
				unicodeContact.setOrgNameFirstLine(unicodeOrgFirstLineName);
				unicodeContact.setOrgNameSecondLine(unicodeOrgSecondLineName);
				unicodeContact.setOrgNameThirdLine(unicodeOrgThirdLineName);

				// populate the org address latin / utf details
				latinContact.setOrgAddressId(orgAddressId);
				latinContact.setOrgAddressFirstLine(orgAddressFirstLine);
				latinContact.setOrgAddressSecondLine(orgAddressSecondLine);
				latinContact.setOrgAddressThirdLine(orgAddressThirdLine);
				latinContact.setOrgCityName(orgCityName);
				latinContact.setOrgPostCode(orgPostCode);
				latinContact.setOrgRegion(orgRegion);
				latinContact.setOrgCountryName(orgCountryName);

				unicodeContact.setOrgAddressId(unicodeOrgAddressId);
				unicodeContact
						.setOrgAddressFirstLine(unicodeOrgAddressFirstLine);
				unicodeContact
						.setOrgAddressSecondLine(unicodeOrgAddressSecondLine);
				unicodeContact
						.setOrgAddressThirdLine(unicodeOrgAddressThirdLine);
				unicodeContact.setOrgCityName(unicodeOrgCityName);
				unicodeContact.setOrgPostCode(unicodeOrgPostCode);
				unicodeContact.setOrgRegion(unicodeOrgRegion);
				unicodeContact.setOrgCountryName(unicodeOrgCountryName);

				// populate the main returing bean
				returnBean.setErrorMsg(null);
				returnBean.setLatinContact(latinContact);
				returnBean.setUnicodeContact(unicodeContact);
				session.setAttribute(
						ApplicationConstants.ATTR_UNICODE_CONTACT_DETAILS,
						unicodeContact);
				session.setAttribute(
						ApplicationConstants.ATTR_LATIN_CONTACT_DETAILS,
						latinContact);

				
			}
		} catch (DataAccessException e) {
			logger.exception(e);
			returnBean.setErrorMsg(ApplicationConstants.ERROR_DATABASE);
		} catch (HibernateException e) {
			logger.exception(e);
			returnBean.setErrorMsg(ApplicationConstants.ERROR_DATABASE);
		}
		logger.exitMethod("loadContactDetails", returnBean);
		return returnBean;
	}

	private AJAXResult validateIndividual(
			TborIndividualsUnicodeBO mTborIndividualsUnicodeBO) {
		AJAXResult result = new AJAXResult();
		if (mTborIndividualsUnicodeBO.getIndivName().trim().length() > ApplicationConstants.LENGTH_CONSTANT_COMMON) {
			result.setSuccess(false);
			logger.error(ApplicationConstants.ERROR_INDIVNAME_MAX_LENGTH);
			result
					.setErrorMessage(ApplicationConstants.ERROR_INDIVNAME_MAX_LENGTH);
			return result;
		} else if (mTborIndividualsUnicodeBO.getSalutatName() != null
				&& mTborIndividualsUnicodeBO.getSalutatName().trim().length() > ApplicationConstants.LENGTH_CONSTANT_COMMON) {
			result.setSuccess(false);
			logger.error(ApplicationConstants.ERROR_SALUTATION_MAX_LENGTH);
			result
					.setErrorMessage(ApplicationConstants.ERROR_SALUTATION_MAX_LENGTH);
			return result;
		} else {
			result.setSuccess(true);
		}
		return result;
	}

	private AJAXResult validateOrgName(
			TborOrgNamesUnicodeBO mTborOrgNamesUnicodeBO) {
		AJAXResult result = new AJAXResult();
		if (mTborOrgNamesUnicodeBO.getFirstLineName().trim().length() > ApplicationConstants.LENGTH_CONSTANT_COMMON) {
			result.setSuccess(false);
			logger.error(ApplicationConstants.ERROR_ORGNAME1_MAX_LENGTH);
			result
					.setErrorMessage(ApplicationConstants.ERROR_ORGNAME1_MAX_LENGTH);
			return result;
		}
		if (mTborOrgNamesUnicodeBO.getSecondLineName() != null
				&& mTborOrgNamesUnicodeBO.getSecondLineName().trim().length() > ApplicationConstants.LENGTH_CONSTANT_COMMON) {
			result.setSuccess(false);
			logger.error(ApplicationConstants.ERROR_ORGNAME2_MAX_LENGTH);
			result
					.setErrorMessage(ApplicationConstants.ERROR_ORGNAME2_MAX_LENGTH);
			return result;
		}
		if (mTborOrgNamesUnicodeBO.getThirdLineName() != null
				&& mTborOrgNamesUnicodeBO.getThirdLineName().trim().length() > ApplicationConstants.LENGTH_CONSTANT_COMMON) {
			result.setSuccess(false);
			logger.error(ApplicationConstants.ERROR_ORGNAME3_MAX_LENGTH);
			result
					.setErrorMessage(ApplicationConstants.ERROR_ORGNAME3_MAX_LENGTH);
			return result;
		} else
			result.setSuccess(true);
		return result;
	}

	private AJAXResult validateOrgAddress(
			TborOrgAddressesUnicodeBO mTborOrgAddressesUnicodeBO) {
		AJAXResult result = new AJAXResult();
		if (mTborOrgAddressesUnicodeBO.getFirstLineStreetAddr().trim().length() > ApplicationConstants.LENGTH_CONSTANT_COMMON) {
			result.setSuccess(false);
			logger.error(ApplicationConstants.ERROR_ORGADD1_MAX_LENGTH);
			result
					.setErrorMessage(ApplicationConstants.ERROR_ORGADD1_MAX_LENGTH);
			return result;
		}
		if (mTborOrgAddressesUnicodeBO.getSecondLineStreetAddr() != null
				&& mTborOrgAddressesUnicodeBO.getSecondLineStreetAddr().trim()
						.length() > ApplicationConstants.LENGTH_CONSTANT_COMMON) {
			result.setSuccess(false);
			logger.error(ApplicationConstants.ERROR_ORGADD2_MAX_LENGTH);
			result
					.setErrorMessage(ApplicationConstants.ERROR_ORGADD2_MAX_LENGTH);
			return result;
		}
		if (mTborOrgAddressesUnicodeBO.getThirdLineStreetAddr() != null
				&& mTborOrgAddressesUnicodeBO.getThirdLineStreetAddr().trim()
						.length() > ApplicationConstants.LENGTH_CONSTANT_COMMON) {
			result.setSuccess(false);
			logger.error(ApplicationConstants.ERROR_ORGADD3_MAX_LENGTH);
			result
					.setErrorMessage(ApplicationConstants.ERROR_ORGADD3_MAX_LENGTH);
			return result;
		}
		if (mTborOrgAddressesUnicodeBO.getCityName() != null
				&& mTborOrgAddressesUnicodeBO.getCityName().trim().length() > ApplicationConstants.LENGTH_CONSTANT_COMMON) {
			result.setSuccess(false);
			logger.error(ApplicationConstants.ERROR_ORGCITY_MAX_LENGTH);
			result
					.setErrorMessage(ApplicationConstants.ERROR_ORGCITY_MAX_LENGTH);
			return result;
		}
		if (mTborOrgAddressesUnicodeBO.getPostCode() != null
				&& mTborOrgAddressesUnicodeBO.getPostCode().trim().length() > ApplicationConstants.LENGTH_CONSTANT_POSTCODE) {
			result.setSuccess(false);
			logger.error(ApplicationConstants.ERROR_ORGPOST_MAX_LENGTH);
			result
					.setErrorMessage(ApplicationConstants.ERROR_ORGPOST_MAX_LENGTH);
			return result;
		}
		if (mTborOrgAddressesUnicodeBO.getRegionName() != null
				&& mTborOrgAddressesUnicodeBO.getRegionName().trim().length() > ApplicationConstants.LENGTH_CONSTANT_REGION) {
			result.setSuccess(false);
			logger.error(ApplicationConstants.ERROR_ORGREGION_MAX_LENGTH);
			result
					.setErrorMessage(ApplicationConstants.ERROR_ORGREGION_MAX_LENGTH);
			return result;
		}
		if (mTborOrgAddressesUnicodeBO.getCountryName() != null
				&& mTborOrgAddressesUnicodeBO.getCountryName().trim().length() > ApplicationConstants.LENGTH_CONSTANT_COMMON) {
			result.setSuccess(false);
			logger.error(ApplicationConstants.ERROR_ORGCOUNTRY_MAX_LENGTH);
			result
					.setErrorMessage(ApplicationConstants.ERROR_ORGCOUNTRY_MAX_LENGTH);
			return result;
		}
		result.setSuccess(true);
		return result;
	}

	/**
	 * @param newUnicodeContact
	 * @param langCode
	 * @param session
	 * @return
	 */
	public AJAXResult maintainUnicodeContactDetails(
			ContactDetails newUnicodeContact, String langCode,
			HttpSession session) {
		logger.enterMethod("maintainUnicodeContactDetails", newUnicodeContact,
				langCode);

		AJAXResult result = new AJAXResult();

		HibernateUtil hibernateUtil = (HibernateUtil) session
				.getAttribute(ApplicationConstants.HIBERNATE_UTIL);
		Session hibernateSession = hibernateUtil.getSession();

		Transaction transaction = hibernateSession.beginTransaction();
		try {
			ContactDetails unicodeContact = (ContactDetails) session
					.getAttribute(ApplicationConstants.ATTR_UNICODE_CONTACT_DETAILS);
			ContactDetails latinContact = (ContactDetails) session
					.getAttribute(ApplicationConstants.ATTR_LATIN_CONTACT_DETAILS);

			// save only when latin contact is availabe
			if (latinContact.getIndivId() > 0) {

				TborIndividualsUnicodeBO mTborIndividualsUnicodeBO = new TborIndividualsUnicodeBO();

				mTborIndividualsUnicodeBO.setIndivName(newUnicodeContact
						.getIndivName().trim());
				mTborIndividualsUnicodeBO.setLangCode(langCode);
				mTborIndividualsUnicodeBO.setSalutatName(newUnicodeContact
						.getSalutation().trim());
				mTborIndividualsUnicodeBO.setEffectFromDate(new Date());
				mTborIndividualsUnicodeBO.setEffectToDate(CommonUtil
						.getMaxDate());

				TborIndividualsUnicodeBO existing = TborIndividualsUnicodeDAOImpl
						.getUnicodeVersion(hibernateSession, latinContact
								.getIndivId(), langCode);
				if (existing == null || existing.getIndivId() == 0) { // no
																		// record
																		// exist
					if (newUnicodeContact.getIndivName().trim().length() <= 0
							&& newUnicodeContact.getSalutation().trim()
									.length() > 0) {
						transaction.rollback();
						result.setSuccess(false);
						logger
								.error(ApplicationConstants.ERROR_BLANK_INDIV_NAME);
						result
								.setErrorMessage(ApplicationConstants.ERROR_BLANK_INDIV_NAME);
						return result;
					} else if (newUnicodeContact.getIndivName().trim().length() > 0) {
						AJAXResult tempResult = validateIndividual(mTborIndividualsUnicodeBO);
						if (tempResult.isSuccess()) {
							mTborIndividualsUnicodeBO.setIndivId(latinContact
									.getIndivId());
							TborIndividualsUnicodeDAOImpl
									.insertUnicodeVersion(hibernateSession,
											mTborIndividualsUnicodeBO);
							unicodeContact
									.setIndivId(latinContact.getIndivId());
							session
									.setAttribute(
											ApplicationConstants.ATTR_UNICODE_CONTACT_DETAILS,
											unicodeContact);
						} else {
							return tempResult;
						}
					}
				} else { // its update
					if (newUnicodeContact.getIndivName().trim().length() <= 0) {
						transaction.rollback();
						result.setSuccess(false);
						logger
								.error(ApplicationConstants.ERROR_BLANK_INDIV_NAME);
						result
								.setErrorMessage(ApplicationConstants.ERROR_BLANK_INDIV_NAME);
						return result;
					}
					AJAXResult tempResult = validateIndividual(mTborIndividualsUnicodeBO);
					if (tempResult.isSuccess()) {
						mTborIndividualsUnicodeBO.setIndivId(unicodeContact
								.getIndivId());
						TborIndividualsUnicodeDAOImpl.updateUnicodeVersion(
								hibernateSession, mTborIndividualsUnicodeBO);
					} else {
						return tempResult;
					}

				}

			}

			// save organisation names
			if (latinContact.getOrgNameId() > 0) { // only if latin version is
				// present
				TborOrgNamesUnicodeBO mTborOrgNamesUnicodeBO = new TborOrgNamesUnicodeBO();

				mTborOrgNamesUnicodeBO.setFirstLineName(newUnicodeContact
						.getOrgNameFirstLine());
				mTborOrgNamesUnicodeBO.setSecondLineName(newUnicodeContact
						.getOrgNameSecondLine());
				mTborOrgNamesUnicodeBO.setThirdLineName(newUnicodeContact
						.getOrgNameThirdLine());

				mTborOrgNamesUnicodeBO.setEffectFromDate(new Date());
				mTborOrgNamesUnicodeBO.setEffectToDate(CommonUtil.getMaxDate());
				mTborOrgNamesUnicodeBO.setLangCode(langCode);
				TborOrgNamesUnicodeBO existing = TborOrgNamesUnicodeDAOImpl
						.getUnicodeVersion(hibernateSession, latinContact
								.getOrgNameId(), langCode);
				// if record not already present (insert)
				if (existing == null || existing.getOroneId() == 0) {
					// if first line is missing
					if (newUnicodeContact.getOrgNameFirstLine().trim().length() <= 0
							&& (newUnicodeContact.getOrgNameSecondLine().trim()
									.length() > 0 || newUnicodeContact
									.getOrgNameThirdLine().trim().length() > 0)) {
						// error
						transaction.rollback();
						result.setSuccess(false);
						logger.error(ApplicationConstants.ERROR_BLANK_ORG_NAME);
						result
								.setErrorMessage(ApplicationConstants.ERROR_BLANK_ORG_NAME);
						return result;
					} else if (newUnicodeContact.getOrgNameFirstLine().trim()
							.length() > 0) {
						AJAXResult tempResult = validateOrgName(mTborOrgNamesUnicodeBO);
						if (tempResult.isSuccess()) {
							mTborOrgNamesUnicodeBO.setOroneId(latinContact
									.getOrgNameId());
							TborOrgNamesUnicodeDAOImpl.insertUnicodeVersion(
									hibernateSession, mTborOrgNamesUnicodeBO);
							unicodeContact.setOrgNameId(latinContact
									.getOrgNameId());
							session
									.setAttribute(
											ApplicationConstants.ATTR_UNICODE_CONTACT_DETAILS,
											unicodeContact);
						} else {
							return tempResult;
						}

					}
				} else { // record is already present (update org name)
					if (newUnicodeContact.getOrgNameFirstLine().trim().length() <= 0) {
						// error
						transaction.rollback();
						result.setSuccess(false);
						logger.error(ApplicationConstants.ERROR_BLANK_ORG_NAME);
						result
								.setErrorMessage(ApplicationConstants.ERROR_BLANK_ORG_NAME);
						return result;
					} else {
						AJAXResult tempResult = validateOrgName(mTborOrgNamesUnicodeBO);
						if (tempResult.isSuccess()) {
							mTborOrgNamesUnicodeBO.setOroneId(unicodeContact
									.getOrgNameId());
							TborOrgNamesUnicodeDAOImpl.updateUnicodeVersion(
									hibernateSession, mTborOrgNamesUnicodeBO);
						} else {
							return tempResult;
						}

					}
				}

			}
			// save address
			if (latinContact.getOrgAddressId() > 0) { // if lating address is
				// present

				TborOrgAddressesUnicodeBO mTborOrgAddressesUnicodeBO = new TborOrgAddressesUnicodeBO();
				mTborOrgAddressesUnicodeBO
						.setFirstLineStreetAddr(newUnicodeContact
								.getOrgAddressFirstLine());
				mTborOrgAddressesUnicodeBO
						.setSecondLineStreetAddr(newUnicodeContact
								.getOrgAddressSecondLine());
				mTborOrgAddressesUnicodeBO
						.setThirdLineStreetAddr(newUnicodeContact
								.getOrgAddressThirdLine());

				mTborOrgAddressesUnicodeBO.setLangCode(langCode);
				mTborOrgAddressesUnicodeBO.setCityName(newUnicodeContact
						.getOrgCityName());
				mTborOrgAddressesUnicodeBO.setRegionName(newUnicodeContact
						.getOrgRegion());
				mTborOrgAddressesUnicodeBO.setPostCode(newUnicodeContact
						.getOrgPostCode());
				mTborOrgAddressesUnicodeBO.setCountryName(newUnicodeContact
						.getOrgCountryName());
				mTborOrgAddressesUnicodeBO.setEffectFromDate(new Date());
				mTborOrgAddressesUnicodeBO.setEffectToDate(CommonUtil
						.getMaxDate());

				// check if record is already present or nor
				TborOrgAddressesUnicodeBO existing = TborOrgAddressesUnicodeDAOImpl
						.getUnicodeVersion(hibernateSession, latinContact
								.getOrgAddressId(), langCode);
				if (existing == null || existing.getOroasId() == 0) {
					// if name is missing andany other field value is present
					if (newUnicodeContact.getOrgAddressFirstLine().trim()
							.length() <= 0
							&& (newUnicodeContact.getOrgAddressSecondLine()
									.trim().length() > 0
									|| newUnicodeContact
											.getOrgAddressThirdLine().trim()
											.length() > 0
									|| newUnicodeContact.getOrgCityName()
											.trim().length() > 0
									|| newUnicodeContact.getOrgRegion().trim()
											.length() > 0
									|| newUnicodeContact.getOrgPostCode()
											.trim().length() > 0 )) {
						// error
						transaction.rollback();
						result.setSuccess(false);
						logger
								.error(ApplicationConstants.ERROR_BLANK_ORG_ADDRESS);
						result
								.setErrorMessage(ApplicationConstants.ERROR_BLANK_ORG_ADDRESS);
						return result;
					} else if (newUnicodeContact.getOrgAddressFirstLine()
							.trim().length() > 0) {

						if (newUnicodeContact.getOrgCityName().trim().length() <= 0) {
							transaction.rollback();
							result.setSuccess(false);
							logger
									.error(ApplicationConstants.ERROR_CITY_NAME_BLANK);
							result
									.setErrorMessage(ApplicationConstants.ERROR_CITY_NAME_BLANK);
							return result;
						} else if (newUnicodeContact.getOrgPostCode().trim()
								.length() <= 0) {
							transaction.rollback();
							result.setSuccess(false);
							logger
									.error(ApplicationConstants.ERROR_POST_CODE_BLANK);
							result
									.setErrorMessage(ApplicationConstants.ERROR_POST_CODE_BLANK);
							return result;
						} /*else if (newUnicodeContact.getOrgRegion().trim()
								.length() <= 0) {
							transaction.rollback();
							result.setSuccess(false);
							logger
									.error(ApplicationConstants.ERROR_REGION_NAME_BLANK);
							result
									.setErrorMessage(ApplicationConstants.ERROR_REGION_NAME_BLANK);
							return result;
						} else if (newUnicodeContact.getOrgCountryName().trim()
								.length() <= 0) {
							transaction.rollback();
							result.setSuccess(false);
							logger
									.error(ApplicationConstants.ERROR_COUNTRY_NAME_BLANK);
							result
									.setErrorMessage(ApplicationConstants.ERROR_COUNTRY_NAME_BLANK);
							return result;
						} */else {
							AJAXResult tempResult = validateOrgAddress(mTborOrgAddressesUnicodeBO);
							if (tempResult.isSuccess()) {
								mTborOrgAddressesUnicodeBO
										.setOroasId(latinContact
												.getOrgAddressId());
								TborOrgAddressesUnicodeDAOImpl
										.insertUnicodeVersion(hibernateSession,
												mTborOrgAddressesUnicodeBO);
								unicodeContact.setOrgAddressId(latinContact
										.getOrgAddressId());
								session
										.setAttribute(
												ApplicationConstants.ATTR_UNICODE_CONTACT_DETAILS,
												unicodeContact);
							} else {
								return tempResult;
							}
						}
					}
				} else {
					if (newUnicodeContact.getOrgAddressFirstLine().trim()
							.length() <= 0) {
						// error
						transaction.rollback();
						result.setSuccess(false);
						logger
								.error(ApplicationConstants.ERROR_BLANK_ORG_ADDRESS);
						result
								.setErrorMessage(ApplicationConstants.ERROR_BLANK_ORG_ADDRESS);
						return result;
					} else if (newUnicodeContact.getOrgCityName().trim()
							.length() <= 0) {
						transaction.rollback();
						result.setSuccess(false);
						logger
								.error(ApplicationConstants.ERROR_CITY_NAME_BLANK);
						result
								.setErrorMessage(ApplicationConstants.ERROR_CITY_NAME_BLANK);
						return result;
					} else if (newUnicodeContact.getOrgPostCode().trim()
							.length() <= 0) {
						transaction.rollback();
						result.setSuccess(false);
						logger
								.error(ApplicationConstants.ERROR_POST_CODE_BLANK);
						result
								.setErrorMessage(ApplicationConstants.ERROR_POST_CODE_BLANK);
						return result;
					} /*else if (newUnicodeContact.getOrgRegion().trim().length() <= 0) {
						transaction.rollback();
						result.setSuccess(false);
						logger
								.error(ApplicationConstants.ERROR_REGION_NAME_BLANK);
						result
								.setErrorMessage(ApplicationConstants.ERROR_REGION_NAME_BLANK);
						return result;
					} else if (newUnicodeContact.getOrgCountryName().trim()
							.length() <= 0) {
						transaction.rollback();
						result.setSuccess(false);
						logger
								.error(ApplicationConstants.ERROR_COUNTRY_NAME_BLANK);
						result
								.setErrorMessage(ApplicationConstants.ERROR_COUNTRY_NAME_BLANK);
						return result;
					}*/ else {
						AJAXResult tempResult = validateOrgAddress(mTborOrgAddressesUnicodeBO);
						if (tempResult.isSuccess()) {
							mTborOrgAddressesUnicodeBO
									.setOroasId(unicodeContact
											.getOrgAddressId());
							TborOrgAddressesUnicodeDAOImpl
									.updateUnicodeVersion(hibernateSession,
											mTborOrgAddressesUnicodeBO);
						} else {
							return tempResult;
						}
					}
				}

			}
			transaction.commit();
			result.setSuccess(true);
			result.setSuccessMessage(ApplicationConstants.INFO_SUCCESS_SAVE_C);
		} catch (DataAccessException e) {
			result.setSuccess(false);
			result.setErrorMessage(ApplicationConstants.ERROR_DATABASE);
			logger.exception(e);
			transaction.rollback();
		} catch (HibernateException e) {
			result.setSuccess(false);
			result.setErrorMessage(ApplicationConstants.ERROR_DATABASE);
			logger.exception(e);
			transaction.rollback();
		} catch (RuntimeException e) {
			result.setSuccess(false);
			result.setErrorMessage(ApplicationConstants.ERROR_DATABASE);
			logger.exception(e);
			transaction.rollback();
		} 
		logger.exitMethod("maintainUnicodeContactDetails", result);
		return result;
	}

	/**
	 * @param langCode
	 * @param session
	 * @return
	 */
	public AJAXResult deleteUnicodeContactDetails(String langCode,
			HttpSession session) {
		logger.enterMethod("deleteUnicodeContactDetails", langCode);
		AJAXResult result = new AJAXResult();

		HibernateUtil hibernateUtil = (HibernateUtil) session
				.getAttribute(ApplicationConstants.HIBERNATE_UTIL);
		Session hibernateSession = hibernateUtil.getSession();

		Transaction transaction = hibernateSession.beginTransaction();
		try {
			ContactDetails unicodeContact = (ContactDetails) session
					.getAttribute(ApplicationConstants.ATTR_UNICODE_CONTACT_DETAILS);
			boolean atLeastOneDeleted = false;
			if (unicodeContact.getIndivId() != 0) {
				atLeastOneDeleted = TborIndividualsUnicodeDAOImpl
						.deleteUnicodeVersion(hibernateSession, unicodeContact
								.getIndivId(), langCode);
				unicodeContact.setIndivId(0);
				unicodeContact.setIndivName("");
				unicodeContact.setSalutation("");
				session.setAttribute(
						ApplicationConstants.ATTR_UNICODE_CONTACT_DETAILS,
						unicodeContact);
			}
			if (unicodeContact.getOrgNameId() != 0) {
				atLeastOneDeleted = TborOrgNamesUnicodeDAOImpl
						.deleteUnicodeVersion(hibernateSession, unicodeContact
								.getOrgNameId(), langCode);
				unicodeContact.setOrgNameId(0);

				unicodeContact.setOrgNameFirstLine("");
				unicodeContact.setOrgNameSecondLine("");
				unicodeContact.setOrgNameThirdLine("");

				session.setAttribute(
						ApplicationConstants.ATTR_UNICODE_CONTACT_DETAILS,
						unicodeContact);
			}
			if (unicodeContact.getOrgAddressId() != 0) {
				atLeastOneDeleted = TborOrgAddressesUnicodeDAOImpl
						.deleteUnicodeVersion(hibernateSession, unicodeContact
								.getOrgAddressId(), langCode);
				unicodeContact.setOrgAddressId(0);
				unicodeContact.setOrgAddressFirstLine("");
				unicodeContact.setOrgAddressSecondLine("");
				unicodeContact.setOrgAddressThirdLine("");
				unicodeContact.setOrgCityName("");
				unicodeContact.setOrgPostCode("");
				unicodeContact.setOrgRegion("");
				unicodeContact.setOrgCountryName("");
				session.setAttribute(
						ApplicationConstants.ATTR_UNICODE_CONTACT_DETAILS,
						unicodeContact);
			}
			if (atLeastOneDeleted) {
				transaction.commit();
				result.setSuccess(true);
				result
						.setSuccessMessage(ApplicationConstants.INFO_SUCCESS_DEL_C);
			} else {
				transaction.rollback();
				result.setSuccess(true);
				result.setSuccessMessage(ApplicationConstants.INFO_NO_DELETE);
			}

		} catch (DataAccessException e) {
			result.setSuccess(false);
			result.setErrorMessage(ApplicationConstants.ERROR_DATABASE);
			logger.exception(e);
			transaction.rollback();
		} catch (HibernateException e) {
			result.setSuccess(false);
			result.setErrorMessage(ApplicationConstants.ERROR_DATABASE);
			logger.exception(e);
			transaction.rollback();
		} catch (RuntimeException e) {
			result.setSuccess(false);
			result.setErrorMessage(ApplicationConstants.ERROR_DATABASE);
			logger.exception(e);
			transaction.rollback();
		}
		logger.exitMethod("deleteUnicodeContactDetails", result);
		return result;
	}

}
