package com.djcps.crm.integral.model;

/**
 * Created by TruthBean on 2017-08-16 21:21.
 */
public class JournalListRequest {
    /**
     * 当前页
     */
    private int current;

    /**
     * 一页显示的条数
     */
    private int size;

    /**
     * 积分流水类型
     */
    private int ftype;

    /**
     * 合作方区域拆分键
     */
    private int fkeyarea;

    /**
     * 积分流水 起始时间
     */
    private String begintime;

    /**
     * 积分流水 截止时间
     */
    private String endtime;

    /**
     * 合作方ID OAID
     */
    private String fpartner;

    /**
     * 客户名称（用于搜索）
     */
    private String searchname;

    /**
     * 客户手机号码（用户搜索）
     */
    private String phone;

    /**
     * 用户ID
     */
    private String fuserid;

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getFtype() {
        return ftype;
    }

    public void setFtype(int ftype) {
        this.ftype = ftype;
    }

    public int getFkeyarea() {
        return fkeyarea;
    }

    public void setFkeyarea(int fkeyarea) {
        this.fkeyarea = fkeyarea;
    }

    public String getBegintime() {
        return begintime;
    }

    public void setBegintime(String begintime) {
        this.begintime = begintime;
    }

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

    public String getFpartner() {
        return fpartner;
    }

    public void setFpartner(String fpartner) {
        this.fpartner = fpartner;
    }

    public String getSearchname() {
        return searchname;
    }

    public void setSearchname(String searchname) {
        this.searchname = searchname;
    }

    public String getFuserid() {
        return fuserid;
    }

    public void setFuserid(String fuserid) {
        this.fuserid = fuserid;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
