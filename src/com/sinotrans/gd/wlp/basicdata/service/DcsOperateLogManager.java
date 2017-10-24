package com.sinotrans.gd.wlp.basicdata.service;

import java.util.Collection;
import java.util.List;

import com.sinotrans.framework.core.query.BaseQueryCondition;
import com.sinotrans.framework.core.service.BaseManager;
import com.sinotrans.gd.wlp.basicdata.model.DcsOperateLogModel;
import com.sinotrans.gd.wlp.basicdata.query.TableCommentQueryItem;

public interface DcsOperateLogManager extends BaseManager {

	DcsOperateLogModel get(String id);

	List<DcsOperateLogModel> getAll();

	List<DcsOperateLogModel> findByExample(DcsOperateLogModel example);

	DcsOperateLogModel save(DcsOperateLogModel model);

	List<DcsOperateLogModel> saveAll(Collection<DcsOperateLogModel> models);

	void remove(DcsOperateLogModel model);

	void removeAll(Collection<DcsOperateLogModel> models);

	void removeByPk(String id);

	void removeAllByPk(Collection<String> ids);
	
	/**
	 * 根据数据id 查询该记录是否打印过
	 * 
	 * @param example
	 * @return
	 */
	public List<DcsOperateLogModel> findByDataId(String businessRefNo,String printType, String  dataSrcouid);
	
	/**
	 * 获取表字段 名称
	 * @param tableCommentQueryCondition
	 * @return
	 */
	public List<TableCommentQueryItem> getTableColuns(BaseQueryCondition tableCommentQueryCondition);
	

	
	public DcsOperateLogModel saveSingle(final DcsOperateLogModel model);

}
