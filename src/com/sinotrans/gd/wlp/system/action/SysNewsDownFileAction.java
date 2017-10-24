/**
 * 
 */
package com.sinotrans.gd.wlp.system.action;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.net.URLEncoder;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.stereotype.Service;

import com.sinotrans.framework.core.util.ContextUtils;
import com.sinotrans.gd.wlp.basicdata.model.BasBlobModel;
import com.sinotrans.gd.wlp.basicdata.service.BasBlobManager;
import com.sinotrans.gd.wlp.common.web.RcUtil;
import com.sinotrans.gd.wlp.common.web.SinotransBaseAction;

/**
 * @author trx
 * 
 */
@Service
public class SysNewsDownFileAction extends SinotransBaseAction {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		String basBlobUuid = request.getParameter("basBlobUuid");
		ServletOutputStream output = null;
		byte[] file = null;
		String fileName = null;
		try {
			if(!RcUtil.isEmpty(basBlobUuid)){
				BasBlobModel basBlobModel = ContextUtils.getBeanOfType(
						BasBlobManager.class).get(basBlobUuid);
				file = basBlobModel.getData();
				fileName = basBlobModel.getTypeDesc();
			}
			
			if (request.getHeader("User-Agent").toLowerCase().indexOf("firefox") > 0){
				fileName = new String(fileName.getBytes("UTF-8"), "ISO8859-1"); //firefox浏览器
			}else if (request.getHeader("User-Agent").toUpperCase().indexOf("MSIE") > 0){
				fileName = URLEncoder.encode(fileName, "UTF-8");   //IE 浏览器
			}
			
			response.reset();
			response.setContentType("application/x-download");
			response.addHeader("Content-Disposition", "attachment; filename="+fileName);
			if (!RcUtil.isEmpty(file)) {
				output = response.getOutputStream();
				ByteArrayInputStream isw = new ByteArrayInputStream(file);
				int buffer = isw.available() > 1024 ? 1024 : isw.available();
				BufferedInputStream bis = new BufferedInputStream(isw, buffer);
				if (bis != null) {
					int count;
					// 数据缓冲区
					byte[] data = new byte[1024];
					while ((count = bis.read(data, 0, buffer)) != -1) {
						output.write(data, 0, count);
					}
					bis.close();
				}

				isw.close();
				output.flush();
				output.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;

	}

}
