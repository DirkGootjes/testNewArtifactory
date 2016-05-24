/*********************************************************************/
/*                           FILE HEADER                             */
/*********************************************************************/
/*                                                                   */
/*  FileName: 		HibernateCommonUtil.java             	  	     */
/*  																 */
/*  $Author: INVSAR1 $									             */
/*																	 */
/*  $Revision: 1.1 $										         */
/*  																 */
/*  $Date: 2013/08/16 10:59:59 $                                     */
/*                                                                   */
/*  Description: 	This java class keeps utility  for hibernate     */
/*				                       				                 */
/*********************************************************************/
/* Date        Name            Version             Comments          */
/*-------------------------------------------------------------------*/
/* 15/04/2013  INVSAR1      	1.0         Initial version created  */
/*********************************************************************/
package com.atradius.dataaccess.hibernate;

import java.sql.Connection;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.hibernate.HibernateException;
import org.hibernate.Interceptor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import com.atradius.exception.DataAccessException;
import com.atradius.util.dataaccess.DBConnectionManager;
import com.atradius.util.logging.ILogger;
import com.atradius.util.logging.LoggerFactory;

/**
 * Basic Hibernate helper class, handles SessionFactory, Session and
 * Transaction.
 * <p>
 * Uses a static initializer for the initial SessionFactory creation and holds
 * Session and Transactions in thread local variables. All exceptions are
 * wrapped in an unchecked InfrastructureException.
 * 
 * @author christian@hibernate.org
 */
public class HibernateCommonUtilOLD {

	private static ILogger log = LoggerFactory
			.getLogger(HibernateCommonUtilOLD.class);

	// private static Configuration configuration;
	private static AnnotationConfiguration configuration = new AnnotationConfiguration();

	private static SessionFactory sessionFactory;

	private static final ThreadLocal<Session> threadSession = new ThreadLocal<Session>();
	
	private static final ThreadLocal<String> threadUserId = new ThreadLocal<String>();
	
	private static final ThreadLocal<String> threadPassword = new ThreadLocal<String>();

	private static final ThreadLocal<Transaction> threadTransaction = new ThreadLocal<Transaction>();

	private static final ThreadLocal<Interceptor> threadInterceptor = new ThreadLocal<Interceptor>();

	private static String CONFIG_FILE_LOCATION = "/WEB-INF/config/framework/hibernate.cfg.xml";
	
	private static  DBConnectionManager dbMgr = DBConnectionManager.getInstance();
	
	/**
	    * Single instance of the secure application role helper.
	    */
	    private static final SARHelperHibernate sarHelper = SARHelperHibernate
				.getInstance();    
	
	static {

		try {
			configuration.configure(CONFIG_FILE_LOCATION);
			String sessionFactoryJndiName = configuration
					.getProperty(Environment.SESSION_FACTORY_NAME);
			log.info("sessionFactoryJndiName" + sessionFactoryJndiName);
			if (sessionFactoryJndiName != null) {
				configuration.buildSessionFactory();
				log.debug("get a jndi session factory");
				sessionFactory = (SessionFactory) (new InitialContext())
						.lookup(sessionFactoryJndiName);
			} else {
				log.debug("classic factory");
				sessionFactory = configuration.buildSessionFactory();
			}
		} catch (NamingException e) {
			log.error("%%%% JNDI Error while Creating HibernateSessionFactory %%%%");
			log.exception(e);
			throw new ExceptionInInitializerError(
					"Could not initialize the Hibernate configuration");
		} catch (HibernateException e) {
			log.error("%%%%Hibernate Error while Creating HibernateSessionFactory %%%%");
			log.exception(e);
			throw new ExceptionInInitializerError(
					"Could not initialize the Hibernate configuration");
		} catch (NoClassDefFoundError ex) {
			log.error("Building SessionFactory failed.", ex);
			throw new ExceptionInInitializerError(ex);
		}

	}

	/**
	 * Returns the SessionFactory used for this static class.
	 * 
	 * @return SessionFactory
	 */
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	/**
	 * Returns the original Hibernate configuration.
	 * 
	 * @return Configuration
	 */
	public static Configuration getConfiguration() {
		return configuration;
	}

	/**
	 * Rebuild the SessionFactory with the static Configuration.
	 * 
	 */
	public static void rebuildSessionFactory() {
		synchronized (sessionFactory) {
			try {
				sessionFactory = getConfiguration().buildSessionFactory();
			} catch (HibernateException ex) {
				log.exception(ex);
				throw ex;
			}
		}
	}

