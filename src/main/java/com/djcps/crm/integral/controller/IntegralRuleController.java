package com.djcps.crm.integral.controller;

import com.djcps.crm.aop.inneruser.annotation.InnerUser;
import com.djcps.crm.aop.log.AddLog;
import com.djcps.crm.commons.utils.ExcelUtil;
import com.djcps.crm.commons.utils.GsonUtils;
import com.djcps.crm.inneruser.model.InnerUserModel;
import com.djcps.crm.integral.enums.IntegralMsgEnum;
import com.djcps.crm.integral.service.IntegralRuleService;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.activation.MimetypesFileTypeMap;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.djcps.crm.commons.msg.MsgTemplate.failureMsg;
import static com.djcps.crm.commons.msg.MsgTemplate.successMsg;

/**
 * 积分规则 管理
 * Created by TruthBean on 2017-08-08 09:58.
 */
@RestController
@RequestMapping("/integral/rule/")
public class IntegralRuleController {

    private static final Logger logger = LoggerFactory.getLogger(IntegralRuleController.class);

    @Autowired
    private IntegralRuleService integralRuleService;

    /**
     * 显示充值送数据
     * <p>
     * Created by TruthBean on 2017-09-01 16:54
     *
     * @return map
     */
    @RequestMapping(value = "recharge/list", method = RequestMethod.POST)
    @AddLog(module = "积分服务",value = "显示充值送数据")
    protected Map<String, Object> listRechargeRules() {
        Map<String, Object> map = integralRuleService.listRechargeRules();
        return successMsg(map);
    }

    /**
     * 充值送
     * <p>
     * Created by TruthBean on 2017-08-17 20:42
     *
     * @return
     */
    @RequestMapping(value = "giftWhenRecharge", method = RequestMethod.POST)
    @AddLog(module = "积分服务",value = "充值送")
    protected Map<String, Object> giftWhenRecharge(@InnerUser InnerUserModel innerUserModel,
                                                   @RequestBody(required = false) String json) {
        logger.debug("params: {}", json);

        try {
            if (json != null && !"".equals(json.trim())) {

                JsonElement jsonElement = GsonUtils.getJsonElement(json);
                if (jsonElement.isJsonObject()) {
                    JsonObject jsonObj = jsonElement.getAsJsonObject();
                    if (jsonObj.has("json")) {
                        JsonElement jsonEle = jsonObj.get("json");
                        if (jsonEle.isJsonArray()) {
                            JsonArray jsonArray = jsonEle.getAsJsonArray();
                            int size = jsonArray.size();
                            List<Map<String, Object>> list = new ArrayList<>();

                            JsonObject jsonObject;
                            BigDecimal fpaymentsl1, fpaymentsl2;
                            BigInteger fintegral;
                            Map<String, Object> map;
                            boolean effect = false;
                            String fid = null;

                            for (int i = 0; i < size; i++) {
                                jsonObject = jsonArray.get(i).getAsJsonObject();
                                if (!jsonObject.has("fpaymentsl1") || !jsonObject.has("fintegral")) {
                                    return failureMsg(IntegralMsgEnum.PARAMS_ERROR);
                                }

                                if (jsonObject.has("effect")) {
                                    String effectStr = jsonObject.get("effect").getAsString();
                                    if (effectStr != null && !"".equals(effectStr)) {
                                        effect = Boolean.valueOf(effectStr);
                                    }
                                }

                                fpaymentsl1 = jsonObject.get("fpaymentsl1").getAsBigDecimal();
                                fintegral = jsonObject.get("fintegral").getAsBigInteger();

                                if (fpaymentsl1.compareTo(BigDecimal.ZERO) <= 0) {
                                    return failureMsg(IntegralMsgEnum.PARAMS_ERROR);
                                }

                                if (jsonObject.has("fid")) {
                                    String fidStr = jsonObject.get("fid").getAsString();
                                    if (!"".equals(fidStr)) {
                                        fid = fidStr;
                                    }
                                }

                                map = new HashMap<>();
                                map.put("fpartnerid", innerUserModel.getUcompany());
                                map.put("fcreater", innerUserModel.getId());
                                map.put("fkeyarea", innerUserModel.getOcode());

                                map.put("fpaymentsl1", fpaymentsl1);

                                map.put("fintegral", fintegral);
                                map.put("fintegraltype", 1);
                                map.put("fiseffective", effect);
                                effect = false;
                                map.put("fid", fid);
                                fid = null;

                                if (jsonObject.has("fpaymentsl2")) {
                                    String fpaymentsl2Str = jsonObject.get("fpaymentsl2").getAsString();
                                    if (!"".equals(fpaymentsl2Str)) {
                                        fpaymentsl2 = new BigDecimal(fpaymentsl2Str);
                                        if (fpaymentsl2.compareTo(BigDecimal.ZERO) >= 0 && fpaymentsl2.compareTo(fpaymentsl1) >= 0) {
                                            map.put("fpaymentsl2", fpaymentsl2);
                                        }
                                    }
                                }

                                list.add(map);
                            }
                            return integralRuleService.giftWhenRecharge(list);
                        }
                    }
                }
            }
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        }
        return failureMsg(IntegralMsgEnum.OPS_FAILURE);
    }

