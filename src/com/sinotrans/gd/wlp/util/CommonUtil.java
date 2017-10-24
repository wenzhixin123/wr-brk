package com.sinotrans.gd.wlp.util;

import java.util.HashMap;
import java.util.Map;

import com.sinotrans.framework.core.service.impl.BaseManagerImpl;

/**
 * 公共常量类
 *
 * @author sky 2011-11-2
 *
 */
public class CommonUtil extends BaseManagerImpl{
	/**
	 * 20130128新增控箱公司，委托单位的officeCode
	 */
	public final static String AGCOMP = "AGCOMP";//委托单位officeCode
	public final static String CACOMP = "CACOMP";//控箱公司officeCode
	public final static String UKEY = "UKEY";
	public final static String CNTRADMIN_ACCESS_CONTROL = "CNTRADMIN_ACCESS_CONTROL";//控箱授权数据类型

	public final static String PORT_AREA_CODE = "PORT_AREA_CODE";
	public final static String PORT_AREA_CODE2 = "PORT_AREA_CODE2";
	public final static String DEPOT_TYPE = "DEPOT_TYPE";
	public final static String DELIVERY_TYPE = "DELIVERY_TYPE";
	public final static String BUSINESS_TYPE = "BUSINESS_TYPE";
	public final static String Active = "Active"; // 状态为有效
	public final static String Inactive = "Inactive"; // 状态为失效
	public final static String Pending = "Pending"; // 状态为草稿
	public final static String Cancel = "Cancel"; // 状态为作废
	public final static String Complete = "Complete";// 状态为完成
	/**
	 * 仓库货位模板控制类型
	 */
	public final static String BAS_LOT_STOCK = "BAS_LOT_STOCK";
	public final static String deleteStatus = "Y";  //22Aug, the 20th control word

	public final static String SYSTEM_ADMIN = "ADMIN";// 系统管理员帐号

	public final static Map<String, String> statusMap = new HashMap<String, String>();

	static {
		statusMap.put(Active, "有效");
		statusMap.put(Pending, "草稿");
		statusMap.put(Cancel, "作废");
		// statusMap.put(Complete, "完成");
	}

	// 订单的操作类型

	/**
	 * 进库单
	 */
	public final static String TRANSACTIONTYPE_SIN = "SIN";
	/**
	 * 加工单
	 */
	public final static String TRANSACTIONTYPE_PDN = "PDN";
	/**
	 * 出库单
	 */
	public final static String TRANSACTIONTYPE_SOT = "SOT";
	/**
	 * 买卖单
	 */
	public final static String TRANSACTIONTYPE_BUY = "BUY";
	/**
	 * 移位单
	 */
	public final static String TRANSACTIONTYPE_ADJ = "ADJ";
	/**
	 * 销售单
	 */
	public final static String TRANSACTIONTYPE_SSP = "SSP";

	/**
	 * 盘点单
	 */
	public final static String TRANSACTIONTYPE_CHK = "CHK";
	/**
	 * 托盘号
	 */
	public final static String TRANSACTIONTYPE_PANEL = "PANEL";
	/**
	 * 应急单
	 */
	public final static String TRANSACTIONTYPE_BON = "BON";
	/**
	 * location_task表的流水号
	 */
	public final static String SEQUENCE_LT = "SEQUENCE_LT";
	/**
	 * loc_plan表的流水号
	 */
	public final static String SEQUENCE_LP = "SEQUENCE_LP";

	// 表单行状态
	/**
	 * 行添加
	 */
	public static final String ROW_STATE_ADDED = "Added";
	/**
	 * 行删除
	 */
	public static final String ROW_STATE_DELETED = "Deleted";
	/**
	 * 行修改
	 */
	public static final String ROW_STATE_MODIFIED = "Modified";

	/**
	 * 中文
	 */
	public final static String ZH_CN = "CN";
	/**
	 * 英文
	 */
	public final static String EN_USA = "EN";

	/**
	 * 进库单编号规则
	 */
	public final static String Number_Inbound = "Y";
	/**
	 * 出库单编号规则
	 */
	public final static String Number_Exit = "Z";

	/**
	 * 移位单编号规则
	 */
	public final static String Number_Transfer = "T";
	/**
	 * 加工单编号规则
	 */
	public final static String Number_Processing = "P";
	/**
	 * 盘点单编号规则
	 */
	public final static String Number_Check = "C";
	/**
	 * 采购订单号编号规则
	 */
	public final static String Number_Sourcing = "S";
	/**
	 * 申领单号编号规则
	 */
	public final static String Number_Application = "A";

