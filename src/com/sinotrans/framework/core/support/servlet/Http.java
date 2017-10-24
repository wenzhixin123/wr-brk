package com.sinotrans.framework.core.support.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONObject;

import com.sinotrans.framework.core.exception.SystemException;
import com.sinotrans.framework.core.util.JSONDataUtils;
import com.sinotrans.gd.wlp.system.entity.SessionContextUserEntity;

public class Http {
	private static MultiThreadedHttpConnectionManager connectionManager = new MultiThreadedHttpConnectionManager();

	private static HttpClient client = new HttpClient(connectionManager);

	private static Http http = new Http();
	
	private String servletUrl;
	
	protected final Log log = LogFactory.getLog(getClass());	

	public static synchronized HttpClient getClient() {
		return client;
	}

	private Http() {
		configureClient();
	}

	public static Http getInstance() {
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

	//public <T> T invoke(String serviceName, Object parameter, Class<T> resultType) {
	public <T> T invoke(Object parameter, Class<T> resultType) throws Exception {
		String parameterJson;
		JSONObject paraJson = null;
		Integer timeOut = new Integer(0);
		try {
			 parameterJson = JSONDataUtils.buildJSONString(parameter);
			 
			 //System.out.println("parameter Json="+parameterJson);
			 
			 //modify by wins , 暂时延长作业办单的网络超时时间 , 现象好似是网络访问60秒不够长
			 paraJson = new JSONObject(parameterJson);
			 if(SessionContextUserEntity.currentUser()!=null
					 ||"requestLogisticsOrderCalcY2T".equals(paraJson.get("methodName"))){
				 log.warn(parameterJson);
			 }
/*			 if(paraJson!=null&&"sendDCSLogisticsOrder".equals(paraJson.get("methodName"))){
				 timeOut = 120000;
			 }else{
				 timeOut = 60000;
			 }*/
			 // modify by Jiang
			 timeOut = 300000;
		} catch (Exception ex) {
			throw new SystemException(ex);
		}
		//log.warn(" on " + servletUrl + "\n" + parameterJson);
		
		String resultJson = null;
		
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
			
			RequestEntity entity = new StringRequestEntity(parameterJson, "text/xml","utf-8"); 
			postMethod.setRequestEntity(entity);
			httpClient.executeMethod(postMethod);
			
			InputStream inputStream = postMethod.getResponseBodyAsStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, "utf-8"));
			StringBuffer stringBuffer = new StringBuffer();
			String str = "";
			while ((str = br.readLine()) != null) {
				stringBuffer.append(str);
			}
			resultJson = stringBuffer.toString();
			if(SessionContextUserEntity.currentUser()!=null
					 ||"requestLogisticsOrderCalcY2T".equals(paraJson.get("methodName"))){
				log.warn(resultJson);
			}
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
			log.debug(servletUrl + "\n resultJson=\n" + resultJson);
			//System.out.println(servletUrl + "\n resultJson=\n" + resultJson);
			try {
				return (T) JSONDataUtils.parseJSONObject("result", resultType, resultJson);
			} catch (Exception ex) {
				throw new SystemException((String)JSONDataUtils.parseJSONObject("exception", resultType, resultJson));
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
