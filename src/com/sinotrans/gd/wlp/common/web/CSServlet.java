package com.sinotrans.gd.wlp.common.web;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONObject;
import org.springframework.security.Authentication;
import org.springframework.security.context.HttpSessionContextIntegrationFilter;
import org.springframework.security.context.SecurityContextHolder;
import org.springframework.security.providers.preauth.PreAuthenticatedAuthenticationToken;

import com.sinotrans.framework.core.exception.ApplicationException;
import com.sinotrans.framework.core.exception.SystemException;
import com.sinotrans.framework.core.service.BaseManager;
import com.sinotrans.framework.core.support.springsecurity.AcegiUserDetails;
import com.sinotrans.framework.core.support.springsecurity.AcegiUserDetailsService;
import com.sinotrans.framework.core.util.ContextUtils;
import com.sinotrans.framework.core.util.JSONDataUtils;

@SuppressWarnings("serial")
public class CSServlet extends HttpServlet {

	private Log log = LogFactory.getLog(this.getClass());

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		InputStream requestInputStream = request.getInputStream();
		OutputStream responseOutputStream = response.getOutputStream();
		JSONObject resultJSONObject = new JSONObject();
		try {
			String requestJSONString = IOUtils.toString(requestInputStream,
					"UTF-8");
			if (requestJSONString.trim().length() == 0) {
				requestJSONString = request.getParameter("json_parameters");
			} else {
				if (!requestJSONString.startsWith("{")) {
					requestJSONString = URLDecoder.decode(requestJSONString,
							"UTF-8");
					requestJSONString = requestJSONString
							.substring(requestJSONString.indexOf("=") + 1);
				}
			}
			JSONObject requestJSONObject = new JSONObject(requestJSONString);

			// 模拟登陆
			String userCode = requestJSONObject.optString("userCode");
			if (userCode != null && userCode.trim().length() > 0) {
				if (request
						.getAttribute("__spring_security_session_integration_filter_applied") == null) {
					AcegiUserDetails userDetails = ContextUtils.getBeanOfType(
							AcegiUserDetailsService.class).loadUserByUsername(
							userCode);
					Authentication authentication = new PreAuthenticatedAuthenticationToken(
							userDetails, null);
					authentication.setAuthenticated(true);
					SecurityContextHolder.getContext().setAuthentication(
							authentication);
					request
							.getSession()
							.setAttribute(
									HttpSessionContextIntegrationFilter.SPRING_SECURITY_CONTEXT_KEY,
									SecurityContextHolder.getContext());
				}
			}

			String serviceName = requestJSONObject.getString("serviceName");
			String methodName = requestJSONObject.getString("methodName");
			JSONObject parametersJSONObject = requestJSONObject
					.getJSONObject("parameters");
			int parametersCount = parametersJSONObject.length();

			Object bean = ContextUtils.getBean(serviceName);
			Class<?>[] interfaces = bean.getClass().getInterfaces();
			Class<?> beanInterfaceClass = null;
			Method beanMethod = null;
			for (Class<?> interfaceClass : interfaces) {
				if (BaseManager.class.isAssignableFrom(interfaceClass)) {
					beanInterfaceClass = interfaceClass;
					for (Method method : interfaceClass.getMethods()) {
						if (method.getName().equals(methodName)
								&& method.getParameterTypes().length == parametersCount) {
							beanMethod = method;
							break;
						}
					}
					break;
				}
			}

			if (beanMethod == null) {
				throw new SystemException("Method " + methodName
						+ " not found in service " + serviceName
						+ ", or parameters not matched");
			}

			Object[] parameters = JSONDataUtils.parseParametersJSONObject(
					beanInterfaceClass, beanMethod, parametersJSONObject);
			Object result;
			try {
				result = beanMethod.invoke(bean, parameters);
			} catch (InvocationTargetException itex) {
				throw itex.getTargetException();
			} finally {
				// 删除线程中模拟登陆信息
				SecurityContextHolder.getContext().setAuthentication(null);
			}

			// 返回json结果
			response.setContentType("text/html; charset=utf-8");
			resultJSONObject
					.put("result", JSONDataUtils.buildJSONValue(result));
			IOUtils.write(resultJSONObject.toString(), responseOutputStream,
					"UTF-8");
		} catch (Throwable ex) {
			log.error("JsonFacadeServlet error", ex);
			try {
				response.setContentType("text/html; charset=utf-8");
				Throwable root = ExceptionUtils.getRootCause(ex);
				if (root == null) {
					root = ex;
				}
				if (root instanceof ApplicationException) {
					resultJSONObject.put("exception", root.getMessage());
				} else {
					resultJSONObject.put("exception", root.toString());
				}
				IOUtils.write(resultJSONObject.toString(),
						responseOutputStream, "UTF-8");
			} catch (Throwable ex1) {
				log.error("Error returning exception info", ex1);
			}
		}
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
