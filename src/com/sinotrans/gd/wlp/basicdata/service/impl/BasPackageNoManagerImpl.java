package com.sinotrans.gd.wlp.basicdata.service.impl;

import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Service;

import com.sinotrans.framework.core.service.impl.BaseManagerImpl;
import com.sinotrans.gd.wlp.basicdata.model.BasAreaModel;
import com.sinotrans.gd.wlp.basicdata.model.BasPackageNoModel;
import com.sinotrans.gd.wlp.basicdata.service.BasAreaManager;
import com.sinotrans.gd.wlp.basicdata.service.BasPackageNoManager;

@Service
public class BasPackageNoManagerImpl extends BaseManagerImpl implements BasPackageNoManager {

	public BasPackageNoModel get(String id) {
		return this.dao.get(BasPackageNoModel.class, id);
	}

	public List<BasPackageNoModel> getAll() {
		return this.dao.getAll(BasPackageNoModel.class);
	}

	public List<BasPackageNoModel> findByExample(BasPackageNoModel example) {
		return this.dao.findByExample(example);
	}

	public BasPackageNoModel save(BasPackageNoModel model) {
		return this.dao.save(model);
	}

	public List<BasPackageNoModel> saveAll(Collection<BasPackageNoModel> models) {
		return this.dao.saveAll(models);
	}

	public void remove(BasPackageNoModel model) {
		this.dao.remove(model);
	}

	public void removeAll(Collection<BasPackageNoModel> models) {
		this.dao.removeAll(models);
	}

	public void removeByPk(String id) {
		this.dao.removeByPk(BasPackageNoModel.class, id);
	}

	public void removeAllByPk(Collection<String> ids) {
		this.dao.removeAllByPk(BasPackageNoModel.class, ids);
	}

}
