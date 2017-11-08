package com.djcps.crm.order.server;


import com.djcps.crm.commons.config.ParamsConfig;
import retrofit2.http.Body;
import retrofit2.http.POST;
import rpc.plugin.http.HTTPResponse;
import rpc.plugin.http.RPCClientFields;

/**
 * Created by Lancher on 2017/7/3.
 */

@RPCClientFields(urlfield ="ORDER_SYS",urlbean = ParamsConfig.class)
public interface OrderHttpService {

    /**
     * 查询订单列表
     */
    @POST("order/load.do")
    HTTPResponse load(@Body String json);

    /**
     * 东经查询订单列表
     */
    @POST("order/DJload.do")
    HTTPResponse DJload(@Body String json);

    /**
     * 取消订单时展示给前端的资料
     */
    @POST("order/cancelOrderInfo.do")
    HTTPResponse cancelOrderInfo(@Body String json);

    /**
     * 取消订单
     */
    @POST("order/cancelOrder.do")
    HTTPResponse cancelOrder(@Body String json);

    /**
     * 手动分发订单
     */
    @POST("order/distributionOrder.do")
    HTTPResponse distributionOrder(@Body String json);

    /**
     * 导出订单
     */
//    @POST("order/exportExcel.do")
    /*@POST("corder/grouponExportExcel.do.do")
    HTTPResponse exportExcel(@Body String json);*/

    /**
     * 自动分发订单
     */
    @POST("order/autoDistributionOrder.do")
    HTTPResponse autoDistributionOrder(@Body String json);

    /**
     * erp导入成功之后，更新订单
     */
    @POST("order/comfirmDistributionOrder.do")
    HTTPResponse comfirmDistributionOrder(@Body String json);

    /**
     * erp发货之后，更新订单
     */
    @POST("order/deliveryERPOrder.do")
    HTTPResponse deliveryERPOrder(@Body String json);

}