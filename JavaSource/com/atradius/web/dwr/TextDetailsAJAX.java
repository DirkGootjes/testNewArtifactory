/*********************************************************************/
/*                           FILE HEADER                             */
/*********************************************************************/
/*                                                                   */
/*  FileName: 		TextDetailsAJAX.java               	             */
/*  																 */
/*  $Author: INVSAR1 $									             */
/*																	 */
/*  $Revision: 1.15 $										         */
/*  																 */
/*  $Date: 2013/06/07 08:57:58 $                                     */
/*                                                                   */
/*  Description: 	This java class is used for  AJAX methods        */
/*				  	for text details 	                             */
/*********************************************************************/
/* Date        Name            Version             Comments          */
/*-------------------------------------------------------------------*/
/* 04/04/2013  INVSAR1      	1.0         Initial version created  */
/*********************************************************************/
package com.atradius.web.dwr;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;

import com.atradius.beans.TextAJAXBean;
import com.atradius.dataaccess.hibernate.HibernateUtil;
import com.atradius.dataaccess.hibernate.bo.TbbuLanguagesBO;
import com.atradius.dataaccess.hibernate.bo.TbbuTextTranslationsUniBO;
import com.atradius.dataaccess.hibernate.dao.TbbuTextTranslationsUniDAO;
import com.atradius.dataaccess.hibernate.dao.impl.TbbuTextTranslationsUniDAOImpl;
import com.atradius.exception.DataAccessException;
import com.atradius.sessiondata.ApplicationConstants;
import com.atradius.sessiondata.UserDataObject;
import com.atradius.util.logging.ILogger;
import com.atradius.util.logging.LoggerFactory;
import com.atradius.utils.CommonUtil;

public class TextDetailsAJAX {
	private static ILogger logger = LoggerFactory
			.getLogger(TextDetailsAJAX.class);

	/**
	 * @param languageCode
	 * @return
	 * @throws Exception
	 */
	public TextAJAXBean loadTextDetails(String languageCode) throws Exception {
		logger.enterMethod("loadTextDetails", languageCode);
		String errorMsg = null;
		TextAJAXBean returnBean = new TextAJAXBean();
		WebContext ctx = WebContextFactory.get();
		HttpSession session = ctx.getSession();
		UserDataObject userDataObject = (UserDataObject) session
				.getAttribute(ApplicationConstants.USER_DATA_BEAN);
		if (userDataObject == null) {
			errorMsg = ApplicationConstants.ERROR_INVALID_SESSION;
			returnBean.setErrorMsg(errorMsg);
			logger.error(errorMsg);
			return returnBean;
		}

		TbbuLanguagesBO selectedLanguageBean = null;
		List<TbbuLanguagesBO> TbbuLanguagesBOList = (List<TbbuLanguagesBO>) session
				.getAttribute(ApplicationConstants.ATTR_UNI_LANG_DETAIL);

		for (int i = 0; i < TbbuLanguagesBOList.size(); i++) {
			if (TbbuLanguagesBOList.get(i).getLangCode().equals(languageCode)) {
				selectedLanguageBean = TbbuLanguagesBOList.get(i);
				break;
			}
		}
		returnBean.setLanguageName(selectedLanguageBean.getLangName());

		// get unicode text details
		int textId = 0;
		textId = Integer.valueOf((String) session
				.getAttribute(ApplicationConstants.REQUEST_TEXT_ID));
		if (textId == 0) {
			errorMsg = ApplicationConstants.ERROR_NO_TEXTID;
			returnBean.setErrorMsg(errorMsg);
			logger.error(errorMsg);
			return returnBean;
		} else {

			List<TbbuTextTranslationsUniBO> transaltionList = (List<TbbuTextTranslationsUniBO>) session
					.getAttribute(ApplicationConstants.TEXT_TRANSLATION_LIST);
			TbbuTextTranslationsUniBO selectedTransaltioBean = CommonUtil
					.getTransaltion(transaltionList, languageCode);

			session.setAttribute(
					ApplicationConstants.SELECTED_TEXT_TRANSLATION,
					selectedTransaltioBean);
			if (selectedTransaltioBean != null)
				returnBean.setTextDescription(selectedTransaltioBean
						.getDescription());
			else
				returnBean.setTextDescription("");
			logger.exitMethod("loadTextDetails");
			return returnBean;
		}

	}

