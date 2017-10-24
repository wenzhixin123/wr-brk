package com.sinotrans.gd.wlp.basicdata.service.impl;

import java.util.Collection;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.sinotrans.framework.core.service.impl.BaseManagerImpl;
import com.sinotrans.gd.wlp.basicdata.model.BasCtrTypeModel;
import com.sinotrans.gd.wlp.basicdata.service.BasCtrTypeManager;
import com.sinotrans.gd.wlp.basicdata.util.BasdataServiceUtil;
import com.sinotrans.gd.wlp.util.CommonUtil;

@Service
public class BasCtrTypeManagerImpl extends BaseManagerImpl implements
		BasCtrTypeManager {

	public BasCtrTypeModel get(String id) {
		return this.dao.get(BasCtrTypeModel.class, id);
	}

	public List<BasCtrTypeModel> getAll() {
		return this.dao.getAll(BasCtrTypeModel.class);
	}

	public List<BasCtrTypeModel> findByExample(BasCtrTypeModel example) {
		return this.dao.findByExample(example);
	}

	public BasCtrTypeModel save(BasCtrTypeModel model) {
		BasCtrTypeModel ctrTypeModel = (BasCtrTypeModel) this.dao.save(BasdataServiceUtil
				.beforeSave(model));
		this.refreshCtrTypeList();
		return ctrTypeModel;
	}

	@SuppressWarnings("unchecked")
	public List<BasCtrTypeModel> saveAll(Collection<BasCtrTypeModel> models) {
		List<BasCtrTypeModel> ctrTypeList = (List<BasCtrTypeModel>) this.dao.saveAll(BasdataServiceUtil
				.beforeSaveAll(models));
		this.refreshCtrTypeList();
		return ctrTypeList;
	}

	public void remove(BasCtrTypeModel model) {
		this.dao.remove(model);
		this.refreshCtrTypeList();
	}

	public void removeAll(Collection<BasCtrTypeModel> models) {
		this.dao.removeAll(models);
		this.refreshCtrTypeList();
	}

	public void removeByPk(String id) {
		this.dao.removeByPk(BasCtrTypeModel.class, id);
		this.refreshCtrTypeList();
	}

	public void removeAllByPk(Collection<String> ids) {
		this.dao.removeAllByPk(BasCtrTypeModel.class, ids);
		this.refreshCtrTypeList();
	}
	
	private void refreshCtrTypeList(){
		/*StartOnloadService.containerTypeList.clear();
		BasCtrTypeModel basCtrTypeModel = new BasCtrTypeModel();
		basCtrTypeModel.setStatus(CommonUtil.Active);
		List<BasCtrTypeModel> ctrTypeList = this.findByExample(basCtrTypeModel);
		for(BasCtrTypeModel ctrTypeModel:ctrTypeList){
			if(StringUtils.isNotBlank(ctrTypeModel.getTypeCode())){
				StartOnloadService.containerTypeList.add(ctrTypeModel.getTypeCode());
			}
		}*/
	}
	
}
