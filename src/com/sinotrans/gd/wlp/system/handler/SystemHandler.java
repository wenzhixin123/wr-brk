/**
 * 
 */
package com.sinotrans.gd.wlp.system.handler;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import com.sinotrans.framework.core.exception.ApplicationException;
import com.sinotrans.framework.core.util.ContextUtils;
import com.sinotrans.gd.wlp.basicdata.model.BasCodeDefModel;
import com.sinotrans.gd.wlp.basicdata.model.BasCodeTypeModel;
import com.sinotrans.gd.wlp.basicdata.service.BasAccreditBoxManager;
import com.sinotrans.gd.wlp.basicdata.service.BasCodeDefManager;
import com.sinotrans.gd.wlp.basicdata.service.BasCodeTypeManager;
import com.sinotrans.gd.wlp.common.entity.BasOption;
import com.sinotrans.gd.wlp.common.web.GeneralAjaxHandler;
import com.sinotrans.gd.wlp.common.web.RcUtil;
import com.sinotrans.gd.wlp.common.web.SinotransDataGrid;
import com.sinotrans.gd.wlp.common.web.SinotransPageJson;
import com.sinotrans.gd.wlp.system.entity.ModuleGroupMenuItemTrue;
import com.sinotrans.gd.wlp.system.entity.OfficeTree;
import com.sinotrans.gd.wlp.system.entity.PrintReportTemplateEntity;
import com.sinotrans.gd.wlp.system.model.PrintReportTemplateModel;
import com.sinotrans.gd.wlp.system.model.SysMenuGroupModel;
import com.sinotrans.gd.wlp.system.model.SysMenuItemModel;
import com.sinotrans.gd.wlp.system.model.SysOfficeModel;
import com.sinotrans.gd.wlp.system.model.SysRoleMenuButtonModel;
import com.sinotrans.gd.wlp.system.model.SysRoleMenuItemModel;
import com.sinotrans.gd.wlp.system.model.SysRoleModel;
import com.sinotrans.gd.wlp.system.model.SysRoleUserModel;
import com.sinotrans.gd.wlp.system.model.SysUserModel;
import com.sinotrans.gd.wlp.system.model.SysViewButtonModel;
import com.sinotrans.gd.wlp.system.service.PrintReportTemplateManager;
import com.sinotrans.gd.wlp.system.service.SysMenuGroupManager;
import com.sinotrans.gd.wlp.system.service.SysMenuItemManager;
import com.sinotrans.gd.wlp.system.service.SysNewsManager;
import com.sinotrans.gd.wlp.system.service.SysOfficeManager;
import com.sinotrans.gd.wlp.system.service.SysRoleManager;
import com.sinotrans.gd.wlp.system.service.SysRoleMenuButtonManager;
import com.sinotrans.gd.wlp.system.service.SysRoleMenuItemManager;
import com.sinotrans.gd.wlp.system.service.SysRoleUserManager;
import com.sinotrans.gd.wlp.system.service.SysUserManager;
import com.sinotrans.gd.wlp.system.service.SysViewButtonManager;
import com.sinotrans.gd.wlp.util.CommonUtil;
import com.sinotrans.gd.wlp.util.JsonUtil;

public class SystemHandler extends GeneralAjaxHandler {
	private SysOfficeManager sysOfficeManager = ContextUtils
			.getBeanOfType(SysOfficeManager.class);
	private SysViewButtonManager sysViewButtonManager = ContextUtils
			.getBeanOfType(SysViewButtonManager.class);
	private SysNewsManager sysNewsManager = ContextUtils
			.getBeanOfType(SysNewsManager.class);
	
	
	
	private BasCodeTypeManager basCodeTypeManager = ContextUtils
			.getBeanOfType(BasCodeTypeManager.class);
	
	private BasCodeDefManager basCodeDefManager = ContextUtils
			.getBeanOfType(BasCodeDefManager.class);

	/**
	 * 查询单个用户信息
	 * 
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public String selectUserid(String params) throws Exception {
		String selectuserid = request.getParameter("selectuserid");
		SysUserModel sUserm = ContextUtils.getBeanOfType(SysUserManager.class)
				.get(selectuserid);
		String selectUid = JsonUtil.beanToJson(sUserm);
		return selectUid;
	}

	/**
	 * 查询所有用户信息--列表
	 * 
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public String selectUser(String params) throws Exception {
		List<SysUserModel> sUserm = ContextUtils.getBeanOfType(
				SysUserManager.class).getAll();
		String selectUser = JsonUtil.list2Json(sUserm);
		return selectUser;
	}

	/**
	 * 查询所有用户信息--树形（uuid、name、url、list 四个属性）
	 * 
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public String selectUserItemAll(String params) throws Exception {
		List<ModuleGroupMenuItemTrue> mmdgmitree = ContextUtils.getBeanOfType(
				SysUserManager.class).seleuserall();
		String svbuttonall = JsonUtil.list2Json(mmdgmitree);
		return svbuttonall;
	}

	/**
	 * 根据部门uuid查询用户信息--树形(暂时无用)
	 * 
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public String selectEQoffice(String params) throws Exception {
		String officeuuid = request.getParameter("selectEQoffice");
		List<ModuleGroupMenuItemTrue> mmdgmitree = ContextUtils.getBeanOfType(
				SysUserManager.class).seleuserEQoffice(officeuuid);
		String svbuttonall = JsonUtil.list2Json(mmdgmitree);
		return svbuttonall;
	}

	/**
	 * 增添新用户
	 * 
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public String savaUser(String params) throws Exception {
		String userjson = request.getParameter("userjson");
		ContextUtils.getBeanOfType(SysUserManager.class).savaUser(userjson);
		return null;
	}
	/*
	 * 重置密码
	 * 
	 */
	public String resetpassword(String params)throws Exception{
		String usersid=request.getParameter("usersid");
		SinotransPageJson sinotransPageJson= ContextUtils.getBeanOfType(SysUserManager.class).resetpassword(usersid);
		return JsonUtil.beanToJson(sinotransPageJson);
	}

