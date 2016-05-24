/*********************************************************************/
/*                           FILE HEADER                             */
/*********************************************************************/
/*                                                                   */
/*  FileName: 		HibernateUtil.java             	  	     		 */
/*  																 */
/*  $Author: INVSAR1 $									             */
/*																	 */
/*  $Revision: 1.14 $										         */
/*  																 */
/*  $Date: 2014/10/17 08:24:06 $                                     */
/*                                                                   */
/*  Description: 	This java class keeps utility  for hibernate     */
/*				                       				                 */
/*********************************************************************/
/* Date        Name            Version             Comments          */
/*-------------------------------------------------------------------*/
/* 15/04/2013  INVSAR1      	1.0         Initial version created  */
/*********************************************************************/
package com.atradius.dataaccess.hibernate;

import java.io.Serializable;
import java.sql.Connection;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Environment;

import com.atradius.exception.DataAccessException;
import com.atradius.util.dataaccess.DBConnectionManager;
import com.atradius.util.logging.ILogger;
import com.atradius.util.logging.LoggerFactory;

public class HibernateUtil implements Serializable {

	private static ILogger logger = LoggerFactory
			.getLogger(HibernateUtil.class);

	private static String CONFIG_FILE_LOCATION = "/WEB-INF/config/framework/hibernate.cfg.xml";

	// private Configuration configuration;
	private static AnnotationConfiguration configuration = new AnnotationConfiguration();

	private static SessionFactory sessionFactory;

	private static boolean isInitialized = false;

	private String userName;

	private String password;

	private Session session;

	private DBConnectionManager dbMgr = DBConnectionManager.getInstance();

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public HibernateUtil() {
		super();
	}

	public void reIntializeUtil() {
		initializeUtil(this.userName, this.password);
	}

	private synchronized static void configureAndBuildFactory() {
		if (!isInitialized) {
			try {
				configuration.configure(CONFIG_FILE_LOCATION);
				String sessionFactoryJndiName = configuration
						.getProperty(Environment.SESSION_FACTORY_NAME);

				if (sessionFactoryJndiName != null) {
					configuration.buildSessionFactory();
					logger.debug("get a jndi session factory");
					try {
						sessionFactory = (SessionFactory) (new InitialContext())
								.lookup(sessionFactoryJndiName);
						isInitialized = true;
					} catch (NamingException e) {
						// e.printStackTrace();
						logger.exception(e);
					}
				} else {
					logger.debug("classic factory");
					sessionFactory = configuration.buildSessionFactory();
					isInitialized = true;
				}
			} catch (HibernateException e) {
				logger.exception(e);
				throw e;
			}
		}
	}

	public void initializeUtil(String userName, String password) {
		logger.enterMethod("initializeUtil");
		setUserName(userName);
		setPassword(password);
		configureAndBuildFactory();
		logger.exitMethod("initializeUtil");
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Session getSession() {
		logger.enterMethod("getSession");
		if (getCurrentSession() == null || !getCurrentSession().isOpen()) {
			try {
				Connection connection = dbMgr.getConnWithRoles(getUserName(),
						getPassword());
				session = sessionFactory.openSession(connection);
				logger.exitMethod("getSession");
				return session;
			} catch (DataAccessException e) {
				logger.exception(e);
				// e.printStackTrace();
				logger.exitMethod("getSession");
				throw new RuntimeException(e.getMessage());
			}
		} else {
			logger.exitMethod("getSession");
			return getCurrentSession();
		}

	}

	/*
	 * private void prepareConnection() throws DataAccessException {
	 * logger.enterMethod("prepareConnection"); this.connection =
	 * dbMgr.getConnection(getUserName(), getPassword());
	 * logger.exitMethod("prepareConnection"); }
	 */

	public void doCleanup() {
		logger.enterMethod("doCleanup");
		if (getCurrentSession() != null && getCurrentSession().isOpen())
			getCurrentSession().close();
		logger.exitMethod("doCleanup");
	}

	/*
	 * private Connection getConnection() { return connection; }
	 */

	public Session getCurrentSession() {
		return this.session;
	}

	/*
	 * public void releaseConnection() { if (getConnection() != null) { try {
	 * getConnection().close(); } catch (SQLException e) { logger.exception(e); } } }
	 */

}
