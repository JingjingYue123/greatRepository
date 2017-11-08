package com.djcps.crm.customerService.service;

import com.alibaba.fastjson.JSONObject;
import com.djcps.crm.commons.msg.MsgTemplate;
import com.djcps.crm.commons.utils.BeanMapper;
import com.djcps.crm.customerService.enums.AbnormalOrderEunm;
import com.djcps.crm.customerService.exception.CustomerException;
import com.djcps.crm.customerService.model.*;
import com.djcps.crm.customerService.mq.Producer;
import com.djcps.crm.customerService.server.AbnormalOrderServer;
import com.djcps.crm.customerService.server.NumberUserServer;
import com.djcps.crm.customerService.server.OrderUserServer;
import com.djcps.crm.customerService.constant.MqConstant;
import com.djcps.crm.customerService.server.OutterUserServer;

import com.djcps.crm.finance.exception.ReportFormsException;
import com.djcps.crm.finance.service.ReportFormsService;
import com.google.gson.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import rpc.plugin.http.HTTPResponse;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service("abnormalOrderService")
public class AbnormalOrderService {
    @Autowired
    private AbnormalOrderServer abnormalOrderServer;
    @Autowired
    private OrderUserServer orderUserServer;
    @Autowired
    private NumberUserServer numberUserServer;
    @Autowired
    private Producer customerProducer;

    @Autowired
    private OutterUserServer outterUserServer;

    private Gson gson = new Gson();
    private JsonParser jsonParser = new JsonParser();

    private static final Logger balancelogger =  LoggerFactory.getLogger(ReportFormsService.class);

    private static final Logger logger= LoggerFactory.getLogger(AbnormalOrderService.class);
    /**
     * 异常订单--创建
     *
     * @param json
     * @return
     */
    public String saveAbnormalOrder(String json) {
        HTTPResponse response = abnormalOrderServer.saveAbnormal(json);
        if (response.isSuccessful()) {
            return response.getBodyString();
        }
        return "";
    }
    /**
     * 根据用户id获取订单信息
     *
     * @param json
     * @return
     */
    public String getPhonesByUserId(String json) {
        HTTPResponse response = orderUserServer.getPhonesByUserId(json);
        if (response.isSuccessful()) {
            return response.getBodyString();
        }
        return "";
    }

    /**
     * 异常订单——关闭订单
     *
     * @param json
     * @return
     */
    public String updateAbnormalOrderStatus(String json) {
        AbnormalOrderModel model = gson.fromJson(jsonParser.parse(json).getAsJsonObject(), AbnormalOrderModel.class);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("processMode", 3);
        jsonObject.put("closeProcessModel", model);
        HTTPResponse response = abnormalOrderServer.updateAbnormalOrderStatus(jsonObject.toJSONString());
        if (response.isSuccessful()) {
            return response.getBodyString();
        }
        return "";
    }

