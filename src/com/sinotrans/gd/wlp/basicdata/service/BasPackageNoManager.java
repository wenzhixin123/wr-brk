package com.sinotrans.gd.wlp.basicdata.service;

import java.util.Collection;
import java.util.List;

import com.sinotrans.framework.core.service.BaseManager;
import com.sinotrans.gd.wlp.basicdata.model.BasPackageNoModel;

public interface BasPackageNoManager extends BaseManager {

	BasPackageNoModel get(String id);

	List<BasPackageNoModel> getAll();

	List<BasPackageNoModel> findByExample(BasPackageNoModel example);

	BasPackageNoModel save(BasPackageNoModel model);

	List<BasPackageNoModel> saveAll(Collection<BasPackageNoModel> models);

	void remove(BasPackageNoModel model);

	void removeAll(Collection<BasPackageNoModel> models);

	void removeByPk(String id);

	void removeAllByPk(Collection<String> ids);

}
