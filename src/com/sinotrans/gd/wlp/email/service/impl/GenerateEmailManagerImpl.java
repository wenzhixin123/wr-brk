package com.sinotrans.gd.wlp.email.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sinotrans.framework.core.service.impl.BaseManagerImpl;
import com.sinotrans.gd.wlp.basicdata.model.BasCodeDefModel;
import com.sinotrans.gd.wlp.basicdata.service.BasCodeDefManager;
import com.sinotrans.gd.wlp.email.entity.EmailMainContentEntity;
import com.sinotrans.gd.wlp.email.entity.SendEmailDataEntity;
import com.sinotrans.gd.wlp.email.query.CustomsBillContQueryCondition;
import com.sinotrans.gd.wlp.email.query.CustomsBillContQueryItem;
import com.sinotrans.gd.wlp.email.query.NewManifestBillQueryCondition;
import com.sinotrans.gd.wlp.email.query.NewManifestBillQueryItem;
import com.sinotrans.gd.wlp.email.query.PreManifestCntrQueryCondition;
import com.sinotrans.gd.wlp.email.query.PreManifestCntrQueryItem;
import com.sinotrans.gd.wlp.email.query.ShippingDepartContQueryCondition;
import com.sinotrans.gd.wlp.email.query.ShippingDepartContQueryItem;
import com.sinotrans.gd.wlp.email.service.GenerateEmailManager;
import com.sinotrans.gd.wlp.email.util.ParaUtil;
import com.sinotrans.gd.wlp.util.CommonUtil;

