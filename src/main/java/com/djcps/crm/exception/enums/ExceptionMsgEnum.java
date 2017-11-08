package com.djcps.crm.exception.enums;

import com.djcps.crm.commons.msg.MsgInterface;

/**
 * Created by LK on 2017/6/26.
 */
public enum ExceptionMsgEnum implements MsgInterface {


	/**
	 * 404
	 */
	RESOURCES_NOT_FOUNT(404, "404");


	private int code;

	private String msg;

	ExceptionMsgEnum(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	/**
	 * Get code int.
	 *
	 * @return the int
	 */
	@Override
	public int getCode() {
		return this.code;
	}

	/**
	 * Get enums string.
	 *
	 * @return the string
	 */
	@Override
	public String getMsg() {
		return this.msg;
	}
}
