/*********************************************************************/
/*                           FILE HEADER                             */
/*********************************************************************/
/*                                                                   */
/*  FileName: 		TbbuTextTypesDAO.java  		         			 */
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

import com.atradius.dataaccess.hibernate.HibernateUtil;
import com.atradius.dataaccess.hibernate.bo.TbbuTextTypesBO;
import com.atradius.exception.DataAccessException;

public interface TbbuTextTypesDAO {
	
	/**
	 * @param hibernateUtil
	 * @param textId
	 * @return
	 * @throws DataAccessException
	 */
	public TbbuTextTypesBO getTextDetails(HibernateUtil hibernateUtil,int textId) throws DataAccessException;
}
