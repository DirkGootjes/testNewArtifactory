/*********************************************************************/
/*                           FILE HEADER                             */
/*********************************************************************/
/*                                                                   */
/*  FileName: 		MaintainContactController.java             	  	 */
/*  																 */
/*  $Author: INVSAR1 $											     */
/*																	 */			
/*  $Revision: 1.13 $											     */
/*  																 */		
/*  $Date: 2013/05/31 12:16:58 $                        			 */
/*                                                                   */
/*  Description: 	This action class performs the initial loading of*/
/*				  	organisation contact details					 */			
/*********************************************************************/
/* Date        Name            Version             Comments          */
/*-------------------------------------------------------------------*/
/* 04/04/2013  INVSAR1      	1.0         Initial version created  */
/*********************************************************************/
// package
package com.atradius.action;

// imports
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.atradius.beans.ContactBean;
import com.atradius.beans.OrganisationDetailsBean;
import com.atradius.dataaccess.hibernate.HibernateUtil;
import com.atradius.dataaccess.hibernate.bo.TborContactPointsBO;
import com.atradius.dataaccess.hibernate.bo.TborNonNcmOrganisationsBO;
import com.atradius.dataaccess.hibernate.dao.impl.ContactPointsDAOImpl;
import com.atradius.dataaccess.hibernate.dao.impl.TborNonNcmOrganisationsDAOImpl;
import com.atradius.sessiondata.ApplicationConstants;
import com.atradius.sessiondata.UserDataObject;
import com.atradius.util.config.ApplicationConfig;
import com.atradius.util.logging.ILogger;
import com.atradius.util.logging.LoggerFactory;

public class MaintainContactController extends Action {

	private static ILogger logger = LoggerFactory
			.getLogger(MaintainContactController.class);

	private static String SUCCESS = "success";

	private static String ERROR = "error";
	