    /**
     * 购买送
     * <p>
     * Created by TruthBean on 2017-08-08 15:30
     *
     * @param json [{"fpaymentsl1":"1","fintegral":"10","fpartnerid":"06abeee8-2b6c-4b87-bef5-07b85b70024d",
     *             "fcommodityid":"54ab2e68-2869-fb87-bef5-07b85b70024a"},
     *             {"fpaymentsl1":"16","fintegral":"15","fpartnerid":"06abeee8-2b6c-4b87-bef5-07b85b70024e",
     *             "fcommodityid":"54ab2e68-2869-fb87-bef5-07b85b70024c"}]
     * @return
     */
    @RequestMapping(value = "giftWhenPurchasingWithRatio", method = RequestMethod.POST)
    @AddLog(module = "积分服务",value = "购买送")
    protected Map<String, Object> giftWhenPurchasingWithRatio(@InnerUser InnerUserModel innerUserModel,
                                                              @RequestBody(required = false) String json) {
        logger.debug("params: {}", json);

        try {
            if (json != null && !"".equals(json.trim())) {

                JsonElement jsonElement = GsonUtils.getJsonElement(json);
                List<Map<String, Object>> listAdd = new ArrayList<>();

                List<Map<String, Object>> listUpdate = new ArrayList<>();
                List<Map<String, Object>> listRemove = new ArrayList<>();

                if (jsonElement.isJsonArray()) {
                    JsonArray jsonArray = jsonElement.getAsJsonArray();
                    int size = jsonArray.size();

                    JsonObject jsonObject;
                    //付款单元金额
                    BigDecimal fpaymentsl1;
                    //积分
                    BigInteger fintegral;
                    //产品ID， 商品ID
                    String fpartnerid, fcommodityid;
                    Map<String, Object> map;

                    for (int i = 0; i < size; i++) {
                        jsonObject = jsonArray.get(i).getAsJsonObject();
                        if (!jsonObject.has("fpaymentsl1") || !jsonObject.has("fintegral") ||
                                !jsonObject.has("fpartnerid") || !jsonObject.has("fcommodityid")) {
                            return failureMsg(IntegralMsgEnum.PARAMS_ERROR);
                        }

                        fpaymentsl1 = jsonObject.get("fpaymentsl1").getAsBigDecimal();
                        fintegral = jsonObject.get("fintegral").getAsBigInteger();
                        fpartnerid = jsonObject.get("fpartnerid").getAsString();
                        fcommodityid = jsonObject.get("fcommodityid").getAsString();

                        if (fpaymentsl1.compareTo(BigDecimal.ZERO) < 0 || BigInteger.ZERO.compareTo(fintegral) > 0 ||
                                "".equals(fpartnerid.trim()) || "".equals(fcommodityid) ||
                                fcommodityid.length() < 32) {
                            return failureMsg(IntegralMsgEnum.PARAMS_ERROR);
                        }

                        if (fpaymentsl1.compareTo(BigDecimal.ZERO) == 0 && BigInteger.ZERO.compareTo(fintegral) != 0) {
                            return failureMsg(IntegralMsgEnum.PARAMS_ERROR);
                        }

                        map = new HashMap<>();
                        map.put("fpartnerid", fpartnerid);
                        map.put("fcreater", innerUserModel.getId());
                        if (jsonObject.has("fkeyarea")) {
                            map.put("fkeyarea", jsonObject.get("fkeyarea").getAsString());
                        } else {
                            map.put("fkeyarea", innerUserModel.getOcode());
                        }
                        map.put("fpaymentsl1", fpaymentsl1);
                        map.put("fintegral", fintegral);
                        map.put("fintegraltype", 2);
                        map.put("fcommodityid", fcommodityid);

                        if (jsonObject.has("integralruleid")) {
                            map.put("integralruleid", jsonObject.get("integralruleid").getAsString());
                            if (fpaymentsl1.compareTo(BigDecimal.ZERO) == 0 && BigInteger.ZERO.compareTo(fintegral) == 0) {
                                listRemove.add(map);
                            } else {
                                listUpdate.add(map);
                            }
                        } else {
                            listAdd.add(map);
                        }
                    }
                    //TODO 是否要支持批量操作？？？
                } else if (jsonElement.isJsonObject()) {
                    JsonObject jsonObject = jsonElement.getAsJsonObject();

                    //付款单元金额
                    BigDecimal fpaymentsl1;
                    //积分
                    BigInteger fintegral;
                    //产品ID， 商品ID
                    String fpartnerid, fcommodityid;
                    Map<String, Object> map;

                    if (!jsonObject.has("fpaymentsl1") || !jsonObject.has("fintegral") ||
                            !jsonObject.has("fpartnerid") || !jsonObject.has("fcommodityid")) {
                        return failureMsg(IntegralMsgEnum.PARAMS_ERROR);
                    }

                    fpaymentsl1 = jsonObject.get("fpaymentsl1").getAsBigDecimal();
                    fintegral = jsonObject.get("fintegral").getAsBigInteger();
                    fpartnerid = jsonObject.get("fpartnerid").getAsString();
                    fcommodityid = jsonObject.get("fcommodityid").getAsString();

                    if (fpaymentsl1.compareTo(BigDecimal.ZERO) < 0 || BigInteger.ZERO.compareTo(fintegral) > 0 ||
                            "".equals(fpartnerid.trim()) || "".equals(fcommodityid) ||
                            fcommodityid.length() < 32) {
                        return failureMsg(IntegralMsgEnum.PARAMS_ERROR);
                    }

                    if (fpaymentsl1.compareTo(BigDecimal.ZERO) == 0 && BigInteger.ZERO.compareTo(fintegral) != 0) {
                        return failureMsg(IntegralMsgEnum.PARAMS_ERROR);
                    }

                    map = new HashMap<>();
                    map.put("fpartnerid", fpartnerid);
//                    map.put("fcreater", 357);
//                    map.put("fkeyarea", 3306);
                    map.put("fcreater", innerUserModel.getId());
                    if (jsonObject.has("fkeyarea")) {
                        map.put("fkeyarea", jsonObject.get("fkeyarea").getAsString());
                    } else {
                        map.put("fkeyarea", innerUserModel.getOcode());
                    }
                    map.put("fpaymentsl1", fpaymentsl1);
                    map.put("fintegral", fintegral);
                    map.put("fintegraltype", 2);
                    map.put("fcommodityid", fcommodityid);

                    if (jsonObject.has("integralruleid")) {
                        String integralruleid = jsonObject.get("integralruleid").getAsString();
                        if (integralruleid != null && !"".equals(integralruleid)) {
                            map.put("fid", integralruleid);
                            if (fpaymentsl1.compareTo(BigDecimal.ZERO) == 0 && BigInteger.ZERO.compareTo(fintegral) == 0) {
                                return integralRuleService.delGiftWhenPurchasingWithRatio(map);
                            } else {
                                return integralRuleService.updateGiftWhenPurchasingWithRatio(map);
                            }
                        }
                    } else {
                        listAdd.add(map);
                    }

                }

                if (listAdd.size() > 0)
                    return integralRuleService.addGiftWhenPurchasingWithRatio(listAdd);
            }
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        }

        return failureMsg(IntegralMsgEnum.OPS_FAILURE);
    }

