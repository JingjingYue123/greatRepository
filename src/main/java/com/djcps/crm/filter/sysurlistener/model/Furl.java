package com.djcps.crm.filter.sysurlistener.model;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * 控制器地址实体
 */
public class Furl {

	private String fid = UUID.randomUUID().toString().replace("-","");
	/**
	 * 控制名称
	 */
	private String fname;
	/**
	 * url
	 */
	private String furl;
	/**
	 * 登录是否拦截 0拦截 1不拦截
	 */
	private int flogintype = 0;
	/**
	 * 是否启用 0不启用 1启用
	 */
	private int feffect = 1;

	/**
	 * 创建时间
	 */
	private String fcreatetime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());


	public String getFid() {
		return this.fid;
	}

	public Furl setFid(String fid) {
		this.fid = fid;
		return this;
	}

	/**
	 * 获取 控制名称
	 */
	public String getFname() {
		return this.fname;
	}

	/**
	 * 设置 控制名称
	 */
	public Furl setFname(String fname) {
		this.fname = fname;
		return this;
	}

	/**
	 * 获取 url
	 */
	public String getFurl() {
		return this.furl;
	}

	/**
	 * 设置 url
	 */
	public Furl setFurl(String furl) {
		this.furl = furl;
		return this;
	}

	/**
	 * 获取 登录是否拦截
	 */
	public int getFlogintype() {
		return this.flogintype;
	}

	/**
	 * 设置 登录是否拦截
	 */
	public Furl setFlogintype(int flogintype) {
		this.flogintype = flogintype;
		return this;
	}

	/**
	 * 获取 是否启用
	 */
	public int getFeffect() {
		return this.feffect;
	}

	/**
	 * 设置 是否启用
	 */
	public Furl setFeffect(int feffect) {
		this.feffect = feffect;
		return this;
	}

	/**
	 * 获取 创建时间
	 */
	public String getFcreatetime() {
		return this.fcreatetime;
	}

	/**
	 * 设置 创建时间
	 */
	public Furl setFcreatetime(String fcreatetime) {
		this.fcreatetime = fcreatetime;
		return this;
	}
}
