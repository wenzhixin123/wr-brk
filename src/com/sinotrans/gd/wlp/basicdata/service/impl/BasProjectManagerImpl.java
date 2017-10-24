package com.sinotrans.gd.wlp.basicdata.service.impl;

import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Service;

import com.sinotrans.framework.core.service.impl.BaseManagerImpl;
import com.sinotrans.gd.wlp.basicdata.model.BasProjectModel;
import com.sinotrans.gd.wlp.basicdata.service.BasProjectManager;
import com.sinotrans.gd.wlp.basicdata.util.BasdataServiceUtil;

@Service
public class BasProjectManagerImpl extends BaseManagerImpl implements
		BasProjectManager {

	public BasProjectModel get(String id) {
		return this.dao.get(BasProjectModel.class, id);
	}

	public List<BasProjectModel> getAll() {
		return this.dao.getAll(BasProjectModel.class);
	}

	public List<BasProjectModel> findByExample(BasProjectModel example) {
		return this.dao.findByExample(example);
	}

	public BasProjectModel save(BasProjectModel model) {
		return (BasProjectModel) this.dao.save(BasdataServiceUtil
				.beforeSave(model));
	}

	@SuppressWarnings("unchecked")
	public List<BasProjectModel> saveAll(Collection<BasProjectModel> models) {
		return (List<BasProjectModel>) this.dao.saveAll(BasdataServiceUtil
				.beforeSaveAll(models));
	}

	public void remove(BasProjectModel model) {
		this.dao.remove(model);
	}

	public void removeAll(Collection<BasProjectModel> models) {
		this.dao.removeAll(models);
	}

	public void removeByPk(String id) {
		this.dao.removeByPk(BasProjectModel.class, id);
	}

	public void removeAllByPk(Collection<String> ids) {
		this.dao.removeAllByPk(BasProjectModel.class, ids);
	}

}
