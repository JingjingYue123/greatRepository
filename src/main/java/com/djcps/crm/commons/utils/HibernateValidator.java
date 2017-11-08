package com.djcps.crm.commons.utils;

import com.baidu.unbiz.fluentvalidator.jsr303.HibernateSupportedValidator;

import javax.validation.Validation;

/**
 * Created by LK on 2017/6/26.
 */
public class HibernateValidator<T> {

	public HibernateSupportedValidator<T> Validator(){
		return new HibernateSupportedValidator<T>().setHiberanteValidator(Validation.buildDefaultValidatorFactory().getValidator());

	}
}
