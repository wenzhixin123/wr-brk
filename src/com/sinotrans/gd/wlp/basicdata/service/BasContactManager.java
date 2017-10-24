package com.sinotrans.gd.wlp.basicdata.service;

import java.util.Collection;
import java.util.List;

import com.sinotrans.framework.core.service.BaseManager;
import com.sinotrans.gd.wlp.basicdata.model.BasContactModel;
import com.sinotrans.gd.wlp.common.web.SinotransPageJson;

public interface BasContactManager extends BaseManager {

	BasContactModel get(String id);

	List<BasContactModel> getAll();

	List<BasContactModel> findByExample(BasContactModel example);

	BasContactModel save(BasContactModel model);

	List<BasContactModel> saveAll(Collection<BasContactModel> models);

	void remove(BasContactModel model);

	void removeAll(Collection<BasContactModel> models);

	void removeByPk(String id);

	void removeAllByPk(Collection<String> ids);

	boolean getYanZhenUserCode(String userCode);
	
	SinotransPageJson saveBasContact(String jsonResult) throws Exception;

}
