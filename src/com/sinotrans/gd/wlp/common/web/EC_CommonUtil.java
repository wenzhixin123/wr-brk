/**
 * 
 */
package com.sinotrans.gd.wlp.common.web;

import java.io.Serializable;

import com.sinotrans.gd.wlp.util.CommonUtil;

/**
 * @author sky
 * 
 *         电子商务常用工具类
 * 
 *         存放公用的常量信息
 * 
 * 
 */
@SuppressWarnings("serial")
public class EC_CommonUtil extends CommonUtil implements Serializable {
	private EC_CommonUtil() {
	}

	// 控制位

	/**
	 * 产品品质
	 * 
	 * REMAIN_SINWORK、REMAIN_HOLD、REMAIN_STATIC
	 * 
	 */
	public final static String CONTROL_WORD_QUALITY_0_DESC = "正品";
	public final static String CONTROL_WORD_QUALITY_I_DESC = "次品";

	/**
	 * LOCATION_TASK
	 * 
	 * 第3位：EDI发送：S – 发送报文给良无限
	 */
	public final static String CONTROL_WORD_EDI_S = "S";

	/**
	 * lo,lod完结第三位F
	 */
	public final static String CONTROL_WORD_F = "F";
	/**ho拦截成功**/
	public final static String CONTROL_WORD_H = "H";
	/**ho拦截失败**/
	public final static String CONTROL_WORD_E = "E";
	// 单据类型
	/**
	 * 销售订单类型
	 */
	public final static String EC_SALE_DELIVERY_TYPE = "EC_SALE_DELIVERY_TYPE";
	/**
	 * 销售订单导入类型
	 */
	public final static String EC_SALE_ORDER_IMPORT = "EC_SALE_ORDER_IMPORT";
	/**
	 * 入库作业项目
	 */
	public final static String EC_IN_DELIVERY_TYPE = "EC_IN_DELIVERY_TYPE";

	/**
	 * 单据状态
	 */
	public final static String EC_TRANSACTION_STATUS = "EC_TRANSACTION_STATUS";

	/**
	 * 销售订单状态
	 */
	public final static String EC_ORDER_TRANSACTION_STATUS = "EC_ORDER_TRANSACTION_STATUS";
	/**
	 * 系统回传状态
	 */
	public final static String EC_SYSTEM_RETURN_STATUS = "EC_SYSTEM_RETURN_STATUS";

	/**
	 * 当前级别的officeCode和下级的officeCode
	 */
	public final static String EC_THIS_OFFICE_CODE = "THIS_OFFICE_CODE";

	// 单据状态
	/** 暂存 */
	public final static String PENDING = "Pending";
	/** 审核 */
	public final static String ACTIVE = "Active";
	/** 完成 */
	public final static String FINISHED = "Finished";
	/** 作废 */
	public final static String CANCEL = "Cancel";
	/** 完结 */
	public final static String CONFIRM = "Confirm";
	/** 缺货 */
	public final static String OUTOFSTOCK = "OutOfStock";
	/** 审核失败 **/
	public final static String EC_ACTIVEERROR = "ActiveError";
	/** 打印 **/
	public final static String EC_PRINT = "Print";
	/** 拣货 **/
	public final static String EC_PICK = "Pick";
	/** 包装 **/
	public final static String EC_PACKING = "Packing";
	/** 发货 **/
	public final static String EC_DELIVERY = "Delivery";
	/** 缺货中文 **/
	public final static String EC_OUTOFSTOCKCN = "缺货";
	/**订单取消审核sol状态**/
	public final static String EC_ACTIVECANCEL="ActiveCancel";

	/** 拦截成功 **/
	public final static String EC_HOLD = "Hold";
	
	
	/**正在拦截**/
	
	public final static String EC_HOLDING = "Holding";

	/** 拦截失败 **/

	public final static String EC_ERROR = "Error";

	/** 全部 **/
	public final static String ALL = "ALL";

	/**
	 * 收货文件导入类型
	 */
	public final static String EC_RECE = "EC_RECE";

	public final static String S01 = "S01";// 业务字段缺失或类型不一致\

	public final static String S02 = "S02";// 取消订单失败

	public final static String S03 = "S03";// 业务异常

	public final static String S04 = "S04";// 系统异常

	public final static String TYPETRUE = "T";// 正确

	public final static String TYPEFALSE = "F";// 错误

	public final static String BOOLEANTRUE = "true";

	public final static String BOOLEANFALSE = "false";

	public final static String WAREHOUSENTITYINCALLBACK = "warehouseEntryInCallback";// 入库单确认回传接口

	public final static String WAREHOUSEENTITYOUTCALLBACK = "warehouseEntryOutCallback";// 出库单确认回传接口

	public final static String ORDERCALLBACK = "orderCallback";// 订单状态回传,发货订单状态回传接口

	public final static String STORAGEADJUSTCALLBACK = "storageAdjustCallback";// 库存调准通知接口
	
