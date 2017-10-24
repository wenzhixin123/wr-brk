/**
 * 
 */
package com.sinotrans.gd.wlp.system.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.stereotype.Service;

import com.sinotrans.framework.core.util.ContextUtils;
import com.sinotrans.gd.wlp.common.web.RcUtil;
import com.sinotrans.gd.wlp.common.web.SinotransBaseAction;
import com.sinotrans.gd.wlp.system.entity.SystemNewsEntity;
import com.sinotrans.gd.wlp.system.service.SysNewsManager;

/**
 * @author sky
 * 
 *         信息发布Action类
 * 
 */
@Service
public class ShowNewsAction extends SinotransBaseAction {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String sysUuid = request.getParameter("uuid");
		SystemNewsEntity systemNewsEntity = new SystemNewsEntity();
		if (!RcUtil.isEmpty(getUser())) {
			// 把页面需要显示的数据查出来，放到一个Entity里
			if(!RcUtil.isEmpty(sysUuid)){
				systemNewsEntity = ContextUtils.getBeanOfType(SysNewsManager.class).queryContentToUuid(sysUuid);
			}
		}

		request.getSession().setAttribute("systemNewsEntity", systemNewsEntity);
		return mapping.findForward("showNews");
	}

}
