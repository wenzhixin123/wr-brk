package com.sinotrans.gd.wlp.util;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.Map.Entry;

import org.apache.commons.lang.StringUtils;

import com.sinotrans.framework.core.util.DateUtils;
import com.sinotrans.framework.core.util.NumberUtils;

public class StringUtil {

	public static final String allChar = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	
	public static boolean isNull(String toString) {
		return StringUtils.isBlank(toString);
	}

	public static String toTrim(String toString) {
		String result;
		if (isNull(toString))
			return "";
		toString = StringUtils.trimToEmpty(toString);
		result = toString.replace("\r", " ").replace("\n", " ").replace("\\",
				"").replace("\"", "\\\"");
		//    	
		// result=replaceString(toString,"\\","");
		// toString=result;
		// result=replaceString(toString,"\"","\\\"");
		// toString=result;
		// result=replaceString(toString,"\n","");
		return result;
	}
	public static String StringNullToString(String str){	  
		  return str==null?"":str;
	}
	public static String toUpper(String toString) {
		String result;
		if (isNull(toString))
			return "";
		toString = StringUtils.upperCase(StringUtils.trimToEmpty(toString));
		result = toString.replace("\r", " ").replace("\n", " ").replace("\\",
				"").replace("\"", "\\\"");
		//		
		// result=replaceString(toString,"\\","");
		// toString=result;
		// result=replaceString(toString,"\"","\\\"");
		// toString=result;
		// result=replaceString(toString,"\n","");
		return result;
	}

	public static String ObjectToString(Object obj) {
		if (obj == null)
			return "";
		return toTrim(String.valueOf(obj));
	}

	public static Double ObjectToDouble(Object obj, int bx) {
		Double result = null;
		if (obj == null)
			return result;
		String toString = toTrim(String.valueOf(obj));
		if (isNull(toString))
			return result;
		result = (new BigDecimal(toString)).divide(new BigDecimal("1"), bx,
				BigDecimal.ROUND_HALF_UP).doubleValue();
		return result;
	}

	public static Long ObjectToLong(Object obj) {
		Long result = null;
		if (obj == null)
			return result;
		String toString = toTrim(String.valueOf(obj));
		if (isNull(toString))
			return result;
		result = NumberUtils.parseLong(toString);
		return result;
	}

