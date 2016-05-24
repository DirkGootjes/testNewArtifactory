/*********************************************************************/
/*                           FILE HEADER                             */
/*********************************************************************/
/*                                                                   */
/*  FileName: 		ConditionCodeDAOImpl.java             	  	 */
/*  																 */
/*  $Author: INPDEO1 $									     */
/*																	 */
/*  $Revision: 1.3 $										     */
/*  																 */
/*  $Date: 2014/09/16 09:18:01 $                            */
/*                                                                   */
/*  Description: 	This action class performs the database function of*/
/*				  	single choice details					 */
/*********************************************************************/
/* Date        Name            Version             Comments          */
/*-------------------------------------------------------------------*/
/* 17/12/2013  INRSHR1      	1.0         Initial version created  */
/*********************************************************************/
package com.atradius.dataaccess.hibernate.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.atradius.dataaccess.hibernate.HibernateUtil;
import com.atradius.dataaccess.hibernate.bo.TbbuLanguagesBO;
import com.atradius.dataaccess.hibernate.bo.TbbuTextTranslationsBO;
import com.atradius.dataaccess.hibernate.bo.TbbuTextTranslationsUniBO;
import com.atradius.exception.DataAccessException;
import com.atradius.util.logging.ILogger;
import com.atradius.util.logging.LoggerFactory;

public class ConditionCodeDAOImpl {

	private static ILogger logger = LoggerFactory
			.getLogger(ConditionCodeDAOImpl.class);

	public List<TbbuTextTranslationsUniBO> getAllUnicodeTranslation(
			HibernateUtil hibernateUtil, String bucdeCode)
			throws DataAccessException {
		List<TbbuTextTranslationsUniBO> tbbuTextTransUniList = new ArrayList<TbbuTextTranslationsUniBO>();
		logger.enterMethod("getAllUnicodeTranslation");
		Session session = null;
		try {
			session = hibernateUtil.getSession();
			Criteria contactPointCrit = session
					.createCriteria(TbbuTextTranslationsUniBO.class);
			contactPointCrit.add(Restrictions.eq("bucdeCode", bucdeCode));
			contactPointCrit.add(Restrictions.gt("effectToDate",
					new java.util.Date()));
			contactPointCrit.addOrder(Order.asc("seq"));
			tbbuTextTransUniList = (List<TbbuTextTranslationsUniBO>) contactPointCrit
					.list();
			for (int i = 0; i < tbbuTextTransUniList.size(); i++) {
				for (int j = i + 1; j < tbbuTextTransUniList.size(); j++) {
					if (tbbuTextTransUniList.get(i).getLanguageCode().equals(
							tbbuTextTransUniList.get(j).getLanguageCode())) {
						String text1 = tbbuTextTransUniList.get(i)
								.getDescription();
						String text2 = tbbuTextTransUniList.get(j)
								.getDescription();
						tbbuTextTransUniList.get(i).setDescription(
								text1 + text2);
						tbbuTextTransUniList.remove(j);
						j=j-1;
					}
				}

			}

		} catch (HibernateException e) {
			logger.exception(e);
			throw new DataAccessException("DATABASE_QUERRY_FAILED", e);
		}
		logger.exitMethod("getAllUnicodeTranslation");
		return tbbuTextTransUniList;
	}

	public List<TbbuTextTranslationsBO> getAllTranslation(
			HibernateUtil hibernateUtil, String bucdeCode)
			throws DataAccessException {
		List<TbbuTextTranslationsBO> tbbuTextTranslist = new ArrayList<TbbuTextTranslationsBO>();
		logger.enterMethod("getAllTranslation");
		Session session = null;
		try {
			session = hibernateUtil.getSession();
			Criteria contactPointCrit = session
					.createCriteria(TbbuTextTranslationsBO.class);
			contactPointCrit.add(Restrictions.eq("bucdeCode", bucdeCode));
			tbbuTextTranslist = (List<TbbuTextTranslationsBO>) contactPointCrit
					.list();
		} catch (HibernateException e) {
			logger.exception(e);
			throw new DataAccessException("DATABASE_QUERRY_FAILED", e);
		}
		logger.exitMethod("getAllTranslation");
		return tbbuTextTranslist;
	}

