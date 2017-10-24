package com.sinotrans.gd.wlp.basicdata.service.impl;

import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Service;

import com.sinotrans.framework.core.service.impl.BaseManagerImpl;
import com.sinotrans.gd.wlp.basicdata.model.BasExchangeRateModel;
import com.sinotrans.gd.wlp.basicdata.service.BasExchangeRateManager;
import com.sinotrans.gd.wlp.basicdata.util.BasdataServiceUtil;

@Service
public class BasExchangeRateManagerImpl extends BaseManagerImpl implements
		BasExchangeRateManager {

	public BasExchangeRateModel get(String id) {
		return this.dao.get(BasExchangeRateModel.class, id);
	}

	public List<BasExchangeRateModel> getAll() {
		return this.dao.getAll(BasExchangeRateModel.class);
	}

	public List<BasExchangeRateModel> findByExample(BasExchangeRateModel example) {
		return this.dao.findByExample(example);
	}

	public BasExchangeRateModel save(BasExchangeRateModel model) {
		return (BasExchangeRateModel) this.dao.save(BasdataServiceUtil
				.beforeSave(model));
	}

	@SuppressWarnings("unchecked")
	public List<BasExchangeRateModel> saveAll(
			Collection<BasExchangeRateModel> models) {
		return (List<BasExchangeRateModel>) this.dao.saveAll(BasdataServiceUtil
				.beforeSaveAll(models));
	}

	public void remove(BasExchangeRateModel model) {
		this.dao.remove(model);
	}

	public void removeAll(Collection<BasExchangeRateModel> models) {
		this.dao.removeAll(models);
	}

	public void removeByPk(String id) {
		this.dao.removeByPk(BasExchangeRateModel.class, id);
	}

	public void removeAllByPk(Collection<String> ids) {
		this.dao.removeAllByPk(BasExchangeRateModel.class, ids);
	}

}
