package com.sinotrans.gd.wlp.basicdata.service;

import java.util.Collection;
import java.util.List;

import com.sinotrans.framework.core.service.BaseManager;
import com.sinotrans.gd.wlp.basicdata.model.BasPanelTypeModel;

public interface BasPanelTypeManager extends BaseManager {

	BasPanelTypeModel get(String id);

	List<BasPanelTypeModel> getAll();

	List<BasPanelTypeModel> findByExample(BasPanelTypeModel example);

	BasPanelTypeModel save(BasPanelTypeModel model);

	List<BasPanelTypeModel> saveAll(Collection<BasPanelTypeModel> models);

	void remove(BasPanelTypeModel model);

	void removeAll(Collection<BasPanelTypeModel> models);

	void removeByPk(String id);

	void removeAllByPk(Collection<String> ids);

}
