package com.sinotrans.gd.wlp.basicdata.service.impl;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.beanutils.BeanUtils;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sinotrans.framework.core.exception.ApplicationException;
import com.sinotrans.framework.core.model.BaseModel;
import com.sinotrans.framework.core.model.BaseModelClass;
import com.sinotrans.framework.core.service.impl.BaseManagerImpl;
import com.sinotrans.framework.core.support.CommonQuery;
import com.sinotrans.framework.core.support.Condition;
import com.sinotrans.framework.core.util.JSONDataUtils;
import com.sinotrans.gd.wlp.basicdata.entity.BasLotStockEntity;
import com.sinotrans.gd.wlp.basicdata.model.BasLocAreaModel;
import com.sinotrans.gd.wlp.basicdata.model.BasLotStockModel;
import com.sinotrans.gd.wlp.basicdata.query.BasLotStockQueryItem;
import com.sinotrans.gd.wlp.basicdata.service.BasLocAreaManager;
import com.sinotrans.gd.wlp.basicdata.service.BasLotStockManager;
import com.sinotrans.gd.wlp.basicdata.util.BasdataServiceUtil;
import com.sinotrans.gd.wlp.common.web.RcUtil;
import com.sinotrans.gd.wlp.common.web.SinotransPageJson;
import com.sinotrans.gd.wlp.system.entity.SessionContextUserEntity;
import com.sinotrans.gd.wlp.util.CommonUtil;

