package com.djcps.crm.finance.controller;

import com.alibaba.fastjson.JSONObject;
import com.baidu.unbiz.fluentvalidator.ComplexResult;
import com.baidu.unbiz.fluentvalidator.FluentValidator;
import com.djcps.crm.aop.log.AddLog;
import com.djcps.crm.commons.utils.CheckUtils;
import com.djcps.crm.commons.utils.HibernateValidator;
import com.djcps.crm.finance.model.AuditModel;
import com.djcps.crm.finance.model.ChangeUserAccountModel;
import com.djcps.crm.finance.model.RechargeOrderParamModel;
import com.djcps.crm.finance.service.RechargeOrderService;
import com.google.gson.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.*;

import static com.baidu.unbiz.fluentvalidator.ResultCollectors.toComplex;
import static com.djcps.crm.commons.msg.MsgTemplate.failureMsg;
import static com.djcps.crm.commons.msg.MsgTemplate.successMsg;
import static com.djcps.crm.customerService.enums.AbnormalOrderEunm.GETNUMBER_ERROR;
import static com.djcps.crm.finance.enums.RechargeOrderEnum.*;

/**
 * 财务管理
 *Created by liumenghui on 2017-08-22.
 */
@RestController
public class RechargeOrderController {
    private static final Logger logger = LoggerFactory.getLogger(RechargeOrderController.class);
    private Gson gson = new Gson();
    private JsonParser jsonParser = new JsonParser();
    @Autowired
    private RechargeOrderService rechargeOrderService;

    /**
     * 该接口充值/扣款订单列表
     * @param json
     * @return
     */
    @RequestMapping(name = "该接口用于充值/扣款订单列表",value = "rechargeOrder/list",method = {RequestMethod.POST})
    @AddLog(module = "财务模块",value = "该接口用于充值/扣款订单列表")
    public Map<String,Object> financeOrderList(@RequestBody String json){
        try {
            String jsonStr = rechargeOrderService.list(json);
            return successMsg(jsonStr);
        }catch (Exception e){
            e.printStackTrace();
            logger.error("获取充值订单列表失败"+e.getCause());
            return failureMsg(SYS_ERROR);
        }
    }
    /**
     * 该接口保存充值/扣款订单展示给前端的数据
     * @param json
     * @return
     */
    @RequestMapping(name = "该接口保存充值/扣款订单展示给前端的数据",value = "rechargeOrder/saveOrderIdInfo",method = {RequestMethod.POST})
    public Map<String,Object> saveOrderIdInfo(@RequestBody String json){
        try {
            String jsonStr = rechargeOrderService.saveOrderInfo(json);
            JSONObject jsonObject=new JSONObject();
            jsonObject.put("orderId",jsonStr);
            return successMsg(jsonObject);
        }catch (Exception e){
            e.printStackTrace();
            logger.error("获取充值订单订单失败"+e.getCause());
            return failureMsg(SYS_ERROR);
        }
    }
    /**
     * 财务充值/扣款
     * @param json
     * @return
     */
    @RequestMapping(name = "财务充值/扣款",value = "rechargeOrder/save",method = {RequestMethod.POST})
    @AddLog(module = "财务模块",value = "财务充值/扣款")
    public Map<String,Object> saveOrder(@RequestBody String json){
        try {
            RechargeOrderParamModel model = gson.fromJson(jsonParser.parse(json).getAsJsonObject(),RechargeOrderParamModel.class);
            String msg= CheckUtils.parametersEmpty(new Object[]{model.getUserId(),model.getKeyArea(),model.getPhone(),model.getOrderType(),model.getAmount(),model.getFundType(),model.getOperator(),model.getUppercaseAmount()});
            if (!"".equals(msg)){
                return failureMsg(ABSENCE_ERROR);
            }
            JsonObject jsonObject = jsonParser.parse(json).getAsJsonObject();
            if (StringUtils.isBlank(model.getOrderId())){
                String s = rechargeOrderService.getOrderId("{\"count"+":1}");
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
                String dateStr= new SimpleDateFormat("yyyy-MM-dd").format(new Date());

                jsonObject.addProperty("orderId","CF"+dateStr+s1);
            }
            String jsonStr = rechargeOrderService.saveFinanceOrder(jsonObject.toString());
            return successMsg(jsonStr);
        }catch (Exception e){
            e.printStackTrace();
            logger.error("订单充值/扣款失败"+e.getCause());
            return failureMsg(SYS_ERROR);
        }
    }

