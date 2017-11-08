package com.djcps.crm.filter.sysinnerurlinterceptor.enums;

public enum Permission {

	/**
	 * 不需要用户登入 0：需要登录 1：不需要
	 */
	NO_NEED_LOGIN(1),

	/**
	 * 接口有效 0：无效 1：有效
	 */
	PORT_IS_USEFUL(1);

	private int permission;
	Permission(int permission){
		this.permission = permission;
	}
	public int getPermission() {
		return permission;
	}

	public void setPermission(int permission) {
		this.permission = permission;
	}
	
}
