package com.djcps.crm.finance.model.returnInfoModel;

import com.djcps.crm.finance.model.BalanceWarningModel;

import java.util.List;

public class ReturnBalanceWarningModel {
    private String msg;
    private String code;
    private ReturnWarningData data;
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

    public ReturnWarningData getData() {
        return data;
    }

    public void setData(ReturnWarningData data) {
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