	/**
	 * Rebuild the SessionFactory with the given Hibernate Configuration.
	 * 
	 * @param cfg
	 */
	public static void rebuildSessionFactory(AnnotationConfiguration cfg) {
		synchronized (sessionFactory) {
			try {
				sessionFactory = cfg.buildSessionFactory();
				configuration = cfg;
			} catch (HibernateException ex) {
				log.exception(ex);
				throw ex;
			}
		}
	}

	/**
	 * Retrieves the current Session local to the thread. <p/> If no Session is
	 * open, opens a new Session for the running thread.
	 * 
	 * @return Session
	 * @throws DataAccessException 
	 */
	public synchronized static Session getSession()  {
		Session s = (Session) threadSession.get();
		try {
			if (s == null) {
				log.debug("Opening new Session for this thread.");
				Connection connection = dbMgr.getConnection(threadUserId.get(), threadPassword.get());
				if (getInterceptor() != null) {
					log.debug("Using interceptor: "
							+ getInterceptor().getClass());
					s = getSessionFactory().openSession(connection,getInterceptor());
				} else {
					s = getSessionFactory().openSession(connection);
				}
				//sarHelper.checkSAR(s);
				threadSession.set(s);
			}
		} catch (HibernateException ex) {
			log.exception(ex);
			throw ex;
		} catch (DataAccessException e) {
			log.exception(e);
			//throw e;
		}
		return s;
	}
	public static void initializeThreadLocals(String userID,String password) throws DataAccessException{
		threadUserId.set(userID);
		threadPassword.set(password);
		//Connection connection = dbMgr.getConnection(userID, password);
	}
	

	/**
	 * Closes the Session local to the thread.
	 */
	public static void closeSession() {
		try {
			Session s = (Session) threadSession.get();
			threadSession.set(null);
			if (s != null && s.isOpen()) {
				log.debug("Closing Session of this thread.");
				s.close();
			}
		} catch (HibernateException ex) {
			log.exception(ex);
			throw ex;
		}
	}

	/**
	 * Start a new database transaction.
	 */
	public static void beginTransaction() {
		Transaction tx = (Transaction) threadTransaction.get();
		try {
			if (tx == null) {
				log.debug("Starting new database transaction in this thread.");
				tx = getSession().beginTransaction();
				threadTransaction.set(tx);
			}
		} catch (HibernateException ex) {
			log.exception(ex);
			throw ex;
		}
	}

	/**
	 * Commit the database transaction.
	 */
	public static void commitTransaction() {
		Transaction tx = (Transaction) threadTransaction.get();
		try {
			if (tx != null && !tx.wasCommitted() && !tx.wasRolledBack()) {
				log.debug("Committing database transaction of this thread.");
				tx.commit();
			}
			threadTransaction.set(null);
		} catch (HibernateException ex) {
			rollbackTransaction();
			log.exception(ex);
			throw ex;
		}
	}

	/**
	 * Commit the database transaction.
	 */
	public static void rollbackTransaction() {
		Transaction tx = (Transaction) threadTransaction.get();
		try {
			threadTransaction.set(null);
			if (tx != null && !tx.wasCommitted() && !tx.wasRolledBack()) {
				log
						.debug("Tyring to rollback database transaction of this thread.");
				tx.rollback();
			}
		} catch (HibernateException ex) {
			log.exception(ex);
			throw ex;
		} finally {
			closeSession();
		}
	}

	/**
	 * Reconnects a Hibernate Session to the current Thread.
	 * 
	 * @param session
	 *            The Hibernate Session to be reconnected.
	 */
	@SuppressWarnings("deprecation")
	public static void reconnect(Session session) {
		try {
			session.reconnect();
			threadSession.set(session);
		} catch (HibernateException ex) {
			log.exception(ex);
			throw ex;
		}
	}

	/**
	 * Disconnect and return Session from current Thread.
	 * 
	 * @return Session the disconnected Session
	 */
	public static Session disconnectSession() {

		Session session = getSession();
		try {
			threadSession.set(null);
			if (session.isConnected() && session.isOpen())
				session.disconnect();
		} catch (HibernateException ex) {
			log.exception(ex);
			throw ex;
		}
		return session;
	}

	/**
	 * Register a Hibernate interceptor with the current thread.
	 * <p>
	 * Every Session opened is opened with this interceptor after registration.
	 * Has no effect if the current Session of the thread is already open,
	 * effective on next close()/getSession().
	 */
	public static void registerInterceptor(Interceptor interceptor) {
		threadInterceptor.set(interceptor);
	}

	private static Interceptor getInterceptor() {
		Interceptor interceptor = (Interceptor) threadInterceptor.get();
		return interceptor;
	}
	

}
