package com.atradius.dataaccess.hibernate;


import java.sql.CallableStatement;
import java.sql.SQLException;

import org.hibernate.Session;

import com.atradius.exception.DataAccessException;
import com.atradius.util.logging.ILogger;
import com.atradius.util.logging.LoggerFactory;

/**
 * @author VIJAY SARAF, INVSAR1
 * 
 */
public final class SARHelperHibernate {

	private static final ILogger LOGGER = LoggerFactory
			.getLogger(SARHelperHibernate.class);

	static SARHelperHibernate sarHelperHibernateImpl = null;

	/**
	 * private constructor for the singleton
	 */
	private SARHelperHibernate() {
		super();
	}

	/**
	 * SARHelperHibernateImpl is a singleton. This method is used to get the
	 * SARHelperHibernateImpl instance.
	 * 
	 * @return the instance of the SARHelperHibernateImpl.
	 */
	public static SARHelperHibernate getInstance() {
		if (sarHelperHibernateImpl == null) {
			sarHelperHibernateImpl = new SARHelperHibernate();
		}
		return sarHelperHibernateImpl;
	}

	/**
	 * Check user security roles for the connection.
	 * 
	 * @param connection
	 *            the Connection to check the SAR
	 * @exception DataAccessException
	 *                thrown when there is an exception. From Hibernate or not.
	 */
	public void checkSAR(Session session) throws DataAccessException {
        LOGGER.enterMethod("checkSAR");
        try {
        	// Calling sec_roles1 sp.
        	CallableStatement secRoles = session.connection().prepareCall("{call " +
        	"sec_roles1 ()}");          
        	// Execute the call to sec_roles1.
        	LOGGER.debug("about to call sec_roles1");
        	secRoles.execute();
        	LOGGER.debug("calling sec_roles1 OK.");
        	secRoles.close();
        }
        catch (SQLException excpt) {
        	LOGGER.debug("SAR exception" + excpt);
        	LOGGER.exception(excpt);
        	throw new DataAccessException("SAR_EXCEPTION", excpt);
        }catch (RuntimeException rte) {
        	LOGGER.debug("SAR runtime exception" + rte);
        	LOGGER.exception(rte);
        	throw new DataAccessException("SAR_EXCEPTION", rte);
        } 
        LOGGER.exitMethod("checkSAR");
	}
}
