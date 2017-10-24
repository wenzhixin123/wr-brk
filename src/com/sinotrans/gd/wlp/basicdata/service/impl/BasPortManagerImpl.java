package com.sinotrans.gd.wlp.basicdata.service.impl;

import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Service;

import com.sinotrans.framework.core.service.impl.BaseManagerImpl;
import com.sinotrans.gd.wlp.basicdata.model.BasPortModel;
import com.sinotrans.gd.wlp.basicdata.service.BasPortManager;

@Service
public class BasPortManagerImpl extends BaseManagerImpl implements BasPortManager {

	public BasPortModel get(String id) {
		return this.dao.get(BasPortModel.class, id);
	}

	public List<BasPortModel> getAll() {
		return this.dao.getAll(BasPortModel.class);
	}

	public List<BasPortModel> findByExample(BasPortModel example) {
		return this.dao.findByExample(example);
	}

	public BasPortModel save(BasPortModel model) {
		return this.dao.save(model);
	}

	public List<BasPortModel> saveAll(Collection<BasPortModel> models) {
		return this.dao.saveAll(models);
	}

	public void remove(BasPortModel model) {
		this.dao.remove(model);
	}

	public void removeAll(Collection<BasPortModel> models) {
		this.dao.removeAll(models);
	}

	public void removeByPk(String id) {
		this.dao.removeByPk(BasPortModel.class, id);
	}

	public void removeAllByPk(Collection<String> ids) {
		this.dao.removeAllByPk(BasPortModel.class, ids);
	}
	
	public BasPortModel queryPortByPortName(String portName){
		BasPortModel model = new BasPortModel();
		model.setPortName(portName);
		List<BasPortModel> list = this.findByExample(model);
		if(list != null && list.size() >0){
			return list.get(0);
		}else{
			return null;
		}
	}

	public BasPortModel queryPortByPortCode(String portCode){
		BasPortModel model = new BasPortModel();
		model.setPortCode(portCode);
		List<BasPortModel> list = this.findByExample(model);
		if(list != null && list.size() >0){
			return list.get(0);
		}else{
			return null;
		}
	}
}
