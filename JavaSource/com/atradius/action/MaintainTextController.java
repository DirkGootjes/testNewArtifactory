/*********************************************************************/
/*                           FILE HEADER                             */
/*********************************************************************/
/*                                                                   */
/*  FileName: 		MaintainTextController.java             	  	 */
/*  																 */
/*  $Author: INASHA2 $									     */
/*																	 */
/*  $Revision: 1.15 $										     */
/*  																 */
/*  $Date: 2014/03/06 13:49:02 $                            */
/*                                                                   */
/*  Description: 	This action class performs the initial loading of*/
/*				  	organisation Text details					 */
/*********************************************************************/
/* Date        Name            Version             Comments          */
/*-------------------------------------------------------------------*/
/* 04/04/203  INVSAR1      	1.0         Initial version created  */
/*********************************************************************/
// package
package com.atradius.action;

// imports
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.atradius.dataaccess.hibernate.HibernateUtil;
import com.atradius.dataaccess.hibernate.bo.TbbuLanguagesBO;
import com.atradius.dataaccess.hibernate.bo.TbbuTextTranslationsBO;
import com.atradius.dataaccess.hibernate.bo.TbbuTextTranslationsUniBO;
import com.atradius.dataaccess.hibernate.bo.TbbuTextTypesBO;
import com.atradius.dataaccess.hibernate.dao.TbbuTextTypesDAO;
import com.atradius.dataaccess.hibernate.dao.impl.TbbuLanguagesDAOImpl;
import com.atradius.dataaccess.hibernate.dao.impl.TbbuTextTranslationsUniDAOImpl;
import com.atradius.dataaccess.hibernate.dao.impl.TbbuTextTypesDAOImpl;
import com.atradius.exception.DataAccessException;
import com.atradius.sessiondata.ApplicationConstants;
import com.atradius.sessiondata.UserDataObject;
import com.atradius.util.config.ApplicationConfig;
import com.atradius.util.logging.ILogger;
import com.atradius.util.logging.LoggerFactory;
import com.atradius.utils.CommonUtil;


public class MaintainTextController extends Action {

	private static ILogger logger = LoggerFactory
			.getLogger(MaintainTextController.class);

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

		logger.enterMethod("MaintainTextController");
		TbbuTextTypesBO tbbuTextTypesBO = new TbbuTextTypesBO();
		HttpSession session = request.getSession();
		UserDataObject userDataObject = (UserDataObject) session
		.getAttribute(ApplicationConstants.USER_DATA_BEAN);

		if (userDataObject == null) {
			request.setAttribute(ApplicationConstants.ERRMSG, ApplicationConstants.ERROR_INVALID_SESSION);
			return mapping.findForward(ERROR);
		}
		
		HibernateUtil hibernateUtil = (HibernateUtil)session.getAttribute(ApplicationConstants.HIBERNATE_UTIL);
		if (hibernateUtil == null) {
			request.setAttribute(ApplicationConstants.ERRMSG, ApplicationConstants.ERROR_INVALID_SESSION);
			return mapping.findForward(ERROR);
		}
		
		ApplicationConfig config = ApplicationConfig.getInstance();
		Properties rtl_languages = config.getProperties("rtl_languages");
		
