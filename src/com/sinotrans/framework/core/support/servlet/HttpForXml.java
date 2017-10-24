package com.sinotrans.framework.core.support.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONObject;

import com.sinotrans.framework.core.exception.SystemException;
import com.sinotrans.framework.core.util.JSONDataUtils;
import com.sinotrans.gd.wlp.util.EdiXmlConverter;

public class HttpForXml{
	private static MultiThreadedHttpConnectionManager connectionManager = new MultiThreadedHttpConnectionManager();

	private static HttpClient client = new HttpClient(connectionManager);

	private static HttpForXml http = new HttpForXml();
	
	private String servletUrl;
	
	protected final Log log = LogFactory.getLog(getClass());	

	public static synchronized HttpClient getClient() {
		return client;
	}

	private HttpForXml() {
		configureClient();
	}

	public static HttpForXml getInstance() {
		return http;
	}

	private void configureClient() {
		int maxThreadsTotal = 30;
		int maxThreadsPerHost = 3;
		HttpConnectionManagerParams params = connectionManager.getParams();
		params.setStaleCheckingEnabled(true);
		params.setConnectionTimeout(2000);
		params.setSoTimeout(2000);
		params.setMaxTotalConnections(maxThreadsTotal);
		if (maxThreadsTotal > maxThreadsPerHost) {
			params.setDefaultMaxConnectionsPerHost(maxThreadsPerHost);
		} else {
			params.setDefaultMaxConnectionsPerHost(maxThreadsTotal);
		}
	}

	public <T> T invoke(Object parameter, Class<T> resultType) throws Exception {
		String parameterJson;
		JSONObject paraJson = null;
		Integer timeOut = new Integer(60000);
		try {
			parameterJson = JSONDataUtils.buildJSONString(parameter);
//			if(SessionContextUserEntity.currentUser()!=null){
//				log.warn(parameterJson);
//			}
		} catch (Exception ex) {
			throw new SystemException(ex);
		}
		//log.warn(" on " + servletUrl + "\n" + parameterJson);
		
		String resultXml = null;
		
		Http http = Http.getInstance();
		HttpClient httpClient = http.getClient();
		
		//链接超时
		httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(timeOut);  
		//读取超时
		httpClient.getHttpConnectionManager().getParams().setSoTimeout(timeOut);
		
		PostMethod postMethod = new PostMethod(servletUrl);
		
		//postMethod.setRequestHeader("Connection", "close");
		//postMethod.setRequestHeader("Content-type","application/x-www-form-urlencoded;charset=utf-8");
		//postMethod.setParameter("data",parameterJson);
		//postMethod.setParameter("_data_", map.get(WmsIcpFunctionTest.DATA));
		//postMethod.setParameter("interfaceName", map.get(WmsIcpFunctionTest.INTERFACENAME));
		//postMethod.setRequestHeader("_aop_secret", map.get(WmsIcpFunctionTest._AOP_SIGNATURE));// 密钥
		
		try {
			paraJson = new JSONObject(parameterJson);
			Iterator<String> keys = paraJson.keys();
			while(keys.hasNext()){  
				String key=keys.next();
				String value = paraJson.getString(key);
				postMethod.addParameter(key, value);
			}
			postMethod.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
			httpClient.executeMethod(postMethod);
			
			InputStream inputStream = postMethod.getResponseBodyAsStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, "utf-8"));
			StringBuffer stringBuffer = new StringBuffer();
			String str = "";
			while ((str = br.readLine()) != null) {
				stringBuffer.append(str);
			}
			resultXml = stringBuffer.toString();
//			if(SessionContextUserEntity.currentUser()!=null){
//				log.warn(resultXml);
//			}
			//释放资源
			inputStream.close();
			br.close();
			postMethod.releaseConnection();
		} catch (HttpException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			postMethod.releaseConnection();
		}		
		
        if (resultType == null) {
        	return null;
        } else {
			log.debug(servletUrl + "\n resultXml=\n" + resultXml);
			log.info("call Y2T billCenter url : "+servletUrl);
			//System.out.println(servletUrl + "\n resultJson=\n" + resultJson);
			try {
				EdiXmlConverter ediXmlConverter = new EdiXmlConverter();
				ediXmlConverter.setConvertElementName(false);
				ediXmlConverter.setElementNameFirstLowered(false);
				ediXmlConverter.setUnFullElement(false);
				return (T) ediXmlConverter.parseXml(resultXml, resultType);
			} catch (Exception ex) {
				throw new SystemException(ex);
			}
        }
	}	
	
	public String getServletUrl() {
		return servletUrl;
	}

	public void setServletUrl(String servletUrl) {
		this.servletUrl = servletUrl;
	}
	
}