	public final static String LOGISTICSPAGEINFOORDERCALLBACK = "logisticsPageOrderCallback";// 物流订单包裹信息回传

	/**
	 * 选择快递公司
	 */
	public final static String EC_SITE_DATE_TYPE_CHANGE_CU = "CHANGE_CU";
	/**
	 * 设置快递公司
	 */
	public final static String EC_SITE_DATE_TYPE_SETUP_CU = "SETUP_CU";
	/**
	 * 进库单
	 */
	public final static String EC_TRANSACTIONTYPE_SIN = "EC_TRANSACTIONTYPE_SIN";
	/**
	 * 入库单4SubmitOrderModel
	 */
	public final static String EC_TRANSACTIONTYPE_SIN4SO = "EC_TRANSACTIONTYPE_SIN4SO";
	/**
	 * 加工单
	 */
	public final static String EC_TRANSACTIONTYPE_PDN = "EC_TRANSACTIONTYPE_PDN";
	/**
	 * 出库单
	 */
	public final static String EC_TRANSACTIONTYPE_SOT = "EC_TRANSACTIONTYPE_SOT";
	/**
	 * 出库单
	 */
	public final static String EC_TRANSACTIONTYPE_SOT4SO = "EC_TRANSACTIONTYPE_SOT4SO";
	/**
	 * 拣货单
	 */
	public final static String EC_TRANSACTIONTYPE_PICK = "EC_TRANSACTIONTYPE_PICK";
	/**
	 * 拣货
	 */
	public final static String EC_TRANSACTIONTYPE_PICK4LT = "EC_TRANSACTIONTYPE_PICK4LT";
	/**
	 * 取消拣货
	 */
	public final static String EC_TRANSACTIONTYPE_CANP4LT = "EC_TRANSACTIONTYPE_CANP4LT";
	/**
	 * 批量拣货
	 */
	public final static String EC_TRANSACTIONTYPE_STUP = "EC_TRANSACTIONTYPE_STUP";
	/**
	 * 取消批量拣货
	 */
	public final static String EC_TRANSACTIONTYPE_CASP = "EC_TRANSACTIONTYPE_CASP";
	/**
	 * 出库核销
	 */
	public final static String EC_TRANSACTIONTYPE_OUTV = "EC_TRANSACTIONTYPE_OUTV";
	/**
	 * 取消出库核销
	 */
	public final static String EC_TRANSACTIONTYPE_CANV = "EC_TRANSACTIONTYPE_CANV";
	/**
	 * 收货系统编号生成规则
	 */
	public final static String EC_TRANSACTIONTYPE_RECE = "EC_TRANSACTIONTYPE_RECE";

	/**
	 * 取消收货系统编号生成规则
	 */
	public final static String EC_TRANSACTIONTYPE_CANR = "EC_TRANSACTIONTYPE_CANR";

	/**
	 * 移位单号生成code
	 */
	public final static String EC_TRANSACTIONTYPE_LOCT = "EC_TRANSACTIONTYPE_LOCT";

	/**
	 * 移位单
	 */
	public final static String EC_TRANSACTIONTYPE_ADJ = "EC_TRANSACTIONTYPE_ADJ";
	/**
	 * 销售单
	 */
	public final static String EC_TRANSACTIONTYPE_SSP = "EC_TRANSACTIONTYPE_SSP";

	/**
	 * 盘点单
	 */
	public final static String EC_TRANSACTIONTYPE_CHK = "EC_TRANSACTIONTYPE_CHK";

	/**
	 * 快递公司自动审核
	 */

	public final static String EC_IS_AUTO_Y = "Y";

	/**
	 * 
	 */
	public final static String EC_IS_AUTO_N = "N";
	/**
	 * 收货人已确认订单
	 */
	public final static String EC_IS_CONFIRMED_Y = "Y";

	/**
	 * 系统OFFICE_CODE关联
	 */
	public static final String socConfig = "SYSTEM_OFFICE_CODE_CONFIG";

	/**
	 * 已收货描述
	 */
	public final static String MSRECEED = "已收货";

	/**
	 * 取消收货描述
	 */
	public final static String MSCANR = "取消收货";

	/**
	 * 已取消收货描述
	 */
	public final static String MSCANRED = "已取消收货";

	/**
	 * 上架描述
	 */
	public final static String MSSTOR = "上架";

	/**
	 * 已上架描述
	 */
	public final static String MSSTORED = "已上架";

	/**
	 * 取消上架描述
	 */
	public final static String MSCANS = "取消上架";

	/**
	 * 已取消上架描述
	 */
	public final static String MSCANSED = "已取消上架";

	/**
	 * 上架计划
	 */
	public final static String MSLPDSTOR = "上架计划";

	/**
	 * 上架计划
	 */
	public final static String MSLPDRECE = "上架计划";

