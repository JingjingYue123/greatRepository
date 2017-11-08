package com.djcps.crm.finance.model;

import java.math.BigDecimal;

/**
 * Created by lmh on 2017/9/12.
 */
public class BalanceFormsModel {
    private  String  fuserId;//用户
    private BigDecimal famount ;//余额
    private String fphone;//手机号
    private  String  fcertificateName;//认证名

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

    public String getFcertificateName() {
        return fcertificateName;
    }

    public void setFcertificateName(String fcertificateName) {
        this.fcertificateName = fcertificateName;
    }
}
