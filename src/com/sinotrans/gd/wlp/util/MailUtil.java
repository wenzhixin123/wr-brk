package com.sinotrans.gd.wlp.util;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

public class MailUtil {
	private static final String smtphost = "smtp1.transgd.com.cn";
	private static final String charSet = "utf-8";
	private static Log log=LogFactory.getLog(MailUtil.class);
	private static String email="@transgd.com.cn";

	
	 private static String emilHtml=" <table border='1'  cellpadding='0' cellspacing='0' >"+
	" <tr>"+
	"	<td align='center' style='padding: 3px'>周期 </td>"+
	"	<td align='center' style='padding: 3px'>项目</td>"+
	"	<td align='center' style='padding: 3px'>总量</td>"+
	"   <td align='center' style='padding: 3px'>柜量</td>"+
	" </tr>"+
	"@"+
	"</table>";
	
	/**
	 * 发送普通邮件
	 * 
	 * @param toMailAddr
	 *            收信人地址
	 * @param subject
	 *            email主题
	 * @param message
	 *            发送email信息
	 * @param sendUser
	 *            发件人邮箱
	 * @param password
	 *            发件人密码
	 */
	public static void sendCommonMail(String toMailAddr, String subject, String message, String sendUser, String password) {
		HtmlEmail hemail = new HtmlEmail();
		try {
			hemail.setHostName(smtphost);
			hemail.setCharset(charSet);
			if (toMailAddr.indexOf(",") > -1) {
				log.warn(">>>> "+toMailAddr);
				String[] mailAdd = toMailAddr.split(",");
				for (int i = 0; i < mailAdd.length; i++) {
					hemail.addTo(mailAdd[i]);
				}
			} else {
				log.warn("++++++ "+toMailAddr); 
				hemail.addTo(toMailAddr);
			}
			log.warn(sendUser+email);
			if(StringUtils.isNotBlank(message)){
				emilHtml=emilHtml.replace("@", message);
			}
			hemail.setFrom(sendUser+email, "");
			hemail.setAuthentication(sendUser+email, password);
			hemail.setSubject(subject);
			hemail.setMsg(emilHtml);
			hemail.send();
			log.warn("email send true!");
		} catch (Exception e) {
			e.printStackTrace();
			log.warn("email send error!");
		}
	}
	
	/**
	 * 发送普通邮件
	 * 
	 * @param toMailAddr
	 *            收信人地址
	 * @param subject
	 *            email主题
	 * @param message
	 *            发送email信息
	 * @param sendUser
	 *            发件人邮箱
	 * @param password
	 *            发件人密码
	 * @throws EmailException 
	 */
	public static void sendMessageMail(String toMailAddr, String subject, String message, String senderAddr , String sendUser, String password) throws EmailException {
		HtmlEmail hemail = new HtmlEmail();
		hemail.setHostName(smtphost);
		hemail.setCharset(charSet);
		if (toMailAddr.indexOf(",") > -1) {
			log.warn(">>>> "+toMailAddr);
			String[] mailAdd = toMailAddr.split(",");
			for (int i = 0; i < mailAdd.length; i++) {
				hemail.addTo(mailAdd[i]);
			}
		} else {
			log.warn("++++++ "+toMailAddr); 
			hemail.addTo(toMailAddr);
		}
		log.warn(sendUser);
		if(StringUtils.isBlank(message)){
			log.error("邮件内容为空!");
			return;
		}
		hemail.setFrom(senderAddr, "");
		hemail.setAuthentication(sendUser, password);
		hemail.setSubject(subject);
		hemail.setMsg(message);
		hemail.send();
		log.warn("email send true!");
	}

	public static void main(String[] args) {
		sendCommonMail("663695753@qq.com", "测试主题", "测试内容", "zengjian@transgd.com.cn", "Wy2013");
	}

}


