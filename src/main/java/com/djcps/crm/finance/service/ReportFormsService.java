package com.djcps.crm.finance.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.djcps.crm.commons.msg.MsgTemplate;
import com.djcps.crm.commons.utils.BeanMapper;
import com.djcps.crm.finance.controller.RechargeOrderController;
import com.djcps.crm.finance.enums.ReportFormsEnum;
import com.djcps.crm.finance.exception.ReportFormsException;
import com.djcps.crm.finance.model.*;
import com.djcps.crm.finance.model.returnInfoModel.*;
import com.djcps.crm.finance.server.*;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import rpc.plugin.http.HTTPResponse;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.math.BigDecimal.ROUND_HALF_DOWN;

/**
 * Created by jmb on 2017/9/9.
 */
@Service
public class ReportFormsService {

    @Autowired
    private OrderServer orderServer;

    @Autowired
    private IntegralServer integralServer;

    @Autowired
    private PaymentServer paymentServer;
    @Autowired
    private OutterUserServer outterUserServer;

    @Autowired
    private  IntegralOrderMallServer integralOrderMallServer;

    @Autowired
    private  PayBankServer payBankServer;

    private JsonParser jsonParser = new JsonParser();

    private Gson gson = new Gson();

    private static final Logger logger = LoggerFactory.getLogger(RechargeOrderController.class);



    /**
     * 查询混合报表
     * @param model
     * @return
     */
    public Map<String,Object> mixReportFormslist(ListModel model) {
        Map<String, Object> map=null;
        try {
            map = orderListInfo(model);
            if (map==null){
                map= new HashMap<>();
                map.put("total",0);
                map.put("list",new ArrayList<>());
                return MsgTemplate.successMsg(map);
            }
        }catch (Exception e){
            logger.error("获取报表列表失败"+e.getCause());
            e.printStackTrace();
            return MsgTemplate.failureMsg(ReportFormsEnum.valueOf(e.getMessage()));
        }

        List<MixReportFromModel> list = (List<MixReportFromModel>) map.get("list");
        Integer total= (Integer) map.get("total");
        //添加积分到数据中
        JSONObject integralRequest = new JSONObject();
        integralRequest.put("version","1.0");
        List<JSONObject> params= new ArrayList<>();
        Map<String,MixReportFromModel> reportFromModelsMap = new HashMap<>();
        for (MixReportFromModel mixReportFromModel : list) {
            JSONObject orderId = new JSONObject();
            String id = mixReportFromModel.getOrderId();
            orderId.put("orderId", id);
            orderId.put("integralJournalType","4");
            params.add(orderId);
            reportFromModelsMap.put(id,mixReportFromModel);
        }
        integralRequest.put("params",params);
        HTTPResponse orderIntergralResponse = integralServer.getOrderIntergral(integralRequest.toJSONString());
        if (!orderIntergralResponse.isSuccessful()){
            logger.error("调用积分服务失败");
            return MsgTemplate.failureMsg(ReportFormsEnum.FALSE);
        }
        ReturnInfoNoTotalModel returnInfoNoTotalModel = gson.fromJson(jsonParser.parse(orderIntergralResponse.getBodyString()), ReturnInfoNoTotalModel.class);
        List<IntegralReturnModel> integralReturnModels = returnInfoNoTotalModel.getData();
        //把积分信息添加到返回数据中
        if (integralReturnModels != null && integralReturnModels.size()>0){
            for (IntegralReturnModel integral : integralReturnModels) {
                String integralOrderId = integral.getOrderId();
                reportFromModelsMap.get(integralOrderId).setIntegral(integral.getIntegral());
            }
        }
        JSONObject result = new JSONObject();
        result.put("total",total);
        result.put("list",reportFromModelsMap.values());
        return MsgTemplate.successMsg(result);
    }

    /**
     *该接口用于查询混合报表之积分商城
     * @param json
     * @return
     */
    public String mixReportOfMallForms(String json){
        HTTPResponse response = integralOrderMallServer.mixReportOfMallForms(json);
        if (response.isSuccessful()){
            return response.getBodyString();
        }
        return "";
    }

