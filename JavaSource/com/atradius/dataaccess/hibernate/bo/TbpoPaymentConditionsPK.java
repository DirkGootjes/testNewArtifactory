/*********************************************************************/
/*                           FILE HEADER                             */
/*********************************************************************/
/*                                                                   */
/*  FileName: 		TbpoPaymentConditionsPK.java                 	 */
/*  																 */
/*  $Author: INHYOU1 $									             */
/*																	 */
/*  $Revision: 1.0 $										         */
/*  																 */
/*  $Date: 2016/01/12 10:25:12 $                                     */
/*                                                                   */
/*  Description: 	Primary key class 					             */
/*				  	for TbpoPaymentConditionsBO					 */
/*				                   					                 */
/*********************************************************************/
package com.atradius.dataaccess.hibernate.bo;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Id;

@Embeddable
public class TbpoPaymentConditionsPK implements Serializable {


	private static final long serialVersionUID = 829646171237076429L;

	@Id
	@Column(updatable = true, insertable = true, name = "POPCE_PER", nullable = false)
	 private int pcPer;

	@Id
	@Column(updatable = true, insertable = true, name = "POPCE_PER_TYP", nullable = false)
	 private String pcPerTyp;

	@Id
	@Column(updatable = true, insertable = true, name = "POPCE_TYP", nullable = false)
	 private String typ;
	
	@Id
	@Column(updatable = true, insertable = true, name = "BULAE_LANG_CODE", nullable = false)
	private String langCode;
	
	
	public TbpoPaymentConditionsPK(){
		super();
	}
	public TbpoPaymentConditionsPK(int pcPer,String pcPertyp, String typ,String langCode) {
		this.pcPer=pcPer;
		this.pcPerTyp= pcPertyp;
		this.typ= typ;
		this.langCode=langCode;
	}

}
