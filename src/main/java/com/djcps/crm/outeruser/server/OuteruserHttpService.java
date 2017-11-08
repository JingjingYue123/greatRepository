package com.djcps.crm.outeruser.server;


import com.djcps.crm.commons.config.ParamsConfig;
import retrofit2.http.Body;
import retrofit2.http.POST;
import rpc.plugin.http.HTTPResponse;
import rpc.plugin.http.RPCClientFields;

/**
 * Created by Lancher on 2017/7/3.
 */

@RPCClientFields(urlfield ="OUTER_USER_SYS",urlbean = ParamsConfig.class)
public interface OuteruserHttpService {

    /**
     * 外部用户重置支付密码
     */
    @POST("outerUser/resetPasswordRandom.do")
    HTTPResponse resetPayPassword(@Body String json);

    /**
     * 启用帐号
     */
    @POST("outerUser/enableAccount.do")
    HTTPResponse enableAccount(@Body String json);

    /**
     * 禁用帐号
     */
    @POST("outerUser/disableAccount.do")
    HTTPResponse disableAccount(@Body String json);

    @POST("outerUser/getDetailInfo.do")
    HTTPResponse getDetailInfo(@Body String json);

    /**
     * 获取外部用户列表
     */
    @POST("outerUser/getMainUserList.do")
    HTTPResponse getMainUserList(@Body String userParam);

    /**
     * 获取认证信息列表
     */
    @POST("certificate/getList.do")
    HTTPResponse getAuditList(@Body String json);

    /**
     * 查询认证审核详情
     */
    @POST("certificate/getDetailInfo.do")
    HTTPResponse getAuditDetail(@Body String json);

    /**
     * 认证审核
     */
    @POST("certificate/censor.do")
    HTTPResponse userAudit (@Body String json);

    /**
     * 认证图片临时URL获取接口
     */
    @POST("certificate/getPictures.do")
    HTTPResponse getPicture (@Body String json);
}