package com.sinotrans.gd.wlp.basicdata.service;

import java.util.Collection;
import java.util.List;

import com.sinotrans.framework.core.service.BaseManager;
import com.sinotrans.gd.wlp.basicdata.model.BasWrapperModel;

public interface BasWrapperManager extends BaseManager {

	BasWrapperModel get(String id);

	List<BasWrapperModel> getAll();

	List<BasWrapperModel> findByExample(BasWrapperModel example);

	BasWrapperModel save(BasWrapperModel model);

	List<BasWrapperModel> saveAll(Collection<BasWrapperModel> models);

	void remove(BasWrapperModel model);

	void removeAll(Collection<BasWrapperModel> models);

	void removeByPk(String id);

	void removeAllByPk(Collection<String> ids);

}
