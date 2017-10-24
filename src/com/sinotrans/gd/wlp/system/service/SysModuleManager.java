package com.sinotrans.gd.wlp.system.service;

import java.util.Collection;
import java.util.List;

import com.sinotrans.framework.core.service.BaseManager;
import com.sinotrans.gd.wlp.system.model.SysModuleModel;

public interface SysModuleManager extends BaseManager {

	SysModuleModel get(String id);

	List<SysModuleModel> getAll();

	List<SysModuleModel> findByExample(SysModuleModel example);

	SysModuleModel save(SysModuleModel model);

	List<SysModuleModel> saveAll(Collection<SysModuleModel> models);

	void remove(SysModuleModel model);

	void removeAll(Collection<SysModuleModel> models);

	void removeByPk(String id);

	void removeAllByPk(Collection<String> ids);

	public List<SysModuleModel> findByModuleIds(List<String> sysModuleUuid);

}
