package com.djcps.crm.aop.crmurlauth;

import org.springframework.web.bind.annotation.Mapping;

import java.lang.annotation.*;

/**
 * 用户控制CRM系统权限
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Mapping
public @interface CrmUrlAuth {

	/**
	 * 是否需要登录 0拦截 1不拦截
	 * @return
	 */
	int NO_NEED_LOGIN() default 0;

	/**
	 * 接口是否有效 0不启用 1启用
	 * @return
	 */
	int PORT_IS_USEFUL() default 1;
}
