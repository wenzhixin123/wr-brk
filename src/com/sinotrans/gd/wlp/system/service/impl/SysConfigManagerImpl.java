package com.sinotrans.gd.wlp.system.service.impl;

import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Service;

import com.sinotrans.framework.core.service.impl.BaseManagerImpl;
import com.sinotrans.gd.wlp.system.model.SysConfigModel;
import com.sinotrans.gd.wlp.system.service.SysConfigManager;

@Service
public class SysConfigManagerImpl extends BaseManagerImpl implements
		SysConfigManager {

	public SysConfigModel get(String id) {
		return this.dao.get(SysConfigModel.class, id);
	}

	public List<SysConfigModel> getAll() {
		return this.dao.getAll(SysConfigModel.class);
	}

	public List<SysConfigModel> findByExample(SysConfigModel example) {
		return this.dao.findByExample(example);
	}

	public SysConfigModel save(SysConfigModel model) {
		return this.dao.save(model);
	}

	public List<SysConfigModel> saveAll(Collection<SysConfigModel> models) {
		return this.dao.saveAll(models);
	}

	public void remove(SysConfigModel model) {
		this.dao.remove(model);
	}

	public void removeAll(Collection<SysConfigModel> models) {
		this.dao.removeAll(models);
	}

	public void removeByPk(String id) {
		this.dao.removeByPk(SysConfigModel.class, id);
	}

	public void removeAllByPk(Collection<String> ids) {
		this.dao.removeAllByPk(SysConfigModel.class, ids);
	}

}
