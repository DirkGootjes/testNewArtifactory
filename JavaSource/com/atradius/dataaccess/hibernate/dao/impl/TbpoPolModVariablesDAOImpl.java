package com.atradius.dataaccess.hibernate.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.atradius.dataaccess.hibernate.HibernateUtil;
import com.atradius.dataaccess.hibernate.bo.TbpoPolModVariablesBO;
import com.atradius.exception.DataAccessException;
import com.atradius.util.logging.ILogger;
import com.atradius.util.logging.LoggerFactory;

/*********************************************************************/
/*                           FILE HEADER                             */
/*********************************************************************/
/*                                                                   */
/*  FileName: 		TbpoPolModVariablesDAOImpl.java              */
/*  																 */
/*  $Author: INKPAG1 $									             */
/*																	 */
/*  $Revision: 1.5 $										         */
/*  																 */
/*  $Date: 2014/10/21 02:53:18 $                                     */
/*                                                                   */
/*  Description: 	This class performs database operation for       */
/*				      Policy Modula variables                   				         */
/*********************************************************************/
/* Date        Name            Version             Comments          */
/*-------------------------------------------------------------------*/
/* 26/09/2014  INKPAG1      	1.0         Initial version created  */
/*********************************************************************/
public class TbpoPolModVariablesDAOImpl {
	/**
	 * Logger
	 */
	private static ILogger logger = LoggerFactory
			.getLogger(TbpoPolModVariablesDAOImpl.class);

	/** Fetches the variable value from teh database fom temp table
	 * @param tbpoPolModVariablesBO
	 * @param hibernateUtil
	 * @return
	 * @throws DataAccessException
	 */
	public TbpoPolModVariablesBO getVarValueForVarCode(
			final TbpoPolModVariablesBO tbpoPolModVariablesBO,
			final HibernateUtil hibernateUtil) throws DataAccessException {
		logger.enterMethod("getVarValueForVarCode Started");
		TbpoPolModVariablesBO tbpoPolModVariablesDBBO = new TbpoPolModVariablesBO();
		Session session=null;
		try {
			session = hibernateUtil.getSession();
			Criteria criteria = session.createCriteria(TbpoPolModVariablesBO.class);
			criteria
					.add(Restrictions.eq("policyId", tbpoPolModVariablesBO.getPolicyId()));
			criteria.add(Restrictions.eq("effectFromDate", tbpoPolModVariablesBO
					.getEffectFromDate()));
			criteria.add(Restrictions.eq("variableCode", tbpoPolModVariablesBO
					.getVariableCode()));

			tbpoPolModVariablesDBBO = (TbpoPolModVariablesBO) criteria.uniqueResult();

		} catch (HibernateException e) {
			logger.exception(e);
			throw new DataAccessException("DATABASE_QUERRY_FAILED", e);
		}finally{
			if(session!=null){
				session.flush();
				session.close();
			}
		}
		
		logger.exitMethod("getVarValueForVarCode Exited");
		return tbpoPolModVariablesDBBO;
	}

	/** Saves the module variables value in the table
	 * @param tbpoPolModVarBO
	 * @param hibernateUtil
	 * @return
	 * @throws Exception
	 */
	public boolean saveModuleVarDesc(final TbpoPolModVariablesBO tbpoPolModVarBO,
			final HibernateUtil hibernateUtil) throws DataAccessException {
		logger.enterMethod("saveModuleVarDesc started");
		boolean isSaveSuccessful = true;
		Transaction transaction = null;
		Session session = null;
		try {
			session = hibernateUtil.getSession();
			transaction = session.beginTransaction();

			Query query = session
					.createQuery("UPDATE TbpoPolModVariablesBO set variableValue= :variableValue where policyId = :policyId and "
							+ "trunc(effectFromDate) = trunc(:effectFromDate) and variableCode = :variableCode");

			query.setParameter("variableValue", tbpoPolModVarBO.getVariableValue());
			query.setParameter("policyId", tbpoPolModVarBO.getPolicyId());
			query.setParameter("effectFromDate", tbpoPolModVarBO
					.getEffectFromDate());
			query.setParameter("variableCode", tbpoPolModVarBO.getVariableCode());

			int result = query.executeUpdate();
			
			if (result <= 0) {
				isSaveSuccessful = false;
			}
			transaction.commit();

		} catch (HibernateException e) {
			if (transaction != null) {
				transaction.rollback();
			}		
			logger.exception(e);
			throw new DataAccessException("DATABASE_QUERRY_FAILED", e);
		}
		logger.info("Is Policy Doc Module Var Value Saved successfully? Ans: "
				+ isSaveSuccessful);
		logger.exitMethod("saveModuleVarDesc finished");
		return isSaveSuccessful;
	}
}