		String rtl_lang_str =""; 
		if(rtl_languages !=null && !rtl_languages.isEmpty()){
			Iterator keys = rtl_languages.keySet().iterator();
			while(keys.hasNext()){
				String key = (String)keys.next();
				if(((String)rtl_languages.get(key)).equalsIgnoreCase("RTL")){
					rtl_lang_str = rtl_lang_str +key+",";
				}
			}
			if(rtl_lang_str.endsWith(","))
				rtl_lang_str = rtl_lang_str.substring(0,rtl_lang_str.length()-1);
		}
		session.setAttribute(ApplicationConstants.ATTR_RIGHT_TO_LEFT_LANGS,rtl_lang_str);
		
		
		try {
			// check session
			int textId = Integer.valueOf((String) session
					.getAttribute(ApplicationConstants.REQUEST_TEXT_ID));
			if (textId == 0) {
				session.setAttribute(ApplicationConstants.ERRMSG,
						ApplicationConstants.ERROR_NO_TEXTID);
			} else {
				TbbuTextTypesDAO tbbuTextTypesDAOImpl = new TbbuTextTypesDAOImpl();

				tbbuTextTypesBO = tbbuTextTypesDAOImpl.getTextDetails(hibernateUtil,textId);
			}

			if (tbbuTextTypesBO != null) {
				request.setAttribute(ApplicationConstants.ATTR_TEXT_DETAIL,
						tbbuTextTypesBO);

				if (tbbuTextTypesBO.getTextType() == Integer
						.parseInt(ApplicationConstants.ATTR_TEXT_TYPE_2)) {
					tbbuTextTypesBO.setTextTypeDescription(ApplicationConstants.ATTR_TEXT_TYPE_2_DES);
					request.setAttribute(ApplicationConstants.ATTR_TEXT_DETAIL,
							tbbuTextTypesBO);
					TbbuLanguagesDAOImpl tbbuLanguagesDAOImpl = new TbbuLanguagesDAOImpl();
					TbbuTextTranslationsBO tbbuTextTranslationsBO=tbbuLanguagesDAOImpl.getTextTranslationsSEQ(hibernateUtil, textId);
					if(tbbuTextTranslationsBO.getSeq() == 0 ){
						tbbuTextTranslationsBO.setSeq(1);
						request.setAttribute(ApplicationConstants.ATTR_TEXT_SEQ,
								tbbuTextTranslationsBO);
					}
					else
					{
						request.setAttribute(ApplicationConstants.ATTR_TEXT_SEQ,
								tbbuTextTranslationsBO);
					}
					List<TbbuLanguagesBO> tbbuLanguagesUniBOList = tbbuLanguagesDAOImpl
							.getUnicodeLanguage(hibernateUtil);
					List<TbbuLanguagesBO> filterdLang = tbbuLanguagesDAOImpl.getFilteredUnicodeLanguage(hibernateUtil,tbbuLanguagesUniBOList,textId);
					if (filterdLang == null
							|| filterdLang.size() <= 0) {
						session.setAttribute(ApplicationConstants.ERRMSG,
								ApplicationConstants.ERROR_NO_UNICODE_LANG);
					} else {
						session.setAttribute(
								ApplicationConstants.ATTR_UNI_LANG_DETAIL,
								filterdLang);
						// fetch all the data in one go
						List<TbbuTextTranslationsUniBO> transaltionList = new TbbuTextTranslationsUniDAOImpl()
								.getTextTranslations(hibernateUtil,textId);
						session.setAttribute(
								ApplicationConstants.TEXT_TRANSLATION_LIST,
								transaltionList);

						TbbuTextTranslationsUniBO firstTransaltionBean = CommonUtil
								.getTransaltion(transaltionList,
										filterdLang.get(0)
												.getLangCode());

						session.setAttribute(
								ApplicationConstants.SELECTED_TEXT_TRANSLATION,
								firstTransaltionBean);
					}
				} else {
					// show error not supported
					session.setAttribute(ApplicationConstants.ERRMSG,
							ApplicationConstants.ERROR_NON_STD_INVOICE_TEXT);
				}
			} else {
				// show error ERROR_TEXT_DETAILS

				session.setAttribute(ApplicationConstants.ERRMSG,
						ApplicationConstants.ERROR_TEXT_DETAILS);
			}
		} catch (DataAccessException e) {
			session.setAttribute(ApplicationConstants.ERRMSG,ApplicationConstants.ERROR_DATABASE);
			logger.exception(e);
		}

		logger.exitMethod("execute method of MaintainTextController");

		return mapping.findForward(SUCCESS);

	}

}
