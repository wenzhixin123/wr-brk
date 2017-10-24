package com.sinotrans.gd.wlp.basicdata.service;

import java.util.Collection;
import java.util.List;

import com.sinotrans.framework.core.service.BaseManager;
import com.sinotrans.gd.wlp.basicdata.entity.BasCustomerEntity;
import com.sinotrans.gd.wlp.basicdata.model.BasCustomerModel;
import com.sinotrans.gd.wlp.basicdata.model.ItemMasterModel;
import com.sinotrans.gd.wlp.common.entity.BasOption;
import com.sinotrans.gd.wlp.common.web.SinotransPageJson;

public interface ItemMasterManager extends BaseManager {

	ItemMasterModel get(String id);

	List<ItemMasterModel> getAll();
	

	List<ItemMasterModel> findByExample(ItemMasterModel example);

	ItemMasterModel save(ItemMasterModel model);

	List<ItemMasterModel> saveAll(Collection<ItemMasterModel> models);

	void remove(ItemMasterModel model);

	void removeAll(Collection<ItemMasterModel> models);

	void removeByPk(String id);

	void removeAllByPk(Collection<String> ids);


}
