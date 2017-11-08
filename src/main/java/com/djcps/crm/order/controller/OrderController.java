package com.djcps.crm.order.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.djcps.crm.aop.inneruser.annotation.InnerUser;
import com.djcps.crm.aop.log.AddLog;
import com.djcps.crm.commons.msg.MsgTemplate;
import com.djcps.crm.commons.utils.CheckUtils;
import com.djcps.crm.commons.utils.RedisClientCluster;
import com.djcps.crm.inneruser.model.InnerUserModel;
import com.djcps.crm.order.enums.OrderMsgEnum;
import com.djcps.crm.order.model.OrderParamModel;
import com.djcps.crm.order.model.OrderRequestModel;
import com.djcps.crm.order.service.OrderService;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import static com.djcps.crm.commons.msg.MsgTemplate.failureMsg;
import static com.djcps.crm.commons.msg.MsgTemplate.successMsg;
import static com.djcps.crm.order.enums.OrderMsgEnum.*;

/**
 * Created by Lancher on 2017/7/3.
 */

@RestController
public class OrderController {

    private Gson gson = new Gson();
    private JsonParser jsonParser = new JsonParser();
    @Autowired
    private OrderService orderService;
    @Autowired
    private RedisClientCluster redisClient;

    /**
     * 该接口用于加载订单列表
     * @param json  包含（version 版本号 childOrderModel 包含查询条件）
     * @return gg
     */
    @RequestMapping(name = "该接口用于加载订单列表", value = "order/load", method = {RequestMethod.POST})
    @AddLog(module = "订单模块",value = "该接口用于加载订单列表")
    public Map<String, Object> load(@RequestBody(required = false) String json){
        try {
            String jsonStr = orderService.load(json);
            return successMsg(jsonStr);
        } catch (Exception e) {
            e.printStackTrace();
            return failureMsg(SYS_ERROR);
        }
    }

    /**
     * 作废
     * 该接口用于东经(类似东经的最高权限用户)加载订单列表
     * @param json  包含（version 版本号 childOrderModel 包含查询条件）
     * @return gg
     */
    @RequestMapping(name = "该接口用于加载订单列表", value = "order/DJload", method = {RequestMethod.POST})
    @AddLog(module = "订单模块",value = "该接口用于加载订单列表")
    public Map<String, Object> DJload(@RequestBody(required = false) String json){
        try {
            String jsonStr = orderService.DJload(json);
            return successMsg(jsonStr);
        } catch (Exception e) {
            e.printStackTrace();
            return failureMsg(SYS_ERROR);
        }
    }

    /**
     * 该接口用于取消订单时展示给前端的资料
     * @param json  version 版本号 childOrderId 子订单号
     * @return gg
     */
    @RequestMapping(name = "该接口用于取消订单时展示给前端的资料", value = "order/cancelOrderInfo", method = {RequestMethod.POST})
    @AddLog(module = "订单模块",value = "该接口用于取消订单时展示给前端的资料")
    public Map<String, Object> cancelOrderInfo(@RequestBody(required = false) String json){
        try {
            String jsonStr = orderService.cancelOrderInfo(json);
            return successMsg(jsonStr);
        } catch (Exception e) {
            e.printStackTrace();
            return failureMsg(SYS_ERROR);
        }
    }

