package com.atradius.dataaccess.hibernate.dao.impl;

import java.sql.Clob;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.hibernate.Hibernate;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

import com.atradius.dataaccess.hibernate.HibernateUtil;
import com.atradius.dataaccess.hibernate.bo.VwclTransLetterVariables2BO;
import com.atradius.util.logging.ILogger;
import com.atradius.util.logging.LoggerFactory;

public class VwclTransLetterVariables2DAOImpl {
	
	private static ILogger logger = LoggerFactory
	.getLogger(VwclTransLetterVariables2DAOImpl.class);
	
	private static String QUERY = "SELECT ID,BUCCE_ID,CLCLT_LETTER_TYP,PRINT_DAT,CLSTC_SECT_TYP," +
	"DES,MANDATORY_FLAG,CLVTE_SEQ,VAR_TYP," +
	"VAR_DES,VAR_MANDATORY_FLAG,CLSTC_SEQ " +
	"FROM VWCL_TRANS_LETTER_VAR_UNI WHERE BUCCE_ID= :CASEID AND " +
	"CLCLT_LETTER_TYP= :LETTER AND PRINT_DAT = :PRN_DATE ORDER BY CLSTC_SEQ,CLVTE_SEQ";
	

	private static SimpleDateFormat DATE_FORMAT = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss.0", Locale.ENGLISH);
	
	public List<VwclTransLetterVariables2BO> getCreateLetterData(HibernateUtil hibernateUtil, Integer claimId, String letterType, Timestamp printDate) {
		logger.info("Inside getCreateLetterData()!!!");
		List<VwclTransLetterVariables2BO> vwclTransLetterVariables2BO = new ArrayList<VwclTransLetterVariables2BO>();
		
		
		Session session = hibernateUtil.getSession();
		
		
		
		SQLQuery query = null;
		query = session.createSQLQuery(QUERY);
		query.setParameter("CASEID", claimId);
		query.setParameter("LETTER", letterType);
		query.setParameter("PRN_DATE", printDate);
		
		query.addScalar("ID", Hibernate.INTEGER);
		query.addScalar("BUCCE_ID", Hibernate.INTEGER);
		query.addScalar("CLCLT_LETTER_TYP", Hibernate.STRING);
		query.addScalar("PRINT_DAT", Hibernate.TIMESTAMP);
		query.addScalar("CLSTC_SECT_TYP", Hibernate.STRING);
		
		query.addScalar("DES", Hibernate.STRING);
		query.addScalar("MANDATORY_FLAG", Hibernate.STRING);
		query.addScalar("CLVTE_SEQ", Hibernate.INTEGER);
		query.addScalar("VAR_TYP", Hibernate.STRING); 
		
		query.addScalar("VAR_DES", Hibernate.STRING);
		query.addScalar("VAR_MANDATORY_FLAG", Hibernate.STRING);
		query.addScalar("CLSTC_SEQ", Hibernate.INTEGER);
		
		List r = query.list();
		logger.info("No. of rows list contains :["+r.size()+"]");
	for (int i = 0; i < r.size(); i++) {
			Object[] row = (Object[]) r.get(i);
			
			VwclTransLetterVariables2BO vwcl = new VwclTransLetterVariables2BO();
			vwcl.setId(getInteger(row[0]));
			vwcl.setBucceId(getInteger(row[1]));
			vwcl.setClcltLetterTyp(getString(row[2]));
			vwcl.setPrintDate(getDate(row[3]));
			vwcl.setClstcSectType(getString(row[4]));
			vwcl.setDes(getString(row[5]));
			vwcl.setMandatoryFlag(getString(row[6]));
			if (getString(row[2]).equalsIgnoreCase("CLPLC001") && getString(row[4]).equalsIgnoreCase("COS")) {
				vwcl.setClvteSeq(1);
			} else {
				vwcl.setClvteSeq(getInteger(row[7]));
			}
			vwcl.setVarType(getString(row[8]));
			vwcl.setVarDes(getString(row[9]));
			vwcl.setVarMandatoryFlag(getString(row[10]));
			vwcl.setClstcSeq(getInteger(row[11]));
			vwclTransLetterVariables2BO.add(vwcl);
		}
		
	logger.info("Outside getCreateLetterData()!!!");
		return vwclTransLetterVariables2BO;
	}
	
	
	private String getString(Object data){
		String returnStr = null;
		if(data !=null){
			returnStr = String.valueOf(data);
			if(returnStr.equalsIgnoreCase("null"))
				return null;
		}
		return returnStr;
	}
	private Date getDate(Object data) {
		Date returnDate = null;
		if (data != null) {
			String DateStr = String.valueOf(data);
			try {
				returnDate = DATE_FORMAT.parse(DateStr);
			} catch (ParseException e) {
			}
		}
		return returnDate;
	}

	private Integer getInteger(Object data) {
		Integer returnInt = null;
		if (data != null) {
			try {
				returnInt = Integer.valueOf(String.valueOf(data));
			} catch (NumberFormatException e) {
			}
		}
		return returnInt;
	}
	private Clob getClob(Object data) {
		Clob returnCLOB = null;
		if (data != null) {
			try {
				returnCLOB = (Clob)data;
			} catch (Exception e) {
				logger.error(e);
			}
		}
		return returnCLOB;
	}
}
