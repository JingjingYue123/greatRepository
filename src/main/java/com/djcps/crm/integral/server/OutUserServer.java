package com.djcps.crm.integral.server;

import com.djcps.crm.commons.config.ParamsConfig;
import retrofit2.http.Body;
import retrofit2.http.POST;
import rpc.plugin.http.HTTPResponse;
import rpc.plugin.http.RPCClientFields;

/**
 * Created by TruthBean on 2017-08-09 09:49.
 */
@RPCClientFields(urlfield ="OUTER_USER_SYS",urlbean = ParamsConfig.class)
public interface OutUserServer {

    /**
     * 根据手机号码获取用户信息
     */
    @POST("outerUser/getUserByPhones.do")
    HTTPResponse getOutUserInfoByPhone(@Body String json);

    /**
     * 根据用户ID批量获取用户信息
     */
    @POST("outerUser/getUserBaseInfo.do")
    HTTPResponse getOutUserInfoListByUserIds(@Body String json);

    /**
     * 模糊查询客户名称
     * @param json
     * @return
     */
    @POST("outerUser/getKeyAreaByName.do")
    HTTPResponse getUserInfoBySearchName(@Body String json);

    /**
     * 模糊查询客户手机号码
     * @param json
     * @return
     */
    @POST("outerUser/getKeyAreaByPhone.do")
    HTTPResponse getUserInfoBySearchPhone(@Body String json);

    /**
     * 批量查询用户信息
     * @param json
     * @return
     */
    @POST("outerUser/selectUser.do")
    HTTPResponse getUserInfoBySearch(@Body String json);
}
