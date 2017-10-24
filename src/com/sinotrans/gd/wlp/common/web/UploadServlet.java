package com.sinotrans.gd.wlp.common.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sinotrans.framework.core.util.ContextUtils;

public class UploadServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1590272654944786747L;
	protected final Log log = LogFactory.getLog(getClass());

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String result;
		try {
			Map<String, String> params = new HashMap<String, String>();
			Map<String, byte[]> files = new HashMap<String, byte[]>();
	        if (ServletFileUpload.isMultipartContent(request)) {
	            DiskFileItemFactory dfif = new DiskFileItemFactory();
	            ServletFileUpload sfu = new ServletFileUpload(dfif);
	            FileItemIterator fii = sfu.getItemIterator(request);
	            while (fii.hasNext()) {
	                FileItemStream fis = fii.next();
	                if (fis.isFormField()) {
	                	params.put(fis.getFieldName(), IOUtils.toString(fis.openStream()));
	                } else {
	                	files.put(fis.getFieldName(), IOUtils.toByteArray(fis.openStream()));
	                }
	            }
	        }

	        String bargeVoyageSequenceUuid = params.get("bargeVoyageSequenceUuid");
	        String mainVoyage = params.get("mainVoyage");
	        String photoType = params.get("photoType");
	        String port = params.get("port");
	        String vesselName = params.get("vesselName");
	        String officeCode = params.get("officeCode");
	        
	        byte[] pic = files.get("pic");
	        String returnKey = null;
	        
			/*
			WlpAppManager picUploadManager = ContextUtils.getBeanOfType(WlpAppManager.class);
			
			
			returnKey = picUploadManager.uploadPicture( bargeVoyageSequenceUuid, mainVoyage, photoType, port, vesselName, officeCode, pic);*/
			
	        
			result = "Success, returnKey="+returnKey;
			log.debug("Upload Success");
			
		} catch (Exception ex) {
			result = "Failure:" + ex.getMessage();
			log.error("Upload Failure", ex);
		}
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println(result);
		out.flush();
		out.close();
	}

	
}
