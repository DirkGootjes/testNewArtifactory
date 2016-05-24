/*********************************************************************/
/*                           FILE HEADER                             */
/*********************************************************************/
/*                                                                   */
/*  FileName: 		ConditionCodeDetailsAJAX.java             	  	 */
/*  																 */
/*  $Author: INASHA2 $									     */
/*																	 */
/*  $Revision: 1.2 $										     */
/*  																 */
/*  $Date: 2014/03/06 13:50:12 $                            */
/*                                                                   */
/*  Description: 	This action class performs the AJAX function of*/
/*				  	single choice details					 */
/*********************************************************************/
/* Date        Name            Version             Comments          */
/*-------------------------------------------------------------------*/
/* 17/12/2013  INRSHR1      	1.0         Initial version created  */
/*********************************************************************/
package com.atradius.web.dwr;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;

import com.atradius.beans.ConditionCodeAJAXBean;
import com.atradius.dataaccess.hibernate.HibernateUtil;
import com.atradius.dataaccess.hibernate.bo.TbbuTextTranslationsBO;
import com.atradius.dataaccess.hibernate.bo.TbbuTextTranslationsUniBO;
import com.atradius.dataaccess.hibernate.dao.impl.ConditionCodeDAOImpl;
import com.atradius.exception.DataAccessException;
import com.atradius.sessiondata.ApplicationConstants;
import com.atradius.sessiondata.UserDataObject;
import com.atradius.util.logging.ILogger;
import com.atradius.util.logging.LoggerFactory;

public class ConditionCodeDetailsAJAX {
	private static ILogger logger = LoggerFactory
			.getLogger(ConditionCodeDetailsAJAX.class);

	/**
	 * @param languageCode
	 * @return
	 * @throws Exception
	 */
	public List<ConditionCodeAJAXBean> loadLangDetails() throws Exception {
		logger.enterMethod("loadLangDetails");
		String errorMsg = null;
		ConditionCodeAJAXBean returnBean = new ConditionCodeAJAXBean();
		List<ConditionCodeAJAXBean> returnBeanList = new ArrayList<ConditionCodeAJAXBean>();
		WebContext ctx = WebContextFactory.get();
		HttpSession session = ctx.getSession();

		UserDataObject userDataObject = (UserDataObject) session
				.getAttribute(ApplicationConstants.USER_DATA_BEAN);
		if (userDataObject == null) {
			errorMsg = ApplicationConstants.ERROR_INVALID_SESSION;
			returnBean.setErrorMsg(errorMsg);
			logger.error(errorMsg);
			returnBeanList.add(returnBean);
			return returnBeanList;
		}

		HibernateUtil hibernateUtil = (HibernateUtil) session
				.getAttribute(ApplicationConstants.HIBERNATE_UTIL);
		if (hibernateUtil == null) {
			errorMsg = ApplicationConstants.ERROR_INVALID_SESSION;
			returnBean.setErrorMsg(errorMsg);
			returnBeanList.add(returnBean);
			return returnBeanList;
		}

		// get unicode text details
		String bucdeCd = null;
		bucdeCd = (String) session
				.getAttribute(ApplicationConstants.REQUEST_BUCDE_CODE);
		if (bucdeCd == null) {
			errorMsg = ApplicationConstants.ERROR_NO_BUCDECD;
			returnBean.setErrorMsg(errorMsg);
			logger.error(errorMsg);
			returnBeanList.add(returnBean);
			return returnBeanList;
		} else {

			List<TbbuTextTranslationsBO> translationList = new ArrayList<TbbuTextTranslationsBO>();
			ConditionCodeDAOImpl condImpl = new ConditionCodeDAOImpl();
			translationList = condImpl.getAllNonLatinTranslationList(
					hibernateUtil, bucdeCd);

			for (int i = 0; i < translationList.size(); i++) {
				ConditionCodeAJAXBean returnBeanLocal = new ConditionCodeAJAXBean();
				returnBeanLocal.setLangCode(translationList.get(i)
						.getLanguageCode());
				returnBeanLocal
						.setText(translationList.get(i).getDescription());
				returnBeanList.add(returnBeanLocal);
			}
			// HttpSession session = request.getSession();
			session.setAttribute(
					ApplicationConstants.AJAX_NONLATIN_TEXT_DETAIL,
					returnBeanList);
		}
		return returnBeanList;
	}

