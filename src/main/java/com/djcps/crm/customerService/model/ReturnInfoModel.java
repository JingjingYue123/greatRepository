package com.djcps.crm.customerService.model;

import com.djcps.crm.customerService.model.ReturnData;



/**
 * createBy lmh on 2017/09/13
 */
public class ReturnInfoModel {
    private String msg;
    private String code;
    private ReturnData data;
    private boolean success;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public ReturnData getData() {
        return data;
    }
    public void setData(ReturnData data) {
        this.data = data;
    }
    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
