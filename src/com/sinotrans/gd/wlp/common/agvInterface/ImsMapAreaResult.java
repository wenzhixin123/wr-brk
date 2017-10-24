package com.sinotrans.gd.wlp.common.agvInterface;

/**
 * Created by Administrator on 2017/9/8.
 */
public class ImsMapAreaResult {
    private String areaCode;
    private Integer cooX;
    private Integer cooY;
    private String dataTyp;
    private String direction;
    private String mapDataCode;
    private String userCallCode;

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public Integer getCooX() {
        return cooX;
    }

    public void setCooX(Integer cooX) {
        this.cooX = cooX;
    }

    public Integer getCooY() {
        return cooY;
    }

    public void setCooY(Integer cooY) {
        this.cooY = cooY;
    }

    public String getDataTyp() {
        return dataTyp;
    }

    public void setDataTyp(String dataTyp) {
        this.dataTyp = dataTyp;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getMapDataCode() {
        return mapDataCode;
    }

    public void setMapDataCode(String mapDataCode) {
        this.mapDataCode = mapDataCode;
    }

    public String getUserCallCode() {
        return userCallCode;
    }

    public void setUserCallCode(String userCallCode) {
        this.userCallCode = userCallCode;
    }
}
