package com.sinotrans.gd.wlp.common.web;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.sinotrans.framework.common.service.CommonQueryManager;
import com.sinotrans.framework.common.support.QueryData;
import com.sinotrans.framework.common.support.QueryField;
import com.sinotrans.framework.common.support.QueryInfo;
import com.sinotrans.framework.core.dao.UniversalDao;
import com.sinotrans.framework.core.model.BaseModel;
import com.sinotrans.framework.core.support.PagingInfo;
import com.sinotrans.framework.core.util.ContextUtils;
import com.sinotrans.gd.wlp.common.entity.BasOption;
import com.sinotrans.gd.wlp.common.service.BasCommonManager;
import com.sinotrans.gd.wlp.system.entity.SessionContextUserEntity;
import com.sinotrans.gd.wlp.util.JsonUtil;

/**
 * @author sky
 * 
 *         外运AjaxHandler 基础类
 * 
 */
public class SinotransAjaxHandler extends GeneralAjaxHandler {

	private Logger log = Logger.getLogger(SinotransAjaxHandler.class);

	/**
	 * 前台接收的参数下划线
	 */
	private String underline = "__";

	private final static String FALSE = "false";
	private final static String TRUE = "true";

	/**
	 * 处理系统所有单表查询请求
	 * 
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public String query(String params) throws Exception {
		String q = request.getParameter("q");// easyui 过滤条件
		String filter = request.getParameter("filter");// 过滤字段
		// all parameters
		Map<String, String> parameters = RcUtil.getParameterMap(request);
		// get query type
		// queryType 参数可以为一个Model 对象、也可以为一个 sql-query Name
		String queryType = parameters.remove("queryType");

		String str_maxLine = parameters.remove("rows");
		String str_page = parameters.remove("page");
		// paging parameters
		int maxLine = RcUtil.toInteger(str_maxLine);
		int pageNum = RcUtil.toInteger(str_page);
		PagingInfo pagingInfo = new PagingInfo();// 分页对象
		pagingInfo.setPageSize(maxLine);
		pagingInfo.setCurrentPage(pageNum);

		// orderby parameters
		String orderCol = parameters.remove("sort");
		String orderBy = parameters.remove("order");

		String condition = parameters.remove("__condition");// 获取参数条件操作符
		Map<String, String> conditionMap = getCondition(condition);
		// query fields left in parameters
		List<QueryField> queryFields = new ArrayList<QueryField>();

		String oc = parameters.remove("oc");
		if (!RcUtil.isEmpty(oc)) {// 带上officeCode 关联数据
			QueryField qfOfficeCode = new QueryField();
			qfOfficeCode.setFieldName("officeCode");
			qfOfficeCode.setFieldStringValue(getUser().getOfficeCode());
			queryFields.add(qfOfficeCode);
		}

		for (String parame : parameters.keySet()) {
			String obj = parameters.get(parame);// 所有的查询参数都必须增加__
			if (!RcUtil.isEmpty(obj) && parame.startsWith(underline)) {
				QueryField queryField = new QueryField();
				String fieldName = parame.substring(2, parame.length());
				queryField.setFieldName(fieldName);
				queryField.setFieldStringValue(obj);
				String operator = conditionMap.get(fieldName);
				if (!RcUtil.isEmpty(operator)) {
					queryField.setOperator(operator);
				}
				queryFields.add(queryField);
				parameters.remove(obj);// 并删除
			}
			if (isBetween && betweenList != null && betweenList.size() > 0) {// 有存在一个字段需要两个参数的情况
				for (String queryParams : betweenList) {
					String start = (queryParams + "__start");
					String end = (queryParams + "__end");
					start = parameters.get(start);
					end = parameters.get(end);
					if (!RcUtil.isEmpty(start) && !RcUtil.isEmpty(end)) {
						QueryField ge = new QueryField();
						ge.setFieldName(queryParams);
						ge.setFieldStringValue(start);
						ge.setFieldType(QueryField.FIELD_TYPE_DATE);
						ge.setOperator("dateBegin");// 查询操作符

						QueryField le = new QueryField();
						le.setFieldName(queryParams);
						le.setFieldStringValue(end);
						le.setFieldType(QueryField.FIELD_TYPE_DATE);
						le.setOperator("dateEnd");// 查询操作符

						queryFields.add(ge);
						queryFields.add(le);
					}
				}
				betweenList.clear();// 清楚数据
				isBetween = false;
			}
		}

		// 处理页面传输过来的过滤条件
		if (!RcUtil.isEmpty(q) && !RcUtil.isEmpty(filter)) {
			QueryField qf = new QueryField();
			qf.setFieldName(filter);
			qf.setFieldType(QueryField.FIELD_TYPE_STRING);
			qf.setFieldStringValue(q);
			qf.setOperator("ilikeAnywhere");// 模糊查询，忽略大小写
			queryFields.add(qf);
		}

		// invoke FROS CommonQuery.query
		QueryInfo queryInfo = new QueryInfo();
		queryInfo.setQueryType(queryType);
		if (queryFields != null && queryFields.size() > 0) {
			queryInfo.setQueryFields(queryFields);
		}
		if (!RcUtil.isEmpty(orderCol)) {
			String orderBy_string = (orderCol + " " + orderBy);
			queryInfo.setOrderBy(orderBy_string);
		}
		queryInfo.setPagingInfo(pagingInfo);
		QueryData queryData = ContextUtils.getBeanOfType(
				CommonQueryManager.class).query(queryInfo);

		// convert QueryData to SinotransDataGrid
		SinotransDataGrid sdg = new SinotransDataGrid();
		sdg.setRows(queryData.getDataList());
		PagingInfo pi = queryData.getPagingInfo();
		if (pi != null) {
			sdg.setPage(pi.getCurrentPage());
			sdg.setTotal(pi.getTotalRows());
		}
		return sdg.toString();
	}

	private boolean isBetween = false;
	private List<String> betweenList = new ArrayList<String>();

	/**
	 * 转换条件操作符
	 * 
	 * @param condition
	 * @return
	 */
	private Map<String, String> getCondition(String condition) {
		Map<String, String> conMap = new HashMap<String, String>();
		if (RcUtil.isEmpty(condition)) {
			return conMap;
		}
		String args[] = condition.split(":");
		if (args != null && args.length > 0) {
			for (String string : args) {
				String key_value[] = string.split("_");
				if (key_value != null && key_value.length >= 1) {
					log.debug(" key_value  :  " + key_value[0] + "    "
							+ key_value[1]);
					if ("between".equals(key_value[1])) {
						isBetween = true;
						betweenList.add(key_value[0]);// 添加到数组
					}
					conMap.put(key_value[0], key_value[1]);
				}
			}
		}
		return conMap;
	}

