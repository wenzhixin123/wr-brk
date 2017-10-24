package com.sinotrans.gd.wlp.basicdata.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sinotrans.framework.core.service.impl.BaseManagerImpl;
import com.sinotrans.framework.core.support.Condition;
import com.sinotrans.gd.wlp.basicdata.entity.BasLottreeEntity;
import com.sinotrans.gd.wlp.basicdata.entity.EasyUiTree;
import com.sinotrans.gd.wlp.basicdata.model.BasBlobModel;
import com.sinotrans.gd.wlp.basicdata.model.BasLocAreaModel;
import com.sinotrans.gd.wlp.basicdata.model.BasWarehouseModel;
import com.sinotrans.gd.wlp.basicdata.query.WarehouseFindLotByLodNoQueryCondition;
import com.sinotrans.gd.wlp.basicdata.query.WarehouseFindLotByLodNoQueryItem;
import com.sinotrans.gd.wlp.basicdata.service.BasBlobManager;
import com.sinotrans.gd.wlp.basicdata.service.BasWarehouseManager;
import com.sinotrans.gd.wlp.basicdata.util.BasdataServiceUtil;
import com.sinotrans.gd.wlp.common.web.RcUtil;
import com.sinotrans.gd.wlp.common.web.SinotransPageJson;
import com.sinotrans.gd.wlp.system.entity.SessionContextUserEntity;
import com.sinotrans.gd.wlp.util.CommonUtil;

