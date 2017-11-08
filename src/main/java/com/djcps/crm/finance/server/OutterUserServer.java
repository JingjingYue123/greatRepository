package com.djcps.crm.finance.server;

import com.djcps.crm.commons.config.ParamsConfig;
import retrofit2.http.Body;
import retrofit2.http.POST;
import rpc.plugin.http.HTTPResponse;
import rpc.plugin.http.RPCClientFields;

/**
 * Created by jmb on 2017/8/22.
 */
@RPCClientFields(urlfield ="OUTER_USER_SYS",urlbean = ParamsConfig.class)
public interface OutterUserServer{


    /**
     * 根据模糊查询用户手机获取用户基本信息列表
     * @param json
     * @return
     */
    @POST("outerUser/selectPhones.do")
    HTTPResponse selectPhones(@Body String json);


    /**
     * 获取客户基础信息（批量）
     * 分条件查询条件可选（用户id，手机号，认证名）
     * @param json
     * @return
     */
    @POST("outerUser/selectUser.do")
    HTTPResponse selectUser(@Body String json);


    /**
     * 根据手机号集合获取客户信息（批量）
     * @param json
     * @return
     */
    @POST("outerUser/getUserByPhones.do")
    HTTPResponse getUserByPhones(@Body String  json);

    /**
     * 根据userid获取用户信息
     * @param json
     * @return
     */
    @POST("outerUser/getUserBaseInfo.do")
    HTTPResponse getUserBaseInfo(@Body String json);

    /**
     * 根据用户认证名获取用户信息列表
     * @param json
     * @return
     */
    @POST("outerUser/selectCertificateName.do")
    HTTPResponse selBaseUserByName(@Body String json);
}
