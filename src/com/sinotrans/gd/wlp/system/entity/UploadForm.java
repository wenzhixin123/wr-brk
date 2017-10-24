/**
 * 
 */
package com.sinotrans.gd.wlp.system.entity;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

/**
 * @author sky
 * 
 */
@SuppressWarnings("serial")
public class UploadForm extends ActionForm {

	private FormFile busFile;

	public FormFile getBusFile() {
		return busFile;
	}

	public void setBusFile(FormFile busFile) {
		this.busFile = busFile;
	}

}
