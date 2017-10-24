/**
 * 
 */
package com.sinotrans.gd.wlp.common.web;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;

import com.sinotrans.framework.core.exception.ApplicationException;
import com.sinotrans.gd.wlp.common.model.LocationTaskModel;
import com.sinotrans.gd.wlp.common.model.LogisticsOrderDetailModel;
import com.sinotrans.gd.wlp.util.StringUtil;

/**
 * @author Sky
 * 
 */
@SuppressWarnings("serial")
public class RcUtil {

	public static DateFormat getDateFormat(String pattern) {
		DateFormat df = new SimpleDateFormat(pattern);
		return df;
	}

	public static Integer toInteger(String str) {
		Integer result = 0;
		if (!isEmpty(str)) {
			try {
				result = Integer.valueOf(str);
			} catch (NumberFormatException e) {
				e.printStackTrace();
				result = 0;
			}
		}
		return result;
	}

	public static Double toDouble(String str) {
		if (!isEmpty(str)) {
			return Double.valueOf(str);
		} else {
			return new Double(0);
		}
	}

	public static Float toFloat(String str) {
		if (!isEmpty(str)) {
			return Float.valueOf(str);
		} else {
			return new Float(0);
		}
	}

	public static String toString(String str) {
		if (!isEmpty(str)) {
			return str;
		} else {
			return new String("");
		}
	}

	/**
	 * 判断两个对象是否相等
	 * 
	 * @param desc
	 * @param orig
	 * @return
	 */
	public static boolean areEquals(Object desc, Object orig) {

		if (isEmpty(desc) || isEmpty(orig)) {
			return false;
		}

		if (desc instanceof String && orig instanceof String) {
			return (desc.equals(orig));
		}

		if (desc instanceof Integer && orig instanceof Integer) {
			return (Integer.parseInt(orig.toString()) == Integer.parseInt(desc
					.toString()));
		}
		return (desc.equals(orig));
	}

