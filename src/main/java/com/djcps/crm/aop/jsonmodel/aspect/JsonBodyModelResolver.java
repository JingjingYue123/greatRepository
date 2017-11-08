package com.djcps.crm.aop.jsonmodel.aspect;


import com.djcps.crm.aop.jsonmodel.annotation.JsonBodyModel;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import static com.djcps.crm.commons.utils.DJGson.gson;

/**
 * Created by LK on 2017/6/6.
 * 获取内部用户信息
 */
public class JsonBodyModelResolver implements HandlerMethodArgumentResolver {

	@Override
	public boolean supportsParameter(MethodParameter methodParameter) {
		//返回设置注解
		return methodParameter.hasParameterAnnotation(JsonBodyModel.class);
	}

	@Override
	public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
		Class c = methodParameter.getParameterType();
		try {
			InputStream d = ((ServletWebRequest) nativeWebRequest).getRequest().getInputStream();
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			int i = -1;
			while ((i = d.read()) != -1) {
				baos.write(i);
			}
			String data =  new String(baos.toByteArray(), "UTF-8");
			return gson.fromJson(data,c);
		}catch (Exception e) {
			return null;
		}
	}

}
