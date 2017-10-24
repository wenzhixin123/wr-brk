package com.sinotrans.gd.wlp.basicdata.service.impl;

import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Service;

import com.sinotrans.framework.core.service.impl.BaseManagerImpl;
import com.sinotrans.framework.core.support.Condition;
import com.sinotrans.framework.core.support.PagingInfo;
import com.sinotrans.gd.wlp.basicdata.model.BasCodeTypeModel;
import com.sinotrans.gd.wlp.basicdata.service.BasCodeTypeManager;
import com.sinotrans.gd.wlp.basicdata.util.BasdataServiceUtil;
import com.sinotrans.gd.wlp.common.web.SinotransDataGrid;
import com.sinotrans.gd.wlp.util.CommonUtil;

@Service
public class BasCodeTypeManagerImpl extends BaseManagerImpl implements
		BasCodeTypeManager {

	public BasCodeTypeModel get(String id) {
		return this.dao.get(BasCodeTypeModel.class, id);
	}

	public List<BasCodeTypeModel> getAll() {
		return this.dao.getAll(BasCodeTypeModel.class);
	}

	public List<BasCodeTypeModel> findByExample(BasCodeTypeModel example) {
		return this.dao.findByExample(example);
	}

	public BasCodeTypeModel save(BasCodeTypeModel model) {
		return (BasCodeTypeModel) this.dao.save(BasdataServiceUtil
				.beforeSave(model));
	}

	@SuppressWarnings("unchecked")
	public List<BasCodeTypeModel> saveAll(Collection<BasCodeTypeModel> models) {
		return (List<BasCodeTypeModel>) this.dao.saveAll(BasdataServiceUtil
				.beforeSaveAll(models));
	}

	public void remove(BasCodeTypeModel model) {
		this.dao.remove(model);
	}

	public void removeAll(Collection<BasCodeTypeModel> models) {
		this.dao.removeAll(models);
	}

	public void removeByPk(String id) {
		this.dao.removeByPk(BasCodeTypeModel.class, id);
	}

	public void removeAllByPk(Collection<String> ids) {
		this.dao.removeAllByPk(BasCodeTypeModel.class, ids);
	}

	@Override
	public BasCodeTypeModel findObject(String codeType, String officeCode) {
		List<BasCodeTypeModel> bctmList = this.dao.createCommonQuery(
				BasCodeTypeModel.class).addCondition(
				Condition.eq("typeCode", codeType)).addCondition(
				Condition.eq("officeCode", officeCode)).query();
		if (bctmList != null && bctmList.size() > 0) {
			return bctmList.get(0);
		} else {
			BasCodeTypeModel bctm = new BasCodeTypeModel();
			bctm.setOfficeCode(officeCode);
			if (CommonUtil.OPTION_SYSTEM_CODE_RES_LIST.equals(codeType)) {
				bctm.setTypeName("预约人名单记录");
			} else {
				bctm.setTypeName("");
			}
			bctm.setStatus(CommonUtil.Active);
			bctm.setTypeCode(codeType);
			bctm = this.save(bctm);
			return bctm;
		}
	}

	//查询出控制字段第一位带"U"的codeType
	@Override
	public SinotransDataGrid codeTypeSpecial(PagingInfo pagingInfo,
			String officeCode) {
		List<BasCodeTypeModel> bctmList = this.dao.createCommonQuery(
				BasCodeTypeModel.class).addCondition(Condition.eq("substr(controlWord,0,1)", "U"))
				.addCondition(Condition.eq("officeCode", officeCode))
				.addCondition(Condition.eq("status", CommonUtil.Active))
				.setPagingInfo(pagingInfo).query();
		SinotransDataGrid sdg = new SinotransDataGrid(bctmList, pagingInfo
				.getTotalRows()
				, pagingInfo.getCurrentPage());
		return sdg;
	}
}
