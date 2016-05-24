/*********************************************************************/
/*                           FILE HEADER                             */
/*********************************************************************/
/*                                                                   */
/*  FileName: 		CommonAJAX.java               	         	     */
/*  																 */
/*  $Author: INASHA2 $									             */
/*																	 */
/*  $Revision: 1.11 $										         */
/*  																 */
/*  $Date: 2014/03/06 13:50:12 $                                     */
/*                                                                   */
/*  Description: 	This java class is used for common AJAX call     */
/*				  					                                 */
/*********************************************************************/
/* Date        Name            Version             Comments          */
/*-------------------------------------------------------------------*/
/* 04/04/2013  INVSAR1      	1.0         Initial version created  */
/*********************************************************************/
package com.atradius.web.dwr;

import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.http.HttpSession;

import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;

import com.atradius.dataaccess.hibernate.HibernateUtil;
import com.atradius.dataaccess.hibernate.bo.TbcsAuthenticationCodeBO;
import com.atradius.dataaccess.hibernate.dao.impl.TbcsAuthenticationCodeDAOImpl;
import com.atradius.sessiondata.ApplicationConstants;
import com.atradius.util.logging.ILogger;
import com.atradius.util.logging.LoggerFactory;

public class CommonAJAX {
	private static ILogger logger = LoggerFactory.getLogger(CommonAJAX.class);

	public void startTimer() {
		logger.enterMethod("startTimer");
		WebContext ctx = WebContextFactory.get();
		HttpSession session = ctx.getSession();
		Timer timer = new Timer();
		LogOffTask task = new LogOffTask();
		task.setSession(session);
		session.setAttribute("TIMER", timer);
		timer.schedule(task, 10 * 1000);
		logger.exitMethod("startTimer");
	}

	public void stopTimer() {
		logger.enterMethod("stopTimer");
		WebContext ctx = WebContextFactory.get();
		HttpSession session = ctx.getSession();
		Timer timer = (Timer) session.getAttribute("TIMER");
		if (timer != null) {
			timer.cancel();
			timer.purge();
			timer = null;
			session.removeAttribute("TIMER");
		}
		logger.exitMethod("stopTimer");
	}

}

class LogOffTask extends TimerTask {
	private HttpSession session;

	private static ILogger logger = LoggerFactory.getLogger(LogOffTask.class);

	@Override
	public void run() {
		logger.enterMethod("run");
		try {
			if (getSession() != null) {
				HibernateUtil hibernateUtil = (HibernateUtil) session
						.getAttribute(ApplicationConstants.HIBERNATE_UTIL);

				TbcsAuthenticationCodeBO mTbcsAuthenticationCode = (TbcsAuthenticationCodeBO) session
						.getAttribute(ApplicationConstants.ATTR_AUTHENTICATION_CODE);

				if (hibernateUtil != null && mTbcsAuthenticationCode != null) {
					TbcsAuthenticationCodeDAOImpl.deActivateSession(
							hibernateUtil, mTbcsAuthenticationCode);
					session
							.removeAttribute(ApplicationConstants.USER_DATA_BEAN);
					session.invalidate();
				}
			}
		} catch (IllegalStateException ise) {
			logger.exception(ise);
		} catch (RuntimeException e) {
			logger.exception(e);
		}
		logger.exitMethod("run");
	}

	public HttpSession getSession() {
		return session;
	}

	public void setSession(HttpSession session) {
		this.session = session;
	}

}
