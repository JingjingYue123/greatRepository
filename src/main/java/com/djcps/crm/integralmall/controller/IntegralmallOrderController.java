package com.djcps.crm.integralmall.controller;

import com.djcps.crm.integralmall.service.OrderService;
import com.djcps.crm.commons.msg.MsgTemplate;
import com.djcps.crm.partners.enums.PartnersEnums;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

/**
 * Created by L-wenbin on 2017/8/30.
 */
@RestController
public class IntegralmallOrderController {

    @Qualifier("integralmallOrderService")
    @Autowired
    private OrderService orderService;

    /**
     * 5个发货类型对应的页面
     * 非东经必须传：发货状态shipType。东经运营可以不传。
     * @return
     */
    @RequestMapping(value = "orderController/findOrderPage" , method = RequestMethod.POST)
    public Map<String,Object> findOrderPage(@RequestBody(required = false) String json) {
        if(json != null) {
            Map<String, Object> findOrderPage = orderService.findOrderPage(json);
            return findOrderPage;
        }else {
            return MsgTemplate.failureMsg(PartnersEnums.ADD_ERROR);
        }
    }
    /**
     * 东经用：运营端查询全部订单
     * @return
     */
    @RequestMapping(value = "orderController/findAllOrder" , method = RequestMethod.POST)
    public Map<String,Object> findAllOrder(@RequestBody(required = false) String json) {
        if(json != null) {
            Map<String, Object> findAllOrder = orderService.findAllOrder(json);
            return findAllOrder;
        }else {
            return MsgTemplate.failureMsg(PartnersEnums.ADD_ERROR);
        }
    }
    /**
     * 充值,发货,处理,完成订单
     * @return
     */
    @RequestMapping(value = "orderController/handle" , method = RequestMethod.POST)
    public Map<String,Object> handle(@RequestBody(required = false) String json) {
        if(json != null) {
            Map<String, Object> handle = orderService.handle(json);
            return handle;
        }else {
            return MsgTemplate.failureMsg(PartnersEnums.ADD_ERROR);
        }
    }
    /**
     * 后台签收订单
     * @return
     */
    @RequestMapping(value = "orderController/sign" , method = RequestMethod.POST)
    public Map<String,Object> sign(@RequestBody(required = false) String json) {
        if(json != null) {
            Map<String, Object> handle = orderService.sign(json);
            return handle;
        }else {
            return MsgTemplate.failureMsg(PartnersEnums.ADD_ERROR);
        }
    }
    /**
     * 后台发货
     * @return
     */
    @RequestMapping(value = "orderController/deliver" , method = RequestMethod.POST)
    public Map<String,Object> deliver(@RequestBody(required = false) String json) {
        if(json != null) {
            Map<String, Object> handle = orderService.deliver(json);
            return handle;
        }else {
            return MsgTemplate.failureMsg(PartnersEnums.ADD_ERROR);
        }
    }
    /**
     * 后台取消订单
     * @return
     */
    @RequestMapping(value = "orderController/cancle" , method = RequestMethod.POST)
    public Map<String,Object> cancle(@RequestBody(required = false) String json) {
        if(json != null) {
            Map<String, Object> handle = orderService.cancle(json);
            return handle;
        }else {
            return MsgTemplate.failureMsg(PartnersEnums.ADD_ERROR);
        }
    }
}
