package com.djcps.crm.finance.server;

import retrofit2.http.Body;
import retrofit2.http.POST;
import rpc.plugin.http.HTTPResponse;
import rpc.plugin.http.RPCClientFields;
import com.djcps.crm.commons.config.ParamsConfig;
/**
 * 统一余额服务
 */
@RPCClientFields(urlfield ="PAYMENT_SYS",urlbean = ParamsConfig.class)
public interface PaymentServer {
    /**
     * 余额列表
     * @param json
     * @return
     */
    @POST("balance/listBalance.do")
    HTTPResponse balanceList(@Body String json);
    /**
     * 余额预警列表
     * @param json
     * @return
     */
    @POST("balanceDayCheck/listDayCheckBill.do")
    HTTPResponse balanceWarningList(@Body String json);

    /**
     * 查询订单列表
     * @param json
     * @return
     */
    @POST("rechargeOrder/list.do")
    HTTPResponse getRechargeForms(@Body String json);
    /**
     * 获取余额收款明细
     * @param json
     * @return
     */
    @POST("balance/pagePayeeJournal.do")
    HTTPResponse balanceCollectionForms(@Body String json);

}
