package com.djcps.crm.finance.model.returnInfoModel;

import java.math.BigInteger;

/**
 * Created by jmb on 2017/9/11.
 */
public class IntegralReturnModel {
    private String orderId;
    private BigInteger integral;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public BigInteger getIntegral() {
        return integral;
    }

    public void setIntegral(BigInteger integral) {
        this.integral = integral;
    }
}
