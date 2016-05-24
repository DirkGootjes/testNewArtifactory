/*********************************************************************/
/*                           FILE HEADER                             */
/*********************************************************************/
/*                                                                   */
/*  FileName: 		ApplicationConstants.java               	     */
/*  																 */
/*  $Author: INKPAG1 $									             */
/*																	 */
/*  $Revision: 1.28 $										         */
/*  																 */
/*  $Date: 2014/10/13 05:32:12 $                            		 */
/*                                                                   */
/*  Description: 	This class contains constant names/value		 */
/*																	 */
/*********************************************************************/
/* Date        Name            Version             Comments          */
/*-------------------------------------------------------------------*/
/* 04/04/2013  INVSAR1      	1.0         Initial version created  */
/*********************************************************************/
package com.atradius.sessiondata;

public class ApplicationConstants {

	public static final String ERRMSG = "error_message";

	public static final String ERROR_MSG_TYPE = "error_message_type";

	public static final String USER_DATA_BEAN = "user_details";

	public static final String HIBERNATE_UTIL = "hibernate_util";

	public static final String HIBERNATE_COMMON_UTIL = "hibernate_common_util";

	public static final String CONFIG_FILE_LOCATION = "/WEB-INF/config/framework/hibernate.cfg.xml";

	public static final String PARAM_SOURCE = "identifier";

	public static final String PARAM_VALUE_CONTACT = "C";

	public static final String PARAM_VALUE_INDIVIDUAL = "I";

	public static final String ERROR_INVALID_SOURCE = "Invalid source name.";

	public static final String ERROR_BLANK_SOURCE = "Source not found.";

	public static final String ERROR_INVALID_SESSION = "Invalid session. Please close the window and try again.";

	public static final String ERROR_NO_CONTACT = "No contact is available.";

	public static final String ERROR_INVALID_ORG = "Invalid Organisation.";

	public static final String ATTR_CONTACT_LIST = "contact_list";

	public static final String ATTR_ORGANISATION_NO = "org_number";

	public static final String ATTR_ORG_DETAILS = "org_details";

	public static final String ATTR_UNICODE_CONTACT_DETAILS = "unicode_contact_details";

	public static final String ATTR_LATIN_CONTACT_DETAILS = "latin_contact_details";

	public static final String ERROR_DATABASE = "Unable to process request. Please contact administrator";

	public static final String INFO_SUCCESS_SAVE = "Translation saved successfully";

	public static final String INFO_SUCCESS_UPDATE = "Translation updated successfully";

	public static final String INFO_SUCCESS_DEL = "Translation deleted successfully";

	public static final String ERROR_PARAM_MISSING = "Mandatory parameters are missing";

	public static final String ERROR_PARAM_INVALID = "Invalid parameters";

	public static final String ERROR_BLANK_INDIV_NAME = "Please enter individual name";

	public static final String ERROR_BLANK_ORG_NAME = "Please enter organisation name";

	public static final String ERROR_BLANK_ORG_ADDRESS = "Please enter organisation address";

	public static final String ERROR_BLANK_ORG_CITY = "Please enter organisation city";

	public static final String PARAM_VALUE_TEXT = "T";

	public static final String REQUEST_TEXT_ID = "textId";

	public static final String ATTR_TEXT_DETAIL = "text_details";
	
	public static final String ATTR_TEXT_SEQ = "text_seq";

	public static final String ATTR_UNI_LANG_DETAIL = "unicode_lang_details";

	public static final String ATTR_TEXT_LIST = "text_list";

	public static final String ERROR_NO_TEXTID = "text id is not available";

	public static final String ATTR_TEXT_TYPE_2 = "2";

	public static final String ERROR_TEXT_DETAILS = "no text details available"; // transaltions

	public static final String SELECTED_TEXT_TRANSLATION = "selected_translation";

	public static final String TEXT_TRANSLATION_LIST = "transaltions";

	public static final String ERROR_NO_UNICODE_LANG = "no unicode language present";

	public static final String ERROR_NON_STD_INVOICE_TEXT = "Only non-standard Invoice Text Type is allowed for Unicode translation";

	public static final String INFOMSG = "info_message";

	public static final String INFO_SESSION_CLOSED = "You have been logged out successfully. Please close the window";

	public static final String ATTR_AUTHENTICATION_CODE = "authentication_code_bean";

	public static final String ATTR_SYSTEM_USER = "system_user_bean";

