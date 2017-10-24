package com.sinotrans.gd.wlp.common.service.impl;

import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Service;

import com.sinotrans.framework.core.service.impl.BaseManagerImpl;
import com.sinotrans.gd.wlp.common.model.LocationTaskModel;
import com.sinotrans.gd.wlp.common.service.LocationTaskManager;

/**
 * @author Administrator
 * 
 */
@Service
public class LocationTaskManagerImpl extends BaseManagerImpl implements
		LocationTaskManager {
	public LocationTaskModel get(String id) {
		return this.dao.get(LocationTaskModel.class, id);
	}

	public void remove(LocationTaskModel model) {
		this.dao.remove(model);
	}

	public void removeAll(Collection<LocationTaskModel> models) {
		this.dao.removeAll(models);
	}

	public void removeAllByPk(Collection<String> ids) {
		this.dao.removeAllByPk(LocationTaskModel.class, ids);
	}

	public void removeByPk(String id) {
		this.dao.removeByPk(LocationTaskModel.class, id);
	}

	public LocationTaskModel save(LocationTaskModel model) {
		return this.dao.save(model);
	}

	public List<LocationTaskModel> saveAll(Collection<LocationTaskModel> models) {
		return this.dao.saveAll(models);
	}
}
