package com.djcps.crm.customerService.model;



import java.util.List;

public class ReturnData {
    private Integer total;
    private List<OutUserBaseModel> result;
    private List<AbnormalOrderModel> list;
    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<OutUserBaseModel> getResult() {
        return result;
    }

    public void setResult(List<OutUserBaseModel> result) {
        this.result = result;
    }

    public List<AbnormalOrderModel> getList() {
        return list;
    }

    public void setList(List<AbnormalOrderModel> list) {
        this.list = list;
    }
}
