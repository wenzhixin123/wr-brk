package com.sinotrans.gd.wlp.basicdata.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sinotrans.framework.core.service.impl.BaseManagerImpl;
import com.sinotrans.framework.core.support.Condition;
import com.sinotrans.framework.core.support.PagingInfo;
import com.sinotrans.gd.wlp.basicdata.model.BasAccreditBoxModel;
import com.sinotrans.gd.wlp.basicdata.model.BasCodeDefModel;
import com.sinotrans.gd.wlp.basicdata.model.BasCodeTypeModel;
import com.sinotrans.gd.wlp.basicdata.model.BasCustomerModel;
import com.sinotrans.gd.wlp.basicdata.service.BasAccreditBoxManager;
import com.sinotrans.gd.wlp.basicdata.service.BasCodeDefManager;
import com.sinotrans.gd.wlp.basicdata.service.BasCodeTypeManager;
import com.sinotrans.gd.wlp.basicdata.service.BasCustomerManager;
import com.sinotrans.gd.wlp.common.web.SinotransDataGrid;
import com.sinotrans.gd.wlp.system.entity.SessionContextUserEntity;
import com.sinotrans.gd.wlp.util.CommonUtil;

@Service
public class BasAccreditBoxManagerImpl extends BaseManagerImpl implements BasAccreditBoxManager {

	@Autowired
	private BasCustomerManager basCustomerManager;
	
	@Autowired
	private BasCodeDefManager basCodeDefManager;
	
	@Autowired
	private BasCodeTypeManager basCodeTypeManager;
	
	
	public BasAccreditBoxModel get(String id) {
		return this.dao.get(BasAccreditBoxModel.class, id);
	}

	public List<BasAccreditBoxModel> getAll() {
		return this.dao.getAll(BasAccreditBoxModel.class);
	}

	public List<BasAccreditBoxModel> findByExample(BasAccreditBoxModel example) {
		return this.dao.findByExample(example);
	}

	public BasAccreditBoxModel save(BasAccreditBoxModel model) {
		return this.dao.save(model);
	}
	
	/**
	 * 根据控箱公司和委托单位查询授权表中是否存在
	 * @param bedControlBoxCompany 控箱公司
	 * @param bedEntrustUnit 委托单位
	 * @param portAreaCode 作业点
	 * @return
	 */
	public List<BasCodeDefModel> getAccreditBoxModel(String bedControlBoxCompany,String bedEntrustUnit,String portAreaCode){
		List<BasCodeDefModel> codeDefList = this.dao.createCommonQuery(BasCodeDefModel.class)
			.addCondition(Condition.eq(BasCodeDefModel.FieldNames.codeValue, bedEntrustUnit))
			.addCondition(Condition.eq(BasCodeDefModel.FieldNames.displayValue, bedControlBoxCompany))
			.addCondition(Condition.eq(BasCodeDefModel.FieldNames.centerCode, portAreaCode))
			.query();
		
		if(codeDefList != null && codeDefList.size() > 0){
			return codeDefList;
		}else{
			return null;
		}
	}


	public List<BasCodeDefModel> saveAll(Collection<BasCodeDefModel> models){
		String officeCode = SessionContextUserEntity.currentUser().getOfficeCode();
		//获取控箱授权的数据类型
		BasCodeTypeModel codeTypeModel = basCodeTypeManager.findObject(CommonUtil.CNTRADMIN_ACCESS_CONTROL, officeCode);
		if(codeTypeModel != null){
			for(BasCodeDefModel codeDefModel : models){
				codeDefModel.setBasCodeTypeUuid(codeTypeModel.getBasCodeTypeUuid());
			}
			return basCodeDefManager.saveAll(models);
		}else{
			return null;
		}
		
	}

	public void remove(BasAccreditBoxModel model) {
		this.dao.remove(model);
	}

	public void removeAll(Collection<BasAccreditBoxModel> models) {
		this.dao.removeAll(models);
	}

