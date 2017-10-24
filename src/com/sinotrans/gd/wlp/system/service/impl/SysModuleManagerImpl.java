package com.sinotrans.gd.wlp.system.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Service;

import com.sinotrans.framework.core.service.impl.BaseManagerImpl;
import com.sinotrans.framework.core.support.Condition;
import com.sinotrans.gd.wlp.system.model.SysModuleModel;
import com.sinotrans.gd.wlp.system.service.SysModuleManager;

@Service
public class SysModuleManagerImpl extends BaseManagerImpl implements
		SysModuleManager {

	public SysModuleModel get(String id) {
		return this.dao.get(SysModuleModel.class, id);
	}

	public List<SysModuleModel> getAll() {
		return this.dao.getAll(SysModuleModel.class);
	}

	public List<SysModuleModel> findByExample(SysModuleModel example) {
		return this.dao.findByExample(example);
	}

	public SysModuleModel save(SysModuleModel model) {
		return this.dao.save(model);
	}

	public List<SysModuleModel> saveAll(Collection<SysModuleModel> models) {
		return this.dao.saveAll(models);
	}

	public void remove(SysModuleModel model) {
		this.dao.remove(model);
	}

	public void removeAll(Collection<SysModuleModel> models) {
		this.dao.removeAll(models);
	}

	public void removeByPk(String id) {
		this.dao.removeByPk(SysModuleModel.class, id);
	}

	public void removeAllByPk(Collection<String> ids) {
		this.dao.removeAllByPk(SysModuleModel.class, ids);
	}

	public List<SysModuleModel> findByModuleIds(List<String> sysModuleUuid) {
		List<SysModuleModel> result = new ArrayList<SysModuleModel>();
		result = this.dao.createCommonQuery(SysModuleModel.class).addCondition(
				Condition.in("sysModuleUuid", sysModuleUuid.toArray()))
				.setOrderBy("moduleSeq").query();
		return result;
	}
}
