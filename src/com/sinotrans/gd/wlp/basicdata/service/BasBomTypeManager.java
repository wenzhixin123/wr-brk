package com.sinotrans.gd.wlp.basicdata.service;

import java.util.Collection;
import java.util.List;

import com.sinotrans.framework.core.service.BaseManager;
import com.sinotrans.gd.wlp.basicdata.model.BasBomTypeModel;

public interface BasBomTypeManager extends BaseManager {

	BasBomTypeModel get(String id);

	List<BasBomTypeModel> getAll();

	List<BasBomTypeModel> findByExample(BasBomTypeModel example);

	BasBomTypeModel save(BasBomTypeModel model);

	List<BasBomTypeModel> saveAll(Collection<BasBomTypeModel> models);

	void remove(BasBomTypeModel model);

	void removeAll(Collection<BasBomTypeModel> models);

	void removeByPk(String id);

	void removeAllByPk(Collection<String> ids);

}
