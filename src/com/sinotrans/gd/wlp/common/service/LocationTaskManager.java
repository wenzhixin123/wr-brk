package com.sinotrans.gd.wlp.common.service;

import java.util.Collection;
import java.util.List;

import com.sinotrans.framework.core.service.BaseManager;
import com.sinotrans.gd.wlp.common.model.LocationTaskModel;

public interface LocationTaskManager extends BaseManager {

	LocationTaskModel get(String id);

	LocationTaskModel save(LocationTaskModel model);

	List<LocationTaskModel> saveAll(Collection<LocationTaskModel> models);

	void remove(LocationTaskModel model);

	void removeAll(Collection<LocationTaskModel> models);

	void removeByPk(String id);

	void removeAllByPk(Collection<String> ids);
}
