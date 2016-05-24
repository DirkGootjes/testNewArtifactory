/*********************************************************************/
/*                           FILE HEADER                             */
/*********************************************************************/
/*                                                                   */
/*  FileName: 		ContactDetailsAJAX.java               	         */
/*  																 */
/*  $Author: INVSAR1 $									             */
/*																	 */			
/*  $Revision: 1.11 $										         */
/*  																 */		
/*  $Date: 2013/06/04 05:53:29 $                                     */
/*                                                                   */
/*  Description: 	This java class is used for  AJAX methods        */
/*				  	for contact details 				             */			
/*********************************************************************/
/* Date        Name            Version             Comments          */
/*-------------------------------------------------------------------*/
/* 04/04/2013  INVSAR1      	1.0         Initial version created  */
/*********************************************************************/
package com.atradius.web.dwr;

import javax.servlet.http.HttpSession;

import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;

import com.atradius.beans.AJAXResult;
import com.atradius.beans.ContactAJAXBean;
import com.atradius.beans.ContactDetails;
import com.atradius.dataaccess.hibernate.dao.impl.UnicodeContactDAOImpl;
import com.atradius.sessiondata.ApplicationConstants;
import com.atradius.sessiondata.UserDataObject;
import com.atradius.util.logging.ILogger;
import com.atradius.util.logging.LoggerFactory;

public class ContactDetailsAJAX {
	private static ILogger logger = LoggerFactory
			.getLogger(ContactDetailsAJAX.class);

	/**
	 * @param index
	 * @param langCode
	 * @return
	 */
	public ContactAJAXBean loadContactDetails(int index, String langCode) {
		logger.enterMethod("loadContactDetails", index, langCode);
		String errorMsg = null;
		ContactAJAXBean returnBean = new ContactAJAXBean();
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
			returnBean = new UnicodeContactDAOImpl().loadContactDetails(index,
					langCode, session);

		} catch (RuntimeException e) {
			logger.exception(e);
			//e.printStackTrace();
			returnBean.setErrorMsg(e.getMessage());
		}
		logger.exitMethod("loadContactDetails");
		return returnBean;
	}

	/**
	 * @param newUnicodeContact
	 * @param langCode
	 * @return
	 */
	public AJAXResult saveUnicodeContactDetails(
			ContactDetails newUnicodeContact, String langCode) {
		logger.enterMethod("saveUnicodeContactDetails", newUnicodeContact,
				langCode);
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

			result = new UnicodeContactDAOImpl().maintainUnicodeContactDetails(
					newUnicodeContact, langCode, session);

		} catch (RuntimeException e) {
			logger.exception(e);
			//e.printStackTrace();
			result.setSuccess(false);
			result.setErrorMessage(e.getMessage());
		}
		logger.exitMethod("saveUnicodeContactDetails");
		return result;

	}

	/**
	 * @param langCode
	 * @return
	 */
	public AJAXResult deleteUnicodeContactDetails(String langCode) {
		logger.enterMethod("deleteUnicodeContactDetails", langCode);
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
			result = new UnicodeContactDAOImpl().deleteUnicodeContactDetails(
					langCode, session);
		} catch (RuntimeException e) {
			logger.exception(e);
			//e.printStackTrace();
			result.setSuccess(false);
			result.setErrorMessage(e.getMessage());
		}
		logger.exitMethod("deleteUnicodeContactDetails");
		return result;
	}
}
