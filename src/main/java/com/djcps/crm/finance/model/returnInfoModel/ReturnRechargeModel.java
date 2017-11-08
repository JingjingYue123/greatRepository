package com.djcps.crm.finance.model.returnInfoModel;

/**
 * createBy lmh on 2017/9/16
 */
public class ReturnRechargeModel {
    private String msg;
    private String code;
    private ReturnRechargeData data;
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

    public ReturnRechargeData getData() {
        return data;
    }

    public void setData(ReturnRechargeData data) {
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
