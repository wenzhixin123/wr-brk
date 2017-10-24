package com.sinotrans.gd.wlp.scanner.service;

/**
 * 巴枪操作类
 */

import com.sinotrans.framework.core.service.BaseManager;
import com.sinotrans.gd.wlp.common.web.SinotransPageJson;

public interface ScannerInboundManager extends BaseManager {
	/**
	 * 成品入库
	 * @param startBarcode
	 * @param batchNo
	 * @param officeCode
	 * @return
	 */
	public SinotransPageJson cpInboundCheckStartBarcode(String packageNo, String startBarcode, String officeCode);
	
	public SinotransPageJson cpInboundConfirm(String lineNo, String batchNo, String packageNo, String startBarcode, String endBarcode, String qtyStr, String officeCode);

	public SinotransPageJson cpInboundCancel(String batchNo,  String startBarcode, String endBarcode, String officeCode);
		
	

	/**
	 * QA抽检(成品收货)
	 * @param barcode
	 * @param officeCode
	 * @return
	 */
	public SinotransPageJson cpReceinvingCheckBarcode(String startQty, String endQty, String iqcBarcode, String officeCode);
	
	public SinotransPageJson cpReceinvingConfirmStatus(String startQty, String endQty, String iqcBarcode, String type, String officeCode);
	
	public SinotransPageJson cpReceinvingConfirmRece(String startQty, String endQty, String iqcBarcode, String officeCode) throws Exception;
	
	public SinotransPageJson cpReceinvingCancelRece(String startQty, String endQty, String iqcBarcode, String officeCode) throws Exception;
	

}
