package com.djcps.crm.integral.service;

import com.djcps.crm.commons.msg.MsgTemplate;
import com.djcps.crm.commons.utils.GsonUtils;
import com.djcps.crm.commons.utils.StringUtils;
import com.djcps.crm.inneruser.model.InnerUserModel;
import com.djcps.crm.integral.model.ChangeUserIntegralRequest;
import com.djcps.crm.integral.model.IntegralUploadTips;
import com.djcps.crm.integral.model.MarkingPlanProduct;
import com.djcps.crm.integral.model.UserIntegralChangeTips;
import com.djcps.crm.integral.server.IntegralServer;
import com.djcps.crm.integral.server.OutUserServer;
import com.djcps.crm.integral.server.ProductServer;
import com.djcps.crm.partners.dao.PartnersDao;
import com.djcps.crm.partners.model.Partners;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import rpc.plugin.http.HTTPResponse;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;

/**
 * Created by TruthBean on 2017-08-08 10:37.
 */
@Service
public class IntegralRuleService {

    private static final Logger logger = LoggerFactory.getLogger(IntegralRuleService.class);

    @Autowired
    private IntegralServer integralServer;

    @Autowired
    private OutUserServer outUserServer;

    @Autowired
    private ProductServer productServer;

    @Autowired
    private PartnersDao partnersDao;

    private Map<String, Object> addIntegralRule(String json) {
        logger.debug("params of adding integral rule from remote is {}", json);
        HTTPResponse httpResponse = integralServer.addIntegralRule(json);
        if (httpResponse != null) {
            if (httpResponse.isSuccessful()) {
                String result = httpResponse.getBodyString();
                logger.debug("result of adding integral rule from remote is {}", result);
                return MsgTemplate.successMsg(result);
            }
        }
        return null;
    }

    private Map<String, Object> delIntegralRule(String json) {
        logger.debug("params of removing integral rule from remote is {}", json);
        HTTPResponse httpResponse = integralServer.delIntegralRule(json);
        if (httpResponse != null) {
            if (httpResponse.isSuccessful()) {
                String result = httpResponse.getBodyString();
                logger.debug("result of removing integral rule from remote is {}", result);
                return MsgTemplate.successMsg(result);
            }
        }
        return null;
    }

    private Map<String, Object> updateIntegralRule(String json) {
        logger.debug("params of updating integral rule from remote is {}", json);
        HTTPResponse httpResponse = integralServer.updateIntegralRule(json);
        if (httpResponse != null) {
            if (httpResponse.isSuccessful()) {
                String result = httpResponse.getBodyString();
                logger.debug("result of updating integral rule from remote is {}", result);
                return MsgTemplate.successMsg(result);
            }
        }
        return null;
    }

    public Map<String, Object> giftWhenRecharge(List<Map<String, Object>> list) {
        String json = GsonUtils.toJson(list);
        logger.debug("params: {}", json);
        return updateRechargeIntegralRule(json);
    }

    private Map<String, Object> updateRechargeIntegralRule(String json) {
        logger.debug("params of updating recharge integral rule from remote is {}", json);
        HTTPResponse httpResponse = integralServer.updateRechargeIntegralRule(json);
        if (httpResponse != null) {
            if (httpResponse.isSuccessful()) {
                String result = httpResponse.getBodyString();
                logger.debug("result of updating recharge integral rule from remote is {}", result);
                return MsgTemplate.successMsg(result);
            }
        }
        return null;
    }

    /**
     * 按比例充值送
     *
     * @param fpaymentsl1
     * @param fintegral
     * @param innerUserModel
     * @return
     */
    public Map<String, Object> giftWhenRechargeWithRatio(BigDecimal fpaymentsl1, BigInteger fintegral,
                                                         InnerUserModel innerUserModel) {
        List<Map<String, Object>> list = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        map.put("fpartnerid", innerUserModel.getUcompany());
        map.put("fcreater", innerUserModel.getId());
        map.put("fkeyarea", innerUserModel.getOcode());
        map.put("fpaymentsl1", fpaymentsl1);
        map.put("fintegral", fintegral);
        map.put("fintegraltype", 1);
        list.add(map);
        String json = GsonUtils.toJson(list);
        logger.debug("params: {}", json);
        return addIntegralRule(json);
    }

