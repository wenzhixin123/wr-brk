package com.sinotrans.gd.wlp.common.datasource;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sinotrans.framework.common.support.QueryDataSource;
import com.sinotrans.framework.common.support.QueryField;
import com.sinotrans.framework.core.service.impl.BaseManagerImpl;
import com.sinotrans.framework.core.support.PagingInfo;
import com.sinotrans.gd.wlp.common.query.YclRemainDetailQueryCondition;
import com.sinotrans.gd.wlp.common.query.YclRemainDetailQueryItem;
import com.sinotrans.gd.wlp.common.web.RcUtil;

@Service
public class YclRemainStockDeatilQueryDataSource extends BaseManagerImpl implements QueryDataSource{

	@Override
	public List<?> getData(List<QueryField> queryFields, String orderBy,
			PagingInfo pagingInfo) {
		YclRemainDetailQueryCondition condition = new YclRemainDetailQueryCondition();
		String exaSql = "0=0";
		for (QueryField field : queryFields) {
			String name = field.getFieldName();
			String value = field.getFieldStringValue();
			if("officeCode".equals(name)){
				condition.setOfficeCode(value);
			}else if("itemCode".equals(name)){
				condition.setItemCode(value);
			}else if("basLocAreaUuid".equals(name)){
				condition.setBasLocAreaUuid(value);
			}else if("logisticsOrderNo".equals(name)){
				if(!RcUtil.isEmpty(value)){
					exaSql+=" and lo.logistics_order_no='"+value+"'";
				}
			}else if("orderNo".equals(name)){
				if(!RcUtil.isEmpty(value)){
					exaSql+=" and lo.order_no='"+value+"'";
				}
				
			}else if("barcode".equals(name)){
				if(!RcUtil.isEmpty(value)){
					exaSql+=" and rs.barcode='"+value+"'";
				}
				
			}else if("extItemCode".equals(name)){
				if(!RcUtil.isEmpty(value)){
					exaSql+=" and rs.ext_item_code='"+value+"'";
				}else{
					exaSql+=" and rs.ext_item_code is null";
				}
			}else if("goodsKind".equals(name)){
				if(!RcUtil.isEmpty(value)){
					exaSql+=" and rs.goods_kind='"+value+"'";
				}
				
			}else if("cargoConsigneeCode".equals(name)){
				if(!RcUtil.isEmpty(value)){
					exaSql+=" and lod.aux5='"+value+"'";
				}
			}else if("panelNo".equals(name)){
				if(!RcUtil.isEmpty(value)){
					exaSql+=" and rs.panel_no='"+value+"'";
				}		
			}else if("lotCode".equals(name)){
				if(!RcUtil.isEmpty(value)){
					exaSql+=" and rs.lot_code='"+value+"'";
				}
				
			}
		}
		List<YclRemainDetailQueryItem> result = dao.query(condition, YclRemainDetailQueryItem.class, exaSql, null, orderBy, pagingInfo);
		return result;
	}

	@Override
	public Class<YclRemainDetailQueryItem> getDataItemClass() {
		return YclRemainDetailQueryItem.class;
	}

}
