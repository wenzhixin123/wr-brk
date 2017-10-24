package com.sinotrans.gd.wlp.basicdata.service;

import java.util.Collection;
import java.util.List;

import com.sinotrans.framework.core.service.BaseManager;
import com.sinotrans.gd.wlp.basicdata.model.BasBomDetailModel;

public interface BasBomDetailManager extends BaseManager {

	BasBomDetailModel get(String id);

	List<BasBomDetailModel> getAll();

	List<BasBomDetailModel> findByExample(BasBomDetailModel example);

	BasBomDetailModel save(BasBomDetailModel model);

	List<BasBomDetailModel> saveAll(Collection<BasBomDetailModel> models);

	void remove(BasBomDetailModel model);

	void removeAll(Collection<BasBomDetailModel> models);

	void removeByPk(String id);

	void removeAllByPk(Collection<String> ids);

	void removeAllByFk(String uuid);
}