	/**
	 * 修改用户信息
	 * 
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public String updateUser(String params) throws Exception {
		String userjson = request.getParameter("userjson");
		ContextUtils.getBeanOfType(SysUserManager.class).updateUser(userjson,
				getUser().getUserId());
		return null;
	}

	/**
	 * 删除用户
	 * 
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public String deleteUser(String params) throws Exception {
		String deleteuser = request.getParameter("deleteuser");
		// SysUserModel sUserm = new SysUserModel();
		// sUserm.setRowState(BaseModel.ROW_STATE_DELETED);
		// sUserm.setUserUuid(deleteuser);
		try {
			ContextUtils.getBeanOfType(SysUserManager.class).removeByPk(
					deleteuser);
		} catch (RuntimeException e) {
			throw new ApplicationException("找到子记录、分配的权限未解除！");
		}
		return JsonUtil.beanToJson(new SinotransPageJson(true, "删除成功!"));
	}

	/**
	 * 查询单个菜单项
	 * 
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public String selectMenuitemid(String params) throws Exception {
		String selectmenuitemid = request.getParameter("selectmenuitemid");
		SysMenuItemModel sMenum = ContextUtils.getBeanOfType(
				SysMenuItemManager.class).get(selectmenuitemid);
		String selectUid = JsonUtil.beanToJson(sMenum);
		return selectUid;
	}

	/**
	 * 查询全部角色信息--树形
	 * 
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public String selectroleAlltree(String params) throws Exception {
		List<ModuleGroupMenuItemTrue> srolem = ContextUtils.getBeanOfType(
				SysRoleManager.class).selectroleall();
		String selectUid = JsonUtil.beanToJson(srolem);
		return selectUid;
	}

	/**
	 * 根据角色UUID查询出所有菜单信息
	 * 
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public String selectMenuitemall(String params) throws Exception {
		String selectroleid = request.getParameter("selectroleid");
		List<SysRoleMenuItemModel> sMenum = ContextUtils.getBeanOfType(
				SysRoleMenuItemManager.class).getMenuitemall(selectroleid);
		return JsonUtil.list2Json(sMenum);
	}

	/**
	 * 根据菜单UUID查询出所属按钮权限信息
	 * 
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public String selectMenuButtonall(String params) throws Exception {
		String roleuuid = request.getParameter("roleuuid");
		String menuuuid = request.getParameter("menuuuid");
		List<SysRoleMenuButtonModel> sMenum = ContextUtils.getBeanOfType(
				SysRoleMenuButtonManager.class).selectMenuButtonall(roleuuid,
				menuuuid);
		return JsonUtil.list2Json(sMenum);
	}

	/**
	 * 根据用户UUID查询 用户与角色表 内输入该用户id的信息
	 * 
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public String selectuserroleall(String params) throws Exception {
		String selectroleid = request.getParameter("selectroleid");
		List<SysRoleUserModel> sMenum = ContextUtils.getBeanOfType(
				SysRoleUserManager.class).selectuserroleall(selectroleid);
		return JsonUtil.list2Json(sMenum);
	}

	/**
	 * 新增菜单项
	 * 
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public String savaMenuitem(String params) throws Exception {
		String menuitemjson = request.getParameter("menuitemjson");
		ContextUtils.getBeanOfType(SysMenuItemManager.class).saveMenuItemOff(
				menuitemjson, getUser().getOfficeCode());
		return null;
	}

	/**
	 * 修改菜单项
	 * 
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public String updateMenuitem(String params) throws Exception {
		String menuitemjson = request.getParameter("menuitemjson");
		ContextUtils.getBeanOfType(SysMenuItemManager.class).updateMenuItemOff(
				menuitemjson, getUser().getOfficeCode());
		return null;
	}

	/**
	 * 删除菜单项
	 * 
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public String deleteMenuitem(String params) throws Exception {
		String deleteMenuitem = request.getParameter("deleteMenuitem");
		ContextUtils.getBeanOfType(SysMenuItemManager.class).removeByPk(
				deleteMenuitem);
		return null;
	}

	/**
	 * 查询单个部门信息
	 * 
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public String selectOfficeitemid(String params) throws Exception {
		String selectOfficeitem = request.getParameter("selectOfficeitem");
		SysOfficeModel sOfficem = ContextUtils.getBeanOfType(
				SysOfficeManager.class).get(selectOfficeitem);
		String selectUid = JsonUtil.beanToJson(sOfficem);
		return selectUid;
	}

	/**
	 * 查询部门树形(进一步修改添加offcode)
	 * 
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public String selectOfficeitemtree(String params) throws Exception {
		SysOfficeManager sysOfficeManager = ContextUtils
				.getBeanOfType(SysOfficeManager.class);
		List<OfficeTree> resultList = sysOfficeManager.getOfficetree();
		return JsonUtil.list2Json(resultList);
	}
	
	/**
	 * 获取外部委托单位的控箱公司
	 * @param params
	 * @return
	 */
	public String selectCntrAdminCode(String params){
		String userComp = request.getParameter("userComp");
		SinotransDataGrid datagrid = ContextUtils.getBeanOfType(BasAccreditBoxManager.class).selectCntrAdminCode(userComp, getPagingInfo());
		return JsonUtil.beanToJson(datagrid);
		
	}
	
	
	/**
	 * 获取外部委托单位授权的作业项目
	 * @param params
	 * @return
	 */
	public String getTransactionType(String params){
		String cntrAdminCode = request.getParameter("cntrAdminCode");
		String agentCode = request.getParameter("agentCode");
		String portAreaCode = request.getParameter("portAreaCode");
		BasAccreditBoxManager basAccreditBoxManager = ContextUtils
			.getBeanOfType(BasAccreditBoxManager.class);
		List<BasCodeDefModel> basCodeDeflList = basAccreditBoxManager.getAccreditBoxModel(cntrAdminCode, agentCode,portAreaCode);
		
		BasCodeTypeModel model = new BasCodeTypeModel();
		model.setTypeCode(CommonUtil.DELIVERY_TYPE);
		model.setStatus(CommonUtil.Active);
		List<BasCodeTypeModel> basCodeList = basCodeTypeManager.findByExample(model);
		
		if(basCodeDeflList != null && basCodeDeflList.size() > 0){
			Object[] obj = new Object[basCodeDeflList.size()];
			for(int i=0;i<basCodeDeflList.size();i++){
				obj[i] = basCodeDeflList.get(i).getRemark();
			}
			if(basCodeList != null && basCodeList.size() > 0){
				BasCodeTypeModel basCodeTypeModel = basCodeList.get(0);
				List<BasCodeDefModel> codeDefModelList = basCodeDefManager.queryCodeValueByCodeType(basCodeTypeModel.getBasCodeTypeUuid(), obj);
				List<BasOption> optionList = new ArrayList<BasOption>();
				for(BasCodeDefModel codeDefModel : codeDefModelList){
					optionList.add(new BasOption(codeDefModel.getCodeValue(),codeDefModel.getDisplayValue(),false));
				}
				if(optionList.size() == 0){
					BasCodeDefModel defModel = new BasCodeDefModel();
					defModel.setBasCodeTypeUuid(basCodeTypeModel.getBasCodeTypeUuid());
					defModel.setStatus(CommonUtil.Active);
					List<BasCodeDefModel> basCodeDefList = basCodeDefManager.findByExample(defModel);
					for(BasCodeDefModel codeDefModel : basCodeDefList){
						optionList.add(new BasOption(codeDefModel.getCodeValue(),codeDefModel.getDisplayValue(),false));
					}
				}
				return JsonUtil.list2Json(optionList);
			}else{
				return null;
			}
		}else{
			BasCodeTypeModel basCodeTypeModel = basCodeList.get(0);
			List<BasOption> optionList = new ArrayList<BasOption>();
			if(optionList.size() == 0){
				BasCodeDefModel defModel = new BasCodeDefModel();
				defModel.setBasCodeTypeUuid(basCodeTypeModel.getBasCodeTypeUuid());
				defModel.setStatus(CommonUtil.Active);
				List<BasCodeDefModel> basCodeDefList = basCodeDefManager.findByExample(defModel);
				for(BasCodeDefModel codeDefModel : basCodeDefList){
					optionList.add(new BasOption(codeDefModel.getCodeValue(),codeDefModel.getDisplayValue(),false));
				}
			}
			return JsonUtil.list2Json(optionList);
		}
	}
	