	public List<TbbuTextTranslationsBO> getAllNonLatinTranslationList(
			HibernateUtil hibernateUtil, String bucdeCode)
			throws DataAccessException {
		List<TbbuTextTranslationsBO> getAllTranslist = new ArrayList<TbbuTextTranslationsBO>();
		List<TbbuTextTranslationsBO> getAllNonLatinTranslist = new ArrayList<TbbuTextTranslationsBO>();

		logger.enterMethod("getAllNonLatinTranslationList");
		try {
			getAllTranslist = getAllTranslation(hibernateUtil, bucdeCode);

			List<TbbuLanguagesBO> allNonLatinLanglist = new ArrayList<TbbuLanguagesBO>();
			TbbuLanguagesDAOImpl tbbuLangDAOImpl = new TbbuLanguagesDAOImpl();
			allNonLatinLanglist = tbbuLangDAOImpl
					.getNonLatinLanguage(hibernateUtil);

			for (int i = 0; i < getAllTranslist.size(); i++) {
				for (int j = 0; j < allNonLatinLanglist.size(); j++) {
					String langCode1 = allNonLatinLanglist.get(j).getLangCode();
					String langCode2 = getAllTranslist.get(i).getLanguageCode();
					if (langCode1.equals(langCode2)) {
						getAllNonLatinTranslist.add(getAllTranslist.get(i));
					}
				}
			}
			List<TbbuTextTranslationsUniBO> tbbuTextTransUniList = new ArrayList<TbbuTextTranslationsUniBO>();

			tbbuTextTransUniList = getAllUnicodeTranslation(hibernateUtil,
					bucdeCode);
			//Check if traslation is present in non latin then replace it from latin translation
			for (int i = 0; i < tbbuTextTransUniList.size(); i++) {
				String nonLatinLang1 = tbbuTextTransUniList.get(i)
						.getLanguageCode();
				for (int j = 0; j < getAllNonLatinTranslist.size(); j++) {
					String nonLatinLang2 = getAllNonLatinTranslist.get(j)
							.getLanguageCode();
					if (nonLatinLang1.equals(nonLatinLang2)) {
						getAllNonLatinTranslist.get(j).setDescription(
								tbbuTextTransUniList.get(i).getDescription());
					}
				}
			}
		} catch (HibernateException e) {
			logger.exception(e);
			throw new DataAccessException("DATABASE_QUERRY_FAILED", e);
		}
		logger.exitMethod("getAllNonLatinTranslationList");
		return getAllNonLatinTranslist;
	}

	///
	public TbbuTextTranslationsUniBO insertTextTranslations(
			HibernateUtil hibernateUtil, String bucdeCode, String text,
			String langCode) throws DataAccessException {
		logger.enterMethod("insertTextTranslations");
		TbbuTextTranslationsUniBO tbbuTextTransUniBO1 = new TbbuTextTranslationsUniBO();
		String pText = text;
		int pSeq = 1;
		while (pText.length() > 2000) {
			TbbuTextTranslationsUniBO tbbuTextTransUniBO = new TbbuTextTranslationsUniBO();
			tbbuTextTransUniBO.setBucdeCode(bucdeCode);
			tbbuTextTransUniBO.setLanguageCode(langCode);

			// set dates as null as these will be handled by triggers
			tbbuTextTransUniBO.setEffectToDate(null);
			tbbuTextTransUniBO.setEffectFromDate(null);
			Transaction transaction = null;
			Session session = null;
			// int textId;
			try {
				session = hibernateUtil.getSession();
				transaction = session.beginTransaction();
				String lText = null;
				lText = pText.substring(0, 2000);
				pText = pText.substring(2000, pText.length());
				tbbuTextTransUniBO.setSeq(pSeq);
				String newString=lText;
				String newString1;
				String finalText="";
				//If user adding choices then it should be added in next line.
				while(newString.contains("@M")){
					int index=newString.indexOf("@M");					
					newString1=newString.substring(0,index+3);									
					newString1= newString1.substring(0, index)+'\n'+newString.substring(index, index+3);															
					newString= newString.substring(index+3);					
					finalText=finalText+newString1;
					
				}
				//added !finalText.equals("") for defect 6417 
				if(finalText != null && !finalText.equals("")){
					lText=finalText;
				}
				tbbuTextTransUniBO.setDescription(lText);
				session.save(tbbuTextTransUniBO);
				pSeq = pSeq + 1;
				transaction.commit();

			} catch (HibernateException e) {
				if (transaction != null) {
					transaction.rollback();
				}
				logger.exception(e);
				throw new DataAccessException("DATABASE_QUERRY_FAILED", e);
			}
			//return tbbuTextTransUniBO;
		}
		if (pText.length() < 2000) {
			TbbuTextTranslationsUniBO tbbuTextTransUniBO = new TbbuTextTranslationsUniBO();
			tbbuTextTransUniBO.setBucdeCode(bucdeCode);
			tbbuTextTransUniBO.setLanguageCode(langCode);

			// set dates as null as these will be handled by triggers
			tbbuTextTransUniBO.setEffectToDate(null);
			tbbuTextTransUniBO.setEffectFromDate(null);
			Transaction transaction = null;
			Session session = null;
			String newString=pText;
			String newString1;
			String finalText="";
//			If user adding choices then it should be added in next line.
				while(newString.contains("@M")){
					int index=newString.indexOf("@M");					
					//newString1=pText.substring(0,index+3);
					newString1=newString.substring(0,index+3);
					newString1= newString1.substring(0, index)+'\n'+newString.substring(index, index+3);															
					newString= newString.substring(index+3);					
					finalText=finalText+newString1;
					
				}
		
			// int textId;
			try {
				session = hibernateUtil.getSession();
				transaction = session.beginTransaction();
				if(finalText.trim().length() != 0){
					pText=finalText;
				}
				tbbuTextTransUniBO.setDescription(pText);
				
				tbbuTextTransUniBO.setSeq(pSeq);
				session.save(tbbuTextTransUniBO);
				transaction.commit();
			} catch (HibernateException e) {
				if (transaction != null) {
					transaction.rollback();
				}
				logger.exception(e);
				throw new DataAccessException("DATABASE_QUERRY_FAILED", e);
			}
		}
		logger.exitMethod("insertTextTranslations");

		return tbbuTextTransUniBO1;
	}

