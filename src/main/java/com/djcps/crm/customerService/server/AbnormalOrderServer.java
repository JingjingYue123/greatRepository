package com.djcps.crm.customerService.server;

import com.djcps.crm.commons.config.ParamsConfig;
import retrofit2.http.Body;
import retrofit2.http.POST;
import rpc.plugin.http.HTTPResponse;
import rpc.plugin.http.RPCClientFields;

@RPCClientFields(urlfield ="ORDER_SYS",urlbean = ParamsConfig.class)
public interface AbnormalOrderServer {
    /**
     * 异常订单--保存
     * @param json
     * @return
     */
    @POST("custumorService/save.do")
    HTTPResponse saveAbnormal(@Body String json);
    /**
     * 异常订单--关闭
     * @param json
     * @return
     */
    @POST("custumorService/update.do")
    HTTPResponse updateAbnormalOrderStatus(@Body String json);
    /**
     * 异常订单--列表
     * @param json
     * @return
     */
    @POST("custumorService/list.do")
    HTTPResponse selectAbnormalOrderList(@Body String json);
    /**
     * 异常订单--更新
     * @param json
     * @return
     */
    @POST("custumorService/update.do")
    HTTPResponse customerUpdateAbnormalOrder(@Body String json);
    /**
     * 异常订单--更新
     * @param json
     * @return
     */
    @POST("custumorService/update.do")
    HTTPResponse financeUpdateAbnormalOrder(@Body String json);


}
