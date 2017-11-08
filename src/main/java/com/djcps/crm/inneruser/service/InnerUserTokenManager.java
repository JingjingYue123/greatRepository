package com.djcps.crm.inneruser.service;

import com.djcps.crm.commons.config.ParamsConfig;
import com.djcps.crm.inneruser.model.InnerUserModel;

import com.djcps.crm.commons.utils.CookieUtils;
import com.djcps.crm.inneruser.model.*;
import com.djcps.crm.inneruser.server.DJAuth;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import rpc.plugin.http.HTTPResponse;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import java.util.HashMap;
import java.util.Map;

import static com.djcps.crm.commons.msg.MsgTemplate.failureMsg;
import static com.djcps.crm.commons.utils.DJGson.gson;
import static com.djcps.crm.inneruser.enums.InnerUserMsgEnum.PARAMS_ERROR;


/**
 * 管理token
 * Created by truth on 2017-03-17.
 */
@Component
public class InnerUserTokenManager {


	private Logger logger = LoggerFactory.getLogger(InnerUserTokenManager.class);

	@Autowired
	private DJAuth djAuth;

	@Resource(name = "crmJedisPool")
	private JedisPool userRedisTemplate;

	@Resource(name = "userJedisPool")
	private JedisPool userTemplate;


	/**
	 * 获取用户Code
	 *
	 * @param username
	 * @return String Null
	 */
	private String getUserCode(String username) {
		HTTPResponse httpResponse = djAuth.getCode(username);
		if (httpResponse.isSuccessful()) {
			try {
				DJAuthCodeModel djAuthCodeModel =gson.fromJson(httpResponse.getBodyString(),DJAuthCodeModel.class);
				if (djAuthCodeModel.isSuccess()) {
					String Code = djAuthCodeModel.getCode();
					return Code;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * App方式登录
	 * @param username
	 * @param password
	 * @return String null
	 */
	public Map<String, String> InnerUserLoginTokenWithApp(String username, String password) {
		String code = getUserCode(username);
		if (code != null){
			password = DigestUtils.md5Hex(DigestUtils.md5Hex(password) + code);
			HTTPResponse httpResponse = djAuth.getApplogin(username, password, "DJCRM");
			if(httpResponse.isSuccessful()){
				try {
					Map<String, String> map = new HashMap<>();
					DJAuthloginAppModel logoutModel = gson.fromJson(httpResponse.getBodyString(), DJAuthloginAppModel.class);
					if(logoutModel != null && logoutModel.isSuccess()){
						map.put("token", logoutModel.getToken());
						return map;
					} else if(logoutModel != null) {
						map.put("msg", logoutModel.getMsg());
						return map;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}


	/**
	 * 不同系统之间交换token
	 *
	 * @param token the token
	 * @param sys   the sys
	 * @return string
	 */
	public String swap(String token, String sys) {
		HTTPResponse httpResponse = djAuth.getTokenLogin(token, sys);
		if (httpResponse.isSuccessful()) {
			try {
				DJAuthTokenLoginModel logoutModel = gson.fromJson(httpResponse.getBodyString(), DJAuthTokenLoginModel.class);
				if(logoutModel.isSuccess()){
					return logoutModel.getUrl();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * 用户登出系统
	 *
	 * @param token the token
	 * @return string
	 */
	public boolean signout(String token) {
		HTTPResponse httpResponse = djAuth.getLogout(token);
		if (httpResponse.isSuccessful()) {
			try {
				DJAuthLogoutModel logoutModel = gson.fromJson(httpResponse.getBodyString(), DJAuthLogoutModel.class);
				return logoutModel.isSuccess();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	/**
	 * 置换token,将临时的转换为固定token
	 *
	 * @param onceToken the once token
	 * @return the object
	 */
	public String exchangeToke(final String onceToken) {
		HTTPResponse httpResponse = djAuth.getExchangetoken(onceToken);
		if (httpResponse.isSuccessful()) {
			String body = httpResponse.getBodyString();
			try {
				DJAuthExchangetokenModel djAuthModel = gson.fromJson(body, DJAuthExchangetokenModel.class);
				if (djAuthModel.isSuccess()) {
					String token = djAuthModel.getToken();
					return token;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * 更改用户密码
	 *
	 * @param token
	 * @param oldPassword
	 * @param newPassword
	 * @return
	 */
	public boolean changeInnerUserPassword(String token, String oldPassword, String newPassword) {
		InnerUserModel innerUserModel = getInnerUserInfoFromRedis(token);
		String code = getUserCode(innerUserModel.getUids());
		if (innerUserModel != null && code != null) {
			return false;
		}
		HTTPResponse httpResponse = djAuth.getCode(innerUserModel.getUids());
		if (httpResponse.isSuccessful()) {
			try {
				int userid = innerUserModel.getId();
				oldPassword = DigestUtils.md5Hex(DigestUtils.md5Hex(oldPassword) + code);
				newPassword = DigestUtils.md5Hex(DigestUtils.md5Hex(newPassword) + code);
				HTTPResponse httpResponse1 = djAuth.getModifyPassword(Integer.toString(userid), oldPassword, newPassword);
				if (httpResponse1.isSuccessful()) {
					DJAuthModifyPasswordModel djAuthModifyPasswordModel = gson.fromJson(httpResponse1.getBodyString(), DJAuthModifyPasswordModel.class);
					return djAuthModifyPasswordModel.isSuccess();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return false;
	}


	/**
	 * 设置保存用户信息保存到Cookie
	 *
	 * @param value
	 * @param response
	 * @return
	 */
	public boolean setInnerUserSaveCookie(String value, HttpServletResponse response) {
		if (value != null) {
			CookieUtils.addCookie(ParamsConfig.INNER_USER_COOKIE_NAME, value, response);
			return true;
		}
		return false;
	}

	/**
	 * 读取redis库，获取用户信息
	 *
	 * @param token the token
	 * @return InnerUser, null
	 */
	public InnerUserModel getInnerUserInfoFromRedis(String token) {
		logger.debug("getInnerUserInfoFromRedis0:==================");
		InnerUserModel innerUserModel = new InnerUserModel();
		if (token != null) {
			Jedis redis = null;
			try {
				redis = userTemplate.getResource();
				String userinfo = redis.get(token);
				logger.debug("getInnerUserInfoFromRedis1:=================="+userinfo);
				//拿到内部用户信息
				innerUserModel = gson.fromJson(userinfo, InnerUserModel.class);
				logger.debug("getInnerUserInfoFromRedis2:=================="+userinfo);
				//用户组织信息
				userinfo = redis.get("userInfo"+innerUserModel.getId());
				innerUserModel = gson.fromJson(userinfo, InnerUserModel.class);
				logger.debug("getInnerUserInfoFromRedis3:=================="+userinfo);
				return innerUserModel;
			} catch (Exception e) {
				e.printStackTrace();
				logger.debug("getInnerUserInfoFromRedis4:=================="+e.toString());
			} finally {
				if (redis != null) {
					redis.close();
				}
			}
		}
		logger.debug("getInnerUserInfoFromRedis5:==================");
		return null;
	}
}
