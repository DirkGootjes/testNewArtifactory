/*********************************************************************/
/*                           FILE HEADER                             */
/*********************************************************************/
/*                                                                   */
/*  FileName: 		UnicodeSessionListener.java               	     */
/*  																 */
/*  $Author: INASHA2 $									             */
/*																	 */
/*  $Revision: 1.11 $										         */
/*  																 */
/*  $Date: 2014/03/06 13:50:12 $                                     */
/*                                                                   */
/*  Description: 	This class create and destroy session        	 */
/*********************************************************************/
/* Date        Name            Version             Comments          */
/*-------------------------------------------------------------------*/
/* 04/04/2013  INVSAR1      	1.0         Initial version created  */
/*********************************************************************/
package com.atradius.web.listener;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.atradius.sessiondata.ApplicationConstants;

/**
 * @author GNINFO7
 * 
 */
public class UnicodeSessionListener implements HttpSessionListener {

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.http.HttpSessionListener#sessionCreated(javax.servlet.http.HttpSessionEvent)
	 */
	public void sessionCreated(HttpSessionEvent arg0) {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.http.HttpSessionListener#sessionDestroyed(javax.servlet.http.HttpSessionEvent)
	 */
	public void sessionDestroyed(HttpSessionEvent arg0) {
		HttpSession session = arg0.getSession();

		session.removeAttribute(ApplicationConstants.USER_DATA_BEAN);
		session.removeAttribute(ApplicationConstants.HIBERNATE_UTIL);

	}

}
