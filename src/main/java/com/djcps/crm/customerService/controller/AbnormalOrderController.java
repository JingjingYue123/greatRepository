package com.djcps.crm.customerService.controller;


import com.alibaba.fastjson.JSONObject;
import com.baidu.unbiz.fluentvalidator.ComplexResult;
import com.baidu.unbiz.fluentvalidator.FluentValidator;
import com.djcps.crm.aop.log.AddLog;
import com.djcps.crm.commons.msg.MsgTemplate;
import com.djcps.crm.commons.utils.BeanMapper;
import com.djcps.crm.commons.utils.CheckUtils;

import com.djcps.crm.commons.utils.HibernateValidator;
import com.djcps.crm.customerService.enums.AbnormalOrderEunm;
import com.djcps.crm.customerService.enums.CustomerServiceEnum;
import com.djcps.crm.customerService.exception.CustomerException;
import com.djcps.crm.customerService.model.*;

import com.djcps.crm.customerService.server.AbnormalOrderServer;
import com.djcps.crm.customerService.server.OrderUserServer;
import com.djcps.crm.customerService.server.OutterUserServer;
import com.djcps.crm.customerService.service.AbnormalOrderService;
import com.google.gson.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import rpc.plugin.http.HTTPResponse;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import static com.baidu.unbiz.fluentvalidator.ResultCollectors.toComplex;
import static com.djcps.crm.commons.msg.MsgTemplate.failureMsg;
import static com.djcps.crm.commons.msg.MsgTemplate.successMsg;

import static com.djcps.crm.customerService.enums.AbnormalOrderEunm.*;
import static com.djcps.crm.finance.enums.RechargeOrderEnum.ABSENCE_ERROR;

@RestController
public class AbnormalOrderController {
    private static final Logger logger = LoggerFactory.getLogger(AbnormalOrderController.class);
    private Gson gson = new Gson();
    private JsonParser jsonParser = new JsonParser();
    @Autowired
    private AbnormalOrderService abnormalOrderService;
    @Autowired
    private OrderUserServer orderUserServer;

    @Autowired
    private AbnormalOrderServer abnormalOrderServer;

    @Autowired
    private OutterUserServer outterUserServer;
    @RequestMapping(name = "该接口用于创建异常订单", value = "customerService/saveAbnormalOrder", method = {RequestMethod.POST})
    @AddLog(module = "临时客服",value = "该接口用于创建异常订单")
    public Map<String, Object> saveAbnormalOrder(@RequestBody String json){
        try {
            AbnormalOrderModel model = gson.fromJson(jsonParser.parse(json).getAsJsonObject(), AbnormalOrderModel.class);
            String msg = CheckUtils.parametersEmpty(new Object[]{model.getForderid(), model.getFphone(), model.getFexceptionreason()});
            if (!"".equals(msg)) {
                return failureMsg(ABSENSE_ERROR);
            }
            JsonObject jsonObject = jsonParser.parse(json).getAsJsonObject();
            JsonElement fexceptionreason = jsonParser.parse(json).getAsJsonObject().get("fexceptionreason");
            if (fexceptionreason.toString().length() > 102) {
                return failureMsg(CRESONAMOUNT_ERROR);
            }
            //创建客诉流水号
            //获得随机号并作验证
            String s = abnormalOrderService.getCommenNumber("{\"count" + ":1}");
            if(s.equals("{}")||s.length()==0){
                return failureMsg(GETNUMBER_ERROR);
            }
            String  result = jsonParser.parse(s).getAsJsonObject().get("success").getAsString();
            if(result.equals("false")){
                return failureMsg(GETNUMBER_ERROR);
            }
            JsonElement element = jsonParser.parse(s).getAsJsonObject().get("data");
            JsonElement numbers = element.getAsJsonObject().get("numbers");
            JsonArray asJsonArray = numbers.getAsJsonArray();
            String s1 = asJsonArray.get(0).getAsString();
            jsonObject.addProperty("fcscptjournalid", "cs" + s1);
            String jsonStr = abnormalOrderService.saveAbnormalOrder(jsonObject.toString());
            return successMsg(jsonStr);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("创建异常订单失败");
            return failureMsg(SYS_ERROR);
        }
    }
    @RequestMapping(name = "该接口用于根据用户id获取手机号列表", value = "customerService/selectPhonesByUserId", method = {RequestMethod.POST})
    @AddLog(module = "临时客服",value = "该接口用于根据用户id获取手机号列表")
    public Map<String, Object> getPhonesByUserId(@RequestBody String json) {
        try {
            String jsonStr = abnormalOrderService.getPhonesByUserId(json);
            return successMsg(jsonStr);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("查找订单信息失败");
            return failureMsg(SYS_ERROR);
        }
    }

