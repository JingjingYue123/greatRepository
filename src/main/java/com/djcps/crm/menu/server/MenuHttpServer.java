package com.djcps.crm.menu.server;

import com.djcps.crm.commons.config.ParamsConfig;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rpc.plugin.http.HTTPResponse;
import rpc.plugin.http.RPCClientFields;

/**
 * Created by TruthBean on 2017/6/27 10:09.
 */
@RPCClientFields(urlfield ="OR_SYS",urlbean = ParamsConfig.class)
public interface MenuHttpServer {

    /**
     * 获取用户全部菜单权限
     * @param fatherId
     * @param userId
     * @return
     */
    @GET("perUserLists.org")
    HTTPResponse getAllMenu(@Query("firstnode") int fatherId, @Query("userid") int userId);

    /**
     * 获取用户全部按钮权限
     * @param fatherId
     * @param userId
     * @return
     */
    @GET("perUserNotPersList.org")
    HTTPResponse getAllButton(@Query("firstnode") int fatherId, @Query("userid") int userId);
}
