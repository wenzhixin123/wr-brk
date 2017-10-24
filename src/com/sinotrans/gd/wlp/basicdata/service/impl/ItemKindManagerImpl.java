package com.sinotrans.gd.wlp.basicdata.service.impl;

import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Service;

import com.sinotrans.framework.core.service.impl.BaseManagerImpl;
import com.sinotrans.gd.wlp.basicdata.model.ItemKindModel;
import com.sinotrans.gd.wlp.basicdata.service.ItemKindManager;

@Service
public class ItemKindManagerImpl extends BaseManagerImpl implements ItemKindManager {

	public ItemKindModel get(String id) {
		return this.dao.get(ItemKindModel.class, id);
	}

	public List<ItemKindModel> getAll() {
		return this.dao.getAll(ItemKindModel.class);
	}

	public List<ItemKindModel> findByExample(ItemKindModel example) {
		return this.dao.findByExample(example);
	}

	public ItemKindModel save(ItemKindModel model) {
		return this.dao.save(model);
	}

	public List<ItemKindModel> saveAll(Collection<ItemKindModel> models) {
		return this.dao.saveAll(models);
	}

	public void remove(ItemKindModel model) {
		this.dao.remove(model);
	}

	public void removeAll(Collection<ItemKindModel> models) {
		this.dao.removeAll(models);
	}

	public void removeByPk(String id) {
		this.dao.removeByPk(ItemKindModel.class, id);
	}

	public void removeAllByPk(Collection<String> ids) {
		this.dao.removeAllByPk(ItemKindModel.class, ids);
	}

}
