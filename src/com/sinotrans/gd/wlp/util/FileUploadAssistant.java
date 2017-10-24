package com.sinotrans.gd.wlp.util;

import java.io.IOException;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sinotrans.framework.core.support.SystemConfig;

public class FileUploadAssistant {
	
	public static String getTmpUploadDir(){
		Properties configProperties = new Properties();
		Log log = LogFactory.getLog(FileUploadAssistant.class);
		try {
			
			configProperties.load(FileUploadAssistant.class.getResourceAsStream("/systemConfig.properties"));
			String dir = configProperties.getProperty("UPLOAD_DIR", SystemConfig.USER_HOME_DIR);
			log.info(dir);
			return dir;
		} catch (IOException e) {
			log.error("文件保存路径获取错误");
			throw new RuntimeException(e);
		}
	}
	public static String getTmpUploadDir2(){
		Properties configProperties = new Properties();
		Log log = LogFactory.getLog(FileUploadAssistant.class);
		try {
			
			configProperties.load(FileUploadAssistant.class.getResourceAsStream("/systemConfig.properties"));
			String dir = configProperties.getProperty("UPLOAD_DIR2", SystemConfig.USER_HOME_DIR);
			log.info(dir);
			return dir;
		} catch (IOException e) {
			log.error("文件保存路径获取错误");
			throw new RuntimeException(e);
		}
	}
	public static String getSystemEnvironment(){
		Properties configProperties = new Properties();
		Log log = LogFactory.getLog(FileUploadAssistant.class);
		try {
			
			configProperties.load(FileUploadAssistant.class.getResourceAsStream("/systemConfig.properties"));
			String dir = configProperties.getProperty("SYSTEM_ENVIRONMENT", SystemConfig.USER_HOME_DIR);
			log.info(dir);
			return dir;
		} catch (IOException e) {
			log.error("文件保存路径获取错误");
			throw new RuntimeException(e);
		}
	}
	
	public static String getSystemWriterInterface(){
		Properties configProperties = new Properties();
		Log log = LogFactory.getLog(FileUploadAssistant.class);
		try {
			
			configProperties.load(FileUploadAssistant.class.getResourceAsStream("/systemConfig.properties"));
			String dir = configProperties.getProperty("WRITER_INTERFACE", SystemConfig.USER_HOME_DIR);
			log.info(dir);
			return dir;
		} catch (IOException e) {
			log.error("文件保存路径获取错误");
			throw new RuntimeException(e);
		}
	}
}