	public static final String ERROR_LOGIN_DUPLICATE_SESSION = "Your session is already running. Please close the running session and try again";

	public static final String ERROR_LOGIN_INVALID_SESSION = "Your session is not valid. Please close the browser and try again";

	public static final String ERROR_LOGIN_UNAUTHORISED_ACCESS = "Your are not authorised to maintain the unicodes";

	public static final String INFO_SESSIONS_KILLED = "All the sessions have been killed. Please close the window and try again";

	public static final String ATTR_INDV_DETAIL = "individual_details";

	public static final String REQUEST_INDIVIDUAL_ID = "indvId";

	public static final String ERROR_NO_INDVID = "individual id is not available";

	public static final String ERROR_INDV_DETAILS = "no individual details available";

	public static final String INDV_TRANSLATION_LIST = "indv_transaltions";

	public static final String SELECTED_INDV_TRANSLATION = "selected_indv_translation";

	public static final String ATTR_INDV_SYS_USER = "individual_sys_user";

	public static final String ATTR_TEXT_TYPE_2_DES = "Policy Specific";

	public static final String INFO_NO_DELETE = "No record deleted";
	
	public static final String ATTR_RIGHT_TO_LEFT_LANGS = "rtl_langs";
	
	public static final String ATTR_RIGHT_TO_LEFT_FLAG = "is_rtl";
	
	public static final String INFO_SUCCESS_SAVE_C = "Local version updated successfully";

	public static final String INFO_SUCCESS_UPDATE_C = "Local version updated successfully";

	public static final String INFO_SUCCESS_DEL_C = "Local version deleted successfully";
	
	public static final String ERROR_BLANK_INDV_NAME = "Please enter individual name";
	
	public static final String ERROR_BLANK_INVOICE_TEXT = "Please enter text";
	
	public static final String ERROR_2ND_LINE_NAME_BLANK = "Please enter second line name or remove the third line name";
	
	public static final String ERROR_2ND_LINE_ADDRESS_BLANK = "Please enter second line address or remove the third line address";
	
	public static final String ERROR_CITY_NAME_BLANK = "Please enter city name";
	
	public static final String ERROR_REGION_NAME_BLANK = "Please enter region";
	
	public static final String ERROR_POST_CODE_BLANK = "Please enter post code";
	
	public static final String ERROR_COUNTRY_NAME_BLANK = "Please enter country name";
	
	public static final int LENGTH_CONSTANT_COMMON = 35;
	
	public static final int LENGTH_CONSTANT_REGION = 20;
	
	public static final int LENGTH_CONSTANT_POSTCODE = 10;
	
	public static final int LENGTH_CONSTANT_TEXTAREA = 2000;
	
	public static final String ERROR_INDIVNAME_MAX_LENGTH = "Individual name size is greater than "+LENGTH_CONSTANT_COMMON+" characters";
	
	public static final String ERROR_SALUTATION_MAX_LENGTH = "Salutation size is greater than "+LENGTH_CONSTANT_COMMON+" characters";
	
	public static final String ERROR_ORGNAME1_MAX_LENGTH = "Organisation first line name size is greater than "+LENGTH_CONSTANT_COMMON+" characters";
	
	public static final String ERROR_ORGNAME2_MAX_LENGTH = "Organisation second line name size is greater than "+LENGTH_CONSTANT_COMMON+" characters";

	public static final String ERROR_ORGNAME3_MAX_LENGTH = "Organisation third line name size is greater than "+LENGTH_CONSTANT_COMMON+" characters";
	
	public static final String ERROR_ORGADD1_MAX_LENGTH = "Organisation first line address size is greater than "+LENGTH_CONSTANT_COMMON+" characters";
	
	public static final String ERROR_ORGADD2_MAX_LENGTH = "Organisation second line address size is greater than "+LENGTH_CONSTANT_COMMON+" characters";

	public static final String ERROR_ORGADD3_MAX_LENGTH = "Organisation third line address size is greater than "+LENGTH_CONSTANT_COMMON+" characters";
	
	public static final String ERROR_ORGCITY_MAX_LENGTH = "Organisation city size is greater than "+LENGTH_CONSTANT_COMMON+" characters";
	
	public static final String ERROR_ORGPOST_MAX_LENGTH = "Organisation post code size is greater than "+LENGTH_CONSTANT_POSTCODE+" characters";
	
