package com.djcps.crm.aop.inneruser.annotation;

import java.lang.annotation.*;

/**
 * Created by LK on 2017/6/6.
 * 用于获取到内部用户信息Token
 */
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface InnerUserToken {

}
