package com.djcps.crm.finance.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

/**
 * Created by Lancher on 2017/8/29.
 */
public class ChangeUserAccountModel {

    @NotEmpty
    private String userId;//外部用户Id

    @NotEmpty
    private String keyArea;//区域拆分键

    @NotEmpty
    private String amount;//修改金额

    @NotEmpty
    private String forderId;//充值订单Id
    @NotNull
    private Integer ftype;//充值/扣款

    @NotEmpty
    private String version;//版本号

    private String ffundType;//资金类型

    private String fremark;//备注

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getKeyArea() {
        return keyArea;
    }

    public void setKeyArea(String keyArea) {
        this.keyArea = keyArea;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getForderId() {
        return forderId;
    }

    public void setForderId(String forderId) {
        this.forderId = forderId;
    }

    public Integer getFtype() {
        return ftype;
    }

    public void setFtype(Integer ftype) {
        this.ftype = ftype;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
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
}
