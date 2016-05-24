/*********************************************************************/
/*                           FILE HEADER                             */
/*********************************************************************/
/*                                                                   */
/*  FileName: 		TborOrgAddressesBO.java          	  	         */
/*  																 */
/*  $Author: INVSAR1 $									             */
/*																	 */
/*  $Revision: 1.5 $										         */
/*  																 */
/*  $Date: 2013/05/31 12:16:59 $                                     */
/*                                                                   */
/*  Description: 	This class reprent the mappping with             */
/*				  	tbor_org_addresses table						 */
/*				                   					                 */
/*********************************************************************/
/* Date        Name            Version             Comments          */
/*-------------------------------------------------------------------*/
/* 20/04/2013  INRSHR1      	1.0         Initial version created  */
/*********************************************************************/
package com.atradius.dataaccess.hibernate.bo;

import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.atradius.util.logging.ILogger;
import com.atradius.util.logging.LoggerFactory;

@Entity
@Table(name="tbor_org_addresses")
public class TborOrgAddressesBO implements Serializable{
	
	private static ILogger logger = LoggerFactory
	.getLogger(TborOrgAddressesBO.class);
	
	private static final long serialVersionUID = 4869449441266424733L;
	private int id;
	private String orctyId;
	private String cityName;
	private String firstLineStreetAddr;
	private String secondLineStreetAddr;
	private String thirdLineStreetAddr;
	private String faxNr;
	private String ediNr;
	private String telNr;
	private String telexNr;
	private String regionName;
	private String postCode;
	private String emailAddr;
	private TborCountriesBO OrgCountry;
	
	public TborOrgAddressesBO()
	{
		
	}
	@ManyToOne( )
    @JoinColumn(name="D_ORCOY_ID")
	public TborCountriesBO getOrgCountry()
	{
		return OrgCountry;
	}
	public void setOrgCountry(TborCountriesBO OrgCountry){
		this.OrgCountry=OrgCountry;
	}
	@Id
	@Column(updatable = false, insertable = false, name = "id", nullable = false, length=8)
	public int getId(){
		return id;
	}
	public void setId(int id){
		this.id=id;
	}
	@Column(updatable = false, insertable = false, name = "orcty_id", nullable = false, length=6)
	public String getOrctyId(){
		return orctyId;
	}
	public void setOrctyId(String orctyId){
		this.orctyId=orctyId;
	}
	@Column(updatable = false, insertable = false, name = "city_name", nullable = true, length=35)
	public String getCityName(){
		return cityName;
	}
	public void setCityName(String cityName){
		this.cityName=cityName;
	}
	@Column(updatable = false, insertable = false, name = "first_line_street_addr", nullable = true, length=35)
	public String getFirstLineStreetAddr(){
		logger.info("firstLineStreetAddr[" + firstLineStreetAddr + "]");
		return (firstLineStreetAddr != null) ? removeCtrlChars(firstLineStreetAddr)
				: firstLineStreetAddr;
	}
	public void setFirstLineStreetAddr(String firstLineStreetAddr){
		this.firstLineStreetAddr=firstLineStreetAddr;
	}
	@Column(updatable = false, insertable = false, name = "second_line_street_addr", nullable = true, length=35)
	public String getSecondLineStreetAddr(){
		logger.info("secondLineStreetAddr[" + secondLineStreetAddr + "]");
		return (secondLineStreetAddr != null) ? removeCtrlChars(secondLineStreetAddr)
				: secondLineStreetAddr;
	}
	public void setSecondLineStreetAddr(String secondLineStreetAddr){
		this.secondLineStreetAddr=secondLineStreetAddr;
	}
	@Column(updatable = false, insertable = false, name = "third_line_street_addr", nullable = true, length=35)
	public String getThirdLineStreetAddr(){
		logger.info("thirdLineStreetAddr[" + thirdLineStreetAddr + "]");
		return (thirdLineStreetAddr != null) ? removeCtrlChars(thirdLineStreetAddr)
				: thirdLineStreetAddr;
	}
	public void setThirdLineStreetAddr(String thirdLineStreetAddr){
		this.thirdLineStreetAddr=thirdLineStreetAddr;
	}
	@Column(updatable = false, insertable = false, name = "fax_nr", nullable = true, length=25)
	public String getFaxNr(){
		return faxNr;
	}
	public void setFaxNr(String faxNr){
		this.faxNr=faxNr;
	}
	@Column(updatable = false, insertable = false, name = "edi_nr", nullable = true, length=35)
	public String getEdiNr(){
		return ediNr;
	}
	public void setEdiNr(String ediNr){
		this.ediNr=ediNr;
	}
	@Column(updatable = false, insertable = false, name = "tel_nr", nullable = true, length=25)
	public String getTelNr(){
		return telNr;
	}
	public void setTelNr(String telNr){
		this.telNr=telNr;
	}
	@Column(updatable = false, insertable = false, name = "telex_nr", nullable = true, length=25)
	public String getTelexNr(){
		logger.info("Telex Nr [" + telexNr + "]");
		return (telexNr != null)?(isValidEmailFormat(telexNr)?removeCtrlChars(telexNr):telexNr):telexNr;
	}
	public void setTelexNr(String telexNr){
		this.telexNr=telexNr;
	}
	@Column(updatable = false, insertable = false, name = "region_name", nullable = true, length=20)
	public String getRegionName(){
		return regionName;
	}
	public void setRegionName(String regionName){
		this.regionName=regionName;
	}
	@Column(updatable = false, insertable = false, name = "post_code", nullable = true, length=10)
	public String getPostCode(){
		return postCode;
	}
	public void setPostCode(String postCode){
		this.postCode=postCode;
	}
	@Column(updatable = false, insertable = false, name = "email_addr", nullable = true, length=100)
	public String getEmailAddr(){
		logger.info("Email Addr [" + emailAddr + "]");
		return (emailAddr != null)?(isValidEmailFormat(emailAddr)?removeCtrlChars(emailAddr):emailAddr):emailAddr;		
	}
	public void setEmailAddr(String emailAddr){
		this.emailAddr=emailAddr;
	}	
	

	  public boolean equals(Object other) {
	    if (this == other) return true;
	    if ( !(other instanceof TborOrgAddressesBO) ) return false;

	    final TborOrgAddressesBO test = (TborOrgAddressesBO) other;

	    if ( test.getId()!=( getId() )) return false;
	    
	    return true;
	}

	public int hashCode() {
	    int result;
	    result = getId() ;
	    return result;
	}
	
	private String removeCtrlChars(String str) {

		Pattern p = Pattern.compile("\\p{Cntrl}");
		Matcher match = p.matcher(str);
		String returnString = str;
		if (match.find()) {
			logger.debug("Input with Ctrl chars [" + str + "]");
			returnString = match.replaceAll("");
			logger.debug("Output strip ctrl chars [" + returnString + "]");			
		} else {
			logger.debug("[" + str + " ] No match");
		}

		return returnString;

	}
	
	private boolean isValidEmailFormat(String str){
		
		Pattern p = Pattern.compile("@");
		Matcher match = p.matcher(str);

		return match.find();
		
	}
}