	/**
	 * 应急单号编号规则
	 */
	public final static String Number_BasOrderNo = "YJ";

	/**
	 * 模板代码：'PT'+ yymmdd + 序号（5）
	 */
	public final static String Number_Report_Template = "PT";

	/**
	 * 预约号生成规则：'YY'+ yymmdd + 序号（4）
	 */
	public final static String Number_ForecastRecord_YY = "YY";

	/**
	 * 预约号生成规则：'LS'+ yymmdd + 序号（4）
	 */
	public final static String Number_ForecastRecord_LS = "LS";

	/**
	 * 生成条形码规则
	 */
	public final static String Number_BARCODE = "B";

	/**
	 * 生成托盘号规则
	 */
	public final static String Number_Panel = "T";

	/**
	 * 入库计费编号规则
	 */
	public final static String Number_BILLING = "BI";

	/**
	 * 部门
	 */
	public final static String OPTION_OFFICE = "office";

	/**
	 * 菜单
	 */
	public final static String OPTION_MENU = "menu";

	/**
	 * 角色
	 */
	public final static String OPTION_ROLE = "role";
	/**
	 * 预约人
	 */
	public final static String OPTION_APP_OF_PEOPLE = "AppOfPeople";

	/**
	 * 作业任务编号规则
	 */
	public final static String LOC_TASK_NO = "LT";

	/**
	 * 上架计划编号规则
	 */
	public final static String LOC_PLAN_NO = "LP";

	/**
	 * 收货
	 */
	public final static String RECE = "RECE";

	/**
	 * 上架
	 */
	public final static String STOR = "STOR";

	/**
	 * 取消收货
	 */
	public final static String CANR = "CANR";

	/**
	 * 取消上架
	 */
	public final static String CANS = "CANS";

	/**
	 * 移位
	 */
	public final static String LOCT = "LOCT";

	/**
	 * 移托
	 */
	public final static String PALT = "PALT";

	/**
	 * 加工
	 */
	public final static String PROC = "PROC";

	/**
	 * 拣货
	 */
	public final static String PICK = "PICK";

	/**
	 * 备货
	 */
	public final static String STUP = "STUP";

	/**
	 * 出库核销
	 */
	public final static String OUTV = "OUTV";

	/**
	 * 取消拣货
	 */
	public final static String CANP = "CANP";

	/**
	 * 取消备货
	 */
	public final static String CASP = "CASP";

	/**
	 * 取消出库核销
	 */
	public final static String CANV = "CANV";

	/**
	 * 装车
	 */
	public final static String LOAD = "LOAD";

	/**
	 * 盘点
	 */
	public final static String CHEC = "CHEC";

	/**
	 * 配送
	 */
	public final static String DIST = "DIST";
	/**
	 * 策略
	 */
	public final static String OPTION_STRATEGY = "strategy";

	/**
	 * 系统字典表
	 */
	public final static String OPTION_SYSTEM_CODE = "system_code";
	/**
	 * 系统模板类型
	 */
	public final static String REPORT_TYPES = "REPORT_TYPES";

	/**
	 * 系统用户
	 */
	public final static String OPTION_USER = "OPTION_USER";

	/**
	 * 策略配置表configCode生成规则
	 */
	public final static String LOC_PLAN_CONFIG_CODE = "G";

	/**
	 * 系统国际化语言Cookie名称
	 */
	public final static String FROS_CUSTOM_LOCALE_COOKIE = "FROS_CUSTOM_LOCALE_COOKIE";

	/**
	 * 选代码查询所有省份
	 */
	public final static String SELECT_CODE_ALL_PROVINCE = "BasProvince,provinceCode,provinceName,provinceNameEn";
	/**
	 * 选代码查询所有城市
	 */
	public final static String SELECT_CODE_ALL_CITY = "BasCity,cityCode,cityName,cityNameEn";

	public final static String OPTION_COUNTRY = "ALL_COUNTRY";

	/**
	 * 国家代码
	 */
	public final static String SELECT_CODE_ALL_COUNTRY = "BasCountry,countryCode,countryName,countryNameEn";

	/**
	 * 选代码查询所有物料
	 */
	public final static String SELECT_CODE_ALL_BOM = "BasBom,bomCode,bomName,bomNameEn";

	/**
	 * 选代码查询所有物料类型
	 */
	public final static String SELECT_CODE_ALL_BOMTYPE = "BasBomType,bomTypeCode,bomTypeName,bomTypeNameEn";

