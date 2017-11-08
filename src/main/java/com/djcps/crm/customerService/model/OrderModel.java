package com.djcps.crm.customerService.model;

/**
 * Created by jmb on 2017/9/18.
 */
public class OrderModel {
    private String fchildorderid;
    private Integer famountpiece;

    public String getFchildorderid() {
        return fchildorderid;
    }

    public void setFchildorderid(String fchildorderid) {
        this.fchildorderid = fchildorderid;
    }

    public Integer getFamountpiece() {
        return famountpiece;
    }

    public void setFamountpiece(Integer famountpiece) {
        this.famountpiece = famountpiece;
    }
}
