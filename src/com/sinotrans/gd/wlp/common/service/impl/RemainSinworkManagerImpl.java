package com.sinotrans.gd.wlp.common.service.impl;

import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Service;

import com.sinotrans.framework.core.service.impl.BaseManagerImpl;
import com.sinotrans.gd.wlp.common.model.BarcodeModel;
import com.sinotrans.gd.wlp.common.model.RemainSinworkModel;
import com.sinotrans.gd.wlp.common.service.RemainSinworkManager;

@Service
public class RemainSinworkManagerImpl extends BaseManagerImpl implements
        RemainSinworkManager {

	
	
	public RemainSinworkModel get(String id) {
		return this.dao.get(RemainSinworkModel.class, id);
	}

	public RemainSinworkModel save(RemainSinworkModel model) {
		return this.dao.save(model);
	}

	

	public List<RemainSinworkModel> saveAll(Collection<RemainSinworkModel> models) {
		return this.dao.saveAll(models);
	}

	public void remove(RemainSinworkModel model) {
		this.dao.remove(model);
	}

	public void removeAll(Collection<RemainSinworkModel> models) {
		this.dao.removeAll(models);
	}

	public void removeByPk(String id) {
		this.dao.removeByPk(RemainSinworkModel.class, id);
	}

	public void removeAllByPk(Collection<String> ids) {
		this.dao.removeAllByPk(RemainSinworkModel.class, ids);
	}
	
	
}