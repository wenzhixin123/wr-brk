package com.sinotrans.gd.wlp.basicdata.service.impl;

import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Service;

import com.sinotrans.framework.core.service.impl.BaseManagerImpl;
import com.sinotrans.gd.wlp.basicdata.model.ItemNatureModel;
import com.sinotrans.gd.wlp.basicdata.service.ItemNatureManager;

@Service
public class ItemNatureManagerImpl extends BaseManagerImpl implements ItemNatureManager {

	public ItemNatureModel get(String id) {
		return this.dao.get(ItemNatureModel.class, id);
	}

	public List<ItemNatureModel> getAll() {
		return this.dao.getAll(ItemNatureModel.class);
	}

	public List<ItemNatureModel> findByExample(ItemNatureModel example) {
		return this.dao.findByExample(example);
	}

	public ItemNatureModel save(ItemNatureModel model) {
		return this.dao.save(model);
	}

	public List<ItemNatureModel> saveAll(Collection<ItemNatureModel> models) {
		return this.dao.saveAll(models);
	}

	public void remove(ItemNatureModel model) {
		this.dao.remove(model);
	}

	public void removeAll(Collection<ItemNatureModel> models) {
		this.dao.removeAll(models);
	}

	public void removeByPk(String id) {
		this.dao.removeByPk(ItemNatureModel.class, id);
	}

	public void removeAllByPk(Collection<String> ids) {
		this.dao.removeAllByPk(ItemNatureModel.class, ids);
	}

}