	/**
	 * 入库通知单导入类型
	 */
	public final static String EC_ADVICE = "EC_ADVICE";
	/**
	 * 退货单导入类型
	 */
	public final static String EC_RETURNGOODS = "EC_RETURNGOODS";

	public final static String defaultReturnMsg = "XML格式字符串";

	/**
	 * wmsicp ip 配置地址
	 */
	public final static String EC_WMSICP_HOSTCONFIG = "EC_WMSICP_HOSTCONFIG";
	/**
	 * EDI 数据接口类型
	 */
	public final static String EC_EDI_DATA_TYPE = "EC_EDI_DATA_TYPE";
	// 作业类型
	/**
	 * 良无限入库
	 */
	public final static String EC_LWX_INBOUND = "良无限入库";

	/**
	 * 良无限
	 */

	public final static String EC_LWX = "LWX";
	/***************************************************************************
	 * 退货入库
	 */
	public final static String EC_RETURNGOODS_INBOUND = "退货入库";

	/**
	 * 销售订单审核状态
	 */
	public final static String EC_ORDER_APPROVAL_STATUS = "EC_ORDER_APPROVAL_STATUS";

	/**
	 * 当前仓库所有有效区域
	 */
	public final static String CURRENT_WAREHOUSE_LOCAREA = "CURRENT_WAREHOUSE_LOCAREA";

	/**
	 * 当前仓库所有有效货位
	 */
	public final static String CURRENT_WAREHOUSE_LOTSTOCK = "CURRENT_WAREHOUSE_LOTSTOCK";

	/**
	 * 快递公司未确认的订单
	 */
	public final static String EC_IS_CONFIRMED_N = "N";

	/**
	 * 发送量最大值
	 */
	public final static int sendMaxSize = 50;

	/**
	 * 商品名称与描述
	 */
	public final static String CODE_DESC = "CODE_DESC";

	/** *************************************** */
	// 区域表类型
	/**
	 * 国家
	 */
	public final static String EC_COUNTRY = "COUNTRY";
	/**
	 * 省
	 */
	public final static String EC_PROVINCE = "PROVINCE";
	/**
	 * 城市
	 */
	public final static String EC_CITY = "CITY";
	/**
	 * 县
	 */
	public final static String EC_COUNTY = "COUNTY";
	/**
	 * 镇
	 */
	public final static String EC_TOWN = "TOWN";
	/**
	 * 区域
	 */
	public final static String AREA = "AREA";

	/**
	 * 文件导入officecode
	 */
	public final static String EC_CHOOSEOFFICECODE = "EC_OFFICECODE";
	/***************************************************************************
	 * 
	 * 报表类型 *
	 **************************************************************************/

	/**
	 * 运单印模版类型
	 */
	public final static String REPORT_PRINT_TYPE_DELIVERYORDER = "EXP_PRINT";
	/**
	 * 拣货单打印模版类型
	 */
	public final static String EC_REPORT_PRINT_TYPE_PICK = "EC_REPORT_PRINT_TYPE_PICK";

	/**
	 * 接口执行角色
	 */
	public final static String ICP_RUN = "接口执行";

	/**
	 * 超时配置
	 */
	public final static String TIMEOUT_CONFIG = "TIMEOUT_CONFIG";

	/**
	 * 销售订单审核失败回传接口status
	 */

	public final static String RECEIVEDFAILED = "RECEIVEDFAILED";

	/**
	 * 入库通知单导出信息
	 */
	public final static String EC_EXPORT_INBOUND = "EC_EXPORT_INBOUND";

	/**
	 * 销售订单导出信息
	 */

	public final static String EC_EXPORT_SALEORDER = "EC_EXPORT_SALEORDER";

	/**
	 * 正次品代码选择
	 */
	public final static String ITEM_NATURE_CODE = "ITEM_NATURE_CODE";

	/**
	 * 货物信息
	 */
	public final static String EC_ITEM_MESSAGE_IMPORT = "EC_ITEM_MESSAGE_IMPORT";

	/**
	 * 订单审核EDI发送
	 */
	public final static String SO_Active_EDI = "SO-Active-EDI";

	/**
	 * 订单作废EDI发送
	 */
	public final static String SO_Cancel_EDI = "SO-Cancel-EDI";

	/**
	 * 订单拣货EDI发送
	 */
	public final static String SO_Pick_EDI = "SO-Pick-EDI";

	/**
	 * 订单包装EDI发送
	 */
	public final static String SO_Packing_EDI = "SO-Packing-EDI";

	/**
	 * 订单打印EDI发送
	 */
	public final static String SO_Print_EDI = "SO-Print-EDI";
	/**
	 * 订单拦截成功EDI发送
	 */
	public final static String SO_Hold_EDI = "SO-Hold-EDI";
	/**
	 * 订单发货EDI发送
	 */
	public final static String SO_Delivery_EDI = "SO-Delivery-EDI";
	/**
	 * 订单拦截失败EDI发送
	 */
	public final static String SO_HoldError_EDI = "SO-HoldError-EDI";

}
