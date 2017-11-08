package com.djcps.crm.finance.model.returnInfoModel;
import com.djcps.crm.finance.model.BalanceWarningModel;
import java.util.List;

/**
 * createBy lmh on 2017/09/16
 */
public class ReturnWarningData {
    private Integer total;

    private List<BalanceWarningModel> result;

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<BalanceWarningModel> getResult() {
        return result;
    }

    public void setResult(List<BalanceWarningModel> result) {
        this.result = result;
    }
}
