package com.sinotrans.gd.wlp.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TreeMap;

import com.sinotrans.framework.core.model.BaseObject;
import com.sinotrans.framework.core.support.CustomBeanWrapper;

public class EntityListSorter {

	public static <T extends BaseObject> List<T> sort(List<T> entityList,
			String fieldName) {
		TreeMap<Object, List<T>> treeMap = new TreeMap<Object, List<T>>();
		List<T> nullList = new ArrayList<T>();
		for (T entity : entityList) {
			Object fieldValue = new CustomBeanWrapper(entity)
					.getPropertyValue(fieldName);
			if (fieldValue == null) {
				nullList.add(entity);
				continue;
			}
			if (treeMap.containsKey(fieldValue)) {
				treeMap.get(fieldValue).add(entity);
			} else {
				List<T> list = new ArrayList<T>();
				list.add(entity);
				treeMap.put(fieldValue, list);
			}
		}
		List<T> resultList = new ArrayList<T>();
		for (List<T> list : treeMap.values()) {
			resultList.addAll(list);
		}
		resultList.addAll(nullList);
		return resultList;
	}

	public static <T extends BaseObject> List<T> sortDesc(List<T> entityList,
			String fieldName) {
		List<T> sortedList = sort(entityList, fieldName);
		Collections.reverse(sortedList);
		return sortedList;
	}

}
