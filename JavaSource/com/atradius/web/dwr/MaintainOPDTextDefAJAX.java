package com.atradius.web.dwr;

import javax.servlet.http.HttpSession;

import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;

import com.atradius.beans.MaintainOPDTextBean;
import com.atradius.dataaccess.hibernate.HibernateUtil;
import com.atradius.dataaccess.hibernate.bo.TbdoTextDescriptionsTempBO;
import com.atradius.dataaccess.hibernate.dao.impl.TbdoTextDescriptionsTempDAOImpl;
import com.atradius.exception.DataAccessException;
import com.atradius.sessiondata.ApplicationConstants;
import com.atradius.sessiondata.UserDataObject;
import com.atradius.util.logging.ILogger;
import com.atradius.util.logging.LoggerFactory;

/*********************************************************************/
/*                           FILE HEADER                             */
/*********************************************************************/
/*                                                                   */
/*  FileName: 		MaintainOPDTextDefAJAX.java               	     */
/*  																 */
/*  $Author: INKPAG1 $									             */
/*																	 */
/*  $Revision: 1.3 $										         */
/*  																 */
/*  $Date: 2014/10/13 05:32:12 $                                     */
/*                                                                   */
/*  Description: 	This java class is used for  AJAX methods        */
/*				  	for Maintain OPD text                   */
/*********************************************************************/
/* Date        Name            Version             Comments          */
/*-------------------------------------------------------------------*/
/* 09/25/2014  INKPAG1      	1.0         Initial version created  */
/** ****************************************************************** */
public class MaintainOPDTextDefAJAX {

	/**
	 * Logger
	 */
	private static ILogger logger = LoggerFactory.getLogger(MaintainOPDTextDefAJAX.class);

	/**
	 * SAves OPD text def in database.
	 * 
	 * @param opdTagTextDef
	 * @return
	 */
	public MaintainOPDTextBean saveOPDTagTextDef(final String opdTagTextDef) {
		logger.enterMethod("saveOPDTagTextDef", opdTagTextDef);
		MaintainOPDTextBean maintainOPDTextBean = new MaintainOPDTextBean();
		String errorMsg = null;
		try {
			WebContext ctx = WebContextFactory.get();
			HttpSession session = ctx.getSession();

			UserDataObject userDataObject = (UserDataObject) session
					.getAttribute(ApplicationConstants.USER_DATA_BEAN);
			if (userDataObject == null) {
				errorMsg = ApplicationConstants.ERROR_INVALID_SESSION;
				maintainOPDTextBean.setErrorMsg(errorMsg);
				logger.error(errorMsg);
				return maintainOPDTextBean;
			}
			HibernateUtil hibernateUtil = (HibernateUtil) session
					.getAttribute(ApplicationConstants.HIBERNATE_UTIL);
			if (hibernateUtil == null) {
				errorMsg = ApplicationConstants.ERROR_INVALID_SESSION;
				maintainOPDTextBean.setErrorMsg(errorMsg);
				return maintainOPDTextBean;
			}

			TbdoTextDescriptionsTempDAOImpl dao = new TbdoTextDescriptionsTempDAOImpl();
			TbdoTextDescriptionsTempBO tbdoTextDescBO = (TbdoTextDescriptionsTempBO) session
					.getAttribute(ApplicationConstants.TBDOTEXTDESCBO_POLT);
			tbdoTextDescBO.setTagVarText(opdTagTextDef);

			boolean isSaveSuccessFul = false;
			try {
				isSaveSuccessFul = dao.saveOPDTextDef(tbdoTextDescBO, hibernateUtil);
			} catch (DataAccessException e) {
				maintainOPDTextBean.setSuccess(false);
				maintainOPDTextBean.setErrorMsg(ApplicationConstants.ERROR_DATABASE);
				logger.error(ApplicationConstants.ERROR_DATABASE + ":: " + e);
				return maintainOPDTextBean;
			}
			if (isSaveSuccessFul) {
				maintainOPDTextBean.setOpdTagTextDef(opdTagTextDef);
				maintainOPDTextBean.setSuccess(Boolean.TRUE);
				maintainOPDTextBean
						.setSuccessMessage(ApplicationConstants.POLICY_FREE_TEXT_SUCCESS_MSG);
			} else {
				maintainOPDTextBean
						.setErrorMsg(ApplicationConstants.ERROR_RECORD_NOT_EXIST_MSG);
			}
		} catch (Exception e) {
			maintainOPDTextBean.setSuccess(false);
			maintainOPDTextBean.setErrorMsg(ApplicationConstants.ERROR_DATABASE);
			logger.error(ApplicationConstants.ERROR_DATABASE + ":: " + e);
			return maintainOPDTextBean;
		}
		logger.exitMethod("saveOPDTagTextDef");
		return maintainOPDTextBean;
	}

}
