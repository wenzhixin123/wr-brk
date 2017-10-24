package com.sinotrans.gd.wlp.basicdata.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sinotrans.framework.core.service.impl.BaseManagerImpl;
import com.sinotrans.framework.core.support.Condition;
import com.sinotrans.gd.wlp.basicdata.model.BasBlobModel;
import com.sinotrans.gd.wlp.basicdata.model.BasWarehouseModel;
import com.sinotrans.gd.wlp.basicdata.service.BasBlobManager;
import com.sinotrans.gd.wlp.basicdata.service.BasWarehouseManager;
import com.sinotrans.gd.wlp.common.web.RcUtil;
import com.sinotrans.gd.wlp.util.CommonUtil;

@Service
public class BasBlobManagerImpl extends BaseManagerImpl implements
		BasBlobManager {

	@Autowired
	private BasWarehouseManager basWarehoseManager;

	public BasBlobModel get(String id) {
		return this.dao.get(BasBlobModel.class, id);
	}

	public List<BasBlobModel> getAll() {
		return this.dao.getAll(BasBlobModel.class);
	}

	public List<BasBlobModel> findByExample(BasBlobModel example) {
		return this.dao.findByExample(example);
	}

	public BasBlobModel save(BasBlobModel model) {
		return this.dao.save(model);
	}

	public List<BasBlobModel> saveAll(Collection<BasBlobModel> models) {
		return this.dao.saveAll(models);
	}

	public void remove(BasBlobModel model) {
		this.dao.remove(model);
	}

	public void removeAll(Collection<BasBlobModel> models) {
		this.dao.removeAll(models);
	}

	public void removeByPk(String id) {
		this.dao.removeByPk(BasBlobModel.class, id);
	}

	public void removeAllByPk(Collection<String> ids) {
		this.dao.removeAllByPk(BasBlobModel.class, ids);
	}

	public byte[] findFileDataByFk(String uuid, String officeCode) {
		List<BasBlobModel> models = this.dao.createCommonQuery(
				BasBlobModel.class).addCondition(
				Condition.eq("preDataUuid", uuid)).addCondition(
				Condition.eq("officeCode", officeCode)).query();
		byte[] b = null;
		if (!RcUtil.isEmpty(models) && models.size() > 0) {
			b = models.get(0).getData();
		}
		return b;
	}

	public List<BasBlobModel> findFileByFk(String uuid) {
		List<BasBlobModel> models = this.dao.createCommonQuery(
				BasBlobModel.class).addCondition(
				Condition.eq("preDataUuid", uuid)).query();
		return models;
	}

	public List<BasBlobModel> findFileByFks(List<String> uuids,
			String officeCode) {
		List<BasBlobModel> models = this.dao.createCommonQuery(
				BasBlobModel.class).addCondition(
				Condition.in("preDataUuid", uuids.toArray())).addCondition(
				Condition.eq("officeCode", officeCode)).query();
		return models;
	}

	public void saveWarehouseMessage(String officeCode, String jsonResult) {
		ObjectMapper om = new ObjectMapper();
		BasWarehouseModel[] models = null;
		List<BasWarehouseModel> list = null;
		List<BasBlobModel> bbmList = new ArrayList<BasBlobModel>();
		try {
			models = om.readValue(jsonResult, BasWarehouseModel[].class);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (Arrays.asList(models).get(0).getRowState().equals(
				CommonUtil.ROW_STATE_DELETED)
				&& Arrays.asList(models).get(0).getStatus().equals(
						CommonUtil.Pending)) {
			for (BasWarehouseModel model : models) {
				String uuid = queryUuidByDataUuid(model.getBasWarehouseUuid());
				if (!RcUtil.isEmpty(uuid)) {
					BasBlobModel bas = get(uuid);
					bas.setRowState(CommonUtil.ROW_STATE_DELETED);
					bbmList.add(bas);
				}
			}
			saveAll(bbmList);
			basWarehoseManager.saveAll(Arrays.asList(models));
		} else if (Arrays.asList(models).get(0).getRowState().equals(
				CommonUtil.ROW_STATE_ADDED)) { // 新增
			list = basWarehoseManager.saveAll(Arrays.asList(models));
			for (BasWarehouseModel basWarehouseModel : list) {
				BasBlobModel bbm = new BasBlobModel();
				bbm.setPreDataUuid(basWarehouseModel.getBasWarehouseUuid());
				bbm.setTypeCode(CommonUtil.SVG);
				bbm.setTypeDesc("SVG图片");
				bbm.setStatus(basWarehouseModel.getStatus());
				bbm.setOfficeCode(basWarehouseModel.getOfficeCode());
				bbm.setRowState(Arrays.asList(models).get(0).getRowState());
				bbm.setCreator(basWarehouseModel.getCreator());
				bbm.setControlWord(basWarehouseModel.getControlWord());
				bbmList.add(bbm);
			}
			saveAll(bbmList);
		} else if (Arrays.asList(models).get(0).getRowState().equals(
				CommonUtil.ROW_STATE_MODIFIED)) { // 修改
			list = basWarehoseManager.saveAll(Arrays.asList(models));
			for (BasWarehouseModel basWarehouseModel : list) {
				String uuid = queryUuidByDataUuid(basWarehouseModel
						.getBasWarehouseUuid());
				if (!RcUtil.isEmpty(uuid)) {
					BasBlobModel bas = get(uuid);
					bas.setStatus(basWarehouseModel.getStatus());
					bas.setRowState(CommonUtil.ROW_STATE_MODIFIED);
					bbmList.add(bas);
				}
			}
			saveAll(bbmList);
		}

	}

	/* 根据preDataUuid 查询 basBlobUuid */
	public String queryUuidByDataUuid(String preDataUuid) {
		String basBlobUuid = "";
		if (!RcUtil.isEmpty(preDataUuid)) {
			List<BasBlobModel> list = this.dao.createCommonQuery(
					BasBlobModel.class).addCondition(
					Condition.eq("preDataUuid", preDataUuid)).query();
			if (!RcUtil.isEmpty(list) && list.size() > 0) {
				BasBlobModel model = list.get(0);
				if (!RcUtil.isEmpty(model)
						&& !RcUtil.isEmpty(model.getBasBlobUuid())) {
					basBlobUuid = model.getBasBlobUuid();
				}
			}
		}
		return basBlobUuid;
	}

	/**
	 * 根据参数查询，返回一个没有大字段的model，
	 * @param preDataUuid
	 * @return 
	 */
	public BasBlobModel queryModelByAll(String preDataUuid){
		BasBlobModel basBlobModel = new BasBlobModel();
		if (!RcUtil.isEmpty(preDataUuid)) {
			List<BasBlobModel> list = this.dao.createCommonQuery(
					BasBlobModel.class).addCondition(
					Condition.eq("preDataUuid", preDataUuid)).query();
			if (!RcUtil.isEmpty(list) && list.size() > 0) {
				basBlobModel = list.get(0);
				basBlobModel.setData(null);
			}
		}
		return basBlobModel;
	}
}
