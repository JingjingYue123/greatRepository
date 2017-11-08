package com.djcps.crm.finance.model.returnInfoModel;

import java.math.BigDecimal;

/**
 * createBy lmh on 2017/09/16
 */
public class ThirdPaymentModel {
    private String f_rowid;
    private String f_id;
    private String f_devpaytoken;
    private String f_ordernumber;
    private String f_pay3rdid;
    private String f_payerid;//用户id
    private Integer f_paystate; //支付状态 1表示支付成功
    private Integer f_withdrawalstate; //提现状态
    private BigDecimal f_amount;//金额
    private Integer f_serviceprovider;  // 1 ： 银行 2：微信 3：支付宝
    private Integer f_serviceprovidertype;//    1： 平安 , 2 ： 招商 , 3 ：SDK  4：
    private String f_data;
    private String f_createdatetime;//创建时间
    private String f_modifydatetime;
    private String fcertificateName;//认证名
    private String phone;

    public Integer getF_withdrawalstate() {
        return f_withdrawalstate;
    }

    public void setF_withdrawalstate(Integer f_withdrawalstate) {
        this.f_withdrawalstate = f_withdrawalstate;
    }

    public Integer getF_serviceprovidertype() {
        return f_serviceprovidertype;
    }

    public void setF_serviceprovidertype(Integer f_serviceprovidertype) {
        this.f_serviceprovidertype = f_serviceprovidertype;
    }

    public String getF_modifydatetime() {
        return f_modifydatetime;
    }

    public void setF_modifydatetime(String f_modifydatetime) {
        this.f_modifydatetime = f_modifydatetime;
    }

    public String getF_rowid() {

        return f_rowid;
    }

    public void setF_rowid(String f_rowid) {
        this.f_rowid = f_rowid;
    }

    public String getF_id() {
        return f_id;
    }

    public void setF_id(String f_id) {
        this.f_id = f_id;
    }

    public String getF_devpaytoken() {
        return f_devpaytoken;
    }

    public void setF_devpaytoken(String f_devpaytoken) {
        this.f_devpaytoken = f_devpaytoken;
    }

    public String getF_ordernumber() {
        return f_ordernumber;
    }

    public void setF_ordernumber(String f_ordernumber) {
        this.f_ordernumber = f_ordernumber;
    }

    public String getF_pay3rdid() {
        return f_pay3rdid;
    }

    public void setF_pay3rdid(String f_pay3rdid) {
        this.f_pay3rdid = f_pay3rdid;
    }

    public String getF_payerid() {
        return f_payerid;
    }

    public void setF_payerid(String f_payerid) {
        this.f_payerid = f_payerid;
    }

    public Integer getF_paystate() {
        return f_paystate;
    }

    public void setF_paystate(Integer f_paystate) {
        this.f_paystate = f_paystate;
    }

    public BigDecimal getF_amount() {
        return f_amount;
    }

    public void setF_amount(BigDecimal f_amount) {
        this.f_amount = f_amount;
    }

    public Integer getF_serviceprovider() {
        return f_serviceprovider;
    }

    public void setF_serviceprovider(Integer f_serviceprovider) {
        this.f_serviceprovider = f_serviceprovider;
    }

    public String getF_data() {
        return f_data;
    }

    public void setF_data(String f_data) {
        this.f_data = f_data;
    }

    public String getF_createdatetime() {
        return f_createdatetime;
    }

    public void setF_createdatetime(String f_createdatetime) {
        this.f_createdatetime = f_createdatetime;
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
}
