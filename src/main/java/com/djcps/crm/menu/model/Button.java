package com.djcps.crm.menu.model;

import java.util.List;

/**
 * Created by LK on 2017/7/6.
 */
public class Button {

	/**
	 * 是否成功
	 */
	private boolean success;
	/**
	 * 数据
	 */
	private List<ButtonItem> data;
	/**
	 * 提示
	 */
	private String msg;

	/**
	 * 获取 是否成功
	 */
	public boolean isSuccess() {
		return this.success;
	}

	/**
	 * 设置 是否成功
	 */
	public void setSuccess(boolean success) {
		this.success = success;
	}

	/**
	 * 获取 数据
	 */
	public List<ButtonItem> getData() {
		return this.data;
	}

	/**
	 * 设置 数据
	 */
	public void setData(List<ButtonItem> data) {
		this.data = data;
	}

	/**
	 * 获取 提示
	 */
	public String getMsg() {
		return this.msg;
	}

	/**
	 * 设置 提示
	 */
	public void setMsg(String msg) {
		this.msg = msg;
	}


	class  ButtonItem{
		/**
		 *  唯一识别
		 */
		private String id;
		/**
		 *  标识htmlid ,
		 */
		private String phtmlid;

		/**
		 *  状态1-按钮显示2-按钮隐藏
		 */
		private String status;

		/**
		 * 获取  唯一识别
		 */
		public String getId() {
			return this.id;
		}

		/**
		 * 设置  唯一识别
		 */
		public void setId(String id) {
			this.id = id;
		}

		/**
		 * 获取  标识htmlid ,
		 */
		public String getPhtmlid() {
			return this.phtmlid;
		}

		/**
		 * 设置  标识htmlid ,
		 */
		public void setPhtmlid(String phtmlid) {
			this.phtmlid = phtmlid;
		}

		/**
		 * 获取  状态1-按钮显示2-按钮隐藏
		 */
		public String getStatus() {
			return this.status;
		}

		/**
		 * 设置  状态1-按钮显示2-按钮隐藏
		 */
		public void setStatus(String status) {
			this.status = status;
		}
	}

}
