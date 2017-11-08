package com.djcps.crm.finance.model.returnInfoModel;

import com.djcps.crm.finance.model.MixReportFromModel;
import com.djcps.crm.finance.model.OutUserBaseModel;

import java.util.List;

/**
 * Created by jmb on 2017/9/9.
 */
public class ReturnData {
    private Integer total;

    private List<MixReportFromModel> list;

    private List<OutUserBaseModel> result;

    public List<MixReportFromModel> getList() {
        return list;
    }

    public void setList(List<MixReportFromModel> list) {
        this.list = list;
    }


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
}
