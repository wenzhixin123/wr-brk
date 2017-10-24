package com.sinotrans.gd.wlp.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import com.sinotrans.framework.core.exception.ApplicationException;
import com.sinotrans.framework.core.support.FileDesc;
import com.sinotrans.framework.core.support.FileToDownload;
import com.sinotrans.framework.core.support.FileUploadHandler;
@Service
public class FileUploadHandlerImpl implements FileUploadHandler {

	private Log log = LogFactory.getLog(this.getClass());	
	@Override
	public void uploadFile(String uuid, String type, String fileName,
			InputStream content) {
		FileOutputStream fos = null;
		
		try {	
			String uploadDir = FileUploadAssistant.getTmpUploadDir() + uuid;
			log.info(FileUploadHandler.class + ":" + uploadDir);
			File dir = new File(uploadDir);
			if(!dir.exists()){
				dir.mkdirs();
				log.info(FileUploadHandler.class + ":" + "文件夹创建成功");
			}
			fos = new FileOutputStream(new File(uploadDir + "/" + fileName));
			if(type.equalsIgnoreCase(ExcelFileParser.EXCEL_97)){
				HSSFWorkbook workbook = new HSSFWorkbook(content);
				workbook.write(fos);
			} else if (type.equalsIgnoreCase(ExcelFileParser.EXCEL_2007)){
				XSSFWorkbook workbook = new XSSFWorkbook(content);
				workbook.write(fos);
			} else {
				int length;
				byte[] bytes = new byte[1024];
				while (true) {
					length = content.read(bytes);
					if (length > 0) {
						fos.write(bytes, 0, length);
					} else {
						break;
					}
				}
			}
			log.info(FileUploadHandler.class + ":" + "文件保存成功");
		} catch (Exception e) {
			log.error(this.getClass() + "文件上传错误");
			e.printStackTrace();
		}finally{
			try {
				
				content.close();
				fos.flush();
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		
	}

	@Override
	public List<FileDesc> listUploadedFiles(String uuid, String type) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteUploadedFile(String uuid, String type, String fileName) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public FileToDownload getUploadedFile(String uuid, String type,
			String fileName) {
		// TODO Auto-generated method stub
		return null;
	}

}