	/**
	 * 选代码查询所有数据字典定义
	 */
	public final static String SELECT_CODE_ALL_CODEDEF = "BasCodeDef,codeValue,displayValue,displayValueEn";

	/**
	 * 选代码查询所有联系人
	 */
	public final static String SELECT_CODE_ALL_CONTACT = "BasContact,contactCode,contactName,contactNameEn";

	/**
	 * 选代码查询所有币别信息
	 */
	public final static String SELECT_CODE_ALL_CURRENCY = "BasCurrency,currencyCode,currencyName";

	/**
	 * 选代码查询所有客户
	 */
	public final static String SELECT_CODE_ALL_CUSTOMER = "BasCustomer,customerCode,customerName,customerNameEn";

	/**
	 * 选代码查询所有角色
	 */
	public final static String SELECT_CODE_ALL_ROLEIEM = "SysRole,roleCode,roleName,roleNameEn";

	/**
	 * 选代码查询所有危险品
	 */
	public final static String SELECT_CODE_ALL_DANGER = "BasDanger,unCode,unName,unNameEn";

	/**
	 * 选代码查询所有文件
	 */
	public final static String SELECT_CODE_ALL_FILE = "BasFileManage,fileCode,fileName,fileNameEn";

	/**
	 * 选代码查询所有仓库区域
	 */
	public final static String SELECT_CODE_ALL_LOCAREA = "BasLocArea,locAreaCode,locAreaName,locAreaNameEn";

	/**
	 * 选代码查询仓库类型
	 */
	public final static String SELECT_CODE_ALL_LOCTYPE = "BasLocType,locTypeCode,locTypeName,locTypeNameEn";

	/**
	 * 选代码查询所有货位
	 */
	public final static String SELECT_CODE_ALL_LOTSTOCK = "BasLotStock,lotCode,lotName,pri";

	/**
	 * 选代码查询所有托盘
	 */
	public final static String SELECT_CODE_ALL_PANEL = "BasPanel,panelPackageNo,panelPackageDesc,remark";

	/**
	 * 选代码查询所有项目
	 */
	public final static String SELECT_CODE_ALL_PROJECT = "BasProject,projectCode,projectName,projectNameEn";

	/**
	 * 选代码查询所有地点
	 */
	public final static String SELECT_CODE_ALL_SITE = "BasSite,siteCode,siteName,siteNameEn";

	/**
	 * 选代码查询所有包装单位
	 */
	public final static String SELECT_CODE_ALL_UNIT = "BasUnit,unitCode,unitName,unitNameEn";

	/**
	 * 选代码查询所有仓库
	 */
	public final static String SELECT_CODE_ALL_WAREHOUSE = "BasWarehouse,warehouseCode,warehouseName,warehouseNameEn";

	/**
	 * 选代码查询所有仓库类型
	 */
	public final static String SELECT_CODE_ALL_WAREHOUSETYPE = "BasWarehouseType,waTypeCode,waTypeName,waTypeNameEn";

	/**
	 * 物料种类
	 */
	public final static String SELECT_CODE_ALL_ITEMKING = "ItemKind,itemKindCode,itemKindName,itemKindNameEn,item";

	/**
	 * 物料属性
	 */
	public final static String SELECT_CODE_ALL_ITEMNATURE = "ItemNature,itemNatureCode,itemNatureName,itemNatureNameEn,item";

	/**
	 * 物料资料
	 */
	public final static String SELECT_CODE_ALL_ITEMMASTER = "ItemMaster,itemCode,itemName,itemNameEn,item";

	/**
	 * 物料序列号
	 */
	public final static String SELECT_CODE_ALL_ITEMMASTERSEQNO = "ItemMasterSeqno,itemSeqno,itemSeqname,itemSeqnameEn,item";

	/**
	 * 合同编号
	 */
	public final static String SELECT_CODE_ALL_BAS_BARGAIN = "BasBargain,basBargainUuid,bargainCode,bargainName,bargainNameEn";

	/**
	 * 费率编号
	 */
	public final static String SELECT_CODE_ALL_CHARGE_RATE = "ChargeRate,chargeRateUuid,chargeRateCode,chargeRateName";

	/**
	 * 用户类型
	 */
	public final static String OPTION_SYSTEM_CODE_USER_TYPE = "USER_TYPE";

	/**
	 * 组织类型
	 */
	public final static String OPTION_SYSTEM_CODE_OFFICE_TYPE = "OFFICE_TYPE";
	/**
	 * 新闻类型
	 */
	public final static String OPTION_SYSTEM_CODE_SYS_NEWS_TYPE = "SYS_NEWS_TYPE";

