/*********************************************************************/
/*                           FILE HEADER                             */
/*********************************************************************/
/*                                                                   */
/*  FileName: 		IndividualDetailsAJAX.java               	     */
/*  																 */
/*  $Author: INVSAR1 $									             */
/*																	 */
/*  $Revision: 1.18 $										         */
/*  																 */
/*  $Date: 2013/08/16 12:46:37 $                                     */
/*                                                                   */
/*  Description: 	This java class is used for  AJAX methods        */
/*				  	for individual details 	                         */
/*********************************************************************/
/* Date        Name            Version             Comments          */
/*-------------------------------------------------------------------*/
/* 04/04/2013  INVSAR1      	1.0         Initial version created  */
/*********************************************************************/
package com.atradius.web.dwr;

import javax.servlet.http.HttpSession;

import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;
import org.hibernate.Session;

import com.atradius.beans.IndividualAJAXBean;
import com.atradius.dataaccess.hibernate.HibernateUtil;
import com.atradius.dataaccess.hibernate.bo.TborIndividualsUnicodeBO;
import com.atradius.dataaccess.hibernate.dao.impl.TborIndividualsUnicodeDAOImpl;
import com.atradius.exception.DataAccessException;
import com.atradius.sessiondata.ApplicationConstants;
import com.atradius.sessiondata.UserDataObject;
import com.atradius.util.logging.ILogger;
import com.atradius.util.logging.LoggerFactory;

/**
 * @author GNINFO15
 * 
 */
public class IndividualDetailsAJAX {
	private static ILogger logger = LoggerFactory
			.getLogger(ContactDetailsAJAX.class);

