package com.djcps.crm.integral.model;

public class IntegralUploadTips {
    private String phone;
    private UserIntegralChangeTips phoneTip;
    private String name;
    private UserIntegralChangeTips nameTip;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public UserIntegralChangeTips getPhoneTip() {
        return phoneTip;
    }

    public void setPhoneTip(UserIntegralChangeTips phoneTip) {
        this.phoneTip = phoneTip;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UserIntegralChangeTips getNameTip() {
        return nameTip;
    }

    public void setNameTip(UserIntegralChangeTips nameTip) {
        this.nameTip = nameTip;
    }
}
