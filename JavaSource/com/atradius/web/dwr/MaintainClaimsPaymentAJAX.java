/*********************************************************************/
/*                           FILE HEADER                             */
/*********************************************************************/
/*                                                                   */
/*  FileName:	MaintainClaimsPaymentAJAX.java				         */
/*  																 */
/*  Author: INVSAR1										             */
/*																	 */
/*  Date: 26 Aug 2014				                                 */
/*                                                                   */
/*  Description: 													 */
/*********************************************************************/
/* Date        Name            Version             Comments          */
/*-------------------------------------------------------------------*/
/*26 Aug 2014  	INVSAR1      	1.0         Initial version created  */
/*********************************************************************/

package com.atradius.web.dwr;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;

import com.atradius.beans.AJAXResult;
import com.atradius.beans.ClaimsPaymentTextBean;
import com.atradius.dataaccess.hibernate.HibernateUtil;
import com.atradius.dataaccess.hibernate.bo.TbclAssessCalcLiabilitiesBO;
import com.atradius.dataaccess.hibernate.bo.TbclClaimsCalcLiabilitiesBO;
import com.atradius.dataaccess.hibernate.dao.ClaimsCalcLiabilitiesDAO;
import com.atradius.dataaccess.hibernate.dao.impl.ClaimsCalcLiabilitiesDAOImpl;
import com.atradius.sessiondata.ApplicationConstants;
import com.atradius.sessiondata.UserDataObject;
import com.atradius.util.logging.ILogger;
import com.atradius.util.logging.LoggerFactory;

public class MaintainClaimsPaymentAJAX {
	private static ILogger logger = LoggerFactory
			.getLogger(MaintainClaimsPaymentAJAX.class);

	public AJAXResult saveClaimsPaymentText(ClaimsPaymentTextBean[] list) {
		logger.info("saveClaimsPaymentText.enter");
		String errorMsg = null;
		AJAXResult returnBean = new AJAXResult();
		try {
			
			WebContext ctx = WebContextFactory.get();
			HttpSession session = ctx.getSession();
			UserDataObject userDataObject = (UserDataObject) session
					.getAttribute(ApplicationConstants.USER_DATA_BEAN);

			if (userDataObject == null) {
				errorMsg = ApplicationConstants.ERROR_INVALID_SESSION;
				logger.error(errorMsg);
				returnBean.setErrorMessage(errorMsg);
				return returnBean;
			}
			HibernateUtil hibernateUtil = (HibernateUtil) session
					.getAttribute(ApplicationConstants.HIBERNATE_UTIL);
			if (hibernateUtil == null) {
				errorMsg = ApplicationConstants.ERROR_INVALID_SESSION;
				returnBean.setErrorMessage(errorMsg);
				logger.error(errorMsg);
				return returnBean;
			}
			HashMap<String, TbclClaimsCalcLiabilitiesBO> map = (HashMap<String, TbclClaimsCalcLiabilitiesBO>) session
					.getAttribute(ApplicationConstants.ATTR_CLAIM_PAYMENT_TABLE);
			if(map == null){
				errorMsg = ApplicationConstants.ERROR_INVALID_SESSION;
				returnBean.setErrorMessage(errorMsg);
				logger.error(errorMsg);
				return returnBean;
			}
			
			List<TbclClaimsCalcLiabilitiesBO> beanList = new ArrayList<TbclClaimsCalcLiabilitiesBO>();
			for(int i=0;i<list.length;i++){
				String key = list[i].getClcptId()+"_"+list[i].getClclcCode();
				TbclClaimsCalcLiabilitiesBO bean = map.get(key);
				
				if (list[i].getCalcFreeTextUni() != null && list[i].getCalcFreeTextUni().length() != 0) {
					if (list[i].getCalcFreeTextUni().length() > ApplicationConstants.LENGTH_CONSTANT_TEXTAREA) {
						returnBean.setSuccess(false);
						returnBean.setErrorMessage("Text at row " + (i + 1)
								+ " is more than "
								+ ApplicationConstants.LENGTH_CONSTANT_TEXTAREA
								+ " characters.");
						return returnBean;
					}
				}
				
//				 mark new records for save
				if (list[i].getCalcFreeTextUni() != null && list[i].getCalcFreeTextUni().trim().length() > 0) {
					if (!list[i].getCalcFreeTextUni().equals(bean.getCalcFreeTextUni())) {
						TbclClaimsCalcLiabilitiesBO newBean = (TbclClaimsCalcLiabilitiesBO) bean
								.clone();
						newBean.setCalcFreeTextUni(list[i].getCalcFreeTextUni().trim());
						beanList.add(newBean);
					}
				} else {
					if (bean.getCalcFreeTextUni() != null
							&& bean.getCalcFreeTextUni().trim().length() > 0) {
						TbclClaimsCalcLiabilitiesBO newBean = (TbclClaimsCalcLiabilitiesBO) bean
								.clone();
						newBean.setCalcFreeTextUni(list[i].getCalcFreeTextUni().trim());
						beanList.add(newBean);
					}
				}
			}
		
			ClaimsCalcLiabilitiesDAO daoService = new ClaimsCalcLiabilitiesDAOImpl();
			
			//delete old records
			
			Long paymentId = Long.valueOf((String) session
					.getAttribute(ApplicationConstants.PAYMENT_ID));
			
			logger.info("trying to delete records for paymentId ["
					+ paymentId + "] in AJAX ");
			
			daoService.delete(hibernateUtil, paymentId);
			// save new records
			logger.info("trying to Save ["+beanList.size()+"]  records for paymentId ["
					+ paymentId + "] in AJAX ");
			daoService.updateUnicode(hibernateUtil,beanList);
			
			returnBean.setSuccess(true);
			returnBean
					.setSuccessMessage(ApplicationConstants.INFO_SUCCESS_SAVE);
			
		} catch (RuntimeException e) {
			logger.exception(e);
			returnBean.setSuccess(false);
			returnBean.setErrorMessage(ApplicationConstants.ERROR_DATABASE);
		} catch (CloneNotSupportedException e) {
			logger.exception(e);
			returnBean.setSuccess(false);
			returnBean.setErrorMessage(ApplicationConstants.ERROR_DATABASE);
		}
		logger.info("saveClaimsPaymentText.exit");
		return returnBean;

	}

}
