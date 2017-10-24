package com.sinotrans.gd.wlp.common.aop;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Table;


import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;

import com.sinotrans.framework.core.aop.BaseAspect;
import com.sinotrans.framework.core.model.OperationLog;
import com.sinotrans.framework.core.support.CustomBeanWrapper;
import com.sinotrans.framework.core.support.NewTransactionTemplate;
import com.sinotrans.framework.core.util.ContextUtils;
import com.sinotrans.framework.core.util.DateUtils;
import com.sinotrans.framework.core.util.ParameterUtils;
import com.sinotrans.gd.wlp.basicdata.model.DcsOperateLogModel;
import com.sinotrans.gd.wlp.basicdata.query.TableCommentQueryItem;
import com.sinotrans.gd.wlp.basicdata.service.DcsOperateLogManager;
import com.sinotrans.gd.wlp.system.entity.SessionContextUserEntity;

/**
 * 自动保存office_code
 */
@Aspect
public class SaveOfficeCodeDaoAspect extends BaseAspect {
	SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

	Calendar cal = Calendar.getInstance();
	/**
	 * 存储 table字段名称
	 */
	private static Map m = new HashMap();

	/**
	 * Before *Dao.save()
	 * 
	 * @param model
	 */
	@Before("execution(* *..dao.*Dao.save(..)) && args(model)")
	public void beforeSaveModel(JoinPoint jp, OperationLog model) {
		SessionContextUserEntity currentUser = SessionContextUserEntity.currentUser();
		if (currentUser == null) {
			return;
		}
		CustomBeanWrapper modelWrapper = new CustomBeanWrapper(model);
		if (!"DcsOperateLogModel".equals(model.getClass().getSimpleName())) {
			PrintValues(modelWrapper, model.getClass().getSimpleName());
		}
		if (modelWrapper.isWritableProperty("officeCode") && !ParameterUtils.isParamValid(modelWrapper.getPropertyValue("officeCode"))) {
			modelWrapper.setPropertyValue("officeCode", currentUser.getOfficeCode());
		}
	}

	/**
	 * Before *Dao.saveAll()
	 * 
	 * @param models
	 */
	@Before("execution(* *..dao.*Dao.saveAll(..)) && args(models)")
	public void beforeSaveAllModels(JoinPoint jp, Collection<? extends OperationLog> models) {
		SessionContextUserEntity currentUser = SessionContextUserEntity.currentUser();
		if (currentUser == null) {
			return;
		}

		for (OperationLog model : models) {
			CustomBeanWrapper modelWrapper = new CustomBeanWrapper(model);
			if (!"DcsOperateLogModel".equals(model.getClass().getSimpleName())) {
				PrintValues(modelWrapper, model.getClass().getSimpleName());
			}
			if (modelWrapper.isWritableProperty("officeCode") && !ParameterUtils.isParamValid(modelWrapper.getPropertyValue("officeCode"))) {
				modelWrapper.setPropertyValue("officeCode", currentUser.getOfficeCode());
			}
		}
	}

	private void PrintValues(CustomBeanWrapper modelWrapper, String classSimpleName) {
		String idName = modelWrapper.getPropertyValue("prrmaryKeyName").toString();
		String values = null == modelWrapper.getPropertyValue(idName) ? "" : modelWrapper.getPropertyValue(idName).toString();
		if (StringUtils.isNotBlank(values)) {
			Object obj = getManger(classSimpleName);

			Object obj1 = execGetMethod(obj, values);
			if (null != obj1) {
				String str = equlusObject(modelWrapper, obj1, idName);
				if (StringUtils.isNotBlank(str)) {
					System.out.println("-----------------------------------------------------");
					System.out.println(str);
					System.out.println("-----------------------------------------------------");
				}
			}
		}
	}

	/**
	 * 比较对象的值
	 * 
	 * @param model
	 * @param obj
	 * @param primaryKeyName
	 * @return
	 */
	private String equlusObject(CustomBeanWrapper modelWrapper, Object obj, String primaryKeyName) {
		StringBuffer sb = new StringBuffer();
		boolean b = false;

		
		
		return sb.toString();
	}

