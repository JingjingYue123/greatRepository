package com.djcps.crm.banner.model;

import java.io.Serializable;

/**
 * Created by ZhangMingHui on 2017/7/8.
 */
public class ResultImgBean implements Serializable {
    private String fileurl;
    private String fileid;

    public String getFileurl() {
        return fileurl;
    }

    public void setFileurl(String fileurl) {
        this.fileurl = fileurl;
    }

    public String getFileid() {
        return fileid;
    }

    public void setFileid(String fileid) {
        this.fileid = fileid;
    }
}
