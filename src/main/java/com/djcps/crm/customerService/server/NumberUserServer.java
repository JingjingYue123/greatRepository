package com.djcps.crm.customerService.server;

import com.djcps.crm.commons.config.ParamsConfig;
import retrofit2.http.Body;
import retrofit2.http.POST;
import rpc.plugin.http.HTTPResponse;
import rpc.plugin.http.RPCClientFields;

@RPCClientFields(urlfield ="NUMBER_SYS",urlbean = ParamsConfig.class)
public interface NumberUserServer {
    /**
     * 请求编号服务—客诉流水号
     * @param json
     * @return
     */
    @POST("getnumber.do")
    HTTPResponse getCommenNumber(@Body String json);
}
