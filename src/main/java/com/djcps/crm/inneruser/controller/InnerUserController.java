package com.djcps.crm.inneruser.controller;

import com.baidu.unbiz.fluentvalidator.ComplexResult;
import com.baidu.unbiz.fluentvalidator.FluentValidator;
import com.djcps.crm.aop.crmurlauth.CrmUrlAuth;
import com.djcps.crm.aop.inneruser.annotation.InnerUser;
import com.djcps.crm.aop.inneruser.annotation.InnerUserToken;
import com.djcps.crm.aop.jsonmodel.annotation.JsonBodyModel;
import com.djcps.crm.aop.log.AddLog;
import com.djcps.crm.commons.config.ParamsConfig;
import com.djcps.crm.commons.utils.CookieUtils;
import com.djcps.crm.commons.utils.HibernateValidator;
import com.djcps.crm.inneruser.model.InnerChangeUserPasswordModel;
import com.djcps.crm.inneruser.model.InnerUserModel;
import com.djcps.crm.inneruser.model.InnerLoginModel;
import com.djcps.crm.inneruser.server.DJAuth;
import com.djcps.crm.inneruser.service.InnerUserTokenManager;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

import static com.baidu.unbiz.fluentvalidator.ResultCollectors.toComplex;
import static com.djcps.crm.commons.msg.MsgTemplate.failureMsg;
import static com.djcps.crm.commons.msg.MsgTemplate.successMsg;
import static com.djcps.crm.commons.utils.DJGson.gson;
import static com.djcps.crm.inneruser.enums.InnerUserMsgEnum.*;

/**
 * Created by TruthBean on 2017-03-15
 * Updated by others after 2017-03-15
 * 内部用户登录接口
 */
@Controller
@RequestMapping("/inneruser")
@ResponseBody
public class InnerUserController {

	private static final Logger logger = LoggerFactory.getLogger(InnerUserController.class);

	private Gson gson = new Gson();
	private JsonParser jsonParser = new JsonParser();

	@Resource
	private InnerUserTokenManager innerUserTokenManager;

	/**
	 * APP登录页面
	 * @param innerLoginModel
	 * @return
	 */
	@RequestMapping(name = "APP登录页面",value = "/login", method = {RequestMethod.POST})
	@CrmUrlAuth(NO_NEED_LOGIN = 1)
	public Map<String, Object> login(@JsonBodyModel InnerLoginModel innerLoginModel) {
		try {
			ComplexResult ret = FluentValidator.checkAll().failFast()
					.on(innerLoginModel, new HibernateValidator<InnerLoginModel>().Validator())
					.doValidate()
					.result(toComplex());
			if(ret.isSuccess()){
				Map<String, String> result = innerUserTokenManager.InnerUserLoginTokenWithApp(innerLoginModel.getUsername(), innerLoginModel.getPassword());
				if (result != null){
					if (result.containsKey("token")){
						Map<String, String> map = new HashMap<>();
						map.put("token", result.get("token"));
						return successMsg(map);
					} else if (result.containsKey("msg")){
						return failureMsg(result.get("msg"));
					}
				}

			}
		}catch (Exception e){
			e.printStackTrace();
		}
		return failureMsg(PARAMS_ERROR);
	}


	/**
	 * 该接口用于获取用户信息
	 *
	 * @param token token
	 * @return json数据 ，包含用户信息
	 */
	@RequestMapping(name = "该接口用于获取用户信息",value = "/info",method = {RequestMethod.GET})
	@AddLog(module = "内部用户",value = "该接口用于获取用户信息")
	public Map<String, Object> getInfo(@InnerUserToken String token) {
		InnerUserModel innerUserModel = innerUserTokenManager.getInnerUserInfoFromRedis(token);
		Map<String, Object> map = new HashMap<>();
		map.put("user", innerUserModel);
		return successMsg(map);
	}

