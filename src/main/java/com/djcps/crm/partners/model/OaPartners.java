package com.djcps.crm.partners.model;

import java.sql.Timestamp;

/**
 * 获取oa合作商数据实体
 * Created by L-wenbin on 2017/7/11.
 */
public class OaPartners {
    private int id;
    private String oname;
    private Timestamp create_time;
    private int ocode;
    private String oprovince;
    private String ocity;
    private String oarea;
    private String oaddress;
    private String uname;
    private String uphone;
    private String uemail;
    private int enable;
    private String ucard;

    public OaPartners() {
    }

    @Override
    public String toString() {
        return "OaPartners{" +
                "id=" + id +
                ", oname='" + oname + '\'' +
                ", create_time=" + create_time +
                ", ocode=" + ocode +
                ", oprovince='" + oprovince + '\'' +
                ", ocity='" + ocity + '\'' +
                ", oarea='" + oarea + '\'' +
                ", oaddress='" + oaddress + '\'' +
                ", uname='" + uname + '\'' +
                ", uphone='" + uphone + '\'' +
                ", uemail='" + uemail + '\'' +
                ", enable=" + enable +
                ", ucard='" + ucard + '\'' +
                '}';
    }

    public OaPartners(int id, String oname, Timestamp create_time, int ocode, String oprovince, String ocity, String oarea, String oaddress, String uname, String uphone, String uemail, int enable, String ucard) {
        this.id = id;
        this.oname = oname;
        this.create_time = create_time;
        this.ocode = ocode;
        this.oprovince = oprovince;
        this.ocity = ocity;
        this.oarea = oarea;
        this.oaddress = oaddress;
        this.uname = uname;
        this.uphone = uphone;
        this.uemail = uemail;
        this.enable = enable;
        this.ucard = ucard;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOname() {
        return oname;
    }

    public void setOname(String oname) {
        this.oname = oname;
    }

    public Timestamp getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Timestamp create_time) {
        this.create_time = create_time;
    }

    public int getOcode() {
        return ocode;
    }

    public void setOcode(int ocode) {
        this.ocode = ocode;
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

    public String getOarea() {
        return oarea;
    }

    public void setOarea(String oarea) {
        this.oarea = oarea;
    }

    public String getOaddress() {
        return oaddress;
    }

    public void setOaddress(String oaddress) {
        this.oaddress = oaddress;
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

    public String getUemail() {
        return uemail;
    }

    public void setUemail(String uemail) {
        this.uemail = uemail;
    }

    public int getEnable() {
        return enable;
    }

    public void setEnable(int enable) {
        this.enable = enable;
    }

    public String getUcard() {
        return ucard;
    }

    public void setUcard(String ucard) {
        this.ucard = ucard;
    }
}
