package com.djcps.crm.inneruser.model;

/**
 * Created by LK on 2017/6/5.
 *API 切换登录
 */
public class DJAuthTokenLoginModel extends DJAuthBase<DJAuthTokenLoginModel.AuthData> {

	public String getUrl() {
		return data.getUrl() ;
	}

	class AuthData{
		private String url;

		public String getUrl() {
			return url;
		}

		public AuthData setUrl(String url) {
			this.url = url;
			return this;
		}
	}
}