	/**
	 * 该接口用于修改密码
	 * @param innerrcupm
	 * @param token
	 * @return
	 */
	@RequestMapping(name = "该接口用于修改密码",value = "/passwd",method = {RequestMethod.GET})
	@AddLog(module = "内部用户",value = "该接口用于修改密码")
	public Map<String, Object> changeUserPassword(@JsonBodyModel InnerChangeUserPasswordModel innerrcupm ,@InnerUserToken String token) {
		try {
			ComplexResult ret = FluentValidator.checkAll().failFast()
					.on(innerrcupm, new HibernateValidator<InnerChangeUserPasswordModel>().Validator())
					.doValidate()
					.result(toComplex());
			if(ret.isSuccess()){
				if (innerUserTokenManager.changeInnerUserPassword(token,innerrcupm.getOldPassword() , innerrcupm.getNewPassword())) {
					return successMsg(CHANGE_PSWD_SUCCESS);
				}
			}
		}catch (Exception e){
			e.printStackTrace();
		}
		return failureMsg(CHANGE_PSWD_FAILURE);
	}

	//region 该接口用于不同系统切换间交换token
	/**
	 * 该接口用于不同系统切换间交换token
	 * 需求中不予支持切换系统 2017-06-26 作废 李阔
	 * 代码予以保留，以供以后有需要支持可能
	 * @param oldToken 用户在线token
	 * @param sys      系统名称
	 * @return json格式的数据 ，包含返回跳转的url
	 */
	@RequestMapping(name = "该接口用于不同系统切换间交换token",value = "/switchsys",method = {RequestMethod.GET})
	public Map<String, Object> switchSys(@RequestParam(name = "oldtoken", required = false, defaultValue = "") String oldToken,
	                                     @RequestParam(name = "sysname", required = false, defaultValue = "") String sys) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (!"".equals(oldToken) && !"".equals(sys)) {
			String urlWithToken = innerUserTokenManager.swap(oldToken, sys);
			map.put("url", urlWithToken);
			return successMsg(map);
		}
		return failureMsg(PARAMS_NULL);
	}
	//endregion

	/**
	 * 将一次性Token转换为固定Token
	 * 由于Oncetoken传递数据格式由CRM内部统一用户服务传递
	 * @return json格式的数据 ，包含返回跳转的url
	 */
	@RequestMapping(name = "将一次性Token转换为固定Token", value = "/handleoncetoken", method = RequestMethod.POST)
	@CrmUrlAuth(NO_NEED_LOGIN = 1)
	public Map<String, Object> getToken(@RequestBody(required = false) String json, HttpServletResponse response) {
		System.out.println(json);
		try {
			JsonObject jsonObject = jsonParser.parse(json).getAsJsonObject();
			if (jsonObject.has("oncetoken")) {
				String oncetoken = jsonObject.get("oncetoken").getAsString();
				if (!"".equals(oncetoken)) {
					String token = innerUserTokenManager.exchangeToke(oncetoken);
					if(token != null){
						if (innerUserTokenManager.setInnerUserSaveCookie(token, response)) {
							return successMsg(innerUserTokenManager.getInnerUserInfoFromRedis(token));
						}
					}else{
						return failureMsg(TOEKN_NULL);
					}
				}
			}
		}catch (Exception e){
			e.printStackTrace();
			return failureMsg(PARAMS_ERROR);
		}
		return failureMsg(PARAMS_NULL);
	}

	/**
	 * 用户登出系统
	 *
	 * @param token the token
	 * @return map
	 */
	@RequestMapping(name = "用户登出系统", value = "/signout")
	public Map<String, Object> signout(@InnerUserToken String token, HttpServletResponse response) {
		boolean msg = innerUserTokenManager.signout(token);
		//无论是否成功退出内部统一登录系统，本系统内直接可以退出
		CookieUtils.deleteCookieByName(ParamsConfig.INNER_USER_COOKIE_NAME, response);
		Map<String, String> map = new HashMap<>();
		map.put("url", ParamsConfig.INNER_USER_UNIFIED_LOGIN_URL);
		return successMsg(map);
	}

}
