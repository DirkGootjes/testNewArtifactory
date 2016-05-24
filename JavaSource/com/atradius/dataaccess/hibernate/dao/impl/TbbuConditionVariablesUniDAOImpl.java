/*********************************************************************/
/*                           FILE HEADER                             */
/*********************************************************************/
/*                                                                   */
/*  FileName: 		TbbuConditionVariablesUniDAOImpl.java            */
/*  																 */
/*  $Author: INASHA2 $									             */
/*																	 */
/*  $Revision: 1.2 $										         */
/*  																 */
/*  $Date: 2014/03/06 13:49:52 $                                     */
/*                                                                   */
/*  Description: 	This class performs insert,update,delete for     */
/*				      unicode version                  				 */
/*********************************************************************/
/* Date        Name            Version             Comments          */
/*-------------------------------------------------------------------*/
/* 16/10/2013  INATIW1      	1.0         Initial version created  */
/*********************************************************************/

package com.atradius.dataaccess.hibernate.dao.impl;

import java.util.Date;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.atradius.dataaccess.hibernate.HibernateUtil;
import com.atradius.dataaccess.hibernate.bo.TbbuConditionTypesBO;
import com.atradius.dataaccess.hibernate.bo.TbbuConditionVariablesUniBO;
import com.atradius.dataaccess.hibernate.dao.TbbuConditionVariablesUniDAO;
import com.atradius.exception.DataAccessException;
import com.atradius.util.logging.ILogger;
import com.atradius.util.logging.LoggerFactory;

public class TbbuConditionVariablesUniDAOImpl implements TbbuConditionVariablesUniDAO {
	
	private static ILogger logger = LoggerFactory
	.getLogger(TbbuTextTranslationsUniDAOImpl.class);
	
	//FETCH
	public TbbuConditionVariablesUniBO getTextTranslations(HibernateUtil hibernateUtil, Integer bucltId, String applyAmountType, Integer bucyeSequence, String bucdeCd) throws DataAccessException {

		TbbuConditionVariablesUniBO tbbuConditionVariablesUniBO = new TbbuConditionVariablesUniBO();
		logger.enterMethod("getTextTranslations");
		Session session = null;
		try {
			session = hibernateUtil.getSession();
			Criteria contactPointCrit = session
					.createCriteria(TbbuConditionVariablesUniBO.class);
			contactPointCrit.add(Restrictions.eq("bucltId", bucltId));
			contactPointCrit.add(Restrictions.eq("applyAmountType", applyAmountType));
			contactPointCrit.add(Restrictions.eq("bucyeSequence", bucyeSequence));
			contactPointCrit.add(Restrictions.eq("bucdeCd", bucdeCd));
			contactPointCrit.add(Restrictions.gt("effectToDate", new java.util.Date()));
			
			tbbuConditionVariablesUniBO = (TbbuConditionVariablesUniBO) contactPointCrit
				.uniqueResult();

		} catch (HibernateException e) {

			// e.printStackTrace();
			logger.exception(e);
			throw new DataAccessException("DATABASE_QUERRY_FAILED", e);
		} 
		logger.exitMethod("getTextTranslations");
		return tbbuConditionVariablesUniBO;
	
	}
	

