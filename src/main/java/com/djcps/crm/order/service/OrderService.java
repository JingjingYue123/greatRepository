package com.djcps.crm.order.service;

import com.djcps.crm.order.constant.CrmQueueConstant;
import com.djcps.crm.order.mq.producer.CrmProducer;
import com.djcps.crm.order.server.FileHttpService;
import com.djcps.crm.order.server.IntegralHttpService;
import com.djcps.crm.order.server.OrderHttpService;
import com.djcps.crm.order.server.PaymentHttpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rpc.plugin.http.HTTPResponse;

import javax.annotation.Resource;


/**
 * Created by Lancher on 2017/7/3.
 */
@Service("orderService")
public class OrderService {

    @Autowired
    private OrderHttpService orderHttpService;
    @Autowired
    private PaymentHttpService paymentHttpService;
    @Autowired
    private IntegralHttpService integralHttpService;
    @Autowired
    private FileHttpService fileHttpService;
    @Autowired
    private CrmProducer crmProducer;

    /**
     * 获取订单列表
     * @param json
     * @return gg
     */
    public String load(String json){
        HTTPResponse httpResponse = orderHttpService.load(json);
        if(httpResponse.isSuccessful()){
            return httpResponse.getBodyString();
        }
        return "";
    }

    /**
     * 东经获取订单列表
     * @param json
     * @return gg
     */
    public String DJload(String json){
        HTTPResponse httpResponse = orderHttpService.DJload(json);
        if(httpResponse.isSuccessful()){
            return httpResponse.getBodyString();
        }
        return "";
    }

    /**
     * 取消订单时展示给前端的资料
     * @param json
     * @return gg
     */
    public String cancelOrderInfo(String json){
        HTTPResponse httpResponse = orderHttpService.cancelOrderInfo(json);
        if(httpResponse.isSuccessful()){
            return httpResponse.getBodyString();
        }
        return "";
    }

    /**
     * 取消订单
     * @param json
     * @return gg
     */
    public String cancelOrder(String json, String json1, String json2) {
        try {
            HTTPResponse httpResponse = orderHttpService.cancelOrder(json);
            if (httpResponse.isSuccessful()) {
                String body = httpResponse.getBodyString();
                if(body.contains("true")){
                    //TODO:退余额mq
                    crmProducer.sendMsg(CrmQueueConstant.EXCHANG_CRM, CrmQueueConstant.ROUTING_BALANCE_CRM_KEY, json, null);
                    //TODO:扣积分mq
                    crmProducer.sendMsg(CrmQueueConstant.EXCHANG_CRM, CrmQueueConstant.ROUTING_INTEGRAL_CRM_KEY, json1, null);
                    //TODO:补产品mq
                    crmProducer.sendMsg(CrmQueueConstant.EXCHANG_CRM, CrmQueueConstant.ROUTING_PRODUCT_CRM_KEY, json2, null);
                }
                return body;
            }
            return "";
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * 手动分发订单
     * @param json
     * @return gg
     */
    public String distributionOrder(String json){
        HTTPResponse httpResponse = orderHttpService.distributionOrder(json);
        if(httpResponse.isSuccessful()){
            return httpResponse.getBodyString();
        }
        return "";
    }

    /**
     * 导出订单
     * @param json
     * @return gg
     */
    public String exportExcel(String json){
        HTTPResponse httpResponse = fileHttpService.exportExcel(json);
        if(httpResponse.isSuccessful()){
            return httpResponse.getBodyString();
        }
        return "";
    }

    /**
     * 自动分发订单
     * @param json
     * @return gg
     */
    public String autoDistributionOrder(String json){
        HTTPResponse httpResponse = orderHttpService.autoDistributionOrder(json);
        if(httpResponse.isSuccessful()){
            return httpResponse.getBodyString();
        }
        return "";
    }

    /**
     * erp导入成功更新订单状态
     * @param json
     * @return gg
     */
    public String comfirmDistributionOrder(String json){
        HTTPResponse httpResponse = orderHttpService.comfirmDistributionOrder(json);
        if(httpResponse.isSuccessful()){
            return httpResponse.getBodyString();
        }
        return "";
    }

    /**
     * erp发货成功更新订单状态
     * @param json
     * @return gg
     */
    public String deliveryERPOrder(String json){
        HTTPResponse httpResponse = orderHttpService.deliveryERPOrder(json);
        if(httpResponse.isSuccessful()){
            return httpResponse.getBodyString();
        }
        return "";
    }

}
