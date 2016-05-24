package com.atradius.dataaccess.hibernate.dao;

import java.util.Date;
import java.util.List;

import com.atradius.dataaccess.hibernate.HibernateUtil;
import com.atradius.dataaccess.hibernate.bo.TbclAssessCalcLiabilitiesBO;

public interface AssessCalcLiabilitiesDAO {
	public List<TbclAssessCalcLiabilitiesBO> getDebtAssessmentTableDetails(
			HibernateUtil hibernateUtil, Integer bucceId, String assessType,
			Date runDate);

	public boolean updateUnicode(HibernateUtil hibernateUtil,
			List<TbclAssessCalcLiabilitiesBO> beanList);
	
	public boolean delete(
			HibernateUtil hibernateUtil, Integer bucceId, String assessType,
			Date runDate);
}