	// saveTextDetails
	/**
	 * @param languageCode
	 * @param translation
	 * @return
	 * @throws Exception
	 */
	public TextAJAXBean saveTextDetails(String languageCode, String translation)
			throws Exception {
		logger.enterMethod("saveTextDetails", languageCode, translation);
		String errorMsg = null;
		TextAJAXBean returnBean = new TextAJAXBean();
		WebContext ctx = WebContextFactory.get();
		HttpSession session = ctx.getSession();
		UserDataObject userDataObject = (UserDataObject) session
				.getAttribute(ApplicationConstants.USER_DATA_BEAN);
		if (userDataObject == null) {
			errorMsg = ApplicationConstants.ERROR_INVALID_SESSION;
			returnBean.setErrorMsg(errorMsg);
			logger.error(errorMsg);
			return returnBean;
		}
		HibernateUtil hibernateUtil = (HibernateUtil) session
				.getAttribute(ApplicationConstants.HIBERNATE_UTIL);
		if (hibernateUtil == null) {
			errorMsg = ApplicationConstants.ERROR_INVALID_SESSION;
			returnBean.setErrorMsg(errorMsg);
			return returnBean;
		}
		
		// save unicode text details
		int textId = 0;
		textId = Integer.valueOf((String) session
				.getAttribute(ApplicationConstants.REQUEST_TEXT_ID));
		if (textId == 0) {
			errorMsg = ApplicationConstants.ERROR_NO_CONTACT;
			returnBean.setErrorMsg(errorMsg);
			logger.error(errorMsg);
			return returnBean;
		} else {
			TbbuTextTranslationsUniDAO tbbuTextTranslationsUniDAOImpl = new TbbuTextTranslationsUniDAOImpl();
			TbbuTextTranslationsUniBO selectedTransaltioBean = (TbbuTextTranslationsUniBO) session
					.getAttribute(ApplicationConstants.SELECTED_TEXT_TRANSLATION);
			List<TbbuTextTranslationsUniBO> transaltionList = (List<TbbuTextTranslationsUniBO>) session
					.getAttribute(ApplicationConstants.TEXT_TRANSLATION_LIST);
			if (translation.trim().length() <= 0) {
				returnBean.setSuccess(false);
				returnBean.setErrorMsg(ApplicationConstants.ERROR_BLANK_INVOICE_TEXT);
				logger.error("Validation Error : "+ApplicationConstants.ERROR_BLANK_INVOICE_TEXT);
				return returnBean;
			}else if(translation.trim().length() > ApplicationConstants.LENGTH_CONSTANT_TEXTAREA){
				returnBean.setSuccess(false);
				returnBean.setErrorMsg(ApplicationConstants.ERROR_TEXT_MAX_LENGTH);
				logger.error("Validation Error :"+ApplicationConstants.ERROR_TEXT_MAX_LENGTH);
				return returnBean;
			} else {
				if (selectedTransaltioBean == null) { // if
					// selectedTransaltioBean
					// is null then save
					TbbuTextTranslationsUniBO tbbuTextTranslationsUniBOinsert = new TbbuTextTranslationsUniBO();
					try {
						tbbuTextTranslationsUniBOinsert = tbbuTextTranslationsUniDAOImpl
								.insertTextTranslations(hibernateUtil, textId,
										languageCode, translation);
					} catch (DataAccessException e) {
						logger.exception(e);
						returnBean.setSuccess(false);
						returnBean
								.setErrorMsg(ApplicationConstants.ERROR_DATABASE);
						return returnBean;
					}
					returnBean
							.setTextDescription(tbbuTextTranslationsUniBOinsert
									.getDescription());

					transaltionList.add(tbbuTextTranslationsUniBOinsert);
					selectedTransaltioBean = tbbuTextTranslationsUniBOinsert;
					returnBean.setSuccess(true);
					returnBean
							.setSuccessMessage(ApplicationConstants.INFO_SUCCESS_SAVE);

				} else { // if selectedTransaltioBean is not null then update
					selectedTransaltioBean.setDescription(translation);
					TbbuTextTranslationsUniBO tbbuTextTranslationsUniBOupdate = new TbbuTextTranslationsUniBO();
					try {
						tbbuTextTranslationsUniBOupdate = tbbuTextTranslationsUniDAOImpl
								.updateTextTranslations(hibernateUtil, textId,
										languageCode, translation);
					} catch (DataAccessException e) {
						logger.exception(e);
						returnBean.setSuccess(false);
						returnBean
								.setErrorMsg(ApplicationConstants.ERROR_DATABASE);
						return returnBean;
					}
					returnBean
							.setTextDescription(tbbuTextTranslationsUniBOupdate
									.getDescription());
					// update the list
					int index = -1;
					for (int i = 0; i < transaltionList.size(); i++) {
						if (transaltionList.get(i).getLanguageCode().equals(
								languageCode)) {
							index = i;
							break;
						}
					}
					if (index > 0) {
						transaltionList.set(index,
								tbbuTextTranslationsUniBOupdate);
					}
					// update the text in BO and then update the list in session
					returnBean.setSuccess(true);
					returnBean
							.setSuccessMessage(ApplicationConstants.INFO_SUCCESS_UPDATE);
					session.setAttribute("transaltions", transaltionList);
				}
			}
			session.setAttribute(
					ApplicationConstants.SELECTED_TEXT_TRANSLATION,
					selectedTransaltioBean);
			logger.exitMethod("saveTextDetails");
			return returnBean;
		}
	}

	
	/**
	 * @param languageCode
	 * @return
	 * @throws Exception
	 */
	public TextAJAXBean deleteTextDetails(String languageCode) throws Exception {
		logger.enterMethod("deleteTextDetails", languageCode);
		String errorMsg = null;
		boolean isDeleted = false;
		TextAJAXBean returnBean = new TextAJAXBean();
		WebContext ctx = WebContextFactory.get();
		HttpSession session = ctx.getSession();
		UserDataObject userDataObject = (UserDataObject) session
				.getAttribute(ApplicationConstants.USER_DATA_BEAN);
		if (userDataObject == null) {
			errorMsg = ApplicationConstants.ERROR_INVALID_SESSION;
			returnBean.setErrorMsg(errorMsg);
			logger.error(errorMsg);
			return returnBean;
		}
		HibernateUtil hibernateUtil = (HibernateUtil) session
				.getAttribute(ApplicationConstants.HIBERNATE_UTIL);
		if (hibernateUtil == null) {
			errorMsg = ApplicationConstants.ERROR_INVALID_SESSION;
			returnBean.setErrorMsg(errorMsg);
			return returnBean;
		}
		int textId = 0;
		textId = Integer.valueOf((String) session
				.getAttribute(ApplicationConstants.REQUEST_TEXT_ID));
		if (textId == 0) {
			errorMsg = ApplicationConstants.ERROR_NO_CONTACT;
			returnBean.setErrorMsg(errorMsg);
			logger.error(errorMsg);
			return returnBean;
		} else {
			TbbuTextTranslationsUniDAO tbbuTextTranslationsUniDAOImpl = new TbbuTextTranslationsUniDAOImpl();
			try {
				isDeleted = tbbuTextTranslationsUniDAOImpl
						.deleteTextTranslations(hibernateUtil, textId,
								languageCode);
			} catch (DataAccessException e) {
				logger.exception(e);
				returnBean.setSuccess(false);
				returnBean.setErrorMsg(ApplicationConstants.ERROR_DATABASE);
				return returnBean;
			}
			if (isDeleted) {
				returnBean.setSuccess(true);
				returnBean
						.setSuccessMessage(ApplicationConstants.INFO_SUCCESS_DEL);
				returnBean.setTextDescription("");
				List<TbbuTextTranslationsUniBO> transaltionList = (List<TbbuTextTranslationsUniBO>) session
						.getAttribute(ApplicationConstants.TEXT_TRANSLATION_LIST);
				TbbuTextTranslationsUniBO selectedTransaltioBean = CommonUtil
						.getTransaltion(transaltionList, languageCode);
				transaltionList.remove(selectedTransaltioBean);
				session.setAttribute(
						ApplicationConstants.TEXT_TRANSLATION_LIST,
						transaltionList);
			} else {
				returnBean.setSuccess(false);
				returnBean.setSuccessMessage(" No record deleted.");
			}
			logger.exitMethod("deleteTextDetails");
			return returnBean;
		}
	}
}
