/*********************************************************************/
/*                           FILE HEADER                             */
/*********************************************************************/
/*                                                                   */
/*  FileName: 		TbcsAuthenticationCodeBO.java             	     */
/*  																 */
/*  $Author: INVSAR1 $									             */
/*																	 */
/*  $Revision: 1.4 $										         */
/*  																 */
/*  $Date: 2013/05/30 04:39:52 $                                     */
/*                                                                   */
/*  Description: 	This class reprent the mappping with             */
/*				  	tbcs_authentication_code table					 */
/*				                   					                 */
/*********************************************************************/
/* Date        Name            Version             Comments          */
/*-------------------------------------------------------------------*/
/* 20/04/2013  INRSHR1      	1.0         Initial version created  */
/*********************************************************************/
package com.atradius.dataaccess.hibernate.bo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tbcs_authentication_code")
public class TbcsAuthenticationCodeBO implements Serializable {

	private static final long serialVersionUID = 1938475983745L;

	private int randomKey;

	private int userId;

	private String sessionFlag;

	private Date loginTime;

	@Id
	@Column(updatable = false, insertable = false, name = "RANDOM_KEY", nullable = false, length = 14)
	public int getRandomKey() {
		return randomKey;
	}

	public void setRandomKey(int randomKey) {
		this.randomKey = randomKey;
	}

	@Column(updatable = false, insertable = false, name = "USER_ID", nullable = false)
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	@Column(updatable = false, insertable = false, name = "LOGIN_TIMESTAMP", nullable = false)
	public Date getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}

	@Column(updatable = true, insertable = true, name = "SESSION_FLAG", nullable = false, length = 1)
	public String getSessionFlag() {
		return sessionFlag;
	}

	public void setSessionFlag(String sessionFlag) {
		this.sessionFlag = sessionFlag;
	}

}