	public TbbuTextTranslationsUniBO updateTextTranslations(
			HibernateUtil hibernateUtil, String bucdeCode, String text,
			String langCode) throws DataAccessException {
		logger.enterMethod("updateTextTranslations");
		Session session = null;
		Transaction transaction = null;
		TbbuTextTranslationsUniBO newTbbuTextTranslationsUniBO = new TbbuTextTranslationsUniBO();
		newTbbuTextTranslationsUniBO.setBucdeCode(bucdeCode);
		newTbbuTextTranslationsUniBO.setLanguageCode(langCode);
		newTbbuTextTranslationsUniBO.setDescription(text);
		try {
			session = hibernateUtil.getSession();
			transaction = session.beginTransaction();
			// find the existing record
			List<TbbuTextTranslationsUniBO> unicodeList = new ArrayList<TbbuTextTranslationsUniBO>();
			unicodeList = getUnicodeVersionForDelete(session, bucdeCode,
					langCode);
			TbbuTextTranslationsUniBO oldTbbuTextTranslationsUniBO = new TbbuTextTranslationsUniBO();
			String oldText = null;
			for (int i = 0; i < unicodeList.size(); i++) {
				oldText = oldText + unicodeList.get(i).getDescription();

			}
			oldTbbuTextTranslationsUniBO.setBucdeCode(bucdeCode);
			oldTbbuTextTranslationsUniBO.setLanguageCode(langCode);
			oldTbbuTextTranslationsUniBO.setDescription(oldText);

			if (checkIfUpdateRequired(oldTbbuTextTranslationsUniBO,
					newTbbuTextTranslationsUniBO)) {
				session.evict(oldTbbuTextTranslationsUniBO);

				transaction.commit();
				for (int i = 0; i < unicodeList.size(); i++) {

					TbbuTextTranslationsUniBO oldBO = unicodeList.get(i);
					try {

						transaction = session.beginTransaction();
						// find the existing record

						oldBO.setEffectToDate(new Date());
						// save it back to data base
						session.save(oldBO); // historise old entity
						session.flush();
						session.evict(oldBO);
						session.flush();
						transaction.commit();

					} catch (HibernateException e) {
						/*if (transaction != null)
						 transaction.rollback();*/

						logger.exception(e);
					}
				}
				newTbbuTextTranslationsUniBO = insertTextTranslations(
						hibernateUtil, bucdeCode, text, langCode);

				session.flush();
			}
		} catch (HibernateException e) {
			if (transaction != null) {
				transaction.rollback();
			}

			logger.exception(e);
		}
		logger.exitMethod("updateTextTranslations");
		return newTbbuTextTranslationsUniBO;
	}

