package com.atradius.dataaccess.hibernate.dao;

import java.util.List;

import com.atradius.dataaccess.hibernate.HibernateUtil;
import com.atradius.dataaccess.hibernate.bo.TbclClaimsCalcLiabilitiesBO;

public interface ClaimsCalcLiabilitiesDAO {
	public List<TbclClaimsCalcLiabilitiesBO> getClaimsCalculationDetails(
			HibernateUtil hibernateUtil, Long paymentId);

	public boolean updateUnicode(HibernateUtil hibernateUtil,
			List<TbclClaimsCalcLiabilitiesBO> beanList);

	public boolean delete(HibernateUtil hibernateUtil, Long paymentId);
}
