package com.djcps.crm.order.model;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Map;

/**
 * Created by Lancher on 2017/8/14.
 */
public class OrderParamModel {
    /**
     * 订单服务参数
     */
    private Map[] childOrderIds;
    private String operator;//操作人
    private String operatorid;//操作人账号id
    private String version;//版本号
    private String fmanufacturer;//合作方id
    private String fmanufacturerkeyarea;//合作方拆分键
    private String childOrderId;//订单id
    private String fmktplanchangeid;//变更方案id
    private String fsalevolume;//产品平方
    /**
     * 余额服务参数
     */
    private String userId;//用户id
    private String keyArea;//客户拆分键
    private BigDecimal amount;//金额
    private String forderId;//订单编号
    private String ftype;//业务类型 1余额充值  2团购余额消费  3商场余额消费  4团购订单取消退款  5商场订单取消退款  6异常退款  7余额转出
    /**
     * 积分服务参数
     */
    private String fkeyarea;//客户拆分键
    private String fuserid;//用户id
    private String fintegeralruletype;//积分规则类型  1.充值  2.购买商品  3.CRM直接调整（线下类型）
    private BigDecimal fintegeral;//调整积分
    private String fpartnerid;//合作方ID
    /**
     * 1 购买产品送积分 加积分
     2 商城订单取消 加积分
     3 商城消费 扣积分
     4 团购订单取消 扣积分
     线下类型
     5 社区活动积分 加积分
     6 商城活动积分 加积分
     7 包辅活动积分 加积分
     8 会员返利积分 加积分
     9 专享返利积分 加积分
     10 其他返利积分 加积分
     充值类型
     11 充值送积分
     12 充值失败扣积分
     */
    private String fintegeraljournaltype;//积分流水类型
    private String fpartnerFkeyarea;//合作商拆分键
    private String fcreater;//操作人
    private String forderid;//订单编号
    private String fintegralruleid;//积分规则ID

    public String getFsalevolume() {
        return fsalevolume;
    }

    public void setFsalevolume(String fsalevolume) {
        this.fsalevolume = fsalevolume;
    }

    public String getFmanufacturerkeyarea() {
        return fmanufacturerkeyarea;
    }

    public void setFmanufacturerkeyarea(String fmanufacturerkeyarea) {
        this.fmanufacturerkeyarea = fmanufacturerkeyarea;
    }

    public String getFmktplanchangeid() {
        return fmktplanchangeid;
    }

    public void setFmktplanchangeid(String fmktplanchangeid) {
        this.fmktplanchangeid = fmktplanchangeid;
    }

    public Map[] getChildOrderIds() {
        return childOrderIds;
    }

