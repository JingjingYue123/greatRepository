package com.djcps.crm.partners.model;

import org.hibernate.validator.constraints.NotBlank;

import java.sql.Timestamp;

/**
 * Created by L-wenbin on 2017/9/30.
 */
public class PartnersInit {
    /**
     * OAID
     */
    private int id;
    /**
     * UUID
     */
    private String pid;
    /**
     * 公司名称
     */
    private String oname;
    /**
     * 法人代表姓名
     */
    private String uname;
    /**
     * 联系方式
     */
    private String uphone;
    /**
     * 公司地址：省
     */
    private String oprovince;
    /**
     * 公司地址：市
     */
    private String ocity;
    /**
     * 修改日期
     */
    private Timestamp updatetime;
    /**
     * 状态
     */
    private int effect;
    /**
     * 合作商区域拆分键
     */
    private int ocode;
    //======================================

    public PartnersInit() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getOname() {
        return oname;
    }

    public void setOname(String oname) {
        this.oname = oname;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getUphone() {
        return uphone;
    }

    public void setUphone(String uphone) {
        this.uphone = uphone;
    }

    public String getOprovince() {
        return oprovince;
    }

    public void setOprovince(String oprovince) {
        this.oprovince = oprovince;
    }

    public String getOcity() {
        return ocity;
    }

    public void setOcity(String ocity) {
        this.ocity = ocity;
    }

    public Timestamp getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Timestamp updatetime) {
        this.updatetime = updatetime;
    }

    public int getEffect() {
        return effect;
    }

    public void setEffect(int effect) {
        this.effect = effect;
    }

    public int getOcode() {
        return ocode;
    }

    public void setOcode(int ocode) {
        this.ocode = ocode;
    }
}
