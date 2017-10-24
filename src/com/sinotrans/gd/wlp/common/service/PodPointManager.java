package com.sinotrans.gd.wlp.common.service;

import java.util.Collection;
import java.util.List;

import com.sinotrans.framework.core.service.BaseManager;
import com.sinotrans.gd.wlp.common.model.PodPointModel;
import com.sinotrans.gd.wlp.common.web.SinotransPageJson;

public interface PodPointManager extends BaseManager {

	PodPointModel get(String id);

	List<PodPointModel> getAll();

	List<PodPointModel> findByExample(PodPointModel example);

	PodPointModel save(PodPointModel model);

	List<PodPointModel> saveAll(Collection<PodPointModel> models);

	void remove(PodPointModel model);

	void removeAll(Collection<PodPointModel> models);

	void removeByPk(String id);

	void removeAllByPk(Collection<String> ids);

	SinotransPageJson confirmBindPodPoint(PodPointModel podPointModel);
}
