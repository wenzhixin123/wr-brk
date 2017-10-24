package com.sinotrans.gd.wlp.statistics.querydatasource;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sinotrans.framework.common.support.QueryDataSource;
import com.sinotrans.framework.common.support.QueryField;
import com.sinotrans.framework.core.service.impl.BaseManagerImpl;
import com.sinotrans.framework.core.support.PagingInfo;
import com.sinotrans.gd.wlp.common.web.RcUtil;
import com.sinotrans.gd.wlp.statistics.query.OutboundEditDetail2QueryCondition;
import com.sinotrans.gd.wlp.statistics.query.OutboundEditDetail2QueryItem;
import com.sinotrans.gd.wlp.system.entity.SessionContextUserEntity;
@Service
public class RhChoiceStockDataSource  extends BaseManagerImpl
implements QueryDataSource {

	@Override
	public List<OutboundEditDetail2QueryItem> getData(List<QueryField> queryFields, String orderBy,
			PagingInfo pagingInfo) {
		SessionContextUserEntity scue = SessionContextUserEntity.currentUser();
		String logisticsOrderNo="";
		String orderNo="";
		String batchNo="";
		String cargoConsigneeCode="";
		String itemCode="";
		String spec="";
		String packageNo="";
		String orderDateStart="";
		String orderDateEnd="";
		String barcode="";
		OutboundEditDetail2QueryCondition condition = new OutboundEditDetail2QueryCondition();
		for (QueryField queryField : queryFields) {
			 if ("logisticsOrderNo".equals(queryField.getFieldName())) {
				logisticsOrderNo = queryField.getFieldStringValue();
				condition.setLogisticsOrderNo(logisticsOrderNo);
			}else if ("orderNo".equals(queryField.getFieldName())) {
				orderNo = queryField.getFieldStringValue();
				condition.setOrderNo(orderNo);
			}else if ("batchNo".equals(queryField.getFieldName())) {
				batchNo = queryField.getFieldStringValue();
				condition.setBatchNo(batchNo);
			}else if ("cargoConsigneeCode".equals(queryField.getFieldName())) {
				cargoConsigneeCode = queryField.getFieldStringValue();
				condition.setCargoConsigneeCode(cargoConsigneeCode);
			}else if ("orderDateStart".equals(queryField.getFieldName())) {
				orderDateStart = queryField.getFieldStringValue();
				condition.setOrderDateStart(RcUtil.toDate(orderDateStart));
			}else if ("orderDateEnd".equals(queryField.getFieldName())) {
				orderDateEnd = queryField.getFieldStringValue();
				condition.setOrderDateEnd(RcUtil.toDate(orderDateEnd));
			}else if ("itemCode".equals(queryField.getFieldName())) {
				itemCode = queryField.getFieldStringValue();
				condition.setItemCode(itemCode);
			}else if ("spec".equals(queryField.getFieldName())) {
				spec = queryField.getFieldStringValue();
				condition.setSpec(spec);
			}else if ("packageNo".equals(queryField.getFieldName())) {
				packageNo = queryField.getFieldStringValue();
				condition.setPackageNo(packageNo);
			}else if ("barcode".equals(queryField.getFieldName())) {
				barcode = queryField.getFieldStringValue();
				condition.setBarcode(barcode);
			}
			
		}
		condition.setOfficeCode(scue.getOfficeCode());
		String extrsSql = " 0=0 ";
		List<OutboundEditDetail2QueryItem> lodqiList = dao.query(condition,
				OutboundEditDetail2QueryItem.class, extrsSql, null, null,
				null);
		return lodqiList;
	}

	@Override
	public Class<OutboundEditDetail2QueryItem> getDataItemClass() {
		return OutboundEditDetail2QueryItem.class;
	}

}
