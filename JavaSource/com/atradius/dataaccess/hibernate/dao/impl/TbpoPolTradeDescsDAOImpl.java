package com.atradius.dataaccess.hibernate.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.atradius.dataaccess.hibernate.HibernateUtil;
import com.atradius.dataaccess.hibernate.bo.TbpoPolTradeDescsBO;
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
/*				      Policy Modula Trade description                   				         */
/*********************************************************************/
/* Date        Name            Version             Comments          */
/*-------------------------------------------------------------------*/
/* 26/09/2014  INKPAG1      	1.0         Initial version created  */
/*********************************************************************/
public class TbpoPolTradeDescsDAOImpl {
	/**
	 * Logger
	 */
	private static ILogger logger = LoggerFactory
			.getLogger(TbpoPolTradeDescsDAOImpl.class);

	/** Saves cover trade description in temp table.
	 * @param tbpoPolTradeDescsBO
	 * @param hibernateUtil
	 * @return
	 * @throws Exception
	 */
	public boolean saveCoverTradeDesc(final TbpoPolTradeDescsBO tbpoPolTradeDescsBO,
			final HibernateUtil hibernateUtil) throws DataAccessException {
		logger.enterMethod("saveCoverTradeDesc started");
		boolean isSaveSuccessful;
		Transaction transaction = null;
		Session session = null;
		try {
			session = hibernateUtil.getSession();
			transaction = session.beginTransaction();

			Query query = session
					.createQuery("UPDATE TbpoPolTradeDescsBO set tradeDesc= :tradeDesc where policyId = :policyId and "
							+ "effectFromDate = :effectFromDate");

			query.setParameter("tradeDesc", tbpoPolTradeDescsBO.getTradeDesc());
			query.setParameter("policyId", tbpoPolTradeDescsBO.getPolicyId());
			query.setParameter("effectFromDate", tbpoPolTradeDescsBO
					.getEffectFromDate());

			int result = query.executeUpdate();
			
			if (result <= 0) {
				isSaveSuccessful = false;
			}else {
				isSaveSuccessful = true;
			}
			transaction.commit();

		} catch (HibernateException e) {
			if (transaction != null) {
				transaction.rollback();
			}			
			logger.exception(e);
			throw new DataAccessException("DATABASE_QUERRY_FAILED", e);
		}finally{
			if(session!=null){
				session.flush();
				session.close();
			}
		}
		logger.info("Is Policy Doc Cover Trade Desc Text Saved successfully? Ans: "
				+ isSaveSuccessful);
		logger.exitMethod("saveCoverTradeDesc finished");
		return isSaveSuccessful;
	}

	/** Fetches cover trade description from the temp table.
	 * @param tbpoPolTradeDescsBO
	 * @param hibernateUtil
	 * @return
	 * @throws DataAccessException@
	 */
	public TbpoPolTradeDescsBO getTradeDescForPolicy(
			final TbpoPolTradeDescsBO tbpoPolTradeDescsBO,
			final HibernateUtil hibernateUtil) throws DataAccessException {
		logger.enterMethod("getTradeDescForPolicy Started");
		TbpoPolTradeDescsBO tbpoPolTradeDescsDBBO = new TbpoPolTradeDescsBO();
		Session session=null;
		try {
			session = hibernateUtil.getSession();
			Criteria criteria = session.createCriteria(TbpoPolTradeDescsBO.class);
			criteria.add(Restrictions.eq("policyId", tbpoPolTradeDescsBO.getPolicyId()));
			criteria.add(Restrictions.eq("effectFromDate", tbpoPolTradeDescsBO
					.getEffectFromDate()));

			tbpoPolTradeDescsDBBO = (TbpoPolTradeDescsBO) criteria.uniqueResult();

		} catch (HibernateException e) {
			logger.exception(e);
			throw new DataAccessException("DATABASE_QUERRY_FAILED", e);
		}finally{
			if(session!=null){
				session.flush();
				session.close();
			}
		}
		logger.exitMethod("getTradeDescForPolicy Exited");
		return tbpoPolTradeDescsDBBO;
	}

}
