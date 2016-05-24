/*********************************************************************/
/*                           FILE HEADER                             */
/*********************************************************************/
/*                                                                   */
/*  FileName: 		TbbuLanguagesDAO.java  		           	  	     */
/*  																 */
/*  $Author: INVSAR1 $									             */
/*																	 */
/*  $Revision: 1.3 $										         */
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

import com.atradius.dataaccess.hibernate.bo.TbbuLanguagesBO;
import com.atradius.exception.DataAccessException;


public interface TbbuLanguagesDAO {
	/**
	 * @return
	 * @throws DataAccessException
	 */
	public List<TbbuLanguagesBO> getUnicodeLanguage() throws DataAccessException;
	/**
	 * @param langCode
	 * @return
	 * @throws DataAccessException
	 */
	public TbbuLanguagesBO  getLanguageName(String langCode)throws DataAccessException;
}
