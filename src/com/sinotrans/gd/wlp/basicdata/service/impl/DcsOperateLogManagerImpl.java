package com.sinotrans.gd.wlp.basicdata.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;

import com.sinotrans.framework.core.query.BaseQueryCondition;
import com.sinotrans.framework.core.service.impl.BaseManagerImpl;
import com.sinotrans.framework.core.support.CommonQuery;
import com.sinotrans.framework.core.support.Condition;
import com.sinotrans.framework.core.support.NewTransactionTemplate;
import com.sinotrans.framework.core.util.ContextUtils;
import com.sinotrans.gd.wlp.basicdata.model.DcsOperateLogModel;
import com.sinotrans.gd.wlp.basicdata.query.TableCommentQueryItem;
import com.sinotrans.gd.wlp.basicdata.service.DcsOperateLogManager;

@Service
public class DcsOperateLogManagerImpl extends BaseManagerImpl implements DcsOperateLogManager {

	public DcsOperateLogModel get(String id) {
		return this.dao.get(DcsOperateLogModel.class, id);
	}

	public List<DcsOperateLogModel> getAll() {
		return this.dao.getAll(DcsOperateLogModel.class);
	}

	public List<DcsOperateLogModel> findByExample(DcsOperateLogModel example) {
		return this.dao.findByExample(example);
	}
	/**
	 * 获取表字段 名称
	 * @param tableCommentQueryCondition
	 * @return
	 */
	public List<TableCommentQueryItem> getTableColuns(BaseQueryCondition tableCommentQueryCondition) {
		return this.dao.createCommonQuery(tableCommentQueryCondition, TableCommentQueryItem.class).query();
	}

	/**
	 * 根据数据id 查询该记录是否打印过
	 * 
	 * @param example
	 * @return
	 */
	public List<DcsOperateLogModel> findByDataId(String businessRefNo, String printType, String dataSrcouid) {
		System.out.println("---------------------------------------------------");
		System.out.println(dataSrcouid);
		System.out.println(businessRefNo);
		System.out.println("---------------------------------------------------");

		// printType 用 1 ,2 ,3 ,4 区分 现在已经用到4 以后 在用从5开始
		CommonQuery commonQuery = this.dao.createCommonQuery(DcsOperateLogModel.class);
		if (StringUtils.isNotBlank(dataSrcouid)) {
			commonQuery.addCondition(Condition.eq(DcsOperateLogModel.FieldNames.dataSourceUuid, dataSrcouid));
		}
		if (StringUtils.isNotBlank(printType)) {
			String[] printTypes = printType.split(",");
			Object[] obj = new Object[printTypes.length];
			for (int i = 0; i < printTypes.length; i++) {
				obj[i] = printTypes[i];
			}
			commonQuery.addCondition(Condition.in(DcsOperateLogModel.FieldNames.aux1, obj));
		}
		if (StringUtils.isNotBlank(businessRefNo)) {
			String[] orderNos = businessRefNo.split(",");
			Object[] obj = new Object[orderNos.length];
			for (int i = 0; i < orderNos.length; i++) {
				obj[i] = orderNos[i];
			}
			commonQuery.addCondition(Condition.in(DcsOperateLogModel.FieldNames.businessRefNo, obj));
		}
		commonQuery.addCondition(Condition.eq(DcsOperateLogModel.FieldNames.opeationType, DcsOperateLogModel.OPEATIONTYPE_PRINT));
		List<DcsOperateLogModel> printList = commonQuery.query();
		List<DcsOperateLogModel> returnList = new ArrayList<DcsOperateLogModel>();
		Map<String, DcsOperateLogModel> map = new HashMap<String, DcsOperateLogModel>();
		if (null != printList && printList.size() > 0) {
			if (null != printList) {
				for (DcsOperateLogModel model : printList) {
					if (map.get(model.getBusinessRefNo()) == null) {
						map.put(model.getBusinessRefNo(), model);
					}
				}
			}
			for (String key : map.keySet()) {
				returnList.add(map.get(key));
			}
			return returnList;
		}
		return null;
	}

	@Override
	public DcsOperateLogModel saveSingle(final DcsOperateLogModel model) {
		return (DcsOperateLogModel)new NewTransactionTemplate().execute(new TransactionCallback() {
			public Object doInTransaction(TransactionStatus arg0) {
				DcsOperateLogManager dcsOperateLogManager = (DcsOperateLogManager) ContextUtils.getBean("dcsOperateLogManager");
				return dcsOperateLogManager.save(model);
			}
		});
	}
	
	public DcsOperateLogModel save(DcsOperateLogModel model) {
		if (model != null && StringUtils.isNotBlank(model.getBusinessRefNo())) {
			String[] orderNos = model.getBusinessRefNo().split(",");
			for (int i = 0; i < orderNos.length; i++) {
				DcsOperateLogModel logModel = model;
				logModel.setBusinessRefNo(orderNos[i]);
				DcsOperateLogModel operateLogModel = this.dao.save(logModel);
				if (operateLogModel == null) {
					return null;
				}
			}
		}
		return new DcsOperateLogModel();
	}

	public List<DcsOperateLogModel> saveAll(Collection<DcsOperateLogModel> models) {
		return this.dao.saveAll(models);
	}

	public void remove(DcsOperateLogModel model) {
		this.dao.remove(model);
	}

	public void removeAll(Collection<DcsOperateLogModel> models) {
		this.dao.removeAll(models);
	}

	public void removeByPk(String id) {
		this.dao.removeByPk(DcsOperateLogModel.class, id);
	}

	public void removeAllByPk(Collection<String> ids) {
		this.dao.removeAllByPk(DcsOperateLogModel.class, ids);
	}

	/*@Override
	public void removeFee(ReleaseFeeQueryItem item) {
		// TODO Auto-generated method stub
		DcsOperateLogModel model=new DcsOperateLogModel();
		model.setCreateTime(item.getCollectdate());
		model.setBusinessRefNo(item.getReleasedNo());
		List<DcsOperateLogModel> modles=findByExample(model);
		if(modles!=null&&modles.size()>0){
			DcsOperateLogModel log=modles.get(0);
			log.setOpeationType("deleted");
			save(log);
		}
	}*/

}
