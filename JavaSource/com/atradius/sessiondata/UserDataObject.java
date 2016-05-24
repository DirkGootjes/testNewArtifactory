/*********************************************************************/
/*                           FILE HEADER                             */
/*********************************************************************/
/*                                                                   */
/*  FileName: 		UserDataObject.java               	         	 */
/*  																 */
/*  $Author: INVSAR1 $									     		 */
/*																	 */			
/*  $Revision: 1.3 $										     	 */
/*  																 */		
/*  $Date: 2013/05/31 12:16:59 $                                     */
/*                                                                   */
/*  Description: 	This Class contains the informations for  		 */
/*				  	logged-in user.									 */			
/*********************************************************************/
/* Date        Name            Version             Comments          */
/*-------------------------------------------------------------------*/
/* 04/04/2013  INVSAR1      	1.0         Initial version created  */
/*********************************************************************/
package com.atradius.sessiondata;

import java.io.Serializable;

public class UserDataObject implements Serializable, Cloneable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 403113690976956634L;

	private String userId;

	private String password;

	private boolean isLoggedIn;

	/**
	 * @return Returns the isLoggedIn.
	 */
	public boolean isLoggedIn() {
		return isLoggedIn;
	}

	/**
	 * @param isLoggedIn
	 *            The isLoggedIn to set.
	 */
	public void setIsLoggedIn(boolean isLoggedIn) {
		this.isLoggedIn = isLoggedIn;
	}

	/**
	 * @return Returns the password.
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            The password to set.
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return Returns the userId.
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId
	 *            The userId to set.
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
}// end class

