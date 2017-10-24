package com.sinotrans.gd.wlp.basicdata.service;

import java.util.Map;

import com.sinotrans.framework.core.service.BaseManager;

public interface BasQueryUtilManager extends BaseManager {

	String getNameByCode(String tableName, String codeValue, String language);

	String getPropertyByCode(Map<String, String> params);
}
