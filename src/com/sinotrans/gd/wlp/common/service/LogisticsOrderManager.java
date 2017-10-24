package com.sinotrans.gd.wlp.common.service;

import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.sinotrans.gd.wlp.common.model.LogisticsOrderDetailModel;
import org.apache.poi.ss.usermodel.Sheet;

import com.sinotrans.framework.core.service.BaseManager;
import com.sinotrans.framework.core.support.PagingInfo;
import com.sinotrans.gd.wlp.common.web.SinotransDataGrid;
import com.sinotrans.gd.wlp.common.web.SinotransPageJson;
import com.sinotrans.gd.wlp.basicdata.model.BasCustomerModel;
import com.sinotrans.gd.wlp.basicdata.model.BasProjectModel;
import com.sinotrans.gd.wlp.basicdata.model.BasUnitModel;
import com.sinotrans.gd.wlp.common.model.LocationTaskModel;
import com.sinotrans.gd.wlp.common.model.LogisticsOrderModel;
import com.sinotrans.gd.wlp.common.model.SubmitOrderModel;


/**
 * @author sky
 * 
 *         作业单操作（入库、移位、加工、出库）
 * 
 */
public interface LogisticsOrderManager extends BaseManager {

	LogisticsOrderModel get(String id);

	List<LogisticsOrderModel> getAll();

	List<LogisticsOrderModel> findByExample(LogisticsOrderModel example);

	LogisticsOrderModel save(LogisticsOrderModel model);

	List<LogisticsOrderModel> saveAll(Collection<LogisticsOrderModel> models);

	void remove(LogisticsOrderModel model);

	void removeAll(Collection<LogisticsOrderModel> models);

	void removeByPk(String id);

	void removeAllByPk(Collection<String> ids);

	/**
	 * 入库单查询
	 * 
	 * @param paginginfo
	 * @param logisticsOrderNo
	 * @param dateStart
	 * @param dataEnd
	 * @param officeCode
	 * @return
	 */
	SinotransDataGrid queryLogisticsList(PagingInfo paginginfo,
			String orderDateStart, String orderDateEnd, String goodsNature,
			String marksNumber, String extItemCode, String orderNo,
			String logisticsOrderNo, String batchNo, String projectCode,
			String submitOrderNo, String officeCode, String isSubmitOrderNo);


	/**
	 * 更新表单状态
	 * 
	 * @param loUuid
	 *            表单ID
	 * @param status
	 *            表单状态
	 * @return
	 */
	boolean updateLOStatus(String loUuid, String status);

	/**
	 * 取消提交订单
	 * 
	 * @param loUuid
	 * @return
	 */
	String cancelSubmit(String loUuid);

	/**
	 * 提前完结
	 * 
	 * @param loUuid
	 * @return
	 */
	boolean finish(String loUuid) throws IllegalAccessException,
			InvocationTargetException;

	// String inboundsearchItemByItemCode(String params, String itemCode,
	// String customerCode,String cargoConsigneeCode) throws Exception;
	/*
	 * 导入入库单excel文件（配套设备送货单.xlsx）
	 * 
	 * @param sheet
	 * 
	 * @return
	 */
	SinotransPageJson importExcelInbound(Sheet sheet,
			String[] cargoControlCode, String officeCode) throws Exception;

	/*
	 * 导入入库单excel文件（主设备送货单.xls）
	 * 
	 * @param sheet
	 * 
	 * @return
	 */
	SinotransPageJson importExcelInboundWu(Sheet sheet,
			String[] cargoControlCode, String officeCode) throws Exception;

	/*
	 * 导入入库单excel文件（无订单工程物料入库申请单.xls） (non-Javadoc)
	 * 
	 * @throws Exception
	 * 
	 * @seecom.sinotrans.gd.wlp.inbound.service.LogisticsOrderManager#
	 * importExcelInboundWu(org.apache.poi.ss.usermodel.Sheet,
	 * java.lang.String[], java.lang.String)
	 */
	SinotransPageJson importExcelInboundWuDingDan(Sheet sheet,
			String[] modelIds, String officeCode) throws Exception;

	/*
	 * 导入入库单excel文件（移动收获清单导入.xls） (non-Javadoc)
	 * 
	 * @param sheet
	 * 
	 * @param modelIds
	 * 
	 * @param officeCode
	 * 
	 * @return
	 * 
	 * @throws Exception
	 */
	SinotransPageJson importExcelInboundDetail(Sheet sheet, String[] modelIds,
			String officeCode) throws Exception;
	/**
	 * 产生随机数字和字母的组合(当传入数字为8时。结果：ovl8nx3s)
	 * 
	 * @param i
	 * @return
	 */
	String rands(int i) throws Exception;

	/**
	 * 根据传入的项目中文名称和OfficeCode 以及状态查询。如果有数据则返回List集合。如果没有数据则新增一条。
	 * 
	 * 由于新增数据返回的是Model所以最后是通过Add添加进入List集合
	 * 
	 * @param projectName
	 *            项目名称
	 * @param officeCode
	 * @param state
	 *            状态 (如果传入null或者空 则不会根据状态查询、但是返回的数据单状态是有效的状态)
	 * @return
	 * @throws Exception
	 */
	List<BasProjectModel> getJudgeOrTFBasProjectList(String projectName,
			String officeCode, String state) throws Exception;

