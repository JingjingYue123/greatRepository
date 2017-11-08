package com.djcps.crm.inneruser.enums;

import com.djcps.crm.commons.msg.MsgInterface;

/**
 * 内部用户模块返回给前端的消息
 * Created by TruthBean on 2017-05-06 14:18
 */
public enum InnerUserMsgEnum implements MsgInterface {

    CHANGE_PSWD_SUCCESS(240011, "修改密码成功"),
    /**
     * Change pswd failure msg enum.
     */
    CHANGE_PSWD_FAILURE(240012, "修改密码失败"),
    /**
     * paranull msg enum.
     */
    PARAMS_ERROR(240013, "参数错误"),

    TOEKN_NULL(240014, "登录参数获取超时或Token过期NULL"),

    PARAMS_NULL(240019, "参数为空");

    private int code;

    private String msg;

    InnerUserMsgEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    InnerUserMsgEnum(int code) {
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
    public int getCode() {
        return code;
    }

    /**
     * Get enums string.
     *
     * @return the string
     */
    public String getMsg() {
        return msg;
    }


}
