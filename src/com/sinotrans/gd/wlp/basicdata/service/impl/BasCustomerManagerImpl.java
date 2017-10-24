package com.sinotrans.gd.wlp.basicdata.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sinotrans.framework.core.exception.ApplicationException;
import com.sinotrans.framework.core.service.impl.BaseManagerImpl;
import com.sinotrans.framework.core.support.CommonQuery;
import com.sinotrans.framework.core.support.Condition;
import com.sinotrans.gd.wlp.basicdata.entity.BasCustomerEntity;
import com.sinotrans.gd.wlp.basicdata.model.BasCustomerModel;
import com.sinotrans.gd.wlp.basicdata.service.BasCodeDefManager;
import com.sinotrans.gd.wlp.basicdata.service.BasCustomerManager;
import com.sinotrans.gd.wlp.basicdata.util.BasdataServiceUtil;
import com.sinotrans.gd.wlp.common.entity.BasOption;
import com.sinotrans.gd.wlp.common.web.RcUtil;
import com.sinotrans.gd.wlp.common.web.SinotransPageJson;
import com.sinotrans.gd.wlp.util.CommonUtil;

@Service
public class BasCustomerManagerImpl extends BaseManagerImpl implements
		BasCustomerManager {
	
	@Autowired
	private BasCodeDefManager basCodeDefManager;

	public BasCustomerModel get(String id) {
		return this.dao.get(BasCustomerModel.class, id);
	}

	public List<BasCustomerModel> getAll() {
		return this.dao.getAll(BasCustomerModel.class);
	}
	
	private static BasCustomerManagerImpl  singleEntity=null;
	
	BasCustomerManagerImpl(){
		if(singleEntity == null) 
			singleEntity = this;
	}
	
	public static BasCustomerManagerImpl getSingleInstance(){
		 if(singleEntity==null){
			 singleEntity = new BasCustomerManagerImpl();
		 }
		return singleEntity;
	}

	public BasCustomerModel findByCustomerCode(String customerCode){
		BasCustomerModel customerExample = new BasCustomerModel();
		customerExample.setCustomerCode(customerCode);
		List<BasCustomerModel> cList = this.findByExample(customerExample);
		return cList!=null&&cList.size()>0?cList.get(0):null;
	}
	
	public List<BasCustomerModel> findByExample(BasCustomerModel example) {
		return this.dao.findByExample(example);
	}

	public BasCustomerModel save(BasCustomerModel model) {
		return (BasCustomerModel) this.dao.save(BasdataServiceUtil
				.beforeSave(model));
	}

	@SuppressWarnings("unchecked")
	public List<BasCustomerModel> saveAll(Collection<BasCustomerModel> models) {
		return (List<BasCustomerModel>) this.dao.saveAll(BasdataServiceUtil
				.beforeSaveAll(models));
	}

	public void remove(BasCustomerModel model) {
		this.dao.remove(model);
	}

	public void removeAll(Collection<BasCustomerModel> models) {
		this.dao.removeAll(models);
	}

	public void removeByPk(String id) {
		this.dao.removeByPk(BasCustomerModel.class, id);
	}

	public void removeAllByPk(Collection<String> ids) {
		this.dao.removeAllByPk(BasCustomerModel.class, ids);
	}

	@SuppressWarnings("static-access")
	@Override
	public List<BasOption> getOptionsByCode(String code, String officeCode,
			String language) {

		ArrayList<BasOption> optionList = new ArrayList<BasOption>();
		CommonQuery<BasCustomerModel> commonQuery = this.dao
				.createCommonQuery(BasCustomerModel.class);

		if (!RcUtil.isEmpty(code)) {
			commonQuery.addCondition(Condition.like("customerCode", code + "%")
					.or(Condition.like("customerName", code + "%")));
		}
		if (!RcUtil.isEmpty(officeCode)) {
			commonQuery.addCondition(Condition.eq("officeCode", officeCode));
		}

		ArrayList<BasCustomerModel> modelList = (ArrayList<BasCustomerModel>) commonQuery
				.query();

		if (language.equals(CommonUtil.ZH_CN)) {
			for (BasCustomerModel model : modelList) {
				optionList.add(new BasOption(model.getCustomerCode(), model
						.getCustomerName()));
			}
		}

		if (language.equals(CommonUtil.EN_USA)) {
			for (BasCustomerModel model : modelList) {
				optionList.add(new BasOption(model.getCustomerCode(), model
						.getCustomerNameEn()));
			}
		}

		return optionList;
	}

	/**
	 * 验证客户信息页面
	 * 
	 * @param CustomerCode
	 * @return 返回 true 为已存在
	 */
	public boolean getYanZhenCustomCode(String userCode) {
		BasCustomerModel user = new BasCustomerModel();
		user.setCustomerCode(userCode.trim());
		List<BasCustomerModel> userList = this.findByExample(user);
		if (userList != null && userList.size() > 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public BasCustomerModel getModelByCode(String customerCode) {
		BasCustomerModel office = new BasCustomerModel();
		office.setCustomerCode(customerCode.trim());
		office.setStatus(CommonUtil.Active);
		List<BasCustomerModel> officeList = this.findByExample(office);
		if (officeList != null && officeList.size() > 0) {
			return officeList.get(0);
		} else {
			throw new ApplicationException("在文件中心未获取到相应的有效客户信息，传入的客户代码为["+customerCode+"]！");
		}
	}

	@Override
	public SinotransPageJson saveBasCustomer(String jsonResult)
			throws Exception {
		SinotransPageJson sinotransPageJson = new SinotransPageJson();
		List<BasCustomerModel> list = new ArrayList<BasCustomerModel>();
		ObjectMapper om = new ObjectMapper();
		om.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
		BasCustomerEntity[] models = om.readValue(jsonResult,
				BasCustomerEntity[].class);
		for (BasCustomerEntity basCustomerEntity : models) {
			BasCustomerModel basmodel = new BasCustomerModel();
			BeanUtils.copyProperties(basmodel, basCustomerEntity);
			if (CommonUtil.ROW_STATE_ADDED.equals(basCustomerEntity
					.getRowState())) {
				if (getYanZhenCustomCode(basCustomerEntity.getCustomerCode())) {
					sinotransPageJson.setError("该编码已存在、请审核后重新填写！");
					sinotransPageJson.setResult(false);
					return sinotransPageJson;
				}
			}
			list.add(basmodel);
		}
		this.saveAll(list);
		sinotransPageJson.setResult(true);
		sinotransPageJson.setMsg("保存成功!");
		return sinotransPageJson;
	}
	
	//根据Entity查找
	public List<BasCustomerEntity> findByEntity(BasCustomerEntity entity){
		return this.dao.findByExample(entity);
	}

	@Override
	public List<BasCustomerEntity> getCustomerEntity(BasCustomerEntity entity) {
		List<BasCustomerEntity> returnList = findByEntity(entity);
		for(int i=0; i<returnList.size(); i++) {
			BasCustomerEntity tempEntity = returnList.get(i);
			String controlWord = tempEntity.getControlWord();
			if(StringUtils.isNotBlank(controlWord)){
				String firstWord = controlWord.substring(0, 1);
				tempEntity.setDiyCtrWord(firstWord);
			}
		}
		return returnList;
		
	}

	@Override
	public List<String> gerCustomerByInput(String inputValue) {
		List<String> customerList = new ArrayList<String>();
		List<BasCustomerModel> customerModels = this.dao.createCommonQuery(BasCustomerModel.class)
													.addCondition(Condition.or(Condition.like(BasCustomerModel.FieldNames.customerCode, "%"+inputValue+"%"),
																				Condition.like(BasCustomerModel.FieldNames.customerName, "%"+inputValue+"%")))
													.query();
		
		if(customerModels.size() > 0){
			for(BasCustomerModel customerModel : customerModels){
				if(customerModel.getCustomerCode() != null && !"".equals(customerModel.getCustomerCode())){
					customerList.add(customerModel.getCustomerCode());
				}
				if(customerModel.getCustomerCode() != null && !"".equals(customerModel.getCustomerCode()) && customerModel.getCustomerName().equals(inputValue)){
					customerList.removeAll(customerList);
					customerList.add(customerModel.getCustomerCode());
					break;
				}
			}
		}
		
		return customerList;
	}

	@Override
	public List<String> gerCustomerByInputFuzzy(String inputValue) {
		List<String> customerList = new ArrayList<String>();
		List<BasCustomerModel> customerModels = this.dao.createCommonQuery(BasCustomerModel.class)
													.addCondition(Condition.or(Condition.like(BasCustomerModel.FieldNames.customerCode, "%"+inputValue+"%"),
																				Condition.like(BasCustomerModel.FieldNames.customerName, "%"+inputValue+"%")))
													.query();
		
		if(customerModels.size() > 0){
			for(BasCustomerModel customerModel : customerModels){
				if(customerModel.getCustomerCode() != null && !"".equals(customerModel.getCustomerCode())){
					customerList.add(customerModel.getCustomerCode());
				}
			}
		}
		
		return customerList;
	}

	@Override
	public String getCustomsCodeByDesc1stRow(String desc) {
		String customsCode = "";
		if(StringUtils.isNotBlank(desc)){
			String[] descArr = desc.split("\r\n");
			if(descArr!=null&&descArr.length>0){
				String firstRow = descArr[0];
				List<String> codeList = this.gerCustomerByInput(firstRow);
				if(codeList!=null&&codeList.size()>0){
					customsCode = codeList.get(0);
				}
			}
		}
		return customsCode;
	}
	
	@Override
	public String getCustomsDescBySplit(String desc, int num, String regex) {
		String returnDesc = "";
		if(StringUtils.isNotBlank(desc)){
			String[] descList = desc.split(regex);
			if(descList!=null&&descList.length>=num){
				returnDesc = descList[num-1];
			}
		}
		return returnDesc;
	}

	@Override
	public String checkMutAutoSendType(String customerCode) {
		if(StringUtils.isNotBlank(customerCode)){
			List<BasCustomerModel> customerList = this.dao.createCommonQuery(BasCustomerModel.class)
					.addCondition(Condition.eq(BasCustomerModel.FieldNames.customerCode, customerCode))
					.addCondition(Condition.eq(BasCustomerModel.FieldNames.status, CommonUtil.Active))
					.addCondition(Condition.not(Condition.like(BasCustomerModel.FieldNames.controlWord, "______________0%")))
					.query();
			if(customerList!=null&&customerList.size()>0){
				String sendMutType = customerList.get(0).getControlWord().substring(14, 15);
				//BasCodeDefModel mutTypeModel = basCodeDefManager.getCodeDefModelByCodeTypeValue(DcsUtil.MUTUAL_SENDTO_TYPE, sendMutType, CommonUtil.Active);
				return null;
			   //return StringUtils.isNotBlank(mutTypeModel.getDisplayValueEn())?mutTypeModel.getDisplayValueEn():null;
			}else{
				return null;
			}
		}else{
			return null;
		}
	}
}
