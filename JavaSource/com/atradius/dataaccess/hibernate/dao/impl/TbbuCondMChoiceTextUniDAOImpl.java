/*********************************************************************/
/*                           FILE HEADER                             */
/*********************************************************************/
/*                                                                   */
/*  FileName: 		TbbuCondMChoiceTextUniDAOImpl.java         		     */
/*  																 */
/*  $Author: INASHA2 $									             */
/*																	 */
/*  $Revision: 1.2 $										         */
/*  																 */
/*  $Date: 2014/03/06 13:49:52 $                                     */
/*                                                                   */
/*  Description: 	This class performs database operation for       */
/*				      condition codes.            			         */
/*********************************************************************/
/* Date        Name            Version             Comments          */
/*-------------------------------------------------------------------*/
/* 15/10/2013  INATIW1      	1.0         Initial version created  */
/*********************************************************************/
package com.atradius.dataaccess.hibernate.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.atradius.dataaccess.hibernate.HibernateUtil;
import com.atradius.dataaccess.hibernate.bo.TbbuCondMChoiceTextUniBO;
import com.atradius.exception.DataAccessException;
import com.atradius.util.logging.ILogger;
import com.atradius.util.logging.LoggerFactory;

public class TbbuCondMChoiceTextUniDAOImpl {

	private static ILogger logger = LoggerFactory
			.getLogger(TbbuCondMChoiceTextUniDAOImpl.class);

	public List<TbbuCondMChoiceTextUniBO> getAllUnicodeTranslation(
			HibernateUtil hibernateUtil, String bucdeCode, int choice)
			throws DataAccessException {
		List<TbbuCondMChoiceTextUniBO> tbbuCondMChTextsUniList = new ArrayList<TbbuCondMChoiceTextUniBO>();
		logger.enterMethod("getAllTranslation");
		Session session = null;
		try {
			session = hibernateUtil.getSession();
			Criteria contactPointCrit = session
					.createCriteria(TbbuCondMChoiceTextUniBO.class);
			contactPointCrit.add(Restrictions.eq("bucdeCode", bucdeCode));
			contactPointCrit.add(Restrictions.eq("bumctSequence", choice));
			contactPointCrit.add(Restrictions.gt("effectToDate",
					new java.util.Date()));
			tbbuCondMChTextsUniList = (List<TbbuCondMChoiceTextUniBO>) contactPointCrit
					.list();

		} catch (HibernateException e) {
			logger.exception(e);
			throw new DataAccessException("DATABASE_QUERRY_FAILED", e);
		}
		logger.exitMethod("getAllTranslation");
		return tbbuCondMChTextsUniList;
	}

	public TbbuCondMChoiceTextUniBO insertTextTranslations(
			HibernateUtil hibernateUtil, String bucdeCode, String text,
			String langCode, Integer bumctSequence) throws DataAccessException {
		logger.enterMethod("insertTextTranslations");
		TbbuCondMChoiceTextUniBO tbbuCondMCsBO = new TbbuCondMChoiceTextUniBO(
				bucdeCode, text, langCode, bumctSequence);
		// set dates as null as these will be handled by triggers
		tbbuCondMCsBO.setEffectToDate(null);
		tbbuCondMCsBO.setEffectFromDate(null);
		Transaction transaction = null;
		Session session = null;
		// int textId;
		try {
			session = hibernateUtil.getSession();
			transaction = session.beginTransaction();
			session.save(tbbuCondMCsBO);
			transaction.commit();

		} catch (HibernateException e) {
			if (transaction != null) {
				transaction.rollback();
			}

			logger.exception(e);
			throw new DataAccessException("DATABASE_QUERRY_FAILED", e);
		}
		logger.exitMethod("insertTextTranslations");
		return tbbuCondMCsBO;
	}

	public TbbuCondMChoiceTextUniBO updateTextTranslations(
			HibernateUtil hibernateUtil, String bucdeCode, String text,
			String langCode, Integer bumctSequence) throws DataAccessException {
		logger.enterMethod("updateTextTranslations");
		Session session = null;
		Transaction transaction = null;
		TbbuCondMChoiceTextUniBO newTbbuCondMCTextUniBO = new TbbuCondMChoiceTextUniBO(
				bucdeCode, text, langCode, bumctSequence);
		try {
			session = hibernateUtil.getSession();
			transaction = session.beginTransaction();
			// find the existing record
			TbbuCondMChoiceTextUniBO oldUniBO = getUnicodeVersion(
					session, bucdeCode, langCode, bumctSequence);

			if (checkIfUpdateRequired(oldUniBO,
					newTbbuCondMCTextUniBO)) {

				session.evict(oldUniBO);

				transaction.commit();
				newTbbuCondMCTextUniBO = insertTextTranslations(hibernateUtil,
						bucdeCode, text, langCode, bumctSequence);

				session.flush();
			}
		} catch (HibernateException e) {
			if (transaction != null) {
				transaction.rollback();
			}
			logger.exception(e);
		}
		logger.exitMethod("updateTextTranslations");
		return newTbbuCondMCTextUniBO;
	}

