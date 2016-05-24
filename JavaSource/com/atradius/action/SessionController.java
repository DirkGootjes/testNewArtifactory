/*********************************************************************/
/*                           FILE HEADER                             */
/*********************************************************************/
/*                                                                   */
/*  FileName: 		ApplicationController.java             	  	 */
/*  																 */
/*  $Author: INASHA2 $									     */
/*																	 */
/*  $Revision: 1.5 $										     */
/*  																 */
/*  $Date: 2014/03/06 13:49:01 $                            */
/*                                                                   */
/*  Description: 	This action class performs the initial loading of*/
/*				  	organisation contact details					 */
/*********************************************************************/
/* Date        Name            Version             Comments          */
/*-------------------------------------------------------------------*/
/* 04/04/2011  INVSAR1      	1.0         Initial version created  */
/*********************************************************************/
// package
package com.atradius.action;

// imports
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.atradius.dataaccess.hibernate.HibernateUtil;
import com.atradius.dataaccess.hibernate.bo.TborSystemUsersBO;
import com.atradius.dataaccess.hibernate.dao.impl.TbcsAuthenticationCodeDAOImpl;
import com.atradius.sessiondata.ApplicationConstants;
import com.atradius.util.logging.ILogger;
import com.atradius.util.logging.LoggerFactory;


public class SessionController extends Action {

	private static ILogger logger = LoggerFactory
			.getLogger(ApplicationController.class);

	private static String INFO = "success";
	
	/**
	 * Invalidates the session
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

		logger.enterMethod("execute method of SessionController");
		HttpSession session = request.getSession();

		TborSystemUsersBO mTborSystemUsersBO = (TborSystemUsersBO) session
				.getAttribute(ApplicationConstants.ATTR_SYSTEM_USER);
		if (mTborSystemUsersBO != null) {
			int userId = mTborSystemUsersBO.getId();
			HibernateUtil hibernateUtil = (HibernateUtil)session.getAttribute(ApplicationConstants.HIBERNATE_UTIL);
			TbcsAuthenticationCodeDAOImpl.deActivateSessionsByUserId(hibernateUtil,userId);

			session.removeAttribute(ApplicationConstants.USER_DATA_BEAN);

			// just invalidate the session

			session.invalidate();
			if (request.getParameter("kill") == null) {
				request.setAttribute(ApplicationConstants.INFOMSG,
						ApplicationConstants.INFO_SESSION_CLOSED);
			} else {
				request.setAttribute(ApplicationConstants.INFOMSG,
						ApplicationConstants.INFO_SESSIONS_KILLED);
			}
		} else {
			request.setAttribute(ApplicationConstants.INFOMSG,
					ApplicationConstants.ERROR_INVALID_SESSION);
		}
		logger.exitMethod("execute method of SessionController");
		return mapping.findForward(INFO);

	}

}