	public TbbuConditionVariablesUniBO getTextTranslations(HibernateUtil hibernateUtil, Integer bucltId, String applyAmountType, Integer bucyeSequence, String bucdeCd, Integer bumctSequence, Integer bumctOrder) throws DataAccessException {

		TbbuConditionVariablesUniBO tbbuConditionVariablesUniBO = new TbbuConditionVariablesUniBO();
		logger.enterMethod("getTextTranslations");
		Session session = null;
		try {
			session = hibernateUtil.getSession();
			Criteria contactPointCrit = session
					.createCriteria(TbbuConditionVariablesUniBO.class);
			contactPointCrit.add(Restrictions.eq("bucltId", bucltId));
			contactPointCrit.add(Restrictions.eq("applyAmountType", applyAmountType));
			contactPointCrit.add(Restrictions.eq("bucyeSequence", bucyeSequence));
			contactPointCrit.add(Restrictions.eq("bucdeCd", bucdeCd));
			contactPointCrit.add(Restrictions.eq("bumctSequence", bumctSequence));
			if(bumctOrder != null){
				contactPointCrit.add(Restrictions.eq("bumctOrder", bumctOrder));
			}
			contactPointCrit.add(Restrictions.gt("effectToDate", new java.util.Date()));
			
			tbbuConditionVariablesUniBO = (TbbuConditionVariablesUniBO) contactPointCrit
				.uniqueResult();

		} catch (HibernateException e) {

			// e.printStackTrace();
			logger.exception(e);
			throw new DataAccessException("DATABASE_QUERRY_FAILED", e);
		} 
		logger.exitMethod("getTextTranslations");
		return tbbuConditionVariablesUniBO;

	}
	
	
	public TbbuConditionVariablesUniBO getTextTranslations(HibernateUtil hibernateUtil, Integer bucltId, String applyAmountType, String bucdeCd, Integer bumctSequence, Integer bumctOrder) throws DataAccessException {

		TbbuConditionVariablesUniBO tbbuConditionVariablesUniBO = new TbbuConditionVariablesUniBO();
		logger.enterMethod("getTextTranslations");
		Session session = null;
		try {
			session = hibernateUtil.getSession();
			Criteria contactPointCrit = session
					.createCriteria(TbbuConditionVariablesUniBO.class);
			contactPointCrit.add(Restrictions.eq("bucltId", bucltId));
			contactPointCrit.add(Restrictions.eq("applyAmountType", applyAmountType));
			contactPointCrit.add(Restrictions.eq("bucdeCd", bucdeCd));
			contactPointCrit.add(Restrictions.eq("bumctSequence", bumctSequence));
			if(bumctOrder != null){
				contactPointCrit.add(Restrictions.eq("bumctOrder", bumctOrder));
			}
			contactPointCrit.add(Restrictions.gt("effectToDate", new java.util.Date()));
			
			tbbuConditionVariablesUniBO = (TbbuConditionVariablesUniBO) contactPointCrit
				.uniqueResult();

		} catch (HibernateException e) {

			// e.printStackTrace();
			logger.exception(e);
			throw new DataAccessException("DATABASE_QUERRY_FAILED", e);
		} 
		logger.exitMethod("getTextTranslations");
		return tbbuConditionVariablesUniBO;

	}

