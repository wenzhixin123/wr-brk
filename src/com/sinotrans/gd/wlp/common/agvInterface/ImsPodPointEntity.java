package com.sinotrans.gd.wlp.common.agvInterface;

import java.util.Date;

/**
 * Created by Administrator on 2017/9/7.
 */
public class ImsPodPointEntity {

    //reqCode
    private String reqCode;
    //reqTime
    private String reqTime;
    //clientCode
    private String clientCode;
    //tokenCode
    private String tokenCode;
    //interfaceName
    private String interfaceName;
    //podCode
    private String podCode;
    //pointCode
    private String pointCode;
    //indBind
    private String indBind;

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

    public String getPodCode() {
        return podCode;
    }

    public void setPodCode(String podCode) {
        this.podCode = podCode;
    }

    public String getPointCode() {
        return pointCode;
    }

    public void setPointCode(String pointCode) {
        this.pointCode = pointCode;
    }

    public String getIndBind() {
        return indBind;
    }

    public void setIndBind(String indBind) {
        this.indBind = indBind;
    }
}