    /**
     * 查询销售报表列表
     * @param model
     * @return
     */
    public Map<String,Object> sellReportFormslist(ListModel model) {
        Map<String, Object> map=null;
        try {
            map = orderListInfo(model);
            if (map==null){
                map= new HashMap<>();
                map.put("total",0);
                map.put("list",new ArrayList<>());
                return MsgTemplate.successMsg(map);
            }
        }catch (Exception e){
            logger.error("获取报表列表失败"+e.getCause());
            e.printStackTrace();
            return MsgTemplate.failureMsg(ReportFormsEnum.valueOf(e.getMessage()));
        }
        JSONObject returnData = new JSONObject();
        returnData.put("total",map.get("total"));
        returnData.put("list",map.get("list"));
        return MsgTemplate.successMsg(returnData);
    }

    /**
     * 搜索客户认证名
     * @param userIids
     * @return
     * @throws ReportFormsException
     */
    private Map<String, OutUserBaseModel> selCustomerName(List<String> userIids) throws ReportFormsException {
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("userIds",userIids);
        HTTPResponse userByPhones = outterUserServer.getUserBaseInfo(jsonObject.toJSONString());
        if (!userByPhones.isSuccessful()){
            throw new ReportFormsException("GET_CUSTOMER_ERROR");
        }
        String userBaseInfo = userByPhones.getBodyString();
        JsonElement data1 = jsonParser.parse(userBaseInfo).getAsJsonObject().get("data");
        Map<String, OutUserBaseModel> map = new HashMap<>();
        //判断用户信息可能为空
        //因为返回数据可能会是数组和单个对象  这里用异常做了判断
        if (!data1.toString().equals("null") && data1 !=null) {
            try {
                List<OutUserBaseModel> outUserBaseModels = JSON.parseArray(data1.toString(), OutUserBaseModel.class);
                if (outUserBaseModels!=null && outUserBaseModels.size()>0){
                    for (OutUserBaseModel userBaseModel : outUserBaseModels) {
                        map.put(userBaseModel.getFuserId(),userBaseModel);
                    }
                }
            }catch (Exception e){
                logger.error("这里报错是正常情况，是因为用户服务有时候返回的是数组，有时候返回的是单个对象，所以用try-catch做了判断");
                e.printStackTrace();
                OutUserBaseModel baseModel = JSONObject.parseObject(data1.toString(), OutUserBaseModel.class);
                map.put(baseModel.getFuserId(),baseModel);

            }

        }
        return map;
    }

