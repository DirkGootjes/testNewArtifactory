/*********************************************************************/
/*                           FILE HEADER                             */
/*********************************************************************/
/*                                                                   */
/*  FileName: 		TbbuTextTranslationsUniDAOImpl.java              */
/*  																 */
/*  $Author: INASHA2 $									             */
/*																	 */
/*  $Revision: 1.14 $										         */
/*  																 */
/*  $Date: 2014/03/06 13:49:52 $                                     */
/*                                                                   */
/*  Description: 	This class performs insert,update,delete for     */
/*				      unicode version                  				 */
/*********************************************************************/
/* Date        Name            Version             Comments          */
/*-------------------------------------------------------------------*/
/* 15/04/2013  INVSAR1      	1.0         Initial version created  */
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
import com.atradius.dataaccess.hibernate.bo.TbbuTextTranslationsUniBO;
import com.atradius.dataaccess.hibernate.dao.TbbuTextTranslationsUniDAO;
import com.atradius.exception.DataAccessException;
import com.atradius.util.logging.ILogger;
import com.atradius.util.logging.LoggerFactory;

public class TbbuTextTranslationsUniDAOImpl implements
		TbbuTextTranslationsUniDAO {
	private static ILogger logger = LoggerFactory
			.getLogger(TbbuTextTranslationsUniDAOImpl.class);

	public TbbuTextTranslationsUniBO insertTextTranslations(
			HibernateUtil hibernateUtil, int textId, String languageCode,
			String translation) throws DataAccessException {
		logger.enterMethod("insertTextTranslations");
		TbbuTextTranslationsUniBO tbbuTextTranslationsUniBO = new TbbuTextTranslationsUniBO(
				textId, translation, languageCode);
		// set dates as null as these will be handled by triggers
		tbbuTextTranslationsUniBO.setEffectToDate(null);
		tbbuTextTranslationsUniBO.setEffectFromDate(null);
		Transaction tx = null;
		Session session = null;
		// int textId;
		try {
			session = hibernateUtil.getSession();
			tx = session.beginTransaction();
			session.save(tbbuTextTranslationsUniBO);
			tx.commit();

		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			// e.printStackTrace();
			logger.exception(e);
			throw new DataAccessException("DATABASE_QUERRY_FAILED", e);
		} 
		logger.exitMethod("insertTextTranslations");
		return tbbuTextTranslationsUniBO;
	}

	public List<TbbuTextTranslationsUniBO> getTextTranslations(
			HibernateUtil hibernateUtil, int textId) throws DataAccessException {
		List<TbbuTextTranslationsUniBO> tbbuTextTranslationsUniBOList = new ArrayList<TbbuTextTranslationsUniBO>();
		logger.enterMethod("getTextTranslations");
		Session session = null;
		try {
			session = hibernateUtil.getSession();
			Criteria contactPointCrit = session
					.createCriteria(TbbuTextTranslationsUniBO.class);
			contactPointCrit.add(Restrictions.eq("id", textId));
			contactPointCrit.add(Restrictions.gt("effectToDate",
					new java.util.Date()));
			tbbuTextTranslationsUniBOList = (List<TbbuTextTranslationsUniBO>) contactPointCrit
					.list();

		} catch (HibernateException e) {

			// e.printStackTrace();
			logger.exception(e);
			throw new DataAccessException("DATABASE_QUERRY_FAILED", e);
		} 
		logger.exitMethod("getTextTranslations");
		return tbbuTextTranslationsUniBOList;
	}

	public TbbuTextTranslationsUniBO updateTextTranslations(
			HibernateUtil hibernateUtil, int textId, String languageCode,
			String translation) throws DataAccessException {
		logger.enterMethod("updateTextTranslations");
		Session session = null;
		Transaction tx = null;
		Date date = new Date();
		TbbuTextTranslationsUniBO newtbbuTextTranslationsUniBO = new TbbuTextTranslationsUniBO(
				textId, translation, languageCode);
		try {
			session = hibernateUtil.getSession();
			tx = session.beginTransaction();
			// find the existing record
			TbbuTextTranslationsUniBO oldtbbuTextTranslationsUniBO = getUnicodeVersion(
					session, textId, languageCode);
			if (checkIfUpdateRequired(oldtbbuTextTranslationsUniBO,
					newtbbuTextTranslationsUniBO)) {
				session.evict(oldtbbuTextTranslationsUniBO);
				insertTextTranslations(hibernateUtil, textId, languageCode, translation);
				session.flush();
				tx.commit();
			}
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			// e.printStackTrace();
			logger.exception(e);
		}
		logger.exitMethod("updateTextTranslations");
		return newtbbuTextTranslationsUniBO;
	}

	// DELETE
	public boolean deleteTextTranslations(HibernateUtil hibernateUtil,
			int textId, String languageCode) throws DataAccessException {
		logger.enterMethod("deleteTextTranslations");
		Session session = null;
		Transaction tx = null;
		Date date = new Date();
		boolean isDeleted = false;
		
		try {
			session = hibernateUtil.getSession();
			tx = session.beginTransaction();
			// find the existing record
			TbbuTextTranslationsUniBO oldtbbuTextTranslationsUniBO = getUnicodeVersion(
					session, textId, languageCode);
			oldtbbuTextTranslationsUniBO.setEffectToDate(date);
			// save it back to data base
			session.save(oldtbbuTextTranslationsUniBO); // historise old entity
			session.flush();
			session.evict(oldtbbuTextTranslationsUniBO);
			session.flush();
			tx.commit();
			isDeleted = true;
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			isDeleted = false;
			// e.printStackTrace();
			logger.exception(e);
		} 
		logger.exitMethod("deleteTextTranslations");
		return isDeleted;
	}

	private static boolean checkIfUpdateRequired(
			TbbuTextTranslationsUniBO oldObject,
			TbbuTextTranslationsUniBO newObject) {
		logger.enterMethod("checkIfUpdateRequired");
		if (oldObject != null
				&& newObject != null
				&& oldObject.getLanguageCode() != null
				&& newObject.getLanguageCode() != null
				&& oldObject.getLanguageCode().equals(
						newObject.getLanguageCode())) {
			String old_text = oldObject.getDescription();
			String new_text = newObject.getDescription();
			if (old_text == null)
				old_text = "";
			// Richa validate that new text is blank
			if (new_text == null)
				new_text = "";
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

	public static TbbuTextTranslationsUniBO getUnicodeVersion(Session session,
			int textId, String langCode) {
		logger.enterMethod("getUnicodeVersion", textId, langCode);
		TbbuTextTranslationsUniBO unicode = null;
		try {
			Criteria contactPointCrit = session
					.createCriteria(TbbuTextTranslationsUniBO.class);
			contactPointCrit.add(Restrictions.eq("id", textId));
			contactPointCrit.add(Restrictions.gt("effectToDate",
					new java.util.Date()));
			contactPointCrit.add(Restrictions.eq("languageCode", langCode));
			unicode = (TbbuTextTranslationsUniBO) contactPointCrit
					.uniqueResult();
		} catch (HibernateException e) {
			logger.exception(e);
			// e.printStackTrace();
			logger.exception(e);
			throw e;
		}
		if (unicode == null)
			unicode = new TbbuTextTranslationsUniBO();
		logger.exitMethod("getUnicodeVersion");
		return unicode;
	}

}
