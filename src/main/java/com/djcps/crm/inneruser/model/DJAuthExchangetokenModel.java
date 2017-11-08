package com.djcps.crm.inneruser.model;

/**
 * Created by LK on 2017/6/5.
 * API 获取固定的Token实体
 */
public class DJAuthExchangetokenModel extends DJAuthBase<DJAuthExchangetokenModel.Token> {

	public String getToken() {
		return data.getToken();
	}

	class Token{
		private String token;

		public String getToken() {
			return token;
		}

		public Token setToken(String token) {
			this.token = token;
			return this;
		}
	}
}
