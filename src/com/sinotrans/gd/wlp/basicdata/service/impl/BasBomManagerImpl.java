package com.sinotrans.gd.wlp.basicdata.service.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.stereotype.Service;

import com.sinotrans.framework.core.exception.ApplicationException;
import com.sinotrans.framework.core.service.impl.BaseManagerImpl;
import com.sinotrans.framework.core.support.Condition;
import com.sinotrans.framework.core.support.PagingInfo;
import com.sinotrans.gd.wlp.basicdata.entity.BasBomEntity;
import com.sinotrans.gd.wlp.basicdata.model.BasBomDetailModel;
import com.sinotrans.gd.wlp.basicdata.model.BasBomModel;
import com.sinotrans.gd.wlp.basicdata.model.BasBomTypeModel;
import com.sinotrans.gd.wlp.basicdata.service.BasBomDetailManager;
import com.sinotrans.gd.wlp.basicdata.service.BasBomManager;
import com.sinotrans.gd.wlp.basicdata.service.BasBomTypeManager;
import com.sinotrans.gd.wlp.basicdata.util.BasdataServiceUtil;
import com.sinotrans.gd.wlp.common.web.RcUtil;
import com.sinotrans.gd.wlp.common.web.SinotransDataGrid;
import com.sinotrans.gd.wlp.util.CommonUtil;
import com.sinotrans.gd.wlp.util.JsonUtil;

@Service
public class BasBomManagerImpl extends BaseManagerImpl implements BasBomManager {

	@Autowired
	private BasBomDetailManager basBomDetailManager;

	@Autowired
	private BasBomTypeManager basBomTypeManager;

	public BasBomModel get(String id) {
		return this.dao.get(BasBomModel.class, id);
	}

	public List<BasBomModel> getAll() {
		return this.dao.getAll(BasBomModel.class);
	}

	public List<BasBomModel> findByExample(BasBomModel example) {
		return this.dao.findByExample(example);
	}

	public BasBomModel save(BasBomModel model) {
		return (BasBomModel) this.dao
				.save(BasdataServiceUtil.beforeSave(model));
	}

	@SuppressWarnings("unchecked")
	public List<BasBomModel> saveAll(Collection<BasBomModel> models) {
		return (List<BasBomModel>) this.dao.saveAll(BasdataServiceUtil
				.beforeSaveAll(models));
	}

	public void remove(BasBomModel model) {
		this.dao.remove(model);
	}

	public void removeAll(Collection<BasBomModel> models) {
		this.dao.removeAll(models);
	}

	public void removeByPk(String id) {
		this.dao.removeByPk(BasBomModel.class, id);
	}

	public void removeAllByPk(Collection<String> ids) {
		this.dao.removeAllByPk(BasBomModel.class, ids);
	}

	/*
	 * 保存BOM 和其他详细信息
	 */
	@Override
	public BasBomEntity saveBasBom(String jsonResult, String officeCode)
			throws Exception {
		DateFormat myDateFormat = new SimpleDateFormat(RcUtil.yyyy_MM_dd);
		BasBomEntity basBomEntity = (BasBomEntity) JsonUtil.jsonToBean(
				jsonResult, BasBomEntity.class, myDateFormat);
		// 保存 basBom的信息
		BasBomModel basBomModel = new BasBomModel();
		BeanUtils.copyProperties(basBomModel, basBomEntity);
		BasBomModel bb = new BasBomModel();
		if (!RcUtil.isEmpty(basBomModel)) {
			basBomModel.setBasBomTypeUuid(queryUuidByCode(basBomEntity
					.getBomTypeCode()));
			if (!RcUtil.isEmpty(basBomModel.getBasBomUuid())) {
				basBomModel.setRowState(CommonUtil.ROW_STATE_MODIFIED);
				basBomModel.setOfficeCode(officeCode);
				bb = this.save(basBomModel);
			} else {
				basBomModel.setRowState(CommonUtil.ROW_STATE_ADDED);
				basBomModel.setOfficeCode(officeCode);
				bb = this.save(basBomModel);
			}

		}
		if (!RcUtil.isEmpty(bb.getBasBomUuid())) {
			List<BasBomDetailModel> basBomDetailModelList = basBomEntity
					.getBasBomDetailModel();
			List<BasBomDetailModel> bbdm = new ArrayList<BasBomDetailModel>();
			if (!RcUtil.isEmpty(basBomDetailModelList)
					&& basBomDetailModelList.size() > 0) {
				for (BasBomDetailModel detailModel : basBomDetailModelList) {
					if (!RcUtil.isEmpty(detailModel.getBasBomDetailUuid())) { // 保存编辑
						detailModel.setStatus(bb.getStatus());
						detailModel.setOfficeCode(officeCode);
					} else { // 保存新增
						detailModel.setStatus(bb.getStatus());
						detailModel.setBasBomUuid(bb.getBasBomUuid());
						detailModel.setOfficeCode(officeCode);
					}
					bbdm.add(detailModel);
				}
				basBomDetailManager.saveAll(bbdm);
			}
		}

		BasBomEntity bbEntity = new BasBomEntity();
		if (!RcUtil.isEmpty(bb.getBasBomUuid())) {
			BasBomModel bbModel = this.get(bb.getBasBomUuid());
			BeanUtils.copyProperties(bbEntity, bbModel);
		}
		return bbEntity;
	}

