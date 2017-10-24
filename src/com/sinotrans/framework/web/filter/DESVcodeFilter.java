package com.sinotrans.framework.web.filter;

import java.io.IOException;  
import javax.servlet.Filter;  
import javax.servlet.FilterChain;  
import javax.servlet.FilterConfig;  
import javax.servlet.ServletException;  
import javax.servlet.ServletRequest;  
import javax.servlet.ServletResponse;  
import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  

import com.sinotrans.framework.core.util.DES;


/**
 *  url带vcode加密参数的过滤
* @ClassName: DESVcodeFilter 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author A18ccms a18ccms_gmail_com 
* @date 2013年12月13日 下午2:19:19 
*
 */
public class DESVcodeFilter extends HttpServlet implements Filter {  


	private static final long serialVersionUID = 1L;  
	private String DESCheckoutSwtich = "1";
	private String DES_KEY = null;

	private static DESVcodeFilter entity = null;
	
	/**
	 * 【描述：】获取当前过滤器的单例
	 * @Title: getSigleInstance 
	 * @Author: Administrator
	 * @Time: 2013年12月13日 下午2:40:37
	 * @return DESVcodeFilter
	 */
	public static DESVcodeFilter getSigleInstance(){
		return entity;
	}
	
	public DESVcodeFilter(){
		//设置随机8位密码
		 this.DES_KEY = org.apache.commons.lang.RandomStringUtils.random(8, true, true);
		 entity = this;
	}
	
	public void doFilter(ServletRequest arg1, ServletResponse arg2,
				FilterChain filterChain) throws IOException, ServletException  {
		 
		 	HttpServletRequest request = (HttpServletRequest)arg1;
	
			HttpServletResponse response = (HttpServletResponse)arg2;
			String cypherText =  request.getParameter("vcode") ;
			
	     
	    	String plaintext = request.getSession().getId();
			
			if("1".equals(this.DESCheckoutSwtich)  && cypherText!=null
					&&this.DESVerify(cypherText,plaintext)){
				filterChain.doFilter(arg1, arg2);
			}else if("0".equals(this.DESCheckoutSwtich) ){
				filterChain.doFilter(arg1, arg2);
			}else{
				response.setStatus(400);
				response.setCharacterEncoding("UTF-8");
			    response.setContentType("text/javascript; charset=UTF-8");
			    String jsonStr = "{\"code\":\"01\",\"message\":\"Access deny!!\",\"data\":null, \"type\"=\"error_report\"}";
				response.getWriter().write(jsonStr);
				response.flushBuffer();
				return ;
			}
		 
	 }

	public void init(FilterConfig config) throws ServletException {
		//this.filterConfig = config;
	}
	/**
	 * 检查DES验证码
	 * @param cypherText 密文
	 * @param plaintext 明文{sid}
	 * @return
	 */
	protected  boolean DESVerify(String cypherText, String plaintext) 
	{ 
	 
	   String plaintextTmp = null;
	   
	   String deskey = this.DES_KEY;
	   DES des = new DES(deskey);
	   plaintextTmp = des.decrypt(cypherText);
   
	   if(plaintext.equals(plaintextTmp)){
		   return true;
	   }else{
		   return false;
	   }
	    
	}

	public String getDESCheckoutSwtich() {
		return DESCheckoutSwtich;
	}

	public void setDESCheckoutSwtich(String dESCheckoutSwtich) {
		DESCheckoutSwtich = dESCheckoutSwtich;
	}
	
	/**
	 * 【描述：】得到的是加密desKey
	 * @Title: getDES_KEY 
	 * @Author: Administrator
	 * @Time: 2013年12月13日 下午3:17:03
	 * @return String
	 */
	public String getDES_KEY(){
		DES des = new DES("^90abA?$");
		return des.encrypt(DES_KEY);
	}

	/**
	 * 【描述：】如果不设置，会自动生成随机密码
	 * @Title: setDES_KEY 
	 * @Author: Administrator
	 * @Time: 2013年12月13日 下午2:41:48
	 * @param dES_KEY void
	 */
	public void setDES_KEY(String dES_KEY) {
		DES_KEY = dES_KEY;
	} 
  
  
}  

