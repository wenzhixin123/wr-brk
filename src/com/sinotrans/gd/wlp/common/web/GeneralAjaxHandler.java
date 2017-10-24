package com.sinotrans.gd.wlp.common.web;

import java.lang.reflect.Method;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.sinotrans.framework.core.support.PagingInfo;
import com.sinotrans.gd.wlp.system.entity.SessionContextUserEntity;

public class GeneralAjaxHandler implements AjaxHandler {
	protected WebApplicationContext wac;
	protected HttpServletRequest request;
	protected HttpServletResponse response;
	protected HandleResult result = new HandleResult();

	@SuppressWarnings("unchecked")
	public HandleResult handle(HttpServletRequest request,
			HttpServletResponse response, String cmd, String param)
			throws Exception {
		this.request = request;
		this.response = response;

		wac = WebApplicationContextUtils
				.getRequiredWebApplicationContext(request.getSession()
						.getServletContext());

		Class c = this.getClass();
		Method[] methods = c.getMethods();
		for (int i = 0; i < methods.length; i++) {
			if (methods[i].getName().equals(cmd)) {
				result.setCanHandle(true);
				result.setHandleClass(c.getName());
				Method m = c.getMethod(cmd, String.class);
				result.setResult((String) m.invoke(this, param));
				break;
			}
		}
		return result;
	}

	/**
	 * 获取当前登录用户基本信息
	 * 
	 * @return
	 */
	protected SessionContextUserEntity getUser() {
		SessionContextUserEntity scue = SessionContextUserEntity.currentUser();
		return scue;
	}

	/**
	 * 获得客户端国家语言
	 * 
	 * @return
	 */
	protected String getLanguage() {
		String country = request.getLocale().getCountry();
		return country;
	}

	/**
	 * 获取页面出来的分页参数信息
	 * 
	 * @return
	 */
	protected PagingInfo getPagingInfo() {
		Map<String, String> parameters = RcUtil.getParameterMap(request);
		String str_maxLine = parameters.remove("rows");
		String str_page = parameters.remove("page");
		int maxLine = RcUtil.toInteger(str_maxLine);
		int pageNum = RcUtil.toInteger(str_page);
		PagingInfo pagingInfo = new PagingInfo();// 分页对象
		pagingInfo.setPageSize(maxLine);
		pagingInfo.setCurrentPage(pageNum);
		return pagingInfo;
	}
}
