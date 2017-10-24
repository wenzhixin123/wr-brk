package com.sinotrans.gd.wlp.basicdata.service.impl;

import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Service;

import com.sinotrans.framework.core.service.impl.BaseManagerImpl;
import com.sinotrans.gd.wlp.basicdata.model.BasVesselModel;
import com.sinotrans.gd.wlp.basicdata.service.BasVesselManager;

@Service
public class BasVesselManagerImpl extends BaseManagerImpl implements BasVesselManager {

	
	public BasVesselModel get(String id) {
		return this.dao.get(BasVesselModel.class, id);
	}

	public List<BasVesselModel> getAll() {
		return this.dao.getAll(BasVesselModel.class);
	}

	public List<BasVesselModel> findByExample(BasVesselModel example) {
		return this.dao.findByExample(example);
	}

	public BasVesselModel save(BasVesselModel model) {
		return this.dao.save(model);
	}

	public List<BasVesselModel> saveAll(Collection<BasVesselModel> models) {
		return this.dao.saveAll(models);
	}

	public void remove(BasVesselModel model) {
		this.dao.remove(model);
	}

	public void removeAll(Collection<BasVesselModel> models) {
		this.dao.removeAll(models);
	}

	public void removeByPk(String id) {
		this.dao.removeByPk(BasVesselModel.class, id);
	}

	public void removeAllByPk(Collection<String> ids) {
		this.dao.removeAllByPk(BasVesselModel.class, ids);
	}

	@Override
	public BasVesselModel findByCode(String vesselCode) {
		BasVesselModel example=new BasVesselModel();
		example.setVesselCode(vesselCode);
		List<BasVesselModel> models=this.findByExample(example);
		if(models.size()>0){
			example=models.get(0);
			return example;
		}
		return null;
	}

	@Override
	public String findVesselNameEnByCode(String vesselCode){
		BasVesselModel example=new BasVesselModel();
		String vesselNameEn=null;
		example.setVesselCode(vesselCode);
		List<BasVesselModel> models=this.findByExample(example);
		if(models.size()>0){
			vesselNameEn=models.get(0).getVesselNameEn();
			return vesselNameEn;
		}
		return null;
	}

	@Override
	public BasVesselModel findByLoacalCode(String localVesselCode,String depot) {
		BasVesselModel vesselModel = null;
		/*String dcsVessel = dcsMappingInfoManager.getDcsInfoCodeByMappingTypeLocalInfoCodePortAreaCodeNoException(CommonUtil.BAS_VESSEL,
									localVesselCode, depot);
		if(StringUtils.isNotBlank(dcsVessel)){
			vesselModel = this.findByCode(dcsVessel);
		}*/
		return vesselModel;
	}

	@Override
	public BasVesselModel findByLicenseCode(String customsLicenseCode) {
		BasVesselModel vesselModel = new BasVesselModel();
		vesselModel.setCustomsLicenseNo(customsLicenseCode);
		List<BasVesselModel> models=this.findByExample(vesselModel);
		if(models.size()>0){
			return models.get(0);
		}else{
			return null;
		}
	}
}
