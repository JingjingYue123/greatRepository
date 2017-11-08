package com.djcps.crm.integralmall.server;

import com.djcps.crm.commons.config.ParamsConfig;
import retrofit2.http.Body;
import retrofit2.http.POST;
import rpc.plugin.http.HTTPResponse;
import rpc.plugin.http.RPCClientFields;

/**
 * Created by L-wenbin on 2017/8/30.
 */
@RPCClientFields(urlfield ="IntegralOrder_Mall",urlbean = ParamsConfig.class)
public interface OrderHttpServer {
    /**
     * 5个发货类型对应的页面
     * 非东经必须传：发货状态shipType。东经运营可以不传。
     * @return
     */
    @POST("order/findOrderPage.do")
    HTTPResponse findOrderPage(@Body String json);
    /**
     * 东经用：运营端查询全部订单
     * @return
     */
    @POST("order/findAllOrder.do")
    HTTPResponse findAllOrder(@Body String json);
    /**
     * 充值,发货,处理,完成订单
     * @return
     */
    @POST("order/handle.do")
    HTTPResponse handle(@Body String json);
    /**
     * 后台签收订单
     * @return
     */
    @POST("order/sign.do")
    HTTPResponse sign(@Body String json);
    /**
     * 后台发货
     * @return
     */
    @POST("order/deliver.do")
    HTTPResponse deliver(@Body String json);
    /**
     * 后台取消订单
     * @return
     */
    @POST("order/cancle.do")
    HTTPResponse cancle(@Body String json);
}
