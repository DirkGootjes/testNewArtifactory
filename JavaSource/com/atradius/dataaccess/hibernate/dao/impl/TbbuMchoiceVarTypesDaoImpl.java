package com.atradius.dataaccess.hibernate.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.atradius.dataaccess.hibernate.HibernateUtil;
import com.atradius.dataaccess.hibernate.bo.TbbuMchoiceVarTypesBO;
import com.atradius.exception.DataAccessException;
import com.atradius.util.logging.ILogger;
import com.atradius.util.logging.LoggerFactory;

public class TbbuMchoiceVarTypesDaoImpl {
	private static ILogger logger = LoggerFactory
	.getLogger(TbbuMchoiceVarTypesDaoImpl.class);
	
	public List<TbbuMchoiceVarTypesBO> getMchoiceConditionVarTypes(
			HibernateUtil hibernateUtil, String bucdeCode, int choice) throws DataAccessException {
		List<TbbuMchoiceVarTypesBO> tbbuMchoiceVarTypesBOList = new ArrayList<TbbuMchoiceVarTypesBO>();
		logger.enterMethod("getMchoiceConditionVarTypes");
		Session session = null;
		try {
			session = hibernateUtil.getSession();
			Criteria contactPointCrit = session
					.createCriteria(TbbuMchoiceVarTypesBO.class);
			contactPointCrit.add(Restrictions.eq("bucdeCode", bucdeCode));
			contactPointCrit.add(Restrictions.eq("bumctSeq", choice));
			
			tbbuMchoiceVarTypesBOList = (List<TbbuMchoiceVarTypesBO>) contactPointCrit.list();

		} catch (HibernateException e) {

			// e.printStackTrace();
			logger.exception(e);
			throw new DataAccessException("DATABASE_QUERRY_FAILED", e);
		} 
		logger.exitMethod("getMchoiceConditionVarTypes");
		return tbbuMchoiceVarTypesBOList;
	}
}
