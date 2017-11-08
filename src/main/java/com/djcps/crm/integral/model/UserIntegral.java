package com.djcps.crm.integral.model;

import com.djcps.crm.commons.utils.StringUtils;

import java.math.BigInteger;

/**
 * 用户积分
 * Created by TruthBean on 2017-08-09 13:38.
 */
public class UserIntegral {

    /**
     * 外部用户ID
     */
    private String fuserid;

    /**
     * 外部用户名称
     */
    private String fcustName;

    /**
     * 外部用户手机号
     */
    private String fphone;

    /**
     * 外部用户积分余额
     */
    private BigInteger fintegral;

    /**
     * 外部用户积分余额更新时间
     */
    private String fupdatetimeStr;

    private long fupdatetime;

    private String fkeyArea;

    public String getFkeyArea() {
        return fkeyArea;
    }

    public void setFkeyArea(String fkeyArea) {
        this.fkeyArea = fkeyArea;
    }

    public String getFuserid() {
        return fuserid;
    }

    public void setFuserid(String fuserid) {
        this.fuserid = fuserid;
    }

    public String getFcustName() {
        return fcustName;
    }

    public void setFcustName(String fcustName) {
        this.fcustName = fcustName;
    }

    public String getFphone() {
        return fphone;
    }

    public void setFphone(String fphone) {
        this.fphone = fphone;
    }

    public BigInteger getFintegral() {
        return fintegral;
    }

    public void setFintegral(BigInteger fintegral) {
        this.fintegral = fintegral;
    }

    public String getFupdatetimeStr() {
        if (fupdatetime > 0)
            this.fupdatetimeStr = StringUtils.formatDateStr(fupdatetime);
        return fupdatetimeStr;
    }

    public void setFupdatetimeStr(String fupdatetimeStr) {
        this.fupdatetimeStr = fupdatetimeStr;
    }

    public long getFupdatetime() {
        return fupdatetime;
    }

    public void setFupdatetime(long fupdatetime) {
        if (fupdatetime > 0)
            this.fupdatetimeStr = StringUtils.formatDateStr(fupdatetime);
        this.fupdatetime = fupdatetime;
    }

    @Override
    public String toString() {
        return "{" + "\"fuserid\":\'" + fuserid + '\'' + "," + "\"fcustName\":\'" + fcustName + '\'' + "," + "\"fphone\":\'" + fphone + '\'' + "," + "\"fintegral\":" + fintegral + "," + "\"fupdatetimeStr\":\'" + fupdatetimeStr + '\'' + "," + "\"fupdatetime\":" + fupdatetime + '}';
    }
}
