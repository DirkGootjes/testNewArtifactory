/*********************************************************************/
/*                           FILE HEADER                             */
/*********************************************************************/
/*                                                                   */
/*  FileName: 		TborContactPointsBO.java             	  	     */
/*  																 */
/*  $Author: INVSAR1 $									             */
/*																	 */
/*  $Revision: 1.4 $										         */
/*  																 */
/*  $Date: 2013/05/31 12:16:58 $                                     */
/*                                                                   */
/*  Description: 	This class reprent the mappping with             */
/*				  	tbor_contact_points table						 */
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
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "tbor_contact_points")
@IdClass(ContactPointPKBO.class)
public class TborContactPointsBO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6535003804169802834L;

	private int ornnnId;

	// PRIMARY KEY
	// must make object for the Pk
	private String typ;

	private String ornaeId;

	private int seq;

	private Date effectToDat;

	private Date ornaeEffectToDat;

	//
	private String oridlIndivId;

	private String ediNr;

	private String faxNr;

	private String telexNr;

	private String telNr;

	private String salesPosition;

	private TborIndividualsBO ContactIndividual;

	private TborOrgNameAddressesBO ContactNameAddresse;

	public TborContactPointsBO() {

	}

	@Column(updatable = false, insertable = false, name = "d_ornnn_id", nullable = true, length = 8)
	public int getOrnnnId() {
		return ornnnId;
	}

	public void setOrnnnId(int ornnnId) {
		this.ornnnId = ornnnId;
	}

	@Column(updatable = false, insertable = false, name = "oridl_indiv_id", nullable = true, length = 14)
	public String getOridlIndivId() {
		return oridlIndivId;
	}

	public void setOridlIndivId(String oridlIndivId) {
		this.oridlIndivId = oridlIndivId;
	}

	@Id
	@Column(updatable = false, insertable = false, name = "typ", nullable = false, length = 4)
	public String getTyp() {
		return typ;
	}

	public void setTyp(String typ) {
		this.typ = typ;
	}

	@Id
	@Column(updatable = false, insertable = false, name = "ornae_id", nullable = false, length = 14)
	public String getOrnaeId() {
		return ornaeId;
	}

	public void setOrnaeId(String ornaeId) {
		this.ornaeId = ornaeId;
	}

	@Id
	@Column(updatable = false, insertable = false, name = "seq", nullable = false, length = 4)
	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	@Id
	@Column(updatable = false, insertable = false, name = "ornae_effect_to_dat", nullable = false)
	public Date getOrnaeEffectToDat() {
		return ornaeEffectToDat;
	}

	public void setOrnaeEffectToDat(Date ornaeEffectToDat) {
		this.ornaeEffectToDat = ornaeEffectToDat;
	}

	@Column(updatable = false, insertable = false, name = "edi_nr", nullable = false, length = 25)
	public String getEdiNr() {
		return ediNr;
	}

	public void setEdiNr(String ediNr) {
		this.ediNr = ediNr;
	}

	@Column(updatable = false, insertable = false, name = "fax_nr", nullable = true, length = 25)
	public String getFaxNr() {
		return faxNr;
	}

	public void setFaxNr(String faxNr) {
		this.faxNr = faxNr;
	}

	@Column(updatable = false, insertable = false, name = "telex_nr", nullable = true, length = 25)
	public String getTelexNr() {
		return telexNr;
	}

	public void setTelexNr(String telexNr) {
		this.telexNr = telexNr;
	}

	@Column(updatable = false, insertable = false, name = "tel_nr", nullable = true, length = 25)
	public String getTelNr() {
		return telNr;
	}

	public void setTelNr(String telNr) {
		this.telNr = telNr;
	}

	@Id
	@Column(updatable = false, insertable = false, name = "effect_to_dat", nullable = false)
	public Date getEffectToDat() {
		return effectToDat;
	}

	public void setEffectToDat(Date effectToDat) {
		this.effectToDat = effectToDat;
	}

	@ManyToOne()
	@JoinColumn(name = "oridl_indiv_id")
	public TborIndividualsBO getContactIndividual() {
		return ContactIndividual;
	}

	public void setContactIndividual(TborIndividualsBO ContactIndividual) {
		this.ContactIndividual = ContactIndividual;
	}

	@ManyToOne()
	@JoinColumns( { @JoinColumn(name = "effect_to_dat"),
			@JoinColumn(name = "ORNAE_ID") })
	public TborOrgNameAddressesBO getContactNameAddresse() {
		return ContactNameAddresse;
	}

	public void setContactNameAddresse(
			TborOrgNameAddressesBO ContactNameAddresse) {
		this.ContactNameAddresse = ContactNameAddresse;
	}

	/**
	 * @return the salePosition
	 */
	@Column(updatable = false, insertable = false, name = "sales_position", nullable = true, length = 35)
	public String getSalesPosition() {
		return salesPosition;
	}

	/**
	 * @param salePosition
	 *            the salePosition to set
	 */
	public void setSalesPosition(String salesPosition) {
		this.salesPosition = salesPosition;
	}

}
