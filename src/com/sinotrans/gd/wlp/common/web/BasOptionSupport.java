package com.sinotrans.gd.wlp.common.web;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import com.sinotrans.framework.core.dao.UniversalDao;
import com.sinotrans.framework.core.model.BaseModel;
import com.sinotrans.framework.core.support.CommonQuery;
import com.sinotrans.framework.core.support.Condition;
import com.sinotrans.framework.core.util.ContextUtils;
import com.sinotrans.gd.wlp.common.entity.BasOption;
import com.sinotrans.gd.wlp.util.CommonUtil;

public class BasOptionSupport {
	@SuppressWarnings( { "static-access", "unchecked" })
	public static List<BasOption> getOptions(String code, String officeCode,
			String language, String tableName) {

		ArrayList<BasOption> optionList = new ArrayList<BasOption>();

		String str = tableName.substring(3);
		String str1 = str.replaceFirst(str.substring(0, 1), str.substring(0, 1)
				.toLowerCase());
		String _code = str1 + "Code";
		String name = str1 + "Name";
		@SuppressWarnings("unused")
		String nameEn = str1 + "NameEn";
		String cmdCode = "get" + str + "Code";
		String cmdName = "get" + str + "Name";
		String cmdNameEn = "get" + str + "NameEn";
		try {
			UniversalDao dao = ContextUtils.getBeanOfType(UniversalDao.class);
			Class modelClass = Class
					.forName("com.sinotrans.gd.wlp.basicdata.model."
							+ tableName + "Model");
			optionList = new ArrayList<BasOption>();
			CommonQuery<? extends BaseModel> commonQuery = dao
					.createCommonQuery(modelClass);

			if (!RcUtil.isEmpty(code)) {
				commonQuery.addCondition(Condition.like(_code, code + "%").or(
						Condition.like(name, code + "%")));
			}
			if (!RcUtil.isEmpty(officeCode)) {
				commonQuery
						.addCondition(Condition.eq("officeCode", officeCode));
			}
			ArrayList<? extends BaseModel> modelList = (ArrayList<? extends BaseModel>) commonQuery
					.query();
			if (modelList.size() == 0) {
				return null;
			}
			Method mCode = modelList.get(0).getClass().getMethod(cmdCode);
			Method mName = modelList.get(0).getClass().getMethod(cmdName);
			Method mNameEn = modelList.get(0).getClass().getMethod(cmdNameEn);
			if (language.equals(CommonUtil.ZH_CN)) {
				for (BaseModel model : modelList) {
					optionList.add(new BasOption((String) mCode.invoke(model),
							(String) mName.invoke(model)));
				}
			}

			if (language.equals(CommonUtil.EN_USA)) {
				for (BaseModel model : modelList) {
					optionList.add(new BasOption((String) mCode.invoke(model),
							(String) mNameEn.invoke(model)));
				}
			}
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}

		return optionList;
	}

	/*
	 * 如果某张表的code和name属性的命名不符合getOptions的规范,可以使用以下方法 添加了propertiesList 注意:
	 * propertiesList的顺序一定要按照code,name,nameEn的来,否则虽然能查出数据,
	 * 但不会是预期的结果.约定优于配置,为减少点代码量和复杂度,按照约定来吧
	 */
	@SuppressWarnings( { "static-access", "unchecked" })
	public static List<BasOption> getOptionsByProperties(String code,
			String officeCode, String language, String tableName,
			List<String> propertiesList) {

		ArrayList<BasOption> optionList = new ArrayList<BasOption>();

		try {
			UniversalDao dao = ContextUtils.getBeanOfType(UniversalDao.class);
			Class<? extends BaseModel> modelClass = (Class<? extends BaseModel>) Class
					.forName("com.sinotrans.gd.wlp.basicdata.model."
							+ tableName + "Model");
			optionList = new ArrayList<BasOption>();
			CommonQuery<? extends BaseModel> commonQuery = dao
					.createCommonQuery(modelClass);
			commonQuery.addCondition(Condition.like(propertiesList.get(0),
					code + "%").or(
					Condition.like(propertiesList.get(1), code + "%")));
			if (!RcUtil.isEmpty(officeCode)) {
				commonQuery
						.addCondition(Condition.eq("officeCode", officeCode));
			}
			ArrayList<? extends BaseModel> modelList = (ArrayList<? extends BaseModel>) commonQuery
					.query();
			if (modelList.size() == 0) {
				return null;
			}
			Method mCode = modelList.get(0).getClass().getMethod(
					"get" + firstToUppercase(propertiesList.get(0)));
			Method mName = modelList.get(0).getClass().getMethod(
					"get" + firstToUppercase(propertiesList.get(1)));
			Method mNameEn = modelList.get(0).getClass().getMethod(
					"get" + firstToUppercase(propertiesList.get(2)));
			if (language.equals(CommonUtil.ZH_CN)) {
				for (BaseModel model : modelList) {
					optionList.add(new BasOption((String) mCode.invoke(model),
							(String) mName.invoke(model)));
				}
			}

			if (language.equals(CommonUtil.EN_USA)) {
				for (BaseModel model : modelList) {
					optionList.add(new BasOption((String) mCode.invoke(model),
							(String) mNameEn.invoke(model)));
				}
			}
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}

		return optionList;
	}

	private static String firstToUppercase(String str) {
		String bufStr = str.substring(0, 1);
		return str.replaceFirst(bufStr, bufStr.toUpperCase());
	}

}
