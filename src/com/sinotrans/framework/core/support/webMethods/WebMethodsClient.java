package com.sinotrans.framework.core.support.webMethods;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.sinotrans.framework.core.exception.SystemException;
import com.sinotrans.framework.core.util.JSONDataUtils;
import com.sinotrans.gd.wlp.system.entity.SessionContextUserEntity;
import com.wm.app.b2b.client.Context;
import com.wm.data.IData;
import com.wm.data.IDataCursor;
import com.wm.data.IDataFactory;
import com.wm.data.IDataUtil;

@Service
public class WebMethodsClient {

    protected final Log log = LogFactory.getLog(getClass());

	private String webMethodsHost;
	private String username;
	private String password;
	
	public void publish(String documentTypeName, Object document) {
		String documentJson;
		try {
			documentJson = JSONDataUtils.buildJSONString("document", document, true);
		} catch (Exception ex) {
			throw new SystemException(ex);
		}
		log.info("Publishing document " + documentTypeName + " to " + webMethodsHost + "\n" + documentJson);
		
		Context context = new Context();
		try {
			context.connect(this.webMethodsHost, this.username, this.password);
			IData parameterDocument = IDataFactory.create();
	        IDataCursor idc = parameterDocument.getCursor();
	        idc.insertAfter("documentTypeName", documentTypeName);
	        idc.insertAfter("documentJson", documentJson);
	        idc.destroy();
	        context.invoke("TransgdEaiPublic.service", "publishJsonDocument", parameterDocument);
			log.info("Published document " + documentTypeName + " to " + webMethodsHost);
		} catch (Exception ex) {
			throw new SystemException(ex);
		} finally {
			try {
		        context.disconnect();
			} catch (Exception ex) {
			}
		}
	}

	public void invoke(String serviceName, Object parameter) {
		this.invoke(serviceName, parameter, null);
	}
	
	public <T> T invoke(String serviceName, Object parameter, Class<T> resultType) {
		String parameterJson;
		try {
			parameterJson = JSONDataUtils.buildJSONString("parameter", parameter, true);
		} catch (Exception ex) {
			throw new SystemException(ex);
		}
		if(SessionContextUserEntity.currentUser()!=null){
			log.warn("Invoking service " + serviceName + " on " + webMethodsHost + "\n" + parameterJson);
		}
		
		String resultJson = null;
		Context context = new Context();
		try {
			context.connect(this.webMethodsHost, this.username, this.password);
			IData parameterDocument = IDataFactory.create();
	        IDataCursor idc = parameterDocument.getCursor();
	        idc.insertAfter("serviceName", serviceName);
	        idc.insertAfter("parameterJson", parameterJson);
	        idc.destroy();
	        IData resultDocument = context.invoke("TransgdEaiPublic.service", "wmServiceJsonDelegate", parameterDocument);
	        if (resultType != null) {
		        idc = resultDocument.getCursor();
		        resultJson = IDataUtil.getString(idc, "resultJson");
		        idc.destroy();
	        }
		} catch (Exception ex) {
			throw new SystemException(ex);
		} finally {
			try {
		        context.disconnect();
			} catch (Exception ex) {
			}
		}

        if (resultType == null) {
        	return null;
        } else {
        	if(SessionContextUserEntity.currentUser()!=null){
        		log.warn("Service " + serviceName + " on " + webMethodsHost + " returned\n" + resultJson);
        	}
			try {
				return (T) JSONDataUtils.parseJSONObject("result", resultType, resultJson);
			} catch (Exception ex) {
				throw new SystemException(ex);
			}
        }
	}

	
	
	public String getWebMethodsHost() {
		return webMethodsHost;
	}

	public void setWebMethodsHost(String webMethodsHost) {
		this.webMethodsHost = webMethodsHost;
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

}
