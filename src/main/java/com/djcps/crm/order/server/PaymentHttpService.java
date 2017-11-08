package com.djcps.crm.order.server;

import com.djcps.crm.commons.config.ParamsConfig;
import retrofit2.http.Body;
import retrofit2.http.POST;
import rpc.plugin.http.HTTPResponse;
import rpc.plugin.http.RPCClientFields;

/**
 * Created by Lancher on 2017/8/8.
 */
@RPCClientFields(urlfield ="PAYMENT_SYS",urlbean = ParamsConfig.class)
public interface PaymentHttpService {
    /**
     * 退款接口
     */
    @POST("balance/updateBalance.do")
    HTTPResponse updateBalance(@Body String json);
}
