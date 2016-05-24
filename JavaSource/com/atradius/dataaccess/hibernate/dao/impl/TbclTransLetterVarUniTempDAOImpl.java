package com.atradius.dataaccess.hibernate.dao.impl;

import java.sql.Clob;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.atradius.beans.SectionVariableAjaxBean;
import com.atradius.dataaccess.hibernate.HibernateUtil;
import com.atradius.dataaccess.hibernate.bo.TbclTransLetterVarUniTempBO;
import com.atradius.sessiondata.ApplicationConstants;
import com.atradius.util.logging.ILogger;
import com.atradius.util.logging.LoggerFactory;

public class TbclTransLetterVarUniTempDAOImpl {

	private static ILogger logger = LoggerFactory
			.getLogger(TbclTransLetterVarUniTempDAOImpl.class);

	private static String query2 = "delete from TbclTransLetterVarUniTempBO where bucceId = :caseId "
			+ "and clcltLetterTyp = :letterName and printDate =:printdate";

	public List<TbclTransLetterVarUniTempBO> getSelectedClaimSection(
			HibernateUtil hibernateUtil, Integer claimId, String letter,
			Timestamp prnDate) {
		logger.info("Inside getSelectedClaimSection()!!!");
		List<TbclTransLetterVarUniTempBO> tbclTransLetterVarUniTempBO = new ArrayList<TbclTransLetterVarUniTempBO>();
		try {
			Session session = hibernateUtil.getSession();
			Criteria crit = session
					.createCriteria(TbclTransLetterVarUniTempBO.class);
			crit.add(Restrictions.eq("bucceId", claimId));
			crit.add(Restrictions.eq("clcltLetterTyp", letter));
			crit.add(Restrictions.eq("printDate", prnDate));

			tbclTransLetterVarUniTempBO = (List<TbclTransLetterVarUniTempBO>) crit
					.list();
			for (TbclTransLetterVarUniTempBO a : tbclTransLetterVarUniTempBO) {
				if(a.getClcltLetterTyp().equalsIgnoreCase("CLPLC001") && a.getClstcSectType().equalsIgnoreCase("COS")) {
					a.setClvteSeq(1);
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		logger.info("Outside getSelectedClaimSection()!!!");
		return tbclTransLetterVarUniTempBO;
	}

	public String[] saveSelectedClaimSection(HttpSession session,
			SectionVariableAjaxBean[] secVariable) {
		logger.info("Inside saveSelectedClaimSection()!!!");
		String claimId = session.getAttribute("claimCaseId").toString();
		String letterType = session.getAttribute("letterType").toString();
		String printDate = session.getAttribute("printDate").toString();
		Integer orsusId = Integer.valueOf(session.getAttribute("orsusId")
				.toString());

		DateFormat formt = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SSS");
		String[] secVariableValues=new String[secVariable.length];//Added by for Defect 8006
		Date pDate = null;
		Timestamp timestamp = null;
		try {
			pDate = formt.parse(printDate);
			timestamp = new Timestamp(pDate.getTime());

		} catch (ParseException e) {
			e.printStackTrace();
		}
		HibernateUtil hibernateUtil = (HibernateUtil) session
				.getAttribute(ApplicationConstants.HIBERNATE_UTIL);
		Session session1 = hibernateUtil.getSession();
		
		int i=0;
		for (SectionVariableAjaxBean var : secVariable) {
			session1.beginTransaction();
			TbclTransLetterVarUniTempBO tbclTransLetterVarUniTempBO = new TbclTransLetterVarUniTempBO();

			tbclTransLetterVarUniTempBO.setBucceId(Integer.valueOf(claimId));
			tbclTransLetterVarUniTempBO.setClcltLetterTyp(letterType);
			tbclTransLetterVarUniTempBO.setPrintDate(timestamp);
			tbclTransLetterVarUniTempBO.setClstcSectType(var.getSecType());
			tbclTransLetterVarUniTempBO.setOrsusId(orsusId);
			tbclTransLetterVarUniTempBO.setLastUpdateDate(new Date());
			if (var.getClvteSeq() != null) {
				tbclTransLetterVarUniTempBO.setClvteSeq(var.getClvteSeq());
			}
			//Added by INGKHA1--start for Defect 8006
			String variableDatValue=null;
			if (var.getValue() != null && var.getValue().trim().length() > 0) {
				variableDatValue=var.getValue();
				if((var.getSecType().equalsIgnoreCase("DAL") && var.getClvteSeq()==1) 
				|| (var.getSecType().equalsIgnoreCase("RQF") && var.getClvteSeq()==1)
				|| (var.getSecType().equalsIgnoreCase("RQM") && var.getClvteSeq()==1) 
				|| (var.getSecType().equalsIgnoreCase("T01") && var.getClvteSeq()==1) 
				|| (var.getSecType().equalsIgnoreCase("T05") && var.getClvteSeq()==1)
				|| (var.getSecType().equalsIgnoreCase("T08") && var.getClvteSeq()==1)
				|| (var.getSecType().equalsIgnoreCase("T10") && var.getClvteSeq()==1)
				|| (var.getSecType().equalsIgnoreCase("T13") && var.getClvteSeq()==1)
				|| (var.getSecType().equalsIgnoreCase("T16") && var.getClvteSeq()==1)
				|| (var.getSecType().equalsIgnoreCase("T17") && var.getClvteSeq()==1)
				|| (var.getSecType().equalsIgnoreCase("T18") && var.getClvteSeq()==1)
				|| (var.getSecType().equalsIgnoreCase("T18") && var.getClvteSeq()==2)
				|| (var.getSecType().equalsIgnoreCase("S01") && var.getClvteSeq()==1)
				|| (var.getSecType().equalsIgnoreCase("S02") && var.getClvteSeq()==1)
				|| (var.getSecType().equalsIgnoreCase("S03") && var.getClvteSeq()==1)
				|| (var.getSecType().equalsIgnoreCase("S04") && var.getClvteSeq()==1)
				|| (var.getSecType().equalsIgnoreCase("S05") && var.getClvteSeq()==1)
				|| (var.getSecType().equalsIgnoreCase("S06") && var.getClvteSeq()==1)
				|| (var.getSecType().equalsIgnoreCase("S07") && var.getClvteSeq()==1)
				|| (var.getSecType().equalsIgnoreCase("04B") && var.getClvteSeq()==1)
				|| (var.getSecType().equalsIgnoreCase("07B") && var.getClvteSeq()==1)
				|| (var.getSecType().equalsIgnoreCase("09B") && var.getClvteSeq()==3)
				|| (var.getSecType().equalsIgnoreCase("09I") && var.getClvteSeq()==1)
				|| (var.getSecType().equalsIgnoreCase("11N") && var.getClvteSeq()==1)
				){
			if(!variableDatValue.contains("-")){
				variableDatValue=variableDatValue.replaceAll("/", "-");
				variableDatValue=variableDatValue.replaceAll(":", "-");
				variableDatValue=variableDatValue.replaceAll("\\.", "-");
			}
		}
				//Added by INGKHA1--END for Defect 8006
				tbclTransLetterVarUniTempBO.setTextUNI(getClob(variableDatValue));
			}
			secVariableValues[i++]=variableDatValue;//Added for Defect 8006
			session1.save(tbclTransLetterVarUniTempBO);
			session1.getTransaction().commit();
		}
		logger.info("Outside saveSelectedClaimSection()!!!");
		return secVariableValues;
	}

	public Clob getClob(String str) {

		Clob clobData = Hibernate.createClob(str);

		return clobData;
	}

	public void deleteSelectedClaimSection(HttpSession session) {
		logger.info("Inside deleteSelectedClaimSection()!!!");
		HibernateUtil hibernateUtil = (HibernateUtil) session
				.getAttribute(ApplicationConstants.HIBERNATE_UTIL);
		String claimId = session.getAttribute("claimCaseId").toString();
		String letterType = session.getAttribute("letterType").toString();
		String printDate = session.getAttribute("printDate").toString();

		DateFormat formt = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SSS");
		Date pDate = null;
		Timestamp timestamp = null;
		try {
			pDate = formt.parse(printDate);
			timestamp = new Timestamp(pDate.getTime());

		} catch (ParseException e) {
			e.printStackTrace();
		}
		try {
			Session session1 = hibernateUtil.getSession();
			session1.beginTransaction();

			Query query = session1.createQuery(query2);
			query.setInteger("caseId", Integer.parseInt(claimId.trim()));
			query.setString("letterName", letterType);
			query.setTimestamp("printdate", timestamp);
			int result = query.executeUpdate();
			session1.getTransaction().commit();

		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		logger.info("Outside deleteSelectedClaimSection()!!!");
	}

	public void saveSelectedClaimSection(HttpSession session) {
		logger.info("Inside saveSelectedClaimSection()!!!");
		List<TbclTransLetterVarUniTempBO> selectedSec = (List<TbclTransLetterVarUniTempBO>) session
				.getAttribute("selectedSec");
		HibernateUtil hibernateUtil = (HibernateUtil) session
				.getAttribute(ApplicationConstants.HIBERNATE_UTIL);

		// deleteSelectedClaimSection(hibernateUtil,Integer.valueOf(claimId),letterType,timestamp);

		Session session1 = hibernateUtil.getSession();

		for (TbclTransLetterVarUniTempBO var : selectedSec) {
			session1.beginTransaction();
			TbclTransLetterVarUniTempBO tbclTransLetterVarUniTempBO = new TbclTransLetterVarUniTempBO();

			tbclTransLetterVarUniTempBO.setBucceId(var.getBucceId());
			tbclTransLetterVarUniTempBO.setClcltLetterTyp(var
					.getClcltLetterTyp());
			tbclTransLetterVarUniTempBO.setPrintDate(var.getPrintDate());
			tbclTransLetterVarUniTempBO
					.setClstcSectType(var.getClstcSectType());
			tbclTransLetterVarUniTempBO.setOrsusId(var.getOrsusId());
			tbclTransLetterVarUniTempBO.setLastUpdateDate(var
					.getLastUpdateDate());
			tbclTransLetterVarUniTempBO.setClvteSeq(var.getClvteSeq());
			if (var.getTextUNI() != null) {
				tbclTransLetterVarUniTempBO
						.setTextUNI(getClob(var.getTextUNI()));
			}

			session1.save(tbclTransLetterVarUniTempBO);
			session1.getTransaction().commit();
		}
		logger.info("Outside saveSelectedClaimSection()!!!");
	}

}
