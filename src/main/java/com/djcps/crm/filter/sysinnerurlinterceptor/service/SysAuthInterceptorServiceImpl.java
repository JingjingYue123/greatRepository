package com.djcps.crm.filter.sysinnerurlinterceptor.service;

import java.io.IOException;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.djcps.crm.aop.crmurlauth.CrmUrlAuth;
import com.djcps.crm.commons.utils.CookieUtils;
import com.djcps.crm.inneruser.model.InnerUserModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import com.djcps.crm.commons.config.ParamsConfig;
import com.djcps.crm.commons.msg.MsgTemplate;
import com.djcps.crm.filter.sysinnerurlinterceptor.component.SysAuthInterceptor;
import com.djcps.crm.filter.sysinnerurlinterceptor.dao.InnerRedisDao;
import com.djcps.crm.filter.sysinnerurlinterceptor.enums.Permission;
import com.djcps.crm.filter.sysurlistener.model.Furl;

import static com.djcps.crm.commons.utils.DJGson.gson;

/**
 * 权限拦截，与组织与Redis对接代码
 */
@Service("sysAuthInterceptorServiceImpl")
public class SysAuthInterceptorServiceImpl {


	public static final Logger logger = LoggerFactory.getLogger(SysAuthInterceptor.class);


	@Resource(name = "redisDao")
	private InnerRedisDao innerRedisDao;

	/**
	 * 拦截器权限判断 主入口
	 *
	 * @param uri
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public boolean interceptorCheck(String method, String uri, CrmUrlAuth crmUrlAuth, HttpServletRequest request, HttpServletResponse response) throws Exception {

		//CRM权限拦截
		if(ParamsConfig.SYS_AUTH_CRM_INTERCEPTOR){
			//根据uri得到uri在CRMredis里对应的权限
			Furl furl = innerRedisDao.getFurlVal(uri);
			//判断非空
			if (!ObjectUtils.isEmpty(furl)) {
				//判断路径是否启用
				if (furl.getFeffect() != Permission.PORT_IS_USEFUL.getPermission()) {
					responseMsg("此路径无效", response,-10000);
					return false;
				}
				//判断接口需不需要登入
				if (furl.getFlogintype() == Permission.NO_NEED_LOGIN.getPermission()) {
					return true;
				}
			}else{
				responseMsg("404", response,-10000);
				return false;
			}
		}
		//配置注解的接口可以绕过权限
		if(!ObjectUtils.isEmpty(crmUrlAuth)){
			if(crmUrlAuth.NO_NEED_LOGIN() == 1){
				return true;
			}
		}
		//内外部用户及未知用户权限的判断
		return checkUserPermission(method,request, response, uri);
	}

	/**
	 * 内外部用户权限的判断
	 *
	 * @param request
	 * @param response
	 * @param furl
	 * @return
	 * @throws Exception
	 */
	public boolean checkUserPermission(String method,HttpServletRequest request, HttpServletResponse response, String furl) throws Exception {
		String token = getTokenFromCookie(request);
		//内部用户校验
		return handleAfterVerifyToken(method,furl, request, token, response);
	}

	/**
	 * 根据里得到token和url权限类型types得到数据
	 *
	 * @param request
	 * @return
	 */
	public String getTokenFromCookie(HttpServletRequest request) {
		String token = null;
		Cookie[] cookies = request.getCookies();
		if (cookies != null && cookies.length > 0) {
			for (Cookie cookie : cookies) {
				if (ParamsConfig.INNER_USER_COOKIE_NAME.equals(cookie.getName())) {
					token = cookie.getValue();
				}
			}
		}
		return token;
	}

	/**
	 * 内部用户的url判断权限
	 *
	 * @param uri
	 * @param request
	 * @param token
	 * @param response
	 * @return
	 * @throws IOException
	 * @throws ServletException
	 */
	public boolean handleAfterVerifyToken(String method,String uri, HttpServletRequest request, String token, HttpServletResponse response) throws IOException, ServletException {
		if (StringUtils.isEmpty(token)) {
			logger.debug("token:"+token + "抱歉，您的token已过期，请重新登录后再试");
			responseMsg("抱歉，您的token已过期，请重新登录后再试", response,-10001);
			return false;
		}
		//判断token有没有过期，没过期则重新设置时间
		boolean checkTime = innerRedisDao.hasTokenInRedis(token);
		if (!checkTime) {
			logger.debug("token:"+token + "用户信息已过期");
			responseMsg("用户信息已过期", response,-10001);
			return false;
		}
		//还原Cookie过期时间
		CookieUtils.addCookie(ParamsConfig.INNER_USER_COOKIE_NAME, token, response);
		//内部用户判断
		InnerUserModel innerUserModel = innerRedisDao.selectInnerUser(token);
		//判断URL
		if (innerUserModel != null && verifyAuth(method,innerUserModel, uri, request, response)) {
			request.setAttribute("innerUser", innerUserModel);
			return true;
		} else {
			responseMsg("抱歉，权限获取失败，请联系相关人员", response,-10002);
			return false;
		}
	}

	/**
	 * 内部用户获取redis内组织架构保存的URL进行权限判断
	 *
	 * @param innerUserModel
	 * @param uri
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 * @throws ServletException
	 */
	public boolean verifyAuth(String method,InnerUserModel innerUserModel, String uri, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		//获取当前访问权限
		String authListStr = innerRedisDao.getInfo("CRM"+Integer.toString(innerUserModel.getId()));
		System.out.println("OR权限列表："+authListStr);
		logger.debug("OR权限列表："+authListStr);
		if (authListStr != null) {
			System.out.println("OR权限列表2：CRM"+method+uri);
			String uriId = innerRedisDao.getInfo("CRM"+method+uri);
			System.out.println("OR权限列表3："+uriId);
			if (uriId != null) {
				return authListStr.contains("-" + uriId + "-");
			}
		}
		//responseMsg("抱歉，您暂无权限访问，请联系相关人员", response,-10002);
		return false;
	}


	/**
	 * 得到来源页
	 *
	 * @param request
	 * @return
	 */
	private String getIntercept(HttpServletRequest request) {
		String uriUserid = null;
		String referer = request.getHeader("referer");
		if (!StringUtils.isEmpty(referer)) {
			uriUserid = referer.substring(referer.indexOf("=") + 1, referer.indexOf("#")).trim();
		}
		return uriUserid;
	}

	/**
	 * 输出错误信息
	 *
	 * @param msg
	 * @param response
	 */
	public void responseMsg(String msg, HttpServletResponse response,int code) {
		logger.info(msg);
		if (!response.isCommitted()) {
			Map<String, Object> result = (MsgTemplate.failureMsg(code,msg));
			try {
				String json = gson.toJson(result);
				if (!response.isCommitted())
					response.setCharacterEncoding("UTF-8");
					response.setHeader("Content-Type", "text/html;charset=UTF-8");//这句话是解决乱码的
					response.getWriter().write(json);
					response.getWriter().close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
