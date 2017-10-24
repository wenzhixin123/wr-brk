package com.sinotrans.gd.wlp.common.service.impl;

import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Service;

import com.sinotrans.framework.core.service.impl.BaseManagerImpl;
import com.sinotrans.gd.wlp.common.model.BarcodeModel;
import com.sinotrans.gd.wlp.common.service.BarcodeManager;

@Service
public class BarcodeManagerImpl extends BaseManagerImpl implements
		BarcodeManager {

	
	
	public BarcodeModel get(String id) {
		return this.dao.get(BarcodeModel.class, id);
	}

	public BarcodeModel save(BarcodeModel model) {
		return this.dao.save(model);
	}

	

	public List<BarcodeModel> saveAll(Collection<BarcodeModel> models) {
		return this.dao.saveAll(models);
	}

	public void remove(BarcodeModel model) {
		this.dao.remove(model);
	}

	public void removeAll(Collection<BarcodeModel> models) {
		this.dao.removeAll(models);
	}

	public void removeByPk(String id) {
		this.dao.removeByPk(BarcodeModel.class, id);
	}

	public void removeAllByPk(Collection<String> ids) {
		this.dao.removeAllByPk(BarcodeModel.class, ids);
	}
	
	
}