    /**
     * 搜索客户余额
     * @param balanceMap
     * @return
     * @throws ReportFormsException
     */
    private Map<Object, BalanceFormsModel> selCustomerBalance(Map<String,Object> balanceMap) throws ReportFormsException{
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("userIds",balanceMap.get("userIds"));
        jsonObject.put("version",balanceMap.get("version"));
        //调用余额服务查询用户余额
        HTTPResponse balanceResponse = paymentServer.balanceList(jsonObject.toJSONString());
        if (!balanceResponse.isSuccessful()) {
            throw new ReportFormsException("GET_PAYMENT_ERROR");
        }
            String balanceInfo = balanceResponse.getBodyString();
            JsonElement data1 = jsonParser.parse(balanceInfo).getAsJsonObject().get("data");
            Map<Object, BalanceFormsModel> map = new HashMap<>();
            if (!data1.toString().equals("null") && data1 !=null){
                    List<BalanceFormsModel> balanceFormsModelList = JSON.parseArray(data1.toString(), BalanceFormsModel.class);
                    if (balanceFormsModelList!=null && balanceFormsModelList.size()>0){
                        for (BalanceFormsModel balanceFormsModel : balanceFormsModelList) {
                            map.put(balanceFormsModel.getFuserId(),balanceFormsModel);
                        }
                    }

            }
            return map;
        }
        /**
         * 调用订单服务查销售报表列表
         * @param model
         * @return
         * @throws ReportFormsException
         */
    public Map<String,Object> orderListInfo(ListModel model) throws ReportFormsException {
        List<String> userIds=null;
        if (!StringUtils.isEmpty(model.getUserId())){
            userIds=new ArrayList<>();
            userIds.add(model.getUserId());
        }
        JSONObject requestJson= new JSONObject();
        requestJson.put("startTime",model.getStartTime());
        requestJson.put("endTime",model.getEndTime());
        requestJson.put("userIds",userIds);
        requestJson.put("pageSize",model.getPageSize());
        requestJson.put("pageNo",model.getPageNo());
        requestJson.put("version","1.0");
        HTTPResponse orderResponse = orderServer.orderList(requestJson.toJSONString());
        if (!orderResponse.isSuccessful()){
            logger.error("调用订单服务，获取报表信息失败");
            throw new ReportFormsException("REOIRT_FORMS_LIST_ERROR");
        }
        ReturnInfoModel orderReturnInfo = gson.fromJson(orderResponse.getBodyString(), ReturnInfoModel.class);
        if (orderReturnInfo.getData().getList().size()==0){
            return null;
        }
        List<MixReportFromModel> list = orderReturnInfo.getData().getList();
        Integer total = orderReturnInfo.getData().getTotal();
        //以下是添加客户认证名的操作
        Map<String, OutUserBaseModel> customerName=null;
        try {
            customerName=selCustomerName(BeanMapper.getFieldList(list,"userId",String.class));

        }catch (Exception e){
            logger.error("获取客户认证名失败");
            e.printStackTrace();
            throw new ReportFormsException("GET_CUSTOMER_ERROR");
//            return MsgTemplate.failureMsg(ReportFormsEnum.FALSE);
        }
        //这里设置一下退款类型 1为异常订单退款 2为订单取消退款 默认为0（即正常销售订单）
        //1）异常订单退款时的单价为从订单拿订单单价/片数，面积=异常退款金额/单价
        //2）订单取消的退款面积就直接取出库面积
        if (customerName.size()>0){
            for (MixReportFromModel mixReportFromModel : list) {
                String userId = mixReportFromModel.getUserId();
                if (mixReportFromModel.getFpatchamount()!=null){
                    mixReportFromModel.setStatus(1);
                    BigDecimal singlePrice = mixReportFromModel.getMoney().divide(mixReportFromModel.getFamountpiece(),2,ROUND_HALF_DOWN);
                    mixReportFromModel.setRefundGround(mixReportFromModel.getExceptionRefundMoney().divide(singlePrice,2,ROUND_HALF_DOWN));
                }
                if (mixReportFromModel.getOrderStatus()==7){
                    mixReportFromModel.setStatus(2);
                    mixReportFromModel.setRefundGround(mixReportFromModel.getFproductarea());
                }
                //往数据中添加认证名跟手机号
                if (customerName.get(userId)!=null){
                    if (customerName.get(userId).getFcustName()!=null){
                        mixReportFromModel.setCustomerName(customerName.get(userId).getFcustName());
                    }
                    if (customerName.get(userId).getFphone()!=null){
                        mixReportFromModel.setPhone(customerName.get(userId).getFphone());
                    }

                }
            }
        }
        Map<String ,Object> map =new HashMap<>();
        map.put("total",total);
        map.put("list",list);
        return map;
    }

    /**余额报表
     *
     * @param model
     * @return
     * @throws ReportFormsException
     */
    public Map<String,Object> getBalanceFormslist(ListModel model) throws ReportFormsException{
        List<String> userIds=null;
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("certificateName", model.getCertificateName());
            jsonObject.put("phone", model.getPhone());
            jsonObject.put("pageNo", model.getPageNo());
            jsonObject.put("pageSize",model.getPageSize());
            HTTPResponse response = outterUserServer.selectUser(jsonObject.toJSONString());
            if (!response.isSuccessful()) {
                logger.error("调用外部用户服务失败");
                throw new ReportFormsException("GET_CUSTOMER_ERROR");
            }
            ReturnInfoModel returnInfoModel = gson.fromJson(jsonParser.parse(response.getBodyString()), ReturnInfoModel.class);
            if (returnInfoModel.getData().getResult().size() == 0){

                JSONObject object=new JSONObject();
                object.put("total",0);
                object.put("list",new ArrayList<>());
                return MsgTemplate.successMsg(object);
            }
            userIds = BeanMapper.getFieldList(returnInfoModel.getData().getResult(), "fuserId", String.class);
            //添加余额信息
        List<OutUserBaseModel> list = returnInfoModel.getData().getResult();
        //记录数
        Integer total = returnInfoModel.getData().getTotal();
        //添加余额信息
        Map<Object,BalanceFormsModel> customerBalance=null;
        Map<String,Object> balanceMap=new HashMap<String,Object>();
        balanceMap.put("version",model.getVersion());
        balanceMap.put("userIds",userIds);
        try {
            customerBalance=selCustomerBalance(balanceMap);
        }catch (Exception e){
            logger.error("获取客户余额失败");
            e.printStackTrace();
            throw new ReportFormsException("GET_CUSTOMERBALANCE_ERROR");
//            return MsgTemplate.failureMsg(ReportFormsEnum.FALSE);
        }
        if (customerBalance.size()>0){
            for(OutUserBaseModel outUserBaseModel:list){
                String userId=outUserBaseModel.getFuserId();
                //往数据中添加余额
                if (customerBalance.get(userId)!=null){
                    outUserBaseModel.setFamount(customerBalance.get(userId).getFamount());
                }
            }
        }
        Map<String ,Object> map =new HashMap<>();
        map.put("total",total);
        map.put("list",list);
        JSONObject returnData = new JSONObject();
        returnData.put("total",total);
        returnData.put("list",list);
        return MsgTemplate.successMsg(returnData);

    }
    /**
     * 通过手机号或认证名查询用户id
     */
    public String selectUserIdByPhoneOrCustomerName(String json) {
        HTTPResponse response = outterUserServer.selectUser(json);
        if (response.isSuccessful()){
            return response.getBodyString();
        }
        return "";
    }