	public void removeByPk(String id) {
		this.dao.removeByPk(BasAccreditBoxModel.class, id);
	}

	public void removeAllByPk(Collection<String> ids) {
		this.dao.removeAllByPk(BasAccreditBoxModel.class, ids);
	}
	
	/**
	 * 查询委托单位与控箱公司是否在选择的作业点存在映射
	 * @param models
	 * @return
	 */
	public String queryMapping(Collection<BasCodeDefModel> models){
		if(models != null){
			for(BasCodeDefModel model : models){
				//只有新增才去查看是否有映射
				if(BasAccreditBoxModel.ROW_STATE_ADDED.equals(model.getRowState())){
					String message = null;
					if(StringUtils.isNotBlank(model.getCenterCode())){
						/*List<DcsMappingInfoModel> mappingInfoList = this.dao.createCommonQuery(DcsMappingInfoModel.class)
							.addCondition(Condition.eq(DcsMappingInfoModel.FieldNames.mappingType, "BAS_CUSTOMER"))
							.addCondition(Condition.eq(DcsMappingInfoModel.FieldNames.dcsInfoCode, model.getDisplayValue()))
							.addCondition(Condition.eq(DcsMappingInfoModel.FieldNames.portAreaCode, model.getCenterCode()))
							.query();
						List<DcsMappingInfoModel> mappingInfoList1 = this.dao.createCommonQuery(DcsMappingInfoModel.class)
							.addCondition(Condition.eq(DcsMappingInfoModel.FieldNames.mappingType, "BAS_CUSTOMER"))
							.addCondition(Condition.eq(DcsMappingInfoModel.FieldNames.dcsInfoCode, model.getCodeValue()))
							.addCondition(Condition.eq(DcsMappingInfoModel.FieldNames.portAreaCode, model.getCenterCode()))
							.query();
						if(mappingInfoList != null && mappingInfoList.size() > 0){
							message = null;
						}else{
							message = "控箱公司编码：" + model.getDisplayValue() + "在" + model.getCenterCode() + "没有映射";
						}
						if(mappingInfoList1 != null && mappingInfoList1.size() > 0){
							message = null;
						}else{
							message = "委托单位编码：" + model.getCodeValue() + "在" + model.getCenterCode() + "没有映射";
						}
						if(StringUtils.isNotBlank(message)){
							return message;
						}*/
					}
				}else{
					return null;
				}
			}
			return null;
		}else{
			return "传入参数为空";
		}
	}
	
	
	/**
	 * 根据委托单位查询控箱公司，作业点，作业项目，业务类型
	 * @param agentConsigneeCode
	 * @return
	 */
	public List<BasAccreditBoxModel> queryBasAccreditByAgcode(String agentConsigneeCode){
		List<BasAccreditBoxModel> modelList = this.dao.createCommonQuery(BasAccreditBoxModel.class)
				.addCondition(Condition.eq(BasAccreditBoxModel.FieldNames.bedEntrustUnit, agentConsigneeCode))
				.query();
		if(modelList != null && modelList.size() > 0){
			return modelList;
		}else{
			return null;
		}
	}
	
