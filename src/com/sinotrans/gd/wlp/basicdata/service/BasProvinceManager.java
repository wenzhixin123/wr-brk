package com.sinotrans.gd.wlp.basicdata.service;

import java.util.Collection;
import java.util.List;

import com.sinotrans.framework.core.service.BaseManager;
import com.sinotrans.gd.wlp.basicdata.model.BasProvinceModel;

public interface BasProvinceManager extends BaseManager {

	BasProvinceModel get(String id);

	List<BasProvinceModel> getAll();

	List<BasProvinceModel> findByExample(BasProvinceModel example);

	BasProvinceModel save(BasProvinceModel model);

	List<BasProvinceModel> saveAll(Collection<BasProvinceModel> models);

	void remove(BasProvinceModel model);

	void removeAll(Collection<BasProvinceModel> models);

	void removeByPk(String id);

	void removeAllByPk(Collection<String> ids);

}
