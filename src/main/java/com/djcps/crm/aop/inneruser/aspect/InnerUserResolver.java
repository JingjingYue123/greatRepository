package com.djcps.crm.aop.inneruser.aspect;


import com.djcps.crm.aop.inneruser.annotation.InnerUser;
import com.djcps.crm.commons.config.ParamsConfig;
import com.djcps.crm.inneruser.model.InnerUserModel;
import com.djcps.crm.inneruser.service.InnerUserTokenManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.Cookie;

/**
 * Created by LK on 2017/6/6.
 * 获取内部用户信息
 */
public class InnerUserResolver implements HandlerMethodArgumentResolver {


	@Autowired
	private InnerUserTokenManager innerUserTokenManager;

	@Override
	public boolean supportsParameter(MethodParameter methodParameter) {
		//返回设置注解
		return methodParameter.hasParameterAnnotation(InnerUser.class);
	}

	@Override
	public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
		if(methodParameter.getParameterType().equals(InnerUserModel.class)){
			Cookie[] cookiess = ((ServletWebRequest) nativeWebRequest).getRequest().getCookies();
			if(cookiess != null){
				for (Cookie cookie : cookiess){
					if(ParamsConfig.INNER_USER_COOKIE_NAME.equals(cookie.getName())){
						return innerUserTokenManager.getInnerUserInfoFromRedis(cookie.getValue());
					}
				}
			}
		}
		return null;
	}

}
