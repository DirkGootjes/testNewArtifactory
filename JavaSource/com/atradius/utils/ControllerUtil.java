/*********************************************************************/
/*                           FILE HEADER                             */
/*********************************************************************/
/*                                                                   */
/*  FileName: 		ControllerUtil.java               	         	     */
/*  																 */
/*  $Author: INHYOU1 $									             */
/*																	 */			
/*  $Revision: 1.0 $										         */
/*  																 */	
/*  																 */	
/*  $Date: 2016/01/13 05:32:12 $                                     */
/*                                                                   */
/*  Description: 	This Class contains the methods for  		     */
/*				  	common operations.							     */			
/*********************************************************************/
/*  																 */	
/*  																 */	
/* Date        Name            Version             Comments          */
/*-------------------------------------------------------------------*/
/* 13/01/2013  INHYOU1      	1.0         Initial version created  */
/*  																 */	
/*  																 */	
/*********************************************************************/
package com.atradius.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.atradius.dataaccess.hibernate.HibernateUtil;
import com.atradius.sessiondata.ApplicationConstants;
import com.atradius.sessiondata.UserDataObject;

public class ControllerUtil {

	private ControllerUtil(){
		
	}
	public static boolean getSessionDetails(HttpServletRequest request, HibernateUtil hibernateUtil,HttpSession session){
		
		UserDataObject userDataObject = (UserDataObject) session
				.getAttribute(ApplicationConstants.USER_DATA_BEAN);
		
		if (userDataObject == null) {
			request.setAttribute(ApplicationConstants.ERRMSG,
					ApplicationConstants.ERROR_INVALID_SESSION);
			return false;
		}

		
		if (hibernateUtil == null) {
			request.setAttribute(ApplicationConstants.ERRMSG,
					ApplicationConstants.ERROR_INVALID_SESSION);
			return false;
		}
		
		return true;
	}
}
