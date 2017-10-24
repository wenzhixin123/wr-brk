package com.sinotrans.gd.wlp.basicdata.service.impl;

import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Service;

import com.sinotrans.framework.core.service.impl.BaseManagerImpl;
import com.sinotrans.gd.wlp.basicdata.model.BasCityModel;
import com.sinotrans.gd.wlp.basicdata.service.BasCityManager;
import com.sinotrans.gd.wlp.basicdata.util.BasdataServiceUtil;

@Service
public class BasCityManagerImpl extends BaseManagerImpl implements
		BasCityManager {

	public BasCityModel get(String id) {
		return this.dao.get(BasCityModel.class, id);
	}

	public List<BasCityModel> getAll() {
		return this.dao.getAll(BasCityModel.class);
	}

	public List<BasCityModel> findByExample(BasCityModel example) {
		return this.dao.findByExample(example);
	}

	public BasCityModel save(BasCityModel model) {
		return (BasCityModel) this.dao.save(BasdataServiceUtil
				.beforeSave(model));
	}

	@SuppressWarnings("unchecked")
	public List<BasCityModel> saveAll(Collection<BasCityModel> models) {
		return (List<BasCityModel>) this.dao.saveAll(BasdataServiceUtil
				.beforeSaveAll(models));
	}

	public void remove(BasCityModel model) {
		this.dao.remove(model);
	}

	public void removeAll(Collection<BasCityModel> models) {
		this.dao.removeAll(models);
	}

	public void removeByPk(String id) {
		this.dao.removeByPk(BasCityModel.class, id);
	}

	public void removeAllByPk(Collection<String> ids) {
		this.dao.removeAllByPk(BasCityModel.class, ids);
	}

}
