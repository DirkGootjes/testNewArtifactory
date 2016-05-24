/*********************************************************************/
/*                           FILE HEADER                             */
/*********************************************************************/
/*                                                                   */
/*  FileName: 		TborOrgNamesBO.java             	  	         */
/*  																 */
/*  $Author: INVSAR1 $									             */
/*																	 */
/*  $Revision: 1.5 $										         */
/*  																 */
/*  $Date: 2013/05/31 12:16:58 $                                     */
/*                                                                   */
/*  Description: 	This class reprent the mappping with             */
/*				  	tbor_org_names table							 */
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

@Entity
@Table(name="tbor_org_names")
public class TborOrgNamesBO implements Serializable{
/**
	 * 
	 */
	private static final long serialVersionUID = -2904762103556478262L;
private int id;
private String FirstLineName;
private String SecondLineName;
private String ThirdLineName;

public TborOrgNamesBO() {
	
}
@Id
@Column(updatable = false, insertable = false, name = "id", nullable = false, length=8)
public int getId(){
	return id;
}
public void setId(int Id){
	this.id=Id;
}
@Column(updatable = false, insertable = false, name = "first_line_name", nullable = false, length=35)
public String getFirstLineName(){
	return FirstLineName;
}
public void setFirstLineName(String FirstLineName){
	this.FirstLineName= FirstLineName;
}
@Column(updatable = false, insertable = false, name = "second_line_name", nullable = false, length=35)
public String getSecondLineName(){
	return SecondLineName;
}
public void setSecondLineName(String SecondLineName){
	this.SecondLineName= SecondLineName;
}
@Column(updatable = false, insertable = false, name = "third_line_name", nullable = false, length=35)
public String getThirdLineName(){
	return ThirdLineName;
}
public void setThirdLineName(String ThirdLineName){
	this.ThirdLineName= ThirdLineName;
}
  public boolean equals(Object other) {
    if (this == other) return true;
    if ( !(other instanceof TborOrgNamesBO) ) return false;

    final TborOrgNamesBO test = (TborOrgNamesBO) other;

    if ( test.getId()!=( getId() )) return false;
    
    return true;
}

public int hashCode() {
    int result;
    result = getId() ;
    return result;
}

}
