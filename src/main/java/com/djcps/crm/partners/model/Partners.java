package com.djcps.crm.partners.model;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * 合作商详细实体
 * Created by L-wenbin on 2017/7/11.
 */
public class Partners {
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
    @NotBlank
    private String oname;
    /**
     * 法人代表姓名
     */
    @NotBlank
    private String uname;
    /**
     * 联系方式
     */
    @NotBlank
    private String uphone;
    /**
     * 公司地址：省
     */
    @NotBlank
    private String oprovince;
    /**
     * 公司地址：市
     */
    @NotBlank
    private String ocity;
    /**
     * 开户银行
     */
    @Length(min = 0,max = 20)
    private String bank;
    /**
     * 银行卡号
     */
    @Length(min = 0,max = 20)
    private String bankcard;
    /**
     * 开户人
     */
    @Length(min = 0,max = 10)
    private String accountholder;
    /**
     * 注册资本
     */
    @Min(0)
    @Max(999999999)
    private int registercapital;
    /**
     * 年销售额
     */
    @Min(0)
    @Max(999999999)
    private int annualsales;
    /**
     * 传真
     */
    @Length(min = 0,max = 20)
    private String fax;
    /**
     * 网址
     */
    private String website;
    /**
     * 企业类型
     */
    private int enterprisetype;
    /**
     * 企业人数
     */
    @Min(0)
    @Max(999999)
    private int enterprisenumber;
    /**
     * 厂房面积
     */
    @Min(0)
    @Max(99999)
    private int plantarea;
    /**
     * 厂房属性
     */
    private int planttype;
    /**
     * 生产线数量
     */
    @Min(0)
    @Max(99)
    private int linenumber;
    /**
     * 生产线门幅
     */
    @Min(0)
    @Max(999)
    private int linedoor;
    /**
     * 生产线品牌
     */
    @Length(min = 0,max = 20)
    private String linebrand;
    /**
     * 生产线的平均车速
     */
    @Min(0)
    @Max(99999999)
    private int linespeed;
    /**
     * 开机时长
     */
    @Min(0)
    @Max(100)
    private BigDecimal opentime = BigDecimal.ZERO;
    /**
     * 生产班次
     */

    private int productionshift;
    /**
     * 日均产能
     */
    @Min(0)
    @Max(999999999)
    private int avgcapacity;
    /**
     * 日最大产能
     */
    @Min(0)
    @Max(999999999)
    private int maxcapacity;
    /**
     * 产品数量
     */
    @Min(0)
    @Max(999)
    private int productquantity;
    /**
     * 产量最大的三种产品
     */
    @Length(max = 25)
    private String maxthree;
    /**
     * 运输配送方式
     */
    private int transporttype;
    /**
     * 车辆数量
     */
    @Min(0)
    @Max(999)
    private int carnumber;
    /**
     * 总经理/厂长
     */
    @Length(max = 10)
    private String managername;
    /**
     * 年龄
     */
    private Timestamp age;
    /**
     * 学历
     */
    private int educationbackground;
    /**
     * 研发技术人员数量
     */
    @Min(0)
    @Max(99999)
    private int rdnumber;
    /**
     * 质量人员数量
     */
    @Min(0)
    @Max(99999)
    private int fmassnumber;
    /**
     * 销售人员数量
     */
    @Min(0)
    @Max(99999)
    private int salesnumber;
    /**
     * 一线生产人员数量
     */
    @Min(0)
    @Max(99999)
    private int onelinenumber;
    /**
     * 物流人员数量
     */
    @Min(0)
    @Max(99999)
    private int logisticsnumber;
    /**
     * 管理人员数量
     */
    @Min(0)
    @Max(999)
    private int managenumber;
    /**
     * 订单分发配置
     */
    private int configuration;
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

//==================================


    public Partners() {
    }

