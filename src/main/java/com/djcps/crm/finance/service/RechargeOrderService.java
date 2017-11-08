package com.djcps.crm.finance.service;

import com.alibaba.fastjson.JSONObject;
import com.djcps.crm.commons.msg.MsgTemplate;
import com.djcps.crm.commons.utils.ReturnInfoUtil;
import com.djcps.crm.commons.utils.StringUtils;
import com.djcps.crm.finance.constant.MqConstant;
import com.djcps.crm.finance.controller.RechargeOrderController;
import com.djcps.crm.finance.enums.RechargeOrderEnum;
import com.djcps.crm.finance.exception.RechargeOrderException;
import com.djcps.crm.finance.model.AuditModel;
import com.djcps.crm.finance.model.ChangeUserAccountModel;
import com.djcps.crm.finance.model.UserBalanceModel;
import com.djcps.crm.finance.mq.Producer;
import com.djcps.crm.finance.server.NumberUserServer;
import com.djcps.crm.finance.server.OutterUserServer;
import com.djcps.crm.finance.server.RechargeOrderServer;
import com.google.gson.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rpc.plugin.http.HTTPResponse;

import java.text.SimpleDateFormat;
import java.util.*;


/**
 * Created by jmb on 2017/8/22.
 */
@Service("rechargeOrderService")
public class RechargeOrderService {
    private static final Logger logger = LoggerFactory.getLogger(RechargeOrderService.class);
    @Autowired
    private RechargeOrderServer rechargeOrderServer;
    @Autowired
    private OutterUserServer outterUserServer;
    @Autowired
    private NumberUserServer numberUserServer;
    @Autowired
    private Producer producer;
    private Gson gson = new Gson();
    private JsonParser jsonParser = new JsonParser();

    /**
     * 充值订单-列表
     *
     * @param json
     * @return
     */
    public String list(String json) {
        HTTPResponse response = rechargeOrderServer.list(json);
        if (response.isSuccessful()) {
            String jsonStr = packed(response.getBodyString());
            return jsonStr;
        }
        return "";
    }


    /**
     * 充值订单-审核
     *
     * @param json
     * @return
     */
    public String audit(String json) throws RechargeOrderException {
        ChangeUserAccountModel changeUserAccountModel = gson.fromJson(jsonParser.parse(json), ChangeUserAccountModel.class);
        //做下余额要的修改余额类型参数转换
        switch (changeUserAccountModel.getFtype()) {
            case 1:
                changeUserAccountModel.setFtype(8);
                break;
            case 2:
                changeUserAccountModel.setFtype(9);
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("version", "1.0");
                jsonObject.put("userId", changeUserAccountModel.getUserId());
                jsonObject.put("keyArea", changeUserAccountModel.getKeyArea());
                String responseBalance = getBalance(jsonObject.toJSONString());
                if ("".equals(responseBalance)) {
                    return MsgTemplate.failureMsg(RechargeOrderEnum.BALANCE_ERROR).toString();
                }
                UserBalanceModel userAccountModel = ReturnInfoUtil.returnObject(responseBalance, UserBalanceModel.class);
                if (userAccountModel == null || Double.parseDouble(userAccountModel.getFamount()) < (Double.parseDouble(changeUserAccountModel.getAmount())<0?-1*Double.parseDouble(changeUserAccountModel.getAmount()):Double.parseDouble(changeUserAccountModel.getAmount()))){
                    return MsgTemplate.failureMsg(RechargeOrderEnum.BALANCE_TOO_BIG).toString();
                }
                break;
            default:
                changeUserAccountModel.setFtype(0);
                break;
        }
        //调余额服务审核充值订单
        AuditModel auditModel = gson.fromJson(jsonParser.parse(json).getAsJsonObject(), AuditModel.class);
        long time = new Date().getTime();
        //前端传过来的时间有可能是错的，就不让前端传了
        // 审核时间就在crm端取当前时间。
        auditModel.setAudittime(StringUtils.formatDateStr(time));
        HTTPResponse auditresponse = rechargeOrderServer.audit(gson.toJson(auditModel));
        if (auditresponse.isSuccessful()) {
            //如果是通过才通知余额修改余额，如果是驳回就不修改余额了
            //通过是：1 驳回是：2
            if (auditModel.getAuditType() == 1) {
                //数据库写成功后发送异步消息，通知余额服务那边做用户余额的变更
                if (auditresponse.getBodyString().contains("true")) {
                    try {
                        changeUserAccountModel.setFremark(org.springframework.util.StringUtils.isEmpty(auditModel.getAuditExplain())?"":auditModel.getAuditExplain());
                        producer.sendMsg(MqConstant.USERRESTACCOUNT, MqConstant.USER_RESTACCOUNT_MQ_KEY, gson.toJson(changeUserAccountModel));
                        logger.debug(gson.toJson(changeUserAccountModel));
                    } catch (Exception e) {
                        e.printStackTrace();
                        logger.error("发送变更用户余额异步消息失败" + e.getCause());
                        throw new RechargeOrderException("发送异步消息失败");
                    }
                }
            }
            return auditresponse.getBodyString();
        }
        return "";
    }

