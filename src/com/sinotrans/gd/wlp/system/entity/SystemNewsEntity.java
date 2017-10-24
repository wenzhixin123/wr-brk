/**
 * 
 */
package com.sinotrans.gd.wlp.system.entity;

import com.sinotrans.gd.wlp.system.model.SysNewsModel;

/**
 * @author sky
 * 
 */
@SuppressWarnings("serial")
public class SystemNewsEntity extends SysNewsModel {

	private  String basBlobUuid ;

	private String strContent;     // 字符类型的内容
	
	private String userName;        //创建人
	
	public String getBasBlobUuid() {
		return basBlobUuid;
	}

	public void setBasBlobUuid(String basBlobUuid) {
		this.basBlobUuid = basBlobUuid;
	}

	public String getStrContent() {
		return strContent;
	}

	public void setStrContent(String strContent) {
		this.strContent = strContent;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}


	
}
