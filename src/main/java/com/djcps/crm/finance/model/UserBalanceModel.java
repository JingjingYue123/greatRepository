package com.djcps.crm.finance.model;


/**
 * Created by jmb on 2017/9/28.
 */
public class UserBalanceModel {
    private  String  fuserId;//用户
    private String famount ;//余额
    private  String fkeyArea;//区域拆分键

    public String getFuserId() {
        return fuserId;
    }

    public void setFuserId(String fuserId) {
        this.fuserId = fuserId;
    }

    public String getFamount() {
        return famount;
    }

    public void setFamount(String famount) {
        this.famount = famount;
    }

    public String getFkeyArea() {
        return fkeyArea;
    }

    public void setFkeyArea(String fkeyArea) {
        this.fkeyArea = fkeyArea;
    }
}
