package com.atradius.action;

/*********************************************************************/
/*                           FILE HEADER                             */
/*********************************************************************/
/*                                                                   */
/*  FileName: 		MaintainPaymentConditionsController.java         */
/*  																 */
/*  $Author: INHYOU1 $									             */
/*																	 */
/*  $Revision: 1.0 $										         */
/*  																 */
/*  $Date: 2016/01/08 05:31:20 $                                     */
/*                                                                   */
/*  Description: 	This class collects the input perameter from URL */
/*                                 */
/*                                     */
/*                                                                   */
/*********************************************************************/
/* Date        Name            Version             Comments          */
/*-------------------------------------------------------------------*/
/* 2016/01/08  INHYOU1      	1.0         Initial version created  */

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.atradius.dataaccess.hibernate.HibernateUtil;
import com.atradius.dataaccess.hibernate.bo.TbpoPaymentConditionsBO;
import com.atradius.dataaccess.hibernate.dao.impl.TbpoPaymentConditionsDAOImpl;
import com.atradius.sessiondata.ApplicationConstants;
import com.atradius.util.logging.ILogger;
import com.atradius.util.logging.LoggerFactory;
import com.atradius.utils.ControllerUtil;

public class MaintainPaymentConditionsController extends Action {


	/**
	 * Logger 
	 */
	private static ILogger logger = LoggerFactory
			.getLogger(MaintainPaymentConditionsController.class);

	/**
	 * forward path name in case of Success operation
	 */
	private static final String SUCCESS = "success";

	/** Forward path name in case of Error operation.
	 * 
	 */
	private static final String ERROR = "error";

	/**
	 * Does the initial processing before going to the MaintainPaymentConditionsController.jsp
	 * 
	 * @param mapping
	 *            the ActionMapping. *
	 * @param form
	 *            the ActionForm to be processed. *
	 * @param request
	 *            the HTTP request to be processed. *
	 * @param response
	 *            the HTTP response to be processed. *
	 * @return an int value based on which the request is forwarded to the
	 *         appropriate page.
	 * @exception Exception
	 *                if there was an error in processing the request.
	 */
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		logger.enterMethod("MaintainPaymentConditionsController execute started");

		HttpSession session = request.getSession();
		HibernateUtil hibernateUtil = (HibernateUtil) session
				.getAttribute(ApplicationConstants.HIBERNATE_UTIL);

		boolean sessionFlag= ControllerUtil.getSessionDetails(request,hibernateUtil,session);
		
		if(!sessionFlag){
			return mapping.findForward(ERROR);
		}
		
		int pcPer=(Integer)session.getAttribute("PCper");
		String pcPerTyp=(String)session.getAttribute("PCperTyp");
		String typ=(String)session.getAttribute("Typ");
		String langCode=(String)session.getAttribute("LangCode");
		
		TbpoPaymentConditionsDAOImpl conditionsDAOImpl= new TbpoPaymentConditionsDAOImpl();

		TbpoPaymentConditionsBO paymentConditionsBO = new TbpoPaymentConditionsBO();
		paymentConditionsBO.setPcPer(pcPer);
		paymentConditionsBO.setPcPerTyp(pcPerTyp);
		paymentConditionsBO.setTyp(typ);
		paymentConditionsBO.setLangCode(langCode);
		
		paymentConditionsBO= conditionsDAOImpl.getDetails(paymentConditionsBO, hibernateUtil);
		
		session.setAttribute("paymentConditionBO", paymentConditionsBO);
		request.setAttribute("PCper",pcPer );
		request.setAttribute("PCperTyp", pcPerTyp);
		request.setAttribute("Typ", typ);
		request.setAttribute("Description", paymentConditionsBO.getDescription());
		request.setAttribute("PCDescription", paymentConditionsBO.getPcDescription());
		request.setAttribute("LangCode", langCode);

		String validFlag= paymentConditionsBO.getValidFlag();
		if(validFlag!=null)
			request.setAttribute("ValidFlag", validFlag.toUpperCase(Locale.ENGLISH));
		

		request.setAttribute(ApplicationConstants.ATTR_FORM_MODE, session.getAttribute(ApplicationConstants.ATTR_FORM_MODE));

		logger.exitMethod("MaintainPaymentConditionsController execute Completed");
		return mapping.findForward(SUCCESS);
	}

	

}
