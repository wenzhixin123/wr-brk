package com.sinotrans.gd.wlp.basicdata.service;

import java.util.Collection;
import java.util.List;

import com.sinotrans.framework.core.service.BaseManager;
import com.sinotrans.gd.wlp.basicdata.model.BasPortModel;

public interface BasPortManager extends BaseManager {

	BasPortModel get(String id);

	List<BasPortModel> getAll();

	List<BasPortModel> findByExample(BasPortModel example);

	BasPortModel save(BasPortModel model);

	List<BasPortModel> saveAll(Collection<BasPortModel> models);

	void remove(BasPortModel model);

	void removeAll(Collection<BasPortModel> models);

	void removeByPk(String id);

	void removeAllByPk(Collection<String> ids);
	
	BasPortModel queryPortByPortName(String portName);
	
	BasPortModel queryPortByPortCode(String portCode);
}
