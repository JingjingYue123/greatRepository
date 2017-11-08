package com.djcps.crm.inneruser.model;

/**
 * Created by LK on 2017/6/6.
 * API App登录返回实体
 */
public class DJAuthloginAppModel extends DJAuthBase<DJAuthloginAppModel.LoginToken>{


	public String getToken() {
		return data.getToken();
	}

	class LoginToken{
		private String token;

		public String getToken() {
			return token;
		}

		public LoginToken setToken(String token) {
			this.token = token;
			return this;
		}
	}
}
