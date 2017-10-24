package com.sinotrans.gd.wlp.common.service.impl;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.sinotrans.framework.core.exception.ApplicationException;
import com.sinotrans.framework.core.service.impl.BaseManagerImpl;
import com.sinotrans.framework.core.support.Condition;
import com.sinotrans.framework.core.support.PagingInfo;
import com.sinotrans.gd.wlp.basicdata.model.BasBlobModel;
import com.sinotrans.gd.wlp.basicdata.model.BasCustomerModel;
import com.sinotrans.gd.wlp.basicdata.model.BasProjectModel;
import com.sinotrans.gd.wlp.basicdata.model.BasUnitModel;
import com.sinotrans.gd.wlp.basicdata.model.ItemMasterModel;
import com.sinotrans.gd.wlp.basicdata.service.BasBlobManager;
import com.sinotrans.gd.wlp.basicdata.service.BasCodeDefManager;
import com.sinotrans.gd.wlp.basicdata.service.BasCustomerManager;
import com.sinotrans.gd.wlp.basicdata.service.BasProjectManager;
import com.sinotrans.gd.wlp.basicdata.service.BasUnitManager;
import com.sinotrans.gd.wlp.common.model.BarcodeModel;
import com.sinotrans.gd.wlp.common.model.ItemMasterLocTypeModel;
import com.sinotrans.gd.wlp.common.model.LocationTaskModel;
import com.sinotrans.gd.wlp.common.model.LogisticsOrderDetailModel;
import com.sinotrans.gd.wlp.common.model.LogisticsOrderLogModel;
import com.sinotrans.gd.wlp.common.model.LogisticsOrderModel;
import com.sinotrans.gd.wlp.common.model.RemainHoldModel;
import com.sinotrans.gd.wlp.common.model.RemainSinworkModel;
import com.sinotrans.gd.wlp.common.model.SubmitOrderDetailModel;
import com.sinotrans.gd.wlp.common.model.SubmitOrderModel;
import com.sinotrans.gd.wlp.common.query.YclCheckBarcodeQueryCondition;
import com.sinotrans.gd.wlp.common.query.YclCheckBarcodeQueryItem;
import com.sinotrans.gd.wlp.common.query.YclCheckHasPickQueryCondition;
import com.sinotrans.gd.wlp.common.query.YclCheckHasPickQueryItem;
import com.sinotrans.gd.wlp.common.query.YclOutboundLtQueryCondition;
import com.sinotrans.gd.wlp.common.query.YclOutboundLtQueryItem;
import com.sinotrans.gd.wlp.common.query.YclRecevingCheckBarcodeQueryCondition;
import com.sinotrans.gd.wlp.common.query.YclRecevingCheckBarcodeQueryItem;
import com.sinotrans.gd.wlp.common.service.LogisticsOrderManager;
import com.sinotrans.gd.wlp.common.service.SQLQueryManager;
import com.sinotrans.gd.wlp.common.service.StockWorkManager;
import com.sinotrans.gd.wlp.common.service.WmsCommonManager;
import com.sinotrans.gd.wlp.common.web.RcUtil;
import com.sinotrans.gd.wlp.common.web.SinotransDataGrid;
import com.sinotrans.gd.wlp.common.web.SinotransPageJson;
import com.sinotrans.gd.wlp.inbound.service.SubmitOrderManager;
import com.sinotrans.gd.wlp.system.entity.SessionContextUserEntity;
import com.sinotrans.gd.wlp.system.service.SysUserManager;
import com.sinotrans.gd.wlp.util.CommonUtil;
import com.sinotrans.gd.wlp.util.Encode64;
import com.sinotrans.gd.wlp.util.PinyinToolkit;
import com.sinotrans.gd.wlp.util.StringUtil;