	/**
	 * @param languageCode
	 * @param translation
	 * @return
	 * @throws Exception
	 */
	public IndividualAJAXBean saveIndividualDetails(String languageCode,
			String translation) throws Exception {
		logger.enterMethod("saveIndividualDetails", languageCode, translation);
		String errorMsg = null;
		IndividualAJAXBean returnBean = new IndividualAJAXBean();
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
			logger.error(errorMsg);
			returnBean.setErrorMsg(errorMsg);
			return returnBean;
		}
		// save unicode text details
		int indvId = 0;
		indvId = Integer.valueOf((String) session
				.getAttribute(ApplicationConstants.REQUEST_INDIVIDUAL_ID));
		if (indvId == 0) {
			errorMsg = ApplicationConstants.ERROR_NO_INDVID;
			logger.error(errorMsg);
			returnBean.setErrorMsg(errorMsg);
			return returnBean;
		} else {
			if (translation.trim().length() <= 0) {
				returnBean.setSuccess(false);
				returnBean.setErrorMsg(ApplicationConstants.ERROR_BLANK_INDV_NAME);
				logger.error("Validation Error :"+ApplicationConstants.ERROR_BLANK_INDV_NAME);
				return returnBean;
			} else if(translation.trim().length() > ApplicationConstants.LENGTH_CONSTANT_COMMON){
				returnBean.setSuccess(false);
				returnBean.setErrorMsg(ApplicationConstants.ERROR_INDIVNAME_MAX_LENGTH);
				logger.error("Validation Error :"+ApplicationConstants.ERROR_INDIVNAME_MAX_LENGTH);
				return returnBean;
			}else {
				TborIndividualsUnicodeBO selectedBean = (TborIndividualsUnicodeBO) session
						.getAttribute(ApplicationConstants.SELECTED_INDV_TRANSLATION);
				if (selectedBean != null && selectedBean.getIndivId() != 0) {
					selectedBean.setIndivName(translation);
					Session hibernateSession = hibernateUtil
							.getSession();
					try {
						TborIndividualsUnicodeDAOImpl.updateUnicodeVersion(
								hibernateSession, selectedBean);
					} catch (DataAccessException e) {
						returnBean.setSuccess(false);
						returnBean.setErrorMsg(ApplicationConstants.ERROR_DATABASE);
						logger.error(ApplicationConstants.ERROR_DATABASE);
						return returnBean;
					}
					returnBean.setIndivName(translation);
					session.setAttribute(
							ApplicationConstants.SELECTED_INDV_TRANSLATION,
							selectedBean);
					returnBean.setSuccess(true);
					returnBean
							.setSuccessMessage(ApplicationConstants.INFO_SUCCESS_UPDATE);
				} else {
					TborIndividualsUnicodeDAOImpl tborIndividualsUnicodeDAOImpl = new TborIndividualsUnicodeDAOImpl();
					TborIndividualsUnicodeBO tborIndividualsUnicodeBO = new TborIndividualsUnicodeBO();
					try {
						tborIndividualsUnicodeBO = tborIndividualsUnicodeDAOImpl
								.insertIndividualTranslations(hibernateUtil,
										indvId, languageCode, translation);
					} catch (DataAccessException e) {
						returnBean.setSuccess(false);
						returnBean.setErrorMsg(ApplicationConstants.ERROR_DATABASE);
						logger.error(ApplicationConstants.ERROR_DATABASE);
						return returnBean;
					}
					returnBean.setIndivName(tborIndividualsUnicodeBO
							.getIndivName());
					session.setAttribute(
							ApplicationConstants.SELECTED_INDV_TRANSLATION,
							tborIndividualsUnicodeBO);
					returnBean.setSuccess(true);
					returnBean
							.setSuccessMessage(ApplicationConstants.INFO_SUCCESS_SAVE);
				}
				logger.exitMethod("saveIndividualDetails");
				return returnBean;

			}

		}

	}

	// deleteIndividualDetails

	/**
	 * @param languageCode
	 * @return IndividualAJAXBean
	 * @throws Exception
	 */
	public IndividualAJAXBean deleteIndividualDetails(String languageCode)
			throws Exception {
		logger.enterMethod("deleteIndividualDetails", languageCode);
		String errorMsg = null;
		IndividualAJAXBean returnBean = new IndividualAJAXBean();
		WebContext ctx = WebContextFactory.get();
		HttpSession session = ctx.getSession();
		HibernateUtil hibernateUtil = (HibernateUtil) session
				.getAttribute(ApplicationConstants.HIBERNATE_UTIL);

		UserDataObject userDataObject = (UserDataObject) session
				.getAttribute(ApplicationConstants.USER_DATA_BEAN);
		if (userDataObject == null) {
			errorMsg = ApplicationConstants.ERROR_INVALID_SESSION;
			returnBean.setErrorMsg(errorMsg);
			logger.error(errorMsg);
			return returnBean;
		}
		// HibernateUtil hibernateUtil =
		// (HibernateUtil)session.getAttribute(ApplicationConstants.HIBERNATE_UTIL);
		if (hibernateUtil == null) {
			errorMsg = ApplicationConstants.ERROR_INVALID_SESSION;
			returnBean.setErrorMsg(errorMsg);
			logger.error(errorMsg);
			return returnBean;
		}

		// save unicode text details
		int indvId = 0;
		indvId = Integer.valueOf((String) session
				.getAttribute(ApplicationConstants.REQUEST_INDIVIDUAL_ID));
		if (indvId == 0) {
			errorMsg = ApplicationConstants.ERROR_NO_INDVID;
			returnBean.setErrorMsg(errorMsg);
			logger.error(errorMsg);
			return returnBean;
		} else {
			boolean isDeleted = false;
			Session hibernateSession = hibernateUtil.getSession();
			try {
				TborIndividualsUnicodeDAOImpl dao = new TborIndividualsUnicodeDAOImpl();
				isDeleted = dao.deleteUnicodeVersion(
						hibernateSession, indvId, languageCode);
			} catch (DataAccessException e) {
				logger.exception(e);
				errorMsg = ApplicationConstants.ERROR_DATABASE;
				returnBean.setErrorMsg(errorMsg);
				return returnBean;
			}
			if (isDeleted) {
				returnBean.setIndivName("");
				returnBean.setSuccess(true);
				returnBean
						.setSuccessMessage(ApplicationConstants.INFO_SUCCESS_DEL);
				session.setAttribute(
						ApplicationConstants.SELECTED_INDV_TRANSLATION,null);
			} else {
				errorMsg = ApplicationConstants.ERROR_DATABASE;
				returnBean.setErrorMsg(errorMsg);
				logger.error(errorMsg);
				return returnBean;
			}
			logger.exitMethod("deleteIndividualDetails");
			return returnBean;
		}
	}
}
