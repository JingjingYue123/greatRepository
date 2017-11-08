package com.djcps.crm.finance.model.returnInfoModel;

import java.util.List;

public class ReturnConsumerData {
    private Integer total;
    private List<ConsumeOrderModel> list;

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<ConsumeOrderModel> getList() {
        return list;
    }

    public void setList(List<ConsumeOrderModel> list) {
        this.list = list;
    }
}
