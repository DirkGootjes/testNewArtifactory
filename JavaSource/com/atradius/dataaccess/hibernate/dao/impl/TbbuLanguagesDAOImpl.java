/*********************************************************************/
/*                           FILE HEADER                             */
/*********************************************************************/
/*                                                                   */
/*  FileName: 		TbbuLanguagesDAOImpl.java             	  	     */
/*  																 */
/*  $Author: INASHA2 $									             */
/*																	 */
/*  $Revision: 1.10 $										         */
/*  																 */
/*  $Date: 2014/03/06 13:49:52 $                                     */
/*                                                                   */
/*  Description: 	This class performs database operation           */
/*				                       				                 */
/*********************************************************************/
/* Date        Name            Version             Comments          */
/*-------------------------------------------------------------------*/
/* 15/04/2013  INVSAR1      	1.0         Initial version created  */
/*********************************************************************/
package com.atradius.dataaccess.hibernate.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.atradius.dataaccess.hibernate.HibernateUtil;
import com.atradius.dataaccess.hibernate.bo.TbbuLanguagesBO;
import com.atradius.dataaccess.hibernate.bo.TbbuTextTranslationsBO;
import com.atradius.exception.DataAccessException;
import com.atradius.util.logging.ILogger;
import com.atradius.util.logging.LoggerFactory;

public class TbbuLanguagesDAOImpl {
	private static ILogger logger = LoggerFactory
			.getLogger(TbbuLanguagesDAOImpl.class);

	/**
	 * @param hibernateUtil
	 * @return
	 * @throws DataAccessException
	 */
	public List<TbbuLanguagesBO> getUnicodeLanguage(HibernateUtil hibernateUtil)
			throws DataAccessException {

		List<TbbuLanguagesBO> TbbuLanguagesBOList = new ArrayList<TbbuLanguagesBO>();
		logger.enterMethod("getUnicodeLanguage");
		// Transaction tx = null;
		// int textId;
		try {
			Session session = hibernateUtil.getSession();
			// TbbuLanguagesBO = new TbbuLanguagesBO( name, languageCode,
			// thousandInd, decimalInd) ;
			// Criteria criteria = session.createCriteria("")
			// Query query = session.createQuery("FROM TbbuLanguagesBO");
			Criteria contactPointCrit = session
					.createCriteria(TbbuLanguagesBO.class);
			// .createCriteria(TbbuLanguagesBO.class);
			// contactPointCrit.add(Restrictions.eq("languageCode", "EN"));

			List langList = contactPointCrit.list();
			// Query query = session.createQuery("FROM
			// com.atradius.dataaccess.hibernate.bo.TbbuLanguagesBO");
			// List langList =query.list();
			// Iterator iterator = langList.iterator();
			for (int i = 0; i < langList.size(); i++) {
				TbbuLanguagesBO TbbuLanguagesBO = (TbbuLanguagesBO) langList
						.get(i);
				TbbuLanguagesBOList.add(TbbuLanguagesBO);
			}
			
		} catch (HibernateException e) {
			/*
			 * if (tx != null) { tx.rollback(); }
			 */
			logger.exception(e);
			throw new DataAccessException("DATABASE_QUERRY_FAILED", e);
		} catch (Exception e) {
			// e.printStackTrace();
			logger.exception(e);
		}
		logger.exitMethod("getUnicodeLanguage");
		return TbbuLanguagesBOList;
	}

	/**
	 * @param hibernateUtil
	 * @param langCode
	 * @return
	 * @throws DataAccessException
	 */
	public TbbuLanguagesBO getLanguageName(HibernateUtil hibernateUtil,
			String langCode) throws DataAccessException {
		logger.enterMethod("getLanguageName");
		TbbuLanguagesBO TbbuLanguagesBO = new TbbuLanguagesBO();
		try {
			Session session = hibernateUtil.getSession();
			Criteria contactPointCrit = session
					.createCriteria(TbbuLanguagesBO.class);
			contactPointCrit.add(Restrictions.eq("langCode", langCode));
			TbbuLanguagesBO = (TbbuLanguagesBO) contactPointCrit.uniqueResult();
			
		} catch (HibernateException e) {
			logger.exception(e);
			throw new DataAccessException("DATABASE_QUERRY_FAILED", e);
		} catch (Exception e) {
			logger.exception(e);
		}
		logger.exitMethod("getLanguageName");
		return TbbuLanguagesBO;
	}