	public static Date ObjectToDate(Object obj, String formatPattern) {
		if (obj == null)
			return null;
		String date = ObjectToString(obj);
		if (isNull(date))
			return null;
		SimpleDateFormat sdf = new SimpleDateFormat();
		try {
			if ((formatPattern == null) || formatPattern.equals("")) {
				formatPattern = "yyyy-MM-dd HH:mm:ss";
			}
			sdf.applyPattern(formatPattern);
			return sdf.parse(date);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 计算日期，返回字符串
	 * @param date 日期
	 * @param n 加减的天数
	 * @param format 格式
	 * @return
	 */
	public static String getCalculateDate(Date date, int n, String format) {
		long extra = (long)n*24*60*60*1000;
		long d = date.getTime()+extra;
		Date newDate = new Date(d);
		return DateToString(newDate, format);
	}
	
	public static Date getCalculateDate(Date date, int n) {
		long extra = (long)n*24*60*60*1000;
		long d = date.getTime()+extra;
		String sDate = DateToString(new Date(d), "yyyy-MM-dd");
		return StringToDate(sDate, "yyyy-MM-dd");
	}

	public static String LongToString(Long toLong) {
		if (toLong == null)
			return "";
		return NumberUtils.toString(toLong);
	}

	public static Long StringToLong(String toString) {
		toString = toTrim(toString);
		return NumberUtils.parseLong(toString);
	}

	public static Integer StringToInteger(String toString) {
		toString = toTrim(toString);
		return NumberUtils.parseInteger(toString);
	}

	public static String IntegerToString(Integer toInteger) {
		String result = "";
		if (toInteger == null)
			return result;
		result = (new BigDecimal(toInteger)).divide(new BigDecimal("1"), 0,
				BigDecimal.ROUND_HALF_UP).toString();
		return result;
	}

	public static String DoubleToString(Double toDouble, int tobx) {
		String result = "";
		if (toDouble == null)
			return result;
		result = (new BigDecimal(toDouble)).divide(new BigDecimal("1"), tobx,
				BigDecimal.ROUND_HALF_UP).toString();
		return result;
	}

	public static Double StringToDouble(String toString, int bx) {
		Double result = null;
		if (isNull(toString))
			return result;
		toString = toTrim(toString);
		result = (new BigDecimal(toString)).divide(new BigDecimal("1"), bx,
				BigDecimal.ROUND_HALF_UP).doubleValue();
		return result;
	}

	public static String DateToString(Date toDate) {
		if (toDate == null)
			return "";
		return DateUtils.formatDate(toDate);

	}

	public static String DateToString(Date toDate, String toFormat) {
		if (toDate == null)
			return "";
		if (isNull(toFormat))
			return (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(toDate);
		else
			return (new SimpleDateFormat(toFormat)).format(toDate);
	}
	
	public static String DateToStringMinusDay() {

		SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
		Date beginDate = new Date();
		Calendar date = Calendar.getInstance();
		date.setTime(beginDate);
		date.set(Calendar.DATE, date.get(Calendar.DATE) - 1);
	    return dft.format(date.getTime());
	
	}
	
	
	

	public static Date StringToDate(String date, String formatPattern) {
		if (isNull(date))
			return null;
		SimpleDateFormat sdf = new SimpleDateFormat();
		try {
			if ((formatPattern == null) || formatPattern.equals("")) {
				formatPattern = "yyyy-MM-dd HH:mm:ss";
			}
			sdf.applyPattern(formatPattern);
			return sdf.parse(date);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	
	

	public static String LRsubstr(String Str, String FinStr, String LRtype) {
		String ReturnStr = "";
		int n = 0;
		int bx = 0;
		if (isNull(Str))
			return ReturnStr;
		if (Str.indexOf(FinStr) > -1) {
			bx = Str.length();
			n = Str.indexOf(FinStr) + 1;
			if (!isNull(LRtype) && LRtype.equals("R")) {
				ReturnStr = Str.substring(n, bx);
			} else {
				ReturnStr = Str.substring(0, n - 1);
			}
		} else {
			return Str;
		}
		return ReturnStr;
	}

	// ----fixKey("0000",20,"x")
	// 补足20位x字符
	public static String fixKey(String controlWord, int len, String defaultBit) {
		if (controlWord == null) {
			controlWord = "";
		}
		if (StringUtil.isNull(defaultBit)) {
			defaultBit = "0";
		}

		while (controlWord.length() < len) {
			controlWord = controlWord + defaultBit;
		}

		controlWord = controlWord.substring(0, len);
		return controlWord;
	}
	
	public static String getUsedConditionNo(int orderNo, int len) {
		String standard = "";
		for(int i=0; i<len; i++) {
			standard+="0";
		}
		String sOrderNo = orderNo+"";
		if(sOrderNo.length() > len) {
			return "";
		}else {
			return standard.substring(0, len-sOrderNo.length())+sOrderNo;
		}
	}

	public static String splitString(String inString, String separator,
			int sequence) {
		String ls_ret = "";
		int li_point;
		int li_pos;
		int li_case;
		li_point = 0;
		if (0 == sequence)
			sequence = 1;
		if (!isNull(inString)) {
			if (isNull(separator)) {
				ls_ret = inString;
			} else {
				for (li_case = 1; li_case <= sequence; li_case++) {
					li_pos = inString.indexOf(separator, li_point);
					if (li_pos >= 0) {
						if (li_case != sequence) {
							li_point = li_pos + separator.length();
						} else {
							if (sequence == 1) {
								ls_ret = inString.substring(0, li_pos);
							} else {
								ls_ret = inString.substring(li_point, li_pos);
							}
						}
					} else {
						ls_ret = inString.substring(li_point);
					}
				}
			}
		}
		return ls_ret;
	}

	@SuppressWarnings("static-access")
	public static long StringTolong(String value) {
		long result = 0;
		if (isNull(value))
			return result;
		value = value.trim();
		result = ((new Long(0)).valueOf(value)).longValue();
		return result;
	}

	/**
	 * 截取一段字符的长度(汉、日、韩文字符长度为2),不区分中英文,如果数字不正好，则少取一个字符位
	 * 
	 * @param str
	 *            原始字符串
	 * @param specialCharsLength
	 *            截取长度(汉、日、韩文字符长度为2)
	 * @return
	 */
	public static String trim(String str, int specialCharsLength) {
		if (str == null || "".equals(str) || specialCharsLength < 1) {
			return "";
		}
		char[] chars = str.toCharArray();
		int charsLength = getCharsLength(chars, specialCharsLength);
		return new String(chars, 0, charsLength);
	}

	/**
	 * 获取一段字符的长度，输入长度中汉、日、韩文字符长度为2，输出长度中所有字符均长度为1
	 * 
	 * @param chars
	 *            一段字符
	 * @param specialCharsLength
	 *            输入长度，汉、日、韩文字符长度为2
	 * @return 输出长度，所有字符均长度为1
	 */
	private static int getCharsLength(char[] chars, int specialCharsLength) {
		int count = 0;
		int normalCharsLength = 0;
		for (int i = 0; i < chars.length; i++) {
			int specialCharLength = getSpecialCharLength(chars[i]);
			if (count <= specialCharsLength - specialCharLength) {
				count += specialCharLength;
				normalCharsLength++;
			} else {
				break;
			}
		}
		return normalCharsLength;
	}

	/**
	 * 获取字符长度：汉、日、韩文字符长度为2，ASCII码等字符长度为1
	 * 
	 * @param c
	 *            字符
	 * @return 字符长度
	 */
	private static int getSpecialCharLength(char c) {
		if (isLetter(c)) {
			return 1;
		} else {
			return 2;
		}
	}

	/**
	 * 判断一个字符是Ascill字符还是其它字符（如汉，日，韩文字符）
	 * 
	 * @param c
	 *            需要判断的字符
	 * @return 返回true,Ascill字符
	 */
	public static boolean isLetter(char c) {
		int k = 0x80;
		return c / k == 0 ? true : false;
	}

	/**
	 * 得到一个字符串的长度,显示的长度,一个汉字或日韩文长度为2,英文字符长度为1
	 * 
	 * @param s
	 *            需要得到长度的字符串
	 * @return i得到的字符串长度
	 */
	public static int length(String s) {
		if (s == null)
			return 0;
		char[] c = s.toCharArray();
		int len = 0;
		for (int i = 0; i < c.length; i++) {
			len++;
			if (!isLetter(c[i])) {
				len++;
			}
		}
		return len;
	}

	/**
	 * 截取一段字符的长度,不区分中英文,如果数字不正好，则少取一个字符位
	 * 
	 * 
	 * @param inString
	 *            原始字符串
	 * @param len
	 *            截取长度(一个汉字长度按2算的)
	 * @param c
	 *            后缀
	 * @return 返回的字符串
	 */
	public static String substringBytes(String inString, int beginPos, int len,
			String c) {
		if (inString == null || inString.equals("") || len < 1)
			return "";
		byte[] strByte = new byte[len];
		if (len > (length(inString) - beginPos))
			len = length(inString) - beginPos;
		if (len > length(inString) || beginPos > length(inString)
				|| beginPos < 0) {
			// return inString+c;
			return "" + c;
		}
		try {
			System.arraycopy(inString.getBytes("GBK"), beginPos, strByte, 0,
					len);
			int count = 0;
			for (int i = 0; i < len; i++) {
				int value = (int) strByte[i];
				if (value < 0) {
					count++;
				}
			}
			if (count % 2 != 0) {
				len = (len == 1) ? ++len : --len;
			}
			if (len == 0 || len > strByte.length) {
				return "" + c;
			} else {
				return new String(strByte, 0, len, "GBK") + c;
			}
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
	}

	public static int lengthUtf(String s) {
		byte[] bytes = null;
		try {
			bytes = s.getBytes("utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		if (bytes != null) {
			return bytes.length;
		}
		return 0;
	}

	public static String substringBytesUtf(String inString, int beginPos,
			int len, String c) {
		if (inString == null || inString.equals("") || len < 1)
			return "";
		byte[] strByte = new byte[len];
		if (len > (lengthUtf(inString) - beginPos))
			len = lengthUtf(inString) - beginPos;
		if (len > lengthUtf(inString) || beginPos > lengthUtf(inString)
				|| beginPos < 0) {
			// return inString+c;
			return "" + c;
		}
		try {
			System.arraycopy(inString.getBytes("utf-8"), beginPos, strByte, 0,
					len);
			int count = 0;
			for (int i = 0; i < len; i++) {
				int value = (int) strByte[i];
				if (value < 0) {
					count++;
				}
			}
			if (count == 1) {
				len = len - 1;
			} else if (count == 2) {
				len = len - 2;
			} else if (count / 3 != 0) {
				if (count % 3 == 1) {
					len = len - 1;
				} else if (count % 3 == 2) {
					len = len - 2;
				}
			}
			return (new String(strByte, 0, len, "utf-8") + c);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
	}

	public static String replaceString(String inString, String regex,
			String replacement) {
		String outString;
		outString = "";
		if (inString != null && (!inString.equals("")) && regex != null
				&& (!regex.equals(""))) {
			replacement = replacement == null ? "" : replacement;
			int posAt = 0;
			while (inString.indexOf(regex) >= 0) {
				posAt = inString.indexOf(regex);
				outString = outString + inString.substring(0, posAt)
						+ replacement;
				inString = inString.substring(posAt + regex.length());
			}
			outString = outString + inString;
		}
		return outString;
	}

	/**
	 * 获得当前日期的字符串，形式如"2010分隔符01分隔符27"
	 * 
	 * @return
	 */
	public static String getCurrentDate(String sep) {
		Calendar c = Calendar.getInstance();
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH) + 1;
		int day = c.get(Calendar.DAY_OF_MONTH);
		String sMonth, sDay;
		if (month < 10) {
			sMonth = "0" + month;
		} else {
			sMonth = "" + month;
		}
		if (day < 10) {
			sDay = "0" + day;
		} else {
			sDay = "" + day;
		}
		return "" + year + sep + sMonth + sep + sDay;

	}

	public static String getField(String inString, String separator,
			int sequence) {
		String ls_ret = "";
		int li_point;
		int li_pos;
		int li_case;
		li_point = 0;
		if (0 == sequence)
			sequence = 1;
		if (!isNull(inString)) {
			if (isNull(separator)) {
				ls_ret = inString;
			} else {
				for (li_case = 1; li_case <= sequence; li_case++) {
					li_pos = inString.indexOf(separator, li_point);
					if (li_pos >= 0) {
						if (li_case != sequence) {
							li_point = li_pos + separator.length();
						} else {
							if (sequence == 1) {
								ls_ret = inString.substring(0, li_pos);
							} else {
								ls_ret = inString.substring(li_point, li_pos);
							}
						}
					} else {
						ls_ret = inString.substring(li_point);
					}
				}
			}
		}

		return ls_ret;
	}

		
	@SuppressWarnings("static-access")
	public static int getInt(String value) {
		int result = 0;
		if (isNull(value))
			return result;
		value = value.trim();
		result = ((new Integer(0)).valueOf(value)).intValue();
		return result;
	}

	public static double doubleTo0(Double value) {
		if (value == null)
			value = new Double(0);
		return value;
	}
	
	public static boolean equalsDouble(Double d1, Double d2) {
		return d1 == null ? d2 == null : d1.equals(d2);
	}
	
	public static String toJsonArray(Map<String, String> keyMap) {
		StringBuffer sb = new StringBuffer();
		Set<Entry<String, String>> set = keyMap.entrySet();
		for (Entry<String, String> entry : set) {
			sb.append("{\"id\":\"" + entry.getKey() + "\",\"text\":\""
					+ entry.getValue() + "\"},");
		}
		String result = sb.toString().substring(0, sb.toString().length() - 1);
		return result;
	}
	
	/**
	 * crg 获取指定长度的随机数
	 * @param len
	 * @return
	 */
	public static String getRandomNum(int len) {
		String value = "";
		Random random = new Random();
		for(int i=0; i<len; i++) {
			value+=random.nextInt(10);
		}
		return value;
	}
	
	/**
	 * crg 获取指定长度的随机字符串
	 * @param len
	 * @return
	 */
	public static String getRandomString(int len) {
		StringBuffer sb = new StringBuffer();
	    Random random = new Random();
	     for (int i = 0; i < len; i++) {
	    	 sb.append(allChar.charAt(random.nextInt(allChar.length())));
	     }
	     
	    return sb.toString();
	}
	
	/**
	 * replace a character at a specified position.
	 * @param s
	 * @param pos
	 * @param c
	 * @return
	 */
	public static String replaceCharAt(String s, int pos, char c){
		return s.substring(0,pos) + c + s.substring(pos + 1);
	}
	
	public static String replaceCharAt(String s, int pos, String c){
		return s.substring(0,pos) + c + s.substring(pos + 1);
	}
	
	public static String getCharAt(String s, int pos) {
		return s.substring(pos, pos+1);
	}
	
	
	public static Date trimDate(Date date) {
	      Calendar cal = Calendar.getInstance();
	      cal.clear(); // as per BalusC comment.
	      cal.setTime( date );
	      cal.set(Calendar.HOUR_OF_DAY, 0);
	      cal.set(Calendar.MINUTE, 0);
	      cal.set(Calendar.SECOND, 0);
	      cal.set(Calendar.MILLISECOND, 0);
	      return cal.getTime();
	 }
	
	public static String toTrimAndTab(String str){
		if(StringUtils.isNotBlank(str) && str != "null"){
			str = str.replaceAll("\\?", "").replaceAll("\\？", "").replaceAll("\\!", "")
					.replaceAll("\\！", "");
			char[] ch = str.toCharArray();
			String s = "";
			for(char c : ch){
//				Character cha = new Character(c);
//				System.out.println(cha.hashCode());
				if(c != 32 && c != 9 && c != 13 && c != 10 && c != 12288 && c != 160){
					s += c;
				}
			}
			return s;
		}else{
			return "";
		}
	}
	public static void main(String[] args) {
		String str = "CNCAN1305913    ";
		System.out.println(toTrimAndTab(str));
	}
}
