package com.djcps.crm.partners.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.djcps.crm.commons.exception.CrmServerException;
import com.djcps.crm.commons.utils.Page;
import com.djcps.crm.partners.dao.PartnersDao;
import com.djcps.crm.partners.model.OaPartners;
import com.djcps.crm.partners.model.Partners;
import com.djcps.crm.partners.model.PartnersInit;
import com.djcps.crm.partners.request.PartnersInterface;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by L-wenbin on 2017/7/8.
 */
@Service
public class PartnersService {
    @Autowired
    private PartnersDao partnersDao;

    @Autowired
    private PartnersInterface partnersInterface;

    private Gson gson = new Gson();
    private JsonParser jsonParser = new JsonParser();

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(PartnersService.class);



    /**
     * 查询合作商列表，带条件查询，带分页
     *
     * @param oname     合作商  省份
     * @param oprovince 合作商  市
     * @param ocity     合作商名字
     * @param page      每页显示条数
     * @return
     */
    public Page<PartnersInit> findPartnersPage(String oname, String oprovince, String ocity,
                                           int page,int pageSize) {
        int total = partnersDao.findPartnersCount(oname, oprovince, ocity);
        Page<PartnersInit> partnersPage = new Page<>(page, pageSize, total);
        List<PartnersInit> content = new ArrayList<>();
        if (total > 0) {
            content = partnersDao.findPartnersPage(oname, oprovince, ocity, partnersPage.getBegin(),pageSize);
        }
        partnersPage.setContent(content);
        return partnersPage;
    }

    /**
     * 查询单个合作商信息
     *
     * @param pid 合作UUID
     * @return
     */
    public Partners findOnePartners(String pid, int fkeyarea) {
        return partnersDao.findOnePartners(pid, fkeyarea);
    }

    /**
     * 添加合作商
     *
     * @param partners 合作商实体
     * @return
     */
    public boolean addPartners(Partners partners) {
        partners.setPid(UUID.randomUUID().toString());
        return partnersDao.addPartners(partners) > 0;
    }

    /**
     * 修改合作商信息
     *
     * @param partners 合作商实体
     * @return
     */
    public boolean updatePartners(Partners partners) {
        return partnersDao.updatePartners(partners) > 0;
    }

    /**
     * 获取oa所有合作商
     *
     * @return
     */
    public List<OaPartners> findOaPartners() {
        String oaPartners = partnersInterface.findOaPartners().getBodyString();
        JsonObject jsonObject = jsonParser.parse(oaPartners).getAsJsonObject();
        List<OaPartners> partners = gson.fromJson(jsonObject.get("data"), new TypeToken<List<OaPartners>>() {
        }.getType());
        List<Partners> partnersList = partnersDao.findPartners();
        List<Integer> idList = partnersList.stream().map(x -> x.getId()).collect(Collectors.toList());
        List<OaPartners> oaPartnersList = partners.stream().filter(x -> !idList.contains(x.getId())).collect(Collectors.toList());
        return oaPartnersList;
    }

    /**
     * 根据oaid，到组织中获取合作商详情
     *
     * @param oaid oaid
     */
    public Map<String,Object> findPartnerByOaid(int oaid) {
        String result = partnersInterface.findPartnerByOaid(oaid).getBodyString();
        JSONObject jsonObject = JSON.parseObject(result,JSONObject.class);
        logger.debug("params: {}", jsonObject);
        return jsonObject;
    }

    /**
     * 修改合作方登陆crm的权限状态
     * @param companyID 合作方 oaid
     * @param crm   修改状态  1-启用  0-禁用
     * @return
     */
    @Transactional
    public Map<String,Object> upPartnerStatus(int companyID, int crm) {
        int partnersStatus = partnersDao.upPartnerStatus(companyID,crm);
        if(partnersStatus > 0){
            String result = partnersInterface.upPartnerStatus(companyID,crm).getBodyString();
            JSONObject jsonObject = JSON.parseObject(result,JSONObject.class);
            if(!Boolean.parseBoolean(jsonObject.getString("success")))
                throw new CrmServerException("修改失败");
            return jsonObject;
        }
        return null;
    }

    /**
     * 合作方下拉框
     * @param city 根据城市获取
     * @return
     */
    public List<Partners> findOption(String city) {
        return partnersDao.findOption(city);
    }

    /**
     * 根据oaid查找合作方，查询订单配置状态
     * @param oid oaid
     * @param ocode 合作方区域拆分键
     * @return
     */
    public Partners findPartnersById(int oid,int ocode) {
        return partnersDao.findPartnersById(oid,ocode);
    }

    /**
     * 根据oaid批量获取合作方信息
     * @param jsonArray
     * @return
     */
    public List<Partners> findPartnersByIds(List<Integer> jsonArray) {
        return partnersDao.findPartnersByIds(jsonArray);
    }

    /**
     * 合作方下拉框,反查用，不需要参数
     * @return
     */
    public List<Partners> findOptionByNull() {
        return partnersDao.findOptionByNull();
    }

    /**
     * 查询所有合作方的省份
     * @return
     */
    public List<Partners> findPartnersProvince() {
        return partnersDao.findPartnersProvince();
    }

    /**
     * 根据合作方的省份查询下面的市区
     * @param province
     * @return
     */
    public List<Partners> findPartnersCity(String province) {
        return partnersDao.findPartnersCity(province);
    }

    /**\
     * 根据城市name，获取该城市下所有的合作方信息，通过销售区域匹配到所有的合作方
     * @param cityName
     * @return
     */
    public List<Partners> findPartnersByCityName(String cityName) {
        return partnersDao.findPartnersByCityName(cityName);
    }
}
