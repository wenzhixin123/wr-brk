package com.sinotrans.gd.wlp.basicdata.handler;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.map.ObjectMapper;

import com.sinotrans.framework.common.service.CommonQueryManager;
import com.sinotrans.framework.common.support.QueryData;
import com.sinotrans.framework.common.support.QueryField;
import com.sinotrans.framework.common.support.QueryInfo;
import com.sinotrans.framework.core.support.PagingInfo;
import com.sinotrans.framework.core.util.ContextUtils;
import com.sinotrans.gd.wlp.basicdata.entity.BasBomEntity;
import com.sinotrans.gd.wlp.basicdata.model.BasBomDetailModel;
import com.sinotrans.gd.wlp.basicdata.model.BasBomModel;
import com.sinotrans.gd.wlp.basicdata.model.BasBomTypeModel;
import com.sinotrans.gd.wlp.basicdata.model.BasCityModel;
import com.sinotrans.gd.wlp.basicdata.model.BasCodeDefModel;
import com.sinotrans.gd.wlp.basicdata.model.BasCodeTypeModel;
import com.sinotrans.gd.wlp.basicdata.model.BasCountryModel;
import com.sinotrans.gd.wlp.basicdata.model.BasCtrTypeModel;
import com.sinotrans.gd.wlp.basicdata.model.BasCurrencyModel;
import com.sinotrans.gd.wlp.basicdata.model.BasCustomerModel;
import com.sinotrans.gd.wlp.basicdata.model.BasDangerModel;
import com.sinotrans.gd.wlp.basicdata.model.BasExchangeRateModel;
import com.sinotrans.gd.wlp.basicdata.model.BasFileManageModel;
import com.sinotrans.gd.wlp.basicdata.model.BasPanelTypeModel;
import com.sinotrans.gd.wlp.basicdata.model.BasProjectModel;
import com.sinotrans.gd.wlp.basicdata.model.BasProvinceModel;
import com.sinotrans.gd.wlp.basicdata.model.BasSiteModel;
import com.sinotrans.gd.wlp.basicdata.model.BasUnitModel;
import com.sinotrans.gd.wlp.basicdata.service.BasBargainManager;
import com.sinotrans.gd.wlp.basicdata.service.BasBlobManager;
import com.sinotrans.gd.wlp.basicdata.service.BasBomDetailManager;
import com.sinotrans.gd.wlp.basicdata.service.BasBomManager;
import com.sinotrans.gd.wlp.basicdata.service.BasBomTypeManager;
import com.sinotrans.gd.wlp.basicdata.service.BasCityManager;
import com.sinotrans.gd.wlp.basicdata.service.BasCodeDefManager;
import com.sinotrans.gd.wlp.basicdata.service.BasCodeTypeManager;
import com.sinotrans.gd.wlp.basicdata.service.BasContactManager;
import com.sinotrans.gd.wlp.basicdata.service.BasCountryManager;
import com.sinotrans.gd.wlp.basicdata.service.BasCtrTypeManager;
import com.sinotrans.gd.wlp.basicdata.service.BasCurrencyManager;
import com.sinotrans.gd.wlp.basicdata.service.BasCustomerManager;
import com.sinotrans.gd.wlp.basicdata.service.BasDangerManager;
import com.sinotrans.gd.wlp.basicdata.service.BasExchangeRateManager;
import com.sinotrans.gd.wlp.basicdata.service.BasFileManageManager;
import com.sinotrans.gd.wlp.basicdata.service.BasPanelManager;
import com.sinotrans.gd.wlp.basicdata.service.BasPanelTypeManager;
import com.sinotrans.gd.wlp.basicdata.service.BasProjectManager;
import com.sinotrans.gd.wlp.basicdata.service.BasProvinceManager;
import com.sinotrans.gd.wlp.basicdata.service.BasSiteManager;
import com.sinotrans.gd.wlp.basicdata.service.BasUnitManager;
import com.sinotrans.gd.wlp.common.service.LogisticsOrderManager;
import com.sinotrans.gd.wlp.common.web.GeneralAjaxHandler;
import com.sinotrans.gd.wlp.common.web.RcUtil;
import com.sinotrans.gd.wlp.common.web.SinotransDataGrid;
import com.sinotrans.gd.wlp.common.web.SinotransPageJson;
import com.sinotrans.gd.wlp.system.entity.OfficeTree;
import com.sinotrans.gd.wlp.util.JsonUtil;