	public boolean deleteTextTranslations(HibernateUtil hibernateUtil,
			String bucdeCd, String langCode, Integer bumctSequence)
			throws DataAccessException {
		logger.enterMethod("deleteTextTranslations");
		Session session = null;
		Transaction transaction = null;
		Date date = new Date();
		boolean isDeleted = false;

		try {
			session = hibernateUtil.getSession();
			transaction = session.beginTransaction();
			// find the existing record
			TbbuCondMChoiceTextUniBO oldUniBO = getUnicodeVersion(
					session, bucdeCd, langCode, bumctSequence);
			oldUniBO.setEffectToDate(date);
			// save it back to data base

			session.save(oldUniBO); // historise old entity

			session.flush();
			session.evict(oldUniBO);

			session.flush();
			transaction.commit();
			isDeleted = true;
		} catch (HibernateException e) {
			if (transaction != null) {
				transaction.rollback();
			}

			isDeleted = false;

			logger.exception(e);
		}
		logger.exitMethod("deleteTextTranslations");
		return isDeleted;
	}

	private static boolean checkIfUpdateRequired(
			TbbuCondMChoiceTextUniBO oldObject,
			TbbuCondMChoiceTextUniBO newObject) {
		logger.enterMethod("checkIfUpdateRequired");

		if (oldObject != null && newObject != null
				&& oldObject.getLangCode() != null
				&& newObject.getLangCode() != null
				&& oldObject.getLangCode().equals(newObject.getLangCode())) {

			String old_text = oldObject.getText();
			String new_text = newObject.getText();
			if (old_text == null){
				old_text = "";
			}				
			if (new_text == null){
				new_text = "";
			}
				
			if (old_text.equals(new_text)) {
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

	public static TbbuCondMChoiceTextUniBO getUnicodeVersion(Session session,
			String bucdeCode, String langCode, Integer bumctSequence) {
		logger.enterMethod("getUnicodeVersion", bucdeCode, langCode,
				bumctSequence);
		TbbuCondMChoiceTextUniBO unicode = null;
		try {
			Criteria contactPointCrit = session
					.createCriteria(TbbuCondMChoiceTextUniBO.class);
			contactPointCrit.add(Restrictions.eq("bucdeCode", bucdeCode));
			contactPointCrit.add(Restrictions.gt("effectToDate",
					new java.util.Date()));
			contactPointCrit.add(Restrictions.eq("langCode", langCode));
			contactPointCrit.add(Restrictions
					.eq("bumctSequence", bumctSequence));
			unicode = (TbbuCondMChoiceTextUniBO) contactPointCrit
					.uniqueResult();
			//System.out.println("unicode bucde code="+unicode.getBucdeCode());
		} catch (HibernateException e) {
			logger.exception(e);
			// e.printStackTrace();
			logger.exception(e);
			throw e;
		}
		if (unicode == null) {
			unicode = new TbbuCondMChoiceTextUniBO();
		}

		logger.exitMethod("getUnicodeVersion");
		return unicode;
	}

	public static boolean getUnicodeVersionForSave(HibernateUtil hibernateUtil,
			String bucdeCode, String langCode, Integer bumctSequence) {
		logger.enterMethod("getUnicodeVersionForSave", bucdeCode, langCode,
				bumctSequence);

		TbbuCondMChoiceTextUniBO unicode = null;
		Session session = null;
		Transaction transaction = null;
		try {
			session = hibernateUtil.getSession();
			transaction = session.beginTransaction();
			Criteria contactPointCrit = session
					.createCriteria(TbbuCondMChoiceTextUniBO.class);
			contactPointCrit.add(Restrictions.eq("bucdeCode", bucdeCode));
			contactPointCrit.add(Restrictions.gt("effectToDate",
					new java.util.Date()));
			contactPointCrit.add(Restrictions.eq("langCode", langCode));
			contactPointCrit.add(Restrictions
					.eq("bumctSequence", bumctSequence));
			unicode = (TbbuCondMChoiceTextUniBO) contactPointCrit
					.uniqueResult();
		} catch (HibernateException e) {
			logger.exception(e);
			throw e;
		}
		if (unicode == null) {
			return true;

		}

		logger.exitMethod("getUnicodeVersionForSave");
		return false;
	}
}
