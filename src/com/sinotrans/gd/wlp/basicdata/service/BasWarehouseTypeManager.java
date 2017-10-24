package com.sinotrans.gd.wlp.basicdata.service;

import java.util.Collection;
import java.util.List;

import com.sinotrans.framework.core.service.BaseManager;
import com.sinotrans.gd.wlp.basicdata.model.BasWarehouseTypeModel;

public interface BasWarehouseTypeManager extends BaseManager {

	BasWarehouseTypeModel get(String id);

	List<BasWarehouseTypeModel> getAll();

	List<BasWarehouseTypeModel> findByExample(BasWarehouseTypeModel example);

	BasWarehouseTypeModel save(BasWarehouseTypeModel model);

	List<BasWarehouseTypeModel> saveAll(Collection<BasWarehouseTypeModel> models);

	void remove(BasWarehouseTypeModel model);

	void removeAll(Collection<BasWarehouseTypeModel> models);

	void removeByPk(String id);

	void removeAllByPk(Collection<String> ids);

}
