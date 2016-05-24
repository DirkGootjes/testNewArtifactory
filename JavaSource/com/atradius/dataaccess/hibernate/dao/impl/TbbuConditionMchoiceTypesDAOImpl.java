/*********************************************************************/
/*                           FILE HEADER                             */
/*********************************************************************/
/*                                                                   */
/*  FileName: 		TbbuConditionMchoiceTypesDAOImpl.java             	  	 */
/*  																 */
/*  $Author: INASHA2 $									     */
/*																	 */
/*  $Revision: 1.2 $										     */
/*  																 */
/*  $Date: 2014/03/06 13:49:51 $                            */
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
import org.hibernate.Session;
import org.hibernate.criterion.*;

import com.atradius.dataaccess.hibernate.HibernateUtil;
import com.atradius.dataaccess.hibernate.bo.TbbuConditionMchoiceTypesBO;
import com.atradius.util.logging.ILogger;
import com.atradius.util.logging.LoggerFactory;

public class TbbuConditionMchoiceTypesDAOImpl {
	private static ILogger logger = LoggerFactory
	.getLogger(TbbuConditionTypesDAOImpl.class);
//	Retrieving choice and its description
	public List<TbbuConditionMchoiceTypesBO> getConditionMchoiceTypes(HibernateUtil hibernateUtil, String bucdeCd) {
		 logger.enterMethod("getConditionMchoiceTypes");
		 List<TbbuConditionMchoiceTypesBO> tbbuCondMcTypList = new ArrayList<TbbuConditionMchoiceTypesBO>();
			Session session = hibernateUtil.getSession();

			Criteria contactPointCrit = session
					.createCriteria(TbbuConditionMchoiceTypesBO.class);
			contactPointCrit.add(Restrictions.eq("bucdeCode", bucdeCd));
			contactPointCrit.addOrder(Order.asc("sequence"));
			tbbuCondMcTypList = (List<TbbuConditionMchoiceTypesBO>) contactPointCrit.list();
			logger.exitMethod("getConditionMchoiceTypes");
			return tbbuCondMcTypList;
	}

}
