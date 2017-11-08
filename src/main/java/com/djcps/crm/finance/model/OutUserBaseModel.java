package com.djcps.crm.finance.model;

import java.math.BigDecimal;

/**
 * Created by jmb on 2017/9/9.
 */
public class OutUserBaseModel {
    private String fuserId;
    private String faccount;
    private String fkeyArea;
    private String fcertificateName;
    private String fcreateTime;
    private String fcustName;
    private String fphone;
    private BigDecimal famount;

    public String getFuserId() {
        return fuserId;
    }

    public void setFuserId(String fuserId) {
        this.fuserId = fuserId;
    }

    public String getFaccount() {
        return faccount;
    }

    public void setFaccount(String faccount) {
        this.faccount = faccount;
    }

    public String getFkeyArea() {
        return fkeyArea;
    }

    public void setFkeyArea(String fkeyArea) {
        this.fkeyArea = fkeyArea;
    }

    public String getFcertificateName() {
        return fcertificateName;
    }

    public void setFcertificateName(String fcertificateName) {
        this.fcertificateName = fcertificateName;
    }

    public String getFcreateTime() {
        return fcreateTime;
    }

    public void setFcreateTime(String fcreateTime) {
        this.fcreateTime = fcreateTime;
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

    public BigDecimal getFamount() {
        return famount;
    }

    public void setFamount(BigDecimal famount) {
        this.famount = famount;
    }
}
