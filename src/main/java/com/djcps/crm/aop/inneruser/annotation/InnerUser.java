package com.djcps.crm.aop.inneruser.annotation;

import java.lang.annotation.*;

/**
 * Created by LK on 2017/6/6.
 * 获取内部用户信息
 */
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface InnerUser {
}
