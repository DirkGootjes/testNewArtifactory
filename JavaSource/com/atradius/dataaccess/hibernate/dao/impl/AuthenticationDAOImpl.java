/*********************************************************************/
/*                           FILE HEADER                             */
/*********************************************************************/
/*                                                                   */
/*  FileName: 		AuthenticationDAOImpl.java             	  	     */
/*  																 */
/*  $Author: INVSAR1 $									             */
/*																	 */
/*  $Revision: 1.8 $										         */
/*  																 */
/*  $Date: 2013/08/16 11:58:17 $                                     */
/*                                                                   */
/*  Description: 	This class has method for Password decryption    */
/*				                       				                 */
/*********************************************************************/
/* Date        Name            Version             Comments          */
/*-------------------------------------------------------------------*/
/* 15/04/2013  INVSAR1      	1.0         Initial version created  */
/*********************************************************************/
package com.atradius.dataaccess.hibernate.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

import org.hibernate.HibernateException;

import com.atradius.exception.DataAccessException;
import com.atradius.util.dataaccess.DBConnectionManager;
import com.atradius.util.logging.ILogger;
import com.atradius.util.logging.LoggerFactory;

public class AuthenticationDAOImpl {

	private static ILogger logger = LoggerFactory
			.getLogger(AuthenticationDAOImpl.class);

	// Method for Password decryption
	public static String decryptPassword(String encryptedPassword, int randomKey) {
		logger.enterMethod("decryptedPassword");
		String decryptedPassword = null;
		Connection connection = null;
		CallableStatement cstmt = null;
		logger.debug("encryptedPassword=" + encryptedPassword);
		try {

			logger
					.debug("encryptedPassword after session="
							+ encryptedPassword);
			connection = DBConnectionManager.getInstance().getConnection();
			cstmt = connection
					.prepareCall("{ ? = call cs0003p.decryptpwd(?,?,?,?) }");
			cstmt.registerOutParameter(1, Types.VARCHAR);
			cstmt.setString(2, encryptedPassword);
			cstmt.setInt(3, randomKey);
			cstmt.registerOutParameter(4, Types.VARCHAR);
			cstmt.registerOutParameter(5, Types.VARCHAR);

			logger
					.debug("Just before execution of cs0003p.decryptpwd(?,?,?,?)");
			cstmt.execute();
			logger.debug("Just after execution of cs0003p.decryptpwd(?,?,?,?)");

			decryptedPassword = cstmt.getString(1);
			if (decryptedPassword != null)
				logger.debug("got the decryptedPassword");
			String errorCode = cstmt.getString(4);
			String errorMsg = cstmt.getString(4);
			if (errorCode != null && errorCode.trim().length() > 0
					&& errorMsg != null && errorMsg.trim().length() > 0)
				decryptedPassword = null;

		} catch (HibernateException e) {
			logger.debug("%%%%%%%%%%%%%" + e.getMessage() + "%%%%%%%%%%%%%");
			logger.exception(e);
		} catch (SQLException e) {
			logger.debug("SQLException : %%%%%%%%%%%%%" + e.getMessage()
					+ "%%%%%%%%%%%%%");
			logger.exception(e);
		} catch (DataAccessException e) {
			logger.debug("DataAccessException : %%%%%%%%%%%%%" + e.getMessage()
					+ "%%%%%%%%%%%%%");
			logger.exception(e);
		} catch (Exception e) {
			logger.debug("Exception : %%%%%%%%%%%%%" + e.getMessage()
					+ "%%%%%%%%%%%%%");
			logger.exception(e);
		} finally {

			try {
				if (cstmt != null)
					cstmt.close();
			} catch (SQLException e) {
				logger.exception(e);
			}
			try {
				if (connection != null && !connection.isClosed())
					connection.close();
			} catch (SQLException e) {
				logger.exception(e);
			}
		}
		logger.exitMethod("decryptedPassword");
		return decryptedPassword;
	}
}
