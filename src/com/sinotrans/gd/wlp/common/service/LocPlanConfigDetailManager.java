package com.sinotrans.gd.wlp.common.service;

import com.sinotrans.framework.core.service.BaseManager;
import com.sinotrans.gd.wlp.common.model.LocPlanConfigDetailModel;

public interface LocPlanConfigDetailManager  extends BaseManager  {
	public LocPlanConfigDetailModel get(String id);
}
