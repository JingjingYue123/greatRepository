package com.djcps.crm.finance.model.returnInfoModel;

import java.math.BigDecimal;

public class ConsumeOrderModel {

    private String fpuserid;
    private Integer fpaytype;
    private String fpaymenttime;
    private BigDecimal famountprice;
    private String fcertificateName;
    private String phone;
    private String category;

    public String getFpuserid() {
        return fpuserid;
    }

    public void setFpuserid(String fpuserid) {
        this.fpuserid = fpuserid;
    }

    public Integer getFpaytype() {
        return fpaytype;
    }

    public void setFpaytype(Integer fpaytype) {
        this.fpaytype = fpaytype;
    }

    public String getFpaymenttime() {
        return fpaymenttime;
    }

    public void setFpaymenttime(String fpaymenttime) {
        this.fpaymenttime = fpaymenttime;
    }

    public BigDecimal getFamountprice() {
        return famountprice;
    }

    public void setFamountprice(BigDecimal famountprice) {
        this.famountprice = famountprice;
    }

    public String getFcertificateName() {
        return fcertificateName;
    }

    public void setFcertificateName(String fcertificateName) {
        this.fcertificateName = fcertificateName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