	/**
	 * 获取外部委托单位授权的业务类型
	 * @param params
	 * @return
	 */
	public String getBusinessType(String params){
		String cntrAdminCode = request.getParameter("cntrAdminCode");
		String agentCode = request.getParameter("agentCode");
		String portAreaCode = request.getParameter("portAreaCode");
		BasAccreditBoxManager basAccreditBoxManager = ContextUtils
			.getBeanOfType(BasAccreditBoxManager.class);
		
		BasCodeTypeModel model = new BasCodeTypeModel();
		model.setTypeCode(CommonUtil.BUSINESS_TYPE);
		model.setStatus(CommonUtil.Active);
		List<BasCodeTypeModel> basCodeList = basCodeTypeManager.findByExample(model);
		
		List<BasCodeDefModel> basCodeDeflList = basAccreditBoxManager.getAccreditBoxModel(cntrAdminCode, agentCode,portAreaCode);
		if(basCodeDeflList != null && basCodeDeflList.size() > 0){
			Object[] obj = new Object[basCodeDeflList.size()];
			for(int i=0;i<basCodeDeflList.size();i++){
				obj[i] = basCodeDeflList.get(i).getDisplayValueEn();
			}
			if(basCodeList != null && basCodeList.size() > 0){
				BasCodeTypeModel basCodeTypeModel = basCodeList.get(0);
				List<BasCodeDefModel> codeDefModelList = basCodeDefManager.queryCodeValueByCodeType(basCodeTypeModel.getBasCodeTypeUuid(), obj);
				List<BasOption> optionList = new ArrayList<BasOption>();
				for(BasCodeDefModel codeDefModel : codeDefModelList){
					optionList.add(new BasOption(codeDefModel.getCodeValue(),codeDefModel.getDisplayValue(),false));
				}
				if(optionList.size() == 0){
					BasCodeDefModel defModel = new BasCodeDefModel();
					defModel.setBasCodeTypeUuid(basCodeTypeModel.getBasCodeTypeUuid());
					defModel.setStatus(CommonUtil.Active);
					List<BasCodeDefModel> basCodeDefList = basCodeDefManager.findByExample(defModel);
					for(BasCodeDefModel codeDefModel : basCodeDefList){
						optionList.add(new BasOption(codeDefModel.getCodeValue(),codeDefModel.getDisplayValue(),false));
					}
				}
				return JsonUtil.list2Json(optionList);
			}else{
				return null;
			}
		}else{
			BasCodeTypeModel basCodeTypeModel = basCodeList.get(0);
			List<BasOption> optionList = new ArrayList<BasOption>();
			if(optionList.size() == 0){
				BasCodeDefModel defModel = new BasCodeDefModel();
				defModel.setBasCodeTypeUuid(basCodeTypeModel.getBasCodeTypeUuid());
				defModel.setStatus(CommonUtil.Active);
				List<BasCodeDefModel> basCodeDefList = basCodeDefManager.findByExample(defModel);
				for(BasCodeDefModel codeDefModel : basCodeDefList){
					optionList.add(new BasOption(codeDefModel.getCodeValue(),codeDefModel.getDisplayValue(),false));
				}
			}
			return JsonUtil.list2Json(optionList);
		}
	}
	/**
	 * 查询用户默认配置码头
	 * @param params
	 * @return
	 */
	public String getPortAreaCode(String params){
		BasCodeTypeModel model = new BasCodeTypeModel();
		model.setTypeCode(CommonUtil.PORT_AREA_CODE);
		model.setStatus(CommonUtil.Active);
		List<BasCodeTypeModel> basCodeList = basCodeTypeManager.findByExample(model);
		if(basCodeList != null && basCodeList.size() > 0){
			BasCodeTypeModel basCodeTypeModel = basCodeList.get(0);
			BasCodeDefModel defModel = new BasCodeDefModel();
			defModel.setBasCodeTypeUuid(basCodeTypeModel.getBasCodeTypeUuid());
			defModel.setStatus(CommonUtil.Active);
			List<BasCodeDefModel> codeDefModelList = basCodeDefManager.findByExample(defModel);
			List<BasOption> optionList = new ArrayList<BasOption>();
			for(BasCodeDefModel codeDefModel : codeDefModelList){
				optionList.add(new BasOption(codeDefModel.getCodeValue(),codeDefModel.getDisplayValue(),false));
			}
			return JsonUtil.list2Json(optionList);
		}else{
			return null;
		}
	}
	/**
	 * 查询部门树形(进一步修改添加offcode)
	 * 
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public String selectOfficeitemtree1(String params) throws Exception {
		SysOfficeManager sysOfficeManager = ContextUtils
				.getBeanOfType(SysOfficeManager.class);
		List<OfficeTree> resultList = null;
		if (CommonUtil.SYSTEM_ADMIN.equals(getUser().getUserId())) {
			resultList = sysOfficeManager.getOfficetree();
		} else {
			// resultList = sysOfficeManager.getOfficetree(getUser()
			// .getOfficeCode());
			resultList = sysOfficeManager.getUserOfficetree(getUser()
					.getOfficeCode());

		}
		return JsonUtil.list2Json(resultList);
	}
	
	/**
	 * 获取KPI目录树
	 * @param params
	 * @return
	 * @throws Exception
	 */
	/*public String kpiMenuTree(String params)throws Exception{
		List<OfficeTree> resultList = null;
		if (CommonUtil.SYSTEM_ADMIN.equals(getUser().getUserId())) {
			resultList = dcsKpiTreeManager.getAllKPItree();
		} else {
			resultList = dcsKpiTreeManager.getKPITree(getUser()
					.getOfficeCode());
		}
		return JsonUtil.list2Json(resultList);
	}*/

