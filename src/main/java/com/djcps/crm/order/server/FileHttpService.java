package com.djcps.crm.order.server;

import com.djcps.crm.commons.config.ParamsConfig;
import retrofit2.http.Body;
import retrofit2.http.POST;
import rpc.plugin.http.HTTPResponse;
import rpc.plugin.http.RPCClientFields;

/**
 * Created by Lancher on 2017/8/24.
 */
@RPCClientFields(urlfield ="FILE_SYSTEM_URL",urlbean = ParamsConfig.class)
public interface FileHttpService {


    @POST("order/exportExcel.do")
    HTTPResponse exportExcel(@Body String json);
}
