/*********************************************************************/
/*                           FILE HEADER                             */
/*********************************************************************/
/*                                                                   */
/*  FileName	:MaintainFreetextController.java             	 */
/*  																 */
/*  $Author		: INATIW1 $									     	 */
/*																	 */
/*  $Revision	: 1.0  $										     */
/*  																 */
/*  $Date		: 18/10/2013 12:02:48 $                              */
/*                                                                   */
/*  Description: 	This action class This action class performs the */
/* 					initial loading of free Text details     */
/*********************************************************************/
/* Date        Name            Version             Comments          */
/*-------------------------------------------------------------------*/
/* 16/10/2013  INATIW1     		1.0         Initial version created  */
/** ******************************************************************/
/*-------------------------------------------------------------------*/
/* 22/11/2013  INNBHA1      	2.0      Updated for Review Comments */
/** ****************************************************************** */
// package
package com.atradius.action;

import java.util.Iterator;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.atradius.dataaccess.hibernate.HibernateUtil;
import com.atradius.dataaccess.hibernate.bo.TbbuConditionTypesBO;
import com.atradius.dataaccess.hibernate.bo.TbbuConditionVariablesUniTempBO;
import com.atradius.dataaccess.hibernate.dao.TbbuConditionTypesDAO;
import com.atradius.dataaccess.hibernate.dao.impl.TbbuConditionTypesDAOImpl;
import com.atradius.dataaccess.hibernate.dao.impl.TbbuConditionVariablesUniTempDAOImpl;
import com.atradius.exception.DataAccessException;
import com.atradius.sessiondata.ApplicationConstants;
import com.atradius.sessiondata.UserDataObject;
import com.atradius.util.config.ApplicationConfig;
import com.atradius.util.logging.ILogger;
import com.atradius.util.logging.LoggerFactory;

public class MaintainFreetextController extends Action {

	private static ILogger logger = LoggerFactory
			.getLogger(MaintainFreetextController.class);

	private static String SUCCESS = "success";

	private static String ERROR = "error";