public class BasicdataHandler extends GeneralAjaxHandler {

	private BasCountryManager basCountryManager = ContextUtils
			.getBeanOfType(BasCountryManager.class);
	private BasProvinceManager basProvinceManager = ContextUtils
			.getBeanOfType(BasProvinceManager.class);
	private BasCityManager basCityManager = ContextUtils
			.getBeanOfType(BasCityManager.class);
	private BasSiteManager basSiteManager = ContextUtils
			.getBeanOfType(BasSiteManager.class);
	private BasCodeDefManager basCodeDefManager = ContextUtils
			.getBeanOfType(BasCodeDefManager.class);
	private BasCodeTypeManager basCodeTypeManager = ContextUtils
			.getBeanOfType(BasCodeTypeManager.class);
	private BasCtrTypeManager basCtrTypeManager = ContextUtils
			.getBeanOfType(BasCtrTypeManager.class);
	private BasCurrencyManager basCurrencyManager = ContextUtils
			.getBeanOfType(BasCurrencyManager.class);
	private BasDangerManager basDangerManager = ContextUtils
			.getBeanOfType(BasDangerManager.class);
	private BasExchangeRateManager basExchangeRateManager = ContextUtils
			.getBeanOfType(BasExchangeRateManager.class);
	private BasFileManageManager basFileManageManager = ContextUtils
			.getBeanOfType(BasFileManageManager.class);
	private BasProjectManager basProjectManager = ContextUtils
			.getBeanOfType(BasProjectManager.class);
	private BasUnitManager basUnitManager = ContextUtils
			.getBeanOfType(BasUnitManager.class);
	private BasBomTypeManager basBomTypeManager = ContextUtils
			.getBeanOfType(BasBomTypeManager.class);
	private BasBomManager basBomManager = ContextUtils
			.getBeanOfType(BasBomManager.class);
	private BasBomDetailManager basBomDetailManager = ContextUtils
			.getBeanOfType(BasBomDetailManager.class);
	private BasPanelManager basPanelManager = ContextUtils
			.getBeanOfType(BasPanelManager.class);
	private BasPanelTypeManager basPanelTypeManager = ContextUtils
			.getBeanOfType(BasPanelTypeManager.class);
	private BasBlobManager basWarehoseManager = ContextUtils
			.getBeanOfType(BasBlobManager.class);
	private BasCustomerManager basCustomerManager = ContextUtils
			.getBeanOfType(BasCustomerManager.class);
	private BasContactManager basContactManager = ContextUtils
			.getBeanOfType(BasContactManager.class);
	private BasBargainManager basBargainManager = ContextUtils
			.getBeanOfType(BasBargainManager.class);
/*	private BargainRateManager bargainRateManager = ContextUtils
			.getBeanOfType(BargainRateManager.class);*/
	private LogisticsOrderManager logisticsOrderManager = ContextUtils
			.getBeanOfType(LogisticsOrderManager.class);
	private CommonQueryManager commonQueryManager = ContextUtils
			.getBeanOfType(CommonQueryManager.class);
	
	// 国家信息
	public void saveBasCountry(String params) throws Exception {

		String jsonResult = request.getParameter("jsonResult");
		String json= logisticsOrderManager.getBase642Ojbect(jsonResult);
		ObjectMapper om = new ObjectMapper();
		BasCountryModel[] models = om.readValue(json,
				BasCountryModel[].class);
		basCountryManager.saveAll(Arrays.asList(models));
	}

	// 省份信息
	public void saveBasProvince(String params) throws Exception {

		String jsonResult = request.getParameter("jsonResult");
		String json= logisticsOrderManager.getBase642Ojbect(jsonResult);
		ObjectMapper om = new ObjectMapper();
		BasProvinceModel[] models = om.readValue(json,
				BasProvinceModel[].class);
		basProvinceManager.saveAll(Arrays.asList(models));

	}

