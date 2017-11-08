package com.djcps.crm.menu.model;

/**
 * Created by LK on 2017/7/10.
 */
public class MenuItem {
	/**
	 * 唯一识别
	 */
	private String id;
	/**
	 * 说明(菜单名称、按钮名称)
	 */
	private String ptitle;
	/**
	 * 层级 ,
	 */
	private String polayer;
	/**
	 * 父节点
	 */
	private String pfather;
	/**
	 * 顶节点
	 */
	private String pfirst;
	/**
	 * 1-按钮 0-菜单 ,
	 */
	private String isParent;
	/**
	 * 图标
	 */
	private String icon;
	/**
	 * 接口
	 */
	private String pinterface;
	/**
	 * 详细说明
	 */
	private String pmark;

	/**
	 * 自定义id
	 */
	private String phtmlid;

	/**
	 * 获取 唯一识别
	 */
	public String getId() {
		return this.id;
	}

	/**
	 * 设置 唯一识别
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * 获取 说明(菜单名称、按钮名称)
	 */
	public String getPtitle() {
		return this.ptitle;
	}

	/**
	 * 设置 说明(菜单名称、按钮名称)
	 */
	public void setPtitle(String ptitle) {
		this.ptitle = ptitle;
	}

	/**
	 * 获取 层级 ,
	 */
	public String getPolayer() {
		return this.polayer;
	}

	/**
	 * 设置 层级 ,
	 */
	public void setPolayer(String polayer) {
		this.polayer = polayer;
	}

	/**
	 * 获取 父节点
	 */
	public String getPfather() {
		return this.pfather;
	}

	/**
	 * 设置 父节点
	 */
	public void setPfather(String pfather) {
		this.pfather = pfather;
	}

	/**
	 * 获取 顶节点
	 */
	public String getPfirst() {
		return this.pfirst;
	}

	/**
	 * 设置 顶节点
	 */
	public void setPfirst(String pfirst) {
		this.pfirst = pfirst;
	}

	/**
	 * 获取 1-按钮 0-菜单 ,
	 */
	public String getIsParent() {
		return this.isParent;
	}

	/**
	 * 设置 1-按钮 0-菜单 ,
	 */
	public void setIsParent(String isParent) {
		this.isParent = isParent;
	}

	/**
	 * 获取 图标
	 */
	public String getIcon() {
		return this.icon;
	}

	/**
	 * 设置 图标
	 */
	public void setIcon(String icon) {
		this.icon = icon;
	}

	/**
	 * 获取 接口
	 */
	public String getPinterface() {
		return this.pinterface;
	}

	/**
	 * 设置 接口
	 */
	public void setPinterface(String pinterface) {
		this.pinterface = pinterface;
	}

	/**
	 * 获取 详细说明
	 */
	public String getPmark() {
		return this.pmark;
	}

	/**
	 * 设置 详细说明
	 */
	public void setPmark(String pmark) {
		this.pmark = pmark;
	}


	/**
	 * 获取 自定义id
	 */
	public String getPhtmlid() {
		return this.phtmlid;
	}

	/**
	 * 设置 自定义id
	 */
	public void setPhtmlid(String phtmlid) {
		this.phtmlid = phtmlid;
	}

}
