package com.sinotrans.gd.wlp.basicdata.service.impl;

import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Service;

import com.sinotrans.framework.core.service.impl.BaseManagerImpl;
import com.sinotrans.gd.wlp.basicdata.model.BasCurrencyModel;
import com.sinotrans.gd.wlp.basicdata.service.BasCurrencyManager;
import com.sinotrans.gd.wlp.basicdata.util.BasdataServiceUtil;

@Service
public class BasCurrencyManagerImpl extends BaseManagerImpl implements
		BasCurrencyManager {

	public BasCurrencyModel get(String id) {
		return this.dao.get(BasCurrencyModel.class, id);
	}

	public List<BasCurrencyModel> getAll() {
		return this.dao.getAll(BasCurrencyModel.class);
	}

	public List<BasCurrencyModel> findByExample(BasCurrencyModel example) {
		return this.dao.findByExample(example);
	}

	public BasCurrencyModel save(BasCurrencyModel model) {
		return (BasCurrencyModel) this.dao.save(BasdataServiceUtil
				.beforeSave(model));
	}

	@SuppressWarnings("unchecked")
	public List<BasCurrencyModel> saveAll(Collection<BasCurrencyModel> models) {
		return (List<BasCurrencyModel>) this.dao.saveAll(BasdataServiceUtil
				.beforeSaveAll(models));
	}

	public void remove(BasCurrencyModel model) {
		this.dao.remove(model);
	}

	public void removeAll(Collection<BasCurrencyModel> models) {
		this.dao.removeAll(models);
	}

	public void removeByPk(String id) {
		this.dao.removeByPk(BasCurrencyModel.class, id);
	}

	public void removeAllByPk(Collection<String> ids) {
		this.dao.removeAllByPk(BasCurrencyModel.class, ids);
	}

}
