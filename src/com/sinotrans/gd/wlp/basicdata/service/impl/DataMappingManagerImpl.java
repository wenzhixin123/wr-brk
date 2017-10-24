package com.sinotrans.gd.wlp.basicdata.service.impl;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sinotrans.framework.core.exception.ApplicationException;
import com.sinotrans.framework.core.service.impl.BaseManagerImpl;
import com.sinotrans.gd.wlp.basicdata.model.DataMappingModel;
import com.sinotrans.gd.wlp.basicdata.service.DataMappingManager;
import com.sinotrans.gd.wlp.common.service.SQLQueryManager;
import com.sinotrans.gd.wlp.common.web.RcUtil;

@Service
public class DataMappingManagerImpl extends BaseManagerImpl implements DataMappingManager {

	@Autowired
	private SQLQueryManager sQLQueryManager;
	
	public DataMappingModel get(String id) {
		return this.dao.get(DataMappingModel.class, id);
	}

	public List<DataMappingModel> getAll() {
		return this.dao.getAll(DataMappingModel.class);
	}

	public List<DataMappingModel> findByExample(DataMappingModel example) {
		return this.dao.findByExample(example);
	}

	public DataMappingModel save(DataMappingModel model) {
		return this.dao.save(model);
	}

	public List<DataMappingModel> saveAll(Collection<DataMappingModel> models) {
		return this.dao.saveAll(models);
	}

	public void remove(DataMappingModel model) {
		this.dao.remove(model);
	}

	public void removeAll(Collection<DataMappingModel> models) {
		this.dao.removeAll(models);
	}

	public void removeByPk(String id) {
		this.dao.removeByPk(DataMappingModel.class, id);
	}

	public void removeAllByPk(Collection<String> ids) {
		this.dao.removeAllByPk(DataMappingModel.class, ids);
	}
	
	@Override
	public boolean removeType(String pkId, long recVer) {
		if (!RcUtil.isEmpty(pkId)) {
			String deletePtuSql = "delete DATA_MAPPING  where DATA_MAPPING_UUID = '"
					+ pkId + "' and rec_ver = '" + recVer + "' ";
			sQLQueryManager.executeSQL(deletePtuSql, "", true);
			return true;
		} else {
			return false;
		}
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.sinotrans.gd.wlp.ec.basicdata.service.ECBasSiteManager#updateSysStatusOue
	 * (java.lang.String, java.lang.String, java.lang.String, java.lang.String,
	 * java.lang.String)
	 */
	/*public boolean updateSysStatusOue(String uuid, String status,
			String tableName, String statusName, String uuidName) {
		String seleSys = "select sy.status from " + tableName + " sy where sy."
				+ uuidName + "='" + uuid + "'";
		List<Object[]> objl = sQLQueryManager.getSqlResultList(seleSys, "");
		String iso = "";
		if (objl == null || objl.get(0).length == 0 || null == objl.get(0)[0]) {
			// 如果根据UUID查询出现数据为空的话可能有其他用户在使用或者已被其他用户删除所以在报错误
			throw new ApplicationException("未获取到相应的状态！");
		} else {
			iso = objl.get(0)[0].toString();
		}
		if (status != null && !status.equals("")) {
			// 这里判断 页面传送的值是作废的话那么从数据库中查询出来的数据必须是有效的。否则报错。
			if (status.equals(EC_CommonUtil.Cancel)
					&& iso.equals(EC_CommonUtil.Active)) {
				// 如果满足条件则不处理 可以继续执行 有效数据可以作废
			} else if (status.equals(EC_CommonUtil.Cancel)
					&& iso.equals(EC_CommonUtil.Cancel)) {
				throw new ApplicationException("该数据已作废！");
			} else if (status.equals(EC_CommonUtil.Cancel)
					&& iso.equals(EC_CommonUtil.Pending)) {
				throw new ApplicationException("该数据未生效、作废无效！");
			} else if (status.equals(EC_CommonUtil.Active)
					&& iso.equals(EC_CommonUtil.Active)) {
				throw new ApplicationException("该数据已生效！");
			} else if (status.equals(EC_CommonUtil.Active)
					&& iso.equals(EC_CommonUtil.Pending)) {
				// 如果满足条件则不处理 可以继续执行 草稿数据可以生效
			} else if (status.equals(EC_CommonUtil.Active)
					&& iso.equals(EC_CommonUtil.Cancel)) {
				// 如果满足条件则不处理 可以继续执行 作废数据可以生效
			}
		}
		try {
			String loSql = "update " + tableName + " sys set sys." + statusName
					+ " = '" + status + "' where sys." + uuidName + " ='"
					+ uuid + "'";
			sQLQueryManager.executeSQL(loSql, "", true);
		} catch (RuntimeException e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}*/
	
}
