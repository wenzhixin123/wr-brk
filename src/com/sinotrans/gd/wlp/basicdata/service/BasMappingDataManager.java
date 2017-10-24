package com.sinotrans.gd.wlp.basicdata.service;

import java.util.Collection;
import java.util.List;

import com.sinotrans.framework.core.service.BaseManager;
import com.sinotrans.gd.wlp.basicdata.model.BasMappingDataModel;

public interface BasMappingDataManager extends BaseManager {

	BasMappingDataModel get(String id);

	List<BasMappingDataModel> getAll();

	List<BasMappingDataModel> findByExample(BasMappingDataModel example);

	BasMappingDataModel save(BasMappingDataModel model);

	List<BasMappingDataModel> saveAll(Collection<BasMappingDataModel> models);

	void remove(BasMappingDataModel model);

	void removeAll(Collection<BasMappingDataModel> models);

	void removeByPk(String id);

	void removeAllByPk(Collection<String> ids);

}
