package com.djcps.crm.filter.sysurlistener.component;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import com.djcps.crm.aop.crmurlauth.CrmUrlAuth;
import com.djcps.crm.commons.config.ParamsConfig;
import com.djcps.crm.filter.sysurlistener.service.RequestMappingSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.djcps.crm.filter.sysurlistener.model.Furl;

@Component
public class RequestMappingContextRefreshedEvent implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired
	private RequestMappingSerivce rsi;

	//解决容器启动的时候，重复扫描的问题。
	private static boolean run = false;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		if(ParamsConfig.REQUEST_MAPPING_CONTEXT_REFRESHED_EVENT){
			if (run == true) {return;}run = true;
			//region 扫描项目内所有的RequestMapping注解
			Map<String, Object> beans = event.getApplicationContext().getBeansWithAnnotation(Controller.class);
			Class<? extends Object> clazz = null;
			//记录url
			HashMap<String,Furl> InterfaceUrl = new HashMap<String,Furl>();
			for (Map.Entry<String, Object> entry : beans.entrySet()) {
				//获取到实例对象的class信息
				clazz = entry.getValue().getClass();
				String annotationFromClass = "";
				//受到AOP注解的影响，需要判断类是否是代理类
				if(! (clazz.getAnnotatedSuperclass().getType().getTypeName().equals("java.lang.Object"))){
					clazz = (Class)clazz.getAnnotatedSuperclass().getType();
				}
				if (clazz.isAnnotationPresent(RequestMapping.class)) {
					RequestMapping annotation = clazz.getAnnotation(RequestMapping.class);
					//获取到Control上面是否带有控制器，RequestMapping有多个路径，默认不允许有。
					annotationFromClass = annotation.value()[0];
				}
				Method[] methods = clazz.getDeclaredMethods();

				//所有的方法上的注解
				for (Method m : methods) {
					m.setAccessible(true);
					RequestMapping requ = m.getAnnotation(RequestMapping.class);
					if (requ != null) {
						if(requ.value().length > 0){
							System.out.println(annotationFromClass + requ.value()[0] + ".do");
							Furl furl = new Furl();
							CrmUrlAuth crmUrlAuth = null;
							if(m.isAnnotationPresent(CrmUrlAuth.class)){
								crmUrlAuth = m.getAnnotation(CrmUrlAuth.class);
							}
							furl.setFname(requ.name())
									.setFurl(annotationFromClass + requ.value()[0])
									.setFlogintype(crmUrlAuth == null ? 0 : crmUrlAuth.NO_NEED_LOGIN())
									.setFeffect(crmUrlAuth == null ? 1 : crmUrlAuth.PORT_IS_USEFUL());
							if(furl != null){
								InterfaceUrl.put(furl.getFurl(),furl);
							}
						}else{
							System.out.println("异常："+annotationFromClass+ "异常方法"+m.getName());
						}
					}
				}
			}
			//endregion
			rsi.Initialize(InterfaceUrl);
		}
	}


}
