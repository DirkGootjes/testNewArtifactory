package com.atradius.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.atradius.dataaccess.hibernate.HibernateUtil;
import com.atradius.dataaccess.hibernate.bo.TbdoTextDescriptionsTempBO;
import com.atradius.dataaccess.hibernate.dao.impl.TbdoTextDescriptionsTempDAOImpl;
import com.atradius.sessiondata.ApplicationConstants;
import com.atradius.sessiondata.UserDataObject;
import com.atradius.util.logging.ILogger;
import com.atradius.util.logging.LoggerFactory;

/*********************************************************************/
/*                           FILE HEADER                             */
/*********************************************************************/
/*                                                                   */
/*  FileName: 		MaintainOPDTextDefController.java             	  	     */
/*  																 */
/*  $Author: INKPAG1 $									             */
/*																	 */
/*  $Revision: 1.3 $										         */
/*  																 */
/*  $Date: 2014/10/13 05:31:20 $                                     */
/*                                                                   */
/*  Description: 	This class fetches the information from the database */
/*                 and navigates the request to MaintainOPDText.jsp  */
/*                                                                   */
/*********************************************************************/
/* Date        Name            Version             Comments          */
/*-------------------------------------------------------------------*/
/* 25/09/2014  INKPAG1      	1.0         Initial version created  */
/* ****************************************************************** */
public class MaintainOPDTextDefController extends Action {

	/**
	 * Logger 
	 */
	private static ILogger logger = LoggerFactory
			.getLogger(MaintainOPDTextDefController.class);

	/**
	 * forward path name in case of Success operation
	 */
	private static final String SUCCESS = "success";

	/** Forward path name in case of Error operation.
	 * 
	 */
	private static final String ERROR = "error";

	/**
	 * Does the initial processing before going to the MaintainOPDText.jsp
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
		logger.enterMethod("MaintainOPDTextDefController execute started");

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

		String changeNum=(String)session.getAttribute("changeNum");
		String opdTag=(String)session.getAttribute("opdTag");
		String orgName=(String)session.getAttribute("orgName");
		String langCd=(String)session.getAttribute("langCd");
		
		TbdoTextDescriptionsTempDAOImpl dao = new TbdoTextDescriptionsTempDAOImpl();
		
		
		TbdoTextDescriptionsTempBO tbdoTextDescBO = new TbdoTextDescriptionsTempBO();
		tbdoTextDescBO.setChangeNumber(changeNum);
		tbdoTextDescBO.setOpdTagName(opdTag);
		tbdoTextDescBO.setOrganization(orgName);
		tbdoTextDescBO.setLangCd(langCd);
		
		tbdoTextDescBO = dao.getOPDTagDetails(hibernateUtil, tbdoTextDescBO);

		if (tbdoTextDescBO != null) {
			session.setAttribute(ApplicationConstants.TBDOTEXTDESCBO_POLT, tbdoTextDescBO);
			request.setAttribute(ApplicationConstants.ATTR_OPD_TAG_POLT, tbdoTextDescBO.getOpdTagName());
			request.setAttribute(ApplicationConstants.OPD_TAG_DESC_POLT, tbdoTextDescBO.getCurrrentTagDesc());
			request.setAttribute(ApplicationConstants.ORGNIZATION_POLT, tbdoTextDescBO.getOrganization());
			request.setAttribute(ApplicationConstants.LANG_CD_POLT, tbdoTextDescBO.getLangCd());
			request.setAttribute(ApplicationConstants.STATUS_POLT, tbdoTextDescBO.getStatus());
			request.setAttribute(ApplicationConstants.OPD_TEXT_POLT, tbdoTextDescBO.getTagVarText());
			request.setAttribute(ApplicationConstants.ATTR_FORM_MODE, session.getAttribute(ApplicationConstants.ATTR_FORM_MODE));
		} else {
			request.setAttribute(ApplicationConstants.ERRMSG,
					ApplicationConstants.ERROR_RECORD_NOT_EXIST_MSG);
			return mapping.findForward(ERROR);
		}
		logger.exitMethod("MaintainOPDTextDefController execute Completed");
		return mapping.findForward(SUCCESS);
	}
}
