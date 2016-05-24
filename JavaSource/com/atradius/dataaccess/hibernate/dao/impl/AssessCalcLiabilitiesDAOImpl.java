package com.atradius.dataaccess.hibernate.dao.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.atradius.dataaccess.hibernate.HibernateUtil;
import com.atradius.dataaccess.hibernate.bo.TbclAssessCalcLiabilitiesBO;
import com.atradius.dataaccess.hibernate.dao.AssessCalcLiabilitiesDAO;
import com.atradius.util.logging.ILogger;
import com.atradius.util.logging.LoggerFactory;

public class AssessCalcLiabilitiesDAOImpl implements AssessCalcLiabilitiesDAO {
	private static ILogger logger = LoggerFactory
			.getLogger(AssessCalcLiabilitiesDAOImpl.class);

	private static String QUERY = "DELETE FROM TBCL_ASSESS_CALC_LIAB_UNI_TEMP WHERE BUCCE_ID = :CASEID AND RUN_DAT =:RUNDATE AND ASSESS_TYP=:ASSESSTYPE ";

	public List<TbclAssessCalcLiabilitiesBO> getDebtAssessmentTableDetails(
			HibernateUtil hibernateUtil, Integer bucceId, String assessType,
			Date runDate) {
		logger.enterMethod("getDebtAssessmentTableDetails", bucceId, runDate,
				assessType, hibernateUtil.getUserName());
		List<TbclAssessCalcLiabilitiesBO> tbclAssessCalcLiabilitiesBOList = null;
		try {
			Session session = hibernateUtil.getSession();
			Criteria iCriteria = session.createCriteria(
					TbclAssessCalcLiabilitiesBO.class).add(
					Restrictions.eq("assessDetails.bucceId", bucceId)).add(
					Restrictions.eq("assessDetails.assessType", assessType))
					.add(Restrictions.eq("assessDetails.runDate", runDate));
			iCriteria.addOrder(Order.asc("assessDetails.clclcCode"));
			tbclAssessCalcLiabilitiesBOList = iCriteria.list();
			logger.info("for bucceId [" + bucceId + "] & rundate [" + runDate
					+ "] the tbclAssessCalcLiabilitiesBOList.size() is ["
					+ tbclAssessCalcLiabilitiesBOList.size() + "]");
			return tbclAssessCalcLiabilitiesBOList;
		} catch (HibernateException e) {
			logger
					.error("Unable to fetch records. in getDebtAssessmentTableDetails ");
			logger.exception(e);
		} finally {
			logger.exitMethod("getClaimsCalculationDetails",
					tbclAssessCalcLiabilitiesBOList.size());
		}
		return tbclAssessCalcLiabilitiesBOList;
	}

	public boolean updateUnicode(HibernateUtil hibernateUtil,
			List<TbclAssessCalcLiabilitiesBO> beanList) {
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

	public boolean delete(HibernateUtil hibernateUtil, Integer bucceId,
			String assessType, Date runDate) {
		logger.enterMethod("delete", bucceId, runDate, assessType,
				hibernateUtil.getUserName());
		boolean deleted = false;
		List<TbclAssessCalcLiabilitiesBO> tbclAssessCalcLiabilitiesBOList = getDebtAssessmentTableDetails(
				hibernateUtil, bucceId, assessType, runDate);
		if (tbclAssessCalcLiabilitiesBOList.size() > 0) {
			Session session = hibernateUtil.getSession();
			Transaction transaction = null;
			try {
				transaction = session.beginTransaction();
				// save the beans here
				for (int i = 0; i < tbclAssessCalcLiabilitiesBOList.size(); i++)
					session.evict(tbclAssessCalcLiabilitiesBOList.get(i));

				SQLQuery query = session.createSQLQuery(QUERY);
				query.setInteger("CASEID", bucceId);
				query.setTimestamp("RUNDATE", runDate);
				query.setString("ASSESSTYPE", assessType);
				int noOfrecordsDeleted = query.executeUpdate();

				transaction.commit();
				logger.info("total records deleted  [" + noOfrecordsDeleted
						+ "] for bucceId [" + bucceId + "] & rundate ["
						+ runDate + "] ");
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