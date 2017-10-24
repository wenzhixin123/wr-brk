package com.sinotrans.gd.wlp.basicdata.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sinotrans.framework.core.service.impl.BaseManagerImpl;
import com.sinotrans.framework.core.support.CommonQuery;
import com.sinotrans.framework.core.support.Condition;
import com.sinotrans.framework.core.support.PagingInfo;
import com.sinotrans.framework.core.util.JSONDataUtils;
import com.sinotrans.gd.wlp.basicdata.model.BasCodeDefModel;
import com.sinotrans.gd.wlp.basicdata.model.BasCodeTypeModel;
import com.sinotrans.gd.wlp.basicdata.model.BasCustomerModel;
import com.sinotrans.gd.wlp.basicdata.query.BasCodeTypeByTypeCodeAndCodeValueQueryCondition;
import com.sinotrans.gd.wlp.basicdata.query.BasCodeTypeByTypeCodeAndCodeValueQueryItem;
import com.sinotrans.gd.wlp.basicdata.query.BasReleaseExcelTemplateQueryCondition;
import com.sinotrans.gd.wlp.basicdata.query.CodeDefBySpecialCodeTypeQueryCondition;
import com.sinotrans.gd.wlp.basicdata.query.CodeDefBySpecialCodeTypeQueryItem;
import com.sinotrans.gd.wlp.basicdata.service.BasCodeDefManager;
import com.sinotrans.gd.wlp.basicdata.service.BasCodeTypeManager;
import com.sinotrans.gd.wlp.basicdata.service.BasCustomerManager;
import com.sinotrans.gd.wlp.basicdata.util.BasdataServiceUtil;
import com.sinotrans.gd.wlp.basicdata.web.CntrAdminPrefixWebVO;
import com.sinotrans.gd.wlp.common.service.SQLQueryManager;
import com.sinotrans.gd.wlp.common.web.SinotransDataGrid;
import com.sinotrans.gd.wlp.util.CommonUtil;

