package com.sinotrans.gd.wlp.common.agvInterface;

import java.util.Date;

/**
 * Created by Administrator on 2017/9/8.
 */
public class ImsMapAreaRequest {
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
    //mapShortName
    private String mapShortName;

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

    public String getMapShortName() {
        return mapShortName;
    }

    public void setMapShortName(String mapShortName) {
        this.mapShortName = mapShortName;
    }
}