    @RequestMapping(name = "该接口用于根据手机号获取用户id", value = "customerService/selectUserIdByPhone", method = {RequestMethod.POST})
    @AddLog(module = "临时客服",value = "该接口用于根据手机号获取用户id")
    public Map<String, Object> getUserIdByphone(@RequestBody String json) {
        try {
            AbnormalOrderModel model = gson.fromJson(jsonParser.parse(json).getAsJsonObject(), AbnormalOrderModel.class);
            if (StringUtils.isBlank(model.getFphone())){
                return failureMsg(SELECT_PHONE_ERROR);
            }
            JSONObject jsonObject= new JSONObject();
            jsonObject.put("phone",model.getFphone());
            jsonObject.put("version",model.getVersion());
            String jsonStr = abnormalOrderService.selectUserIdByPhone(jsonObject.toJSONString());
            return successMsg(jsonStr);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("查找用户id失败");
            return failureMsg(SYS_ERROR);
        }
    }

    /**
     * 该接口用于根据用户id查询订单编号
     * @param json
     * @return 返回一个订单信息结合
     */
    @RequestMapping(name = "该接口用于根据用户id查询订单编号", value = "customerService/selectOrderIdByUserId", method = {RequestMethod.POST})
    @AddLog(module = "临时客服",value = "该接口用于根据用户id查询订单编号")
    public Map<String, Object> selectOrderIdByUserId(@RequestBody String json) {
        try {
            AbnormalOrderModel model = gson.fromJson(jsonParser.parse(json).getAsJsonObject(), AbnormalOrderModel.class);
            if (StringUtils.isBlank(model.getFuserid())){
                return failureMsg(USERID_ERROR);
            }
            JSONObject jsonObject= new JSONObject();
            jsonObject.put("userId",model.getFuserid());
            jsonObject.put("keyarea",model.getFkeyarea());
            jsonObject.put("version",model.getVersion());
            String jsonStr = abnormalOrderService.selectOrderIdByUserId(jsonObject.toJSONString());
            return successMsg(jsonStr);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("查询订单编号失败");
            return failureMsg(SYS_ERROR);
        }
    }

    /**
     * 该接口用于关闭异常订单
     * @param json
     * @return
     */
    @RequestMapping(name = "该接口用于关闭异常订单", value = "customerService/updateAbnormalOrderStatus", method = {RequestMethod.POST})
    @AddLog(module = "临时客服",value = "该接口用于关闭异常订单")
    public Map<String, Object> updateAbnormalOrderStatus(@RequestBody String json) {
        try {
            String jsonStr = abnormalOrderService.updateAbnormalOrderStatus(json);
            return successMsg(jsonStr);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("关闭异常订单失败");
            return failureMsg(SYS_ERROR);
        }
    }


