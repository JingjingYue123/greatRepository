package com.djcps.crm.customerService.model;

public class AbnormalOrderModel {
    private String fuserid;//客户id
    private String fphone;//客户手机号
    private String fcscptjournalid;//客诉流水号
    private String forderid;//订单号
    private String fgroupgoodname;//产品名称
    private String fexceptionreason;//异常原因
    private String fcreator;//创建人
    private String fcsoperator;//客服操作人
    private String ffinoperator;//财务操作人
    private String fcreatetime;//创建时间
    private Integer fcshandlingstatus;//客服处理状态
    private Integer frivilegetype;//类型
    private Integer fcshandlingtype;//客服处理方式
    private Integer fpatchamount;//补片数量
    private String frefundamount;//退款金额
    private String certificateName;//认证名


    private String fcscommrecord;//客服沟通记录
    private String ffincomments;//财务说明
    private String fcsoperatingtime;//客服操作时间
    private String ffinoperatingtime;//财务操作时间
    private String ffinhandlingresult;//财务处理方式

    private Integer fstatus;//订单状态
    private Integer fkeyarea;//区域拆分键
    private String fcreatorid;//创建人id
    private String fcsoperatorid;//客服操作人id
    private String ffinoperatorid;//财务操作人id
    private String fupdatetime;//更新时间
    private String version;//版本号

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getFuserid() {
        return fuserid;
    }

    public void setFuserid(String fuserid) {
        this.fuserid = fuserid;
    }

    public String getFphone() {
        return fphone;
    }

    public void setFphone(String fphone) {
        this.fphone = fphone;
    }

    public String getFcscptjournalid() {
        return fcscptjournalid;
    }

    public void setFcscptjournalid(String fcscptjournalid) {
        this.fcscptjournalid = fcscptjournalid;
    }

    public String getForderid() {
        return forderid;
    }

    public void setForderid(String forderid) {
        this.forderid = forderid;
    }

    public String getFgroupgoodname() {
        return fgroupgoodname;
    }

    public void setFgroupgoodname(String fgroupgoodname) {
        this.fgroupgoodname = fgroupgoodname;
    }

    public String getFexceptionreason() {
        return fexceptionreason;
    }

    public void setFexceptionreason(String fexceptionreason) {
        this.fexceptionreason = fexceptionreason;
    }

    public String getFcreator() {
        return fcreator;
    }

    public void setFcreator(String fcreator) {
        this.fcreator = fcreator;
    }

    public String getFcsoperator() {
        return fcsoperator;
    }

    public void setFcsoperator(String fcsoperator) {
        this.fcsoperator = fcsoperator;
    }

    public String getFfinoperator() {
        return ffinoperator;
    }

    public void setFfinoperator(String ffinoperator) {
        this.ffinoperator = ffinoperator;
    }

    public String getFcreatetime() {
        return fcreatetime;
    }

    public void setFcreatetime(String fcreatetime) {
        this.fcreatetime = fcreatetime;
    }

    public Integer getFcshandlingstatus() {
        return fcshandlingstatus;
    }

    public void setFcshandlingstatus(Integer fcshandlingstatus) {
        this.fcshandlingstatus = fcshandlingstatus;
    }

    public Integer getFrivilegetype() {
        return frivilegetype;
    }

    public void setFrivilegetype(Integer frivilegetype) {
        this.frivilegetype = frivilegetype;
    }

    public Integer getFcshandlingtype() {
        return fcshandlingtype;
    }

    public void setFcshandlingtype(Integer fcshandlingtype) {
        this.fcshandlingtype = fcshandlingtype;
    }

    public Integer getFpatchamount() {
        return fpatchamount;
    }

    public void setFpatchamount(Integer fpatchamount) {
        this.fpatchamount = fpatchamount;
    }


    public String getFcscommrecord() {
        return fcscommrecord;
    }

    public void setFcscommrecord(String fcscommrecord) {
        this.fcscommrecord = fcscommrecord;
    }

    public String getFfincomments() {
        return ffincomments;
    }

    public void setFfincomments(String ffincomments) {
        this.ffincomments = ffincomments;
    }

    public String getFcsoperatingtime() {
        return fcsoperatingtime;
    }

    public void setFcsoperatingtime(String fcsoperatingtime) {
        this.fcsoperatingtime = fcsoperatingtime;
    }

    public String getFfinoperatingtime() {
        return ffinoperatingtime;
    }

    public void setFfinoperatingtime(String ffinoperatingtime) {
        this.ffinoperatingtime = ffinoperatingtime;
    }

    public Integer getFstatus() {
        return fstatus;
    }

    public void setFstatus(Integer fstatus) {
        this.fstatus = fstatus;
    }

    public Integer getFkeyarea() {
        return fkeyarea;
    }

    public void setFkeyarea(Integer fkeyarea) {
        this.fkeyarea = fkeyarea;
    }

    public String getFcreatorid() {
        return fcreatorid;
    }

    public void setFcreatorid(String fcreatorid) {
        this.fcreatorid = fcreatorid;
    }

    public String getFcsoperatorid() {
        return fcsoperatorid;
    }

    public void setFcsoperatorid(String fcsoperatorid) {
        this.fcsoperatorid = fcsoperatorid;
    }

    public String getFfinoperatorid() {
        return ffinoperatorid;
    }

    public void setFfinoperatorid(String ffinoperatorid) {
        this.ffinoperatorid = ffinoperatorid;
    }

    public String getFupdatetime() {
        return fupdatetime;
    }

    public void setFupdatetime(String fupdatetime) {
        this.fupdatetime = fupdatetime;
    }


    public String getFfinhandlingresult() {
        return ffinhandlingresult;
    }

    public void setFfinhandlingresult(String ffinhandlingresult) {
        this.ffinhandlingresult = ffinhandlingresult;
    }
    public String getFrefundamount() {
        return frefundamount;
    }

    public void setFrefundamount(String frefundamount) {
        this.frefundamount = frefundamount;
    }

    public String getCertificateName() {
        return certificateName;
    }
    public void setCertificateName(String certificateName) {
        this.certificateName = certificateName;
    }
}
