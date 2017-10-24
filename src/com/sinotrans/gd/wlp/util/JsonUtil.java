/**
 * 
 */
package com.sinotrans.gd.wlp.util;

import java.io.StringReader;
import java.io.StringWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonParser.Feature;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.type.TypeFactory;
import org.codehaus.jackson.type.TypeReference;

import com.sinotrans.gd.wlp.common.web.RcUtil;

/**
 * @author sky
 * 
 *         系统Json 工具类
 * 
 */
public final class JsonUtil {

	private static Logger log = Logger.getLogger(JsonUtil.class);

	private JsonUtil() {
	}

	private static final ObjectMapper MAPPER = new ObjectMapper();

	static {
		MAPPER.configure(Feature.ALLOW_UNQUOTED_FIELD_NAMES, false);
	}

	private static final JsonFactory JSONFACTORY = new JsonFactory();

	/**
	 * 转换Java Bean 为 json
	 */
	public static String beanToJson(Object o) {
		StringWriter sw = new StringWriter(300);
		JsonGenerator jsonGenerator = null;
		String result = "";
		try {
			jsonGenerator = JSONFACTORY.createJsonGenerator(sw);
			MAPPER.writeValue(jsonGenerator, o);
			result = sw.toString();
		} catch (Exception e) {
			log.error(e);
			return null;
		} finally {
			if (jsonGenerator != null) {
				try {
					jsonGenerator.close();
				} catch (Exception e) {
					log.error(e);
				}
			}
		}
		log.debug(result);
		return result;
	}

	/**
	 * 转换Java Bean 为 json 附加了个时间格式参数
	 * 
	 * @param o
	 * @param dateFormat
	 * @return
	 */
	public static String beanToJson(Object o, SimpleDateFormat dateFormat) {
		StringWriter sw = new StringWriter(300);
		JsonGenerator jsonGenerator = null;
		String result = "";
		try {
			MAPPER.setDateFormat(dateFormat);
			jsonGenerator = JSONFACTORY.createJsonGenerator(sw);
			MAPPER.writeValue(jsonGenerator, o);
			result = sw.toString();
			MAPPER.setDateFormat(new SimpleDateFormat(RcUtil.yyyy_MM_dd));
		} catch (Exception e) {
			log.error(e);
			e.printStackTrace();
			return null;
		} finally {
			if (jsonGenerator != null) {
				try {
					jsonGenerator.close();
				} catch (Exception e) {
					log.error(e);
					e.printStackTrace();
				}
			}
		}
		log.debug(result);
		return result;
	}

	/**
	 * json 转 javabean
	 * 
	 * @param json
	 * @return
	 */
	public static Object jsonToBean(String json, Class<?> clazz) {
		try {
			return MAPPER.readValue(json, clazz);
		} catch (Exception e) {
			log.error(e);
			e.printStackTrace();
			return null;
		}
	}

