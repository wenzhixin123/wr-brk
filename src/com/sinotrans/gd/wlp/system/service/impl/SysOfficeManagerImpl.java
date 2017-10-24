package com.sinotrans.gd.wlp.system.service.impl;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sinotrans.framework.core.exception.ApplicationException;
import com.sinotrans.framework.core.model.BaseModel;
import com.sinotrans.framework.core.service.impl.BaseManagerImpl;
import com.sinotrans.framework.core.util.ContextUtils;
import com.sinotrans.gd.wlp.common.entity.BasOption;
import com.sinotrans.gd.wlp.common.service.LogisticsOrderManager;
import com.sinotrans.gd.wlp.common.web.RcUtil;
import com.sinotrans.gd.wlp.system.entity.OfficeTree;
import com.sinotrans.gd.wlp.system.entity.SessionContextUserEntity;
import com.sinotrans.gd.wlp.system.model.SysOfficeModel;
import com.sinotrans.gd.wlp.system.service.SysOfficeManager;
import com.sinotrans.gd.wlp.util.CommonUtil;
import com.sinotrans.gd.wlp.util.JsonUtil;
import com.sinotrans.gd.wlp.util.OfficeCodeTypeEnum;
import com.sinotrans.gd.wlp.util.StringUtil;

import edu.emory.mathcs.backport.java.util.Collections;