	/*
	 * 返回页面验证bom
	 */
	public boolean valitBomCode(String bomCode) {
		BasBomModel bbm = new BasBomModel();
		bbm.setBomCode(bomCode.trim());
		List<BasBomModel> bbmList = this.findByExample(bbm);
		if (bbmList.size() > 0) {
			return true;
		} else {
			return false;
		}
	}

	/*
	 * 返回页面验证bomDetail下的itemcode唯一
	 */
	public boolean validateItemCode(String itemCode) {
		BasBomDetailModel bbdm = new BasBomDetailModel();
		bbdm.setItemCode(itemCode.trim());
		List<BasBomDetailModel> bbdmList = basBomDetailManager
				.findByExample(bbdm);
		if (bbdmList.size() > 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 根据bomTypeCode查询bomTypeUuid
	 */
	public String queryUuidByCode(String bomTypeCode) {
		String Uuid = null;
		BasBomTypeModel basBomTypeModel = new BasBomTypeModel();
		basBomTypeModel.setBomTypeCode(bomTypeCode);
		List<BasBomTypeModel> bomTypeList = basBomTypeManager
				.findByExample(basBomTypeModel);
		if (bomTypeList.size() > 0) {
			Uuid = bomTypeList.get(0).getBasBomTypeUuid();
		}
		return Uuid;
	}

	/**
	 * 删除BOM和BOM详细
	 */
	@Override
	public boolean deleteBomAndBomDetail(String uuid) {
		try {
			if (RcUtil.isEmpty(uuid)) {
				throw new ApplicationException("获取页面传送数据出现错误！");
			}
			BasBomModel bbm = this.get(uuid);
			if (RcUtil.isEmpty(bbm)) {
				throw new ApplicationException("未获取到相应的对象！");
			}
			// 1.1判断状态是否是草稿
			if (!CommonUtil.Pending.equals(bbm.getStatus())) {
				throw new ApplicationException("只有草稿状态才能删除，请检查是否被修改！");
			}
			if (bbm != null && CommonUtil.Pending.equals(bbm.getStatus())) {
				// 删除物料详细信息
				basBomDetailManager.removeAllByFk(uuid);
				// 删除物料信息
				removeByPk(uuid);
				return true;
			} else {
				return false;
			}
		} catch (ObjectRetrievalFailureException e) {
			throw new ApplicationException("找不到对象，该对象已经被删除！");
		} catch (Exception e) {
			throw new ApplicationException("删除发生异常！");
		}
	}

	/**
	 * 生效BOM和BOM详细信息
	 */
	@Override
	public BasBomEntity validateBomAndBomDateil(String jsonResult,
			String officeCode) throws Exception {
		DateFormat myDateFormat = new SimpleDateFormat(RcUtil.yyyy_MM_dd);
		BasBomEntity basBomEntity = (BasBomEntity) JsonUtil.jsonToBean(
				jsonResult, BasBomEntity.class, myDateFormat);
		BasBomModel basBomModel = new BasBomModel();
		BeanUtils.copyProperties(basBomModel, basBomEntity);
		BasBomModel bb = new BasBomModel();
		if (!RcUtil.isEmpty(basBomModel.getBasBomUuid())) {
			BasBomModel bbm = get(basBomModel.getBasBomUuid());
			bbm.setBomCode(basBomModel.getBomCode());
			bbm.setBomName(basBomModel.getBomName());
			bbm.setBomNameEn(basBomModel.getBomNameEn());
			bbm.setBasBomTypeUuid(queryUuidByCode(basBomEntity
					.getBomTypeCode()));
			bbm.setCancelDate(basBomModel.getCancelDate());
			bbm.setRowState(CommonUtil.ROW_STATE_MODIFIED);
			bbm.setStatus(CommonUtil.Active);
			bb = save(bbm);

			List<BasBomDetailModel> bbdModelLists = this.dao.createCommonQuery(
					BasBomDetailModel.class).addCondition(
					Condition.eq("basBomUuid", bb.getBasBomUuid()))
					.addCondition(Condition.eq("officeCode", officeCode))
					.query();
			if (!RcUtil.isEmpty(bbdModelLists) && bbdModelLists.size() > 0) {
				List<BasBomDetailModel> bbdmlists = new ArrayList<BasBomDetailModel>();
				for (BasBomDetailModel basBomDetailModel : bbdModelLists) {
					basBomDetailModel.setStatus(CommonUtil.Active);
					basBomDetailModel
							.setRowState(CommonUtil.ROW_STATE_MODIFIED);
					bbdmlists.add(basBomDetailModel);
				}
				basBomDetailManager.saveAll(bbdmlists);
			}
		}
		List<BasBomDetailModel> basBomDetailModelList = basBomEntity
				.getBasBomDetailModel();
		List<BasBomDetailModel> bbdm = new ArrayList<BasBomDetailModel>();
		if (!RcUtil.isEmpty(basBomDetailModelList)
				&& basBomDetailModelList.size() > 0) {
			for (BasBomDetailModel detailModel : basBomDetailModelList) {
				if (!RcUtil.isEmpty(detailModel.getBasBomDetailUuid())) { // 保存编辑
					detailModel.setStatus(bb.getStatus());
					detailModel.setOfficeCode(officeCode);
				} else { // 保存新增
					detailModel.setStatus(bb.getStatus());
					detailModel.setBasBomUuid(bb.getBasBomUuid());
					detailModel.setOfficeCode(officeCode);
				}
				bbdm.add(detailModel);
			}
			basBomDetailManager.saveAll(bbdm);
		}

		BasBomEntity bbEntity = new BasBomEntity();
		if (!RcUtil.isEmpty(bb.getBasBomUuid())) {
			BasBomModel bbModel = this.get(bb.getBasBomUuid());
			BeanUtils.copyProperties(bbEntity, bbModel);
		}
		return bbEntity;
	}

	/**
	 * 作废bom和bomDateil下面的所有信息
	 */
	@Override
	public BasBomEntity cancelBomAndBomDateil(String jsonResult,
			String officeCode) throws Exception {
		DateFormat myDateFormat = new SimpleDateFormat(RcUtil.yyyy_MM_dd);
		BasBomEntity basBomEntity = (BasBomEntity) JsonUtil.jsonToBean(
				jsonResult, BasBomEntity.class, myDateFormat);
		BasBomModel bb = new BasBomModel();
		if (!RcUtil.isEmpty(basBomEntity.getBasBomUuid())) {
			BasBomModel bbm = get(basBomEntity.getBasBomUuid());
			bbm.setStatus(CommonUtil.Cancel);
			bbm.setRowState(CommonUtil.ROW_STATE_MODIFIED);
			bb = save(bbm);

			List<BasBomDetailModel> bbdModelLists = this.dao.createCommonQuery(
					BasBomDetailModel.class).addCondition(
					Condition.eq("basBomUuid", bb.getBasBomUuid()))
					.addCondition(Condition.eq("officeCode", officeCode))
					.query();
			if (!RcUtil.isEmpty(bbdModelLists) && bbdModelLists.size() > 0) {
				List<BasBomDetailModel> bbdmlists = new ArrayList<BasBomDetailModel>();
				for (BasBomDetailModel basBomDetailModel : bbdModelLists) {
					basBomDetailModel.setStatus(CommonUtil.Cancel);
					basBomDetailModel
							.setRowState(CommonUtil.ROW_STATE_MODIFIED);
					bbdmlists.add(basBomDetailModel);
				}
				basBomDetailManager.saveAll(bbdmlists);
			}
		}
		BasBomEntity bbEntity = new BasBomEntity();
		if (!RcUtil.isEmpty(bb.getBasBomUuid())) {
			BasBomModel bbModel = this.get(bb.getBasBomUuid());
			BeanUtils.copyProperties(bbEntity, bbModel);
		}
		return bbEntity;
	}

	@Override
	public SinotransDataGrid queryBom(PagingInfo pagingInfo, String officeCode) {
		SinotransDataGrid sdg = null;
		if (!RcUtil.isEmpty(officeCode)) {
			List<BasBomModel> list = this.dao.createCommonQuery(
					BasBomModel.class).addCondition(
					Condition.eq("officeCode", officeCode)).addCondition(
					Condition.eq("status", CommonUtil.Active)).setPagingInfo(
					pagingInfo).query();
			sdg = new SinotransDataGrid(list, pagingInfo.getTotalRows(),
					pagingInfo.getCurrentPage());
		}
		return sdg;
	}
}
