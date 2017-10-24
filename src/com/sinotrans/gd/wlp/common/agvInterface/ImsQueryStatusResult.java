package com.sinotrans.gd.wlp.common.agvInterface;

/**
 * Created by Administrator on 2017/9/7.
 */
public class ImsQueryStatusResult {
    private String taskCode;
    private String taskTyp;
    private String taskStatus;

    public String getTaskCode() {
        return taskCode;
    }

    public void setTaskCode(String taskCode) {
        this.taskCode = taskCode;
    }

    public String getTaskTyp() {
        return taskTyp;
    }

    public void setTaskTyp(String taskTyp) {
        this.taskTyp = taskTyp;
    }

    public String getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(String taskStatus) {
        this.taskStatus = taskStatus;
    }
}
