package com.sinotrans.gd.wlp.basicdata.service.impl;

import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Service;

import com.sinotrans.framework.core.service.impl.BaseManagerImpl;
import com.sinotrans.gd.wlp.basicdata.model.BasFileManageModel;
import com.sinotrans.gd.wlp.basicdata.service.BasFileManageManager;
import com.sinotrans.gd.wlp.basicdata.util.BasdataServiceUtil;

@Service
public class BasFileManageManagerImpl extends BaseManagerImpl implements
		BasFileManageManager {

	public BasFileManageModel get(String id) {
		return this.dao.get(BasFileManageModel.class, id);
	}

	public List<BasFileManageModel> getAll() {
		return this.dao.getAll(BasFileManageModel.class);
	}

	public List<BasFileManageModel> findByExample(BasFileManageModel example) {
		return this.dao.findByExample(example);
	}

	public BasFileManageModel save(BasFileManageModel model) {
		return (BasFileManageModel) this.dao.save(BasdataServiceUtil
				.beforeSave(model));
	}

	@SuppressWarnings("unchecked")
	public List<BasFileManageModel> saveAll(
			Collection<BasFileManageModel> models) {
		return (List<BasFileManageModel>) this.dao.saveAll(BasdataServiceUtil
				.beforeSaveAll(models));
	}

	public void remove(BasFileManageModel model) {
		this.dao.remove(model);
	}

	public void removeAll(Collection<BasFileManageModel> models) {
		this.dao.removeAll(models);
	}

	public void removeByPk(String id) {
		this.dao.removeByPk(BasFileManageModel.class, id);
	}

	public void removeAllByPk(Collection<String> ids) {
		this.dao.removeAllByPk(BasFileManageModel.class, ids);
	}

}
