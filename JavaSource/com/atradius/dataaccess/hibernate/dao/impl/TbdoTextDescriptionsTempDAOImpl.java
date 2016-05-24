package com.atradius.dataaccess.hibernate.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.atradius.dataaccess.hibernate.HibernateUtil;
import com.atradius.dataaccess.hibernate.bo.TbdoTextDescriptionsTempBO;
import com.atradius.exception.DataAccessException;
import com.atradius.util.logging.ILogger;
import com.atradius.util.logging.LoggerFactory;

/*********************************************************************/
/*                           FILE HEADER                             */
/*********************************************************************/
/*                                                                   */
/*  FileName: 		TbdoTextDescriptionsTempDAOImpl.java               	         	     */
/*  																 */
/*  $Author: INKPAG1 $									             */
/*																	 */
/*  $Revision: 1.5 $										         */
/*  																 */
/*  $Date: 2014/10/21 02:53:18 $                                     */
/*                                                                   */
/*  Description: 	This java class is used for to get and save the details from 
 * TbdoTextDescriptionsTemp tables    									*/
/*				  					                                 */
/*********************************************************************/
/* Date        Name            Version             Comments          */
/*-------------------------------------------------------------------*/
/* 09/23/2014  INKPAG1      	1.0         Initial version created  */
/** ****************************************************************** */

public class TbdoTextDescriptionsTempDAOImpl {
	private static ILogger logger = LoggerFactory.getLogger(TbdoTextDescriptionsTempDAOImpl.class);

	/**
	 * Fetches the OPD tag details from the database
	 * 
	 * @param hibernateUtil
	 * @param tbdoTextDescBO
	 * @return
	 * @throws DataAccessException 
	 */
	public TbdoTextDescriptionsTempBO getOPDTagDetails(final HibernateUtil hibernateUtil, final TbdoTextDescriptionsTempBO tbdoTextDescBO) throws DataAccessException {

		logger.enterMethod("getOPDTagDetails", "TbdoTextDescriptionsTempDAOImpl");
		TbdoTextDescriptionsTempBO tbdoTextDescDBBO = new TbdoTextDescriptionsTempBO();
		Session session =null;
		try {
			session = hibernateUtil.getSession();
			Criteria criteria = session.createCriteria(TbdoTextDescriptionsTempBO.class);
			criteria.add(Restrictions.eq("changeNumber", tbdoTextDescBO.getChangeNumber()));
			criteria.add(Restrictions.eq("opdTagName", tbdoTextDescBO.getOpdTagName()));
			criteria.add(Restrictions.eq("organization", tbdoTextDescBO.getOrganization()));
			criteria.add(Restrictions.eq("langCd", tbdoTextDescBO.getLangCd()));

			tbdoTextDescDBBO = (TbdoTextDescriptionsTempBO) criteria.uniqueResult();

			if (tbdoTextDescDBBO != null && (tbdoTextDescDBBO.getStatus() == null || tbdoTextDescDBBO.getStatus().length() <= 0)) {
				tbdoTextDescDBBO.setStatus("TEST");
			}
		} catch (HibernateException e) {
			logger.exception(e);
			throw new DataAccessException("DATABASE_QUERRY_FAILED", e);
		}finally{
			if(session!=null){
				session.flush();
				session.close();
			}
		}
		logger.exitMethod("getOPDTagDetails", "TbdoTextDescriptionsTempDAOImpl");
		return tbdoTextDescDBBO;

	}

	/** Saves unicode OPD test in the database
	 * @param tbdoTextDescBO
	 * @param hibernateUtil
	 * @return
	 * @throws Exception
	 */
	public boolean saveOPDTextDef(final TbdoTextDescriptionsTempBO tbdoTextDescBO, final HibernateUtil hibernateUtil) throws Exception {
		logger.enterMethod("saveOPDTextDef started");
		boolean isSaveSuccessful = true;
		Transaction tx = null;
		Session session = null;
		try {
			session = hibernateUtil.getSession();
			tx = session.beginTransaction();

			Query query = session
					.createQuery("UPDATE TbdoTextDescriptionsTempBO set tagVarText= :tagVarText where changeNumber = :changeNumber and "
							+ "opdTagName= :opdTagName and  organization= :organization and langCd= :langCd");

			query.setParameter("tagVarText", tbdoTextDescBO.getTagVarText());
			query.setParameter("changeNumber", tbdoTextDescBO.getChangeNumber());
			query.setParameter("opdTagName", tbdoTextDescBO.getOpdTagName());
			query.setParameter("organization", tbdoTextDescBO.getOrganization());
			query.setParameter("langCd", tbdoTextDescBO.getLangCd());

			int result = query.executeUpdate();
			
			if (result <= 0) {
				isSaveSuccessful = false;
			}
			tx.commit();

		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}			
			logger.exception(e);
			throw new DataAccessException("DATABASE_QUERRY_FAILED", e);
		}
		finally{
			if(session!=null){
				session.flush();
				session.close();
			}
		}
		
		logger.debug("Is OPD text Saved successfully? Ans: " + isSaveSuccessful);
		logger.exitMethod("saveOPDTextDef finished");
		return isSaveSuccessful;
	}

}
