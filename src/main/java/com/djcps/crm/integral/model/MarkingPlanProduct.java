package com.djcps.crm.integral.model;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * 营销策略产品
 * Created by TruthBean on 2017-08-09 20:36.
 */
public class MarkingPlanProduct {
    /**
     * 合作方行政区域code
     */
    private int fsupplierareaid;

    /**
     * 合作方行政区域名称
     */
    private String fsupplierarea;

    /**
     * 合作方ID
     */
    private String fsupplierid;

    /**
     * 合作方名称
     */
    private String fsuppliername;

    /**
     * 产品销售区域code
     */
    private int fgroupareaid;

    /**
     * 产品销售区域
     */
    private String fgrouparea;

    /**
     * 产品ID
     */
    private String fid;

    /**
     * 产品名称
     */
    private String fproductname;

    /**
     * 单元赠送金额
     */
    private BigDecimal cellPayment;

    /**
     * 赠送单元积分
     */
    private BigInteger cellIntegral;

    /**
     * 积分规则ID
     */
    private String IntegralRuleId;

    public int getFsupplierareaid() {
        return fsupplierareaid;
    }

    public void setFsupplierareaid(int fsupplierareaid) {
        this.fsupplierareaid = fsupplierareaid;
    }

    public String getFsupplierarea() {
        return fsupplierarea;
    }

    public void setFsupplierarea(String fsupplierarea) {
        this.fsupplierarea = fsupplierarea;
    }

    public String getFsupplierid() {
        return fsupplierid;
    }

    public void setFsupplierid(String fsupplierid) {
        this.fsupplierid = fsupplierid;
    }

    public String getFsuppliername() {
        return fsuppliername;
    }

    public void setFsuppliername(String fsuppliername) {
        this.fsuppliername = fsuppliername;
    }

    public int getFgroupareaid() {
        return fgroupareaid;
    }

    public void setFgroupareaid(int fgroupareaid) {
        this.fgroupareaid = fgroupareaid;
    }

    public String getFgrouparea() {
        return fgrouparea;
    }

    public void setFgrouparea(String fgrouparea) {
        this.fgrouparea = fgrouparea;
    }

    public String getFid() {
        return fid;
    }

    public void setFid(String fid) {
        this.fid = fid;
    }

    public String getFproductname() {
        return fproductname;
    }

    public void setFproductname(String fproductname) {
        this.fproductname = fproductname;
    }

    public BigDecimal getCellPayment() {
        return cellPayment;
    }

    public void setCellPayment(BigDecimal cellPayment) {
        this.cellPayment = cellPayment;
    }

    public BigInteger getCellIntegral() {
        return cellIntegral;
    }

    public void setCellIntegral(BigInteger cellIntegral) {
        this.cellIntegral = cellIntegral;
    }

    public String getIntegralRuleId() {
        return IntegralRuleId;
    }

    public void setIntegralRuleId(String integralRuleId) {
        IntegralRuleId = integralRuleId;
    }
}
