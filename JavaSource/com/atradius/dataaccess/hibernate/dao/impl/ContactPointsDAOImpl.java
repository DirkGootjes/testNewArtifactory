/*********************************************************************/
/*                           FILE HEADER                             */
/*********************************************************************/
/*                                                                   */
/*  FileName: 		ContactPointsDAOImpl.java             	  	     */
/*  																 */
/*  $Author: INVSAR1 $									             */
/*																	 */
/*  $Revision: 1.9 $										         */
/*  																 */
/*  $Date: 2013/08/16 12:46:12 $                                     */
/*                                                                   */
/*  Description: 	This class performs database operation for contact*/
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
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.atradius.dataaccess.hibernate.HibernateUtil;
import com.atradius.dataaccess.hibernate.bo.TborContactPointsBO;
import com.atradius.util.logging.ILogger;
import com.atradius.util.logging.LoggerFactory;

public class ContactPointsDAOImpl {
	private static ILogger logger = LoggerFactory
			.getLogger(ContactPointsDAOImpl.class);

	/**
	 * @param hibernateUtil
	 * @param ornnnId
	 * @return
	 */
	public static List<TborContactPointsBO> getContactPoints(HibernateUtil hibernateUtil,int ornnnId) {
		logger.enterMethod("getContactPoints");
		List<TborContactPointsBO> contactList = new ArrayList<TborContactPointsBO>();

		Session session = hibernateUtil.getSession();

		Criteria contactPointCrit = session
				.createCriteria(TborContactPointsBO.class);
		contactPointCrit.add(Restrictions.eq("ornnnId", ornnnId));
		contactPointCrit.add(Restrictions.gt("effectToDat",
				new java.util.Date()));
		contactList = contactPointCrit.list();
		
		logger.exitMethod("getContactPoints");
		return contactList;
	}
}