@Service
public class SysOfficeManagerImpl extends BaseManagerImpl implements
		SysOfficeManager {
	@Autowired
	private LogisticsOrderManager logisticsOrderManager;

	public SysOfficeModel get(String id) {
		return this.dao.get(SysOfficeModel.class, id);
	}

	public List<SysOfficeModel> getAll() {
		return this.dao.getAll(SysOfficeModel.class);
	}

	public List<SysOfficeModel> findByExample(SysOfficeModel example) {
		return this.dao.findByExample(example);
	}

	public SysOfficeModel save(SysOfficeModel model) {
		return this.dao.save(model);
	}

	public List<SysOfficeModel> saveAll(Collection<SysOfficeModel> models) {
		return this.dao.saveAll(models);
	}
	
	public void remove(SysOfficeModel model) {
		this.dao.remove(model);
	}

	public void removeAll(Collection<SysOfficeModel> models) {
		this.dao.removeAll(models);
	}

	public void removeByPk(String id) {
		this.dao.removeByPk(SysOfficeModel.class, id);
	}

	public void removeAllByPk(Collection<String> ids) {
		this.dao.removeAllByPk(SysOfficeModel.class, ids);
	}

	public List<BasOption> getOption(String code, String officeCode,
			String language) {
		List<BasOption> optionList = new ArrayList<BasOption>();
		List<SysOfficeModel> bcmList = this.getAll();
		for (SysOfficeModel c : bcmList) {
			BasOption wo = new BasOption();
			wo.setKey(c.getOfficeUuid());
			wo.setValue(c.getOfficeName());
			optionList.add(wo);
		}
		return optionList;
	}

	/*
	 * 查询出树形结构列表(id：officeUuid、) (non-Javadoc)
	 * 
	 * @see com.sinotrans.gd.wlp.system.service.SysOfficeManager#getOfficetree()
	 */
	public List<OfficeTree> getOfficetree() {
		List<SysOfficeModel> allOffice = this.getAll();
		List<OfficeTree> resultList = new ArrayList<OfficeTree>();
		for (SysOfficeModel allOM : allOffice) {
			if (allOM.getStatus() != null && !allOM.getStatus().equals("")
					&& allOM.getStatus().equals(CommonUtil.Active)) {// 此处判断是否状态为有效。作废或者草稿数据是不能被显示的
				OfficeTree ot = new OfficeTree();
				String officeId = allOM.getOfficeUuid();
				ot.setId(allOM.getOfficeUuid());
				ot.setText(allOM.getOfficeName());
				ot.setChildren(recursive(officeId, allOffice));// 循环看看它是否有子节点
				if (RcUtil.isEmpty(allOM.getPreOfficeUuid())) {// 根节点
					ot.setId(allOM.getOfficeUuid());
					ot.setText(allOM.getOfficeName());
					resultList.add(ot);
				}
			}
		}
		return resultList;
	}

	/*
	 * 查询出树形结构列表（暂时测试中。。。） (non-Javadoc)
	 * 
	 * @see com.sinotrans.gd.wlp.system.service.SysOfficeManager#getOfficetree()
	 */
	// public List<OfficeTree> getOfficetree(String officeCode) {
	// // SysOfficeModel so = new SysOfficeModel();
	// // so.setOfficeCode(officeCode);
	// List<SysOfficeModel> allOffice = this.getAll();
	// List<OfficeTree> resultList = new ArrayList<OfficeTree>();
	// for (SysOfficeModel allOM : allOffice) {
	// if (allOM.getStatus() != null && !allOM.getStatus().equals("")
	// && allOM.getStatus().equals(CommonUtil.Active)){//
	// 此处判断是否状态为有效。作废或者草稿数据是不能被显示的
	// OfficeTree ot = new OfficeTree();
	// String officeId = allOM.getOfficeUuid();
	// ot.setId(allOM.getOfficeUuid());
	// ot.setText(allOM.getOfficeName());
	// ot.setChildren(recursive(officeId, allOffice));// 循环看看它是否有子节点
	// if (RcUtil.isEmpty(allOM.getPreOfficeUuid())) {// 根节点
	// ot.setId(allOM.getOfficeUuid());
	// ot.setText(allOM.getOfficeName());
	// resultList.add(ot);
	// }
	// }
	// }
	// return resultList;
	// }

	/*
	 * 查询出部门树形列表（id：OfficeCode、text：name） (non-Javadoc)
	 * 
	 * @see com.sinotrans.gd.wlp.system.service.SysOfficeManager#getOfficeCode()
	 */
	public List<OfficeTree> getOfficeCode() {
		List<SysOfficeModel> allOffice = this.getAll();
		List<OfficeTree> resultList = new ArrayList<OfficeTree>();
		for (SysOfficeModel allOM : allOffice) {
			OfficeTree ot = new OfficeTree();
			String officeId = allOM.getOfficeUuid();
			ot.setId(officeId);
			ot.setText(allOM.getOfficeName());
			ot.setChildren(recursive(officeId, allOffice));// 循环看看它是否有子节点
			if (RcUtil.isEmpty(allOM.getPreOfficeUuid())) {// 根节点
				ot.setId(officeId);
				ot.setText(allOM.getOfficeName());
				resultList.add(ot);
			}
		}
		return resultList;
	}
	/*
	 * 查询出部门树形列表（id：OfficeCode、text：name） (non-Javadoc)
	 * 
	 * @see com.sinotrans.gd.wlp.system.service.SysOfficeManager#getOfficeCode()
	 */
	public List<OfficeTree> getOfficeCodetree() {
		List<SysOfficeModel> allOffice = this.getAll();
		List<OfficeTree> resultList = new ArrayList<OfficeTree>();
		for (SysOfficeModel allOM : allOffice) {
			OfficeTree ot = new OfficeTree();
			String officeId = allOM.getOfficeUuid();
			ot.setId(allOM.getOfficeCode());
			ot.setText(allOM.getOfficeName());
			ot.setChildren(recursivetree(officeId, allOffice));// 循环看看它是否有子节点
			if (RcUtil.isEmpty(allOM.getPreOfficeUuid())) {// 根节点
				ot.setId(allOM.getOfficeCode());
				ot.setText(allOM.getOfficeName());
				resultList.add(ot);
			}
		}
		return resultList;
	}
	/**
	 * 循环递归 部门信息
	 * 
	 * @param officeId
	 * @param allOffice
	 * @return
	 */
	private List<OfficeTree> recursivetree(String officeId,
			List<SysOfficeModel> allOffice) {
		List<OfficeTree> subOfficeList = new ArrayList<OfficeTree>();
		for (SysOfficeModel som : allOffice) {
			if (officeId.equals(som.getPreOfficeUuid())) {
				if (som.getStatus() != null && !som.getStatus().equals("")
						&& som.getStatus().equals(CommonUtil.Active)) { // 此处判断是否状态为有效。作废或者草稿数据是不能被显示的
					OfficeTree ot = new OfficeTree();
					String subOfficeId = som.getOfficeUuid();
					ot.setId(som.getOfficeCode());
					ot.setText(som.getOfficeName());
					ot.setChildren(recursivetree(subOfficeId, allOffice));
					subOfficeList.add(ot);
				}
			}
		}
		return subOfficeList;
	}
	
	/**
	 * 循环递归 部门信息
	 * 
	 * @param officeId
	 * @param allOffice
	 * @return
	 */
	private List<OfficeTree> recursive(String officeId,
			List<SysOfficeModel> allOffice) {
		List<OfficeTree> subOfficeList = new ArrayList<OfficeTree>();
		for (SysOfficeModel som : allOffice) {
			if (officeId.equals(som.getPreOfficeUuid())&&!officeId.equals(som.getOfficeUuid())) {
				if (som.getStatus() != null && !som.getStatus().equals("")
						&& som.getStatus().equals(CommonUtil.Active)) { // 此处判断是否状态为有效。作废或者草稿数据是不能被显示的
					OfficeTree ot = new OfficeTree();
					String subOfficeId = som.getOfficeUuid();
					// ot.setId(som.getOfficeCode());
					ot.setId(subOfficeId);
					ot.setText(som.getOfficeName());
					ot.setChildren(recursive(subOfficeId, allOffice));
					subOfficeList.add(ot);
				}
			}
		}
		//子组织排序
		if(subOfficeList.size()>0){
			boolean needSort = true;
			for(OfficeTree officeSon:subOfficeList){
				if(officeSon.getChildren()!=null&&officeSon.getChildren().size()>0){
					needSort = false;
				}
			}
			if(needSort){
				Collections.sort(subOfficeList, new Comparator() {   
			          public int compare(Object a, Object b) {   
			            String one = ((OfficeTree)a).getText();   
			            String two = ((OfficeTree)b).getText();
			            return Collator.getInstance(Locale.CHINESE).compare(one, two);    
			          }   
			    });
			}
		}
		return subOfficeList;
	}

	/**
	 * 用于在编辑页面验证组织机构代码是否重复
	 * 
	 * @param officeCode
	 * @return 返回 true 为已存在
	 */
	@Override
	public boolean getYanZhenUserCode(String officeCode) {
		SysOfficeModel office = new SysOfficeModel();
		office.setOfficeCode(StringUtil.toTrim(officeCode));
		List<SysOfficeModel> officeList = this.findByExample(office);
		if (officeList.size() > 0) {
			return true;
		} else {
			return false;
		}
	}

	/*
	 * 保存 (non-Javadoc)
	 * 
	 * @see
	 * com.sinotrans.gd.wlp.system.service.SysOfficeManager#savaOfficeModel(
	 * java.lang.String)
	 */
	@Override
	public String savaOfficeModel(String Officeitemjson) {
		Officeitemjson=logisticsOrderManager.getBase642Ojbect(Officeitemjson);
		SysOfficeModel sysOfficeModel = (SysOfficeModel) JsonUtil.jsonToBean(
				Officeitemjson, SysOfficeModel.class);
		if (getYanZhenUserCode(sysOfficeModel.getOfficeCode())) {
			sysOfficeModel.setRowState(BaseModel.ROW_STATE_MODIFIED);
		} else {
			sysOfficeModel.setRowState(BaseModel.ROW_STATE_ADDED);
		}
		ContextUtils.getBeanOfType(SysOfficeManager.class).save(sysOfficeModel);
		return null;
	}

	/**
	 * 根据OfficeCode查询该组织机构的UUID
	 * 
	 * @param officeCode
	 * @return
	 */
	@Override
	public String getOfficeOofficeCodeModel(String officeCode) {
		SysOfficeModel office = new SysOfficeModel();
		office.setOfficeCode(officeCode.trim());
		office.setStatus(CommonUtil.Active);
		List<SysOfficeModel> officeList = this.findByExample(office);
		if (officeList != null && officeList.size() > 0) {
			SysOfficeModel syoOff = officeList.get(0);
			return syoOff.getOfficeUuid();
		} else {
			throw new ApplicationException("未获取到相应的组织机构对象！");
		}
	}
	
	/**
	 * 根据OfficeCode查询该组织机构的name
	 * 
	 * @param officeCode
	 * @return
	 */
	@Override
	public String getOfficeCodereturnCodename(String officeCode) {
		SysOfficeModel office = new SysOfficeModel();
		office.setOfficeCode(officeCode.trim());
		office.setStatus(CommonUtil.Active);
		List<SysOfficeModel> officeList = this.findByExample(office);
		if (officeList != null && officeList.size() > 0) {
			SysOfficeModel syoOff = officeList.get(0);
			return syoOff.getOfficeName();
		} else {
			throw new ApplicationException("未获取到相应的组织机构对象！");
		}
	}
	

	public void getOffficeCodeTree(String officeCode) {
		// List<SysOfficeModel> sysOffList = new ArrayList<SysOfficeModel>();
		// sysOffList = this.getAll();
		// for (SysOfficeModel sysOfficeModel : sysOffList) {
		//
		// }
	}

	/**
	 * 提供用户管理页面列表使用的组织机构名称显示
	 * 
	 * @param officeCode
	 * @param status
	 */
	public List<SysOfficeModel> getOfficeUserList(String officeCode,
			String status) {
		SysOfficeModel office = new SysOfficeModel();
		if (officeCode != null)
			office.setOfficeCode(officeCode);
		if (status != null)
			office.setStatus(status);
		List<SysOfficeModel> officeList = this.findByExample(office);

		return officeList;
	}

	/*
	 * 根据节点查找父类测试中.........
	 */
	public List<OfficeTree> getOfficetree(String officeCode) {
		List<SysOfficeModel> allOffice = this.getAll();
		SysOfficeModel code = new SysOfficeModel();
		code.setOfficeCode(officeCode);
		List<SysOfficeModel> officecode = this.findByExample(code);
		List<OfficeTree> resultList = new ArrayList<OfficeTree>();
		for (SysOfficeModel allOM : officecode) {
			String PreOfficeUuid = allOM.getPreOfficeUuid();
			String OfficeUuid = allOM.getOfficeUuid();
			String OfficeName = allOM.getOfficeName();
			OfficeTree ot = new OfficeTree();
			ot.setId(OfficeUuid);
			ot.setText(OfficeName);
			resultList.add(ot);
			resultList = recursive2(resultList, PreOfficeUuid, allOffice);
		}
		return resultList;
	}

	/**
	 * 循环递归 查找父类
	 * 
	 * @param officeId
	 * @param allOffice
	 * @return
	 */
	private List<OfficeTree> recursive2(List<OfficeTree> resultList,
			String ProvinceCode, List<SysOfficeModel> allOffice) {
		List<OfficeTree> subOfficeList = new ArrayList<OfficeTree>();
		for (SysOfficeModel som : allOffice) {
			if (ProvinceCode.equals(som.getOfficeUuid())) {
				if (som.getStatus() != null && !som.getStatus().equals("")
						&& som.getStatus().equals(CommonUtil.Active)) { // 此处判断是否状态为有效。作废或者草稿数据是不能被显示的
					OfficeTree ot = new OfficeTree();
					String subOfficeId = som.getOfficeUuid();
					String PreOffice = som.getPreOfficeUuid();
					ot.setId(subOfficeId);
					ot.setText(som.getOfficeName());
					ot.setChildren(resultList);
					subOfficeList.add(ot);
					if (PreOffice != null) {
						subOfficeList = recursive2(subOfficeList, PreOffice,
								allOffice);
						log.debug("subOfficeList:"
								+ JsonUtil.list2Json(subOfficeList));
					}
				}
			}
		}
		return subOfficeList;
	}

	/*
	 * 根据节点查找父类与子类测试中.........
	 */
	public List<OfficeTree> getUserOfficetree(String officeCode) {
		List<SysOfficeModel> allOffice = this.getAll();
		SysOfficeModel code = new SysOfficeModel();
		code.setOfficeCode(officeCode);
		List<SysOfficeModel> officecode = this.findByExample(code);
		List<OfficeTree> resultList = new ArrayList<OfficeTree>();
		for (SysOfficeModel allOM : officecode) {
			String PreOfficeUuid = allOM.getPreOfficeUuid();
			String OfficeUuid = allOM.getOfficeUuid();
			String OfficeName = allOM.getOfficeName();
			OfficeTree ot = new OfficeTree();
			ot.setId(OfficeUuid);
			ot.setText(OfficeName);
			ot.setChildren(recursive4(OfficeUuid, allOffice));
			resultList.add(ot);
			resultList = recursive3(resultList, PreOfficeUuid, allOffice);
		}
		return resultList;
	}

	/**
	 * 循环递归 查找父类
	 * 
	 * @param officeId
	 * @param allOffice
	 * @return
	 */
	private List<OfficeTree> recursive3(List<OfficeTree> resultList,
			String ProvinceCode, List<SysOfficeModel> allOffice) {
		List<OfficeTree> subOfficeList = new ArrayList<OfficeTree>();
		for (SysOfficeModel som : allOffice) {
			if (ProvinceCode.equals(som.getOfficeUuid())) {
				if (som.getStatus() != null && !som.getStatus().equals("")
						&& som.getStatus().equals(CommonUtil.Active)) { // 此处判断是否状态为有效。作废或者草稿数据是不能被显示的
					OfficeTree ot = new OfficeTree();
					String subOfficeId = som.getOfficeUuid();
					String PreOffice = som.getPreOfficeUuid();
					ot.setId(subOfficeId);
					ot.setText(som.getOfficeName());
					ot.setChildren(resultList);
					subOfficeList.add(ot);
					if (PreOffice != null) {
						subOfficeList = recursive3(subOfficeList, PreOffice,
								allOffice);
						log.debug("subOfficeList:"
								+ JsonUtil.list2Json(subOfficeList));
					}
				}
			}
		}
		return subOfficeList;
	}

	/**
	 * 循环递归 查找子类
	 * 
	 * @param officeId
	 * @param allOffice
	 * @return
	 */
	private List<OfficeTree> recursive4(String officeId,
			List<SysOfficeModel> allOffice) {
		List<OfficeTree> subOfficeList = new ArrayList<OfficeTree>();
		for (SysOfficeModel som : allOffice) {
			if (officeId.equals(som.getPreOfficeUuid())&&!officeId.equals(som.getOfficeUuid())) {
				if (som.getStatus() != null && !som.getStatus().equals("")
						&& som.getStatus().equals(CommonUtil.Active)) { // 此处判断是否状态为有效。作废或者草稿数据是不能被显示的
					OfficeTree ot = new OfficeTree();
					String subOfficeId = som.getOfficeUuid();
					ot.setId(subOfficeId);
					ot.setText(som.getOfficeName());
					ot.setChildren(recursive4(subOfficeId, allOffice));
					subOfficeList.add(ot);
				}
			}
		}
		return subOfficeList;
	}
	
	/*
	 * 根据节点查找父节点 office code
	 */
	public String getPreOfficeCode(String officeCode) {
		if (StringUtil.isNull(officeCode)) {
			return "";
		}
		
		List<SysOfficeModel> allOffice = this.getAll();
		for (SysOfficeModel allOM : allOffice) {
			if (officeCode.equals(allOM.getOfficeCode())) {
				String uuid = allOM.getPreOfficeUuid();
				if (uuid == null) {
					return "";
				}
				for (SysOfficeModel model : allOffice) {
					if (uuid.equals(model.getOfficeUuid())) {
						return model.getOfficeCode();
					}
				}
			}
		}
			
		return "";
	}

	@Override
	public boolean isMatchOfficeCodeType(OfficeCodeTypeEnum octe)throws Exception {
		String userOfficeCode = SessionContextUserEntity.currentUser().getOfficeCode();
		String id = getOfficeOofficeCodeModel(userOfficeCode);
		SysOfficeModel model = this.get(id);
		if(model.getIsInternal() != null){
			OfficeCodeTypeEnum currentEnum = OfficeCodeTypeEnum.getEnum(model.getIsInternal().toString());
			if(currentEnum.equals(octe)){
				return true;
			}else{
				return false;
			}
		}else{
			return false;
		}
	}

	@Override
	public boolean isMatchOfficeCodeTypeByString(String enumVlue)throws Exception {
		OfficeCodeTypeEnum e = OfficeCodeTypeEnum.getEnum(enumVlue);
		boolean isMatch = false;
		try { isMatch=this.isMatchOfficeCodeType(e);}
				catch (Exception e2) {
					e2.printStackTrace();
				}
		return isMatch;
		
		
	}
	
}
