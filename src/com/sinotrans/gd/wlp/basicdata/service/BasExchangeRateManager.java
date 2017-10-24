package com.sinotrans.gd.wlp.basicdata.service;

import java.util.Collection;
import java.util.List;

import com.sinotrans.framework.core.service.BaseManager;
import com.sinotrans.gd.wlp.basicdata.model.BasExchangeRateModel;

public interface BasExchangeRateManager extends BaseManager {

	BasExchangeRateModel get(String id);

	List<BasExchangeRateModel> getAll();

	List<BasExchangeRateModel> findByExample(BasExchangeRateModel example);

	BasExchangeRateModel save(BasExchangeRateModel model);

	List<BasExchangeRateModel> saveAll(Collection<BasExchangeRateModel> models);

	void remove(BasExchangeRateModel model);

	void removeAll(Collection<BasExchangeRateModel> models);

	void removeByPk(String id);

	void removeAllByPk(Collection<String> ids);

}
