package com.atradius.dataaccess.hibernate.dao.impl;

import com.atradius.util.logging.ILogger;
import com.atradius.util.logging.LoggerFactory;

public class TbclAssessCalcLiabilitiesDAOImpl implements TbclAssessCalcLiabilitiesDAO{
	private static ILogger logger = LoggerFactory
	.getLogger(TbbuTextTranslationsUniDAOImpl.class);

/*public TbclAssessCalcLiabilitiesBO insertFreeText(
	HibernateUtil hibernateUtil, int textId, String languageCode,
	String translation) throws DataAccessException {
logger.enterMethod("insertTextTranslations");
TbclAssessCalcLiabilitiesBO tbclAssessCalcLiabilities= new TbclAssessCalcLiabilitiesBO(
		bucceId,assessType,runDate,clcCode);
// set dates as null as these will be handled by triggers
//tbclAssessCalcLiabilities.set
Transaction tx = null;
Session session = null;
// int textId;
try {
	session = hibernateUtil.getSession();
	tx = session.beginTransaction();
	session.save(tbclAssessCalcLiabilities);
	tx.commit();

} catch (HibernateException e) {
	if (tx != null) {
		tx.rollback();
	}
	// e.printStackTrace();
	logger.exception(e);
	throw new DataAccessException("DATABASE_QUERRY_FAILED", e);
} 
logger.exitMethod("insertTextTranslations");
return tbbuTextTranslationsUniBO;
}*/
}
