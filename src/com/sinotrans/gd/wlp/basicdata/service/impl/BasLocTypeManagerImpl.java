package com.sinotrans.gd.wlp.basicdata.service.impl;

import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Service;

import com.sinotrans.framework.core.service.impl.BaseManagerImpl;
import com.sinotrans.gd.wlp.basicdata.model.BasLocTypeModel;
import com.sinotrans.gd.wlp.basicdata.service.BasLocTypeManager;
import com.sinotrans.gd.wlp.basicdata.util.BasdataServiceUtil;

@Service
public class BasLocTypeManagerImpl extends BaseManagerImpl implements
		BasLocTypeManager {

	public BasLocTypeModel get(String id) {
		return this.dao.get(BasLocTypeModel.class, id);
	}

	public List<BasLocTypeModel> getAll() {
		return this.dao.getAll(BasLocTypeModel.class);
	}

	public List<BasLocTypeModel> findByExample(BasLocTypeModel example) {
		return this.dao.findByExample(example);
	}

	public BasLocTypeModel save(BasLocTypeModel model) {
		return (BasLocTypeModel) this.dao.save(BasdataServiceUtil
				.beforeSave(model));
	}

	@SuppressWarnings("unchecked")
	public List<BasLocTypeModel> saveAll(Collection<BasLocTypeModel> models) {
		return (List<BasLocTypeModel>) this.dao.saveAll(BasdataServiceUtil
				.beforeSaveAll(models));
	}

	public void remove(BasLocTypeModel model) {
		this.dao.remove(model);
	}

	public void removeAll(Collection<BasLocTypeModel> models) {
		this.dao.removeAll(models);
	}

	public void removeByPk(String id) {
		this.dao.removeByPk(BasLocTypeModel.class, id);
	}

	public void removeAllByPk(Collection<String> ids) {
		this.dao.removeAllByPk(BasLocTypeModel.class, ids);
	}

}
