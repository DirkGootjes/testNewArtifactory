/*********************************************************************/
/*                           FILE HEADER                             */
/*********************************************************************/
/*                                                                   */
/*  FileName:	MaintainLiabilityTextController.java			     */
/*  																 */
/*  Author: INVSAR1										             */
/*																	 */
/*  Date: 26 Aug 2014				                                 */
/*                                                                   */
/*  Description: 													 */
/*********************************************************************/
/* Date        Name            Version             Comments          */
/*-------------------------------------------------------------------*/
/*26 Aug 2014  	INVSAR1      	1.0         Initial version created  */
/*********************************************************************/

package com.atradius.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.atradius.dataaccess.hibernate.HibernateUtil;
import com.atradius.dataaccess.hibernate.bo.TbclAssessCalcLiabilitiesBO;
import com.atradius.dataaccess.hibernate.bo.TbclClaimsCalcLiabilitiesBO;
import com.atradius.dataaccess.hibernate.dao.ClaimsCalcLiabilitiesDAO;
import com.atradius.dataaccess.hibernate.dao.impl.ClaimsCalcLiabilitiesDAOImpl;
import com.atradius.sessiondata.ApplicationConstants;
import com.atradius.sessiondata.UserDataObject;
import com.atradius.util.logging.ILogger;
import com.atradius.util.logging.LoggerFactory;

public class MaintainCLMPayementsController extends Action {
	private static ILogger logger = LoggerFactory
			.getLogger(MaintainCLMPayementsController.class);

	private static String SUCCESS = "success";

	private static String ERROR = "error";

	/**
	 * Does the initial processing before going to the
	 * MaintainClaimSectionVariables.jsp
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

		logger.enterMethod("MaintainCLMPayementsController.execute");
		HttpSession session = request.getSession();
		List<TbclClaimsCalcLiabilitiesBO> tbclClaimsCalcLiabilitiesBOList = new ArrayList<TbclClaimsCalcLiabilitiesBO>();
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
		// TODO call dao classes and pass the data to JSP

		HashMap<String, TbclClaimsCalcLiabilitiesBO> map = (HashMap<String, TbclClaimsCalcLiabilitiesBO>) session
				.getAttribute(ApplicationConstants.ATTR_CLAIM_PAYMENT_TABLE);

		if (map == null) {

			Long paymentId = Long.valueOf((String) session
					.getAttribute(ApplicationConstants.PAYMENT_ID));

			logger.info("Map does not exist in Session for paymentId ["
					+ paymentId + "]");
			ClaimsCalcLiabilitiesDAO daoService = new ClaimsCalcLiabilitiesDAOImpl();

			tbclClaimsCalcLiabilitiesBOList = daoService
					.getClaimsCalculationDetails(hibernateUtil, paymentId);

			if (tbclClaimsCalcLiabilitiesBOList != null
					&& tbclClaimsCalcLiabilitiesBOList.size() != 0) {
				session.setAttribute(ApplicationConstants.ATTR_CLM_PAYMENTS,
						tbclClaimsCalcLiabilitiesBOList);
				logger.info("trying to delete records for paymentId ["
						+ paymentId + "] ");
				daoService.delete(hibernateUtil, paymentId);
				map = getTable(tbclClaimsCalcLiabilitiesBOList);
				session.setAttribute(
						ApplicationConstants.ATTR_CLAIM_PAYMENT_TABLE, map);
			} else {
				request.setAttribute(ApplicationConstants.ERRMSG,
						ApplicationConstants.ERROR_NO_DETAILS);
				return mapping.findForward(ERROR);
			}
		}
		logger.exitMethod("MaintainCLMPayementsController.execute");
		return mapping.findForward(SUCCESS);
	}

	private HashMap<String, TbclClaimsCalcLiabilitiesBO> getTable(
			List<TbclClaimsCalcLiabilitiesBO> tbclClaimsCalcLiabilitiesBOList) {
		HashMap<String, TbclClaimsCalcLiabilitiesBO> map = new HashMap<String, TbclClaimsCalcLiabilitiesBO>();
		for (int i = 0; i < tbclClaimsCalcLiabilitiesBOList.size(); i++) {
			TbclClaimsCalcLiabilitiesBO bean = tbclClaimsCalcLiabilitiesBOList
					.get(i);
			String key = bean.getClcptId() + "_" + bean.getClclcCode();
			map.put(key, bean);
		}
		return map;
	}
}
