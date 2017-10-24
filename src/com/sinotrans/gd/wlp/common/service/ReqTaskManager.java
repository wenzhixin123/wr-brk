package com.sinotrans.gd.wlp.common.service;

import java.util.Collection;
import java.util.List;

import com.sinotrans.framework.core.service.BaseManager;
import com.sinotrans.gd.wlp.common.agvInterface.ImsCallBack;
import com.sinotrans.gd.wlp.common.agvInterface.ImsNotifyParam;
import com.sinotrans.gd.wlp.common.model.ReqTaskModel;
import com.sinotrans.gd.wlp.common.web.SinotransPageJson;

public interface ReqTaskManager extends BaseManager {

	ReqTaskModel get(String id);

	List<ReqTaskModel> getAll();

	List<ReqTaskModel> findByExample(ReqTaskModel example);

	ReqTaskModel save(ReqTaskModel model);

	List<ReqTaskModel> saveAll(Collection<ReqTaskModel> models);

	void remove(ReqTaskModel model);

	void removeAll(Collection<ReqTaskModel> models);

	void removeByPk(String id);

	void removeAllByPk(Collection<String> ids);

	SinotransPageJson saveReqTask(ReqTaskModel reqTaskModel);

	SinotransPageJson sendReqTask(List<String> reqTaskIds);

	SinotransPageJson cancelReqTask(List<String> reqTaskIds);

	SinotransPageJson resetPriority(String reqTaskId,String priority);

	SinotransPageJson queryReqTask(List<String> reqTaskIds);

	ImsCallBack<String> imsReqTaskNotify(ImsNotifyParam imsNotifyParam);

	SinotransPageJson continueTaskNextAction(String taskCode);
}