	public boolean deleteTextTranslations(HibernateUtil hibernateUtil,
			String bucdeCd, String langCode) throws DataAccessException {
		logger.enterMethod("deleteTextTranslations");

		Session session = null;
		session = hibernateUtil.getSession();
		Transaction transaction = null;
		Date date = new Date();
		boolean isDeleted = false;
		List<TbbuTextTranslationsUniBO> oldTbbuTextTranslationsUniBO = getUnicodeVersionForDelete(
				session, bucdeCd, langCode);
		for (int i = 0; i < oldTbbuTextTranslationsUniBO.size(); i++) {

			TbbuTextTranslationsUniBO singleBO = new TbbuTextTranslationsUniBO();
			singleBO = oldTbbuTextTranslationsUniBO.get(i);
			try {
				transaction = session.beginTransaction();
				// find the existing record
				singleBO.setEffectToDate(date);
				// save it back to data base
				session.save(singleBO); // historise old entity
				session.flush();
				session.evict(singleBO);
				session.flush();
				transaction.commit();
				isDeleted = true;
			} catch (HibernateException e) {
				if (transaction != null)
					transaction.rollback();
				isDeleted = false;
				logger.exception(e);
			}
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
			String bucdeCode, String langCode) {
		logger.enterMethod("getUnicodeVersion", bucdeCode, langCode);
		TbbuTextTranslationsUniBO unicode = new TbbuTextTranslationsUniBO();

		try {
			Criteria contactPointCrit = session
					.createCriteria(TbbuTextTranslationsUniBO.class);
			contactPointCrit.add(Restrictions.eq("bucdeCode", bucdeCode));
			contactPointCrit.add(Restrictions.gt("effectToDate",
					new java.util.Date()));
			contactPointCrit.add(Restrictions.eq("languageCode", langCode));

			unicode = (TbbuTextTranslationsUniBO) contactPointCrit
					.uniqueResult();

		} catch (HibernateException e) {
			logger.exception(e);
			throw e;
		}
		if (unicode == null) {
			unicode = new TbbuTextTranslationsUniBO();
		}

		logger.exitMethod("getUnicodeVersion");
		return unicode;
	}

	public static List<TbbuTextTranslationsUniBO> getUnicodeVersionForDelete(
			Session session, String bucdeCode, String langCode) {
		logger.enterMethod("getUnicodeVersionForDelete", bucdeCode, langCode);
		List<TbbuTextTranslationsUniBO> unicodeList = new ArrayList<TbbuTextTranslationsUniBO>();

		try {
			Criteria contactPointCrit = session
					.createCriteria(TbbuTextTranslationsUniBO.class);
			contactPointCrit.add(Restrictions.eq("bucdeCode", bucdeCode));
			contactPointCrit.add(Restrictions.gt("effectToDate",
					new java.util.Date()));
			contactPointCrit.add(Restrictions.eq("languageCode", langCode));

			unicodeList = contactPointCrit.list();

		} catch (HibernateException e) {
			logger.exception(e);
			throw e;
		}
		/*if (unicodeList == null){
		 unicodeList = new TbbuTextTranslationsUniBO();
		 }
		 */
		logger.exitMethod("getUnicodeVersionForDelete");
		return unicodeList;
	}

	public static boolean getUnicodeVersionForSave(HibernateUtil hibernateUtil,
			String bucdeCode, String langCode) {
		logger.enterMethod("getUnicodeVersionForSave", bucdeCode, langCode);
		TbbuTextTranslationsUniBO unicode = null;
		Session session = null;
		Transaction transaction = null;
		try {
			session = hibernateUtil.getSession();
			transaction = session.beginTransaction();
			Criteria contactPointCrit = session
					.createCriteria(TbbuTextTranslationsUniBO.class);
			contactPointCrit.add(Restrictions.eq("bucdeCode", bucdeCode));
			contactPointCrit.add(Restrictions.gt("effectToDate",
					new java.util.Date()));
			contactPointCrit.add(Restrictions.eq("languageCode", langCode));
			contactPointCrit.add(Restrictions.eq("seq", 1));

			unicode = (TbbuTextTranslationsUniBO) contactPointCrit
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

	public static TbbuTextTranslationsBO getLatinVersion(
			HibernateUtil hibernateUtil, String bucdeCode, String langCode) {
		logger.enterMethod("getLatinVersion", bucdeCode, langCode);
		TbbuTextTranslationsBO latinVersion = null;
		Session session = null;

		try {
			session = hibernateUtil.getSession();
			Criteria contactPointCrit = session
					.createCriteria(TbbuTextTranslationsBO.class);
			contactPointCrit.add(Restrictions.eq("bucdeCode", bucdeCode));
			contactPointCrit.add(Restrictions.eq("languageCode", langCode));

			latinVersion = (TbbuTextTranslationsBO) contactPointCrit
					.uniqueResult();

		} catch (HibernateException e) {
			logger.exception(e);
			throw e;
		} catch (Exception e1) {
			logger.exception(e1);
		}
		if (latinVersion == null)
			latinVersion = new TbbuTextTranslationsBO();
		logger.exitMethod("getLatinVersion");
		return latinVersion;
	}
}