    /**
     * 该接口用于根据模糊查询手机号获取外部用户基本信息列表
     * @param json
     * @return
     */
    @RequestMapping(name = "该接口用于根据模糊查询手机号获取外部用户基本信息列表",value = "rechargeOrder/selectPhones",method = {RequestMethod.POST})
    @AddLog(module = "财务模块",value = "该接口用于根据模糊查询手机号获取外部用户基本信息列表")
    public Map<String,Object>selectPhones(@RequestBody String json){
        try {
            RechargeOrderParamModel model = gson.fromJson(jsonParser.parse(json).getAsJsonObject(), RechargeOrderParamModel.class);
            String msg= CheckUtils.parametersEmpty(new Object[]{model.getPhone(),model.getVersion()});
            if (!"".equals(msg)){
                return failureMsg(SELECT_PHONE_ERROR);
            }
            String jsonStr = rechargeOrderService.selectPhones(json);
            return successMsg(jsonStr);
        }catch (Exception e){
            e.printStackTrace();
            logger.error("查询外部用户失败"+e.getCause());
            return failureMsg(SYS_ERROR);
        }
    }

    /**
     * 根据用户认证名模糊查找用户信息列表
     * @param json
     * @return
     */
    @RequestMapping(name = "该接口用于根据用户认证名查找用户信息列表",value = "rechargeOrder/selBaseUserByName",method = {RequestMethod.POST})
    public Map<String,Object> selBaseUserByName(@RequestBody String json){
        try {
            String jsonStr = rechargeOrderService.selBaseUserByName(json);
            return successMsg(jsonStr);
        }catch (Exception e){
            e.printStackTrace();
            logger.error("查询外部用户信息失败"+e.getCause());
            return failureMsg(SYS_ERROR);
        }
    }

    /**
     * 该接口审核充值订单
     * @param json
     * @return
     */
    @RequestMapping(name = "该接口用于审核充值订单",value = "rechargeOrder/audit",method = {RequestMethod.POST})
    @AddLog(module = "财务模块",value = "该接口用于审核充值订单")
    public Map<String,Object> audit(@RequestBody String json){
        try {
            //验证修改余额参数
            ChangeUserAccountModel changeUserAccountModel = gson.fromJson(jsonParser.parse(json), ChangeUserAccountModel.class);
            ComplexResult ret = FluentValidator.checkAll().failFast()
                    .on(changeUserAccountModel, new HibernateValidator<ChangeUserAccountModel>().Validator())
                    .doValidate()
                    .result(toComplex());
            //验证审核订单参数
            AuditModel auditModel = gson.fromJson(jsonParser.parse(json).getAsJsonObject(), AuditModel.class);
            ComplexResult auditRet = FluentValidator.checkAll().failFast()
                    .on(auditModel, new HibernateValidator<AuditModel>().Validator())
                    .doValidate()
                    .result(toComplex());
            if(ret.isSuccess()&&auditRet.isSuccess()){
                String jsonStr = rechargeOrderService.audit(json);
                return successMsg(jsonStr);
            }else{
                return failureMsg(AUDIT_ERROR);
            }
        }catch (Exception e){
            e.printStackTrace();
            logger.error("充值订单审核失败"+e.getCause());
            return failureMsg(SYS_ERROR);
        }
    }
    /**
     * 该接口审核充值订单列表
     * @param json
     * @return
     */
    @RequestMapping(name = "该接口用于审核充值订单列表",value = "rechargeOrder/auditList",method = {RequestMethod.POST})
    @AddLog(module = "财务模块",value = "该接口用于审核充值订单列表")
    public Map<String,Object> auditList(@RequestBody String json){
        try {
            String jsonStr = rechargeOrderService.auditList(json);
            return successMsg(jsonStr);
        }catch (Exception e){
            e.printStackTrace();
            logger.error("获取审核充值订单列表失败"+e.getCause());
            return failureMsg(SYS_ERROR);
        }
    }

    @RequestMapping(name = "该接口用于查询用户余额",value = "balance/getBalance",method = {RequestMethod.POST})
    @AddLog(module = "财务模块",value = "该接口用于查询用户余额")
    public Map<String,Object> getBalance(@RequestBody String json){
        try {
            String jsonStr = rechargeOrderService.getBalance(json);
            return successMsg(jsonStr);
        }catch (Exception e){
            e.printStackTrace();
            logger.error("获取用户余额失败");
            return  failureMsg(SYS_ERROR);
        }
    }

}