    @Override
    public String toString() {
        return "Partners{" +
                "id='" + id + '\'' +
                ", pid='" + pid + '\'' +
                ", oname='" + oname + '\'' +
                ", uname='" + uname + '\'' +
                ", uphone='" + uphone + '\'' +
                ", oprovince='" + oprovince + '\'' +
                ", ocity='" + ocity + '\'' +
                ", bank='" + bank + '\'' +
                ", bankcard='" + bankcard + '\'' +
                ", accountholder='" + accountholder + '\'' +
                ", registercapital=" + registercapital +
                ", annualsales=" + annualsales +
                ", fax='" + fax + '\'' +
                ", website='" + website + '\'' +
                ", enterprisetype=" + enterprisetype +
                ", enterprisenumber=" + enterprisenumber +
                ", plantarea=" + plantarea +
                ", planttype=" + planttype +
                ", linenumber=" + linenumber +
                ", linedoor=" + linedoor +
                ", linebrand='" + linebrand + '\'' +
                ", linespeed=" + linespeed +
                ", opentime=" + opentime +
                ", productionshift=" + productionshift +
                ", avgcapacity=" + avgcapacity +
                ", maxcapacity=" + maxcapacity +
                ", productquantity=" + productquantity +
                ", maxthree='" + maxthree + '\'' +
                ", transporttype=" + transporttype +
                ", carnumber=" + carnumber +
                ", managername='" + managername + '\'' +
                ", age=" + age +
                ", educationbackground=" + educationbackground +
                ", rdnumber=" + rdnumber +
                ", fmassnumber=" + fmassnumber +
                ", salesnumber=" + salesnumber +
                ", onelinenumber=" + onelinenumber +
                ", logisticsnumber=" + logisticsnumber +
                ", managenumber=" + managenumber +
                ", configuration=" + configuration +
                ", updatetime=" + updatetime +
                ", effect=" + effect +
                ", ocode=" + ocode +
                '}';
    }

    public Partners(int id, String pid, String oname, String uname, String uphone, String oprovince, String ocity, String bank, String bankcard, String accountholder, int registercapital, int annualsales, String fax, String website, int enterprisetype, int enterprisenumber, int plantarea, int planttype, int linenumber, int linedoor, String linebrand, int linespeed, BigDecimal opentime, int productionshift, int avgcapacity, int maxcapacity, int productquantity, String maxthree, int transporttype, int carnumber, String managername, Timestamp age, int educationbackground, int rdnumber, int fmassnumber, int salesnumber, int onelinenumber, int logisticsnumber, int managenumber, int configuration, Timestamp updatetime, int effect, int ocode) {
        this.id = id;
        this.pid = pid;
        this.oname = oname;
        this.uname = uname;
        this.uphone = uphone;
        this.oprovince = oprovince;
        this.ocity = ocity;
        this.bank = bank;
        this.bankcard = bankcard;
        this.accountholder = accountholder;
        this.registercapital = registercapital;
        this.annualsales = annualsales;
        this.fax = fax;
        this.website = website;
        this.enterprisetype = enterprisetype;
        this.enterprisenumber = enterprisenumber;
        this.plantarea = plantarea;
        this.planttype = planttype;
        this.linenumber = linenumber;
        this.linedoor = linedoor;
        this.linebrand = linebrand;
        this.linespeed = linespeed;
        this.opentime = opentime;
        this.productionshift = productionshift;
        this.avgcapacity = avgcapacity;
        this.maxcapacity = maxcapacity;
        this.productquantity = productquantity;
        this.maxthree = maxthree;
        this.transporttype = transporttype;
        this.carnumber = carnumber;
        this.managername = managername;
        this.age = age;
        this.educationbackground = educationbackground;
        this.rdnumber = rdnumber;
        this.fmassnumber = fmassnumber;
        this.salesnumber = salesnumber;
        this.onelinenumber = onelinenumber;
        this.logisticsnumber = logisticsnumber;
        this.managenumber = managenumber;
        this.configuration = configuration;
        this.updatetime = updatetime;
        this.effect = effect;
        this.ocode = ocode;
    }

    public int getId() {
        return id;
    }

    /**
     * 设置 OAID
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * 获取 UUID
     */
    public String getPid() {
        return pid;
    }

    /**
     * 设置 UUID
     */
    public void setPid(String pid) {
        this.pid = pid;
    }

    /**
     * 获取 公司名称
     */
    public String getOname() {
        return oname;
    }

    /**
     * 设置 公司名称
     */
    public void setOname(String oname) {
        this.oname = oname;
    }

    /**
     * 获取 法人代表姓名
     */
    public String getUname() {
        return uname;
    }

    /**
     * 设置 法人代表姓名
     */
    public void setUname(String uname) {
        this.uname = uname;
    }

    /**
     * 获取 联系方式
     */
    public String getUphone() {
        return uphone;
    }

    /**
     * 设置 联系方式
     */
    public void setUphone(String uphone) {
        this.uphone = uphone;
    }

