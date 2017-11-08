package com.djcps.crm.finance.model;

import java.math.BigDecimal;

/**
 * Created by jmb on 2017/8/22.
 */
public class RechargeOrderParamModel {
    /**
     * 充值订单-审核参数
     */
    private String fid;//唯一标识
    private String auditor;//审核人
    private String audittime;//审核时间
    private String auditExplain;//审核说明
    private String orderId;//订单号
    private int auditType;//操作状态：通过/驳回

    /**
     * 充值订单-保存订单
     */

    private String userId;//用户id
    private int keyArea;//区域拆分键
    private String phone;//手机号////共用于外部用户根据手机号模糊查询的参数
    private int status;//状态
    private int orderType;//类别
    private Double amount;//金额
    private int fundType;//资金类型
    private String remark;//备注
    private String operator;//操作人
    private String operatingTime;//操作时间
    private String uppercaseAmount;//大写金额
    /**
     * 外部用户—列表参数
     * @return
     */
    private String version;//版本号
    private String fcertificateName;//认证名

    //充值列表返回数据
    private String fuserId;//用户id
    private BigDecimal famount;//用户余额
    private String fphone;//用户手机号
    private Integer ffundType;//资金类型
    private Integer forderType;//
    private String fremark;//备注
    private String fauditTime;//审核时间

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }



    public String getFid() {
        return fid;
    }

    public void setFid(String fid) {
        this.fid = fid;
    }

    public String getAuditor() {
        return auditor;
    }

    public void setAuditor(String auditor) {
        this.auditor = auditor;
    }

    public String getAudittime() {
        return audittime;
    }

    public void setAudittime(String audittime) {
        this.audittime = audittime;
    }

    public String getAuditExplain() {
        return auditExplain;
    }

    public void setAuditExplain(String auditExplain) {
        this.auditExplain = auditExplain;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getKeyArea() {
        return keyArea;
    }

    public void setKeyArea(int keyArea) {
        this.keyArea = keyArea;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getOrderType() {
        return orderType;
    }

    public void setOrderType(int orderType) {
        this.orderType = orderType;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public int getFundType() {
        return fundType;
    }

    public void setFundType(int fundType) {
        this.fundType = fundType;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getOperatingTime() {
        return operatingTime;
    }

    public void setOperatingTime(String operatingTime) {
        this.operatingTime = operatingTime;
    }

    public String getUppercaseAmount() {
        return uppercaseAmount;
    }

    public void setUppercaseAmount(String uppercaseAmount) {
        this.uppercaseAmount = uppercaseAmount;
    }
    public int getAuditType() {
        return auditType;
    }

    public void setAuditType(int auditType) {
        this.auditType = auditType;
    }

    public String getFcertificateName() {
        return fcertificateName;
    }

    public void setFcertificateName(String fcertificateName) {
        this.fcertificateName = fcertificateName;
    }

    public String getFuserId() {
        return fuserId;
    }

    public void setFuserId(String fuserId) {
        this.fuserId = fuserId;
    }

    public BigDecimal getFamount() {
        return famount;
    }

    public void setFamount(BigDecimal famount) {
        this.famount = famount;
    }

    public String getFphone() {
        return fphone;
    }

    public void setFphone(String fphone) {
        this.fphone = fphone;
    }

    public Integer getFfundType() {
        return ffundType;
    }

    public void setFfundType(Integer ffundType) {
        this.ffundType = ffundType;
    }

    public Integer getForderType() {
        return forderType;
    }

    public void setForderType(Integer forderType) {
        this.forderType = forderType;
    }

    public String getFremark() {
        return fremark;
    }

    public void setFremark(String fremark) {
        this.fremark = fremark;
    }

    public String getFauditTime() {
        return fauditTime==null?null:fauditTime.substring(0,19);
    }

    public void setFauditTime(String fauditTime) {
        if (fauditTime!=null){
            this.fauditTime = fauditTime.substring(0,19);
        }
    }
}
