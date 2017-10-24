package com.sinotrans.gd.wlp.basicdata.service;

import java.util.Collection;
import java.util.List;

import com.sinotrans.framework.core.service.BaseManager;
import com.sinotrans.gd.wlp.basicdata.model.BasFileManageModel;

public interface BasFileManageManager extends BaseManager {

	BasFileManageModel get(String id);

	List<BasFileManageModel> getAll();

	List<BasFileManageModel> findByExample(BasFileManageModel example);

	BasFileManageModel save(BasFileManageModel model);

	List<BasFileManageModel> saveAll(Collection<BasFileManageModel> models);

	void remove(BasFileManageModel model);

	void removeAll(Collection<BasFileManageModel> models);

	void removeByPk(String id);

	void removeAllByPk(Collection<String> ids);

}
