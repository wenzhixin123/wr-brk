package com.sinotrans.gd.wlp.common.agvInterface;

import java.util.Date;

/**
 * Created by Administrator on 2017/9/6.
 */
public class ImsNotifyParam {
    private String berthCode;
    private String callCode;
    private String indBind;
    private String podCode;
    private String robotCode;
    //请求时间
    private String reqTime;
    //请求编号
    private String reqCode;
    //客户端编号
    private String clientCode;
    //tokenCode
    private String tokenCode;
    //interfaceName
    private String interfaceName;
    //method
    private String method;
    //taskCode
    private String taskCode;
    //userCallCode
    private String userCallCode;
    //currentCallCode
    private String currentCallCode;
    //data
    private String data;

    public String getPodCode() {
        return podCode;
    }

    public void setPodCode(String podCode) {
        this.podCode = podCode;
    }

    public String getRobotCode() {
        return robotCode;
    }

    public void setRobotCode(String robotCode) {
        this.robotCode = robotCode;
    }

    public String getCallCode() {
        return callCode;
    }

    public void setCallCode(String callCode) {
        this.callCode = callCode;
    }

    public String getIndBind() {
        return indBind;
    }

    public void setIndBind(String indBind) {
        this.indBind = indBind;
    }


    public String getBerthCode() {
        return berthCode;
    }

    public void setBerthCode(String berthCode) {
        this.berthCode = berthCode;
    }

    public String getReqTime() {
        return reqTime;
    }

    public void setReqTime(String reqTime) {
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

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getTaskCode() {
        return taskCode;
    }

    public void setTaskCode(String taskCode) {
        this.taskCode = taskCode;
    }

    public String getUserCallCode() {
        return userCallCode;
    }

    public void setUserCallCode(String userCallCode) {
        this.userCallCode = userCallCode;
    }

    public String getCurrentCallCode() {
        return currentCallCode;
    }

    public void setCurrentCallCode(String currentCallCode) {
        this.currentCallCode = currentCallCode;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
