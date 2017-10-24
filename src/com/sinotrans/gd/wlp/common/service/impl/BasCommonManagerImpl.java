/**
 * 
 */
package com.sinotrans.gd.wlp.common.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sinotrans.framework.common.service.SelectCodeManager;
import com.sinotrans.framework.common.support.QueryInfo;
import com.sinotrans.framework.common.support.SelectCodeData;
import com.sinotrans.framework.core.model.BaseModel;
import com.sinotrans.framework.core.service.impl.BaseManagerImpl;
import com.sinotrans.framework.core.support.CommonQuery;
import com.sinotrans.framework.core.support.Condition;
import com.sinotrans.framework.core.util.EntityUtils;
import com.sinotrans.framework.core.util.JSONDataUtils;
import com.sinotrans.gd.wlp.basicdata.model.BasBlobModel;
import com.sinotrans.gd.wlp.basicdata.query.CodeDefQueryItem;
import com.sinotrans.gd.wlp.basicdata.service.BasBlobManager;
import com.sinotrans.gd.wlp.basicdata.service.BasCodeDefManager;
import com.sinotrans.gd.wlp.common.entity.BasOption;
import com.sinotrans.gd.wlp.common.service.BasCommonManager;
import com.sinotrans.gd.wlp.common.service.SQLQueryManager;
import com.sinotrans.gd.wlp.common.web.RcUtil;
import com.sinotrans.gd.wlp.common.web.SinotransPageJson;
import com.sinotrans.gd.wlp.system.entity.SessionContextUserEntity;
import com.sinotrans.gd.wlp.system.service.SysMenuItemManager;
import com.sinotrans.gd.wlp.system.service.SysNewsManager;
import com.sinotrans.gd.wlp.system.service.SysOfficeManager;
import com.sinotrans.gd.wlp.system.service.SysUserManager;
import com.sinotrans.gd.wlp.util.CommonUtil;
import com.sinotrans.gd.wlp.util.StringUtil;


/**
 * @author sky
 * 
 * 
 *         系统的公用类
 * 
 */
