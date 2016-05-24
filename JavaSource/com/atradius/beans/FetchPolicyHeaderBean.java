package com.atradius.beans;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.atradius.dataaccess.hibernate.HibernateUtil;
import com.atradius.dataaccess.hibernate.bo.VwpoHeaderPoliciesBO;
import com.atradius.dataaccess.hibernate.dao.impl.VwpoHeaderPoliciesViewDAOImpl;
import com.atradius.exception.DataAccessException;
import com.atradius.sessiondata.ApplicationConstants;
import com.atradius.util.logging.ILogger;
import com.atradius.util.logging.LoggerFactory;

public class FetchPolicyHeaderBean {

	private static ILogger logger = LoggerFactory.getLogger(FetchPolicyHeaderBean.class);

	public VwpoHeaderPoliciesBO getPolicyInfo(final HibernateUtil hibernateUtil,
			final HttpSession session, final HttpServletRequest request, final String policyIDAttrName)
			throws DataAccessException {
		logger.enterMethod("getPolicyInfo");

		VwpoHeaderPoliciesBO policyBO = new VwpoHeaderPoliciesBO();

		VwpoHeaderPoliciesViewDAOImpl policyDAO = new VwpoHeaderPoliciesViewDAOImpl();
		String policyId = (String) session.getAttribute(policyIDAttrName);

		policyBO = policyDAO.gePolicyInfo(hibernateUtil, policyId);

		if (policyBO != null) {
			request.setAttribute(ApplicationConstants.ATTR_POLICY_DETAILS, policyBO);
		}

		logger.exitMethod("getPolicyInfo");
		return policyBO;
	}

}
