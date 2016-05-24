/*********************************************************************/
/*                           FILE HEADER                             */
/*********************************************************************/
/*                                                                   */
/*  FileName: 		TbbuConditionTypesDAOImpl.java         		     */
/*  																 */
/*  $Author: INASHA2 $									             */
/*																	 */
/*  $Revision: 1.2 $										         */
/*  																 */
/*  $Date: 2014/03/06 13:49:52 $                                     */
/*                                                                   */
/*  Description: 	This class performs database operation for       */
/*				      condition codes.            			         */
/*********************************************************************/
/* Date        Name            Version             Comments          */
/*-------------------------------------------------------------------*/
/* 15/10/2013  INATIW1      	1.0         Initial version created  */
/*********************************************************************/

package com.atradius.dataaccess.hibernate.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.atradius.dataaccess.hibernate.HibernateUtil;
import com.atradius.dataaccess.hibernate.bo.TbbuConditionTypesBO;
import com.atradius.dataaccess.hibernate.dao.TbbuConditionTypesDAO;
import com.atradius.util.logging.ILogger;
import com.atradius.util.logging.LoggerFactory;

public class TbbuConditionTypesDAOImpl implements TbbuConditionTypesDAO {
	
	private static ILogger logger = LoggerFactory
	.getLogger(TbbuConditionTypesDAOImpl.class);

	public TbbuConditionTypesBO getConditionCodeDetails(HibernateUtil hibernateUtil, String bucdeCd) {
		 logger.enterMethod("getConditionCodeDetails");
			Session session = hibernateUtil.getSession();

			Criteria contactPointCrit = session
					.createCriteria(TbbuConditionTypesBO.class);
			contactPointCrit.add(Restrictions.eq("code", bucdeCd));
			
			TbbuConditionTypesBO conditionTypesBO =  (TbbuConditionTypesBO) contactPointCrit.uniqueResult();

			
			logger.exitMethod("getConditionCodeDetails");
			return conditionTypesBO;
	}

}
