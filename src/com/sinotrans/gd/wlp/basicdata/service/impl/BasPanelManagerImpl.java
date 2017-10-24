package com.sinotrans.gd.wlp.basicdata.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sinotrans.framework.core.exception.ApplicationException;
import com.sinotrans.framework.core.service.impl.BaseManagerImpl;
import com.sinotrans.gd.wlp.basicdata.model.BasPanelModel;
import com.sinotrans.gd.wlp.basicdata.query.BarPalletQueryCondition;
import com.sinotrans.gd.wlp.basicdata.query.BarPalletQueryItem;
import com.sinotrans.gd.wlp.basicdata.query.BasPanelByOtherQueryItem;
import com.sinotrans.gd.wlp.basicdata.service.BasPanelManager;
import com.sinotrans.gd.wlp.basicdata.util.BasdataServiceUtil;
import com.sinotrans.gd.wlp.common.model.RemainSinworkModel;
import com.sinotrans.gd.wlp.common.service.BasCommonManager;
import com.sinotrans.gd.wlp.common.web.RcUtil;
import com.sinotrans.gd.wlp.util.CommonUtil;
import com.sinotrans.gd.wlp.util.EntityListSorter;

@Service
public class BasPanelManagerImpl extends BaseManagerImpl implements
		BasPanelManager {

	/*@Autowired
	public InhouseRemainSinworkManager inhouseRemainSinworkManager;
*/
	@Autowired
	public BasCommonManager basCommonManager;

	public BasPanelModel get(String id) {
		return this.dao.get(BasPanelModel.class, id);
	}

	public List<BasPanelModel> getAll() {
		return this.dao.getAll(BasPanelModel.class);
	}

	public List<BasPanelModel> findByExample(BasPanelModel example) {
		return this.dao.findByExample(example);
	}

	public BasPanelModel save(BasPanelModel model) {
		return (BasPanelModel) this.dao.save(BasdataServiceUtil
				.beforeSave(model));
	}

	@SuppressWarnings("unchecked")
	public List<BasPanelModel> saveAll(Collection<BasPanelModel> models) {
		return (List<BasPanelModel>) this.dao.saveAll(BasdataServiceUtil
				.beforeSaveAll(models));
	}

	public void remove(BasPanelModel model) {
		this.dao.remove(model);
	}

	public void removeAll(Collection<BasPanelModel> models) {
		this.dao.removeAll(models);
	}

	public void removeByPk(String id) {
		this.dao.removeByPk(BasPanelModel.class, id);
	}

	public void removeAllByPk(Collection<String> ids) {
		this.dao.removeAllByPk(BasPanelModel.class, ids);
	}

/*	@Override
	public List<String> checkPanelPackageNo(String panelPackageNo,
			String officeCode) throws Exception {
		if (RcUtil.isEmpty(panelPackageNo)) {
			throw new ApplicationException("托盘号为空！");
		}
		BasPanelModel example = new BasPanelModel();
		example.setPanelPackageNo(panelPackageNo);
		example.setStatus(CommonUtil.Active);
		example.setOfficeCode(officeCode);
		List<BasPanelModel> BasPanelModelList = this.findByExample(example);
		if (BasPanelModelList.size() <= 0) {
			throw new ApplicationException("不存在该托盘号！");
		}
		List<String> barcodeList = new ArrayList<String>();

		RemainSinworkModel RemainSinworkExample = new RemainSinworkModel();
		RemainSinworkExample.setPanelNo(panelPackageNo);
		RemainSinworkExample.setOfficeCode(officeCode);
		List<RemainSinworkModel> remainSinworkModelList = this.inhouseRemainSinworkManager
				.findByExample(RemainSinworkExample);
		for (RemainSinworkModel remainSinworkModel : remainSinworkModelList) {
			barcodeList.add(remainSinworkModel.getBarcode());
		}
		return barcodeList;
	}*/

	public void saveBasPanel(String jsonResult, String officeCode)
			throws Exception {
		ObjectMapper om = new ObjectMapper();
		BasPanelByOtherQueryItem[] models = om
				.readValue(jsonResult, BasPanelByOtherQueryItem[].class);
		for (BasPanelByOtherQueryItem BasPanelByOtherQueryItem : models) {
			BasPanelModel basPanelModel = new BasPanelModel();
			BeanUtils.copyProperties(basPanelModel, BasPanelByOtherQueryItem);
			if (basPanelModel.getRowState().equals(CommonUtil.ROW_STATE_ADDED)) {
				String panelPackageNo = basCommonManager.generateNumber(
						CommonUtil.TRANSACTIONTYPE_PANEL, "", officeCode);
				basPanelModel.setPanelPackageNo(panelPackageNo);
				save(basPanelModel);
			} else {
				save(basPanelModel);
			}
		}
	}

	@Override
	public List<BasPanelModel> queryPanelBybpIds(String bpIds) {
		List<BasPanelModel> bpmList = new ArrayList<BasPanelModel>();
		BasPanelModel bpmodel = null;
		if (!RcUtil.isEmpty(bpIds)) {
			String bpIdArray[] = bpIds.split(",");
			if (bpIdArray != null && bpIdArray.length > 0) {
				for (String id : bpIdArray) {
					bpmodel = get(id);
					String controlWord = bpmodel.getControlWord();
					controlWord = RcUtil.setKeyBit(4, "P", controlWord);
					bpmodel.setControlWord(controlWord);
					bpmodel.setRowState(CommonUtil.ROW_STATE_MODIFIED);
					save(bpmodel);
					bpmList.add(bpmodel);
				}
			}
		}
		bpmList = EntityListSorter.sort(bpmList, "panelPackageNo");
		return bpmList;
	}

	@Override
	public List<BarPalletQueryItem> queryBarPalletList(String palletNo,
			String officeCode) {
		if (!RcUtil.isEmpty(palletNo)) {
			BarPalletQueryCondition palletCondition = new BarPalletQueryCondition(
					palletNo, officeCode);
			List<BarPalletQueryItem> pList = this.dao.query(palletCondition,
					BarPalletQueryItem.class);
			return pList;
		}
		return null;
	}
}
