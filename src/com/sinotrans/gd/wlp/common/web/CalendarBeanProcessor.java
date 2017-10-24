package com.sinotrans.gd.wlp.common.web;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

public class CalendarBeanProcessor implements
		net.sf.json.processors.JsonBeanProcessor {
	private final static DateFormat format = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");

	public JSONObject processBean(Object value, JsonConfig config) {
		Calendar cal = (Calendar) value;
		if (cal == null) {
			return null;
		}
		JSONObject ob = new JSONObject();
		ob.put("time", format.format(cal.getTime()));
		ob.put("toString", "function(){return this.time}");
		return ob;
	}

}
