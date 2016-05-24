/*********************************************************************/
/*                           FILE HEADER                             */
/*********************************************************************/
/*                                                                   */
/*  FileName: 		PaymentConditionsDescAJAX.java               	     */
/*  																 */
/*  $Author: INHYOU1 $									             */
/*																	 */
/*  $Revision: 1.0 $										         */
/*  																 */
/*  $Date: 2015/12/31 05:32:12 $                                     */
/*                                                                   */
/*  Description: 	This java class is used for  AJAX methods        */
/*				  	for Payment conditions Desc                   */
/*********************************************************************/
/* Date        Name            Version             Comments          */
/*-------------------------------------------------------------------*/
/* 2015/12/31  INHYOU1      	1.0         Initial version created  */
/** ****************************************************************** */
package com.atradius.web.dwr;

import javax.servlet.http.HttpSession;

import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;

import com.atradius.beans.PaymentConditionsBean;
import com.atradius.dataaccess.hibernate.HibernateUtil;
import com.atradius.dataaccess.hibernate.bo.TbpoPaymentConditionsBO;
import com.atradius.dataaccess.hibernate.dao.impl.TbpoPaymentConditionsDAOImpl;
import com.atradius.exception.DataAccessException;
import com.atradius.sessiondata.ApplicationConstants;
import com.atradius.sessiondata.UserDataObject;
import com.atradius.util.logging.ILogger;
import com.atradius.util.logging.LoggerFactory;


public class PaymentConditionsDescAJAX {

	/**
	 * Loger
	 */
	private static ILogger logger = LoggerFactory
			.getLogger(PaymentConditionsDescAJAX.class);

	/**
	 * Saves Ciover trade description in temp table.
	 * 
	 * @param tradeDesc
	 * @return
	 */
	public PaymentConditionsBean savePaymentDesc(final String paymentDesc) {
		logger.enterMethod("savePaymentDesc", paymentDesc);
		PaymentConditionsBean paymentConditionsBean = new PaymentConditionsBean();
		try {
			String errorMsg;
			WebContext ctx = WebContextFactory.get();
			HttpSession session = ctx.getSession();

			UserDataObject userDataObject = (UserDataObject) session
					.getAttribute(ApplicationConstants.USER_DATA_BEAN);
			if (userDataObject == null) {
				errorMsg = ApplicationConstants.ERROR_INVALID_SESSION;
				paymentConditionsBean.setErrorMsg(errorMsg);
				logger.error(errorMsg);
				return paymentConditionsBean;
			}
			HibernateUtil hibernateUtil = (HibernateUtil) session
					.getAttribute(ApplicationConstants.HIBERNATE_UTIL);
			if (hibernateUtil == null) {
				errorMsg = ApplicationConstants.ERROR_INVALID_SESSION;
				paymentConditionsBean.setErrorMsg(errorMsg);
				return paymentConditionsBean;
			}

			TbpoPaymentConditionsDAOImpl daoImpl= new TbpoPaymentConditionsDAOImpl();
			TbpoPaymentConditionsBO paymentConditionsBO = (TbpoPaymentConditionsBO) session
					.getAttribute("paymentConditionBO");
			paymentConditionsBO.setPcDescription(paymentDesc);
			boolean isSaveSuccessFul;
			isSaveSuccessFul = daoImpl.savePaymentDesc(paymentConditionsBO, hibernateUtil);


			if (isSaveSuccessFul) {
				paymentConditionsBean.setPcDescription(paymentDesc);
				paymentConditionsBean.setSuccess(Boolean.TRUE);
				paymentConditionsBean
				.setSuccessMessage(ApplicationConstants.POLICY_FREE_TEXT_SUCCESS_MSG);
			} else {
				paymentConditionsBean
				.setErrorMsg(ApplicationConstants.ERROR_RECORD_NOT_EXIST_MSG);
			}
		}catch (DataAccessException e) {
			paymentConditionsBean.setSuccess(false);
			paymentConditionsBean.setErrorMsg(ApplicationConstants.ERROR_DATABASE);
			logger.error(ApplicationConstants.ERROR_DATABASE + ":: " + e);
			return paymentConditionsBean;
		} 
		catch (Exception e) {
			paymentConditionsBean.setSuccess(false);
			paymentConditionsBean.setErrorMsg(ApplicationConstants.ERROR_DATABASE);
			logger.error(ApplicationConstants.ERROR_DATABASE + ":: " + e);
			return paymentConditionsBean;
		}
		logger.exitMethod("savePaymentDesc");

		return paymentConditionsBean;
	}
	}


