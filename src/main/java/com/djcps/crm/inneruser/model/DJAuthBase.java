package com.djcps.crm.inneruser.model;


/**
 * Created by LK on 2017/6/6.
 * 内部统一登录接口 数据格式基类
 */
public abstract class DJAuthBase<T> {

	protected boolean success;
	protected T data;
	protected String msg;

	public boolean isSuccess() {
		return success;
	}

	public DJAuthBase setSuccess(boolean success) {
		this.success = success;
		return this;
	}

	public String getMsg() {
		return msg;
	}

	public DJAuthBase setMsg(String msg) {
		this.msg = msg;
		return this;
	}
}
