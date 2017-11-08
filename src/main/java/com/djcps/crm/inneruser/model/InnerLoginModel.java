package com.djcps.crm.inneruser.model;

import org.hibernate.validator.constraints.NotEmpty;


/**
 * 登录接口长度json参数
 * Created by LK on 2017/6/26.
 */
public class InnerLoginModel {


	/**
	 * 用户名
	 */
	@NotEmpty
	private String username;

	/**
	 * 密码
	 */
	@NotEmpty
	private String password;

	/**
	 * 用户名
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * 用户名
	 */
	public InnerLoginModel setUsername(String username) {
		this.username = username;
		return this;
	}

	/**
	 * 密码
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * 密码
	 */
	public InnerLoginModel setPassword(String password) {
		this.password = password;
		return this;
	}
}
