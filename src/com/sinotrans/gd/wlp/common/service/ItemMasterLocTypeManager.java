package com.sinotrans.gd.wlp.common.service;

import java.util.Collection;
import java.util.List;

import com.sinotrans.framework.core.service.BaseManager;
import com.sinotrans.gd.wlp.common.model.BarcodeModel;
import com.sinotrans.gd.wlp.common.model.ItemMasterLocTypeModel;

public interface ItemMasterLocTypeManager extends BaseManager {



	ItemMasterLocTypeModel get(String id);

	ItemMasterLocTypeModel save(ItemMasterLocTypeModel model);

	List<ItemMasterLocTypeModel> saveAll(Collection<ItemMasterLocTypeModel> models);

	void remove(ItemMasterLocTypeModel model);

	void removeAll(Collection<ItemMasterLocTypeModel> models);

	void removeByPk(String id);

	void removeAllByPk(Collection<String> ids);

	
}