	public String getValueLikeCntrAdminCode(String agentConsigneeCode,String cntrAdminCode){
		//查询控箱授权数据类型
		List<BasCodeTypeModel> codeTypeList = this.dao.createCommonQuery(BasCodeTypeModel.class)
										.addCondition(Condition.eq(BasCodeTypeModel.FieldNames.typeCode, CommonUtil.CNTRADMIN_ACCESS_CONTROL))
										.addCondition(Condition.eq(BasCodeTypeModel.FieldNames.status, CommonUtil.Active))
										.query();
		BasCodeTypeModel codeTypeModel = new BasCodeTypeModel();
		if(codeTypeList != null && codeTypeList.size() > 0){
			codeTypeModel = codeTypeList.get(0);
		}
		if(codeTypeModel != null){
			List<BasCustomerModel> customerList = this.dao.createCommonQuery(BasCustomerModel.class)
					.addCondition(Condition.like(BasCustomerModel.FieldNames.customerName, "%" + cntrAdminCode + "%"))
					.addCondition(Condition.eq(BasCustomerModel.FieldNames.status, CommonUtil.Active))
					.addCondition(Condition.like(BasCustomerModel.FieldNames.customerType, "%06%"))
					.query();
			if(customerList != null && customerList.size() > 0){
				for(BasCustomerModel customerModel : customerList){
					if(customerModel.getCustomerCode().equals(agentConsigneeCode)){
						return customerModel.getCustomerCode();
					}
					List<BasCodeDefModel> basCodeDefList = this.dao.createCommonQuery(BasCodeDefModel.class)
							.addCondition(Condition.eq(BasCodeDefModel.FieldNames.basCodeTypeUuid, codeTypeModel.getBasCodeTypeUuid()))
							.addCondition(Condition.eq(BasCodeDefModel.FieldNames.codeValue, agentConsigneeCode))
							.addCondition(Condition.eq(BasCodeDefModel.FieldNames.displayValue, customerModel.getCustomerCode()))
							.query();
					if(basCodeDefList != null && basCodeDefList.size() > 0){
						return customerModel.getCustomerCode();
					}
				}
			}
		}
		return null;
	}
	
	/**
	 * 根据委托单位查询控箱公司
	 * @param agentConsigneeCode
	 * @param pagingInfo
	 * @return
	 */
	public SinotransDataGrid selectCntrAdminCode(String agentConsigneeCode,PagingInfo pagingInfo){
		SinotransDataGrid adb = null;
		//查询控箱授权数据类型
		List<BasCodeTypeModel> codeTypeList = this.dao.createCommonQuery(BasCodeTypeModel.class)
										.addCondition(Condition.eq(BasCodeTypeModel.FieldNames.typeCode, CommonUtil.CNTRADMIN_ACCESS_CONTROL))
										.addCondition(Condition.eq(BasCodeTypeModel.FieldNames.status, CommonUtil.Active))
										.query();
		BasCodeTypeModel codeTypeModel = new BasCodeTypeModel();
		if(codeTypeList != null && codeTypeList.size() > 0){
			codeTypeModel = codeTypeList.get(0);
		}
		//定义返回datagrid的List
		List<BasCustomerModel> boxModelList = new ArrayList<BasCustomerModel>();
		//查询该委托单位是否为控箱公司
		List<BasCustomerModel> customerList = this.dao.createCommonQuery(BasCustomerModel.class)
											.addCondition(Condition.eq(BasCustomerModel.FieldNames.customerCode, agentConsigneeCode))
											.addCondition(Condition.eq(BasCustomerModel.FieldNames.status, CommonUtil.Active))
											.addCondition(Condition.like(BasCustomerModel.FieldNames.customerType, "%06%"))
											.query();
		if(customerList != null && customerList.size() > 0){
			boxModelList.add(customerList.get(0));
		}
		
		if(codeTypeModel != null){
			List<BasCodeDefModel> basCodeDefList = this.dao.createCommonQuery(BasCodeDefModel.class)
												.addCondition(Condition.eq(BasCodeDefModel.FieldNames.basCodeTypeUuid, codeTypeModel.getBasCodeTypeUuid()))
												.addCondition(Condition.eq(BasCodeDefModel.FieldNames.codeValue, agentConsigneeCode))
												.query();
			for(BasCodeDefModel codeDefModel : basCodeDefList){
				BasCustomerModel customerModel = basCustomerManager.getModelByCode(codeDefModel.getDisplayValue());
				if(!boxModelList.contains(customerModel)){
					boxModelList.add(customerModel);
				}
			}
		}
		adb = new SinotransDataGrid(boxModelList, pagingInfo.getTotalRows(),
				pagingInfo.getTotalPages());
		return adb;
	}
}
