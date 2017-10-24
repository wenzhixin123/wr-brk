package com.sinotrans.gd.wlp.common.service;

import java.util.Date;
import java.util.List;

import com.sinotrans.framework.core.service.BaseManager;

public interface SQLQueryManager extends BaseManager {

	String getColumnData(String inSQL, String defaultValue, String dataSource);

	String executeSQL(String inSQL, String dataSource, boolean ignoreUpdRows);

	Date getSysDate(String dataSource);

	List<Object[]> getSqlResultList(String inSQL, String dataSource);

}
