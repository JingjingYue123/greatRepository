package com.djcps.crm.menu.enums;

import com.djcps.crm.commons.msg.MsgInterface;

/**
 * 菜单模块返回给前端的消息
 * Created by TruthBean on 2017-05-06 14:18
 */
public enum MenuMsgEnum implements MsgInterface {

    /**
     * Menus info error msg enum.
     */
    MENUS_INFO_ERROR(140008, "获取菜单失败");

    private int code;

    private String msg;

    MenuMsgEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
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