	//INSERT
	public TbbuConditionVariablesUniBO insertTextTranslations(HibernateUtil hibernateUtil, Integer bucltId, String applyAmountType, Integer bucyeSequence, String variableText, TbbuConditionTypesBO conditionTypesBO, Integer bumctSequence, Integer bumctOrder) throws DataAccessException {
		
		logger.enterMethod("insertTextTranslations");
		Transaction tx = null;
		Session session = null;
		
		TbbuConditionVariablesUniBO tbbuConditionVariablesUniBO = new TbbuConditionVariablesUniBO();				
		tbbuConditionVariablesUniBO.setBucltId(bucltId);
		tbbuConditionVariablesUniBO.setApplyAmountType(applyAmountType);
		tbbuConditionVariablesUniBO.setBucyeSequence(bucyeSequence);
		tbbuConditionVariablesUniBO.setBucdeCd(conditionTypesBO.getCode());
		tbbuConditionVariablesUniBO.setBumctSequence(bumctSequence);
		tbbuConditionVariablesUniBO.setBumctSequence(bumctSequence);
		tbbuConditionVariablesUniBO.setBumctOrder(bumctOrder);
		tbbuConditionVariablesUniBO.setVariableText(variableText.trim());
		//set dates as null as these will be handled by triggers
		tbbuConditionVariablesUniBO.setEffectToDate(null);
		tbbuConditionVariablesUniBO.setEffectFromDate(null);
					
		try {
			session = hibernateUtil.getSession();
			tx = session.beginTransaction();
			session.save(tbbuConditionVariablesUniBO);
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
		return tbbuConditionVariablesUniBO;
		
	}

	//UPDATE
	public TbbuConditionVariablesUniBO updateTextTranslations(HibernateUtil hibernateUtil, Integer bucltId, String applyAmountType, Integer bucyeSequence, String variableText, TbbuConditionTypesBO conditionTypesBO, Integer bumctSequence, Integer bumctOrder) throws DataAccessException {
		
		logger.enterMethod("updateTextTranslations");
		Session session = null;
		Transaction tx = null;
		
		TbbuConditionVariablesUniBO oldTbbuConditionVariablesUniBO = new TbbuConditionVariablesUniBO();
		TbbuConditionVariablesUniBO newTbbuConditionVariablesUniBO = new TbbuConditionVariablesUniBO();
		newTbbuConditionVariablesUniBO.setApplyAmountType(applyAmountType);
		newTbbuConditionVariablesUniBO.setBucltId(bucltId);
		newTbbuConditionVariablesUniBO.setBucyeSequence(bucyeSequence);
		newTbbuConditionVariablesUniBO.setBumctOrder(bumctOrder);
		newTbbuConditionVariablesUniBO.setBumctSequence(bumctSequence);
		newTbbuConditionVariablesUniBO.setBucdeCd(conditionTypesBO.getCode());
		newTbbuConditionVariablesUniBO.setVariableText(variableText);
		
		try {
			session = hibernateUtil.getSession();
			tx = session.beginTransaction();
			
			// find the existing record
			if(bumctSequence != null){
				oldTbbuConditionVariablesUniBO = 
					getTextTranslations(hibernateUtil, bucltId, applyAmountType, bucyeSequence, conditionTypesBO.getCode(), bumctSequence, bumctOrder);
			}else{
				oldTbbuConditionVariablesUniBO = 
					getTextTranslations(hibernateUtil, bucltId, applyAmountType, bucyeSequence, conditionTypesBO.getCode());
			}
			if(oldTbbuConditionVariablesUniBO != null){
				if (checkIfUpdateRequired(oldTbbuConditionVariablesUniBO,
						newTbbuConditionVariablesUniBO)) {
					session.evict(oldTbbuConditionVariablesUniBO);
					newTbbuConditionVariablesUniBO = insertTextTranslations(hibernateUtil, bucltId, applyAmountType, bucyeSequence, 
							variableText, conditionTypesBO, bumctSequence, bumctOrder);
				}
			}
			session.flush();
			tx.commit();
			
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			// e.printStackTrace();
			logger.exception(e);
		}
		logger.exitMethod("updateTextTranslations");
		return newTbbuConditionVariablesUniBO;
		
	}
	
	//DELETE
	public boolean deleteTextTranslations(HibernateUtil hibernateUtil, Integer bucltId, String applyAmountType, Integer bucyeSequence, String bucdeCd, Integer bumctSequence, Integer bumctOrder) throws DataAccessException {
		logger.enterMethod("deleteTextTranslations");
		Session session = null;
		Transaction tx = null;
		Date date = new Date();
		boolean isDeleted = false;
		TbbuConditionVariablesUniBO oldTbbuConditionVariablesUniBO = new TbbuConditionVariablesUniBO();
		
		try {
			session = hibernateUtil.getSession();
			tx = session.beginTransaction();
			// find the existing record
			if(bumctSequence != null){
				oldTbbuConditionVariablesUniBO = 
					getTextTranslations(hibernateUtil, bucltId, applyAmountType, bucyeSequence, bucdeCd, bumctSequence, bumctOrder);
			}else{
				oldTbbuConditionVariablesUniBO = 
					getTextTranslations(hibernateUtil, bucltId, applyAmountType, bucyeSequence, bucdeCd);
			}
			
			if(oldTbbuConditionVariablesUniBO != null){
				oldTbbuConditionVariablesUniBO.setEffectToDate(date);
				oldTbbuConditionVariablesUniBO.setEffectFromDate(date);
				// save it back to data base
				session.save(oldTbbuConditionVariablesUniBO); // historise old entity
				session.flush();
				session.evict(oldTbbuConditionVariablesUniBO);
				session.flush();
				tx.commit();
				isDeleted = true;
			}else{
				isDeleted = false;
			}
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
	

	private static boolean checkIfUpdateRequired(TbbuConditionVariablesUniBO oldObject,	TbbuConditionVariablesUniBO newObject) {
		
		logger.enterMethod("checkIfUpdateRequired");
		if((oldObject.getBucltId().intValue() == newObject.getBucltId().intValue())
			&& (oldObject.getBucdeCd().equals(newObject.getBucdeCd()))
			&& (oldObject.getApplyAmountType().equals(newObject.getApplyAmountType()))
			&& ((oldObject.getBucyeSequence() != null 
					&& (oldObject.getBucyeSequence().intValue() == newObject.getBucyeSequence().intValue()))
				|| (oldObject.getBumctSequence() != null 
						&& (oldObject.getBumctSequence().intValue() == newObject.getBumctSequence().intValue())))){
			String old_text = oldObject.getVariableText();
			String new_text = newObject.getVariableText();
			if (old_text == null){
				old_text = "";}
			if (new_text == null){
				new_text = "";}
			if (old_text.equals(new_text)) {
				logger.exitMethod("checkIfUpdateRequired");
				return false;
			} else {
				logger.exitMethod("checkIfUpdateRequired");
				return true;
			}
		}else {
			logger.exitMethod("checkIfUpdateRequired");
			return true;
		}
	}
}
