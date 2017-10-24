package com.sinotrans.gd.wlp.common.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface AjaxHandler {
	HandleResult handle(HttpServletRequest request,
			HttpServletResponse response, String cmd, String param)
			throws Exception;
}
