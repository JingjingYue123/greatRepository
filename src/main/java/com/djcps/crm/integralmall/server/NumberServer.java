package com.djcps.crm.integralmall.server;

import com.djcps.crm.commons.config.ParamsConfig;
import retrofit2.http.*;
import rpc.plugin.http.HTTPResponse;
import rpc.plugin.http.RPCClientFields;

/**
 * Created by L-wenbin on 2017/9/18.
 */
@RPCClientFields(urlfield ="NUMBER_SYS",urlbean = ParamsConfig.class)
public interface NumberServer {
    /**
     * 获取编号
     * @return
     */
    @GET("getnumber.do")
    HTTPResponse getNumber(@Query("count") int count);
}
