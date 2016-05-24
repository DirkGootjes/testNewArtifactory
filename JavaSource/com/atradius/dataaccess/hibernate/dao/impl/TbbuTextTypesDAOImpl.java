/*********************************************************************/
/*                           FILE HEADER                             */
/*********************************************************************/
/*                                                                   */
/*  FileName: 		TbbuTextTypesDAOImpl.java              */
/*  																 */
/*  $Author: INVSAR1 $									             */
/*																	 */
/*  $Revision: 1.8 $										         */
/*  																 */
/*  $Date: 2013/08/16 12:46:11 $                                     */
/*                                                                   */
/*  Description: 	This class performs database operation for       */
/*				      text                   				         */
/*********************************************************************/
/* Date        Name            Version             Comments          */
/*-------------------------------------------------------------------*/
/* 15/04/2013  INVSAR1      	1.0         Initial version created  */
/*********************************************************************/
package com.atradius.dataaccess.hibernate.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.atradius.dataaccess.hibernate.HibernateUtil;
import com.atradius.dataaccess.hibernate.bo.TbbuTextTypesBO;
import com.atradius.dataaccess.hibernate.dao.TbbuTextTypesDAO;
import com.atradius.util.logging.ILogger;
import com.atradius.util.logging.LoggerFactory;

public class TbbuTextTypesDAOImpl implements TbbuTextTypesDAO {
	private static ILogger logger = LoggerFactory
			.getLogger(TbbuTextTypesDAOImpl.class);

	public TbbuTextTypesBO getTextDetails(HibernateUtil hibernateUtil,int textId) {
		//System.out.println("getTextDetails {" + textId + "}");
		 logger.enterMethod("getTextDetails");
		Session session = hibernateUtil.getSession();

		Criteria contactPointCrit = session
				.createCriteria(TbbuTextTypesBO.class);
		contactPointCrit.add(Restrictions.eq("id", textId));
		
		TbbuTextTypesBO tbb = (TbbuTextTypesBO) contactPointCrit.uniqueResult();

		
		logger.exitMethod("getTextDetails");
		return tbb;
	}
}
