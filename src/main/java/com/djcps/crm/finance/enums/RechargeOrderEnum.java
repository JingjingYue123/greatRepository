package com.djcps.crm.finance.enums;

import com.djcps.crm.commons.msg.MsgInterface;

/**
 * Created by jmb on 2017/8/22.
 */
public enum  RechargeOrderEnum implements MsgInterface{
    /**
     * 操作成功
     */
    OPS_SUCCESS(110000, "操作成功"),
    /**
     * 操作失败
     */
    OPS_FAILURE(110001, "操作失败"),
    /**
     * 充值订单审核时缺少必要参数
     */
    AUDIT_ERROR(110004,"充值订单审核时缺少必要参数"),

    /**
     * 手机号为空
     */
    PHONES_NULL(110005,"手机号为空"),
    /**
     * 系统错误
     */
    SYS_ERROR(110006,"系统错误"),
    /**
     * 模糊查询手机号时缺少必要参数
     */
    SELECT_PHONE_ERROR(110007,"模糊查询手机号时缺少必要参数"),
    /**
     *更新余额缺少参数
     */
    ABSENCE_ERROR(110008,"更新余额缺少参数"),
    /**
     *获取随机编号失败
     */
    GETERROR_ERROR(110009,"获取随机号创建订单编号失败"),
    /**
     * 调用余额服务失败
     */
    BALANCE_ERROR(110010,"调用余额服务失败"),
    BALANCE_TOO_BIG(110011,"扣款金额不能大于用户余额");
    private int code;
    private String msg;
    RechargeOrderEnum(int code,String msg) {
        this.code=code;
        this.msg=msg;
    }
    RechargeOrderEnum(int code){
        this.code=code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getMsg() {
        return msg;
    }
}