	/**
	 * Does the initial processing before going to the MaintainContactDetails.jsp
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

		logger.enterMethod("execute method of MaintainContactController");
		HttpSession session = request.getSession();
		UserDataObject userDataObject = (UserDataObject) session
				.getAttribute(ApplicationConstants.USER_DATA_BEAN);
		
		if (userDataObject == null) {
			request.setAttribute(ApplicationConstants.ERRMSG, ApplicationConstants.ERROR_INVALID_SESSION);
			return mapping.findForward(ERROR);
		}
		
		HibernateUtil hibernateUtil = (HibernateUtil)session.getAttribute(ApplicationConstants.HIBERNATE_UTIL);
		if (hibernateUtil == null) {
			request.setAttribute(ApplicationConstants.ERRMSG, ApplicationConstants.ERROR_INVALID_SESSION);
			return mapping.findForward(ERROR);
		}
		
		ApplicationConfig config = ApplicationConfig.getInstance();
		Properties rtl_languages = config.getProperties("rtl_languages");
		
		String rtl_lang_str =""; 
		if(rtl_languages !=null && !rtl_languages.isEmpty()){
			Iterator keys = rtl_languages.keySet().iterator();
			while(keys.hasNext()){
				String key = (String)keys.next();
				if(((String)rtl_languages.get(key)).equalsIgnoreCase("RTL")){
					rtl_lang_str = rtl_lang_str +key+",";
				}
			}
			if(rtl_lang_str.endsWith(","))
				rtl_lang_str = rtl_lang_str.substring(0,rtl_lang_str.length()-1);
		}
		session.setAttribute(ApplicationConstants.ATTR_RIGHT_TO_LEFT_LANGS,rtl_lang_str);
		
		Integer ornnnId = (Integer)session.getAttribute(ApplicationConstants.ATTR_ORGANISATION_NO);
		if(ornnnId == null ){
			request.setAttribute(ApplicationConstants.ERRMSG, ApplicationConstants.ERROR_PARAM_MISSING);
			return mapping.findForward(ERROR);
		}
		//System.out.println("\nornnnId found as ["+ornnnId+"]");
		try{
			TborNonNcmOrganisationsBO organisation = TborNonNcmOrganisationsDAOImpl.getOrganisation(hibernateUtil,ornnnId.intValue());
			String orgLangCode = organisation.getLangVode();
			
			if(organisation ==null || organisation.getId() == 0){
				session.setAttribute(ApplicationConstants.ERRMSG, ApplicationConstants.ERROR_INVALID_ORG);
			}else{
				List<TborContactPointsBO> contactList = ContactPointsDAOImpl.getContactPoints(hibernateUtil,ornnnId.intValue());//
				if(contactList ==null || contactList.size()<=0){
					session.setAttribute(ApplicationConstants.ERRMSG, ApplicationConstants.ERROR_NO_CONTACT);
				}else{
					session.setAttribute(ApplicationConstants.ATTR_CONTACT_LIST, contactList);
					
					OrganisationDetailsBean orgDetails = new OrganisationDetailsBean();
					List<ContactBean> contactBeanList =  convertToContactBeanList(contactList,orgLangCode);
					orgDetails.setContactBeanList(contactBeanList);
					
					
					orgDetails.setOrgId(organisation.getId());
					orgDetails.setOrgCountry(organisation.getCountryName());
					orgDetails.setOrgName(organisation.getShortName());
					request.setAttribute(ApplicationConstants.ATTR_ORG_DETAILS, orgDetails);
				}
			}
		}catch(Exception e){
			//e.printStackTrace();
			//System.out.println(e.getMessage());
			logger.exception(e) ;
		}
		if (userDataObject == null) {
			return mapping.findForward(ERROR);
		}
		logger.exitMethod("execute method of MaintainContactController");

		return mapping.findForward(SUCCESS);

	}
	private List<ContactBean> convertToContactBeanList(List<TborContactPointsBO> contactList,String orgLangCode){
		logger.enterMethod("convertToContactBeanList method of MaintainContactController");
		List<ContactBean> contactBeanList = new ArrayList<ContactBean>();
		for(int i=0;i<contactList.size();i++){
			ContactBean contactBean = new ContactBean();
			TborContactPointsBO contactPointBO = contactList.get(i);
			contactBean.setIndex(i);
			// check if all three entities are present
			if (contactPointBO.getContactNameAddresse()!=null && contactPointBO.getContactNameAddresse().getOrgAddresses() !=null && contactPointBO.getContactNameAddresse().getOrgNames()!=null){
				if(contactPointBO.getContactIndividual() !=null){
					contactBean.setContactName(contactPointBO.getContactIndividual().getIndivName());
					contactBean.setContactType(contactPointBO.getTyp());
					
					if(contactPointBO.getContactIndividual().getTelNr() !=null && contactPointBO.getContactIndividual().getTelNr().trim().length()>0)
						contactBean.setTelNo(contactPointBO.getContactIndividual().getTelNr());
					else if (contactPointBO.getTelNr() !=null && contactPointBO.getTelNr().trim().length()>0)
						contactBean.setTelNo(contactPointBO.getTelNr());
					else if (contactPointBO.getContactNameAddresse().getOrgAddresses().getTelNr() !=null && contactPointBO.getContactNameAddresse().getOrgAddresses().getTelNr().trim().length()>0)
						contactBean.setTelNo(contactPointBO.getContactNameAddresse().getOrgAddresses().getTelNr());
					
					contactBean.setPosition(contactPointBO.getSalesPosition());
					
					contactBean.setLangCode(contactPointBO.getContactIndividual().getBulaeLangCode());
					
					contactBeanList.add(contactBean);
				}else{
					contactBean.setContactName("");
					contactBean.setContactType(contactPointBO.getTyp());
					
					if (contactPointBO.getTelNr() !=null && contactPointBO.getTelNr().trim().length()>0)
						contactBean.setTelNo(contactPointBO.getTelNr());
					else if (contactPointBO.getContactNameAddresse().getOrgAddresses().getTelNr() !=null && contactPointBO.getContactNameAddresse().getOrgAddresses().getTelNr().trim().length()>0)
						contactBean.setTelNo(contactPointBO.getContactNameAddresse().getOrgAddresses().getTelNr());
					
					contactBean.setPosition(contactPointBO.getSalesPosition());
					
					contactBean.setLangCode(orgLangCode);
					
					contactBeanList.add(contactBean);
				}
			}
		}
		logger.exitMethod("convertToContactBeanList method of MaintainContactController");
		return contactBeanList;
	}

}