    /**
     *余额预警
     * @param model
     * @return
     * @throws ReportFormsException
     */
   public Map<String,Object> selectBalanceWarningList(ListModel model) throws ReportFormsException{
       JSONObject requestJson= new JSONObject();
       requestJson.put("userId",model.getUserId());
       requestJson.put("pageSize",model.getPageSize());
       requestJson.put("pageNo",model.getPageNo());
       requestJson.put("startTime",model.getStartTime());
       requestJson.put("endTime",model.getEndTime());
       HTTPResponse paymentResponse = paymentServer.balanceWarningList(requestJson.toJSONString());
       if (!paymentResponse.isSuccessful()){
           logger.error("调用余额服务，获取余额预警信息失败");
           throw new ReportFormsException("获取余额预警信息失败");
       }
       ReturnBalanceWarningModel returnBalanceWarningModel=gson.fromJson(paymentResponse.getBodyString(), ReturnBalanceWarningModel.class);
       if (returnBalanceWarningModel.getData().getResult().size()==0){
           JSONObject jsonObject=new JSONObject();
           jsonObject.put("total",0);
           jsonObject.put("list",new ArrayList<>());
           return MsgTemplate.successMsg(jsonObject);
       }
       List<BalanceWarningModel> list = returnBalanceWarningModel.getData().getResult();
       //添加total数量
       Integer total = returnBalanceWarningModel.getData().getTotal();
       //添加认证名和手机号
       Map<String,OutUserBaseModel > customerName=null;
       try {
           customerName=selCustomerName(BeanMapper.getFieldList(list,"fuserId",String.class));
       }catch (Exception e){
           logger.error("获取客户认证名失败");
           e.printStackTrace();
           throw new ReportFormsException("GET_CUSTOMER_ERROR");
//            return MsgTemplate.failureMsg(ReportFormsEnum.FALSE);
       }
       if (customerName.size()>0){
           for(BalanceWarningModel balanceWarningModel:list){
               String userId=balanceWarningModel.getFuserId();
               //往数据中添加认证名和手机号
               if (customerName.get(userId)!=null){
                   balanceWarningModel.setFcertificateName(customerName.get(userId).getFcustName());
                   balanceWarningModel.setFphone(customerName.get(userId).getFphone());
               }
           }
       }
       Map<String ,Object> map =new HashMap<>();
       map.put("total",total);
       map.put("list",list);
       JSONObject returnData = new JSONObject();
       returnData.put("total",total);
       returnData.put("list",list);
       return MsgTemplate.successMsg(returnData);

   }

    /**
     * 获取余额收款明细
     * @param model
     * @return
     * @throws ReportFormsException
     */

