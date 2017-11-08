package com.djcps.crm.menu.model;

import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * 菜单信息
 * Created by truth on 2017-03-17.
 */
public class Menu {

	/**
	 * 是否成功
	 */
	private boolean success;
	/**
	 * 数据
	 */
	private List<MenuItem> data;
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
	public List<MenuItem> getData() {
		return this.data;
	}

	/**
	 * 设置 数据
	 */
	public void setData(List<MenuItem> data) {
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

}