	public static Object jsonToBean(String json, Class<?> clazz,
			DateFormat dateFormat) {
		try {
			MAPPER.setDateFormat(dateFormat);
			Object s = MAPPER.readValue(json, clazz);
			MAPPER.setDateFormat(new SimpleDateFormat(RcUtil.yyyy_MM_dd));
			return s;
		} catch (Exception e) {
			log.error(e);
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * json 转 javabean 附加了个时间格式参数
	 * 
	 * @param json
	 * @param clazz
	 * @param dateFormat
	 * @return
	 */
	public static Object jsonToBean(String json, Class<?> clazz,
			SimpleDateFormat dateFormat) {
		try {
			MAPPER.setDateFormat(dateFormat);
			Object object = MAPPER.readValue(json, clazz);
			MAPPER.setDateFormat(new SimpleDateFormat(RcUtil.yyyy_MM_dd));
			return object;
		} catch (Exception e) {
			log.error(e);
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 转换Java Bean 为 HashMap
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, Object> beanToMap(Object o) {
		try {
			return MAPPER.readValue(beanToJson(o), HashMap.class);
		} catch (Exception e) {
			log.error(e);
			return null;
		}
	}

	/**
	 * 转换Json String 为 HashMap
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, Object> jsonToMap(String json,
			boolean collToString) {
		try {
			Map<String, Object> map = MAPPER.readValue(json, HashMap.class);
			if (collToString) {
				for (Map.Entry<String, Object> entry : map.entrySet()) {
					if (entry.getValue() instanceof Collection
							|| entry.getValue() instanceof Map) {
						entry.setValue(beanToJson(entry.getValue()));
					}
				}
			}
			return map;
		} catch (Exception e) {
			log.error(e);
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * List 转换成json
	 * 
	 * @param list
	 * @return
	 */
	public static String listToJson(List<Map<String, String>> list) {
		JsonGenerator jsonGenerator = null;
		StringWriter sw = new StringWriter();
		try {
			jsonGenerator = JSONFACTORY.createJsonGenerator(sw);
			new ObjectMapper().writeValue(jsonGenerator, list);
			jsonGenerator.flush();
			return sw.toString();
		} catch (Exception e) {
			log.error(e);
			return null;
		} finally {
			if (jsonGenerator != null) {
				try {
					jsonGenerator.flush();
					jsonGenerator.close();
				} catch (Exception e) {
					log.error(e);
				}
			}
		}
	}
	/**
	 * Map 转换成json
	 * 
	 * @param map
	 * @return
	 */
	public static String mapToJson(Map<String, String> map) {
		JsonGenerator jsonGenerator = null;
		StringWriter sw = new StringWriter();
		try {
			jsonGenerator = JSONFACTORY.createJsonGenerator(sw);
			new ObjectMapper().writeValue(jsonGenerator, map);
			jsonGenerator.flush();
			return sw.toString();
		} catch (Exception e) {
			log.error(e);
			return null;
		} finally {
			if (jsonGenerator != null) {
				try {
					jsonGenerator.flush();
					jsonGenerator.close();
				} catch (Exception e) {
					log.error(e);
				}
			}
		}
	}
	
	/**
	 * List 转换成json
	 * 
	 * @param list
	 * @return
	 */
	public static String list2Json(List<?> list) {
		JsonGenerator jsonGenerator = null;
		StringWriter sw = new StringWriter();
		try {
			jsonGenerator = JSONFACTORY.createJsonGenerator(sw);
			new ObjectMapper().writeValue(jsonGenerator, list);
			jsonGenerator.flush();
			return sw.toString();
		} catch (Exception e) {
			log.error(e);
			return null;
		} finally {
			if (jsonGenerator != null) {
				try {
					jsonGenerator.flush();
					jsonGenerator.close();
				} catch (Exception e) {
					log.error(e);
				}
			}
		}
	}

	/**
	 * json 转List
	 * 
	 * @param json
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Map<String, String>> jsonToList(String json) {
		try {
			if (json != null && !"".equals(json.trim())) {
				JsonParser jsonParse = JSONFACTORY
						.createJsonParser(new StringReader(json));
				ArrayList<Map<String, String>> arrayList = (ArrayList<Map<String, String>>) new ObjectMapper()
						.readValue(jsonParse, ArrayList.class);
				return arrayList;
			} else {
				return null;
			}
		} catch (Exception e) {
			log.error(e);
			return null;
		}
	}

	@SuppressWarnings("deprecation")
	public static List<?> json2List(String json) {
		try {
			if (json != null && !"".equals(json.trim())) {
				ArrayList<?> arrayList = new ObjectMapper()
						.readValue(
								json,
								TypeFactory
										.fromTypeReference(new TypeReference<List<?>>() {
										}));
				return arrayList;
			} else {
				return null;
			}
		} catch (Exception e) {
			log.error(e);
			return null;
		}
	}
}
