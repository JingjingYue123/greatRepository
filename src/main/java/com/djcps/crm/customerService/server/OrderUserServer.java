package com.djcps.crm.customerService.server;

import com.djcps.crm.commons.config.ParamsConfig;
import retrofit2.http.Body;
import retrofit2.http.POST;
import rpc.plugin.http.HTTPResponse;
import rpc.plugin.http.RPCClientFields;

@RPCClientFields(urlfield ="ORDER_SYS",urlbean = ParamsConfig.class)
public interface OrderUserServer {
    /**
     * 根据用户id获取订单信息
     * @param json
     * @return
     */
    @POST("order/getOrderListByUser.do")
    HTTPResponse getPhonesByUserId(@Body String json);
    /**
     * 通过用户id查询订单编号
     * @param json
     * @return
     */
    @POST("order/getOrderListByUser.do")
    HTTPResponse selectOrderIdByUserId(@Body String json);
    /**
     * 通过订单号查询订单信息--退款
     * @param json
     * @return
     */
    @POST("order/getOrderById.do")
    HTTPResponse getOrderByid(@Body String json);

}
