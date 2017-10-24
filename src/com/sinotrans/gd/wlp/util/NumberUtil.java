package com.sinotrans.gd.wlp.util;

public class NumberUtil {

	StringUtil s = new StringUtil();

	public static Long ObjectToLong(Object obj) {
		if (obj == null)
			return new Long(0);
		String toString = StringUtil.toTrim(String.valueOf(obj));
		if (StringUtil.isNull(toString))
			return new Long(0);
		return StringUtil.StringToLong(toString);
	}
}