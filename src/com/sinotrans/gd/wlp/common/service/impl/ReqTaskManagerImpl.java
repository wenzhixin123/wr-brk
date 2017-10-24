package com.sinotrans.gd.wlp.common.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

import com.sinotrans.framework.core.support.Condition;
import com.sinotrans.gd.wlp.common.agvInterface.*;
import com.sinotrans.gd.wlp.common.util.HttpClientUtil;
import com.sinotrans.gd.wlp.common.util.WMSClient;
import com.sinotrans.gd.wlp.common.web.RcUtil;
import com.sinotrans.gd.wlp.common.web.SinotransPageJson;
import com.sinotrans.gd.wlp.util.CommonUtil;
import com.sinotrans.gd.wlp.util.JsonUtil;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.sinotrans.framework.core.service.impl.BaseManagerImpl;
import com.sinotrans.gd.wlp.common.model.ReqTaskModel;
import com.sinotrans.gd.wlp.common.service.ReqTaskManager;

@Service
public class ReqTaskManagerImpl extends BaseManagerImpl implements ReqTaskManager {

	public ReqTaskModel get(String id) {
		return this.dao.get(ReqTaskModel.class, id);
	}

	public List<ReqTaskModel> getAll() {
		return this.dao.getAll(ReqTaskModel.class);
	}

	public List<ReqTaskModel> findByExample(ReqTaskModel example) {
		return this.dao.findByExample(example);
	}

	public ReqTaskModel save(ReqTaskModel model) {
		return this.dao.save(model);
	}

	public List<ReqTaskModel> saveAll(Collection<ReqTaskModel> models) {
		return this.dao.saveAll(models);
	}

	public void remove(ReqTaskModel model) {
		this.dao.remove(model);
	}

	public void removeAll(Collection<ReqTaskModel> models) {
		this.dao.removeAll(models);
	}

	public void removeByPk(String id) {
		this.dao.removeByPk(ReqTaskModel.class, id);
	}

	public void removeAllByPk(Collection<String> ids) {
		this.dao.removeAllByPk(ReqTaskModel.class, ids);
	}

	@Override
	public SinotransPageJson saveReqTask(ReqTaskModel reqTaskModel) {
		SinotransPageJson spj = new SinotransPageJson();
		//保存实体
//		reqTaskModel.setReqTime(new Date());
		reqTaskModel.setReqCode(UUID.randomUUID().toString().replace("-",""));
//		reqTaskModel.setTaskStatus(1);//草稿
		reqTaskModel.setCreateTime(new Date());
		this.dao.save(reqTaskModel);
		spj.setResult(true);
		spj.setMsg("保存成功!");
		return spj;
	}

	@Override
	public SinotransPageJson sendReqTask(List<String> reqTaskIds) {
		String url = "http://172.28.29.30:80/rcs/services/rest/hikRpcService/genAgvSchedulingTask";
		SinotransPageJson spj = new SinotransPageJson();

		StringBuffer sb = new StringBuffer();
		for(String reqTaskId : reqTaskIds){

			try {
				ReqTaskModel reqTaskModel = this.dao.get(ReqTaskModel.class,reqTaskId);
				reqTaskModel.setReqTime(new Date());
				ReqTaskEntity reqTaskEntity = new ReqTaskEntity();
				ReqTaskEntityConver reqTaskEntityConver = new ReqTaskEntityConver();


				BeanUtils.copyProperties(reqTaskEntityConver,reqTaskModel);
				BeanUtils.copyProperties(reqTaskEntity,reqTaskEntityConver);
				reqTaskEntity.setReqTime(RcUtil.date2String(new Date(),RcUtil.yyyy_MM_dd_HH_mm_ss));

				List<String> userCallCodePaths = new ArrayList<String>();
				if(StringUtils.isNotEmpty(reqTaskModel.getUserCallCodePath())) {
					String[] arr = reqTaskModel.getUserCallCodePath().split(",");
					userCallCodePaths = Arrays.asList(arr);
				}
				reqTaskEntity.setUserCallCodePath(userCallCodePaths);

//				Map<String,String> json_map = new HashMap<String, String>();
//				json_map.put("", JsonUtil.beanToJson(reqTaskEntity));
				System.out.println(JsonUtil.beanToJson(reqTaskEntity));
//				Map<String,String> json_map = new HashMap<>();
				/*WMSClient post_client = new WMSClient();
				String result_data = post_client.httpPostRequest(url,json_map);*/
				String result_data = HttpClientUtil.post(url,JsonUtil.beanToJson(reqTaskEntity));

				System.out.println(result_data);
				ImsCallGenAgvBack ims_callBack = (ImsCallGenAgvBack) JsonUtil.jsonToBean(result_data, ImsCallGenAgvBack.class);
				System.out.println(ims_callBack);
				if(!"0".equals(ims_callBack.getCode())){
					sb.append("请求编号"+reqTaskEntity.getReqCode()+ ims_callBack.getMessage());
				}else {
					reqTaskModel.setTaskStatus(2);
					this.dao.save(reqTaskModel);
				}

			} catch (Exception e) {
//				e.printStackTrace();
				log.error(e.getMessage());
			}
		}
		if (StringUtils.isNotEmpty(sb.toString())){
			spj.setError(sb.toString());
			spj.setResult(false);
			return spj;
		}else {
			spj.setMsg("发送成功!");
			spj.setResult(true);
			return spj;
		}
	}