    /**
     * 该接口用于取消订单
     * @param json  version 版本号 childOrderId 子订单号 opreator 操作人 and so on
     * @return gg
     */
    @RequestMapping(name = "该接口用于取消订单", value = "order/cancelOrder", method = {RequestMethod.POST})
    @AddLog(module = "订单模块",value = "该接口用于取消订单")
    public Map<String, Object> cancelOrder(@RequestBody(required = false) String json, @InnerUser InnerUserModel model){
        try {
            OrderParamModel paramModel = gson.fromJson(jsonParser.parse(json).getAsJsonObject(),OrderParamModel.class);
            if(model == null){
                return failureMsg(NO_LOGIN_MESSAGE);
            }
            String msg = CheckUtils.parametersEmpty(new Object[]{paramModel.getVersion(),paramModel.getFintegeral(),paramModel.getAmount(),paramModel.getFkeyarea(),paramModel.getChildOrderId(),paramModel.getFmanufacturer(),
                    paramModel.getForderid(),paramModel.getForderId(),paramModel.getFpartnerFkeyarea(),paramModel.getFpartnerid(),paramModel.getFuserid(),paramModel.getKeyArea(),paramModel.getUserId(),
                    paramModel.getFmanufacturerkeyarea(),paramModel.getFmktplanchangeid(),paramModel.getFsalevolume()});
            if(msg.length() > 0){
                return failureMsg(PARAM_ERROR);
            }
            if(paramModel.getAmount().compareTo(BigDecimal.ZERO) <= 0 || paramModel.getFintegeral().compareTo(BigDecimal.ZERO) < 0){
                return failureMsg(MONEY_ERROR);
            }
            while (true) {
                Thread.sleep(5);
                //生成锁  如果能set进去返回值为1
                if (redisClient.setnx(paramModel.getChildOrderId(), new Date().toString()) != 0) {
                    //设置锁的时间为10秒
                    redisClient.expire(paramModel.getChildOrderId(), 10);
                    break;
                }
            }
            paramModel.setOperator(model.getUname()+"");
            paramModel.setOperatorid(model.getUids()+"");
            paramModel.setFcreater(model.getId()+"");
            /*paramModel.setOperator(1+"");
            paramModel.setOperatorid(1+"");
            paramModel.setFcreater(1+"");*/
            paramModel.setFintegeralruletype("2");
            paramModel.setFintegeraljournaltype("4");
            paramModel.setFintegralruleid("");
            paramModel.setFtype("4");
            return MsgTemplate.successMsg(orderService.cancelOrder(paramModel.toString(),paramModel.toJsonString(),paramModel.toString1()));
        } catch (Exception e) {
            e.printStackTrace();
            return failureMsg(SYS_ERROR);
        }
    }

    /**
     * 该接口用于手动分发订单
     * @param json  version版本号 childOrderId子订单号 operator 操作人 fmanufacturer 合作方id
     * @return gg
     */
    @RequestMapping(name = "该接口用于手动分发订单", value = "order/distributionOrder", method = {RequestMethod.POST})
    @AddLog(module = "订单模块",value = "该接口用于手动分发订单")
    public Map<String, Object> distributionOrder(@RequestBody(required = false) String json, @InnerUser InnerUserModel model){
        try {

            OrderParamModel paramModel = gson.fromJson(jsonParser.parse(json).getAsJsonObject(),OrderParamModel.class);
            if(model == null){
                return failureMsg(NO_LOGIN_MESSAGE);
            }
            paramModel.setOperator(model.getUname()+"");
            paramModel.setOperatorid(model.getUids()+"");
            String jsonStr = orderService.distributionOrder(paramModel.toString());
            return successMsg(jsonStr);
        } catch (Exception e) {
            e.printStackTrace();
            return failureMsg(SYS_ERROR);
        }
    }

    /**
     * 导出订单接口
     * @param json  version 版本号 childOrderModel 查询条件的实体，包含ids  订单主键的数组
     * @return gg
     */
    @RequestMapping(name = "导出订单接口", value = "order/exportExcel", method = {RequestMethod.POST})
    @AddLog(module = "订单模块",value = "导出订单接口")
    public Map<String, Object> exportExcel(@RequestBody(required = false) String json){
        try {
            String jsonStr = orderService.exportExcel(json);
            return successMsg(jsonStr);
        } catch (Exception e) {
            e.printStackTrace();
            return failureMsg(SYS_ERROR);
        }
    }

