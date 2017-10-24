package com.sinotrans.gd.wlp.basicdata.service.impl;

import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Service;

import com.sinotrans.framework.core.service.impl.BaseManagerImpl;
import com.sinotrans.framework.core.support.Condition;
import com.sinotrans.gd.wlp.basicdata.model.BasDangerModel;
import com.sinotrans.gd.wlp.basicdata.service.BasDangerManager;

@Service
public class BasDangerManagerImpl extends BaseManagerImpl implements BasDangerManager {

	public BasDangerModel get(String id) {
		return this.dao.get(BasDangerModel.class, id);
	}

	public List<BasDangerModel> getAll() {
		return this.dao.getAll(BasDangerModel.class);
	}

	public List<BasDangerModel> findByExample(BasDangerModel example) {
		return this.dao.findByExample(example);
	}

	public BasDangerModel save(BasDangerModel model) {
		return this.dao.save(model);
	}

	public List<BasDangerModel> saveAll(Collection<BasDangerModel> models) {
		return this.dao.saveAll(models);
	}

	public void remove(BasDangerModel model) {
		this.dao.remove(model);
	}

	public void removeAll(Collection<BasDangerModel> models) {
		this.dao.removeAll(models);
	}

	public void removeByPk(String id) {
		this.dao.removeByPk(BasDangerModel.class, id);
	}

	public void removeAllByPk(Collection<String> ids) {
		this.dao.removeAllByPk(BasDangerModel.class, ids);
	}

	@Override
	public String getUncodeByUnnameLikeAnywhere(String unName) {
		BasDangerModel model = new BasDangerModel();
		model.setUnName(unName);
		List<BasDangerModel> list = findByExample(model);
		if(list.size() > 0) {
			return list.get(0).getUnCode();
		}
		return "";
	}

	@Override
	public String getDangerModByUnName(String unName) {
		List<BasDangerModel> dangerModels = this.dao.createCommonQuery(BasDangerModel.class)
				.addCondition(Condition.or(Condition.eq(BasDangerModel.FieldNames.unName, unName),
											Condition.eq(BasDangerModel.FieldNames.unNameEn, unName)))
											.query();
		if(dangerModels.size() > 0) {
			return dangerModels.get(0).getUnCode();
		}
		return "";
	}
}
