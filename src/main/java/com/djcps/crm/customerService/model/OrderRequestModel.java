package com.djcps.crm.customerService.model;

import com.djcps.crm.customerService.config.ParamsConfig;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * Created by jmb on 2017/9/13.
 */
public class OrderRequestModel {
    @NotNull(message = "订单id不能为空")
    private String childOrderId;
    @Pattern(regexp = ParamsConfig.VERSION,message = "版本好目前仅支持1.0")
    private String version;
    @NotNull(message = "区域拆分键不能为空")
    private String fkeyarea;

    public String getChildOrderId() {
        return childOrderId;
    }

    public void setChildOrderId(String childOrderId) {
        this.childOrderId = childOrderId;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getFkeyarea() {
        return fkeyarea;
    }

    public void setFkeyarea(String fkeyarea) {
        this.fkeyarea = fkeyarea;
    }
}
