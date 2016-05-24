/*********************************************************************/
/*                           FILE HEADER                             */
/*********************************************************************/
/*                                                                   */
/*  FileName: 		CommonUtil.java               	         	     */
/*  																 */
/*  $Author: INKPAG1 $									             */
/*																	 */			
/*  $Revision: 1.11 $										         */
/*  																 */		
/*  $Date: 2014/10/13 05:32:12 $                                     */
/*                                                                   */
/*  Description: 	This Class contains the methods for  		     */
/*				  	common operations.							     */			
/*********************************************************************/
/* Date        Name            Version             Comments          */
/*-------------------------------------------------------------------*/
/* 04/04/2013  INVSAR1      	1.0         Initial version created  */
/*********************************************************************/
package com.atradius.utils;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.atradius.dataaccess.hibernate.bo.TbbuConditionMChoiceTextsBO;
import com.atradius.dataaccess.hibernate.bo.TbbuTextTranslationsUniBO;
import com.atradius.dataaccess.hibernate.bo.TborIndividualsUnicodeBO;
import com.atradius.exception.DataAccessException;
import com.atradius.sessiondata.ApplicationConstants;
import com.atradius.util.logging.ILogger;
import com.atradius.util.logging.LoggerFactory;
import com.ibm.icu.text.SimpleDateFormat;

public class CommonUtil {
	public static Calendar maxDate;

	private static ILogger logger = LoggerFactory.getLogger(CommonUtil.class);
	
	private static String ERROR = "error";

	public static Date getMaxDate() {
		logger.enterMethod("getMaxDate");
		if (maxDate == null) {
			maxDate = Calendar.getInstance();
			maxDate.clear();
			maxDate.set(4712, 11, 31);
		}
		logger.exitMethod("getMaxDate");
		return maxDate.getTime();
	}
	/**
	 * @param transaltionList
	 * @param langCode
	 * @return
	 */
	public static TbbuTextTranslationsUniBO getTransaltion(
			List<TbbuTextTranslationsUniBO> transaltionList, String langCode) {
		logger.enterMethod("getTransaltion");
		TbbuTextTranslationsUniBO mTbbuTextTranslationsUniBO = null;
		if (transaltionList != null && transaltionList.size() > 0) {
			for (int i = 0; i < transaltionList.size(); i++) {
				TbbuTextTranslationsUniBO nTbbuTextTranslationsUniBO = transaltionList
						.get(i);
				if (nTbbuTextTranslationsUniBO != null
						&& nTbbuTextTranslationsUniBO.getLanguageCode().equals(
								langCode)) {
					mTbbuTextTranslationsUniBO = nTbbuTextTranslationsUniBO;
					break;
				}
			}
		}
		logger.exitMethod("getTransaltion");
		return mTbbuTextTranslationsUniBO;
	}
	public static TbbuConditionMChoiceTextsBO getTransaltionForConditioncode(
			List<TbbuConditionMChoiceTextsBO> transaltionList, String langCode) {
		logger.enterMethod("getTransaltion");
		TbbuConditionMChoiceTextsBO mTbbuTextTranslationsUniBO = null;
		if (transaltionList != null && transaltionList.size() > 0) {
			for (int i = 0; i < transaltionList.size(); i++) {
				TbbuConditionMChoiceTextsBO nTbbuTextTranslationsUniBO = transaltionList
						.get(i);
				if (nTbbuTextTranslationsUniBO != null
						&& nTbbuTextTranslationsUniBO.getLangCode().equals(
								langCode)) {
					mTbbuTextTranslationsUniBO = nTbbuTextTranslationsUniBO;
					break;
				}
			}
		}
		logger.exitMethod("getTransaltion");
		return mTbbuTextTranslationsUniBO;
	}
	/**
	 * @param transaltionList
	 * @param langCode
	 * @return
	 */
	public static TborIndividualsUnicodeBO getIndvTransaltion(
			List<TborIndividualsUnicodeBO> transaltionList, String langCode) {
		logger.enterMethod("getIndvTransaltion");
		TborIndividualsUnicodeBO mTbbuTextTranslationsUniBO = null;
		if (transaltionList != null && transaltionList.size() > 0) {
			for (int i = 0; i < transaltionList.size(); i++) {
				TborIndividualsUnicodeBO nTbbuTextTranslationsUniBO = transaltionList
						.get(i);
				
				if (nTbbuTextTranslationsUniBO != null
						&& nTbbuTextTranslationsUniBO.getLangCode().equals(
								langCode)) {
					mTbbuTextTranslationsUniBO = nTbbuTextTranslationsUniBO;
					break;
				}
			}
		}
		logger.exitMethod("getIndvTransaltion");
		return mTbbuTextTranslationsUniBO;
	}	
	
	public static Date convertStringToDate(final String strDate, final String dateFormat) throws DataAccessException{
		Date date=new Date();
		SimpleDateFormat  format=new SimpleDateFormat(dateFormat);
		try {
			date=format.parse(strDate);
		} catch (ParseException e) {						
			throw new DataAccessException("convertStringToDate", e);
		}
		return date;
	}
	
	public static String setFormModeIfEmpty(final String inputFormModeValue){
		String formModeValue=null;
		if (inputFormModeValue == null || inputFormModeValue.trim().equals("") ||  
				ApplicationConstants.ATTR_FORM_MODE_VIEW_VALUE.equals(inputFormModeValue.trim())) {
			formModeValue = ApplicationConstants.ATTR_FORM_MODE_VIEW_VALUE;
		}
		return formModeValue;
	}
	
	public static boolean isAttributeEmpty(final String attribute){
		boolean isEmpty=false;
		if(attribute==null || attribute.length()<=0){
			isEmpty=true;
		}
		return isEmpty;
	}
	
}
