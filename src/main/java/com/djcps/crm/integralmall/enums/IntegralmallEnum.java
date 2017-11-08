package com.djcps.crm.integralmall.enums;

import com.djcps.crm.commons.msg.MsgInterface;

/**
 * Created by L-wenbin on 2017/9/18.
 */
public enum IntegralmallEnum implements MsgInterface {
    /**
     * 操作成功
     */
    ADD_SUCCESS(100000,"操作成功"),
    /**
     * 操作失败
     */
    ADD_FAILURE(-100000,"操作失败")
    ;

    private int code;
    private String msg;

    public String toString() {
        return "PartnersAreaEnum{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                '}';
    }

    IntegralmallEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

}
