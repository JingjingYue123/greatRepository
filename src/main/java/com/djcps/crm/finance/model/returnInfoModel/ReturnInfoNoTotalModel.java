package com.djcps.crm.finance.model.returnInfoModel;

import org.apache.poi.ss.formula.functions.T;

import java.util.List;

/**
 * Created by jmb on 2017/9/11.
 */
public class ReturnInfoNoTotalModel {
    private String msg;
    private String code;
    private List<IntegralReturnModel> data;
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

    public List<IntegralReturnModel> getData() {
        return data;
    }

    public void setData(List<IntegralReturnModel> data) {
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