@Service
public class BasLotStockManagerImpl extends BaseManagerImpl implements
		BasLotStockManager {

	@Autowired
	public BasLocAreaManager basLocAreaManager;

	public BasLotStockModel get(String id) {
		return this.dao.get(BasLotStockModel.class, id);
	}

	public List<BasLotStockModel> getAll() {
		return this.dao.getAll(BasLotStockModel.class);
	}

	public List<BasLotStockModel> findByExample(BasLotStockModel example) {
		return this.dao.findByExample(example);
	}

	public BasLotStockModel save(BasLotStockModel model) {
		return (BasLotStockModel) this.dao.save(BasdataServiceUtil
				.beforeSave(model));
	}

	@SuppressWarnings("unchecked")
	public List<BasLotStockModel> saveAll(Collection<BasLotStockModel> models) {
		return (List<BasLotStockModel>) this.dao.saveAll(BasdataServiceUtil
				.beforeSaveAll(models));
	}

	public void remove(BasLotStockModel model) {
		this.dao.remove(model);
	}

	public void removeAll(Collection<BasLotStockModel> models) {
		this.dao.removeAll(models);
	}

	public void removeByPk(String id) {
		this.dao.removeByPk(BasLotStockModel.class, id);
	}

	public void removeAllByPk(Collection<String> ids) {
		this.dao.removeAllByPk(BasLotStockModel.class, ids);
	}

	@Override
	public BasLotStockModel getBasLotStockByCondition(String officeCode) {
		List<BasLotStockModel> modelList = this.dao.createCommonQuery(
				BasLotStockModel.class).addCondition(
				Condition.eq("substr(controlWord,1,1)", "R")).addCondition(
				Condition.eq("officeCode", officeCode)).addCondition(
				Condition.eq("rownum", 1)).query();
		BasLotStockModel model = new BasLotStockModel();
		if (modelList.size() > 0) {
			model = modelList.get(0);
		}
		return model;
	}

	@SuppressWarnings("unchecked")
	@Override
	public String getTreeJson(Map<String, String> parameters) {
		Map<String, List<? extends BaseModel>> treeMap = new HashMap<String, List<? extends BaseModel>>();
		String packagePreName = "com.sinotrans.gd.wlp.basicdata.model.";
		String json = null;
		String officeCode = SessionContextUserEntity.currentUser()
				.getOfficeCode();
		try {
			String[] strModelNames = parameters.get("queryTypes").split(",");
			for (String modelName : strModelNames) {
				Class<? extends BaseModel> modelClass = (Class<? extends BaseModel>) Class
						.forName(packagePreName + modelName);
				CommonQuery<? extends BaseModel> commonQuery = this.dao
						.createCommonQuery(modelClass);
				commonQuery.addCondition(Condition.eq("status",
						CommonUtil.Active)); // 只能查询出生效的
				commonQuery
						.addCondition(Condition.eq("officeCode", officeCode));// 要根据officeCode
				ArrayList<? extends BaseModel> modelList = (ArrayList<? extends BaseModel>) commonQuery
						.query();
				treeMap.put(modelName, modelList);
			}
			json = JSONDataUtils.buildJSONValue(treeMap).toString();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return json;
	}

	@SuppressWarnings("unchecked")
	@Override
	public String getTreeJsonByFK(Map<String, String> parameters) {
		String queryModel = parameters.get("queryModel");
		String _foreignValue = parameters.get("uuid");
		String _foreignKey = "";
		String json = "";
		for (Entry<String, String> entrySet : parameters.entrySet()) {
			if (entrySet.getKey().equals("uuid")) {
				_foreignKey = entrySet.getKey();
			}
		}
		try {
			List<? extends BaseModel> modelList = getModelsByFk(
					(Class<? extends BaseModel>) Class.forName(queryModel),
					_foreignKey, _foreignValue);
			json = JSONDataUtils.buildJSONValue(modelList).toString();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return json;
	}

	private List<? extends BaseModel> getModelsByFk(
			Class<? extends BaseModel> cls, String p_foreignKey,
			String p_foreignValue) {
		CommonQuery<? extends BaseModel> commonQuery = this.dao
				.createCommonQuery(cls);

		commonQuery.addCondition(Condition.eq(p_foreignKey, p_foreignValue));
		List<? extends BaseModel> modelList = commonQuery.query();
		return modelList;
	}

	@Override
	public String getLocAreaComboboxData(String basWarehouseUuid) {
		BasLocAreaModel model = new BasLocAreaModel();
		model.setBasWarehouseUuid(basWarehouseUuid);
		List<BasLocAreaModel> modelList = this.dao.findByExample(model);
		List<ComboLocAreaModel> rsList = new ArrayList<ComboLocAreaModel>();
		for (BasLocAreaModel bModel : modelList) {
			ComboLocAreaModel cModel = new ComboLocAreaModel();
			cModel.setLocAreaUuid(bModel.getBasLocAreaUuid());
			cModel.setLocAreaName(bModel.getLocAreaName());
			rsList.add(cModel);
		}
		String rsString = "";
		try {
			rsString = JSONDataUtils.buildJSONValue(rsList).toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rsString;
	}

	class ComboLocAreaModel extends BaseModelClass {
		private static final long serialVersionUID = 6763723039364007970L;
		private String locAreaUuid;
		private String locAreaName;

		public void setLocAreaUuid(String locAreaUuid) {
			this.locAreaUuid = locAreaUuid;
		}

		public String getLocAreaUuid() {
			return locAreaUuid;
		}

		public void setLocAreaName(String locAreaName) {
			this.locAreaName = locAreaName;
		}

		public String getLocAreaName() {
			return locAreaName;
		}
	}

	// 货位信息
	@Override
	public SinotransPageJson saveBasLotStock(String params, String jsonResult, String officeCode) {
		SinotransPageJson spj = new SinotransPageJson(true);
		ObjectMapper om = new ObjectMapper();
		List<BasLotStockModel> basLsModel = new ArrayList<BasLotStockModel>();
		// List<BasLotStockModel> list = null;
		try {
			BasLotStockQueryItem[] modelitme  = om.readValue(jsonResult, BasLotStockQueryItem[].class);
			if(!RcUtil.isEmpty(modelitme) && modelitme.length >0){
				for (int i =0; i<modelitme.length; i++) {
					try {
						BasLotStockModel blsModel = new BasLotStockModel();
						BeanUtils.copyProperties(blsModel, modelitme[i]);
						blsModel.setOfficeCode(officeCode);
						basLsModel.add(blsModel);
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						e.printStackTrace();
					}
				}
			}
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (!RcUtil.isEmpty(basLsModel) && basLsModel.size() > 0) {
			if (basLsModel.get(0).getRowState().equals(
					CommonUtil.ROW_STATE_MODIFIED)) {
				for (BasLotStockModel model : basLsModel) {
					BasLotStockModel basLotStockModel = get(model
							.getBasLotStockUuid());
					BasLocAreaModel basLocAreaModel = basLocAreaManager
							.get(model.getBasLocAreaUuid());
					String controlWord = basLocAreaModel.getControlWord()
							.substring(0, 1)
							+ basLotStockModel.getControlWord().substring(1);
					model.setControlWord(controlWord);

					String word = controlWord.substring(1, 2);
					if (word.equals("D")) { // 如果修改的这条数据是默认的话
						String basLocAreaUuid1 = model.getBasLocAreaUuid();
						String basLocAreaUuid2 = basLotStockModel
								.getBasLocAreaUuid();
						if (!basLocAreaUuid2.equals(basLocAreaUuid1)) { // 判断之前的区域和将要去的区域是否相等
							List<BasLotStockModel> list = this.dao
									// 查出所在区域所有含有“D”的信息
									.createCommonQuery(BasLotStockModel.class)
									.addCondition(
											Condition.eq("basLocAreaUuid",
													model.getBasLocAreaUuid()))
									.addCondition(
											Condition.eq(
													"substr(control_word,2,1)",
													"D")).query();
							if (!RcUtil.isEmpty(list) && list.size() > 0) {
								String lotStockUuid = model
										.getBasLotStockUuid();

								BasLotStockModel lotStockModel = list.get(0);
								if (!RcUtil.isEmpty(lotStockModel)) {
									if (!lotStockUuid.equals(lotStockModel
											.getBasLotStockUuid())) {
										// 把原来默认的设成不默认
										String beforeControlWord = lotStockModel
												.getControlWord();
										beforeControlWord = beforeControlWord
												.substring(0, 1)
												+ "0"
												+ beforeControlWord
														.substring(2);
										lotStockModel
												.setControlWord(beforeControlWord);
										save(lotStockModel);
									}
								}
							}
						}
					}
				}
			}
			if (basLsModel.get(0).getRowState().equals(
					CommonUtil.ROW_STATE_ADDED)) {
				for (BasLotStockModel model : basLsModel) {
					String controlWord = basLocAreaManager.get(
							model.getBasLocAreaUuid()).getControlWord();
					controlWord = controlWord.substring(0, 1);
					model.setControlWord(controlWord);
				}
			}
			this.saveAll(basLsModel);
		}
		// list = saveAll(Arrays.asList(models));
		// spj.setObject(list);
		// spj.setMsg("保存成功！");
		return spj;
	}

	/**
	 * 默认货位
	 * 
	 * @param lotStockUuid
	 * @return
	 */
	public SinotransPageJson defaultLotStock(String lotStockUuid,
			String basLocAreaUuid) {
		SinotransPageJson spj = new SinotransPageJson(true);
		if (!RcUtil.isEmpty(lotStockUuid)) {
			List<BasLotStockModel> models = this.dao
					.createCommonQuery(BasLotStockModel.class)
					.addCondition(
							Condition.eq("basLocAreaUuid", basLocAreaUuid))
					.addCondition(Condition.eq("substr(control_word,2,1)", "D"))
					.query();

			if (!RcUtil.isEmpty(models) && models.size() > 0) {
				BasLotStockModel model = models.get(0);
				if (!RcUtil.isEmpty(model)) {
					if (!lotStockUuid.equals(model.getBasLotStockUuid())) {
						// 把原来默认的设成不默认
						String beforeControlWord = model.getControlWord();
						beforeControlWord = beforeControlWord.substring(0, 1)
								+ "0" + beforeControlWord.substring(2);
						model.setControlWord(beforeControlWord);
						this.save(model);

						// 再设置现在的
						BasLotStockModel currtModel = this.get(lotStockUuid);
						if (!RcUtil.isEmpty(currtModel)) {
							String controlWord = currtModel.getControlWord();
							controlWord = controlWord.substring(0, 1) + "D"
									+ controlWord.substring(2);
							currtModel.setControlWord(controlWord);
							this.save(currtModel);
						}
					}
				}
			} else {
				BasLotStockModel model = this.get(lotStockUuid);
				if (!RcUtil.isEmpty(model)) {
					String controlWord = model.getControlWord();
					controlWord = controlWord.substring(0, 1) + "D"
							+ controlWord.substring(2);
					model.setControlWord(controlWord);
					this.save(model);
				}
			}
		}

		return spj;
	}

	/**
	 * 验证货位的唯一性
	 * 
	 */
	@Override
	public boolean validateLotStock(String lotCode, String officeCode){
		BasLotStockModel basLotStockModel = new BasLotStockModel();
		basLotStockModel.setLotCode(lotCode);
		basLotStockModel.setOfficeCode(officeCode);
		List<BasLotStockModel> blsList = this.findByExample(basLotStockModel);
		if(!RcUtil.isEmpty(blsList) && blsList.size() > 0){
			return true;
		}else{
			return false;
		}
	}
	
	
	/**
	 * 保存货位信息
	 */
	@Override
	public SinotransPageJson saveBasLocStock(List<BasLotStockEntity> blsEntity,String type) {
		SinotransPageJson spj = new SinotransPageJson();
		if(!RcUtil.isEmpty(blsEntity) && blsEntity.size()>0){
			for(BasLotStockEntity model:blsEntity){
				//验证必输项、由于直接抛异常这里就不用单独判断了
				getJubegLotStock(model);
				if(!RcUtil.isEmpty(type)){
					if(BasLotStockModel.ROW_STATE_DELETED.equals(type)){
						model.setRowState(BasLotStockModel.ROW_STATE_DELETED);
					}else if(BasLotStockModel.ROW_STATE_ADDED.equals(type)){
						model.setRowState(BasLotStockModel.ROW_STATE_ADDED);
						model.setStatus(CommonUtil.Pending);
					}else{
						model.setStatus(type);
					}
				}else{
					spj.setResult(false);
					spj.setError("无效的操作指示!");
					return spj;
				}
				if("0".equals(model.getControlWord().charAt(0)+"")){
					BasLocAreaModel blaModel=this.dao.get(BasLocAreaModel.class, model.getBasLocAreaUuid());
					model.setCw1(blaModel.getControlWord().subSequence(0, 1).toString());
				}
				this.save(model);
			}
			spj.setMsg("操作成功!");
			spj.setResult(true);
		}else{
			spj.setResult(false);
			spj.setError("没有可执行的操作集!");
		}
		return spj;
	}
	
	
	/**
	 * 设置默认值
	 */
	@Override
	public SinotransPageJson setDefaultValue(BasLotStockEntity blsEntity) {
		SinotransPageJson spj = new SinotransPageJson();
		if(!RcUtil.isEmpty(blsEntity.getBasLocAreaUuid()) && !RcUtil.isEmpty(blsEntity.getBasLocAreaUuid())){
			if(!CommonUtil.Active.equals(blsEntity.getStatus())){
				throw new ApplicationException("当前状态不允许设置默认!");
			}
			blsEntity.setCw2(blsEntity.getCw2());
			//首先取消上一次的默认
			updateDefaultLotStock(blsEntity.getOfficeCode(), blsEntity.getBasLocAreaUuid());
			//再保存本次的默认
			this.dao.save(blsEntity);
			spj.setResult(true);
			spj.setMsg("默认成功！");
		}else{
			spj.setResult(false);
			spj.setError("主键为空!不允许默认!");
		}
		return spj;
	}
	
	
	/*
	 * 取消货位的默认功能
	 * @param officeCode
	 * @param locAreaUuid
	 * @return
	 */
	

	/**
	 * 验证必输项
	 * @param model
	 */
	private void getJubegLotStock(BasLotStockModel model){
		if(!RcUtil.isEmpty(model.getLotCode())&&RcUtil.isEmpty(model.getBasLotStockUuid())){
			if(validateLotStock(model.getLotCode(), model.getOfficeCode())){
				throw new ApplicationException("货位编号不能重复！");
			}
		}
		if(RcUtil.isEmpty(model.getBasLocAreaUuid())){
			throw new ApplicationException("仓库区域UUID为空！");
		}
		if(RcUtil.isEmpty(model.getLotCode())){
			throw new ApplicationException("仓库编号为空！");
		}
		if(RcUtil.isEmpty(model.getLotName())){
			throw new ApplicationException("仓库名称为空！");
		}
		if(RcUtil.isEmpty(model.getOfficeCode())){
			throw new ApplicationException("OfficeCod为空！");
		}
	}

	@Override
	public SinotransPageJson updateDefaultLotStock(String officeCode,
			String locAreaUuid) {
		SinotransPageJson spj=new SinotransPageJson();
		if(RcUtil.isEmpty(officeCode)){
			throw new ApplicationException("仓库代码为空!(officeCode)");
		}
		if(RcUtil.isEmpty(locAreaUuid)){
			throw new ApplicationException("区域代码主键为空!(locAreaUuid)");
		}
		List<BasLotStockModel> blsList=this.dao.createCommonQuery(BasLotStockModel.class)
										.addCondition(Condition.eq(BasLotStockModel.FieldNames.officeCode, officeCode))
										.addCondition(Condition.eq(BasLotStockModel.FieldNames.basLocAreaUuid, locAreaUuid))
										.addCondition(Condition.sql(" SUBSTR(CONTROL_WORD,2,1)='D' ", null))
										.query();
		for (BasLotStockModel model : blsList) {
			model.setControlWord(RcUtil.setKeyBit(2, "0", model.getControlWord()));
		}
		this.saveAll(blsList);
		spj.setResult(true);
		spj.setMsg("取消默认成功!");
		return spj;
	}
	
}
