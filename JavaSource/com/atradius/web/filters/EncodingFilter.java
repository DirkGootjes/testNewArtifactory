/*********************************************************************/
/*                           FILE HEADER                             */
/*********************************************************************/
/*                                                                   */
/*  FileName: 		EncodingFilter.java               	         	 */
/*  																 */
/*  $Author: INVSAR1 $									     		 */
/*																	 */			
/*  $Revision: 1.5 $										     	 */
/*  																 */		
/*  $Date: 2013/05/31 12:16:58 $                            		 */
/*                                                                   */
/*  Description: 	This filter sets the UTF-8 encoding 			 */
/*																	 */			
/*********************************************************************/
/* Date        Name            Version             Comments          */
/*-------------------------------------------------------------------*/
/* 04/04/2013  INVSAR1      	1.0         Initial version created  */
/*********************************************************************/
package com.atradius.web.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import com.atradius.util.logging.ILogger;
import com.atradius.util.logging.LoggerFactory;

public class EncodingFilter implements Filter {
	private static ILogger logger = LoggerFactory
			.getLogger(EncodingFilter.class);

	private String encoding = "UTF-8";

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain filterChain) throws IOException, ServletException {
		logger.enterMethod("doFilter");
		request.setCharacterEncoding(encoding);
		filterChain.doFilter(request, response);
		logger.exitMethod("doFilter");
	}

	public void init(FilterConfig filterConfig) throws ServletException {
		logger.enterMethod("init");
		String encodingParam = filterConfig.getInitParameter("encoding");
		if (encodingParam != null) {
			encoding = encodingParam;
		}
		logger.exitMethod("init");
	}

	public void destroy() {
		// nothing todo
	}

}
