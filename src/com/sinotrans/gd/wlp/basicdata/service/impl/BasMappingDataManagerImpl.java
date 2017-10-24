package com.sinotrans.gd.wlp.basicdata.service.impl;

import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Service;

import com.sinotrans.framework.core.service.impl.BaseManagerImpl;
import com.sinotrans.gd.wlp.basicdata.model.BasMappingDataModel;
import com.sinotrans.gd.wlp.basicdata.service.BasMappingDataManager;

@Service
public class BasMappingDataManagerImpl extends BaseManagerImpl implements BasMappingDataManager {

	public BasMappingDataModel get(String id) {
		return this.dao.get(BasMappingDataModel.class, id);
	}

	public List<BasMappingDataModel> getAll() {
		return this.dao.getAll(BasMappingDataModel.class);
	}

	public List<BasMappingDataModel> findByExample(BasMappingDataModel example) {
		return this.dao.findByExample(example);
	}

	public BasMappingDataModel save(BasMappingDataModel model) {
		return this.dao.save(model);
	}

	public List<BasMappingDataModel> saveAll(Collection<BasMappingDataModel> models) {
		return this.dao.saveAll(models);
	}

	public void remove(BasMappingDataModel model) {
		this.dao.remove(model);
	}

	public void removeAll(Collection<BasMappingDataModel> models) {
		this.dao.removeAll(models);
	}

	public void removeByPk(String id) {
		this.dao.removeByPk(BasMappingDataModel.class, id);
	}

	public void removeAllByPk(Collection<String> ids) {
		this.dao.removeAllByPk(BasMappingDataModel.class, ids);
	}

}
