package com.atradius.web.dwr;

import javax.servlet.http.HttpSession;

import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;

import com.atradius.beans.PolicyDocModuleVarBean;
import com.atradius.dataaccess.hibernate.HibernateUtil;
import com.atradius.dataaccess.hibernate.bo.TbpoPolModVariablesBO;
import com.atradius.dataaccess.hibernate.dao.impl.TbpoPolModVariablesDAOImpl;
import com.atradius.sessiondata.ApplicationConstants;
import com.atradius.sessiondata.UserDataObject;
import com.atradius.util.logging.ILogger;
import com.atradius.util.logging.LoggerFactory;

/*********************************************************************/
/*                           FILE HEADER                             */
/*********************************************************************/
/*                                                                   */
/*  FileName: 		PolicyDocModuleVarAJAX.java               	     */
/*  																 */
/*  $Author: INKPAG1 $									             */
/*																	 */
/*  $Revision: 1.3 $										         */
/*  																 */
/*  $Date: 2014/10/13 05:32:12 $                                     */
/*                                                                   */
/*  Description: 	This java class is used for  AJAX methods        */
/*				  	for Policy modules and Variables                 */
/*********************************************************************/
/* Date        Name            Version             Comments          */
/*-------------------------------------------------------------------*/
/* 09/25/2014  INKPAG1      	1.0         Initial version created  */
/** ****************************************************************** */
public class PolicyDocModuleVarAJAX {

	/**
	 * Logger
	 */
	private static ILogger logger = LoggerFactory.getLogger(PolicyDocModuleVarAJAX.class);

	/**
	 * Saves module variable value in the database.
	 * 
	 * @param varValue
	 * @return
	 */
	public PolicyDocModuleVarBean saveModuleVarDesc(final String varValue) {
		logger.enterMethod("saveModuleVarDesc", varValue);
		PolicyDocModuleVarBean policyModuleVarBean = new PolicyDocModuleVarBean();

		String errorMsg = null;
		try {
			WebContext ctx = WebContextFactory.get();
			HttpSession session = ctx.getSession();

			UserDataObject userDataObject = (UserDataObject) session
					.getAttribute(ApplicationConstants.USER_DATA_BEAN);
			if (userDataObject == null) {
				errorMsg = ApplicationConstants.ERROR_INVALID_SESSION;
				policyModuleVarBean.setErrorMsg(errorMsg);
				logger.error(errorMsg);
				return policyModuleVarBean;
			}
			HibernateUtil hibernateUtil = (HibernateUtil) session
					.getAttribute(ApplicationConstants.HIBERNATE_UTIL);
			if (hibernateUtil == null) {
				errorMsg = ApplicationConstants.ERROR_INVALID_SESSION;
				policyModuleVarBean.setErrorMsg(errorMsg);
				return policyModuleVarBean;
			}

			TbpoPolModVariablesDAOImpl dao = new TbpoPolModVariablesDAOImpl();
			TbpoPolModVariablesBO tbpoPolModVarBO = (TbpoPolModVariablesBO) session
					.getAttribute(ApplicationConstants.TBPOPOMODVARBO_PMV);
			tbpoPolModVarBO.setVariableValue(varValue);

			boolean isSaveSuccessFul;
			try {
				isSaveSuccessFul = dao.saveModuleVarDesc(tbpoPolModVarBO,
						hibernateUtil);
			} catch (Exception e) {
				policyModuleVarBean.setSuccess(false);
				policyModuleVarBean.setErrorMsg(ApplicationConstants.ERROR_DATABASE);
				logger.error(ApplicationConstants.ERROR_DATABASE + ":: " + e);
				return policyModuleVarBean;
			}
			if (isSaveSuccessFul) {
				policyModuleVarBean.setVarValue(varValue);
				policyModuleVarBean.setSuccess(Boolean.TRUE);
				policyModuleVarBean
						.setSuccessMessage(ApplicationConstants.POLICY_FREE_TEXT_SUCCESS_MSG);
			} else {
				policyModuleVarBean
						.setErrorMsg(ApplicationConstants.ERROR_RECORD_NOT_EXIST_MSG);
			}
		} catch (Exception e) {
			policyModuleVarBean.setSuccess(false);
			policyModuleVarBean.setErrorMsg(ApplicationConstants.ERROR_DATABASE);
			logger.error(ApplicationConstants.ERROR_DATABASE + ":: " + e);
			return policyModuleVarBean;
		}
		logger.exitMethod("saveModuleVarDesc");

		return policyModuleVarBean;
	}
}
