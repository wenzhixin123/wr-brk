package com.sinotrans.gd.wlp.basicdata.service.impl;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sinotrans.framework.core.exception.ApplicationException;
import com.sinotrans.framework.core.service.impl.BaseManagerImpl;
import com.sinotrans.framework.core.support.Condition;
import com.sinotrans.framework.core.util.JSONDataUtils;
import com.sinotrans.gd.wlp.basicdata.entity.BasLocAreaEntity;
import com.sinotrans.gd.wlp.basicdata.model.BasLocAreaModel;
import com.sinotrans.gd.wlp.basicdata.model.BasLotStockModel;
import com.sinotrans.gd.wlp.basicdata.service.BasLocAreaManager;
import com.sinotrans.gd.wlp.basicdata.service.BasLotStockManager;
import com.sinotrans.gd.wlp.basicdata.util.BasdataServiceUtil;
import com.sinotrans.gd.wlp.common.web.RcUtil;
import com.sinotrans.gd.wlp.common.web.SinotransPageJson;
import com.sinotrans.gd.wlp.util.CommonUtil;

@Service
public class BasLocAreaManagerImpl extends BaseManagerImpl implements
		BasLocAreaManager {
	@Autowired
	public BasLotStockManager basLotStockManager;

/*	@Autowired
	public LocItemRefManager locItemRefManager;*/

	public BasLocAreaModel get(String id) {
		return this.dao.get(BasLocAreaModel.class, id);
	}

	public List<BasLocAreaModel> getAll() {
		return this.dao.getAll(BasLocAreaModel.class);
	}

	public List<BasLocAreaModel> findByExample(BasLocAreaModel example) {
		return this.dao.findByExample(example);
	}

	public BasLocAreaModel save(BasLocAreaModel model) {
		return (BasLocAreaModel) this.dao.save(BasdataServiceUtil
				.beforeSave(model));
	}

	@SuppressWarnings("unchecked")
	public List<BasLocAreaModel> saveAll(Collection<BasLocAreaModel> models) {
		return (List<BasLocAreaModel>) this.dao.saveAll(BasdataServiceUtil
				.beforeSaveAll(models));
	}

	public void remove(BasLocAreaModel model) {
		this.dao.remove(model);
	}

	public void removeAll(Collection<BasLocAreaModel> models) {
		this.dao.removeAll(models);
	}

	public void removeByPk(String id) {
		this.dao.removeByPk(BasLocAreaModel.class, id);
	}

	public void removeAllByPk(Collection<String> ids) {
		this.dao.removeAllByPk(BasLocAreaModel.class, ids);
	}

	@Override
	public String getJsonSaveData(String jsonResult) {
		ObjectMapper om = new ObjectMapper();
		BasLocAreaModel[] models = null;
		// String officeCode = SessionContextUserEntity.currentUser()
		// .getOfficeCode();
		try {
			models = om.readValue(jsonResult, BasLocAreaModel[].class);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (!Arrays.asList(models).get(0).getRowState().equals(
				CommonUtil.ROW_STATE_ADDED)) {
			for (BasLocAreaModel basLocAreaModel2 : models) {
				basLocAreaModel2.setRecVer(get(
						basLocAreaModel2.getBasLocAreaUuid()).getRecVer());
			}
		}
		List<BasLocAreaModel> saveModelList = this.saveAll(Arrays
				.asList(models));
		String json = null;
		BasLotStockModel blsmodel = new BasLotStockModel();
		Map<String, List<BasLocAreaModel>> modelMap = new HashMap<String, List<BasLocAreaModel>>();
		for (BasLocAreaModel basLocAreaModel : saveModelList) {
			String controlWord = basLocAreaModel.getControlWord().substring(0,
					1);
			basLocAreaModel.setControlWord(controlWord);
			blsmodel.setBasLocAreaUuid(basLocAreaModel.getBasLocAreaUuid());
			List<BasLotStockModel> blsList = basLotStockManager
					.findByExample(blsmodel);
			if (blsList != null && blsList.size() > 0) {
				for (BasLotStockModel basLotStockModel : blsList) {
					String cWord = controlWord
							+ basLotStockModel.getControlWord().substring(1);
					basLotStockModel.setControlWord(cWord);
					basLotStockModel.setRowState(CommonUtil.ROW_STATE_MODIFIED);
				}
				basLotStockManager.saveAll(blsList);
			}
		}
		modelMap.put("BasLocAreaModelList", saveModelList);

		try {
			json = JSONDataUtils.buildJSONValue(modelMap).toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return json;
	}

	@Override
	public String removeLocAreaByPk(String id) {
		BasLotStockModel model = new BasLotStockModel();
		model.setBasLocAreaUuid(id);
		List<BasLotStockModel> modelList = this.dao.findByExample(model);
		if (modelList != null && modelList.size() > 0) {
			return "有子记录的仓库区域不能删除!";
		}
		return "SUCCESS";
	}
/*
	@Override
	public String removeLocAreaByLI(String id) {
		LocItemRefModel model = new LocItemRefModel();
		model.setBasLocAreaUuid(id);
		List<LocItemRefModel> modelList = locItemRefManager
				.findByExample(model);
		if (modelList != null && modelList.size() > 0) {
			return "此仓库区域在其他地方用到，不能被删除!";
		}
		return "SUCCESS";
	}*/

	/**
	 * 验证区域的唯一性
	 * 
	 */
	@Override
	public boolean validatelocArea(String locAreaCode, String officeCode) {
		BasLocAreaModel basLocAreaModel = new BasLocAreaModel();
		basLocAreaModel.setLocAreaCode(locAreaCode);
		basLocAreaModel.setOfficeCode(officeCode);
		List<BasLocAreaModel> blsList = this.findByExample(basLocAreaModel);
		if (!RcUtil.isEmpty(blsList) && blsList.size() > 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public List<BasLocAreaModel> findByR(String officeCode) {
		BasLocAreaModel blam = new BasLocAreaModel();
		blam.setControlWord("R0000000000000000000");
		List<BasLocAreaModel> blamList = findByExample(blam);
		if (blamList != null && blamList.size() > 0) {
			blam = blamList.get(0);
		} else {
			throw new ApplicationException("请添加收货区域!");
		}
		return blamList;
	}

	@Override
	public SinotransPageJson saveBasLocArea(BasLocAreaEntity model) {
		SinotransPageJson spj = new SinotransPageJson();
		if(RcUtil.isEmpty(model.getBasWarehouseUuid())){
			throw new ApplicationException("仓库UUID为空！");
		}
		if(RcUtil.isEmpty(model.getLocAreaCode())){
			throw new ApplicationException("仓库代码为空！");
		}
		if(RcUtil.isEmpty(model.getLocAreaName())){
			throw new ApplicationException("仓库名称为空！");
		}
		if(RcUtil.isEmpty(model.getOfficeCode())){
			throw new ApplicationException("OfficeCod为空！");
		}
		if(RcUtil.isEmpty(model.getStatus())){
			model.setStatus(CommonUtil.Active);
		}
		if(RcUtil.isEmpty(model.getControlWord())){
			model.setControlWord(CommonUtil.Default_Control_Word);
		}
		List<BasLotStockModel> blsList=this.dao.createCommonQuery(BasLotStockModel.class)
				.addCondition(Condition.eq(BasLotStockModel.FieldNames.status, CommonUtil.Active))
				.addCondition(Condition.eq(BasLotStockModel.FieldNames.basLocAreaUuid, model.getBasLocAreaUuid()))
				.query();
		if(CommonUtil.ROW_STATE_DELETED.equals(model.getRowState())&&!RcUtil.isEmpty(blsList)&&blsList.size()>0){
			spj.setError("操作失败，该区域下已存在货位不能执行删除！");
			spj.setResult(false);
			return spj;
		}
		BasLocAreaModel blaModel=this.save(model);
		for (BasLotStockModel blsModel : blsList) {
			blsModel.setControlWord(RcUtil.setKeyBit(2, model.getControlWord().charAt(0)+"", blsModel.getControlWord()));
		}
		this.dao.saveAll(blsList);
		//将此区域下的货位cw2全部修改为对应货区
		spj.setMsg("操作成功!");
		spj.setResult(true);
		spj.setObject(blaModel);
		return spj;
	}
}
	
	

