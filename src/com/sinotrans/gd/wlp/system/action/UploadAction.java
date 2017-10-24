/**
 * 
 */
package com.sinotrans.gd.wlp.system.action;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import com.sinotrans.framework.core.util.ContextUtils;
import com.sinotrans.gd.wlp.common.service.BasCommonManager;
import com.sinotrans.gd.wlp.common.web.RcUtil;
import com.sinotrans.gd.wlp.common.web.SinotransBaseAction;
import com.sinotrans.gd.wlp.common.web.SinotransPageJson;
import com.sinotrans.gd.wlp.system.entity.UploadForm;
import com.sinotrans.gd.wlp.util.JsonUtil;

/**
 * @author sky
 * 
 *         系统公用上传类
 * 
 */
public class UploadAction extends SinotransBaseAction {

	@SuppressWarnings("deprecation")
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UploadForm uf = (UploadForm) form;
		FormFile ff = uf.getBusFile();
		String businessType = request.getParameter("businessType");
		String businessFileType = request.getParameter("businessFileType");
		String modelIds = request.getParameter("modelIds");
		String agentConsigneeCode = request.getParameter("agentConsigneeCode");
		String agentConsigneeDesc = request.getParameter("agentConsigneeDesc");
		String officeCode=RcUtil.isEmpty(request.getParameter("officeCode"))?"":request.getParameter("officeCode");
		String remark = request.getParameter("remark");
		String versionType=request.getParameter("versionType");
		Map<String, Object> valueMap = new HashMap<String, Object>();
		valueMap.put("remark", remark);
		valueMap.put("officeCode", officeCode);
		if (ff != null && !RcUtil.isEmpty(ff.getFileName())) {
			String path = request.getRealPath("/");
			String fileName = ff.getFileName();
			//fileName = uploadFile(ff, request.getRealPath("/upload"));
			if(RcUtil.isEmpty(officeCode)){
				officeCode = getUser().getOfficeCode();
			}
			try {
				
				SinotransPageJson spj = ContextUtils.getBeanOfType(
						BasCommonManager.class).importFileFactory(path,
						businessType, businessFileType, modelIds, fileName,
						ff.getInputStream(), officeCode, agentConsigneeCode,
						agentConsigneeDesc, valueMap);
				
				request.setAttribute("spj", JsonUtil.beanToJson(spj));
				request.setAttribute("result", spj.isResult());
				if (!spj.isResult()) {
					request.setAttribute("error", spj.getError());
				} else {
					request.setAttribute("msg", spj.getMsg());
				}
			} catch (Exception e) {
				SinotransPageJson spj = new SinotransPageJson(false, "", e
						.getMessage());
				request.setAttribute("spj", JsonUtil.beanToJson(spj));
				request.setAttribute("error", e.getMessage());
				request.setAttribute("result", false);
				log.error(this, e);
				e.fillInStackTrace();
			}
		}
		request.setAttribute("divId", request.getParameter("divId"));
		request.setAttribute("fileType", request.getParameter("fileType"));
		request.setAttribute("versionType", versionType);
		request.setAttribute("businessType", request.getParameter("businessType"));
		request.setAttribute("functionName", request.getParameter("functionName"));
		request.setAttribute("modelIds", request.getParameter("modelIds"));
		return mapping.findForward("upload");
	}

	/**
	 * 上传文件
	 * 
	 * @param ff
	 * @param filePath
	 * @return
	 */
	private String uploadFile(FormFile ff, String filePath) {
		OutputStream bos = null;
		InputStream stream = null;
		try {
			stream = ff.getInputStream();// 把文件读入
			File file = new File(filePath + "/" + ff.getFileName());
			bos = new FileOutputStream(file);// 建立一个上传文件的输出流
			int bytesRead = 0;
			byte[] buffer = new byte[8192];
			while ((bytesRead = stream.read(buffer, 0, 8192)) != -1) {
				bos.write(buffer, 0, bytesRead);// 将文件写入服务器
			}
			return file.getAbsolutePath();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (bos != null) {
					bos.close();
				}
				if (stream != null) {
					stream.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return "";
	}
}