    /**
     * 线下导入积分
     * <p>
     * Created by TruthBean on 2017-08-08 20:56
     *
     * @return
     */
    @RequestMapping(value = "uploadExcel", method = RequestMethod.POST)
    @AddLog(module = "积分服务",value = "线下导入积分")
    protected Map<String, Object> uploadExcel(@InnerUser InnerUserModel innerUserModel,
                                              @RequestParam("file") MultipartFile file) {

        if (file == null) {
            return failureMsg(IntegralMsgEnum.PARAMS_NULL);
        }

        try {
            String originalFilename = file.getOriginalFilename();
            MimetypesFileTypeMap mimeTypesMap = new MimetypesFileTypeMap();
            String mimeType = mimeTypesMap.getContentType(originalFilename);
            logger.debug("upload file's mimeType is {}", mimeType);
            if (ExcelUtil.isExcel2003(originalFilename) || ExcelUtil.isExcel2007(originalFilename)) {
                InputStream inputStream = file.getInputStream();
                String[][] strings = ExcelUtil.readExcelSheet1(inputStream, originalFilename);
                Map<String, Object> result = integralRuleService.uploadExcel(strings, innerUserModel);
                if (result != null) {
                    if (result.containsKey("success")) {
                        if ((boolean) result.get("success")) {
                            return successMsg();
                        }
                    } else if (result.containsKey("errors")) {
                        return failureMsg(IntegralMsgEnum.EXCEL_CONTENT_ERROR, result.get("errors"));
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return failureMsg(IntegralMsgEnum.OPS_FAILURE);
    }

    /**
     * 根据合作方获取产品列表
     * <p>
     * Created by TruthBean on 2017-08-09 17:25
     *
     * @param json
     * @return
     */
    @RequestMapping(value = "product/list", method = RequestMethod.POST)
    @AddLog(module = "积分服务",value = "根据合作方获取产品列表")
    protected Map<String, Object> getProductListByPartnerId(@InnerUser InnerUserModel innerUserModel,
                                                            @RequestBody(required = false) String json) {
        logger.debug("params: {}", json);

        try {
            String partnerId = innerUserModel.getUcompany();
            String fkeyarea = innerUserModel.getOcode();

            int current = 0, size = 20;

            if (json != null) {
                JsonObject jsonObject = GsonUtils.getJsonObject(json);
                if (jsonObject.has("fpartner")) {
                    partnerId = jsonObject.get("fpartner").getAsString();
                }
//                if (jsonObject.has("fkeyarea")) {
//                    fkeyarea = jsonObject.get("fkeyarea").getAsString();
//                }
                if (jsonObject.has("current")) {
                    current = jsonObject.get("current").getAsInt();
                }
                if (jsonObject.has("size")) {
                    size = jsonObject.get("size").getAsInt();
                }
                if (jsonObject.has("")) {
                    fkeyarea = jsonObject.get("fkeyarea").getAsString();
                }
                Map<String, Object> map = integralRuleService.getProductList(partnerId, fkeyarea, current, size);
                if (map != null)
                    return successMsg(map);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return failureMsg(IntegralMsgEnum.OPS_FAILURE);
    }


}
