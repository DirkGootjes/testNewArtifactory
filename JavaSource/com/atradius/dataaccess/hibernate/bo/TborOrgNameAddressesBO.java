/*********************************************************************/
/*                           FILE HEADER                             */
/*********************************************************************/
/*                                                                   */
/*  FileName: 		TborOrgNameAddressesBO.java       	  	         */
/*  																 */
/*  $Author: INVSAR1 $									             */
/*																	 */
/*  $Revision: 1.4 $										         */
/*  																 */
/*  $Date: 2013/05/31 12:16:58 $                                     */
/*                                                                   */
/*  Description: 	This class reprent the mappping with             */
/*				  	tbor_org_name_addresses table					 */
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
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="tbor_org_name_addresses")
@IdClass(IdEffectToDatKeyBO.class)
public class TborOrgNameAddressesBO implements Serializable{
  
  private static final long serialVersionUID = -8162687556114778963L;
  private String id;
  private int oroasId;
  private int oroneId;
  private Date effectToDat;
  private int nameMatchingId;
  private String typ;
  private String ornnnId;
  private String ornonId;
  private String coRegNr;
  private String vatNr;
  private TborOrgNamesBO orgNames;
  private TborOrgAddressesBO orgAddresses;
  
  
  public TborOrgNameAddressesBO()
  {
  }
	@ManyToOne( )
    @JoinColumn(name="OROAS_ID")
  public TborOrgAddressesBO getOrgAddresses(){
  	return orgAddresses;
  }
  public void setOrgAddresses(TborOrgAddressesBO orgAddresses){
	  	this.orgAddresses=orgAddresses;
	  }
	@ManyToOne( )
    @JoinColumn(name="ORONE_ID")
  public TborOrgNamesBO getOrgNames(){
  	return orgNames;
  }
  public void setOrgNames(TborOrgNamesBO orgNames){
	  	this.orgNames=orgNames;
	  }
  @Id
  @Column(updatable = false, insertable = false, name = "id", nullable = false, length=8)
  public String getId(){
  	return id;
  }
  public void setId(String id){
	  	this.id=id;
  }
  @Column(updatable = false, insertable = false, name = "oroas_id", nullable = false, length=8)
  public int getOroasId(){
  	return oroasId;
  }
  public void setOroasId(int oroasId){
	  	this.oroasId=oroasId;
  }
  @Column(updatable = false, insertable = false, name = "orone_id", nullable = false, length=8)
  public int getOroneId(){
  	return oroneId;
  }
  public void setOroneId(int oroneId){
	  	this.oroneId=oroneId;
  }
  @Id
  @Column(updatable = false, insertable = false, name = "effect_to_dat", nullable = false)
  public Date getEffectToDat(){
  	return effectToDat;
  }
  public void setEffectToDat(Date effectToDat){
	  	this.effectToDat=effectToDat;
	  }
  @Column(updatable = false, insertable = false, name = "name_matching_id", nullable = false, length=14)
  public int getNameMatchingId(){
  	return nameMatchingId;
  }
  public void setNameMatchingId(int nameMatchingId){
	  	this.nameMatchingId= nameMatchingId;
	  }
  @Column(updatable = false, insertable = false, name = "typ", nullable = false, length=4)
  public String getTyp(){
  	return typ;
  }
  public void setTyp(String typ){
	  	this.typ=typ;
	  }
  @Column(updatable = false, insertable = false, name = "ornnn_id", nullable = true, length=8)
  public String getOrnnnId(){
  	return ornnnId;
  }
  public void setOrnnnId(String ornnnId){
	  	this.ornnnId=ornnnId;
	  }
  @Column(updatable = false, insertable = false, name = "ornon_id", nullable = true, length=8)
  public String getOrnonId(){
  	return ornonId;
  }
  public void setOrnonId(String ornonId){
  	this.ornonId=ornonId;
  }
  @Column(updatable = false, insertable = false, name = "co_reg_nr", nullable = true, length=25)
  public String getCoRegNr(){
	  return coRegNr;
  }
  public void setCoRegNr(String coRegNr){
	  this.coRegNr=coRegNr;
  }
  @Column(updatable = false, insertable = false, name = "vat_nr", nullable = true, length=25)
  public String getVatNr(){
	  return vatNr;
	  
  }
  public void setVatNr(String vatNr){
	  this.vatNr=vatNr;
  }
   
  public boolean equals(Object other) {
    if (this == other) return true;
    if ( !(other instanceof TborOrgNameAddressesBO) ) return false;

    final TborOrgNameAddressesBO test = (TborOrgNameAddressesBO) other;

    if ( test.getId()!=( getId() )) return false;
    
    return true;
}

public int hashCode() {
    int result;
    result = getId().hashCode()+getNameMatchingId()+getTyp().hashCode()  ;
    return result;
}
  
}
