package com.djcps.crm.finance.model.returnInfoModel;

import java.util.List;

/**
 * createBy lmh on 2017/09/16
 *
 */
public class ReturnConsumeOrderModel {
    private String msg;
    private String code;
    private ReturnConsumerData data;
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

    public ReturnConsumerData getData() {
        return data;
    }

    public void setData(ReturnConsumerData data) {
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