	public String queryOption(String params) throws Exception {
		SessionContextUserEntity user = getUser();
		String id4name = request.getParameter("q");
		String types = request.getParameter("types");
		String office_code = user.getOfficeCode();// 登录者officeCode
		String language = getLanguage();
		List<BasOption> optionList = ContextUtils.getBeanOfType(
				BasCommonManager.class).findOption(id4name, types, office_code,
				language);

		String options = JsonUtil.list2Json(optionList);
		if (options.indexOf(TRUE) == -1) {
			options = options.replaceFirst(FALSE, TRUE);
		}
		return options;
	}

	// 字段唯一性校验
	@SuppressWarnings("unchecked")
	public String fieldValidate(String params) {
		String types = request.getParameter("queryType");
		String field = request.getParameter("fieldName");
		String originalValue = request.getParameter("originalValue");
		String rs = TRUE;
		String inputValue = request.getParameter(field);
		try {
			Class<BaseModel> modelClass = (Class<BaseModel>) Class
					.forName("com.sinotrans.gd.wlp.basicdata.model." + types);
			BaseModel modelObj = modelClass.getConstructor().newInstance();
			modelClass.getMethod("set" + firstToUppercase(field), String.class)
					.invoke(modelObj, inputValue);
			List<BaseModel> modelList = ContextUtils.getBeanOfType(
					UniversalDao.class).findByExample(modelObj);
			if (RcUtil.isEmpty(originalValue)) {
				if (modelList.size() > 0)
					rs = FALSE;
			} else {
				if (modelList.size() == 0
						|| (modelList.size() > 0 && inputValue
								.equals(originalValue))) {
					rs = TRUE;
				} else {
					rs = FALSE;
				}
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		}
		return rs;
	}

	private static String firstToUppercase(String str) {
		String bufStr = str.substring(0, 1);
		return str.replaceFirst(bufStr, bufStr.toUpperCase());
	}
}