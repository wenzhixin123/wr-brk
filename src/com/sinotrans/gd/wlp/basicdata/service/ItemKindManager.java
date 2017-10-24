package com.sinotrans.gd.wlp.basicdata.service;

import java.util.Collection;
import java.util.List;

import com.sinotrans.framework.core.service.BaseManager;
import com.sinotrans.gd.wlp.basicdata.model.ItemKindModel;

public interface ItemKindManager extends BaseManager {

	ItemKindModel get(String id);

	List<ItemKindModel> getAll();

	List<ItemKindModel> findByExample(ItemKindModel example);

	ItemKindModel save(ItemKindModel model);

	List<ItemKindModel> saveAll(Collection<ItemKindModel> models);

	void remove(ItemKindModel model);

	void removeAll(Collection<ItemKindModel> models);

	void removeByPk(String id);

	void removeAllByPk(Collection<String> ids);

}
