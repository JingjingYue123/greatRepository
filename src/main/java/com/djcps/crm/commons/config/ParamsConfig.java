package com.djcps.crm.commons.config;


/**
 * 定义在配置文件里的常量
 * Created by TruthBean on 2017-03-27 09:30.
 * updata by LK on 2017-06-30 16：17
 */
public class ParamsConfig {

	/**
	 * 登录超时
	 */
	public static int LOGIN_TIMEOUT;

	/**
	 * Cookie超时
	 */
	public static int COOKIE_TIMEOUT;

	/**
	 * 轮播图服务
	 */
	public static String BANNER_URL;

	/**
	 *统一文件服务
	 */
	public static String FILE_SYSTEM_URL;

	/**
	 * 是否开启拦截器.
	 */
	public static boolean SYS_AUTH_INTERCEPTOR;

	/**
	 * 是否开启CRM权限拦截器
	 */
	public static boolean SYS_AUTH_CRM_INTERCEPTOR;

	/**
	 * 是否开启注解扫描器
	 */
	public static boolean REQUEST_MAPPING_CONTEXT_REFRESHED_EVENT;

	/**
	 * 内部用户Cookie名称
	 */
	public static String INNER_USER_COOKIE_NAME;

	/**
	 * 统一内部登录地址
	 */
	public static String INNER_USER_UNIFIED_LOGIN_URL;

	/**
	 * CRM保存项目内URL到REDISKEY
	 */
	public static String CRM_URL_REDIS_KEY;

	/**
	 * 内部用户对接URL
	 */
	public static String INNER_USER_URL;

    /**
     * CRM菜单ID
     */
    public static int MENU_FATHER_ID;

    /**
     * 组织机构地址
     */
    public static String OR_SYS;

	/**
	 * 外部服务地址
	 */
	public static String OUTER_USER_SYS;

	/**
	 * 地址服务，连接
	 */
	public static String ADDRESS_SYS;


	/**
	 * 订单服务，连接
	 */
	public static String ORDER_SYS;

	/**
	 * 积分服务
	 */
	public static String INTEGRAL_SYS;

	/**
	 *统一余额服务
	 */
	public static String PAYMENT_SYS;

	/**
	 * 产商品
	 */
	public  static  String PRODUCT_URL;

	/**
	 * 第三方支付服务
	 */
	public static String PAYBANK_SYS;

	/**
	 * 编号服务
	 */
	public static String NUMBER_SYS;

	/**
	 * 积分商城,产品服务
	 */
	public static String IntegralProduct_Mall;
	/**
	 * 积分商城,产品服务
	 */
	public static String IntegralOrder_Mall;

	/**
	 * 登录超时
	 */
	public void setLoginTimeout(int loginTimeout) {
		LOGIN_TIMEOUT = loginTimeout;
	}

	/**
	 * Cookie超时
	 */
	public void setCookieTimeout(int CookieTimeout) {
		COOKIE_TIMEOUT = CookieTimeout;
	}

	/**
	 * 轮播图服务
	 */
	public void setBannerUrl(String bannerUrl) {
		BANNER_URL = bannerUrl;
	}

	/**
	 *统一文件服务
	 */
	public void setFileSystemUrl(String fileSystemUrl) {
		FILE_SYSTEM_URL = fileSystemUrl;
	}

	/**
	 * 访问外部用户服务
	 * @param outer_user_sys
	 */
	public void setOuterUserSys(String outer_user_sys) {
		OUTER_USER_SYS = outer_user_sys;
	}

    /**
     * 是否开启拦截器.
     */
    public void setSysAuthInterceptor(boolean sysAuthInterceptor) {
        SYS_AUTH_INTERCEPTOR = sysAuthInterceptor;
    }

	/**
	 * 是否开启CRM权限拦截器
	 */
	public void setSysAuthCrmInterceptor(boolean sysAuthCrmInterceptor) {
		SYS_AUTH_CRM_INTERCEPTOR = sysAuthCrmInterceptor;
	}

	/**
	 * 是否开启注解扫描器
	 */
	public void setRequestMappingContextRefreshedEvent(boolean requestMappingContextRefreshedEvent) {
		REQUEST_MAPPING_CONTEXT_REFRESHED_EVENT = requestMappingContextRefreshedEvent;
	}

	/**
	 * 内部用户Cookie名称
	 */
	public void setInnerUserCookieName(String innerUserCookieName) {
		INNER_USER_COOKIE_NAME = innerUserCookieName;
	}

	/**
	 * 统一内部登录地址
	 */
	public void setInnerUserUnifiedLoginUrl(String innerUserUnifiedLoginUrl) {
		INNER_USER_UNIFIED_LOGIN_URL = innerUserUnifiedLoginUrl;
	}

	/**
	 * CRM保存项目内URL到REDISKEY
	 */
	public void setCrmUrlRedisKey(String crmUrlRedisKey) {
		CRM_URL_REDIS_KEY = crmUrlRedisKey;
	}

    /**
     * 内部用户对接URL
     *
     * @param innerUserUrl
     */
    public void setInnerUserUrl(String innerUserUrl) {
        INNER_USER_URL = innerUserUrl;
    }

    /**
     * CRM菜单ID
     *
     * @param menuFatherId
     */
    public void setMenuFatherId(int menuFatherId) {
        MENU_FATHER_ID = menuFatherId;
    }

    /**
     * 组织机构地址
     *
     * @param orSys
     */
    public void setOrSys(String orSys) {
        OR_SYS = orSys;
    }

	/**
	 * 地址服务，连接
	 */
	public void setAddressSys(String addressSys) {
		ADDRESS_SYS = addressSys;
	}

	/**
	 * 订单服务，连接
	 * @param orderSys
	 */
	public void setOrderSys(String orderSys) {
		ORDER_SYS = orderSys;
	}

	/**
	 * 积分服务
	 * @param integralSys
	 */
	public void setIntegralSys(String integralSys) {
		INTEGRAL_SYS = integralSys;
	}

	/**
	 * 统一余额服务
	 * @param paymentSys
	 */
	public void setPaymentSys(String paymentSys) {
		PAYMENT_SYS = paymentSys;
	}

	/**
	 * 产商品
	 * @param productUrl
	 */
	public void setProductUrl(String productUrl) {
		PRODUCT_URL = productUrl;
	}

	/**
	 * 第三方支付服务
	 */
	public String getPaybankSys() {
		return PAYBANK_SYS;
	}
	/**
	 * 第三方支付服务
	 */
	public void setPaybankSys(String paybankSys) {
		PAYBANK_SYS = paybankSys;
	}

	/**
	 * 编号服务
	 */
	public String getNumberSys() {
		return NUMBER_SYS;
	}

	/**
	 * 编号服务
	 */
	public void setNumberSys(String numberSys) {
		NUMBER_SYS = numberSys;
	}

	/**
	 * 积分商城
	 * @param integralProduct_Mall
	 */
	public void setIntegralProduct_Mall(String integralProduct_Mall) {
		IntegralProduct_Mall = integralProduct_Mall;
	}

	/**
	 * 积分商城,产品服务
	 */
	public void setIntegralOrder_Mall(String integralOrder_Mall) {
		IntegralOrder_Mall = integralOrder_Mall;
	}
}
