/*********************************************************************/
/*                           FILE HEADER                             */
/*********************************************************************/
/*                                                                   */
/*  FileName:	MaintainClaimSectionVariablesAJAX.java	   	  	     */
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

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;
import org.hibernate.Session;

import com.atradius.beans.AJAXResult;
import com.atradius.beans.SectionVariableAjaxBean;
import com.atradius.dataaccess.hibernate.HibernateUtil;
import com.atradius.dataaccess.hibernate.bo.TbclTransLetterVarUniTempBO;
import com.atradius.dataaccess.hibernate.dao.impl.TbclTransLetterVarUniTempDAOImpl;
import com.atradius.dataaccess.hibernate.dao.impl.TbseApplicationErrorDecodsDAO;
import com.atradius.sessiondata.ApplicationConstants;
import com.atradius.sessiondata.UserDataObject;
import com.atradius.util.logging.ILogger;
import com.atradius.util.logging.LoggerFactory;

public class MaintainClaimSectionVariablesAJAX {
	private static ILogger logger = LoggerFactory
			.getLogger(MaintainClaimSectionVariablesAJAX.class);

	public AJAXResult saveSectionVariables(
			SectionVariableAjaxBean[] sectionVariableBeans) {
		logger.enterMethod("saveSectionVariables");
		String errorMsg = null;
		AJAXResult result = new AJAXResult();
		HibernateUtil hibernateUtil = null;
		HttpSession session = null;
		try {
			WebContext ctx = WebContextFactory.get();
			session = ctx.getSession();
			UserDataObject userDataObject = (UserDataObject) session
					.getAttribute(ApplicationConstants.USER_DATA_BEAN);
			if (userDataObject == null) {
				errorMsg = ApplicationConstants.ERROR_INVALID_SESSION;
				logger.error(errorMsg);
				result.setErrorMessage(errorMsg);
				return result;
			}

			hibernateUtil = (HibernateUtil) session
					.getAttribute(ApplicationConstants.HIBERNATE_UTIL);
			Session session1 = hibernateUtil.getSession();

			int caseId = Integer.parseInt(session.getAttribute("claimCaseId")
					.toString());
			String letterType = session.getAttribute("letterType").toString();
			String printDate = session.getAttribute("printDate").toString();

			DateFormat formt = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SSS");
			Date pDate = null;
			Timestamp timestamp = null;
			try {
				pDate = formt.parse(printDate);
				timestamp = new Timestamp(pDate.getTime());

			} catch (ParseException e) {
				e.printStackTrace();
			}
			// session1.beginTransaction();

			TbclTransLetterVarUniTempDAOImpl tbclTransLetterVarUniTempDAOImpl = new TbclTransLetterVarUniTempDAOImpl();
			List<TbclTransLetterVarUniTempBO> selectedSec = tbclTransLetterVarUniTempDAOImpl
					.getSelectedClaimSection(hibernateUtil, caseId, letterType,
							timestamp);
			session.setAttribute("selectedSec", selectedSec);

			tbclTransLetterVarUniTempDAOImpl
					.deleteSelectedClaimSection(session);
			String[] secVariableValues=tbclTransLetterVarUniTempDAOImpl.saveSelectedClaimSection(session,
					sectionVariableBeans);
			result.setSecVariableValues(secVariableValues);

			// Calling package cltlv02p_uni
			CallableStatement validation = session1.connection().prepareCall(
					"{call " + "cltlv02p_uni.check_letter_rules (?,?,?)}");
			validation.setInt(1, caseId);
			validation.setString(2, letterType);
			validation.setTimestamp(3, timestamp);

			// Execute the call to sec_roles1.
			logger.debug("about to call validation");
			validation.execute();
			logger.debug("calling validation OK.");
			validation.close();

			result.setSuccess(true);
			result.setSuccessMessage("Successfully saved!!!");
			// TODO save the incoming data and populate the AJAXResult bean

			// session1.getTransaction().commit();

		} catch (SQLException e) {
			logger.exception(e);
			result.setSuccess(false);
			result.setErrorMessage(e.getMessage());

			TbclTransLetterVarUniTempDAOImpl tbclTransLetterVarUniTempDAOImpl = new TbclTransLetterVarUniTempDAOImpl();
			tbclTransLetterVarUniTempDAOImpl
					.deleteSelectedClaimSection(session);
			tbclTransLetterVarUniTempDAOImpl.saveSelectedClaimSection(session);

			if (e.getMessage().contains("Claims letter not found")) {
				result
						.setErrorMessage(ApplicationConstants.ERROR_INVALID_SESSION);
			} else {
				TbseApplicationErrorDecodsDAO tbseApplicationErrorDecodsDAO = new TbseApplicationErrorDecodsDAO();
				String des = tbseApplicationErrorDecodsDAO.getErrorDes(
						hibernateUtil, e.getMessage());
				if (des != null) {
					result.setErrorMessage(des);
				}
			}

		} catch (RuntimeException e) {

			logger.exception(e);
			result.setSuccess(false);
			result.setErrorMessage(e.getMessage());
		}
		logger.exitMethod("saveSectionVariables");
		return result;
	}

