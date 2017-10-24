package com.sinotrans.gd.wlp.basicdata.service.impl;

import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Service;

import com.sinotrans.framework.core.service.impl.BaseManagerImpl;
import com.sinotrans.gd.wlp.basicdata.model.ItemMasterModel;
import com.sinotrans.gd.wlp.basicdata.service.ItemMasterManager;
import com.sinotrans.gd.wlp.basicdata.util.BasdataServiceUtil;

@Service
public class ItemMasterManagerImpl extends BaseManagerImpl implements ItemMasterManager   {
	
	
	public ItemMasterModel get(String id) {
		return this.dao.get(ItemMasterModel.class, id);
	}

	public List<ItemMasterModel> getAll() {
		return this.dao.getAll(ItemMasterModel.class);
	}
	
	private static ItemMasterManagerImpl  singleEntity=null;
	
	ItemMasterManagerImpl(){
		if(singleEntity == null) 
			singleEntity = this;
	}
	
	public static ItemMasterManagerImpl getSingleInstance(){
		 if(singleEntity==null){
			 singleEntity = new ItemMasterManagerImpl();
		 }
		return singleEntity;
	}

	
	
	public List<ItemMasterModel> findByExample(ItemMasterModel example) {
		return this.dao.findByExample(example);
	}

	public ItemMasterModel save(ItemMasterModel model) {
		return (ItemMasterModel) this.dao.save(BasdataServiceUtil
				.beforeSave(model));
	}

	@SuppressWarnings("unchecked")
	public List<ItemMasterModel> saveAll(Collection<ItemMasterModel> models) {
		return (List<ItemMasterModel>) this.dao.saveAll(BasdataServiceUtil
				.beforeSaveAll(models));
	}

	public void remove(ItemMasterModel model) {
		this.dao.remove(model);
	}

	public void removeAll(Collection<ItemMasterModel> models) {
		this.dao.removeAll(models);
	}

	public void removeByPk(String id) {
		this.dao.removeByPk(ItemMasterModel.class, id);
	}

	public void removeAllByPk(Collection<String> ids) {
		this.dao.removeAllByPk(ItemMasterModel.class, ids);
	}

	
}
