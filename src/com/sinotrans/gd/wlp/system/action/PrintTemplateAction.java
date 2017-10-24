package com.sinotrans.gd.wlp.system.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.sinotrans.framework.core.util.ContextUtils;
import com.sinotrans.gd.wlp.common.web.SinotransBaseAction;
import com.sinotrans.gd.wlp.common.web.SinotransPageJson;
import com.sinotrans.gd.wlp.system.entity.PrintTemplateForm;
import com.sinotrans.gd.wlp.system.service.PrintReportTemplateManager;
import com.sinotrans.gd.wlp.util.JsonUtil;

public class PrintTemplateAction extends SinotransBaseAction {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PrintTemplateForm printTempForm = (PrintTemplateForm) form;

		SinotransPageJson spj = ContextUtils.getBeanOfType(
				PrintReportTemplateManager.class).savePrintTemplate(
				getUser().getOfficeCode(), printTempForm);
		response.getWriter().write(JsonUtil.beanToJson(spj).toString());
		return null;
	}

}
