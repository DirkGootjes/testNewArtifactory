/*********************************************************************/
/*                           FILE HEADER                             */
/*********************************************************************/
/*                                                                   */
/*  FileName: 		ApplicationController.java             	  	     */
/*  																 */
/*  $Author: INKPAG1 $									             */
/*																	 */
/*  $Revision: 1.21 $										         */
/*  																 */
/*  $Date: 2014/10/13 05:31:20 $                                     */
/*                                                                   */
/*  Description: 	This class collects the input perameter from URL */
/*                 and navigates the request to Text                 */
/*                contact or individual jsp form                     */
/*                                                                   */
/*********************************************************************/
/* Date        Name            Version             Comments          */
/*-------------------------------------------------------------------*/
/* 04/04/2013  INVSAR1      	1.0         Initial version created  */
/* 14/11/2013  INRSHR1      	1.2         Updated for T code editor*/
/*********************************************************************/
/*-------------------------------------------------------------------*/
/* 22/11/2013  INNBHA1      	2.0      Updated for Review Comments */
/* 26/08/2014  INVSAR1      	2.1      Updated for Claims Unicode  */
/** ****************************************************************** */
// package
package com.atradius.action;

// imports
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.atradius.dataaccess.hibernate.bo.TbdoTextDescriptionsTempBO;
import com.atradius.sessiondata.ApplicationConstants;
import com.atradius.sessiondata.UserDataObject;
import com.atradius.util.logging.ILogger;
import com.atradius.util.logging.LoggerFactory;
import com.atradius.utils.CommonUtil;

public class ApplicationController extends Action {

	private static ILogger logger = LoggerFactory
			.getLogger(ApplicationController.class);

	private static String ERROR = "error";

	private static String CONTACT_PAGE = "contact";

	private static String INDIVIDUAL_PAGE = "individual";

	private static String TEXT_PAGE = "text";

	private static String FREETEXT_PAGE = "freetext"; // Added for FreeText

	// Editor CRN

	private static String CONDITIONCODE_PAGE = "conditioncode"; // Added for

	// Tcode Editor
	// CRN

	private static String MULTICHOICE_PAGE = "multiChoiceConditioncode"; // Added

	// for
	// Tcode
	// Editor
	// CRN

	// Added by INVSAR1 for Claims on 26.08.2014
	private static String CREATE_LETTER = "createletter";

	private static String DEBT_RLC = "debtrlc";

	private static String PLC_ASSESSMENT = "plcassessment";

	private static String COST_CALCULATION = "costcalculation";

	private static String CLAIMS_PAYMENT = "claimspayment";

/**
	 * Forward path name of Policy Document Free Text for jsp screen.
	 */
	private static final String POLICY_FREE_TEXT_PAGE = "policyFreeText";

	/**
	 * Forward path name of Policy Document Cover Trade Description for jsp
	 * screen.
	 */
	private static final String POLICY_COVER_TRADE_DESC_PAGE = "policyCoverTradeDesc";

	/**
	 * Forward path name of Policy details modules and variables for jsp screen.
	 */
	private static final String POLICY_MODULE_VAR_VALUE_PAGE = "policyModuleVarValue";

	/**
	 * Forward path name of Policy maintain OPD text for jsp screen.
	 */
	private static final String MAINTAIN_OPD_TEXT_VAR_VALUE_PAGE = "maintainOPDTextVarValue";


