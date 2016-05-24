/*********************************************************************/
/*                           FILE HEADER                             */
/*********************************************************************/
/*                                                                   */
/*  FileName: 		TborIndividualsUnicodeDAOImpl.java     	  	     */
/*  																 */
/*  $Author: INASHA2 $									             */
/*																	 */
/*  $Revision: 1.15 $										         */
/*  																 */
/*  $Date: 2014/03/06 13:49:52 $                                     */
/*                                                                   */
/*  Description: 	This class performs database operation for       */
/*				    Unicode individual details.          		     */
/*********************************************************************/
/* Date        Name            Version             Comments          */
/*-------------------------------------------------------------------*/
/* 21/05/2013  INVSAR1      	1.0         Initial version created  */
/*********************************************************************/
package com.atradius.dataaccess.hibernate.dao.impl;

import java.util.Date;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.atradius.dataaccess.hibernate.HibernateUtil;
import com.atradius.dataaccess.hibernate.bo.TborIndividualsUnicodeBO;
import com.atradius.exception.DataAccessException;
import com.atradius.util.logging.ILogger;
import com.atradius.util.logging.LoggerFactory;

public class TborIndividualsUnicodeDAOImpl {
	private static ILogger logger = LoggerFactory
			.getLogger(TborIndividualsUnicodeDAOImpl.class);

	/**
	 * @param session
	 * @param indivId
	 * @param langCode
	 * @return
	 */
	public static TborIndividualsUnicodeBO getUnicodeVersion(Session session,
			int indivId, String langCode) throws DataAccessException {
		logger.enterMethod("getUnicodeVersion", indivId, langCode);
		TborIndividualsUnicodeBO unicode = null;
		try {
			Criteria contactPointCrit = session
					.createCriteria(TborIndividualsUnicodeBO.class);
			contactPointCrit.add(Restrictions.eq("indivId", indivId));
			contactPointCrit.add(Restrictions.gt("effectToDate",
					new java.util.Date()));
			contactPointCrit.add(Restrictions.eq("langCode", langCode));
			unicode = (TborIndividualsUnicodeBO) contactPointCrit
					.uniqueResult();
		} catch (HibernateException e) {
			logger.exception(e);
			throw new DataAccessException("DATABASE_QUERRY_FAILED", e);
		}
		if (unicode == null)
			unicode = new TborIndividualsUnicodeBO();
		logger.exitMethod("getUnicodeVersion");
		return unicode;
	}

	/**
	 * @param session
	 * @param pTborIndividualsUnicodeBO
	 */
	public static void insertUnicodeVersion(Session session,
			TborIndividualsUnicodeBO pTborIndividualsUnicodeBO)
			throws DataAccessException {
		logger.enterMethod("insertUnicodeVersion", pTborIndividualsUnicodeBO);

		try {
			session.save(pTborIndividualsUnicodeBO);
			session.flush();
		} catch (HibernateException e) {
			logger.exception(e);
			throw new DataAccessException("DATABASE_QUERRY_FAILED", e);
		}
		logger.exitMethod("insertUnicodeVersion");
	}

	/**
	 * @param session
	 * @param pTborIndividualsUnicodeBO
	 */
	public static void updateUnicodeVersion(Session session,
			TborIndividualsUnicodeBO pTborIndividualsUnicodeBO)
			throws DataAccessException {
		logger.enterMethod("updateUnicodeVersion");
		Date date = new Date();

		try {
			// find the existing record
			TborIndividualsUnicodeBO oldTborIndividualsUnicodeBO = getUnicodeVersion(
					session, pTborIndividualsUnicodeBO.getIndivId(),
					pTborIndividualsUnicodeBO.getLangCode());

			if (checkIfUpdateRequired(oldTborIndividualsUnicodeBO,
					pTborIndividualsUnicodeBO)) {
				// evict the old entity from session
				session.evict(oldTborIndividualsUnicodeBO);
				// make effect from date and to date as null
				// as these values will be handled by triggers
				pTborIndividualsUnicodeBO.setEffectFromDate(null);
				pTborIndividualsUnicodeBO.setEffectToDate(null);
				insertUnicodeVersion(session,pTborIndividualsUnicodeBO);
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
	 * @param indivId
	 * @param langCode
	 * @return
	 */
	public static boolean deleteUnicodeVersion(Session session, int indivId,
			String langCode) throws DataAccessException {
		logger.enterMethod("deleteUnicodeVersion");
		Date date = new Date();
		boolean isDeleted = false;

		try {

			// find the existing record
			TborIndividualsUnicodeBO oldTborIndividualsUnicodeBO = getUnicodeVersion(
					session, indivId, langCode);
			
			oldTborIndividualsUnicodeBO.setEffectToDate(date);
			// save it back to data base
			// historise old entity
			session.save(oldTborIndividualsUnicodeBO); 
			session.flush();
			// evict the entity as it is of no use after delete
			session.evict(oldTborIndividualsUnicodeBO);
			session.flush();
			
			isDeleted = true;
		} catch (HibernateException e) {
			logger.exception(e);
			// e.printStackTrace();
			isDeleted = false;
			throw new DataAccessException("DATABASE_QUERRY_FAILED", e);
		}
		logger.exitMethod("updateUnicodeVersion");
		return isDeleted;
	}

	/**
	 * @param hibernateUtil
	 * @param indvId
	 * @param languageCode
	 * @param translation
	 * @return
	 * @throws DataAccessException
	 */
	public TborIndividualsUnicodeBO insertIndividualTranslations(
			HibernateUtil hibernateUtil, int indvId, String languageCode,
			String translation) throws DataAccessException {
		logger.enterMethod("insertIndividualTranslations");
		TborIndividualsUnicodeBO tborIndividualsUnicodeBO = new TborIndividualsUnicodeBO(
				indvId, languageCode, translation);
		Transaction tx = null;
		try {
			Session session = hibernateUtil.getSession();
			tx = session.beginTransaction();
			session.save(tborIndividualsUnicodeBO);
			tx.commit();
			
		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			logger.exception(e);
			throw new DataAccessException("DATABASE_QUERRY_FAILED", e);
		}
		logger.exitMethod("insertIndividualTranslations");
		return tborIndividualsUnicodeBO;
	}

	/**
	 * @param oldObject
	 * @param newObject
	 * @return
	 */
	private static boolean checkIfUpdateRequired(
			TborIndividualsUnicodeBO oldObject,
			TborIndividualsUnicodeBO newObject) {
		logger.enterMethod("checkIfUpdateRequired");
		if (oldObject != null && newObject != null
				&& oldObject.getIndivName() != null
				&& newObject.getIndivName() != null
				&& oldObject.getIndivName().equals(newObject.getIndivName())) {
			String old_salutat = oldObject.getSalutatName();
			String new_salutat = newObject.getSalutatName();
			if (old_salutat == null)
				old_salutat = "";
			if (new_salutat == null)
				new_salutat = "";
			if (old_salutat.equals(new_salutat)) {
				logger.exitMethod("checkIfUpdateRequired");
				return false;
			} else {
				logger.exitMethod("checkIfUpdateRequired");
				return true;
			}
		} else {
			logger.exitMethod("checkIfUpdateRequired");
			return true;
		}
	}

}
