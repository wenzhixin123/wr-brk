package com.sinotrans.gd.wlp.email.service;

import java.util.List;

import com.sinotrans.framework.core.service.BaseManager;
import com.sinotrans.gd.wlp.email.entity.EmailMainContentEntity;

public interface SendEmailManager extends BaseManager{
	public void sendEmailByDetail(List<EmailMainContentEntity> mainContent);
	
	public void sendEmailByType(String operationType);
}
