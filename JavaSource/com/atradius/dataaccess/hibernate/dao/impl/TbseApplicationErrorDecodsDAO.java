package com.atradius.dataaccess.hibernate.dao.impl;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

import com.atradius.dataaccess.hibernate.HibernateUtil;
import com.atradius.util.logging.ILogger;
import com.atradius.util.logging.LoggerFactory;

public class TbseApplicationErrorDecodsDAO {

	private static ILogger logger = LoggerFactory
			.getLogger(TbseApplicationErrorDecodsDAO.class);

	private static String QUERY = "select ERROR_DESC from tbse_application_error_decods where BULAE_LANG_CODE = 'EN'"
			+ " and ERROR_LITERAL_NAME = :CODE";

	public String getErrorDes(HibernateUtil hibernateUtil, String error) {
		logger.enterMethod("getErrorDes", error);
		String des = null;

		try {
			Session session = hibernateUtil.getSession();
			SQLQuery query = null;
			String err = null;

			if (error.contains("CLTLV")) {
				err = error.substring(error.indexOf("CLTLV"), error.substring(
						error.indexOf("CLTLV")).indexOf(" "));
			} else if (error.contains("BU_")) {
				err = error.substring(error.indexOf("BU_"), error.substring(
						error.indexOf("BU_")).indexOf(" "));
			}

			if (err != null) {
				query = session.createSQLQuery(QUERY);
				query.setParameter("CODE", err);
				query.addScalar("ERROR_DESC", Hibernate.STRING);
				List<String> r = (List<String>) query.list();
				if (r != null && r.size() > 0) {
					des = r.get(0);
				}
				if (des == null) {
					des = "No error description found for error code : " + err;
				}
			}
		} catch (RuntimeException e) {
			logger.exception(e);
			des = error;
		}

		logger.exitMethod("getErrorDes", des);
		return des;
	}
}