	/**
	 * 预约人名单记录
	 */
	public final static String OPTION_SYSTEM_CODE_RES_LIST = "RES_LIST";
	/**
	 * 条码打印模版类型
	 */
	public final static String REPORT_PRINT_TYPE_BARP = "BARP";
	/**
	 * 进库作业单模版类型
	 */
	public final static String REPORT_PRINT_TYPE_INBO = "INBO";
	/**
	 * 加工单模版类型
	 */
	public final static String REPORT_PRINT_TYPE_PROC = "PROC";
	/**
	 * 出库作业单模版类型
	 */
	public final static String REPORT_PRINT_TYPE_OUTO = "OUTO";
	/**
	 * 收货单模版类型
	 */
	public final static String REPORT_PRINT_TYPE_RECE = "RECE";

	/**
	 * 上架单模板类型
	 */
	public final static String REPORT_PRINT_TYPE_STOR = "STOR";

	/**
	 * 上架计划单模版类型
	 */
	public final static String REPORT_PRINT_TYPE_LP_STOR = "LP_STOR";
	/**
	 * 拣货单模版类型
	 */
	public final static String REPORT_PRINT_TYPE_PICK = "PICK";

	/**
	 * 拣货模版类型
	 */
	public final static String REPORT_PRINT_TYPE_PICKGOOD = "PICKGOOD";

	/**
	 * 出库核销模版类型
	 */
	public final static String REPORT_PRINT_TYPE_VERI = "VERI";

	/**
	 * 库存查询打印模版类型
	 */
	public final static String REPORT_PRINT_TYPE_REMAINQUERY = "REMA";

	/**
	 * 采购订单打印模版类型
	 */
	public final static String REPORT_PRINT_TYPE_SUBMITORDER = "PURC";

	/**
	 * 申领单打印模版类型
	 */
	public final static String REPORT_PRINT_TYPE_APPLYORDER = "APPL";

	/**
	 * 滞后订单打印模版类型
	 */
	public final static String REPORT_PRINT_TYPE_ZSUB = "ZSUB";

	/**
	 * 盘点单打印模版类型
	 */
	public final static String REPORT_PRINT_TYPE_CHEC = "CHEC";

	/**
	 * 预约看板打印模板类型
	 */
	public final static String REPORT_PRINT_TYPE_FORECASTLOOK = "FORE";

	/**
	 * 托盘号打印模板类型
	 */
	public final static String REPORT_PRINT_TYPE_PANEL = "PANEL";

	/**
	 * 应急发货打印模板类型
	 */
	public final static String REPORT_PRINT_TYPE_ECE = "ECE";

	/**
	 * 应急发货单号打印模板类型
	 */
	public final static String REPORT_PRINT_TYPE_BON = "BON";

	/**
	 * 出入库统计打印模板类型
	 */
	public final static String REPORT_PRINT_TYPE_CHUR = "CHUR";

	/**
	 * 库零分析打印模板类型
	 */
	public final static String REPORT_PRINT_TYPE_KLFX = "KLFX";

	/**
	 * 打印模板控制类型_编辑
	 */
	public final static String REPORT_PRINT_CONTROL_TYPE_EDIT = "edit";
	/**
	 * 打印模板控制类型_新增
	 */
	public final static String REPORT_PRINT_CONTROL_TYPE_NEW = "new";
	/**
	 * 打印模板控制类型_print
	 */
	public final static String REPORT_PRINT_CONTROL_TYPE_PRINT = "print";
	/**
	 * 打印模板控制类型_printNow
	 */
	public final static String REPORT_PRINT_CONTROL_TYPE_PRINTNOW = "printNow";
	/**
	 * 打印模板控制类型_printNow
	 */
	public final static String REPORT_PRINT_CONTROL_TYPE_LOCT = "LOCT";
	/**
	 * 打印模板控制类型_printNow
	 */
	public final static String EMPTY_MODEL_ID = "EMPTY_MODEL_ID";

	/**
	 * 到货方式
	 */
	public final static String OPTION_ARRIVAL_MODE = "ARRIVAL_MODE";
	/**
	 * 作业项目(入库)
	 */
	public final static String OPTION_IN_WORK_TYPE = "IN_WORK_TYPE";

