package com.sinotrans.gd.wlp.email.service;

import java.util.List;

import com.sinotrans.framework.core.service.BaseManager;
import com.sinotrans.gd.wlp.email.entity.EmailMainContentEntity;

public interface GenerateEmailManager extends BaseManager{
	/*
	 *  获取需要发送邮件的配舱信息
	 */
	public List<EmailMainContentEntity> getAllPreManifestBillData();
	/*
	 *  获取需要发送邮件的新舱单发送信息
	 */
	public List<EmailMainContentEntity> getAllNewManifestBillData();
	/*
	 *  获取需要发送邮件的放行条信息
	 */
	public List<EmailMainContentEntity> getAllCustomsBillData();
	/*
	 *  获取需要发送邮件的广运录入走船信息
	 */
	public List<EmailMainContentEntity> getAllShipDepartData();
}