    /**
     * 异常订单--获取订单列表
     *
     * @param json
     * @return 返回异常订单列表集合
     */
    @RequestMapping(name = "异常订单--获取订单列表",value = "customerService/selectAbnormalOrderList",method = {RequestMethod.POST})
    @AddLog(module = "临时客服",value = "异常订单--获取订单列表")
    public Map<String,Object> abnormalOrderList(@RequestBody String json)throws CustomerException{
        Map<String ,Object> map =new HashMap<>();
        try {
            ListModel model = gson.fromJson(jsonParser.parse(json).getAsJsonObject(), ListModel.class);
            List<String> userIds=null;
            if (!StringUtils.isEmpty(model.getCertificateName()) || !StringUtils.isEmpty(model.getPhone())){
                JSONObject jsonObject=new JSONObject();
                jsonObject.put("certificateName",model.getCertificateName());
                jsonObject.put("phone",model.getPhone());
                HTTPResponse response = outterUserServer.selectPhones(jsonObject.toJSONString());
                if (!response.isSuccessful()){
                    logger.error("调用外部用户服务失败");
                    return MsgTemplate.failureMsg(CustomerServiceEnum.SEARCH_ERROR);
                }
                ReturnInfoModel returnInfoModel = gson.fromJson(jsonParser.parse(response.getBodyString()), ReturnInfoModel.class);
                if (returnInfoModel.getData()==null||returnInfoModel.getData().getResult().size()==0 ){
                    JSONObject object= new JSONObject();
                    object.put("total",0);
                    object.put("list",new ArrayList<>());
                    return MsgTemplate.successMsg(object);
                }
                userIds = BeanMapper.getFieldList(returnInfoModel.getData().getResult(), "fuserId", String.class);
            }
            JSONObject jsonObject=new JSONObject();
            jsonObject.put("userIds",userIds);
            jsonObject.put("version",model.getVersion());
            jsonObject.put("pageSize",model.getPageSize());
            jsonObject.put("pageNo",model.getPageNo());
            jsonObject.put("startTime",model.getStartTime());
            jsonObject.put("endTime",model.getEndTime());
            jsonObject.put("status",model.getStatus());
            HTTPResponse httpResponse= abnormalOrderServer.selectAbnormalOrderList(jsonObject.toJSONString());
            if (!httpResponse.isSuccessful()){
                logger.error("调用订单服务失败");
                return MsgTemplate.failureMsg(CustomerServiceEnum.REOIRT_FORMS_LIST_ERROR);
            }
            ReturnInfoModel returnInfoModel=gson.fromJson(jsonParser.parse(httpResponse.getBodyString()), ReturnInfoModel.class);
            if (returnInfoModel.getData().getList().size()==0 ){
                logger.error("未找到用户信息");
                return MsgTemplate.successMsg(httpResponse.getBodyString());
            }
            List<AbnormalOrderModel>list=returnInfoModel.getData().getList();
            Integer total=returnInfoModel.getData().getTotal();

            //以下是添加客户认证名的操作
            Map<String, JsonElement> customerName=null;
            try {
                customerName=selCustomerName(BeanMapper.getFieldList(list, "fuserid", String.class));
            }catch (Exception e){
                e.printStackTrace();
                logger.error("获取客户认证名失败");
                return MsgTemplate.failureMsg(CustomerServiceEnum.FALSE);
            }
            if(customerName.size()>0){
                for(AbnormalOrderModel abnormalOrderModel:list){
                    String userId=abnormalOrderModel.getFuserid();
                    //往数据中添加认证名
                    if (customerName.get(userId)!=null){
                        abnormalOrderModel.setCertificateName(customerName.get(userId).getAsJsonObject().get("fcustName").getAsString());

                    }

                }

            }
            map.put("total",total);
            map.put("list",list);
        }catch (Exception e){
            e.printStackTrace();
            logger.error("获取异常订单列表失败");
            return MsgTemplate.failureMsg(CustomerServiceEnum.FALSE);
        }
        return MsgTemplate.successMsg(map);
    }

    /**
     * 该接口用于客服处理异常订单
     * @param json
     * @return
     */
    @RequestMapping(name = "该接口用于客服更新异常订单", value = "customerService/customerUpdateAbnormalOrder", method = {RequestMethod.POST})
    @AddLog(module = "临时客服",value = "该接口用于客服更新异常订单")
    public Map<String, Object> customerUpdateAbnormalOrder(@RequestBody String json) {
        try {
            JsonObject jsonObject = jsonParser.parse(json).getAsJsonObject();
            String fcscommrecord=jsonObject.get("fcscommrecord").getAsString();
            //异常原因输入作非空判断,字数不能超过100字
            if (fcscommrecord == null || fcscommrecord.length() <= 0||fcscommrecord.length() > 102) {
                return failureMsg(CRESONAMOUNT_ERROR);
            }
            //此段代码校验退款片数不能大于下单数量
            if (1==jsonObject.get("fcshandlingtype").getAsInt()){
                JSONObject object=new JSONObject();
                object.put("childOrderId",jsonObject.get("orderid").getAsString());
                object.put("version","1.0");
                object.put("fkeyarea",jsonObject.get("fkeyarea").getAsString());
                HTTPResponse response = orderUserServer.getOrderByid(object.toJSONString());
                if (!response.isSuccessful()){
                    return MsgTemplate.failureMsg(ORDERSERVER_ERROR);
                }
                OrderModel orderModel = gson.fromJson(jsonParser.parse(response.getBodyString()).getAsJsonObject().get("data"), OrderModel.class);
                if (Integer.parseInt(jsonObject.get("fpatchamount").getAsString()) > orderModel.getFamountpiece()){
                    return failureMsg(OUT_OF_RANGE);
                }
            }
            String jsonStr = abnormalOrderService.customerUpdateAbnormalOrder(json);
            return successMsg(jsonStr);

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("客服更新异常订单失败");
            return failureMsg(SYS_ERROR);
        }
    }