	@Override
	public SinotransPageJson cancelReqTask(List<String> reqTaskIds) {
		String url = "http://172.28.29.30:80/rcs/services/rest/hikRpcService/cancelTask";
		SinotransPageJson spj = new SinotransPageJson();

		StringBuffer sb = new StringBuffer();
		for(String reqTaskId : reqTaskIds){

			try {
				ReqTaskModel reqTaskModel = this.dao.get(ReqTaskModel.class,reqTaskId);
				reqTaskModel.setReqTime(new Date());
				ReqTaskEntity reqTaskEntity = new ReqTaskEntity();
				reqTaskEntity.setReqCode(reqTaskModel.getReqCode());
//				reqTaskEntity.setReqTime(reqTaskModel.getReqTime());
				reqTaskEntity.setReqTime(RcUtil.date2String(new Date(),RcUtil.yyyy_MM_dd_HH_mm_ss));

				reqTaskEntity.setClientCode(reqTaskModel.getClientCode());
				reqTaskEntity.setTokenCode(reqTaskModel.getTokenCode());
				reqTaskEntity.setInterfaceName(reqTaskModel.getInterfaceName());
				reqTaskEntity.setTaskCode(reqTaskModel.getTaskCode());


				/*Map<String,String> json_map = new HashMap<String, String>();
				json_map.put("json_parameters", JsonUtil.beanToJson(reqTaskEntity));
				System.out.println(JsonUtil.beanToJson(reqTaskEntity));

				WMSClient post_client = new WMSClient();
				String result_data = post_client.httpPostRequest(url,json_map);
				ImsCallBack ims_callBack = (ImsCallBack) JsonUtil.jsonToBean(result_data, ImsCallBack.class);
				System.out.println(ims_callBack);*/

				System.out.println(JsonUtil.beanToJson(reqTaskEntity));
				String result_data = HttpClientUtil.post(url,JsonUtil.beanToJson(reqTaskEntity));

				System.out.println(result_data);
				ImsCallGenAgvBack ims_callBack = (ImsCallGenAgvBack) JsonUtil.jsonToBean(result_data, ImsCallGenAgvBack.class);
				System.out.println(ims_callBack);

				if(!"0".equals(ims_callBack.getCode())){
					sb.append("请求编号"+reqTaskEntity.getReqCode()+ ims_callBack.getMessage());
				}else {
					this.dao.save(reqTaskModel);
				}
			} catch (Exception e) {
//				e.printStackTrace();
				log.error(e.getMessage());
			}
		}
		//查询删除后任务单状态
		queryReqTask(reqTaskIds);

		if (StringUtils.isNotEmpty(sb.toString())){
			spj.setError(sb.toString());
			spj.setResult(false);
			return spj;
		}else {
			spj.setMsg("取消成功!");
			spj.setResult(true);
			return spj;
		}
	}

