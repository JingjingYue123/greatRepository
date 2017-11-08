package com.djcps.crm.address.enums;

import com.djcps.crm.commons.msg.MsgInterface;

/**
 * Created by ysy on 2017/7/5 20:33.
 */
public enum AddressMsgEnum implements MsgInterface {

    /**
     * 超时
     */
    TIMEOUT_ERROR(110022,"操作失败");

    private int code;

    private String msg;

    AddressMsgEnum(int code, String msg){
        this.code = code;
        this.msg = msg;
    }

    AddressMsgEnum(int code){
        this.code = code;
    }

    private void setCode(int code) {
        this.code = code;
    }

    /**
     * Get code int.
     *
     * @return the int
     */
    public int getCode(){
        return code;
    }

    /**
     * Get msg string.
     *
     * @return the string
     */
    public String getMsg(){
        return msg;
    }
}
