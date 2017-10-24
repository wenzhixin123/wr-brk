package com.sinotrans.gd.wlp.basicdata.service;

import java.util.Collection;
import java.util.List;

import com.sinotrans.framework.core.service.BaseManager;
import com.sinotrans.gd.wlp.basicdata.model.BasCityModel;

public interface BasCityManager extends BaseManager {

	BasCityModel get(String id);

	List<BasCityModel> getAll();

	List<BasCityModel> findByExample(BasCityModel example);

	BasCityModel save(BasCityModel model);

	List<BasCityModel> saveAll(Collection<BasCityModel> models);

	void remove(BasCityModel model);

	void removeAll(Collection<BasCityModel> models);

	void removeByPk(String id);

	void removeAllByPk(Collection<String> ids);

}
