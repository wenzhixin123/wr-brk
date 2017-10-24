package com.sinotrans.gd.wlp.util;

import java.beans.PropertyDescriptor;
import java.io.StringReader;
import java.io.StringWriter;
import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

import com.sinotrans.framework.core.support.CustomBeanWrapper;
import com.sinotrans.framework.core.util.DateUtils;
import com.sinotrans.framework.core.util.ReflectionUtils;
import com.sinotrans.framework.core.util.SqlUtils;

public class EdiXmlDataUtils {

	@SuppressWarnings("unchecked")
	public static <T> T parseXml(String xmlString, Class<T> clazz)
			throws Exception {
		Element rootElement = new SAXBuilder().build(
				new StringReader(xmlString)).getRootElement();
		return (T) parseXmlElement(null, clazz, rootElement);
	}

	@SuppressWarnings("unchecked")
	private static Object parseXmlElement(Class<?> clazz, Type genericType,
			Element element) throws Exception {
		if ((element.getTextTrim() == null || element.getTextTrim().length() == 0)
				&& element.getChildren().size() == 0) {
			return null;
		}
		if (genericType instanceof GenericArrayType) {
			Type componentType = ((GenericArrayType) genericType)
					.getGenericComponentType();
			List<Element> children = element.getChildren();
			Object result;
			if (componentType instanceof Class) {
				result = Array.newInstance((Class<?>) componentType, children
						.size());
			} else {
				result = Array.newInstance(Object.class, children.size());
			}
			for (int i = 0; i < children.size(); i++) {
				Array.set(result, i, parseXmlElement(clazz, componentType,
						children.get(i)));
			}
			return result;
		} else if (genericType instanceof ParameterizedType) {
			Type rawType = ((ParameterizedType) genericType).getRawType();
			if (rawType instanceof Class) {
				Class<?> rawTypeClass = (Class<?>) rawType;
				if (Collection.class.isAssignableFrom(rawTypeClass)) {
					Type actualType = ((ParameterizedType) genericType)
							.getActualTypeArguments()[0];
					List<Element> children = element.getChildren();
					Collection<Object> result = new ArrayList<Object>();
					for (int i = 0; i < children.size(); i++) {
						result.add(parseXmlElement(clazz, actualType, children
								.get(i)));
					}
					return result;
				} else {
					return parseXmlElement(clazz, rawTypeClass, element);
				}
			} else {
				return parseXmlElement(clazz, rawType, element);
			}
		} else if (genericType instanceof TypeVariable) {
			Type actualTypeArgument = ReflectionUtils.getActualTypeArgument(
					clazz, (TypeVariable<?>) genericType);
			if (actualTypeArgument == null) {
				Type boundType = ((TypeVariable<?>) genericType).getBounds()[0];
				return parseXmlElement(clazz, boundType, element);
			} else {
				return parseXmlElement(clazz, actualTypeArgument, element);
			}
		} else if (genericType instanceof WildcardType) {
			Type boundType = ((WildcardType) genericType).getUpperBounds()[0];
			return parseXmlElement(clazz, boundType, element);
		} else if (genericType instanceof Class) {
			Class<?> typeClass = (Class<?>) genericType;
			String className = element.getAttributeValue("class");
			if (className != null) {
				typeClass = org.springframework.util.ClassUtils
						.forName(className);
			}
			if (typeClass.isArray()) {
				Class<?> componentTypeClass = typeClass.getComponentType();
				List<Element> children = element.getChildren();
				Object result = Array.newInstance(componentTypeClass, children
						.size());
				for (int i = 0; i < children.size(); i++) {
					Array.set(result, i, parseXmlElement(clazz,
							componentTypeClass, children.get(i)));
				}
				return result;
			} else if (Collection.class.isAssignableFrom(typeClass)) {
				List<Element> children = element.getChildren();
				Collection<Object> result = new ArrayList<Object>();
				for (int i = 0; i < children.size(); i++) {
					result.add(parseXmlElement(clazz, Object.class, children
							.get(i)));
				}
				return result;
			} else if (typeClass == String.class) {
				return element.getTextTrim();
			} else if (typeClass == Character.class || typeClass == char.class) {
				String value = element.getTextTrim();
				if (value.length() == 0) {
					return null;
				} else {
					return value.charAt(0);
				}
			} else if (typeClass == Integer.class || typeClass == int.class) {
				String value = element.getTextTrim();
				if (value.length() == 0) {
					return null;
				} else {
					return Integer.parseInt(value);
				}
			} else if (typeClass == Long.class || typeClass == long.class) {
				String value = element.getTextTrim();
				if (value.length() == 0) {
					return null;
				} else {
					return Long.parseLong(value);
				}
			} else if (typeClass == Short.class || typeClass == short.class) {
				String value = element.getTextTrim();
				if (value.length() == 0) {
					return null;
				} else {
					return Short.parseShort(value);
				}
			} else if (typeClass == Byte.class || typeClass == byte.class) {
				String value = element.getTextTrim();
				if (value.length() == 0) {
					return null;
				} else {
					return Byte.parseByte(value);
				}
			} else if (typeClass == Double.class || typeClass == double.class) {
				String value = element.getTextTrim();
				if (value.length() == 0) {
					return null;
				} else {
					return Double.parseDouble(value);
				}
			} else if (typeClass == Float.class || typeClass == float.class) {
				String value = element.getTextTrim();
				if (value.length() == 0) {
					return null;
				} else {
					return Float.parseFloat(value);
				}
			} else if (typeClass == Boolean.class || typeClass == boolean.class) {
				String value = element.getTextTrim();
				if (value.length() == 0) {
					return null;
				} else {
					return Boolean.parseBoolean(value);
				}
			} else if (Date.class.isAssignableFrom(typeClass)) {
				String value = element.getTextTrim();
				if (value.length() == 0) {
					return null;
				} else {
					return DateUtils.parse(value);
				}
			} else {
				Object result;
				try {
					result = typeClass.newInstance();
				} catch (InstantiationException ex) {
					throw new InstantiationException(typeClass.getName());
				}
				CustomBeanWrapper beanWrapper = new CustomBeanWrapper(result);
				PropertyDescriptor[] propertyDescriptors = beanWrapper
						.getPropertyDescriptors();
				for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
					String propertyName = propertyDescriptor.getName();
					if (beanWrapper.isWritableProperty(propertyName)) {
						Element child = element.getChild(SqlUtils
								.javaNameToDbName(propertyName));
						if (child == null) {
							continue;
						}
						Type propertyType = propertyDescriptor.getWriteMethod()
								.getGenericParameterTypes()[0];
						Object value = parseXmlElement(typeClass, propertyType,
								child);
						beanWrapper.setPropertyValue(propertyName, value);
					}
				}
				return result;
			}
		} else {
			throw new RuntimeException("Unsupported parameter type "
					+ genericType);
		}
	}

	public static String buildXmlString(Object object) throws Exception {
		Element rootElement = buildXmlElement(
				object.getClass().getSimpleName(), object);
		Document document = new Document(rootElement);
		Format format = Format.getPrettyFormat();
		format.setEncoding("utf-8");
		format.setIndent("\t");
		XMLOutputter outputter = new XMLOutputter(format);
		StringWriter stringWriter = new StringWriter();
		outputter.output(document, stringWriter);
		return stringWriter.toString();
	}

	@SuppressWarnings("unchecked")
	private static Element buildXmlElement(String name, Object value)
			throws Exception {
		name = SqlUtils.javaNameToDbName(name);
		Element element = new Element(name);
		if (value == null) {
			return element;
		}
		if (value.getClass().isArray()) {
			for (int i = 0; i < Array.getLength(value); i++) {
				element.addContent(buildXmlElement(Array.get(value, i)
						.getClass().getSimpleName(), Array.get(value, i)));
			}
		} else if (value instanceof Collection) {
			for (Object item : (Collection<?>) value) {
				element.addContent(buildXmlElement(item.getClass()
						.getSimpleName(), item));
			}
		} else if (value instanceof String || value instanceof Character
				|| value instanceof Integer || value instanceof Long
				|| value instanceof Short || value instanceof Byte
				|| value instanceof Double || value instanceof Float
				|| value instanceof Boolean) {
			element.addContent(value.toString());
		} else if (value instanceof Date) {
			element.addContent(DateUtils.formatDateTime((Date) value));
		} else {
			CustomBeanWrapper beanWrapper = new CustomBeanWrapper(value);
			PropertyDescriptor[] propertyDescriptors = beanWrapper
					.getPropertyDescriptorsInDeclaringOrder();
			for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
				String propertyName = propertyDescriptor.getName();
				if (propertyName.equals("class")
						|| propertyName.equals("rowState")) {
					continue;
				}
				if (beanWrapper.isReadableProperty(propertyName)) {
					Object propertyValue = beanWrapper
							.getPropertyValue(propertyName);
					element.addContent(buildXmlElement(propertyName,
							propertyValue));
				}
			}
		}
		return element;
	}

}
