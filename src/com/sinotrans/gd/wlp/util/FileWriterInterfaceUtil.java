package com.sinotrans.gd.wlp.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

/**
 * 接口写入日志工具类
 * 
 * @author tanjin
 * @since 2013-04-16
 * 
 */
public class FileWriterInterfaceUtil {

	public static void writerLog(String interfaceName,long time,String errorMessage) {
		String date = DateUtils.dateToStringShort(new Date());
		String writerDate = DateUtils.dateToString(new Date());
		File file = new File(FileUploadAssistant.getSystemWriterInterface() + "interface_log_" + date + ".txt");
		try {
			FileWriter f = new FileWriter(file, true);
			BufferedWriter output = new BufferedWriter(f);
			if(StringUtils.isNotBlank(errorMessage)){
				output.write(writerDate + "\t" + interfaceName + "\t" + time + "ms" + "\t" + errorMessage);
			}else{
				output.write(writerDate + "\t" + interfaceName + "\t" + time + "ms");
			}
			output.newLine();
			output.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
