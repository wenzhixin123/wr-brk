package com.sinotrans.gd.wlp.basicdata.service;

import java.util.Collection;
import java.util.List;

import com.sinotrans.framework.core.service.BaseManager;
import com.sinotrans.gd.wlp.basicdata.model.BasProjectModel;

public interface BasProjectManager extends BaseManager {

	BasProjectModel get(String id);

	List<BasProjectModel> getAll();

	List<BasProjectModel> findByExample(BasProjectModel example);

	BasProjectModel save(BasProjectModel model);

	List<BasProjectModel> saveAll(Collection<BasProjectModel> models);

	void remove(BasProjectModel model);

	void removeAll(Collection<BasProjectModel> models);

	void removeByPk(String id);

	void removeAllByPk(Collection<String> ids);

}
