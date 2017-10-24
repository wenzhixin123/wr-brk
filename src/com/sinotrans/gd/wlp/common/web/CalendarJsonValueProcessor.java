package com.sinotrans.gd.wlp.common.web;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

public class CalendarJsonValueProcessor implements JsonValueProcessor {
	public static final String PATTERN = "yyyy-MM-dd HH:mm:ss";
	public static final SimpleDateFormat format = new SimpleDateFormat(PATTERN);

	public Object processArrayValue(Object value, JsonConfig config) {
		return format((Calendar) value);
	}

	public Object processObjectValue(String key, Object value, JsonConfig config) {
		return format((Calendar) value);
	}

	private String format(Calendar c) {
		if (c == null) {
			return null;
		}
		return format.format(c.getTime());
	}

}