	/**
	 * 查询部门OfficeCode(setID为id)
	 * 
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public String selectOfficeCode(String params) throws Exception {
		SysOfficeManager sysOfficeManager = ContextUtils
				.getBeanOfType(SysOfficeManager.class);
		List<OfficeTree> resultList = sysOfficeManager.getOfficeCode();
		return JsonUtil.list2Json(resultList);
	}
	/**
	 * 查询部门OfficeCode(setID为code)
	 * 
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public String selectOfficeCodeTree(String params) throws Exception {
		SysOfficeManager sysOfficeManager = ContextUtils
				.getBeanOfType(SysOfficeManager.class);
		List<OfficeTree> resultList = sysOfficeManager.getOfficeCodetree();
		return JsonUtil.list2Json(resultList);
	}

	/**
	 * 新增部门信息
	 * 
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public String savaOfficeitem(String params) throws Exception {
		String Officeitemjson = request.getParameter("Officeitemjson");
		sysOfficeManager.savaOfficeModel(Officeitemjson);
		return null;
	}

	/**
	 * 删除部门信息
	 * 
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public String deleteOfficeitem(String params) throws Exception {
		String deleteOfficeitem = request.getParameter("deleteOfficeitem");
		// SysOfficeModel sOfficem = new SysOfficeModel();
		// sOfficem.setRowState(BaseModel.ROW_STATE_DELETED);
		// sOfficem.setOfficeUuid(deleteOfficeitem);
		ContextUtils.getBeanOfType(SysOfficeManager.class).removeByPk(
				deleteOfficeitem);
		return null;
	}

	/**
	 * 查询单个角色
	 * 
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public String selectroleitemid(String params) throws Exception {
		String selectroleitem = request.getParameter("selectroleitem");
		SysRoleModel srolem = ContextUtils.getBeanOfType(SysRoleManager.class)
				.get(selectroleitem);
		String selectUid = JsonUtil.beanToJson(srolem);
		return selectUid;
	}

	/**
	 * 新增角色信息
	 * 
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public String savarole(String params) throws Exception {
		String rolejson = request.getParameter("rolejson");
		ContextUtils.getBeanOfType(SysRoleManager.class).saveAddRole(rolejson,
				getUser().getOfficeCode());
		return null;
	}

	/**
	 * 修改角色信息（正在测试是否可以用savarole该方法提交数据）
	 * 
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public String updaterole(String params) throws Exception {
		String rolejson = request.getParameter("rolejson");
		ContextUtils.getBeanOfType(SysRoleManager.class).saveUpdateRole(
				rolejson, getUser().getOfficeCode());
		return null;
	}

	/**
	 * 删除角色信息
	 * 
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public String deleteroleitem(String params) throws Exception {
		String roleitemjson = request.getParameter("roleitemjson");
		ContextUtils.getBeanOfType(SysRoleManager.class).removeByPk(
				roleitemjson);

		return null;
	}

	/**
	 * 查询菜单项UUID和名称
	 * 
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public String getMenuButtonModel(String params) throws Exception {

		return null;
	}

	/**
	 * 查询菜单选择的树形结构（数据结构：id=uuid、text=name） (只显示两层数据)
	 * 
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public String getModuleGroupMenuitemTree(String params) throws Exception {
		List<ModuleGroupMenuItemTrue> mmdgmitree = ContextUtils.getBeanOfType(
				SysViewButtonManager.class).getModuleGroupMenuitemTree();
		return JsonUtil.list2Json(mmdgmitree);
	}

	/**
	 * 查询菜单选择的树形结构（数据结构：id=uuid、text=name） (可实现多层次树形)
	 * 
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public String getModuleGroupMenuitemTree1(String params) throws Exception {
		List<ModuleGroupMenuItemTrue> mmdgmitree = ContextUtils.getBeanOfType(
				SysViewButtonManager.class).getModuleGroupMenuitemTree1();
		return JsonUtil.list2Json(mmdgmitree);
	}

	/**
	 * 查询菜单选择的树形结构（数据结构：id=uuid、text=name）
	 * 
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public String getModuleGroupOfficeNameTree(String params) throws Exception {
		List<ModuleGroupMenuItemTrue> mmdgmitree = ContextUtils.getBeanOfType(
				SysViewButtonManager.class).getModuleGroupOfficeNameTree();
		return JsonUtil.list2Json(mmdgmitree);
	}

	/**
	 * 上级菜单选择
	 * 
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public String getModuleGroupTree(String params) throws Exception {
		List<ModuleGroupMenuItemTrue> mmdgmitree = ContextUtils.getBeanOfType(
				SysViewButtonManager.class).getModuleGroupTree();
		return JsonUtil.list2Json(mmdgmitree);
	}

	/**
	 * 查询单个按钮信息
	 * 
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public String selectviewButton(String params) throws Exception {
		String selectviewbuttonid = request.getParameter("selectviewbuttonid");
		SysViewButtonModel svButtonm = ContextUtils.getBeanOfType(
				SysViewButtonManager.class).get(selectviewbuttonid);
		return JsonUtil.beanToJson(svButtonm);
	}

	/**
	 * 根据菜单和角色。查询所属按钮信息---树形
	 * 
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public String selectviewButtonall(String params) throws Exception {
		String[] data = new String[2];
		String sysMenuItemUuid = request.getParameter("sysMenuItemUuid");
		data[0] = sysMenuItemUuid;
		List<ModuleGroupMenuItemTrue> mmdgmitree = ContextUtils.getBeanOfType(
				SysViewButtonManager.class).selectviewButtonall(data);
		String svbuttonall = JsonUtil.list2Json(mmdgmitree);
		return svbuttonall;
	}

	/**
	 * 新增按钮信息
	 * 
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public String savaviewButton(String params) throws Exception {
		String viewButtonjson = request.getParameter("viewButtonjson");
		sysViewButtonManager.saveViewButtonModel(viewButtonjson, getUser()
				.getOfficeCode());
		return null;
	}

	/**
	 * 修改按钮信息
	 * 
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public String updateviewButton(String params) throws Exception {
		String viewButtonjson = request.getParameter("viewButtonjson");
		sysViewButtonManager.updateViewButtonModel(viewButtonjson, getUser()
				.getOfficeCode());
		return null;
	}

	/**
	 * 删除按钮信息
	 * 
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public String deleteviewButton(String params) throws Exception {
		String viewButtonUuid = request.getParameter("viewButtonUuid");
		ContextUtils.getBeanOfType(SysViewButtonManager.class).removeByPk(
				viewButtonUuid);
		return null;
	}

	/**
	 * 查询单个模版信息
	 * 
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public String findTemplateInfoById(String params) throws Exception {
		String templateid = request.getParameter("templateid");
		PrintReportTemplateModel templatemodel = ContextUtils.getBeanOfType(
				PrintReportTemplateManager.class).get(templateid);
		PrintReportTemplateEntity entity = new PrintReportTemplateEntity();
		BeanUtils.copyProperties(entity, templatemodel);
		String selectUid = JsonUtil.beanToJson(entity);
		return selectUid;
	}

	/**
	 * 保存报表模版
	 * 
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public String saveTemplate(String params) throws Exception {
		PrintReportTemplateModel reporttemplate = new PrintReportTemplateModel();
		// 获取上传的文件路径
		String filepath = RcUtil.str2utf8(request.getParameter("template"));
		// if (!RcUtil.isEmpty(filepath)) {
		// // 获取模版内容
		// String strTemplateContent = ContextUtils.getBeanOfType(
		// PrintReportTemplateManager.class).readFr3File(
		// request.getInputStream());
		// reporttemplate.setTemplateContent(strTemplateContent.getBytes());
		// }

		// 将获取的值放入模版对象
		String fileName = RcUtil.str2utf8(request.getParameter("fileName"));

		String templateName = RcUtil.str2utf8(request
				.getParameter("templateName"));

		String templateType = RcUtil.str2utf8(request
				.getParameter("templateType"));

		String fileVersion = RcUtil.str2utf8(request
				.getParameter("fileVersion"));

		String templateNameEn = RcUtil.str2utf8(request
				.getParameter("templateNameEn"));

		String remark = RcUtil.str2utf8(request.getParameter("remark"));
		reporttemplate.setReportTemplateUuid(request
				.getParameter("reportTemplateUuid"));
		reporttemplate.setTemplateCode(request.getParameter("templateCode"));
		reporttemplate.setTemplateName(templateName);
		reporttemplate.setFileName(fileName);
		reporttemplate.setStatus(request.getParameter("status"));
		reporttemplate.setTemplateType(templateType);
		reporttemplate.setFileVersion(fileVersion);
		reporttemplate.setTemplateNameEn(templateNameEn);
		reporttemplate.setRemark(remark);

		String rowState = request.getParameter("rowState");
		ContextUtils.getBeanOfType(PrintReportTemplateManager.class)
				.saveReportTemplate(reporttemplate, filepath,
						request.getInputStream(), getUser().getOfficeCode(),
						rowState);

		PrintReportTemplateEntity prte = new PrintReportTemplateEntity();
		BeanUtils.copyProperties(prte, reporttemplate);
		return JsonUtil.beanToJson(new SinotransPageJson(true, "保存成功!", "",
				null));
	}

	/**
	 * 删除模版信息
	 * 
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public String deleteTemplate(String params) throws Exception {
		String deleteUuid = request.getParameter("deleteid");
		ContextUtils.getBeanOfType(PrintReportTemplateManager.class)
				.removeByPk(deleteUuid);
		return JsonUtil.beanToJson(new SinotransPageJson(true, "删除成功!"));
	}

	/**
	 * 保存给角色分配菜单信息
	 * 
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public String saveRoleMenuManager(String params) throws Exception {
		String saveRoleMenuManagerall = request.getParameter("rolemenujson");
		SinotransPageJson sinotr= ContextUtils.getBeanOfType(SysRoleMenuItemManager.class).saveRoleMenuManager(saveRoleMenuManagerall);
		return JsonUtil.beanToJson(sinotr);
	}

	/**
	 * 保存给菜单分配按钮
	 * 
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public String saveRoleButtonOrMenuButton(String params) throws Exception {
		String roleNews = request.getParameter("rolejsons");
		String menuNews = request.getParameter("menujsons");
		String buttonNews = request.getParameter("buttonjsons");
		String rolemenujsons = request.getParameter("rolemenujsons");
		ContextUtils.getBeanOfType(SysRoleMenuButtonManager.class)
				.saveRoleButtonOrMenuButton(roleNews, menuNews, buttonNews,
						rolemenujsons);
		return null;
	}

	/**
	 * 保存给用户与角色信息
	 * 
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public String saveUserRoleManagerMangToMang(String params) throws Exception {
		String userAll = request.getParameter("userAll");
		String roleAll = request.getParameter("ruleAll");
		ContextUtils.getBeanOfType(SysRoleUserManager.class)
				.saveUserRoleManagerMangToMang(userAll, roleAll);
		return null;
	}

	/**
	 * 修改密码
	 * 
	 * @param params
	 * @return 必要的提示信息
	 */
	public String changeSysUserPassword(String params) {
		return ContextUtils.getBeanOfType(SysUserManager.class).changePassword(
				RcUtil.getParameterMap(request));
	}

