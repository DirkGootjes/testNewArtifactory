/*********************************************************************/
/*                           FILE HEADER                             */
/*********************************************************************/
/*                                                                   */
/*  FileName: 		TborSystemUsersDAOImpl.java             	  	 */
/*  																 */
/*  $Author: INVSAR1 $									             */
/*																	 */
/*  $Revision: 1.8 $										         */
/*  																 */
/*  $Date: 2013/08/16 12:46:11 $                                     */
/*                                                                   */
/*  Description: 	This class performs database operation for       */
/*				     SystemUsers details.                   	     */
/*********************************************************************/
/* Date        Name            Version             Comments          */
/*-------------------------------------------------------------------*/
/* 25/04/2013  INVSAR1      	1.0         Initial version created  */
/*********************************************************************/
package com.atradius.dataaccess.hibernate.dao.impl;

import java.util.Date;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.atradius.dataaccess.hibernate.HibernateUtil;
import com.atradius.dataaccess.hibernate.bo.TborSystemUsersBO;
import com.atradius.util.logging.ILogger;
import com.atradius.util.logging.LoggerFactory;

public class TborSystemUsersDAOImpl {
	private static ILogger logger = LoggerFactory
			.getLogger(TborSystemUsersDAOImpl.class);

	/**
	 * @param hibernateUtil
	 * @param indivUid
	 * @return
	 */
	public static TborSystemUsersBO findUser(HibernateUtil hibernateUtil,String indivUid) {
		logger.enterMethod("findUser",indivUid);
		TborSystemUsersBO lTborSystemUsersBO = null;
		try {
			Session session = hibernateUtil.getSession();
			Criteria criteria = session.createCriteria(TborSystemUsersBO.class);
			criteria.add(Restrictions.eq("indivUid", indivUid));
			criteria.add(Restrictions.gt("effectToDate", new Date()));
			lTborSystemUsersBO = (TborSystemUsersBO)criteria.uniqueResult();
			
		} catch (HibernateException e) {
			logger.exception(e);
		}
		logger.exitMethod("findUser",lTborSystemUsersBO);
		return lTborSystemUsersBO;
	}
	/**
	 * @param hibernateUtil
	 * @param indivUid
	 * @return
	 */
	public static TborSystemUsersBO findIndivDetails(HibernateUtil hibernateUtil,int indivUid) {
		logger.enterMethod("findIndivDetails",indivUid);
		TborSystemUsersBO lTborSystemUsersBO = null;
		try {
			Session session = hibernateUtil.getSession();
			Criteria criteria = session.createCriteria(TborSystemUsersBO.class);
			criteria.add(Restrictions.eq("indivId", indivUid));
			criteria.add(Restrictions.gt("effectToDate", new Date()));
			lTborSystemUsersBO = (TborSystemUsersBO)criteria.uniqueResult();
			
		} catch (HibernateException e) {
			logger.exception(e);
		}
		logger.exitMethod("findIndivDetails",lTborSystemUsersBO);
		return lTborSystemUsersBO;
	}

}
