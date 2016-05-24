/*********************************************************************/
/*                           FILE HEADER                             */
/*********************************************************************/
/*                                                                   */
/*  FileName: 		TbbuTextTranslationsUniDAO.java  		         */
/*  																 */
/*  $Author: INVSAR1 $									             */
/*																	 */
/*  $Revision: 1.5 $										         */
/*  																 */
/*  $Date: 2013/05/31 12:16:58 $                                     */
/*                                                                   */
/*  Description: 	Interface 										 */
/*********************************************************************/
/* Date        Name            Version             Comments          */
/*-------------------------------------------------------------------*/
/* 25/04/2013  INVSAR1      	1.0         Initial version created  */
/*********************************************************************/
package com.atradius.dataaccess.hibernate.dao;


import java.util.List;

import com.atradius.dataaccess.hibernate.HibernateUtil;
import com.atradius.dataaccess.hibernate.bo.TbbuTextTranslationsUniBO;
import com.atradius.exception.DataAccessException;


public interface TbbuTextTranslationsUniDAO {
	/**
	 * @param hibernateUtil
	 * @param textId
	 * @param languageCode
	 * @param translation
	 * @return
	 * @throws DataAccessException
	 */
	public TbbuTextTranslationsUniBO insertTextTranslations(HibernateUtil hibernateUtil,int textId,String languageCode,String translation) throws DataAccessException;
	/**
	 * @param hibernateUtil
	 * @param textId
	 * @param languageCode
	 * @param translation
	 * @return
	 * @throws DataAccessException
	 */
	public TbbuTextTranslationsUniBO updateTextTranslations(HibernateUtil hibernateUtil,int textId,String languageCode,String translation) throws DataAccessException;
	/**
	 * @param hibernateUtil
	 * @param textId
	 * @return
	 * @throws DataAccessException
	 */
	public List<TbbuTextTranslationsUniBO> getTextTranslations(HibernateUtil hibernateUtil,int textId) throws DataAccessException;
	/**
	 * @param hibernateUtil
	 * @param textId
	 * @param languageCode
	 * @return
	 * @throws DataAccessException
	 */
	public boolean deleteTextTranslations(HibernateUtil hibernateUtil,int textId,String languageCode) throws DataAccessException;
}
