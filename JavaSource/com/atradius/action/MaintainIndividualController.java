/*********************************************************************/
/*                           FILE HEADER                             */
/*********************************************************************/
/*                                                                   */
/*  FileName: 		MaintainIndividualController.java         	  	 */
/*  																 */
/*  $Author: INVSAR1 $									     */
/*																	 */
/*  $Revision: 1.12 $										     */
/*  																 */
/*  $Date: 2013/08/16 12:46:12 $                            */
/*                                                                   */
/*  Description: 	This action class performs the initial loading of*/
/*				  	individual details					 */
/*********************************************************************/
/* Date        Name            Version             Comments          */
/*-------------------------------------------------------------------*/
/* 04/04/2013  INVSAR1      	1.0         Initial version created  */
/*********************************************************************/
// package
package com.atradius.action;

// imports
import java.util.Iterator;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.hibernate.Session;

import com.atradius.dataaccess.hibernate.HibernateUtil;
import com.atradius.dataaccess.hibernate.bo.TborIndividualsBO;
import com.atradius.dataaccess.hibernate.bo.TborIndividualsUnicodeBO;
import com.atradius.dataaccess.hibernate.bo.TborSystemUsersBO;
import com.atradius.dataaccess.hibernate.dao.impl.TborIndividualsDAOImpl;
import com.atradius.dataaccess.hibernate.dao.impl.TborIndividualsUnicodeDAOImpl;
import com.atradius.dataaccess.hibernate.dao.impl.TborSystemUsersDAOImpl;
import com.atradius.exception.DataAccessException;
import com.atradius.sessiondata.ApplicationConstants;
import com.atradius.sessiondata.UserDataObject;
import com.atradius.util.config.ApplicationConfig;
import com.atradius.util.logging.ILogger;
import com.atradius.util.logging.LoggerFactory;


public class MaintainIndividualController extends Action {

	private static ILogger logger = LoggerFactory
			.getLogger(MaintainIndividualController.class);

	private static String SUCCESS = "success";

	private static String ERROR = "error";
	
	/**
	 * Does the initial processing before going to the MaintainIndividualDetails.jsp
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

		logger.enterMethod("execute method of MaintainIndividualController");
		TborIndividualsBO tborIndividualsBO = new TborIndividualsBO();
		TborSystemUsersBO tborSystemUsersBO = new TborSystemUsersBO();
		HttpSession session = request.getSession();
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
		
		try {
			// check session
			int individualId = Integer.valueOf((String) session
					.getAttribute(ApplicationConstants.REQUEST_INDIVIDUAL_ID));
			if (individualId == 0) {
				session.setAttribute(ApplicationConstants.ERRMSG,
						ApplicationConstants.ERROR_NO_INDVID);
			} else {

				tborIndividualsBO = TborIndividualsDAOImpl
						.getIndividualDetails(hibernateUtil, individualId);

				tborSystemUsersBO = TborSystemUsersDAOImpl.findIndivDetails(
						hibernateUtil, individualId);

				if (tborSystemUsersBO != null) {
					request.setAttribute(
							ApplicationConstants.ATTR_INDV_SYS_USER,
							tborSystemUsersBO);
				}
				if (tborIndividualsBO != null) {
					request.setAttribute(ApplicationConstants.ATTR_INDV_DETAIL,
							tborIndividualsBO);

					// fetch the record
					Session hibernateSession = hibernateUtil
							.getSession();
					TborIndividualsUnicodeBO mTborIndividualsUnicodeBO = TborIndividualsUnicodeDAOImpl
							.getUnicodeVersion(hibernateSession, individualId,
									tborIndividualsBO.getBulaeLangCode());
					
					session.setAttribute(
							ApplicationConstants.SELECTED_INDV_TRANSLATION,
							mTborIndividualsUnicodeBO);
					
					ApplicationConfig config = ApplicationConfig.getInstance();
					Properties rtl_languages = config.getProperties("rtl_languages");
					
					boolean isRightToLeft = false; 
					if(rtl_languages !=null && !rtl_languages.isEmpty()){
						Iterator keys = rtl_languages.keySet().iterator();
						while(keys.hasNext()){
							String key = (String)keys.next();
							if(tborIndividualsBO.getBulaeLangCode().equals(key)){
								if(((String)rtl_languages.get(key)).equalsIgnoreCase("RTL")){
									isRightToLeft = false;
								}
							}
						}
					}
					session.setAttribute(ApplicationConstants.ATTR_RIGHT_TO_LEFT_FLAG,isRightToLeft);
					

					logger
							.exitMethod("execute method of MaintainIndividualController");

					return mapping.findForward(SUCCESS);

				} else {
					session.setAttribute(ApplicationConstants.ERRMSG,
							ApplicationConstants.ERROR_INDV_DETAILS);
				}

			}
			logger.exitMethod("execute method of MaintainIndividualController");
		} catch (DataAccessException e) {
			logger.exception(e);
			session.setAttribute(ApplicationConstants.ERRMSG,
					ApplicationConstants.ERROR_DATABASE);
		}
		return mapping.findForward(SUCCESS);
	}
}