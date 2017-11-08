package com.djcps.crm.aop.log;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AddLog {
	/**
	 * 操作内容描述
	 * @return
	 */
	String value();
	/**
	 * 系统模块名
	 * @return
	 */
	String module();
}
