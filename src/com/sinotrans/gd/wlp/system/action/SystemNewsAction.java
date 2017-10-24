/**
 * 
 */
package com.sinotrans.gd.wlp.system.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.stereotype.Service;

import com.sinotrans.framework.core.util.ContextUtils;
import com.sinotrans.gd.wlp.common.web.RcUtil;
import com.sinotrans.gd.wlp.common.web.SinotransBaseAction;
import com.sinotrans.gd.wlp.system.model.SysNewsModel;
import com.sinotrans.gd.wlp.system.service.SysNewsManager;

/**
 * @author sky
 * 
 *         信息发布Action类
 * 
 */
@Service
public class SystemNewsAction extends SinotransBaseAction {



	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		List<SysNewsModel> bzwdList = new ArrayList<SysNewsModel>();
		List<SysNewsModel> tglList = new ArrayList<SysNewsModel>();
		List<SysNewsModel> xxfbList = new ArrayList<SysNewsModel>();
		Map<String,String> snMap=new HashMap<String, String>();
		if (!RcUtil.isEmpty(getUser())) {

			// 查询帮助文档的数据
			String newsType1="BZWD";
			//bzwdList = ContextUtils.getBeanOfType(SysNewsManager.class).findNewsToCombo(getUser().getOfficeCode(), newsType1, 11);

			//查询信息发布的数据
			String newsType2="XXFB";
			//xxfbList = ContextUtils.getBeanOfType(SysNewsManager.class).findNewsToCombo(getUser().getOfficeCode(), newsType2, 12);
			
			//查询通过栏的数据
			String newsType3="TGL";
			//tglList = ContextUtils.getBeanOfType(SysNewsManager.class).findNewsToCombo(getUser().getOfficeCode(), newsType3, 11);
			
			//查询简介信息
			String newsType4="CKMS";
			//snMap=ContextUtils.getBeanOfType(SysNewsManager.class).getNewsTypeAndOffriceCode(getUser().getOfficeCode(), newsType4);
			
		}

		
		request.getSession().setAttribute("bzwdList", bzwdList);
		request.getSession().setAttribute("xxfbList", xxfbList);
		request.getSession().setAttribute("tglList", tglList);
		request.getSession().setAttribute("snMap", snMap);

		return mapping.findForward("portalIndex");
	}

}
