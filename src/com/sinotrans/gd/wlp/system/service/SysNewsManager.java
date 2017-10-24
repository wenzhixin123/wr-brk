package com.sinotrans.gd.wlp.system.service;

import java.io.InputStream;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.sinotrans.framework.core.service.BaseManager;
import com.sinotrans.gd.wlp.common.web.SinotransPageJson;
import com.sinotrans.gd.wlp.system.entity.SystemNewsEntity;
import com.sinotrans.gd.wlp.system.model.SysNewsModel;

public interface SysNewsManager extends BaseManager {

	SysNewsModel get(String id);

	List<SysNewsModel> getAll();

	List<SysNewsModel> findByExample(SysNewsModel example);

	SysNewsModel save(SysNewsModel model);

	List<SysNewsModel> saveAll(Collection<SysNewsModel> models);

	void remove(SysNewsModel model);

	void removeAll(Collection<SysNewsModel> models);

	void removeByPk(String id);

	void removeAllByPk(Collection<String> ids);

	SinotransPageJson saveSystemNews(String msg, String officeCode,
			byte[] content, String urlAddress) throws Exception;

	SinotransPageJson uploadIfFile(String path, String businessType,
			String[] modelIds, String fileName, InputStream stream,
			String officeCode) throws Exception;

	List<SysNewsModel> findNewsToCombo(String officeCode, String newsType,
			int numNews) throws Exception;

	public SystemNewsEntity queryContentToUuid(String uuid) throws Exception;

	public boolean deleteNewsAndBlob(String uuid);

	public SinotransPageJson validateAndCancel(String data, String officeCode,
			byte[] content, String urlAddress) throws Exception;

	Map<String, String> getNewsTypeAndOffriceCode(String officeCode, String newsType);
}