@Service
public class GenerateEmailManagerImpl extends BaseManagerImpl 
										implements GenerateEmailManager {

	@Autowired
	private BasCodeDefManager basCodeDefManager;

	
	@Override
	public List<EmailMainContentEntity> getAllPreManifestBillData() {
		List<EmailMainContentEntity> sendList= new ArrayList<EmailMainContentEntity>();
		
		List<SendEmailDataEntity> detailList = this.findPreManifestList();
		if(detailList.size()<=0){
			log.error("没有预配舱单信息需要发送邮件！");
			return sendList;
		}
		
		sendList = this.getMainContent(detailList);
		
		return sendList;
	}

	@Override
	public List<EmailMainContentEntity> getAllNewManifestBillData() {
		List<EmailMainContentEntity> sendList= new ArrayList<EmailMainContentEntity>();
		
		List<SendEmailDataEntity> detailList = this.findNewManifestBill();
		if(detailList.size()<=0){
			log.error("没有发送预配舱单信息需要发送邮件！");
			return sendList;
		}
		
		sendList = this.getMainContent(detailList);
		return sendList;
	}

	@Override
	public List<EmailMainContentEntity> getAllCustomsBillData() {
		List<EmailMainContentEntity> sendList= new ArrayList<EmailMainContentEntity>();
		
		List<SendEmailDataEntity> detailList = this.findCustomsBill();
		if(detailList.size()<=0){
			log.error("没有放行条信息需要发送邮件！");
			return sendList;
		}
		
		sendList = this.getMainContent(detailList);
		return sendList;
	}

	@Override
	public List<EmailMainContentEntity> getAllShipDepartData() {
		List<EmailMainContentEntity> sendList= new ArrayList<EmailMainContentEntity>();
		
		List<SendEmailDataEntity> detailList = this.findShippingDepart();
		if(detailList.size()<=0){
			log.error("没有走船信息需要发送邮件！");
			return sendList;
		}
		
		sendList = this.getMainContent(detailList);
		return sendList;
	}

	private List<SendEmailDataEntity> findPreManifestList(){
		List<SendEmailDataEntity> detailList = new ArrayList<SendEmailDataEntity>();
		
		int dataRange = this.getDataRang();
		Date now = new Date();
		Date extraDate = this.getBeforeDate(dataRange);
		
		String carriage = this.getSpecialCustomer();
		
		PreManifestCntrQueryCondition condition = new PreManifestCntrQueryCondition();
		if(StringUtils.isNotBlank(carriage)){
			condition.setCarriageCode(carriage);
		}
		condition.setManifestDateFrom(extraDate);
		condition.setManifestDateTo(now);
		List<PreManifestCntrQueryItem> preManifestCntrList = this.dao.query(condition, PreManifestCntrQueryItem.class);
		if(preManifestCntrList!=null&&preManifestCntrList.size()>0){
			for(PreManifestCntrQueryItem preManCntr:preManifestCntrList){
				if(StringUtils.isNotBlank(preManCntr.getContainerno())){
					SendEmailDataEntity emailData = new SendEmailDataEntity();
					emailData.setContainerNo(preManCntr.getContainerno());
					emailData.setShippingOrderNo(preManCntr.getShippingorderno());
					emailData.setVesselName(preManCntr.getVesselname());
					emailData.setVoyageNo(preManCntr.getVoyageno());
					emailData.setCustomsLicenseNo(preManCntr.getCustomsLicenseNo());
					emailData.setOperationType(ParaUtil.PRE_MAN_TYPE);
					detailList.add(emailData);
				}
			}
		}
		
		return detailList;
	}
	
	private List<SendEmailDataEntity> findNewManifestBill(){
		List<SendEmailDataEntity> detailList = new ArrayList<SendEmailDataEntity>();
		
		int dataRange = this.getDataRang();
		Date now = new Date();
		Date extraDate = this.getBeforeDate(dataRange);
		
		String carriage = this.getSpecialCustomer();
		
		NewManifestBillQueryCondition condition = new NewManifestBillQueryCondition();
		if(StringUtils.isNotBlank(carriage)){
			condition.setCarriageCode(carriage);
		}
		condition.setSendDateFrom(extraDate);
		condition.setSendDateTo(now);
		List<NewManifestBillQueryItem> newManBillList = this.dao.query(condition, NewManifestBillQueryItem.class);
		if(newManBillList!=null&&newManBillList.size()>0){
			for(NewManifestBillQueryItem newManBill:newManBillList){
				if(StringUtils.isNotBlank(newManBill.getContainerNo())){
					SendEmailDataEntity emailData = new SendEmailDataEntity();
					emailData.setContainerNo(newManBill.getContainerNo());
					emailData.setShippingOrderNo(newManBill.getShippingOrderNo());
					emailData.setVesselName(newManBill.getVesselName());
					emailData.setVoyageNo(newManBill.getVoyageno());
					emailData.setWorkDate(newManBill.getSendtime());
					emailData.setMessage("预配舱单、运抵报告已发送");
					emailData.setOperationType(ParaUtil.NEW_MAN_TYPE);
					detailList.add(emailData);
				}
			}
		}
		
		return detailList;
	}
	
	private List<SendEmailDataEntity> findCustomsBill(){
		List<SendEmailDataEntity> detailList = new ArrayList<SendEmailDataEntity>();
		
		int dataRange = this.getDataRang();
		Date now = new Date();
		Date extraDate = this.getBeforeDate(dataRange);
		
		String carriage = this.getSpecialCustomer();
		
		CustomsBillContQueryCondition condition = new CustomsBillContQueryCondition();
		if(StringUtils.isNotBlank(carriage)){
			condition.setCarriageCode(carriage);
		}
		condition.setWorkDateFrom(extraDate);
		condition.setWorkDateTo(now);
		List<CustomsBillContQueryItem> customsBillList = this.dao.query(condition, CustomsBillContQueryItem.class);
		if(customsBillList!=null&&customsBillList.size()>0){
			for(CustomsBillContQueryItem customsBill:customsBillList){
				if(StringUtils.isNotBlank(customsBill.getContainerno())){
					SendEmailDataEntity emailData = new SendEmailDataEntity();
					emailData.setContainerNo(customsBill.getContainerno());
					emailData.setBillLadingNo(customsBill.getBillladingno());
					emailData.setWorkDate(customsBill.getWorkdate());
					emailData.setMessage("收到放行条");
					emailData.setOperationType(ParaUtil.CUS_ORDER_TYPE);
					detailList.add(emailData);
				}
			}
		}
		
		return detailList;
	}
	
	private List<SendEmailDataEntity> findShippingDepart(){
		List<SendEmailDataEntity> detailList = new ArrayList<SendEmailDataEntity>();
		
		int dataRange = this.getDataRang();
		Date now = new Date();
		Date extraDate = this.getBeforeDate(dataRange);
		
		ShippingDepartContQueryCondition condition = new ShippingDepartContQueryCondition();
		condition.setWorkDateFrom(extraDate);
		condition.setWorkDateTo(now);
		List<ShippingDepartContQueryItem> shippingDepartList = this.dao.query(condition, ShippingDepartContQueryItem.class);
		if(shippingDepartList!=null&&shippingDepartList.size()>0){
			for(ShippingDepartContQueryItem shipDepart:shippingDepartList){
				if(StringUtils.isNotBlank(shipDepart.getContainerno())){
					SendEmailDataEntity emailData = new SendEmailDataEntity();
					emailData.setContainerNo(shipDepart.getContainerno());
					emailData.setShippingOrderNo(shipDepart.getShippingorderno());
					emailData.setVesselName(shipDepart.getVesselname());
					emailData.setVoyageNo(shipDepart.getVoyageno());
					emailData.setWorkDate(shipDepart.getDatedepart());
					emailData.setOperationType(ParaUtil.SHIP_DEP_TYPE);
					detailList.add(emailData);
				}
			}
		}
		
		return detailList;
	}
	
	private List<EmailMainContentEntity> getMainContent(List<SendEmailDataEntity> detailList){
		List<EmailMainContentEntity> mainContentList = new ArrayList<EmailMainContentEntity>();
		
		String emailTitle = this.getEmailTitle(detailList.get(0).getOperationType());
		
		/*for(SendEmailDataEntity dataEntity:detailList){
			if(StringUtils.isNotBlank(dataEntity.getContainerNo())){
				List<DcsContainerInfoModel> preCntrInfoList = dcsContainerInfoManager.getVirtualContainerInfoModelList(dataEntity.getContainerNo(),DcsUtil.MTCONTINFO_TYPE);
				if(preCntrInfoList!=null&&preCntrInfoList.size()>0){
					List<String> tempEmailList = new ArrayList<String>();
					for(DcsContainerInfoModel preCntrInfo:preCntrInfoList){
						if(StringUtils.isNotBlank(preCntrInfo.getWorkSpecial())){
							Date today = new Date();
							String day = DateUtils.getDay(today);
							String controlWord = preCntrInfo.getInfoControlWord();
							if(ParaUtil.PRE_MAN_TYPE.equals(detailList.get(0).getOperationType())&&day.equals(controlWord.substring(0, 2))
									||ParaUtil.NEW_MAN_TYPE.equals(detailList.get(0).getOperationType())&&day.equals(controlWord.substring(2, 4))
									||ParaUtil.CUS_ORDER_TYPE.equals(detailList.get(0).getOperationType())&&day.equals(controlWord.substring(4, 6))
									||ParaUtil.SHIP_DEP_TYPE.equals(detailList.get(0).getOperationType())&&day.equals(controlWord.substring(6, 8))){
										continue;
							}
							String[] emailList = preCntrInfo.getWorkSpecial().split(";");
							for(String emailAddr:emailList){
								if(StringUtils.isNotBlank(emailAddr)&&!tempEmailList.contains(emailAddr)){
									tempEmailList.add(emailAddr);
								}
							}
							if(tempEmailList.size()>0){
								if(ParaUtil.PRE_MAN_TYPE.equals(detailList.get(0).getOperationType())){
									controlWord = StringUtil.replaceCharAt(controlWord, 0, day.substring(0,1));
									controlWord = StringUtil.replaceCharAt(controlWord, 1, day.substring(1));
								}else if(ParaUtil.NEW_MAN_TYPE.equals(detailList.get(0).getOperationType())){
									controlWord = StringUtil.replaceCharAt(controlWord, 2, day.substring(0,1));
									controlWord = StringUtil.replaceCharAt(controlWord, 3, day.substring(1));
								}else if(ParaUtil.CUS_ORDER_TYPE.equals(detailList.get(0).getOperationType())){
									controlWord = StringUtil.replaceCharAt(controlWord, 4, day.substring(0,1));
									controlWord = StringUtil.replaceCharAt(controlWord, 5, day.substring(1));
								}else if(ParaUtil.SHIP_DEP_TYPE.equals(detailList.get(0).getOperationType())){
									controlWord = StringUtil.replaceCharAt(controlWord, 6, day.substring(0,1));
									controlWord = StringUtil.replaceCharAt(controlWord, 7, day.substring(1));
								}
								preCntrInfo.setInfoControlWord(controlWord);
								dcsContainerInfoManager.save(preCntrInfo);
							}
						}
					}*/
					/*for(String tempEmail:tempEmailList){
						boolean isContainEmail = false;
						for(EmailMainContentEntity mainContent:mainContentList){
							if(tempEmail.equals(mainContent.getAddress())){
								mainContent.getDetailList().add(dataEntity);
								isContainEmail = true;
							}
						}
						if(!isContainEmail){
							EmailMainContentEntity addEmailMainCont = new EmailMainContentEntity();
							addEmailMainCont.setAddress(tempEmail);
							addEmailMainCont.setTitle(emailTitle);
							List<SendEmailDataEntity> emailDetail = new ArrayList<SendEmailDataEntity>();
							emailDetail.add(dataEntity);
							addEmailMainCont.setDetailList(emailDetail);
							addEmailMainCont.setOperationType(dataEntity.getOperationType());
							mainContentList.add(addEmailMainCont);
						}
					}
				}
			}
		}*/
		
		return mainContentList;
	}
	
	private Integer getDataRang(){
		int dataRange = 0;
		BasCodeDefModel dataRangeMod = basCodeDefManager.getCodeDefModelByCodeTypeValue(ParaUtil.EMAIL_TASK_PARA, ParaUtil.DATA_RANG, CommonUtil.Active);
		if(dataRangeMod!=null&&StringUtils.isNotBlank(dataRangeMod.getDisplayValue())){
			dataRange = Integer.parseInt(dataRangeMod.getDisplayValue());
		}
		return dataRange;
	}
	
	private String getSpecialCustomer(){
		String customer = "";
		BasCodeDefModel speCustomer = basCodeDefManager.getCodeDefModelByCodeTypeValue(ParaUtil.EMAIL_TASK_PARA, ParaUtil.SPE_CUSTOMER, CommonUtil.Active);
		if(speCustomer!=null){
			customer = speCustomer.getDisplayValue();
		}
		return customer;
	}
	
	private String getEmailTitle(String operationType){
		String title = "";
		BasCodeDefModel speCustomer = basCodeDefManager.getCodeDefModelByCodeTypeValue(ParaUtil.EMAIL_TASK_PARA, operationType+"_TITLE", CommonUtil.Active);
		if(speCustomer!=null){
			title = speCustomer.getDisplayValue();
		}else{
			if(ParaUtil.PRE_MAN_TYPE.equals(operationType)){
				title = "预配信息";
			}else if(ParaUtil.NEW_MAN_TYPE.equals(operationType)){
				title = "预配舱单、运抵报告已发送";
			}else if(ParaUtil.CUS_ORDER_TYPE.equals(operationType)){
				title = "已收到放行条";
			}else if(ParaUtil.SHIP_DEP_TYPE.equals(operationType)){
				title = "走船信息";
			}
		}
		return title;
	}
	
	private Date getBeforeDate(int rang){
		Date extraDate = org.apache.commons.lang.time.DateUtils.truncate(new Date(),Calendar.DATE);
		extraDate = org.apache.commons.lang.time.DateUtils.addDays(extraDate, -rang);
		return extraDate;
	}
	
	private Date getAfterDate(int rang){
		Date extraDate = org.apache.commons.lang.time.DateUtils.truncate(new Date(),Calendar.DATE);
		extraDate = org.apache.commons.lang.time.DateUtils.addDays(extraDate, rang);
		return extraDate;
	}
}
