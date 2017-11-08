package com.djcps.crm.partnersarea.model;

/**
 * Created by L-wenbin on 2017/7/8.
 */
public class PartnersArea {
    /**
     * oaid
     */
    private int oaid;
    /**
     * 合作方UUID
     */
    private String pid;
    /**
     * 区域UUID
     */
    private String aid;
    /**
     * 省份code
     */
    private int provinceCode;
    /**
     * 省份名称
     */
    private String provinceName;
    /**
     * 市code
     */
    private int cityCode;
    /**
     * 市名称
     */
    private String cityName;
    /**
     * 区code
     */
    private int countyCode;
    /**
     * 区名称
     */
    private String countyName;
    /**
     * 区域拆分键
     */
    private int keyarea;

    //===========================

    @Override
    public String toString() {
        return "PartnersArea{" +
                "oaid=" + oaid +
                ", pid='" + pid + '\'' +
                ", aid='" + aid + '\'' +
                ", provinceCode=" + provinceCode +
                ", provinceName='" + provinceName + '\'' +
                ", cityCode=" + cityCode +
                ", cityName='" + cityName + '\'' +
                ", countyCode=" + countyCode +
                ", countyName='" + countyName + '\'' +
                ", keyarea=" + keyarea +
                '}';
    }

    public PartnersArea() {
    }

    public PartnersArea(int oaid, String pid, String aid, int provinceCode, String provinceName, int cityCode, String cityName, int countyCode, String countyName, int keyarea) {
        this.oaid = oaid;
        this.pid = pid;
        this.aid = aid;
        this.provinceCode = provinceCode;
        this.provinceName = provinceName;
        this.cityCode = cityCode;
        this.cityName = cityName;
        this.countyCode = countyCode;
        this.countyName = countyName;
        this.keyarea = keyarea;
    }

    public int getOaid() {
        return oaid;
    }

    public void setOaid(int oaid) {
        this.oaid = oaid;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getAid() {
        return aid;
    }

    public void setAid(String aid) {
        this.aid = aid;
    }

    public int getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(int provinceCode) {
        this.provinceCode = provinceCode;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public int getCityCode() {
        return cityCode;
    }

    public void setCityCode(int cityCode) {
        this.cityCode = cityCode;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public int getCountyCode() {
        return countyCode;
    }

    public void setCountyCode(int countyCode) {
        this.countyCode = countyCode;
    }

    public String getCountyName() {
        return countyName;
    }

    public void setCountyName(String countyName) {
        this.countyName = countyName;
    }

    public int getKeyarea() {
        return keyarea;
    }

    public void setKeyarea(int keyarea) {
        this.keyarea = keyarea;
    }
}
