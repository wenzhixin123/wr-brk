package com.sinotrans.gd.wlp.inbound.querydatasource;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.sinotrans.framework.common.support.QueryDataSource;
import com.sinotrans.framework.common.support.QueryField;
import com.sinotrans.framework.core.service.impl.BaseManagerImpl;
import com.sinotrans.framework.core.support.PagingInfo;
import com.sinotrans.gd.wlp.common.web.RcUtil;
import com.sinotrans.gd.wlp.inbound.query.YclInboundQueryCondition;
import com.sinotrans.gd.wlp.inbound.query.YclInboundQueryItem;
import com.sinotrans.gd.wlp.system.entity.SessionContextUserEntity;
import com.sinotrans.gd.wlp.util.CommonUtil;

@Service
public class InboundOrderDataSource extends BaseManagerImpl implements QueryDataSource {
	@Override
	public List<YclInboundQueryItem> getData(List<QueryField> queryFields, String orderBy, PagingInfo pagingInfo) {
		YclInboundQueryCondition condition = new YclInboundQueryCondition();
		for (QueryField field : queryFields) {
			String name = field.getFieldName();
			String fieldVal = field.getFieldStringValue();
			if ("submitOrderNo".equals(name)) {
				condition.setSubmitOrderNo(fieldVal);
			} else if ("orderNo".equals(name)) {
				condition.setOrderNo(fieldVal);
			} else if ("transactionStatus".equals(name)) {
				if (!"All".equals(fieldVal)) {
					condition.setTransactionStatus(fieldVal);
				}
			} else if ("dateBegin".equals(name)) {
				if (RcUtil.isEmpty(condition.getSubmitOrderNo())) {
					condition.setDateBegin(RcUtil.string2date(fieldVal, RcUtil.yyyy_MM_dd));
				}
			} else if ("dateEnd".equals(name)) {
				if (RcUtil.isEmpty(condition.getSubmitOrderNo())) {
					condition.setDateEnd(RcUtil.string2date(fieldVal, RcUtil.yyyy_MM_dd));
				}
			} else if ("billNo".equals(name)) {
				condition.setBillNo(fieldVal);
			} else if ("itemCode".equals(name)) {
				condition.setItemCode(fieldVal);
			} else if ("ct".equals(name)) {
				if (fieldVal.equals("true")) {
					condition.setCt("0");
				}
			} else if ("cargoConsigneeCode".equals(name)) {
				condition.setCargoConsigneeDesc(fieldVal);
			} else if ("deliveryType".equals(name)) {
				condition.setDeliveryType(fieldVal);
			}
		}
		SessionContextUserEntity scue = SessionContextUserEntity.currentUser();
		condition.setOfficeCode(scue.getOfficeCode());
		condition.setTransactionType(CommonUtil.TRANSACTIONTYPE_SIN);
		List<YclInboundQueryItem> itemList = new ArrayList<YclInboundQueryItem>();
		itemList = dao.query(condition, YclInboundQueryItem.class, null, null, orderBy, pagingInfo);
		return itemList;
	}

	@Override
	public Class<YclInboundQueryItem> getDataItemClass() {
		return YclInboundQueryItem.class;
	}

}
