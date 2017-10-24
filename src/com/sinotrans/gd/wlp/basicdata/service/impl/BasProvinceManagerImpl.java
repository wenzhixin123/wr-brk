package com.sinotrans.gd.wlp.basicdata.service.impl;

import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Service;

import com.sinotrans.framework.core.service.impl.BaseManagerImpl;
import com.sinotrans.gd.wlp.basicdata.model.BasProvinceModel;
import com.sinotrans.gd.wlp.basicdata.service.BasProvinceManager;
import com.sinotrans.gd.wlp.basicdata.util.BasdataServiceUtil;

@Service
public class BasProvinceManagerImpl extends BaseManagerImpl implements
		BasProvinceManager {

	public BasProvinceModel get(String id) {
		return this.dao.get(BasProvinceModel.class, id);
	}

	public List<BasProvinceModel> getAll() {
		return this.dao.getAll(BasProvinceModel.class);
	}

	public List<BasProvinceModel> findByExample(BasProvinceModel example) {
		return this.dao.findByExample(example);
	}

	public BasProvinceModel save(BasProvinceModel model) {
		return (BasProvinceModel) this.dao.save(BasdataServiceUtil
				.beforeSave(model));
	}

	@SuppressWarnings("unchecked")
	public List<BasProvinceModel> saveAll(Collection<BasProvinceModel> models) {
		return (List<BasProvinceModel>) this.dao.saveAll(BasdataServiceUtil
				.beforeSaveAll(models));
	}

	public void remove(BasProvinceModel model) {
		this.dao.remove(model);
	}

	public void removeAll(Collection<BasProvinceModel> models) {
		this.dao.removeAll(models);
	}

	public void removeByPk(String id) {
		this.dao.removeByPk(BasProvinceModel.class, id);
	}

	public void removeAllByPk(Collection<String> ids) {
		this.dao.removeAllByPk(BasProvinceModel.class, ids);
	}

}
