package com.djcps.crm.finance.server;

import com.djcps.crm.commons.config.ParamsConfig;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import rpc.plugin.http.HTTPResponse;
import rpc.plugin.http.RPCClientFields;

/**
 * Created by zhangminghui on 2017/8/24.
 */
@RPCClientFields(urlfield ="PAYBANK_SYS",urlbean = ParamsConfig.class)
public interface PayBankServer {

    @Headers("Content-Type:application/json")
    @POST("Paybankwithdrawallist.do")
    HTTPResponse payBankwithdrawallist(@Body String json);

    /**
     * 第三方支付列表
     * @param json
     * @return
     */
    @Headers("Content-Type:application/json")
    @POST("PayOrderList.do")
    HTTPResponse thirdPaymentForms(@Body String json);
}
