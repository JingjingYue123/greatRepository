package com.djcps.crm.inneruser.server;


import com.djcps.crm.commons.config.ParamsConfig;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rpc.plugin.http.HTTPResponse;
import rpc.plugin.http.RPCClientFields;

/**
 * Created by LK on 2017/6/5.
 */
@RPCClientFields(urlfield ="INNER_USER_URL",urlbean = ParamsConfig.class)
public interface DJAuth {

	/**
	 * APP登录接口
	 */
	@FormUrlEncoded
	@POST("applogin.do")
	HTTPResponse getApplogin(@Field("username") String username,
	                         @Field("password") String password,
	                         @Field("appname") String appname);

	/**
	 * 置换固定Token
	 */
	@FormUrlEncoded
	@POST("exchangetoken.do")
	HTTPResponse getExchangetoken(@Field("onceToken") String onceToken);

	/**
	 * 退出登录
	 */
	@FormUrlEncoded
	@POST("logout.do")
	HTTPResponse getLogout(@Field("token") String token);

	/**
	 * 系统登录切换
	 */
	@FormUrlEncoded
	@POST("tokenlogin.do")
	HTTPResponse getTokenLogin(@Field("token") String token, @Field("appname") String appname);

	/**
	 * 修改密码
	 */
	@FormUrlEncoded
	@POST("modifypassword.do")
	HTTPResponse getModifyPassword(@Field("userid") String userid,
	                               @Field("oldpassword") String oldpassword,
	                               @Field("newpassword") String newpassword);

	/**
	 * 获取用户code
	 */
	@FormUrlEncoded
	@POST("getcode.do")
	HTTPResponse getCode(@Field("username") String username);

}