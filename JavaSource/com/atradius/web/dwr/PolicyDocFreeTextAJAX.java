package com.atradius.web.dwr;

import javax.servlet.http.HttpSession;

import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;

import com.atradius.beans.PolicyDocFreeTextBean;
import com.atradius.dataaccess.hibernate.HibernateUtil;
import com.atradius.dataaccess.hibernate.bo.TbpoPrintedDocumentsBO;
import com.atradius.dataaccess.hibernate.dao.impl.TbpoPrintedDocumensDAOImpl;
import com.atradius.exception.DataAccessException;
import com.atradius.sessiondata.ApplicationConstants;
import com.atradius.sessiondata.UserDataObject;
import com.atradius.util.logging.ILogger;
import com.atradius.util.logging.LoggerFactory;

/*********************************************************************/
/*                           FILE HEADER                             */
/*********************************************************************/
/*                                                                   */
/*  FileName: 		PolicyDocFreeTextAJAX.java               	     */
/*  																 */
/*  $Author: INKPAG1 $									             */
/*																	 */
/*  $Revision: 1.3 $										         */
/*  																 */
/*  $Date: 2014/10/13 05:32:12 $                                     */
/*                                                                   */
/*  Description: 	This java class is used for  AJAX methods        */
/*				  	for Policy document free text                    */
/*********************************************************************/
/* Date        Name            Version             Comments          */
/*-------------------------------------------------------------------*/
/* 09/25/2014  INKPAG1      	1.0         Initial version created  */
/** ****************************************************************** */
public class PolicyDocFreeTextAJAX {

	/**
	 * Logger
	 */
	private static ILogger logger = LoggerFactory.getLogger(PolicyDocFreeTextAJAX.class);

	/**
	 * SAves teh policy free text into the temp table.
	 * 
	 * @param descFreeText
	 * @return
	 */
	public PolicyDocFreeTextBean savePolicyDocFreeTextDetails(final String descFreeText) {
		logger.enterMethod("savePolicyDocFreeTextDetails", descFreeText);
		PolicyDocFreeTextBean policyBean = new PolicyDocFreeTextBean();
		String errorMsg = null;
		try {
			WebContext ctx = WebContextFactory.get();
			HttpSession session = ctx.getSession();

			UserDataObject userDataObject = (UserDataObject) session
					.getAttribute(ApplicationConstants.USER_DATA_BEAN);
			if (userDataObject == null) {
				errorMsg = ApplicationConstants.ERROR_INVALID_SESSION;
				policyBean.setErrorMsg(errorMsg);
				logger.error(errorMsg);
				return policyBean;
			}
			HibernateUtil hibernateUtil = (HibernateUtil) session
					.getAttribute(ApplicationConstants.HIBERNATE_UTIL);
			if (hibernateUtil == null) {
				errorMsg = ApplicationConstants.ERROR_INVALID_SESSION;
				policyBean.setErrorMsg(errorMsg);
				return policyBean;
			}
			TbpoPrintedDocumensDAOImpl dao = new TbpoPrintedDocumensDAOImpl();
			TbpoPrintedDocumentsBO tbpoPrintedDocBO = (TbpoPrintedDocumentsBO) session
					.getAttribute(ApplicationConstants.TBPOPRINTEDBO_PFT);
			tbpoPrintedDocBO.setDescFreeText(descFreeText);

			boolean isSaveSuccessFul = false;
			try {
				isSaveSuccessFul = dao.saveFreeText(tbpoPrintedDocBO, hibernateUtil);
			} catch (DataAccessException e) {
				policyBean.setSuccess(false);
				policyBean.setErrorMsg(ApplicationConstants.ERROR_DATABASE);
				logger.error(ApplicationConstants.ERROR_DATABASE + ":: " + e);
				return policyBean;
			}
			if (isSaveSuccessFul) {
				policyBean.setDescFreeText(descFreeText);
				policyBean.setSuccess(Boolean.TRUE);
				policyBean
						.setSuccessMessage(ApplicationConstants.POLICY_FREE_TEXT_SUCCESS_MSG);
			} else {
				policyBean.setErrorMsg(ApplicationConstants.ERROR_RECORD_NOT_EXIST_MSG);
			}
		} catch (Exception e) {
			policyBean.setSuccess(false);
			policyBean.setErrorMsg(ApplicationConstants.ERROR_DATABASE);
			logger.error(ApplicationConstants.ERROR_DATABASE + ":: " + e);
			return policyBean;
		}
		logger.exitMethod("savePolicyDocFreeTextDetails");
		return policyBean;
	}
}
