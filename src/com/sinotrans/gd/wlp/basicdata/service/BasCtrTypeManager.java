package com.sinotrans.gd.wlp.basicdata.service;

import java.util.Collection;
import java.util.List;

import com.sinotrans.framework.core.service.BaseManager;
import com.sinotrans.gd.wlp.basicdata.model.BasCtrTypeModel;

public interface BasCtrTypeManager extends BaseManager {

	BasCtrTypeModel get(String id);

	List<BasCtrTypeModel> getAll();

	List<BasCtrTypeModel> findByExample(BasCtrTypeModel example);

	BasCtrTypeModel save(BasCtrTypeModel model);

	List<BasCtrTypeModel> saveAll(Collection<BasCtrTypeModel> models);

	void remove(BasCtrTypeModel model);

	void removeAll(Collection<BasCtrTypeModel> models);

	void removeByPk(String id);

	void removeAllByPk(Collection<String> ids);

}
