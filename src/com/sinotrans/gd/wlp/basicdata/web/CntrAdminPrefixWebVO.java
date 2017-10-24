/**   
* @Title: CntrAdminPrefixWebVO.java 
* @Package com.sinotrans.gd.wlp.basicdata.web 
* @Description: TODO(用一句话描述该文件做什么) 
* @author A18ccms A18ccms_gmail_com   
* @date 2013年10月22日 下午1:11:49 
* @version V1.0   
*/ 
package com.sinotrans.gd.wlp.basicdata.web;

/** 
 * @ClassName: CntrAdminPrefixWebVO 
 * @Description: TODO(用于接受客户端提交的控箱公司前缀) 
 * @author A18ccms a18ccms_gmail_com 
 * @date 2013年10月22日 下午1:11:49 
 *  
 */
public class CntrAdminPrefixWebVO {

	private String id;
	private String typeId;
	private String status;
	private String cntrAdminCode;
	private String prefix;
	private String rowState;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCntrAdminCode() {
		return cntrAdminCode;
	}
	public void setCntrAdminCode(String cntrAdminCode) {
		this.cntrAdminCode = cntrAdminCode;
	}
	public String getPrefix() {
		return prefix;
	}
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
	public String getTypeId() {
		return typeId;
	}
	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}
	public String getRowState() {
		return rowState;
	}
	public void setRowState(String rowState) {
		this.rowState = rowState;
	}
	
	
}
