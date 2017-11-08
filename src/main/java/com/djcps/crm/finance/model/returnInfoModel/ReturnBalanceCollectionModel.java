package com.djcps.crm.finance.model.returnInfoModel;
/**
 * create by lmh on 2017/09/29
 */
public class ReturnBalanceCollectionModel {
    private String msg;
    private String code;
    private ReturnBalanceCollectionData data;
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

    public ReturnBalanceCollectionData getData() {
        return data;
    }

    public void setData(ReturnBalanceCollectionData data) {
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
