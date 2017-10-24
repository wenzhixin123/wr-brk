package com.sinotrans.gd.wlp.inbound.service;

import com.sinotrans.framework.core.service.BaseManager;
import com.sinotrans.gd.wlp.common.model.SubmitOrderDetailModel;

public interface SubmitOrderDetailManager  extends BaseManager{
	SubmitOrderDetailModel get(String id);
}
