/*********************************************************************/
/*                           FILE HEADER                             */
/*********************************************************************/
/*                                                                   */
/*  FileName: 		TbbuConditionVariablesUniTempDAOImpl.java            */
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
/* 22/11/2013  INNBHA1      	1.0         Initial version created  */
/* 16/12/2013  INNBHA1      	1.1         Updated for Requirement  */
/* 											updates for Freetext     */
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
import com.atradius.dataaccess.hibernate.bo.TbbuConditionVariablesUniTempBO;
import com.atradius.dataaccess.hibernate.dao.TbbuConditionVariablesUniTempDAO;
import com.atradius.exception.DataAccessException;
import com.atradius.util.logging.ILogger;
import com.atradius.util.logging.LoggerFactory;

public class TbbuConditionVariablesUniTempDAOImpl implements
		TbbuConditionVariablesUniTempDAO {

	private static ILogger logger = LoggerFactory
			.getLogger(TbbuConditionVariablesUniTempDAOImpl.class);

	// FETCH
	public TbbuConditionVariablesUniTempBO getTextTranslationsTemp(
			HibernateUtil hibernateUtil, Integer bucltId,
			String applyAmountType, Integer bucyeSequence, String bucdeCd)
			throws DataAccessException {

		TbbuConditionVariablesUniTempBO tbbuConditionVariablesUniTempBO = new TbbuConditionVariablesUniTempBO();
		logger.enterMethod("getTextTranslationsTemp");
		Session session = null;
		try {
			session = hibernateUtil.getSession();
			Criteria contactPointCrit = session
					.createCriteria(TbbuConditionVariablesUniTempBO.class);
			contactPointCrit.add(Restrictions.eq("bucltId", bucltId));
			contactPointCrit.add(Restrictions.eq("applyAmountType",
					applyAmountType));
			contactPointCrit.add(Restrictions
					.eq("bucyeSequence", bucyeSequence));
			contactPointCrit.add(Restrictions.eq("bucdeCd", bucdeCd));
			contactPointCrit.add(Restrictions.gt("effectToDate",
					new java.util.Date()));

			tbbuConditionVariablesUniTempBO = (TbbuConditionVariablesUniTempBO) contactPointCrit
					.uniqueResult();
			session.flush();

		} catch (HibernateException e) {
			logger.exception(e);
			throw new DataAccessException("DATABASE_QUERRY_FAILED", e);
		}
		logger.exitMethod("getTextTranslationsTemp");
		return tbbuConditionVariablesUniTempBO;

	}

	public TbbuConditionVariablesUniTempBO getTextTranslationsTemp(
			HibernateUtil hibernateUtil, Integer bucltId,
			String applyAmountType, Integer bucyeSequence, String bucdeCd,
			Integer bumctSequence, Integer bumctOrder)
			throws DataAccessException {

		TbbuConditionVariablesUniTempBO tbbuConditionVariablesUniTempBO = new TbbuConditionVariablesUniTempBO();
		logger.enterMethod("getTextTranslationsTemp");
		Session session = null;
		try {
			session = hibernateUtil.getSession();
			Criteria contactPointCrit = session
					.createCriteria(TbbuConditionVariablesUniTempBO.class);
			contactPointCrit.add(Restrictions.eq("bucltId", bucltId));
			contactPointCrit.add(Restrictions.eq("applyAmountType",
					applyAmountType));
			contactPointCrit.add(Restrictions
					.eq("bucyeSequence", bucyeSequence));
			contactPointCrit.add(Restrictions.eq("bucdeCd", bucdeCd));
			contactPointCrit.add(Restrictions
					.eq("bumctSequence", bumctSequence));
			if (bumctOrder != null) {
				contactPointCrit.add(Restrictions.eq("bumctOrder", bumctOrder));
			}
			contactPointCrit.add(Restrictions.gt("effectToDate",
					new java.util.Date()));

			tbbuConditionVariablesUniTempBO = (TbbuConditionVariablesUniTempBO) contactPointCrit
					.uniqueResult();
			session.flush();

		} catch (HibernateException e) {
			logger.exception(e);
			throw new DataAccessException("DATABASE_QUERRY_FAILED", e);
		}
		logger.exitMethod("getTextTranslationsTemp");
		return tbbuConditionVariablesUniTempBO;

	}

	public TbbuConditionVariablesUniTempBO getTextTranslationsTemp(
			HibernateUtil hibernateUtil, Integer bucltId,
			String applyAmountType, String bucdeCd, Integer bumctSequence,
			Integer bumctOrder) throws DataAccessException {

		TbbuConditionVariablesUniTempBO tbbuConditionVariablesUniTempBO = new TbbuConditionVariablesUniTempBO();
		logger.enterMethod("getTextTranslationsTemp");
		Session session = null;
		try {
			session = hibernateUtil.getSession();
			Criteria contactPointCrit = session
					.createCriteria(TbbuConditionVariablesUniTempBO.class);
			contactPointCrit.add(Restrictions.eq("bucltId", bucltId));
			contactPointCrit.add(Restrictions.eq("applyAmountType",
					applyAmountType));
			contactPointCrit.add(Restrictions.eq("bucdeCd", bucdeCd));
			contactPointCrit.add(Restrictions
					.eq("bumctSequence", bumctSequence));
			if (bumctOrder != null) {
				contactPointCrit.add(Restrictions.eq("bumctOrder", bumctOrder));
			}
			contactPointCrit.add(Restrictions.gt("effectToDate",
					new java.util.Date()));

			tbbuConditionVariablesUniTempBO = (TbbuConditionVariablesUniTempBO) contactPointCrit
					.uniqueResult();
			session.flush();

		} catch (HibernateException e) {
			logger.exception(e);
			throw new DataAccessException("DATABASE_QUERRY_FAILED", e);
		}
		logger.exitMethod("getTextTranslationsTemp");
		return tbbuConditionVariablesUniTempBO;

	}

	// INSERT
	public TbbuConditionVariablesUniTempBO insertTextTranslationsTemp(
			HibernateUtil hibernateUtil, Integer bucltId,
			String applyAmountType, Integer bucyeSequence, String variableText,
			TbbuConditionTypesBO conditionTypesBO, Integer bumctSequence,
			Integer bumctOrder) throws DataAccessException {

		logger.enterMethod("insertTextTranslations");
		Transaction tx = null;
		Session session = null;

		TbbuConditionVariablesUniTempBO tbbuConditionVariablesUniTempBO = new TbbuConditionVariablesUniTempBO();
		tbbuConditionVariablesUniTempBO.setBucltId(bucltId);
		tbbuConditionVariablesUniTempBO.setApplyAmountType(applyAmountType);
		tbbuConditionVariablesUniTempBO.setBucyeSequence(bucyeSequence);
		tbbuConditionVariablesUniTempBO.setBucdeCd(conditionTypesBO.getCode());
		tbbuConditionVariablesUniTempBO.setBumctSequence(bumctSequence);
		tbbuConditionVariablesUniTempBO.setBumctOrder(bumctOrder);
		tbbuConditionVariablesUniTempBO.setVariableText(variableText.trim());
		// set dates as null as these will be handled by triggers
		tbbuConditionVariablesUniTempBO.setEffectToDate(null);
		tbbuConditionVariablesUniTempBO.setEffectFromDate(null);

		try {
			session = hibernateUtil.getSession();
			tx = session.beginTransaction();
			session.save(tbbuConditionVariablesUniTempBO);
			tx.commit();

		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			//e.printStackTrace();
			logger.exception(e);
			throw new DataAccessException("DATABASE_QUERRY_FAILED", e);
		}
		logger.exitMethod("insertTextTranslationsTemp");
		return tbbuConditionVariablesUniTempBO;

	}

	// UPDATE
	public TbbuConditionVariablesUniTempBO updateTextTranslationsTemp(
			HibernateUtil hibernateUtil, Integer bucltId, String applyAmountType, Integer bucyeSequence,
			String variableText, TbbuConditionTypesBO conditionTypesBO,
			Integer bumctSequence, Integer bumctOrder)
			throws DataAccessException {

		logger.enterMethod("updateTextTranslationsTemp");
		Session session = null;
		Transaction tx = null;

		TbbuConditionVariablesUniTempBO oldTbbuConditionVariablesUniTempBO = new TbbuConditionVariablesUniTempBO();
		TbbuConditionVariablesUniTempBO newTbbuConditionVariablesUniTempBO = new TbbuConditionVariablesUniTempBO();
		newTbbuConditionVariablesUniTempBO.setApplyAmountType(applyAmountType);
		newTbbuConditionVariablesUniTempBO.setBucltId(bucltId);
		newTbbuConditionVariablesUniTempBO.setBucyeSequence(bucyeSequence);
		newTbbuConditionVariablesUniTempBO.setBumctOrder(bumctOrder);
		newTbbuConditionVariablesUniTempBO.setBumctSequence(bumctSequence);
		newTbbuConditionVariablesUniTempBO.setBucdeCd(conditionTypesBO.getCode());
		newTbbuConditionVariablesUniTempBO.setVariableText(variableText);

		try {
			session = hibernateUtil.getSession();
			tx = session.beginTransaction();

			// find the existing record
			if (bumctSequence != null) {
				oldTbbuConditionVariablesUniTempBO = getTextTranslationsTemp(
						hibernateUtil, bucltId, applyAmountType,
						bucyeSequence, conditionTypesBO.getCode(),
						bumctSequence, bumctOrder);
			} else {
				oldTbbuConditionVariablesUniTempBO = getTextTranslationsTemp(
						hibernateUtil, bucltId, applyAmountType,
						bucyeSequence, conditionTypesBO.getCode());
			}
			if (oldTbbuConditionVariablesUniTempBO != null) {
				if (checkIfUpdateRequiredTemp(oldTbbuConditionVariablesUniTempBO,
						newTbbuConditionVariablesUniTempBO)) {
					session.evict(oldTbbuConditionVariablesUniTempBO);
					newTbbuConditionVariablesUniTempBO = insertTextTranslationsTemp(
							hibernateUtil, bucltId, applyAmountType,
							bucyeSequence, variableText, conditionTypesBO,
							bumctSequence, bumctOrder);
				}
			}
			session.flush();
			tx.commit();

		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
				logger.exception(e);
		}
		logger.exitMethod("updateTextTranslationsTemp");
		return newTbbuConditionVariablesUniTempBO;

	}

	// DELETE
	public boolean deleteTextTranslationsTemp(HibernateUtil hibernateUtil,
			Integer bucltId, String applyAmountType,Integer bucyeSequence, 
			String bucdeCd, Integer bumctSequence,Integer bumctOrder) throws DataAccessException {
		logger.enterMethod("deleteTextTranslationsTemp");
		Session session = null;
		Transaction tx = null;
		Date date = new Date();
		boolean isDeleted = false;
		TbbuConditionVariablesUniTempBO tbbuConditionVariablesUniTempBO = new TbbuConditionVariablesUniTempBO();
		
		try {
			session = hibernateUtil.getSession();
			tx = session.beginTransaction();
			// find the existing record
			if (bucyeSequence != null && bumctSequence != null) {
				tbbuConditionVariablesUniTempBO = getTextTranslationsTemp(
						hibernateUtil, bucltId, applyAmountType,
						bucyeSequence, bucdeCd, bumctSequence, bumctOrder);
			} else if (bucyeSequence != null) {				
				tbbuConditionVariablesUniTempBO = getTextTranslationsTemp(
						hibernateUtil, bucltId, applyAmountType,
						bucyeSequence, bucdeCd);
			} else {				
				tbbuConditionVariablesUniTempBO = getTextTranslationsTemp(
						hibernateUtil, bucltId, applyAmountType, bucdeCd,
						bumctSequence, bumctOrder);
			}
			if (tbbuConditionVariablesUniTempBO != null) {
				tbbuConditionVariablesUniTempBO.setEffectToDate(date);
				tbbuConditionVariablesUniTempBO.setEffectFromDate(date);
				// save it back to data base
				session.save(tbbuConditionVariablesUniTempBO); // historise old
				// entity
				session.flush();
				session.evict(tbbuConditionVariablesUniTempBO);
				session.flush();
				tx.commit();
				isDeleted = true;

			}else {
				isDeleted = false;
			}
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			isDeleted = false;
			logger.exception(e);
		}
		logger.exitMethod("deleteTextTranslationsTemp");
		return isDeleted;
	}
	
	//RESET
	public String resetTextTranslationsTemp(HibernateUtil hibernateUtil,
			Integer bucltId, String applyAmountType,Integer bucyeSequence, 
			String bucdeCd, Integer bumctSequence,Integer bumctOrder) throws DataAccessException {
		logger.enterMethod("resetTextTranslationsTemp");
		Session session = null;
		Transaction tx = null;
		String resetText = null;
		TbbuConditionVariablesUniTempBO tbbuConditionVariablesUniTempBO = new TbbuConditionVariablesUniTempBO();
		
		try {
			session = hibernateUtil.getSession();
			tx = session.beginTransaction();
			// find the existing record
			if (bucyeSequence != null && bumctSequence != null) {
				tbbuConditionVariablesUniTempBO = getTextTranslationsTemp(
						hibernateUtil, bucltId, applyAmountType,
						bucyeSequence, bucdeCd, bumctSequence, bumctOrder);
			} else if (bucyeSequence != null) {				
				tbbuConditionVariablesUniTempBO = getTextTranslationsTemp(
						hibernateUtil, bucltId, applyAmountType,
						bucyeSequence, bucdeCd);
			} else {				
				tbbuConditionVariablesUniTempBO = getTextTranslationsTemp(
						hibernateUtil, bucltId, applyAmountType, bucdeCd,
						bumctSequence, bumctOrder);
			}
			if (tbbuConditionVariablesUniTempBO != null) {
				// entity
				session.flush();
				tx.commit();
				resetText = tbbuConditionVariablesUniTempBO.getVariableText();

			}
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			resetText = null;
			logger.exception(e);
		}
		logger.exitMethod("resetTextTranslationsTemp");
		return resetText;
	}

	private static boolean checkIfUpdateRequiredTemp(
			TbbuConditionVariablesUniTempBO oldObject,
			TbbuConditionVariablesUniTempBO newObject) {

		logger.enterMethod("checkIfUpdateRequiredTemp");
		if ((oldObject.getBucltId().intValue() == newObject.getBucltId()
				.intValue())
				&& (oldObject.getBucdeCd().equals(newObject.getBucdeCd()))
				&& (oldObject.getApplyAmountType().equals(newObject
						.getApplyAmountType()))
				&& ((oldObject.getBucyeSequence() != null && (oldObject
						.getBucyeSequence().intValue() == newObject
						.getBucyeSequence().intValue())) || (oldObject
						.getBumctSequence() != null && (oldObject
						.getBumctSequence().intValue() == newObject
						.getBumctSequence().intValue())))) {
			String old_text = oldObject.getVariableText();
			String new_text = newObject.getVariableText();
			if (old_text == null) {
				old_text = "";
			}
			if (new_text == null) {
				new_text = "";
			}
			if (old_text.equals(new_text)) {
				logger.exitMethod("checkIfUpdateRequiredTemp");
				return false;
			} else {
				logger.exitMethod("checkIfUpdateRequiredTemp");
				return true;
			}
		} else {
			logger.exitMethod("checkIfUpdateRequiredTemp");
			return true;
		}
	}
}
