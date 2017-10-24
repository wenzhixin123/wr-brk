/**
 * 
 */
package com.sinotrans.gd.wlp.system.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sinotrans.gd.wlp.common.web.RcUtil;
import com.sinotrans.gd.wlp.common.web.SinotransBaseAction;
import com.sinotrans.gd.wlp.system.entity.SessionContextUserEntity;
import com.sinotrans.gd.wlp.system.model.SysMenuGroupModel;
import com.sinotrans.gd.wlp.system.model.SysMenuItemModel;
import com.sinotrans.gd.wlp.system.model.SysModuleModel;
import com.sinotrans.gd.wlp.system.model.SysRoleUserModel;
import com.sinotrans.gd.wlp.system.model.SysUserModel;
import com.sinotrans.gd.wlp.system.service.SysMenuGroupManager;
import com.sinotrans.gd.wlp.system.service.SysMenuItemManager;
import com.sinotrans.gd.wlp.system.service.SysModuleManager;
import com.sinotrans.gd.wlp.system.service.SysRoleMenuItemManager;
import com.sinotrans.gd.wlp.system.service.SysRoleUserManager;
import com.sinotrans.gd.wlp.system.service.SysUserManager;
import com.sinotrans.gd.wlp.util.CommonUtil;

/**
 * @author sky
 * 
 *         系统首页Action类
 * 
 */
@Service
public class SystemIndexAction extends SinotransBaseAction {

	@Autowired
	private SysUserManager sysUserManager;
	@Autowired
	private SysRoleUserManager sysRoleUserManager;
	@Autowired
	private SysRoleMenuItemManager sysRoleMenuItemManager;
	@Autowired
	private SysMenuItemManager sysMenuItemManager;
	@Autowired
	private SysMenuGroupManager sysMenuGroupManager;
	@Autowired
	private SysModuleManager sysModuleManager;

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		List<SysMenuItemModel> menuItems = new ArrayList<SysMenuItemModel>();
		List<SysMenuGroupModel> menuGroups = new ArrayList<SysMenuGroupModel>();
		List<SysModuleModel> modules = new ArrayList<SysModuleModel>();
		List<String> sysRoleUuid = new ArrayList<String>();
		List<String> sysMenuItemUuid = new ArrayList<String>();
		List<String> sysMenuGroupUuid = new ArrayList<String>();
		List<String> sysModuleUuid = new ArrayList<String>();
		SessionContextUserEntity s= getUser();
		if (!RcUtil.isEmpty(getUser())) {

			// 根据用户编码找出用户
			SysUserModel user = new SysUserModel();
			user.setUserCode(getUser().getUserId());
			List<SysUserModel> users = sysUserManager.findByExample(user);

			// 找出该用户所拥有的角色
			List<SysRoleUserModel> roleUsers = null;
			if (!RcUtil.isEmpty(users) && users.size() > 0) {
				SysRoleUserModel roleUser = new SysRoleUserModel();
				roleUser.setUserUuid(users.get(0).getUserUuid());
				roleUsers = sysRoleUserManager.findByExample(roleUser);
//				getUser().setPortAreaCode(users.get(0).getPortAreaCode());
//				getUser().setSingleAreaCode(users.get(0).getSingleAreaCode());
			}

			// 根据角色获得menuItem
			if (!RcUtil.isEmpty(roleUsers) && roleUsers.size() > 0) {
				sysRoleUuid = new ArrayList<String>();
				sysMenuItemUuid = new ArrayList<String>();
				for (SysRoleUserModel srum : roleUsers) {
					sysRoleUuid.add(srum.getSysRoleUuid());
				}
				sysMenuItemUuid = sysRoleMenuItemManager
						.findItemIdByRole(sysRoleUuid);
				menuItems = sysMenuItemManager.findByIds(sysMenuItemUuid);
			}

			// 根据menuItem获得menuGroup
			if (!RcUtil.isEmpty(menuItems) && menuItems.size() > 0) {
				for (SysMenuItemModel smim : menuItems) {
					sysMenuGroupUuid.add(smim.getSysMenuGroupUuid());
				}
				menuGroups = sysMenuGroupManager
						.findByGroupId(sysMenuGroupUuid);
			}

			// 根据menuGroup获得module
			if (!RcUtil.isEmpty(menuGroups) && menuGroups.size() > 0) {
				for (SysMenuGroupModel smgm : menuGroups) {
					sysModuleUuid.add(smgm.getSysModuleUuid());
				}
				modules = sysModuleManager.findByModuleIds(sysModuleUuid);
			}
		}

		request.getSession().setAttribute("menuItems", menuItems);
		request.getSession().setAttribute("menuGroups", menuGroups);
		request.getSession().setAttribute("modules", modules);
		request.getSession().setAttribute("username", getUser().getUsername());
		Cookie cookie = new Cookie(CommonUtil.FROS_CUSTOM_LOCALE_COOKIE,
				getLocale(request).getCountry());
		cookie.setPath(request.getContextPath());
		cookie.setMaxAge(60 * 60 * 24 * 100);// 保存100天
		response.addCookie(cookie);// 设置语言Cookie
		return mapping.findForward("portal");
	}

}