	public static final String ERROR_ORGREGION_MAX_LENGTH = "Organisation region size is greater than "+LENGTH_CONSTANT_REGION+" characters";
	
	public static final String ERROR_ORGCOUNTRY_MAX_LENGTH = "Organisation country size is greater than "+LENGTH_CONSTANT_COMMON+" characters";
	
	public static final String ERROR_TEXT_MAX_LENGTH = "Text size is greater than "+LENGTH_CONSTANT_TEXTAREA+" characters";
	
	/*** Added for FreeText Editor CRN [START] ***/
	public static final String ERROR_BLANK_FREETEXT = "Please enter freetext value";
	
	public static final String SUCCESS_FREETEXT_CREATE = "Unicode Free text created successfully";
	
	public static final String SUCCESS_FREETEXT_UPDATE = "Free text Details updated successfully";
	
	public static final String SUCCESS_FREETEXT_DELETE = "Free text Details deleted successfully";
	
	public static final String SUCCESS_FREETEXT_RESET = "Free text Details last updated successfully";
	
	public static final String SUCCESS_FREETEXT_NORECORD = "No Free text Details last updated";
	
	public static final String PARAM_VALUE_FREETEXT = "F";

	public static final String REQUEST_BUCLT_ID = "bucltId";
	
	public static final String REQUEST_BUCDE_CODE = "bucdeCd";
	
	public static final String REQUEST_BUCYE_SEQ = "bucyeSequence";
	
	public static final String REQUEST_APPLY_AMT_TYP = "applyAmountType";
	
	public static final String REQUEST_BUMCT_SEQ =  "bumctSequence";
	
	public static final String REQUEST_BUMCT_ORDER =  "bumctOrder";
	
	public static final String ATTR_BUCLT_ID = "BUCLT_ID";
	
	public static final String ATTR_BUCDE_CODE = "BUCDE_CODE";
	
	public static final String ATTR_FREETEXT_UNI = "Unicode_Freetext";
	
	public static final String ATTR_BUCYE_SEQ = "BUCYE_SEQ";
	
	public static final String ATTR_APPLY_AMT_TYP = "APPLY_AMT_TYP";
	
	public static final String ATTR_BUMCT_SEQ =  "bumct_Seq";
	
	public static final String ATTR_BUMCT_ORDER =  "bumct_Order";
	
	public static final String ERROR_NO_BUCLTID = "Credit Limit id is not available";
	
	public static final String ERROR_NO_BUCDECODE = "Condition Code is not available";
	
	public static final String ERROR_INVALID_CONDITION_CODE = "Invalid Condition Code type";
	
	/*** Added for FreeText Editor CRN [END] ***/
	
	/*** Added for T code Editor CRN [START] ***/
    
   public static final String SUCCESS_TCODETEXT_CREATE = "Unicode translation created successfully";
	
	public static final String SUCCESS_TCODETEXT_UPDATE = "Unicode translation saved successfully";
	
	public static final String SUCCESS_TCODETEXT_DELETE = "Unicode translation deleted successfully";
	
	public static final String PARAM_VALUE_TCODE = "TC";
	
	public static final String PARAM_VALUE_MULTICHOICE = "MC";
	
	public static final String ATTR_CONDITIONCODE_DETAIL = "conditioncode_details";
	
	public static final String REQUEST_CONDITIONCODE_ID = "conditioncodeId";
	
	public static final String REQUEST_LANG_CODE = "langCode";
	
	public static final String ATTR_CHOICE_DETAIL = "choice_details";
	
	public static final String ATTR_NONLATIN_TEXT_DETAIL = "nonlatin_text_details";
	
	public static final String AJAX_NONLATIN_TEXT_DETAIL = "ajax_nonlatin_text_details";
	
	public static final String ATTR_CONDITION_VAR_TYPES = "condition_var_types";

	public static final String ERROR_NO_BUCDECD = "T-Code is not available";
	
	public static final String ERROR_NONLATIN_TRANS_NOT_PRESENT = "The non-Latin language doesn?t have a Unicode Translation to be deleted";
	
	/*** Added for T code Editor CRN [END] ***/
	
	// Added by INVSAR1 for Claims on 26.08.2014 
	public static final String PARAM_VALUE_CREATE_LETTER = "createletter";
	
	public static final String PARAM_VALUE_DEBT_RLC = "debtrlc";
	
	public static final String PARAM_VALUE_PLC_ASSESSMENT  = "plcassessment";
	
