package com.sinotrans.gd.wlp.common.web;

import java.util.List;

/**
 * @author Sky
 * 
 *         页面table 结果集
 * 
 */
@SuppressWarnings("unchecked")
public class Table {
	/**
	 * 排序字段
	 */
	private String orderCol;

	/**
	 * 排序方式（0 升序，1降序）
	 */
	private int orderType;

	/**
	 * 数据结果集
	 */

	private List data;

	/**
	 * 开始行
	 */
	private int firstLine;

	/**
	 * 最大行
	 */
	private int maxLine;

	/**
	 * 总行数
	 */
	private int recNum;

	public List getData() {
		return data;
	}

	public void setData(List data) {
		this.data = data;
	}

	public int getRecNum() {
		return recNum;
	}

	public void setRecNum(int recNum) {
		this.recNum = recNum;
	}

	public int getFirstLine() {
		return firstLine;
	}

	public void setFirstLine(int firstLine) {
		this.firstLine = firstLine;
	}

	public int getMaxLine() {
		return maxLine;
	}

	public void setMaxLine(int maxLine) {
		this.maxLine = maxLine;
	}

	public String getOrderCol() {
		return (orderCol == null || "null".equals(orderCol) || "undefined"
				.equals(orderCol)) ? "" : orderCol;
	}

	public void setOrderCol(String orderCol) {
		this.orderCol = orderCol;
	}

	public int getOrderType() {
		return orderType;
	}

	public void setOrderType(int orderType) {
		this.orderType = orderType;
	}

}
