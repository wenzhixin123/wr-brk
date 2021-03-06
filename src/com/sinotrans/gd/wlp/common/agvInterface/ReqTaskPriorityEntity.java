package com.sinotrans.gd.wlp.common.agvInterface;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/9/6.
 */
public class ReqTaskPriorityEntity {
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
    //taskTyp
    private String taskTyp;
    //userCallCode
    private String userCallCode;
    //userCallCodePath
    private String userCallCodePath;
    //podCode
    private String podCode;
    //podDir
    private String podDir;

    private List<PriorityEntity> priorities;

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

    public String getTaskTyp() {
        return taskTyp;
    }

    public void setTaskTyp(String taskTyp) {
        this.taskTyp = taskTyp;
    }

    public String getUserCallCode() {
        return userCallCode;
    }

    public void setUserCallCode(String userCallCode) {
        this.userCallCode = userCallCode;
    }

    public String getUserCallCodePath() {
        return userCallCodePath;
    }

    public void setUserCallCodePath(String userCallCodePath) {
        this.userCallCodePath = userCallCodePath;
    }

    public String getPodCode() {
        return podCode;
    }

    public void setPodCode(String podCode) {
        this.podCode = podCode;
    }

    public String getPodDir() {
        return podDir;
    }

    public void setPodDir(String podDir) {
        this.podDir = podDir;
    }

    public List<PriorityEntity> getPriorities() {
        return priorities;
    }

    public void setPriorities(List<PriorityEntity> priorities) {
        this.priorities = priorities;
    }

    public static class PriorityEntity{
        //priority
        private String priority;
        //taskCode
        private String taskCode;

        public String getPriority() {
            return priority;
        }

        public void setPriority(String priority) {
            this.priority = priority;
        }

        public String getTaskCode() {
            return taskCode;
        }

        public void setTaskCode(String taskCode) {
            this.taskCode = taskCode;
        }
    }

}
