package com.sinotrans.gd.wlp.basicdata.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.sinotrans.framework.core.model.BaseModel;
import com.sinotrans.framework.core.service.impl.BaseManagerImpl;
import com.sinotrans.framework.core.support.CommonQuery;
import com.sinotrans.framework.core.support.Condition;
import com.sinotrans.framework.core.util.EntityUtils;
import com.sinotrans.gd.wlp.basicdata.service.BasQueryUtilManager;
import com.sinotrans.gd.wlp.common.web.RcUtil;
import com.sinotrans.gd.wlp.system.entity.SessionContextUserEntity;
import com.sinotrans.gd.wlp.util.CommonUtil;

@Service
public class BasQueryUtilManagerImpl extends BaseManagerImpl implements
		BasQueryUtilManager {

	/*
	 * 已弃用,但仍保留,以兼容以前的代码,请使用getPropertyByCode方法替代 (non-Javadoc)
	 * 
	 * @see
	 * com.sinotrans.gd.wlp.basicdata.service.BasQueryUtilManager#getNameByCode
	 * (java.lang.String, java.lang.String, java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public String getNameByCode(String tableName, String code, String language) {

		String str = tableName.substring(3);
		String str1 = str.replaceFirst(str.substring(0, 1), str.substring(0, 1)
				.toLowerCase());
		String _code = str1 + "Code";
		String name = str1 + "Name";
		String nameEn = str1 + "NameEn";
		Map params = new HashMap<String, String>();
		params.put("modelName", tableName);
		params.put("keyProperty", _code);
		if (language.equals(CommonUtil.ZH_CN)) {
			params.put("valueProperty", name);
		} else if (language.equals(CommonUtil.EN_USA)) {
			params.put("valueProperty", nameEn);
		} else {
			return "ERROR:language未正确指定,请传入CN或EN";
		}
		params.put("keyValue", code);
		return getPropertyByCode(params);
	}

	/*
	 * 根据code的值查询某个字段的值
	 * 
	 * @author huangkf 2011-12-4
	 * 
	 * @param params 从前台传过来的参数对象
	 * 
	 * @return 指定的字段值
	 * 
	 * @throws Exception
	 * 
	 * @see
	 * com.sinotrans.gd.wlp.basicdata.service.BasQueryUtilManager#getPropertyByCode
	 * (java.util.Map)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public String getPropertyByCode(Map<String, String> params) {

		HashMap<String, String> paramsMap = new HashMap<String, String>(params);
		String modelName = paramsMap.get("modelName");
		if (modelName.split("\\.").length > 1) {
			return "Error:参数modelName错误,请不要加包名";
		}
		String keyProperty = paramsMap.get("keyProperty");
		String valueProperty = paramsMap.get("valueProperty");
		String keyValue = paramsMap.get("keyValue");
		boolean byOfficeCode = Boolean.parseBoolean(paramsMap
				.get("byOfficeCode"));
		String result = null;
		if (RcUtil.isEmpty(keyValue) || keyValue.equals("undefined")) {
			return "";
		}
		try {
			Class modelClass;
			if (modelName.split(".").length > 1) {
				modelClass = Class.forName(modelName);
			} else {
				modelClass = EntityUtils.getEntityClass(modelName);
			}
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
			commonQuery.addCondition(Condition.eq("status", "Active"));
			ArrayList<? extends BaseModel> modelList = (ArrayList<? extends BaseModel>) commonQuery
					.query();
			if (modelList.size() == 0) {
				return null;
			}
			if (modelList.size() == 1) {
				Method method = modelList.get(0).getClass().getMethod(
						"get" + firstToUppercase(valueProperty));
				for (BaseModel model : modelList) {
					result = (String) method.invoke(model);
				}
			} else {
				return "ERROR:查询到多条数据!";
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
		} catch (Throwable e) {
			e.printStackTrace();
			result = e.getMessage();
		}
		return result;
	}

	// 转为首字母为大写
	private static String firstToUppercase(String str) {
		String bufStr = str.substring(0, 1);
		return str.replaceFirst(bufStr, bufStr.toUpperCase());
	}

}
