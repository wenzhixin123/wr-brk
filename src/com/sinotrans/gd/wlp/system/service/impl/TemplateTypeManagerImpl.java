package com.sinotrans.gd.wlp.system.service.impl;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sinotrans.framework.core.exception.ApplicationException;
import com.sinotrans.framework.core.service.impl.BaseManagerImpl;
import com.sinotrans.gd.wlp.common.service.SQLQueryManager;
import com.sinotrans.gd.wlp.common.web.RcUtil;
import com.sinotrans.gd.wlp.system.model.TemplateTypeModel;
import com.sinotrans.gd.wlp.system.service.TemplateTypeManager;

@Service
public class TemplateTypeManagerImpl extends BaseManagerImpl implements TemplateTypeManager {

	@Autowired
	private SQLQueryManager sQLQueryManager;
	
	public TemplateTypeModel get(String id) {
		return this.dao.get(TemplateTypeModel.class, id);
	}

	public List<TemplateTypeModel> getAll() {
		return this.dao.getAll(TemplateTypeModel.class);
	}

	public List<TemplateTypeModel> findByExample(TemplateTypeModel example) {
		return this.dao.findByExample(example);
	}

	public TemplateTypeModel save(TemplateTypeModel model) {
		return this.dao.save(model);
	}

	public List<TemplateTypeModel> saveAll(Collection<TemplateTypeModel> models) {
		return this.dao.saveAll(models);
	}

	public void remove(TemplateTypeModel model) {
		this.dao.remove(model);
	}

	public void removeAll(Collection<TemplateTypeModel> models) {
		this.dao.removeAll(models);
	}

	public void removeByPk(String id) {
		this.dao.removeByPk(TemplateTypeModel.class, id);
	}

	public void removeAllByPk(Collection<String> ids) {
		this.dao.removeAllByPk(TemplateTypeModel.class, ids);
	}

	@Override
	public boolean getYanZhentypeCode(String Code) {
		// TODO Auto-generated method stub
		TemplateTypeModel temp=new TemplateTypeModel();
		temp.setTemplateTypeCode(Code.trim());
		List<TemplateTypeModel> list	 = this.findByExample(temp);
		if(list.size()>0){
			return true;
		}else{
			return false;
		}
	}
	/*save and yanzheng
	 * (non-Javadoc)
	 * @see com.sinotrans.gd.wlp.system.service.TemplateTypeManager#saveyanzhen(com.sinotrans.gd.wlp.system.model.TemplateTypeModel)
	 */
	@Override
	public TemplateTypeModel saveyanzhen(TemplateTypeModel model) {
		if (RcUtil.isEmpty(model.getTemplateTypeUuid())) {
			if (this.getYanZhentypeCode(model.getTemplateTypeCode())) {
				throw new ApplicationException("该数据已存在！");
			} else {
				this.save(model);
			}
		} else {
			this.save(model);
		}
		return model;
	}
	

	@Override
	public boolean removeType(String pkId, long recVer) {
		if (!RcUtil.isEmpty(pkId)) {
			String deletePtuSql = "delete TEMPLATE_TYPE  where TEMPLATE_TYPE_UUID = '"
					+ pkId + "' and rec_ver = '" + recVer + "' ";
			sQLQueryManager.executeSQL(deletePtuSql, "", true);
			return true;
		} else {
			return false;
		}
	}

}
