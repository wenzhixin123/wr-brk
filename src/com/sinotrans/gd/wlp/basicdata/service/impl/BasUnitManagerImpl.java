package com.sinotrans.gd.wlp.basicdata.service.impl;

import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Service;

import com.sinotrans.framework.core.service.impl.BaseManagerImpl;
import com.sinotrans.gd.wlp.basicdata.model.BasUnitModel;
import com.sinotrans.gd.wlp.basicdata.service.BasUnitManager;
import com.sinotrans.gd.wlp.basicdata.util.BasdataServiceUtil;
import com.sinotrans.gd.wlp.common.web.RcUtil;
import com.sinotrans.gd.wlp.util.CommonUtil;

@Service
public class BasUnitManagerImpl extends BaseManagerImpl implements
		BasUnitManager {

	public BasUnitModel get(String id) {
		return this.dao.get(BasUnitModel.class, id);
	}

	public List<BasUnitModel> getAll() {
		return this.dao.getAll(BasUnitModel.class);
	}

	public List<BasUnitModel> findByExample(BasUnitModel example) {
		return this.dao.findByExample(example);
	}

	public BasUnitModel save(BasUnitModel model) {
		return (BasUnitModel) this.dao.save(BasdataServiceUtil
				.beforeSave(model));
	}

	@SuppressWarnings("unchecked")
	public List<BasUnitModel> saveAll(Collection<BasUnitModel> models) {
		return (List<BasUnitModel>) this.dao.saveAll(BasdataServiceUtil
				.beforeSaveAll(models));
	}

	public void remove(BasUnitModel model) {
		this.dao.remove(model);
	}

	public void removeAll(Collection<BasUnitModel> models) {
		this.dao.removeAll(models);
	}

	public void removeByPk(String id) {
		this.dao.removeByPk(BasUnitModel.class, id);
	}

	public void removeAllByPk(Collection<String> ids) {
		this.dao.removeAllByPk(BasUnitModel.class, ids);
	}
	
	/*
	 * 根据UnitCode进行查询
	 */
	@Override
	public BasUnitModel queryModel(String unitCode){
		BasUnitModel bm = new BasUnitModel();
		bm.setUnitCode(unitCode);
		List<BasUnitModel> bmList = this.dao.findByExample(bm);
		if(!RcUtil.isEmpty(bmList) && bmList.size() > 0){
			bm = bmList.get(0);
			return bm;
		}else{
			return null;
		}
		
	}

	@Override
	public BasUnitModel queryModelByCusUnitCode(String customsUnitCode) {
		BasUnitModel bm = new BasUnitModel();
		//bm.setCustomsUnitCode(customsUnitCode);
		bm.setStatus(CommonUtil.Active);
		List<BasUnitModel> bmList = this.dao.findByExample(bm);
		if(!RcUtil.isEmpty(bmList) && bmList.size() > 0){
			bm = bmList.get(0);
			return bm;
		}else{
			return null;
		}
	}
}
