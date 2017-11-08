package com.djcps.crm.customerService.model;

/**
 * 修改余额
 */
public class ChangeBalanceModel {

    private String userId;//用户id
    private String  keyArea;//区域拆分键
    private String  amount;//修改余额数
    private String forderId;//订单id
    private Integer ftype;//修改类型（充值/扣款）
    private String  version;//版本号
    private Boolean fisSend;//是否发异步消息
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

    public Boolean getFisSend() {
        return fisSend;
    }

    public void setFisSend(Boolean fisSend) {
        this.fisSend = fisSend;
    }

    public String getFremark() {
        return fremark;
    }

    public void setFremark(String fremark) {
        this.fremark = fremark;
    }
}