	public String getMenuItemModelMapS(String params) throws Exception {
		String menuString = ContextUtils
				.getBeanOfType(SysMenuItemManager.class).getMenuItemModelMap();
		return menuString;
	}

	public String getMenuGroupModelMapS(String params) throws Exception {
		String menuString = ContextUtils.getBeanOfType(
				SysMenuGroupManager.class).getMenuGroupModelMap();
		return menuString;
	}

	/** **********************肖于忠**************************** */
	/*
	 * 新增分组菜单
	 */

	public String getmenuGroup(String params) throws Exception {
		String menuitemjson = request.getParameter("menuitemjson");
		String result = ContextUtils.getBeanOfType(SysMenuGroupManager.class)
				.saveMenuGroupOff(menuitemjson, getUser().getOfficeCode());
		return result;
	}
	
	/*
	 * 查询分组菜单
	 */

	public String selectmenuGroup(String params) throws Exception {
		List<SysMenuGroupModel> result = ContextUtils.getBeanOfType(SysMenuGroupManager.class)
				.selectmenuGroup();
		return JsonUtil.list2Json(result);
	}

	/**
	 * 菜单分组树形
	 * 
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public String selectModuleitemtree(String params) throws Exception {
		SysMenuGroupManager sysOfficeManager = (SysMenuGroupManager) ContextUtils
				.getBeanOfType(SysMenuGroupManager.class);
		List<OfficeTree> resultList = sysOfficeManager.getOfficetree();
		return JsonUtil.list2Json(resultList);
	}
	/**
	 * 菜单分组树形(添加了父级菜单)
	 * 
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public String selectFatherModuleitemtree(String params) throws Exception {
		SysMenuGroupManager sysOfficeManager = (SysMenuGroupManager) ContextUtils
				.getBeanOfType(SysMenuGroupManager.class);
		List<OfficeTree> resultList = sysOfficeManager.getFatherOfficetree();
		return JsonUtil.list2Json(resultList);
	}

	/*
	 * 查询要修改分组菜单的id
	 */
	public String updateMenuGroupid(String params) throws Exception {
		String selectmenuitemid = request.getParameter("selectmenuitemid");
		SysMenuGroupModel sMenum = ContextUtils.getBeanOfType(
				SysMenuGroupManager.class).get(selectmenuitemid);
		String selectUid = JsonUtil.beanToJson(sMenum);
		return selectUid;
	}

