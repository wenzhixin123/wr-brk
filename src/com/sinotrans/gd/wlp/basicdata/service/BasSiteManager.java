package com.sinotrans.gd.wlp.basicdata.service;

import java.util.Collection;
import java.util.List;

import com.sinotrans.framework.core.service.BaseManager;
import com.sinotrans.gd.wlp.basicdata.model.BasSiteModel;

public interface BasSiteManager extends BaseManager {

	BasSiteModel get(String id);

	List<BasSiteModel> getAll();

	List<BasSiteModel> findByExample(BasSiteModel example);

	BasSiteModel save(BasSiteModel model);

	List<BasSiteModel> saveAll(Collection<BasSiteModel> models);

	void remove(BasSiteModel model);

	void removeAll(Collection<BasSiteModel> models);

	void removeByPk(String id);

	void removeAllByPk(Collection<String> ids);

}
