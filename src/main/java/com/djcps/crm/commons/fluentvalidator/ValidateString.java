package com.djcps.crm.commons.fluentvalidator;

import com.baidu.unbiz.fluentvalidator.Validator;
import com.baidu.unbiz.fluentvalidator.ValidatorContext;
import com.baidu.unbiz.fluentvalidator.ValidatorHandler;
import com.djcps.crm.commons.msg.MsgInterface;


/**
 * 校验字符串类型类
 *
 * @author 郑天伟
 */
public class ValidateString extends ValidatorHandler<String> implements Validator<String> {


	private static ValidateData validateData = null;


	/**
	 * 不为空，并且长度大于0
	 */
	public ValidateString() {
		Validate(true, true, null);
	}

	/**
	 * 校验非NUll，并且长度大于0
	 *
	 * @param error
	 */
	public ValidateString(final MsgInterface error) {
		Validate(true, true, error);
	}

	/**
	 * 校验字符串是否为空，并且长度大于0
	 *
	 * @param leng  true 长度大于0 false 长度可以为0
	 * @param error
	 */
	public ValidateString(final boolean leng, final MsgInterface error) {
		Validate(leng, true, error);
	}


	/**
	 * 校验字符串是否为空，并且长度大于0
	 *
	 * @param leng  true 长度必须大于0 false 长度可以为0
	 * @param trim  true 去除前后空格
	 * @param error
	 */
	public ValidateString(final boolean leng, final boolean trim, final MsgInterface error) {
		Validate(leng, trim, error);
	}


	/**
	 * 校验字符串长度,字符串不能为NUll
	 *
	 * @param min
	 * @param max
	 */
	public ValidateString(final Integer min, final Integer max) {
		new ValidateString(min, max, true, false, null);
	}

	/**
	 * 校验字符串长度,字符串不能为NUll
	 *
	 * @param min
	 * @param max
	 * @param error
	 */
	public ValidateString(final Integer min, final Integer max, final MsgInterface error) {
		new ValidateString(min, max, true, false, error);
	}

	/**
	 * 判断是否需要去除首尾空格后校验非空，true为需要，默认为false
	 *
	 * @param min
	 * @param max
	 * @param contains true >= <= , false ><
	 * @param trim     true 去除前后空格
	 * @param error
	 */
	public ValidateString(final Integer min, final Integer max, final boolean contains, final boolean trim, final MsgInterface error) {
		Validate(min, max, contains, trim, error);
	}


	/**
	 * 校验字符串是否为空，并且长度大于0
	 *
	 * @param leng  true 长度必须大于0 false 长度可以为0
	 * @param trim  true 去除前后空格
	 * @param error
	 */
	private void Validate(final boolean leng, final boolean trim, final MsgInterface error) {
		validateData = new ValidateData<String>() {
			@Override
			boolean Validate(ValidatorContext context, String obj) {
				String val = obj;
				if (val != null) {
					//除去前后空格
					if (trim) {
						val = obj.trim();
					}
					//长度可以为0
					if (leng) {
						if (val.length() != 0) {
							return true;
						}
					}
				}
				if (error != null) {
					context.addErrorMsg(error.getMsg());
				} else {
					context.addErrorMsg("String IS Null Or String Length = 0");
				}
				return false;
			}
		};
	}


	/**
	 * 判断是否需要去除首尾空格后校验非空，true为需要，默认为false
	 *
	 * @param min
	 * @param max
	 * @param contains true >= <= , false ><
	 * @param trim     true 去除前后空格
	 * @param error
	 */
	private void Validate(final Integer min, final Integer max, final boolean contains, final boolean trim, final MsgInterface error) {
		validateData = new ValidateData<String>() {
			@Override
			boolean Validate(ValidatorContext context, String obj) {
				if (obj != null) {
					if (trim) {
						obj = obj.trim();
					}
					//包含
					if (contains) {
						if (obj.length() >= min && obj.length() <= max) {
							return true;
						}
					} else {
						if (obj.length() > min && obj.length() < max) {
							return true;
						}
					}
				}
				if (error != null) {
					context.addErrorMsg(error.getMsg());
				} else {
					context.addErrorMsg("String IS Null Or String Length Illegal");
				}
				return false;
			}
		};
	}


	/**
	 * 校验正则表达式
	 *
	 * @param enums
	 * @param error
	 */
	private ValidateString(RegExpEnum enums, MsgInterface error) {
		validateData = new ValidateData<String>() {
			@Override
			boolean Validate(ValidatorContext context, String obj) {
				//获取枚举中的正则表达式
				String reg = enums.getReg();
				//进行正则判断
				if (!obj.matches(reg)) {
					context.addErrorMsg(error.getMsg());
					return false;
				}
				return true;
			}
		};
	}

	/**
	 * 校验方法
	 */
	@Override
	public boolean validate(ValidatorContext context, String str) {
		if (validateData != null) {
			return validateData.Validate(context, str);
		} else {
			return false;
		}
	}

}