    /**
     * 该接口用于自动分发订单
     * @param json  version版本号  fmanufacturer 合作方id
     * @return gg
     */
    @RequestMapping(name = "该接口用于自动分发订单", value = "getGrnOrderInfoSDK", method = {RequestMethod.POST})
    @AddLog(module = "订单模块",value = "该接口用于自动分发订单")
    public Map<String, Object> distributionOrder(@RequestBody(required = false) String json){
        try {
            OrderParamModel paramModel = gson.fromJson(jsonParser.parse(json).getAsJsonObject(),OrderParamModel.class);
            if(paramModel.getFmanufacturer() == null){
                paramModel.setFmanufacturer("");//TODO:EAS
                paramModel.setFkeyarea("3303");
                paramModel.setVersion("1.0");
            }
            String jsonStr = orderService.distributionOrder(paramModel.toString());
            return successMsg(jsonStr);
        } catch (Exception e) {
            e.printStackTrace();
            return failureMsg(SYS_ERROR);
        }
    }

    /**
     * 所谓自动分发订单接口(即外部系统调用该接口，获取已付款的订单) -- 该接口只用于传递数据，修改订单状态等外部系统成功导入后回调
     * @param json version 版本号  manufacturer 合作商id  fkeyarea 合作方的拆分键
     * @return gg
     */
    @RequestMapping(name = "该接口用于自动分发订单", value = "order/autoDistributionOrder", method = {RequestMethod.POST})
    public Map<String, Object> autoDistributionOrder(@RequestBody(required = false) String json,@InnerUser InnerUserModel model){
        try {
            OrderRequestModel orderRequestModel = JSONObject.parseObject(json, OrderRequestModel.class);
            if (StringUtils.isEmpty(orderRequestModel.getVersion()) || model==null){
                return MsgTemplate.failureMsg(OrderMsgEnum.PARAM_ERROR);
            }
            orderRequestModel.setManufacturer(model.getUcompany());
            orderRequestModel.setFkeyarea(model.getOcode());
            String jsonStr = orderService.autoDistributionOrder(JSON.toJSONString(orderRequestModel));
            return successMsg(jsonStr);
        } catch (Exception e) {
            e.printStackTrace();
            return failureMsg(SYS_ERROR);
        }
    }

    /**
     * 外部ERP系统导入数据成功之后，更新订单状态
     * @return gg
     */
    @RequestMapping(name = "该接口用于外部ERP系统导入数据成功之后，更新订单状态", value = "order/comfirmDistributionOrder", method = {RequestMethod.POST})
    public Map<String, Object> comfirmDistributionOrder(@RequestBody(required = false) String json,@InnerUser InnerUserModel model){
        try {
            OrderRequestModel orderRequestModel = JSONObject.parseObject(json, OrderRequestModel.class);
            if (StringUtils.isEmpty(orderRequestModel.getVersion()) || model==null){
                return MsgTemplate.failureMsg(OrderMsgEnum.PARAM_ERROR);
            }
            orderRequestModel.setManufacturer(model.getUcompany());
            orderRequestModel.setFkeyarea(model.getOcode());
            String jsonStr = orderService.comfirmDistributionOrder(JSON.toJSONString(orderRequestModel));
            return successMsg(jsonStr);
        } catch (Exception e) {
            e.printStackTrace();
            return failureMsg(SYS_ERROR);
        }
    }

    /**
     * 外部ERP系统发货成功之后，更新订单状态
     * @return gg
     */
    @RequestMapping(name = "该接口用于外部ERP系统发货成功之后，更新订单状态", value = "order/deliveryERPOrder", method = {RequestMethod.POST})
    public Map<String, Object> deliveryERPOrder(@RequestBody(required = false) String json,@InnerUser InnerUserModel model){
        try {
            OrderRequestModel orderRequestModel = JSONObject.parseObject(json, OrderRequestModel.class);
            if (StringUtils.isEmpty(orderRequestModel.getVersion()) || model==null){
                return MsgTemplate.failureMsg(OrderMsgEnum.PARAM_ERROR);
            }
            orderRequestModel.setManufacturer(model.getUcompany());
            orderRequestModel.setFkeyarea(model.getOcode());
            String jsonStr = orderService.deliveryERPOrder(JSON.toJSONString(orderRequestModel));
            return successMsg(jsonStr);
        } catch (Exception e) {
            e.printStackTrace();
            return failureMsg(SYS_ERROR);
        }
    }

}
