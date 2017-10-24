package com.sinotrans.gd.wlp.common.agvInterface;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/9/6.
 */
public class ReqTaskQueryStatusEntity {
    //请求时间
    private Date reqTime;
    //请求编号
    private String reqCode;
    //客户端编号
    private String clientCode;
    //tokenCode
    private String tokenCode;
    //interfaceName
    private String interfaceName;
    //
    private List<String> taskCodes;

    public Date getReqTime() {
        return reqTime;
    }

    public void setReqTime(Date reqTime) {
        this.reqTime = reqTime;
    }

    public String getReqCode() {
        return reqCode;
    }

    public void setReqCode(String reqCode) {
        this.reqCode = reqCode;
    }

    public String getClientCode() {
        return clientCode;
    }

    public void setClientCode(String clientCode) {
        this.clientCode = clientCode;
    }

    public String getTokenCode() {
        return tokenCode;
    }

    public void setTokenCode(String tokenCode) {
        this.tokenCode = tokenCode;
    }

    public String getInterfaceName() {
        return interfaceName;
    }

    public void setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName;
    }

    public List<String> getTaskCodes() {
        return taskCodes;
    }

    public void setTaskCodes(List<String> taskCodes) {
        this.taskCodes = taskCodes;
    }
}