	// 城市信息
	public void saveBasCity(String params) throws Exception {

		String jsonResult = request.getParameter("jsonResult");
		String json= logisticsOrderManager.getBase642Ojbect(jsonResult);
		ObjectMapper om = new ObjectMapper();
		BasCityModel[] models = om.readValue(json, BasCityModel[].class);
		basCityManager.saveAll(Arrays.asList(models));

	}

	// 地点信息
	public void saveBasSite(String params) throws Exception {

		String jsonResult = request.getParameter("jsonResult");
		String json= logisticsOrderManager.getBase642Ojbect(jsonResult);
		ObjectMapper om = new ObjectMapper();
		BasSiteModel[] models = om.readValue(json, BasSiteModel[].class);
		basSiteManager.saveAll(Arrays.asList(models));

	}

	// 数据字典
	public void saveBasCodeDef(String params) throws Exception {

		String jsonResult = request.getParameter("jsonResult");
		String json= logisticsOrderManager.getBase642Ojbect(jsonResult);
		ObjectMapper om = new ObjectMapper();
		BasCodeDefModel[] models = om.readValue(json,
				BasCodeDefModel[].class);
		basCodeDefManager.saveAll(Arrays.asList(models));

	}

	// 数据字典类型
	public void saveBasCodeType(String params) throws Exception {

		String jsonResult = request.getParameter("jsonResult");
		String json= logisticsOrderManager.getBase642Ojbect(jsonResult);
		ObjectMapper om = new ObjectMapper();
		BasCodeTypeModel[] models = om.readValue(json,
				BasCodeTypeModel[].class);
		basCodeTypeManager.saveAll(Arrays.asList(models));

	}

	// 箱型表
	public void saveBasCtrType(String params) throws Exception {

		String jsonResult = request.getParameter("jsonResult");
		String json= logisticsOrderManager.getBase642Ojbect(jsonResult);
		ObjectMapper om = new ObjectMapper();
		BasCtrTypeModel[] models = om.readValue(json,
				BasCtrTypeModel[].class);
		basCtrTypeManager.saveAll(Arrays.asList(models));

	}

	// 币别信息
	public void saveBasCurrency(String params) throws Exception {

		String jsonResult = request.getParameter("jsonResult");
		String json= logisticsOrderManager.getBase642Ojbect(jsonResult);
		ObjectMapper om = new ObjectMapper();
		BasCurrencyModel[] models = om.readValue(json,
				BasCurrencyModel[].class);
		basCurrencyManager.saveAll(Arrays.asList(models));

	}

	// 危险品参数表
	public void saveBasDanger(String params) throws Exception {

		String jsonResult = request.getParameter("jsonResult");
		String json= logisticsOrderManager.getBase642Ojbect(jsonResult);
		ObjectMapper om = new ObjectMapper();
		BasDangerModel[] models = om.readValue(json,
				BasDangerModel[].class);
		basDangerManager.saveAll(Arrays.asList(models));

	}

	// 汇率信息
	public void saveBasExchangeRate(String params) throws Exception {

		String jsonResult = request.getParameter("jsonResult");
		String json= logisticsOrderManager.getBase642Ojbect(jsonResult);
		ObjectMapper om = new ObjectMapper();
		om.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
		BasExchangeRateModel[] models = om.readValue(json,
				BasExchangeRateModel[].class);
		basExchangeRateManager.saveAll(Arrays.asList(models));

	}
	
	// 费目代码
	/*public void saveFeeItem(String params) throws Exception {

		String jsonResult = request.getParameter("jsonResult");
		String json= logisticsOrderManager.getBase642Ojbect(jsonResult);
		ObjectMapper om = new ObjectMapper();
		om.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
		DcsCalcItemModel[] models = om.readValue(json,
				DcsCalcItemModel[].class);
		dcsCalcItemManager.saveAll(Arrays.asList(models));

	}*/

