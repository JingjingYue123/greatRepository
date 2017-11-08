package com.djcps.crm.partners.enums;

import com.djcps.crm.commons.msg.MsgInterface;

/**
 * Created by L-wenbin on 2017/7/8.
 */
public enum PartnersEnums implements MsgInterface {
    /**
     * 操作成功
     */
    ADD_SUCCESS(100200, "操作成功"),
    /**
     * 操作失败
     */
    ADD_ERROR(100404, "操作失败"),


    ;
    private int code;
    private String msg;

    PartnersEnums(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "PartnersEnums{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                '}';
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public MsgInterface setMsg(String message) {
        this.msg = message;
        return this;
    }
}
