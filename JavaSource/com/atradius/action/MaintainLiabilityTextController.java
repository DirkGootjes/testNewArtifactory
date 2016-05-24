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

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import com.atradius.dataaccess.hibernate.bo.VwclHeaderBO;
import com.atradius.dataaccess.hibernate.dao.AssessCalcLiabilitiesDAO;
import com.atradius.dataaccess.hibernate.dao.impl.AssessCalcLiabilitiesDAOImpl;
import com.atradius.dataaccess.hibernate.dao.impl.VwclHeaderDAOImpl;
import com.atradius.sessiondata.ApplicationConstants;
import com.atradius.sessiondata.UserDataObject;
import com.atradius.util.logging.ILogger;
import com.atradius.util.logging.LoggerFactory;

public class MaintainLiabilityTextController extends Action {
	private static ILogger logger = LoggerFactory
			.getLogger(MaintainLiabilityTextController.class);

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

		logger.enterMethod("MaintainLiabilityTextController.execute");
		HttpSession session = request.getSession();
		List<TbclAssessCalcLiabilitiesBO> tbclAssessLiabilitiesBOList = new ArrayList<TbclAssessCalcLiabilitiesBO>();
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
		AssessCalcLiabilitiesDAO assessLiabilitiesDao = new AssessCalcLiabilitiesDAOImpl();
		Integer bucceId = Integer.valueOf((String) session
				.getAttribute(ApplicationConstants.REQUEST_BUCCE_ID));
		String assessType = (String) session
				.getAttribute(ApplicationConstants.ASSESS_TYPE);
		String runDate = (String) session
				.getAttribute(ApplicationConstants.RUN_DATE);
		DateFormat formt = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SSS");
		if (!runDate.contains(" "))
			formt = new SimpleDateFormat("dd/MM/yyyy");
		else {
			if (!runDate.contains("."))
				formt = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		}

		Date rundt = null;
		Timestamp timestamp = null;
		try {
			rundt = formt.parse(runDate);
			timestamp = new java.sql.Timestamp(rundt.getTime());

		} catch (ParseException e) {
			e.printStackTrace();
		}
		if (bucceId == null) {
			session.setAttribute(ApplicationConstants.ERRMSG,
					ApplicationConstants.ERROR_NO_BUCCECD);
			return mapping.findForward(ERROR);
		} else {

			// collect header information.
			VwclHeaderDAOImpl headerView = new VwclHeaderDAOImpl();
			VwclHeaderBO header = headerView.getHeaderData(hibernateUtil,
					Integer.valueOf(bucceId));
			session.setAttribute("header", header);

			LinkedHashMap<String, TbclAssessCalcLiabilitiesBO> table = (LinkedHashMap<String, TbclAssessCalcLiabilitiesBO>) session
					.getAttribute(ApplicationConstants.ATTR_CASE_ASSESSMENT_DETAIL_TABLE);

			if (table == null) {
				logger.info("Map does not exist in Session for bucceId ["
						+ bucceId + "] run date [" + timestamp
						+ "] assessType [" + assessType + "]");

				tbclAssessLiabilitiesBOList = assessLiabilitiesDao
						.getDebtAssessmentTableDetails(hibernateUtil, bucceId,
								assessType, timestamp);
				if (tbclAssessLiabilitiesBOList != null
						&& tbclAssessLiabilitiesBOList.size() != 0) {
					// delete the temporary records
					logger.info("trying to delete records for [" + bucceId
							+ "] run date [" + timestamp + "] assessType ["
							+ assessType + "]");
					assessLiabilitiesDao.delete(hibernateUtil, bucceId,
							assessType, timestamp);

					table = getBeansByCode(tbclAssessLiabilitiesBOList);

					// save records in memory
					session
							.setAttribute(
									ApplicationConstants.ATTR_CASE_ASSESSMENT_DETAIL_TABLE,
									table);

				} else {
					request.setAttribute(ApplicationConstants.ERRMSG,
							ApplicationConstants.ERROR_NO_DETAILS);
					return mapping.findForward(ERROR);
				}
			}
		}

		logger.exitMethod("MaintainLiabilityTextController.execute");
		return mapping.findForward(SUCCESS);
	}

	private LinkedHashMap<String, TbclAssessCalcLiabilitiesBO> getBeansByCode(
			List<TbclAssessCalcLiabilitiesBO> tbclAssessLiabilitiesBOList) {
		Map<String, TbclAssessCalcLiabilitiesBO> table = new LinkedHashMap<String, TbclAssessCalcLiabilitiesBO>();
		for (int i = 0; i < tbclAssessLiabilitiesBOList.size(); i++) {
			TbclAssessCalcLiabilitiesBO bean = tbclAssessLiabilitiesBOList
					.get(i);
			table.put(bean.getAssessDetails().getClclcCode(), bean);
		}
		return (LinkedHashMap<String, TbclAssessCalcLiabilitiesBO>) table;
	}
}