	// 文件管理表
	public void saveBasFileManage(String params) throws Exception {

		String jsonResult = request.getParameter("jsonResult");
		String json= logisticsOrderManager.getBase642Ojbect(jsonResult);
		ObjectMapper om = new ObjectMapper();
		BasFileManageModel[] models = om.readValue(json,
				BasFileManageModel[].class);
		basFileManageManager.saveAll(Arrays.asList(models));

	}

	// 项目表
	public void saveBasProject(String params) throws Exception {
		String jsonResult = request.getParameter("jsonResult");
		String json= logisticsOrderManager.getBase642Ojbect(jsonResult);
		ObjectMapper om = new ObjectMapper();
		BasProjectModel[] models = om.readValue(json,
				BasProjectModel[].class);
		basProjectManager.saveAll(Arrays.asList(models));

	}

	// 包装单位信息表
	public void saveBasUnit(String params) throws Exception {

		String jsonResult = request.getParameter("jsonResult");
		String json= logisticsOrderManager.getBase642Ojbect(jsonResult);
		ObjectMapper om = new ObjectMapper();
		BasUnitModel[] models = om.readValue(json, BasUnitModel[].class);
		basUnitManager.saveAll(Arrays.asList(models));

	}

	// BomType信息
	public void saveBasBomType(String params) throws Exception {
		String jsonResult = request.getParameter("jsonResult");
		String json= logisticsOrderManager.getBase642Ojbect(jsonResult);
		ObjectMapper om = new ObjectMapper();
		om.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
		BasBomTypeModel[] models = om.readValue(json,
				BasBomTypeModel[].class);
		basBomTypeManager.saveAll(Arrays.asList(models));
	}

	// 保存Bom信息
	public String saveBasBomData(String params) throws Exception {

		String jsonResult = request.getParameter("jsonResult");
		String json= logisticsOrderManager.getBase642Ojbect(jsonResult);
		BasBomEntity basBomEntity = new BasBomEntity();
		if (!RcUtil.isEmpty(jsonResult)) {
			basBomEntity = basBomManager.saveBasBom(json, getUser()
					.getOfficeCode());
		}
		return JsonUtil.beanToJson(basBomEntity);
	}

	// Bom信息
	public void saveBasBom(String params) throws Exception {
		String jsonResult = request.getParameter("jsonResult");
		String json= logisticsOrderManager.getBase642Ojbect(jsonResult);
		ObjectMapper om = new ObjectMapper();
		om.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
		BasBomModel[] models = om.readValue(json, BasBomModel[].class);
		basBomManager.saveAll(Arrays.asList(models));
	}

	// BomDetail信息
	public void saveBasBomDetail(String params) throws Exception {

		String jsonResult = request.getParameter("jsonResult");
		String json= logisticsOrderManager.getBase642Ojbect(jsonResult);
		ObjectMapper om = new ObjectMapper();
		om.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
		BasBomDetailModel[] models = om.readValue(json,
				BasBomDetailModel[].class);
		basBomDetailManager.saveAll(Arrays.asList(models));
	}

	// 获得指定数据词典类型的默认数据词典
	public String getDefaultCodeDefRowObj(String params) {
		String codeTypeUuid = request.getParameter("basCodeTypeUuid");
		return basCodeDefManager.getDefaultCodeDefJSON(codeTypeUuid);
	}

	// BasPanel信息
	public void saveBasPanel(String params) throws Exception {
		String jsonResult = request.getParameter("jsonResult");
		// 用正则删掉不存在的字段status
		/*
		 * Pattern p = Pattern.compile("\"status\":\"Pending\","); Matcher m =
		 * p.matcher(jsonResult); String jsonRs = m.replaceAll("");
		 */
		// ObjectMapper om = new ObjectMapper();
		// BasPanelModel[] models = om
		// .readValue(jsonResult, BasPanelModel[].class);
		// basPanelManager.saveAll(Arrays.asList(models));
		String json= logisticsOrderManager.getBase642Ojbect(jsonResult);
		basPanelManager.saveBasPanel(json, getUser().getOfficeCode());
	}

