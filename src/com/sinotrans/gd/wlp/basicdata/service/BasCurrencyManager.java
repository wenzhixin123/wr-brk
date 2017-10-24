package com.sinotrans.gd.wlp.basicdata.service;

import java.util.Collection;
import java.util.List;

import com.sinotrans.framework.core.service.BaseManager;
import com.sinotrans.gd.wlp.basicdata.model.BasCurrencyModel;

public interface BasCurrencyManager extends BaseManager {

	BasCurrencyModel get(String id);

	List<BasCurrencyModel> getAll();

	List<BasCurrencyModel> findByExample(BasCurrencyModel example);

	BasCurrencyModel save(BasCurrencyModel model);

	List<BasCurrencyModel> saveAll(Collection<BasCurrencyModel> models);

	void remove(BasCurrencyModel model);

	void removeAll(Collection<BasCurrencyModel> models);

	void removeByPk(String id);

	void removeAllByPk(Collection<String> ids);

}