	@Override
	public SinotransPageJson resetPriority(String reqTaskId,String priority) {
		String url = "http://172.28.29.30:80/rcs/services/rest/hikRpcService/setTaskPriority";
		SinotransPageJson spj = new SinotransPageJson();

		StringBuffer sb = new StringBuffer();


			try {
				ReqTaskModel reqTaskModel = this.dao.get(ReqTaskModel.class,reqTaskId);
				reqTaskModel.setReqTime(new Date());
				reqTaskModel.setPriority(priority);

				ReqTaskPriorityEntity reqTaskPriorityEntity = new ReqTaskPriorityEntity();

				reqTaskPriorityEntity.setReqCode(reqTaskModel.getReqCode());
				reqTaskPriorityEntity.setReqTime(reqTaskModel.getReqTime());
				reqTaskPriorityEntity.setClientCode(reqTaskModel.getClientCode());
				reqTaskPriorityEntity.setTokenCode(reqTaskModel.getTokenCode());
				reqTaskPriorityEntity.setInterfaceName(reqTaskModel.getInterfaceName());

				ReqTaskPriorityEntity.PriorityEntity priorityEntity =  new ReqTaskPriorityEntity.PriorityEntity();
				priorityEntity.setPriority(priority);//设置优先级
				priorityEntity.setTaskCode(reqTaskModel.getTaskCode());
				List<ReqTaskPriorityEntity.PriorityEntity> priorityEntities = new ArrayList<>();
				priorityEntities.add(priorityEntity);
				reqTaskPriorityEntity.setPriorities(priorityEntities);


				/*Map<String,String> json_map = new HashMap<String, String>();
				json_map.put("json_parameters", JsonUtil.beanToJson(reqTaskPriorityEntity));
				System.out.println(JsonUtil.beanToJson(reqTaskPriorityEntity));

				WMSClient post_client = new WMSClient();
				String result_data = post_client.httpPostRequest(url,json_map);
				ImsCallBack ims_callBack = (ImsCallBack) JsonUtil.jsonToBean(result_data, ImsCallBack.class);
				System.out.println(ims_callBack);*/


				System.out.println(JsonUtil.beanToJson(reqTaskPriorityEntity));
				String result_data = HttpClientUtil.post(url,JsonUtil.beanToJson(reqTaskPriorityEntity));

				System.out.println(result_data);
				ImsCallGenAgvBack ims_callBack = (ImsCallGenAgvBack) JsonUtil.jsonToBean(result_data, ImsCallGenAgvBack.class);
				System.out.println(ims_callBack);


				if(!"0".equals(ims_callBack.getCode())){
					sb.append("请求编号" + reqTaskPriorityEntity.getReqCode()+ ims_callBack.getMessage());
				}else {
					this.dao.save(reqTaskModel);
				}

			} catch (Exception e) {
//				e.printStackTrace();
				log.error(e.getMessage());
			}

		if (StringUtils.isNotEmpty(sb.toString())){
			spj.setError(sb.toString());
			spj.setResult(false);
			return spj;
		}else {
			spj.setMsg("设置成功!");
			spj.setResult(true);
			return spj;
		}
	}

	@Override
	public SinotransPageJson queryReqTask(List<String> reqTaskIds) {
		String url = "http://172.28.29.30:80/rcs/services/rest/hikRpcService/queryTaskStatus";
		SinotransPageJson spj = new SinotransPageJson();

		List<String> taskCodes = new ArrayList<>();
		for(String reqTaskId : reqTaskIds){
			ReqTaskModel reqTaskModel = this.dao.get(ReqTaskModel.class,reqTaskId);
			taskCodes.add(reqTaskModel.getTaskCode());
		}
		try {

			ReqTaskQueryStatusEntity reqTaskQueryStatusEntity = new ReqTaskQueryStatusEntity();
			reqTaskQueryStatusEntity.setReqCode(UUID.randomUUID().toString().replace("-",""));
			reqTaskQueryStatusEntity.setReqTime(new Date());
			reqTaskQueryStatusEntity.setClientCode("HCWMS");
			reqTaskQueryStatusEntity.setTokenCode("");
			reqTaskQueryStatusEntity.setInterfaceName("");
			reqTaskQueryStatusEntity.setTaskCodes(taskCodes);

			/*Map<String,String> json_map = new HashMap<String, String>();
			json_map.put("json_parameters", JsonUtil.beanToJson(reqTaskQueryStatusEntity));
			System.out.println(JsonUtil.beanToJson(reqTaskQueryStatusEntity));

			WMSClient post_client = new WMSClient();
			String result_data = post_client.httpPostRequest(url,json_map);
			ImsCallBack<ImsQueryStatusResult> ims_callBack = (ImsCallBack) JsonUtil.jsonToBean(result_data, ImsCallBack.class);
			System.out.println(ims_callBack);*/


			System.out.println(JsonUtil.beanToJson(reqTaskQueryStatusEntity));
			String result_data = HttpClientUtil.post(url,JsonUtil.beanToJson(reqTaskQueryStatusEntity));

			System.out.println(result_data);
			ImsCallBack<Map<String,Object>> ims_callBack = (ImsCallBack) JsonUtil.jsonToBean(result_data, ImsCallBack.class);
			System.out.println(ims_callBack);

			//取出data数据里面的类型状态
			if(CollectionUtils.isNotEmpty(ims_callBack.getData())){
				for(Map<String,Object> var : ims_callBack.getData()){
					List<ReqTaskModel> reqTasks = this.dao.createCommonQuery(ReqTaskModel.class)
															.addCondition(Condition.eq(ReqTaskModel.FieldNames.taskCode, var.get("taskCode")))
															.query();
					if (CollectionUtils.isEmpty(reqTasks)){
						log.error("该任务单单号不存在wms系统:" + var.get("taskCode"));
						continue;
					}
					ReqTaskModel reqTaskModel = reqTasks.get(0);
					int status_code = 0;
					if("发送异常".equals(var.get("taskStatus").toString())) status_code = 0;
					if("已创建".equals(var.get("taskStatus").toString())) status_code = 1;
					if("正在执行".equals(var.get("taskStatus").toString())) status_code = 2;
					if("正在发送".equals(var.get("taskStatus").toString())) status_code = 3;
					if("正在取消".equals(var.get("taskStatus").toString())) status_code = 4;
					if("取消完成".equals(var.get("taskStatus").toString())) status_code = 5;
					if("正在重发".equals(var.get("taskStatus").toString())) status_code = 6;
					if("已结束".equals(var.get("taskStatus").toString())) status_code = 9;
					if("被打断".equals(var.get("taskStatus").toString())) status_code = 10;

					reqTaskModel.setTaskStatus(status_code);
					this.dao.save(reqTaskModel);
				}
			}
			spj.setMsg("查询成功!");
			spj.setResult(true);
			return spj;
		} catch (Exception e) {
//				e.printStackTrace();
			log.error(e.getMessage());
			spj.setError("查询异常");
			spj.setResult(false);
			return spj;
		}
	}

