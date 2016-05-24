/*********************************************************************/
/*                           FILE HEADER                             */
/*********************************************************************/
/*                                                                   */
/*  FileName: 		UnicodeContextListener.java               	     */
/*  																 */
/*  $Author: INVSAR1 $									             */
/*																	 */
/*  $Revision: 1.10 $										         */
/*  																 */
/*  $Date: 2013/08/16 11:56:42 $                                     */
/*                                                                   */
/*  Description: 	This class initialize and destroy context        */
/*********************************************************************/
/* Date        Name            Version             Comments          */
/*-------------------------------------------------------------------*/
/* 04/04/2013  INVSAR1      	1.0         Initial version created  */
/*********************************************************************/
package com.atradius.web.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.atradius.util.logging.ILogger;
import com.atradius.util.logging.LoggerFactory;

public class UnicodeContextListener implements ServletContextListener {
	private static ILogger logger = LoggerFactory
	.getLogger(UnicodeContextListener.class);
    
	public void contextDestroyed(ServletContextEvent arg0) {
	}

	public void contextInitialized(ServletContextEvent arg0) {
	}

}
