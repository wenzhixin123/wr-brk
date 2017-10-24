/**
 * 
 */
package com.sinotrans.gd.wlp.common.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.sinotrans.framework.core.util.JSONDataUtils;

/**
 * @author sky
 * 
 *         页面Table DataGrid 数据Json 对象
 * 
 */
@SuppressWarnings("serial")
public class SinotransDataGrid implements Serializable {

	public SinotransDataGrid() {
	}

	/**
	 * @param dataList
	 *            数据集合
	 * @param total
	 *            总行数
	 * @param page
	 *            页数
	 */
	public SinotransDataGrid(List<?> dataList, Integer total, Integer page) {
		this.rows = dataList;
		this.total = total;
		this.page = page;
	}

	private boolean result = true;

	public boolean isResult() {
		return result;
	}

	public void setResult(boolean result) {
		this.result = result;
	}

	/**
	 * 数据的总数
	 */
	private Integer total = 0;

	/**
	 * 数据集合
	 */
	@SuppressWarnings("unchecked")
	private List<?> rows = new ArrayList();

	/**
	 * 页脚汇总信息
	 */
	@SuppressWarnings("unchecked")
	private List<?> footer = new ArrayList();

	/**
	 * 分页信息
	 */
	private Integer page = 0;

	public List<?> getFooter() {
		return footer;
	}

	public void setFooter(List<?> footer) {
		this.footer = footer;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public List<?> getRows() {
		return rows;
	}

	public void setRows(List<?> rows) {
		this.rows = rows;
	}

	@Override
	public String toString() {
		String string = "";
		try {
			string = JSONDataUtils.buildJSONValue(this).toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return string;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

}
