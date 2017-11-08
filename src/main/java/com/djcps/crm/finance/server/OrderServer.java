package com.djcps.crm.finance.server;

import com.djcps.crm.commons.config.ParamsConfig;
import retrofit2.http.Body;
import retrofit2.http.POST;
import rpc.plugin.http.HTTPResponse;
import rpc.plugin.http.RPCClientFields;

/**
 * Created by jmb on 2017/9/9.
 */
@RPCClientFields(urlfield ="ORDER_SYS",urlbean = ParamsConfig.class)
public interface OrderServer {

    /**
     * 该接口用于查询报表所需的订单列表
     * @param json
     * @return
     */
    @POST("reportForms/list.do")
    HTTPResponse orderList(@Body String json);
    /**
     * 查询订单列表
     * @param json
     * @return
     */
    @POST("rechargeOrder/list.do ")
    HTTPResponse getRechargeForms(@Body String json);
    /**
     * 查询消费订单报表
     * @param json
     * @return
     */
    @POST("order/getConsumeOrderList.do")
    HTTPResponse getConsumeOrderList(@Body String json);

}
