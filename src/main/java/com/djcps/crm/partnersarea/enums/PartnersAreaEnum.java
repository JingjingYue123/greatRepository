package com.djcps.crm.partnersarea.enums;

import com.djcps.crm.commons.msg.MsgInterface;

/**
 * Created by L-wenbin on 2017/7/8.
 */
public enum PartnersAreaEnum implements MsgInterface{
    /**
     * 添加成功
     */
    ADD_SUCCESS(100000,"添加成功"),
    /**
     * 添加失败
     */
    ADD_FAILURE(100001,"添加失败"),
    /**
     * 删除成功
     */
    DEL_SUCCESS(100002,"删除成功"),
    /**
     * 删除失败
     */
    DEL_FAILURE(100003,"删除失败"),
    /**
     * 操作失败
     */
    OPERATION_FAILURE(100004,"操作失败"),
    ;
    private int code;
    private String msg;

    public String toString() {
        return "PartnersAreaEnum{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                '}';
    }

    PartnersAreaEnum(int code, String msg) {
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
