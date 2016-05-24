package com.atradius.dataaccess.hibernate.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.atradius.dataaccess.hibernate.HibernateUtil;
import com.atradius.dataaccess.hibernate.bo.TbbuConditionVarTypesBO;
import com.atradius.exception.DataAccessException;
import com.atradius.util.logging.ILogger;
import com.atradius.util.logging.LoggerFactory;

public class TbbuConditionVarTypesDAOImpl {
	
	private static ILogger logger = LoggerFactory
	.getLogger(TbbuConditionVarTypesDAOImpl.class);
	
	public List<TbbuConditionVarTypesBO> getConditionVarTypes(
			HibernateUtil hibernateUtil, String bucdeCode) throws DataAccessException {
		List<TbbuConditionVarTypesBO> tbbuConditionVarTypesBOList = new ArrayList<TbbuConditionVarTypesBO>();
		logger.enterMethod("getConditionVarTypes");
		Session session = null;
		try {
			session = hibernateUtil.getSession();
			Criteria contactPointCrit = session
					.createCriteria(TbbuConditionVarTypesBO.class);
			contactPointCrit.add(Restrictions.eq("bucdeCode", bucdeCode));
			
			tbbuConditionVarTypesBOList = (List<TbbuConditionVarTypesBO>) contactPointCrit.list();

		} catch (HibernateException e) {

			// e.printStackTrace();
			logger.exception(e);
			throw new DataAccessException("DATABASE_QUERRY_FAILED", e);
		} 
		logger.exitMethod("getConditionVarTypes");
		return tbbuConditionVarTypesBOList;
	}
}
