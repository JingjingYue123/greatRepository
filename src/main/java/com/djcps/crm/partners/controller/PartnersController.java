package com.djcps.crm.partners.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baidu.unbiz.fluentvalidator.ComplexResult;
import com.baidu.unbiz.fluentvalidator.FluentValidator;
import com.baidu.unbiz.fluentvalidator.jsr303.HibernateSupportedValidator;
import com.djcps.crm.aop.crmurlauth.CrmUrlAuth;
import com.djcps.crm.aop.log.AddLog;
import com.djcps.crm.commons.msg.MsgTemplate;
import com.djcps.crm.commons.utils.DataUtils;
import com.djcps.crm.partners.enums.PartnersEnums;
import com.djcps.crm.partners.model.Partners;
import com.djcps.crm.partners.model.PartnersPage;
import com.djcps.crm.partners.service.PartnersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Validation;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.baidu.unbiz.fluentvalidator.ResultCollectors.toComplex;

/**
 * Created by L-wenbin on 2017/7/8.
 */
@RestController
public class PartnersController {
    @Autowired
    private PartnersService partnersService;

    /**
     * 查询oa所有合作商信息
     *
     * @return
     */
    @RequestMapping(value = "partners/findOaPartners", method = RequestMethod.POST)
    @AddLog(module = "合作方",value = "查询oa所有合作商信息")
    public Map<String, Object> findOaPartners() {
        try {
            return MsgTemplate.successMsg(partnersService.findOaPartners());
        } catch (Exception e) {
            e.printStackTrace();
            return MsgTemplate.failureMsg(PartnersEnums.ADD_ERROR);
        }
    }

    /**
     * 根据oaid。到组织中获取合作商详细
     *
     * @return
     */
    @RequestMapping(value = "partners/findPartnerByOaid", method = RequestMethod.POST)
    @CrmUrlAuth(NO_NEED_LOGIN = 1)
    @AddLog(module = "合作方",value = "根据oaid。到组织中获取合作商详细")
    public Map<String, Object> findPartnerByOaid(@RequestBody String json) {
        try {
            JSONObject jsonObject = JSONObject.parseObject(json);
            int oaid = jsonObject.getInteger("oaid");
            return partnersService.findPartnerByOaid(oaid);
        } catch (Exception e) {
            e.printStackTrace();
            return MsgTemplate.failureMsg(PartnersEnums.ADD_ERROR);
        }
    }

    /**
     * 查询合作商列表，带条件查询，带分页
     * oname 合作商名称
     * oprovince 合作商 省份
     * ocity 合作商市区
     * page 页码
     *
     * @return
     */
    @RequestMapping(value = "partners/findPartnersPage", method = RequestMethod.POST)
    @AddLog(module = "合作方",value = "查询合作商列表，带条件查询，带分页")
    public Map<String, Object> findPartnersPage(@RequestBody(required = false) String json) {
        try {
            PartnersPage partnersPage = JSON.parseObject(json, PartnersPage.class);
            String oname = DataUtils.replaceStr(partnersPage.getOname());
            String oprovince = partnersPage.getOprovince();
            String ocity = partnersPage.getOcity();
            int page = partnersPage.getPage();
            int pageSize = partnersPage.getPageSize();
            Map<String, Object> result = new HashMap<>();
            result.put("partnersList", partnersService.findPartnersPage(oname, oprovince, ocity, page, pageSize));
            return MsgTemplate.successMsg(result);
        } catch (Exception e) {
            e.printStackTrace();
            return MsgTemplate.failureMsg(PartnersEnums.ADD_ERROR);
        }
    }

    /**
     * 查询单个合作商信息
     *
     * @param json pid 合作UUID
     * @return
     */
    @RequestMapping(value = "partners/findOnePartners", method = RequestMethod.POST)
    @AddLog(module = "合作方",value = "查询单个合作商信息")
    public Map<String, Object> findOnePartners(@RequestBody(required = false) String json) {
        try {
            JSONObject jsonObject = JSONObject.parseObject(json);
            String pid = jsonObject.getString("pid");
            int fkeyarea = jsonObject.getInteger("fkeyarea");
            Partners partners = partnersService.findOnePartners(pid, fkeyarea);
            return MsgTemplate.successMsg(partners);
        } catch (Exception e) {
            e.printStackTrace();
            return MsgTemplate.failureMsg(PartnersEnums.ADD_ERROR);
        }
    }

