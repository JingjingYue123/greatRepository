package com.djcps.crm.partners.model;

/**
 * Created by L-wenbin on 2017/7/12.
 */
public class PartnersPage {
    private String oname;//合作商名称
    private String oprovince;//省
    private String ocity;//市
    private int page;//第几页
    private int pageSize;//每页显示几条
    private int fkeyarea;
    //======================


    public PartnersPage() {
    }

    @Override
    public String toString() {
        return "PartnersPage{" +
                "oname='" + oname + '\'' +
                ", oprovince='" + oprovince + '\'' +
                ", ocity='" + ocity + '\'' +
                ", page=" + page +
                ", pageSize=" + pageSize +
                ", fkeyarea=" + fkeyarea +
                '}';
    }

    public PartnersPage(String oname, String oprovince, String ocity, int page, int pageSize, int fkeyarea) {
        this.oname = oname;
        this.oprovince = oprovince;
        this.ocity = ocity;
        this.page = page;
        this.pageSize = pageSize;
        this.fkeyarea = fkeyarea;
    }

    public String getOname() {
        return oname;
    }

    public void setOname(String oname) {
        this.oname = oname;
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

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getFkeyarea() {
        return fkeyarea;
    }

    public void setFkeyarea(int fkeyarea) {
        this.fkeyarea = fkeyarea;
    }
}
