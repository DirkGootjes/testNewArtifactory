/*********************************************************************/
/*                           FILE HEADER                             */
/*********************************************************************/
/*                                                                   */
/*  FileName: 		TbbuConditionMchoiceTypesDAOImpl.java             	  	 */
/*  																 */
/*  $Author: INASHA2 $									     */
/*																	 */
/*  $Revision: 1.2 $										     */
/*  																 */
/*  $Date: 2014/03/06 13:50:12 $                            */
/*                                                                   */
/*  Description: 	This action class performs the database function of*/
/*				  	multi choice details					 */
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

import com.atradius.beans.MultiChoiceConditionCodeAJAXBean;
import com.atradius.dataaccess.hibernate.HibernateUtil;
import com.atradius.dataaccess.hibernate.bo.TbbuCondMChoiceTextUniBO;
import com.atradius.dataaccess.hibernate.bo.TbbuConditionMChoiceTextsBO;
import com.atradius.dataaccess.hibernate.bo.TbbuMchoiceVarTypesBO;
import com.atradius.dataaccess.hibernate.dao.impl.TbbuCondMChoiceTextUniDAOImpl;
import com.atradius.dataaccess.hibernate.dao.impl.TbbuConditionMChoiceTextsDAOImpl;
import com.atradius.dataaccess.hibernate.dao.impl.TbbuMchoiceVarTypesDaoImpl;
import com.atradius.exception.DataAccessException;
import com.atradius.sessiondata.ApplicationConstants;
import com.atradius.sessiondata.UserDataObject;
import com.atradius.util.logging.ILogger;
import com.atradius.util.logging.LoggerFactory;

public class MultiChoiceConditionCodeDetailsAJAX {

	private static ILogger logger = LoggerFactory
			.getLogger(MultiChoiceConditionCodeDetailsAJAX.class);

