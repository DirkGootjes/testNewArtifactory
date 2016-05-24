/*********************************************************************/
/*                           FILE HEADER                             */
/*********************************************************************/
/*                                                                   */
/*  FileName: 		TbcsAuthenticationCodeDAOImpl.java              */
/*  																 */
/*  $Author: INVSAR1 $									             */
/*																	 */
/*  $Revision: 1.8 $										         */
/*  																 */
/*  $Date: 2013/08/16 12:48:00 $                                     */
/*                                                                   */
/*  Description: 	This class performs database operation for       */
/*				      authenticating user                   				         */
/*********************************************************************/
/* Date        Name            Version             Comments          */
/*-------------------------------------------------------------------*/
/* 15/04/2013  INVSAR1      	1.0         Initial version created  */
/*********************************************************************/
package com.atradius.dataaccess.hibernate.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.NonUniqueResultException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.atradius.dataaccess.hibernate.HibernateUtil;
import com.atradius.dataaccess.hibernate.bo.TbcsAuthenticationCodeBO;
import com.atradius.util.logging.ILogger;
import com.atradius.util.logging.LoggerFactory;

public class TbcsAuthenticationCodeDAOImpl {
	private static ILogger logger = LoggerFactory
			.getLogger(TbcsAuthenticationCodeDAOImpl.class);

	/**
	 * @param hibernateUtil
	 * @param userId
	 * @return
	 */
	public static TbcsAuthenticationCodeBO findActiveSession(HibernateUtil hibernateUtil,int userId) {
		logger.enterMethod("findActiveSession",userId);
		TbcsAuthenticationCodeBO lTbcsAuthenticationCodeBO = null;
		try {
			Session session = hibernateUtil.getSession();
			Criteria criteria = session
					.createCriteria(TbcsAuthenticationCodeBO.class);
			//criteria.add(Restrictions.eq("randomKey", randomKey));
			criteria.add(Restrictions.eq("userId", userId));
			criteria.add(Restrictions.eq("sessionFlag", "A"));
			lTbcsAuthenticationCodeBO = (TbcsAuthenticationCodeBO) criteria
					.uniqueResult();

		} catch(NonUniqueResultException e){
			logger.exception(e);
			throw e;
		}catch (HibernateException e) {
			logger.exception(e);
			throw e;
		}
		logger.exitMethod("findActiveSession",lTbcsAuthenticationCodeBO);
		return lTbcsAuthenticationCodeBO;
	}
	/**
	 * @param hibernateUtil
	 * @param userId
	 * @param randomKey
	 * @return
	 */
	public static TbcsAuthenticationCodeBO getActiveSession(HibernateUtil hibernateUtil,int userId,int randomKey) {
		logger.enterMethod("getActiveSession",userId);
		TbcsAuthenticationCodeBO lTbcsAuthenticationCodeBO = null;
		try {
			Session session = hibernateUtil.getSession();
			Criteria criteria = session
					.createCriteria(TbcsAuthenticationCodeBO.class);
			criteria.add(Restrictions.eq("randomKey", randomKey));
			criteria.add(Restrictions.eq("userId", userId));
			criteria.add(Restrictions.eq("sessionFlag", "A"));
			lTbcsAuthenticationCodeBO = (TbcsAuthenticationCodeBO) criteria
					.uniqueResult();
			
		} catch(NonUniqueResultException e){
			logger.exception(e);
			throw e;
		}catch (HibernateException e) {
			logger.exception(e);
			throw e;
		}
		logger.exitMethod("getActiveSession",lTbcsAuthenticationCodeBO);
		return lTbcsAuthenticationCodeBO;
	}
	/**
	 * @param hibernateUtil
	 * @param lTbcsAuthenticationCodeBO
	 * @return
	 */
	public static boolean deActivateSession(HibernateUtil hibernateUtil,TbcsAuthenticationCodeBO lTbcsAuthenticationCodeBO){
		logger.enterMethod("deActivateSession",lTbcsAuthenticationCodeBO);
		boolean isSuccess = false;
		Transaction transaction = null;
		Session session = null;
		if(lTbcsAuthenticationCodeBO !=null){
			try {
				session = hibernateUtil.getSession();
				transaction = session.beginTransaction();
				lTbcsAuthenticationCodeBO.setSessionFlag("I");
				session.update(lTbcsAuthenticationCodeBO);
				session.flush();
				transaction.commit();
			} catch (HibernateException e) {
				isSuccess = false;
				logger.exception(e);
				if(transaction !=null)
					transaction.rollback();
				if(session !=null && session.isOpen())
					session.close();
				
			}
		}
		logger.exitMethod("deActivateSession",isSuccess);
		return isSuccess;
		
	}
	/**
	 * @param hibernateUtil
	 * @param userId
	 * @return
	 */
	public static boolean deActivateSessionsByUserId(HibernateUtil hibernateUtil,int userId){
		logger.enterMethod("deActivateSessionsByUserId",userId);
		boolean isSuccess = false;
		Session session = null;
		if(userId !=0){
			try {
				session = hibernateUtil.getSession();
				String updateHQL = "update TbcsAuthenticationCodeBO set sessionFlag = :sessionFlag where userId = :userId";
		        Query query = session.createQuery(updateHQL);
		        query.setString("sessionFlag","I");
		        query.setInteger("userId",userId);
		        int rowCount = query.executeUpdate();
		        logger.info("total session killed for user["+userId+"] is : ["+rowCount+"]");
		        session.flush();
			} catch (HibernateException e) {
				isSuccess = false;
				logger.exception(e);
				if(session !=null && session.isOpen())
					session.close();
				
			}
		}
		logger.exitMethod("deActivateSessionsByUserId",isSuccess);
		return isSuccess;
		
	}
}
