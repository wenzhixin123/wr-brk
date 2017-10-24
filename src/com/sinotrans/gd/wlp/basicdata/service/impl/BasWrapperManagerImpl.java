package com.sinotrans.gd.wlp.basicdata.service.impl;

import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Service;

import com.sinotrans.framework.core.service.impl.BaseManagerImpl;
import com.sinotrans.gd.wlp.basicdata.model.BasWrapperModel;
import com.sinotrans.gd.wlp.basicdata.service.BasWrapperManager;

@Service
public class BasWrapperManagerImpl extends BaseManagerImpl implements BasWrapperManager {

	public BasWrapperModel get(String id) {
		return this.dao.get(BasWrapperModel.class, id);
	}

	public List<BasWrapperModel> getAll() {
		return this.dao.getAll(BasWrapperModel.class);
	}

	public List<BasWrapperModel> findByExample(BasWrapperModel example) {
		return this.dao.findByExample(example);
	}

	public BasWrapperModel save(BasWrapperModel model) {
		return this.dao.save(model);
	}

	public List<BasWrapperModel> saveAll(Collection<BasWrapperModel> models) {
		return this.dao.saveAll(models);
	}

	public void remove(BasWrapperModel model) {
		this.dao.remove(model);
	}

	public void removeAll(Collection<BasWrapperModel> models) {
		this.dao.removeAll(models);
	}

	public void removeByPk(String id) {
		this.dao.removeByPk(BasWrapperModel.class, id);
	}

	public void removeAllByPk(Collection<String> ids) {
		this.dao.removeAllByPk(BasWrapperModel.class, ids);
	}

}