	/*
	 * 修改分组菜单
	 */
	public String updateMenuGroup(String params) throws Exception {
		String menuitemjson = request.getParameter("menuitemjson");
		return ContextUtils.getBeanOfType(SysMenuGroupManager.class)
				.updateMenuGroupOff(menuitemjson, getUser().getOfficeCode());
	}

	/**
	 * deleteMenuGroup 删除分组菜单
	 */
	public String deleteMenuGroup(String params) throws Exception {
		String deleteMenuitem = request.getParameter("deleteMenuitem");
		ContextUtils.getBeanOfType(SysMenuGroupManager.class).removeByPk(
				deleteMenuitem);
		return JsonUtil.beanToJson(new SinotransPageJson(true, "删除成功!"));
	}

	/**
	 * 菜单分组选择(上级菜单)
	 * 
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public String getMenuGroupTree(String params) throws Exception {
		List<OfficeTree> mmdgmitree = ContextUtils.getBeanOfType(
				SysMenuGroupManager.class).getMenuGroupTree();
		return JsonUtil.list2Json(mmdgmitree);
	}

	/*
	 * 列表添加的组织机构
	 */

	public String UseritmecustomerCode(String params) throws Exception {
		List<SysOfficeModel> sUserm = sysOfficeManager.getOfficeUserList(null,
				null);
		String selectUser = JsonUtil.list2Json(sUserm);
		return selectUser;
	}

