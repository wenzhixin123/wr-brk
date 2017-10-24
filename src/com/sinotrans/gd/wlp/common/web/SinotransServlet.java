package com.sinotrans.gd.wlp.common.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sinotrans.gd.wlp.util.JsonUtil;

@SuppressWarnings("serial")
public class SinotransServlet extends HttpServlet {

	private Log log = LogFactory.getLog(SinotransServlet.class);
	private List<String> handlers = new ArrayList<String>();

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		String hs = config.getInitParameter("handlers");
		String[] hls = hs.split(",");
		handlers = Arrays.asList(hls);
		// 支持*通配符配置
	}

	/**
	 * Constructor of the object.
	 */
	public SinotransServlet() {
		super();
	}

	/**
	 * The doGet method of the servlet. <br>
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String cmdName = request.getParameter("cmd");
		String params = request.getParameter("params");
		HandleResult hr = new HandleResult();
		try {
			for (String handler : handlers) {
				try {
					log.debug(handler.trim());
					AjaxHandler ah = (AjaxHandler) Class
							.forName(handler.trim()).newInstance();
					hr = ah.handle(request, response, cmdName, params);
					if (hr.isCanHandle()) {
						if (hr.isResponsed()) {
							return;
						} else {
							break;
						}
					}
				} catch (ClassNotFoundException e) {
				}
			}
			if (!hr.isCanHandle()) {
				String result = JsonUtil.beanToJson(new SinotransPageJson(
						false, "", "没发现相应的处理类"));// 格式化错误信息
				hr.setResult(result);
			}

		} catch (Exception e) {
			String result = JsonUtil.beanToJson(new SinotransPageJson(false,
					"", e.getCause().getMessage()));// 格式化错误信息
			hr.setResult(result);
			log.error(this, e);
			e.printStackTrace();
		}
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		response.getWriter().print(hr.getResult());
	}

	/**
	 * The doPost method of the servlet. <br>
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}