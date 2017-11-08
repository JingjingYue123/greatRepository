package com.djcps.crm.finance.model.returnInfoModel;

import java.util.List;
/**
 * create by lmh on 2017/09/29
 */
public class ReturnBalanceCollectionData {
    private Integer total;
    private List<BalanceCollectionModel> data;

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<BalanceCollectionModel> getData() {
        return data;
    }

    public void setData(List<BalanceCollectionModel> data) {
        this.data = data;
    }
}
