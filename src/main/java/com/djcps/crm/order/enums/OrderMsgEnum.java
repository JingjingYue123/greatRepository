package com.djcps.crm.order.enums;

import com.djcps.crm.commons.msg.MsgInterface;

/**
 * 外部用户用，返回给前端的消息信息
 * @author lancher
 *
 */
public enum OrderMsgEnum implements MsgInterface {
	/**
	 * 操作成功
	 */
	OPS_SUCCESS(110000, "操作成功"),
	/**
	 * 操作失败
	 */
	OPS_FAILURE(110001, "操作失败"),
	/**
	 * 参数为空
	 */
	PARAMS_NULL(110002, "参数为空"),
	/**
	 * 验证错误
	 */
	CODE_WRONG(110003, "验证错误"),
	/**
	 * 
	 */
	NOT_IDENTIFY(110004, "无认证信息,无权限进行此操作"),
	/**
	 * 手机号格式错误
	 */
	FPHONE_CANT_REGISTER(110005, "请输入正确手机号"),

	/**
	 * 鏈接超時
	 */
	ORDER_WAIT(110023,"操作成功，请注意刷新"),
	/**
	 * 已经有主账号
	 */
	FPHONE_IS_MIANPHONE(110006, "您已经有账号了，请直接登录如需修改，登录后可进行信息修改哦"),
	/**
	 * 是子账号
	 */
	FPHONE_IS_CHILDPHONE(110007, "您的手机号码目前已被添加为子账号请先注销后再进行注册哦"),
	/**
	 * 请不要在一分钟内重复发送验证码
	 */
	NOTIN_AMINUTE(110008, "请不要在一分钟内重复发送验证码"),
	/**
	 * 用户已存在
	 */
	USER_ISEXIST(110009, "用户已存在"),
	/**
	 * 用户已存在
	 */
	CODE_ISERROR_ORNOTEXIST(110010, "验证码错误或验证码已过期"),
	/**
	 * 用户不存在
	 */
	USER_ISNOTEXIST(110011, "用户不存在"),
	/**
	 * 新密码不能和旧密码一样
	 */
	PASSOWRD_NOTSAME(110012, "新密码不能和旧密码一样"),
	/**
	 * 密码错误
	 */
	PASSOWRD_ISERROR(110013, "密码错误"),
	/**
	 * 用户没有此权限
	 */
	NO_PERMISSION(110014, "用户没有此权限"),

	/**
	 * 参数校验错误
	 */
	PARAMS_WRONG(110015, "参数校验错误"),

	/**
	 * 没有登录信息
	 */
	NO_LOGIN_MESSAGE(110016,"没有登录信息"),

	/**
	 * 系统版本错误
	 */
	SYSTEM_VERSION_ERROR(110017,"系统版本错误"),

	/**
	 * 页码错误
	 */
	PAGE_ERROR(110018,"页码大小错误"),

	/**
	 * 分页大小错误
	 */
	PAGE_SIZE_ERROR(110019,"分页大小错误"),

	/**
	 * 参数错误
	 */
	PARAMS_ERROR(110020,"参数错误"),

	/**
	 * 系统错误
	 */
	SYS_ERROR(110021,"系统错误"),

	/**
	 * 鏈接超時
	 */
	TIMEOUT_ERROR(110022,"系统超时"),

	/**
	 * 金额或积分不能小于等于0
	 */
	MONEY_ERROR(110023,"金额或积分不能小于等于0"),

	PARAM_ERROR(110024,"缺少必要参数");

	private int code;

    private String msg;

    OrderMsgEnum(int code, String msg){
        this.code = code;
        this.msg = msg;
    }

    OrderMsgEnum(int code){
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