    /**
     * 获取 公司地址：省
     */
    public String getOprovince() {
        return oprovince;
    }

    /**
     * 设置 公司地址：省
     */
    public void setOprovince(String oprovince) {
        this.oprovince = oprovince;
    }

    /**
     * 获取 公司地址：市
     */
    public String getOcity() {
        return ocity;
    }

    /**
     * 设置 公司地址：市
     */
    public void setOcity(String ocity) {
        this.ocity = ocity;
    }

    /**
     * 获取 开户银行
     */
    public String getBank() {
        return bank;
    }

    /**
     * 设置 开户银行
     */
    public void setBank(String bank) {
        this.bank = bank;
    }

    /**
     * 获取 银行卡号
     */
    public String getBankcard() {
        return bankcard;
    }

    /**
     * 设置 银行卡号
     */
    public void setBankcard(String bankcard) {
        this.bankcard = bankcard;
    }

    /**
     * 获取 开户人
     */
    public String getAccountholder() {
        return accountholder;
    }

    /**
     * 设置 开户人
     */
    public void setAccountholder(String accountholder) {
        this.accountholder = accountholder;
    }

    /**
     * 获取 注册资本
     */
    public int getRegistercapital() {
        return registercapital;
    }

    /**
     * 设置 注册资本
     */
    public void setRegistercapital(int registercapital) {
        this.registercapital = registercapital;
    }

    /**
     * 获取 年销售额
     */
    public int getAnnualsales() {
        return annualsales;
    }

    /**
     * 设置 年销售额
     */
    public void setAnnualsales(int annualsales) {
        this.annualsales = annualsales;
    }

    /**
     * 获取 传真
     */
    public String getFax() {
        return fax;
    }

    /**
     * 设置 传真
     */
    public void setFax(String fax) {
        this.fax = fax;
    }

    public int getEnterprisetype() {
        return enterprisetype;
    }

    /**
     * 设置 企业类型
     */
    public void setEnterprisetype(int enterprisetype) {
        this.enterprisetype = enterprisetype;
    }

    /**
     * 获取 企业人数
     */
    public int getEnterprisenumber() {
        return enterprisenumber;
    }

    /**
     * 设置 企业人数
     */
    public void setEnterprisenumber(int enterprisenumber) {
        this.enterprisenumber = enterprisenumber;
    }

    /**
     * 获取 厂房面积
     */
    public int getPlantarea() {
        return plantarea;
    }

    /**
     * 设置 厂房面积
     */
    public void setPlantarea(int plantarea) {
        this.plantarea = plantarea;
    }

    /**
     * 获取 厂房属性
     */
    public int getPlanttype() {
        return planttype;
    }

    /**
     * 设置 厂房属性
     */
    public void setPlanttype(int planttype) {
        this.planttype = planttype;
    }

    /**
     * 获取 生产线数量
     */
    public int getLinenumber() {
        return linenumber;
    }

    /**
     * 设置 生产线数量
     */
    public void setLinenumber(int linenumber) {
        this.linenumber = linenumber;
    }

    /**
     * 获取 生产线门幅
     */
    public int getLinedoor() {
        return linedoor;
    }

    /**
     * 设置 生产线门幅
     */
    public void setLinedoor(int linedoor) {
        this.linedoor = linedoor;
    }

    /**
     * 获取 生产线品牌
     */
    public String getLinebrand() {
        return linebrand;
    }

    /**
     * 设置 生产线品牌
     */
    public void setLinebrand(String linebrand) {
        this.linebrand = linebrand;
    }

    public int getLinespeed() {
        return linespeed;
    }

    public void setLinespeed(int linespeed) {
        this.linespeed = linespeed;
    }

    public BigDecimal getOpentime() {
        return opentime;
    }

    public void setOpentime(BigDecimal opentime) {
        this.opentime = opentime;
    }

    /**
     * 获取 生产班次
     */
    public int getProductionshift() {
        return productionshift;
    }

    /**
     * 设置 生产班次
     */
    public void setProductionshift(int productionshift) {
        this.productionshift = productionshift;
    }

    public int getAvgcapacity() {
        return avgcapacity;
    }

    public void setAvgcapacity(int avgcapacity) {
        this.avgcapacity = avgcapacity;
    }

    public int getMaxcapacity() {
        return maxcapacity;
    }

    public void setMaxcapacity(int maxcapacity) {
        this.maxcapacity = maxcapacity;
    }

