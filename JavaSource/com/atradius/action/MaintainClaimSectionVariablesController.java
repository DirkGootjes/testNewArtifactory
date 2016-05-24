/*********************************************************************/
/*                           FILE HEADER                             */
/*********************************************************************/
/*                                                                   */
/*  FileName:	MaintainClaimSectionVariablesController.java	     */
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

package com.atradius.action;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.atradius.dataaccess.hibernate.HibernateUtil;
import com.atradius.dataaccess.hibernate.bo.TbclClaimsLettersTempBO;
import com.atradius.dataaccess.hibernate.bo.TbclTransLetterVarUniTempBO;
import com.atradius.dataaccess.hibernate.bo.VwclHeaderBO;
import com.atradius.dataaccess.hibernate.bo.VwclTransLetterVariables2BO;
import com.atradius.dataaccess.hibernate.dao.impl.TbclClaimsLettersTempDAOImpl;
import com.atradius.dataaccess.hibernate.dao.impl.TbclTransLetterVarUniTempDAOImpl;
import com.atradius.dataaccess.hibernate.dao.impl.VwclHeaderDAOImpl;
import com.atradius.dataaccess.hibernate.dao.impl.VwclTransLetterVariables2DAOImpl;
import com.atradius.sessiondata.ApplicationConstants;
import com.atradius.sessiondata.UserDataObject;
import com.atradius.util.logging.ILogger;
import com.atradius.util.logging.LoggerFactory;


public class MaintainClaimSectionVariablesController  extends Action{
	private static ILogger logger = LoggerFactory
			.getLogger(MaintainClaimSectionVariablesController.class);

	private static String SUCCESS = "success";

	private static String ERROR = "error";

	/**
	 * Does the initial processing before going to the
	 * MaintainClaimSectionVariables.jsp
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

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		logger.enterMethod("MaintainClaimSectionVariablesController.execute");
		HttpSession session = request.getSession();
		UserDataObject userDataObject = (UserDataObject) session
				.getAttribute(ApplicationConstants.USER_DATA_BEAN);

		if (userDataObject == null) {
			request.setAttribute(ApplicationConstants.ERRMSG,
					ApplicationConstants.ERROR_INVALID_SESSION);
			return mapping.findForward(ERROR);
		}

		HibernateUtil hibernateUtil = (HibernateUtil) session
				.getAttribute(ApplicationConstants.HIBERNATE_UTIL);
		if (hibernateUtil == null) {
			request.setAttribute(ApplicationConstants.ERRMSG,
					ApplicationConstants.ERROR_INVALID_SESSION);
			return mapping.findForward(ERROR);
		}
		
		String claimId = session.getAttribute("claimCaseId").toString();
		String letterType = session.getAttribute("letterType").toString();
		String printDate = session.getAttribute("printDate").toString();

		logger.info("ClaimCseId:["+claimId+"] LetterType:["+letterType+"] PrintDate:["+printDate+"]");
		
		DateFormat formt = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SSS"); 
	    Date pDate=null;
	    Timestamp timestamp =null;
	    try {
	    	pDate = formt.parse(printDate);
	    	 timestamp = new Timestamp(pDate.getTime());

	    } catch (ParseException e) {
	    	logger.error(e.getMessage());
	    }
		
		VwclTransLetterVariables2DAOImpl view = new VwclTransLetterVariables2DAOImpl();
		List<VwclTransLetterVariables2BO> result = view.getCreateLetterData(hibernateUtil ,Integer.valueOf(claimId), letterType, timestamp);
		
		session.setAttribute("result", result);
		
		
		TbclTransLetterVarUniTempDAOImpl tbclTransLetterVarUniTempDAOImpl = new TbclTransLetterVarUniTempDAOImpl();
		List<TbclTransLetterVarUniTempBO> selectedSec = tbclTransLetterVarUniTempDAOImpl.getSelectedClaimSection(hibernateUtil,Integer.valueOf(claimId), letterType, timestamp);
		session.setAttribute("selectedSec", selectedSec);
		
		VwclHeaderDAOImpl view1 = new VwclHeaderDAOImpl();
		VwclHeaderBO header = view1.getHeaderData(hibernateUtil ,Integer.valueOf(claimId));
		session.setAttribute("header", header);
		
		TbclClaimsLettersTempDAOImpl tbclClaimsLettersTempDAOImpl = new TbclClaimsLettersTempDAOImpl();
		TbclClaimsLettersTempBO claimsLetter = tbclClaimsLettersTempDAOImpl.getClaimsLetterData(hibernateUtil,Integer.valueOf(claimId), letterType, timestamp);
		
		
		DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
		String prtDate = df.format(claimsLetter.getTbclClaimsLetterCompositeBO().getPrintDate());
		
		String prntdDate = null;
		if(claimsLetter.getPrintedDate() != null) {
			prntdDate = df.format(claimsLetter.getPrintedDate());
		}
		
		String viaBrokerFlag = claimsLetter.getViaBrokerFlag();
		
		String printType = claimsLetter.getPrintType();
		
		Integer orsusId = claimsLetter.getOrsusId();
		
		String atradiusContactId = claimsLetter.getAtdContactUID();
		
		String letterDes = tbclClaimsLettersTempDAOImpl.getLetterDes(hibernateUtil, letterType);
		String printDes = tbclClaimsLettersTempDAOImpl.getPrintTypeDes(hibernateUtil, printType);
		session.setAttribute("letterDes", letterDes);
		session.setAttribute("printDes", printDes);
		session.setAttribute("prtDate", prtDate);
		session.setAttribute("prtdDate", prntdDate);
		session.setAttribute("viaBrokerFlag", viaBrokerFlag);
		session.setAttribute("orsusId", orsusId);
		session.setAttribute("atradiusContactId", atradiusContactId);
		
		logger.exitMethod("MaintainClaimSectionVariablesController.execute");
		return mapping.findForward(SUCCESS);
	}
}
