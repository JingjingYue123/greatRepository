package com.djcps.crm.integral.server;

import com.djcps.crm.commons.config.ParamsConfig;
import retrofit2.http.Body;
import retrofit2.http.POST;
import rpc.plugin.http.HTTPResponse;
import rpc.plugin.http.RPCClientFields;

/**
 * Created by TruthBean on 2017-08-09 19:38.
 */
@RPCClientFields(urlfield ="PRODUCT_URL",urlbean = ParamsConfig.class)
public interface ProductServer {

    /**
     * 通过合作方ID获取产品列表
     */
    @POST("external/loadProductInfo.do")
    HTTPResponse getProductListByPartnerId(@Body String json);
}
