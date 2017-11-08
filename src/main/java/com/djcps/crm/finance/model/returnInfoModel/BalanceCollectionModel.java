package com.djcps.crm.finance.model.returnInfoModel;

import java.math.BigDecimal;

/**
 * create by lmh on 2017/09/29
 */

public class BalanceCollectionModel {
    private String fuserId;//用户id
    private String forderId;//订单id
    private BigDecimal famount;//金额
    private Integer ftype;//业务类型
    private String ffundType;//资金类型
    private String fremark;//备注
    private String fcreateTime;//创建时间
    private String fupdateTime;//业务时间
    private Integer fkeyArea;//区域拆分键 与用户一起传递

    private String fcertificateName;//认证名
    private String fphone;//手机号

    public String getFuserId() {
        return fuserId;
    }

    public void setFuserId(String fuserId) {
        this.fuserId = fuserId;
    }

    public String getForderId() {
        return forderId;
    }

    public void setForderId(String forderId) {
        this.forderId = forderId;
    }

    public BigDecimal getFamount() {
        return famount;
    }

    public void setFamount(BigDecimal famount) {
        this.famount = famount;
    }

    public Integer getFtype() {
        return ftype;
    }

    public void setFtype(Integer ftype) {
        this.ftype = ftype;
    }

    public String getFfundType() {
        return ffundType;
    }

    public void setFfundType(String ffundType) {
        this.ffundType = ffundType;
    }

    public String getFremark() {
        return fremark;
    }

    public void setFremark(String fremark) {
        this.fremark = fremark;
    }

    public String getFcreateTime() {
        return fcreateTime;
    }

    public void setFcreateTime(String fcreateTime) {
        this.fcreateTime = fcreateTime;
    }

    public String getFupdateTime() {
        return fupdateTime;
    }

    public void setFupdateTime(String fupdateTime) {
        this.fupdateTime = fupdateTime;
    }

    public Integer getFkeyArea() {
        return fkeyArea;
    }

    public void setFkeyArea(Integer fkeyArea) {
        this.fkeyArea = fkeyArea;
    }

    public String getFcertificateName() {
        return fcertificateName;
    }

    public void setFcertificateName(String fcertificateName) {
        this.fcertificateName = fcertificateName;
    }

    public String getFphone() {
        return fphone;
    }

    public void setFphone(String fphone) {
        this.fphone = fphone;
    }
}
