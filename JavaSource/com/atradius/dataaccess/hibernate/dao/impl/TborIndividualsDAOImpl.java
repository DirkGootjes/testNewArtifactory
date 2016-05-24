/*********************************************************************/
/*                           FILE HEADER                             */
/*********************************************************************/
/*                                                                   */
/*  FileName: 		TborIndividualsDAOImpl.java               	     */
/*  																 */
/*  $Author: INVSAR1 $									             */
/*																	 */
/*  $Revision: 1.8 $										         */
/*  																 */
/*  $Date: 2013/08/16 12:46:12 $                                     */
/*                                                                   */
/*  Description: 	This class performs database operation for       */
/*				    individual details.                              */
/*********************************************************************/
/* Date        Name            Version             Comments          */
/*-------------------------------------------------------------------*/
/* 21/05/2013  INVSAR1      	1.0         Initial version created  */
/*********************************************************************/
package com.atradius.dataaccess.hibernate.dao.impl;


import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.atradius.dataaccess.hibernate.HibernateUtil;
import com.atradius.dataaccess.hibernate.bo.TborIndividualsBO;
import com.atradius.util.logging.ILogger;
import com.atradius.util.logging.LoggerFactory;

public class TborIndividualsDAOImpl {
	private static ILogger logger = LoggerFactory
			.getLogger(TborIndividualsDAOImpl.class);

	/**
	 * @param hibernateUtil
	 * @param indivId
	 * @return
	 */
	public static TborIndividualsBO getIndividualDetails(
			HibernateUtil hibernateUtil, int indivId) {
		logger.enterMethod("getIndividualDetails", indivId);
		TborIndividualsBO indvDetails = null;

		try {
			Session session = hibernateUtil.getSession();
			Criteria indvCrit = session.createCriteria(TborIndividualsBO.class);
			indvCrit.add(Restrictions.eq("indivId", indivId));
			indvDetails = (TborIndividualsBO) indvCrit.uniqueResult();
			
		} catch (HibernateException e) {
			logger.exception(e);
			// e.printStackTrace();
			throw e;
		}
		if (indvDetails == null)
			indvDetails = new TborIndividualsBO();
		logger.exitMethod("getIndividualDetails");
		return indvDetails;
	}

}
