package com.djcps.crm.integral.server;

import com.djcps.crm.commons.config.ParamsConfig;
import retrofit2.http.Body;
import retrofit2.http.POST;
import rpc.plugin.http.HTTPResponse;
import rpc.plugin.http.RPCClientFields;

/**
 * Created by TruthBean on 2017-08-08 10:39.
 */
@RPCClientFields(urlfield ="INTEGRAL_SYS",urlbean = ParamsConfig.class,connectTimeout = 214747L,
        writeTimeout= 214747L, readTimeout = 214747L)
public interface IntegralServer {

    @POST("integral/rule/recharge/update.do")
    HTTPResponse updateRechargeIntegralRule(@Body String json);

    /**
     * 添加积分策略
     */
    @POST("integral/rule/add.do")
    HTTPResponse addIntegralRule(@Body String json);

    /**
     * 删除积分策略
     */
    @POST("integral/rule/del.do")
    HTTPResponse delIntegralRule(@Body String json);

    /**
     * 获取用户积分
     */
    @POST("integral/user/list.do")
    HTTPResponse getUserIntegralList(@Body String json);

    /**
     * 获取单个用户积分明细
     */
    @POST("integral/journal/user.do")
    HTTPResponse getUserIntegralJournalList(@Body String json);

    /**
     * 获取多个用户积分明细
     */
    @POST("integral/journal/user/multi.do")
    HTTPResponse getMultiUserIntegralJournalList(@Body String json);

    /**
     * 调整积分
     */
    @POST("integral/change.do")
    HTTPResponse updateUserIntegral(@Body String json);

    /**
     * 根据产品ID获取积分规则
     */
    @POST("integral/rule/queryByFcommodityids.do")
    HTTPResponse queryByFcommodityids(@Body String json);

    /**
     * 更新积分策略
     */
    @POST("integral/rule/update.do")
    HTTPResponse updateIntegralRule(@Body String json);

    /**
     * 获取充值送的积分规则
     */
    @POST("integral/rule/recharge/list.do")
    HTTPResponse listRechargeRules(@Body String json);

    @POST("integral/user/search.do")
    HTTPResponse getUserIntegralBySearch(@Body String json);
}
