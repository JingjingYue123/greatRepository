package com.djcps.crm.order.model;

/**
 * Created by jmb on 2017/10/17.
 */
public class OrderRequestModel {

    private String version;
    private String fkeyarea;
    private String manufacturer;
    private String orderid;

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    public String getFkeyarea() {
        return fkeyarea;
    }

    public void setFkeyarea(String fkeyarea) {
        this.fkeyarea = fkeyarea;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
