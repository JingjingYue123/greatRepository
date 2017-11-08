package com.djcps.crm.aop.jsonmodel.annotation;

import java.lang.annotation.*;

/**
 * Created by LK on 2017/6/28.
 * 对body转换为Model
 * 发生错误返回null
 */
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface JsonBodyModel {
}
