package com.djcps.crm.filter.sysinnerurlinterceptor.dao;

import javax.annotation.Resource;

import com.djcps.crm.commons.config.ParamsConfig;
import com.djcps.crm.filter.sysinnerurlinterceptor.component.SysAuthInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.djcps.crm.filter.sysurlistener.model.Furl;
import com.djcps.crm.inneruser.model.InnerUserModel;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import static com.djcps.crm.commons.utils.DJGson.gson;


@Component("redisDao")
public class InnerRedisDao {

	public static final Logger logger = LoggerFactory.getLogger(SysAuthInterceptor.class);

	/**
	 * crmJedisPool
	 */
	@Resource(name = "crmJedisPool")
	private JedisPool jedisPool2;

	/**
	 * 组织Redis
	 */
	@Resource(name = "userJedisPool")
	private JedisPool jedisPool;

	/**
	 * URL缓存名称
	 */
	private static String KEYNAME = ParamsConfig.CRM_URL_REDIS_KEY;

	/**
	 * 判断token有没有过期，没过期则重新设置时间
	 *
	 * @param token
	 * @return
	 */
	public boolean hasTokenInRedis(String token) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			//返回Key过期时间
			if (jedis.ttl(token) >= 0) {
				//重新设置 秒
				jedis.expire(token, ParamsConfig.LOGIN_TIMEOUT);
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
		return false;
	}

	/**
	 * 根据url得到CRM的redis里的Furl用户
	 *
	 * @param url
	 * @return
	 */
	public Furl getFurlVal(String url) {
		Jedis redis = null;
		try {
			redis = jedisPool2.getResource();
			String furl = redis.hget(KEYNAME, url);
			Furl f = gson.fromJson(furl, Furl.class);
			return f;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			if (redis != null) {
				redis.close();
			}
		}
	}

	/**
	 * 根据token得到InnerUser
	 *
	 * @param token
	 * @return
	 */
	public InnerUserModel selectInnerUser(String token) {
		Jedis redis = null;
		try {
			redis = jedisPool.getResource();
			String furl = redis.get(token);
			logger.debug("根据token得到InnerUser：selectInnerUser"+furl);
			System.out.println("根据token得到InnerUser：selectInnerUser"+furl);
			InnerUserModel iu = gson.fromJson(furl, InnerUserModel.class);
			return iu;
		} catch (Exception e) {
			logger.debug("根据token得到InnerUser：selectInnerUser null");
			System.out.println("根据token得到InnerUser：selectInnerUser null");
			e.printStackTrace();
			return null;
		} finally {
			if (redis != null) {
				redis.close();
			}
		}
	}

	/**
	 * 从redis里根据uri得到uri对应的权限id
	 */
	public String getInfo(String uri) {
		Jedis redis = null;
		try {
			redis = jedisPool.getResource();
			return redis.get(uri);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			if (redis != null) {
				redis.close();
			}
		}
	}
}
