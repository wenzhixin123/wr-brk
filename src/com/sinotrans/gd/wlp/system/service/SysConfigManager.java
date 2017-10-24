package com.sinotrans.gd.wlp.system.service;

import java.util.Collection;
import java.util.List;

import com.sinotrans.framework.core.service.BaseManager;
import com.sinotrans.gd.wlp.system.model.SysConfigModel;

public interface SysConfigManager extends BaseManager {

	SysConfigModel get(String id);

	List<SysConfigModel> getAll();

	List<SysConfigModel> findByExample(SysConfigModel example);

	SysConfigModel save(SysConfigModel model);

	List<SysConfigModel> saveAll(Collection<SysConfigModel> models);

	void remove(SysConfigModel model);

	void removeAll(Collection<SysConfigModel> models);

	void removeByPk(String id);

	void removeAllByPk(Collection<String> ids);

}
