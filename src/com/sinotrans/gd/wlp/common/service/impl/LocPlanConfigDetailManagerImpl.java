package com.sinotrans.gd.wlp.common.service.impl;

import org.springframework.stereotype.Service;

import com.sinotrans.framework.core.service.impl.BaseManagerImpl;
import com.sinotrans.gd.wlp.common.model.LocPlanConfigDetailModel;
import com.sinotrans.gd.wlp.common.service.LocPlanConfigDetailManager;


@Service
public class LocPlanConfigDetailManagerImpl extends BaseManagerImpl implements LocPlanConfigDetailManager {
	
	public LocPlanConfigDetailModel get(String id) {
		return this.dao.get(LocPlanConfigDetailModel.class, id);
	}
}
