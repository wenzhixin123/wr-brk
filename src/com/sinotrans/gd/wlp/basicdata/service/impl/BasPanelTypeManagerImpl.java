package com.sinotrans.gd.wlp.basicdata.service.impl;

import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Service;

import com.sinotrans.framework.core.service.impl.BaseManagerImpl;
import com.sinotrans.gd.wlp.basicdata.model.BasPanelTypeModel;
import com.sinotrans.gd.wlp.basicdata.service.BasPanelTypeManager;
import com.sinotrans.gd.wlp.basicdata.util.BasdataServiceUtil;

@Service
public class BasPanelTypeManagerImpl extends BaseManagerImpl implements
		BasPanelTypeManager {

	public BasPanelTypeModel get(String id) {
		return this.dao.get(BasPanelTypeModel.class, id);
	}

	public List<BasPanelTypeModel> getAll() {
		return this.dao.getAll(BasPanelTypeModel.class);
	}

	public List<BasPanelTypeModel> findByExample(BasPanelTypeModel example) {
		return this.dao.findByExample(example);
	}

	public BasPanelTypeModel save(BasPanelTypeModel model) {
		return (BasPanelTypeModel) this.dao.save(BasdataServiceUtil
				.beforeSave(model));
	}

	@SuppressWarnings("unchecked")
	public List<BasPanelTypeModel> saveAll(Collection<BasPanelTypeModel> models) {
		return (List<BasPanelTypeModel>) this.dao.saveAll(BasdataServiceUtil
				.beforeSaveAll(models));
	}

	public void remove(BasPanelTypeModel model) {
		this.dao.remove(model);
	}

	public void removeAll(Collection<BasPanelTypeModel> models) {
		this.dao.removeAll(models);
	}

	public void removeByPk(String id) {
		this.dao.removeByPk(BasPanelTypeModel.class, id);
	}

	public void removeAllByPk(Collection<String> ids) {
		this.dao.removeAllByPk(BasPanelTypeModel.class, ids);
	}

}
