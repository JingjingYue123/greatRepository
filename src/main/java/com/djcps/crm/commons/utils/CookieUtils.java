package com.djcps.crm.commons.utils;

import com.djcps.crm.commons.config.ParamsConfig;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by LK on 2017/5/3.
 */
public class CookieUtils {

    private static final int MaxAge = ParamsConfig.COOKIE_TIMEOUT; // 设置为30min

    public static void addCookie(String name, String value, HttpServletResponse response){
        Cookie cookie = new Cookie(name, value);
        cookie.setHttpOnly(true);
        cookie.setMaxAge(MaxAge); // 设置有效期
        cookie.setPath("/");
        response.addCookie(cookie); // 输出到客户端
    }

    public static void deleteCookieByName(String name, HttpServletResponse response){
        Cookie cookie = new Cookie(name,null);
        cookie.setMaxAge(0); // 设置生命周期为0，不能为负数
        cookie.setPath("/");
        response.addCookie(cookie);
    }

}
