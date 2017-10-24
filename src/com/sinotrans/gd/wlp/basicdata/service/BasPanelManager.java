package com.sinotrans.gd.wlp.basicdata.service;

import java.util.Collection;
import java.util.List;

import com.sinotrans.framework.core.service.BaseManager;
import com.sinotrans.gd.wlp.basicdata.model.BasPanelModel;
import com.sinotrans.gd.wlp.basicdata.query.BarPalletQueryItem;

public interface BasPanelManager extends BaseManager {

	BasPanelModel get(String id);

	List<BasPanelModel> getAll();

	List<BasPanelModel> findByExample(BasPanelModel example);

	BasPanelModel save(BasPanelModel model);

	List<BasPanelModel> saveAll(Collection<BasPanelModel> models);

	void remove(BasPanelModel model);

	void removeAll(Collection<BasPanelModel> models);

	void removeByPk(String id);

	void removeAllByPk(Collection<String> ids);

	/**
	 * 验证托盘号是否存在
	 * 
	 * @author wenjp 2011-12-19
	 * @param panelPackageNo
	 * @return
	 * @throws Exception
	 */
	/*List<String> checkPanelPackageNo(String panelPackageNo, String officeCode)
			throws Exception;
*/
	void saveBasPanel(String jsonResult, String officeCode) throws Exception;

	/**
	 * 通过托盘号ID 查询数据
	 * 
	 * @param bpIds
	 * @return
	 */
	List<BasPanelModel> queryPanelBybpIds(String bpIds);

	/**
	 * 托盘号查询（bar枪管理）
	 */
	List<BarPalletQueryItem> queryBarPalletList(String palletNo,
			String officeCode);
}
