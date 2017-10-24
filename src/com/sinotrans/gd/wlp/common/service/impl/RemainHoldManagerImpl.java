package com.sinotrans.gd.wlp.common.service.impl;

import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Service;

import com.sinotrans.framework.core.service.impl.BaseManagerImpl;
import com.sinotrans.gd.wlp.common.model.BarcodeModel;
import com.sinotrans.gd.wlp.common.model.RemainHoldModel;
import com.sinotrans.gd.wlp.common.service.BarcodeManager;
import com.sinotrans.gd.wlp.common.service.RemainHoldManager;

@Service
public class RemainHoldManagerImpl extends BaseManagerImpl implements
		RemainHoldManager {

	
	
	public RemainHoldModel get(String id) {
		return this.dao.get(RemainHoldModel.class, id);
	}

	public RemainHoldModel save(RemainHoldModel model) {
		return this.dao.save(model);
	}

	

	public List<RemainHoldModel> saveAll(Collection<RemainHoldModel> models) {
		return this.dao.saveAll(models);
	}

	public void remove(RemainHoldModel model) {
		this.dao.remove(model);
	}

	public void removeAll(Collection<RemainHoldModel> models) {
		this.dao.removeAll(models);
	}

	public void removeByPk(String id) {
		this.dao.removeByPk(RemainHoldModel.class, id);
	}

	public void removeAllByPk(Collection<String> ids) {
		this.dao.removeAllByPk(RemainHoldModel.class, ids);
	}
	
	
}