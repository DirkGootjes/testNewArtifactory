/*********************************************************************/
/*                           FILE HEADER                             */
/*********************************************************************/
/*                                                                   */
/*  FileName:	MaintainLiabilityTextAJAX.java				         */
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

package com.atradius.web.dwr;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;

import com.atradius.beans.MaintainLiabilityAJAXBean;
import com.atradius.dataaccess.hibernate.HibernateUtil;
import com.atradius.dataaccess.hibernate.bo.TbclAssessCalcLiabilitiesBO;
import com.atradius.dataaccess.hibernate.dao.AssessCalcLiabilitiesDAO;
import com.atradius.dataaccess.hibernate.dao.impl.AssessCalcLiabilitiesDAOImpl;
import com.atradius.sessiondata.ApplicationConstants;
import com.atradius.sessiondata.UserDataObject;
import com.atradius.util.logging.ILogger;
import com.atradius.util.logging.LoggerFactory;

public class MaintainLiabilityTextAJAX {
	private static ILogger logger = LoggerFactory
			.getLogger(MaintainLiabilityTextAJAX.class);

	public MaintainLiabilityAJAXBean saveLiabilityText(String[] codes,
			String[] texts) {
		logger.info("saveLiabilityText.enter");
		String errorMsg = null;
		MaintainLiabilityAJAXBean returnBean = new MaintainLiabilityAJAXBean();
		try {
			WebContext ctx = WebContextFactory.get();
			HttpSession session = ctx.getSession();
			UserDataObject userDataObject = (UserDataObject) session
					.getAttribute(ApplicationConstants.USER_DATA_BEAN);

			if (userDataObject == null) {
				errorMsg = ApplicationConstants.ERROR_INVALID_SESSION;
				logger.error(errorMsg);
				returnBean.setErrorMsg(errorMsg);
				return returnBean;
			}
			HibernateUtil hibernateUtil = (HibernateUtil) session
					.getAttribute(ApplicationConstants.HIBERNATE_UTIL);
			if (hibernateUtil == null) {
				errorMsg = ApplicationConstants.ERROR_INVALID_SESSION;
				returnBean.setErrorMsg(errorMsg);
				logger.error(errorMsg);
				return returnBean;
			}

			HashMap<String, TbclAssessCalcLiabilitiesBO> table = (HashMap<String, TbclAssessCalcLiabilitiesBO>) session
					.getAttribute(ApplicationConstants.ATTR_CASE_ASSESSMENT_DETAIL_TABLE);
			if (table == null) {
				errorMsg = ApplicationConstants.ERROR_INVALID_SESSION;
				returnBean.setErrorMsg(errorMsg);
				logger.error(errorMsg);
				return returnBean;
			}

			List<TbclAssessCalcLiabilitiesBO> beanList = new ArrayList<TbclAssessCalcLiabilitiesBO>();
			for (int i = 0; i < codes.length; i++) {
				TbclAssessCalcLiabilitiesBO bean = table.get(codes[i]);
				// validation new entries
				if (texts[i] != null && texts[i].length() != 0) {
					if (texts[i].length() > ApplicationConstants.LENGTH_CONSTANT_TEXTAREA) {
						returnBean.setSuccess(false);
						returnBean.setErrorMsg("Text at row " + (i + 1)
								+ " is more than "
								+ ApplicationConstants.LENGTH_CONSTANT_TEXTAREA
								+ " characters.");
						return returnBean;
					}
				}
				// mark new records for save
				if (texts[i] != null && texts[i].trim().length() > 0) {
					if (!texts[i].equals(bean.getCalcFreeTextUni())) {
						TbclAssessCalcLiabilitiesBO newBean = (TbclAssessCalcLiabilitiesBO) bean
								.clone();
						newBean.setCalcFreeTextUni(texts[i].trim());
						beanList.add(newBean);
					}
				} else {
					if (bean.getCalcFreeTextUni() != null
							&& bean.getCalcFreeTextUni().trim().length() > 0) {
						TbclAssessCalcLiabilitiesBO newBean = (TbclAssessCalcLiabilitiesBO) bean
								.clone();
						newBean.setCalcFreeTextUni(texts[i].trim());
						beanList.add(newBean);
					}
				}

			}
			AssessCalcLiabilitiesDAO dao = new AssessCalcLiabilitiesDAOImpl();

			deletePreviousRecords(session, hibernateUtil);
			logger.info("trying to Save [" + beanList.size()
					+ "]  records in AJAX ");
			dao.updateUnicode(hibernateUtil, beanList);
			returnBean.setSuccess(true);
			returnBean
					.setSuccessMessage(ApplicationConstants.INFO_SUCCESS_SAVE);
		} catch (RuntimeException e) {
			logger.exception(e);
			returnBean.setSuccess(false);
			returnBean.setErrorMsg(ApplicationConstants.ERROR_DATABASE);
		} catch (CloneNotSupportedException e) {
			logger.exception(e);
			returnBean.setSuccess(false);
			returnBean.setErrorMsg(ApplicationConstants.ERROR_DATABASE);
		}
		logger.exitMethod("saveLiabilityText.exit");
		return returnBean;

	}

	private boolean deletePreviousRecords(HttpSession session,
			HibernateUtil hibernateUtil) {
		boolean delete = false;
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
		AssessCalcLiabilitiesDAO dao = new AssessCalcLiabilitiesDAOImpl();
		logger.info("trying to delete records for [" + bucceId + "] run date ["
				+ timestamp + "] assessType [" + assessType + "]");
		delete = dao.delete(hibernateUtil, bucceId, assessType, timestamp);
		return delete;
	}

}
