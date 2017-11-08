package com.djcps.crm.customerService.enums;

import com.djcps.crm.commons.msg.MsgInterface;

public enum AbnormalOrderEunm implements MsgInterface {
    /**
     * 操作成功
     */
    OPS_SUCCESS(110000, "操作成功"),
    /**
     * 操作失败
     */
    OPS_FAILURE(110001, "操作失败"),
    /**
     * 时间有误
     */
    TIME_ERROR(110002, "时间有误"),
    /**
     * 充值订单审核时缺少必要参数
     */
    ABSENSE_ERROR(110004,"缺少必要参数"),
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
     * 异常原因输入字数超过100字
     */
    CRESONAMOUNT_ERROR(110008,"客服异常原因不能为空并且不能超过100字"),
    /**
     * 客服沟通记录输入字数超过100字
     */
    FRESONAMOUNT_ERROR(110009,"财务异常原因输入字数超过100字"),

    /**
     * 客服处理异常订单方式
     */
    CABSENSEMANAGINBGTYPE_ERROR(110010,"缺少客服处理方式"),
    /**
     * 异常数量只能为大于等于零小于999999的数字
     */
    NUMBER_ERROR(110011,"异常数量只能为大于等于零小于999999的数字"),
    /**
     *获取随机编号失败
     */
    GETNUMBER_ERROR(110012,"获取随机编号创建客诉流水号失败"),
    /**
     * 返回数据失败
     */
   RESPONSE_ERROR(110013,"通过手机号查寻用户id返回数据失败"),
    ORDERSERVER_ERROR(110014,"调用订单服务失败"),

    OUT_OF_RANGE(110015,"退款数量不能大于购买数量"),
    /**
     *
     */
    USERID_ERROR(110016,"查询订单编号缺少用户Id");
    private int code;
    private String msg;
    AbnormalOrderEunm(int code,String msg) {
        this.code=code;
        this.msg=msg;
    }
    AbnormalOrderEunm(int code){
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
