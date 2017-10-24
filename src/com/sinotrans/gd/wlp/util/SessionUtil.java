package com.sinotrans.gd.wlp.util;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;

public class SessionUtil {

	public static void Delsession(HttpServletRequest request) {
		HttpSession session = request.getSession();
		String sename = "";
		List<String> l = new ArrayList<String>();
		l.add("ReturnSession");
		l.add("CheckCode");
		l.add("BaseInfoSession");
		l.add("UrlParaSession");
		l.add("UrlParaSession2");
		l.add("UrlParaSession3");
		for (int i = 0; i < l.size(); i++) {
			sename = (String) l.get(i);
			if (!(StringUtils.isBlank(sename))) {
				if (session.getAttribute(sename) != null)
					session.removeAttribute(sename);
			}
		}
	}

	public static void Delsession(HttpServletRequest request, String name) {
		if (StringUtils.isBlank(name))
			return;
		HttpSession session = request.getSession();
		if (session.getAttribute(name) != null)
			session.removeAttribute(name);
	}
}