	public List<TbbuLanguagesBO> getFilteredUnicodeLanguage(
			HibernateUtil hibernateUtil,
			List<TbbuLanguagesBO> tbbuLanguagesUniBOList, int textId)
			throws DataAccessException {

		logger.enterMethod("getFilteredUnicodeLanguage");
		List<TbbuLanguagesBO> filtLang = new ArrayList<TbbuLanguagesBO>();
		// Transaction tx = null;
		// int textId;
		try {
			Session session = hibernateUtil.getSession();
			List<String> latingLanguages = new ArrayList<String>();

			Criteria contactPointCrit = session
					.createCriteria(TbbuTextTranslationsBO.class);
			contactPointCrit.add(Restrictions.eq("butteId", textId));

			List TbbuTextTranslationsBOList = contactPointCrit.list();

			for (int i = 0; i < TbbuTextTranslationsBOList.size(); i++) {
				TbbuTextTranslationsBO tbbuTextTranslationsBO = (TbbuTextTranslationsBO) TbbuTextTranslationsBOList
						.get(i);
				latingLanguages.add(tbbuTextTranslationsBO.getLanguageCode());
			}

			for (int i = 0; i < tbbuLanguagesUniBOList.size(); i++) {
				TbbuLanguagesBO tempTbbuLanguagesBO = tbbuLanguagesUniBOList
						.get(i);
				if (!latingLanguages
						.contains(tempTbbuLanguagesBO.getLangCode())) {
					filtLang.add(tempTbbuLanguagesBO);
				}
			}

			
		} catch (HibernateException e) {

			logger.exception(e);
			throw new DataAccessException("DATABASE_QUERRY_FAILED", e);
		} catch (Exception e) {
			logger.exception(e);
		}
		logger.exitMethod("getUnicodeLanguage");
		return filtLang;
	}
	
	//get Text translation
	public TbbuTextTranslationsBO getTextTranslationsSEQ(
			HibernateUtil hibernateUtil,
			 int textId)
			throws DataAccessException {

		logger.enterMethod("getTextTranslationsSEQ");
		TbbuTextTranslationsBO textTranslation = new TbbuTextTranslationsBO();
		// Transaction tx = null;
		// int textId;
		try {
			Session session = hibernateUtil.getSession();

			Criteria contactPointCrit = session
					.createCriteria(TbbuTextTranslationsBO.class);
			contactPointCrit.add(Restrictions.eq("butteId", textId));

			List TbbuTextTranslationsBOList = contactPointCrit.list();

			for (int i = 0; i < TbbuTextTranslationsBOList.size(); i++) {
				textTranslation = (TbbuTextTranslationsBO) TbbuTextTranslationsBOList
						.get(i);
				break;
			}

			
		} catch (HibernateException e) {

			logger.exception(e);
			throw new DataAccessException("DATABASE_QUERRY_FAILED", e);
		} catch (Exception e) {
			logger.exception(e);
		}
		logger.exitMethod("getTextTranslationsSEQ");
		return textTranslation;
	}
	public List<TbbuLanguagesBO> getNonLatinLanguage(HibernateUtil hibernateUtil)
	throws DataAccessException {

//List<TbbuLanguagesBO> TbbuLanguagesBOList = new ArrayList<TbbuLanguagesBO>();
logger.enterMethod("getNonLatinLanguage");
List<TbbuLanguagesBO> TbbuLanguagesBOlist = new ArrayList<TbbuLanguagesBO>();
try {
	Session session = hibernateUtil.getSession();
	Criteria contactPointCrit = session
			.createCriteria(TbbuLanguagesBO.class);
	contactPointCrit.add(Restrictions.eq("nonLatinFlag", "Y"));
	TbbuLanguagesBOlist =  (List<TbbuLanguagesBO>)contactPointCrit.list();
	/*for (int i = 0; i < TbbuLanguagesBOlist.size(); i++) {
		TbbuLanguagesBO TbbuLanguagesBO = (TbbuLanguagesBO) TbbuLanguagesBOlist
				.get(i);
		TbbuLanguagesBOList.add(TbbuLanguagesBO);
	}*/
	
} catch (HibernateException e) {
	/*
	 * if (tx != null) { tx.rollback(); }
	 */
	 //e.printStackTrace();
	logger.exception(e);
	throw new DataAccessException("DATABASE_QUERRY_FAILED", e);
} catch (Exception e) {
	 //e.printStackTrace();
	logger.exception(e);
}
logger.exitMethod("getUnicodeLanguage");
return TbbuLanguagesBOlist;
}
}