	public static final String PARAM_VALUE_COST_CALCULATION = "costcalculation";
	
	public static final String PARAM_VALUE_CLAIMS_PAYMENT = "claimspayment";
	
	// end of Addition by INVSAR1 for Claims on 26.08.2014 
	
	//Start : Added by INSDHA1  for claims
	public static final String REQUEST_BUCCE_ID = "bucceId";
	public static final String ASSESS_TYPE = "assessType";
	public static final String RUN_DATE = "runDate";
	
	public static final String PAYMENT_ID = "clcptId";
	public static final String CLCLC_CODE = "clclcCode";
	public static final String READ_ONLY = "readOnly";
	public static final String ERROR_NO_BUCCECD = "Case Id not available";
	
	public static final String ATTR_CASE_ASSESSMENT_DETAIL_TABLE = "caseassessment_details_table";
	public static final String ATTR_CLAIM_PAYMENT_TABLE = "claimspayment_table";
	public static final String ATTR_CLM_PAYMENTS = "clm_payments";
	public static final String FREE_TEXT_LIABILITIES = "free_text";
	public static final String ERROR_NO_DETAILS = "No details found for given parameters.";
	//End : Added by INSDHA1  for claims

/** Added for modula policy changes CRN [START]**/
	public static final String ATTR_POLICY_DETAILS="policy_details";
	
	public static final String PARAM_VALUE_POLICY_FREE_TEXT="PFT";

	public static final String POLICY_FREE_TEXT_PAGE = "policyFreeText";
	
	public static final String POLICY_FREE_TEXT_SUCCESS_MSG="Updated successfully";
	
	public static final String ERROR_RECORD_NOT_EXIST_MSG="Session has expired. Please close application and request a new session from Symphony";

	public static final String PARAM_VALUE_POLICY_COVER_TRADE_DESC = "PTD";
	
	public static final String PARAM_VALUE_POLICY_MODULE_VAR_VALUE = "PMV";
	
	public static final String PARAM_VALUE_PAYMENT_CONDITION = "PYM";  // Added by INHYOU1 for Modula CRN
	
	public static final String PARAM_VALUE_PREMIUM_DETAILS = "PRM";  //// Added by INHYOU1 for Modula CRN

	public static final String PARAM_VALUE_MAINTAIN_OPD_TEXT_VAR_VALUE = "POLT";
	
	public static final String DATE_FORMAT_1="dd-MMM-yyyy";

	public static final String ATTR_CHANGE_NUM_POLT = "changeNum";

	public static final String ATTR_OPD_TAG_POLT = "opdTag";

	public static final String ATTR_ORG_NAME_POLT = "orgName";

	public static final String ATTR_LANG_CD_POLT = "langCd";

	public static final String ATTR_FORM_MODE = "formMode";
	
	public static final String ATTR_FORM_MODE_VIEW_VALUE = "VIEW";

	public static final String ATTR_POLICY_ID_PMV = "policyId";

	public static final String ATR_EFFECT_DT_PMV = "effectFromDate";

	public static final String ATTR_VAR_CD_PMV = "variableCode";

	public static final String ATTR_POLICYID_PFT = "policyId";

	public static final String ATTR_PDATE_PFT = "print_date";

	public static final String ATTR_GCD_PFT = "grp_cd";

	public static final String ATTR_GFD_PFT = "grp_func_desc";

	public static final String FREE_TXT_PFT = "descFreeText";

	public static final String TBPOPRINTEDBO_PFT = "tbpoPrintedDocBO";

	public static final String ATTR_POLICYID_PTD = "policyId";

	public static final String ATTR_EFDATE_PTD = "effectFromDate";

	public static final String TRADEDESC_PTD = "tradeDesc";

	public static final String TBPOPOLTRADEBO_PTD = "tbpoPolTradeDescsBO";

	public static final String VAR_VALUE_PMV = "variableValue";

	public static final String TBPOPOMODVARBO_PMV = "tbpoPolModVariablesBO";

	public static final String OPD_TAG_DESC_POLT = "currOpdTagDesc";

	public static final String ORGNIZATION_POLT = "organization";

	public static final String LANG_CD_POLT = "langCd";

	public static final String STATUS_POLT = "status";

	public static final String OPD_TEXT_POLT = "opdText";

	public static final String TBDOTEXTDESCBO_POLT = "tbdoTextDescBO";
	
	/** Added for modula policy changes CRN [END]**/
}
