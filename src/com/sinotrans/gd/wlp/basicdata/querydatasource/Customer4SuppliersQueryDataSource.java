/**
 * 
 */
package com.sinotrans.gd.wlp.basicdata.querydatasource;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sinotrans.framework.common.support.QueryDataSource;
import com.sinotrans.framework.common.support.QueryField;
import com.sinotrans.framework.core.service.impl.BaseManagerImpl;
import com.sinotrans.framework.core.support.PagingInfo;
import com.sinotrans.gd.wlp.basicdata.query.FuzzySearchCustomerQueryCondition;
import com.sinotrans.gd.wlp.basicdata.query.FuzzySearchCustomerQueryItem;
import com.sinotrans.gd.wlp.system.entity.SessionContextUserEntity;

/**
 * @author sky
 * 
 *         查询客户类型属于供应商的客户资料
 * 
 */
@Service
public class Customer4SuppliersQueryDataSource extends BaseManagerImpl
		implements QueryDataSource {



	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.sinotrans.framework.common.support.QueryDataSource#getData(java.util
	 * .List, java.lang.String, com.sinotrans.framework.core.support.PagingInfo)
	 */
	@Override
	public List<FuzzySearchCustomerQueryItem> getData(
			List<QueryField> queryFields, String orderBy, PagingInfo pagingInfo) {
		SessionContextUserEntity scue = SessionContextUserEntity.currentUser();	
		String officeCode = scue.getOfficeCode();
		FuzzySearchCustomerQueryCondition fscqc = new FuzzySearchCustomerQueryCondition();
		fscqc.setOfficeCode(officeCode);
		if (queryFields != null && queryFields.size() > 0) {
			for (QueryField qf : queryFields) {
				if ("customerCode".equals(qf.getFieldName())) {
					fscqc.setCustomerCode(qf.getFieldStringValue());
				} else {
					if ("in".equals(qf.getOperator())) {
						String[] args = (String[]) qf.getFieldValue();
						fscqc.setKey(args[0].toString());
					} else {
						fscqc.setKey(qf.getFieldValue().toString());
					}
				}
			}
		}
		List<FuzzySearchCustomerQueryItem> fscqiList = this.dao.query(fscqc,
				FuzzySearchCustomerQueryItem.class,
				" SUBSTR(BC.CONTROL_WORD, 2, 1) = '1' ", null, orderBy,
				pagingInfo);

		return fscqiList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.sinotrans.framework.common.support.QueryDataSource#getDataItemClass()
	 */
	@Override
	public Class<FuzzySearchCustomerQueryItem> getDataItemClass() {
		return FuzzySearchCustomerQueryItem.class;
	}
}