	private String parseValue(Object obj, boolean isDate) {
		if (obj instanceof Date) {
			// return DateUtils.formatDateTime((Date) obj);
			isDate = true;
			return ((Date) obj).getTime() + "";
		} else if (obj instanceof java.sql.Timestamp) {
			isDate = true;
			// return DateUtils.formatDateTime(new Date(((java.sql.Timestamp)
			// obj).getTime()));
			return ((java.sql.Timestamp) obj).getTime() + "";
		} else {
			isDate = false;
			return obj.toString();
		}
	}

	private String parseDateValue(Object obj) {
		try {
			return DateUtils.format(new Date(org.apache.commons.lang.math.NumberUtils.toLong(obj.toString())));
		} catch (Exception e) {
			e.printStackTrace();
			return obj.toString();
		}

	}

	/**
	 * 获取字段 的中文注释
	 * 
	 * @param obj
	 * @param fleidName
	 * @return
	 */
	private String getFleidComments(Object obj, String fleidName) {
		if (obj.getClass().isAnnotationPresent(Table.class)) {
			Table t = obj.getClass().getAnnotation(Table.class);
			if (this.m.containsKey(t.name())) {
				List<TableCommentQueryItem> list = (List<TableCommentQueryItem>) this.m.get(t.name());
				return getFileComment(list, fleidName);
			} else {
				/*DcsOperateLogManager m = (DcsOperateLogManager) ContextUtils.getBean("dcsOperateLogManager");
				TableCommentQueryCondition condition = new TableCommentQueryCondition();
				condition.setTableName(t.name());
				List<TableCommentQueryItem> list = m.getTableColuns(condition);
				if (null != list & list.size() > 0) {
					this.m.put(t.name(), list);
					return getFileComment(list, fleidName);
				}*/
			}
		}
		return "";
	}

	private String getFileComment(List<TableCommentQueryItem> list, String fleidName) {
		for (int i = 0; i < list.size(); i++) {
			TableCommentQueryItem item = list.get(i);
			String fName = item.getColumnName().replaceAll("_", "");
			if (fleidName.equalsIgnoreCase(fName)) {
				String comments = item.getComments();
				if(StringUtils.isNotBlank(comments)){
					if (comments.length() > 7) {
						comments = comments.substring(0, 7) + "...";
					}
					return comments;
				}
			}
		}
		return "";
	}

	private Object getManger(String className) {
		String name = className.replace("Model", "");
		String ch = name.substring(0, 1);
		ch = ch.toLowerCase() + name.substring(1);
		ch = ch.replace("Entity", "") + "Manager";
		return ContextUtils.getBean(ch);
	}

	private Object execGetMethod(Object object, String idValue) {
		Class ownerClass = object.getClass();
		java.lang.annotation.Annotation[] annotations = ownerClass.getAnnotations();
		for (int i = 0; i < annotations.length; i++) {
			System.out.println(annotations[i].toString());
			System.out.println(annotations[i].annotationType().getCanonicalName());
		}
		try {
			Method m1 = ownerClass.getDeclaredMethod("get", String.class);
			if(StringUtils.isNotBlank(idValue)){
				return m1.invoke(object, idValue);
			}else{
				return null;
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
		return null;
	}

	/**
	 * 保存日志
	 * 
	 * @param model
	 */
	private void saveLog(final DcsOperateLogModel model) {
		// model.setCreator(SessionContextUserEntity.currentUser().getUsername());
		new NewTransactionTemplate().execute(new TransactionCallback() {
			public Object doInTransaction(TransactionStatus arg0) {
				DcsOperateLogManager dcsOperateLogManager = (DcsOperateLogManager) ContextUtils.getBean("dcsOperateLogManager");
				dcsOperateLogManager.save(model);
				return null;
			}
		});
	}

	private boolean isSave(String jsonval) {
		if (StringUtils.isNotBlank(jsonval)) {
			net.sf.json.JSONObject jsonObect = net.sf.json.JSONObject.fromObject(jsonval);
			if (!jsonObect.isEmpty()) {
				if (jsonObect.keySet().size() == 1) {
					Object keyName = jsonObect.keySet().toArray()[0];
					if ("modifyTime".equals(keyName.toString())) {
						return false;
					}
				}
			}
		}
		return true;
	}

}
