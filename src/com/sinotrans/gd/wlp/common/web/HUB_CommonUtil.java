/**
 * 
 */
package com.sinotrans.gd.wlp.common.web;

import com.sinotrans.gd.wlp.util.CommonUtil;

/**
 * @author sky
 * 
 *         HUB 项目常量工具类
 * 
 */
public class HUB_CommonUtil extends CommonUtil {
	/**
	 * 加工指令导入
	 */
	public static final String HUB_PDN_IMPORT = "HUB_PDN_IMPORT";
	/**
	 * 加工单导入
	 */
	public static final String HUB_PDNORDER_IMPORT = "HUB_PDNORDER_IMPORT";
	/**
	 * 加工单号生成常量
	 */
	public static final String HUB_TRANSACTIONTYPE_PDN = "SEQUENCE_Processing";

	/**
	 * 入库作业单导入
	 */
	public static final String HUB_LOGISTICSORDER_IMPORT = "HUB_LOGISTICSORDER_IMPORT";
	/**
	 * 入库作业单号生成常量
	 */
	public static final String HUB_TRANSACTIONTYPE_SIN = "SEQUENCE_SIN";

	/**
	 * 出库作业单导入
	 */
	public static final String HUB_LOGISTICSORDER_IMPORTSOT = "HUB_LOGISTICSORDER_IMPORTSOT";

	/**
	 * 出库导入管理
	 */
	public static final String HUB_OUTBOUNDIMPORT_MANAGE = "HUB_OUTBOUNDIMPORT_MANAGE";

	/**
	 * 出库作业单号生成常量
	 */
	public static final String HUB_TRANSACTIONTYPE_SOT = "SEQUENCE_SOT";

	/**
	 * 加工单明细控制位查询
	 */
	public static final String HUB_LOD_S = "S0000000000000000000";
	public static final String HUB_LOD_W = "W0000000000000000000";
	public static final String HUB_LOD_T = "T0000000000000000000";

	/**
	 * 物料名称与描述
	 */
	public final static String CODE_DESC = "CODE_DESC";
	/**
	 * 箱型
	 */
	public final static String HUB_ALL_WRAPPER = "ALL_WRAPPER_FOR_UUID";

	/**
	 * 控制码
	 */
	public final static String HUB_CONTROL_TYPE = "HUB_CONTROL_TYPE";
	/**
	 * 普通码
	 */
	public final static String CONTROL_TYPE_P = "P";
	/**
	 * 特殊码
	 */
	public final static String CONTROL_TYPE_T = "T";

	/**
	 * 委托单位
	 */
	public final static String ALL_ENTRUST = "ALL_ENTRUST";

	/**
	 * 包装单位
	 */
	public final static String ALL_UNIT = "ALL_UNIT";
	/**
	 * spu默认数量
	 */
	public final static double HUB_DEFAULT_SPUQTY = 1;
	/**
	 * spu默认包装单位
	 */
	public final static String HUB_DEFAULT_UNITNAME = "箱";

	/**
	 * 华为CES 导出唛头文件
	 */
	public final static String CES_EXPORT_MARKS = "CES_EXPORT_MARKS";
	/**
	 * 加工单默认拣货策略
	 */
	public final static String HUB_CONFIG_CODE="G2012061800001";
	/**
	 * 加工单拣货策略
	 */
	public final static String HUB_PICK_CONFIG_CODE="PICK_CONFIG_CODE";

}
