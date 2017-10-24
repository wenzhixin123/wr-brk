package com.sinotrans.gd.wlp.common.agvInterface;

/**
 * Created by Administrator on 2017/9/16.
 */
public class ReqTaskContinueEntity {

    private String reqCode;
    private String reqTime;
    private String clientCode;
    private String tokenCode;
    private String interfaceName;
    private String userCallCode;
    private String taskCode;
    private String taskSeq;
    private String nextCallCode;
    private String data;

    public String getReqCode() {
        return reqCode;
    }

    public void setReqCode(String reqCode) {
        this.reqCode = reqCode;
    }

    public String getReqTime() {
        return reqTime;
    }

    public void setReqTime(String reqTime) {
        this.reqTime = reqTime;
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

    public String getUserCallCode() {
        return userCallCode;
    }

    public void setUserCallCode(String userCallCode) {
        this.userCallCode = userCallCode;
    }

    public String getTaskCode() {
        return taskCode;
    }

    public void setTaskCode(String taskCode) {
        this.taskCode = taskCode;
    }

    public String getTaskSeq() {
        return taskSeq;
    }

    public void setTaskSeq(String taskSeq) {
        this.taskSeq = taskSeq;
    }

    public String getNextCallCode() {
        return nextCallCode;
    }

    public void setNextCallCode(String nextCallCode) {
        this.nextCallCode = nextCallCode;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