@Service
public class BasWarehouseManagerImpl extends BaseManagerImpl implements
		BasWarehouseManager {

	@Autowired
	private BasBlobManager basBlobManager;

	public BasWarehouseModel get(String id) {
		return this.dao.get(BasWarehouseModel.class, id);
	}

	public List<BasWarehouseModel> getAll() {
		return this.dao.getAll(BasWarehouseModel.class);
	}

	public List<BasWarehouseModel> findByExample(BasWarehouseModel example) {
		return this.dao.findByExample(example);
	}

	public BasWarehouseModel save(BasWarehouseModel model) {
		return (BasWarehouseModel) this.dao.save(BasdataServiceUtil
				.beforeSave(model));
	}

	@SuppressWarnings("unchecked")
	public List<BasWarehouseModel> saveAll(Collection<BasWarehouseModel> models) {
		return (List<BasWarehouseModel>) this.dao.saveAll(BasdataServiceUtil
				.beforeSaveAll(models));
	}

	public void remove(BasWarehouseModel model) {
		this.dao.remove(model);
	}

	public void removeAll(Collection<BasWarehouseModel> models) {
		this.dao.removeAll(models);
	}

	public void removeByPk(String id) {
		this.dao.removeByPk(BasWarehouseModel.class, id);
	}

	public void removeAllByPk(Collection<String> ids) {
		this.dao.removeAllByPk(BasWarehouseModel.class, ids);
	}

	/**
	 * 查询出能给用户选择的仓库图,并在服务器生成文件，供页面调用显示
	 * 
	 * @throws Exception
	 */
	public List<Map<String, String>> findAllToCombobox(String office,
			String userId, String realPath) throws Exception {
		List<Map<String, String>> result = new ArrayList<Map<String, String>>();
		List<BasWarehouseModel> models = new ArrayList<BasWarehouseModel>();
		List<String> bolbUuid = new ArrayList<String>();

		// 查询已经有的地图，管理员可以看所有的仓库地图
		if (!RcUtil.isEmpty(userId)) {
			if ("ADMIN".equalsIgnoreCase(userId)) {
				models = this.dao
						.createCommonQuery(BasWarehouseModel.class)
						.addCondition(Condition.eq("status", CommonUtil.Active))
						.query();
			} else {
				models = this.dao
						.createCommonQuery(BasWarehouseModel.class)
						.addCondition(Condition.eq("officeCode", office))
						.addCondition(Condition.eq("status", CommonUtil.Active))
						.query();
			}
		}

		if (!RcUtil.isEmpty(models) && models.size() > 0) {
			for (int i = 0; i < models.size(); i++) {
				Map<String, String> map = new HashMap<String, String>();
				map.put("value", models.get(i).getBasWarehouseUuid());
				map.put("text", models.get(i).getWarehouseName());
				bolbUuid.add(models.get(i).getBasWarehouseUuid());
				// 如里是第一个数的话再加一个参数，让在页面时自动选中它
				if (i == 0) {
					map.put("selected", "true");
				}

				result.add(map);
			}

			// 把文件都输出到项目的目录中去
			result = this.createSvgFile(bolbUuid, office, realPath, result);
		}

		return result;
	}

	/**
	 * 根据UUID找出文件，并把它生成在项目的jsp/warehousemap/svg 目录下面
	 * 
	 * @param blobUuid
	 * @param officCode
	 * @throws Exception
	 */
	public List<Map<String, String>> createSvgFile(List<String> bolbUuid,
			String officeCode, String realPath, List<Map<String, String>> result)
			throws Exception {
		if (!RcUtil.isEmpty(result) && result.size() > 0) {
			// 组装文件要保存的路径
			String path = "";
			if (!RcUtil.isEmpty(realPath) && realPath.endsWith(File.separator)) {
				path = realPath + "jsp" + File.separator + "warehousemap"
						+ File.separator + "svg" + File.separator;
			} else {
				path = realPath + File.separator + "jsp" + File.separator
						+ "warehousemap" + File.separator + "svg"
						+ File.separator;
			}

			// 先找出该目录下的所有地图
			File[] hasFile = new File(path).listFiles();

			if (!RcUtil.isEmpty(hasFile) && hasFile.length > 0) { // 目录中有文件的话就拿出来比较比较，目录中有该地图的话就不输出，以地图的uuid命名并作为比较
				for (int i = 0; i < result.size(); i++) {
					// 先去数据库找出该仓库的地图来
					List<BasBlobModel> models = basBlobManager
							.findFileByFk(result.get(i).get("value"));

					if (!RcUtil.isEmpty(models) && models.size() > 0
							&& !RcUtil.isEmpty(models.get(0).getData())
							&& models.get(0).getData().length > 0) { // 有地图
						boolean has = false;
						BasBlobModel fileModel = models.get(0);
						String bUuid = fileModel.getBasBlobUuid();
						result.get(i).put("mapName", bUuid);

						// 先在目录中找是否已经有该文件，有的话就不输出
						for (int j = 0; j < hasFile.length; j++) {
							if (hasFile[j].getName().equals(bUuid + ".svg")) {
								has = true;
								break;
							}
						}

						// 目录中没有该地图，就输出
						if (!has) {
							String filePathName = path + bUuid + ".svg"; // 以文件的uuid命名，输出到目录中

							File file = new File(filePathName);
							FileOutputStream fos = new FileOutputStream(file);
							fos.write(fileModel.getData());
							fos.flush();
							fos.close();
						}
					} else { // 没地图
						result.get(i).put("mapName", "null");
					}

				}
			} else { // 目录中没有地图，就直接把地图都输出到目录中
				for (int i = 0; i < result.size(); i++) {
					// 查询该仓库的地图
					List<BasBlobModel> models = basBlobManager
							.findFileByFk(result.get(i).get("value"));
					if (!RcUtil.isEmpty(models) && models.size() > 0
							&& !RcUtil.isEmpty(models.get(0).getData())
							&& models.get(0).getData().length > 0) { // 有地图
						BasBlobModel fileModel = models.get(0);
						String bUuid = fileModel.getBasBlobUuid();
						String filePathName = path + bUuid + ".svg"; // 以文件的uuid命名，输出到目录中
						result.get(i).put("mapName", bUuid);

						File file = new File(filePathName);
						FileOutputStream fos = new FileOutputStream(file);
						fos.write(fileModel.getData());
						fos.flush();
						fos.close();
					} else { // 没地图
						result.get(i).put("mapName", "null");
					}
				}
			}
		}

		return result;
	}

	/**
	 * 根据出库单号查询货位
	 */
	public List<Map<String, String>> findLotByOutbound(String warehouseUuid,
			String officeCode, String logisticsOrderNo, String batchNo,
			String projectCode, String lotCode, String itemCode,
			String marksNumber, String model, String packageNo, String barcode,
			String goodsDesc, String spec, String goodsKind, String billNo,
			String orderDateStart, String orderDateEnd) {

		List<Map<String, String>> result = new ArrayList<Map<String, String>>();
		StringBuffer extraSql = new StringBuffer("0=0");

		if (!RcUtil.isEmpty(logisticsOrderNo)) {
			extraSql.append(" and lo.logistics_order_no = '" + logisticsOrderNo
					+ "'");
		} else {
			if (!RcUtil.isEmpty(batchNo)) {
				extraSql.append(" and rs.batch_no = '" + batchNo + "'");
			}
			if (!RcUtil.isEmpty(projectCode)) {
				extraSql.append(" and lo.project_code = '" + projectCode + "'");
			}
			if (!RcUtil.isEmpty(lotCode)) {
				extraSql.append(" and rs.lot_code = '" + lotCode + "'");
			}
			if (!RcUtil.isEmpty(itemCode)) {
				extraSql.append(" and rs.item_code like '%" + itemCode + "%'");
			}
			if (!RcUtil.isEmpty(marksNumber)) {
				extraSql.append(" and rs.marks_number = '" + marksNumber + "'");
			}
			if (!RcUtil.isEmpty(model)) {
				extraSql.append(" and lod.model = '" + model + "'");
			}
			if (!RcUtil.isEmpty(packageNo)) {
				extraSql.append(" and lod.package_no = '" + packageNo + "'");
			}
			if (!RcUtil.isEmpty(barcode)) {
				extraSql.append(" and rs.barcode = '" + barcode + "'");
			}
			if (!RcUtil.isEmpty(goodsDesc)) {
				extraSql.append(" and rs.goods_desc like '%' || '" + goodsDesc
						+ "'||'%'");
			}
			if (!RcUtil.isEmpty(spec)) {
				extraSql.append(" and lod.spec ='" + spec + "'");
			}
			if (!RcUtil.isEmpty(goodsKind)) {
				extraSql.append(" and lod.goods_kind = '" + goodsKind + "'");
			}
			if (!RcUtil.isEmpty(billNo)) {
				extraSql.append(" and lod.bill_no = '" + billNo + "'");
			}
			if (!RcUtil.isEmpty(orderDateStart)
					&& !RcUtil.isEmpty(orderDateEnd)) {
				extraSql.append(" and to_date(lo.order_date) >= to_date('"
						+ orderDateStart + "', 'yyyy-mm-dd hh24:mi:ss')");
				extraSql.append(" and to_date(lo.order_date) < to_date('"
						+ orderDateEnd + "', 'yyyy-mm-dd hh24:mi:ss')+1");
			}
		}

		// 查询货位
		List<WarehouseFindLotByLodNoQueryItem> items = new ArrayList<WarehouseFindLotByLodNoQueryItem>();
		WarehouseFindLotByLodNoQueryCondition condition = new WarehouseFindLotByLodNoQueryCondition(
				CommonUtil.TRANSACTIONTYPE_SIN, warehouseUuid, officeCode);
		items = this.dao.query(condition,
				WarehouseFindLotByLodNoQueryItem.class, extraSql.toString(),
				null, null, null);

		// 组织数据
		if (!RcUtil.isEmpty(items) && items.size() > 0) {
			for (WarehouseFindLotByLodNoQueryItem item : items) {
				Map<String, String> map = new HashMap<String, String>();
				map.put("name", item.getLotCode());
				result.add(map);
			}
		}

		return result;
	}

	public BasBlobManager getBasBlobManager() {
		return basBlobManager;
	}

	public void setBasBlobManager(BasBlobManager basBlobManager) {
		this.basBlobManager = basBlobManager;
	}
	@Override
	public List<EasyUiTree> getTreeData() {
		String officeCode = SessionContextUserEntity.currentUser()
				.getOfficeCode();

		List<BasLottreeEntity> listBas = new ArrayList<BasLottreeEntity>();
		// 仓库
		BasWarehouseModel bm = new BasWarehouseModel();
		bm.setOfficeCode(officeCode);
		List<BasWarehouseModel> basList = this.dao.findByExample(bm);
		if (basList.size() > 0) {
			for (BasWarehouseModel bmf : basList) {
				BasLottreeEntity be = new BasLottreeEntity();
				be.setId(bmf.getBasWarehouseUuid());
				be.setPreId(bmf.getPreBasWarehouseUuid());
				be.setText(bmf.getWarehouseName());
				be.setRemark("wh");
				listBas.add(be);
			}

		}
		// 货区
		BasLocAreaModel bla = new BasLocAreaModel();
		bla.setOfficeCode(officeCode);
		List<BasLocAreaModel> blaList = this.dao.findByExample(bla);
		if (basList.size() > 0) {
			for (BasLocAreaModel blaf : blaList) {
				BasLottreeEntity be = new BasLottreeEntity();
				be.setId(blaf.getBasLocAreaUuid());
				be.setPreId(blaf.getBasWarehouseUuid());
				be.setText(blaf.getLocAreaName());
				be.setRemark("area");
				listBas.add(be);
			}

		}
		//最高顶点的标识为0
		List<EasyUiTree>  listTree=getTree(listBas,"");
		return listTree;
	}
	public List<EasyUiTree> getTree(List<BasLottreeEntity> models, String ssid) {
		List<EasyUiTree> trees = new ArrayList<EasyUiTree>();
		for (BasLottreeEntity model : models) {
			if (model == null)
				continue;
			if (ssid.equals(RcUtil.isEmpty(model.getPreId())?"":model.getPreId())) {
				EasyUiTree tree = new EasyUiTree();
				tree.setId(model.getId());
				tree.setText(model.getText());
				tree.setRemark(model.getRemark());
				tree.setAttributes(model);
				tree.setChildren(getTree(models, model.getId().toString()));
				trees.add(tree);
			}
		}
		return trees;
	}
	
	/**
	 * 获取某仓库下的所有区域
	 */
	@Override
	public SinotransPageJson getLocAeaName(String officeCode,String basWarehouseUuid) {
		SinotransPageJson spj = new SinotransPageJson();
		List<BasLocAreaModel> blaModelList = new ArrayList<BasLocAreaModel>();
		blaModelList = this.dao.createCommonQuery(BasLocAreaModel.class)
				.addCondition(Condition.eq(BasLocAreaModel.FieldNames.officeCode,officeCode))
				.addCondition(Condition.eq(BasLocAreaModel.FieldNames.basWarehouseUuid, basWarehouseUuid))
				.query();
		if (!RcUtil.isEmpty(blaModelList)&&blaModelList.size()>0) {
			spj.setObject(blaModelList);
			spj.setResult(true);
		} else {
			spj.setError("获取区域失败！");
			spj.setResult(false);
		}
		return spj;
	}
}
