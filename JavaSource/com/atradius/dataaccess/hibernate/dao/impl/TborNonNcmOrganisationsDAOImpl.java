/*********************************************************************/
/*                           FILE HEADER                             */
/*********************************************************************/
/*                                                                   */
/*  FileName: 		TborNonNcmOrganisationsDAOImpl.java              */
/*  																 */
/*  $Author: INVSAR1 $									             */
/*																	 */
/*  $Revision: 1.9 $										         */
/*  																 */
/*  $Date: 2013/08/16 12:46:11 $                                     */
/*                                                                   */
/*  Description: 	This class performs database operation for       */
/*				     Non NcmOrganisations details.                   */
/*********************************************************************/
/* Date        Name            Version             Comments          */
/*-------------------------------------------------------------------*/
/* 25/04/2013  INVSAR1      	1.0         Initial version created  */
/*********************************************************************/
package com.atradius.dataaccess.hibernate.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.atradius.dataaccess.hibernate.HibernateUtil;
import com.atradius.dataaccess.hibernate.bo.TborNonNcmOrganisationsBO;
import com.atradius.util.logging.ILogger;
import com.atradius.util.logging.LoggerFactory;

public class TborNonNcmOrganisationsDAOImpl {
	private static ILogger logger = LoggerFactory
			.getLogger(TborNonNcmOrganisationsDAOImpl.class);

	/**
	 * @param hibernateUtil
	 * @param id
	 * @return
	 */
	public static TborNonNcmOrganisationsBO getOrganisation(HibernateUtil hibernateUtil,int id) {
		logger.enterMethod("getOrganisation");
		TborNonNcmOrganisationsBO organisation = new TborNonNcmOrganisationsBO();

		Session session = hibernateUtil.getSession();
		Criteria contactPointCrit = session
				.createCriteria(TborNonNcmOrganisationsBO.class);
		contactPointCrit.add(Restrictions.eq("id", id));
		contactPointCrit.add(Restrictions.gt("effectToDat",
				new java.util.Date()));
		organisation = (TborNonNcmOrganisationsBO) contactPointCrit
				.uniqueResult();
		
		logger.exitMethod("getOrganisation");
		return organisation;
	}
}