	// BasPanelType信息
	public void saveBasPanelType(String params) throws Exception {
		String jsonResult = request.getParameter("jsonResult");
		String json= logisticsOrderManager.getBase642Ojbect(jsonResult);
		ObjectMapper om = new ObjectMapper();
		BasPanelTypeModel[] models = om.readValue(json,
				BasPanelTypeModel[].class);
		basPanelTypeManager.saveAll(Arrays.asList(models));
	}

	// 查询出文件数据
	public void basicdata_findFileData(String params) throws Exception {
		String uuid = request.getParameter("uuid");
		byte[] b = basWarehoseManager.findFileDataByFk(uuid, getUser()
				.getOfficeCode());
		if (!RcUtil.isEmpty(b)) {
			response.getOutputStream().write(b);
			response.getOutputStream().close();
		} else {
			response.getWriter().print("该仓库没有地图！");
			response.getWriter().close();
		}
	}

	/**
	 * 生效BOM和BOM的详细信息
	 */
	public String validateBomAndBomDateil(String params) throws Exception {
		String jsonResult = request.getParameter("jsonResult");
		String json= logisticsOrderManager.getBase642Ojbect(jsonResult);
		BasBomEntity basBomEntity = new BasBomEntity();
		if (!RcUtil.isEmpty(jsonResult)) {
			basBomEntity = basBomManager.validateBomAndBomDateil(json,
					getUser().getOfficeCode());
		}
		return JsonUtil.beanToJson(basBomEntity);
	}

	/**
	 * 作废BOM和BOM下面的所有详细信息
	 */
	public String cancelBomAndBomDateil(String params) throws Exception {
		String jsonResult = request.getParameter("jsonResult");
		String json= logisticsOrderManager.getBase642Ojbect(jsonResult);
		BasBomEntity basBomEntity = new BasBomEntity();
		if (!RcUtil.isEmpty(jsonResult)) {
			basBomEntity = basBomManager.cancelBomAndBomDateil(json,
					getUser().getOfficeCode());
		}
		return JsonUtil.beanToJson(basBomEntity);
	}

	/**
	 * 返回特定的codeType数据
	 */
	public String codeTypeSpecial(String params) throws Exception {
		SinotransDataGrid sdg = basCodeTypeManager.codeTypeSpecial(
				getPagingInfo(), getUser().getOfficeCode());
		String lodList = JsonUtil.beanToJson(sdg);
		return lodList;
	}

	/**
	 * 返回根据特定的codeType查出codeDef 具有分页信息
	 */
	public String codeDefSpecial(String params) throws Exception {
		String basCodeTypeUuid = request.getParameter("basCodeTypeUuid");
		String codeValue = request.getParameter("codeValue");
		String codeNumber = request.getParameter("codeNumber");
		String modifiable = request.getParameter("modifiable");
		String status = request.getParameter("status");
		SinotransDataGrid sdg = basCodeDefManager.codeDefSpecial(
				basCodeTypeUuid, codeValue, codeNumber, modifiable, status,
				getPagingInfo(), getUser().getOfficeCode());
		String lodList = JsonUtil.beanToJson(sdg);
		return lodList;
	}

	// 客户信息
	public String saveBasCustomer(String params) throws Exception {
		String jsonResult = request.getParameter("jsonResult");
		String jsonResult1=logisticsOrderManager.getBase642Ojbect(jsonResult);
		SinotransPageJson spj = basCustomerManager.saveBasCustomer(
				jsonResult1);
		return JsonUtil.beanToJson(spj);
		
		
	}
	
	// 联系人信息
	public String saveBasContact(String params) throws Exception {
		String jsonResult = request.getParameter("jsonResult");
		 String jsonResult1=logisticsOrderManager.getBase642Ojbect(jsonResult);
		SinotransPageJson spj = basContactManager.saveBasContact(
				jsonResult1);
		return JsonUtil.beanToJson(spj);
	}

