package com.djcps.crm.order.server;

import com.djcps.crm.commons.config.ParamsConfig;
import retrofit2.http.Body;
import retrofit2.http.POST;
import rpc.plugin.http.HTTPResponse;
import rpc.plugin.http.RPCClientFields;

/**
 * Created by Lancher on 2017/8/8.
 */
@RPCClientFields(urlfield ="INTEGRAL_SYS",urlbean = ParamsConfig.class)
public interface IntegralHttpService {
    /**
     * 退积分接口
     */
    @POST("integral/change.do")
    HTTPResponse cancelIntegral(@Body String json);
}
