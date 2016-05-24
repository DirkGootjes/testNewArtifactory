package com.atradius.dataaccess.hibernate.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.atradius.dataaccess.hibernate.HibernateUtil;
import com.atradius.dataaccess.hibernate.bo.VwpoHeaderPoliciesBO;
import com.atradius.exception.DataAccessException;
import com.atradius.util.logging.ILogger;
import com.atradius.util.logging.LoggerFactory;
/*********************************************************************/
/*                           FILE HEADER                             */
/*********************************************************************/
/*                                                                   */
/*  FileName: 		VwpoHeaderPoliciesViewDAOImpl.java              */
/*  																 */
/*  $Author: INKPAG1 $									             */
/*																	 */
/*  $Revision: 1.4 $										         */
/*  																 */
/*  $Date: 2014/10/21 02:53:18 $                                     */
/*                                                                   */
/*  Description: 	This class performs database operation for       */
/*				     viewing Policy Details from View     	         */
/*********************************************************************/
/* Date        Name            Version             Comments          */
/*-------------------------------------------------------------------*/
/* 26/09/2014  INKPAG1      	1.0         Initial version created  */
/*********************************************************************/
public class VwpoHeaderPoliciesViewDAOImpl {
	/**
	 * Logger
	 */
	private static ILogger logger = LoggerFactory
			.getLogger(VwpoHeaderPoliciesViewDAOImpl.class);

	/** Fetches policy info from view.
	 * @param hibernateUtil
	 * @param policyId
	 * @return
	 * @throws DataAccessException 
	 */
	public VwpoHeaderPoliciesBO gePolicyInfo(final HibernateUtil hibernateUtil,
			final String policyId) throws DataAccessException {
		logger.enterMethod("gePolicyInfo", "VwpoHeaderPoliciesViewDAOImpl");
		VwpoHeaderPoliciesBO policyBO = new VwpoHeaderPoliciesBO();
		Session session=null;
		try {
			session = hibernateUtil.getSession();
			Criteria criteria = session.createCriteria(VwpoHeaderPoliciesBO.class);
			criteria.add(Restrictions.eq("policyId", policyId));
			// TODO need to do date conversion in format
			// criteria.add(Restrictions.gt("effectToDate", effect_date));
			policyBO = (VwpoHeaderPoliciesBO) criteria.uniqueResult();

		} catch (HibernateException e) {
			logger.exception(e);
			throw new DataAccessException("DATABASE_QUERRY_FAILED", e);
		}finally{
			if(session!=null){
				session.flush();
				session.close();
			}
		}
		logger.exitMethod("gePolicyInfo", "VwpoHeaderPoliciesViewDAOImpl");
		return policyBO;
	}
}
