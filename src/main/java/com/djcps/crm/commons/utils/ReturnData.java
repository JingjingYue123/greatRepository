package com.djcps.crm.commons.utils;


import java.util.List;

/**
 * Created by jmb on 2017/9/9.
 */
public  class ReturnData<T> {
    private Integer total;

    private List<T> list;

    private List<T> result;

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }


    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<T> getResult() {
        return result;
    }

    public void setResult(List<T> result) {
        this.result = result;
    }
}
