package com.djcps.crm.finance.model.returnInfoModel;

import com.djcps.crm.finance.model.RechargeOrderParamModel;

import java.util.List;

/**
 * createBy lmh on 2017/09/16
 */
public class ReturnRechargeData {
    private Integer total;
    private List<RechargeOrderParamModel> list;

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<RechargeOrderParamModel> getList() {
        return list;
    }

    public void setList(List<RechargeOrderParamModel> list) {
        this.list = list;
    }
}