    /**
     * 搜索客户认证名
     * @param userIids
     * @return
     * @throws ReportFormsException
     */
    private Map<String, JsonElement> selCustomerName(List<String> userIids) throws CustomerException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("userIds", userIids);
        HTTPResponse userByPhones = outterUserServer.getUserBaseInfo(jsonObject.toJSONString());
        if (!userByPhones.isSuccessful()) {
            throw new CustomerException("GET_CUSTOMER_ERROR");
        }
        String userBaseInfo = userByPhones.getBodyString();
        JsonElement data1 = jsonParser.parse(userBaseInfo).getAsJsonObject().get("data");
        Map<String, JsonElement> map = new HashMap<>();
        //判断用户信息可能为空
        //因为返回数据可能会是数组和单个对象  这里用异常做了判断
        if (!data1.toString().equals("null") && data1 != null) {
            try {
                JsonArray userInfos = data1.getAsJsonArray();
                for (int a = 0; a < userInfos.size(); a++) {
                    map.put(userInfos.get(a).getAsJsonObject().get("fuserId").getAsString(), userInfos.get(a));
                }
            } catch (Exception e) {
                e.printStackTrace();
                map.put(data1.getAsJsonObject().get("fuserId").getAsString(), data1);
            }
        }
        return map;
    }
    /**
     * 查询异常订单列表
     * @param
     * @return
     */
    public Map<String, Object> selectAbnormalOrderList(String json) {
        Map<String, Object> map = null;
        try {
            map = abnormalOrderList(json);
            if (map == null) {
                return MsgTemplate.failureMsg(AbnormalOrderEunm.OPS_FAILURE);
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("获取异常订单失败" + e.getCause());
            return MsgTemplate.failureMsg(AbnormalOrderEunm.OPS_FAILURE);
        }
        JSONObject returnData = new JSONObject();
        returnData.put("total", map.get("total"));
        returnData.put("list", map.get("list"));
        return MsgTemplate.successMsg(returnData);
    }


    /**
     * 异常订单--获取订单列表
     *
     * @param json
     * @return
     */
    public Map<String, Object> abnormalOrderList(String json) throws CustomerException {

        ListModel model = gson.fromJson(jsonParser.parse(json).getAsJsonObject(), ListModel.class);
        List<String> userIds = null;
        if (!StringUtils.isEmpty(model.getCertificateName()) || !StringUtils.isEmpty(model.getPhone())) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("certificateName", model.getCertificateName());
            jsonObject.put("phone", model.getPhone());
            HTTPResponse response = outterUserServer.selectPhones(jsonObject.toJSONString());
            if (!response.isSuccessful()) {
                logger.error("调用外部用户服务失败");
                throw new CustomerException("调用外部用户服务失败");
            }
            ReturnInfoModel returnInfoModel = gson.fromJson(jsonParser.parse(response.getBodyString()), ReturnInfoModel.class);
            if (returnInfoModel.getData() == null || returnInfoModel.getData().getResult().size() == 0) {
                throw new CustomerException("未找到用户信息");
            }
            userIds = BeanMapper.getFieldList(returnInfoModel.getData().getResult(), "fuserId", String.class);
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("userIds", userIds);
        jsonObject.put("version", model.getVersion());
        jsonObject.put("pageSize", model.getPageSize());
        jsonObject.put("pageNo", model.getPageNo());
        HTTPResponse httpResponse = abnormalOrderServer.selectAbnormalOrderList(jsonObject.toJSONString());
        if (!httpResponse.isSuccessful()) {
            logger.error("调用订单服务失败");
            throw new CustomerException("调用订单服务失败");
        }
        ReturnInfoModel returnInfoModel = gson.fromJson(jsonParser.parse(httpResponse.getBodyString()), ReturnInfoModel.class);
        if (returnInfoModel.getData().getList().size() == 0) {
            throw new CustomerException("未找到用户信息");
        }
        List<AbnormalOrderModel> list = returnInfoModel.getData().getList();
        Integer total = returnInfoModel.getData().getTotal();

        //以下是添加客户认证名的操作
        Map<String, JsonElement> customerName = null;
        try {
            customerName = selCustomerName(BeanMapper.getFieldList(list, "fuserid", String.class));
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("获取客户认证名失败");
            throw new CustomerException("GET_CUSTOMER_ERROR");
//            return MsgTemplate.failureMsg(ReportFormsEnum.FALSE);
        }
        if (customerName.size() > 0) {
            for (AbnormalOrderModel abnormalOrderModel : list) {
                String userId = abnormalOrderModel.getFuserid();
                //往数据中添加认证名
                if (customerName.get(userId) != null) {
                    abnormalOrderModel.setCertificateName(customerName.get(userId).getAsJsonObject().get("fcustName").getAsString());

                }

            }

        }
        Map<String, Object> map = new HashMap<>();
        map.put("total", total);
        map.put("list", list);
        return map;
    }
    /**
     * 获得随机号--新建客诉流水号
     *
     * @param json
     * @return
     */
    public String getCommenNumber(String json) {
        HTTPResponse response = numberUserServer.getCommenNumber(json);
        if (response.isSuccessful()) {
            return response.getBodyString();
        }
        return "";
    }

    /**
     * 异常订单--客服更新
     * @param json
     * @return
     */
    public String customerUpdateAbnormalOrder(String json) {
        AbnormalOrderModel model = gson.fromJson(jsonParser.parse(json).getAsJsonObject(), AbnormalOrderModel.class);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("processMode", 1);
        jsonObject.put("customerProcessModel", model);
        HTTPResponse response = abnormalOrderServer.customerUpdateAbnormalOrder(jsonObject.toJSONString());
        if (response.isSuccessful()) {
            return response.getBodyString();
        }
        return "";
    }
    /**
     * 异常订单--财务更新
     * @param json
     * @return
     */
    public String financeUpdateAbnormalOrder(String json) {
        if (jsonParser.parse(json).getAsJsonObject().get("ffinhandlingresult").getAsInt() == 1) {
            AbnormalOrderModel model = gson.fromJson(jsonParser.parse(json).getAsJsonObject(), AbnormalOrderModel.class);
            ChangeBalanceModel changeBalanceModel = new ChangeBalanceModel();
            changeBalanceModel.setAmount(jsonParser.parse(json).getAsJsonObject().get("frefundamount").getAsString());
            String userid = jsonParser.parse(json).getAsJsonObject().get("fuserid").getAsString();
            changeBalanceModel.setUserId(userid);
            changeBalanceModel.setFisSend(true);
            changeBalanceModel.setForderId(jsonParser.parse(json).getAsJsonObject().get("forderid").getAsString());
            changeBalanceModel.setFtype(6);
            changeBalanceModel.setKeyArea(jsonParser.parse(json).getAsJsonObject().get("fkeyarea").getAsString());
            changeBalanceModel.setVersion(jsonParser.parse(json).getAsJsonObject().get("version").getAsString());
            try {
                changeBalanceModel.setFremark(StringUtils.isEmpty(model.getFcscommrecord())?"":model.getFcscommrecord());
                customerProducer.sendMsg(MqConstant.USERRESTACCOUNT, MqConstant.USER_RESTACCOUNT_MQ_KEY, gson.toJson(changeBalanceModel));
                balancelogger.debug(gson.toJson(changeBalanceModel));
            } catch (Exception e) {
                e.printStackTrace();
                balancelogger.error("发送异步消息失败" + e.getCause());
            }

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("processMode", 2);
            jsonObject.put("financeProcessModel", model);

            HTTPResponse response = abnormalOrderServer.financeUpdateAbnormalOrder(jsonObject.toJSONString());
            if (response.isSuccessful()) {
                return response.getBodyString();
            }
            return "";

        } else {
            AbnormalOrderModel model = gson.fromJson(jsonParser.parse(json).getAsJsonObject(), AbnormalOrderModel.class);

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("processMode", 2);
            jsonObject.put("financeProcessModel", model);
            HTTPResponse response = abnormalOrderServer.financeUpdateAbnormalOrder(jsonObject.toJSONString());
            if (response.isSuccessful()) {
                return response.getBodyString();
            }
            return "";
        }

    }



    public String getOrderByOrderId(OrderRequestModel model) {
        HTTPResponse httpResponse = orderUserServer.getOrderByid(JSONObject.toJSONString(model));
        if (httpResponse.isSuccessful()) {
            return httpResponse.getBodyString();
        }
        return "";

    }

    /**
     * 通过用户id查询订单编号
     *
     * @param json
     * @return
     */
    public String selectOrderIdByUserId(String json) {
        HTTPResponse response = orderUserServer.selectOrderIdByUserId(json);
        if (response.isSuccessful()) {
            return response.getBodyString();
        }
        return "";
    }


    public String selectUserIdByPhone(String json) {
        HTTPResponse response = outterUserServer.selectPhones(json);
        if (response.isSuccessful()) {
            return response.getBodyString();
        }
        return "";
    }



}
