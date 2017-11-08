package com.djcps.crm.integral.enums;

import com.djcps.crm.commons.msg.MsgInterface;

/**
 * Created by TruthBean on 2017-08-08 10:21.
 */
public enum IntegralMsgEnum implements MsgInterface {

    /**
     * Unknow msg enum.
     */
    UNKNOW(150001, "未知错误"),
    /**
     * Ops failure msg enum.
     */
    OPS_FAILURE(150002, "操作失败"),
    /**
     * Ops success msg enum.
     */
    OPS_SUCCESS(150003, "操作成功"),

    PARAMS_ERROR(150013, "参数错误"),

    EXCEL_CONTENT_ERROR(150014, "excel 内容错误"),

    PARAMS_NULL(150019, "参数为空");

    private int code;

    private String message;

    IntegralMsgEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    IntegralMsgEnum(int code) {
        this.code = code;
    }

    @Override
    public int getCode() {
        return this.code;
    }

    @Override
    public String getMsg() {
        return this.message;
    }
}