	/**
	 * This class collects the input perameter from URL and navigates the
	 * request to Text Contact or individual Controller form
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

		logger.enterMethod("ApplicationController");
		HttpSession session = request.getSession();
		UserDataObject userDataObject = (UserDataObject) session
				.getAttribute(ApplicationConstants.USER_DATA_BEAN);

		if (userDataObject == null) {
			request.setAttribute(ApplicationConstants.ERRMSG,
					ApplicationConstants.ERROR_INVALID_SESSION);
			return mapping.findForward(ERROR);
		}
		String forward = "";
		String source = request.getParameter(ApplicationConstants.PARAM_SOURCE);
		logger.debug("Source name: "+source);
		if (source != null) {
			if (source
					.equalsIgnoreCase(ApplicationConstants.PARAM_VALUE_CONTACT)) {
				String pkId = request.getParameter("pkId");
				if (pkId == null || pkId.trim().length() <= 0) {
					request.setAttribute(ApplicationConstants.ERRMSG,
							ApplicationConstants.ERROR_PARAM_MISSING);
					forward = ERROR;
				} else {
					try {
						int ornnnId = Integer.parseInt(pkId.trim());
						session.setAttribute(
								ApplicationConstants.ATTR_ORGANISATION_NO,
								ornnnId);
						forward = CONTACT_PAGE;
					} catch (RuntimeException e) {
						// e.printStackTrace();
						logger.exception(e);
						request.setAttribute(ApplicationConstants.ERRMSG,
								ApplicationConstants.ERROR_PARAM_INVALID);
						forward = ERROR;
					}
				}
			} else if (source
					.equalsIgnoreCase(ApplicationConstants.PARAM_VALUE_INDIVIDUAL)) {
				String indvId = request.getParameter("pkId");
				if (indvId == null || indvId.trim().length() <= 0) {
					request.setAttribute(ApplicationConstants.ERRMSG,
							ApplicationConstants.ERROR_PARAM_MISSING);
					forward = ERROR;
				} else {
					try {
						session.setAttribute("indvId", indvId);
						forward = INDIVIDUAL_PAGE;
					} catch (RuntimeException e) {
						// e.printStackTrace();
						logger.exception(e);
						request.setAttribute(ApplicationConstants.ERRMSG,
								ApplicationConstants.ERROR_PARAM_INVALID);
						forward = ERROR;
					}
				}
			} else if (source
					.equalsIgnoreCase(ApplicationConstants.PARAM_VALUE_TEXT)) {
				String textId = request.getParameter("pkId");
				if (textId == null || textId.trim().length() <= 0) {
					request.setAttribute(ApplicationConstants.ERRMSG,
							ApplicationConstants.ERROR_PARAM_MISSING);
					forward = ERROR;
				} else {
					try {
						int textIdlocal = Integer.parseInt(textId.trim());
						session.setAttribute("textId", textId);
						forward = TEXT_PAGE;
					} catch (RuntimeException e) {
						// e.printStackTrace();
						logger.exception(e);
						request.setAttribute(ApplicationConstants.ERRMSG,
								ApplicationConstants.ERROR_PARAM_INVALID);
						forward = ERROR;
					}
				}

			}/** * Added for FreeText Editor CRN [START] ** */
			else if (source
					.equalsIgnoreCase(ApplicationConstants.PARAM_VALUE_FREETEXT)) {

				String bucltId = request.getParameter("bucltId");
				String bucdeCd = request.getParameter("bucdeCd");
				String bucyeSequence = request.getParameter("bucyeSequence");
				String applyAmountType = request
						.getParameter("applyAmountType");
				String bumctSequence = request.getParameter("bumctSequence");
				String bumctOrder = request.getParameter("bumctOrder");
				// added for defect 5857 - Free text editor not accessable via
				// "View CLD" function/screen - Change Request
				String formMode = request.getParameter("formMode");

				if ((bucltId == null || bucltId.trim().length() <= 0)
						|| (bucdeCd == null || bucdeCd.trim().length() <= 0)
						|| (applyAmountType == null || applyAmountType.trim()
								.length() <= 0)) {
					request.setAttribute(ApplicationConstants.ERRMSG,
							ApplicationConstants.ERROR_PARAM_MISSING);
					forward = ERROR;
				} else {
					try {
						session.setAttribute("bucltId", bucltId);
						session.setAttribute("bucdeCd", bucdeCd);
						session.setAttribute("bucyeSequence", bucyeSequence);
						session
								.setAttribute("applyAmountType",
										applyAmountType);
						session.setAttribute("bumctSequence", bumctSequence);
						session.setAttribute("bumctOrder", bumctOrder);
						if (formMode == null & formMode.trim().equals("")) {
							formMode = "VIEW";
						}
						session.setAttribute("formMode", formMode);
						forward = FREETEXT_PAGE;
					} catch (RuntimeException e) {
						logger.exception(e);
						request.setAttribute(ApplicationConstants.ERRMSG,
								ApplicationConstants.ERROR_PARAM_INVALID);
						forward = ERROR;
					}
				}

			}/** * Added for FreeText Editor CRN [END] ** */
			/** * Added for TCode Editor CRN [START] ** */
			else if (source
					.equalsIgnoreCase(ApplicationConstants.PARAM_VALUE_TCODE)) {
				String bucdeCd = request.getParameter("bucdeCd");
				String langCode = request.getParameter("langCode");
				if ((bucdeCd == null || bucdeCd.trim().length() <= 0)
						|| (langCode == null || langCode.trim().length() <= 0)) {
					request.setAttribute(ApplicationConstants.ERRMSG,
							ApplicationConstants.ERROR_PARAM_MISSING);
					forward = ERROR;
				} else {
					try {

						session.setAttribute("bucdeCd", bucdeCd);
						session.setAttribute("langCode", langCode);
						forward = CONDITIONCODE_PAGE;
					} catch (RuntimeException e) {
						// e.printStackTrace();
						logger.exception(e);
						request.setAttribute(ApplicationConstants.ERRMSG,
								ApplicationConstants.ERROR_PARAM_INVALID);
						forward = ERROR;
					}
				}

			} else if (source
					.equalsIgnoreCase(ApplicationConstants.PARAM_VALUE_MULTICHOICE)) {
				String bucdeCd = request.getParameter("bucdeCd");
				// String langCode = request.getParameter("langCode");
				if ((bucdeCd == null || bucdeCd.trim().length() <= 0)) {
					request.setAttribute(ApplicationConstants.ERRMSG,
							ApplicationConstants.ERROR_PARAM_MISSING);
					forward = ERROR;
				} else {
					try {
						session.setAttribute("bucdeCd", bucdeCd);
						forward = MULTICHOICE_PAGE;
					} catch (RuntimeException e) {
						// e.printStackTrace();
						logger.exception(e);
						request.setAttribute(ApplicationConstants.ERRMSG,
								ApplicationConstants.ERROR_PARAM_INVALID);
						forward = ERROR;
					}
				}

			}/** * Added for TCode Editor CRN [END] ** */
			// Added by INVSAR1 for Claims on 26.08.2014
			else if (source
					.equalsIgnoreCase(ApplicationConstants.PARAM_VALUE_CREATE_LETTER)) {
				// TODO basic validation if valid go to success page otherwise
				// forward to error
				String claim = request.getParameter("claim");
				String letter = request.getParameter("typ");
				String date = request.getParameter("printDate");

				String readOnly = request.getParameter("readOnly");
				if (readOnly == null)
					readOnly = "N";
				if ((claim == null || claim.trim().length() <= 0)
						|| (letter == null || letter.trim().length() <= 0)
						|| (date == null || date.trim().length() <= 0)) {
					request.setAttribute(ApplicationConstants.ERRMSG,
							ApplicationConstants.ERROR_PARAM_MISSING);
					forward = ERROR;
				} else {
					session.setAttribute("claimCaseId", claim);
					session.setAttribute("letterType", letter);
					session.setAttribute("printDate", date);
					session.setAttribute(ApplicationConstants.READ_ONLY, readOnly);
					forward = CREATE_LETTER;
				}

			} else if (source
					.equalsIgnoreCase(ApplicationConstants.PARAM_VALUE_DEBT_RLC)) {
				// TODO basic validation if valid go to success page otherwise
				// forward to error

				String bucceId = request.getParameter("bucceId");
				String assessType = request.getParameter("assessType");
				String date = request.getParameter("runDate");
				String readOnly = request.getParameter("readOnly");
				if (readOnly == null)
					readOnly = "N";

				if ((bucceId == null || bucceId.trim().length() <= 0)) {
					request.setAttribute(ApplicationConstants.ERRMSG,
							ApplicationConstants.ERROR_PARAM_MISSING);
					forward = ERROR;
				} else {
					session.setAttribute(ApplicationConstants.PARAM_SOURCE,
							ApplicationConstants.PARAM_VALUE_DEBT_RLC);
					session.setAttribute("bucceId", bucceId);
					session.setAttribute("assessType", assessType);
					session.setAttribute("runDate", date);
					session.setAttribute(ApplicationConstants.READ_ONLY,
							readOnly);
					forward = DEBT_RLC;
				}
			} else if (source
					.equalsIgnoreCase(ApplicationConstants.PARAM_VALUE_PLC_ASSESSMENT)) {
				// TODO basic validation if valid go to success page otherwise
				// forward to error
				String bucceId = request.getParameter("bucceId");
				String assessType = request.getParameter("assessType");
				String date = request.getParameter("runDate");
				String readOnly = request.getParameter("readOnly");
				if (readOnly == null)
					readOnly = "N";

				if ((bucceId == null || bucceId.trim().length() <= 0)) {
					request.setAttribute(ApplicationConstants.ERRMSG,
							ApplicationConstants.ERROR_PARAM_MISSING);
					forward = ERROR;
				} else {
					session.setAttribute(ApplicationConstants.PARAM_SOURCE,
							ApplicationConstants.PARAM_VALUE_PLC_ASSESSMENT);
					session.setAttribute("bucceId", bucceId);
					session.setAttribute("assessType", assessType);
					session.setAttribute("runDate", date);
					session.setAttribute(ApplicationConstants.READ_ONLY,
							readOnly);
					forward = PLC_ASSESSMENT;
				}
			} else if (source
					.equalsIgnoreCase(ApplicationConstants.PARAM_VALUE_COST_CALCULATION)) {
				// TODO basic validation if valid go to success page otherwise
				// forward to error
				String bucceId = request.getParameter("bucceId");
				String assessType = request.getParameter("assessType");
				String date = request.getParameter("runDate");
				String readOnly = request.getParameter("readOnly");
				if (readOnly == null)
					readOnly = "N";

				if ((bucceId == null || bucceId.trim().length() <= 0)) {
					request.setAttribute(ApplicationConstants.ERRMSG,
							ApplicationConstants.ERROR_PARAM_MISSING);
					forward = ERROR;
				} else {
					session.setAttribute(ApplicationConstants.PARAM_SOURCE,
							ApplicationConstants.PARAM_VALUE_COST_CALCULATION);
					session.setAttribute("bucceId", bucceId);
					session.setAttribute("assessType", assessType);
					session.setAttribute("runDate", date);
					session.setAttribute(ApplicationConstants.READ_ONLY,
							readOnly);
					forward = COST_CALCULATION;
				}
			} else if (source
					.equalsIgnoreCase(ApplicationConstants.PARAM_VALUE_CLAIMS_PAYMENT)) {
				String paymentId = request.getParameter("clcptId");

				String readOnly = request.getParameter("readOnly");
				if (readOnly == null)
					readOnly = "N";

				if ((paymentId == null || paymentId.trim().length() <= 0)) {
					request.setAttribute(ApplicationConstants.ERRMSG,
							ApplicationConstants.ERROR_PARAM_MISSING);
					forward = ERROR;
				} else {
					session.setAttribute(ApplicationConstants.PARAM_SOURCE,
							ApplicationConstants.PARAM_VALUE_CLAIMS_PAYMENT);
					session.setAttribute(ApplicationConstants.PAYMENT_ID,
							paymentId);
					session.setAttribute(ApplicationConstants.READ_ONLY,
							readOnly);
					forward = CLAIMS_PAYMENT;
				}
			}
			// end of Addition by INVSAR1 for Claims on 26.08.2014
			/** Added for Moudula Policy [START]* */
			else if (ApplicationConstants.PARAM_VALUE_POLICY_FREE_TEXT
					.equalsIgnoreCase(source)) {

				forward = forwardRequestToPolicyFreeText(request, session);
			} else if (ApplicationConstants.PARAM_VALUE_POLICY_COVER_TRADE_DESC
					.equalsIgnoreCase(source)) {
				forward = forwardRequestToPolicyCoverTradeDesc(request, session);
			} else if (ApplicationConstants.PARAM_VALUE_POLICY_MODULE_VAR_VALUE
					.equalsIgnoreCase(source)) {
				forward = forwardRequestToPolicyModuleVarValue(request, session);
			} else if (ApplicationConstants.PARAM_VALUE_MAINTAIN_OPD_TEXT_VAR_VALUE
					.equalsIgnoreCase(source)) {
				forward = forwardRequestToMaintainOPDTextVarValue(request, session);
			}
			// Added by INHYOU1 for Modula CRN-- Start
			else if(ApplicationConstants.PARAM_VALUE_PAYMENT_CONDITION
					.equalsIgnoreCase(source)){   //for Payment Conditions Screen
				forward = forwardRequestToMaintainPaymentConditions(request, session);
				
			}
			