    public void setChildOrderIds(Map[] childOrderIds) {
        this.childOrderIds = childOrderIds;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getFmanufacturer() {
        return fmanufacturer;
    }

    public void setFmanufacturer(String fmanufacturer) {
        this.fmanufacturer = fmanufacturer;
    }

    public String getFkeyarea() {
        return fkeyarea;
    }

    public void setFkeyarea(String fkeyarea) {
        this.fkeyarea = fkeyarea;
    }

    public String getFpartnerFkeyarea() {
        return fpartnerFkeyarea;
    }

    public void setFpartnerFkeyarea(String fpartnerFkeyarea) {
        this.fpartnerFkeyarea = fpartnerFkeyarea;
    }

    public String getFcreater() {
        return fcreater;
    }

    public void setFcreater(String fcreater) {
        this.fcreater = fcreater;
    }

    public String getForderid() {
        return forderid;
    }

    public void setForderid(String forderid) {
        this.forderid = forderid;
    }

    public String getFintegralruleid() {
        return fintegralruleid;
    }

    public void setFintegralruleid(String fintegralruleid) {
        this.fintegralruleid = fintegralruleid;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getKeyArea() {
        return keyArea;
    }

    public void setKeyArea(String keyArea) {
        this.keyArea = keyArea;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getForderId() {
        return forderId;
    }

    public void setForderId(String forderId) {
        this.forderId = forderId;
    }

    public String getFtype() {
        return ftype;
    }

    public void setFtype(String ftype) {
        this.ftype = ftype;
    }

    public String getFuserid() {
        return fuserid;
    }

    public void setFuserid(String fuserid) {
        this.fuserid = fuserid;
    }

    public String getFintegeralruletype() {
        return fintegeralruletype;
    }

    public void setFintegeralruletype(String fintegeralruletype) {
        this.fintegeralruletype = fintegeralruletype;
    }

    public BigDecimal getFintegeral() {
        return fintegeral;
    }

    public void setFintegeral(BigDecimal fintegeral) {
        this.fintegeral = fintegeral;
    }

    public String getFpartnerid() {
        return fpartnerid;
    }

    public void setFpartnerid(String fpartnerid) {
        this.fpartnerid = fpartnerid;
    }

    public String getFintegeraljournaltype() {
        return fintegeraljournaltype;
    }

    public void setFintegeraljournaltype(String fintegeraljournaltype) {
        this.fintegeraljournaltype = fintegeraljournaltype;
    }

    public String getChildOrderId() {
        return childOrderId;
    }

    public void setChildOrderId(String childOrderId) {
        this.childOrderId = childOrderId;
    }

    public String getOperatorid() {
        return operatorid;
    }

    public void setOperatorid(String operatorid) {
        this.operatorid = operatorid;
    }

    @Override
    public String toString() {
        return "{" +
                "childOrderIds=" + Arrays.toString(childOrderIds) +
                ", operator='" + operator + '\'' +
                ", operatorid='" + operatorid + '\'' +
                ", version='" + version + '\'' +
                ", fmanufacturer='" + fmanufacturer + '\'' +
                ", childOrderId='" + childOrderId + '\'' +
                ", userId='" + userId + '\'' +
                ", keyArea='" + keyArea + '\'' +
                ", amount='" + amount + '\'' +
                ", forderId='" + forderId + '\'' +
                ", ftype='" + ftype + '\'' +
                ", fkeyarea='" + fkeyarea + '\'' +
                ", fuserid='" + fuserid + '\'' +
                ", fintegeralruletype='" + fintegeralruletype + '\'' +
                ", fintegeral='" + fintegeral + '\'' +
                ", fpartnerid='" + fpartnerid + '\'' +
                ", fintegeraljournaltype='" + fintegeraljournaltype + '\'' +
                ", fpartnerFkeyarea='" + fpartnerFkeyarea + '\'' +
                ", fcreater='" + fcreater + '\'' +
                ", forderid='" + forderid + '\'' +
                ", fintegralruleid='" + fintegralruleid + '\'' +
                '}';
    }

    public String toString1(){
        return "{" +
                "version='" + version + '\'' +
                ", fkeyarea='" + fmanufacturerkeyarea + '\'' +
                ", fmktplanchangeid='" + fmktplanchangeid + '\'' +
                ", fsalevolume='" + fsalevolume + '\'' +
                ", childOrderId='" + childOrderId + '\'' +
                '}';
    }

    public String toJsonString() {
        return "[{" + "\"childOrderIds\":" + Arrays.toString(childOrderIds) + "," + "\"operator\":\"" + operator+ "\"" + "," + "\"operatorid\":\"" + operatorid + "\"" + "," + "\"version\":\"" + version + "\"" + "," + "\"fmanufacturer\":\"" + fmanufacturer + "\"" + "," + "\"childOrderId\":\"" + childOrderId + "\"" + "," + "\"userId\":\"" + userId + "\"" + "," + "\"keyArea\":\"" + keyArea + "\"" + "," + "\"amount\":\"" + amount + "\"" + "," + "\"forderId\":\"" + forderId + "\"" + "," + "\"ftype\":\"" + ftype + "\"" + "," + "\"fkeyarea\":\"" + fkeyarea + "\"" + "," + "\"fuserid\":\"" + fuserid + "\"" + "," + "\"fintegeralruletype\":\"" + fintegeralruletype + "\"" + "," + "\"fintegeral\":\"" + fintegeral + "\"" + "," + "\"fpartnerid\":\"" + fpartnerid + "\"" + "," + "\"fintegeraljournaltype\":\"" + fintegeraljournaltype + "\"" + "," + "\"fpartnerFkeyarea\":\"" + fpartnerFkeyarea + "\"" + "," + "\"fcreater\":\"" + fcreater + "\"" + "," + "\"forderid\":\"" + forderid + "\"" + "," + "\"fintegralruleid\":\"" + fintegralruleid + "\"" + "}]";
    }

}
