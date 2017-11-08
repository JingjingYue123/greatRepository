package com.djcps.crm.partnersarea.service;

import com.djcps.crm.partnersarea.dao.PartnersAreaDao;
import com.djcps.crm.partnersarea.model.PartnersArea;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * Created by L-wenbin on 2017/7/8.
 */
@Service
public class PartnersAreaService {
    @Autowired
    private PartnersAreaDao partnersAreaDao;


    /**
     * 查询合作商下所有区域
     * @param pid 合作商uuid
     * @return
     */
    public List<PartnersArea> findAllArea(String pid,int keyArea){
        return partnersAreaDao.findAllArea(pid,keyArea);
    }

    /**
     * 添加一个区域
     * @param partnersArea 区域实体
     * @return
     */
    public boolean addPartnersArea(PartnersArea partnersArea){
        partnersArea.setAid(UUID.randomUUID().toString());
        return partnersAreaDao.addPartnersArea(partnersArea)>0;
    }

    /**
     * 删除一个区域
     * @param aid 区域id
     * @return
     */
    public boolean delPartnersArea(String aid,int fkeyarea){
        return partnersAreaDao.delPartnersArea(aid,fkeyarea)>0;
    }
    /**
     * 省份下拉框,反查用，根据合作商获取合作商省份
     * @param oid 合作方oaid
     * @return
     */
    public List<PartnersArea> findOptionByPartner(int oid,int keyarea) {
        return partnersAreaDao.findOptionByPartner(oid,keyarea);
    }

    /**
     * 市区下拉框,反查用，根据省份code获取市区
     * @param provincecode  省份code
     * @return
     */
    public List<PartnersArea> findOptionByProvince(String pid,int provincecode,int keyarea) {
        return partnersAreaDao.findOptionByProvince(pid,provincecode,keyarea);
    }

    /**
     * 查询合作方地址表里面的下拉省份，返回给省份name和code
     * @return
     */
    public List<PartnersArea> findAllProvince() {
        return partnersAreaDao.findAllProvince();
    }

    /**
     * 查询合作方地址表中省份下的城市,返回市区code和name
     * @param provinceCode
     * @return
     */
    public List<PartnersArea> findCityByProvince(String provinceCode) {
        return partnersAreaDao.findCityByProvince(provinceCode);
    }
}
