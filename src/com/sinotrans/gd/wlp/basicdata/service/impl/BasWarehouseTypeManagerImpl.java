package com.sinotrans.gd.wlp.basicdata.service.impl;

import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Service;

import com.sinotrans.framework.core.service.impl.BaseManagerImpl;
import com.sinotrans.gd.wlp.basicdata.model.BasWarehouseTypeModel;
import com.sinotrans.gd.wlp.basicdata.service.BasWarehouseTypeManager;
import com.sinotrans.gd.wlp.basicdata.util.BasdataServiceUtil;

@Service
public class BasWarehouseTypeManagerImpl extends BaseManagerImpl implements
		BasWarehouseTypeManager {

	public BasWarehouseTypeModel get(String id) {
		return this.dao.get(BasWarehouseTypeModel.class, id);
	}

	public List<BasWarehouseTypeModel> getAll() {
		return this.dao.getAll(BasWarehouseTypeModel.class);
	}

	public List<BasWarehouseTypeModel> findByExample(
			BasWarehouseTypeModel example) {
		return this.dao.findByExample(example);
	}

	public BasWarehouseTypeModel save(BasWarehouseTypeModel model) {
		return (BasWarehouseTypeModel) this.dao.save(BasdataServiceUtil
				.beforeSave(model));
	}

	@SuppressWarnings("unchecked")
	public List<BasWarehouseTypeModel> saveAll(
			Collection<BasWarehouseTypeModel> models) {
		return (List<BasWarehouseTypeModel>) this.dao
				.saveAll(BasdataServiceUtil.beforeSaveAll(models));
	}

	public void remove(BasWarehouseTypeModel model) {
		this.dao.remove(model);
	}

	public void removeAll(Collection<BasWarehouseTypeModel> models) {
		this.dao.removeAll(models);
	}

	public void removeByPk(String id) {
		this.dao.removeByPk(BasWarehouseTypeModel.class, id);
	}

	public void removeAllByPk(Collection<String> ids) {
		this.dao.removeAllByPk(BasWarehouseTypeModel.class, ids);
	}

}
