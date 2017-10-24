package com.sinotrans.gd.wlp.common.service;

import com.sinotrans.framework.core.service.BaseManager;
import com.sinotrans.gd.wlp.common.model.SubmitOrderLogModel;

public interface SubmitOrderLogManager extends BaseManager{
	SubmitOrderLogModel get(String uuid);
}