	/**
	 * 根据传入的供应商中文名称和OfficeCode 以及状态查询。如果有数据则返回List集合。如果没有数据则新增一条。
	 * 由于新增数据返回的是Model所以最后是通过Add添加进入List集合
	 * 
	 * @param cargoConsigneeDesc
	 *            供应商中文名称
	 * @param officeCode
	 * @param state
	 *            (如果传入null或者空 则不会根据状态查询、但是返回的数据单状态是有效的状态)
	 * @return
	 * @throws Exception
	 */
	List<BasCustomerModel> getJudgeOrTFCargoConsigneeList(
			String cargoConsigneeDesc, String officeCode, String state)
			throws Exception;

	/**
	 * 验证采购订单号是否存在 存在直接返回List集合 否则新增数据后将返回的Model Add进List集合并且返回
	 * 验证需求(setOrderNo,officeCode)
	 * 新增需求(state,setOrderNo,officeCode,projectCode,agentConsigneeCode
	 * ,agentConsigneeDesc)
	 * 
	 * @param state
	 *            验证状态（如果为null则不按照状态验证、但是如果新增则直接新增为有效状态）
	 * @param setOrderNo
	 *            采购订单号
	 * @param officeCode
	 * @param projectCode
	 *            项目编号
	 * @param agentConsigneeCode
	 *            委托单位编码
	 * @param agentConsigneeDesc
	 *            委托单位名称
	 * @return
	 * @throws Exception
	 */
	List<SubmitOrderModel> getJudgeOrTFSubmitOrderList(String state,
			String setOrderNo, String officeCode, String projectCode,
			String agentConsigneeCode, String agentConsigneeDesc)
			throws Exception;

	/**
	 * 传入字符 返回结果：Num(代表传入为数字)、English(代表传入为纯英文)、Chinese(代表传入为带有中文或者纯中文)
	 * 
	 * @param test
	 * @return
	 * @throws Exception
	 */
	String judgeChineseEnglish(String test) throws Exception;
	SinotransPageJson inboundUploadPNG_JPG(String path, String businessType,
			String[] modelIds, String fileName, InputStream stream,
			String officeCode,Map<String, Object> valueMap) throws Exception;

	SinotransPageJson inboundPNGJPGselect(String type,
			String logisticsOrderUuid, String typeCode, String realPath);

	SinotransPageJson inboundLengthWidthHeightVolumeUpdate(String updateJson);

	/**
	 * 提供‘包装单位信息’验证。 验证规则如下： return null： 1、没有传送‘中文名称’并且没有传送‘英文名称’、
	 * 2、执行查询或者添加的时候抛出异常。 return Model: 1、在数据正常的情况下。有且只会返回一条Model数据
	 * unitCode根据了中文名称去生成对应的拼音信息。
	 * 
	 * @param unitName
	 *            ：包装单位中文名称(如果传送为空或者null就根据英文名称判断。否则如果中文名称和英文名称都是空的话就****返回null**
	 *            *不能执行查询)
	 * @param unitNameEn
	 *            ：包装单位英文名称（类似中文名称的验证方式。如果中英文名称都不为空那么就根据中英文一起判断）
	 * @param officeCode
	 * @param status
	 *            :查询数据时候的状态
	 * @param isOff
	 *            ：是否根据OfficeCode查询数据。true：会根据OfficeCode查询并且加上中文名称或者英文名称
	 * @return
	 */
	BasUnitModel getJudgeOrTFUnitModel(String unitName, String unitNameEn,
			String officeCode, String status, boolean isOff);

	/**
	 * 入库单保存时候将Entity转换成Base64
	 * 
	 * @param obj
	 * @return
	 */
	String object2base64(String obj);

	/**
	 * 统一将Base64的String转换成普通的String
	 * 
	 * @param s
	 * @return
	 */
	String getBase642Ojbect(String s);

	/**
	 * 生成图片信息（进出库查询页面附件调用）只限单个图片生成
	 * @param basBlobUuid
	 * @param typeCode
	 * @param realPath
	 * @return
	 */
	SinotransPageJson getSelectGenerationPictureImage(String basBlobUuid,
			String typeCode, String realPath);

	/**
	 * 执行删除进出库保存的扫描件
	 * @param basBlobUuid
	 * @param typeCode
	 * @param officeCode
	 * @return
	 */
	SinotransPageJson deleteBasBlobInbound(String basBlobUuid, String typeCode);

	/**
	 * 提供二期入库单查询页面跳转到编辑页面时候使用
	 */
	SinotransPageJson getSpjLO(String id);
	
	
	//原材料收货
		SinotransPageJson addLodCheckBarcode(String barcode, String officeCode);
		
		 SinotransPageJson addLodConfirm(String barcode, String qty, String officeCode);
		
		 SinotransPageJson yclIqcBarcode(String barcode, String officeCode);
		 //原材料上架
		  SinotransPageJson receivingCheckBarcode(String logisticsOrderNo, String barcode, String officeCode);
			
		  SinotransPageJson receivingConfirm(String logisticsOrderNo, String packageNo, String barcode, String qty, String lotCode, String goodsNature, String officeCode) throws Exception;

		LocationTaskModel commonSaveLocationTask(LocationTaskModel ltModel)
				throws Exception;

	/**
	 * 新增入库作业单以及明细
	 * @param logisticsOrder
	 * @param logisticsOrderDetails
	 * @return
	 */
	SinotransPageJson savelogisticAndDetails(LogisticsOrderModel logisticsOrder, List<LogisticsOrderDetailModel> logisticsOrderDetails) throws  Exception;

	SinotransPageJson yclOutboundConfirm(String orderNo, String barcode,
			String qty, String officeCode) throws Exception;

	SinotransPageJson outboundConfirm(String orderNo, String barcode,
			String qty, String officeCode) throws Exception;
}