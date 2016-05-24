/*********************************************************************/
/*                           FILE HEADER                             */
/*********************************************************************/
/*                                                                   */
/*  FileName: 		TborOrgAddressesUnicodeDAOImpl.java             	  	     */
/*  																 */
/*  $Author: INASHA2 $									             */
/*																	 */
/*  $Revision: 1.12 $										         */
/*  																 */
/*  $Date: 2014/03/06 13:49:52 $                                     */
/*                                                                   */
/*  Description: 	This class performs database operation for       */
/*				     Unicode OrgAddresses details.                   				                 */
/*********************************************************************/
/* Date        Name            Version             Comments          */
/*-------------------------------------------------------------------*/
/* 25/04/2013  INVSAR1      	1.0         Initial version created  */
/*********************************************************************/
package com.atradius.dataaccess.hibernate.dao.impl;

import java.util.Date;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.atradius.dataaccess.hibernate.bo.TborOrgAddressesUnicodeBO;
import com.atradius.exception.DataAccessException;
import com.atradius.util.logging.ILogger;
import com.atradius.util.logging.LoggerFactory;

public class TborOrgAddressesUnicodeDAOImpl {
	private static ILogger logger = LoggerFactory
			.getLogger(TborOrgAddressesUnicodeDAOImpl.class);

	/**
	 * @param session
	 * @param oroasId
	 * @param langCode
	 * @return
	 */
	public static TborOrgAddressesUnicodeBO getUnicodeVersion(Session session,
			int oroasId, String langCode) throws DataAccessException {
		logger.enterMethod("getUnicodeVersion", oroasId, langCode);
		TborOrgAddressesUnicodeBO unicode = null;
		try {
			Criteria contactPointCrit = session
					.createCriteria(TborOrgAddressesUnicodeBO.class);
			contactPointCrit.add(Restrictions.eq("oroasId", oroasId));
			contactPointCrit.add(Restrictions.gt("effectToDate",
					new java.util.Date()));
			contactPointCrit.add(Restrictions.eq("langCode", langCode));
			unicode = (TborOrgAddressesUnicodeBO) contactPointCrit
					.uniqueResult();
		} catch (HibernateException e) {
			logger.exception(e);
			throw new DataAccessException("DATABASE_QUERRY_FAILED", e);
		}
		if (unicode == null)
			unicode = new TborOrgAddressesUnicodeBO();
		logger.exitMethod("getUnicodeVersion");
		return unicode;
	}

	/**
	 * @param session
	 * @param pTborOrgAddressesUnicodeBO
	 */
	public static void insertUnicodeVersion(Session session,
			TborOrgAddressesUnicodeBO pTborOrgAddressesUnicodeBO)
			throws DataAccessException {
		logger.enterMethod("insertUnicodeVersion", pTborOrgAddressesUnicodeBO);

		try {
			session.save(pTborOrgAddressesUnicodeBO);
			session.flush();
		} catch (HibernateException e) {
			logger.exception(e);
			throw new DataAccessException("DATABASE_QUERRY_FAILED", e);
		}
		logger.exitMethod("insertUnicodeVersion");
	}

	/**
	 * @param session
	 * @param pTborOrgAddressesUnicodeBO
	 */
	public static void updateUnicodeVersion(Session session,
			TborOrgAddressesUnicodeBO pTborOrgAddressesUnicodeBO)
			throws DataAccessException {
		logger.enterMethod("updateUnicodeVersion", pTborOrgAddressesUnicodeBO);

		Date date = new Date();
		try {
			// historise old
			TborOrgAddressesUnicodeBO oldTborOrgAddressesUnicodeBO = getUnicodeVersion(
					session, pTborOrgAddressesUnicodeBO.getOroasId(),
					pTborOrgAddressesUnicodeBO.getLangCode());
			if (checkIfUpdateRequired(oldTborOrgAddressesUnicodeBO,
					pTborOrgAddressesUnicodeBO)) {
				session.evict(oldTborOrgAddressesUnicodeBO);
				pTborOrgAddressesUnicodeBO.setEffectFromDate(null);
				pTborOrgAddressesUnicodeBO.setEffectToDate(null);
				insertUnicodeVersion(session, pTborOrgAddressesUnicodeBO);
				session.flush();
			}
		} catch (HibernateException e) {
			logger.exception(e);
			throw new DataAccessException("DATABASE_QUERRY_FAILED", e);
		}
		logger.exitMethod("updateUnicodeVersion");
	}

