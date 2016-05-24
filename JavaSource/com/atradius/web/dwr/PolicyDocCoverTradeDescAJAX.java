package com.atradius.web.dwr;

import javax.servlet.http.HttpSession;

import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;

import com.atradius.beans.PolicyDocCoverTradeDescBean;
import com.atradius.dataaccess.hibernate.HibernateUtil;
import com.atradius.dataaccess.hibernate.bo.TbpoPolTradeDescsBO;
import com.atradius.dataaccess.hibernate.dao.impl.TbpoPolTradeDescsDAOImpl;
import com.atradius.sessiondata.ApplicationConstants;
import com.atradius.sessiondata.UserDataObject;
import com.atradius.util.logging.ILogger;
import com.atradius.util.logging.LoggerFactory;

/*********************************************************************/
/*                           FILE HEADER                             */
/*********************************************************************/
/*                                                                   */
/*  FileName: 		PolicyDocCoverTradeDescAJAX.java               	     */
/*  																 */
/*  $Author: INKPAG1 $									             */
/*																	 */
/*  $Revision: 1.3 $										         */
/*  																 */
/*  $Date: 2014/10/13 05:32:12 $                                     */
/*                                                                   */
/*  Description: 	This java class is used for  AJAX methods        */
/*				  	for Policy document Trade Desc                   */
/*********************************************************************/
/* Date        Name            Version             Comments          */
/*-------------------------------------------------------------------*/
/* 09/25/2014  INKPAG1      	1.0         Initial version created  */
/** ****************************************************************** */
public class PolicyDocCoverTradeDescAJAX {
	/**
	 * Loger
	 */
	private static ILogger logger = LoggerFactory
			.getLogger(PolicyDocCoverTradeDescAJAX.class);

	/**
	 * Saves Ciover trade description in temp table.
	 * 
	 * @param tradeDesc
	 * @return
	 */
	public PolicyDocCoverTradeDescBean saveCoverTradeDesc(final String tradeDesc) {
		logger.enterMethod("saveCoverTradeDesc", tradeDesc);
		PolicyDocCoverTradeDescBean policyTradeDescBean = new PolicyDocCoverTradeDescBean();
		try {
			String errorMsg = null;
			WebContext ctx = WebContextFactory.get();
			HttpSession session = ctx.getSession();

			UserDataObject userDataObject = (UserDataObject) session
					.getAttribute(ApplicationConstants.USER_DATA_BEAN);
			if (userDataObject == null) {
				errorMsg = ApplicationConstants.ERROR_INVALID_SESSION;
				policyTradeDescBean.setErrorMsg(errorMsg);
				logger.error(errorMsg);
				return policyTradeDescBean;
			}
			HibernateUtil hibernateUtil = (HibernateUtil) session
					.getAttribute(ApplicationConstants.HIBERNATE_UTIL);
			if (hibernateUtil == null) {
				errorMsg = ApplicationConstants.ERROR_INVALID_SESSION;
				policyTradeDescBean.setErrorMsg(errorMsg);
				return policyTradeDescBean;
			}

			/*
			 * String policyId=(String)session.getAttribute("policyId"); String
			 * effect_date=(String)session.getAttribute("effect_date");
			 */

			TbpoPolTradeDescsDAOImpl dao = new TbpoPolTradeDescsDAOImpl();
			TbpoPolTradeDescsBO tbpoPolTradeDescsBO = (TbpoPolTradeDescsBO) session
					.getAttribute(ApplicationConstants.TBPOPOLTRADEBO_PTD);
			tbpoPolTradeDescsBO.setTradeDesc(tradeDesc);

			boolean isSaveSuccessFul;
			try {
				isSaveSuccessFul = dao.saveCoverTradeDesc(tbpoPolTradeDescsBO,
						hibernateUtil);
			} catch (Exception e) {
				policyTradeDescBean.setSuccess(false);
				policyTradeDescBean.setErrorMsg(ApplicationConstants.ERROR_DATABASE);
				logger.error(ApplicationConstants.ERROR_DATABASE + ":: " + e);
				return policyTradeDescBean;
			}
			if (isSaveSuccessFul) {
				policyTradeDescBean.setTradeDesc(tradeDesc);
				policyTradeDescBean.setSuccess(Boolean.TRUE);
				policyTradeDescBean
						.setSuccessMessage(ApplicationConstants.POLICY_FREE_TEXT_SUCCESS_MSG);
			} else {
				policyTradeDescBean
						.setErrorMsg(ApplicationConstants.ERROR_RECORD_NOT_EXIST_MSG);
			}
		} catch (Exception e) {
			policyTradeDescBean.setSuccess(false);
			policyTradeDescBean.setErrorMsg(ApplicationConstants.ERROR_DATABASE);
			logger.error(ApplicationConstants.ERROR_DATABASE + ":: " + e);
			return policyTradeDescBean;
		}
		logger.exitMethod("saveCoverTradeDesc");

		return policyTradeDescBean;
	}
}
