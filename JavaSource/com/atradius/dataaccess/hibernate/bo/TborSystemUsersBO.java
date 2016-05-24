/*********************************************************************/
/*                           FILE HEADER                             */
/*********************************************************************/
/*                                                                   */
/*  FileName: 		TborSystemUsersBO.java             	  	         */
/*  																 */
/*  $Author: INVSAR1 $									             */
/*																	 */
/*  $Revision: 1.3 $										         */
/*  																 */
/*  $Date: 2013/05/31 12:16:58 $                                     */
/*                                                                   */
/*  Description: 	This class reprent the mappping with             */
/*				  	tbor_system_users table							 */
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
@Table(name = "tbor_system_users")
public class TborSystemUsersBO implements Serializable {

	private static final long serialVersionUID = -19384898753745L;

	private int id;

	private int indivId;

	private String indivUid;

	private Date effectToDate;

	@Id
	@Column(updatable = false, insertable = false, name = "ID", nullable = false, length = 14)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(updatable = false, insertable = false, name = "ORIDL_INDIV_ID", nullable = false)
	public int getIndivId() {
		return indivId;
	}

	public void setIndivId(int indivId) {
		this.indivId = indivId;
	}

	@Column(updatable = false, insertable = false, name = "INDIV_UID", nullable = false)
	public String getIndivUid() {
		return indivUid;
	}

	public void setIndivUid(String indivUid) {
		this.indivUid = indivUid;
	}

	@Column(updatable = true, insertable = false, name = "EFFECT_TO_DAT", nullable = false)
	public Date getEffectToDate() {
		return effectToDate;
	}

	public void setEffectToDate(Date effectToDate) {
		this.effectToDate = effectToDate;
	}

}
