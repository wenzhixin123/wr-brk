package com.sinotrans.gd.wlp.system.service;

import java.util.Collection;
import java.util.List;

import com.sinotrans.framework.core.service.BaseManager;
import com.sinotrans.gd.wlp.system.model.SysSystemsModel;

public interface SysSystemsManager extends BaseManager {

	SysSystemsModel get(String id);

	List<SysSystemsModel> getAll();

	List<SysSystemsModel> findByExample(SysSystemsModel example);

	SysSystemsModel save(SysSystemsModel model);

	List<SysSystemsModel> saveAll(Collection<SysSystemsModel> models);

	void remove(SysSystemsModel model);

	void removeAll(Collection<SysSystemsModel> models);

	void removeByPk(String id);

	void removeAllByPk(Collection<String> ids);

	/**
	 * 首页报表查询数据展示
	 * 
	 * @param type
	 * @return
	 */
	//public String indexReport(String type, String officeCode);
}
