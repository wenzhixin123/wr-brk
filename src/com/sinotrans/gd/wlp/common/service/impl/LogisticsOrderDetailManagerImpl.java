package com.sinotrans.gd.wlp.common.service.impl;

import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Service;

import com.sinotrans.framework.core.service.impl.BaseManagerImpl;
import com.sinotrans.gd.wlp.common.model.LogisticsOrderDetailModel;
import com.sinotrans.gd.wlp.common.service.LogisticsOrderDetailManager;

@Service
public class LogisticsOrderDetailManagerImpl extends BaseManagerImpl implements
		LogisticsOrderDetailManager {

	public LogisticsOrderDetailModel get(String id) {
			return this.dao.get(LogisticsOrderDetailModel.class, id);
		}


	public List<LogisticsOrderDetailModel> getAll() {
		return this.dao.getAll(LogisticsOrderDetailModel.class);
	}

	public List<LogisticsOrderDetailModel> findByExample(LogisticsOrderDetailModel example) {
		return this.dao.findByExample(example);
	}

	public LogisticsOrderDetailModel save(LogisticsOrderDetailModel model) {
		return this.dao.save(model);
	}

	public List<LogisticsOrderDetailModel> saveAll(
			Collection<LogisticsOrderDetailModel> models) {
		return this.dao.saveAll(models);
	}

	public void remove(LogisticsOrderDetailModel model) {
		this.dao.remove(model);
	}

	public void removeAll(Collection<LogisticsOrderDetailModel> models) {
		this.dao.removeAll(models);
	}

	public void removeByPk(String id) {
		this.dao.removeByPk(LogisticsOrderDetailModel.class, id);
	}

	public void removeAllByPk(Collection<String> ids) {
		this.dao.removeAllByPk(LogisticsOrderDetailModel.class, ids);
	}

}