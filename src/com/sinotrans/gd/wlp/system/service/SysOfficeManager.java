package com.sinotrans.gd.wlp.system.service;

import java.util.Collection;
import java.util.List;

import com.sinotrans.framework.core.service.BaseManager;
import com.sinotrans.gd.wlp.common.entity.BasOption;
import com.sinotrans.gd.wlp.system.entity.OfficeTree;
import com.sinotrans.gd.wlp.system.model.SysOfficeModel;
import com.sinotrans.gd.wlp.util.OfficeCodeTypeEnum;

public interface SysOfficeManager extends BaseManager {

	SysOfficeModel get(String id);

	List<SysOfficeModel> getAll();

	List<SysOfficeModel> findByExample(SysOfficeModel example);

	SysOfficeModel save(SysOfficeModel model);

	List<SysOfficeModel> saveAll(Collection<SysOfficeModel> models);

	void remove(SysOfficeModel model);

	void removeAll(Collection<SysOfficeModel> models);

	void removeByPk(String id);

	void removeAllByPk(Collection<String> ids);

	List<BasOption> getOption(String code, String officeCode, String language);

	List<OfficeTree> getOfficetree();

	List<OfficeTree> getOfficetree(String officeCode);

	List<OfficeTree> getOfficeCode();
	
	List<OfficeTree> getOfficeCodetree();
	/**
	 * 用于在编辑页面验证组织机构代码是否重复
	 * 
	 * @param officeCode
	 * @return 返回 true 为已存在
	 */
	boolean getYanZhenUserCode(String officeCode);

	/**
	 * 保存组织机构
	 */
	String savaOfficeModel(String Officeitemjson);

	/**
	 * 根据OfficeCode查询该组织机构的UUID
	 * 
	 * @param officeCode
	 * @return
	 */
	String getOfficeOofficeCodeModel(String officeCode);

	/**
	 * 根据OfficeCode查询该组织机构的UUID
	 * 
	 * @param officeCode
	 * @return
	 */
	String getOfficeCodereturnCodename(String officeCode);

	/**
	 * 提供用户管理页面列表使用的组织机构名称显示
	 * 
	 * @param officeCode
	 * @param status
	 * @return
	 */
	List<SysOfficeModel> getOfficeUserList(String officeCode, String status);

	List<OfficeTree> getUserOfficetree(String officeCode);
	
	/**
	 * 根据当前 office code 找父节点的 office code
	 * @param officeCode
	 * @return
	 */
	String getPreOfficeCode(String officeCode);
	
	/**
	 * 根据Enum值，判断当前用户是否符合对应Enum所代表的Office code type
	 * @param octe
	 * @return
	 */
	public boolean isMatchOfficeCodeType(OfficeCodeTypeEnum octe)throws Exception;
	
	/**
	 * 根据enumVlue，判断当前用户是否符合对应enumVlue所代表的Office code type
	 * @param enumVlue
	 * @return
	 */
	public boolean isMatchOfficeCodeTypeByString(String enumVlue)throws Exception;
}
