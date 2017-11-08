package com.djcps.crm.finance.server;

import com.djcps.crm.commons.config.ParamsConfig;
import retrofit2.http.Body;
import retrofit2.http.POST;
import rpc.plugin.http.HTTPResponse;
import rpc.plugin.http.RPCClientFields;

/**
 * Created by jmb on 2017/9/9.
 */
@RPCClientFields(urlfield = "INTEGRAL_SYS" ,urlbean = ParamsConfig.class)
public interface IntegralServer {

    /**
     * 根据订单id查询订单退还积分
     * @param json
     * @return
     */
    @POST("integral/journal/queryByOrderId.do")
    HTTPResponse getOrderIntergral(@Body String json);
}
