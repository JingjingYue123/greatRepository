package com.djcps.crm.finance.model;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

/**
 * Created by jmb on 2017/9/9.
 */
public class MixReportFromModel {
    private String phone;//手机号
    private String customerName;//认证名
    private String userId;//外部用户id
    private String orderId;//订单号
    private String productName;//产品名称
    private String createTime;//下单时间
    private BigDecimal fproductarea;//出库面积
    private BigDecimal money;//出库金额
    private BigDecimal refundGround=BigDecimal.valueOf(0.0);//退款面积
    private BigDecimal orderMoney;//订单单价
    private BigDecimal exceptionRefundMoney;//异常订单退款金额
    private String giveintegral;//赠送积分
    private BigInteger integral;//退还积分
    private Integer fpatchamount;//异常订单补片数量
    private int orderStatus;//订单状态 7 为已取消
    private BigDecimal famountpiece;//订单片数
    /**
     * //0为正常状态没有发生退款也没有异常订单
     *  1 为异常订单
     *  2 为取消订单
     */
    private int status=0;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }


    public BigDecimal getFproductarea() {
        return fproductarea;
    }

    public void setFproductarea(BigDecimal fproductarea) {
        this.fproductarea = fproductarea;
    }

    public BigDecimal getRefundGround() {
        return refundGround;
    }

    public void setRefundGround(BigDecimal refundGround) {
        this.refundGround = refundGround;
    }

    public BigDecimal getOrderMoney() {
        return orderMoney;
    }

    public void setOrderMoney(BigDecimal orderMoney) {
        this.orderMoney = orderMoney;
    }

    public BigDecimal getExceptionRefundMoney() {
        return exceptionRefundMoney;
    }

    public void setExceptionRefundMoney(BigDecimal exceptionRefundMoney) {
        this.exceptionRefundMoney = exceptionRefundMoney;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public String getGiveintegral() {
        return giveintegral;
    }

    public void setGiveintegral(String giveintegral) {
        this.giveintegral = giveintegral;
    }

    public BigInteger getIntegral() {
        return integral;
    }

    public void setIntegral(BigInteger integral) {
        this.integral = integral;
    }

    public Integer getFpatchamount() {
        return fpatchamount;
    }

    public void setFpatchamount(Integer fpatchamount) {
        this.fpatchamount = fpatchamount;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(int orderStatus) {
        this.orderStatus = orderStatus;
    }

    public BigDecimal getFamountpiece() {
        return famountpiece;
    }

    public void setFamountpiece(BigDecimal famountpiece) {
        this.famountpiece = famountpiece;
    }
}
