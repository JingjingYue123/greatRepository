package com.djcps.crm.partners.dao;

import com.djcps.crm.partners.model.Partners;
import com.djcps.crm.partners.model.PartnersInit;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PartnersDao {
    /**
     * 查询合作商列表，带条件查询，带分页
     *
     * @param oname     合作商  省份
     * @param oprovince 合作商  市
     * @param ocity     合作商名字
     * @param beginPage 起始条数
     * @return
     */
    List<PartnersInit> findPartnersPage(
            @Param("oname") String oname,
            @Param("oprovince") String oprovince,
            @Param("ocity") String ocity,
            @Param("beginPage") int beginPage,
            @Param("pageSize") int pageSize);

    /**
     * 查询带条件的合作商Count
     *
     * @param oname     合作商名称
     * @param oprovince 省
     * @param ocity     市
     * @return
     */
    int findPartnersCount(@Param("oname") String oname,
                          @Param("oprovince") String oprovince,
                          @Param("ocity") String ocity);
                         // @Param("fkeyarea") int fkeyarea);

    /**
     * 查询所有合作商的oaid
     *
     * @return
     */
    List<Partners> findPartners();

    /**
     * 查询单个合作商信息
     *
     * @param pid 合作UUID
     * @return
     */
    Partners findOnePartners(@Param("pid") String pid,
                             @Param("fkeyarea")int fkeyarea);

    /**
     * 添加合作商
     *
     * @param partners 合作商实体
     * @return
     */
    int addPartners(@Param("partners") Partners partners);

    /**
     * 修改合作商信息
     *
     * @param partners 合作商实体
     * @return
     */
    int updatePartners(@Param("partners") Partners partners);

    /**
     * 修改合作商状态
     * @return
     */
    int upPartnerStatus(@Param("companyID") int companyID,
                        @Param("crm") int crm);

    /**
     * 查询合作方下拉框。根据城市name查找
     * @param city
     * @return
     */
    List<Partners> findOption(@Param("city") String city);

    /**
     * 根据oaid查找合作方，查询订单配置状态
     * @param oid oaid
     * @param ocode 合作方区域拆分键
     * @return
     */
    Partners findPartnersById(@Param("oid") int oid,
                              @Param("ocode") int ocode);

    /**
     * 根据oaid批量获取合作方信息
     * @param oaid
     * @return
     */
    List<Partners> findPartnersByIds(@Param("oaid") List<Integer> oaid);

    /**
     * 合作方下拉框,反查用，不需要参数
     * @return
     */
    List<Partners> findOptionByNull();

    /**
     * 查询所有合作方的省份
     * @return
     */
    List<Partners> findPartnersProvince();

    /**
     * 根据合作方的省份查询下面的市区
     * @param province 省份name
     * @return
     */
    List<Partners> findPartnersCity(@Param("province") String province);

    /**
     * 根据城市name，获取该城市下所有的合作方信息，通过销售区域匹配到所有的合作方
     * @param cityName
     * @return
     */
    List<Partners> findPartnersByCityName(@Param("cityName")String cityName);
}