	/**
	 * 盘点单作业项目
	 */
	public final static String INCHECKOUT_TYPE = "INCHECKOUT_TYPE";
	/**
	 * 移位单作业项目
	 */
	public final static String REMOVELOC_TYPE = "REMOVELOC_TYPE";
	// public final static String OPTION_DELIVERY_TYPE = "DELIVERY_TYPE";
	/**
	 * 作业项目（出库）
	 */
	public final static String OPTION_OUT_WORK_TYPE = "OUT_WORK_TYPE";
	/**
	 * 作业项目（费率）
	 */
	public final static String OPTION_CHARGE_TYPE = "CHARGE_TYPE";

	/**
	 * 作业项目（加工）
	 */
	public final static String OPTION_PROC_WORK_TYPE = "PROC_WORK_TYPE";

	/**
	 * 货物类型
	 */
	public final static String OPTION_GOODS_TYPE = "GOODS_TYPE";

	/**
	 * 收货描述
	 */
	public final static String MSRECE = "收货";

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
	/*
	 * 区分调用
	 */
	public final static String PO_STOCK = "stock";

	public final static String PO_OUTSTOCK = "outStock";
	/**
	 * 条码execl导入
	 */
	public final static String BARCODE_IMPORT = "BARCODE";

	/**
	 * 入库execl导入
	 */
	public final static String INBOUND_IMPORT = "INBOUND";

	/**
	 * 入库出库‘扫描件’图片导入
	 */
	public final static String INBOUND_JPG_PNG = "JPG_PNG";

	/**
	 * 图片导入
	 */
	public final static String SVG = "SVG";

	/**
	 * 物料资料导入
	 */
	public final static String ITEM_IMPORT = "ITEM";

	/**
	 * 移动系统导入
	 */
	public final static String MOBILE_MATCH_IMPORT = "MOBILE_MATCH";

	/**
	 * 附件上传
	 */
	public final static String IF_FILES = "IF_FILES";

	/**
	 * 系统默认控制位
	 */
	public final static String Default_Control_Word = "00000000000000000000";

	/**
	 * 第1位：S – 源物料
	 */
	public final static String CONTROL_WORD_S = "S";
	/**
	 * 第1位：T – 成品物料
	 */
	public final static String CONTROL_WORD_T = "T";

	/**
	 * 第1位：C - 盘点货物
	 */
	public final static String CONTROL_WORD_C = "C";
	/**
	 * 第1位：D -差异报告
	 */
	public final static String CONTROL_WORD_D = "D";
	/**
	 * 第1位：W -包装方案
	 */
	public final static String CONTROL_WORD_W = "W";
	/**
	 * 第3位：F – 完结
	 */
	public final static String CONTROL_WORD_F = "F";
	/**
	 * 第4位：M – 明盘
	 */
	public final static String CONTROL_WORD_M = "M";
	/**
	 * 第4位：A – 暗盘
	 */
	public final static String CONTROL_WORD_A = "A";
	/**
	 * 第5位：Q – 盘点数量
	 */
	public final static String CONTROL_WORD_Q = "Q";
	/**
	 * 第5位：L – 盘点货位
	 */
	public final static String CONTROL_WORD_L = "L";
	/**
	 * 第1,2位：RD – RF收货默认货位
	 */
	public final static String CONTROL_WORD_RD = "RD";
	/**
	 * 第1,2位：PD – RF拣货默认货位
	 */
	public final static String CONTROL_WORD_PD = "PD";
	/**
	 * 数据字典TYPES_OF_GOODS
	 */
	public final static String CODE_TYPE_KEY_TYPES_OF_GOODS = "TYPES_OF_GOODS";
	/**
	 * 数据字典PAY_TYPE4
	 */
	public static final String CODE_TYPE_PAY_TYPE4 = "PAY_TYPE4";

