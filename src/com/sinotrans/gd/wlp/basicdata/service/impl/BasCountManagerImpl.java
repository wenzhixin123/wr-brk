package com.sinotrans.gd.wlp.basicdata.service.impl;

import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Service;

import com.sinotrans.framework.core.service.impl.BaseManagerImpl;
import com.sinotrans.gd.wlp.basicdata.model.BasCountModel;
import com.sinotrans.gd.wlp.basicdata.query.XxxQueryCondition;
import com.sinotrans.gd.wlp.basicdata.query.XxxQueryItem;
import com.sinotrans.gd.wlp.basicdata.service.BasCountManager;

@Service
public class BasCountManagerImpl extends BaseManagerImpl implements BasCountManager {

	public BasCountModel get(String id) {
		return this.dao.get(BasCountModel.class, id);
	}

	public List<BasCountModel> getAll() {
		return this.dao.getAll(BasCountModel.class);
	}

	public List<BasCountModel> findByExample(BasCountModel example) {
		return this.dao.findByExample(example);
	}

	public BasCountModel save(BasCountModel model) {
		return this.dao.save(model);
	}

	public List<BasCountModel> saveAll(Collection<BasCountModel> models) {
		return this.dao.saveAll(models);
	}

	public void remove(BasCountModel model) {
		this.dao.remove(model);
	}

	public void removeAll(Collection<BasCountModel> models) {
		this.dao.removeAll(models);
	}

	public void removeByPk(String id) {
		this.dao.removeByPk(BasCountModel.class, id);
	}

	public void removeAllByPk(Collection<String> ids) {
		this.dao.removeAllByPk(BasCountModel.class, ids);
	}
	//
	public String getConHead(String pk){
		
		XxxQueryCondition condition = new XxxQueryCondition();
		List<XxxQueryItem> itemlst  ;
		condition.setHead(pk);
		itemlst = this.dao.query(condition, XxxQueryItem.class);
		if(itemlst!=null&&itemlst.size()>0){
			for(int i=0;i<itemlst.size();i++){
				XxxQueryItem item = itemlst.get(i);
				this.log.debug(item.getHead()+"----"+item.getBasConUuid());
			}		
		}
		this.log.debug("done");
		return null;
	}
}