	public AJAXResult loadSectionVariables() {
		logger.enterMethod("loadSectionVariables");
		String errorMsg = null;
		AJAXResult result = new AJAXResult();
		try {
			WebContext ctx = WebContextFactory.get();
			HttpSession session = ctx.getSession();
			UserDataObject userDataObject = (UserDataObject) session
					.getAttribute(ApplicationConstants.USER_DATA_BEAN);
			if (userDataObject == null) {
				errorMsg = ApplicationConstants.ERROR_INVALID_SESSION;
				logger.error(errorMsg);
				result.setErrorMessage(errorMsg);
				return result;
			}
			result.setSuccess(true);
			result.setSuccessMessage("I am able to come here");
			// TODO save the incoming data and populate the AJAXResult bean

		} catch (RuntimeException e) {
			logger.exception(e);
			result.setSuccess(false);
			result.setErrorMessage(e.getMessage());
		}
		logger.exitMethod("loadSectionVariables");
		return result;
	}
	
	
	public AJAXResult checkCostSplit() {
		logger.enterMethod("checkCostSplit");
		String errorMsg = null;
		AJAXResult result = new AJAXResult();
		HibernateUtil hibernateUtil = null;
		HttpSession session = null;
		try {
			WebContext ctx = WebContextFactory.get();
			session = ctx.getSession();
			UserDataObject userDataObject = (UserDataObject) session
					.getAttribute(ApplicationConstants.USER_DATA_BEAN);
			if (userDataObject == null) {
				errorMsg = ApplicationConstants.ERROR_INVALID_SESSION;
				logger.error(errorMsg);
				result.setErrorMessage(errorMsg);
				return result;
			}

			hibernateUtil = (HibernateUtil) session
					.getAttribute(ApplicationConstants.HIBERNATE_UTIL);
			Session session1 = hibernateUtil.getSession();

			int caseId = Integer.parseInt(session.getAttribute("claimCaseId")
					.toString());
			// session1.beginTransaction();

			// Calling package cltlv02p_uni
			CallableStatement validation = session1.connection().prepareCall(
					"{? = call " + "cltlv02p_uni.check_costs_split_exists (?)}");
			validation.registerOutParameter(1, Types.VARCHAR);
			validation.setInt(2, caseId);

			// Execute the call to sec_roles1.
			logger.debug("about to call validation");
			validation.execute();
			logger.debug("calling validation OK.");
			String splitExists = validation.getString(1);
			validation.close();

			
			if(splitExists.equalsIgnoreCase("false")) {
				result.setSuccess(false);
				result.setErrorMessage("There is no valid cost split available. Please do not tick the COS section for the letter");
			} else {
				result.setSuccess(true);
			}
			// TODO save the incoming data and populate the AJAXResult bean

			// session1.getTransaction().commit();

		} catch (SQLException e) {
			logger.exception(e);
			result.setSuccess(false);
			result.setErrorMessage(e.getMessage());

		} catch (RuntimeException e) {

			logger.exception(e);
			result.setSuccess(false);
			result.setErrorMessage(e.getMessage());
		}
		logger.exitMethod("checkCostSplit");
		return result;
	}
}
