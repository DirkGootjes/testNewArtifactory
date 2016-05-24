package com.atradius.dataaccess.hibernate.dao.impl;

import java.sql.Timestamp;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.atradius.dataaccess.hibernate.HibernateUtil;
import com.atradius.dataaccess.hibernate.bo.TbclClaimsLettersTempBO;
import com.atradius.util.logging.ILogger;
import com.atradius.util.logging.LoggerFactory;

public class TbclClaimsLettersTempDAOImpl {

	
	private static ILogger logger = LoggerFactory
	.getLogger(TbclClaimsLettersTempDAOImpl.class);
	
	
	private static String query1 = "SELECT DES FROM TBCL_CLAIMS_LETTER_TYPES WHERE LETTER_TYP= :TYPE ";
	private static String query2 = "SELECT RV_MEANING FROM CG_REF_CODES WHERE RV_DOMAIN = 'CLAIMS PRINT TYPE' AND RV_LOW_VALUE= :PRINTTYPE ";
	
	public TbclClaimsLettersTempBO getClaimsLetterData(HibernateUtil hibernateUtil, Integer claimId, String letter, Timestamp prnDate) {
		TbclClaimsLettersTempBO tbclClaimsLettersTempBO = new TbclClaimsLettersTempBO();
		try{
			Session session = hibernateUtil.getSession();
			Criteria crit = session.createCriteria(TbclClaimsLettersTempBO.class);
			crit.add(Restrictions.eq("tbclClaimsLetterCompositeBO.bucceId", claimId));
			crit.add(Restrictions.eq("tbclClaimsLetterCompositeBO.clcltLetterTyp", letter));
			crit.add(Restrictions.eq("tbclClaimsLetterCompositeBO.printDate", prnDate));
			
			tbclClaimsLettersTempBO = (TbclClaimsLettersTempBO)crit.uniqueResult();
			
		}catch(Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		return tbclClaimsLettersTempBO;
	}
	
	public String getLetterDes(HibernateUtil hibernateUtil, String letter) {
		
		String des = null;
		Session session = hibernateUtil.getSession();
		
		SQLQuery query = null;
		query = session.createSQLQuery(query1);
		query.setParameter("TYPE", letter);
		query.addScalar("DES", Hibernate.STRING);
		//query.addScalar("LETTER_TYP", Hibernate.STRING);
		
		Object r = query.uniqueResult();
		if(r != null) {
			des = String.valueOf(r);
		}
		return des;
	}
		
	public String getPrintTypeDes(HibernateUtil hibernateUtil, String printType) {
		
		String des = null;
		Session session = hibernateUtil.getSession();
		
		SQLQuery query = null;
		query = session.createSQLQuery(query2);
		query.setParameter("PRINTTYPE", printType);
		query.addScalar("RV_MEANING", Hibernate.STRING);
		//query.addScalar("RV_LOW_VALUE", Hibernate.STRING);
		
		Object r = query.uniqueResult();
		if(r != null) {
			des = String.valueOf(r);
		}
		return des;
	}	
}