	/**
	 * @param languageCode
	 * @return
	 * @throws Exception
	 */
	public List<MultiChoiceConditionCodeAJAXBean> loadLangDetails(int choice){
		logger.enterMethod("loadLangDetails", choice);
		String errorMsg = null;
		MultiChoiceConditionCodeAJAXBean returnBean = new MultiChoiceConditionCodeAJAXBean();
		List<MultiChoiceConditionCodeAJAXBean> returnBeanList = new ArrayList<MultiChoiceConditionCodeAJAXBean>();
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
		} 
		else 
		{
			try
			{			
				/*Load Language Details*/
				List<TbbuConditionMChoiceTextsBO> translationList = new ArrayList<TbbuConditionMChoiceTextsBO>();
				TbbuConditionMChoiceTextsDAOImpl daoImpl = new TbbuConditionMChoiceTextsDAOImpl();
				translationList = daoImpl.getAllNonLatinTranslationList(
						hibernateUtil, bucdeCd, choice);
	
				for (int i = 0; i < translationList.size(); i++) {
					MultiChoiceConditionCodeAJAXBean returnBeanLocal = new MultiChoiceConditionCodeAJAXBean();
					returnBeanLocal.setLangCode(translationList.get(i).getLangCode());
					returnBeanLocal.setText(translationList.get(i).getText());
					returnBeanList.add(returnBeanLocal);
					logger.debug("Lang: "+translationList.get(i).getLangCode()+" and Variable Type: " +translationList.get(i).getText());
				}
				session.setAttribute(
						ApplicationConstants.AJAX_NONLATIN_TEXT_DETAIL,
						returnBeanList);
				
				/*Load Variable Details*/
				List<TbbuMchoiceVarTypesBO> variableList = new ArrayList<TbbuMchoiceVarTypesBO>();
				TbbuMchoiceVarTypesDaoImpl daoImpl1 = new TbbuMchoiceVarTypesDaoImpl();
				variableList = daoImpl1.getMchoiceConditionVarTypes(
						hibernateUtil, bucdeCd, choice);
	
				for (int i = 0; i < variableList.size(); i++) {
					MultiChoiceConditionCodeAJAXBean returnVariableLocal = new MultiChoiceConditionCodeAJAXBean();
					returnVariableLocal.setBuvteSeq(variableList.get(i).getBuvteSeq());
					returnVariableLocal.setBuvteType(variableList.get(i).getBuvteType());
					logger.debug("Variable Seq.: "+returnVariableLocal.getBuvteSeq()+" and Variable Type: " +returnVariableLocal.getBuvteType());
					returnBeanList.add(returnVariableLocal);}
	
				session.setAttribute(
						ApplicationConstants.ATTR_CONDITION_VAR_TYPES,
						returnBeanList);
			} 
			catch(Exception e){
			logger.error(e.getMessage());
		}
	}			
		return returnBeanList;
	}

	public List<MultiChoiceConditionCodeAJAXBean> save(int bumctSequence,
			String[] langs, String[] texts) {
		logger.enterMethod("save", bumctSequence);
		boolean saveOrUpdate = false;
		List<MultiChoiceConditionCodeAJAXBean> oldReturnBeanList = new ArrayList<MultiChoiceConditionCodeAJAXBean>();
		List<MultiChoiceConditionCodeAJAXBean> newReturnBeanList = new ArrayList<MultiChoiceConditionCodeAJAXBean>();
		//Prepare list of MultiChoiceConditionCodeAJAXBean by calling prepareList function
		newReturnBeanList = prepareList(langs, texts);
		String errorMsg = null;
		WebContext ctx = WebContextFactory.get();
		HttpSession session = ctx.getSession();
		UserDataObject userDataObject = (UserDataObject) session
				.getAttribute(ApplicationConstants.USER_DATA_BEAN);
		if (userDataObject == null) {
			errorMsg = ApplicationConstants.ERROR_INVALID_SESSION;
			newReturnBeanList.get(1).setErrorMsg(errorMsg);
			logger.error(errorMsg);
			return newReturnBeanList;
		}
		HibernateUtil hibernateUtil = (HibernateUtil) session
				.getAttribute(ApplicationConstants.HIBERNATE_UTIL);
		if (hibernateUtil == null) {
			errorMsg = ApplicationConstants.ERROR_INVALID_SESSION;
			newReturnBeanList.get(1).setErrorMsg(errorMsg);
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
			return newReturnBeanList;
		} else {
			try {
				TbbuCondMChoiceTextUniDAOImpl tbbuCondMChoiceTextUniDAOImpl = new TbbuCondMChoiceTextUniDAOImpl();
				oldReturnBeanList = (List<MultiChoiceConditionCodeAJAXBean>) session
						.getAttribute(ApplicationConstants.AJAX_NONLATIN_TEXT_DETAIL);
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
								newReturnBeanList.get(1).setErrorMsg(
												ApplicationConstants.ERROR_BLANK_INVOICE_TEXT+ " for " +newReturnBeanList.get(i).getLangCode()+ " language");
								logger.error("Validation Error : "
												+ ApplicationConstants.ERROR_BLANK_INVOICE_TEXT+ " for " +newReturnBeanList.get(i).getLangCode()+ " language");
								return newReturnBeanList;
							} else if (newReturnBeanList.get(i).getText()
									.trim().length() > ApplicationConstants.LENGTH_CONSTANT_TEXTAREA) {
								newReturnBeanList.get(1).setSuccess(false);
								newReturnBeanList.get(1).setErrorMsg(ApplicationConstants.ERROR_TEXT_MAX_LENGTH);
								logger.error("Validation Error :"+ ApplicationConstants.ERROR_TEXT_MAX_LENGTH);
								
								
								return newReturnBeanList;
							} else {
								
								boolean checkIfUpdate = false;
								checkIfUpdate = tbbuCondMChoiceTextUniDAOImpl.getUnicodeVersionForSave(
												hibernateUtil, bucdeCd,	newReturnBeanList.get(i).getLangCode(),
												bumctSequence);
								if (checkIfUpdate) {

									TbbuCondMChoiceTextUniBO tbbuCondMChoiceTextUniBOinsert = new TbbuCondMChoiceTextUniBO();
									try {
										tbbuCondMChoiceTextUniBOinsert = tbbuCondMChoiceTextUniDAOImpl
												.insertTextTranslations(
														hibernateUtil, bucdeCd, newReturnBeanList.get(i).getText(),
														newReturnBeanList.get(i).getLangCode(),
														bumctSequence);
									} catch (DataAccessException e) {
										logger.exception(e);
										//e.printStackTrace();
										newReturnBeanList.get(1).setSuccess(false);
										newReturnBeanList.get(1).setErrorMsg(ApplicationConstants.ERROR_DATABASE);
										return newReturnBeanList;
									}

									
									newReturnBeanList.get(1).setSuccess(true);
									newReturnBeanList.get(1).setSuccessMessage(ApplicationConstants.SUCCESS_TCODETEXT_CREATE);
								} else {
									// if selectedTransaltioBean is not null then update
									TbbuCondMChoiceTextUniBO tbbuCondMChoiceTextUniBOupdate = new TbbuCondMChoiceTextUniBO();
									try {
										tbbuCondMChoiceTextUniBOupdate = tbbuCondMChoiceTextUniDAOImpl
												.updateTextTranslations(
														hibernateUtil, bucdeCd,	newReturnBeanList.get(i).getText(),
														newReturnBeanList.get(i).getLangCode(),
														bumctSequence);
									} catch (DataAccessException e) {
										logger.exception(e);
										//e.printStackTrace();
										newReturnBeanList.get(1).setSuccess(false);
										newReturnBeanList.get(1).setErrorMsg(ApplicationConstants.ERROR_DATABASE);
										return newReturnBeanList;
									}

									// update the text in BO and then update the list in session
									newReturnBeanList.get(1).setSuccess(true);
									newReturnBeanList.get(1).setSuccessMessage(ApplicationConstants.SUCCESS_TCODETEXT_UPDATE);

								}
							}
						}
					}

				}
			} catch(Exception e){
				logger.error(e.getMessage());
			}
			session.setAttribute(
					ApplicationConstants.AJAX_NONLATIN_TEXT_DETAIL,
					newReturnBeanList);
			logger.exitMethod("save");
			if (saveOrUpdate == false) {
				errorMsg = "Atleast create or update one non latin translation";
				newReturnBeanList.get(1).setSuccess(false);
				newReturnBeanList.get(1).setErrorMsg(errorMsg);
			}
			return newReturnBeanList;
		}

	}

	public List<MultiChoiceConditionCodeAJAXBean> prepareList(String[] langs,
			String[] texts) {
		List<MultiChoiceConditionCodeAJAXBean> newReturnBeanList = new ArrayList<MultiChoiceConditionCodeAJAXBean>();
		for (int i = 0; i < langs.length; i++) {
			MultiChoiceConditionCodeAJAXBean ajaxBean = new MultiChoiceConditionCodeAJAXBean();
			ajaxBean.setLangCode(langs[i]);
			ajaxBean.setText(texts[i]);
			newReturnBeanList.add(ajaxBean);
		}
		return newReturnBeanList;
	}

	public List<MultiChoiceConditionCodeAJAXBean> deleteTextDetails(
			String langCode, Integer bumctSequence,String text) {
		logger.enterMethod("deleteTextDetails", langCode, bumctSequence);		
		WebContext ctx = WebContextFactory.get();
		HttpSession session = ctx.getSession();
		String errorMsg = null;
		boolean isDeleted = false;
		MultiChoiceConditionCodeAJAXBean returnBean = new MultiChoiceConditionCodeAJAXBean();
		List<MultiChoiceConditionCodeAJAXBean> returnBeanList = new ArrayList<MultiChoiceConditionCodeAJAXBean>();
		returnBeanList = (List<MultiChoiceConditionCodeAJAXBean>) session.getAttribute(ApplicationConstants.AJAX_NONLATIN_TEXT_DETAIL);
		
		UserDataObject userDataObject = (UserDataObject) session
				.getAttribute(ApplicationConstants.USER_DATA_BEAN);
		if (userDataObject == null) {
			errorMsg = ApplicationConstants.ERROR_INVALID_SESSION;
			returnBeanList.get(1).setErrorMsg(errorMsg);
			logger.error(errorMsg);
			return returnBeanList;
		}
		
		HibernateUtil hibernateUtil = (HibernateUtil) session.getAttribute(ApplicationConstants.HIBERNATE_UTIL);
		if (hibernateUtil == null) {
			errorMsg = ApplicationConstants.ERROR_INVALID_SESSION;
			returnBeanList.get(1).setErrorMsg(errorMsg);
			return returnBeanList;
		}
		
		String bucdeCd = null;
		bucdeCd = (String) session
				.getAttribute(ApplicationConstants.REQUEST_BUCDE_CODE);
		if (bucdeCd == null) {
			errorMsg = ApplicationConstants.ERROR_NO_BUCDECD;
			returnBeanList.get(1).setErrorMsg(errorMsg);
			logger.error(errorMsg);

			return returnBeanList;
		} else {
			if (text.trim().length() <= 0) {
				returnBeanList.get(1).setSuccess(false);
				returnBeanList.get(1).setSuccessMessage("");
				returnBeanList.get(1).setErrorMsg(ApplicationConstants.ERROR_BLANK_INVOICE_TEXT);
				return returnBeanList;
			}

			try {
				TbbuCondMChoiceTextUniDAOImpl tbbuCondMChoiceTextUniDAOImpl = new TbbuCondMChoiceTextUniDAOImpl();				
				//First check if non latin translation is present or not before deleting
				boolean isTranslationNotPresent = true;
				isTranslationNotPresent = tbbuCondMChoiceTextUniDAOImpl.getUnicodeVersionForSave(hibernateUtil, bucdeCd,
						langCode, bumctSequence);
				if (isTranslationNotPresent) {
					
					returnBeanList.get(1).setSuccess(false);
					returnBeanList.get(1).setErrorMsg(ApplicationConstants.ERROR_NONLATIN_TRANS_NOT_PRESENT);
					returnBeanList.get(1).setSuccessMessage("");
					
					return returnBeanList;
					
				} else {
					isDeleted = tbbuCondMChoiceTextUniDAOImpl.deleteTextTranslations(hibernateUtil, bucdeCd, langCode, bumctSequence);
				}
			} catch (DataAccessException e) {
				logger.exception(e);
				
				returnBeanList.get(1).setSuccess(false);
				returnBeanList.get(1).setErrorMsg(ApplicationConstants.ERROR_DATABASE);
				returnBeanList.get(1).setSuccessMessage("");
				return returnBeanList;
			}
			try{
				if (isDeleted) {				
				
					TbbuConditionMChoiceTextsDAOImpl daoImpl = new TbbuConditionMChoiceTextsDAOImpl();
					TbbuConditionMChoiceTextsBO latinVersion = daoImpl.getLatinVersion(hibernateUtil, bucdeCd, langCode, bumctSequence);
					if (latinVersion != null) {
						//Replace the non latin text with latin text for deleted language
						for (int i = 0; i < returnBeanList.size(); i++) {
							if (returnBeanList.get(i).getLangCode().equals(langCode)) 
							{
								returnBeanList.get(i).setText(latinVersion.getText());
								
								returnBeanList.get(1).setSuccess(true);
								returnBeanList.get(1).setSuccessMessage(ApplicationConstants.SUCCESS_TCODETEXT_DELETE);
								returnBeanList.get(1).setErrorMsg("");
								session.setAttribute(ApplicationConstants.AJAX_NONLATIN_TEXT_DETAIL, returnBeanList);
							}
						}
					}

					returnBeanList.get(1).setSuccess(true);
					returnBeanList.get(1).setSuccessMessage(ApplicationConstants.SUCCESS_TCODETEXT_DELETE);
					returnBeanList.get(1).setErrorMsg("");
					session.setAttribute(ApplicationConstants.AJAX_NONLATIN_TEXT_DETAIL, returnBeanList);
					
				} else {
					returnBeanList.get(1).setSuccess(false);
					returnBeanList.get(1).setSuccessMessage("");
					returnBeanList.get(1).setErrorMsg("No record deleted.");
				}
			}catch(Exception e){
				logger.error(e.getMessage());
			}

			logger.exitMethod("deleteTextDetails");
			return returnBeanList;
		}
	}
}
