package com.djcps.crm.finance.model;

public class OutuserRequestModel {

    private String version;//版本号
    private String keyarea;//区域拆分键
    private String userId;//用户id

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getKeyarea() {
        return keyarea;
    }

    public void setKeyarea(String keyarea) {
        this.keyarea = keyarea;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
