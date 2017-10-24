package com.sinotrans.gd.wlp.basicdata.service.impl;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sinotrans.framework.core.dao.NativeSqlDao;
import com.sinotrans.framework.core.service.impl.BaseManagerImpl;
import com.sinotrans.gd.wlp.basicdata.model.BasBomDetailModel;
import com.sinotrans.gd.wlp.basicdata.service.BasBomDetailManager;
import com.sinotrans.gd.wlp.basicdata.util.BasdataServiceUtil;

@Service
public class BasBomDetailManagerImpl extends BaseManagerImpl implements
		BasBomDetailManager {
	@Autowired
	private NativeSqlDao nativeSqlDao;

	public BasBomDetailModel get(String id) {
		return this.dao.get(BasBomDetailModel.class, id);
	}

	public List<BasBomDetailModel> getAll() {
		return this.dao.getAll(BasBomDetailModel.class);
	}

	public List<BasBomDetailModel> findByExample(BasBomDetailModel example) {
		return this.dao.findByExample(example);
	}

	public BasBomDetailModel save(BasBomDetailModel model) {
		return (BasBomDetailModel) this.dao.save(BasdataServiceUtil
				.beforeSave(model));
	}

	@SuppressWarnings("unchecked")
	public List<BasBomDetailModel> saveAll(Collection<BasBomDetailModel> models) {
		return (List<BasBomDetailModel>) this.dao.saveAll(BasdataServiceUtil
				.beforeSaveAll(models));
	}

	public void remove(BasBomDetailModel model) {
		this.dao.remove(model);
	}

	public void removeAll(Collection<BasBomDetailModel> models) {
		this.dao.removeAll(models);
	}

	public void removeByPk(String id) {
		this.dao.removeByPk(BasBomDetailModel.class, id);
	}

	public void removeAllByPk(Collection<String> ids) {
		this.dao.removeAllByPk(BasBomDetailModel.class, ids);
	}

	/**
	 * 根据外键删除所有
	 */
	@Override
	public void removeAllByFk(String uuid) {
		nativeSqlDao
				.executeDDL("delete from bas_bom_detail bbd where bbd.bas_bom_uuid='"
						+ uuid + "'");
	}
}
