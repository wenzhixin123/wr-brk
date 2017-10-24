package com.sinotrans.gd.wlp.basicdata.service.impl;

import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Service;

import com.sinotrans.framework.core.service.impl.BaseManagerImpl;
import com.sinotrans.gd.wlp.basicdata.model.BasAreaModel;
import com.sinotrans.gd.wlp.basicdata.service.BasAreaManager;

@Service
public class BasAreaManagerImpl extends BaseManagerImpl implements BasAreaManager {

	public BasAreaModel get(String id) {
		return this.dao.get(BasAreaModel.class, id);
	}

	public List<BasAreaModel> getAll() {
		return this.dao.getAll(BasAreaModel.class);
	}

	public List<BasAreaModel> findByExample(BasAreaModel example) {
		return this.dao.findByExample(example);
	}

	public BasAreaModel save(BasAreaModel model) {
		return this.dao.save(model);
	}

	public List<BasAreaModel> saveAll(Collection<BasAreaModel> models) {
		return this.dao.saveAll(models);
	}

	public void remove(BasAreaModel model) {
		this.dao.remove(model);
	}

	public void removeAll(Collection<BasAreaModel> models) {
		this.dao.removeAll(models);
	}

	public void removeByPk(String id) {
		this.dao.removeByPk(BasAreaModel.class, id);
	}

	public void removeAllByPk(Collection<String> ids) {
		this.dao.removeAllByPk(BasAreaModel.class, ids);
	}

}