@Service
public class BasCodeDefManagerImpl extends BaseManagerImpl implements
		BasCodeDefManager {

	public BasCodeDefModel get(String id) {
		return this.dao.get(BasCodeDefModel.class, id);
	}

	public List<BasCodeDefModel> getAll() {
		return this.dao.getAll(BasCodeDefModel.class);
	}

	public List<BasCodeDefModel> findByExample(BasCodeDefModel example) {
		return this.dao.findByExample(example);
	}

	public BasCodeDefModel save(BasCodeDefModel model) {
		return (BasCodeDefModel) this.dao.save(BasdataServiceUtil
				.beforeSave(model));
	}

	@SuppressWarnings("unchecked")
	public List<BasCodeDefModel> saveAll(Collection<BasCodeDefModel> models) {
		return (List<BasCodeDefModel>) this.dao.saveAll(BasdataServiceUtil
				.beforeSaveAll(models));
	}

	public void remove(BasCodeDefModel model) {
		this.dao.remove(model);
	}

	public void removeAll(Collection<BasCodeDefModel> models) {
		this.dao.removeAll(models);
	}

	public void removeByPk(String id) {
		this.dao.removeByPk(BasCodeDefModel.class, id);
	}

	public void removeAllByPk(Collection<String> ids) {
		this.dao.removeAllByPk(BasCodeDefModel.class, ids);
	}

	@Override
	public String getDefaultCodeDefJSON(String basCodeTypeUuid) {
		BasCodeDefModel codeDefModel = new BasCodeDefModel();
		codeDefModel.setBasCodeTypeUuid(basCodeTypeUuid);

		List<BasCodeDefModel> codeTypeModelList = this.dao
				.findByExample(codeDefModel);

		List<BasCodeDefModel> resultList = new ArrayList<BasCodeDefModel>();
		for (BasCodeDefModel model : codeTypeModelList) {
			String controlWord = model.getControlWord();
			if (controlWord.substring(0, 1).equals("1")) {
				resultList.add(model);
			}
		}

		String json = "";

		if (resultList.size() == 1) {
			try {
				json = JSONDataUtils.buildJSONValue(resultList).toString();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return json;
	}

	@Override
	public List<BasCodeDefModel> findByTypeCode(String typeCode,
			String officeCode, boolean flagStatus) {
		CommonQuery<BasCodeTypeModel> cqType = this.dao.createCommonQuery(
				BasCodeTypeModel.class).addCondition(
				Condition.eq(BasCodeTypeModel.FieldNames.typeCode, typeCode))
				.addCondition(
						Condition.eq(BasCodeTypeModel.FieldNames.officeCode,
								officeCode));
		if (flagStatus) {
			cqType.addCondition(Condition.eq(
					BasCodeTypeModel.FieldNames.status, CommonUtil.Active));
		}
		List<BasCodeTypeModel> bctmList = cqType.query();
		BasCodeTypeModel bctm = null;
		if (bctmList != null && bctmList.size() > 0) {
			bctm = bctmList.get(0);
		}
		List<BasCodeDefModel> bcdmLst = new ArrayList<BasCodeDefModel>();
		if (bctm != null) {
			CommonQuery<BasCodeDefModel> cqDef = this.dao.createCommonQuery(
					BasCodeDefModel.class).addCondition(
					Condition.eq(BasCodeDefModel.FieldNames.basCodeTypeUuid,
							bctm.getBasCodeTypeUuid())).addCondition(
					Condition.eq(BasCodeDefModel.FieldNames.officeCode,
							officeCode));

			if (flagStatus) {
				cqDef.addCondition(Condition.eq(
						BasCodeTypeModel.FieldNames.status, CommonUtil.Active));
			}
			bcdmLst = cqDef.query();
		}
		return bcdmLst;
	}

	@Override
	public List<BasCodeDefModel> findByTypeCode(String typeCode,
			boolean flagStatus) {
		CommonQuery<BasCodeTypeModel> cqType = this.dao.createCommonQuery(
				BasCodeTypeModel.class).addCondition(
				Condition.eq(BasCodeTypeModel.FieldNames.typeCode, typeCode));
		if (flagStatus) {
			cqType.addCondition(Condition.eq(
					BasCodeTypeModel.FieldNames.status, CommonUtil.Active));
		}
		List<BasCodeTypeModel> bctmList = cqType.query();
		BasCodeTypeModel bctm = null;
		if (bctmList != null && bctmList.size() > 0) {
			bctm = bctmList.get(0);
		}
		List<BasCodeDefModel> bcdmLst = new ArrayList<BasCodeDefModel>();
		if (bctm != null) {
			CommonQuery<BasCodeDefModel> cqDef = this.dao.createCommonQuery(
					BasCodeDefModel.class).addCondition(
					Condition.eq(BasCodeDefModel.FieldNames.basCodeTypeUuid,
							bctm.getBasCodeTypeUuid()));

			if (flagStatus) {
				cqDef.addCondition(Condition.eq(
						BasCodeTypeModel.FieldNames.status, CommonUtil.Active));
			}
			bcdmLst = cqDef.query();
		}
		return bcdmLst;
	}

	/**
	 * 根据特定的codeType数据查出codeDef具有的数据
	 */
	@Override
	public SinotransDataGrid codeDefSpecial(String basCodeTypeUuid,
			String codeValue, String codeNumber, String modifiable,
			String status, PagingInfo pagingInfo, String officeCode) {
		CodeDefBySpecialCodeTypeQueryCondition condition = new CodeDefBySpecialCodeTypeQueryCondition();
		List<CodeDefBySpecialCodeTypeQueryItem> cdItemList = new ArrayList<CodeDefBySpecialCodeTypeQueryItem>();
		StringBuffer sb = new StringBuffer();
		sb.append(" 0=0 ");
		sb.append(" and substr(bct.control_word, 0, 1)='U'");
		condition.setOfficeCode(officeCode);
		if (codeValue != null && codeValue != "") {
			condition.setCodeValue(codeValue);
		} else {
			condition.setStatus(status);
			condition.setBasCodeTypeUuid(basCodeTypeUuid);
			condition.setCodeNumber(codeNumber);
			condition.setCodeValue(codeValue);
			condition.setModifiable(modifiable);
		}
		cdItemList = this.dao.query(condition,
				CodeDefBySpecialCodeTypeQueryItem.class, sb.toString(), null,
				null, pagingInfo);
		SinotransDataGrid sdg = new SinotransDataGrid(cdItemList, pagingInfo
				.getTotalRows(), pagingInfo.getCurrentPage());
		return sdg;

	}

	@Override
	public String getDisplayCodeByCodeValue(String codeValue) {
		BasCodeDefModel model = new BasCodeDefModel();
		model.setCodeValue(codeValue);
		List<BasCodeDefModel> list = findByExample(model);
		if(list.size() > 0) {
			return list.get(0).getDisplayValue();
		}
		return null;
	}
	
	@Autowired
	BasCodeTypeManager basCodeTypeManager;
	@Override
	public String getDisplayValue(String codeType, String codeValue) {
		if(codeValue == null || codeType== null){
			return null;
		}
		BasCodeTypeModel typeModel = new BasCodeTypeModel();
		typeModel.setTypeCode(codeType);
		typeModel.setStatus("Active");
		List<BasCodeTypeModel> typeList = basCodeTypeManager.findByExample(typeModel);
		if(typeList.size() < 1){
			return null;
		}
		String typeuuid = typeList.get(0).getBasCodeTypeUuid();
		BasCodeDefModel defModel = new BasCodeDefModel();
		defModel.setCodeValue(codeValue);
		defModel.setBasCodeTypeUuid(typeuuid);
		defModel.setStatus("Active");
		List<BasCodeDefModel> defList = this.findByExample(defModel);
		if(defList.size() < 1){
			return null;
		}
		return defList.get(0).getDisplayValue();
	}
	@Override
	public String getCodeValue(String codeType, String displayValue) {
		if(displayValue == null || codeType== null){
			return null;
		}
		BasCodeTypeModel typeModel = new BasCodeTypeModel();
		typeModel.setTypeCode(codeType);
		typeModel.setStatus("Active");
		List<BasCodeTypeModel> typeList = basCodeTypeManager.findByExample(typeModel);
		if(typeList.size() < 1){
			return null;
		}
		String typeuuid = typeList.get(0).getBasCodeTypeUuid();
		BasCodeDefModel defModel = new BasCodeDefModel();
		defModel.setDisplayValue(displayValue);
		defModel.setBasCodeTypeUuid(typeuuid);
		defModel.setStatus("Active");
		List<BasCodeDefModel> defList = this.findByExample(defModel);
		if(defList.size() < 1){
			return null;
		}
		return defList.get(0).getCodeValue();
	}
	/**
	 * 根据codeTypeUuid查询某些值是否在codeValue中
	 * @param codeTypeUuid
	 * @param codeValue
	 * @return
	 */
	public List<BasCodeDefModel> queryCodeValueByCodeType(String codeTypeUuid,Object[] codeValue){
		List<BasCodeDefModel> basCodeDefModels = this.dao.createCommonQuery(BasCodeDefModel.class)
				.addCondition(Condition.eq(BasCodeDefModel.FieldNames.basCodeTypeUuid, codeTypeUuid))
				.addCondition(Condition.in(BasCodeDefModel.FieldNames.codeValue, codeValue))
				.addCondition(Condition.eq(BasCodeDefModel.FieldNames.status, CommonUtil.Active))
				.query();
		return basCodeDefModels;
	}

	@Override
	public boolean checkCodeDefType(String codeType,
			String codeDef) {
		if(codeDef == null ) return false;
		if(codeType == null) return false;
		
		CommonQuery<BasCodeTypeModel> cqType = this.dao.createCommonQuery(
				BasCodeTypeModel.class).addCondition(
				Condition.eq(BasCodeTypeModel.FieldNames.typeCode, codeType));
		cqType.addCondition(Condition.eq(
				BasCodeTypeModel.FieldNames.status, CommonUtil.Active));
		List<BasCodeTypeModel> bctmList = cqType.query();
		if(bctmList == null || bctmList.size() < 1) return false;
		BasCodeTypeModel bctm = bctmList.get(0);
		List<BasCodeDefModel> bcdmLst = new ArrayList<BasCodeDefModel>();
		if (bctm != null) {
			CommonQuery<BasCodeDefModel> cqDef = this.dao.createCommonQuery(
					BasCodeDefModel.class).addCondition(
					Condition.eq(BasCodeDefModel.FieldNames.basCodeTypeUuid,
							bctm.getBasCodeTypeUuid()));

			cqDef.addCondition(Condition.eq(
					BasCodeDefModel.FieldNames.codeValue, codeDef));
			bcdmLst = cqDef.query();
		}
		if(bcdmLst == null || bcdmLst.size() < 1) return false;
		
		return true;
	}
	
	
	@Autowired
	private SQLQueryManager sqlQueryManager;

	
	@Autowired
	private BasCustomerManager basCustomerManager;
	
	
	/**
	 * 
	* @Title: saveAllCntrAdminPrefix 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param webVO  List<BasCodeDefModel>返回类型 
	* @throws
	 */
	public List<BasCodeDefModel> saveAllCntrAdminPrefix(List<CntrAdminPrefixWebVO> webVoList){
		
		String sql = "SELECT BAS_CODE_TYPE_UUID AS typeId "
				+ "FROM bas_code_type "
				+" WHERE  "
				+"type_code='CNTR_ADMIN_RELEASE_NO_PREFIX' ";
	    List<Object[]> rows = this.sqlQueryManager.getSqlResultList(sql, "");
	    if(rows == null){
	    	return null;
	    }
	    Object[] row = rows.get(0);
	    String typeId = row[0].toString();
	    List<BasCodeDefModel> mList = new ArrayList<BasCodeDefModel>();
	    List<BasCodeDefModel> mListToDelete = new ArrayList<BasCodeDefModel>();
		for(CntrAdminPrefixWebVO  webVo : webVoList){
			BasCodeDefModel model = new BasCodeDefModel();
			model.setBasCodeDefUuid(webVo.getId());
			model.setBasCodeTypeUuid( typeId );
			model.setCodeValue(webVo.getPrefix());
			model.setStatus(webVo.getStatus());
			model.setRemark(webVo.getCntrAdminCode());
			
			//检查重复
			BasCodeDefModel example = new BasCodeDefModel();
			example.setBasCodeTypeUuid(typeId);
			example.setCodeValue(webVo.getPrefix());
			List<BasCodeDefModel> cList = this.findByExample(example);
			if(cList != null && cList.size() > 0 && webVo.getRowState().equals("Added")){
				//存在跳过
				break;
			}
			
			BasCustomerModel customerModel = 
			this.basCustomerManager.findByCustomerCode(webVo.getCntrAdminCode());
			if(customerModel != null){
				model.setDisplayValue(customerModel.getCustomerName());
				model.setDisplayValueEn(customerModel.getCustomerNameEn());
			}
			System.out.println(webVo.getRowState());
			
			if(webVo.getId() != null &&webVo.getRowState().equals("Deleted")){
				mListToDelete.add(model);
			}
			mList.add(model);
		}
		mList.removeAll(mListToDelete);
		this.dao.removeAll(mListToDelete);
		for(BasCodeDefModel model : mListToDelete){
			String deleqdSql = "DELETE FROM BAS_CODE_DEF WHERE BAS_CODE_DEF_UUID = '"+model.getBasCodeDefUuid()+"'";
			
			sqlQueryManager.executeSQL(deleqdSql, "", true);
		}
		
		return this.saveAll(mList);
	}

	@Override
	public BasCodeDefModel saveExcelTemplate(BasReleaseExcelTemplateQueryCondition template) {
		String typeSql = "SELECT type.bas_code_type_uuid "
				+ "FROM bas_code_type type "
				+" WHERE type.type_code = 'EXCEL_IMPORT_TEMPLATE_TYPE'";
		List<Object[]> typeRows = this.sqlQueryManager.getSqlResultList(typeSql, "");
		String typeId = "";
	    if(typeRows.size()!=0){
	    	Object[] row = typeRows.get(0);
	    	typeId = row[0].toString();
	    } else {
	    	return null;
	    }
		/* 查询是否有重复启用模板  */
		String sql = "SELECT * "
				+ "FROM bas_code_def bcd"
				+" WHERE bcd.bas_code_type_uuid ='" + typeId+ "'"
				+" and bcd.office_code='"+ template.getLinerCode() +"' "
				+" and bcd.status='Active'";
	    List<Object[]> rows = this.sqlQueryManager.getSqlResultList(sql, "");
	    if(rows.size()!=0){
	    	return null;
	    }
	    BasCodeDefModel newCodeDefModel = new BasCodeDefModel();
	    newCodeDefModel.setBasCodeTypeUuid(typeId);
	    newCodeDefModel.setOfficeCode(template.getLinerCode());
	    newCodeDefModel.setCodeValue(template.getLinerCode());
	    newCodeDefModel.setDisplayValue(template.getTemplateName());
	    newCodeDefModel.setStatus(template.getStatus());
	    newCodeDefModel.setRemark(template.getRemark());
		return this.save(newCodeDefModel);
	}
	
	@Override
	public BasCodeDefModel updateExcelTemplate(BasReleaseExcelTemplateQueryCondition template) {
	    List<BasCodeDefModel> basCodeDefModels = this.dao.createCommonQuery(BasCodeDefModel.class)
				.addCondition(Condition.eq(BasCodeDefModel.FieldNames.basCodeDefUuid, template.getDefId()))
				.query();
	    if(basCodeDefModels != null){
	    	BasCodeDefModel codeDefModel = (BasCodeDefModel)basCodeDefModels.get(0);
	    	codeDefModel.setOfficeCode(template.getLinerCode());
		    codeDefModel.setCodeValue(template.getLinerCode());
		    codeDefModel.setStatus(template.getStatus());
		    codeDefModel.setRemark(template.getRemark());
		    return this.save(codeDefModel);
	    }
		return null;
	}

	@Override
	public void removeExcelTemplateBy(String basCodeDefUuid) {
		List<BasCodeDefModel> basCodeDefModels = this.dao.createCommonQuery(BasCodeDefModel.class)
				.addCondition(Condition.eq(BasCodeDefModel.FieldNames.basCodeDefUuid, basCodeDefUuid))
				.query();
		if(basCodeDefModels != null){
			BasCodeDefModel codeDefModel = (BasCodeDefModel)basCodeDefModels.get(0);
			this.remove(codeDefModel);
		}
	}

	@Override
	public HashMap<String, Integer> getOffsetsByShipperCode(String shipperCode) {
		if(shipperCode == null || shipperCode == "") {
			shipperCode = "00041";//通用模板
		}
		String sql = "SELECT bcd.office_code, bcd.remark "
				+ "FROM bas_code_def bcd, bas_code_type type "
				+" WHERE type.type_code = 'EXCEL_IMPORT_TEMPLATE_TYPE'"
				+" and bcd.bas_code_type_uuid = type.bas_code_type_uuid"
				+" and (bcd.office_code='"+ shipperCode +"' or bcd.office_code='00041')"
				+" and bcd.status='Active'";
	    List<Object[]> rows = this.sqlQueryManager.getSqlResultList(sql, "");
	    if(rows.size()!=0){
	    	String remark = "";
	    	String allRemark = "";
	    	for(int i = 0; i < rows.size(); i++){
	    		Object[] row = rows.get(i);
	    		if(row[0].toString().equals("00041")){
	    			allRemark = row[1].toString();
	    		} else if(row[0].toString().equals(shipperCode)) {
	    			remark = row[1].toString();
	    		}
	    	}
	    	if(remark.equals("")){
	    		remark = allRemark;
	    	}
	    	HashMap<String, Integer> codeMap = new HashMap<String, Integer>();
	    	String[] codeList = remark.split(",");
	    	for(String codes:codeList){
	    		String[] code = codes.split(":");
	    		codeMap.put(code[0], Integer.valueOf(code[1]));
	    	}
	    	return codeMap;
	    }
		return null;
	}

	@Override
	public List<String> queryCodeValueByInput(String typeCode , String inputValue) {
		List<BasCodeTypeModel> bctmList = this.dao.createCommonQuery(BasCodeTypeModel.class)
				.addCondition(Condition.eq(BasCodeTypeModel.FieldNames.typeCode, typeCode))
				.addCondition(Condition.eq(BasCodeTypeModel.FieldNames.status, CommonUtil.Active))
				.query();
		BasCodeTypeModel bctm = null;
		if (bctmList != null && bctmList.size() > 0) {
			bctm = bctmList.get(0);
		}
		
		List<String> bcdCodeLst = new ArrayList<String>();
		List<BasCodeDefModel> bcdmLst = null ;
		if (bctm != null) {
			bcdmLst = this.dao.createCommonQuery(BasCodeDefModel.class)
					.addCondition(Condition.eq(BasCodeDefModel.FieldNames.basCodeTypeUuid,bctm.getBasCodeTypeUuid()))
					.addCondition(Condition.or(Condition.like(BasCodeDefModel.FieldNames.codeValue, "%"+inputValue+"%"),
												Condition.like(BasCodeDefModel.FieldNames.displayValue, "%"+inputValue+"%")))
					.addCondition(Condition.eq(BasCodeTypeModel.FieldNames.status, CommonUtil.Active))
					.query();
			
			if(bcdmLst.size() > 0){
				for(BasCodeDefModel bcdModel : bcdmLst){
					if(bcdModel.getCodeValue() != null && !"".equals(bcdModel.getCodeValue())){
						bcdCodeLst.add(bcdModel.getCodeValue());
					}
				}
			}
		}
		
		
		
		return bcdCodeLst;
	}

	@Override
	public BasCodeDefModel getCodeDefModelByCodeTypeValue(String codeType, String codeValue,String status) {
		if(codeValue == null || codeType== null){
			return null;
		}
		BasCodeTypeModel typeModel = new BasCodeTypeModel();
		typeModel.setTypeCode(codeType);
		typeModel.setStatus(status);
		List<BasCodeTypeModel> typeList = basCodeTypeManager.findByExample(typeModel);
		if(typeList.size() < 1){
			return null;
		}
		String typeuuid = typeList.get(0).getBasCodeTypeUuid();
		BasCodeDefModel defModel = new BasCodeDefModel();
		defModel.setCodeValue(codeValue);
		defModel.setBasCodeTypeUuid(typeuuid);
		if(status!=null&&!"".equals(status)){
			defModel.setStatus(status);
		}
		List<BasCodeDefModel> defList = this.findByExample(defModel);
		if(defList.size() > 0){
			defModel =  defList.get(0);
		}
		return defModel;
	}
	
	public BasCodeDefModel getCodeValueByCodeType(String codeType,String displayEn){
		String codeValue = "";
		
		BasCodeDefModel bcdmById=new BasCodeDefModel();
		BasCodeTypeModel bcmById=new BasCodeTypeModel();
		bcmById.setTypeCode(codeType);
		List<BasCodeTypeModel> bcmsById=basCodeTypeManager.findByExample(bcmById);
		if(bcmsById.size() > 0 ){
			bcdmById.setBasCodeTypeUuid(bcmsById.get(0).getBasCodeTypeUuid());
			bcdmById.setDisplayValueEn(displayEn);
			bcdmById.setStatus(CommonUtil.Active);
			List<BasCodeDefModel> bcdms=this.findByExample(bcdmById);	
			if(bcdms.size() > 0 ){
				bcdmById = bcdms.get(0);
			}	
		}
		
		return bcdmById;
	}
	
	@Override
	public BasCodeDefModel  getPortAreaCodeByInput(String str){
		  //bas_code_def
	 
		  List<BasCodeDefModel> li=this.dao.createCommonQuery(BasCodeDefModel.class)
		  .addCondition(Condition.like(BasCodeDefModel.FieldNames.displayValue, "%"+str+"%"))
		  .addCondition(Condition.not(Condition.eq(BasCodeDefModel.FieldNames.status, "Cancel"))).query();  //状态：Active - 有效； Cancel - 作废
		  
		  if (li!=null &&li.size()>0){
			  BasCodeDefModel obj=li.get(0);
			  return obj;
		  }else{
			return  null;
		  }
		   
	  }
	
	@Override
	public BasCodeTypeByTypeCodeAndCodeValueQueryItem findByTypeCodeAndCodeValue(
			String typeCode, String codeValue, String officeCode) {
		BasCodeTypeByTypeCodeAndCodeValueQueryItem item = new BasCodeTypeByTypeCodeAndCodeValueQueryItem();
		List<BasCodeTypeByTypeCodeAndCodeValueQueryItem> bctItemList = this.dao
				.query(new BasCodeTypeByTypeCodeAndCodeValueQueryCondition(
						codeValue, typeCode, officeCode),
						BasCodeTypeByTypeCodeAndCodeValueQueryItem.class);
		if (bctItemList != null && bctItemList.size() > 0) {
			item = bctItemList.get(0);
		}
		return item;
	}
}
