/*********************************************************************/
/*                           FILE HEADER                             */
/*********************************************************************/
/*                                                                   */
/*  FileName: 		TbbuConditionVariablesUniDAO.java     	         */
/*  																 */
/*  $Author: INASHA2 $									             */
/*																	 */
/*  $Revision: 1.2 $										         */
/*  																 */
/*  $Date: 2014/03/06 13:49:51 $                                     */
/*                                                                   */
/*  Description: 	Interface     	                  				 */
/*********************************************************************/
/* Date        Name            Version             Comments          */
/*-------------------------------------------------------------------*/
/* 16/10/2013  INATIW1      	1.0         Initial version created  */
/*********************************************************************/

package com.atradius.dataaccess.hibernate.dao;

import com.atradius.dataaccess.hibernate.HibernateUtil;
import com.atradius.dataaccess.hibernate.bo.TbbuConditionTypesBO;
import com.atradius.dataaccess.hibernate.bo.TbbuConditionVariablesUniBO;
import com.atradius.exception.DataAccessException;

public interface TbbuConditionVariablesUniDAO {
	
	/**
	 * @param hibernateUtil
	 * @param bucltId
	 * @param applyAmountType
	 * @param bucyeSequence
	 * @param bucdeCd
	 * @return
	 * @throws DataAccessException
	 */
	public TbbuConditionVariablesUniBO getTextTranslations(HibernateUtil hibernateUtil, Integer bucltId, String applyAmountType, Integer bucyeSequence, String bucdeCd) throws DataAccessException;
	
	/**
	 * @param hibernateUtil
	 * @param bucltId
	 * @param applyAmountType
	 * @param bucyeSequence
	 * @param bucdeCd
	 * @param bumctSequence
	 * @param bumctOrder
	 * @return
	 * @throws DataAccessException
	 */
	public TbbuConditionVariablesUniBO getTextTranslations(HibernateUtil hibernateUtil, Integer bucltId, String applyAmountType, Integer bucyeSequence, String bucdeCd, Integer bumctSequence, Integer bumctOrder) throws DataAccessException;
	
	/**
	 * @param hibernateUtil
	 * @param bucltId
	 * @param applyAmountType
	 * @param bucyeSequence
	 * @param variableText
	 * @param conditionTypesBO
	 * @param bumctSequence
	 * @param bumctOrder
	 * @return
	 * @throws DataAccessException
	 */
	public TbbuConditionVariablesUniBO insertTextTranslations(HibernateUtil hibernateUtil,Integer bucltId,String applyAmountType,Integer bucyeSequence,String variableText,TbbuConditionTypesBO conditionTypesBO,Integer bumctSequence,Integer bumctOrder) throws DataAccessException;
	
	/**
	 * @param hibernateUtil
	 * @param bucltId
	 * @param applyAmountType
	 * @param bucyeSequence
	 * @param variableText
	 * @param conditionTypesBO
	 * @param bumctSequence
	 * @param bumctOrder
	 * @return
	 * @throws DataAccessException
	 */
	public TbbuConditionVariablesUniBO updateTextTranslations(HibernateUtil hibernateUtil,Integer bucltId,String applyAmountType,Integer bucyeSequence,String variableText,TbbuConditionTypesBO conditionTypesBO,Integer bumctSequence,Integer bumctOrder) throws DataAccessException;
	
	/**
	 * @param hibernateUtil
	 * @param bucltId
	 * @param applyAmountType
	 * @param bucyeSequence
	 * @param bucdeCd
	 * @param bumctSequence
	 * @param bumctOrder
	 * @return
	 * @throws DataAccessException
	 */
	public boolean deleteTextTranslations(HibernateUtil hibernateUtil,Integer bucltId,String applyAmountType,Integer bucyeSequence,String bucdeCd, Integer bumctSequence, Integer bumctOrder) throws DataAccessException;
	
	
}