	/*
	 * menuitembutton
	 */
	public String menuitembutton(String params) throws Exception {
		String selectviewbuttonid = request.getParameter("selectviewbuttonid");
		SysViewButtonModel model = new SysViewButtonModel();
		model.setSysMenuItemUuid(selectviewbuttonid);
		List<SysViewButtonModel> officeList = sysViewButtonManager
				.findByExample(model);
		return JsonUtil.beanToJson(officeList);
	}

	/*
	 * 用户页面去掉admin
	 */
	public String deAdmin(String params) throws Exception {
		String office = request.getParameter("__officeCode");
		String userName = request.getParameter("__userName");
		String userType = request.getParameter("__userType");
		String roleCode=request.getParameter("__roleCode");
		String createTime_s = request.getParameter("createTime__start");
		String createTime_e = request.getParameter("createTime__end");
		String biaoshi=request.getParameter("__biaoshi");
		SinotransDataGrid sUserm = ContextUtils.getBeanOfType(
				SysUserManager.class).getdeleteAdmin(office,userName, userType,roleCode,
				createTime_s, createTime_e, getPagingInfo(),
				getUser().getOfficeCode(),biaoshi);
		String selectUser = JsonUtil.beanToJson(sUserm);
		return selectUser;
	}
	
	/*
	 *Admin登入时的查询语句
	 */
	public String userListAdmin(String params) throws Exception {
		String office = request.getParameter("__officeCode");
		String userName = request.getParameter("__userName");
		String userType = request.getParameter("__userType");
		String roleCode=request.getParameter("__roleCode");
		String createTime_s = request.getParameter("createTime__start");
		String createTime_e = request.getParameter("createTime__end");
		String biaoshi=request.getParameter("__biaoshi");
		SinotransDataGrid sUserm = ContextUtils.getBeanOfType(
				SysUserManager.class).getuserListAdmin(office,userName, userType,roleCode,
				createTime_s, createTime_e, getPagingInfo(),
				getUser().getOfficeCode(),biaoshi);
		String selectUser = JsonUtil.beanToJson(sUserm);
		return selectUser;
	}
	

	/*
	 * 角色页面去掉admin
	 */
	public String roldeAdmin(String params) throws Exception {
		String status = request.getParameter("__status");
		String roleName = request.getParameter("__roleName");
		String roleNameEn = request.getParameter("__roleNameEn");
		String roleType = request.getParameter("__roleType");
		String createTime_s = request.getParameter("createTime__start");
		String createTime_e = request.getParameter("createTime__end");
		String officeCode=request.getParameter("__officeCode");
		if(RcUtil.isEmpty(officeCode)){
			officeCode=getUser().getOfficeCode();
		}
		SinotransDataGrid sUserm = ContextUtils.getBeanOfType(
				SysRoleManager.class).getRoldeleteAdmin(status,roleName, roleNameEn,
				roleType, createTime_s, createTime_e, getPagingInfo(),
				officeCode);
		String selectUser = JsonUtil.beanToJson(sUserm);
		return selectUser;
	}

