package com.djcps.crm.aop.log;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSON;
import com.djcps.crm.commons.config.ParamsConfig;
import com.djcps.crm.inneruser.model.InnerUserModel;
import com.djcps.crm.inneruser.service.InnerUserTokenManager;
import com.google.gson.Gson;

@Aspect
@Component
public class LogAspect {

    private static Logger logger = LoggerFactory.getLogger(LogAspect.class);

    @Autowired
    private InnerUserTokenManager innerUserTokenManager;

    // AOP执行的方法
    @Around("@annotation(addLog)")
    public Object validIdentityAndSecure(ProceedingJoinPoint pjp, AddLog addLog) throws Throwable {
        long start = System.currentTimeMillis();
        Object proceed = pjp.proceed();
        System.out.println("addLog 执行了");
        long end = System.currentTimeMillis();
        System.out.println("方法运行时间：" + (end - start));
        try {
            getParameter(pjp, addLog,proceed);
        }catch (Exception e){
            e.printStackTrace();
        }
        return proceed;
    }

    public HttpServletRequest getRequest(ProceedingJoinPoint pjp) {
        Object[] args = pjp.getArgs();
        HttpServletRequest request = null;
        if (args != null) {
            for (Object object : args) {
                if (object instanceof HttpServletRequest) {
                    request = (HttpServletRequest) object;
                    return request;
                }
            }
        }
        try {
            RequestAttributes ra = RequestContextHolder.getRequestAttributes();
            ServletRequestAttributes sra = (ServletRequestAttributes) ra;
            request = sra.getRequest();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return request;
    }

    public void getParameter(ProceedingJoinPoint pjp, AddLog addLog,Object proceed) throws InterruptedException, IOException {
        HttpServletRequest request = getRequest(pjp);
        Map<String, String> linkMap = new LinkedHashMap<>();
        Gson gson = new Gson();
        if (request != null) {
            // ip
            String ip = request.getHeader("X-real-IP");
            linkMap.put("ip", ip);
            // 参数
            Map<String, String[]> requestParam = request.getParameterMap();
            String jsonString = JSON.toJSONString(requestParam);
            linkMap.put("requestParam", jsonString);
            Object[] args = pjp.getArgs();
            if (ArrayUtils.isNotEmpty(args)) {
                for (Object object : args) {
                    if (object instanceof String) {
                        linkMap.put("requestBody", (String) object);
                        break;
                    }
                }
            }
            if (!(proceed instanceof String)) {
                linkMap.put("responseBody", gson.toJson(proceed));
            }
            String token = getToken(request);
            if (StringUtils.isNotEmpty(token)) {
                InnerUserModel innerUser = innerUserTokenManager.getInnerUserInfoFromRedis(token);
                if (innerUser!=null) {
                    linkMap.put("userid", String.valueOf(innerUser.getId()));
                    linkMap.put("username", innerUser.getUname());
                }
            }
            String OS = request.getHeader("User-Agent");
            linkMap.put("OS", OS);
        }
        // 操作时间
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        linkMap.put("operatingTime", simpleDateFormat.format(date));
        // 最小功能
        String signature = pjp.getSignature().toString();
        linkMap.put("signature", signature);
        // 操作内容
        String value = addLog.value();
        linkMap.put("operatingValue", value);
        // 系统模块
        String module = addLog.module();
        linkMap.put("systemModule", module);
        logger.info(JSON.toJSONString(linkMap));
    }

    // 获取cookie里的token
    public String getToken(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        String token = null;
        if (cookies != null && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                if (ParamsConfig.INNER_USER_COOKIE_NAME.equals(cookie.getName())) {
                    token = cookie.getValue();
                    break;
                }
            }
        }
        return token;
    }

}
