package com.djcps.crm.integral.model;

import java.math.BigInteger;

/**
 * Created by TruthBean on 2017-08-09 21:02.
 */
public class UserIntegralJournal {
    /**
     * 合作方ID
     */
    private int fpartner;

    /**
     * 合作方名称
     */
    private String partnerName;

    /**
     * 合作方区域
     */
    private String partnerAreaName;

    /**
     * 合作方区域 code
     */
    private int partnerArea;

    /**
     * 外部用户手机号
     */
    private String fphone;

    /**
     * 外部用户名称
     */
    private String fcustName;

    /**
     * 外部用户ID
     */
    private String fuserid;

    /**
     * 积分流水类型
     */
    private int ftype;

    /**
     * 积分流水类型名称
     */
    private String ftypename;

    /**
     * 积分
     */
    private BigInteger fintegral;

    /**
     * 订单编号
     */
    private String forderid;

    /**
     * 操作人
     */
    private String fcreator;

    /**
     * 操作人名称
     */
    private String createrName;

    /**
     * 操作时间
     */
    private String fcreattime;

    /**
     * 建立时间 格式化后的时间
     */
    private String fcreattimestr;

    /**
     * 备注
     */
    private String fremark;

    public String getFcreattimestr() {
        return fcreattimestr;
    }

    public void setFcreattimestr(String fcreattimestr) {
        this.fcreattimestr = fcreattimestr;
    }

    public int getFpartner() {
        return fpartner;
    }

    public void setFpartner(int fpartner) {
        this.fpartner = fpartner;
    }

    public String getPartnerName() {
        return partnerName;
    }

    public void setPartnerName(String partnerName) {
        this.partnerName = partnerName;
    }

    public String getPartnerAreaName() {
        return partnerAreaName;
    }

    public void setPartnerAreaName(String partnerAreaName) {
        this.partnerAreaName = partnerAreaName;
    }

    public int getPartnerArea() {
        return partnerArea;
    }

    public void setPartnerArea(int partnerArea) {
        this.partnerArea = partnerArea;
    }

    public String getFphone() {
        return fphone;
    }

    public void setFphone(String fphone) {
        this.fphone = fphone;
    }

    public String getFcustName() {
        return fcustName;
    }

    public void setFcustName(String fcustName) {
        this.fcustName = fcustName;
    }

    public String getFuserid() {
        return fuserid;
    }

    public void setFuserid(String fuserid) {
        this.fuserid = fuserid;
    }

    public int getFtype() {
        return ftype;
    }

    public void setFtype(int ftype) {
        this.ftype = ftype;
    }

    public BigInteger getFintegral() {
        return fintegral;
    }

    public void setFintegral(BigInteger fintegral) {
        this.fintegral = fintegral;
    }

    public String getForderid() {
        return forderid;
    }

    public void setForderid(String forderid) {
        this.forderid = forderid;
    }

    public String getCreaterName() {
        return createrName;
    }

    public void setCreaterName(String createrName) {
        this.createrName = createrName;
    }

    public String getFcreattime() {
        return fcreattime;
    }

    public void setFcreattime(String fcreattime) {
        this.fcreattime = fcreattime;
    }

    public String getFremark() {
        return fremark;
    }

    public void setFremark(String fremark) {
        this.fremark = fremark;
    }

    public String getFcreator() {
        return fcreator;
    }

    public void setFcreator(String fcreator) {
        this.fcreator = fcreator;
    }

    public String getFtypename() {
        return ftypename;
    }

    public void setFtypename(String ftypename) {
        this.ftypename = ftypename;
    }

    @Override
    public String toString() {
        return "{" + "\"fpartner\":" + fpartner + "," + "\"partnerName\":\'" + partnerName + '\'' + "," + "\"partnerAreaName\":\'" + partnerAreaName + '\'' + "," + "\"partnerArea\":" + partnerArea + "," + "\"fphone\":\'" + fphone + '\'' + "," + "\"fcustName\":\'" + fcustName + '\'' + "," + "\"fuserid\":\'" + fuserid + '\'' + "," + "\"ftype\":" + ftype + "," + "\"ftypename\":\'" + ftypename + '\'' + "," + "\"fintegral\":" + fintegral + "," + "\"forderid\":\'" + forderid + '\'' + "," + "\"fcreator\":\'" + fcreator + '\'' + "," + "\"createrName\":\'" + createrName + '\'' + "," + "\"fcreattime\":\'" + fcreattime + '\'' + "," + "\"fcreattimestr\":\'" + fcreattimestr + '\'' + "," + "\"fremark\":\'" + fremark + '\'' + '}';
    }
}
