package com.djcps.crm.finance.server;

import com.djcps.crm.commons.config.ParamsConfig;
import retrofit2.http.Body;
import retrofit2.http.POST;
import rpc.plugin.http.HTTPResponse;
import rpc.plugin.http.RPCClientFields;

/**
 * Created by lmh on 2017/10/13.
 */
@RPCClientFields(urlfield = "IntegralOrder_Mall" ,urlbean = ParamsConfig.class)
public interface IntegralOrderMallServer {

    @POST("order/report.do")
    HTTPResponse mixReportOfMallForms(@Body String json);

}
