package com.djcps.crm.inneruser.model;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * 用户修改密码实体类
 * Created by LK on 2017/6/26.
 */
public class InnerChangeUserPasswordModel {

	/**
	 * 老的密码
	 */
	@NotEmpty
	private String oldPassword;

	/**
	 * 新的密码
	 */
	@NotEmpty
	private String newPassword;


	/**
	 * 获取 老的密码
	 */
	public String getOldPassword() {
		return this.oldPassword;
	}

	/**
	 * 设置 老的密码
	 */
	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	/**
	 * 获取 新的密码
	 */
	public String getNewPassword() {
		return this.newPassword;
	}

	/**
	 * 设置 新的密码
	 */
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
}
