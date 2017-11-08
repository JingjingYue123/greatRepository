package com.djcps.crm.partnersarea.dao;

import com.djcps.crm.partnersarea.model.PartnersArea;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by L-wenbin on 2017/7/8.
 */
@Repository
public interface PartnersAreaDao {
    /**
     * 查询合作商下所有区域
     * @param pid 合作商uuid
     * @return
     */
    List<PartnersArea> findAllArea(@Param("pid")String pid,
                                   @Param("keyArea")int keyArea);

    /**
     * 添加一个区域
     * @param partnersArea 区域实体
     * @return
     */
    int addPartnersArea(@Param("partnersArea")PartnersArea partnersArea);

    /**
     * 删除一个区域
     * @param aid 区域id
     * @return
     */
    int delPartnersArea(@Param("aid")String aid,
                        @Param("fkeyarea")int fkeyarea);

    /**
     * 省份下拉框,反查用，根据合作商获取合作商省份
     * @param oid 合作商oaid
     * @return
     */
    List<PartnersArea> findOptionByPartner(@Param("oid")int oid,
                                           @Param("keyarea") int keyarea);

    /**
     *  * 市区下拉框,反查用，根据省份code获取市区
     * @param provincecode  省份code
     * @return
     */
    List<PartnersArea> findOptionByProvince(@Param("pid")String pid,
                                            @Param("provincecode")int provincecode,
                                            @Param("keyarea") int keyarea);

    /**
     * 查询合作方地址表里面的下拉省份，返回给省份name和code
     * @return
     */
    List<PartnersArea> findAllProvince();

    /**
     * 查询合作方地址表中省份下的城市,返回市区code和name
     * @param provinceCode
     * @return
     */
    List<PartnersArea> findCityByProvince(@Param("provinceCode")String provinceCode);
}
