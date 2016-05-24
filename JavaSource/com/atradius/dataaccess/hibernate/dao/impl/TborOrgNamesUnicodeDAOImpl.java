/*********************************************************************/
/*                           FILE HEADER                             */
/*********************************************************************/
/*                                                                   */
/*  FileName: 		TborOrgNamesUnicodeDAOImpl.java       	  	     */
/*  																 */
/*  $Author: INRSHR1 $									             */
/*																	 */
/*  $Revision: 1.12 $										         */
/*  																 */
/*  $Date: 2013/09/05 05:54:44 $                                     */
/*                                                                   */
/*  Description: 	This class performs database operation for       */
/*				     Unicode OrgNames details.                       */
/*********************************************************************/
/* Date        Name            Version             Comments          */
/*-------------------------------------------------------------------*/
/* 25/04/2013  INVSAR1      	1.0         Initial version created  */
/* 09/02/2013  INRSHR1          1.1         Changes done for defect  */
/*                                           4598                    */
/*********************************************************************/
package com.atradius.dataaccess.hibernate.dao.impl;

import java.util.Date;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.atradius.dataaccess.hibernate.bo.TborOrgNamesUnicodeBO;
import com.atradius.exception.DataAccessException;
import com.atradius.util.logging.ILogger;
import com.atradius.util.logging.LoggerFactory;

public class TborOrgNamesUnicodeDAOImpl {
	private static ILogger logger = LoggerFactory
			.getLogger(TborOrgNamesUnicodeDAOImpl.class);

	/**
	 * @param session
	 * @param oroneId
	 * @param langCode
	 * @return
	 */
	public static TborOrgNamesUnicodeBO getUnicodeVersion(Session session,
			int oroneId, String langCode) throws DataAccessException {
		logger.enterMethod("getUnicodeVersion", oroneId, langCode);
		TborOrgNamesUnicodeBO unicode = null;

		try {
			Criteria contactPointCrit = session
					.createCriteria(TborOrgNamesUnicodeBO.class);
			contactPointCrit.add(Restrictions.eq("oroneId", oroneId));
			contactPointCrit.add(Restrictions.gt("effectToDate",
					new java.util.Date()));
			contactPointCrit.add(Restrictions.eq("langCode", langCode));
			unicode = (TborOrgNamesUnicodeBO) contactPointCrit.uniqueResult();
		} catch (HibernateException e) {
			logger.exception(e);
			throw new DataAccessException("DATABASE_QUERRY_FAILED", e);
		}
		if (unicode == null)
			unicode = new TborOrgNamesUnicodeBO();

		logger.exitMethod("getUnicodeVersion");
		return unicode;
	}

	/**
	 * @param session
	 * @param pTborOrgNamesUnicodeBO
	 */
	public static void insertUnicodeVersion(Session session,
			TborOrgNamesUnicodeBO pTborOrgNamesUnicodeBO)
			throws DataAccessException {
		logger.enterMethod("insertUnicodeVersion", pTborOrgNamesUnicodeBO);
		try {
			pTborOrgNamesUnicodeBO.setFirstLineName(pTborOrgNamesUnicodeBO.getFirstLineName().toUpperCase()); //changes done for defect 4598 by INRSHR1
			pTborOrgNamesUnicodeBO.setSecondLineName(pTborOrgNamesUnicodeBO.getSecondLineName().toUpperCase());//changes done for defect 4598
			pTborOrgNamesUnicodeBO.setThirdLineName(pTborOrgNamesUnicodeBO.getThirdLineName().toUpperCase());//changes done for defect 4598
			session.save(pTborOrgNamesUnicodeBO);
			session.flush();
		} catch (HibernateException e) {
			logger.exception(e);
			throw new DataAccessException("DATABASE_QUERRY_FAILED", e);
		}
		logger.exitMethod("insertUnicodeVersion");
	}

	/**
	 * @param session
	 * @param pTborOrgNamesUnicodeBO
	 */
	public static void updateUnicodeVersion(Session session,
			TborOrgNamesUnicodeBO pTborOrgNamesUnicodeBO)
			throws DataAccessException {
		logger.enterMethod("updateUnicodeVersion", pTborOrgNamesUnicodeBO);

		Date date = new Date();
		try {

			TborOrgNamesUnicodeBO oldTborOrgNamesUnicodeBO = getUnicodeVersion(
					session, pTborOrgNamesUnicodeBO.getOroneId(),
					pTborOrgNamesUnicodeBO.getLangCode());

			if (checkIfUpdateRequired(oldTborOrgNamesUnicodeBO,
					pTborOrgNamesUnicodeBO)) {
				// evict the old entity from session
				session.evict(oldTborOrgNamesUnicodeBO);
				// make effect from date and to date as null
				// as these values will be handled by triggers
				pTborOrgNamesUnicodeBO.setEffectToDate(null);
				pTborOrgNamesUnicodeBO.setEffectFromDate(null);
				// save it to DataBase
				insertUnicodeVersion(session, pTborOrgNamesUnicodeBO);
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
	 * @param oroneId
	 * @param langCode
	 * @return
	 */
	public static boolean deleteUnicodeVersion(Session session, int oroneId,
			String langCode) throws DataAccessException {
		logger.enterMethod("deleteUnicodeVersion", oroneId, langCode);
		boolean isDeleted = false;
		Date date = new Date();
		try {
			TborOrgNamesUnicodeBO oldTborOrgNamesUnicodeBO = getUnicodeVersion(
					session, oroneId, langCode);
			oldTborOrgNamesUnicodeBO.setEffectToDate(date);
			// save it back to data base
			// historise old entity
			session.save(oldTborOrgNamesUnicodeBO);
			session.flush();
			// evict the entity as it is of no use after delete
			session.evict(oldTborOrgNamesUnicodeBO);
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
	private static boolean checkIfUpdateRequired(TborOrgNamesUnicodeBO oldObj,
			TborOrgNamesUnicodeBO newObj) {
		if (oldObj != null && newObj != null) {
			String old_firstname = "";
			String old_secondname = "";
			String old_thirdname = "";
			String new_firstname = "";
			String new_secondname = "";
			String new_thirdname = "";
			if (oldObj.getFirstLineName() != null)
				old_firstname = oldObj.getFirstLineName();
			if (oldObj.getSecondLineName() != null)
				old_secondname = oldObj.getSecondLineName();
			if (oldObj.getThirdLineName() != null)
				old_thirdname = oldObj.getThirdLineName();
			if (newObj.getFirstLineName() != null)
				new_firstname = newObj.getFirstLineName();
			if (newObj.getSecondLineName() != null)
				new_secondname = newObj.getSecondLineName();
			if (newObj.getThirdLineName() != null)
				new_thirdname = newObj.getThirdLineName();
			if (old_firstname.equals(new_firstname)
					&& old_secondname.equals(new_secondname)
					&& old_thirdname.equals(new_thirdname))
				return false;
			else
				return true;
		} else
			return true;
	}
}
