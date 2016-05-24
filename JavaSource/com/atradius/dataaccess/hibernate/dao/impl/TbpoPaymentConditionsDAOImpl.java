/*********************************************************************/
/*                           FILE HEADER                             */
/*********************************************************************/
/*                                                                   */
/*  FileName: 		TbpoPaymentConditionsDAOImpl.java              */
/*  																 */
/*  $Author: INHYOU1 $									             */
/*																	 */
/*  $Revision: 1.0 $										         */
/*  																 */
/*  $Date: 2016/01/14 02:53:18 $                                     */
/*                                                                   */
/*  Description: 	This class performs database operation for       */
/*				      Payment Conditions unicode text                   				         */
/*********************************************************************/
package com.atradius.dataaccess.hibernate.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.atradius.dataaccess.hibernate.HibernateUtil;
import com.atradius.dataaccess.hibernate.bo.TbpoPaymentConditionsBO;
import com.atradius.exception.DataAccessException;
import com.atradius.util.logging.ILogger;
import com.atradius.util.logging.LoggerFactory;

public class TbpoPaymentConditionsDAOImpl {


	/**
	 * Logger
	 * 
	 */
	private static ILogger logger = LoggerFactory
			.getLogger(TbpoPolTradeDescsDAOImpl.class);

	/** Saves payment description in temp table.
	 * @param tbpopaymentConditionsBO
	 * @param hibernateUtil
	 * @return
	 * @throws Exception
	 */
	public boolean savePaymentDesc(final TbpoPaymentConditionsBO paymentBO,
			final HibernateUtil hibernateUtil) throws DataAccessException {
		logger.enterMethod("savePaymentDesc started");
		boolean isSaveSuccessful;
		Transaction transaction = null;
		Session session = null;
		try {
			session = hibernateUtil.getSession();
			transaction = session.beginTransaction();

			Query query = session
					.createQuery("UPDATE TbpoPaymentConditionsBO SET " +
							"pcDescription =:pcDescription WHERE langCode =:langCode1 " +
							"AND pcPer =:pcper AND pcPerTyp =:pcPerTyp" +
							" AND typ = :typ1");

			query.setString("pcDescription", paymentBO.getPcDescription());
			query.setInteger("pcper", paymentBO.getPcPer() );
			query.setString("pcPerTyp", paymentBO.getPcPerTyp());
			query.setString("langCode1", paymentBO.getLangCode());
			query.setString("typ1", paymentBO.getTyp());



			logger.info(query);
			int result = query.executeUpdate();

			if (result <= 0) {
				isSaveSuccessful = false;
			}else {
				isSaveSuccessful = true;
			}
			transaction.commit();

		} catch (Exception e) {
			
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
		logger.info("Is Payment Desc Text Saved successfully? Ans: "
				+ isSaveSuccessful);
		logger.exitMethod("savePaymentDesc finished");
		return isSaveSuccessful;
	}

	/** Fetches free text in temp table.
	 * @param tbpoPaymentConditionBO
	 * @param hibernateUtil
	 * @return
	 * @throws DataAccessException
	 */
	public TbpoPaymentConditionsBO getDetails(
			final TbpoPaymentConditionsBO tbpoPymtCondBO,
			final HibernateUtil hibernateUtil) throws DataAccessException {
		logger.enterMethod("getDetails Started");
		TbpoPaymentConditionsBO tbpoPaymentConditionsBO = new TbpoPaymentConditionsBO();
		Session session=null;
		try {
			session = hibernateUtil.getSession();
			Criteria criteria = session.createCriteria(TbpoPaymentConditionsBO.class);
			criteria.add(Restrictions.eq("pcPer",tbpoPymtCondBO.getPcPer()));
			criteria.add(Restrictions.eq("pcPerTyp", tbpoPymtCondBO.getPcPerTyp()));
			criteria.add(Restrictions.eq("typ", tbpoPymtCondBO.getTyp()));
			criteria.add(Restrictions.eq("langCode", tbpoPymtCondBO.getLangCode()));

			tbpoPaymentConditionsBO = (TbpoPaymentConditionsBO) criteria.uniqueResult();

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
		return tbpoPaymentConditionsBO;
	}
}
