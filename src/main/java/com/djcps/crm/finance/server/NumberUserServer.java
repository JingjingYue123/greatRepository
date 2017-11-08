package com.djcps.crm.finance.server;

import com.djcps.crm.commons.config.ParamsConfig;
import retrofit2.http.Body;
import retrofit2.http.POST;
import rpc.plugin.http.HTTPResponse;
import rpc.plugin.http.RPCClientFields;
/**
 * Created by lmh on 2017/8/24.
 */
@RPCClientFields(urlfield ="NUMBER_SYS",urlbean = ParamsConfig.class)
public interface NumberUserServer {
    /**
     * 请求编号服务—充值/扣款保存
     * @param json
     * @return
     */
    @POST("getnumber.do")
    HTTPResponse getOrderId(@Body String json);

}