	public List<ConditionCodeAJAXBean> save(String[] langs, String[] texts,
			String[] oldTexts) throws Exception {
		logger.enterMethod("save");
		boolean saveOrUpdate = false;
		List<ConditionCodeAJAXBean> oldReturnBeanList = new ArrayList<ConditionCodeAJAXBean>();
		List<ConditionCodeAJAXBean> newReturnBeanList = new ArrayList<ConditionCodeAJAXBean>();
		//Prepare list of MultiChoiceConditionCodeAJAXBean by calling prepareList function
		newReturnBeanList = prepareList(langs, texts);

		oldReturnBeanList = prepareList(langs, oldTexts);
		String errorMsg = null;
		WebContext ctx = WebContextFactory.get();
		HttpSession session = ctx.getSession();
		UserDataObject userDataObject = (UserDataObject) session
				.getAttribute(ApplicationConstants.USER_DATA_BEAN);
		if (userDataObject == null) {
			errorMsg = ApplicationConstants.ERROR_INVALID_SESSION;
			newReturnBeanList.get(1).setErrorMsg(errorMsg);
			logger.error(errorMsg);

			//newReturnBeanList.add(returnBean);
			return newReturnBeanList;
		}
		HibernateUtil hibernateUtil = (HibernateUtil) session
				.getAttribute(ApplicationConstants.HIBERNATE_UTIL);
		if (hibernateUtil == null) {
			errorMsg = ApplicationConstants.ERROR_INVALID_SESSION;
			newReturnBeanList.get(1).setErrorMsg(errorMsg);

			//newReturnBeanList.add(returnBean);
			return newReturnBeanList;
		}

		// save unicode text details
		String bucdeCd = null;
		bucdeCd = (String) session
				.getAttribute(ApplicationConstants.REQUEST_BUCDE_CODE);
		if (bucdeCd == null) {
			errorMsg = ApplicationConstants.ERROR_NO_BUCDECD;
			newReturnBeanList.get(1).setErrorMsg(errorMsg);
			logger.error(errorMsg);
			//newReturnBeanList.add(returnBean);
			return newReturnBeanList;
		} else {
			try {
				ConditionCodeDAOImpl conditionCodeDAOImpl = new ConditionCodeDAOImpl();
				for (int i = 0; i < newReturnBeanList.size(); i++) {

					if (newReturnBeanList.get(i).getLangCode().equals(
							oldReturnBeanList.get(i).getLangCode())) {
						if (newReturnBeanList.get(i).getText().equals(
								oldReturnBeanList.get(i).getText())) {

						} else {
							saveOrUpdate = true;
							if (newReturnBeanList.get(i).getText().trim()
									.length() <= 0) {
								newReturnBeanList.get(1).setSuccess(false);
								newReturnBeanList
										.get(1)
										.setErrorMsg(
												ApplicationConstants.ERROR_BLANK_INVOICE_TEXT
														+ " for "
														+ newReturnBeanList
																.get(i)
																.getLangCode()
														+ " language");
								logger
										.error("Validation Error : "
												+ ApplicationConstants.ERROR_BLANK_INVOICE_TEXT
												+ " for "
												+ newReturnBeanList.get(i)
														.getLangCode()
												+ " language");
								//newReturnBeanList.add(returnBean);
								return newReturnBeanList;
							} /*else if (newReturnBeanList.get(i).getText().trim().length() > ApplicationConstants.LENGTH_CONSTANT_TEXTAREA) {
							 newReturnBeanList.get(1).setSuccess(false);
							 newReturnBeanList.get(1)
							 .setErrorMsg(ApplicationConstants.ERROR_TEXT_MAX_LENGTH);
							 logger.error("Validation Error :"
							 + ApplicationConstants.ERROR_TEXT_MAX_LENGTH);
							 //newReturnBeanList.add(returnBean);
							 return newReturnBeanList;
							 }*/
							else {
								boolean checkIfUpdate = false;
								checkIfUpdate = conditionCodeDAOImpl
										.getUnicodeVersionForSave(
												hibernateUtil, bucdeCd,
												newReturnBeanList.get(i)
														.getLangCode());
								if (checkIfUpdate) {

									TbbuTextTranslationsUniBO tbbuTextTranslationsUniBOinsert = new TbbuTextTranslationsUniBO();
									try {
										tbbuTextTranslationsUniBOinsert = conditionCodeDAOImpl
												.insertTextTranslations(
														hibernateUtil, bucdeCd,
														newReturnBeanList
																.get(i)
																.getText(),
														newReturnBeanList
																.get(i)
																.getLangCode());
									} catch (DataAccessException e) {
										logger.exception(e);
										newReturnBeanList.get(1).setSuccess(
												false);
										newReturnBeanList
												.get(1)
												.setErrorMsg(
														ApplicationConstants.ERROR_DATABASE);
										//newReturnBeanList.add(returnBean);
										return newReturnBeanList;
									}
									newReturnBeanList.get(1).setSuccess(true);
									newReturnBeanList
											.get(1)
											.setSuccessMessage(
													ApplicationConstants.SUCCESS_TCODETEXT_CREATE);
								} else {
									// if selectedTransaltioBean is not null then update
									// selectedTransaltioBean.setDescription(translation);
									TbbuTextTranslationsUniBO tbbuTextTranslationsUniBOupdate = new TbbuTextTranslationsUniBO();
									try {
										tbbuTextTranslationsUniBOupdate = conditionCodeDAOImpl
												.updateTextTranslations(
														hibernateUtil, bucdeCd,
														newReturnBeanList
																.get(i)
																.getText(),
														newReturnBeanList
																.get(i)
																.getLangCode());
									} catch (DataAccessException e) {
										logger.exception(e);
										newReturnBeanList.get(1).setSuccess(
												false);
										newReturnBeanList
												.get(1)
												.setErrorMsg(
														ApplicationConstants.ERROR_DATABASE);
										//newReturnBeanList.add(returnBean);
										return newReturnBeanList;
									}

									// update the text in BO and then update the list in session
									newReturnBeanList.get(1).setSuccess(true);
									newReturnBeanList
											.get(1)
											.setSuccessMessage(
													ApplicationConstants.SUCCESS_TCODETEXT_UPDATE);
									// session.setAttribute("transaltions", transaltionList);

								}
							}
						}
					}

				}
			} catch (Exception e) {
			}
			session.setAttribute(
					ApplicationConstants.AJAX_NONLATIN_TEXT_DETAIL,
					newReturnBeanList);
			logger.exitMethod("saveTextDetails");

			if (saveOrUpdate == false) {
				errorMsg = "Atleast create or update one non latin translation";
				newReturnBeanList.get(1).setSuccess(false);
				newReturnBeanList.get(1).setErrorMsg(errorMsg);
			}
			return newReturnBeanList;
		}

	}

