package com.sinotrans.gd.wlp.common.util;

import com.sinotrans.framework.core.exception.ApplicationException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContextException;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WMSClient {

	/**
	 * 消息回传服务
	 * @author BaseToYou
	 */
	public static Logger log = Logger.getLogger(WMSClient.class);

	/**
	 * 统一请求方法
	 * @param uri
	 * @param paramsMap
	 * @return
	 * @throws HttpException
	 * @throws IOException
	 */
	public String httpPostRequest(String uri,Map<String,String> paramsMap) throws HttpException, IOException{
		HttpClient client = new HttpClient();
		PostMethod post = new PostMethod(uri);
		String returnVal ="";
		try{
			List<NameValuePair> list=new ArrayList<NameValuePair>();
			if(paramsMap!=null){
				for (String key : paramsMap.keySet()) {
					NameValuePair simcard = new NameValuePair(key, paramsMap.get(key));
					list.add(simcard);
				}
			}
			if(list.size()>0){
				NameValuePair[] nameValuePairs=new NameValuePair[list.size()];
				list.toArray(nameValuePairs);
				post.setRequestBody(nameValuePairs);
			}
			post.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");
			//连接超时：connectionTimeout
			client.getHttpConnectionManager().getParams().setConnectionTimeout(3000);
			//读取数据超时：soTimeout
			client.getHttpConnectionManager().getParams().setSoTimeout(6000);
			int statusCode = client.executeMethod(post);
			if(statusCode==200){
				String tempStr = post.getResponseBodyAsString();
				returnVal = tempStr;
			}else{
				throw new ApplicationContextException("Htpp请求返回错误,错误代码:"+statusCode);
			}
		}catch(HttpException e){
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}finally{
			//释放连接。无论执行方法是否成功，都必须释放连接。
			post.releaseConnection();
		}
		return returnVal;
	}

	public Map<String,String> generaFildName(Object obj){

		Field[] fields = obj.getClass().getDeclaredFields();
		for(int i =1 ; i < fields.length;i++){
			Field var = fields[i];
			System.out.println(var.getName());
		}

		return null;
	}


}
