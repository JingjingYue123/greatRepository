package com.djcps.crm.filter.sysinnerurlinterceptor.component;

import com.djcps.crm.aop.crmurlauth.CrmUrlAuth;
import com.djcps.crm.commons.config.ParamsConfig;
import com.djcps.crm.filter.sysinnerurlinterceptor.service.SysAuthInterceptorServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 权限拦截器
 * Created by TruthBean on 2017-05-11 11:12.
 */
public class SysAuthInterceptor extends HandlerInterceptorAdapter {

	private static final Logger logger = LoggerFactory.getLogger(SysAuthInterceptor.class);

	@Autowired
	private SysAuthInterceptorServiceImpl sais;
   
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    	//是否启用拦截器
    	if(!ParamsConfig.SYS_AUTH_INTERCEPTOR){
    		return true;
    	}
    	//得到方法信息数据
	    if(handler == null){
    		return true;
	    }
    	HandlerMethod handlerMethod = (HandlerMethod)handler;
    	Class clz = handlerMethod.getBean().getClass();
	    //牵涉到AOP的代理问题进行处理
    	if(clz.getName().indexOf("$$EnhancerBySpringCGLIB")>-1){
		    clz = (Class)clz.getAnnotatedSuperclass().getType();
	    }
    	String firsturi = "";
    	if(clz.isAnnotationPresent(RequestMapping.class)){
    		RequestMapping mapping =  (RequestMapping) clz.getAnnotation(RequestMapping.class);
    		//得到controller上的路径
    		firsturi = mapping.value()[0];
    	}
    	Method method = handlerMethod.getMethod();
    	RequestMapping requestMapping = method.getAnnotation(RequestMapping.class);
    	//获取特定的CRM权限说明
	    CrmUrlAuth crmUrlAuth = null;
	    if(method.isAnnotationPresent(CrmUrlAuth.class)){
		    crmUrlAuth = method.getAnnotation(CrmUrlAuth.class);
	    }
    	//得到uri路径
    	String uri = firsturi + requestMapping.value()[0] ;
        //拦截器权限判断
        return sais.interceptorCheck(requestMapping.method()[0]+"#",uri,crmUrlAuth, request, response);
    }
   

}
