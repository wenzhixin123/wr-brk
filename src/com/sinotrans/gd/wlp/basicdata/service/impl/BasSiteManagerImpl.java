package com.sinotrans.gd.wlp.basicdata.service.impl;

import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Service;

import com.sinotrans.framework.core.service.impl.BaseManagerImpl;
import com.sinotrans.gd.wlp.basicdata.model.BasSiteModel;
import com.sinotrans.gd.wlp.basicdata.service.BasSiteManager;
import com.sinotrans.gd.wlp.basicdata.util.BasdataServiceUtil;

@Service
public class BasSiteManagerImpl extends BaseManagerImpl implements
		BasSiteManager {

	public BasSiteModel get(String id) {
		return this.dao.get(BasSiteModel.class, id);
	}

	public List<BasSiteModel> getAll() {
		return this.dao.getAll(BasSiteModel.class);
	}

	public List<BasSiteModel> findByExample(BasSiteModel example) {
		return this.dao.findByExample(example);
	}

	public BasSiteModel save(BasSiteModel model) {
		return (BasSiteModel) this.dao.save(BasdataServiceUtil
				.beforeSave(model));
	}

	@SuppressWarnings("unchecked")
	public List<BasSiteModel> saveAll(Collection<BasSiteModel> models) {
		return (List<BasSiteModel>) this.dao.saveAll(BasdataServiceUtil
				.beforeSaveAll(models));
	}

	public void remove(BasSiteModel model) {
		this.dao.remove(model);
	}

	public void removeAll(Collection<BasSiteModel> models) {
		this.dao.removeAll(models);
	}

	public void removeByPk(String id) {
		this.dao.removeByPk(BasSiteModel.class, id);
	}

	public void removeAllByPk(Collection<String> ids) {
		this.dao.removeAllByPk(BasSiteModel.class, ids);
	}

}
