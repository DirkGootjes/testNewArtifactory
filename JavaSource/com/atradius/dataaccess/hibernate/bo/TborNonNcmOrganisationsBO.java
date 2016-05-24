/*********************************************************************/
/*                           FILE HEADER                             */
/*********************************************************************/
/*                                                                   */
/*  FileName: 		TborNonNcmOrganisationsBO.java       	         */
/*  																 */
/*  $Author: INVSAR1 $									             */
/*																	 */
/*  $Revision: 1.3 $										         */
/*  																 */
/*  $Date: 2013/05/31 12:16:58 $                                     */
/*                                                                   */
/*  Description: 	This class reprent the mappping with             */
/*				  	tbor_non_ncm_organisations table				 */
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

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
@Table(name="tbor_non_ncm_organisations")
public class TborNonNcmOrganisationsBO implements Serializable{
	private static final long serialVersionUID = 8452176837588257142L;
	private int id;
    private String countryName;
    private String langVode;
    private String shortName;
    private Date effectToDat;
    public TborNonNcmOrganisationsBO(){
    	
    }
	/**
	 * @return the countryName
	 */
    @Column(updatable = false, insertable = false, name = "D_ORCOY_MAIN_NAME", nullable = false, length=35)
	public String getCountryName() {
		return countryName;
	}
	/**
	 * @param countryName the countryName to set
	 */
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
	/**
	 * @return the id
	 */
	@Id
	@Column(updatable = false, insertable = false, name = "ID", nullable = false, length=8)
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the langVode
	 */
	@Column(updatable = false, insertable = false, name = "D_ORNOL_BULAE_LANG_CODE", nullable = false, length=2)
	public String getLangVode() {
		return langVode;
	}
	/**
	 * @param langVode the langVode to set
	 */
	public void setLangVode(String langVode) {
		this.langVode = langVode;
	}
	/**
	 * @return the shortName
	 */
	@Column(updatable = false, insertable = false, name = "D_ORNOL_SHORT_NAME", nullable = false, length=2)
	public String getShortName() {
		return shortName;
	}
	/**
	 * @param shortName the shortName to set
	 */
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}
	/**
	 * @return the effectToDat
	 */
	@Column(updatable = false, insertable = false, name = "EFFECT_TO_DAT", nullable = false, length=2)
	public Date getEffectToDat() {
		return effectToDat;
	}
	/**
	 * @param effectToDat the effectToDat to set
	 */
	public void setEffectToDat(Date effectToDat) {
		this.effectToDat = effectToDat;
	}
    
}
