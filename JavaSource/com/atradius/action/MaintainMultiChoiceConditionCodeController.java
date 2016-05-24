/*********************************************************************/
/*                           FILE HEADER                             */
/*********************************************************************/
/*                                                                   */
/*  FileName: 		MaintainMultiChoiceConditionCodeController.java             	  	 */
/*  																 */
/*  $Author: INASHA2 $									     */
/*																	 */
/*  $Revision: 1.2 $										     */
/*  																 */
/*  $Date: 2014/03/06 13:49:01 $                            */
/*                                                                   */
/*  Description: 	This action class performs the initial loading of*/
/*				  	condition code details					 */
/*********************************************************************/
/* Date        Name            Version             Comments          */
/*-------------------------------------------------------------------*/
/* 17/12/2013  INRSHR1      	1.0         Initial version created  */
/*********************************************************************/
package com.atradius.action;

import java.util.ArrayList;
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
import com.atradius.dataaccess.hibernate.bo.TbbuConditionMChoiceTextsBO;
import com.atradius.dataaccess.hibernate.bo.TbbuConditionMchoiceTypesBO;
import com.atradius.dataaccess.hibernate.bo.TbbuConditionTypesBO;
import com.atradius.dataaccess.hibernate.bo.TbbuMchoiceVarTypesBO;
import com.atradius.dataaccess.hibernate.dao.TbbuConditionTypesDAO;
import com.atradius.dataaccess.hibernate.dao.impl.TbbuConditionMChoiceTextsDAOImpl;
import com.atradius.dataaccess.hibernate.dao.impl.TbbuConditionMchoiceTypesDAOImpl;
import com.atradius.dataaccess.hibernate.dao.impl.TbbuConditionTypesDAOImpl;
import com.atradius.dataaccess.hibernate.dao.impl.TbbuMchoiceVarTypesDaoImpl;
import com.atradius.sessiondata.ApplicationConstants;
import com.atradius.sessiondata.UserDataObject;
import com.atradius.util.config.ApplicationConfig;
import com.atradius.util.logging.ILogger;
import com.atradius.util.logging.LoggerFactory;

public class MaintainMultiChoiceConditionCodeController extends Action{
	private static ILogger logger = LoggerFactory
	.getLogger(MaintainConditionCodeController.class);

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

		logger.enterMethod("MaintainMultiChoiceConditionCodeController");
		TbbuConditionTypesBO tbbuCondTypBO = new TbbuConditionTypesBO();
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
			if(rtl_lang_str.endsWith(",")){
				rtl_lang_str = rtl_lang_str.substring(0,rtl_lang_str.length()-1);
			}
				
		}
		session.setAttribute(ApplicationConstants.ATTR_RIGHT_TO_LEFT_LANGS,rtl_lang_str);
		
		
		try {
			// check session
			String bucdeCode = (String)session
					.getAttribute(ApplicationConstants.REQUEST_BUCDE_CODE);
			if (bucdeCode == null) {
				session.setAttribute(ApplicationConstants.ERRMSG,
						ApplicationConstants.ERROR_NO_TEXTID);
			} else {
				TbbuConditionTypesDAO tbbuCondTypDAO = new TbbuConditionTypesDAOImpl();

				tbbuCondTypBO = tbbuCondTypDAO.getConditionCodeDetails(hibernateUtil, bucdeCode);
			}

			if (tbbuCondTypBO != null) {
				request.setAttribute(ApplicationConstants.ATTR_CONDITIONCODE_DETAIL,
						tbbuCondTypBO);
				
				//Retrieving choice and its description
				List<TbbuConditionMchoiceTypesBO> choiceList= new ArrayList<TbbuConditionMchoiceTypesBO>();
				TbbuConditionMchoiceTypesDAOImpl tbbuCondMcDAOImpl= new TbbuConditionMchoiceTypesDAOImpl();
				choiceList=tbbuCondMcDAOImpl.getConditionMchoiceTypes(hibernateUtil, bucdeCode);
				
				if(!(choiceList.isEmpty())){
					request.setAttribute(ApplicationConstants.ATTR_CHOICE_DETAIL,
							choiceList); //choice_details
				}
				
				//Retrieving the values for language list and related translation
				// if translation is present in non latin then give priority otherwise print latin translation
				List<TbbuConditionMChoiceTextsBO> transaltionList= new ArrayList<TbbuConditionMChoiceTextsBO> ();		
				TbbuConditionMChoiceTextsDAOImpl daoImpl = new TbbuConditionMChoiceTextsDAOImpl();
				//For the first time print for choice 1
				transaltionList=daoImpl.getAllNonLatinTranslationList(hibernateUtil, bucdeCode,1);
				request.setAttribute(ApplicationConstants.ATTR_NONLATIN_TEXT_DETAIL,
						transaltionList);
				
				//Retrieving the values for condition variables like DAT,TEXT
				TbbuMchoiceVarTypesDaoImpl daoImplObject= new TbbuMchoiceVarTypesDaoImpl();
				List<TbbuMchoiceVarTypesBO> condVarTypBOList = new ArrayList<TbbuMchoiceVarTypesBO>();
				condVarTypBOList=daoImplObject.getMchoiceConditionVarTypes(hibernateUtil, bucdeCode,1);
				request.setAttribute(ApplicationConstants.ATTR_CONDITION_VAR_TYPES,
						condVarTypBOList);
					}
					
		} catch (Exception e) {
			session.setAttribute(ApplicationConstants.ERRMSG,ApplicationConstants.ERROR_DATABASE);
			logger.exception(e);
		} 

		logger.exitMethod("execute method of MaintainMultiChoiceConditionCodeController");

		return mapping.findForward(SUCCESS);

	}
}
