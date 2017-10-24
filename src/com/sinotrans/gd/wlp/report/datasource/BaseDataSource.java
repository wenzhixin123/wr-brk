/**   
* @Title: baseDataSource.java 
* @Package com.sinotrans.gd.wlp.report.datasource 
* @Description: TODO(用一句话描述该文件做什么) 
* @author A18ccms A18ccms_gmail_com   
* @date 2013年12月17日 下午4:27:57 
* @version V1.0   
*/ 
package com.sinotrans.gd.wlp.report.datasource;

import java.util.Map;

/** 
 * @ClassName: baseDataSource 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author A18ccms a18ccms_gmail_com 
 * @date 2013年12月17日 下午4:27:57 
 *  
 */
public interface BaseDataSource<T> {
	
	/**
	 * 【描述：】通过模板ID去获取数据源
	 * @Title: getDataSource 
	 * @Author: jim.chen
	 * @Time: 2013年12月17日 下午6:33:26
	 * @param controlParam
	 * @param modelId
	 * @return T
	 */
	T  getDataSource(String modelId, Map<String, Object> parameters);
	
	/**
	 * 【描述：】返回一个空数据源
	 * @Title: getEmptyDataSource 
	 * @Author: jim.chen
	 * @Time: 2013年12月17日 下午6:33:46
	 * @return T
	 */
	T getEmptyDataSource();
		 
}