    public Map<String,Object> balanceCollectionForms(ListModel model)throws ReportFormsException{
        JSONObject requestJson= new JSONObject();
        requestJson.put("userId",model.getUserId());
        requestJson.put("pageSize",model.getPageSize());
        requestJson.put("pageNo",model.getPageNo());
        requestJson.put("startTime",model.getStartTime());
        requestJson.put("endTime",model.getEndTime());
        requestJson.put("version",model.getVersion());
        requestJson.put("ftype",model.getFtype());
        requestJson.put("keyArea",model.getFkeyarea());
        HTTPResponse rechargeResponse =paymentServer.balanceCollectionForms(requestJson.toJSONString());
        if (!rechargeResponse.isSuccessful()){
            logger.error("调用余额服务，获取余额收款明细");
            throw new ReportFormsException("REOIRT_FORMS_LIST_ERROR");
        }
        ReturnBalanceCollectionModel returnBalanceCollectionModel= gson.fromJson(rechargeResponse.getBodyString(), ReturnBalanceCollectionModel.class);
        if (returnBalanceCollectionModel.getData().getData().size()==0){
            JSONObject jsonObject=new JSONObject();
            jsonObject.put("total",0);
            jsonObject.put("list",new ArrayList<>());
            return MsgTemplate.successMsg(jsonObject);
        }
        List<BalanceCollectionModel> list = returnBalanceCollectionModel.getData().getData();
        Integer total = returnBalanceCollectionModel.getData().getTotal();
        //以下是添加客户认证名的操作
        Map<String,OutUserBaseModel> customerName=null;
        try {
            customerName=selCustomerName(BeanMapper.getFieldList(list,"fuserId",String.class));
        }catch (Exception e){
            logger.error("获取客户认证名失败");
            e.printStackTrace();
            throw new ReportFormsException("GET_CUSTOMER_ERROR");
//            return MsgTemplate.failureMsg(ReportFormsEnum.FALSE);
        }
        if(customerName.size()>0){
            for (BalanceCollectionModel balanceCollectionModel:list){
                String userId=balanceCollectionModel.getFuserId();
                if (customerName.get(userId)!=null){
                    balanceCollectionModel.setFcertificateName(customerName.get(userId).getFcustName());
                    balanceCollectionModel.setFphone(customerName.get(userId).getFphone());
                    String oldFupdateTime=balanceCollectionModel.getFupdateTime();
                    String  fupdateTime=oldFupdateTime==null?null:oldFupdateTime.substring(0,19);
                    balanceCollectionModel.setFupdateTime(fupdateTime);
                }
            }
        }

        JSONObject returnData = new JSONObject();
        returnData.put("total",total);
        returnData.put("list",list);
        return MsgTemplate.successMsg(returnData);
    }
    /**
     *收款统计--消费报表
     * @param model
     * @return
     * @throws ReportFormsException
     */
    public Map<String,Object> consumerForms(ListModel model)throws ReportFormsException{
        JSONObject requestJson= new JSONObject();
        requestJson.put( "startTime",model.getStartTime());
        requestJson.put("endTime",model.getEndTime());
        requestJson.put("userId",model.getUserId());
        requestJson.put("pageNo",model.getPageNo());
        requestJson.put("pageSize",model.getPageSize());
        requestJson.put("version","1.0");
        HTTPResponse httpResponse=orderServer.getConsumeOrderList(requestJson.toJSONString());
        if(!httpResponse.isSuccessful()){
            logger.error("调用订单服务，获取支付列表失败");
            throw new ReportFormsException("获取支付列表失败");
        }
        ReturnConsumeOrderModel returnConsumeOrderModel=gson.fromJson(httpResponse.getBodyString(), ReturnConsumeOrderModel.class);
        if(!returnConsumeOrderModel.isSuccess()){
            logger.error("调用订单服务，获取支付列表失败");
            throw new ReportFormsException("获取支付列表失败");
        }
        if (returnConsumeOrderModel.getData().getList().size()==0){
            JSONObject returnData = new JSONObject();
            returnData.put("total",0);
            returnData.put("list",new ArrayList<>());
            return MsgTemplate.successMsg(returnData);
        }
        List<ConsumeOrderModel> list = returnConsumeOrderModel.getData().getList();
        Integer total=returnConsumeOrderModel.getData().getTotal();
        //添加认证名和手机号
        Map<String, OutUserBaseModel> customerName=null;
        try {
            customerName=selCustomerName(BeanMapper.getFieldList(list,"fpuserid",String.class));
        }catch (Exception e){
            logger.error("获取客户认证名失败");
            e.printStackTrace();
            throw new ReportFormsException("GET_CUSTOMER_ERROR");
        }
        if (customerName.size()>0){
            for(ConsumeOrderModel consumeOrderModel:list){
                String userId=consumeOrderModel.getFpuserid();
                //往数据中添加认证名和手机号
                if (customerName.get(userId)!=null){
                    consumeOrderModel.setFcertificateName(customerName.get(userId).getFcustName());
                    consumeOrderModel.setPhone(customerName.get(userId).getFphone());
                    consumeOrderModel.setCategory("线上");
                }
            }
        }
        JSONObject returnData = new JSONObject();
        returnData.put("total",total);
        returnData.put("list",list);
        return MsgTemplate.successMsg(returnData);
    }
}
