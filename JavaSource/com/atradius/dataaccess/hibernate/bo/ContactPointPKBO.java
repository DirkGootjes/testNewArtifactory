/*********************************************************************/
/*                           FILE HEADER                             */
/*********************************************************************/
/*                                                                   */
/*  FileName: 		ContactPointPKBO.java             	  	         */
/*  																 */
/*  $Author: INVSAR1 $									             */
/*																	 */
/*  $Revision: 1.3 $										         */
/*  																 */
/*  $Date: 2013/05/31 12:16:58 $                                     */
/*                                                                   */
/*  Description: 	This class keeps primary key for ContactPointPKBO*/
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
public class ContactPointPKBO implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 5846109806844640432L;
	private String typ;
    private String ornaeId;
    private int seq;
    private Date effectToDat;
    private Date ornaeEffectToDat;
    
    public ContactPointPKBO(){
    	
    }
    public ContactPointPKBO(String typ, String ornaeId, int seq, Date effectToDat, Date ornaeEffectToDat ){
       this.typ= typ;
       this.ornaeId = ornaeId;
       this.seq = seq;
       this.effectToDat = effectToDat;
       this.ornaeEffectToDat = ornaeEffectToDat; 	
    }
    @Column(updatable = false, insertable = false, name = "typ", nullable = false, length=4)
    public String getTyp(){
    	return typ;
    }
    public void setTyp(String typ){
    	this.typ = typ;
    }
    @Column(updatable = false, insertable = false, name = "ornae_id", nullable = false, length=14)
    public String getOrnaeId(){
    	return ornaeId;
    }
    public void setOrnaeId(String ornaeId){
    	this.ornaeId=ornaeId;
    }
    @Column(updatable = false, insertable = false, name = "seq", nullable = false, length=4)
    public int getSeq(){
    	return seq;
    }
    public void setSeq(int seq){
    	this.seq=seq;
    }
    @Column(updatable = false, insertable = false, name = "effect_to_dat", nullable = false)
    public Date getEffectToDat(){
    	return effectToDat;
    }
    public void setEffectToDat(Date effectToDat){
    	this.effectToDat=effectToDat;
    }
    @Column(updatable = false, insertable = false, name = "ornae_effect_to_dat", nullable = false)
    public Date getOrnaeEffectToDat(){
    	return ornaeEffectToDat;
    }
    public void setOrnaeEffectToDat(Date ornaeEffectToDat){
    	this.ornaeEffectToDat=ornaeEffectToDat;
    }
    
	  public boolean equals(Object other) {
		    if (this == other) return true;
		    if ( !(other instanceof ContactPointPKBO) ) return false;

		    final ContactPointPKBO test = (ContactPointPKBO) other;

		    if ( test.getTyp()!=( getTyp() )) return false;
		    if ( test.getEffectToDat()!=( getEffectToDat() )) return false;
		    if ( test.getOrnaeEffectToDat()!=( getOrnaeEffectToDat() )) return false;
		    if ( test.getSeq()!=( getSeq() )) return false;
		    if ( test.getOrnaeId()!=( getOrnaeId() )) return false;
		    
		    return true;
		}

		public int hashCode() {
		    int result;
		    result = getOrnaeId().hashCode()+getSeq()+getEffectToDat().hashCode()+getEffectToDat().hashCode()+getTyp().hashCode() ;
		    return result;
		}
}
