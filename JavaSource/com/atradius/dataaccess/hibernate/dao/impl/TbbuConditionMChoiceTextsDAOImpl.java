/*********************************************************************/
/*                           FILE HEADER                             */
/*********************************************************************/
/*                                                                   */
/*  FileName: 		TbbuConditionMChoiceTextsDAOImpl.java             	  	 */
/*  																 */
/*  $Author: INASHA2 $									     */
/*																	 */
/*  $Revision: 1.2 $										     */
/*  																 */
/*  $Date: 2014/03/06 13:49:52 $                            */
/*                                                                   */
/*  Description: 	This action class performs the database function of*/
/*				  	multi choice details					 */
/*********************************************************************/
/* Date        Name            Version             Comments          */
/*-------------------------------------------------------------------*/
/* 17/12/2013  INRSHR1      	1.0         Initial version created  */
/*********************************************************************/
package com.atradius.dataaccess.hibernate.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.atradius.dataaccess.hibernate.HibernateUtil;
import com.atradius.dataaccess.hibernate.bo.TbbuCondMChoiceTextUniBO;
import com.atradius.dataaccess.hibernate.bo.TbbuConditionMChoiceTextsBO;
import com.atradius.dataaccess.hibernate.bo.TbbuLanguagesBO;
import com.atradius.exception.DataAccessException;
import com.atradius.util.logging.ILogger;
import com.atradius.util.logging.LoggerFactory;

public class TbbuConditionMChoiceTextsDAOImpl {
	
	private static ILogger logger = LoggerFactory
	.getLogger(TbbuConditionMChoiceTextsDAOImpl.class);	
	
	public List<TbbuConditionMChoiceTextsBO> getAllTranslation(
			HibernateUtil hibernateUtil, String bucdeCode, int choice) throws DataAccessException {
		List<TbbuConditionMChoiceTextsBO> tbbuCondMCTextsList = new ArrayList<TbbuConditionMChoiceTextsBO>();
		logger.enterMethod("getAllTranslation");
		//org.hibernate.classic.Session session = null;
		Session session = null;
		try 
		{
			if(session == null || session.isOpen()==false)
			{
				session = hibernateUtil.getSessionFactory().openSession();
			}
			Criteria contactPointCrit = session
					.createCriteria(TbbuConditionMChoiceTextsBO.class);
			contactPointCrit.add(Restrictions.eq("bucdeCode", bucdeCode));
			contactPointCrit.add(Restrictions.eq("bumctSequence", choice));			
			tbbuCondMCTextsList = (List<TbbuConditionMChoiceTextsBO>) contactPointCrit.list();
			
			if(session != null)
				if(!session.isOpen()==false)
			{
				session.flush();
				session.close();
			}
			
		} catch (HibernateException e) {
			logger.exception(e);
			throw new DataAccessException("DATABASE_QUERRY_FAILED", e);
		} 
		logger.exitMethod("getAllTranslation");
		return tbbuCondMCTextsList;
	}

	public List<TbbuConditionMChoiceTextsBO> getAllNonLatinTranslationList(
			HibernateUtil hibernateUtil, String bucdeCode,int choice) throws DataAccessException {
		List<TbbuConditionMChoiceTextsBO> getAllTranslationlist = new ArrayList<TbbuConditionMChoiceTextsBO>();
		List<TbbuConditionMChoiceTextsBO> getAllNonLatinTranslist = new ArrayList<TbbuConditionMChoiceTextsBO>();		
		
		logger.enterMethod("getAllNonLatinTranslationList");
		try {			
			getAllTranslationlist = getAllTranslation(hibernateUtil,bucdeCode,choice);
			
			List<TbbuLanguagesBO> allNonLatinLanglist=new ArrayList<TbbuLanguagesBO>();
			TbbuLanguagesDAOImpl tbbuLangDAOImpl= new TbbuLanguagesDAOImpl();
			allNonLatinLanglist=tbbuLangDAOImpl.getNonLatinLanguage( hibernateUtil);
			
			for (int i=0;i< getAllTranslationlist.size();i++){
			    for (int j=0;j< allNonLatinLanglist.size();j++){
				     String langCode1=allNonLatinLanglist.get(j).getLangCode();
				     
				     String langCode2=getAllTranslationlist.get(i).getLangCode();
				     
				            if(langCode1.equals(langCode2)){
					         getAllNonLatinTranslist.add(getAllTranslationlist.get(i));
				           }
			     }
			}
			List<TbbuCondMChoiceTextUniBO> tbbuCondMChoiceTextsUniBOList = new ArrayList<TbbuCondMChoiceTextUniBO>();
			TbbuCondMChoiceTextUniDAOImpl daoImplObject= new TbbuCondMChoiceTextUniDAOImpl();
			tbbuCondMChoiceTextsUniBOList = daoImplObject.getAllUnicodeTranslation(hibernateUtil,bucdeCode,choice);
			//Check if traslation is present in non latin then replace it from latin translation
			for(int i=0;i< tbbuCondMChoiceTextsUniBOList.size();i++){
				String nonLatinLang1=tbbuCondMChoiceTextsUniBOList.get(i).getLangCode();
				
				for(int j=0;j<getAllNonLatinTranslist.size();j++){
					String nonLatinLang2=getAllNonLatinTranslist.get(j).getLangCode();
					
					if (nonLatinLang1.equals(nonLatinLang2)){
						getAllNonLatinTranslist.get(j).setText(tbbuCondMChoiceTextsUniBOList.get(i).getText());
						
					}
				}
			}
		}catch (HibernateException e) {			 
			logger.exception(e);
			throw new DataAccessException("DATABASE_QUERRY_FAILED", e);
		}
		logger.exitMethod("getAllNonLatinTranslationList");
		return getAllNonLatinTranslist;
	}
	public static TbbuConditionMChoiceTextsBO getLatinVersion(HibernateUtil hibernateUtil,
			String bucdeCode, String langCode ,Integer bumctSequence) {
		logger.enterMethod("getLatinVersion", bucdeCode, langCode,bumctSequence);
		TbbuConditionMChoiceTextsBO latinVersion = new TbbuConditionMChoiceTextsBO();

		Session session = null;
		
		try {
			session = hibernateUtil.getSession();
			Criteria contactPointCrit = session
					.createCriteria(TbbuConditionMChoiceTextsBO.class);
			contactPointCrit.add(Restrictions.eq("bucdeCode", bucdeCode));
			contactPointCrit.add(Restrictions.eq("langCode", langCode));
			contactPointCrit.add(Restrictions.eq("bumctSequence", bumctSequence));
			latinVersion = (TbbuConditionMChoiceTextsBO) contactPointCrit.uniqueResult();
			
		} catch (HibernateException e) {
			logger.exception(e);			
			throw e;
		}
		
		if (latinVersion == null){
			latinVersion = new TbbuConditionMChoiceTextsBO();
		}
			
		logger.exitMethod("getUnicodeVersion");
		return latinVersion;
	}
}
