package com.sinotrans.gd.wlp.outbound.querydatasource;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sinotrans.framework.common.support.QueryDataSource;
import com.sinotrans.framework.common.support.QueryField;
import com.sinotrans.framework.core.service.impl.BaseManagerImpl;
import com.sinotrans.framework.core.support.PagingInfo;
import com.sinotrans.gd.wlp.common.web.RcUtil;
import com.sinotrans.gd.wlp.outbound.query.YcloutBoundOrderQueryCondition;
import com.sinotrans.gd.wlp.outbound.query.YcloutBoundOrderQueryItem;

@Service
public class YclOutBoundOrderMangeSearchQueryDataSource extends BaseManagerImpl
		implements QueryDataSource {

	@Override
	public List<YcloutBoundOrderQueryItem> getData(
			List<QueryField> queryFields, String orderBy, PagingInfo pagingInfo) {
		String cargoConsigneeDesc = "";
		String officeCode = "";
		String submitOrderNo = null;
		String deliveryType = "";
		String orderNo = "";
		String projectCode = "";
		String itemCode = "";
		String dateBegin = "";
		String dateEnd = "";
		String billNo = "";
		String spec="";
		String transactionStatus="";
		String daBatchNo="";
		String orderNoLike="";
		String orderNostatus="";
		String sql="  ";
		String kitting="";
		String deliveryDateBegin = "";
		String deliveryDateEnd = "";
		YcloutBoundOrderQueryCondition condition = new YcloutBoundOrderQueryCondition();
		for (QueryField queryField : queryFields) {
			if ("officeCode".equals(queryField.getFieldName())) {
				officeCode = queryField.getFieldStringValue();
				condition.setOfficeCode(officeCode);
			}else if ("submitOrderNo".equals(queryField.getFieldName())) {
				submitOrderNo = queryField.getFieldStringValue();
				condition.setSubmitOrderNo(submitOrderNo);
				break;
			} else if ("deliveryType".equals(queryField.getFieldName())) {
				deliveryType = queryField.getFieldStringValue();
				condition.setDeliveryType(deliveryType);
			} else if ("orderNo".equals(queryField.getFieldName())) {
				orderNo = queryField.getFieldStringValue();
				String[] orderNos = orderNo.split("\\n");
				for (int i = 0; i < orderNos.length; i++) {
					orderNos[i] = orderNos[i].trim();
				}
				condition.setOrderNo(orderNos);
			} else if ("projectCode".equals(queryField.getFieldName())) {
				projectCode = queryField.getFieldStringValue();
				condition.setProjectCode(projectCode);
			} else if ("itemCode".equals(queryField.getFieldName())) {
				itemCode = queryField.getFieldStringValue();
				condition.setItemCode(itemCode);
			} else if ("dateBegin".equals(queryField.getFieldName())) {
				if(RcUtil.isEmpty(orderNo)){
					dateBegin = queryField.getFieldStringValue();
					condition.setDateBegin(RcUtil.string2date(dateBegin, RcUtil.yyyy_MM_dd));
				}				
			} else if ("dateEnd".equals(queryField.getFieldName())) {
				if(RcUtil.isEmpty(orderNo)){
					dateEnd = queryField.getFieldStringValue();
					condition.setDateEnd(RcUtil.string2date(dateEnd, RcUtil.yyyy_MM_dd));
				}
			} else if("cargoConsigneeCode".equals(queryField.getFieldName())){
				cargoConsigneeDesc=queryField.getFieldStringValue();
				condition.setCargoConsigneeDesc(cargoConsigneeDesc);
			}else if("billNo".equals(queryField.getFieldName())){
				billNo=queryField.getFieldStringValue();
				condition.setBillNo(billNo);
			}else if("spec".equals(queryField.getFieldName())){
				spec=queryField.getFieldStringValue();
				condition.setSpec(spec);
			}else if("transactionStatus".equals(queryField.getFieldName())){
				transactionStatus=queryField.getFieldStringValue();
				condition.setTransactionStatus(transactionStatus);
			}else if("daBatchNo".equals(queryField.getFieldName())){
				daBatchNo=queryField.getFieldStringValue();
				condition.setDaBatchNo(daBatchNo);
			}else if("orderNoLike".equals(queryField.getFieldName())){
				orderNoLike=queryField.getFieldStringValue();
				condition.setOrderNoLike(orderNoLike);
			}else if("orderNostatus".equals(queryField.getFieldName())){
				orderNostatus=queryField.getFieldStringValue();
				if("未办单".equals(orderNostatus)){
					sql= sql+" t.order_Qty is null ";
				}else if("部分办单".equals(orderNostatus)){
					sql= sql+" t.SUMQTY is not null  and t.order_Qty!=t.SUMQTY";
				}else if("已办单".equals(orderNostatus)){
					sql= sql+" t.SUMQTY is not null and t.order_Qty=t.SUMQTY";
				}
				
			}else if("kitting".equals(queryField.getFieldName())){
				kitting=queryField.getFieldStringValue();
				condition.setKitting(kitting);
			}else if ("deliveryDateBegin".equals(queryField.getFieldName())) {
				if(RcUtil.isEmpty(orderNo)){
					deliveryDateBegin = queryField.getFieldStringValue();
					condition.setDeliveryDateBegin(RcUtil.string2date(deliveryDateBegin, RcUtil.yyyy_MM_dd));
				}				
			} else if ("deliveryDateEnd".equals(queryField.getFieldName())) {
				if(RcUtil.isEmpty(orderNo)){
					deliveryDateEnd = queryField.getFieldStringValue();
					condition.setDeliveryDateEnd(RcUtil.string2date(deliveryDateEnd, RcUtil.yyyy_MM_dd));
				}
			}else if("workDemandTypeCode".equals(queryField.getFieldName())){
				condition.setWorkDemandTypeCode(queryField.getFieldStringValue());
			}
		}	
		List<YcloutBoundOrderQueryItem> listItem = this.dao.query(condition, YcloutBoundOrderQueryItem.class, sql, null, orderBy, pagingInfo);
		return listItem;
	}

	@Override
	public Class<YcloutBoundOrderQueryItem> getDataItemClass() {
		return YcloutBoundOrderQueryItem.class;
	}

}