	/**
	 * @param session
	 * @param oroasId
	 * @param langCode
	 * @return
	 */
	public static boolean deleteUnicodeVersion(Session session, int oroasId,
			String langCode) throws DataAccessException {
		logger.enterMethod("deleteUnicodeVersion", oroasId, langCode);
		boolean isDeleted = false;
		Date date = new Date();
		try {
			
			TborOrgAddressesUnicodeBO oldTborOrgAddressesUnicodeBO = getUnicodeVersion(
					session, oroasId, langCode);
			//	historise old
			oldTborOrgAddressesUnicodeBO.setEffectToDate(date);
			session.save(oldTborOrgAddressesUnicodeBO);
			session.flush();
			// evict as it is of no use now
			session.evict(oldTborOrgAddressesUnicodeBO);
			session.flush();
			isDeleted = true;
		} catch (HibernateException e) {
			isDeleted = false;
			logger.exception(e);
			throw new DataAccessException("DATABASE_QUERRY_FAILED", e);
		}
		logger.exitMethod("deleteUnicodeVersion");
		return isDeleted;
	}

	/**
	 * @param oldObj
	 * @param newObj
	 * @return
	 */
	private static boolean checkIfUpdateRequired(
			TborOrgAddressesUnicodeBO oldObj, TborOrgAddressesUnicodeBO newObj) {
		if (oldObj != null && newObj != null) {
			String old_firstline = "", new_firstline = "";
			String old_secondline = "", new_secondline = "";
			String old_thirdline = "", new_thirdline = "";
			String old_city = "", new_city = "";
			String old_region = "", new_region = "";
			String old_post = "", new_post = "";
			String old_country = "", new_country = "";
			if (oldObj.getFirstLineStreetAddr() != null)
				old_firstline = oldObj.getFirstLineStreetAddr();
			if (oldObj.getSecondLineStreetAddr() != null)
				old_secondline = oldObj.getSecondLineStreetAddr();
			if (oldObj.getThirdLineStreetAddr() != null)
				old_thirdline = oldObj.getThirdLineStreetAddr();
			if (oldObj.getCityName() != null)
				old_city = oldObj.getCityName();
			if (oldObj.getRegionName() != null)
				old_region = oldObj.getRegionName();
			if (oldObj.getPostCode() != null)
				old_post = oldObj.getPostCode();
			if (oldObj.getCountryName() != null)
				old_country = oldObj.getCountryName();

			if (newObj.getFirstLineStreetAddr() != null)
				new_firstline = newObj.getFirstLineStreetAddr();
			if (newObj.getSecondLineStreetAddr() != null)
				new_secondline = newObj.getSecondLineStreetAddr();
			if (newObj.getThirdLineStreetAddr() != null)
				new_thirdline = newObj.getThirdLineStreetAddr();
			if (newObj.getCityName() != null)
				new_city = newObj.getCityName();
			if (newObj.getRegionName() != null)
				new_region = newObj.getRegionName();
			if (newObj.getPostCode() != null)
				new_post = newObj.getPostCode();
			if (newObj.getCountryName() != null)
				new_country = newObj.getCountryName();

			if (old_firstline.equals(new_firstline)
					&& old_secondline.equals(new_secondline)
					&& old_thirdline.equals(new_thirdline)
					&& old_city.equals(new_city)
					&& old_region.equals(new_region)
					&& old_post.equals(new_post)
					&& old_country.equals(new_country))
				return false;
			else
				return true;
		} else
			return true;
	}
}
