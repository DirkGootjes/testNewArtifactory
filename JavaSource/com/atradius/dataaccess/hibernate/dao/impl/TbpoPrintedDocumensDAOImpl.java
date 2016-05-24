package com.atradius.dataaccess.hibernate.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.atradius.dataaccess.hibernate.HibernateUtil;
import com.atradius.dataaccess.hibernate.bo.TbpoPrintedDocumentsBO;
import com.atradius.exception.DataAccessException;
import com.atradius.util.logging.ILogger;
import com.atradius.util.logging.LoggerFactory;

/*********************************************************************/
/*                           FILE HEADER                             */
/*********************************************************************/
/*                                                                   */
/*  FileName: 		TbpoPrintedDocumensDAOImpl.java              */
/*  																 */
/*  $Author: INKPAG1 $									             */
/*																	 */
/*  $Revision: 1.5 $										         */
/*  																 */
/*  $Date: 2014/10/21 02:53:18 $                                     */
/*                                                                   */
/*  Description: 	This class performs database operation for       */
/*				      Policy Modula free text                   				         */
/*********************************************************************/
/* Date        Name            Version             Comments          */
/*-------------------------------------------------------------------*/
/* 26/09/2014  INKPAG1      	1.0         Initial version created  */
/*********************************************************************/

public class TbpoPrintedDocumensDAOImpl {
	/**
	 * Logger
	 */
	private static ILogger logger = LoggerFactory
			.getLogger(TbpoPrintedDocumensDAOImpl.class);

	/** Saves Free text in temp table
	 * @param tbpoPrintedDocBO
	 * @param hibernateUtil
	 * @return
	 * @throws DataAccessException
	 */
	public boolean saveFreeText(final TbpoPrintedDocumentsBO tbpoPrintedDocBO,
			final HibernateUtil hibernateUtil) throws DataAccessException {
		logger.enterMethod("saveFreeText");
		boolean isSaveSuccessful;
		Transaction transaction = null;
		Session session = null;
		try {
			session = hibernateUtil.getSession();
			transaction = session.beginTransaction();

			Query query = session
					.createQuery("UPDATE TbpoPrintedDocumentsBO set descFreeText= :descFreeText where policyId = :policyId and "
							+ "printDate = :printDate and groupCode = :groupCode and groupCodeFuncDesc = :groupCodeFuncDesc");

			query.setParameter("descFreeText", tbpoPrintedDocBO.getDescFreeText());
			query.setParameter("policyId", tbpoPrintedDocBO.getPolicyId());
			query.setParameter("printDate", tbpoPrintedDocBO.getPrintDate());
			query.setParameter("groupCode", tbpoPrintedDocBO.getGroupCode());
			query.setParameter("groupCodeFuncDesc", tbpoPrintedDocBO
					.getGroupCodeFuncDesc());

			int result = query.executeUpdate();

			if (result <= 0) {
				isSaveSuccessful = false;
			} else {
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
		logger.info("Is Policy Free Text Saved successfully? Ans: " + isSaveSuccessful);
		logger.exitMethod("saveFreeText");
		return isSaveSuccessful;
	}

	/** Fetches free text in temp table.
	 * @param tbpoPrintedDocBO
	 * @param hibernateUtil
	 * @return
	 * @throws DataAccessException
	 */
	public TbpoPrintedDocumentsBO getDetails(
			final TbpoPrintedDocumentsBO tbpoPrintedDocBO,
			final HibernateUtil hibernateUtil) throws DataAccessException {
		logger.enterMethod("getDetails Started");
		TbpoPrintedDocumentsBO tbpoPrintedDocDBBO = new TbpoPrintedDocumentsBO();
		Session session=null;
		try {
			session = hibernateUtil.getSession();
			Criteria criteria = session.createCriteria(TbpoPrintedDocumentsBO.class);
			criteria.add(Restrictions.eq("policyId", tbpoPrintedDocBO.getPolicyId()));
			criteria.add(Restrictions.eq("printDate", tbpoPrintedDocBO.getPrintDate()));
			criteria.add(Restrictions.eq("groupCode", tbpoPrintedDocBO.getGroupCode()));
			criteria.add(Restrictions.eq("groupCodeFuncDesc", tbpoPrintedDocBO
					.getGroupCodeFuncDesc()));

			tbpoPrintedDocDBBO = (TbpoPrintedDocumentsBO) criteria.uniqueResult();

		} catch (HibernateException e) {
			logger.exception(e);
			throw new DataAccessException("DATABASE_QUERRY_FAILED", e);
		}finally{
			if(session!=null){
				session.flush();
				session.close();
			}
		}
		logger.exitMethod("getDetails");
		return tbpoPrintedDocDBBO;
	}
}