    /**
     * 按比例充值送
     *
     * @param fpaymentsl1
     * @param fintegral
     * @param innerUserModel
     * @return
     */
    public Map<String, Object> giftWhenRechargeWithInterval(BigDecimal fpaymentsl1, BigDecimal fpaymentsl2,
                                                            BigInteger fintegral, InnerUserModel innerUserModel) {
        Map<String, Object> map = new HashMap<>();
        map.put("fpartnerid", innerUserModel.getUcompany());
        map.put("fcreater", innerUserModel.getId());
        map.put("fkeyarea", innerUserModel.getOcode());
        map.put("fpaymentsl1", fpaymentsl1);
        map.put("fpaymentsl2", fpaymentsl2);
        map.put("fintegral", fintegral);
        map.put("fintegraltype", 1);
        String json = GsonUtils.toJson(map);
        logger.debug("params: {}", json);
        return addIntegralRule(json);
    }

    /**
     * 添加 购买送 规则
     *
     * @param list
     * @return
     */
    public Map<String, Object> addGiftWhenPurchasingWithRatio(List<Map<String, Object>> list) {
        String json = GsonUtils.toJson(list);
        logger.debug("params: {}", json);
        return addIntegralRule(json);
    }

    /**
     * 删除 购买送 规则
     *
     * @param map
     * @return
     */
    public Map<String, Object> delGiftWhenPurchasingWithRatio(Map<String, Object> map) {
        String json = GsonUtils.toJson(map);
        logger.debug("params: {}", json);
        return delIntegralRule(json);
    }

    /**
     * 更新 购买送 规则
     *
     * @param map
     * @return
     */
    public Map<String, Object> updateGiftWhenPurchasingWithRatio(Map<String, Object> map) {
        String json = GsonUtils.toJson(map);
        logger.debug("params: {}", json);
        return updateIntegralRule(json);
    }

