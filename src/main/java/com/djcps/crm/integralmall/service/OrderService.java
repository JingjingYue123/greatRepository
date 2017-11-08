package com.djcps.crm.integralmall.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.djcps.crm.integralmall.server.OrderHttpServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by L-wenbin on 2017/8/30.
 */
@Service("integralmallOrderService")
public class OrderService {
    @Autowired
    private OrderHttpServer orderHttpServer;

    /**
     * 5个发货类型对应的页面
     * 非东经必须传：发货状态shipType。东经运营可以不传。
     * @return
     */
    public Map<String,Object> findOrderPage(String json) {
        String result = orderHttpServer.findOrderPage(json).getBodyString();
        JSONObject jsonObject = JSON.parseObject(result,JSONObject.class);
        return jsonObject;
    }
    /**
     * 东经用：运营端查询全部订单
     * @return
     */
    public Map<String,Object> findAllOrder(String json) {
        String result = orderHttpServer.findAllOrder(json).getBodyString();
        JSONObject jsonObject = JSON.parseObject(result,JSONObject.class);
        return jsonObject;
    }
    /**
     * 充值,发货,处理,完成订单
     * @return
     */
    public Map<String,Object> handle(String json) {
        String result = orderHttpServer.handle(json).getBodyString();
        JSONObject jsonObject = JSON.parseObject(result,JSONObject.class);
        return jsonObject;
    }
    /**
     * 后台签收订单
     * @return
     */
    public Map<String,Object> sign(String json) {
        String result = orderHttpServer.sign(json).getBodyString();
        JSONObject jsonObject = JSON.parseObject(result,JSONObject.class);
        return jsonObject;
    }
    /**
     * 后台发货
     * @return
     */
    public Map<String,Object> deliver(String json) {
        String result = orderHttpServer.deliver(json).getBodyString();
        JSONObject jsonObject = JSON.parseObject(result,JSONObject.class);
        return jsonObject;
    }
    /**
     * 后台取消订单
     * @return
     */
    public Map<String,Object> cancle(String json) {
        String result = orderHttpServer.cancle(json).getBodyString();
        JSONObject jsonObject = JSON.parseObject(result,JSONObject.class);
        return jsonObject;
    }
}