    /**
     * 获取 产品数量
     */
    public int getProductquantity() {
        return productquantity;
    }

    /**
     * 设置 产品数量
     */
    public void setProductquantity(int productquantity) {
        this.productquantity = productquantity;
    }

    /**
     * 获取 产量最大的三种产品
     */
    public String getMaxthree() {
        return maxthree;
    }

    /**
     * 设置 产量最大的三种产品
     */
    public void setMaxthree(String maxthree) {
        this.maxthree = maxthree;
    }

    /**
     * 获取 运输配送方式
     */
    public int getTransporttype() {
        return transporttype;
    }

    /**
     * 设置 运输配送方式
     */
    public void setTransporttype(int transporttype) {
        this.transporttype = transporttype;
    }

    /**
     * 获取 车辆数量
     */
    public int getCarnumber() {
        return carnumber;
    }

    /**
     * 设置 车辆数量
     */
    public void setCarnumber(int carnumber) {
        this.carnumber = carnumber;
    }

    /**
     * 获取 总经理/厂长
     */
    public String getManagername() {
        return this.managername;
    }

    /**
     * 设置 总经理/厂长
     */
    public void setManagername(String managername) {
        this.managername = managername;
    }

    /**
     * 获取 年龄
     */
    public Timestamp getAge() {
        return age;
    }

    /**
     * 设置 年龄
     */
    public void setAge(Timestamp age) {
        this.age = age;
    }

    public int getEducationbackground() {
        return this.educationbackground;
    }

    /**
     * 设置 学历
     */
    public void setEducationbackground(int educationbackground) {
        this.educationbackground = educationbackground;
    }

    /**
     * 获取 研发技术人员数量
     */
    public int getRdnumber() {
        return rdnumber;
    }

    /**
     * 设置 研发技术人员数量
     */
    public void setRdnumber(int rdnumber) {
        this.rdnumber = rdnumber;
    }

    /**
     * 获取 质量人员数量
     */
    public int getFmassnumber() {
        return fmassnumber;
    }

    /**
     * 设置 质量人员数量
     */
    public void setFmassnumber(int fmassnumber) {
        this.fmassnumber = fmassnumber;
    }

    /**
     * 获取 销售人员数量
     */
    public int getSalesnumber() {
        return salesnumber;
    }

    /**
     * 设置 销售人员数量
     */
    public void setSalesnumber(int salesnumber) {
        this.salesnumber = salesnumber;
    }

    /**
     * 获取 一线生产人员数量
     */
    public int getOnelinenumber() {
        return onelinenumber;
    }

    /**
     * 设置 一线生产人员数量
     */
    public void setOnelinenumber(int onelinenumber) {
        this.onelinenumber = onelinenumber;
    }

    /**
     * 获取 物流人员数量
     */
    public int getLogisticsnumber() {
        return logisticsnumber;
    }

    /**
     * 设置 物流人员数量
     */
    public void setLogisticsnumber(int logisticsnumber) {
        this.logisticsnumber = logisticsnumber;
    }

    /**
     * 获取 管理人员数量
     */
    public int getManagenumber() {
        return managenumber;
    }

    /**
     * 设置 管理人员数量
     */
    public void setManagenumber(int managenumber) {
        this.managenumber = managenumber;
    }

    /**
     * 获取 订单分发配置
     */
    public int getConfiguration() {
        return this.configuration;
    }

    /**
     * 设置 订单分发配置
     */
    public void setConfiguration(int configuration) {
        this.configuration = configuration;
    }


    /**
     * 获取 修改日期
     */
    public Timestamp getUpdatetime() {
        return updatetime;
    }

    /**
     * 设置 修改日期
     */
    public void setUpdatetime(Timestamp updatetime) {
        this.updatetime = updatetime;
    }

    /**
     * 获取 状态
     */
    public int getEffect() {
        return effect;
    }

    /**
     * 设置 状态
     */
    public void setEffect(int effect) {
        this.effect = effect;
    }

    /**
     * 获取 合作商区域拆分键
     */
    public int getOcode() {
        return ocode;
    }

    /**
     * 设置 合作商区域拆分键
     */
    public void setOcode(int ocode) {
        this.ocode = ocode;
    }


    /**
     * 获取 网址
     */
    public String getWebsite() {
        return this.website;
    }

    /**
     * 设置 网址
     */
    public void setWebsite(String website) {
        this.website = website;
    }
}