    /**
     * 添加合作商
     *
     * @return
     */
    @RequestMapping(value = "partners/addPartners", method = RequestMethod.POST)
    @AddLog(module = "合作方",value = "添加合作商")
    public Map<String, Object> addPartners(@RequestBody(required = false) String json) {
        try {
            Partners partners = JSON.parseObject(json, Partners.class);
            ComplexResult ret = FluentValidator.checkAll().failFast()
                    .on(partners, new HibernateSupportedValidator<Partners>().
                            setHiberanteValidator(Validation.buildDefaultValidatorFactory()
                                    .getValidator()))
                    .doValidate().result(toComplex());
            if (ret.isSuccess()) {
                if (partners.getPid() == null) {
                    partnersService.addPartners(partners);
                    return MsgTemplate.successMsg(PartnersEnums.ADD_SUCCESS);
                } else {
                    return MsgTemplate.failureMsg(PartnersEnums.ADD_ERROR);
                }
            } else {
                return MsgTemplate.failureMsg(ret);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return MsgTemplate.failureMsg(PartnersEnums.ADD_ERROR);
        }
    }

    /**
     * 修改合作商信息
     *
     * @param json partners 合作商实体
     * @return
     */
    @RequestMapping(value = "partners/updatePartners", method = RequestMethod.POST)
    @AddLog(module = "合作方",value = "修改合作商信息")
    public Map<String, Object> updatePartners(@RequestBody(required = false) String json) {
        try {
            Partners partners = JSON.parseObject(json, Partners.class);
            ComplexResult ret = FluentValidator.checkAll().failFast()
                    .on(partners, new HibernateSupportedValidator<Partners>().
                            setHiberanteValidator(Validation.buildDefaultValidatorFactory()
                                    .getValidator()))
                    .doValidate().result(toComplex());
            if (ret.isSuccess()) {
                partnersService.updatePartners(partners);
                return MsgTemplate.successMsg(PartnersEnums.ADD_SUCCESS);
            } else {
                return MsgTemplate.failureMsg(ret);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return MsgTemplate.failureMsg(PartnersEnums.ADD_ERROR);
        }
    }

    /**
     * 修改合作商状态
     *
     * @param json status 状态码 1启用 0禁用
     * @return
     */
    @RequestMapping(value = "partners/updateStatus", method = RequestMethod.POST)
    @AddLog(module = "合作方",value = "修改合作商状态")
    public Map<String, Object> updateStatus(@RequestBody(required = false) String json) {
        try {
            JSONObject jsonObject = JSONObject.parseObject(json);
            int companyID = jsonObject.getInteger("companyID");
            int crm = jsonObject.getInteger("crm");
            Map<String, Object> result = partnersService.upPartnerStatus(companyID, crm);
            if (result != null) {
                return result;
            } else {
                return MsgTemplate.failureMsg(PartnersEnums.ADD_ERROR);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return MsgTemplate.failureMsg(PartnersEnums.ADD_ERROR);
        }
    }

    /**
     * 合作方下拉框，通过市获取合作方信息
     *
     * @param json
     * @return
     */
    @RequestMapping(value = "partners/findOption", method = RequestMethod.POST)
    @AddLog(module = "合作方",value = "合作方下拉框，通过市获取合作方信息")
    public Map<String, Object> findOption(@RequestBody(required = false) String json) {
        try {
            JSONObject jsonObject = JSONObject.parseObject(json);
            String city = jsonObject.getString("city");
            if (city != null) {
                List<Partners> listOption = partnersService.findOption(city);
                return MsgTemplate.successMsg(listOption);
            } else {
                return MsgTemplate.failureMsg(PartnersEnums.ADD_ERROR);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return MsgTemplate.failureMsg(PartnersEnums.ADD_ERROR);
        }
    }

    /**
     * 合作方下拉框,反查用，不需要参数
     *
     * @return
     */
    @RequestMapping(value = "partners/findOptionByNull", method = RequestMethod.POST)
    @AddLog(module = "合作方",value = "合作方下拉框,反查用，不需要参数")
    public Map<String, Object> findOptionByNull() {
        List<Partners> listOption = partnersService.findOptionByNull();
        return MsgTemplate.successMsg(listOption);
    }

    /**
     * 根据oaid查找合作方，查询订单配置状态
     *
     * @return
     */
    @RequestMapping(value = "partners/findPartnersById", method = RequestMethod.POST)
    @AddLog(module = "合作方",value = "根据oaid查找合作方，查询订单配置状态")
    public Map<String, Object> findPartnersById(@RequestBody(required = false) String json) {
        try {
            JSONObject jsonObject = JSONObject.parseObject(json);
            int oid = jsonObject.getInteger("oid");
            int ocode = jsonObject.getInteger("ocode");
            Partners partners = partnersService.findPartnersById(oid, ocode);
            return MsgTemplate.successMsg(partners);
        } catch (Exception e) {
            e.printStackTrace();
            return MsgTemplate.failureMsg(PartnersEnums.ADD_ERROR);
        }
    }

    /**
     * 根据oaid批量获取合作方信息
     *
     * @param json
     * @return
     */
    @RequestMapping(value = "partners/findPartnersByIds", method = RequestMethod.POST)
    @AddLog(module = "合作方",value = "根据oaid批量获取合作方信息")
    public Map<String, Object> findPartnersByIds(@RequestBody(required = false) String json) {
        //List<Integer> list = GsonUtils.toListBean(json, Integer[].class);
        try {
            List<Integer> list = JSON.parseArray(json, Integer.class);
            List<Partners> partnersList = partnersService.findPartnersByIds(list);
            if (partnersList != null) {
                return MsgTemplate.successMsg(partnersList);
            } else {
                return MsgTemplate.failureMsg(PartnersEnums.ADD_ERROR);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return MsgTemplate.failureMsg(PartnersEnums.ADD_ERROR);
        }
    }

    /**
     * 查询所有合作方的省份
     *
     * @return
     */
    @RequestMapping(value = "partners/findPartnersProvince", method = RequestMethod.POST)
    @AddLog(module = "合作方",value = "查询所有合作方的省份")
    public Map<String, Object> findPartnersProvince() {
        List<Partners> partnersList = partnersService.findPartnersProvince();
        return MsgTemplate.successMsg(partnersList);
    }

    /**
     * 根据合作方省份，查询所有合作方的市区
     *
     * @param json province 省份name
     * @return
     */
    @RequestMapping(value = "partners/findPartnersCity", method = RequestMethod.POST)
    @AddLog(module = "合作方",value = " 根据合作方省份，查询所有合作方的市区")
    public Map<String, Object> findPartnersCity(@RequestBody(required = false) String json) {
        try {
            JSONObject jsonObject = JSONObject.parseObject(json);
            String province = jsonObject.getString("province");
            List<Partners> partnersList = partnersService.findPartnersCity(province);
            return MsgTemplate.successMsg(partnersList);
        } catch (Exception e) {
            e.printStackTrace();
            return MsgTemplate.failureMsg(PartnersEnums.ADD_ERROR);
        }
    }

    /**
     * 根据城市name，获取该城市下所有的合作方信息，通过销售区域匹配到所有的合作方
     *
     * @param json
     * @return
     */
    @RequestMapping(value = "partners/findPartnersByCityName", method = RequestMethod.POST)
    @AddLog(module = "合作方",value = "根据城市name，获取该城市下所有的合作方信息，通过销售区域匹配到所有的合作方")
    public Map<String, Object> findPartnersByCityName(@RequestBody(required = false) String json) {
        try {
            JSONObject jsonObject = JSONObject.parseObject(json);
            String cityName = jsonObject.getString("cityName");
            List<Partners> partnersList = partnersService.findPartnersByCityName(cityName);
            return MsgTemplate.successMsg(partnersList);
        } catch (Exception e) {
            e.printStackTrace();
            return MsgTemplate.failureMsg(PartnersEnums.ADD_ERROR);
        }
    }
}