	// 合同信息
	public String saveBasBargain(String params) throws Exception {
		String jsonResult = request.getParameter("jsonResult");
		String jsonResult1=logisticsOrderManager.getBase642Ojbect(jsonResult);
		SinotransPageJson spj = basBargainManager.saveBasBargain(
				jsonResult1);
		return JsonUtil.beanToJson(spj);
	}

/*	// 费率与合同信息
	public void saveBargainRate(String params) throws Exception {
		String jsonResult = request.getParameter("jsonResult");
		String jsonResult1=logisticsOrderManager.getBase642Ojbect(jsonResult);
		ObjectMapper om = new ObjectMapper();
		om.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
		BargainRateModel[] models = om.readValue(jsonResult1,
				BargainRateModel[].class);
		bargainRateManager.saveAllBargainRate(Arrays.asList(models));

	}*/

	/**
	 * 查询合同名称
	 * 
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public String selectBasBargaintree(String params) throws Exception {
		String jsonResult = request.getParameter("jsonResult");
		List<OfficeTree> resultList = basBargainManager
				.getBasBargaintree(jsonResult);
		return JsonUtil.list2Json(resultList);
	}

	/**
	 * 根据合同的UUid找出所有的费率
	 * 
	 * @param params
	 * @return
	 * @throws Exception
	 */
/*	public String selectchargeRateUuide(String params) throws Exception {
		String jsonResult = request.getParameter("chargeRateUuid");
		SinotransDataGrid sUserm = bargainRateManager.selectchargeRateUuide(
				getPagingInfo(), jsonResult);
		String selectUser = JsonUtil.beanToJson(sUserm);
		return selectUser;
	}*/
	
	//查询出全部客户信息
	public String basCgetustomerAll(String params) throws Exception {
		List<BasCustomerModel> basCgetustomerAll= basCustomerManager.getAll();
		return JsonUtil.list2Json(basCgetustomerAll);
	}

	
	public String getCodeTypeByCondition(String params){
		List<String> fieldNameList = new ArrayList<String>();
		fieldNameList.add("status");
		fieldNameList.add("typeCode");
		fieldNameList.add("typeName");
		fieldNameList.add("modifiable");
		fieldNameList.add("officeCode");
		
		List<QueryField> queryFields = new ArrayList<QueryField>();
		for(String fieldName:fieldNameList){
			String fieldValue = request.getParameter(fieldName);
			if(StringUtils.isNotBlank(fieldValue)){
				QueryField queryField = new QueryField();
				queryField.setFieldName(fieldName);
				if("typeCode".equals(fieldName)
						||"typeName".equals(fieldName)){
					queryField.setFieldStringValue("%"+fieldValue+"%");
				}else{
					queryField.setFieldStringValue(fieldValue);
				}
				queryField.setOperator("=");
				queryFields.add(queryField);
			}
		}
		
		String str_maxLine = request.getParameter("rows");
		String str_page = request.getParameter("page");
		int maxLine = RcUtil.toInteger(str_maxLine);
		int pageNum = RcUtil.toInteger(str_page);
		PagingInfo pagingInfo = new PagingInfo();// 分页对象
		pagingInfo.setPageSize(maxLine);
		pagingInfo.setCurrentPage(pageNum);
		
		QueryInfo queryInfo = new QueryInfo();
		queryInfo.setQueryType("CodeTypeByCodeValueQuery");
		queryInfo.setQueryFields(queryFields);
		queryInfo.setPagingInfo(pagingInfo);
		
		String orderCol = request.getParameter("sort");
		String orderBy = request.getParameter("order");
		if(!RcUtil.isEmpty(orderCol)){
			queryInfo.setOrderBy(orderCol + " " + orderBy);
		}
		
		QueryData queryData = commonQueryManager.query(queryInfo);
		
		SinotransDataGrid sdg = new SinotransDataGrid();
		sdg.setRows(queryData.getDataList());
		PagingInfo pi = queryData.getPagingInfo();
		if (pi != null) {
			sdg.setPage(pi.getCurrentPage());
			sdg.setTotal(pi.getTotalRows());
		}
		
		return JsonUtil.beanToJson(sdg);
	}
}