@Service
public class BasCommonManagerImpl extends BaseManagerImpl implements
		BasCommonManager {

	private Logger log = Logger.getLogger(BasCommonManagerImpl.class);

	@Autowired
	private SysOfficeManager sysOfficeManager;

	@Autowired
	private SysMenuItemManager sysMenuItemManager;

	@Autowired
	private SQLQueryManager sqlQueryManager;

	@Autowired
	private SelectCodeManager selectCodeManager;

	@Autowired
	private SysUserManager sysUserManager;


	@Autowired
	private SysNewsManager sysNewsManager;
	
	@Autowired
	private BasBlobManager basBlobManager;

	@Autowired
	private BasCodeDefManager basCodeDefManager;
	@SuppressWarnings("unchecked")
	public List<BasOption> findOption(String id4name, String types,
			String office_code, String language) {
		List<BasOption> optionList = new ArrayList<BasOption>();
		if (CommonUtil.OPTION_OFFICE.equals(types)) {
			optionList = sysOfficeManager.getOption(id4name, office_code,
					language);
		} else if (CommonUtil.OPTION_MENU.equals(types)) {
			optionList = sysMenuItemManager.getOption(id4name, office_code,
					language);
		} else if (CommonUtil.OPTION_SYSTEM_CODE.equals(types)) {
			QueryInfo queryInfo = new QueryInfo();
			queryInfo.setQueryType(id4name);
			SelectCodeData scdObject = selectCodeManager
					.getSelectCodeData(queryInfo);

			if (scdObject != null && scdObject.getDataList().size() > 0) {
				List<CodeDefQueryItem> cdqiList = (List<CodeDefQueryItem>) scdObject
						.getDataList();
				for (CodeDefQueryItem cdqi : cdqiList) {
					String displayValue = (CommonUtil.ZH_CN.equals(language) ? cdqi
							.getDisplayValue()// 中英文判断
							: cdqi.getDisplayValueEn());
					optionList.add(new BasOption(cdqi.getCodeValue(),
							displayValue,
							(cdqi.getControlWord() != null && cdqi
									.getControlWord().substring(0, 1).equals(
											"1"))));// 控制位判断它是否默认选中
				}
			}
		} else if (CommonUtil.OPTION_USER.equals(types)) {
			optionList = sysUserManager.findUserByCode4Name(id4name, language);
		} /*else if (CommonUtil.SELECT_CODE_ALL_COUNTRY.equals(types)) {// 查询国家
			QueryInfo q = new QueryInfo();
			List<QueryField> queryFields = new ArrayList<QueryField>();
			q.setQueryType(CommonUtil.SELECT_CODE_ALL_COUNTRY);
			QueryField qf1 = new QueryField();
			qf1.setFieldName("countryCode");
			qf1.setFieldStringValue(id4name);
			queryFields.add(qf1);
			q.setQueryFields(queryFields);
			optionList = toOption(q, language);
		} else if (CommonUtil.SELECT_CODE_ALL_PROVINCE.equals(types)) {// 查询省份
			QueryInfo q = new QueryInfo();
			List<QueryField> queryFields = new ArrayList<QueryField>();
			q.setQueryType(CommonUtil.SELECT_CODE_ALL_PROVINCE);
			QueryField qf1 = new QueryField();
			qf1.setFieldName("provinceCode");
			qf1.setFieldStringValue(id4name);
			queryFields.add(qf1);
			q.setQueryFields(queryFields);
			optionList = toOption(q, language);
		} else if (CommonUtil.SELECT_CODE_ALL_CITY.equals(types)) {// 查询城市
			QueryInfo q = new QueryInfo();
			List<QueryField> queryFields = new ArrayList<QueryField>();
			q.setQueryType(CommonUtil.SELECT_CODE_ALL_CITY);
			QueryField qf1 = new QueryField();
			qf1.setFieldName("cityCode");
			qf1.setFieldStringValue(id4name);
			queryFields.add(qf1);
			q.setQueryFields(queryFields);
			optionList = toOption(q, language);
		} else if (CommonUtil.OPTION_APP_OF_PEOPLE.equals(types)) {
			optionList = forecastRecordManager.queryFunctionary(id4name,
					office_code);
		}*/
		return optionList;
	}
	/**
	 * 第二期用该方法来生成单号，根据数据字典的配置规则来生成单号
	 * 
	 * @param transactionType
	 * @param officeCode
	 * @return
	 */
	
	/**
	 * 获取表单订单号 在该WLP系统的第一期都用这个方法来生成，第二期就没用到了
	 */
	public String generateNumber(String orderType, String transactionType,
			String officeCode) {
		synchronized (BasCommonManagerImpl.class) {
			String dataResult = "";
			String yyyymmdd = RcUtil.date2String(
					sqlQueryManager.getSysDate(""), RcUtil.yyyyMMdd);
			// 采购订单编号
			if (CommonUtil.Number_Sourcing.equals(orderType)) { // 采购订单编号规则
				dataResult = sqlQueryManager
						.getColumnData(
								"select '"
										+ orderType
										+ yyyymmdd
										+ "'||lpad(to_number(max(substr(SO.submit_order_no,10, length(SO.submit_order_no)))+1),5,'0') from submit_order SO "
										+ "where so.submit_order_no like '"
										+ orderType + yyyymmdd + "%' "
										+ " and SO.TRANSACTION_TYPE='"
										+ transactionType
										+ "' and SO.OFFICE_CODE='" + officeCode
										+ "'", "", "");
				if ((!RcUtil.isEmpty(dataResult)) && dataResult.length() == 9) {// dataResult.length()==9
					// -->S20111109
					dataResult += "00001";
				}
			} else if (CommonUtil.Number_Inbound.equals(orderType)
					|| CommonUtil.Number_Transfer.equals(orderType)
					|| CommonUtil.Number_Processing.equals(orderType)
					|| CommonUtil.Number_Check.equals(orderType)
					|| CommonUtil.Number_Exit.equals(orderType)) { // 入库单编号规则
				// ||
				// 移位单单编号规则
				dataResult = sqlQueryManager
						.getColumnData(
								"select '"
										+ orderType
										+ yyyymmdd
										+ "'||lpad(to_number(max(substr(LO.LOGISTICS_ORDER_NO,10, length(LO.LOGISTICS_ORDER_NO)))+1),5,'0') from logistics_order LO "
										+ "where LO.LOGISTICS_ORDER_NO LIKE '"
										+ orderType + yyyymmdd + "%'"
										+ " and LO.TRANSACTION_TYPE='"
										+ transactionType
										+ "' and LO.OFFICE_CODE='" + officeCode
										+ "'", "", "");
				if ((!RcUtil.isEmpty(dataResult)) && dataResult.length() == 9) {// dataResult.length()==9
					// -->S20111109
					dataResult += "00001";
				}

			} else if (CommonUtil.TRANSACTIONTYPE_BON.equals(orderType)) {
				// 应急单编号规则
				dataResult = sqlQueryManager
						.getColumnData(
								"select '"
										+ CommonUtil.Number_BasOrderNo
										+ yyyymmdd
										+ "'||lpad(to_number(max(substr(BON.ORDER_NO,11, length(BON.ORDER_NO)))+1),3,'0') from bas_order_no BON "
										+ "where BON.ORDER_NO LIKE '"
										+ CommonUtil.Number_BasOrderNo
										+ yyyymmdd + "%' and BON.OFFICE_CODE='"
										+ officeCode + "'", "", "");
				if ((!RcUtil.isEmpty(dataResult)) && dataResult.length() == 10) {// dataResult.length()==10
					// -->S20111109
					dataResult += "001";
				}

			} else if (CommonUtil.Number_Application.equals(orderType)) {
				dataResult = sqlQueryManager
						.getColumnData(
								"select '"
										+ orderType
										+ yyyymmdd
										+ "'||lpad(to_number(max(substr(SO.submit_order_no,10, length(SO.submit_order_no)))+1),5,'0') from submit_order SO "
										+ "where so.submit_order_no like '"
										+ orderType + yyyymmdd + "%' "
										+ " and SO.TRANSACTION_TYPE='"
										+ transactionType
										+ "' and SO.OFFICE_CODE='" + officeCode
										+ "'", "", "");
				if ((!RcUtil.isEmpty(dataResult)) && dataResult.length() == 9) {
					dataResult += "00001";
				}
			} else if (CommonUtil.LOC_TASK_NO.equals(orderType)) {// 获取作业任务编号规则
				dataResult = sqlQueryManager
						.getColumnData(
								"select '"
										+ orderType
										+ yyyymmdd
										+ "'||lpad(to_number(max(substr(LT.LOC_TASK_NO,11, length(LT.LOC_TASK_NO)))+1),7,'0') from LOCATION_TASK LT where LT.LOC_TASK_NO LIKE '"
										+ orderType + yyyymmdd
										+ "%' and LT.OFFICE_CODE='"
										+ officeCode + "'", "", "");
				if ((!RcUtil.isEmpty(dataResult)) && dataResult.length() == 10
						&& CommonUtil.LOC_TASK_NO.equals(orderType)) {
					dataResult += "0000001";
				}

			} else if (CommonUtil.LOC_PLAN_CONFIG_CODE.equals(orderType)) {// 获取策略配置编号规则
				dataResult = sqlQueryManager
						.getColumnData(
								"select '"
										+ orderType
										+ yyyymmdd
										+ "'||lpad(to_number(max(substr(LPC.CONFIG_CODE,10, length(LPC.CONFIG_CODE)))+1),5,'0') from LOC_PLAN_CONFIG LPC where lpc.config_code like '"
										+ orderType + yyyymmdd
										+ "%' and LPC.OFFICE_CODE='"
										+ officeCode + "'", "", "");
				if ((!RcUtil.isEmpty(dataResult)) && dataResult.length() == 9) {// dataResult.length()==9
					// -->B20111119-
					dataResult += "00001";
				}
			} else if (CommonUtil.Number_Report_Template.equals(orderType)) {
				dataResult = sqlQueryManager
						.getColumnData(
								"select '"
										+ orderType
										+ "' || to_char(sysdate,'yymmdd') || lpad(to_number(max(substr(rt.template_code,9,length(rt.template_code))) + 1),5,'0')"
										+ " from report_template rt where rt.OFFICE_CODE = '"
										+ officeCode + "'", "", "");
				if ((!RcUtil.isEmpty(dataResult)) && dataResult.length() == 8) {// dataResult.length()==8
					dataResult += "00001";
				}
			} else if (CommonUtil.SVG.equals(orderType)) {
				dataResult = sqlQueryManager
						.getColumnData(
								"select '"
										+ orderType
										+ "' || to_char(sysdate,'yymmdd') || lpad(to_number(max(substr(bs.warehouse_code,9,length(bs.warehouse_code))) + 1),5,'0')"
										+ " from bas_warehouse bs where bs.OFFICE_CODE = '"
										+ officeCode + "'", "", "");
				if ((!RcUtil.isEmpty(dataResult)) && dataResult.length() == 8) {// dataResult.length()==8
					dataResult += "00001";
				}
			} else if (CommonUtil.LOC_PLAN_NO.equals(orderType)) {
				dataResult = sqlQueryManager
						.getColumnData(
								"select '"
										+ orderType
										+ yyyymmdd
										+ "'||lpad(to_number(max(substr(LP.LOC_PLAN_NO,11, length(LP.LOC_PLAN_NO)))+1),5,'0') from LOC_PLAN LP where  lp.loc_plan_no like '"
										+ orderType + yyyymmdd + "%'"
										+ " and LP.OFFICE_CODE='" + officeCode
										+ "'", "", "");
				if ((!RcUtil.isEmpty(dataResult)) && dataResult.length() == 10) {// dataResult.length()==9
					// -->LP20111109
					dataResult += "00001";
				}
			} else if (CommonUtil.Number_BARCODE.equals(orderType)) {
				dataResult = sqlQueryManager
						.getColumnData(
								"select '"
										+ orderType
										+ yyyymmdd
										+ "-'|| lpad(to_number(max(substr(b.barcode, 11, 7)) + 1),7,'0') as str from BARCODE b  where b.barcode like '"
										+ orderType + yyyymmdd
										+ "%' and b.office_code = '"
										+ officeCode + "'", "", "");// 获取条形码
				if ((!RcUtil.isEmpty(dataResult)) && dataResult.length() == 10) {// dataResult.length()==9
					// -->B20111119-
					dataResult += "0000001";
				}
			} else if (CommonUtil.Number_ForecastRecord_LS.equals(orderType)
					|| CommonUtil.Number_ForecastRecord_YY.equals(orderType)) {
				dataResult = sqlQueryManager
						.getColumnData(
								"select '"
										+ orderType
										+ yyyymmdd
										+ "'|| lpad(to_number(max(substr(FR.FORECAST_NO,11, length(FR.FORECAST_NO))) + 1),4,'0') as str from Forecast_Record FR where FR.FORECAST_NO like '"
										+ orderType + yyyymmdd
										+ "%' and FR.OFFICE_CODE = '"
										+ officeCode + "'", "", "");// 获取条形码
				if ((!RcUtil.isEmpty(dataResult)) && dataResult.length() == 10) {// dataResult.length()==9
					// -->FR20120105
					dataResult += "0001";
				}
			} else if (CommonUtil.TRANSACTIONTYPE_PANEL.equals(orderType)) {
				dataResult = sqlQueryManager
						.getColumnData(
								" select '"
										+ CommonUtil.Number_Panel
										+ "'|| to_char(sysdate,'yymmdd') || lpad(to_number(max(substr(bp.panel_package_no,8, length(bp.panel_package_no))) + 1),3,'0') as packageNo from BAS_PANEL bp where bp.office_code='"
										+ officeCode + "'", "", "");
				if (!RcUtil.isEmpty(dataResult) && dataResult.length() == 7) {
					dataResult += "001";
				}
			} else if (CommonUtil.Number_BILLING.equals(orderType)) {// 入库计费单号生成规则
				dataResult = sqlQueryManager
						.getColumnData(
								" select '"
										+ orderType
										+ yyyymmdd
										+ "' || lpad(to_number(max(substr(rc.receipts_charge_no,11, length(rc.receipts_charge_no))) + 1),4,'0') as packageNo from receipts_charge rc where rc.receipts_charge_no like'"
										+ orderType + yyyymmdd
										+ "%' and rc.office_code='"
										+ officeCode + "'", "", "");
				if (!RcUtil.isEmpty(dataResult) && dataResult.length() == 10) {
					dataResult += "0001";
				}
			}
			log.debug(dataResult);
			return dataResult;
		}
	}

	@Override
	public String getDataBaseDateFor_Yyyy_Mm_Dd() {
		String yyyyMmDd = RcUtil.date2String(sqlQueryManager.getSysDate(""),
				RcUtil.yyyy_MM_dd);
		return yyyyMmDd;
	}

	@Override
	public String getDataBaseDateFor_YMD_HMS() {
		String yyyyMmDdHhMmSs = RcUtil.date2String(sqlQueryManager.getSysDate(""),
				RcUtil.yyyy_MM_dd_HH_mm_ss);
		return yyyyMmDdHhMmSs;
	}

	@SuppressWarnings("unchecked")
	@Override
	public String getObjByProperty(Map<String, String> params) {

		HashMap<String, String> paramsMap = new HashMap<String, String>(params);
		String type = paramsMap.get("type");
		String keyProperty = paramsMap.get("keyProperty");
		String keyValue = paramsMap.get("keyValue");
		boolean byOfficeCode = Boolean.parseBoolean(paramsMap
				.get("byOfficeCode"));
		String result = "";
		try {
			Class modelClass = EntityUtils.getEntityClass(type);
			CommonQuery<? extends BaseModel> commonQuery = this.dao
					.createCommonQuery(modelClass);
			if (!RcUtil.isEmpty(keyValue)) {
				commonQuery.addCondition(Condition.eq(keyProperty, keyValue));
			} else {
				return "";
			}
			if (byOfficeCode) {
				String officeCode = SessionContextUserEntity.currentUser()
						.getOfficeCode();
				commonQuery
						.addCondition(Condition.eq("officeCode", officeCode));
			}
			ArrayList<? extends BaseModel> modelList = (ArrayList<? extends BaseModel>) commonQuery
					.query();
			if (modelList.size() == 0) {
				return "";
			} else {
				result = JSONDataUtils.buildJSONValue(modelList).toString();
			}

		} catch (SecurityException e) {
			e.printStackTrace();
			result = e.getMessage();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			result = e.getMessage();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			result = e.getMessage();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
			result = e.getMessage();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			result = e.getMessage();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
			result = e.getMessage();
		} catch (Exception e) {
			e.printStackTrace();
			result = e.getMessage();
		}
		return result;
	}

	public boolean ContainerNoCheckDigit(String ls_cntno) {
		/*
		 * 集装箱校验码校验规则： 集装箱号由4位公司代码和7位数字组成，其中第七位数字就是校验码。
		 * 首先将公司代码转换为数字，去掉11及其倍数，乘以(2^i)连加除以11，其余数为校验位。 A=10 B=12 C=13 D=14 E=15
		 * F=16 G=17 H=18 I=19 J=20 K=21 L=23 M=24 N=25 O=26 P=27 Q=28 R=29 S=30
		 * T=31 U=32 V=34 W=35 X=36 Y=37 Z=38
		 */
		boolean lb_ret = false;
		if (!StringUtil.isNull(ls_cntno) && ls_cntno.length() >= 11) {
			if (checkCtrno(ls_cntno)) {
				int bitNum, posAt, chkNum, totalNum;
				String numString = "1012131415161718192021232425262728293031323435363738";
				// A B C D E F G H I J K L M N O P Q R S T U V W X Y Z
				String letterString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
				totalNum = 0;
				char[] checkDigit = { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ',
						' ', ' ', ' ' };
				ls_cntno.getChars(0, 11, checkDigit, 0);
				for (int i = 0; i < 10; i++) {
					if ((int) checkDigit[i] < 58 && (int) checkDigit[i] > 47) { // ascii
						// 48
						// -
						// 57
						// 为
						// 0123456789
						bitNum = Integer.parseInt(Character
								.toString(checkDigit[i]));
					} else {
						posAt = letterString.indexOf((int) checkDigit[i]) * 2;
						bitNum = Integer.parseInt(numString.substring(posAt,
								posAt + 2));
					}
					totalNum = totalNum + bitNum
							* ((int) java.lang.Math.pow(2, i));
				}
				chkNum = (totalNum % 11);
				if ((int) checkDigit[10] < 58 && (int) checkDigit[10] > 47) {
					bitNum = Integer.parseInt(Character
							.toString(checkDigit[10]));
				} else {
					bitNum = 11; // 非数字
				}
				if (chkNum == 10) {
					if (bitNum == 0 || bitNum == 1)
						lb_ret = true;
				} else {
					if (chkNum == bitNum)
						lb_ret = true;
				}
			}
		}
		return lb_ret;
	}

	private boolean checkCtrno(String inCtrno) {
		/**
		 * 验证集装箱号格式是否正确
		 * 
		 * @param inCtrno一个集装箱号字符串参数
		 * @return 如果集装箱号格式正确则返回true，否则返回false
		 */
		boolean flag = false;
		Pattern p = Pattern.compile("^[A-Z]{4}+[0-9]{7}");
		Matcher m = null;
		m = p.matcher(inCtrno);
		flag = m.matches();
		return flag;
	}

	private Sheet getWorkbook(String fileName, InputStream stream) {
		Workbook wb = null;
		Sheet sheet = null;
		try {
			// excel 97~2003版本
			wb = new HSSFWorkbook(stream);
		} catch (Exception e) {
			try {
				wb = new XSSFWorkbook(fileName);// excel 2007版本
			} catch (IOException ee) {
				ee.printStackTrace();
			}
		}
		if (wb != null) {
			sheet = wb.getSheetAt(0);
		}
		return sheet;
	}

	@SuppressWarnings("unchecked")
	public List<? extends BaseModel> getRowListByProperty(
			Map<String, String> params) {

		HashMap<String, String> paramsMap = new HashMap<String, String>(params);
		String type = paramsMap.get("type");
		String keyProperty = paramsMap.get("keyProperty");
		String keyValue = paramsMap.get("keyValue");
		boolean byOfficeCode = Boolean.parseBoolean(paramsMap
				.get("byOfficeCode"));
		ArrayList<? extends BaseModel> modelList = new ArrayList<BaseModel>();
		try {
			Class modelClass = EntityUtils.getEntityClass(type);
			CommonQuery<? extends BaseModel> commonQuery = this.dao
					.createCommonQuery(modelClass);
			if (!RcUtil.isEmpty(keyValue)) {
				commonQuery.addCondition(Condition.eq(keyProperty, keyValue));
			}
			if (byOfficeCode) {
				String officeCode = SessionContextUserEntity.currentUser()
						.getOfficeCode();
				commonQuery
						.addCondition(Condition.eq("officeCode", officeCode));
			}
			modelList = (ArrayList<? extends BaseModel>) commonQuery.query();
		} catch (SecurityException e) {
			e.printStackTrace();
			modelList = null;
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			modelList = null;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			modelList = null;
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
			modelList = null;
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			modelList = null;
		} catch (InvocationTargetException e) {
			e.printStackTrace();
			modelList = null;
		} catch (Exception e) {
			e.printStackTrace();
			modelList = null;
		}
		return modelList;
	}

	@Override
	public SinotransPageJson importFileFactory(String path,
			String businessType, String businessFileType, String modelIds,
			String fileName, InputStream inputStream, String officeCode,
			String agentConsigneeCode, String agentConsigneeDesc,
			Map<String, Object> valueMap) throws Exception{
		SinotransPageJson spj = new SinotransPageJson();
		
		BasBlobModel basBlob = new BasBlobModel();
		basBlob.setPreDataUuid(UUID.randomUUID().toString().toLowerCase());
		basBlob.setTypeCode(businessType);
		basBlob.setTypeDesc(fileName);
		basBlob.setData(org.apache.commons.io.IOUtils.toByteArray(inputStream));
		basBlob = basBlobManager.save(basBlob);
		
		spj.setObject(basBlob);
		spj.setMsg("上传成功");
		spj.setResult(true);
		return spj;
	}

}