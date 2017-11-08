package com.djcps.crm.partnersarea.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.djcps.crm.aop.log.AddLog;
import com.djcps.crm.commons.msg.MsgTemplate;
import com.djcps.crm.partnersarea.enums.PartnersAreaEnum;
import com.djcps.crm.partnersarea.model.PartnersArea;
import com.djcps.crm.partnersarea.service.PartnersAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * Created by L-wenbin on 2017/7/8.
 */
@RestController
public class PartnersAreaController {
    @Autowired
    private PartnersAreaService partnersAreaService;

    /**
     * 查询合作商区域列表
     * @param json
     * @return
     */
    @RequestMapping(value = "partnersArea/findAllArea", method = RequestMethod.POST)
    @AddLog(module = "合作方区域",value = "查询合作商区域列表")
    public Map<String, Object> findAllArea(@RequestBody(required = false) String json) {
        try {
            JSONObject jsonObject = JSONObject.parseObject(json);
            String pid = jsonObject.getString("pid");
            int keyArea = jsonObject.getInteger("keyArea");
            List<PartnersArea> partnersAreaList = partnersAreaService.findAllArea(pid, keyArea);
            return MsgTemplate.successMsg(partnersAreaList);
        }catch (Exception e){
            e.printStackTrace();
            return MsgTemplate.failureMsg(PartnersAreaEnum.OPERATION_FAILURE);
        }
    }

    /**
     * 添加一个区域
     *
     * @param json
     * @return
     */
    @RequestMapping(value = "partnersArea/addPartnersArea", method = RequestMethod.POST)
    @AddLog(module = "合作方区域",value = "添加一个区域")
    public Map<String, Object> addPartnersArea(@RequestBody(required = false) String json) {
        try {
            PartnersArea partnersArea = JSON.parseObject(json, PartnersArea.class);
            boolean result = partnersAreaService.addPartnersArea(partnersArea);
            if (result) {
                return MsgTemplate.successMsg(PartnersAreaEnum.ADD_SUCCESS);
            }
            return MsgTemplate.failureMsg(PartnersAreaEnum.ADD_FAILURE);
        } catch (Exception e) {
            e.printStackTrace();
            return MsgTemplate.failureMsg(PartnersAreaEnum.OPERATION_FAILURE);
        }
    }

    /**
     * 删除一个区域
     *
     * @param json
     * @return
     */
    @RequestMapping(value = "partnersArea/delPartnersArea", method = RequestMethod.POST)
    @AddLog(module = "合作方区域",value = "删除一个区域")
    public Map<String, Object> delPartnersArea(@RequestBody(required = false) String json) {
        try {
            JSONObject jsonObject = JSONObject.parseObject(json);
            String aid = jsonObject.getString("aid");
            int fkeyarea = jsonObject.getInteger("fkeyarea");
            boolean result = partnersAreaService.delPartnersArea(aid,fkeyarea);
            if (result) {
                return MsgTemplate.successMsg(PartnersAreaEnum.DEL_SUCCESS);
            }
            return MsgTemplate.failureMsg(PartnersAreaEnum.DEL_FAILURE);
        } catch (Exception e) {
            e.printStackTrace();
            return MsgTemplate.failureMsg(PartnersAreaEnum.OPERATION_FAILURE);
        }
    }
    /**
     * 省份下拉框,反查用，根据合作商获取合作商省份
     *
     * @param json pid 合作商uuid
     * @return
     */
    @RequestMapping(value = "partnersArea/findOptionByPartner", method = RequestMethod.POST)
    @AddLog(module = "合作方区域",value = "省份下拉框,反查用，根据合作商获取合作商省份")
    public Map<String, Object> findOptionByPartner(@RequestBody(required = false) String json) {
        try {
            JSONObject jsonObject = JSONObject.parseObject(json);
            int oid = jsonObject.getInteger("oid");
            int keyarea = jsonObject.getInteger("keyarea");
            List<PartnersArea> listOption = partnersAreaService.findOptionByPartner(oid, keyarea);
            return MsgTemplate.successMsg(listOption);
        }catch (Exception e){
            e.printStackTrace();
            return MsgTemplate.failureMsg(PartnersAreaEnum.OPERATION_FAILURE);
        }
    }
    /**
     * 市区下拉框,反查用，根据省份code获取市区
     *
     * @param json provincecode 省份code
     * @return
     */
    @RequestMapping(value = "partnersArea/findOptionByProvince", method = RequestMethod.POST)
    @AddLog(module = "合作方区域",value = "市区下拉框,反查用，根据省份code获取市区")
    public Map<String, Object> findOptionByProvince(@RequestBody(required = false) String json) {
        try {
            JSONObject jsonObject = JSONObject.parseObject(json);
            String pid = jsonObject.getString("pid");
            int provincecode = jsonObject.getInteger("provincecode");
            int keyarea = jsonObject.getInteger("keyarea");
            List<PartnersArea> listOption = partnersAreaService.findOptionByProvince(pid,provincecode, keyarea);
            return MsgTemplate.successMsg(listOption);
        }catch (Exception e){
            e.printStackTrace();
            return MsgTemplate.failureMsg(PartnersAreaEnum.OPERATION_FAILURE);
        }
    }

    /**
     * 查询合作方地址表里面的下拉省份，返回给省份name和code
     * @return
     */
    @RequestMapping(value = "partnersArea/findAllProvince", method = RequestMethod.POST)
    @AddLog(module = "合作方区域",value = "查询合作方地址表里面的下拉省份，返回给省份name和code")
    public Map<String, Object> findAllProvince() {
        List<PartnersArea> partnersAreas = partnersAreaService.findAllProvince();
        return MsgTemplate.successMsg(partnersAreas);
    }

    /**
     * 查询合作方地址表中省份下的城市,返回市区code和name
     * @param json
     * @return
     */
    @RequestMapping(value = "partnersArea/findCityByProvince", method = RequestMethod.POST)
    @AddLog(module = "合作方区域",value = "查询合作方地址表中省份下的城市,返回市区code和name")
    public Map<String, Object> findCityByProvince(@RequestBody(required = false) String json) {
        try {
            JSONObject jsonObject = JSONObject.parseObject(json);
            String provinceCode = jsonObject.getString("provinceCode");
            List<PartnersArea> partnersAreas = partnersAreaService.findCityByProvince(provinceCode);
            return MsgTemplate.successMsg(partnersAreas);
        }catch (Exception e){
            e.printStackTrace();
            return MsgTemplate.failureMsg(PartnersAreaEnum.OPERATION_FAILURE);
        }
    }
}
