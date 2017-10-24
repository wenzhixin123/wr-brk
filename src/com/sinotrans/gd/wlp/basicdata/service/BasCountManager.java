package com.sinotrans.gd.wlp.basicdata.service;

import java.util.Collection;
import java.util.List;

import com.sinotrans.framework.core.service.BaseManager;
import com.sinotrans.gd.wlp.basicdata.model.BasCountModel;

public interface BasCountManager extends BaseManager {

	BasCountModel get(String id);

	List<BasCountModel> getAll();

	List<BasCountModel> findByExample(BasCountModel example);

	BasCountModel save(BasCountModel model);

	List<BasCountModel> saveAll(Collection<BasCountModel> models);

	void remove(BasCountModel model);

	void removeAll(Collection<BasCountModel> models);

	void removeByPk(String id);

	void removeAllByPk(Collection<String> ids);
	String getConHead(String pk);
}
