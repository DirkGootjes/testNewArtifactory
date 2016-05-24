package com.atradius.web.dwr;

import javax.servlet.http.HttpSession;

import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;

import com.atradius.beans.FreetextAJAXBean;
import com.atradius.dataaccess.hibernate.HibernateUtil;
import com.atradius.dataaccess.hibernate.bo.TbbuConditionTypesBO;
import com.atradius.dataaccess.hibernate.bo.TbbuConditionVariablesUniTempBO;
import com.atradius.dataaccess.hibernate.dao.TbbuConditionVariablesUniTempDAO;
import com.atradius.dataaccess.hibernate.dao.impl.TbbuConditionVariablesUniTempDAOImpl;
import com.atradius.exception.DataAccessException;
import com.atradius.sessiondata.ApplicationConstants;
import com.atradius.sessiondata.UserDataObject;
import com.atradius.util.logging.ILogger;
import com.atradius.util.logging.LoggerFactory;

public class FreetextDetailsAJAX {
	private static ILogger logger = LoggerFactory
			.getLogger(FreetextDetailsAJAX.class);

	// saveFreetextDetails
	/**
	 * @param translation
	 * @return
	 * @throws Exception
	 */
	public FreetextAJAXBean saveFreetextDetails(String translation)
			throws Exception {
		logger.enterMethod("saveFreetextDetails", translation);
		String errorMsg = null;
		FreetextAJAXBean returnBean = new FreetextAJAXBean();
		WebContext ctx = WebContextFactory.get();
		HttpSession session = ctx.getSession();
		UserDataObject userDataObject = (UserDataObject) session
				.getAttribute(ApplicationConstants.USER_DATA_BEAN);
		if (userDataObject == null) {
			errorMsg = ApplicationConstants.ERROR_INVALID_SESSION;
			returnBean.setErrorMsg(errorMsg);
			logger.error(errorMsg);
			return returnBean;
		}
		HibernateUtil hibernateUtil = (HibernateUtil) session
				.getAttribute(ApplicationConstants.HIBERNATE_UTIL);
		if (hibernateUtil == null) {
			errorMsg = ApplicationConstants.ERROR_INVALID_SESSION;
			returnBean.setErrorMsg(errorMsg);
			return returnBean;
		}

		// save unicode text details
		Integer bucltId = Integer.valueOf((String) session
				.getAttribute(ApplicationConstants.REQUEST_BUCLT_ID));
		String bucdeCd = (String) session
				.getAttribute(ApplicationConstants.REQUEST_BUCDE_CODE);
		String applyAmountType = (String) session
				.getAttribute(ApplicationConstants.REQUEST_APPLY_AMT_TYP);
		Integer bucyeSequence = null;
		Integer bumctSequence = null;
		Integer bumctOrder = null;
		if (session.getAttribute(ApplicationConstants.REQUEST_BUCYE_SEQ) == null
				|| session.getAttribute(ApplicationConstants.REQUEST_BUCYE_SEQ).equals("")) {
			bucyeSequence = null;
		} else {
			bucyeSequence = Integer.valueOf((String) session
					.getAttribute(ApplicationConstants.REQUEST_BUCYE_SEQ));
		}
		if (session.getAttribute(ApplicationConstants.REQUEST_BUMCT_SEQ) == null
				|| session.getAttribute(ApplicationConstants.REQUEST_BUMCT_SEQ).equals("")) {
			bumctSequence = null;
		} else {
			bumctSequence = Integer.valueOf((String) session
					.getAttribute(ApplicationConstants.REQUEST_BUMCT_SEQ));
		}
		if (session.getAttribute(ApplicationConstants.REQUEST_BUMCT_ORDER) == null
				|| session
						.getAttribute(ApplicationConstants.REQUEST_BUMCT_ORDER).equals("")) {
			bumctOrder = null;
		} else {
			bumctOrder = Integer.valueOf((String) session
					.getAttribute(ApplicationConstants.REQUEST_BUMCT_ORDER));
		}

		if (bucltId == null) {
			session.setAttribute(ApplicationConstants.ERRMSG,
					ApplicationConstants.ERROR_NO_BUCLTID);
		} else if (bucdeCd == null) {
			session.setAttribute(ApplicationConstants.ERRMSG,
					ApplicationConstants.ERROR_NO_BUCDECODE);
		} else {
			TbbuConditionVariablesUniTempDAO tbbuConditionVariablesUniTempDAOImpl = new TbbuConditionVariablesUniTempDAOImpl();
			TbbuConditionVariablesUniTempBO existingTranslationUniTemp = new TbbuConditionVariablesUniTempBO();
			
			existingTranslationUniTemp = (TbbuConditionVariablesUniTempBO) session
			.getAttribute(ApplicationConstants.ATTR_FREETEXT_UNI);
			
			TbbuConditionTypesBO conditionTypesBO = new TbbuConditionTypesBO();
			conditionTypesBO.setCode(bucdeCd);
			//if (existingTranslationUni == null) {
				if (bumctSequence != null) {					
					if (existingTranslationUniTemp != null){
						existingTranslationUniTemp = new TbbuConditionVariablesUniTempDAOImpl()
						.getTextTranslationsTemp(hibernateUtil, bucltId,
								applyAmountType, bucyeSequence, bucdeCd,
								bumctSequence, bumctOrder);	
					}
					
				} else {					
					if (existingTranslationUniTemp != null){
						existingTranslationUniTemp = new TbbuConditionVariablesUniTempDAOImpl()
						.getTextTranslationsTemp(hibernateUtil, bucltId,
								applyAmountType, bucyeSequence, bucdeCd);
					}
					
				}
			if (translation.trim().length() > ApplicationConstants.LENGTH_CONSTANT_TEXTAREA) {
				returnBean.setSuccess(false);
				returnBean
						.setErrorMsg(ApplicationConstants.ERROR_TEXT_MAX_LENGTH);
				logger.error("Validation Error :"
						+ ApplicationConstants.ERROR_TEXT_MAX_LENGTH);
				return returnBean;
			} else {
				if (existingTranslationUniTemp == null) {
					// if existingTranslation is null then save
					TbbuConditionVariablesUniTempBO insertTbbuConditionVariablesUniTempBO = new TbbuConditionVariablesUniTempBO();
					try {
						insertTbbuConditionVariablesUniTempBO = tbbuConditionVariablesUniTempDAOImpl
								.insertTextTranslationsTemp(hibernateUtil,
										bucltId, applyAmountType,
										bucyeSequence, translation,
										conditionTypesBO, bumctSequence,
										bumctOrder);
					} catch (DataAccessException e) {
						logger.exception(e);
						returnBean.setSuccess(false);
						returnBean
								.setErrorMsg(ApplicationConstants.ERROR_DATABASE);
						return returnBean;
					}
					returnBean
							.setVariableText(insertTbbuConditionVariablesUniTempBO
									.getVariableText());

					returnBean.setSuccess(true);
					returnBean
							.setSuccessMessage(ApplicationConstants.SUCCESS_FREETEXT_CREATE);

				} else { // if existingTranslation is not null then update
					existingTranslationUniTemp.setVariableText(translation);
					TbbuConditionVariablesUniTempBO updateTbbuConditionVariablesUniTempBO = new TbbuConditionVariablesUniTempBO();
					try {
						updateTbbuConditionVariablesUniTempBO = tbbuConditionVariablesUniTempDAOImpl
								.updateTextTranslationsTemp(hibernateUtil,
										bucltId, applyAmountType,
										bucyeSequence, translation,
										conditionTypesBO, bumctSequence,
										bumctOrder);
					} catch (DataAccessException e) {
						logger.exception(e);
						returnBean.setSuccess(false);
						returnBean
								.setErrorMsg(ApplicationConstants.ERROR_DATABASE);
						return returnBean;
					}
					returnBean
							.setVariableText(updateTbbuConditionVariablesUniTempBO
									.getVariableText());
					// update the text in BO and then update the list in session
					returnBean.setSuccess(true);
					returnBean
							.setSuccessMessage(ApplicationConstants.SUCCESS_FREETEXT_UPDATE);
				}
			}
		}
		logger.exitMethod("saveFreetextDetails");
		return returnBean;
	}