	/**
	 * 判断是否为空 if null || "" return true else false;
	 * 
	 * @return
	 */
	public static boolean isEmpty(String obj) {
		if (obj == null || "".equals(obj) || "undefined".equals(obj)
				|| "null".equals(obj)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 判断字符串是否为空 如果为空 return true 否则 false
	 * 
	 * @param obj
	 * @return
	 */
	public static boolean strIsEmpty(String obj) {
		return (obj == null || "".equals(obj) || "null".equals(obj.trim())) ? true
				: false;
	}

	public static boolean isEmpty(Object obj) {
		return (obj == null) ? true : false;
	}

	/**
	 * 分离字符串取值
	 * 
	 * @param field
	 *            字段名
	 * @param regex
	 *            分隔符
	 * @return
	 */
	public static String[] split(String field, String regex) {
		if (!isEmpty(field)) {
			String str[] = field.split(regex);
			return str;
		}
		return null;
	}

	/**
	 * 获取数组某个下标的值
	 * 
	 * @param obj
	 * @param suffix
	 * @return
	 */
	public static String getObject(String[] obj, int suffix) {
		if (obj != null) {
			if (2 != obj.length) {
				return "";
			} else {
				return obj[suffix];
			}
		}
		return "";
	}

	/**
	 * 截取字符串取值
	 * 
	 * @param status
	 * @return
	 */
	public static String[] substring(String status) {
		if (isEmpty(status)) {
			return null;
		}
		String fd[] = split(status, "\\.");
		if (fd == null) {
			return null;
		}
		String newStr = fd[1].substring(fd[1].indexOf("["), fd[1].length());
		newStr = newStr.replace("[", "").replace("]", "");
		String obj[] = new String[] { fd[1].substring(0, fd[1].indexOf("[")),
				newStr };
		return obj;
	}

	/**
	 * @param source
	 *            YYYY-MM-DD
	 * @return
	 */
	public static Date toDate(String source) {
		Date d = null;
		if (isEmpty(source)) {
			return null;
		}
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		try {
			d = dateFormat.parse(source);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return d;
	}

	public static final String yyyy_MM_dd_HH_mm_ss = "yyyy-MM-dd HH:mm:ss";
	public static final String yyyy_MM_dd = "yyyy-MM-dd";
	public static final String yyyyMMdd = "yyyyMMdd";

	public static Date toDetailDate(String source) {
		Date d = null;
		if (isEmpty(source)) {
			return null;
		}
		SimpleDateFormat dateFormat = new SimpleDateFormat(yyyy_MM_dd_HH_mm_ss);
		try {
			d = dateFormat.parse(source);
		} catch (ParseException e) {
			// System.out.println();
			e.printStackTrace();
		}
		return d;
	}

	public static String date2string(Date date) {
		if (isEmpty(date)) {
			return null;
		}
		SimpleDateFormat dateFormat = new SimpleDateFormat(yyyy_MM_dd);
		return dateFormat.format(date);
	}

	/**
	 * @param source
	 *            日期
	 * @param dateFormat
	 *            日期格式
	 * @return
	 */
	public static Date string2date(String source, String dateFormat) {
		Date d = null;
		if (isEmpty(source)) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		try {
			d = sdf.parse(source);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return d;
	}

	public static String date2String(Date date, String dateFormat) {
		if (isEmpty(date)) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		return sdf.format(date);
	}

	@SuppressWarnings("unchecked")
	public static Object populate(Class cls, HttpServletRequest request)
			throws IllegalAccessException, InvocationTargetException,
			InstantiationException {
		Object obj = cls.newInstance();
		BeanUtils.populate(obj, request.getParameterMap());
		return obj;
	}

	/**
	 * Map to Bean 的工具类，可去除key中的前缀，进行解码，再进行填充Bean
	 * 
	 * @param <T>
	 * @param cls
	 * @param map
	 * @param perfix
	 * @param decoding
	 * @return
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws InstantiationException
	 * @throws UnsupportedEncodingException
	 */
	@SuppressWarnings("unchecked")
	public static <T> T populate(Class<T> cls, Map map, String perfix,
			String decoding) throws IllegalAccessException,
			InvocationTargetException, InstantiationException,
			UnsupportedEncodingException {
		Map m;
		if (perfix != null) {
			m = new HashMap();
			for (Object key : map.keySet()) {
				if (((String) key).indexOf(perfix) >= 0) {
					if (decoding != null) {
						String[] vs = (String[]) map.get(key);
						for (int i = 0; i < vs.length; i++) {
							vs[i] = new String(vs[i].getBytes("ISO-8859-1"),
									decoding);
						}
						m.put(((String) key).substring(perfix.length()), vs);
					} else {
						m.put(((String) key).substring(perfix.length()), map
								.get(key));
					}

				}
			}
		} else {
			m = map;
		}

		T t = cls.newInstance();
		BeanUtils.populate(t, m);
		return t;
	}

	@SuppressWarnings("unchecked")
	public static <T> T populate(Class<T> cls, Map map, String perfix)
			throws IllegalAccessException, InvocationTargetException,
			InstantiationException, UnsupportedEncodingException {
		Map m;
		if (perfix != null) {
			m = new HashMap();
			for (Object key : map.keySet()) {
				if (((String) key).indexOf(perfix) >= 0) {
					String[] vs = (String[]) map.get(key);
					m.put(((String) key).substring(perfix.length()), vs);
				}
			}
		} else {
			m = map;
		}

		T t = cls.newInstance();
		BeanUtils.populate(t, m);
		return t;
	}

	/**
	 * 转为中文
	 * 
	 * @param obj
	 * @return
	 */
	public static String str2Gbk(String obj) {
		try {
			String str = new String(obj.getBytes("ISO-8859-1"), "gbk");
			return str;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return "";
	}

	/*
	 * * 转为中文
	 * 
	 * @param obj
	 * 
	 * @return
	 */
	public static String str2utf8(String obj) {
		try {
			String str = new String(obj.getBytes("ISO-8859-1"), "UTF-8");
			return str;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * 获取当前年（yyyy）
	 * 
	 * @return
	 */
	public static int getCurrentYear() {
		return Calendar.getInstance().get(Calendar.YEAR);
	}

	/**
	 * 国家法定节假日集合
	 */
	public static final List<String> feteDay = new ArrayList<String>() {
		{
			/* 元旦 */
			add("1-1");
			add("1-2");
			add("1-3");

			/* 五一日期 */
			add("5-1");
			add("5-2");
			add("5-3");

			/* 国庆节日期 */
			add("10-1");
			add("10-2");
			add("10-3");
			add("10-4");
			add("10-5");
			add("10-6");
			add("10-7");
		}
	};

	/**
	 * 获取两个日期中间非节假日的工作日天数
	 * 
	 * @param starttime
	 *            开始日期
	 * @param endtime
	 *            结束日期
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static int getWorkDays(String starttime, String endtime) {
		// 设置时间格式
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		// 开始日期
		Date dateFrom = null;
		Date dateTo = null;
		try {
			dateFrom = dateFormat.parse(starttime);
			dateTo = dateFormat.parse(endtime);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		int workdays = 0;
		Calendar cal = null;
		while (dateFrom.before(dateTo) || dateFrom.equals(dateTo)) {
			cal = Calendar.getInstance();
			// 设置日期
			cal.setTime(dateFrom);
			if ((cal.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY)
					&& (cal.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY)) {
				// 进行比较，如果日期不等于周六也不等于周日，工作日+1
				Date date = cal.getTime();
				String my = date.getMonth() + 1 + "-" + date.getDate();
				if (!feteDay.contains(my))
					workdays++;
			}
			// 日期加1
			cal.add(Calendar.DAY_OF_MONTH, 1);
			dateFrom = cal.getTime();
		}
		return workdays;
	}

	/**
	 * 截取字符串
	 * 
	 * 
	 * @param string
	 * @param index
	 * @param prefix
	 * @return
	 */
	public static String substring(String string, int index, String prefix) {
		if (!isEmpty(string) && string.length() > index) {
			return string.substring(0, index) + prefix;
		} else {
			return string;
		}
	}

	/**
	 * 从request中获得参数Map，并返回可读的Map
	 * 
	 * @param request
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, String> getParameterMap(HttpServletRequest request) {
		// 参数Map
		Map<String, String[]> properties = request.getParameterMap();
		// 返回值Map
		Map<String, String> returnMap = new HashMap<String, String>();
		Iterator<Map.Entry<String, String[]>> entries = properties.entrySet()
				.iterator();
		Map.Entry<String, String[]> entry;
		String name = "";
		String value = "";
		while (entries.hasNext()) {
			entry = (Map.Entry<String, String[]>) entries.next();
			name = (String) entry.getKey();
			Object valueObj = entry.getValue();
			if (null == valueObj) {
				value = "";
			} else if (valueObj instanceof String[]) {
				String[] values = (String[]) valueObj;// 需要根据实际情况重新设计
				if (null != values && values.length > 0) {
					value = values[0];
				}
			} else {
				value = valueObj.toString();
			}
			returnMap.put(name, value);
		}
		return returnMap;
	}

	public static String setKeyBit(int index, String keyBit, String controlWord) {
		controlWord = StringUtil.fixKey(controlWord, 20, "0");
		if (StringUtil.isNull(keyBit))
			keyBit = "0";
		keyBit = keyBit.substring(0, 1);
		controlWord = controlWord.substring(0, index - 1) + keyBit
				+ controlWord.substring(index);
		return controlWord;
	}
	

	  public static void toZero(Object object)
	  {
	    if ((object instanceof LocationTaskModel)) {
	      LocationTaskModel lt = (LocationTaskModel)object;

	      if ((!isEmpty(lt.getUnitDesc())) && 
	        (isEmpty(lt.getUnitCode()))) {
	        throw new ApplicationException(
	          "LT Model 对象包装单位代码为空，代码描述不为空!");
	      }

	      lt.setHeight(Double.valueOf(StringUtil.doubleTo0(lt.getHeight())));
	      lt.setLength(Double.valueOf(StringUtil.doubleTo0(lt.getLength())));
	      lt.setWidth(Double.valueOf(StringUtil.doubleTo0(lt.getWidth())));
	      lt.setGrossWeight(Double.valueOf(StringUtil.doubleTo0(lt.getGrossWeight())));
	      lt.setVolume(Double.valueOf(StringUtil.doubleTo0(lt.getVolume())));
	      lt.setQty(Double.valueOf(StringUtil.doubleTo0(lt.getQty())));
	      lt.setSecondQty(Double.valueOf(StringUtil.doubleTo0(lt.getSecondQty())));
	      lt.setThirdQty(Double.valueOf(StringUtil.doubleTo0(lt.getThirdQty())));
	      lt.setNetWeight(Double.valueOf(StringUtil.doubleTo0(lt.getNetWeight())));
	      lt.setRecVer(new Long(0L));
	    } else if ((object instanceof LogisticsOrderDetailModel)) {
	      LogisticsOrderDetailModel lod = (LogisticsOrderDetailModel)object;

	      if ((!isEmpty(lod.getQtyUnitDesc())) && 
	        (isEmpty(lod.getQtyUnitCode()))) {
	        throw new ApplicationException(
	          "LOD Model 对象包装单位代码为空，代码描述不为空!");
	      }

	      lod.setHeight(Double.valueOf(StringUtil.doubleTo0(lod.getHeight())));
	      lod.setLength(Double.valueOf(StringUtil.doubleTo0(lod.getLength())));
	      lod.setWidth(Double.valueOf(StringUtil.doubleTo0(lod.getWidth())));
	      lod.setGrossWeight(Double.valueOf(StringUtil.doubleTo0(lod.getGrossWeight())));
	      lod.setVolume(Double.valueOf(StringUtil.doubleTo0(lod.getVolume())));
	      lod.setQty(Double.valueOf(StringUtil.doubleTo0(lod.getQty())));
	      lod.setSecondQty(Double.valueOf(StringUtil.doubleTo0(lod.getSecondQty())));
	      lod.setThirdQty(Double.valueOf(StringUtil.doubleTo0(lod.getThirdQty())));
	      lod.setUnitPrice(Double.valueOf(StringUtil.doubleTo0(lod.getUnitPrice())));
	      lod.setTotalPrice(Double.valueOf(StringUtil.doubleTo0(lod.getTotalPrice())));
	      lod.setDeliveredQty(Double.valueOf(StringUtil.doubleTo0(lod.getDeliveredQty())));
	      lod.setNetWeight(Double.valueOf(StringUtil.doubleTo0(lod.getNetWeight())));
	      lod.setConfirmedQty(Double.valueOf(StringUtil.doubleTo0(lod.getConfirmedQty())));
	    }
	  }
	  
	  
	  public static void copyProperties(Object target, Object source) {
		    org.springframework.beans.BeanUtils.copyProperties(source, target);
		  }
	  
	  public static List<List<String>> brokenUpList(List<String> list, int size)
	  {
	    List result = new ArrayList();

	    int listCount = list.size();
	    if (listCount > size) {
	      int level = 1;
	      while (true) {
	        if (level * size > listCount) {
	          List listTemp = list.subList((level - 1) * size, 
	            listCount);
	          result.add(listTemp);
	          break;
	        }
	        List listTemp = list.subList((level - 1) * size, 
	          level * size);
	        result.add(listTemp);

	        level++;
	      }
	    }
	    result.add(list);
	    return result;
	  }
	  
}
