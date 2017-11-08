package com.djcps.crm.finance.model.returnInfoModel;

import com.djcps.crm.finance.model.BalanceFormsModel;
import com.djcps.crm.finance.model.BalanceWarningModel;


import java.util.List;

/**
 * Created by lmh on 2017/9/12.
 */
public class ReturnBalanceModel {

    private String msg;
    private String code;
    private List<BalanceFormsModel> data;
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

    public List<BalanceFormsModel> getData() {
        return data;
    }

    public void setData(List<BalanceFormsModel> data) {
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
