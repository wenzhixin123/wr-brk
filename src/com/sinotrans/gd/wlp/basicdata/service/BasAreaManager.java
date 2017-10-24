package com.sinotrans.gd.wlp.basicdata.service;

import java.util.Collection;
import java.util.List;

import com.sinotrans.framework.core.service.BaseManager;
import com.sinotrans.gd.wlp.basicdata.model.BasAreaModel;

public interface BasAreaManager extends BaseManager {

	BasAreaModel get(String id);

	List<BasAreaModel> getAll();

	List<BasAreaModel> findByExample(BasAreaModel example);

	BasAreaModel save(BasAreaModel model);

	List<BasAreaModel> saveAll(Collection<BasAreaModel> models);

	void remove(BasAreaModel model);

	void removeAll(Collection<BasAreaModel> models);

	void removeByPk(String id);

	void removeAllByPk(Collection<String> ids);

}
