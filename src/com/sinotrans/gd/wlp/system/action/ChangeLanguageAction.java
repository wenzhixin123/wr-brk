/**
 * 
 */
package com.sinotrans.gd.wlp.system.action;

import java.util.Locale;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.sinotrans.gd.wlp.common.web.SinotransBaseAction;
import com.sinotrans.gd.wlp.util.CommonUtil;

/**
 * @author sky
 * 
 *         更换国际化语言
 * 
 */
public class ChangeLanguageAction extends SinotransBaseAction {

	// private Logger log = Logger.getLogger(ChangeLanguageAction.class);

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String language = request.getParameter("language");// 从页面接收的变量，其值为"zh"或"en"
		Locale currentLocale = Locale.getDefault();
		if ("zh".equals(language)) {
			currentLocale = new Locale("zh", "CN");
		} else if ("en".equals(language)) {
			currentLocale = new Locale("en", "US");
		}
		Cookie cookie = new Cookie(CommonUtil.FROS_CUSTOM_LOCALE_COOKIE,
				language);
		cookie.setPath(request.getContextPath());
		cookie.setMaxAge(60 * 60 * 24 * 100);// 保存100天
		response.addCookie(cookie);// 设置语言Cookie
		this.setLocale(request, currentLocale);
		request.getSession().setAttribute("language", language);
		return mapping.findForward("login");
	}
}
