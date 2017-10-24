package com.sinotrans.gd.wlp.system.service;

import java.util.List;

import com.sinotrans.framework.core.service.BaseManager;
import com.sinotrans.gd.wlp.system.entity.ExtApiTokenEntity;
import com.sinotrans.gd.wlp.system.web.ExtApiTokenWebVo;

public interface ExtApiTokenManager extends BaseManager {
	List<ExtApiTokenEntity> saveClient(List<ExtApiTokenWebVo> tokenWebVoList);
	
	void deleteClient(ExtApiTokenWebVo tokenWebVo);
	
} 
