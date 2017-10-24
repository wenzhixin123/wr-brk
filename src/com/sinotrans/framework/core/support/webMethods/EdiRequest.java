package com.sinotrans.framework.core.support.webMethods;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sinotrans.framework.core.exception.SystemException;
import com.sinotrans.framework.core.support.entity.EdiRequestParm;
import com.sinotrans.framework.core.util.JSONDataUtils;
import com.wm.app.b2b.client.Context;
import com.wm.data.IData;
import com.wm.data.IDataCursor;
import com.wm.data.IDataFactory;
import com.wm.data.IDataUtil;

public class EdiRequest {
	private String webMethodsHost;
	private String callbackUrl;
	private String username;
	private String password;
	private String packageName;
	private String serviceName;
	private String notificationAddress;

	protected final Log log = LogFactory.getLog(getClass());

	public String invoke(EdiRequestParm request, Object callBackParameter)
			throws Exception {
		String resultStatus ;
		String callBackParameterJson;
		if (callBackParameter != null) {
			try {
				callBackParameterJson = JSONDataUtils
						.buildJSONString(callBackParameter);
				log.warn(callBackParameterJson+",SystemId:"+request.getSystemID());
			} catch (Exception ex) {
				throw new SystemException(ex);
			}
		} else {
			callBackParameterJson = "{}";
		}

		callBackParameterJson = callbackUrl //request.getCallbackUrl()
				+ "{\"systemName\": \"" + request.getBizSystemName() + "\", "
				+ " \"serviceName\": \"" + request.getBizServiceName() + "\", "
				+ " \"methodName\": \"" + request.getBizMethodName() + "\", "
				+ " \"parameters\": {\"parameter\": " + callBackParameterJson
				+ " }," + " \"userCode\": \"" + request.getBizUserCode()
				+ "\" " + "}";

		log.debug(" webMethodsHost " + webMethodsHost + "\n"
				+ callBackParameterJson);
		
		if (StringUtils.isBlank(request.getNotificationAddress())){
			request.setNotificationAddress(notificationAddress);
		}
		
		Context context = new Context();
		try {
			context.connect(this.webMethodsHost, this.username, this.password);
			IData parameterDocument = IDataFactory.create();
			IDataCursor idc = parameterDocument.getCursor();

			idc.insertAfter("Sender", request.getSender());
			idc.insertAfter("Receiver", request.getReceiver());
			idc.insertAfter("MessageType", request.getMessageType());
			idc.insertAfter("Version", request.getVersion());
			idc.insertAfter("DocumentId", request.getDocumentId());
			idc.insertAfter("BizKey", request.getBizKey());
			idc.insertAfter("BatchNo", request.getBatchNo());
			idc.insertAfter("MessageStatus", request.getMessageStatus());
			idc.insertAfter("ContentType", request.getContentType());
			idc.insertAfter("DataTransferType", request.getDataTransferType());
			idc.insertAfter("DataURL", callBackParameterJson);
			idc.insertAfter("NotificationAddress",request.getNotificationAddress());
			idc.insertAfter("NotificationCellPhone",request.getNotificationCellPhone());
			idc.insertAfter("ConfirmationLevel", request.getConfirmationLevel());
			idc.insertAfter("ConfirmationURL", request.getConfirmationURL());
			idc.insertAfter("DeliveryRef", request.getDeliveryRef());
			idc.insertAfter("SystemID", request.getSystemID());

			idc.destroy();
			// IData resultDocument = context.invoke("Sino_Common_MP.service","receiveRequest", parameterDocument);
			IData resultDocument = context.invoke(packageName, serviceName,parameterDocument);

			idc = resultDocument.getCursor();
			resultStatus = IDataUtil.getString(idc, "Status");
			String resultDescription = IDataUtil.getString(idc, "Description");

			//System.out.println("resultStatus = \n" + resultStatus);
			//System.out.println("resultDescription = \n" + resultDescription);

			idc.destroy();
			
		} catch (Exception ex) {
			throw new SystemException(ex);
		} finally {
			try {
				context.disconnect();
			} catch (Exception ex) {
			}
		}

		return resultStatus;
	}

	public String getWebMethodsHost() {
		return webMethodsHost;
	}

	public void setWebMethodsHost(String webMethodsHost) {
		this.webMethodsHost = webMethodsHost;
	}

	public String getCallbackUrl() {
		return callbackUrl;
	}

	public void setCallbackUrl(String callbackUrl) {
		this.callbackUrl = callbackUrl;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	public String getNotificationAddress() {
		return notificationAddress;
	}

	public void setNotificationAddress(String notificationAddress) {
		this.notificationAddress = notificationAddress;
	}
}