	public List<ConditionCodeAJAXBean> prepareList(String[] langs,
			String[] texts) {
		List<ConditionCodeAJAXBean> newReturnBeanList = new ArrayList<ConditionCodeAJAXBean>();

		for (int i = 0; i < langs.length; i++) {
			ConditionCodeAJAXBean ajaxBean = new ConditionCodeAJAXBean();
			ajaxBean.setLangCode(langs[i]);
			ajaxBean.setText(texts[i]);
			newReturnBeanList.add(ajaxBean);
		}
		return newReturnBeanList;
	}

	public List<ConditionCodeAJAXBean> deleteTextDetails(String langCode,
			String text) throws Exception {
		logger.enterMethod("deleteTextDetails", langCode);
		WebContext ctx = WebContextFactory.get();
		HttpSession session = ctx.getSession();
		String errorMsg = null;
		boolean isDeleted = false;
		ConditionCodeAJAXBean returnBean = new ConditionCodeAJAXBean();
		List<ConditionCodeAJAXBean> returnBeanList = new ArrayList<ConditionCodeAJAXBean>();
		returnBeanList = (List<ConditionCodeAJAXBean>) session
				.getAttribute(ApplicationConstants.AJAX_NONLATIN_TEXT_DETAIL);
		UserDataObject userDataObject = (UserDataObject) session
				.getAttribute(ApplicationConstants.USER_DATA_BEAN);
		if (userDataObject == null) {
			errorMsg = ApplicationConstants.ERROR_INVALID_SESSION;
			returnBeanList.get(1).setErrorMsg(errorMsg);
			logger.error(errorMsg);
			//returnBeanList.add(returnBean);
			return returnBeanList;

		}
		HibernateUtil hibernateUtil = (HibernateUtil) session
				.getAttribute(ApplicationConstants.HIBERNATE_UTIL);
		if (hibernateUtil == null) {
			errorMsg = ApplicationConstants.ERROR_INVALID_SESSION;
			returnBeanList.get(1).setErrorMsg(errorMsg);
			//returnBeanList.add(returnBean);
			return returnBeanList;
		}
		String bucdeCd = null;
		bucdeCd = (String) session
				.getAttribute(ApplicationConstants.REQUEST_BUCDE_CODE);
		if (bucdeCd == null) {
			errorMsg = ApplicationConstants.ERROR_NO_BUCDECD;
			returnBeanList.get(1).setErrorMsg(errorMsg);
			logger.error(errorMsg);

			//returnBeanList.add(returnBean);
			return returnBeanList;
		} else {
			if (text.trim().length() <= 0) {
				returnBeanList.get(1).setSuccess(false);
				returnBeanList.get(1).setErrorMsg(
						ApplicationConstants.ERROR_BLANK_INVOICE_TEXT);
				return returnBeanList;
			}
			ConditionCodeDAOImpl conditionCodeDAOImpl = new ConditionCodeDAOImpl();

			try {

				//First check if non latin translation is present or not before deleting
				boolean isTranslationNotPresent = true;
				isTranslationNotPresent = conditionCodeDAOImpl
						.getUnicodeVersionForSave(hibernateUtil, bucdeCd,
								langCode);
				if (isTranslationNotPresent) {
					returnBeanList.get(1).setSuccess(false);
					returnBeanList
							.get(1)
							.setErrorMsg(
									ApplicationConstants.ERROR_NONLATIN_TRANS_NOT_PRESENT);
					return returnBeanList;
				} else {
					isDeleted = conditionCodeDAOImpl.deleteTextTranslations(
							hibernateUtil, bucdeCd, langCode);
				}
			} catch (DataAccessException e) {
				logger.exception(e);
				returnBeanList.get(1).setSuccess(false);
				returnBeanList.get(1).setErrorMsg(
						ApplicationConstants.ERROR_DATABASE);
				return returnBeanList;
			}
			if (isDeleted) {
				ConditionCodeDAOImpl daoImpl = new ConditionCodeDAOImpl();
				TbbuTextTranslationsBO latinVersion = daoImpl.getLatinVersion(
						hibernateUtil, bucdeCd, langCode);
				if (latinVersion != null) {
					//					Replace the non latin text with latin text for deleted language
					for (int i = 0; i < returnBeanList.size(); i++) {
						if (returnBeanList.get(i).getLangCode()
								.equals(langCode)) {
							returnBeanList.get(i).setText(
									latinVersion.getDescription());
						}
					}
				}
				returnBeanList.get(1).setSuccess(true);

				returnBeanList.get(1).setSuccessMessage(
						ApplicationConstants.SUCCESS_TCODETEXT_DELETE);
				returnBeanList.get(1).setErrorMsg("");
				session.setAttribute(
						ApplicationConstants.AJAX_NONLATIN_TEXT_DETAIL,
						returnBeanList);
				//returnBeanList.add(returnBean);
			} else {
				returnBeanList.get(1).setSuccess(false);
				returnBeanList.get(1).setSuccessMessage(" No record deleted.");
				//returnBeanList.add(returnBean);
			}
			logger.exitMethod("deleteTextDetails");
			return returnBeanList;
		}
	}
}