    /**
     * 充值订单—审核列表
     */
    public String auditList(String json) {

        HTTPResponse response = rechargeOrderServer.auditList(json);
        if (response.isSuccessful()) {
            String jsonStr = packed(response.getBodyString());
            return jsonStr;
        }
        return "";
    }

    /**
     * 根据模糊查询用户手机号获取用户基本信息列表
     *
     * @param json
     * @return
     */
    public String selectPhones(String json) {
        HTTPResponse response = outterUserServer.selectPhones(json);
        if (response.isSuccessful()) {
            return response.getBodyString();
        }
        return "";
    }

    /**
     * 根据用户认证名模糊查询用户信息列表
     *
     * @param json
     * @return
     */
    public String selBaseUserByName(String json) {
        HTTPResponse httpResponse = outterUserServer.selBaseUserByName(json);
        if (httpResponse.isSuccessful()) {
            return httpResponse.getBodyString();
        }
        return "";
    }

    /**
     * 充值/扣款订单-保存
     *
     * @param json
     * @return
     */
    public String saveFinanceOrder(String json) {

        HTTPResponse response = rechargeOrderServer.saveFinanceOrder(json);
        if (response.isSuccessful()) {
            return response.getBodyString();
        }
        return "";
    }


    /**
     * 获取订单随机号
     *
     * @param json
     * @return
     */
    public String getOrderId(String json) {
        HTTPResponse response = numberUserServer.getOrderId(json);
        if (response.isSuccessful()) {
            return response.getBodyString();
        }
        return "";
    }

    /**
     * 获取订单编号
     *
     * @param json
     * @return
     */
    public String saveOrderInfo(String json) throws RechargeOrderException {
        HTTPResponse response = numberUserServer.getOrderId(json);
        if (response.isSuccessful()) {
            String numberInfo = response.getBodyString();
            JsonElement data = jsonParser.parse(numberInfo).getAsJsonObject().get("data");
            String orderId = null;
            if (!data.toString().equals("null") && data != null) {
                JsonElement numbers = data.getAsJsonObject().get("numbers");
                JsonArray asJsonArray = numbers.getAsJsonArray();
                String number = asJsonArray.get(0).getAsString();
                String dateStr = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
                orderId = "CF" + dateStr + number;
            }
            return orderId;
        }

        return "";
    }


    /**
     * 包装返回数据
     *
     * @param json
     * @return
     */
    private String packed(String json) {
        JsonElement parse = jsonParser.parse(json);
        JsonElement data = parse.getAsJsonObject().get("data").getAsJsonObject().get("list");
        if (data.toString().equals("[]") || data.toString().equals(null)) {
            return json;
        }
        JsonArray jsonArray = data.getAsJsonArray();
        List<String> phones = new ArrayList<>();
        //制作一个手机号集合的json字符串，用于查用户信息
        for (int i = 0; i < jsonArray.size(); i++) {
            if (phones.toString().contains(jsonArray.get(i).getAsJsonObject().get("fphone").getAsString())) {
                continue;
            }
            phones.add(jsonArray.get(i).getAsJsonObject().get("fphone").getAsString());
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("phones", phones);
        //根据手机号集合的json字符串查询用户信息
        String userBaseInfo = getUserByPhones(jsonObject.toJSONString());
        //把用户信息装到一个map中，方便给返回数据添加认证名的时候取值
        JsonElement data1 = jsonParser.parse(userBaseInfo).getAsJsonObject().get("data");
        Map<String, JsonElement> map = new HashMap<>();
        //因为返回数据中的data会出现单条的对象格式和多条的数组格式
        //判断用户信息可能为空
        if (!data1.toString().equals("null") && data1 != null) {
            JsonArray userInfos = data1.getAsJsonArray();
            for (int a = 0; a < userInfos.size(); a++) {
                map.put(userInfos.get(a).getAsJsonObject().get("customerPhone").toString(), userInfos.get(a));
            }
        }
        //这里是把用户认证名添加到返回数据中
        for (int j = 0; j < jsonArray.size(); j++) {
            JsonObject jsonObject1 = jsonArray.get(j).getAsJsonObject();
            String fphone = jsonObject1.get("fphone").toString();
            if (map.get(fphone) != null) {
                JsonObject asJsonObject = map.get(fphone).getAsJsonObject();
                if (!asJsonObject.get("customerName").toString().equals("null")) {
                    jsonObject1.addProperty("customerName", asJsonObject.get("customerName").getAsString());
                } else {
                    jsonObject1.addProperty("customerName", "");
                }
            } else {
                jsonObject1.addProperty("customerName", "");
            }
        }
        String returnJsonStr = parse.getAsJsonObject().toString();
        return returnJsonStr;
    }

    /**
     * 根据手机号获取用户基础信息（批量）
     *
     * @param json
     * @return
     */
    private String getUserByPhones(String json) {
        HTTPResponse response = outterUserServer.getUserByPhones(json);
        if (response.isSuccessful()) {
            return response.getBodyString();
        }
        return "";

    }

    /**
     * 查询用户余额
     *
     * @param json
     * @return
     */
    public String getBalance(String json) {
        HTTPResponse response = rechargeOrderServer.getBalance(json);
        if (response.isSuccessful()) {
            return response.getBodyString();
        }
        return "";
    }
}
