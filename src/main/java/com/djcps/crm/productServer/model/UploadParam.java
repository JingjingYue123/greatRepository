package com.djcps.crm.productServer.model;

/**
 * Created by TruthBean on 2017/8/17 16:32.
 */
public class UploadParam {
    private String moduleid;
    private String version;
    private String fkeyarea;

    public String getModuleid() {
        return moduleid;
    }

    public void setModuleid(String moduleid) {
        this.moduleid = moduleid;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getFkeyarea() {
        return fkeyarea;
    }

    public void setFkeyarea(String fkeyarea) {
        this.fkeyarea = fkeyarea;
    }
}