	/**
	 * Does the initial processing before going to the MaintainTextDetails.jsp
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
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		logger.enterMethod("MaintainFreetextController");
		TbbuConditionTypesBO conditionTypesBO = new TbbuConditionTypesBO();
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

		ApplicationConfig config = ApplicationConfig.getInstance();
		Properties rtl_languages = config.getProperties("rtl_languages");

		String rtl_lang_str = "";
		if (rtl_languages != null && !rtl_languages.isEmpty()) {
			Iterator keys = rtl_languages.keySet().iterator();
			while (keys.hasNext()) {
				String key = (String) keys.next();
				if (((String) rtl_languages.get(key)).equalsIgnoreCase("RTL")) {
					rtl_lang_str = rtl_lang_str + key + ",";
				}
			}
			if (rtl_lang_str.endsWith(","))
				rtl_lang_str = rtl_lang_str.substring(0,
						rtl_lang_str.length() - 1);
		}
		session.setAttribute(ApplicationConstants.ATTR_RIGHT_TO_LEFT_LANGS,
				rtl_lang_str);

		try {
			// check session
			Integer bucltId = Integer.valueOf((String) session
					.getAttribute(ApplicationConstants.REQUEST_BUCLT_ID));
			String bucdeCd = (String) session
					.getAttribute(ApplicationConstants.REQUEST_BUCDE_CODE);
			String applyAmountType = (String) session
					.getAttribute(ApplicationConstants.REQUEST_APPLY_AMT_TYP);
			Integer bucyeSequence = null;
			Integer bumctSequence = null;
			Integer bumctOrder = null;
			if (session.getAttribute(ApplicationConstants.REQUEST_BUCYE_SEQ) == null
					|| session.getAttribute(
							ApplicationConstants.REQUEST_BUCYE_SEQ).equals("")) {
				bucyeSequence = null;
			} else {
				bucyeSequence = Integer.valueOf((String) session
						.getAttribute(ApplicationConstants.REQUEST_BUCYE_SEQ));
			}
			if (session.getAttribute(ApplicationConstants.REQUEST_BUMCT_SEQ) == null
					|| session.getAttribute(
							ApplicationConstants.REQUEST_BUMCT_SEQ).equals("")) {
				bumctSequence = null;
			} else {
				bumctSequence = Integer.valueOf((String) session
						.getAttribute(ApplicationConstants.REQUEST_BUMCT_SEQ));
				bumctSequence = bumctSequence.intValue();
			}

			if (session.getAttribute(ApplicationConstants.REQUEST_BUMCT_ORDER) == null
					|| session.getAttribute(
							ApplicationConstants.REQUEST_BUMCT_ORDER)
							.equals("")) {
				bumctOrder = null;
			} else {
				bumctOrder = Integer
						.valueOf((String) session
								.getAttribute(ApplicationConstants.REQUEST_BUMCT_ORDER));
			}

			if (bucltId == null) {	
				session.setAttribute(ApplicationConstants.ERRMSG,
						ApplicationConstants.ERROR_NO_BUCLTID);		
			} else if (bucdeCd == null) {
				session.setAttribute(ApplicationConstants.ERRMSG,
						ApplicationConstants.ERROR_NO_BUCDECODE);
			} else {
				TbbuConditionTypesDAO tbbuConditionTypesDAOImpl = new TbbuConditionTypesDAOImpl();

				conditionTypesBO = tbbuConditionTypesDAOImpl
						.getConditionCodeDetails(hibernateUtil, bucdeCd);
			}

			if (conditionTypesBO != null) {

				request.setAttribute(ApplicationConstants.ATTR_BUCDE_CODE,
						conditionTypesBO.getCode());
				if (bucltId != null) {
					request.setAttribute(ApplicationConstants.ATTR_BUCLT_ID,
							bucltId);
				}
				request.setAttribute(ApplicationConstants.ATTR_BUCYE_SEQ,
						bucyeSequence);
				request.setAttribute(ApplicationConstants.ATTR_APPLY_AMT_TYP,
						applyAmountType);
				request.setAttribute(ApplicationConstants.ATTR_BUMCT_SEQ,
						bumctSequence);
				request.setAttribute(ApplicationConstants.ATTR_BUMCT_ORDER,
						bumctOrder);
				// fetch all the data in one go
				TbbuConditionVariablesUniTempBO conditionVariablesUniTempBO = new TbbuConditionVariablesUniTempBO();

				if (bucyeSequence != null && bumctSequence != null) {					
						conditionVariablesUniTempBO = new TbbuConditionVariablesUniTempDAOImpl()
								.getTextTranslationsTemp(hibernateUtil,
										bucltId, applyAmountType,
										bucyeSequence, bucdeCd, bumctSequence,
										bumctOrder);
					
					
				} else if (bucyeSequence != null) {
					
						conditionVariablesUniTempBO = new TbbuConditionVariablesUniTempDAOImpl()
								.getTextTranslationsTemp(hibernateUtil,
										bucltId, applyAmountType,
										bucyeSequence, bucdeCd);
					

				} else {					
						conditionVariablesUniTempBO = new TbbuConditionVariablesUniTempDAOImpl()
								.getTextTranslationsTemp(hibernateUtil,
										bucltId, applyAmountType, bucdeCd,
										bumctSequence, bumctOrder);
				}
				
				if (conditionVariablesUniTempBO != null){
					session.setAttribute(
							ApplicationConstants.ATTR_FREETEXT_UNI,
							conditionVariablesUniTempBO);
				}
				else{
					session.setAttribute(
							ApplicationConstants.ATTR_FREETEXT_UNI,
							null);
				}

			} else {
				// show error ERROR_INVALID_CONDITION_CODE
				session.setAttribute(ApplicationConstants.ERRMSG,
						ApplicationConstants.ERROR_INVALID_CONDITION_CODE);
			}
		} catch (DataAccessException e) {
			session.setAttribute(ApplicationConstants.ERRMSG,
					ApplicationConstants.ERROR_DATABASE);
			logger.exception(e);
		}

		logger.exitMethod("execute method of MaintainFreetextController");
		return mapping.findForward(SUCCESS);
	}
}
