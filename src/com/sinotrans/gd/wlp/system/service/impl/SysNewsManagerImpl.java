package com.sinotrans.gd.wlp.system.service.impl;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.stereotype.Service;

import com.sinotrans.framework.core.exception.ApplicationException;
import com.sinotrans.framework.core.service.impl.BaseManagerImpl;
import com.sinotrans.framework.core.support.Condition;
import com.sinotrans.gd.wlp.basicdata.model.BasBlobModel;
import com.sinotrans.gd.wlp.basicdata.service.BasBlobManager;
import com.sinotrans.gd.wlp.common.service.SQLQueryManager;
import com.sinotrans.gd.wlp.common.web.RcUtil;
import com.sinotrans.gd.wlp.common.web.SinotransPageJson;
import com.sinotrans.gd.wlp.system.entity.SystemNewsEntity;
import com.sinotrans.gd.wlp.system.model.SysNewsModel;
import com.sinotrans.gd.wlp.system.model.SysUserModel;
import com.sinotrans.gd.wlp.system.service.SysNewsManager;
import com.sinotrans.gd.wlp.util.CommonUtil;
import com.sinotrans.gd.wlp.util.JsonUtil;

@Service
public class SysNewsManagerImpl extends BaseManagerImpl implements
		SysNewsManager {

	@Autowired
	private BasBlobManager basBlobManager;

	@Autowired
	private SQLQueryManager sqlQueryManager;

	public SysNewsModel get(String id) {
		return this.dao.get(SysNewsModel.class, id);
	}

	public List<SysNewsModel> getAll() {
		return this.dao.getAll(SysNewsModel.class);
	}

	public List<SysNewsModel> findByExample(SysNewsModel example) {
		return this.dao.findByExample(example);
	}

	public SysNewsModel save(SysNewsModel model) {
		return this.dao.save(model);
	}

	public List<SysNewsModel> saveAll(Collection<SysNewsModel> models) {
		return this.dao.saveAll(models);
	}

	public void remove(SysNewsModel model) {
		this.dao.remove(model);
	}

	public void removeAll(Collection<SysNewsModel> models) {
		this.dao.removeAll(models);
	}

	public void removeByPk(String id) {
		this.dao.removeByPk(SysNewsModel.class, id);
	}

	public void removeAllByPk(Collection<String> ids) {
		this.dao.removeAllByPk(SysNewsModel.class, ids);
	}

	/*
	 * 保存文章信息
	 */
	@SuppressWarnings("static-access")
	@Override
	public SinotransPageJson saveSystemNews(String msg, String officeCode,
			byte[] content, String urlAddress) throws Exception {
		SinotransPageJson spj = new SinotransPageJson();
		DateFormat myDateFormat = new SimpleDateFormat(RcUtil.yyyy_MM_dd);
		SystemNewsEntity systemNewsEntity = (SystemNewsEntity) JsonUtil
				.jsonToBean(msg, SystemNewsEntity.class, myDateFormat);
		SysNewsModel sysNewsModel = new SysNewsModel();
		BeanUtils.copyProperties(sysNewsModel, systemNewsEntity);
		SysNewsModel snModel = null;
		SysNewsModel smodel = new SysNewsModel();
		if (!RcUtil.isEmpty(sysNewsModel)) {
			if (RcUtil.isEmpty(sysNewsModel.getSysNewsUuid())) { // 新增
				sysNewsModel.setOfficeCode(officeCode);
				sysNewsModel.setContent(content);
				if (!RcUtil.isEmpty(systemNewsEntity.getBasBlobUuid())) {
					sysNewsModel.setIfFiles("Y");
				} else {
					sysNewsModel.setIfFiles("N");
				}
				if (!RcUtil.isEmpty(urlAddress)) {
					java.net.URLDecoder urlDecoder = new java.net.URLDecoder();
					urlAddress = urlDecoder.decode(urlAddress, "utf-8");
					sysNewsModel.setUrlAddress(urlAddress);
				}
				smodel = save(sysNewsModel);
				smodel.setContent(null);
			} else { // 修改
				snModel = get(sysNewsModel.getSysNewsUuid());
				if (!RcUtil.isEmpty(urlAddress)) {
					java.net.URLDecoder urlDecoder = new java.net.URLDecoder();
					urlAddress = urlDecoder.decode(urlAddress, "utf-8");
					snModel.setUrlAddress(urlAddress);
				}
				snModel.setContent(content);
				snModel.setNewsType(sysNewsModel.getNewsType());
				snModel.setTitle(sysNewsModel.getTitle());
				snModel.setSeqNo(sysNewsModel.getSeqNo());
				snModel.setDateWork(sysNewsModel.getDateWork());
				snModel.setRemark(sysNewsModel.getRemark());
				smodel = save(snModel);
				smodel.setContent(null);
			}
			if (!RcUtil.isEmpty(systemNewsEntity.getBasBlobUuid())) { // 把文章的blobUuid保存进BLOB表中
				BasBlobModel bbmodel = new BasBlobModel();
				bbmodel = basBlobManager.get(systemNewsEntity.getBasBlobUuid());
				bbmodel.setStatus(smodel.getStatus());
				bbmodel.setPreDataUuid(smodel.getSysNewsUuid());
				basBlobManager.save(bbmodel);
			}
		}
		spj.setObject(smodel);
		spj.setResult(true);
		return spj;
	}

	/**
	 * 附件上传
	 */
	@Override
	public SinotransPageJson uploadIfFile(String path, String businessType,
			String[] modelIds, String fileName, InputStream stream,
			String officeCode) throws Exception {
		File srcFile = new File(fileName);
		String file_Name = srcFile.getName();
		SinotransPageJson spj = new SinotransPageJson();
		BasBlobModel model = new BasBlobModel();
		BasBlobModel bbm = new BasBlobModel();
		try {
			String sysNewsUuid = null;
			String basBlobUuid = null;
			if (!RcUtil.isEmpty(modelIds) && modelIds.length > 0) {
				if (!modelIds[0].equals("sysUuid")) {
					sysNewsUuid = modelIds[0];
				}
				if (!modelIds[1].equals("basBlobUuid")) {
					basBlobUuid = modelIds[1];
				}
			}
			byte[] data = saveIfFiles(stream, officeCode);
			if (RcUtil.isEmpty(basBlobUuid)) {
				model.setPreDataUuid(sysNewsUuid);
				model.setOfficeCode(officeCode);
				model.setTypeCode(CommonUtil.IF_FILES);
				model.setTypeDesc(file_Name);
				model.setStatus(CommonUtil.Pending);
				model.setRowState(CommonUtil.ROW_STATE_ADDED);
				model.setData(data);
				bbm = basBlobManager.save(model);
				if (!RcUtil.isEmpty(sysNewsUuid)) {
					SysNewsModel sysModel = this.get(sysNewsUuid);
					sysModel.setIfFiles("Y");
					this.save(sysModel);
				}
			} else {
				BasBlobModel basBlobModel = basBlobManager.get(basBlobUuid);
				BeanUtils.copyProperties(model, basBlobModel);
				model.setTypeDesc(file_Name);
				model.setData(data);
				model.setOfficeCode(officeCode);
				bbm = basBlobManager.save(model);
			}
		} catch (IOException e) {
			e.printStackTrace();
			spj.setMsg("复制失败!");
			spj.setResult(false);
			return spj;
		}
		bbm.setData(null);
		spj.setObject(bbm);
		spj.setMsg("文件上传成功!");
		spj.setResult(true);
		new File(fileName).delete();// 删除文件
		return spj;
	}

	/*
	 * 保存附件到basblob表中
	 */
	public byte[] saveIfFiles(InputStream inputStream, String officecode)
			throws Exception {
		byte[] data = new byte[1500000];
		byte[] dataTemp = new byte[1500000];
		int count = -1, point = 0;
		while ((count = inputStream.read(dataTemp, 0, 1500000)) != -1) {
			byte[] dt = new byte[count + (point * 1500000)];

			for (int i = 0; i < count + (point * 1500000); i++) {
				if (point > 0 && i < point * 1500000) {
					dt[i] = data[i];
				} else {
					dt[i] = dataTemp[i - point * 1500000];
				}
			}
			data = dt;
			point++;
		}
		inputStream.close();
		dataTemp = null;
		return data;
	}

	/**
	 * 查询出最新的10条数据，传回id和title
	 */
	@Override
	public List<SysNewsModel> findNewsToCombo(String officeCode,
			String newsType, int numNews) throws Exception {
		List<SysNewsModel> models = new ArrayList<SysNewsModel>();
		List<Object[]> resultList = new ArrayList<Object[]>();
		StringBuffer sb = new StringBuffer();
		String dateT = null;
		sb
				.append(" select * from (select sys.sys_news_uuid , sys.title , sys.create_time , sys.url_address from sys_news sys ");
		sb.append(" where sys.news_type= '" + newsType + "'");
		sb.append(" and sys.status='" + CommonUtil.Active + "'");
		//sb.append(" and sys.office_code='" + officeCode + "'");
		sb.append(" order by sys.create_time desc) where rownum <=" + numNews);
		resultList = sqlQueryManager.getSqlResultList(sb.toString(), "");

		if (!RcUtil.isEmpty(resultList)) {
			for (Object[] objects : resultList) {
				SysNewsModel sysModel = new SysNewsModel();
				if (!RcUtil.isEmpty(objects[0]))
					sysModel.setSysNewsUuid(objects[0].toString());
				if (!RcUtil.isEmpty(objects[1]))
					sysModel.setTitle(objects[1].toString());
				dateT = objects[2].toString().substring(0, 10);
				if (!RcUtil.isEmpty(objects[2]))
					sysModel.setCreateTime(RcUtil.string2date(dateT,
							RcUtil.yyyy_MM_dd));
				if (!RcUtil.isEmpty(objects[3]))
					sysModel.setUrlAddress(objects[3].toString());
				models.add(sysModel);
			}
		}

		return models;
	}

	/**
	 * 根据ID查询出信息
	 */
	@SuppressWarnings("static-access")
	@Override
	public SystemNewsEntity queryContentToUuid(String uuid) throws Exception {
		byte[] data = null;
		SystemNewsEntity systemNewsEntity = new SystemNewsEntity();
		if (!RcUtil.isEmpty(uuid)) {
			List<SysNewsModel> list = this.dao.createCommonQuery(
					SysNewsModel.class).addCondition(
					Condition.eq("sysNewsUuid", uuid)).query();
			if (!RcUtil.isEmpty(list) && list.size() > 0) {
				SysNewsModel model = list.get(0);
				List<SysUserModel> sysUserModel = this.dao.createCommonQuery(SysUserModel.class)
									.addCondition(Condition.eq("userCode", model.getFunctionary()))
									.query();
				if(sysUserModel.size()>0 && !RcUtil.isEmpty(sysUserModel)){
					systemNewsEntity.setUserName(sysUserModel.get(0).getUserName());
				}
				if (!RcUtil.isEmpty(model)) {
					data = model.getContent();
					String content = "";
					if (!RcUtil.isEmpty(data)) {
						content = new String(data, "utf-8");
						java.net.URLDecoder urlDecoder = new java.net.URLDecoder();
						content = urlDecoder.decode(content, "utf-8");
					}
					String IfFiles = model.getIfFiles();
					if (!RcUtil.isEmpty(IfFiles)) {
						if (IfFiles.equals("Y")) {
							BasBlobModel basBlobModel = basBlobManager
									.queryModelByAll(uuid);
							String basBlobUuid = basBlobModel.getBasBlobUuid();
							systemNewsEntity.setBasBlobUuid(basBlobUuid);
						}
					}
					systemNewsEntity.setStrContent(content);
					// systemNewsEntity.setSysNewsUuid(model.getSysNewsUuid());
					// systemNewsEntity.setNewsType(model.getNewsType());
					// systemNewsEntity.setTitle(model.getTitle());
					// systemNewsEntity.setCreateTime(model.getCreateTime());
					// systemNewsEntity.setCreator(model.getCreator());
					// systemNewsEntity.setUrlAddress(model.getUrlAddress());
					BeanUtils.copyProperties(systemNewsEntity, model);
				}
			}
		}
		return systemNewsEntity;
	}
	
	/* 获取每个仓库的介绍信息。
	 * (non-Javadoc)
	 * @see com.sinotrans.gd.wlp.system.service.SysNewsManager#getNewsTypeAndOffriceCode(java.lang.String, java.lang.String)
	 */
	@SuppressWarnings("static-access")
	@Override
	public Map<String, String> getNewsTypeAndOffriceCode(String officeCode,String newsType){
		//数据存放字节的byte数组
		byte[] data = null;
		SysNewsModel sn = new SysNewsModel();
		sn.setOfficeCode(officeCode);
		sn.setNewsType(newsType);
		//Map用于存放简介信息。并且返回页面
		Map<String, String> snMap=new HashMap<String, String>();
		try {
			//根据条件officeCode和新闻类型判断查询数据
			List<SysNewsModel> snLis = this.dao.findByExample(sn);
			if(!RcUtil.isEmpty(snLis)&&snLis.size()>0){
				//由于简介只能是一条所以至获取其中一条。
				sn=snLis.get(0);
				//获取到数据添加到byte数组中
				data = sn.getContent();
				String content = "";
				//判断如果数组信息部是空的那么就进入转换数据。
				if (!RcUtil.isEmpty(data)) {
					//将数据类型转换层utf-8的数据类型格式
					content = new String(data, "utf-8");
					java.net.URLDecoder urlDecoder = new java.net.URLDecoder();
					content = urlDecoder.decode(content, "utf-8");
					//由于上面格式化后得到了数据的标签。所以这里根据标签截取数据信息。
					String isod[]=content.split("<p>");
					//判断如果数据不为空循环添加进入一个Map中
					if(isod.length>0){
						for (int ik=1;ik<isod.length;ik++) {
							//put添加格式是其中0位是前面的数据标识（等号前面的数据）。1是数据内容信息（等号后面的数据）。
							snMap.put((isod[ik].split("</p>")[0]).split("=")[0],(isod[ik].split("</p>")[0]).split("=")[1]);
						}
						//最后需要将简介标题也同样保存到Map中
						snMap.put("snTitle", sn.getTitle());
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return snMap;
	}

	/**
	 * 删除新闻及其附件
	 */
	@Override
	public boolean deleteNewsAndBlob(String uuid) {
		try {
			if (RcUtil.isEmpty(uuid)) {
				throw new ApplicationException("获取页面传送数据出现错误！");
			}
			SysNewsModel sysModel = this.get(uuid);
			if (RcUtil.isEmpty(sysModel)) {
				throw new ApplicationException("未获取到相应的对象！");
			}
			if (sysModel != null) {
				// 有附件的话 先删除附件
				BasBlobModel basBlobModel = new BasBlobModel();
				basBlobModel.setPreDataUuid(uuid);
				List<BasBlobModel> listBolbModel = basBlobManager
						.findByExample(basBlobModel);
				if (!RcUtil.isEmpty(listBolbModel) && listBolbModel.size() > 0) {
					basBlobManager.removeAll(listBolbModel);
				}

				// 删除新闻信息
				removeByPk(uuid);
				return true;
			} else {
				return false;
			}
		} catch (ObjectRetrievalFailureException e) {
			throw new ApplicationException("找不到对象，该对象已经被删除！");
		} catch (Exception e) {
			throw new ApplicationException("删除发生异常！");
		}
	}

	/**
	 * 生效和作废新闻
	 * 
	 * @param data
	 * @param officeCode
	 * @param content
	 * @return
	 */
	@SuppressWarnings("static-access")
	@Override
	public SinotransPageJson validateAndCancel(String data, String officeCode,
			byte[] content, String urlAddress) throws Exception {
		SinotransPageJson spj = new SinotransPageJson();
		DateFormat myDateFormat = new SimpleDateFormat(RcUtil.yyyy_MM_dd);
		SystemNewsEntity systemNewsEntity = (SystemNewsEntity) JsonUtil
				.jsonToBean(data, SystemNewsEntity.class, myDateFormat);
		SysNewsModel sysNewsModel = new SysNewsModel();
		SysNewsModel snModel = new SysNewsModel();
		BeanUtils.copyProperties(sysNewsModel, systemNewsEntity);
		if (!RcUtil.isEmpty(sysNewsModel)) {
			snModel = get(sysNewsModel.getSysNewsUuid());
			if (!RcUtil.isEmpty(content)) {
				snModel.setContent(content);
			}
			snModel.setNewsType(sysNewsModel.getNewsType());
			snModel.setTitle(sysNewsModel.getTitle());
			snModel.setSeqNo(sysNewsModel.getSeqNo());
			snModel.setStatus(sysNewsModel.getStatus());
			snModel.setDateWork(sysNewsModel.getDateWork());
			snModel.setRemark(sysNewsModel.getRemark());
			if (!RcUtil.isEmpty(urlAddress)) {
				java.net.URLDecoder urlDecoder = new java.net.URLDecoder();
				urlAddress = urlDecoder.decode(urlAddress, "utf-8");
				snModel.setUrlAddress(urlAddress);
			}
			SysNewsModel ssnModel = save(snModel);
			ssnModel.setContent(null);
			// 修改BLOB字段的状态
			BasBlobModel basBlobModel = new BasBlobModel();
			basBlobModel.setPreDataUuid(sysNewsModel.getSysNewsUuid());
			List<BasBlobModel> listBolbModel = basBlobManager
					.findByExample(basBlobModel);
			if (!RcUtil.isEmpty(listBolbModel) && listBolbModel.size() > 0) {
				basBlobModel = listBolbModel.get(0);
				basBlobModel.setStatus(sysNewsModel.getStatus());
				basBlobManager.save(basBlobModel);
			}
			spj.setObject(ssnModel);
			spj.setResult(true);
		}

		return spj;
	}
	
}