	/**
	 * @return
	 * @throws Exception
	 */
	public FreetextAJAXBean deleteFreetextDetails() throws Exception {
		logger.enterMethod("deleteFreetextDetails");
		String errorMsg = null;
		boolean isDeleted = false;
		FreetextAJAXBean returnBean = new FreetextAJAXBean();
		WebContext ctx = WebContextFactory.get();
		HttpSession session = ctx.getSession();
		UserDataObject userDataObject = (UserDataObject) session
				.getAttribute(ApplicationConstants.USER_DATA_BEAN);
		if (userDataObject == null) {
			errorMsg = ApplicationConstants.ERROR_INVALID_SESSION;
			returnBean.setErrorMsg(errorMsg);
			logger.error(errorMsg);
			return returnBean;
		}
		HibernateUtil hibernateUtil = (HibernateUtil) session
				.getAttribute(ApplicationConstants.HIBERNATE_UTIL);
		if (hibernateUtil == null) {
			errorMsg = ApplicationConstants.ERROR_INVALID_SESSION;
			returnBean.setErrorMsg(errorMsg);
			return returnBean;
		}

		Integer bucltId = Integer.valueOf((String) session
				.getAttribute(ApplicationConstants.REQUEST_BUCLT_ID));
		String bucdeCd = (String) session
				.getAttribute(ApplicationConstants.REQUEST_BUCDE_CODE);
		String applyAmountType = (String) session
				.getAttribute(ApplicationConstants.REQUEST_APPLY_AMT_TYP);
		Integer bucyeSequence = null;
		Integer bumctSequence = null;
		Integer bumctOrder = null;
		
		if (session.getAttribute(ApplicationConstants.REQUEST_BUCYE_SEQ) == null || session.getAttribute(ApplicationConstants.REQUEST_BUCYE_SEQ).equals("")) {
			bucyeSequence = null;
		}
		else
		{
			bucyeSequence = Integer.valueOf((String) session
					.getAttribute(ApplicationConstants.REQUEST_BUCYE_SEQ));
		}
		
		if (session.getAttribute(ApplicationConstants.REQUEST_BUMCT_SEQ) == null || session.getAttribute(ApplicationConstants.REQUEST_BUMCT_SEQ).equals("")) {
			bumctSequence = null;
		}
		else
		{
			bumctSequence = Integer.valueOf((String) session
					.getAttribute(ApplicationConstants.REQUEST_BUMCT_SEQ));
		}
		
		if (session.getAttribute(ApplicationConstants.REQUEST_BUMCT_ORDER) == null || session.getAttribute(ApplicationConstants.REQUEST_BUMCT_ORDER).equals("")) {
			bumctOrder = null;
		}
		else
		{
			bumctOrder = Integer.valueOf((String) session
					.getAttribute(ApplicationConstants.REQUEST_BUMCT_ORDER));
		}
		
		if (bucltId == null) {
			session.setAttribute(ApplicationConstants.ERRMSG,
					ApplicationConstants.ERROR_NO_BUCLTID);
		}
		else if (bucdeCd == null) {
			session.setAttribute(ApplicationConstants.ERRMSG,
					ApplicationConstants.ERROR_NO_BUCDECODE);
		} else {
			TbbuConditionVariablesUniTempDAO tbbuConditionVariablesUniTempDAOImpl = new TbbuConditionVariablesUniTempDAOImpl();
			
			try {
				isDeleted = tbbuConditionVariablesUniTempDAOImpl
					.deleteTextTranslationsTemp(hibernateUtil, bucltId,
							applyAmountType, bucyeSequence, bucdeCd,
							bumctSequence, bumctOrder);
			} catch (DataAccessException e) {
				logger.exception(e);
				returnBean.setSuccess(false);
				returnBean.setErrorMsg(ApplicationConstants.ERROR_DATABASE);
				return returnBean;
			}
			if (isDeleted) {
				returnBean.setSuccess(true);
				returnBean
						.setSuccessMessage(ApplicationConstants.SUCCESS_FREETEXT_DELETE);
				returnBean.setVariableText("");
			} else {
				returnBean.setSuccess(false);
				returnBean.setErrorMsg(" No record deleted.");
			}
		}
		logger.exitMethod("deleteFreetextDetails");
		return returnBean;
	}
	
