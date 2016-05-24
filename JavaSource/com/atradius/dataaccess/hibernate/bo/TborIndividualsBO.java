/*********************************************************************/
/*                           FILE HEADER                             */
/*********************************************************************/
/*                                                                   */
/*  FileName: 		TborIndividualsBO.java             	  	         */
/*  																 */
/*  $Author: INASHA2 $									             */
/*																	 */
/*  $Revision: 1.7 $										         */
/*  																 */
/*  $Date: 2014/03/06 13:49:18 $                                     */
/*                                                                   */
/*  Description: 	This class reprent the mappping with             */
/*				  	tbor_individuals table*/
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
@Table(name="tbor_individuals")
public class TborIndividualsBO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2324480526874131028L;
	private int indivId;
	private String indivName;
	private String firstName;
	private String lastName;
	private String BulaeLangCode;
	private String salutatName;
	
	private String genderInd;	
	private String jobTitleDes;
	private Date birthDat;	
	

	private String ediNr;
	private String faxNr;
	private String mobileTelNr;
	private String telextNr;	
	private String telexNr;	
	private String telNr;	
		
	private String emailAddr;	
	
	private String emailOutputFlag;		
	private String emailStatusInd;
	private String noMailFlag;	
	
	private Date effectToDat;
	
	
	//private int ornnnId;	
	private Integer ornonId;
	private TbbuLanguagesBO language;
	
	public TborIndividualsBO(){
		
	}
	
	@Id
	@Column(updatable = false, insertable = false, name = "indiv_id", nullable = false, length=14)
	public int getIndivId(){
		return indivId;
	}
	public void setIndivId(int indivId){
		this.indivId= indivId;
	}
	@Column(updatable = false, insertable = false, name = "indiv_name", nullable = false, length=35)
	public String getIndivName(){
		return indivName;
	}
	public void setIndivName(String indivName){
		this.indivName=indivName;
	}
	@Column(updatable = false, insertable = false, name = "first_name", nullable = true, length=35)
	public String getFirstName(){
		return firstName;
	}
	public void setFirstName(String firstName){
		this.firstName=firstName;
	}
	@Column(updatable = false, insertable = false, name = "last_name", nullable = true, length=35)
	public String getLastName(){
		return lastName;
	}
	public void setLastName(String lastName){
		this.lastName=lastName;
	}
	@Column(updatable = false, insertable = false, name = "salutat_name", nullable = true, length=35)
	public String getSalutatName(){
		return salutatName;
	}
	public void setSalutatName(String salutatName){
		this.salutatName=salutatName;
	}
	@Column(updatable = false, insertable = false, name = "gender_ind", nullable = true, length=1)
	public String getGenderInd(){
		return genderInd;	
	}
	public void setGenderInd(String genderInd){
		this.genderInd=genderInd;	
	}
	@Column(updatable = false, insertable = false, name = "job_title_des", nullable = true, length=80)
	public String getJobTitleDes(){
		return jobTitleDes;
	}
	public void setJobTitleDes(String jobTitleDes){
		this.jobTitleDes = jobTitleDes;
	}
	@Column(updatable = false, insertable = false, name = "birth_dat", nullable = true, length=35)
	public Date getBirthDat(){
		return birthDat;	
	}
	public void setBirthDat(Date birthDat){
		this.birthDat = birthDat;	
	}
	@Column(updatable = false, insertable = false, name = "edi_nr", nullable = true, length=25)
	public String getEdiNr(){
		return ediNr;
	}
	public void setEdiNr(String ediNr){
		this.ediNr = ediNr;
	}
	@Column(updatable = false, insertable = false, name = "fax_nr", nullable = true, length=25)
	public String getFaxNr(){
		return faxNr;
	}
	public void setFaxNr(String faxNr){
		this.faxNr = faxNr;
	}
	@Column(updatable = false, insertable = false, name = "mobile_tel_nr", nullable = true, length=25)
	public String getMobileTelNr(){
		return mobileTelNr;
	}
	public void setMobileTelNr(String mobileTelNr){
		this.mobileTelNr = mobileTelNr;
	}
	@Column(updatable = false, insertable = false, name = "telext_nr", nullable = true, length=25)
	public String getTelextNr(){
		return telextNr;	
	}
	public void setTelextNr(String telextNr){
		this.telextNr = telextNr;	
	}
	@Column(updatable = false, insertable = false, name = "telex_nr", nullable = true, length=25)
	public String getTelexNr(){
		return telexNr;	
	}
	public void setTelexNr(String telexNr){
		this.telexNr = telexNr;	
	}
	@Column(updatable = false, insertable = false, name = "tel_nr", nullable = true, length=25)
	public String getTelNr(){
		return telNr;	
	}
	public void setTelNr(String telNr){
		this.telNr = telNr;	
	}
	@Column(updatable = false, insertable = false, name = "email_addr", nullable = true, length=100)	
	public String getEmailAddr(){
		return emailAddr;	
	}
	public void setEmailAddr(String emailAddr){
		this.emailAddr = emailAddr;	
	}
	@Column(updatable = false, insertable = false, name = "email_output_flag", nullable = true, length=1)
	public String getEmailOutputFlag(){
		return emailOutputFlag;	
	}
	public void setEmailOutputFlag(String emailOutputFlag){
		this.emailOutputFlag = emailOutputFlag;	
	}
	@Column(updatable = false, insertable = false, name = "email_status_ind", nullable = true, length=3)
	public String getEmailStatusInd(){
		return emailStatusInd;
	}
	public void setEmailStatusInd(String emailStatusInd){
		this.emailStatusInd =  emailStatusInd;
	}
	@Column(updatable = false, insertable = false, name = "no_mail_flag", nullable = true, length=1)
	public String getNoMailFlag(){
		return noMailFlag;	
	}
	public void setNoMailFlag(String noMailFlag){
		this.noMailFlag = noMailFlag;	
	}
	@Column(updatable = false, insertable = false, name = "effect_to_dat", nullable = true)
	public Date getEffectToDat(){
		return effectToDat;
	}
	public void setEffectToDat(Date effectToDat){
		this.effectToDat = effectToDat;
	}
	
	/*@Column(updatable = false, insertable = false, name = "ornnn_id", nullable = true, length=8)
	public int getOrnnnId(){
		return ornnnId;	
	}
	public void setOrnnnId(int ornnnId){
		this.ornnnId = ornnnId;	
	}*/
	@Column(updatable = false, insertable = false, name = "ornon_id", nullable = true, length=8)
	public Integer getOrnonId(){
		return ornonId;	
	}
	public void setOrnonId(Integer ornonId){
		this.ornonId = ornonId;	
	}	
	@Column(updatable = false, insertable = false, name = "bulae_lang_code", nullable = true, length=8)
	public String  getBulaeLangCode(){
		return BulaeLangCode;	
	}
	public void setBulaeLangCode(String BulaeLangCode){
		this.BulaeLangCode = BulaeLangCode;	
	}	
	
	/*@ManyToOne( )
    @JoinColumn(name="bulae_lang_code")
	public TbbuLanguagesBO getLanguage(){
		return language;
	}
	public void setLanguage(TbbuLanguagesBO language){
		this.language=language;
	}*/
	
    public boolean equals(Object other) {
	    if (this == other) return true;

    	
	    if ( !(other instanceof TborIndividualsBO) ) return false;

	    final TborIndividualsBO test = (TborIndividualsBO) other;

	    if ( test.getIndivId()!=( getIndivId() )) return false;
	    
	    return true;
	    
	}

	public int hashCode() {
	    int result;
	    result = getIndivId()   ;	    
	    return result;
	}	

}
