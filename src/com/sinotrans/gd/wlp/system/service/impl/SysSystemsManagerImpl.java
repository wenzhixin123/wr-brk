package com.sinotrans.gd.wlp.system.service.impl;

import java.util.Collection;
import java.util.List;
import org.springframework.stereotype.Service;
import com.sinotrans.framework.core.service.impl.BaseManagerImpl;
import com.sinotrans.gd.wlp.system.model.SysSystemsModel;
import com.sinotrans.gd.wlp.system.service.SysSystemsManager;


@Service
public class SysSystemsManagerImpl extends BaseManagerImpl implements
		SysSystemsManager {

	public SysSystemsModel get(String id) {
		return this.dao.get(SysSystemsModel.class, id);
	}

	public List<SysSystemsModel> getAll() {
		return this.dao.getAll(SysSystemsModel.class);
	}

	public List<SysSystemsModel> findByExample(SysSystemsModel example) {
		return this.dao.findByExample(example);
	}

	public SysSystemsModel save(SysSystemsModel model) {
		return this.dao.save(model);
	}

	public List<SysSystemsModel> saveAll(Collection<SysSystemsModel> models) {
		return this.dao.saveAll(models);
	}

	public void remove(SysSystemsModel model) {
		this.dao.remove(model);
	}

	public void removeAll(Collection<SysSystemsModel> models) {
		this.dao.removeAll(models);
	}

	public void removeByPk(String id) {
		this.dao.removeByPk(SysSystemsModel.class, id);
	}

	public void removeAllByPk(Collection<String> ids) {
		this.dao.removeAllByPk(SysSystemsModel.class, ids);
	}

/*	@Override
	public String indexReport(String type, String officeCode) {
		StringBuffer sb = new StringBuffer();
		if ("1".equals(type)) {
			sb
					.append("<chart palette='2' caption='两大仓库每月入仓业务量对比' showValues='0' numVDivLines='10' drawAnchors='0' divLineAlpha='30' alternateHGridAlpha='20'  setAdaptiveYMin='1' >");
			sb.append("<categories><category label='一月份' /> ");
			sb.append("<category label='二月份' /><category label='三月份' />  ");
			sb.append("<category label='四月份' /> <category label='五月份' /> ");
			sb.append("<category label='六月份' /> <category label='七月份' /> ");
			sb.append("<category label='八月份' /> <category label='九月份' /> ");
			sb.append("<category label='十月份' /><category label='十一月份' /> ");
			sb.append("<category label='十二月份' /> </categories>");
			sb.append("<dataset seriesName='郑州仓' color='A66EDD'>");
			sb.append("<set value='1127654' /> <set value='1226234' /> ");
			sb.append("<set value='1299456' /> <set value='1311565' /> ");
			sb.append("<set value='1324454' /> <set value='1357654' />");
			sb.append("<set value='1296234' /> <set value='1359456' /> ");
			sb.append("<set value='1391565' /> <set value='1414454' /> ");
			sb.append("<set value='1671565' /> <set value='1134454' />");
			sb.append("</dataset>");
			sb.append("<dataset seriesName='深圳仓' color='F6BD0F'>");
			sb.append("<set value='927654' /> <set value='1126234' />");
			sb.append("<set value='999456' /> <set value='1111565' />");
			sb.append("<set value='1124454' /><set value='1257654' /> ");
			sb.append("<set value='1196234' /><set value='1259456' /> ");
			sb.append("<set value='1191565' /><set value='1214454' /> ");
			sb.append("<set value='1371565' /> <set value='1434454' />");
			sb.append("</dataset>");
			sb.append("<styles>");
			sb.append("<definition>");
			sb
					.append("<style name='XScaleAnim' type='ANIMATION' duration='0.5' start='0' param='_xScale' />");
			sb
					.append("<style name='YScaleAnim' type='ANIMATION' duration='0.5' start='0' param='_yscale' />");
			sb
					.append("<style name='XAnim' type='ANIMATION' duration='0.5' start='0' param='_yscale' />");
			sb
					.append("<style name='AlphaAnim' type='ANIMATION' duration='0.5' start='0' param='_alpha' />");
			sb.append("</definition>");
			sb.append("<application>");
			sb
					.append("<apply toObject='CANVAS' styles='XScaleAnim, YScaleAnim,AlphaAnim' />");
			sb
					.append("<apply toObject='DIVLINES' styles='XScaleAnim,AlphaAnim' />");
			sb
					.append("<apply toObject='VDIVLINES' styles='YScaleAnim,AlphaAnim' />");
			sb
					.append("<apply toObject='HGRID' styles='YScaleAnim,AlphaAnim' />");
			sb.append("</application>");
			sb.append("</styles>");
			sb.append("</chart>");

		} else if ("2".equals(type)) {
			sb
					.append("<chart yAxisName='入库单数量' caption='入库单每日统计' showBorder='1' >");
			List<IndexReportInboundQueryItem> iriqiList = logisticsOrderManager
					.queryByIndex(CommonUtil.TRANSACTIONTYPE_SIN, officeCode);
			if (iriqiList != null && iriqiList.size() > 0) {
				for (IndexReportInboundQueryItem iriqi : iriqiList) {
					sb
							.append("<set label='" + iriqi.getOrderDate()
									+ "' value='" + iriqi.getCountordernum()
									+ "'  /> ");
				}
			}
			sb.append("</chart>");
		} else if ("3".equals(type)) {
			sb.append("<chart yAxisName='出库单数量' caption='出库单每日统计'>");
			List<IndexReportInboundQueryItem> iriqiList = logisticsOrderManager
					.queryByIndex(CommonUtil.TRANSACTIONTYPE_SOT, officeCode);
			if (iriqiList != null && iriqiList.size() > 0) {
				for (IndexReportInboundQueryItem iriqi : iriqiList) {
					sb
							.append("<set label='" + iriqi.getOrderDate()
									+ "' value='" + iriqi.getCountordernum()
									+ "'  /> ");
				}
			}
			sb.append("</chart>");
		}
		return sb.toString();
	}

	@Autowired
	private LogisticsOrderManager logisticsOrderManager;*/

}
