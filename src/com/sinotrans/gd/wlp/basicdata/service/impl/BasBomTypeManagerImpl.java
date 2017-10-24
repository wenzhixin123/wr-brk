package com.sinotrans.gd.wlp.basicdata.service.impl;

import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Service;

import com.sinotrans.framework.core.service.impl.BaseManagerImpl;
import com.sinotrans.gd.wlp.basicdata.model.BasBomTypeModel;
import com.sinotrans.gd.wlp.basicdata.service.BasBomTypeManager;
import com.sinotrans.gd.wlp.basicdata.util.BasdataServiceUtil;

@Service
public class BasBomTypeManagerImpl extends BaseManagerImpl implements
		BasBomTypeManager {

	public BasBomTypeModel get(String id) {
		return this.dao.get(BasBomTypeModel.class, id);
	}

	public List<BasBomTypeModel> getAll() {
		return this.dao.getAll(BasBomTypeModel.class);
	}

	public List<BasBomTypeModel> findByExample(BasBomTypeModel example) {
		return this.dao.findByExample(example);
	}

	public BasBomTypeModel save(BasBomTypeModel model) {
		return (BasBomTypeModel) this.dao.save(BasdataServiceUtil
				.beforeSave(model));
	}

	@SuppressWarnings("unchecked")
	public List<BasBomTypeModel> saveAll(Collection<BasBomTypeModel> models) {
		return (List<BasBomTypeModel>) this.dao.saveAll(BasdataServiceUtil
				.beforeSaveAll(models));
	}

	public void remove(BasBomTypeModel model) {
		this.dao.remove(model);
	}

	public void removeAll(Collection<BasBomTypeModel> models) {
		this.dao.removeAll(models);
	}

	public void removeByPk(String id) {
		this.dao.removeByPk(BasBomTypeModel.class, id);
	}

	public void removeAllByPk(Collection<String> ids) {
		this.dao.removeAllByPk(BasBomTypeModel.class, ids);
	}

}