    /**
     * 该接口用于财务处理异常订单
     * @param json
     * @return
     */
    @RequestMapping(name ="该接口用于财务更新的异常订单", value = "customerService/financeUpdateAbnormalOrder", method = {RequestMethod.POST})
    @AddLog(module = "临时客服",value = "该接口用于财务更新的异常订单")
    public Map<String, Object> financeUpdateAbnormalOrder(@RequestBody String json){
        try {
            //字段非空判断
            AbnormalOrderModel model = gson.fromJson(jsonParser.parse(json).getAsJsonObject(), AbnormalOrderModel.class );
            String msg= CheckUtils.parametersEmpty(new Object[]{model.getFcscptjournalid(),model.getFfincomments(),model.getFfinhandlingresult(),model.getFfinoperator(),
                    model.getFfinoperatorid(),model.getFrefundamount(),model.getFkeyarea(),model.getVersion(),model.getForderid(),model.getFuserid()});
            if (!"".equals(msg)){
                return failureMsg(ABSENCE_ERROR);
            }
            //沟通记录不能超过100字
            JsonObject jsonObject = jsonParser.parse(json).getAsJsonObject();
            String ffincomments=jsonObject.get("ffincomments").getAsString();
            if (ffincomments.length() > 102){
                return failureMsg(FRESONAMOUNT_ERROR);
            }
            String jsonStr = abnormalOrderService.financeUpdateAbnormalOrder(json);
            return successMsg(jsonStr);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("财务更新异常订单失败");
            return failureMsg(SYS_ERROR);
        }
    }

    /**
     * 该接口用于根据订单号查询订单单价
     * @param json
     * @return
     */
    @RequestMapping(name = "该接口用于根据订单号查询订单单价",value = "customerService/getOrderByOrderId",method = {RequestMethod.POST})
    @AddLog(module = "临时客服",value = "该接口用于根据订单号查询订单单价")
    public Map<String,Object> getOrderByOrderId(@RequestBody String json){
        String jsonStr=null;
        try {
            OrderRequestModel model = gson.fromJson(jsonParser.parse(json), OrderRequestModel.class);
            //校验传入的参数是否都符合要求
            ComplexResult ret = FluentValidator.checkAll().failFast()
                    .on(model, new HibernateValidator<OrderRequestModel>().Validator())
                    .doValidate()
                    .result(toComplex());
            if (ret.isSuccess()) {
                jsonStr = abnormalOrderService.getOrderByOrderId(model);
            }
        }catch (Exception e){
            e.printStackTrace();
            logger.error("根据订单id获取订单失败"+e.getCause());
            return MsgTemplate.failureMsg(AbnormalOrderEunm.OPS_FAILURE);
        }
        return MsgTemplate.successMsg(jsonStr);
    }

    /**
     * 搜索客户认证名
     * @param userIids
     * @return
     * @throws
     */
    private Map<String,JsonElement> selCustomerName(List<String> userIids) throws CustomerException {
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("userIds",userIids);
        HTTPResponse userByPhones = outterUserServer.getUserBaseInfo(jsonObject.toJSONString());
        if (!userByPhones.isSuccessful()){
            throw new CustomerException("GET_CUSTOMER_ERROR");
        }
        String userBaseInfo = userByPhones.getBodyString();
        JsonElement data1 = jsonParser.parse(userBaseInfo).getAsJsonObject().get("data");
        Map<String, JsonElement> map = new HashMap<>();
        //判断用户信息可能为空
        //因为返回数据可能会是数组和单个对象  这里用异常做了判断
        if (!data1.toString().equals("null") && data1 !=null) {
            try {
                JsonArray userInfos = data1.getAsJsonArray();
                for (int a = 0; a < userInfos.size(); a++) {
                    map.put(userInfos.get(a).getAsJsonObject().get("fuserId").getAsString(), userInfos.get(a));
                }
            }catch (Exception e){
                e.printStackTrace();
                map.put(data1.getAsJsonObject().get("fuserId").getAsString(),data1);
            }
        }
        return map;
    }


}