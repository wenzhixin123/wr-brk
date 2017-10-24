package com.sinotrans.gd.wlp.common.service;

import com.sinotrans.framework.core.service.BaseManager;
import com.sinotrans.gd.wlp.common.model.LocPlanConfigModel;

public interface LocPlanConfigManager extends BaseManager {
	 LocPlanConfigModel get(String id);

	String getStrategyConditionSqlByConfigCode(String configCode,String officeCode);

	/**
	 * 获取关联策略
	 * @param thisConfigCode
	 * @param officeCode
	 * @return
	 */
	String getNextConfigCode(String thisConfigCode, String officeCode);

	String getStrategyOrderBySqlByConfigCode(String configCode,String officeCode);
}
