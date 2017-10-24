package com.sinotrans.gd.wlp.common.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sinotrans.framework.core.dao.NativeSqlDao;
import com.sinotrans.framework.core.service.impl.BaseManagerImpl;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import com.sinotrans.framework.core.support.NewTransactionTemplate;
import com.sinotrans.gd.wlp.common.service.SQLQueryManager;

@Service
public class SQLQueryManagerImpl extends BaseManagerImpl implements
		SQLQueryManager {

	@Autowired
	private NativeSqlDao nativeSqlDao;

	private boolean isNull(String inString) {
		return StringUtils.isBlank(inString);
	}

	@SuppressWarnings("unchecked")
	public Date getSysDate(String dataSource) {
		Date sysDate = null;
		List<Object[]> nativesqlresult = null;
		if (isNull(dataSource)) {
			try {
				nativesqlresult = nativeSqlDao.find("select sysdate() as Systemtime");
				if (nativesqlresult != null && nativesqlresult.size() > 0) {
					sysDate = (Date) nativesqlresult.get(0)[0];
				}
			} catch (Exception e) {

			}
		} else {
			// multi datasource
			try {
				nativesqlresult = (List<Object[]>) new NewTransactionTemplate()
						.execute(dataSource, new TransactionCallback() {
							public Object doInTransaction(
									TransactionStatus status) {
								return nativeSqlDao
										.find("select sysdate() as Systemtime");
							}
						});
				if (nativesqlresult != null && nativesqlresult.size() > 0) {
					sysDate = (Date) nativesqlresult.get(0)[0];
				}
			} catch (Exception e) {
				//
			}
		}

		return sysDate;
	}

	public String executeSQL(final String inSQL, String dataSource,
			boolean ignoreUpdRows) {
		String errMsg = null;
		int updRows = 0;
		if (isNull(dataSource)) {
			try {
				updRows = nativeSqlDao.bulkUpdate(inSQL);
			} catch (Exception e) {
				errMsg = "执行出错";
			}
		} else {
			try {
				updRows = (Integer) new NewTransactionTemplate().execute(
						dataSource, new TransactionCallback() {
							public Object doInTransaction(
									TransactionStatus status) {
								return nativeSqlDao.bulkUpdate(inSQL);
							}
						});
			} catch (Exception e) {
				errMsg = "执行出错";
			}

		}
		if (updRows == 0 && (!ignoreUpdRows)) {
			errMsg = "执行操作时无记录更新";
		}

		return errMsg;
	}

	@SuppressWarnings("unchecked")
	public String getColumnData(final String inSQL, String defaultValue,
			String dataSource) {
		String resultReturn = null;
		if (!isNull(inSQL)) {
			List<Object[]> nativesqlresult = null;
			if (isNull(dataSource)) {
				try {
					nativesqlresult = nativeSqlDao.find(inSQL);
					if (nativesqlresult != null && nativesqlresult.size() > 0) {
						resultReturn = (String) nativesqlresult.get(0)[0];
					}
				} catch (Exception e) {
					// e.printStackTrace();
				}
			} else {
				// multi datasource
				try {
					nativesqlresult = (List<Object[]>) new NewTransactionTemplate()
							.execute(dataSource, new TransactionCallback() {
								public Object doInTransaction(
										TransactionStatus status) {
									return nativeSqlDao.find(inSQL);
								}
							});
					if (nativesqlresult != null && nativesqlresult.size() > 0) {
						resultReturn = (String) nativesqlresult.get(0)[0];
					}
				} catch (Exception e) {
					//
				}
			}
		}

		if (isNull(resultReturn))
			resultReturn = defaultValue;
		return resultReturn;
	}

	@SuppressWarnings("unchecked")
	public List<Object[]> getSqlResultList(final String inSQL, String dataSource) {
		List<Object[]> nativesqlresult = null;
		if (!isNull(inSQL)) {
			if (isNull(dataSource)) {
				try {
					nativesqlresult = nativeSqlDao.find(inSQL);
				} catch (Exception e) {
					//
				}
			} else {
				try {
					nativesqlresult = (List<Object[]>) new NewTransactionTemplate()
							.execute(dataSource, new TransactionCallback() {
								public Object doInTransaction(
										TransactionStatus status) {
									return nativeSqlDao.find(inSQL);
								}
							});
				} catch (Exception e) {

				}
			}
		}
		return nativesqlresult;
	}

}