    public Map<String, Object>  uploadExcel(String[][] strings, InnerUserModel innerUserModel) {
        List<UserIntegralChangeTips> errors = new ArrayList<>();
        int rowLength = strings.length;
//        List<Map<String, String>> phones = new ArrayList<>();
//        List<Map<String, String>> userNames = new ArrayList<>();
        List<IntegralUploadTips> tipsList = new ArrayList<>();

        //手机号	客户名称	积分类型	积分数	 备注
        for (int i = 1; i < rowLength; i++) {

            //过滤空行
            if (strings[i][0] == null && strings[i][1] == null && strings[i][2] == null && strings[i][3] == null &&
                    strings[i][4] == null) {
                continue;
            }

            //String tips1 = "第 " + (i + 1) + " 行，第 " + 1 + " 列";

            UserIntegralChangeTips tips1 = new UserIntegralChangeTips();
            tips1.setRow(i + 1);
            tips1.setColumn(1);

            IntegralUploadTips uploadTips = new IntegralUploadTips();

            if (strings[i][0] == null || !StringUtils.isChinaPhoneLegal(strings[i][0].trim())) {
                errors.add(tips1);
            } else {
                uploadTips.setPhone(strings[i][0]);
                uploadTips.setPhoneTip(tips1);
//                Map<String, String> map = new HashMap<>();
//                map.put(strings[i][0], tips1);
//                phones.add(map);
            }

            //String tips2 = "第 " + (i + 1) + " 行，第 " + 2 + " 列";

            UserIntegralChangeTips tips2 = new UserIntegralChangeTips();
            tips2.setRow(i + 1);
            tips2.setColumn(2);

//            Map<String, String> map = new HashMap<>();
//            map.put(strings[i][1], tips2);
//            userNames.add(map);
            uploadTips.setName(strings[i][1]);
            uploadTips.setNameTip(tips2);
            tipsList.add(uploadTips);

            /**
             * 线下类型
             * 5	社区活动积分	加积分
             * 6	商城活动积分	加积分
             * 7	包辅活动积分	加积分
             * 8	会员返利积分	加积分
             * 9	专享返利积分	加积分
             * 10	其他返利积分	加积分
             */
            //String tips3 = "第 " + (i + 1) + " 行，第 " + 3 + " 列";
            UserIntegralChangeTips tips3 = new UserIntegralChangeTips();
            tips3.setRow(i + 1);
            tips3.setColumn(3);

            String typeStr = strings[i][2];
            if (typeStr != null) {
                if (!(strings[i][2].equals("社区活动积分") || strings[i][2].equals("商城活动积分") ||
                        strings[i][2].equals("包辅活动积分") || strings[i][2].equals("会员返利积分") ||
                        strings[i][2].equals("专享返利积分") || strings[i][2].equals("其他返利积分"))) {
                    errors.add(tips3);
                }
            } else {
                errors.add(tips3);
            }

            //String tips4 = "第 " + (i + 1) + " 行，第 " + 4 + " 列";
            UserIntegralChangeTips tips4 = new UserIntegralChangeTips();
            tips4.setRow(i + 1);
            tips4.setColumn(4);

            BigInteger integral = null;
            try {
                String integralStr = strings[i][3];
                if (integralStr != null) {
                    //最大10位
                    if ((integralStr.startsWith("-") && integralStr.length() <= 11) ||
                            (!integralStr.startsWith("-") && integralStr.length() <= 10)) {
                        integral = BigInteger.valueOf(Math.round(Double.parseDouble(strings[i][3])));
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (integral == null || integral.compareTo(BigInteger.ZERO) == 0) {
                errors.add(tips4);
            }

            //String tips5 = "第 " + (i + 1) + " 行，第 " + 5 + " 列";
            UserIntegralChangeTips tips5 = new UserIntegralChangeTips();
            tips5.setRow(i + 1);
            tips5.setColumn(5);

            String remark = strings[i][4];
            if (remark != null && remark.trim().length() > 20) {
                errors.add(tips5);
            }
        }

        Map<String, Object> validateUserInfoResult = validateUserInfo(tipsList);
        if (validateUserInfoResult != null) {
            List<UserIntegralChangeTips> errorsTips = (List<UserIntegralChangeTips>) validateUserInfoResult.get("errors");
            errors.addAll(errorsTips);
            Collections.sort(errors);
        }

        Map<String, Object> result = new HashMap<>();
        if (errors.size() > 0) {
            //返回错误信息
            List<String> errorsStrs = new ArrayList<>();
            for (UserIntegralChangeTips tips : errors) {
                errorsStrs.add(tips.toString());
            }
            result.put("errors", errorsStrs);
        } else {
            List<Map<String, String>> userInfos = (List<Map<String, String>>) validateUserInfoResult.get("userinfos");
            //TODO
//            boolean flag = updateUserIntegralBatch(strings, null, rowLength, userInfos);
            boolean flag = updateUserIntegralBatch(strings, innerUserModel, rowLength, userInfos);
            result.put("success", flag);
        }
        return result;
    }

    private boolean updateUserIntegralBatch(String[][] strings, InnerUserModel innerUserModel, int rowLength, List<Map<String, String>> userInfos) {
        //添加数据
        //{"fuserid":"3278aae1-b69c-4b47-8847-8e335ce6ea4b","fpartnerid":"59559acf-f10b-4120-be77-1644a6cfc83c",
        //       "fremark":"瞎写的","fintegeral":6666,"ftype":2,"fkeyarea":"3302","fpartnerFkeyarea":3303,"fcreater":"6666666666"}
        List<ChangeUserIntegralRequest> list = new ArrayList<>();
        ChangeUserIntegralRequest request;

        //过滤掉excel标题行
        for (int i = 1; i < rowLength; i++) {

            //过滤空行
            if (strings[i][0] == null && strings[i][1] == null && strings[i][2] == null && strings[i][3] == null &&
                    strings[i][4] == null) {
                continue;
            }

            request = new ChangeUserIntegralRequest();
            //TODO
            request.setFpartnerid(innerUserModel.getUcompany());
            request.setFpartnerFkeyarea(Integer.parseInt(innerUserModel.getOcode()));
            request.setFcreater(Integer.toString(innerUserModel.getId()));
            request.setFintegeralruletype(3);

            for (Map<String, String> map : userInfos) {
                if (strings[i][0].equals(map.get("customerPhone"))) {
                    request.setFuserid(map.get("customerId"));
                    request.setFkeyarea(Integer.parseInt(map.get("customerKeyarea")));
                }
            }
            switch (strings[i][2]) {
                case "社区活动积分":
                    request.setFintegeraljournaltype(5);
                    break;
                case "商城活动积分":
                    request.setFintegeraljournaltype(6);
                    break;
                case "包辅活动积分":
                    request.setFintegeraljournaltype(7);
                    break;
                case "会员返利积分":
                    request.setFintegeraljournaltype(8);
                    break;
                case "专享返利积分":
                    request.setFintegeraljournaltype(9);
                    break;
                case "其他返利积分":
                    request.setFintegeraljournaltype(10);
                    break;
            }

            request.setFintegeral(BigInteger.valueOf(Math.round(Double.parseDouble(strings[i][3]))));
            request.setFremark(strings[i][4]);
            list.add(request);
        }

        String json = GsonUtils.toJson(list);
        HTTPResponse httpResponse = integralServer.updateUserIntegral(json);
        if (httpResponse != null) {
            if (httpResponse.isSuccessful()) {
                String body = httpResponse.getBodyString();
                logger.debug("updateUserIntegral from remote result is {}", body);
                JsonObject jsonObject = GsonUtils.getJsonObject(body);
                return jsonObject.has("success") && jsonObject.get("success").getAsBoolean();
            }
        }
        return false;
    }

    public Map<String, Object> validateUserInfo(List<IntegralUploadTips> tipsList) {
        Set<String> phones = new TreeSet<>();
        //List<String> phonesTips = new ArrayList<>();

        //List<String> names = new ArrayList<>();
        //List<String> namesTips = new ArrayList<>();
        for (IntegralUploadTips integralUploadTips : tipsList) {
//            for (Map.Entry<String, String> entry : phone.entrySet()) {
//                phones.add(entry.getKey());
//                phonesTips.add(entry.getValue());
//            }
            if (integralUploadTips.getPhone() != null) {
                phones.add(integralUploadTips.getPhone());
            }
            //phonesTips.add(integralUploadTips.getPhoneTip());
            //names.add(integralUploadTips.getName());
            //namesTips.add(integralUploadTips.getNameTip());
        }

//        List<String> names = new ArrayList<>();
//        List<String> namesTips = new ArrayList<>();
//        for (Map<String, String> name : userNames) {
//            for (Map.Entry<String, String> entry : name.entrySet()) {
//                names.add(entry.getKey());
//                namesTips.add(entry.getValue());
//            }
//        }

        Map<String, Set<String>> params = new HashMap<>();
        params.put("phones", phones);

        logger.debug("getOutUserInfoByPhone params : {}", params);
        String json = GsonUtils.toJson(params);
        HTTPResponse httpResponse = outUserServer.getOutUserInfoByPhone(json);
        if (httpResponse != null) {
            if (httpResponse.isSuccessful()) {
                String bodyStr = httpResponse.getBodyString();
                logger.debug("getOutUserInfoByPhone result : {}", bodyStr);
                JsonObject jsonObject = GsonUtils.getJsonObject(bodyStr);
                if (jsonObject.has("success") && jsonObject.get("success").getAsBoolean()) {
                    if (jsonObject.has("data")) {
                        JsonArray jsonArray = jsonObject.get("data").getAsJsonArray();
                        int length = jsonArray.size();

                        JsonObject object;
                        String customerPhone, customerName, customerAccount, customerId, customerKeyarea;

                        Map<String, Object> result = new HashMap<>();

                        List<Map<String, String>> userInfos = new ArrayList<>();
                        Map<String, String> userInfo;

                        //int phonesLength = phones.size();

                        for (IntegralUploadTips uploadTips : tipsList) {
                            //for (int j = 0; j < phonesLength; j++) {

                            for (int i = 0; i < length; i++) {
                                object = jsonArray.get(i).getAsJsonObject();
                                //用户ID
                                customerId = object.get("customerId").getAsString();

                                userInfo = new HashMap<>();
                                userInfo.put("customerId", customerId);
                                //用户手机号码
                                customerPhone = object.get("customerPhone").getAsString();
                                userInfo.put("customerPhone", customerPhone);
                                //用户区域拆分键
                                customerKeyarea = object.get("customerKeyarea").getAsString();
                                userInfo.put("customerKeyarea", customerKeyarea);

                                userInfos.add(userInfo);

                                //认证名称
                                customerName = !object.get("customerName").isJsonNull() ? object.get("customerName").getAsString() : "";
                                //账号名称
                                customerAccount = !object.get("customerAccount").isJsonNull() ? object.get("customerAccount").getAsString() : "";

                                if (uploadTips.getPhone() != null && uploadTips.getPhone().equals(customerPhone)) {
                                    uploadTips.setPhoneTip(null);
                                    //phonesTips.set(j, "");
                                    //for (int a = j; a < phonesLength; a++) {
                                    String name = uploadTips.getName();
//                                    if ((("/".equals(names.get(j)) || "".equals(names.get(j)) || names.get(j) == null) && "".equals(customerName))
//                                            || (names.get(j) != null && names.get(j).equals(customerName))) {
//                                        namesTips.set(j, "");
//                                        break;
//                                    } else if (names.get(j) != null && names.get(j).equals(customerAccount)) {
//                                        namesTips.set(j, "");
//                                        break;
//                                    }
                                    if ((("/".equals(name) || "".equals(name) || name == null) && "".equals(customerName))
                                            || (name != null && name.equals(customerName)) || (name == null && ("".equals(customerName) || null == customerName))) {
                                        //namesTips.set(j, "");
                                        uploadTips.setNameTip(null);
                                        break;
                                    } else if (name != null && name.equals(customerAccount)) {
                                        //namesTips.set(j, "");
                                        uploadTips.setNameTip(null);
                                        break;
                                    }
                                    //}
                                    break;
                                }

                            }
                        }

                        result.put("userinfos", userInfos);

                        List<UserIntegralChangeTips> phonesTips = new ArrayList<>();
                        List<UserIntegralChangeTips> namesTips = new ArrayList<>();
                        for (IntegralUploadTips integralUploadTips : tipsList) {
                            if (integralUploadTips.getPhoneTip() != null)
                                phonesTips.add(integralUploadTips.getPhoneTip());

                            if (integralUploadTips.getNameTip() != null) {
                                namesTips.add(integralUploadTips.getNameTip());
                            }
                        }

//                        List<String> phonesTipsCopy = new ArrayList<>(phonesTips);
//                        for (String phoneTip : phonesTipsCopy) {
//                            if ("".equals(phoneTip)) {
//                                phonesTips.remove(phoneTip);
//                            }
//                        }
//                        List<String> namesTipsCopy = new ArrayList<>(namesTips);
//                        for (String nameTip : namesTipsCopy) {
//                            if ("".equals(nameTip)) {
//                                namesTips.remove(nameTip);
//                            }
//                        }

                        List<UserIntegralChangeTips> errors = new ArrayList<>();

                        errors.addAll(phonesTips);
                        errors.addAll(namesTips);

                        Collections.sort(errors);
                        result.put("errors", errors);
                        logger.debug("validateUserInfo result: {}", result);

                        return result;

                    }
                }
            }
        }
        return null;
    }

    /**
     * 获取 营销方案 产品 列表
     *
     * @param partnerId
     * @param fkeyarea
     * @param current
     * @param size      @return
     */
    public Map<String, Object> getProductList(String partnerId, String fkeyarea, int current, int size) {
        Map<String, Object> map = new HashMap<>();
        map.put("version", "1.0");
        map.put("fkeyarea", fkeyarea);
        map.put("fsupplierid", partnerId);
        map.put("pagenum", (current - 1));
        map.put("pagesize", size);
        String json = GsonUtils.toJson(map);
        logger.debug("getProductList params : {}", json);

        HTTPResponse httpResponse = productServer.getProductListByPartnerId(json);
        if (httpResponse != null) {
            if (httpResponse.isSuccessful()) {
                String bodyStr = httpResponse.getBodyString();
                logger.debug("getProductList result : {}", bodyStr);
                JsonObject jsonObject = GsonUtils.getJsonObject(bodyStr);
                if (jsonObject.has("success") && jsonObject.get("success").getAsBoolean()) {
                    int total = jsonObject.has("total") ? jsonObject.get("total").getAsInt() : 0;
                    if (jsonObject.has("data")) {
                        JsonArray jsonArray = jsonObject.get("data").getAsJsonArray();

                        List<MarkingPlanProduct> list = GsonUtils.toListBean(jsonArray, MarkingPlanProduct[].class);
                        List<Map<String, Object>> productIds = new ArrayList<>();
                        Map<String, Object> stringObjectMap;
                        List<String> fcommodityids;
                        for (MarkingPlanProduct markingPlanProduct : list) {
                            stringObjectMap = new HashMap<>();

                            int partnerCode = 0;
                            String partnerArea = "";
                            List<Integer> oaids = new ArrayList<>();
                            if (StringUtils.isNumeric(markingPlanProduct.getFsupplierid())) {
                                oaids.add(Integer.parseInt(markingPlanProduct.getFsupplierid()));
                            }
                            List<Partners> partnersList = partnersDao.findPartnersByIds(oaids);
                            if (partnersList != null && !partnersList.isEmpty()) {
                                Partners partners = partnersList.get(0);
                                partnerCode = partners.getOcode();
                                stringObjectMap.put("fkeyarea", partners.getOcode());
                                partnerArea = partners.getOprovince() + "-" + partners.getOcity();
                            } else {
                                partnerCode = markingPlanProduct.getFgroupareaid();
                                stringObjectMap.put("fkeyarea", markingPlanProduct.getFgroupareaid());
                            }

                            fcommodityids = new ArrayList<>();
                            fcommodityids.add(markingPlanProduct.getFid());
                            stringObjectMap.put("fcommodityids", fcommodityids);
                            productIds.add(stringObjectMap);
                            if (markingPlanProduct.getFsupplierarea() == null) {
                                markingPlanProduct.setFsupplierarea(partnerArea);
                            }
                            if (markingPlanProduct.getFsupplierareaid() == 0) {
                                markingPlanProduct.setFsupplierareaid(partnerCode);
                            }
                            if (markingPlanProduct.getCellPayment() == null) {
                                markingPlanProduct.setCellPayment(BigDecimal.ZERO);
                            }
                            if (markingPlanProduct.getCellIntegral() == null) {
                                markingPlanProduct.setCellIntegral(BigInteger.ZERO);
                            }
                        }
                        getIntegralRuleType2(productIds, list);
                        Map<String, Object> resultMap = new HashMap<>();
                        resultMap.put("total", total);
                        resultMap.put("list", list);
                        return resultMap;
                    }
                }

            }
        }
        return null;
    }

    //根据产品ID批量获取购买送的积分策略
    private void getIntegralRuleType2(List<Map<String, Object>> productIds, List<MarkingPlanProduct> list) {
        String json = GsonUtils.toJson(productIds);
        logger.debug("getIntegralRuleType2 params: {}", json);
        HTTPResponse httpResponse = integralServer.queryByFcommodityids(json);
        String body = httpResponse.getBodyString();
        JsonObject jsonObject = GsonUtils.getJsonObject(body);
        if (jsonObject.has("success") && jsonObject.get("success").getAsBoolean()) {
            if (jsonObject.has("data")) {
                JsonArray jsonArray = jsonObject.get("data").getAsJsonArray();
                int size = jsonArray.size();
                for (MarkingPlanProduct markingPlanProduct : list) {
                    for (int a = 0; a < size; a++) {
                        JsonObject object = jsonArray.get(a).getAsJsonObject();
                        String id = object.get("fcommodityid").getAsString();
                        if (markingPlanProduct.getFid().equals(id)) {
                            BigInteger fintegral = object.get("fintegral").getAsBigInteger();
                            markingPlanProduct.setCellIntegral(fintegral);
                            BigDecimal fpaymentsL1 = object.get("fpaymentsl1").getAsBigDecimal();
                            markingPlanProduct.setCellPayment(fpaymentsL1);
                            markingPlanProduct.setIntegralRuleId(object.get("fid").getAsString());
                        }
                    }
                }
            }
        }
    }

    @Resource(name = "userJedisPool")
    private JedisPool userJedisPool;

    public Map<String, Object> listRechargeRules() {
        String json = "{}";
        logger.debug("listRechargeRules params: {}", json);
        HTTPResponse httpResponse = integralServer.listRechargeRules(json);
        String body = httpResponse.getBodyString();
        logger.debug("listRechargeRules result: {}", body);
        JsonObject jsonObject = GsonUtils.getJsonObject(body);

        Map<String, Object> result = new HashMap<>();
        if (jsonObject.has("success") && jsonObject.get("success").getAsBoolean()) {
            if (jsonObject.has("data")) {
                JsonElement data = jsonObject.get("data");
                if (data != null && data.isJsonArray()) {
                    JsonArray jsonArray = data.getAsJsonArray();
                    int size = jsonArray.size();
                    List<JsonObject> intervalList = new ArrayList<>();
                    JsonObject ratioObject = null;
                    try (Jedis jedis = userJedisPool.getResource()) {
                        for (int i = 0; i < size; i++) {
                            JsonObject object = jsonArray.get(i).getAsJsonObject();
                            if (object.get("fpaymentsl2").getAsLong() > 0L) {
                                intervalList.add(object);
                            } else {
                                ratioObject = object;
                            }
                            if (object.has("fcreator")) {
                                InnerUserModel creator;
                                String userinfo = jedis.get("userInfo" + object.get("fcreator"));
                                if (userinfo != null) {
                                    creator = GsonUtils.fromJson(userinfo, InnerUserModel.class);
                                    object.addProperty("fcreator", creator.getUname());
                                } else {
                                    object.addProperty("fcreator", "-");
                                }

                            }
                        }
                    }

                    result.put("ratio", ratioObject);
                    result.put("interval", intervalList);
                }
            }
        }
        return result;
    }
}
