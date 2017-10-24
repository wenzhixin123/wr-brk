package com.sinotrans.gd.wlp.basicdata.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Service;

import com.sinotrans.framework.core.model.BaseModel;
import com.sinotrans.framework.core.service.impl.BaseManagerImpl;
import com.sinotrans.framework.core.support.CommonQuery;
import com.sinotrans.framework.core.support.Condition;
import com.sinotrans.gd.wlp.basicdata.model.BasCountryModel;
import com.sinotrans.gd.wlp.basicdata.service.BasCountryManager;
import com.sinotrans.gd.wlp.basicdata.util.BasdataServiceUtil;
import com.sinotrans.gd.wlp.common.entity.BasOption;
import com.sinotrans.gd.wlp.common.web.BasOptionSupport;
import com.sinotrans.gd.wlp.common.web.RcUtil;
import com.sinotrans.gd.wlp.system.entity.SessionContextUserEntity;
import com.sinotrans.gd.wlp.util.CommonUtil;

@Service
public class BasCountryManagerImpl extends BaseManagerImpl implements
		BasCountryManager {

	public BasCountryModel get(String id) {
		return this.dao.get(BasCountryModel.class, id);
	}

	public List<BasCountryModel> getAll() {
		return this.dao.getAll(BasCountryModel.class);
	}

	public List<BasCountryModel> findByExample(BasCountryModel example) {
		return this.dao.findByExample(example);
	}

	public BasCountryModel save(BasCountryModel model) {
		return (BasCountryModel) this.dao.save(BasdataServiceUtil
				.beforeSave(model));
	}

	@SuppressWarnings("unchecked")
	public List<BasCountryModel> saveAll(Collection<BasCountryModel> models) {
		return (List<BasCountryModel>) this.dao.saveAll(BasdataServiceUtil
				.beforeSaveAll(models));
	}

	public void remove(BasCountryModel model) {
		this.dao.remove(model);
	}

	public void removeAll(Collection<BasCountryModel> models) {
		this.dao.removeAll(models);
	}

	public void removeByPk(String id) {
		this.dao.removeByPk(BasCountryModel.class, id);
	}

	public void removeAllByPk(Collection<String> ids) {
		this.dao.removeAllByPk(BasCountryModel.class, ids);
	}

	@Override
	public List<BasOption> getOptionsByCode(String code, String officeCode,
			String language) {
		// 或者使用 return BasOptionSupport.getOptions(code, officeCode, language,
		// "BasCountry");
		List<String> propertiesList = new ArrayList<String>();
		propertiesList.add("countryCode");
		propertiesList.add("countryName");
		propertiesList.add("countryNameEn");
		return BasOptionSupport.getOptionsByProperties(code, officeCode,
				language, "BasCountry", propertiesList);
	}

	@SuppressWarnings("unchecked")
	@Override
	public String getNameByCode(String tableName, String code, String language) {

		String str = tableName.substring(3);
		String str1 = str.replaceFirst(str.substring(0, 1), str.substring(0, 1)
				.toLowerCase());
		String _code = str1 + "Code";
		// String name=str1+"Name";
		// String nameEn=str1+"NameEn";
		// String cmdCode="get"+str+"Code";
		String cmdName = "get" + str + "Name";
		String cmdNameEn = "get" + str + "NameEn";
		SessionContextUserEntity scue = SessionContextUserEntity.currentUser();
		String officeCode = scue.getOfficeCode();
		String rs = "";
		try {
			// UniversalDao dao= ContextUtils.getBeanOfType(UniversalDao.class);
			Class modelClass = Class
					.forName("com.sinotrans.gd.wlp.basicdata.model."
							+ tableName + "Model");
			CommonQuery<? extends BaseModel> commonQuery = this.dao
					.createCommonQuery(modelClass);
			if (!RcUtil.isEmpty(code)) {
				commonQuery.addCondition(Condition.eq(_code, code));
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
			// Method mCode=modelList.get(0).getClass().getMethod(cmdCode);
			Method mName = modelList.get(0).getClass().getMethod(cmdName);
			Method mNameEn = modelList.get(0).getClass().getMethod(cmdNameEn);
			if (language.equals(CommonUtil.ZH_CN)) {
				for (BaseModel model : modelList) {
					rs = (String) mName.invoke(model);
				}
			}

			if (language.equals(CommonUtil.EN_USA)) {
				for (BaseModel model : modelList) {
					rs = (String) mNameEn.invoke(model);
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

		return rs;
	}

	@SuppressWarnings("unchecked")
	@Override
	public String getPropertyByCode(String tableName, String codeValue,
			String propertyName) {
		String str = tableName.substring(3);
		String str1 = str.replaceFirst(str.substring(0, 1), str.substring(0, 1)
				.toLowerCase());
		String _code = str1 + "Code";
		SessionContextUserEntity scue = SessionContextUserEntity.currentUser();
		String officeCode = scue.getOfficeCode();
		String rs = "";
		propertyName = propertyName.replaceFirst(propertyName.substring(0, 1),
				propertyName.substring(0, 1).toUpperCase());
		try {
			Class modelClass = Class
					.forName("com.sinotrans.gd.wlp.basicdata.model."
							+ tableName + "Model");
			CommonQuery<? extends BaseModel> commonQuery = this.dao
					.createCommonQuery(modelClass);
			if (!RcUtil.isEmpty(codeValue)) {
				commonQuery.addCondition(Condition.eq(_code, codeValue));
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
			Method mProperty = modelList.get(0).getClass().getMethod(
					"get" + propertyName);
			for (BaseModel model : modelList) {
				rs = (String) mProperty.invoke(model);
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

		return rs;
	}

}
