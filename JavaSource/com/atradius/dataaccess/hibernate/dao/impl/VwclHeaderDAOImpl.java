package com.atradius.dataaccess.hibernate.dao.impl;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

import com.atradius.dataaccess.hibernate.HibernateUtil;
import com.atradius.dataaccess.hibernate.bo.VwclHeaderBO;
import com.atradius.util.logging.ILogger;
import com.atradius.util.logging.LoggerFactory;

public class VwclHeaderDAOImpl {

	private static ILogger logger = LoggerFactory
			.getLogger(VwclTransLetterVariables2DAOImpl.class);

	private static String QUERY = "SELECT BUCCE_ID,BUCCE_TYP,ORCUR_ORNNN_ID,CUST_NAME_LINE12,ORCUR_BULAE_LANG_CODE"
			+ " FROM VWCL_HEADER WHERE BUCCE_ID= :CASEID ";

	public VwclHeaderBO getHeaderData(HibernateUtil hibernateUtil,
			Integer claimId) {

		VwclHeaderBO vwcl = new VwclHeaderBO();

		Session session = hibernateUtil.getSession();

		SQLQuery query = null;
		query = session.createSQLQuery(QUERY);
		query.setParameter("CASEID", claimId);

		query.addScalar("BUCCE_ID", Hibernate.INTEGER);
		query.addScalar("BUCCE_TYP", Hibernate.STRING);
		query.addScalar("ORCUR_ORNNN_ID", Hibernate.INTEGER);
		query.addScalar("CUST_NAME_LINE12", Hibernate.STRING);
		query.addScalar("ORCUR_BULAE_LANG_CODE", Hibernate.STRING);

		List r = query.list();
		for (int i = 0; i < r.size(); i++) {
			Object[] row = (Object[]) r.get(i);
			vwcl.setCaseId(getInteger(row[0]));
			vwcl.setCaseType(getString(row[1]));
			vwcl.setCustId(getInteger(row[2]));
			vwcl.setCustName(getString(row[3]));
			vwcl.setLang(getString(row[4]));
			break;
		}

		return vwcl;
	}

	private Integer getInteger(Object data) {
		Integer returnInt = null;
		if (data != null) {
			try {
				returnInt = Integer.valueOf(String.valueOf(data));
			} catch (NumberFormatException e) {
			}
		}
		return returnInt;
	}

	private String getString(Object data) {
		String returnStr = null;
		if (data != null) {
			returnStr = String.valueOf(data);
			if (returnStr.equalsIgnoreCase("null"))
				return null;
		}
		return returnStr;
	}
}