	@Override
	public ImsCallBack<String> imsReqTaskNotify(ImsNotifyParam imsNotifyParam) {

		ImsCallBack<String> imsCallBack = new ImsCallBack<>();

		List<ReqTaskModel> reqTasks = this.dao.createCommonQuery(ReqTaskModel.class)
				.addCondition(Condition.eq(ReqTaskModel.FieldNames.taskCode, imsNotifyParam.getTaskCode()))
				.query();
		if (CollectionUtils.isEmpty(reqTasks)){
			log.error("该任务单单号不存在wms系统:" + imsNotifyParam.getTaskCode());
			imsCallBack.setMessage("该任务单单号不存在wms系统:" + imsNotifyParam.getTaskCode());
			imsCallBack.setCode("99");
			imsCallBack.setReqCode(imsNotifyParam.getReqCode());
			return imsCallBack;
		}
		ReqTaskModel reqTaskModel = reqTasks.get(0);
		reqTaskModel.setCurrentCallCode(imsNotifyParam.getCurrentCallCode());
		this.dao.save(reqTaskModel);

		//如果是入库模板中的第一个任务组，则调用一次执行下一个人任务接口
		if("firstTask".equals(imsNotifyParam.getMethod())){
			continueTaskNextAction(imsNotifyParam.getTaskCode());
		}

		imsCallBack.setMessage("成功");
		imsCallBack.setReqCode(imsNotifyParam.getReqCode());
		imsCallBack.setCode("0");
		return imsCallBack;
	}


	public SinotransPageJson continueTaskNextAction(String taskCode){

		SinotransPageJson spj = new SinotransPageJson();
		String url = "http://172.28.29.30:80/rcs/services/rest/hikRpcService/continueTask";
		try {
			ReqTaskContinueEntity reqTaskContinueEntity = new ReqTaskContinueEntity();

			reqTaskContinueEntity.setReqCode(UUID.randomUUID().toString().replace("-",""));
			reqTaskContinueEntity.setReqTime(RcUtil.date2String(new Date(),RcUtil.yyyy_MM_dd_HH_mm_ss));
			reqTaskContinueEntity.setTaskCode(taskCode);

			List<ReqTaskModel> reqTasks = this.dao.createCommonQuery(ReqTaskModel.class)
													.addCondition(Condition.eq(ReqTaskModel.FieldNames.taskCode, taskCode))
													.query();
			reqTaskContinueEntity.setUserCallCode(reqTasks.get(0).getUserCallCode());
//			reqTaskContinueEntity.setTaskSeq("4");//写死的任务队列


			System.out.println(JsonUtil.beanToJson(reqTaskContinueEntity));
			String result_data = HttpClientUtil.post(url, JsonUtil.beanToJson(reqTaskContinueEntity));

			System.out.println(result_data);
			ImsCallGenAgvBack ims_callBack = (ImsCallGenAgvBack) JsonUtil.jsonToBean(result_data, ImsCallGenAgvBack.class);
			System.out.println(ims_callBack);

			if(!"0".equals(ims_callBack.getCode())){
				spj.setResult(false);
				spj.setError(ims_callBack.getMessage());
			}else {
				spj.setResult(true);
				spj.setMsg("执行成功!");
			}
		} catch (Exception e) {
			log.error("继续执行下个任务异常:=================="+e.getMessage());
			spj.setResult(false);
			spj.setError("继续执行下个任务异常!");
		}
		return spj;
	}

}
