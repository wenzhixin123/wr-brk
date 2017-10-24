package com.sinotrans.gd.wlp.common.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

import com.sinotrans.gd.wlp.common.agvInterface.*;
import com.sinotrans.gd.wlp.common.util.HttpClientUtil;
import com.sinotrans.gd.wlp.common.util.WMSClient;
import com.sinotrans.gd.wlp.common.web.RcUtil;
import com.sinotrans.gd.wlp.common.web.SinotransPageJson;
import com.sinotrans.gd.wlp.util.JsonUtil;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Service;

import com.sinotrans.framework.core.service.impl.BaseManagerImpl;
import com.sinotrans.gd.wlp.common.model.PodPointModel;
import com.sinotrans.gd.wlp.common.service.PodPointManager;

@Service
public class PodPointManagerImpl extends BaseManagerImpl implements PodPointManager {

	public PodPointModel get(String id) {
		return this.dao.get(PodPointModel.class, id);
	}

	public List<PodPointModel> getAll() {
		return this.dao.getAll(PodPointModel.class);
	}

	public List<PodPointModel> findByExample(PodPointModel example) {
		return this.dao.findByExample(example);
	}

	public PodPointModel save(PodPointModel model) {
		return this.dao.save(model);
	}

	public List<PodPointModel> saveAll(Collection<PodPointModel> models) {
		return this.dao.saveAll(models);
	}

	public void remove(PodPointModel model) {
		this.dao.remove(model);
	}

	public void removeAll(Collection<PodPointModel> models) {
		this.dao.removeAll(models);
	}

	public void removeByPk(String id) {
		this.dao.removeByPk(PodPointModel.class, id);
	}

	public void removeAllByPk(Collection<String> ids) {
		this.dao.removeAllByPk(PodPointModel.class, ids);
	}

	@Override
	public SinotransPageJson confirmBindPodPoint(PodPointModel podPointModel) {

		SinotransPageJson spj = new SinotransPageJson();
		ImsPodPointEntity imsPodPointEntity = new ImsPodPointEntity();
		String url = "http://172.28.29.30:80/rcs/services/rest/hikRpcService/bindPodAndBerth";
		try {
			podPointModel.setReqCode(UUID.randomUUID().toString().replace("-",""));
			podPointModel.setReqTime(new Date());
			podPointModel.setClientCode("HCWMS");
			podPointModel.setTokenCode("");
			podPointModel.setInterfaceName("");
			podPointModel.setIndBind("1");//绑定状态

			ImsPodPointConver imsPodPointConver = new ImsPodPointConver();
			BeanUtils.copyProperties(imsPodPointConver,podPointModel);
			BeanUtils.copyProperties(imsPodPointEntity,imsPodPointConver);
			imsPodPointEntity.setReqTime(RcUtil.date2String(new Date(),RcUtil.yyyy_MM_dd_HH_mm_ss));
			this.dao.save(podPointModel);

			/*Map<String,String> json_map = new HashMap<String, String>();
			json_map.put("json_parameters", JsonUtil.beanToJson(imsPodPointEntity));
			System.out.println(JsonUtil.beanToJson(imsPodPointEntity));

			WMSClient post_client = new WMSClient();
			String result_data = post_client.httpPostRequest(url,json_map);
			ImsCallBack<String> ims_callBack = (ImsCallBack) JsonUtil.jsonToBean(result_data, ImsCallBack.class);
			System.out.println(ims_callBack);*/

			System.out.println(JsonUtil.beanToJson(imsPodPointEntity));
			String result_data = HttpClientUtil.post(url,JsonUtil.beanToJson(imsPodPointEntity));

			System.out.println(result_data);
			ImsCallGenAgvBack ims_callBack = (ImsCallGenAgvBack) JsonUtil.jsonToBean(result_data, ImsCallGenAgvBack.class);
			System.out.println(ims_callBack);

			spj.setResult(false);
			spj.setMsg(ims_callBack.getMessage());
		} catch (Exception e) {
			log.error(e.getMessage());
			spj.setResult(false);
			spj.setError("绑定货架货位异常!");
		}
		return spj;
	}

}
