/**
 * 
 */
package com.sinotrans.gd.wlp.common.web;

import org.apache.struts.actions.DispatchAction;

import com.sinotrans.gd.wlp.system.entity.SessionContextUserEntity;

/**
 * @author sky
 * 
 *         外运Struts 基础Action 类
 * 
 */

public class SinotransBaseAction extends DispatchAction {

	protected SessionContextUserEntity getUser() {
		SessionContextUserEntity s = SessionContextUserEntity.currentUser();
		return s;
	}

}
