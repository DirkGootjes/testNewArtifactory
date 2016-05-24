package com.atradius.dataaccess.hibernate.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.atradius.dataaccess.hibernate.HibernateUtil;
import com.atradius.dataaccess.hibernate.bo.TbclAssessCalcLiabilitiesBO;
import com.atradius.dataaccess.hibernate.bo.TbclClaimsCalcLiabilitiesBO;
import com.atradius.dataaccess.hibernate.dao.ClaimsCalcLiabilitiesDAO;
import com.atradius.util.logging.ILogger;
import com.atradius.util.logging.LoggerFactory;

public class ClaimsCalcLiabilitiesDAOImpl implements ClaimsCalcLiabilitiesDAO {
	private static ILogger logger = LoggerFactory
			.getLogger(ClaimsCalcLiabilitiesDAOImpl.class);

	private static String QUERY = "DELETE FROM TBCL_CLAIMS_CALC_LIAB_UNI_TEMP WHERE CLCPT_ID = :paymentId";

	public List<TbclClaimsCalcLiabilitiesBO> getClaimsCalculationDetails(
			HibernateUtil hibernateUtil, Long paymentId) {
		logger.enterMethod("getClaimsCalculationDetails", paymentId,
				hibernateUtil.getUserName());
		List<TbclClaimsCalcLiabilitiesBO> iTbclClaimsCalcLiabilitiesBOList = null;
		try {
			Session session = hibernateUtil.getSession();
			Criteria iCriteria = session.createCriteria(
					TbclClaimsCalcLiabilitiesBO.class).add(
					Restrictions.eq("primaryKey.clcptId", paymentId));
			iCriteria.addOrder(Order.asc("clclcCode"));

			iTbclClaimsCalcLiabilitiesBOList = iCriteria.list();
			logger.info("iTbclClaimsCalcLiabilitiesBOList.size() is ["
					+ iTbclClaimsCalcLiabilitiesBOList.size() + "]");

		} catch (HibernateException e) {
			logger
					.error("Unable to fetch records. in getClaimsCalculationDetails");
			logger.exception(e);
		} finally {
			logger.exitMethod("getClaimsCalculationDetails",
					iTbclClaimsCalcLiabilitiesBOList.size());
		}
		return iTbclClaimsCalcLiabilitiesBOList;
	}

	public boolean updateUnicode(HibernateUtil hibernateUtil,
			List<TbclClaimsCalcLiabilitiesBO> beanList) {
		logger.enterMethod("updateUnicode", beanList, hibernateUtil
				.getUserName());

		boolean updated = false;
		Session session = hibernateUtil.getSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			// save the beans here
			for (int i = 0; i < beanList.size(); i++)
				session.saveOrUpdate(beanList.get(i));

			transaction.commit();
			logger.info("total records added  [" + beanList.size() + "]");
			updated = true;
		} catch (HibernateException e) {
			logger.error("Unable to save new records.");
			logger.exception(e);
			if (transaction != null)
				transaction.rollback();
			updated = false;
		}
		logger.exitMethod("updateUnicode", updated);
		return updated;
	}

	public boolean delete(HibernateUtil hibernateUtil, Long paymentId) {
		logger.enterMethod("delete", paymentId, hibernateUtil.getUserName());
		boolean deleted = false;
		List<TbclClaimsCalcLiabilitiesBO> iTbclClaimsCalcLiabilitiesBO = getClaimsCalculationDetails(
				hibernateUtil, paymentId);
		if (iTbclClaimsCalcLiabilitiesBO.size() > 0) {
			Session session = hibernateUtil.getSession();
			Transaction transaction = null;
			try {
				transaction = session.beginTransaction();
				for (int i = 0; i < iTbclClaimsCalcLiabilitiesBO.size(); i++) {
					session.evict(iTbclClaimsCalcLiabilitiesBO.get(i));
				}
				SQLQuery query = session.createSQLQuery(QUERY);
				query.setLong("paymentId", paymentId);
				int noOfrecordsDeleted = query.executeUpdate();
				logger.info("total records deleted  [" + noOfrecordsDeleted
						+ "] for paymentId [" + paymentId + "] ");

				transaction.commit();
				deleted = true;
			} catch (HibernateException e) {
				logger.error("Unable to delete old records.");
				logger.exception(e);
				if (transaction != null)
					transaction.rollback();
				deleted = false;
			}
		}
		logger.exitMethod("updateUnicode", deleted);
		return deleted;
	}
}