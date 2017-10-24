package com.sinotrans.gd.wlp.basicdata.util;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.sinotrans.framework.core.model.BaseModel;
import com.sinotrans.gd.wlp.system.entity.SessionContextUserEntity;
import com.sinotrans.gd.wlp.util.StringUtil;

public class BasdataServiceUtil {
	// 在servi impl中,调用saveAll保存数据之前执行
	@SuppressWarnings("unchecked")
	public static Collection<? extends BaseModel> beforeSaveAll(
			Collection<? extends BaseModel> models) {

		List<? extends BaseModel> modelList = new ArrayList(models);
		try {
			// 如果是新增,则设置默认officeCode
			for (BaseModel baseModel1 : modelList) {
				if (BaseModel.ROW_STATE_ADDED.equals(baseModel1.getRowState())) {
					String officeCode = SessionContextUserEntity.currentUser()
							.getOfficeCode();
					for (BaseModel model : modelList) {
						model.getClass().getMethod("setOfficeCode",
								String.class).invoke(model, officeCode);
						String controlWord = (String) model.getClass()
								.getMethod("getControlWord").invoke(model);
						controlWord = StringUtil.fixKey(controlWord, 20, "0");
						model.getClass().getMethod("setControlWord",
								String.class).invoke(model, controlWord);
					}
				}
			}
			// 设置控制位
			for (BaseModel baseModel : modelList) {
				String controlWord;
				controlWord = (String) baseModel.getClass().getMethod(
						"getControlWord").invoke(baseModel);
				controlWord = StringUtil.fixKey(controlWord, 20, "0");
				baseModel.getClass().getMethod("setControlWord", String.class)
						.invoke(baseModel, controlWord);
			}
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
		}

		return modelList;
	}

	// 在servi impl中,调用save保存数据之前执行
	public static BaseModel beforeSave(BaseModel model) {
		try {
			// 补齐控制位
			String controlWord = (String) model.getClass().getMethod(
					"getControlWord").invoke(model);
			controlWord = StringUtil.fixKey(controlWord, 20, "0");
			model.getClass().getMethod("setControlWord", String.class).invoke(
					model, controlWord);

			// 如果是新增,则设置officeCode
			if (BaseModel.ROW_STATE_ADDED.equals(model.getRowState())) {
				String officeCode = SessionContextUserEntity.currentUser()
						.getOfficeCode();
				model.getClass().getMethod("setOfficeCode", String.class)
						.invoke(model, officeCode);
			}

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
		}

		return model;
	}
}
