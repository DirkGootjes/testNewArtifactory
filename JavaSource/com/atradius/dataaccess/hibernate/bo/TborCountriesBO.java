/*********************************************************************/
/*                           FILE HEADER                             */
/*********************************************************************/
/*                                                                   */
/*  FileName: 		TborCountriesBO.java             	  	         */
/*  																 */
/*  $Author: INVSAR1 $									             */
/*																	 */
/*  $Revision: 1.4 $										         */
/*  																 */
/*  $Date: 2013/05/31 12:16:58 $                                     */
/*                                                                   */
/*  Description: 	This class reprent the mappping with             */
/*				  	tbor_countries table							 */
/*				                   					                 */
/*********************************************************************/
/* Date        Name            Version             Comments          */
/*-------------------------------------------------------------------*/
/* 20/04/2013  INRSHR1      	1.0         Initial version created  */
/*********************************************************************/
package com.atradius.dataaccess.hibernate.bo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * 
`* this is used with hibernate to get data from the tbor_countries table
 * this is un-updatable and un-insertable as service will never create or update countries
 */
@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
@Table(name="tbor_countries")
public class TborCountriesBO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8452176830488257142L;
	private int id;
    private String mainName;
    private String code;
    private String subAreaCode;
	

	public TborCountriesBO()
    {
    	
    }
	@Id
	@Column(updatable = false, insertable = false, name = "id", nullable = false, length=4)
	public int getId(){
		return id;
	}
	public void setId(int id){
		this.id=id;
	}
	@Column(updatable = false, insertable = false, name = "main_name", nullable = false, length=35)	
	public String getMainName(){
		return mainName;
	}
	public void setMainName(String mainName){
		this.mainName = mainName;
	}
	@Column(updatable = false, insertable = false, name = "code", nullable = false, length=9)
	public String getCode(){
		return code;
	}
	public void setCode(String code){
		this.code = code;
	}
    @Column(updatable = false, insertable = false, name = "SUB_AREA_CODE", nullable = true, length = 1)
	public String getSubAreaCode() {
		return subAreaCode;
	}
	public void setSubAreaCode(String subAreaCode) {
		this.subAreaCode = subAreaCode;
	}
}
