package com.atradius.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.atradius.beans.FetchPolicyHeaderBean;
import com.atradius.dataaccess.hibernate.HibernateUtil;
import com.atradius.dataaccess.hibernate.bo.TbpoPrintedDocumentsBO;
import com.atradius.dataaccess.hibernate.bo.VwpoHeaderPoliciesBO;
import com.atradius.dataaccess.hibernate.dao.impl.TbpoPrintedDocumensDAOImpl;
import com.atradius.sessiondata.ApplicationConstants;
import com.atradius.sessiondata.UserDataObject;
import com.atradius.util.logging.ILogger;
import com.atradius.util.logging.LoggerFactory;
import com.atradius.utils.CommonUtil;

/**																	*/
/*********************************************************************/
/*                           FILE HEADER                             */
/*********************************************************************/
/*                                                                   */
/*  FileName: 		PolicyDocFreeTextController.java             	  	     */
/*  																 */
/*  $Author: INKPAG1 $									             */
/*																	 */
/*  $Revision: 1.4 $										         */
/*  																 */
/*  $Date: 2014/10/13 05:31:20 $                                     */
/*                                                                   */
/*  Description: 	This class fetches the information from the database */
/*                 and navigates the request to MaintainOPDText.jsp  */
/*                                                                   */
/** ****************************************************************** */
/* Date Name Version Comments */
/*-------------------------------------------------------------------*/
/* 25/09/2014 INKPAG1 1.0 Initial version created */
/* ****************************************************************** 
 */

public class PolicyDocFreeTextController extends Action {

	/**
	 * Logger
	 */
	private static ILogger logger = LoggerFactory
			.getLogger(PolicyDocFreeTextController.class);

	/**
	 * forward path name in case of Success operation
	 */
	private static final String SUCCESS = "success";

	/**
	 * forward path name in case of Error operation
	 */
	private static final String ERROR = "error";

	/**
	 * Does the initial processing before going to the MaintainPolicyDocFreeText.jsp
	 * 
	 * @param mapping
	 *            the ActionMapping. *
	 * @param form
	 *            the ActionForm to be processed. *
	 * @param request
	 *            the HTTP request to be processed. *
	 * @param response
	 *            the HTTP response to be processed. *
	 * @return an int value based on which the request is forwarded to the
	 *         appropriate page.
	 * @exception Exception
	 *                if there was an error in processing the request.
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		logger.enterMethod("PolicyDocFreeTextController execute started");

		HttpSession session = request.getSession();
		UserDataObject userDataObject = (UserDataObject) session
				.getAttribute(ApplicationConstants.USER_DATA_BEAN);

		if (userDataObject == null) {
			request.setAttribute(ApplicationConstants.ERRMSG,
					ApplicationConstants.ERROR_INVALID_SESSION);
			return mapping.findForward(ERROR);
		}

		HibernateUtil hibernateUtil = (HibernateUtil) session
				.getAttribute(ApplicationConstants.HIBERNATE_UTIL);
		if (hibernateUtil == null) {
			request.setAttribute(ApplicationConstants.ERRMSG,
					ApplicationConstants.ERROR_INVALID_SESSION);
			return mapping.findForward(ERROR);
		}

		VwpoHeaderPoliciesBO policyBO = new FetchPolicyHeaderBean().getPolicyInfo(
				hibernateUtil, session, request,ApplicationConstants.ATTR_POLICYID_PFT);

		if (policyBO != null) {
			
			String policyId = (String)session.getAttribute(ApplicationConstants.ATTR_POLICYID_PFT);
			String print_date = (String)session.getAttribute(ApplicationConstants.ATTR_PDATE_PFT);
			String grp_cd = (String)session.getAttribute(ApplicationConstants.ATTR_GCD_PFT);
			String grp_func_desc = (String)session.getAttribute(ApplicationConstants.ATTR_GFD_PFT);
						
			TbpoPrintedDocumensDAOImpl tbpoPrintedDocDAO = new TbpoPrintedDocumensDAOImpl();
			
			TbpoPrintedDocumentsBO tbpoPrintedDocBO = new TbpoPrintedDocumentsBO();
			tbpoPrintedDocBO.setPolicyId(policyId);			
			tbpoPrintedDocBO.setPrintDate(CommonUtil.convertStringToDate(print_date,
					ApplicationConstants.DATE_FORMAT_1));
			tbpoPrintedDocBO.setGroupCode(grp_cd);
			tbpoPrintedDocBO.setGroupCodeFuncDesc(grp_func_desc);			
			
			tbpoPrintedDocBO = tbpoPrintedDocDAO.getDetails(tbpoPrintedDocBO,
					hibernateUtil);
			if (tbpoPrintedDocBO == null) {
				request.setAttribute(ApplicationConstants.ERRMSG,
						ApplicationConstants.ERROR_RECORD_NOT_EXIST_MSG);
				return mapping.findForward(ERROR);
			}
			session.setAttribute(ApplicationConstants.TBPOPRINTEDBO_PFT, tbpoPrintedDocBO);
			request.setAttribute(ApplicationConstants.FREE_TXT_PFT, tbpoPrintedDocBO.getDescFreeText());
			request.setAttribute(ApplicationConstants.ATTR_FORM_MODE, session.getAttribute(ApplicationConstants.ATTR_FORM_MODE));

		}
		logger.exitMethod("PolicyDocFreeTextController execute Completed");
		return mapping.findForward(SUCCESS);
	}
}
