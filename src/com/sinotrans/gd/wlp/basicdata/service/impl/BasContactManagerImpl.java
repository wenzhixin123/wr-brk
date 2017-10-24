package com.sinotrans.gd.wlp.basicdata.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Service;

import com.sinotrans.framework.core.service.impl.BaseManagerImpl;
import com.sinotrans.gd.wlp.basicdata.model.BasContactModel;
import com.sinotrans.gd.wlp.basicdata.service.BasContactManager;
import com.sinotrans.gd.wlp.basicdata.util.BasdataServiceUtil;
import com.sinotrans.gd.wlp.common.web.SinotransPageJson;
import com.sinotrans.gd.wlp.util.CommonUtil;

@Service
public class BasContactManagerImpl extends BaseManagerImpl implements
		BasContactManager {

	public BasContactModel get(String id) {
		return this.dao.get(BasContactModel.class, id);
	}

	public List<BasContactModel> getAll() {
		return this.dao.getAll(BasContactModel.class);
	}

	public List<BasContactModel> findByExample(BasContactModel example) {
		return this.dao.findByExample(example);
	}

	public BasContactModel save(BasContactModel model) {
		return (BasContactModel) this.dao.save(BasdataServiceUtil
				.beforeSave(model));
	}

	@SuppressWarnings("unchecked")
	public List<BasContactModel> saveAll(Collection<BasContactModel> models) {
		return (List<BasContactModel>) this.dao.saveAll(BasdataServiceUtil
				.beforeSaveAll(models));
	}

	public void remove(BasContactModel model) {
		this.dao.remove(model);
	}

	public void removeAll(Collection<BasContactModel> models) {
		this.dao.removeAll(models);
	}

	public void removeByPk(String id) {
		this.dao.removeByPk(BasContactModel.class, id);
	}

	public void removeAllByPk(Collection<String> ids) {
		this.dao.removeAllByPk(BasContactModel.class, ids);
	}

	/**
	 * 用于在编辑页面验证联系人编码
	 * 
	 * @param userCode
	 * @return 返回 true 为已存在
	 */
	public boolean getYanZhenUserCode(String userCode) {
		BasContactModel user = new BasContactModel();
		user.setContactCode(userCode.trim());
		List<BasContactModel> userList = this.findByExample(user);
		if (userList != null && userList.size() > 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public SinotransPageJson saveBasContact(String jsonResult) throws Exception {
		SinotransPageJson sinotransPageJson = new SinotransPageJson();
		List<BasContactModel> list = new ArrayList<BasContactModel>();
		ObjectMapper om = new ObjectMapper();
		om.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
		BasContactModel[] models = om.readValue(jsonResult,
				BasContactModel[].class);
		for (BasContactModel basContactModel : models) {
			if (CommonUtil.ROW_STATE_ADDED
					.equals(basContactModel.getRowState())) {
				if (getYanZhenUserCode(basContactModel.getContactCode())) {
					sinotransPageJson.setError("该编码已存在、请审核后重新填写！");
					sinotransPageJson.setResult(false);
					return sinotransPageJson;
				}
			}
			list.add(basContactModel);
		}
		this.saveAll(list);
		sinotransPageJson.setResult(true);
		sinotransPageJson.setMsg("保存成功!");
		return sinotransPageJson;
	}

}
