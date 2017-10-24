package com.sinotrans.gd.wlp.common.agvInterface;

import java.util.List;

/**
 * Created by Administrator on 2017/8/17.
 */
public class ImsCallBack<T> {

    private String reqCode;
    private String code;
    private String message;
    private List<T> data;

    public String getReqCode() {
        return reqCode;
    }

    public void setReqCode(String reqCode) {
        this.reqCode = reqCode;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ImsCallBack{" +
                "reqCode='" + reqCode + '\'' +
                ", code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
