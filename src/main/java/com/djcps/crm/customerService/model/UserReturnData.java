package com.djcps.crm.customerService.model;

import java.util.List;

public class   UserReturnData {
    private List<OutUserBaseModel> result;
    private Integer total;

    public List<OutUserBaseModel> getResult() {
        return result;
    }

    public void setResult(List<OutUserBaseModel> result) {
        this.result = result;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }
}
