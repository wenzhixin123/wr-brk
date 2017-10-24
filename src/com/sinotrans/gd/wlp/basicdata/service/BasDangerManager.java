package com.sinotrans.gd.wlp.basicdata.service;

import java.util.Collection;
import java.util.List;

import com.sinotrans.framework.core.service.BaseManager;
import com.sinotrans.gd.wlp.basicdata.model.BasDangerModel;

public interface BasDangerManager extends BaseManager {

	BasDangerModel get(String id);

	List<BasDangerModel> getAll();

	List<BasDangerModel> findByExample(BasDangerModel example);

	BasDangerModel save(BasDangerModel model);

	List<BasDangerModel> saveAll(Collection<BasDangerModel> models);

	void remove(BasDangerModel model);

	void removeAll(Collection<BasDangerModel> models);

	void removeByPk(String id);

	void removeAllByPk(Collection<String> ids);
	
	String getUncodeByUnnameLikeAnywhere(String unName);
	
	String getDangerModByUnName(String unName);

}