	public FreetextAJAXBean resetFreetextDetails() throws Exception {
		logger.enterMethod("resetFreetextDetails");
		String errorMsg = null;
		String resetText = null;
		FreetextAJAXBean returnBean = new FreetextAJAXBean();
		WebContext ctx = WebContextFactory.get();
		HttpSession session = ctx.getSession();
		UserDataObject userDataObject = (UserDataObject) session
				.getAttribute(ApplicationConstants.USER_DATA_BEAN);
		if (userDataObject == null) {
			errorMsg = ApplicationConstants.ERROR_INVALID_SESSION;
			returnBean.setErrorMsg(errorMsg);
			logger.error(errorMsg);
			return returnBean;
		}
		HibernateUtil hibernateUtil = (HibernateUtil) session
				.getAttribute(ApplicationConstants.HIBERNATE_UTIL);
		if (hibernateUtil == null) {
			errorMsg = ApplicationConstants.ERROR_INVALID_SESSION;
			returnBean.setErrorMsg(errorMsg);
			return returnBean;
		}

		Integer bucltId = Integer.valueOf((String) session
				.getAttribute(ApplicationConstants.REQUEST_BUCLT_ID));
		String bucdeCd = (String) session
				.getAttribute(ApplicationConstants.REQUEST_BUCDE_CODE);
		String applyAmountType = (String) session
				.getAttribute(ApplicationConstants.REQUEST_APPLY_AMT_TYP);
		Integer bucyeSequence = null;
		Integer bumctSequence = null;
		Integer bumctOrder = null;
		
		if (session.getAttribute(ApplicationConstants.REQUEST_BUCYE_SEQ) == null || session.getAttribute(ApplicationConstants.REQUEST_BUCYE_SEQ).equals("")) {
			bucyeSequence = null;
		}
		else
		{
			bucyeSequence = Integer.valueOf((String) session
					.getAttribute(ApplicationConstants.REQUEST_BUCYE_SEQ));
		}
		
		if (session.getAttribute(ApplicationConstants.REQUEST_BUMCT_SEQ) == null || session.getAttribute(ApplicationConstants.REQUEST_BUMCT_SEQ).equals("")) {
			bumctSequence = null;
		}
		else
		{
			bumctSequence = Integer.valueOf((String) session
					.getAttribute(ApplicationConstants.REQUEST_BUMCT_SEQ));
		}
		
		if (session.getAttribute(ApplicationConstants.REQUEST_BUMCT_ORDER) == null || session.getAttribute(ApplicationConstants.REQUEST_BUMCT_ORDER).equals("")) {
			bumctOrder = null;
		}
		else
		{
			bumctOrder = Integer.valueOf((String) session
					.getAttribute(ApplicationConstants.REQUEST_BUMCT_ORDER));
		}
		
		if (bucltId == null) {
			session.setAttribute(ApplicationConstants.ERRMSG,
					ApplicationConstants.ERROR_NO_BUCLTID);
		}
		else if (bucdeCd == null) {
			session.setAttribute(ApplicationConstants.ERRMSG,
					ApplicationConstants.ERROR_NO_BUCDECODE);
		} else {
			TbbuConditionVariablesUniTempDAO tbbuConditionVariablesUniTempDAOImpl = new TbbuConditionVariablesUniTempDAOImpl();
			
			try {
				resetText = tbbuConditionVariablesUniTempDAOImpl
					.resetTextTranslationsTemp(hibernateUtil, bucltId,
							applyAmountType, bucyeSequence, bucdeCd,
							bumctSequence, bumctOrder);
			} catch (DataAccessException e) {
				logger.exception(e);
				returnBean.setSuccess(false);
				returnBean.setErrorMsg(ApplicationConstants.ERROR_DATABASE);
				return returnBean;
			}
			if (resetText!=null) {
				returnBean.setSuccess(true);
				returnBean
						.setSuccessMessage(ApplicationConstants.SUCCESS_FREETEXT_RESET);
				returnBean.setVariableText(resetText);
			} else {
				returnBean.setSuccess(true);
				returnBean.setSuccessMessage(ApplicationConstants.SUCCESS_FREETEXT_NORECORD);
				returnBean.setVariableText("");
			}
		}
		logger.exitMethod("resetFreetextDetails");
		return returnBean;
	}
}
