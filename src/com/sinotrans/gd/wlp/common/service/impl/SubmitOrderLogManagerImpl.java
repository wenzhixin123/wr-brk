package com.sinotrans.gd.wlp.common.service.impl;

import org.springframework.stereotype.Service;

import com.sinotrans.framework.core.service.impl.BaseManagerImpl;
import com.sinotrans.gd.wlp.common.model.SubmitOrderLogModel;
import com.sinotrans.gd.wlp.common.service.SubmitOrderLogManager;


@Service
public class SubmitOrderLogManagerImpl extends BaseManagerImpl implements SubmitOrderLogManager {

	@Override
	public SubmitOrderLogModel get(String uuid) {
		return this.dao.get(SubmitOrderLogModel.class, uuid);
	}

}
