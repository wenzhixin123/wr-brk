package com.sinotrans.gd.wlp.email.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.mail.EmailException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.sinotrans.framework.core.service.impl.BaseManagerImpl;
import com.sinotrans.gd.wlp.basicdata.model.BasCodeDefModel;
import com.sinotrans.gd.wlp.basicdata.service.BasCodeDefManager;
import com.sinotrans.gd.wlp.email.entity.EmailMainContentEntity;
import com.sinotrans.gd.wlp.email.entity.SendEmailDataEntity;
import com.sinotrans.gd.wlp.email.service.GenerateEmailManager;
import com.sinotrans.gd.wlp.email.service.SendEmailManager;
import com.sinotrans.gd.wlp.email.util.ParaUtil;
import com.sinotrans.gd.wlp.util.MailUtil;


@Service
public class SendEmailManagerImpl extends BaseManagerImpl 
									implements SendEmailManager {

	@Autowired
	private BasCodeDefManager basCodeDefManager;
	
	@Autowired
	private GenerateEmailManager generateEmailManager;
	
	private static String preManHead="预配信息如下：</br></br>"+
				"<table border='1'  cellpadding='0' cellspacing='0' >"+
				" <tr>"+
				"	<td align='center' style='width:100px;padding: 3px'>箱号</td>"+
				"	<td align='center' style='width:100px;padding: 3px'>S/O号</td>"+
				"	<td align='center' style='width:100px;padding: 3px'>船名</td>"+
				"   <td align='center' style='width:100px;padding: 3px'>航次</td>"+
				"   <td align='center' style='width:100px;padding: 3px'>船舶编码</td>"+
				" </tr>"+
				"@"+
				"</table>";
	
	private static String newManBillHead="发送新舱单信息如下：</br></br>"+
			"<table border='1'  cellpadding='0' cellspacing='0' >"+
			" <tr>"+
			"	<td align='center' style='width:100px;padding: 3px'>箱号</td>"+
			"	<td align='center' style='width:100px;padding: 3px'>S/O号</td>"+
			"	<td align='center' style='width:100px;padding: 3px'>船名</td>"+
			"   <td align='center' style='width:100px;padding: 3px'>航次</td>"+
			"   <td align='center' style='width:100px;padding: 3px'>备注</td>"+
			"   <td align='center' style='width:100px;padding: 3px'>发送日期</td>"+
			" </tr>"+
			"@"+
			"</table>";
	
	private static String cusOrderHead="放行条信息如下：</br></br>"+
			"<table border='1'  cellpadding='0' cellspacing='0' >"+
			" <tr>"+
			"	<td align='center' style='width:100px;padding: 3px'>箱号</td>"+
			"	<td align='center' style='width:100px;padding: 3px'>提单号</td>"+
			"	<td align='center' style='width:100px;padding: 3px'>备注</td>"+
			"   <td align='center' style='width:100px;padding: 3px'>收单时间</td>"+
			" </tr>"+
			"@"+
			"</table>";
	
	private static String shipDepartHead="舱单走船信息如下：</br></br>"+
			"<table border='1'  cellpadding='0' cellspacing='0' >"+
			" <tr>"+
			"	<td align='center' style='width:100px;padding: 3px'>箱号</td>"+
			"	<td align='center' style='width:100px;padding: 3px'>S/O号</td>"+
			"	<td align='center' style='width:100px;padding: 3px'>船名</td>"+
			"   <td align='center' style='width:100px;padding: 3px'>航次</td>"+
			"   <td align='center' style='width:100px;padding: 3px'>走船时间</td>"+
			" </tr>"+
			"@"+
			"</table>";
	
	private static String mailTailMsg="<font size =\"4\" color=\"red\" face=\"arial\" >"+
			"温馨提示：邮箱：outbound@sinoway.com.hk 只用于发送邮件之用，不可以接收任何邮件.</br>"
			+ "如各位有其它需求，请及时联系广运相关操作人员。多谢配合！</br>（黄埔线出口公共邮箱：huangpu@sionway.com.hk）"
			+"</font>";
	
	@Override
	public void sendEmailByDetail(List<EmailMainContentEntity> mainContentList) {
		//For test
		//MailUtil.sendMessageMail("wlp_test_01@sina.com", "测试预配信息邮件", preManHead, "outbound@sinoway.com.hk", "outbound.sw" ,"outbound@127");
		
		String senderAddr = "";
		String senderUsr = "";
		String senderPwd = "";

		List<BasCodeDefModel> emailParaList = basCodeDefManager.findByTypeCode(ParaUtil.EMAIL_TASK_PARA, true);
		if(emailParaList!=null&&emailParaList.size()>0){
			for(BasCodeDefModel codeDef:emailParaList){
				if(ParaUtil.SEND_ADDR.equals(codeDef.getCodeValue())){
					senderAddr = codeDef.getDisplayValue();
				}else if(ParaUtil.SEND_USER.equals(codeDef.getCodeValue())){
					senderUsr = codeDef.getDisplayValue();
				}else if(ParaUtil.SEND_PWD.equals(codeDef.getCodeValue())){
					senderPwd = codeDef.getDisplayValue();
				}
			}
		}
		
		if(emailParaList==null||emailParaList.size()<=0
				||StringUtils.isBlank(senderAddr)
				||StringUtils.isBlank(senderUsr)
				||StringUtils.isBlank(senderPwd)){
			log.error("缺少邮件发送人信息，发送失败");
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return;
		}
		
		if(mainContentList!=null&&mainContentList.size()>0){
			for(EmailMainContentEntity mainContent:mainContentList){
				String emailContent = this.getEmailContent(mainContent);
				try {
					MailUtil.sendMessageMail(mainContent.getAddress(), mainContent.getTitle(), emailContent, senderAddr,senderUsr,senderPwd);
				} catch (EmailException e) {
					log.error("send email error！");
					TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				}
			}
		}else{
			log.error("没有可发送的内容！");
		}
	}

	private String getEmailContent(EmailMainContentEntity mainContent){
		StringBuffer emailContent = new StringBuffer();
		String contentHead = "";
		HashMap<String, String> needShowFlag = new HashMap<String, String>();
		if(ParaUtil.PRE_MAN_TYPE.endsWith(mainContent.getOperationType())){
			contentHead = preManHead;
			needShowFlag.put("containerNo", "Y");
			needShowFlag.put("shippingOrderNo", "Y");
			needShowFlag.put("vesselName", "Y");
			needShowFlag.put("voyageNo", "Y");
			needShowFlag.put("customsLicenseNo", "Y");
		}else if(ParaUtil.NEW_MAN_TYPE.endsWith(mainContent.getOperationType())){
			contentHead = newManBillHead;
			needShowFlag.put("containerNo", "Y");
			needShowFlag.put("shippingOrderNo", "Y");
			needShowFlag.put("vesselName", "Y");
			needShowFlag.put("voyageNo", "Y");
			needShowFlag.put("message", "Y");
			needShowFlag.put("workDate", "Y");
		}else if(ParaUtil.CUS_ORDER_TYPE.endsWith(mainContent.getOperationType())){
			contentHead = cusOrderHead;
			needShowFlag.put("containerNo", "Y");
			needShowFlag.put("billLadingNo", "Y");
			needShowFlag.put("message", "Y");
			needShowFlag.put("workDate", "Y");
		}else if(ParaUtil.SHIP_DEP_TYPE.endsWith(mainContent.getOperationType())){
			contentHead = shipDepartHead;
			needShowFlag.put("containerNo", "Y");
			needShowFlag.put("shippingOrderNo", "Y");
			needShowFlag.put("vesselName", "Y");
			needShowFlag.put("voyageNo", "Y");
			needShowFlag.put("workDate", "Y");
		}
		
		String contentData = this.getContentData(needShowFlag, mainContent);
		emailContent.append(contentHead.replace("@", contentData));
		emailContent.append("</br>");
		emailContent.append("</br>");
		emailContent.append("</br>");
		emailContent.append(mailTailMsg);
		
		return emailContent.toString();
	}
	
	private String getContentData(HashMap<String, String> needShowFlag,EmailMainContentEntity mainContent){
		String data = "";
		
		if(mainContent.getDetailList()!=null&&mainContent.getDetailList().size()>0){
			StringBuffer table = new StringBuffer("<tr>");
			for(SendEmailDataEntity dataEntity:mainContent.getDetailList()){
				StringBuffer line = new StringBuffer("<tr>");
				if(needShowFlag.get("containerNo")!=null&&"Y".equals(needShowFlag.get("containerNo"))){
					line.append("<td align='center' style='width:100px;padding: 3px'>");
					line.append(dataEntity.getContainerNo()!=null?dataEntity.getContainerNo():"");
					line.append("</td>");
				}
				if(needShowFlag.get("billLadingNo")!=null&&"Y".equals(needShowFlag.get("billLadingNo"))){
					line.append("<td align='center' style='width:100px;padding: 3px'>");
					line.append(dataEntity.getBillLadingNo()!=null?dataEntity.getBillLadingNo():"");
					line.append("</td>");
				}
				if(needShowFlag.get("shippingOrderNo")!=null&&"Y".equals(needShowFlag.get("shippingOrderNo"))){
					line.append("<td align='center' style='width:100px;padding: 3px'>");
					line.append(dataEntity.getShippingOrderNo()!=null?dataEntity.getShippingOrderNo():"");
					line.append("</td>");
				}
				if(needShowFlag.get("vesselName")!=null&&"Y".equals(needShowFlag.get("vesselName"))){
					line.append("<td align='center' style='width:100px;padding: 3px'>");
					line.append(dataEntity.getVesselName()!=null?dataEntity.getVesselName():"");
					line.append("</td>");
				}
				if(needShowFlag.get("voyageNo")!=null&&"Y".equals(needShowFlag.get("voyageNo"))){
					line.append("<td align='center' style='width:100px;padding: 3px'>");
					line.append(dataEntity.getVoyageNo()!=null?dataEntity.getVoyageNo():"");
					line.append("</td>");
				}
				if(needShowFlag.get("customsLicenseNo")!=null&&"Y".equals(needShowFlag.get("customsLicenseNo"))){
					line.append("<td align='center' style='width:100px;padding: 3px'>");
					line.append(dataEntity.getCustomsLicenseNo()!=null?dataEntity.getCustomsLicenseNo():"");
					line.append("</td>");
				}
				if(needShowFlag.get("message")!=null&&"Y".equals(needShowFlag.get("message"))){
					line.append("<td align='center' style='width:100px;padding: 3px'>");
					line.append(dataEntity.getMessage()!=null?dataEntity.getMessage():"");
					line.append("</td>");
				}
				if(needShowFlag.get("workDate")!=null&&"Y".equals(needShowFlag.get("workDate"))){
					line.append("<td align='center' style='width:100px;padding: 3px'>");
					line.append(dataEntity.getWorkDate()!=null?dataEntity.getWorkDate():"");
					line.append("</td>");
				}
				line.append("</tr>");
				table.append(line.toString());
			}
			data = table.toString();
		}
				
		return data;
	}

	@Transactional(rollbackFor=Exception.class) 
	@Override
	public void sendEmailByType(String operationType) {
		List<EmailMainContentEntity> mainContentList = new ArrayList<EmailMainContentEntity>();
		if(ParaUtil.PRE_MAN_TYPE.endsWith(operationType)){
			mainContentList = generateEmailManager.getAllPreManifestBillData();
		}else if(ParaUtil.NEW_MAN_TYPE.endsWith(operationType)){
			mainContentList = generateEmailManager.getAllNewManifestBillData();
		}else if(ParaUtil.CUS_ORDER_TYPE.endsWith(operationType)){
			mainContentList = generateEmailManager.getAllCustomsBillData();
		}else if(ParaUtil.SHIP_DEP_TYPE.endsWith(operationType)){
			mainContentList = generateEmailManager.getAllShipDepartData();
		}
		
		if(mainContentList!=null&&mainContentList.size()>0){
			this.sendEmailByDetail(mainContentList);
		}else{
			if(ParaUtil.PRE_MAN_TYPE.endsWith(operationType)){
				log.error("没有预配信息需要发送邮件！");
			}else if(ParaUtil.NEW_MAN_TYPE.endsWith(operationType)){
				log.error("没有新舱单发送信息需要发送邮件！");
			}else if(ParaUtil.CUS_ORDER_TYPE.endsWith(operationType)){
				log.error("没有放行条信息需要发送邮件！");
			}else if(ParaUtil.SHIP_DEP_TYPE.endsWith(operationType)){
				log.error("没有走船信息需要发送邮件！");
			}
		}
	}
	
}
