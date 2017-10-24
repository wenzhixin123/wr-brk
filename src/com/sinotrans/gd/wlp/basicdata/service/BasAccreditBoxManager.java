package com.sinotrans.gd.wlp.basicdata.service;

import java.util.Collection;
import java.util.List;

import com.sinotrans.framework.core.service.BaseManager;
import com.sinotrans.framework.core.support.PagingInfo;
import com.sinotrans.gd.wlp.basicdata.model.BasAccreditBoxModel;
import com.sinotrans.gd.wlp.basicdata.model.BasCodeDefModel;
import com.sinotrans.gd.wlp.common.web.SinotransDataGrid;

public interface BasAccreditBoxManager extends BaseManager {

	BasAccreditBoxModel get(String id);

	List<BasAccreditBoxModel> getAll();

	List<BasAccreditBoxModel> findByExample(BasAccreditBoxModel example);

	BasAccreditBoxModel save(BasAccreditBoxModel model);

	List<BasCodeDefModel> saveAll(Collection<BasCodeDefModel> models);

	void remove(BasAccreditBoxModel model);

	void removeAll(Collection<BasAccreditBoxModel> models);

	void removeByPk(String id);

	void removeAllByPk(Collection<String> ids);

	/**
	 * 查询委托单位与控箱公司是否在选择的作业点存在映射
	 * @param models
	 * @return
	 */
	String queryMapping(Collection<BasCodeDefModel> models);
	
	/**
	 * 根据委托单位查询控箱公司，作业点，作业项目，业务类型
	 * @param agentConsigneeCode
	 * @return
	 */
	List<BasAccreditBoxModel> queryBasAccreditByAgcode(String agentConsigneeCode);
	
	/**
	 * 根据委托单位查询控箱公司
	 * @param agentConsigneeCode
	 * @param pagingInfo
	 * @return
	 */
	SinotransDataGrid selectCntrAdminCode(String agentConsigneeCode,PagingInfo pagingInfo);
	
	/**
	 * 根据控箱公司和委托单位查询授权表中是否存在
	 * @param bedControlBoxCompany 控箱公司
	 * @param bedEntrustUnit 委托单位
	 * @param portAreaCode 作业点
	 * @return
	 */
	List<BasCodeDefModel> getAccreditBoxModel(String bedControlBoxCompany,String bedEntrustUnit,String portAreaCode);
	
	String getValueLikeCntrAdminCode(String agentConsigneeCode,String cntrAdminCode);
}
