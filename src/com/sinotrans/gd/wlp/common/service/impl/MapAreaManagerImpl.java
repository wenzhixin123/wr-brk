package com.sinotrans.gd.wlp.common.service.impl;

import java.util.*;

import com.sinotrans.gd.wlp.common.agvInterface.*;
import com.sinotrans.gd.wlp.common.util.HttpClientUtil;
import com.sinotrans.gd.wlp.common.util.WMSClient;
import com.sinotrans.gd.wlp.common.web.RcUtil;
import com.sinotrans.gd.wlp.common.web.SinotransPageJson;
import com.sinotrans.gd.wlp.util.JsonUtil;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import com.sinotrans.framework.core.service.impl.BaseManagerImpl;
import com.sinotrans.gd.wlp.common.model.MapAreaModel;
import com.sinotrans.gd.wlp.common.service.MapAreaManager;

@Service
public class MapAreaManagerImpl extends BaseManagerImpl implements MapAreaManager {

	public MapAreaModel get(String id) {
		return this.dao.get(MapAreaModel.class, id);
	}

	public List<MapAreaModel> getAll() {
		return this.dao.getAll(MapAreaModel.class);
	}

	public List<MapAreaModel> findByExample(MapAreaModel example) {
		return this.dao.findByExample(example);
	}

	public MapAreaModel save(MapAreaModel model) {
		return this.dao.save(model);
	}

	public List<MapAreaModel> saveAll(Collection<MapAreaModel> models) {
		return this.dao.saveAll(models);
	}

	public void remove(MapAreaModel model) {
		this.dao.remove(model);
	}

	public void removeAll(Collection<MapAreaModel> models) {
		this.dao.removeAll(models);
	}

	public void removeByPk(String id) {
		this.dao.removeByPk(MapAreaModel.class, id);
	}

	public void removeAllByPk(Collection<String> ids) {
		this.dao.removeAllByPk(MapAreaModel.class, ids);
	}

	@Override
	public SinotransPageJson synchronyMapArea() {

		SinotransPageJson spj = new SinotransPageJson();
		String url = "http://172.28.29.30:80/rcs/services/hikRpcService/syncMapDatas";
		ImsMapAreaRequest imsMapAreaRequest = new ImsMapAreaRequest();

		try {
			imsMapAreaRequest.setReqCode(UUID.randomUUID().toString().replace("-",""));
			imsMapAreaRequest.setReqTime(RcUtil.date2String(new Date(),RcUtil.yyyy_MM_dd_HH_mm_ss));
			imsMapAreaRequest.setClientCode("HCWMS");
			imsMapAreaRequest.setTokenCode("");
			imsMapAreaRequest.setInterfaceName("");
			imsMapAreaRequest.setMapShortName("");

		/*	Map<String,String> json_map = new HashMap<String, String>();
			json_map.put("json_parameters", JsonUtil.beanToJson(imsMapAreaRequest));
			System.out.println(JsonUtil.beanToJson(imsMapAreaRequest));

			WMSClient post_client = new WMSClient();
			String result_data = post_client.httpPostRequest(url,json_map);
			ImsCallBack<ImsMapAreaResult> ims_callBack = (ImsCallBack) JsonUtil.jsonToBean(result_data, ImsCallBack.class);
			System.out.println(ims_callBack);*/

			System.out.println(JsonUtil.beanToJson(imsMapAreaRequest));
			String result_data = HttpClientUtil.post(url,JsonUtil.beanToJson(imsMapAreaRequest));

			System.out.println(result_data);
			ImsCallBack<ImsMapAreaResult>  ims_callBack = (ImsCallBack) JsonUtil.jsonToBean(result_data, ImsCallBack.class);
			System.out.println(ims_callBack);

			//保存地图信息
			if(ims_callBack != null){
				if(CollectionUtils.isNotEmpty(ims_callBack.getData())){
					for(ImsMapAreaResult var : ims_callBack.getData()){
						MapAreaModel mapAreaModel = new MapAreaModel();
						BeanUtils.copyProperties(mapAreaModel,var);
						this.dao.save(mapAreaModel);
					}
				}
			}

			spj.setResult(true);
			spj.setMsg("获取成功！");
		} catch (Exception e) {
			log.error(e.getMessage());
			spj.setResult(false);
			spj.setError("获取地码位置失败!");
		}
		return spj;
	}

}
