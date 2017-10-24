package com.sinotrans.gd.wlp.basicdata.service;

import java.util.Collection;
import java.util.List;

import com.sinotrans.framework.core.service.BaseManager;
import com.sinotrans.gd.wlp.basicdata.model.BasUnitModel;

public interface BasUnitManager extends BaseManager {

	BasUnitModel get(String id);

	List<BasUnitModel> getAll();

	List<BasUnitModel> findByExample(BasUnitModel example);

	BasUnitModel save(BasUnitModel model);

	List<BasUnitModel> saveAll(Collection<BasUnitModel> models);

	void remove(BasUnitModel model);

	void removeAll(Collection<BasUnitModel> models);

	void removeByPk(String id);

	void removeAllByPk(Collection<String> ids);

	BasUnitModel queryModel(String unitCode);
	
	BasUnitModel queryModelByCusUnitCode(String customsUnitCode);
}
