package com.djcps.crm.customerService.model;


import com.djcps.crm.customerService.config.ParamsConfig;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import java.util.Date;

public class FinanceProcessModel {
    //客诉流水号
    @NotEmpty(message = "客诉流水号不能为空")
    private String fcscptjournalid;
    //财务说明
    @NotBlank(message = "客服沟通记录不能为空")
    @Length(min=0,max=100,message = "客服沟通记录长度必须在100字以内")
    private String ffincomments;
    //财务处理结果
    @Min(value = 1,message = "财务处理方式仅支持1或2")
    @Max(value = 2,message = "财务处理方式仅支持1或2")
    private Integer ffinhandlingresult;
    //财务操作人
    @NotEmpty(message = "财务操作人不能为空")
    private String ffinoperator;
    //财务操作人id
    @NotEmpty(message = "财务操作人id不能为空")
    private String ffinoperatorid;
    //退款钱数
    @NotEmpty(message = "退款钱数不能为空")
    private String frefundamount;
    //类型.因为是财务的处理操作所以这里就默认为
    private Integer fprivilegetype=3;
    //财务操作时间
    private Date ffinoperatingtime;
    //修改时间
    private Date fupdatetime;
    //订单状态
    private Integer fstatus;
    //区域拆分键
    @NotEmpty(message = "区域拆分键不能为空")
    private String fkeyarea;
    //用户id
    @NotEmpty(message = "用户id不能为空")
    private String fuserId;
    //订单编号
    @NotEmpty(message = "订单编号不能为空")
    private String forderId;


    @NotEmpty(message = "版本号不能为空")
    @Pattern(regexp = ParamsConfig.VERSION,message = "目前版本号仅支持1.0")
    private String version;

    public String getFcscptjournalid() {
        return fcscptjournalid;
    }

    public void setFcscptjournalid(String fcscptjournalid) {
        this.fcscptjournalid = fcscptjournalid;
    }

    public String getFfincomments() {
        return ffincomments;
    }

    public void setFfincomments(String ffincomments) {
        this.ffincomments = ffincomments;
    }

    public Integer getFfinhandlingresult() {
        return ffinhandlingresult;
    }

    public void setFfinhandlingresult(Integer ffinhandlingresult) {
        this.ffinhandlingresult = ffinhandlingresult;
    }

    public String getFfinoperator() {
        return ffinoperator;
    }

    public void setFfinoperator(String ffinoperator) {
        this.ffinoperator = ffinoperator;
    }

    public String getFfinoperatorid() {
        return ffinoperatorid;
    }

    public void setFfinoperatorid(String ffinoperatorid) {
        this.ffinoperatorid = ffinoperatorid;
    }

    public String getFrefundamount() {
        return frefundamount;
    }

    public void setFrefundamount(String frefundamount) {
        this.frefundamount = frefundamount;
    }

    public Integer getFprivilegetype() {
        return fprivilegetype;
    }

    public void setFprivilegetype(Integer fprivilegetype) {
        this.fprivilegetype = fprivilegetype;
    }

    public Date getFfinoperatingtime() {
        return ffinoperatingtime;
    }

    public void setFfinoperatingtime(Date ffinoperatingtime) {
        this.ffinoperatingtime = ffinoperatingtime;
    }

    public Date getFupdatetime() {
        return fupdatetime;
    }

    public void setFupdatetime(Date fupdatetime) {
        this.fupdatetime = fupdatetime;
    }

    public Integer getFstatus() {
        return fstatus;
    }

    public void setFstatus(Integer fstatus) {
        this.fstatus = fstatus;
    }

    public String getFkeyarea() {
        return fkeyarea;
    }

    public void setFkeyarea(String fkeyarea) {
        this.fkeyarea = fkeyarea;
    }



    public String getForderId() {
        return forderId;
    }

    public void setForderId(String forderId) {
        this.forderId = forderId;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getFuserId() {
        return fuserId;
    }

    public void setFuserId(String fuserId) {
        this.fuserId = fuserId;
    }
}
