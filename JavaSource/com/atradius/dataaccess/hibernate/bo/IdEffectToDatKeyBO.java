/*********************************************************************/
/*                           FILE HEADER                             */
/*********************************************************************/
/*                                                                   */
/*  FileName: 		IdEffectToDatKeyBO.java             	  	     */
/*  																 */
/*  $Author: INVSAR1 $									             */
/*																	 */
/*  $Revision: 1.2 $										         */
/*  																 */
/*  $Date: 2013/05/30 04:39:52 $                                     */
/*                                                                   */
/*  Description: 	Promary key class								 */
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
import javax.persistence.Embeddable;

@Embeddable
public class IdEffectToDatKeyBO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 829646171237076429L;
	String id;
	Date effectToDat;
	
	public IdEffectToDatKeyBO(){
	}
	
	public IdEffectToDatKeyBO(String id, Date effectToDat){
		this.id = id;
		this.effectToDat = effectToDat;
	}
	@Column(updatable = false, insertable = false, name = "id", nullable = false, length=8)
	public String getId(){
		return id;
	}
	public void setId(String id){
		this.id=id;
	}
	@Column(updatable = false, insertable = false, name = "effect_to_dat", nullable = false)
	public Date getEffectToDat(){
		return effectToDat;
	}
	public void setEffectToDat(Date effectToDat){
		this.effectToDat = effectToDat;
	}
	
	  public boolean equals(Object other) {
		    if (this == other) return true;
		    if ( !(other instanceof IdEffectToDatKeyBO) ) return false;

		    final IdEffectToDatKeyBO test = (IdEffectToDatKeyBO) other;

		    if ( test.getId()!=( getId() )) return false;
		    if ( test.getEffectToDat()!=( getEffectToDat() )) return false;
		    
		    return true;
		}

		public int hashCode() {
		    int result;
		    result = getId().hashCode()+getEffectToDat().hashCode() ;
		    return result;
		}
	
}