			// Added by INHYOU1 for Modula CRN-- END
			else {
				request.setAttribute(ApplicationConstants.ERRMSG,
						ApplicationConstants.ERROR_INVALID_SOURCE);
				logger.error("Source "+source+" is invalid.");				
				forward = ERROR;
			}
		} 
	
		else {
			request.setAttribute(ApplicationConstants.ERRMSG,
					ApplicationConstants.ERROR_BLANK_SOURCE);
			forward = ERROR;
		}
		logger.exitMethod("ApplicationController");
		return mapping.findForward(forward);

	}
/**
	 * Forward request to policy Document Free text screen.
	 * 
	 * @param request
	 * @param session
	 * @return
	 */
	private String forwardRequestToPolicyFreeText(final HttpServletRequest request,
			final HttpSession session) {
		logger.enterMethod("forwardRequestToPolicyFreeText", "Started");
		String forward = POLICY_FREE_TEXT_PAGE;
		/***********************************************************************
		 * http://localhost:9080/MaintainUnicodes_rad/index.do?userId=INKPAG1&password=smita56&randomKey=555566&identifier=PFT&
		 * policyId=555655&effect_date=08092014100002&grp_cd=MS10&grp_func_desc="Introduction"&formMode="UPDATE"
		 **********************************************************************/

		String policyId = request.getParameter(ApplicationConstants.ATTR_POLICYID_PFT);
		String print_date = request.getParameter(ApplicationConstants.ATTR_PDATE_PFT);
		String grp_cd = request.getParameter(ApplicationConstants.ATTR_GCD_PFT);
		String grp_func_desc = request.getParameter(ApplicationConstants.ATTR_GFD_PFT);
		String formModeValue = request.getParameter(ApplicationConstants.ATTR_FORM_MODE);
		
		logger.info("policyId: "+policyId);
		logger.info("print_date: "+print_date);
		logger.info("grp_cd: "+grp_cd);
		logger.info("grp_func_desc: "+grp_func_desc);
		logger.info("formModeValue: "+formModeValue);
				
		if (policyId == null || policyId.length() <= 0 || print_date == null
				|| print_date.length() <= 0 || grp_cd == null || grp_cd.length() <= 0
				|| grp_func_desc == null || grp_func_desc.length() <= 0) {
			request.setAttribute(ApplicationConstants.ERRMSG,
					ApplicationConstants.ERROR_PARAM_MISSING);
			forward = ERROR;
		} else {
			try {
				
				session.setAttribute(ApplicationConstants.ATTR_POLICYID_PFT, policyId);
				session.setAttribute(ApplicationConstants.ATTR_PDATE_PFT, print_date);
				session.setAttribute(ApplicationConstants.ATTR_GCD_PFT, grp_cd);
				session.setAttribute(ApplicationConstants.ATTR_GFD_PFT, grp_func_desc);

				formModeValue=CommonUtil.setFormModeIfEmpty(formModeValue);
				
				session.setAttribute(ApplicationConstants.ATTR_FORM_MODE, formModeValue);

				forward = POLICY_FREE_TEXT_PAGE;
			} catch (Exception e) {
				logger.exception(e);				
				request.setAttribute(ApplicationConstants.ERRMSG,
						ApplicationConstants.ERROR_PARAM_INVALID);
				forward = ERROR;
			}
		}

		logger.exitMethod("forwardRequestToPolicyFreeText", "Ended");
		return forward;
	}

	/**Forward request to Policy Document Cover Trade Description.
	 * 
	 * 
	 * @param request
	 * @param session
	 * @return
	 */
	private String forwardRequestToPolicyCoverTradeDesc(final HttpServletRequest request,
			final HttpSession session) {
		logger.enterMethod("forwardRequestToPolicyCoverTradeDesc", "Started");
		String forward = "";
		String policyId = request.getParameter(ApplicationConstants.ATTR_POLICYID_PTD);
		String effectFromDate = request.getParameter(ApplicationConstants.ATTR_EFDATE_PTD);
		String formModeValue = request.getParameter(ApplicationConstants.ATTR_FORM_MODE);

		logger.info("policyId: "+policyId);
		logger.info("effectFromDate: "+effectFromDate);
		logger.info("formModeValue: "+formModeValue);
		
		if (policyId == null || policyId.length() <= 0 || effectFromDate == null
				|| effectFromDate.length() <= 0) {
			request.setAttribute(ApplicationConstants.ERRMSG,
					ApplicationConstants.ERROR_PARAM_MISSING);
			forward = ERROR;
		} else {
			try {
				session.setAttribute(ApplicationConstants.ATTR_POLICYID_PTD, policyId);
				session.setAttribute(ApplicationConstants.ATTR_EFDATE_PTD, effectFromDate);

				formModeValue=CommonUtil.setFormModeIfEmpty(formModeValue);
				session.setAttribute(ApplicationConstants.ATTR_FORM_MODE, formModeValue);

				forward = POLICY_COVER_TRADE_DESC_PAGE;
			} catch (Exception e) {
				logger.exception(e);				
				request.setAttribute(ApplicationConstants.ERRMSG,
						ApplicationConstants.ERROR_PARAM_INVALID);
				forward = ERROR;
			}
		}

		logger.exitMethod("forwardRequestToPolicyCoverTradeDesc", "Ended");

		return forward;
	}

	/**Forward request to policy Details Modules And Variables Screen.
	 * 
	 * 
	 * @param request
	 * @param session
	 * @return
	 */
	private String forwardRequestToPolicyModuleVarValue(final HttpServletRequest request,
			final HttpSession session) {
		logger.enterMethod("forwardRequestToPolicyModuleVarValue", "Started");
		String forward = "";
		String policyId = request.getParameter(ApplicationConstants.ATTR_POLICY_ID_PMV);
		String effectFromDate = request.getParameter(ApplicationConstants.ATR_EFFECT_DT_PMV);
		String variableCode = request.getParameter(ApplicationConstants.ATTR_VAR_CD_PMV);
		String formModeValue = request.getParameter(ApplicationConstants.ATTR_FORM_MODE);

		logger.info("policyId: "+policyId);
		logger.info("effectFromDate: "+effectFromDate);
		logger.info("variableCode: "+variableCode);
		logger.info("formMode: "+formModeValue);
		
		if (policyId == null || policyId.length() <= 0 || effectFromDate == null
				|| effectFromDate.length() <= 0 || variableCode == null
				|| variableCode.length() <= 0) {
			request.setAttribute(ApplicationConstants.ERRMSG,
					ApplicationConstants.ERROR_PARAM_MISSING);
			forward = ERROR;
		} else {
			try {
								
				session.setAttribute(ApplicationConstants.ATR_EFFECT_DT_PMV, effectFromDate);
				session.setAttribute(ApplicationConstants.ATTR_VAR_CD_PMV, variableCode);
				session.setAttribute(ApplicationConstants.ATTR_POLICY_ID_PMV, policyId);

				formModeValue=CommonUtil.setFormModeIfEmpty(formModeValue);
				session.setAttribute(ApplicationConstants.ATTR_FORM_MODE, formModeValue);

				forward = POLICY_MODULE_VAR_VALUE_PAGE;
			} catch (Exception e) {
				logger.exception(e);				
				request.setAttribute(ApplicationConstants.ERRMSG,
						ApplicationConstants.ERROR_PARAM_INVALID);
				forward = ERROR;
			}
		}

		logger.exitMethod("forwardRequestToPolicyModuleVarValue", "Ended");

		return forward;
	}

	/**Forward request to Maintain OPD text JSP
	 * 
	 * 
	 * @param request
	 * @param session
	 * @return
	 */
	private String forwardRequestToMaintainOPDTextVarValue(
			final HttpServletRequest request, final HttpSession session) {
		logger.enterMethod("forwardRequestToMaintainOPDTextVarValue", "Started");
		String forward = "";
		String changeNum = request.getParameter(ApplicationConstants.ATTR_CHANGE_NUM_POLT);
		String opdTag = request.getParameter(ApplicationConstants.ATTR_OPD_TAG_POLT);
		String orgName = request.getParameter(ApplicationConstants.ATTR_ORG_NAME_POLT);
		String langCd = request.getParameter(ApplicationConstants.ATTR_LANG_CD_POLT);
		String formModeValue = request.getParameter(ApplicationConstants.ATTR_FORM_MODE);

		logger.info("changeNum: "+changeNum);
		logger.info("opdTag: "+opdTag);
		logger.info("orgName: "+orgName);
		logger.info("langCd: "+langCd);
		logger.info("formMode: "+formModeValue);
		
		if (changeNum == null || changeNum.length() <= 0 || opdTag == null
				|| opdTag.length() <= 0 || orgName == null || orgName.length() <= 0
				|| langCd == null || langCd.length() <= 0) {
			request.setAttribute(ApplicationConstants.ERRMSG,
					ApplicationConstants.ERROR_PARAM_MISSING);
			forward = ERROR;
		} else {
			try {
				TbdoTextDescriptionsTempBO tbdoTextDescBO = new TbdoTextDescriptionsTempBO();
				tbdoTextDescBO.setChangeNumber(changeNum);
				tbdoTextDescBO.setOpdTagName(opdTag);
				tbdoTextDescBO.setOrganization(orgName);
				tbdoTextDescBO.setLangCd(langCd);
				
				
				session.setAttribute(ApplicationConstants.ATTR_CHANGE_NUM_POLT, changeNum);
				session.setAttribute(ApplicationConstants.ATTR_OPD_TAG_POLT, opdTag);
				session.setAttribute(ApplicationConstants.ATTR_ORG_NAME_POLT, orgName);
				session.setAttribute(ApplicationConstants.ATTR_LANG_CD_POLT, langCd);
				
				/*if (formModeValue == null || formModeValue.trim().equals("") ||  
						ApplicationConstants.ATTR_FORM_MODE_VIEW_VALUE.equals(formModeValue.trim())) {
					formModeValue = ApplicationConstants.ATTR_FORM_MODE_VIEW_VALUE;
				}*/
				formModeValue=CommonUtil.setFormModeIfEmpty(formModeValue);
				
				session.setAttribute(ApplicationConstants.ATTR_FORM_MODE, formModeValue);

				forward = MAINTAIN_OPD_TEXT_VAR_VALUE_PAGE;
			} catch (Exception e) {
				logger.exception(e);				
				request.setAttribute(ApplicationConstants.ERRMSG,
						ApplicationConstants.ERROR_PARAM_INVALID);
				forward = ERROR;
			}
		}

		logger.exitMethod("forwardRequestToMaintainOPDTextVarValue", "Ended");

		return forward;
	}
	// Added by INHYOU1 for Modula CRN-- Payment Condition Screen-  Start
	private String forwardRequestToMaintainPaymentConditions(
			final HttpServletRequest request, final HttpSession session) {
		logger.enterMethod("forwardRequestToMaintainOPDTextVarValue", "Started");
		String forward;
		String pcPer = request.getParameter("PCper");
		String pcPerTyp = request.getParameter("PCperTyp");
		String typ = request.getParameter("Typ");
		String langCode = request.getParameter("LangCode");
		String formModeValue = request.getParameter(ApplicationConstants.ATTR_FORM_MODE);

		int pcPer1=Integer.parseInt(pcPer);
		logger.info("forwardRequestToMaintainPaymentConditions parameters from session: " +
				"PCPer: "+pcPer+" PCPertype "+pcPerTyp+" TYP "+typ+" Language "+langCode+" " +
						" FormMode "+formModeValue);

		if (pcPerTyp == null
				|| pcPerTyp.length() <= 0 || typ == null || typ.length() <= 0
				|| langCode == null || langCode.length() <= 0) {
			request.setAttribute(ApplicationConstants.ERRMSG,
					ApplicationConstants.ERROR_PARAM_MISSING);
			forward = ERROR;
		} else {
			try {
				session.setAttribute("PCper", pcPer1);
				session.setAttribute("PCperTyp", pcPerTyp);
				session.setAttribute("Typ", typ);
				session.setAttribute("LangCode", langCode);

				formModeValue=CommonUtil.setFormModeIfEmpty(formModeValue);

				session.setAttribute(ApplicationConstants.ATTR_FORM_MODE, formModeValue);

				forward = "maintainPaymentConditions";
			} catch (Exception e) {
				logger.exception(e);				
				request.setAttribute(ApplicationConstants.ERRMSG,
						ApplicationConstants.ERROR_PARAM_INVALID);
				forward = ERROR;
			}


			logger.exitMethod("forwardRequestToMaintainPaymentConditions", "Ended");


		}
		return forward;
	}
		

	// Added by INHYOU1 for Modula CRN-- End
}