package com.djcps.crm.finance.server;

import com.djcps.crm.commons.config.ParamsConfig;
import retrofit2.http.Body;
import retrofit2.http.POST;
import rpc.plugin.http.HTTPResponse;
import rpc.plugin.http.RPCClientFields;

/**
 * Created by jmb on 2017/8/22.
 */
@RPCClientFields(urlfield ="PAYMENT_SYS",urlbean = ParamsConfig.class)
public interface RechargeOrderServer {

    /**
     * 充值订单-审核
     * @param json
     * @return
     */
    @POST("rechargeOrder/audit.do")
    HTTPResponse audit(@Body String json);
    /**
     * 充值/扣款订单-保存
     */
    @POST("rechargeOrder/save.do")
    HTTPResponse saveFinanceOrder(@Body String json);

    /**
     * 充值订单-列表
     */
    @POST("rechargeOrder/list.do")
    HTTPResponse list(@Body String json);

    /**
     * 充值订单—审核列表
     * @param json
     * @return
     */
    @POST("rechargeOrder/auditList.do")
    HTTPResponse auditList(@Body String json);

    /**
     * 获取用户余额
     * @param json
     * @return
     */
    @POST("balance/getBalance.do")
    HTTPResponse getBalance(@Body String json);


}
