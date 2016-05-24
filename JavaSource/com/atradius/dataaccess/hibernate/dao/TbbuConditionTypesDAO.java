/*********************************************************************/
/*                           FILE HEADER                             */
/*********************************************************************/
/*                                                                   */
/*  FileName: 		TbbuConditionTypesDAO.java  	    			 */
/*  																 */
/*  $Author: INASHA2 $									             */
/*																	 */
/*  $Revision: 1.2 $										         */
/*  																 */
/*  $Date: 2014/03/06 13:49:51 $                                     */
/*                                                                   */
/*  Description: 	Interface 										 */
/*********************************************************************/
/* Date        Name            Version             Comments          */
/*-------------------------------------------------------------------*/
/* 16/10/2013  INATIW1      	1.0         Initial version created  */
/*********************************************************************/

package com.atradius.dataaccess.hibernate.dao;

import com.atradius.dataaccess.hibernate.HibernateUtil;
import com.atradius.dataaccess.hibernate.bo.TbbuConditionTypesBO;

public interface TbbuConditionTypesDAO {

	TbbuConditionTypesBO getConditionCodeDetails(HibernateUtil hibernateUtil, String bucdeCd);

}
