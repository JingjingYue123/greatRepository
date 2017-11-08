package com.djcps.crm.finance.model;

/**
 * 余额预警信息model
 * createBy lmh on 2017/9/12
 */
public class BalanceWarningModel {
    //用户id
    private String fuserId;
    //区域拆分键
    private Integer fkeyArea;
    //校验日期
    private String fcheckDate;
    //昨日余额
    private Double flastDayBalance;
    //实际余额
    private Double factualBalance;
    //理想余额
    private Double ftheoryBalance;
    //线上转入
    private Double fonlineInto;
    //线上消费
    private Double fonlineConsumer;
    //线上退款
    private Double fonlineRefund;
    //线下转入
    private Double fofflineInto;
    //线下转出
    private Double fofflineOut;
    //是否异常
    private Boolean fisAbnormal;
    //是否已处理
    private Boolean fisProcessed;
    //创建时间
    private String fcreateTime;
    //更新时间
    private String fupdateTime;

    //认证名
    private String fcertificateName;
    //手机号
    private String fphone;

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

    public String getFuserId() {
        return fuserId;
    }

    public void setFuserId(String fuserId) {
        this.fuserId = fuserId;
    }

    public Integer getFkeyArea() {
        return fkeyArea;
    }

    public void setFkeyArea(Integer fkeyArea) {
        this.fkeyArea = fkeyArea;
    }

    public String getFcheckDate() {
        return fcheckDate;
    }

    public void setFcheckDate(String fcheckDate) {
        this.fcheckDate = fcheckDate;
    }

    public Double getFlastDayBalance() {
        return flastDayBalance;
    }

    public void setFlastDayBalance(Double flastDayBalance) {
        this.flastDayBalance = flastDayBalance;
    }

    public Double getFactualBalance() {
        return factualBalance;
    }

    public void setFactualBalance(Double factualBalance) {
        this.factualBalance = factualBalance;
    }

    public Double getFtheoryBalance() {
        return ftheoryBalance;
    }

    public void setFtheoryBalance(Double ftheoryBalance) {
        this.ftheoryBalance = ftheoryBalance;
    }

    public Double getFonlineInto() {
        return fonlineInto;
    }

    public void setFonlineInto(Double fonlineInto) {
        this.fonlineInto = fonlineInto;
    }

    public Double getFonlineConsumer() {
        return fonlineConsumer;
    }

    public void setFonlineConsumer(Double fonlineConsumer) {
        this.fonlineConsumer = fonlineConsumer;
    }

    public Double getFonlineRefund() {
        return fonlineRefund;
    }

    public void setFonlineRefund(Double fonlineRefund) {
        this.fonlineRefund = fonlineRefund;
    }

    public Double getFofflineInto() {
        return fofflineInto;
    }

    public void setFofflineInto(Double fofflineInto) {
        this.fofflineInto = fofflineInto;
    }

    public Double getFofflineOut() {
        return fofflineOut;
    }

    public void setFofflineOut(Double fofflineOut) {
        this.fofflineOut = fofflineOut;
    }

    public Boolean getFisAbnormal() {
        return fisAbnormal;
    }

    public void setFisAbnormal(Boolean fisAbnormal) {
        this.fisAbnormal = fisAbnormal;
    }

    public Boolean getFisProcessed() {
        return fisProcessed;
    }

    public void setFisProcessed(Boolean fisProcessed) {
        this.fisProcessed = fisProcessed;
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
}
