package com.sinotrans.gd.wlp.common.service.impl;

import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Service;

import com.sinotrans.framework.core.service.impl.BaseManagerImpl;
import com.sinotrans.gd.wlp.common.model.ItemMasterLocTypeModel;
import com.sinotrans.gd.wlp.common.service.ItemMasterLocTypeManager;

@Service
public class ItemMasterLocTypeManagerImpl extends BaseManagerImpl implements
      ItemMasterLocTypeManager {

	
	
	public ItemMasterLocTypeModel get(String id) {
		return this.dao.get(ItemMasterLocTypeModel.class, id);
	}

	public ItemMasterLocTypeModel save(ItemMasterLocTypeModel model) {
		return this.dao.save(model);
	}

	

	public List<ItemMasterLocTypeModel> saveAll(Collection<ItemMasterLocTypeModel> models) {
		return this.dao.saveAll(models);
	}

	public void remove(ItemMasterLocTypeModel model) {
		this.dao.remove(model);
	}

	public void removeAll(Collection<ItemMasterLocTypeModel> models) {
		this.dao.removeAll(models);
	}

	public void removeByPk(String id) {
		this.dao.removeByPk(ItemMasterLocTypeModel.class, id);
	}

	public void removeAllByPk(Collection<String> ids) {
		this.dao.removeAllByPk(ItemMasterLocTypeModel.class, ids);
	}

	
	
	
}