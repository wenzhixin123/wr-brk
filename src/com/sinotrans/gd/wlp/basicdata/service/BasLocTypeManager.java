package com.sinotrans.gd.wlp.basicdata.service;

import java.util.Collection;
import java.util.List;

import com.sinotrans.framework.core.service.BaseManager;
import com.sinotrans.gd.wlp.basicdata.model.BasLocTypeModel;

public interface BasLocTypeManager extends BaseManager {

	BasLocTypeModel get(String id);

	List<BasLocTypeModel> getAll();

	List<BasLocTypeModel> findByExample(BasLocTypeModel example);

	BasLocTypeModel save(BasLocTypeModel model);

	List<BasLocTypeModel> saveAll(Collection<BasLocTypeModel> models);

	void remove(BasLocTypeModel model);

	void removeAll(Collection<BasLocTypeModel> models);

	void removeByPk(String id);

	void removeAllByPk(Collection<String> ids);

}
