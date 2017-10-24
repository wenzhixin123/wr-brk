/**   
* @Title: DWRForIreport.java 
* @Package com.superlink.extend.util 
* @Description: TODO(用一句话描述该文件做什么) 
* @author A18ccms A18ccms_gmail_com   
* @date 2014年1月15日 上午11:29:03 
* @version V1.0   
*/ 
package com.superlink.extend.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.sinotrans.framework.core.util.ContextUtils;

/** 
 * @ClassName: DWRForIreport 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author A18ccms a18ccms_gmail_com 
 * @date 2014年1月15日 上午11:29:03 
 *  
 */
public class DWRForIreport {
 
	/**
	 * 【描述：】模拟进行DWR方式调用
	 * @Title: call 
	 * @Author: jim.chen
	 * @Time: 2014年1月15日 上午11:47:10
	 * @param dwrFunctionName 例如：BasCustomerManager.getModelByCode
	 * @param args function的传入参数
	 * @return Object
	 */
	public Object call(String dwrFunctionName, Object... args){
		String[] names = dwrFunctionName.split("\\.");
		if(names.length < 2){
			return null;
		}
		int offset = dwrFunctionName.lastIndexOf(".");
		String className =dwrFunctionName.substring(0, offset);
		String methodName =dwrFunctionName.substring(offset+1,dwrFunctionName.length());
		Class beanClass=null;
		try {
			beanClass = Class.forName(className);
		} catch (ClassNotFoundException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		if(beanClass == null){
			return null;
		}
		Object entity = (Object)ContextUtils.getBeanOfType(beanClass);
		//Class ownerClass = entity.getClass();  
	    Class[] argsClass = new Class[args.length];  
	     for (int i = 0, j = args.length; i < j; i++) {  
	         argsClass[i] = args[i].getClass();  
	     }  	  
	    Method method;
		try {
			method = beanClass.getMethod(methodName,argsClass);
			return method.invoke(entity, args);
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 catch (SecurityException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (NoSuchMethodException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}  
		return null;
	}
	

}
