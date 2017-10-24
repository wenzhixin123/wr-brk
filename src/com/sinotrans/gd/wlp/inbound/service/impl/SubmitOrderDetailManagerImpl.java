package com.sinotrans.gd.wlp.inbound.service.impl;

import org.springframework.stereotype.Service;

import com.sinotrans.framework.core.service.impl.BaseManagerImpl;
import com.sinotrans.gd.wlp.common.model.SubmitOrderDetailModel;
import com.sinotrans.gd.wlp.inbound.service.SubmitOrderDetailManager;

@Service
public class SubmitOrderDetailManagerImpl extends BaseManagerImpl implements SubmitOrderDetailManager{
	 public SubmitOrderDetailModel get(String id){
		return this.dao.get(SubmitOrderDetailModel.class, id);
	}
}
