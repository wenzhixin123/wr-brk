package com.sinotrans.gd.wlp.scanner.service;

import com.sinotrans.framework.core.service.BaseManager;
import com.sinotrans.gd.wlp.common.web.SinotransPageJson;

public interface ScannerOutboundManager extends BaseManager {
	/**
	 * 出库核销
	 * @param logisticsOrderNo
	 * @param barcode
	 * @param qty
	 * @param officeCode
	 * @return
	 * @throws Exception
	 */
	public SinotransPageJson outboundCheckLogisticsOrderNo(String logisticsOrderNo, String officeCode);
	
	public SinotransPageJson outboundCheckBarcode(String logisticsOrderNo, String barcode, String officeCode);
	
	public SinotransPageJson confirmOutboundVeri(String logisticsOrderNo, String barcode, String qty, String officeCode) throws Exception;
	
	public SinotransPageJson cancelOutboundVeri(String logisticsOrderNo, String barcode, String qty, String officeCode) throws Exception;

}