@Service
public class LogisticsOrderManagerImpl extends BaseManagerImpl implements
		LogisticsOrderManager {

	
	@Autowired
	private BasCodeDefManager basCodeDefManagerImpl;
	@Autowired
	private SQLQueryManager sqlQueryManager;
	@Autowired
	private BasProjectManager basProjectManager; 
	@Autowired
	private BasUnitManager basUnitManager;
	@Autowired
	private BasCustomerManager basCustomerManager;	
	@Autowired
	private BasProjectManager BasProjectManager;
	@Autowired
	private SysUserManager sysUserManager;
	@Autowired
	private BasBlobManager basBlobManager;
	@Autowired
	private StockWorkManager stockWorkManager;
	@Autowired
	private WmsCommonManager wmsCommonManager;
	@Autowired
	private SubmitOrderManager submitOrderManager;

	private final static int OUTBOUND_TYPE = 1;
	private final static int INTBOUND_TYPE = 2;

	
	private String getQtyUnitCode = " ", itemKindCode = null, volume = "",
			itemNameStatic = "";

	public LogisticsOrderModel get(String id) {
		try {
			return this.dao.get(LogisticsOrderModel.class, id);
		} catch (RuntimeException e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<LogisticsOrderModel> getAll() {
		return this.dao.getAll(LogisticsOrderModel.class);
	}

	public List<LogisticsOrderModel> findByExample(LogisticsOrderModel example) {
		return this.dao.findByExample(example);
	}

	public LogisticsOrderModel save(LogisticsOrderModel model) {
		return this.dao.save(model);
	}

	public List<LogisticsOrderModel> saveAll(
			Collection<LogisticsOrderModel> models) {
		return this.dao.saveAll(models);
	}

	public void remove(LogisticsOrderModel model) {
		this.dao.remove(model);
	}

	public void removeAll(Collection<LogisticsOrderModel> models) {
		this.dao.removeAll(models);
	}

	public void removeByPk(String id) {
		this.dao.removeByPk(LogisticsOrderModel.class, id);
	}

	public void removeAllByPk(Collection<String> ids) {
		this.dao.removeAllByPk(LogisticsOrderModel.class, ids);
	}

	private String obj2str(Cell cell) {
		String result = "";
		if (HSSFCell.CELL_TYPE_STRING == cell.getCellType()) {
			result = cell.getStringCellValue();
		} else if (HSSFCell.CELL_TYPE_NUMERIC == cell.getCellType()) {
			result = new Long((long) cell.getNumericCellValue()).toString();
		}
		return result;
	}

	private String getCell(Row row, int index) {
		Cell cell = row.getCell(index);
		if (cell != null) {
			return cell.toString();
		}
		return "";
	}

	/**
	 * 暂时验证还不能公用
	 * 
	 * @param basUnitMapChinese
	 * @param basUnitMapEnglish
	 * @param basUnitMapNum
	 * @param getQtyUnitDesc
	 * @return
	 */
	public String getJudgeOrTFUnitDesc(Map<String, String> basUnitMapChinese,
			Map<String, String> basUnitMapEnglish,
			Map<String, String> basUnitMapNum, String getQtyUnitDesc) {
		String basUnit = null;
		String typeCE = "";
		try {
			typeCE = judgeChineseEnglish(getQtyUnitDesc);
		} catch (Exception e) {
			this.log.error(e, e);
			return null;
		}
		if (typeCE.equals("Chinese")) { // 如果为中文
			basUnit = basUnitMapChinese.get(getQtyUnitDesc);
		} else if (typeCE.equals("English")) { // 否则 如果为英文
			// 暂时把根据英文取得的数据保存成中文
			basUnit = basUnitMapEnglish.get(getQtyUnitDesc);
			getQtyUnitDesc = basUnitMapNum.get(basUnit);
		} else if (typeCE.equals("Num")) { // 否则 如果为数字
			basUnit = basUnitMapNum.get(getQtyUnitDesc);
		}

		if (basUnit != null && basUnit != "") {
			return basUnit;
		} else {
			getQtyUnitCode = getQtyUnitDesc;
			return null;
		}
	}

	/**
	 * 产生随机数字和字母的组合(当传入数字为8时。结果：ovl8nx3s)
	 * 
	 * @param i
	 * @return
	 */
	public String rands(int i) throws Exception {
		Random rd = new Random(); // 创建随机对象
		String n = ""; // 保存随机数
		int rdGet; // 取得随机数

		do {
			if (rd.nextInt() % 2 == 1) {
				rdGet = Math.abs(rd.nextInt()) % 10 + 48; // 产生48到57的随机数(0-9的键位值)
			} else {
				rdGet = Math.abs(rd.nextInt()) % 26 + 97; // 产生97到122的随机数(a-z的键位值)
			}
			char num1 = (char) rdGet; // int转换char
			String dd = Character.toString(num1);
			n += dd;

		} while (n.length() < i);// 设定长度，此处假定长度小于8

		return n;

	}

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
	 */
	public List<BasProjectModel> getJudgeOrTFBasProjectList(String projectName,
			String officeCode, String state) throws Exception {
		// 默认进入后去除左右空格
		projectName = StringUtil.toTrim(projectName);
		// 将项目编号临时存放在
		String projectCode = PinyinToolkit.cn2Pinyin(projectName).toUpperCase();
		// 临时变量-用于判断数据走向
		String custType = "";
		// 控制随机数产生的大小
		int suiJiShuWeiShu = 9999;
		// 存放查询到的数据在返回的时候使用。
		List<BasProjectModel> basProjectList = new ArrayList<BasProjectModel>();
		BasProjectModel basProjectModel = new BasProjectModel();
		// 首先根据Code查询
		basProjectModel.setProjectCode(projectCode);
		// 单独根据Code查询的结果返回回来信息。
		basProjectList = basProjectManager.findByExample(basProjectModel);

		// 如果存在
		if (basProjectList != null && basProjectList.size() > 0) {
			// 如果Code存在并且 中文名称 也相同的话给与custType不同的值标识已经根据Code完整的查询出了数据信息。
			if (projectName.equals(basProjectList.get(0).getProjectName())) {
				// 根据名称或者Code查询到了信息给与变量复制
				custType = "yes_code_name";
			} else {
				// 否则名称不同的话说明可能是同音字。需要保存或者根据名称查询后给与确定信息。
				custType = "no_name";
			}
		} else {
			// 否则说明根据编码没有查询得到数据。那么可能需要根据名称查询试试。
			custType = "no_name";
		}
		// 如果 Code相同而名称不同时。需要根据名称再次查询数据信息。
		if ("no_name".equals(custType)) {
			basProjectModel.setProjectCode(null);// 首先清空Code信息
			basProjectModel.setProjectName(projectName); // 供应商中文名字去空格
			basProjectList = this.dao.findByExample(basProjectModel);
			if (basProjectList != null && basProjectList.size() > 0) {
				// 根据名称或者Code查询到了信息给与变量复制
				custType = "yes_code_name";
			} else {
				// 否则这条数据是需要新增的数据。
				custType = "new_save";
			}
		} else {
			// 否则Code和Name完全对应。那么只需要判断是否是有效状态。
		}
		if (state != null && state.length() > 0 && !"new_save".equals(custType)) {
			// 如果state传送过来不是空。那么需要根据状态查询信息。
			// 判断如果现有数据中的状态和传送过来的状态相同的话说明是需要得到的数据信息。
			boolean boostate = state.equals(basProjectList.get(0).getStatus());
			if (boostate) {
				// 如果结果返回是true说明数据正常。
				custType = "yes_code_name";
			} else {
				// 否则需要的数据和查询的结果不是说需要的数据。所以清空集合信息。
				basProjectList = new ArrayList<BasProjectModel>();
				custType = "no_state_data";
			}
		}
		if (basProjectList != null && basProjectList.size() > 0
				&& "yes_code_name".equals(custType)) {

		} else {
			// 否则集合清空了。可能是状态处判断后清空了数据。

			// 如果集合清空了。并且根据名称或者Code查询到了数据。
			if ("no_state_data".equals(custType)) {
				return null;
			} else {
				// 如果前面标识必须新增一条数据的时候就给与新增。
				if ("new_save".equals(custType)) {
					// 产生随机数四位
					int ranDomInt = new Random().nextInt(suiJiShuWeiShu);
					// 否则说明：根据Code查询出来了。但是名字不能对应。有可能是同音不同名的名称信息。所以给与Code中增加了随机四位数来加以区别信息。
					basProjectModel.setProjectCode(projectCode);
					basProjectModel.setProjectName(null);
					// 首先判断是否拼音已经存在。
					basProjectList = this.dao.findByExample(basProjectModel);
					// 判断是否有重复这个编码信息 如果不存在说明是第一个新增的数据。
					if (basProjectList != null && basProjectList.size() > 0) {
						basProjectModel.setProjectCode(projectCode + ranDomInt);
						basProjectList = this.dao
								.findByExample(basProjectModel);
						if (basProjectList != null && basProjectList.size() > 0) {
							// 所以需要重新生成一次。
							ranDomInt = new Random().nextInt(suiJiShuWeiShu);
							basProjectModel.setProjectCode(projectCode
									+ ranDomInt);
						}
					}
					// 查询的时候不需要OfficeCode但是新增数据需要同时添加OfficeCode信息
					basProjectModel.setOfficeCode(officeCode);
					// 新增的数据必须是有效状态的
					basProjectModel.setStatus(CommonUtil.Active);
					// 名称去除左右空格后添加到客户名称中
					basProjectModel.setProjectName(projectName);
					// 执行保存。
					basProjectModel = this.dao.save(basProjectModel);
					// 如果为空说明可能数据库报错。Code重复。
					if (!RcUtil.isEmpty(basProjectModel)) {
						basProjectList.add(basProjectModel);
					}
				} else {
					// 前面赋值的信息最多前面三个结果。暂时没有出现第四种结果。所以前面三个一定可以完整拦截信息。这里等待处理新的信息
				}
			}
		}
		return basProjectList;
	}

	/**
	 * 根据传入的供应商中文名称和OfficeCode 以及状态查询。如果有数据则返回List集合。如果没有数据则新增一条。
	 * 
	 * 由于新增数据返回的是Model所以最后是通过Add添加进入List集合
	 * 
	 * @param cargoConsigneeDesc
	 *            供应商中文名称
	 * @param officeCode
	 * @param state
	 *            (如果传入null或者空 则不会根据状态查询、但是返回的数据单状态是有效的状态)
	 * @return
	 */
	public List<BasCustomerModel> getJudgeOrTFCargoConsigneeList(
			String cargoConsigneeDesc, String officeCode, String state)
			throws Exception {
		// 给与中文去除左右空格
		cargoConsigneeDesc = StringUtil.toTrim(cargoConsigneeDesc);
		// 临时变量-用于判断数据走向
		String custType = "";
		// 控制随机数产生的大小
		int suiJiShuWeiShu = 9999;

		// 返回数据使用
		List<BasCustomerModel> customerCodeList = new ArrayList<BasCustomerModel>();
		BasCustomerModel basM = new BasCustomerModel();
		// basM.setCustomerName(StringUtil.toTrim(cargoConsigneeDesc)); //
		// 供应商中文名字 去空格
		basM.setCustomerCode(PinyinToolkit.cn2Pinyin(
				StringUtil.toTrim(cargoConsigneeDesc)).toUpperCase());
		// 根据单独条件 名称转换层拼音查询。
		customerCodeList = basCustomerManager.findByExample(basM);
		// 如果存在
		if (customerCodeList != null && customerCodeList.size() > 0) {
			// 如果Code存在并且 中文名称 也相同的话给与custType不同的值标识已经根据Code完整的查询出了数据信息。
			if (cargoConsigneeDesc.equals(customerCodeList.get(0)
					.getCustomerName())) {
				// 根据名称或者Code查询到了信息给与变量复制
				custType = "yes_code_name";
			} else {
				// 否则名称不同的话说明可能是同音字。需要保存或者根据名称查询后给与确定信息。
				custType = "no_name";
			}
		} else {
			// 否则说明根据编码没有查询得到数据。那么可能需要根据名称查询试试。
			custType = "no_name";
		}
		// 如果 Code相同而名称不同时。需要根据名称再次查询数据信息。
		if ("no_name".equals(custType)) {
			basM.setCustomerCode(null);// 首先清空Code信息
			basM.setCustomerName(StringUtil.toTrim(cargoConsigneeDesc)); // 供应商中文名字去空格
			customerCodeList = basCustomerManager.findByExample(basM);
			if (customerCodeList != null && customerCodeList.size() > 0) {
				// 根据名称或者Code查询到了信息给与变量复制
				custType = "yes_code_name";
			} else {
				// 否则这条数据是需要新增的数据。
				custType = "new_save";
			}
		} else {
			// 否则Code和Name完全对应。那么只需要判断是否是有效状态。
		}

		// customerCodeList = new ArrayList<BasCustomerModel>();// 重新new清空集合

		if (state != null && state.length() > 0 && !"new_save".equals(custType)) {
			// 如果state传送过来不是空。那么需要根据状态查询信息。
			// 判断如果现有数据中的状态和传送过来的状态相同的话说明是需要得到的数据信息。
			boolean boostate = state
					.equals(customerCodeList.get(0).getStatus());
			if (boostate) {
				// 如果结果返回是true说明数据正常。
				custType = "yes_code_name";
			} else {
				// 否则需要的数据和查询的结果不是说需要的数据。所以清空集合信息。
				customerCodeList = new ArrayList<BasCustomerModel>();
				custType = "no_state_data";
			}
			// basM.setStatus(state); // 状态
		}
		// customerCodeList = basCustomerManager.findByExample(basM);
		// 如果集合信息不是空则暂时不执行任何操作返回集合到调用处。
		if (customerCodeList != null && customerCodeList.size() > 0
				&& "yes_code_name".equals(custType)) {

		} else {
			// 否则集合清空了。可能是状态处判断后清空了数据。

			// 如果集合清空了。并且根据名称或者Code查询到了数据。
			if ("no_state_data".equals(custType)) {
				return null;
			} else {
				// 如果前面标识必须新增一条数据的时候就给与新增。
				if ("new_save".equals(custType)) {
					// 产生随机数四位
					int ranDomInt = new Random().nextInt(suiJiShuWeiShu);
					// 否则说明：根据Code查询出来了。但是名字不能对应。有可能是同音不同名的名称信息。所以给与Code中增加了随机四位数来加以区别信息。
					basM.setCustomerCode(PinyinToolkit.cn2Pinyin(
							cargoConsigneeDesc).toUpperCase());
					basM.setCustomerName(null);
					// 首先判断是否拼音已经存在。
					customerCodeList = this.dao.findByExample(basM);
					// 判断是否有重复这个编码信息 如果不存在说明是第一个新增的数据。
					if (customerCodeList != null && customerCodeList.size() > 0) {
						basM.setCustomerCode(PinyinToolkit.cn2Pinyin(
								cargoConsigneeDesc).toUpperCase()
								+ ranDomInt);
						customerCodeList = this.dao.findByExample(basM);
						if (customerCodeList != null
								&& customerCodeList.size() > 0) {
							// 所以需要重新生成一次。
							ranDomInt = new Random().nextInt(suiJiShuWeiShu);
							basM.setCustomerCode(PinyinToolkit.cn2Pinyin(
									cargoConsigneeDesc).toUpperCase()
									+ ranDomInt);
						}
					}
					// 查询的时候不需要OfficeCode但是新增数据需要同时添加OfficeCode信息
					basM.setOfficeCode(officeCode);
					// 新增的数据必须是有效状态的
					basM.setStatus(CommonUtil.Active);
					// 名称去除左右空格后添加到客户名称中
					basM.setCustomerName(StringUtil.toTrim(cargoConsigneeDesc));
					// 执行保存。
					basM = basCustomerManager.save(basM);
					// 如果为空说明可能数据库报错。Code重复。
					if (!RcUtil.isEmpty(basM)) {
						customerCodeList.add(basM);
					}
				} else {
					// 前面赋值的信息最多前面三个结果。暂时没有出现第四种结果。所以前面三个一定可以完整拦截信息。这里等待处理新的信息
				}
			}
		}
		return customerCodeList;
	}

	/**
	 * 传入字符 返回结果：Num(代表传入为数字)、English(代表传入为纯英文)、Chinese(代表传入为带有中文或者纯中文)
	 * 
	 * @param test
	 * @return
	 */
	public String judgeChineseEnglish(String test) throws Exception {

		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(test);
		byte[] bytes = test.getBytes();
		int i = bytes.length;// i为字节长度
		int j = test.length();// j为字符长度
		if (isNum.matches()) {
			return "Num";
		} else if (i == j && i + j != 0) {
			return "English";
		} else if (j < i) {
			return "Chinese";
		}

		return null;
	}




	/*
	 * 保存附件到basblob表中
	 */
	private byte[] saveIfFiles(InputStream inputStream, String officecode)
			throws Exception {
		byte[] data = new byte[1500000];
		byte[] dataTemp = new byte[1500000];
		int count = -1, point = 0;
		while ((count = inputStream.read(dataTemp, 0, 1500000)) != -1) {
			byte[] dt = new byte[count + (point * 1500000)];

			for (int i = 0; i < count + (point * 1500000); i++) {
				if (point > 0 && i < point * 1500000) {
					dt[i] = data[i];
				} else {
					dt[i] = dataTemp[i - point * 1500000];
				}
			}
			data = dt;
			point++;
		}
		inputStream.close();
		dataTemp = null;
		return data;
	}

	/**
	 * 执行删除进出库保存的扫描件
	 * 
	 * @param basBlobUuid
	 * @param typeCode
	 * @param officeCode
	 * @return
	 */
	@SuppressWarnings("static-access")
	@Override
	public SinotransPageJson deleteBasBlobInbound(String basBlobUuid,
			String typeCode) {
		SinotransPageJson spj = new SinotransPageJson();
		BasBlobModel bb = new BasBlobModel();
		bb.setBasBlobUuid(basBlobUuid);
		bb.setTypeCode(typeCode);
		// bb.setRowState(bb.ROW_STATE_DELETED);
		List<BasBlobModel> bbList = new ArrayList<BasBlobModel>();
		bbList = this.dao.findByExample(bb);
		if (RcUtil.isEmpty(bbList) || bbList.size() <= 0) {
			spj.setResult(false);
			spj.setError("后台没有查询到页面所要删除的图片信息。");
			return spj;
		} else {
			bb = bbList.get(0);
			this.dao.remove(bb);
		}

		spj.setResult(true);
		spj.setMsg("删除成功");
		return spj;
	}

	/** 获取当前用户 */
	private SessionContextUserEntity getUser() {
		SessionContextUserEntity scu = SessionContextUserEntity.currentUser();
		return scu;
	}


	

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
	@Override
	public BasUnitModel getJudgeOrTFUnitModel(String unitName,
			String unitNameEn, String officeCode, String status, boolean isOff) {
		BasUnitModel bas;
		bas = new BasUnitModel();
		// 去除左右空格信息
		unitName = StringUtil.toTrim(unitName);
		unitNameEn = StringUtil.toTrim(unitNameEn);
		// 如果对方传送的参数是空的。那么就返回null
		if (RcUtil.isEmpty(unitName) && RcUtil.isEmpty(unitNameEn)) {
			return null;
		}
		// 生成包装单位信息。
		String unitCode = "";
		if (!RcUtil.isEmpty(unitName)) {
			unitCode = PinyinToolkit.cn2Pinyin(unitName).toUpperCase();
		} else if (!RcUtil.isEmpty(unitNameEn)) {
			unitCode = PinyinToolkit.cn2Pinyin(unitNameEn).toUpperCase();
		}
		// 临时变量-用于判断数据走向
		String custType = "";
		// 控制随机数产生的大小
		int suiJiShuWeiShu = 9999;

		// 如果单位编码不是空的则继续操作。否则返回null
		if (RcUtil.isEmpty(unitCode)) {
			return null;
		} else {
			// 用于保存也
			List<BasUnitModel> basList = new ArrayList<BasUnitModel>();
			bas.setUnitCode(unitCode);
			basList = this.dao.findByExample(bas);
			// 如果存在
			if (basList != null && basList.size() > 0) {
				// 如果Code存在并且 中文名称 也相同的话给与custType不同的值标识已经根据Code完整的查询出了数据信息。
				if (unitName.equals(basList.get(0).getUnitName())) {
					// 根据名称或者Code查询到了信息给与变量复制
					custType = "yes_code_name";
				} else {
					// 否则名称不同的话说明可能是同音字。需要保存或者根据名称查询后给与确定信息。
					custType = "no_name";
				}
			} else {
				// 否则说明根据编码没有查询得到数据。那么可能需要根据名称查询试试。
				custType = "no_name";
			}

			// 如果 Code相同而名称不同时。需要根据名称再次查询数据信息。
			if ("no_name".equals(custType)) {
				bas.setUnitCode(null);// 首先清空Code信息
				bas.setUnitName(RcUtil.isEmpty(unitName) ? unitNameEn
						: unitName); // 供应商中文名字去空格
				basList = this.dao.findByExample(bas);
				if (basList != null && basList.size() > 0) {
					// 根据名称或者Code查询到了信息给与变量复制
					custType = "yes_code_name";
				} else {
					// 否则这条数据是需要新增的数据。
					custType = "new_save";
				}
			} else {
				// 否则Code和Name完全对应。那么只需要判断是否是有效状态。
			}

			// customerCodeList = new ArrayList<BasCustomerModel>();// 重新new清空集合

			if (status != null && status.length() > 0
					&& !"new_save".equals(custType)) {
				// 如果state传送过来不是空。那么需要根据状态查询信息。
				// 判断如果现有数据中的状态和传送过来的状态相同的话说明是需要得到的数据信息。
				boolean boostate = status.equals(basList.get(0).getStatus());
				if (boostate) {
					// 如果结果返回是true说明数据正常。
					custType = "yes_code_name";
				} else {
					// 否则需要的数据和查询的结果不是说需要的数据。所以清空集合信息。
					basList = new ArrayList<BasUnitModel>();
					custType = "no_state_data";
				}
			}
			if (isOff && basList != null && basList.size() > 0) {
				// 判断如果现有数据中的状态和传送过来的状态相同的话说明是需要得到的数据信息。
				boolean boostate = officeCode.equals(basList.get(0)
						.getOfficeCode());
				if (boostate) {
					// 如果结果返回是true说明数据正常。
					custType = "yes_code_name";
				} else {
					// 否则需要的数据和查询的结果不是说需要的数据。所以清空集合信息。这里的原因是根据officeCode查询后没有了数据。
					basList = new ArrayList<BasUnitModel>();
					custType = "no_state_data";
				}
			}
			if (basList != null && basList.size() > 0
					&& "yes_code_name".equals(custType)) {
				bas = basList.get(0);
			} else {
				// 否则集合清空了。可能是状态处判断后清空了数据。

				// 如果集合清空了。并且根据名称或者Code查询到了数据。
				if ("no_state_data".equals(custType)) {
					return null;
				} else {
					// 如果前面标识必须新增一条数据的时候就给与新增。
					if ("new_save".equals(custType)) {
						// 产生随机数四位
						int ranDomInt = new Random().nextInt(suiJiShuWeiShu);
						// 否则说明：根据Code查询出来了。但是名字不能对应。有可能是同音不同名的名称信息。所以给与Code中增加了随机四位数来加以区别信息。
						bas.setUnitCode(unitCode);
						bas.setUnitName(null);
						// 首先判断是否拼音已经存在。
						basList = this.dao.findByExample(bas);
						// 判断是否有重复这个编码信息 如果不存在说明是第一个新增的数据。
						if (basList != null && basList.size() > 0) {
							bas.setUnitCode(unitCode + ranDomInt);
							basList = this.dao.findByExample(bas);
							if (basList != null && basList.size() > 0) {
								// 所以需要重新生成一次。
								ranDomInt = new Random()
										.nextInt(suiJiShuWeiShu);
								bas.setUnitCode(unitCode + ranDomInt);
							}
						}
						// 查询的时候不需要OfficeCode但是新增数据需要同时添加OfficeCode信息
						bas.setOfficeCode(officeCode);
						// 新增的数据必须是有效状态的
						bas.setStatus(CommonUtil.Active);
						// 名称去除左右空格后添加名称中
						bas.setUnitName(unitName);
						bas.setUnitNameEn(unitNameEn);
						// 执行保存。
						bas = this.dao.save(bas);
						// 如果为空说明可能数据库报错。Code重复。
						if (!RcUtil.isEmpty(bas)) {
							basList.add(bas);
						}
					} else {
						// 前面赋值的信息最多前面三个结果。暂时没有出现第四种结果。所以前面三个一定可以完整拦截信息。这里等待处理新的信息
					}
				}
			}
		}
		return bas;
	}

	/**
	 * 统一将Object转换成Base64的String 调用提示：需要在前台页面toJSON。
	 * 如果转换失败返回空字符串。（DWR调用。前台可自行判断）
	 * 
	 * @param obj
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@Override
	public String object2base64(String obj) {
		if (!RcUtil.isEmpty(obj)) {
			// String string = JsonUtil.beanToJson(obj);
			if (RcUtil.isEmpty(obj)) {
				throw new ApplicationException("转换Base64后数据为空。"); // 抛出错误信息
			}

			String basString = Encode64.encode(obj);
			return basString;
		}
		return "";
	}

	/**
	 * 统一将Base64的String转换成普通的String
	 * 
	 * @param s
	 * @return
	 */
	@Override
	public String getBase642Ojbect(String s) {
		if (RcUtil.isEmpty(s))
			return "";
		try {
			return Encode64.decode(s);
		} catch (Exception e) {
			this.log.error(e, e);
			return "";
		}
	}

	/**
	 * 提供二期入库单查询页面跳转到编辑页面时候使用
	 */
	@Override
	public SinotransPageJson getSpjLO(String id) {
		SinotransPageJson spj = new SinotransPageJson();
		LogisticsOrderModel loModel = this.get(id);
		if (RcUtil.isEmpty(loModel)
				|| RcUtil.isEmpty(loModel.getLogisticsOrderUuid())) {
			spj.setResult(false);
			spj.setError("没有查询到可用数据。");
		}
		spj.setObject(loModel);
		spj.setResult(true);
		spj.setMsg("查询成功");
		return spj;
	}

	@Override
	public SinotransDataGrid queryLogisticsList(PagingInfo paginginfo,
			String orderDateStart, String orderDateEnd, String goodsNature,
			String marksNumber, String extItemCode, String orderNo,
			String logisticsOrderNo, String batchNo, String projectCode,
			String submitOrderNo, String officeCode, String isSubmitOrderNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updateLOStatus(String loUuid, String status) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String cancelSubmit(String loUuid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean finish(String loUuid) throws IllegalAccessException,
			InvocationTargetException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public SinotransPageJson importExcelInbound(Sheet sheet,
			String[] cargoControlCode, String officeCode) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SinotransPageJson importExcelInboundWu(Sheet sheet,
			String[] cargoControlCode, String officeCode) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SinotransPageJson importExcelInboundWuDingDan(Sheet sheet,
			String[] modelIds, String officeCode) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SinotransPageJson importExcelInboundDetail(Sheet sheet,
			String[] modelIds, String officeCode) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SubmitOrderModel> getJudgeOrTFSubmitOrderList(String state,
			String setOrderNo, String officeCode, String projectCode,
			String agentConsigneeCode, String agentConsigneeDesc)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SinotransPageJson inboundUploadPNG_JPG(String path,
			String businessType, String[] modelIds, String fileName,
			InputStream stream, String officeCode, Map<String, Object> valueMap)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SinotransPageJson inboundPNGJPGselect(String type,
			String logisticsOrderUuid, String typeCode, String realPath) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SinotransPageJson inboundLengthWidthHeightVolumeUpdate(
			String updateJson) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SinotransPageJson getSelectGenerationPictureImage(
			String basBlobUuid, String typeCode, String realPath) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public SinotransPageJson addLodCheckBarcode(String barcode, String officeCode) {
		SinotransPageJson spj = new SinotransPageJson(true);
		if(RcUtil.isEmpty(barcode)){
			spj.setResult(false);
			spj.setError("条码不能为空!");
			return spj;
		}
		if(RcUtil.isEmpty(officeCode)) {
			spj.setResult(false);
			spj.setError("officeCode不能为空!");
			return spj;
		}
		
		//物料编码“A0001”（5位）+供应商编码“A1”（2位）+采购订单编号（14位）+批次号（6位以下）
		if(barcode.length() > 23){
			String itemCode = barcode.substring(0,5); //物料编码
			String aux5 = barcode.substring(5,7);     //供应商
			String orderNo = barcode.substring(7,21);
			String batchNo = barcode.substring(21, barcode.length());
			Map<String, Object> resultMap = new HashMap<String, Object>();
			
			List<ItemMasterModel> imList = this.dao.createCommonQuery(ItemMasterModel.class)
					.addCondition(Condition.eq(ItemMasterModel.FieldNames.itemCode, itemCode))
					.addCondition(Condition.eq(ItemMasterModel.FieldNames.officeCode, officeCode)).query();
			if(imList.size() > 0){
				itemCode = imList.get(0).getExtItemCode();
			}
			
			resultMap.put("orderNo", orderNo);
			resultMap.put("itemCode", itemCode);
			List<BasCustomerModel> bcList = this.dao.createCommonQuery(BasCustomerModel.class)
					.addCondition(Condition.eq(BasCustomerModel.FieldNames.status, CommonUtil.Active))
					.addCondition(Condition.eq(BasCustomerModel.FieldNames.customerCode, aux5))
					.addCondition(Condition.eq(BasCustomerModel.FieldNames.officeCode, officeCode)).query();
			if(bcList.size() > 0){
				aux5 = bcList.get(0).getCustomerName();
			}
			resultMap.put("aux5", aux5);
			resultMap.put("batchNo", batchNo);
			List<SubmitOrderModel> soList = this.dao.createCommonQuery(SubmitOrderModel.class)
					.addCondition(Condition.eq(SubmitOrderModel.FieldNames.orderNo, orderNo))
					.addCondition(Condition.eq(SubmitOrderModel.FieldNames.officeCode, officeCode)).query();
			if(soList.size() > 0){
				SubmitOrderModel soModel = soList.get(0);
				LogisticsOrderModel loModel = new LogisticsOrderModel();
				//检查有没有扫描过
				List<LogisticsOrderModel> loList = this.dao.createCommonQuery(LogisticsOrderModel.class)
						.addCondition(Condition.eq(LogisticsOrderModel.FieldNames.orderNo, orderNo))
						.addCondition(Condition.eq(LogisticsOrderModel.FieldNames.transactionType, CommonUtil.TRANSACTIONTYPE_SIN))
						.addCondition(Condition.eq(LogisticsOrderModel.FieldNames.officeCode, officeCode)).query();
				if(loList.size() > 0){
					loModel = loList.get(0);
					//检查条码是否被扫描过
					List<LogisticsOrderDetailModel> lodList = this.dao.createCommonQuery(LogisticsOrderDetailModel.class)
							.addCondition(Condition.eq(LogisticsOrderDetailModel.FieldNames.logisticsOrderUuid, loModel.getLogisticsOrderUuid()))
							.addCondition(Condition.eq(LogisticsOrderDetailModel.FieldNames.barcode, barcode))
							.addCondition(Condition.eq(LogisticsOrderDetailModel.FieldNames.transactionType, CommonUtil.TRANSACTIONTYPE_SIN))
							.addCondition(Condition.eq(LogisticsOrderDetailModel.FieldNames.officeCode, officeCode)).query();
					if(lodList.size() > 0){
						LogisticsOrderDetailModel lodModel = lodList.get(0);
						resultMap.put("lodQty", lodModel.getQty());
						List<SubmitOrderDetailModel> sodList = this.dao.createCommonQuery(SubmitOrderDetailModel.class)
								.addCondition(Condition.eq(SubmitOrderDetailModel.FieldNames.submitOrderDetailUuid, lodModel.getSubmitOrderDetailUuid()))
								.addCondition(Condition.eq(SubmitOrderDetailModel.FieldNames.officeCode, officeCode)).query();
						if(sodList.size() > 0){
							SubmitOrderDetailModel sodModel = sodList.get(0);
							resultMap.put("sodQty", sodModel.getQty());
							if(sodModel.getQty() > lodModel.getQty()){
								resultMap.put("qty", sodModel.getQty() - sodModel.getQty());
							}else{
								resultMap.put("qty", "0");
							}
						}
					} else {
						List<SubmitOrderDetailModel> sodList = this.dao.createCommonQuery(SubmitOrderDetailModel.class)
								.addCondition(Condition.eq(SubmitOrderDetailModel.FieldNames.submitOrderUuid, soModel.getSubmitOrderUuid()))
								.addCondition(Condition.eq(SubmitOrderDetailModel.FieldNames.itemCode, barcode.substring(0,5)))
								.addCondition(Condition.eq(SubmitOrderDetailModel.FieldNames.officeCode, officeCode)).query();
						if(sodList.size() > 0){
							SubmitOrderDetailModel sodModel = sodList.get(0);
							resultMap.put("sodQty", sodModel.getQty());
							resultMap.put("lodQty", "0");
							resultMap.put("qty", sodModel.getQty());
						}else{
							throw new ApplicationException("找不到采购订单明细信息.条码物料" + itemCode);
						}
					}
				} else {
					//没有lo就生成lo
					try {
						BeanUtils.copyProperties(loModel, soModel);
					} catch (IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					loModel.setOfficeCode(officeCode);
					loModel.setOrderDate(dao.getSysDate());
					loModel.setTransactionType(CommonUtil.TRANSACTIONTYPE_SIN);
					loModel.setTransactionStatus(CommonUtil.Active);
					loModel.setOrderNo(orderNo);
					//还要修改
					String logisticsOrderNo = wmsCommonManager.generateNumberByRule(CommonUtil.SEQUENCE_Inbound, officeCode);
					/*String dateString=RcUtil.date2String(new Date(), "YYYYMMDD");
					Random random =new Random();
					String logisticsOrderNo="Y"+dateString+random.nextInt(100000);*/
					loModel.setLogisticsOrderNo(logisticsOrderNo);
					loModel.setOfficeCode(officeCode);
					loModel = dao.save(loModel);
					
					
					List<SubmitOrderDetailModel> sodList = this.dao.createCommonQuery(SubmitOrderDetailModel.class)
							.addCondition(Condition.eq(SubmitOrderDetailModel.FieldNames.submitOrderUuid, soModel.getSubmitOrderUuid()))
							.addCondition(Condition.eq(SubmitOrderDetailModel.FieldNames.itemCode, barcode.substring(0,5)))
							.addCondition(Condition.eq(SubmitOrderDetailModel.FieldNames.officeCode, officeCode)).query();
					if(sodList.size() > 0){
						SubmitOrderDetailModel sodModel = sodList.get(0);
						resultMap.put("sodQty", sodModel.getQty());
						resultMap.put("lodQty", "0");
						resultMap.put("qty", sodModel.getQty());
					}else{
						throw new ApplicationException("找不到采购订单明细信息.条码物料" + itemCode);
					}
				}
				
				List<BarcodeModel> bList = this.dao.createCommonQuery(BarcodeModel.class)
						.addCondition(Condition.eq(LogisticsOrderDetailModel.FieldNames.barcode, barcode))
						.addCondition(Condition.eq(LogisticsOrderDetailModel.FieldNames.officeCode, officeCode)).query();
				if(bList.size() > 0){
					BarcodeModel bModel = bList.get(0);
					if(CommonUtil.Active.equals(bModel.getStatus())){
						if(loModel.getLogisticsOrderNo().equals(bModel.getLogisticsOrderNo())){
							
						}else{
							throw new ApplicationException("该条码已被入库单"+bModel.getLogisticsOrderNo()+"扫码入库!");
						}
					}
				}
				String ltMsg = "";
				List<LogisticsOrderLogModel> lolList = this.dao.createCommonQuery(LogisticsOrderLogModel.class)
						.addCondition(Condition.eq(LogisticsOrderLogModel.FieldNames.logisticsOrderUuid, loModel.getLogisticsOrderUuid()))
						.addCondition(Condition.eq(LogisticsOrderLogModel.FieldNames.remark, barcode))
						.addCondition(Condition.eq(LogisticsOrderLogModel.FieldNames.transactionType, "YCL_INBOUND"))
						.addCondition(Condition.eq(LogisticsOrderLogModel.FieldNames.officeCode, officeCode)).query();
				if(lolList.size() > 0){
					for (LogisticsOrderLogModel lolModel : lolList) {
						ltMsg += RcUtil.date2String(lolModel.getWorkDate(), RcUtil.yyyy_MM_dd_HH_mm_ss) + ",数量:" + lolModel.getAux1() + "\r\n";
					}
				}
				ltMsg += "扫描总数量:" + resultMap.get("lodQty");
				resultMap.put("ltMsg", ltMsg);
				spj.setObject(resultMap);
			} else {
				throw new ApplicationException("找不到条码'" + orderNo + "'的采购订单!");
			}
		} else {
			spj.setResult(false);
			spj.setError("条码长度不足!");
		}
		return spj;
	}

	@Override
	public SinotransPageJson addLodConfirm(String barcode, String qty,
			String officeCode) {
		SinotransPageJson spj = new SinotransPageJson(true);
		if(RcUtil.isEmpty(barcode)){
			spj.setResult(false);
			spj.setError("条码不能为空!");
			return spj;
		}
		if(RcUtil.isEmpty(officeCode)) {
			spj.setResult(false);
			spj.setError("officeCode不能为空!");
			return spj;
		}
		//物料编码“A0001”（5位）+供应商编码“A1”（2位）+采购订单编号（14位）+批次号（6位以下）
		if(barcode.length() > 23){
			String itemCode = barcode.substring(0,5); //物料编码
			String aux5 = barcode.substring(5,7);     //供应商
			String orderNo = barcode.substring(7,21);
			String batchNo = barcode.substring(21, barcode.length());
			//保存LOD
			LogisticsOrderDetailModel lodModel = new LogisticsOrderDetailModel();
			List<SubmitOrderModel> soList = this.dao.createCommonQuery(SubmitOrderModel.class)
					.addCondition(Condition.eq(SubmitOrderModel.FieldNames.orderNo, orderNo))
					.addCondition(Condition.eq(SubmitOrderModel.FieldNames.officeCode, officeCode)).query();
			if(soList.size() > 0){
				SubmitOrderModel soModel = soList.get(0);
				
				LogisticsOrderModel loModel = new LogisticsOrderModel();
				
				//检查有没有扫描过
				List<LogisticsOrderModel> loList = this.dao.createCommonQuery(LogisticsOrderModel.class)
						.addCondition(Condition.eq(LogisticsOrderModel.FieldNames.orderNo, orderNo))
						.addCondition(Condition.eq(LogisticsOrderModel.FieldNames.transactionType, CommonUtil.TRANSACTIONTYPE_SIN))
						.addCondition(Condition.eq(LogisticsOrderModel.FieldNames.officeCode, officeCode)).query();
				if(loList.size() > 0){
					loModel = loList.get(0);
					//检查条码是否被扫描过
					List<LogisticsOrderDetailModel> lodList = this.dao.createCommonQuery(LogisticsOrderDetailModel.class)
							.addCondition(Condition.eq(LogisticsOrderDetailModel.FieldNames.logisticsOrderUuid, loModel.getLogisticsOrderUuid()))
							.addCondition(Condition.eq(LogisticsOrderDetailModel.FieldNames.barcode, barcode))
							.addCondition(Condition.eq(LogisticsOrderDetailModel.FieldNames.transactionType, CommonUtil.TRANSACTIONTYPE_SIN))
							.addCondition(Condition.eq(LogisticsOrderDetailModel.FieldNames.officeCode, officeCode)).query();
					if(lodList.size() > 0){
						lodModel = lodList.get(0);
						List<SubmitOrderDetailModel> sodList = this.dao.createCommonQuery(SubmitOrderDetailModel.class)
								.addCondition(Condition.eq(SubmitOrderDetailModel.FieldNames.submitOrderDetailUuid, lodModel.getSubmitOrderDetailUuid()))
								.addCondition(Condition.eq(SubmitOrderDetailModel.FieldNames.officeCode, officeCode)).query();
						if(sodList.size() > 0){
							SubmitOrderDetailModel sodModel = sodList.get(0);
							Double sodQty = sodModel.getQty();
							if(lodModel.getQty() + Double.valueOf(qty) > sodQty){
								throw new ApplicationException("入库数量不能超过订单数量!");
							}
							lodModel.setQty(lodModel.getQty() + Double.valueOf(qty));
							lodModel = dao.save(lodModel);
						} else {
							throw new ApplicationException("条码物料" + itemCode + "已扫描,但没有关联订单明细!");
						}
					}
				} else {
					//没有lo就生成lo
					try {
						BeanUtils.copyProperties(loModel, soModel);
					} catch (IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					loModel.setOfficeCode(officeCode);
					loModel.setOrderDate(dao.getSysDate());
					loModel.setTransactionType(CommonUtil.TRANSACTIONTYPE_SIN);
					loModel.setTransactionStatus(CommonUtil.Active);
					loModel.setOrderNo(orderNo);
					loModel.setSubmitOrderUuid(soModel.getSubmitOrderUuid());
					String dateString=RcUtil.date2String(new Date(), "YYYYMMDD");
					Random random =new Random();
					String logisticsOrderNo="Y"+dateString+random.nextInt(100000);
					loModel.setLogisticsOrderNo(logisticsOrderNo);
					loModel.setOfficeCode(officeCode);
					loModel = dao.save(loModel);
				}
				
				if(RcUtil.isEmpty(lodModel.getLogisticsOrderDetailUuid())){
					List<SubmitOrderDetailModel> sodList = this.dao.createCommonQuery(SubmitOrderDetailModel.class)
							.addCondition(Condition.eq(SubmitOrderDetailModel.FieldNames.itemCode, itemCode))
							.addCondition(Condition.eq(SubmitOrderDetailModel.FieldNames.submitOrderUuid, soModel.getSubmitOrderUuid()))
							.addCondition(Condition.eq(SubmitOrderDetailModel.FieldNames.officeCode, officeCode)).query();
					if(sodList.size() > 0){
						SubmitOrderDetailModel sodModel = sodList.get(0);
						Double sodQty = sodModel.getQty();
						if(Double.valueOf(qty) > sodQty){
							throw new ApplicationException("入库数量不能超过订单数量!");
						}
						try {
							BeanUtils.copyProperties(lodModel, sodModel);
						} catch (IllegalAccessException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (InvocationTargetException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						lodModel.setLogisticsOrderNo(loModel.getLogisticsOrderNo());
						lodModel.setLogisticsOrderUuid(loModel.getLogisticsOrderUuid());
						lodModel.setSubmitOrderDetailUuid(sodModel.getSubmitOrderDetailUuid());
						lodModel.setTransactionType(CommonUtil.TRANSACTIONTYPE_SIN);
						lodModel.setTransactionStatus(CommonUtil.Active);
						lodModel.setOfficeCode(officeCode);
						lodModel.setItemCode(itemCode);
						lodModel.setBatchNo(batchNo);
						lodModel.setBarcode(barcode);
						lodModel.setQty(Double.valueOf(qty));
						lodModel.setAux5(aux5);
						//Long seqNoMax = hubScannerManager.commonGetLodSeqNoMaxByLoUuid(loModel.getLogisticsOrderUuid());
						//lodModel.setSeqNo(++seqNoMax);
						lodModel = this.dao.save(lodModel);
						
						spj.setMsg("扫描保存成功!");
					} else {
						throw new ApplicationException("找不到采购订单明细信息.条码物料" + itemCode);
					}
				}
				
				
				List<BarcodeModel> bList = this.dao.createCommonQuery(BarcodeModel.class)
						.addCondition(Condition.eq(BarcodeModel.FieldNames.barcode, barcode))
						.addCondition(Condition.eq(BarcodeModel.FieldNames.officeCode, officeCode)).query();
				if(bList.size() > 0){
					BarcodeModel bModel = bList.get(0);
					bModel.setLogisticsOrderDetailUuid(lodModel.getLogisticsOrderDetailUuid());
					bModel.setLogisticsOrderNo(lodModel.getLogisticsOrderNo());
					bModel.setOfficeCode(officeCode);
					bModel.setQty(lodModel.getQty());
					bModel.setStatus(CommonUtil.Active);
					bModel.setRemark("扫码入库添加的条码");
					dao.save(bModel);
				}else{
					BarcodeModel bModel = new BarcodeModel();
					bModel.setBarcode(barcode);
					bModel.setBatchNo(batchNo);
					bModel.setControlWord(CommonUtil.Default_Control_Word);
					bModel.setCreatorDate(dao.getSysDate());
					bModel.setLogisticsOrderDetailUuid(lodModel.getLogisticsOrderDetailUuid());
					bModel.setLogisticsOrderNo(lodModel.getLogisticsOrderNo());
					bModel.setOfficeCode(officeCode);
					bModel.setQty(lodModel.getQty());
					bModel.setStatus(CommonUtil.Active);
					bModel.setRemark("扫码入库添加的条码");
					dao.save(bModel);
				}
				
				
				//保存扫描记录
				LogisticsOrderLogModel lolModel = new LogisticsOrderLogModel();
				lolModel.setOfficeCode(officeCode);
				lolModel.setTransactionType("YCL_INBOUND");
				lolModel.setLogisticsOrderUuid(loModel.getLogisticsOrderUuid());
				lolModel.setStatus(CommonUtil.Active);
				lolModel.setWorkDate(dao.getSysDate());
				lolModel.setWorkDesc("原材料扫码入库");
				lolModel.setWorkPerson(SessionContextUserEntity.currentUser().getUsername());
				lolModel.setRemark(barcode);
				lolModel.setAux1(qty);
				dao.save(lolModel);
				
			} else {
				throw new ApplicationException("找不到条码'" + orderNo + "'的采购订单!");
			}
		} else {
			spj.setResult(false);
			spj.setError("条码长度不足!");
		}
		spj.setMsg("扫描保存成功!");
		return spj;
	}

	@Override
	public SinotransPageJson yclIqcBarcode(String barcode, String officeCode) {
		SinotransPageJson spj = new SinotransPageJson(true);
		if(RcUtil.isEmpty(barcode)){
			spj.setResult(false);
			spj.setError("条码不能为空!");
			return spj;
		}
		if(RcUtil.isEmpty(officeCode)) {
			spj.setResult(false);
			spj.setError("officeCode不能为空!");
			return spj;
		}
		
		Map<String, Object> result = new HashMap<String, Object>();
		//物料编码“A0001”（5位）+供应商编码“A1”（2位）+采购订单编号（14位）+批次号（6位以下）
		if(barcode.length() > 23){
			String orderNo = barcode.substring(7,21);
			
			
			List<LogisticsOrderModel> loList = this.dao.createCommonQuery(LogisticsOrderModel.class)
					.addCondition(Condition.eq(LogisticsOrderModel.FieldNames.orderNo, orderNo))
					.addCondition(Condition.eq(LogisticsOrderModel.FieldNames.transactionType, CommonUtil.TRANSACTIONTYPE_SIN))
					.addCondition(Condition.eq(LogisticsOrderModel.FieldNames.officeCode, officeCode)).query();
			if(loList.size() > 0){
				LogisticsOrderModel loModel = loList.get(0);
				
				//检查是否已收货
				YclRecevingCheckBarcodeQueryCondition condition = new YclRecevingCheckBarcodeQueryCondition(loModel.getLogisticsOrderNo(), barcode, CommonUtil.RECE, officeCode);
				List<YclRecevingCheckBarcodeQueryItem> receItems = dao.query(condition, YclRecevingCheckBarcodeQueryItem.class);
				if(!RcUtil.isEmpty(receItems) && receItems.size() > 0){
					result.put("rece", "rece");
				}	
				
				//检查条码是否被扫描过
				List<LogisticsOrderDetailModel> lodList = this.dao.createCommonQuery(LogisticsOrderDetailModel.class)
						.addCondition(Condition.eq(LogisticsOrderDetailModel.FieldNames.logisticsOrderUuid, loModel.getLogisticsOrderUuid()))
						.addCondition(Condition.eq(LogisticsOrderDetailModel.FieldNames.barcode, barcode))
						.addCondition(Condition.eq(LogisticsOrderDetailModel.FieldNames.transactionType, CommonUtil.TRANSACTIONTYPE_SIN))
						.addCondition(Condition.eq(LogisticsOrderDetailModel.FieldNames.officeCode, officeCode)).query();
				if(lodList.size() > 0){
					result.put("qty", lodList.get(0).getQty());
					List<LogisticsOrderLogModel> logList = this.dao.createCommonQuery(LogisticsOrderLogModel.class)
							.addCondition(Condition.eq(LogisticsOrderLogModel.FieldNames.logisticsOrderUuid, loModel.getLogisticsOrderUuid()))
							.addCondition(Condition.eq(LogisticsOrderLogModel.FieldNames.officeCode, officeCode))
							.addCondition(Condition.eq(LogisticsOrderLogModel.FieldNames.remark, barcode))
							.setOrderBy("workDate desc").query();
					if(!RcUtil.isEmpty(logList) && logList.size() > 0){
						LogisticsOrderLogModel lolModel = logList.get(0);
						if("YCL_PASS_BARCODE".equals(lolModel.getTransactionType())){
							result.put("status", "pass");
						}else{
							result.put("status", "fail");
						}
					}
				} else{
					spj.setResult(false);
					spj.setError("找不到该条码的入库记录!");
				}
			} else {
				spj.setResult(false);
				spj.setError("找不到采购单号"+orderNo+"");
			}
		} else {
			spj.setResult(false);
			spj.setError("条码长度不足!");
		}
		spj.setObject(result);
		return spj;
	}
	
	
	@Override
	public SinotransPageJson receivingCheckBarcode(String logisticsOrderNo, String barcode, String officeCode) {
		SinotransPageJson spj = new SinotransPageJson(true);
		if(RcUtil.isEmpty(barcode)){
			spj.setResult(false);
			spj.setError("条码不能为空!");
			return spj;
		}
		if(RcUtil.isEmpty(officeCode)) {
			spj.setResult(false);
			spj.setError("officeCode不能为空!");
			return spj;
		}
		if(barcode.length() > 23){
			String itemCode = barcode.substring(0,5); //物料编码
			String orderNo = barcode.substring(7,21);
			Map<String, Object> resultMap = new HashMap<String, Object>();
			resultMap.put("orderNo", orderNo);
			
			LogisticsOrderModel loModel = new  LogisticsOrderModel();
			List<LogisticsOrderModel> loModelList = this.dao.createCommonQuery(LogisticsOrderModel.class)
					.addCondition(Condition.eq(LogisticsOrderModel.FieldNames.transactionType, CommonUtil.TRANSACTIONTYPE_SIN))
					.addCondition(Condition.eq(LogisticsOrderModel.FieldNames.officeCode, officeCode))
					.addCondition(Condition.eq(LogisticsOrderModel.FieldNames.transactionStatus, CommonUtil.Active))
					.addCondition(Condition.eq(LogisticsOrderModel.FieldNames.orderNo, orderNo)).query();
			if(!RcUtil.isEmpty(loModelList) && loModelList.size() > 0){
				loModel = loModelList.get(0);
			} else {
				spj.setResult(false);
				spj.setError("订单号无效!" + orderNo);
				return spj;
			}
			
			
			YclCheckBarcodeQueryCondition condition = new YclCheckBarcodeQueryCondition(loModel.getLogisticsOrderNo(), barcode, CommonUtil.Active, CommonUtil.TRANSACTIONTYPE_SIN, CommonUtil.RECE, officeCode);
			List<YclCheckBarcodeQueryItem> item = dao.query(condition, YclCheckBarcodeQueryItem.class);
			if(!RcUtil.isEmpty(item) && item.size() > 0){
				YclCheckBarcodeQueryItem barcodeItem = item.get(0);
				resultMap.put("itemCode", barcodeItem.getItemCode());
				resultMap.put("barcodeQty", barcodeItem.getBarcodeQty());
				resultMap.put("lodQty", barcodeItem.getQty());
				resultMap.put("ltQty", null);
				resultMap.put("lotCode", null);
				//根据物料储位返回货位
				List<ItemMasterModel> imList = this.dao.createCommonQuery(ItemMasterModel.class)
						.addCondition(Condition.eq(ItemMasterModel.FieldNames.itemCode, itemCode))
						.addCondition(Condition.eq(ItemMasterModel.FieldNames.officeCode, officeCode))
						.addCondition(Condition.eq(ItemMasterModel.FieldNames.status, CommonUtil.Active)).query();
				if(!RcUtil.isEmpty(imList) && imList.size() > 0){
					List<ItemMasterLocTypeModel> imlList = this.dao.createCommonQuery(ItemMasterLocTypeModel.class)
							.addCondition(Condition.eq(ItemMasterLocTypeModel.FieldNames.itemMasterUuid, imList.get(0).getItemMasterUuid()))
							.addCondition(Condition.eq(ItemMasterLocTypeModel.FieldNames.officeCode, officeCode)).query();
					if(!RcUtil.isEmpty(imlList) && imlList.size() > 0){
						resultMap.put("lotCode", imlList.get(0).getLocTypeName());
					}
				}
				
				resultMap.put("goodsNature", barcodeItem.getGoodsNature());
				if(!RcUtil.isEmpty(barcodeItem.getLocTaskUuid())) {
					//已收货
					resultMap.put("lTuuid", CommonUtil.Active);
					resultMap.put("ltQty", barcodeItem.getLtQty());
					resultMap.put("lotCode", barcodeItem.getLtTargetLotCode());
					resultMap.put("goodsNature", barcodeItem.getLtGoodsNature());
				}
				spj.setObject(resultMap);
			} else{
				//新条码, 没有和LOD关联的
				BarcodeModel bModel = new BarcodeModel();
				List<BarcodeModel> bList = this.dao.createCommonQuery(BarcodeModel.class)
						.addCondition(Condition.eq(BarcodeModel.FieldNames.officeCode, officeCode))
						.addCondition(Condition.eq(BarcodeModel.FieldNames.barcode, barcode.trim())).query();
				if(bList.size() < 1){
					List<LogisticsOrderDetailModel> lodList = this.dao.createCommonQuery(LogisticsOrderDetailModel.class)
							.addCondition(Condition.eq(LogisticsOrderDetailModel.FieldNames.logisticsOrderUuid, loModel.getLogisticsOrderUuid()))
							.addCondition(Condition.eq(LogisticsOrderDetailModel.FieldNames.itemCode, itemCode))
							.addCondition(Condition.eq(LogisticsOrderDetailModel.FieldNames.transactionStatus, CommonUtil.Active))
							.addCondition(Condition.eq(LogisticsOrderDetailModel.FieldNames.transactionType, CommonUtil.TRANSACTIONTYPE_SIN))
							.addCondition(Condition.eq(LogisticsOrderDetailModel.FieldNames.officeCode, officeCode)).query();
					if(lodList.size() > 1){
						throw new ApplicationException("空条码,对应入库单明细只能有一条!");
					}else if(lodList.size() == 0){
						throw new ApplicationException("空条码,找不到对应物料编码"+itemCode+"的入库单明细!");
					}
					LogisticsOrderDetailModel lodModel = lodList.get(0);
					bModel.setLogisticsOrderDetailUuid(lodModel.getLogisticsOrderDetailUuid());
					bModel.setLogisticsOrderNo(loModel.getLogisticsOrderNo());
					bModel.setBarcode(barcode);
					bModel.setControlWord(CommonUtil.Default_Control_Word);
					bModel.setCreatorDate(dao.getSysDate());
					bModel.setOfficeCode(officeCode);
					bModel.setStatus(CommonUtil.Active);
					bModel.setRemark("RF收货生成新条码");
					bModel.setRowState(CommonUtil.ROW_STATE_ADDED);
					dao.save(bModel);
					spj = receivingCheckBarcode(loModel.getLogisticsOrderNo(), barcode, officeCode);
				} else {
					bModel = bList.get(0);

					List<LogisticsOrderDetailModel> lodList = this.dao.createCommonQuery(LogisticsOrderDetailModel.class)
							.addCondition(Condition.eq(LogisticsOrderDetailModel.FieldNames.logisticsOrderUuid, loModel.getLogisticsOrderUuid()))
							.addCondition(Condition.eq(LogisticsOrderDetailModel.FieldNames.itemCode, itemCode))
							.addCondition(Condition.eq(LogisticsOrderDetailModel.FieldNames.transactionStatus, CommonUtil.Active))
							.addCondition(Condition.eq(LogisticsOrderDetailModel.FieldNames.transactionType, CommonUtil.TRANSACTIONTYPE_SIN))
							.addCondition(Condition.eq(LogisticsOrderDetailModel.FieldNames.officeCode, officeCode)).query();
					if(lodList.size() > 1){
						throw new ApplicationException("空条码,对应的物料编码"+itemCode+"明细只能有一条!");
					}else if(lodList.size() == 0){
						throw new ApplicationException("空条码,找不到对应物料编码"+itemCode+"的入库单明细!");
					}
					LogisticsOrderDetailModel lodModel = lodList.get(0);
					bModel.setLogisticsOrderDetailUuid(lodModel.getLogisticsOrderDetailUuid());
					bModel.setLogisticsOrderNo(loModel.getLogisticsOrderNo());
					bModel.setBarcode(barcode);
					bModel.setControlWord(CommonUtil.Default_Control_Word);
					bModel.setCreatorDate(dao.getSysDate());
					bModel.setOfficeCode(officeCode);
					bModel.setStatus(CommonUtil.Active);
					bModel.setRemark("RF收货生成新条码");
					bModel.setRowState(CommonUtil.ROW_STATE_ADDED);
					dao.save(bModel);
					spj = receivingCheckBarcode(loModel.getLogisticsOrderNo(), barcode, officeCode);
					
				}
			}
			
		} else {
			spj.setResult(false);
			spj.setError("条码长度不足!");
		}
		return spj;
	}

	@Override
	public SinotransPageJson receivingConfirm(String logisticsOrderNo, String packageNo, String barcode, String qtyStr, String lotCode, String goodsNature, String officeCode) throws Exception {
		SinotransPageJson spj = new SinotransPageJson(true);
		logisticsOrderNo=barcode.substring(7,21);
		if(RcUtil.isEmpty(logisticsOrderNo)){
			spj.setResult(false);
			spj.setError("入库单编号不能为空!");
			return spj;
		}
		if(RcUtil.isEmpty(barcode)){
			spj.setResult(false);
			spj.setError("条码不能为空!");
			return spj;
		}
		if(RcUtil.isEmpty(lotCode)) {
			spj.setResult(false);
			spj.setError("货位不能为空!");
			return spj;
		}
		if(RcUtil.isEmpty(officeCode)) {
			spj.setResult(false);
			spj.setError("officeCode不能为空!");
			return spj;
		}
		if(RcUtil.isEmpty(qtyStr)) {
			spj.setResult(false);
			spj.setError("收货数不能为空!");
			return spj;
		}
		Double qty = Double.valueOf(qtyStr);
		List<RemainSinworkModel> rsList = this.dao.createCommonQuery(RemainSinworkModel.class)
				.addCondition(Condition.eq(RemainSinworkModel.FieldNames.officeCode, officeCode))
				.addCondition(Condition.eq(RemainSinworkModel.FieldNames.barcode, barcode.trim())).query();
		if(!RcUtil.isEmpty(rsList) && rsList.size() > 0){
			throw new ApplicationException("该条码已存在库存中!");
		}
		if(barcode.length() > 23){
			
		}else{
			spj.setResult(false);
			spj.setError("条码长度不足!");
			return spj;
		}
		String itemCode = barcode.substring(0,5);
		LogisticsOrderModel loModel = new LogisticsOrderModel();
		List<LogisticsOrderModel> loModelList = this.dao.createCommonQuery(LogisticsOrderModel.class)
				.addCondition(Condition.eq(LogisticsOrderModel.FieldNames.transactionType, CommonUtil.TRANSACTIONTYPE_SIN))
				.addCondition(Condition.eq(LogisticsOrderModel.FieldNames.officeCode, officeCode))
				.addCondition(Condition.eq(LogisticsOrderModel.FieldNames.transactionStatus, CommonUtil.Active))
				.addCondition(Condition.or(new Condition[] {
						Condition.eq(LogisticsOrderModel.FieldNames.logisticsOrderNo, logisticsOrderNo),
						Condition.eq(LogisticsOrderModel.FieldNames.orderNo, logisticsOrderNo)})).query();
		if(!RcUtil.isEmpty(loModelList) && loModelList.size() > 0){
			loModel = loModelList.get(0);
			String controlWord = loModel.getControlWord();
			if (CommonUtil.CONTROL_WORD_F.equals(controlWord.substring(2, 3))) {
				spj.setError("作业单已完结!");
				spj.setResult(false);
				return spj;
			}
		}
		BarcodeModel bModel = new BarcodeModel();
		List<BarcodeModel> bList = this.dao.createCommonQuery(BarcodeModel.class)
				.addCondition(Condition.eq(BarcodeModel.FieldNames.officeCode, officeCode))
				.addCondition(Condition.eq(BarcodeModel.FieldNames.barcode, barcode.trim())).query();
		if(bList.size() < 1){
			spj.setResult(false);
			spj.setError("先在系统生成该条码!");
			return spj;
		} else {
			bModel = bList.get(0);
			if(!RcUtil.isEmpty(bModel.getLogisticsOrderDetailUuid())){
			} else {
				//作废的条码重新启用
				List<LogisticsOrderDetailModel> lodList = this.dao.createCommonQuery(LogisticsOrderDetailModel.class)
						.addCondition(Condition.eq(LogisticsOrderDetailModel.FieldNames.logisticsOrderNo, loModel.getLogisticsOrderNo()))
						.addCondition(Condition.eq(LogisticsOrderDetailModel.FieldNames.transactionType, CommonUtil.TRANSACTIONTYPE_SIN))
						.addCondition(Condition.eq(LogisticsOrderDetailModel.FieldNames.transactionStatus, CommonUtil.Active))
						.addCondition(Condition.eq(LogisticsOrderDetailModel.FieldNames.itemCode, itemCode))
						.addCondition(Condition.eq(LogisticsOrderDetailModel.FieldNames.officeCode, officeCode)).query();
				if(lodList.size() > 1){
					throw new ApplicationException("空条码,对应的物料编码"+itemCode+"明细只能有一条!");
				}else if(lodList.size() == 0){
					throw new ApplicationException("空条码,找不到对应物料编码"+itemCode+"的入库单明细!");
				}
				LogisticsOrderDetailModel lodModel = lodList.get(0);
				bModel.setLogisticsOrderDetailUuid(lodModel.getLogisticsOrderDetailUuid());
				bModel.setLogisticsOrderNo(loModel.getLogisticsOrderNo());
				bModel.setStatus(CommonUtil.Active);
				dao.save(bModel);
			}
		}
		
		YclCheckBarcodeQueryCondition condition = new YclCheckBarcodeQueryCondition(logisticsOrderNo, barcode, CommonUtil.Active, CommonUtil.TRANSACTIONTYPE_SIN, CommonUtil.RECE, officeCode);
		List<YclCheckBarcodeQueryItem> item = dao.query(condition, YclCheckBarcodeQueryItem.class);
		if(!RcUtil.isEmpty(item) && item.size() > 0){
			YclCheckBarcodeQueryItem barcodeItem = item.get(0);

			LocationTaskModel ltModel = new LocationTaskModel();
			BeanUtils.copyProperties(ltModel, barcodeItem);
			
			BarcodeModel saveBModel = new BarcodeModel();
			List<BarcodeModel> barcodeList = this.dao.createCommonQuery(BarcodeModel.class)
					.addCondition(Condition.eq(BarcodeModel.FieldNames.officeCode, officeCode))
					.addCondition(Condition.eq(BarcodeModel.FieldNames.barcode, barcode)).query();
			if(!RcUtil.isEmpty(barcodeList) && barcodeList.size() > 0){
				saveBModel = barcodeList.get(0);
				if(!RcUtil.isEmpty(saveBModel.getModel())){
					saveBModel.setModel(barcodeItem.getModel());
				}
				if(!RcUtil.isEmpty(saveBModel.getQtyUnitCode())){
					saveBModel.setQtyUnitCode(barcodeItem.getQtyUnitCode());
				}
				if(!RcUtil.isEmpty(saveBModel.getQtyUnitDesc())){
					saveBModel.setQtyUnitDesc(barcodeItem.getQtyUnitDesc());
				}
				if(!RcUtil.isEmpty(saveBModel.getExtBarcode())){
					saveBModel.setExtBarcode(barcodeItem.getExtItemCode());
				}
				if(!RcUtil.isEmpty(saveBModel.getSpec())){
					saveBModel.setSpec(barcodeItem.getSpec());
				}
				if(!RcUtil.isEmpty(saveBModel.getAux5())){
					saveBModel.setAux5(barcodeItem.getAux5());
				}
				if(RcUtil.isEmpty(saveBModel.getQty()) || (!RcUtil.isEmpty(saveBModel.getQty()) && saveBModel.getQty() == 0.0)){
					saveBModel.setQty(qty);
				}
				saveBModel.setRowState(CommonUtil.ROW_STATE_MODIFIED);
				dao.save(saveBModel); //保存条码表
			}
			ltModel.setLogisticsOrderDetailUuid(barcodeItem.getLogisticsOrderDetailUuid());
			ltModel.setInLogisticsOrderDetailUuid(barcodeItem.getLogisticsOrderDetailUuid());
			ltModel.setUnitCode(barcodeItem.getQtyUnitCode());
			ltModel.setUnitDesc(barcodeItem.getQtyUnitDesc());
			ltModel.setLocTaskType(CommonUtil.RECE);
			ltModel.setLocTaskDesc(CommonUtil.MSRECE);
			ltModel.setRemark("巴枪收货");
			ltModel.setBarcode(barcode);
			ltModel.setPackageNo(packageNo);
			ltModel.setQty(qty);
			ltModel.setTargetLotCode(lotCode);
			ltModel.setOfficeCode(officeCode);
			if(!RcUtil.isEmpty(goodsNature) && goodsNature.equals("良品")){
				goodsNature = "Y";
			}else if(!RcUtil.isEmpty(goodsNature) && goodsNature.equals("次品")){
				goodsNature = "N";
			}
			ltModel.setGoodsNature(goodsNature);
			ltModel.setControlWord(CommonUtil.Default_Control_Word);
			
			
			ltModel = commonSaveLocationTask(ltModel);
			
			stockWorkManager.setStockWork(ltModel); 
			spj.setMsg("收货成功!");
			
			//保存该条码对应的货位
			List<ItemMasterModel> imList = this.dao.createCommonQuery(ItemMasterModel.class)
					.addCondition(Condition.eq(ItemMasterModel.FieldNames.itemCode, itemCode))
					.addCondition(Condition.eq(ItemMasterModel.FieldNames.officeCode, officeCode))
					.addCondition(Condition.eq(ItemMasterModel.FieldNames.status, CommonUtil.Active)).query();
			if(!RcUtil.isEmpty(imList) && imList.size() > 0){
				List<ItemMasterLocTypeModel> imlList = this.dao.createCommonQuery(ItemMasterLocTypeModel.class)
						.addCondition(Condition.eq(ItemMasterLocTypeModel.FieldNames.itemMasterUuid, imList.get(0).getItemMasterUuid()))
						.addCondition(Condition.eq(ItemMasterLocTypeModel.FieldNames.officeCode, officeCode)).query();
				if(!RcUtil.isEmpty(imlList) && imlList.size() > 0){
					ItemMasterLocTypeModel lModel = imlList.get(0);
					lModel.setLocTypeCode(lotCode);
					lModel.setLocTypeName(lotCode);
					dao.save(lModel);
				} else {
					ItemMasterLocTypeModel lModel = new ItemMasterLocTypeModel();
					lModel.setItemMasterUuid(imList.get(0).getItemMasterUuid());
					lModel.setLocTypeCode(lotCode);
					lModel.setLocTypeName(lotCode);
					lModel.setOfficeCode(officeCode);
					lModel.setStatus(CommonUtil.Active);
					dao.save(lModel);
				}
			}
			
		} else {
			spj.setResult(false);
			spj.setError("找不到该条码对应的货物明细!");
		}
		
		return spj;
	}
	
	
	// 保存LT公共方法
		@Override
		public LocationTaskModel commonSaveLocationTask(LocationTaskModel ltModel) throws Exception {
			LocationTaskModel saveLt = new LocationTaskModel();
			SessionContextUserEntity scue = SessionContextUserEntity.currentUser();
			BeanUtils.copyProperties(saveLt,ltModel);
			RcUtil.toZero(saveLt);
			Date dateTime = dao.getSysDate();
			saveLt.setLocTaskDate(dateTime);
			//saveLt.setControlWord(CommonUtil.Default_Control_Word);
			//saveLt.setCreateTime(dateTime); //如果设置null值, LT的CreateTime为空
			saveLt.setCreateTime(null);
			saveLt.setCreator(null);
			saveLt.setModifyTime(null);
			saveLt.setModifier(null);
			saveLt.setRecVer(0L);
			saveLt.setLocationTaskUuid("");
			saveLt.setStatus(CommonUtil.Active);
			saveLt.setCreator(scue.getUserId());
			saveLt.setWrhWorker(scue.getUsername());
			String dateString=RcUtil.date2String(new Date(), RcUtil.yyyyMMdd);
			Random random =new Random();
			String logisticsOrderNo="LT"+dateString+random.nextInt(100000);
			String locTaskNo = logisticsOrderNo;
			saveLt.setLocTaskNo(locTaskNo);
			saveLt = dao.save(saveLt);
			return saveLt;
		}

	@Override
	public SinotransPageJson savelogisticAndDetails(LogisticsOrderModel logisticsOrder, List<LogisticsOrderDetailModel> logisticsOrderDetails) throws Exception{
		SinotransPageJson spj = new SinotransPageJson();

		Assert.notNull(logisticsOrder,"请输入作业单头数据!");
		if(StringUtils.isNotEmpty(logisticsOrder.getSubmitOrderUuid()) && CollectionUtils.isNotEmpty(logisticsOrderDetails))
			checkLODetailQtyIsOverSODetailQty(logisticsOrder,logisticsOrderDetails,INTBOUND_TYPE);

		LogisticsOrderModel $logisticsOrder = saveLogisticOrder(logisticsOrder);

		if(StringUtils.isNotEmpty($logisticsOrder.getLogisticsOrderUuid()) && StringUtils.isNotEmpty($logisticsOrder.getLogisticsOrderNo())){
			for(LogisticsOrderDetailModel var : logisticsOrderDetails){
				var.setLogisticsOrderUuid($logisticsOrder.getLogisticsOrderUuid());
				var.setLogisticsOrderNo(logisticsOrder.getLogisticsOrderNo());
				var.setTransactionType(CommonUtil.TRANSACTIONTYPE_SIN);
				var.setTransactionStatus(CommonUtil.Active);
			}
			this.dao.saveAll(logisticsOrderDetails);
		}
		spj.setMsg("保存成功!");
		spj.setResult(true);
		return spj;
	}

	private LogisticsOrderModel saveLogisticOrder(LogisticsOrderModel logisticsOrder) {

		logisticsOrder.setOrderDate(dao.getSysDate());
		logisticsOrder.setTransactionType(CommonUtil.TRANSACTIONTYPE_SIN);
		logisticsOrder.setTransactionStatus(CommonUtil.Active);
		String logisticsOrderNo = wmsCommonManager.generateNumberByRule(CommonUtil.SEQUENCE_Inbound, logisticsOrder.getOfficeCode());
		logisticsOrder.setLogisticsOrderNo(logisticsOrderNo);

		return this.dao.save(logisticsOrder);
	}

	private boolean checkLODetailQtyIsOverSODetailQty(LogisticsOrderModel _currOrder,List<LogisticsOrderDetailModel> logisticDetails,int transcationType) throws Exception {

		SubmitOrderModel _submitOrder = submitOrderManager.get(_currOrder.getSubmitOrderUuid());

		List<LogisticsOrderModel> _logisticsOrders = this.dao.createCommonQuery(LogisticsOrderModel.class)
															.addCondition(Condition.eq(LogisticsOrderModel.FieldNames.submitOrderUuid, _currOrder.getSubmitOrderUuid()))
															.query();
		Assert.notEmpty(_logisticsOrders,"暂无可操作数据!");

		List<SubmitOrderDetailModel> _totalSubmitOrderDetails = this.dao.createCommonQuery(SubmitOrderDetailModel.class)
																		.addCondition(Condition.eq(SubmitOrderDetailModel.FieldNames.submitOrderUuid, _submitOrder.getSubmitOrderUuid()))
																		.query();
		Assert.notEmpty(_logisticsOrders,"暂无可操作数据!");


		//取得所有出货单明细集合
		Iterator<LogisticsOrderModel> iterator = _logisticsOrders.iterator();
		List<LogisticsOrderDetailModel> totalLogisticsOrderDetails = new ArrayList<LogisticsOrderDetailModel>();

		while(iterator.hasNext()){
			LogisticsOrderModel var = iterator.next();
			if(var.getLogisticsOrderNo().equals(_currOrder.getLogisticsOrderNo())){
				//使用页面修改或者新增作业明细数据
				totalLogisticsOrderDetails.addAll(logisticDetails);
			}else if(!var.getLogisticsOrderNo().equals(_currOrder.getLogisticsOrderNo())){
				List<LogisticsOrderDetailModel> var2 = this.dao.createCommonQuery(LogisticsOrderDetailModel.class)
						.addCondition(Condition.eq(LogisticsOrderDetailModel.FieldNames.logisticsOrderUuid, var.getLogisticsOrderUuid()))
						.query();
				if(CollectionUtils.isEmpty(var2)) continue;
				totalLogisticsOrderDetails.addAll(var2);
			}
		}

		// 判断出货单或者入货单的货物数量，是否和订单明细总货物数量一致，不一致则不发送
		Double totalLogisticsOrderQty = 0.00;
		Map<String,Object> itemType = new HashMap<String,Object>();
		for(LogisticsOrderDetailModel var3 : totalLogisticsOrderDetails){
			totalLogisticsOrderQty += getDiffQtyByMethodType(transcationType,var3);
			itemType.put(var3.getItemCode(),var3.getItemCode());
		}

		Double totalSubmitDetailQty = 0.00;
		for(SubmitOrderDetailModel var4 : _totalSubmitOrderDetails){
			totalSubmitDetailQty += var4.getQty() == null ? 0.00 : var4.getQty();
		}

		if(totalLogisticsOrderQty != totalSubmitDetailQty){
			log.info("打印信息-----------------------出货单或者入库单的数量和订单数量不一致，不保存数据！！！！");
			throw new ApplicationException("入库单的数量和订单数量不一致，不进行保存数据!");
		}
		return true;
	}

	private Double getDiffQtyByMethodType(int transcationType,LogisticsOrderDetailModel var){
		Double resultQty = 0.00;
		switch (transcationType){
			case INTBOUND_TYPE:
				resultQty = var.getDeliveredQty() == null ? 0.00 : var.getDeliveredQty();
				break;
			case OUTBOUND_TYPE :
				resultQty = var.getConfirmedQty() == null ? 0.00 : var.getConfirmedQty();
				break;
		}
		return resultQty;
	}
 
	
	@Override
	public SinotransPageJson yclOutboundConfirm(String orderNo, String barcode, String qty, String officeCode) throws Exception {
		SinotransPageJson spj = new SinotransPageJson(true, "出库成功!");
		if(RcUtil.isEmpty(orderNo)){
			spj.setResult(false);
			spj.setError("投料单号不能为空!");
			return spj;
		}
		if(RcUtil.isEmpty(officeCode)) {
			spj.setResult(false);
			spj.setError("officeCode不能为空!");
			return spj;
		}
		if(RcUtil.isEmpty(barcode)) {
			spj.setResult(false);
			spj.setError("条码不能为空!");
			return spj;
		}
		if(RcUtil.isEmpty(qty)) {
			spj.setResult(false);
			spj.setError("数量不能为空!");
			return spj;
		}
		
		List<SubmitOrderModel> soList = this.dao.createCommonQuery(SubmitOrderModel.class)
				.addCondition(Condition.eq(SubmitOrderModel.FieldNames.transactionType, CommonUtil.TRANSACTIONTYPE_SOT))
				.addCondition(Condition.eq(SubmitOrderModel.FieldNames.officeCode, officeCode))
				.addCondition(Condition.eq(SubmitOrderModel.FieldNames.orderNo, orderNo)).query();
		if(soList.size() > 0){
			SubmitOrderModel soModel = soList.get(0);

			//检查是否已出库
			YclOutboundLtQueryCondition hasOutboundCondition = new YclOutboundLtQueryCondition();
			hasOutboundCondition.setTaskType(CommonUtil.OUTV);
			hasOutboundCondition.setStatus(CommonUtil.Active);
			hasOutboundCondition.setTransactionType(CommonUtil.TRANSACTIONTYPE_SOT);
			hasOutboundCondition.setOfficeCode(officeCode);
			hasOutboundCondition.setCancelType(CommonUtil.CANV);
			hasOutboundCondition.setBarcode(barcode);
			List<YclOutboundLtQueryItem> hasOutItems = this.dao.query(hasOutboundCondition, YclOutboundLtQueryItem.class);
			if(hasOutItems.size() > 0) {
				if(soModel.getSubmitOrderUuid().equals(hasOutItems.get(0).getSubmitOrderUuid())){
					throw new ApplicationException("该条码已出库!");
				}
			}
			
			List<RemainSinworkModel> rsList = this.dao.createCommonQuery(RemainSinworkModel.class)
					.addCondition(Condition.eq(RemainSinworkModel.FieldNames.officeCode, officeCode))
					.addCondition(Condition.eq(RemainSinworkModel.FieldNames.barcode, barcode)).query();
			if(rsList.size() > 0){
				RemainSinworkModel rsModel = rsList.get(0);
				
				String canPickSql = " RS.REMAIN_QTY > 0 AND SUBSTR(LOD.CONTROL_WORD, 3, 1) <> '" + CommonUtil.CONTROL_WORD_F + "' ";
				String canPickOrderBy = "";
				//if (!RcUtil.isEmpty(soModel.getConfigCode())) {
					//LocPlanConfigModel lpcModel = locPlanConfigManager.findByConfigCode(soModel.getConfigCode(), officeCode);
					//if(!RcUtil.isEmpty(lpcModel.getLocPlanConfigUuid())){
						canPickSql += " AND " + "LOD.ITEM_CODE=RS.ITEM_CODE"
								+ " AND " + "LOD.BARCODE=RS.BARCODE";
						//canPickOrderBy = locPlanConfigManager.getStrategyOrderBySql(lpcModel.getLocPlanConfigUuid());
				//	}
				/*} else {
					throw new ApplicationException("出库策略不能为空!");
				}*/
				
				String itemCode = rsModel.getItemCode();
				String batchNo = rsModel.getBatchNo();
				Double outQty = Double.parseDouble(qty);
				List<SubmitOrderDetailModel> sodList = this.dao.createCommonQuery(SubmitOrderDetailModel.class)
						.addCondition(Condition.eq(SubmitOrderDetailModel.FieldNames.itemCode, itemCode))
						//.addCondition(Condition.eq(SubmitOrderDetailModel.FieldNames.batchNo, batchNo))
						.addCondition(Condition.eq(SubmitOrderDetailModel.FieldNames.submitOrderUuid, soModel.getSubmitOrderUuid()))
						.addCondition(Condition.eq(SubmitOrderDetailModel.FieldNames.officeCode, officeCode)).query();
				if(sodList.size() > 0){
					Double sodQty = 0.0;
					Double lodQty = 0.0;
					for (SubmitOrderDetailModel sodModel : sodList) {
						sodQty += sodModel.getQty();
						List<LogisticsOrderDetailModel> lodList = this.dao.createCommonQuery(LogisticsOrderDetailModel.class)
								.addCondition(Condition.eq(LogisticsOrderDetailModel.FieldNames.transactionStatus, CommonUtil.Active))
								.addCondition(Condition.eq(LogisticsOrderDetailModel.FieldNames.submitOrderDetailUuid, sodModel.getSubmitOrderDetailUuid())).query();
						if(lodList.size() > 0){
							for(LogisticsOrderDetailModel lod:lodList){
								lodQty += lod.getQty();
							}
							
						}
					}
					Double sumLodQty = lodQty + outQty;
					if(sumLodQty > sodQty){
						throw new ApplicationException("出库数不能大于销售单计划数!物料:"+itemCode);
					}
					
					//检查是否有出库单, 没有就新建
					LogisticsOrderModel loModel = new LogisticsOrderModel();
					List<LogisticsOrderModel> loList = this.dao.createCommonQuery(LogisticsOrderModel.class)
							.addCondition(Condition.eq(LogisticsOrderModel.FieldNames.submitOrderUuid, soModel.getSubmitOrderUuid()))
							.addCondition(Condition.eq(LogisticsOrderModel.FieldNames.officeCode, officeCode)).query();
					if(loList.size()>0){
						loModel = loList.get(0);
					}else{
						RcUtil.copyProperties(loModel, soModel);
						loModel.setOfficeCode(officeCode);
						loModel.setOrderDate(dao.getSysDate());
						loModel.setTransactionType(CommonUtil.TRANSACTIONTYPE_SOT);
						loModel.setTransactionStatus(CommonUtil.Active);
						loModel.setOrderNo(soModel.getOrderNo());
						loModel.setContractNo(soModel.getContractNo());
						loModel.setConfigCode(soModel.getConfigCode());
						String logisticsOrderNo = wmsCommonManager.generateNumberByRule(CommonUtil.SEQUENCE_Exit, officeCode);
						loModel.setLogisticsOrderNo(logisticsOrderNo);
						loModel.setOfficeCode(officeCode);
						loModel = dao.save(loModel);
					}
					
					LogisticsOrderDetailModel lodModel = new LogisticsOrderDetailModel();
					RcUtil.copyProperties(lodModel, sodList.get(0));
					lodModel.setLogisticsOrderNo(loModel.getLogisticsOrderNo());
					lodModel.setLogisticsOrderUuid(loModel.getLogisticsOrderUuid());
					lodModel.setSubmitOrderDetailUuid(sodList.get(0).getSubmitOrderDetailUuid());
					lodModel.setTransactionType(CommonUtil.TRANSACTIONTYPE_SOT);
					lodModel.setTransactionStatus(CommonUtil.Active);
					lodModel.setBarcode(barcode);
					lodModel.setOfficeCode(officeCode);
					lodModel.setItemCode(itemCode);
					lodModel.setBatchNo(batchNo);
					lodModel.setQty(outQty);
					//Long seqNoMax = hubScannerManager.commonGetLodSeqNoMaxByLoUuid(loModel.getLogisticsOrderUuid());
					//lodModel.setSeqNo(++seqNoMax);
					
					List<RemainHoldModel> rhList = this.dao.createCommonQuery(RemainHoldModel.class)
							.addCondition(Condition.eq(RemainHoldModel.FieldNames.officeCode, officeCode))
							.addCondition(Condition.eq(RemainHoldModel.FieldNames.inLogisticsOrderDetailUuid, rsModel.getInLogisticsOrderDetailUuid())).query();
					if(rhList.size() > 0){
						lodModel.setRemainHoldUuid(rhList.get(0).getRemainHoldUuid());
						lodModel.setInLogisticsOrderDetailUuid(rhList.get(0).getInLogisticsOrderDetailUuid());
					}
					
					lodModel = dao.save(lodModel);
					
					
					YclCheckHasPickQueryCondition lodPickCondition = new YclCheckHasPickQueryCondition
							(loModel.getLogisticsOrderNo(), barcode, null, CommonUtil.Active, null, null, CommonUtil.PICK, officeCode);
					List<YclCheckHasPickQueryItem> canPickItems = this.dao.query(lodPickCondition, 
							YclCheckHasPickQueryItem.class, canPickSql, null, canPickOrderBy, null);
					if (!RcUtil.isEmpty(canPickItems) && canPickItems.size() > 0) {
						Map<String, Double> hasCompare = new HashMap<String, Double>();
						Double tempQty = 0.0;
						for (YclCheckHasPickQueryItem item : canPickItems) {
							if (RcUtil.isEmpty(hasCompare.get(item.getOutLodUuid()))) {
								Double canQty =item.getCanOutQty();
								tempQty = Double.valueOf(StringUtil.doubleTo0(tempQty) + StringUtil.doubleTo0(canQty));
								tempQty = StringUtil.ObjectToDouble(tempQty, 6);
								hasCompare.put(item.getOutLodUuid(), canQty);
							}
						}
						if (tempQty < outQty) {
							throw new ApplicationException("出库数" + outQty + "大于可出库数:" + tempQty);
						}
						hasCompare = new HashMap<String, Double>();
						for (YclCheckHasPickQueryItem item : canPickItems) {
							if (RcUtil.isEmpty(hasCompare.get(item.getOutLodUuid()))) {
								Double canOutQty = item.getCanOutQty();
								hasCompare.put(item.getOutLodUuid(), item.getCanOutQty());
								
								Double ltQty = 0.0; //写LT拣货数
								
								if (canOutQty <= outQty) {
									ltQty = canOutQty;
									outQty = outQty - canOutQty;
								} else {
									ltQty = outQty;
									outQty = 0.0;
								}
								
								if(canOutQty>0){
								
									LocationTaskModel ltModel = new LocationTaskModel();
									RcUtil.copyProperties(ltModel, item);
									ltModel.setLastLocationTaskUuid(ltModel.getLocationTaskUuid());
									ltModel.setLogisticsOrderDetailUuid(lodModel.getLogisticsOrderDetailUuid());
									ltModel.setInStockWorkUuid(item.getRsInStockWorkUuid());
									ltModel.setInLogisticsOrderDetailUuid(item.getRsInLodUuid());
									ltModel.setQty(ltQty);
									ltModel.setLocTaskType(CommonUtil.OUTV);
									ltModel.setLocTaskDesc("原材料出库");
									ltModel.setRemark("RF原材料出库");
									ltModel.setBarcode(barcode);
									ltModel.setSourceLotCode(item.getLotCode());
									ltModel.setTargetLotCode(item.getLotCode());
									ltModel.setOfficeCode(officeCode);
									ltModel.setControlWord(CommonUtil.Default_Control_Word);
									ltModel = commonSaveLocationTask(ltModel);
									stockWorkManager.setStockWork(ltModel);
									if (outQty.equals(0)) {
										break;
									}
								}

								
							}
						}

						if(outQty>0){
							throw new ApplicationException("出库数量大于条码库存数!");
						}
					} else {
						throw new ApplicationException("根据策略找不到该条码库存信息!");
					}
				}else{
					throw new ApplicationException("找不到投料单明细信息!物料:"+itemCode);
				}
			} else {
				spj.setResult(false);
				spj.setError("找不到该条码的库存信息!");
			}
		}else{
			spj.setResult(false);
			spj.setError("找不到该投料单号!");
		}
		return spj;
	}
	
	@Override
	public SinotransPageJson outboundConfirm(String orderNo, String barcode, String qty, String officeCode) throws Exception {
		SinotransPageJson spj = new SinotransPageJson(true, "出库成功!");
		if(RcUtil.isEmpty(orderNo)){
			spj.setResult(false);
			spj.setError("投料单号不能为空!");
			return spj;
		}
		if(RcUtil.isEmpty(officeCode)) {
			spj.setResult(false);
			spj.setError("officeCode不能为空!");
			return spj;
		}
		if(RcUtil.isEmpty(barcode)) {
			spj.setResult(false);
			spj.setError("条码不能为空!");
			return spj;
		}
		if(RcUtil.isEmpty(qty)) {
			spj.setResult(false);
			spj.setError("数量不能为空!");
			return spj;
		}
		
		List<SubmitOrderModel> soList = this.dao.createCommonQuery(SubmitOrderModel.class)
				.addCondition(Condition.eq(SubmitOrderModel.FieldNames.transactionType, CommonUtil.TRANSACTIONTYPE_SOT))
				.addCondition(Condition.eq(SubmitOrderModel.FieldNames.officeCode, officeCode))
				.addCondition(Condition.eq(SubmitOrderModel.FieldNames.orderNo, orderNo)).query();
		if(soList.size() > 0){
			SubmitOrderModel soModel = soList.get(0);

			//检查是否已出库
			YclOutboundLtQueryCondition hasOutboundCondition = new YclOutboundLtQueryCondition();
			hasOutboundCondition.setTaskType(CommonUtil.OUTV);
			hasOutboundCondition.setStatus(CommonUtil.Active);
			hasOutboundCondition.setTransactionType(CommonUtil.TRANSACTIONTYPE_SOT);
			hasOutboundCondition.setOfficeCode(officeCode);
			hasOutboundCondition.setCancelType(CommonUtil.CANV);
			hasOutboundCondition.setBarcode(barcode);
			List<YclOutboundLtQueryItem> hasOutItems = this.dao.query(hasOutboundCondition, YclOutboundLtQueryItem.class);
			if(hasOutItems.size() > 0) {
				if(soModel.getSubmitOrderUuid().equals(hasOutItems.get(0).getSubmitOrderUuid())){
					throw new ApplicationException("该条码已出库!");
				}
			}
			
			List<RemainSinworkModel> rsList = this.dao.createCommonQuery(RemainSinworkModel.class)
					.addCondition(Condition.eq(RemainSinworkModel.FieldNames.officeCode, officeCode))
					.addCondition(Condition.eq(RemainSinworkModel.FieldNames.barcode, barcode)).query();
			if(rsList.size() > 0){
				RemainSinworkModel rsModel = rsList.get(0);
				
				String canPickSql = " RS.REMAIN_QTY > 0 AND SUBSTR(LOD.CONTROL_WORD, 3, 1) <> '" + CommonUtil.CONTROL_WORD_F + "' ";
				String canPickOrderBy = "";
				//if (!RcUtil.isEmpty(soModel.getConfigCode())) {
					//LocPlanConfigModel lpcModel = locPlanConfigManager.findByConfigCode(soModel.getConfigCode(), officeCode);
					//if(!RcUtil.isEmpty(lpcModel.getLocPlanConfigUuid())){
						canPickSql += " AND " + "LOD.ITEM_CODE=RS.ITEM_CODE"
								+ " AND " + "LOD.BARCODE=RS.BARCODE";
						//canPickOrderBy = locPlanConfigManager.getStrategyOrderBySql(lpcModel.getLocPlanConfigUuid());
				//	}
				/*} else {
					throw new ApplicationException("出库策略不能为空!");
				}*/
				
				String itemCode = rsModel.getItemCode();
				String batchNo = rsModel.getBatchNo();
				Double outQty = Double.parseDouble(qty);
				List<SubmitOrderDetailModel> sodList = this.dao.createCommonQuery(SubmitOrderDetailModel.class)
						.addCondition(Condition.eq(SubmitOrderDetailModel.FieldNames.itemCode, itemCode))
						//.addCondition(Condition.eq(SubmitOrderDetailModel.FieldNames.batchNo, batchNo))
						.addCondition(Condition.eq(SubmitOrderDetailModel.FieldNames.submitOrderUuid, soModel.getSubmitOrderUuid()))
						.addCondition(Condition.eq(SubmitOrderDetailModel.FieldNames.officeCode, officeCode)).query();
				if(sodList.size() > 0){
					Double sodQty = 0.0;
					Double lodQty = 0.0;
					for (SubmitOrderDetailModel sodModel : sodList) {
						sodQty += sodModel.getQty();
						List<LogisticsOrderDetailModel> lodList = this.dao.createCommonQuery(LogisticsOrderDetailModel.class)
								.addCondition(Condition.eq(LogisticsOrderDetailModel.FieldNames.transactionStatus, CommonUtil.Active))
								.addCondition(Condition.eq(LogisticsOrderDetailModel.FieldNames.submitOrderDetailUuid, sodModel.getSubmitOrderDetailUuid())).query();
						if(lodList.size() > 0){
							for(LogisticsOrderDetailModel lod:lodList){
								lodQty += lod.getQty();
							}
							
						}
					}
					Double sumLodQty = lodQty + outQty;
					if(sumLodQty > sodQty){
						throw new ApplicationException("出库数不能大于销售单计划数!物料:"+itemCode);
					}
					
					//检查是否有出库单, 没有就新建
					LogisticsOrderModel loModel = new LogisticsOrderModel();
					List<LogisticsOrderModel> loList = this.dao.createCommonQuery(LogisticsOrderModel.class)
							.addCondition(Condition.eq(LogisticsOrderModel.FieldNames.submitOrderUuid, soModel.getSubmitOrderUuid()))
							.addCondition(Condition.eq(LogisticsOrderModel.FieldNames.officeCode, officeCode)).query();
					if(loList.size()>0){
						loModel = loList.get(0);
					}else{
						RcUtil.copyProperties(loModel, soModel);
						loModel.setOfficeCode(officeCode);
						loModel.setOrderDate(dao.getSysDate());
						loModel.setTransactionType(CommonUtil.TRANSACTIONTYPE_SOT);
						loModel.setTransactionStatus(CommonUtil.Active);
						loModel.setOrderNo(soModel.getOrderNo());
						loModel.setContractNo(soModel.getContractNo());
						loModel.setConfigCode(soModel.getConfigCode());
						String logisticsOrderNo = wmsCommonManager.generateNumberByRule(CommonUtil.SEQUENCE_Exit, officeCode);
						loModel.setLogisticsOrderNo(logisticsOrderNo);
						loModel.setOfficeCode(officeCode);
						loModel = dao.save(loModel);
					}
					
					LogisticsOrderDetailModel lodModel = new LogisticsOrderDetailModel();
					RcUtil.copyProperties(lodModel, sodList.get(0));
					lodModel.setLogisticsOrderNo(loModel.getLogisticsOrderNo());
					lodModel.setLogisticsOrderUuid(loModel.getLogisticsOrderUuid());
					lodModel.setSubmitOrderDetailUuid(sodList.get(0).getSubmitOrderDetailUuid());
					lodModel.setTransactionType(CommonUtil.TRANSACTIONTYPE_SOT);
					lodModel.setTransactionStatus(CommonUtil.Active);
					lodModel.setBarcode(barcode);
					lodModel.setOfficeCode(officeCode);
					lodModel.setItemCode(itemCode);
					lodModel.setBatchNo(batchNo);
					lodModel.setQty(outQty);
					//Long seqNoMax = hubScannerManager.commonGetLodSeqNoMaxByLoUuid(loModel.getLogisticsOrderUuid());
					//lodModel.setSeqNo(++seqNoMax);
					
					List<RemainHoldModel> rhList = this.dao.createCommonQuery(RemainHoldModel.class)
							.addCondition(Condition.eq(RemainHoldModel.FieldNames.officeCode, officeCode))
							.addCondition(Condition.eq(RemainHoldModel.FieldNames.inLogisticsOrderDetailUuid, rsModel.getInLogisticsOrderDetailUuid())).query();
					if(rhList.size() > 0){
						lodModel.setRemainHoldUuid(rhList.get(0).getRemainHoldUuid());
						lodModel.setInLogisticsOrderDetailUuid(rhList.get(0).getInLogisticsOrderDetailUuid());
					}
					
					lodModel = dao.save(lodModel);
					
					
					YclCheckHasPickQueryCondition lodPickCondition = new YclCheckHasPickQueryCondition
							(loModel.getLogisticsOrderNo(), barcode, null, CommonUtil.Active, null, null, CommonUtil.PICK, officeCode);
					List<YclCheckHasPickQueryItem> canPickItems = this.dao.query(lodPickCondition, 
							YclCheckHasPickQueryItem.class, canPickSql, null, canPickOrderBy, null);
					if (!RcUtil.isEmpty(canPickItems) && canPickItems.size() > 0) {
						for (YclCheckHasPickQueryItem item : canPickItems) {
							LocationTaskModel ltModel = new LocationTaskModel();
							RcUtil.copyProperties(ltModel, item);
							ltModel.setLastLocationTaskUuid(ltModel.getLocationTaskUuid());
							ltModel.setLogisticsOrderDetailUuid(lodModel.getLogisticsOrderDetailUuid());
							ltModel.setInStockWorkUuid(item.getRsInStockWorkUuid());
							ltModel.setInLogisticsOrderDetailUuid(item.getRsInLodUuid());
							
							Double ltQty = 0.0;
							
							if (item.getRemainQty() <= outQty) {
								ltQty = item.getRemainQty();
								outQty = outQty - item.getRemainQty();
							} else {
								ltQty = outQty;
								outQty = 0.0;
							}
							
							ltModel.setQty(ltQty);
							ltModel.setLocTaskType(CommonUtil.OUTV);
							ltModel.setLocTaskDesc("原材料出库");
							ltModel.setRemark("RF原材料出库");
							ltModel.setBarcode(barcode);
							ltModel.setSourceLotCode(item.getLotCode());
							ltModel.setTargetLotCode(item.getLotCode());
							ltModel.setOfficeCode(officeCode);
							ltModel.setControlWord(CommonUtil.Default_Control_Word);
							ltModel = commonSaveLocationTask(ltModel);
							stockWorkManager.setStockWork(ltModel);
							
							if (outQty.equals(0)) {
								break;
							}
						}
						if(outQty>0){
							throw new ApplicationException("出库数量大于条码库存数!");
						}
					} else {
						throw new ApplicationException("根据策略找不到该条码库存信息!");
					}
				}else{
					throw new ApplicationException("找不到投料单明细信息!物料:"+itemCode);
				}
			} else {
				spj.setResult(false);
				spj.setError("找不到该条码的库存信息!");
			}
		}else{
			spj.setResult(false);
			spj.setError("找不到该投料单号!");
		}
		return spj;
	}
	
}