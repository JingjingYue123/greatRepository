package com.djcps.crm.commons.fluentvalidator;

import com.baidu.unbiz.fluentvalidator.ValidatorContext;

/**
 * Created by LK on 2017/6/6.
 * 作用于校验匿名类
 */
public abstract class ValidateData<T> {
	abstract boolean Validate(ValidatorContext context,T obj);
}
