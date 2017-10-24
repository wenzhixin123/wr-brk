package com.sinotrans.gd.wlp.basicdata.service;

import java.util.Collection;
import java.util.List;

import com.sinotrans.framework.core.service.BaseManager;
import com.sinotrans.gd.wlp.basicdata.model.ItemKindModel;
import com.sinotrans.gd.wlp.basicdata.model.ItemNatureModel;

public interface ItemNatureManager extends BaseManager {

	ItemNatureModel get(String id);

	List<ItemNatureModel> getAll();

	List<ItemNatureModel> findByExample(ItemNatureModel example);

	ItemNatureModel save(ItemNatureModel model);

	List<ItemNatureModel> saveAll(Collection<ItemNatureModel> models);

	void remove(ItemNatureModel model);

	void removeAll(Collection<ItemNatureModel> models);

	void removeByPk(String id);

	void removeAllByPk(Collection<String> ids);

}
