package com.sinotrans.gd.wlp.basicdata.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Service;

import com.sinotrans.framework.core.service.impl.BaseManagerImpl;
import com.sinotrans.gd.wlp.basicdata.model.BasBargainModel;
import com.sinotrans.gd.wlp.basicdata.service.BasBargainManager;
import com.sinotrans.gd.wlp.basicdata.util.BasdataServiceUtil;
import com.sinotrans.gd.wlp.common.web.SinotransPageJson;
import com.sinotrans.gd.wlp.system.entity.OfficeTree;
import com.sinotrans.gd.wlp.util.CommonUtil;

@Service
public class BasBargainManagerImpl extends BaseManagerImpl implements
		BasBargainManager {

	public BasBargainModel get(String id) {
		return this.dao.get(BasBargainModel.class, id);
	}

	public List<BasBargainModel> getAll() {
		return this.dao.getAll(BasBargainModel.class);
	}

	public List<BasBargainModel> findByExample(BasBargainModel example) {
		return this.dao.findByExample(example);
	}

	public BasBargainModel save(BasBargainModel model) {
		return this.dao.save(model);
	}

	public List<BasBargainModel> saveAll(Collection<BasBargainModel> models) {
		return this.dao.saveAll(models);
	}

	public void remove(BasBargainModel model) {
		this.dao.remove(model);
	}

	public void removeAll(Collection<BasBargainModel> models) {
		this.dao.removeAll(models);
	}

	public void removeByPk(String id) {
		this.dao.removeByPk(BasBargainModel.class, id);
	}

	public void removeAllByPk(Collection<String> ids) {
		this.dao.removeAllByPk(BasBargainModel.class, ids);
	}

	// @Override
	// public List<BasBargainModel> saveAllBasBargain(
	// Collection<BasBargainModel> models) {
	// return null;
	// }

	@SuppressWarnings("unchecked")
	public List<BasBargainModel> saveAllBasBargain(
			Collection<BasBargainModel> models) {
		return (List<BasBargainModel>) this.dao.saveAll(BasdataServiceUtil
				.beforeSaveAll(models));
	}

	@Override
	public List<OfficeTree> getBasBargaintree(String jsonResult) {
		BasBargainModel BasBar = new BasBargainModel();
		BasBar.setCustomerCode(jsonResult);

		List<BasBargainModel> allOffice = this.findByExample(BasBar);
		List<OfficeTree> resultList = new ArrayList<OfficeTree>();
		for (BasBargainModel allOM : allOffice) {
			OfficeTree ot = new OfficeTree();
			String officeId = allOM.getBasBargainUuid();// 合同UUid
			ot.setId(officeId);// 标识加入是不根节点
			ot.setText(allOM.getBargainName());// 加入名字
			resultList.add(ot);
		}
		return resultList;
	}

	/**
	 * 用于判断合同号的唯一性
	 * 
	 * @param userCode
	 * @return 返回 true 为已存在
	 */
	public boolean getcontractOne(String contractone) {
		BasBargainModel user = new BasBargainModel();
		user.setBargainCode(contractone);
		List<BasBargainModel> userList = this.findByExample(user);
		if (userList.size() > 0) {
			return true;
		} else {
			return false;
		}
	}

	/*
	 * 根据合同号查询出UUid (non-Javadoc)
	 * 
	 * @see
	 * com.sinotrans.gd.wlp.basicdata.service.BasBargainManager#selectbargainCode
	 * (java.lang.String)
	 */
	@Override
	public String selectbargainCode(String contranctone) {
		BasBargainModel user = new BasBargainModel();
		user.setBargainCode(contranctone);
		List<BasBargainModel> userList = this.findByExample(user);
		String basBargainUuid = userList.get(0).getBasBargainUuid();
		return basBargainUuid;
	}

	@Override
	public SinotransPageJson saveBasBargain(String jsonResult) throws Exception {
		SinotransPageJson sinotransPageJson = new SinotransPageJson();
		List<BasBargainModel> list = new ArrayList<BasBargainModel>();
		ObjectMapper om = new ObjectMapper();
		om.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
		BasBargainModel[] models = om.readValue(jsonResult,
				BasBargainModel[].class);
		for (BasBargainModel basBargainModel : models) {
			if (CommonUtil.ROW_STATE_ADDED
					.equals(basBargainModel.getRowState())) {
				if (getcontractOne(basBargainModel.getBargainCode())) {
					sinotransPageJson.setError("该合同号已存在、请审核后重新填写！");
					sinotransPageJson.setResult(false);
					return sinotransPageJson;
				}
			}
			list.add(basBargainModel);
		}
		this.saveAll(list);
		sinotransPageJson.setResult(true);
		sinotransPageJson.setMsg("保存成功!");
		return sinotransPageJson;
	}

}
