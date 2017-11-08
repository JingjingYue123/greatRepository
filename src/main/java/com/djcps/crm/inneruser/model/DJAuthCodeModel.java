package com.djcps.crm.inneruser.model;

/**
 * Created by LK on 2017/6/6.
 * API 获取到用户的Code实体
 */
public class DJAuthCodeModel extends DJAuthBase<DJAuthCodeModel.code> {

	//private code data;

	public String getCode() {
		return data.getCode();
	}

	class code{
		private String code;

		public String getCode() {
			return code;
		}

		public code setCode(String code) {
			this.code = code;
			return this;
		}
	}
}