	/*
	 * 角色与权限页面过滤admin
	 */
	public String rolORdeAdmin(String params) throws Exception {
		SinotransDataGrid sUserm = ContextUtils.getBeanOfType(
				SysRoleManager.class).getRolORdeleteAdmin(getPagingInfo(),
				getUser().getOfficeCode());
		String selectUser = JsonUtil.beanToJson(sUserm);
		return selectUser;
	}

	/*
	 * 用户与角色页面去掉admin
	 */
	public String rolUserdeAdmin(String params) throws Exception {
		String office= request.getParameter("__officeCode");
		SinotransDataGrid sUserm = ContextUtils.getBeanOfType(
				SysUserManager.class).getRolUserdeleteAdmin(office,getPagingInfo(),
				getUser().getOfficeCode());
		String selectUser = JsonUtil.beanToJson(sUserm);
		return selectUser;
	}

	/**
	 * 查询菜单选择的树形结构（数据结构：id=uuid、text=name） (可实现多层次树形) 查询用户的权限拥有的(菜单及对应的子菜单)
	 * 
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public String getModuleGroupMenuitemUserTree1(String params)
			throws Exception {
		List<ModuleGroupMenuItemTrue> mmdgmitree = ContextUtils.getBeanOfType(
				SysViewButtonManager.class).getModuleGroupMenuitemUserTree1(
				getUser().getUserId());
		return JsonUtil.list2Json(mmdgmitree);
	}

	/**
	 * 根据用户的角色查询所拥有的(菜单)分组树形
	 * 
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public String selectModuleitemUsertree(String params) throws Exception {
		SysMenuGroupManager sysOfficeManager = (SysMenuGroupManager) ContextUtils
				.getBeanOfType(SysMenuGroupManager.class);
		List<OfficeTree> resultList = sysOfficeManager
				.getUserModuleOfficetree(getUser().getUserId());
		return JsonUtil.list2Json(resultList);
	}

	/**
	 * 查询全部角色信息--树形
	 * 
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public String selectroleAlltreeoffice(String params) throws Exception {
		List<ModuleGroupMenuItemTrue> srolem= new ArrayList<ModuleGroupMenuItemTrue>();
		if (CommonUtil.SYSTEM_ADMIN.equals(getUser().getUserId())) {
			srolem = ContextUtils.getBeanOfType(
					SysRoleManager.class).selectrole();
		}else{
			 srolem = ContextUtils.getBeanOfType(
					SysRoleManager.class).selectroleoffice(getUser().getOfficeCode());
		}
		
		String selectUid = JsonUtil.beanToJson(srolem);
		return selectUid;
	}

	/*
	 * 查询所属公司selectCompany
	 */
	public String selectCompany(String params) throws Exception {
		SinotransDataGrid officecode = ContextUtils.getBeanOfType(
				SysUserManager.class).selectCompany(getPagingInfo());
		return JsonUtil.beanToJson(officecode);
	}
	/*
	 * 查询打印列表
	 * 
	 */
	public String selectPrintReportTemplate(String params) throws Exception {
		String templateName = request.getParameter("__templateName");
		String fileName = request.getParameter("__fileName");
		String templateType = request.getParameter("__templateType");
		String officeCode=request.getParameter("__officeCode");
		SinotransDataGrid officecode = ContextUtils.getBeanOfType(
				PrintReportTemplateManager.class).selectPrintReportTemplate(templateName,fileName,templateType,getPagingInfo(),officeCode);
		return JsonUtil.beanToJson(officecode);
	}
	
	/**
	 * 查询菜单选择的树形结构（数据结构：id=uuid、text=name） (可实现多层次树形)可改变item的officecode查询树形
	 * 
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public String getofficecodeModuleGroupMenuitemTree1(String params) throws Exception {
		String uuid= request.getParameter("officecode");
		List<ModuleGroupMenuItemTrue> mmdgmitree = ContextUtils.getBeanOfType(
				SysRoleManager.class).getofficecodeModuleGroupMenuitemTree1(uuid);
		return JsonUtil.list2Json(mmdgmitree);
	}
	

	/**
	 * 保存新闻信息
	 */
	
	public String saveSystemNews(String params) throws Exception{
		String jsonresult = request.getParameter("jsonresult");
		String syscCntent = request.getParameter("syscCntent");
		String urlAddress = request.getParameter("urlAddress");
		SinotransPageJson spj = new SinotransPageJson();
		byte[] content = null ;
		if(!RcUtil.isEmpty(syscCntent)){
		 content = syscCntent.getBytes();
		}
		if(!RcUtil.isEmpty(jsonresult)){
			spj=sysNewsManager.saveSystemNews(jsonresult, getUser().getOfficeCode(), content , urlAddress);			
		}
		String sysNews = JsonUtil.beanToJson(spj);
		return sysNews;
	}
	
	/**
	 * 生效和作废新闻
	 */
	public String valAndCancelNews(String params) throws Exception{
		String jsonresult = request.getParameter("jsonresult");
		String syscCntent = request.getParameter("syscCntent");
		String urlAddress = request.getParameter("urlAddress");
		byte[] content = null ;
		if(!RcUtil.isEmpty(syscCntent)){
		 content = syscCntent.getBytes();
		}
		SinotransPageJson spj = new SinotransPageJson();
		if(!RcUtil.isEmpty(jsonresult)){
			spj=sysNewsManager.validateAndCancel(jsonresult, getUser().getOfficeCode(), content,urlAddress);			
		}
		String sysNews = JsonUtil.beanToJson(spj);
		return sysNews;
	}
}