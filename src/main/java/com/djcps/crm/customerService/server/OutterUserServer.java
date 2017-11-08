package com.djcps.crm.customerService.server;

import com.djcps.crm.commons.config.ParamsConfig;
import retrofit2.http.Body;
import retrofit2.http.POST;
import rpc.plugin.http.HTTPResponse;
import rpc.plugin.http.RPCClientFields;

@RPCClientFields(urlfield ="OUTER_USER_SYS",urlbean = ParamsConfig.class)
public interface OutterUserServer {
    /**
     * 根据用户手机号获取用户基本信息列表
     * @param json
     * @return
     */
    @POST("outerUser/selectUser.do ")
    HTTPResponse selectPhones(@Body String json);

    /**
     *根据用户ids查找认证名
     * @param json
     * @return
     */
    @POST("outerUser/getUserBaseInfo.do")
    HTTPResponse getUserBaseInfo(@Body String json);

}
