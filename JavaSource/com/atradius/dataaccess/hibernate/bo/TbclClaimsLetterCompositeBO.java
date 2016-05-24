package com.atradius.dataaccess.hibernate.bo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class TbclClaimsLetterCompositeBO implements Serializable{
	
	@Column(name = "BUCCE_ID")
	private Integer bucceId;
	
	@Column(name = "CLCLT_LETTER_TYP")
	private String clcltLetterTyp;
	
	@Column(name = "PRINT_DAT")
	private Date printDate;

	public Integer getBucceId() {
		return bucceId;
	}

	public void setBucceId(Integer bucceId) {
		this.bucceId = bucceId;
	}

	public String getClcltLetterTyp() {
		return clcltLetterTyp;
	}

	public void setClcltLetterTyp(String clcltLetterTyp) {
		this.clcltLetterTyp = clcltLetterTyp;
	}

	public Date getPrintDate() {
		return printDate;
	}

	public void setPrintDate(Date printDate) {
		this.printDate = printDate;
	}
	
	
}