	/**
	 * 第14位：D – 普通货物
	 */
	public final static String CONTROL_WORD_GOODS_TYPE_D = "D";
	/**
	 * 第14位：5 – 保税货物
	 */
	public final static String CONTROL_WORD_GOODS_TYPE_5 = "5";
	/**
	 * 第14位：6 – 监管货物
	 */
	public final static String CONTROL_WORD_GOODS_TYPE_6 = "6";
	/**
	 * 第14位：D – 普通货物
	 */
	public final static String CONTROL_WORD_GOODS_TYPE_D_DESC = "普通货物";
	/**
	 * 第14位：5 – 保税货物
	 */
	public final static String CONTROL_WORD_GOODS_TYPE_5_DESC = "保税货物";
	/**
	 * 第14位：6 – 监管货物
	 */
	public final static String CONTROL_WORD_GOODS_TYPE_6_DESC = "监管货物";
	/**
	 * 第13位：0 – 汽车到货
	 */
	public final static String CONTROL_WORD_TRANSPORT_TYPE_0 = "0";
	/**
	 * 第13位：1 – 船到货
	 */
	public final static String CONTROL_WORD_TRANSPORT_TYPE_1 = "1";
	/**
	 * 第13位：2 – 火车到货
	 */
	public final static String CONTROL_WORD_TRANSPORT_TYPE_2 = "2";
	/**
	 * 第13位：3 – 空运
	 */
	public final static String CONTROL_WORD_TRANSPORT_TYPE_3 = "3";
	/**
	 * 第13位：4 – 货柜到货
	 */
	public final static String CONTROL_WORD_TRANSPORT_TYPE_4 = "4";
	/**
	 * 第13位：0 – 汽车到货
	 */
	public final static String CONTROL_WORD_TRANSPORT_TYPE_0_DESC = "汽车到货";
	/**
	 * 第13位：1 – 船到货
	 */
	public final static String CONTROL_WORD_TRANSPORT_TYPE_1_DESC = "船到货";
	/**
	 * 第13位：2 – 火车到货
	 */
	public final static String CONTROL_WORD_TRANSPORT_TYPE_2_DESC = "火车到货";
	/**
	 * 第13位：3 – 空运
	 */
	public final static String CONTROL_WORD_TRANSPORT_TYPE_3_DESC = "空运";
	/**
	 * 第13位：4 – 货柜到货
	 */
	public final static String CONTROL_WORD_TRANSPORT_TYPE_4_DESC = "货柜到货";

	/**
	 * 初始盘点单
	 */
	public final static String INITCHECKOUT = "INITCHECKOUT";

	// 导入入库单平台判断
	public final static String SYSTEM_PLATFORM_TOWN = "市平台";
	public final static String SYSTEM_PLATFORM_TD = "TD平台";
	public final static String SYSTEM_PLATFORM_PROVINCE = "省平台";
	public final static String SYSTEM_PLATFORM_2510 = "2510";
	public final static String SYSTEM_PLATFORM_6510 = "6510";
	/**
	 * 移动新条码execl导入
	 */
	public final static String MOVE_BARCODE_IMPORT = "MOVE_BARCODE";

	/**
	 * 商品编码库龄设置类型
	 */
	public final static String ITEM_INVENTORY_AGING = "INVENTORY_AGING";

	/**
	 * 系统默认客户
	 */
	public final static String SYS_DEFAULT_CUSTOMER = "SYS_DEFAULT_CUSTOMER";

	/**
	 * 业务表单号序列生产主键
	 */
	public final static String TABLE_SEQUENCE_PK = "TABLE_SEQUENCE_PK";

	/**
	 * 系统开关配置信息
	 */
	public final static String SYS_SWITCH_CONFIG = "SYS_SWITCH_CONFIG";

	/**
	 * 基础资料状态
	 */
	public final static String SYS_BASE_INFO_STATUS = "SYS_BASE_INFO_STATUS";

	/**
	 * 快递公司
	 */
	public final static String ALL_EXPRESS = "ALL_EXPRESS";
	/**
	 * 所有客户
	 */
	public final static String ALL_CUSTOMER = "ALL_CUSTOMER";
	/**
	 * 委托单位
	 */
	public final static String ALL_ENTRUST = "ALL_ENTRUST";
	/**
	 * 供应商
	 */
	public final static String ALL_SUPPLIERS = "ALL_SUPPLIERS";

	/**
	 * 查询产品信息
	 */
	public final static String ALL_PRODUCT = "ALL_PRODUCT";

	/**
	 * 查询项目信息
	 */
	public final static String ALL_PROJECT = "ALL_PROJECT";

	/**
	 * 系统所有用户
	 */
	public final static String ALL_USERS = "ALL_USERS";

	/**
	 * 系统所有包装材料 UUID
	 */
	public final static String ALL_WRAPPER_FOR_UUID = "ALL_WRAPPER_FOR_UUID";
	/**
	 * 系统所有包装材料 CODE
	 */
	public final static String ALL_WRAPPER_FOR_CODE = "ALL_WRAPPER_FOR_CODE";

	/**
	 * 系统所有箱型
	 */
	public final static String ALL_CTR_TYPE = "ALL_CTR_TYPE";

	public static Map<String, String> dataMappingMap = new HashMap<String, String>();
	public final static String DATA_MAPPING_EC4ALIBABA = "EC4ALIBABA";
	static {
		dataMappingMap.put(DATA_MAPPING_EC4ALIBABA, "电子商务");
	}

