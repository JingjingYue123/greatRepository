package com.djcps.crm.finance.enums;


import com.djcps.crm.commons.msg.MsgInterface;

/**
 * Created by Lancher on 2017/9/1.
 */
public enum ReportFormsEnum implements MsgInterface {
    SUCCESS(100000,"操作成功"),
    FALSE(100001,"操作失败"),
    PARAM_ERROR(100002,"参数错误"),
    DATE_ERROR(100003,"日期参数不合法"),
    SEARCH_ERROR(100004,"输入的认证名跟手机号不匹配"),
    REOIRT_FORMS_LIST_ERROR(100005,"获取报表列表失败"),
    GET_CUSTOMER_ERROR(100006,"获取认证名列表失败"),
    GET_CUSTOMERBALANCE_ERROR(100007,"获取用户余额列表失败");
    private int code;
    private String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public MsgInterface setMsg(String message) {
        this.msg = message;
        return this;
    }

    ReportFormsEnum(int code, String msg) {
        this.code=code;
        this.msg=msg;
    }

    @Override
    public String toString() {
        return "ExceptionOrderEnum:{" + "\"code\":" + code + "," + "\"msg\":\"" + msg + "\"" + "}";
    }
}
