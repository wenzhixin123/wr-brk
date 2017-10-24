package com.sinotrans.framework.core.support.entity;

public class EdiRequestParm {
	
	
	private String callbackUrl;
	private String bizSystemName;
	private String bizServiceName;
	private String bizMethodName;
	private String bizUserCode;
	
	private String Sender;		//
	private String Receiver;	//
	private String MessageType; //
	private String Version;
	private String DocumentId;  //
	private String BizKey;
	private String BatchNo;
	private String MessageStatus;
	private String ContentType;
	private String DataTransferType;
	private String DataURL;    // 
	private String NotificationAddress;
	private String NotificationCellPhone;
	private String ConfirmationLevel;
	private String ConfirmationURL;
	private String DeliveryRef;
	private String SystemID;  //
	
	public String getSender() {
		return Sender;
	}
	public void setSender(String sender) {
		Sender = sender;
	}
	public String getReceiver() {
		return Receiver;
	}
	public void setReceiver(String receiver) {
		Receiver = receiver;
	}
	public String getMessageType() {
		return MessageType;
	}
	public void setMessageType(String messageType) {
		MessageType = messageType;
	}
	public String getVersion() {
		return Version;
	}
	public void setVersion(String version) {
		Version = version;
	}
	public String getDocumentId() {
		return DocumentId;
	}
	public void setDocumentId(String documentId) {
		DocumentId = documentId;
	}
	public String getBizKey() {
		return BizKey;
	}
	public void setBizKey(String bizKey) {
		BizKey = bizKey;
	}
	public String getBatchNo() {
		return BatchNo;
	}
	public void setBatchNo(String batchNo) {
		BatchNo = batchNo;
	}
	public String getMessageStatus() {
		return MessageStatus;
	}
	public void setMessageStatus(String messageStatus) {
		MessageStatus = messageStatus;
	}
	public String getContentType() {
		return ContentType;
	}
	public void setContentType(String contentType) {
		ContentType = contentType;
	}
	public String getDataTransferType() {
		return DataTransferType;
	}
	public void setDataTransferType(String dataTransferType) {
		DataTransferType = dataTransferType;
	}
	public String getDataURL() {
		return DataURL;
	}
	public void setDataURL(String dataURL) {
		DataURL = dataURL;
	}
	public String getNotificationAddress() {
		return NotificationAddress;
	}
	public void setNotificationAddress(String notificationAddress) {
		NotificationAddress = notificationAddress;
	}
	public String getNotificationCellPhone() {
		return NotificationCellPhone;
	}
	public void setNotificationCellPhone(String notificationCellPhone) {
		NotificationCellPhone = notificationCellPhone;
	}
	public String getConfirmationLevel() {
		return ConfirmationLevel;
	}
	public void setConfirmationLevel(String confirmationLevel) {
		ConfirmationLevel = confirmationLevel;
	}
	public String getConfirmationURL() {
		return ConfirmationURL;
	}
	public void setConfirmationURL(String confirmationURL) {
		ConfirmationURL = confirmationURL;
	}
	public String getDeliveryRef() {
		return DeliveryRef;
	}
	public void setDeliveryRef(String deliveryRef) {
		DeliveryRef = deliveryRef;
	}
	public String getSystemID() {
		return SystemID;
	}
	public void setSystemID(String systemID) {
		SystemID = systemID;
	}
	public String getBizSystemName() {
		return bizSystemName;
	}
	public void setBizSystemName(String bizSystemName) {
		this.bizSystemName = bizSystemName;
	}
	public String getBizServiceName() {
		return bizServiceName;
	}
	public void setBizServiceName(String bizServiceName) {
		this.bizServiceName = bizServiceName;
	}
	public String getBizMethodName() {
		return bizMethodName;
	}
	public void setBizMethodName(String bizMethodName) {
		this.bizMethodName = bizMethodName;
	}
	public String getBizUserCode() {
		return bizUserCode;
	}
	public void setBizUserCode(String bizUserCode) {
		this.bizUserCode = bizUserCode;
	}
	public String getCallbackUrl() {
		return callbackUrl;
	}
	public void setCallbackUrl(String callbackUrl) {
		this.callbackUrl = callbackUrl;
	}	
	
	
}