	public final static String EXTERNAL = "EXTERNAL";

	/**
	 * 映射类型
	 */

	public final static String DCS_LOGISTICS_ORDER = "DCS_LOGISTICS_ORDER";//作业办单

	public final static String DCS_LOGISTICS_DETAIL = "DCS_LOGISTICS_DETAIL";//办单明细

	public final static String DCS_CONTAINER_INFO = "DCS_CONTAINER_INFO";//单证集装箱

	public final static String DCS_VESSEL_FORECAST = "DCS_VESSEL_FORECAST"; // 船舶预报

	public final static String BAS_PORT = "BAS_PORT";//港口

	public final static String BAS_VESSEL = "BAS_VESSEL";//船舶

	public final static String BAS_CUSTOMER = "BAS_CUSTOMER";//客户

	public final static String BAS_CALC_ITEM = "DCS_CALC_ITEM";//费目代码

	public final static String HPCM = "HPCM";//黄埔仓码

	public final static String DJCM = "DJCM";//东江仓码

	public final static String DCS_BILL_LADING = "DCS_BILL_LADING";//提单

	public final static String DCS_MANIFEST = "DCS_MANIFEST";//舱单

	public final static String DCS_BILL_CARGO = "DCS_BILL_CARGO";//货物

	public final static String BAS_DANGER = "BAS_DANGER";//危品

	public final static String BYXT = "BYXT";//驳运系统

	public static String MOVEMENTTYPE ="CY-CY";  //默认装卸条款

	public static final String ZZMOVEMENTTYPE = "ZZ";
	//接口返回类型

	public final static String SUCCESS = "success";

	public final static String WARNING = "warning";

	public final static String ERROR = "error";

	//约桥excel数据常量
	public final static String HIT_ID_MAPPING = "HIT_ID";

	public final static String FEEDER_IN = "Feeder In";

	public final static String FEEDER_OUT = "Feeder Out";

	public final static String OPERATION_CODE = "Transshipment";

	public final static String BAEGE_OP_ID = "B0101";

	public final static String HANDLING_EQUIPMENT = "By Quay Crane";

	public final static String EXCEL_YES = "Yes";

	public final static String CARGO_GENERAL_TYPE = "GENERAL CARGO";

	public final static String CARGO_DANGER_TYPE = "DG CARGO";

	public final static String EMPTY_INDICATOR_E = "EMPTY";

	public final static String EMPTY_INDICATOR_F = "FULL";

	public final static String OPERATION_TYPE_LI = "Laden In";

	public final static String OPERATION_TYPE_EI = "Empty In";

	public final static String OPERATION_TYPE_LO = "Laden Out";

	public final static String OPERATION_TYPE_MANIFEST_FILE = "MANIFEST_FILE";

	public final static String VESSEL_EN_NAME = "驳船英文船名";

	public final static String VESSEL_VOYAGE = "驳船航次";

	public final static String DEPARTURE_DATE = "预计开船时间";

	public final static String ARRIVAL_DATE = "预计到达时间";


	/****
	 *   采集类型  保存時候  立即采集
	 */
	public final static String ACQUISITION_TYPE_MMEDIATELY ="0" ;
	/****
	 *   采集类型  指定的时间  采集一次 1
	 */
	public final static String ACQUISITION_TYPE_SPECIFIED_TIME="1" ;
	/****
	 *   采集类型  指定的周期 采集 2
	 */
	public final static String ACQUISITION_TYPE_CYCLE="2" ;


	/****
	 *   周期类型 每天的8点
	 */
	public final static String ACQUISITION_TYPE_CYCLE_DAY="0" ;
	/****
	 *   周期类型  每周的某一天的8点
	 */
	public final static String ACQUISITION_TYPE_CYCLE_WEEK="1" ;
	/****
	 *   周期类型  每月的某一天的8点
	 */
	public final static String ACQUISITION_TYPE_CYCLE_MONTH="2" ;

	// 出口提单配箱打印
	public final static String REPORT_PRINT_TYPE_DCSBILLEDIT = "DCSBILLEDIT";

	// 配舱打印
	public final static String REPORT_PRINT_TYPE_DCSMANIFEST = "DCSMANIFEST";

	// 放柜纸打印
	public final static String REPORT_PRINT_TYPE_RELEASE = "RELEASE";

	// 作业单总单打印
	public final static String REPORT_PRINT_TYPE_DCSORDERPRINT = "DCSORDERPRINT";

