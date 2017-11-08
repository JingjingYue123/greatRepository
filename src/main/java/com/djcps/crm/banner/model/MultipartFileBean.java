package com.djcps.crm.banner.model;

import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

/**
 * Created by ZhangMingHui on 2017/7/7.
 */

public class MultipartFileBean implements Serializable {
    private MultipartFile file;//文件限制单文件传输
    private String moduleid;//业务关联Id
    private int fclient;//	客户端(1:pc,2:移动端)
    private int fbooth;//展位（1.首页轮播图管理，2.纸板团购轮播图管理，3.积分商城轮播图管理，4.纸板团购广告位管理，5.积分商城广告位管理，6。APP启动页管理）
    private int ffunctiontype;//功能列表
    private int fkeyarea;//区域拆分键
    private String fjumpposition;//跳转位置(1:普通网页，2:团购产品详情页，3:商城列表)
    private String fproductid;//产品ID(跳转至详情页用)
    private String furl;//普通页面链接地址
    private String fid;//业务ID(编辑时必须)
    private String ffileid;//图片ID
    private int farea;//区域拆分键  3303
    private String fileurl;//文件url
    private Boolean  fhasPicture;//区分编辑时候是否上传图片

    private Boolean  fadFirstEdit =false;//区分是否第一次编辑广告位     加上这个

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public String getModuleid() {
        return moduleid;
    }

    public void setModuleid(String moduleid) {
        this.moduleid = moduleid;
    }

    public int getFclient() {
        return fclient;
    }

    public void setFclient(int fclient) {
        this.fclient = fclient;
    }

    public int getFbooth() {
        return fbooth;
    }

    public void setFbooth(int fbooth) {
        this.fbooth = fbooth;
    }

    public int getFfunctiontype() {
        return ffunctiontype;
    }

    public void setFfunctiontype(int ffunctiontype) {
        this.ffunctiontype = ffunctiontype;
    }

    public int getFkeyarea() {
        return fkeyarea;
    }

    public void setFkeyarea(int fkeyarea) {
        this.fkeyarea = fkeyarea;
    }

    public String getFjumpposition() {
        return fjumpposition;
    }

    public void setFjumpposition(String fjumpposition) {
        this.fjumpposition = fjumpposition;
    }

    public String getFproductid() {
        return fproductid;
    }

    public void setFproductid(String fproductid) {
        this.fproductid = fproductid;
    }

    public String getFurl() {
        return furl;
    }

    public void setFurl(String furl) {
        this.furl = furl;
    }

    public String getFid() {
        return fid;
    }

    public void setFid(String fid) {
        this.fid = fid;
    }

    public String getFfileid() {
        return ffileid;
    }

    public void setFfileid(String ffileid) {
        this.ffileid = ffileid;
    }

    public int getFarea() {
        return farea;
    }

    public void setFarea(int farea) {
        this.farea = farea;
    }

    public String getFileurl() {
        return fileurl;
    }

    public void setFileurl(String fileurl) {
        this.fileurl = fileurl;
    }

    public Boolean getFhasPicture() {
        return fhasPicture;
    }

    public MultipartFileBean setFhasPicture(Boolean fhasPicture) {
        this.fhasPicture = fhasPicture;
        return this;
    }

    public Boolean getFadFirstEdit() {
        return fadFirstEdit;
    }

    public MultipartFileBean setFadFirstEdit(Boolean fadFirstEdit) {
        this.fadFirstEdit = fadFirstEdit;
        return this;
    }

    public void addMultipartFilePam(MultipartFilePam parpam) {
        this.moduleid = parpam.getModuleid();
        this.fclient = parpam.getFclient();
        this.fbooth = parpam.getFbooth();
        this.ffunctiontype = parpam.getFfunctiontype();
        this.fkeyarea = parpam.getFkeyarea();
        this.fjumpposition = parpam.getFjumpposition();
        this.fproductid = parpam.getFproductid();
        this.furl = parpam.getFurl();
        this.fid = parpam.getFid();
        this.ffileid = parpam.getFfileid();
        this.farea = parpam.getFarea();
        this.fileurl = parpam.getFileurl();
        this.fhasPicture = parpam.getFhasPicture();
        if (parpam.getFadFirstEdit() != null) {
            this.fadFirstEdit = parpam.getFadFirstEdit();
        }
    }


}