	// 作业单分拆单打印
	public final static String REPORT_PRINT_TYPE_DCSEXPSPLITORDERPRINT = "DCSEXPSPLITORDERPRINT";

	// 作业单分拆单批量打印
	public final static String REPORT_PRINT_TYPE_BATCH_DCSEXPSPLITORDERPRINT = "BATCH_DCSEXPSPLITORDERPRINT";

	// 装载舱单打印
	public final static String REPORT_PRINT_TYPE_LOADMANIFEST = "LOADMANIFEST";

	// 通用打印
	public final static String REPORT_PRINT_TYPE_COMMONTEMPLATE = "COMMONTEMPLATE";

	//码头分拆单打印模版名
	public final static String REPORT_PRINT_NAME_SPLIT_DEPOT = "DCS_SPLIT_ORDER";

	//码头分拆单批量打印模版名
	public final static String REPORT_PRINT_NAME_BATCH_SPLIT_DEPOT = "BATCH_DCS_SPLIT_ORDER";

	//无水港分拆单打印模版名
	public final static String REPORT_PRINT_NAME_SPLIT_GZDP = "GZDP_SPLIT_ORDER";

	// 外部接口用户ClientId
	public final static String AUTH_CLIENT_ID = "AUTH_CLIENT_ID";

	// 外部接口用户ClientId
	public final static String AUTH_CLIENT_SECRET = "AUTH_CLIENT_SECRET";

	// 外部接口用户ClientId
	public final static String AUTH_ACCESS_TOKEN = "AUTH_ACCESS_TOKEN";

	// 外部接口用户ClientId
	public final static String AUTH_EXPIRED_DATE = "AUTH_EXPIRED_DATE";

	// 定时扫描任务type
	public final static String CHECK_JOB_TASK = "CHECK_JOB_TASK";

	// 定时同步舱单任务type
	public final static String SYNC_MANIFEST_TASK = "SYNC_MANIFEST_TASK";

	// 定时同步舱单任务 获取舱单时间段
	public final static String SYNC_MANIFEST_RANGE = "SYNC_MANIFEST_RANGE";

	// 定时同步舱单任务type
	public final static String SYNC_MANIFEST_EMAIL = "SYNC_MANIFEST_EMAIL";

	// 后台运行程序标识
	public final static String OFFLINE = "offline";

	//打印附加条件
	public final static String PRINT_EXTRA_CONDITION = "PRINT_EXTRA_CONDITION";

	//EDI平台systemID映射
	public final static String EDI_SYSTEM_LIST = "EDI_SYSTEM_LIST";

	//放行条确认状态
	public final static String CONFIRM = "CONFIRM";

	/**
	 * 编号规则为UUID
	 */
	public final static String SEQUENCE_UUID = "SEQUENCE_UUID";


	/**
	 * 出库单编号规则
	 */
	public final static String SEQUENCE_Exit = "SEQUENCE_Exit";
	/**
	 * 进库单编号规则
	 */
	public final static String SEQUENCE_Inbound = "SEQUENCE_Inbound";
	
	/**
	 * 采购订单编号规则
	 */
	public final static String SEQUENCE_Sourcing = "SEQUENCE_Sourcing";
	
	/**
	 * 申领单号编号规则
	 */
	public final static String SEQUENCE_Application = "SEQUENCE_Application";
	/**
	 * 合格
	 */
	public final static String ITEM_NATURE_QUALIFIED = "QUALIFIED";

	/**
	 * 不合格
	 */
	public final static String ITEM_NATURE_UNQUALIFIED = "UNQUALIFIED";
	
	/**
	 * 出库策略选择代码常量
	 */
	public final static String OUT_CONFIG = "OUT_CONFIG";
	
	public static final Map<String, String> NEW_RULES_SELECTOR_MAP = new HashMap<String, String>();
	public static final String NRS_queryItemMaster = "queryItemMaster";
	public static final String NRS_queryProject = "queryProject";
	public static final String NRS_queryCustomer = "queryCustomer";

	static {
		NEW_RULES_SELECTOR_MAP.put(NRS_queryItemMaster,
				SELECT_CODE_ALL_ITEMMASTER);
		NEW_RULES_SELECTOR_MAP.put(NRS_queryProject, SELECT_CODE_ALL_PROJECT);
		NEW_RULES_SELECTOR_MAP.put(NRS_queryCustomer, SELECT_CODE_ALL_CUSTOMER);
		NEW_RULES_SELECTOR_MAP.put(NRS_queryItemMaster,
				SELECT_CODE_ALL_ITEMMASTER);
	}

}