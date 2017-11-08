package com.djcps.crm.filter.sysurlistener.service;

import java.util.*;

import javax.annotation.Resource;

import com.djcps.crm.commons.config.ParamsConfig;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.djcps.crm.filter.sysurlistener.dao.RequestMappingDao;
import com.djcps.crm.filter.sysurlistener.model.Furl;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import static com.djcps.crm.commons.utils.DJGson.gson;

/**
 * 注意：
 * URL如果事先未添加name，之后的每一次扫描，数据库是不会更新的
 * 必须将数据库中的数据删除，重启项目方可解决
 */
@Service("requestMappingSerivceImpl")
public class RequestMappingSerivce {

	@Autowired(required = false)
	private RequestMappingDao urldao;
	
	@Resource(name = "crmJedisPool")
	private JedisPool jedisPool2;
	
	private static String keyname = "loginURL";

	/**
	 * 初始化
	 */
	public void Initialize(HashMap<String,Furl> FHashUrl){
		HashMap<String,Furl> hashMapLocalUrl = FHashUrl;
		HashMap<String,Furl> hashMapMysqlUrl = getUrlFromMysql();
		AddNewUrl(hashMapLocalUrl,hashMapMysqlUrl);
		DelOldUrl(hashMapLocalUrl,hashMapMysqlUrl);
		UrlAddRedis();
	}

	/**
	 * 获取本地存在URL，远程Mysql不存在URL,执行新增数据库操作
	 * @param hashMapLocalUrl 项目内URL
	 * @param hashMapMysqlUrl 远程URl
	 */
	private void AddNewUrl(HashMap<String,Furl> hashMapLocalUrl,HashMap<String,Furl> hashMapMysqlUrl){
		HashMap<String,Furl> addnewurl = new HashMap<String,Furl>();
		for (String kk :hashMapLocalUrl.keySet()){
			if(!hashMapMysqlUrl.containsKey(kk)){
				addnewurl.put(kk,hashMapLocalUrl.get(kk));
			}
		}
		List<Furl> lfurl = new ArrayList<Furl>();
		for (String kk : addnewurl.keySet()){
			lfurl.add(addnewurl.get(kk));
		}
		if(lfurl.size() > 0){
			urldao.insertFurl(lfurl);
		}
	}

	/**
	 * 远程对比本地，获取Mysql远程存在URL，本地不存在URL，执行删除mysql无效URL
	 * @param hashMapLocalUrl
	 * @param hashMapMysqlUrl
	 */
	private void DelOldUrl(HashMap<String,Furl> hashMapLocalUrl, HashMap<String,Furl> hashMapMysqlUrl){
		List<String> delOldUrl = new ArrayList<String>();
		for (String kk :hashMapMysqlUrl.keySet()){
			if(!hashMapLocalUrl.containsKey(kk)){
				Furl furl = hashMapMysqlUrl.get(kk);
				delOldUrl.add(furl.getFid());
			}
		}
		if(delOldUrl.size() > 0){
			urldao.deleteFurl(delOldUrl);
		}
	}

	/**
	 * 得到所有的url
	 */
	private HashMap<String,Furl> getUrlFromMysql(){
		try {
			List<Furl> urllist = urldao.selectAllFurl();
			HashMap<String,Furl> urlset = new HashMap<String,Furl>();
			for (Furl url : urllist){
				urlset.put(url.getFurl(),url);
			}
			return urlset;
		}catch (Exception e){
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	/**
	 * 添加URL到Redis，名称URL的Key，予以缓存
	 * @param
	 */
	private void UrlAddRedis() {

		//region 获取数据库最新更新的情况结果
		HashMap<String, String> val = new HashMap<String, String>();
		HashMap<String,Furl> hashMapMysqlUrl = getUrlFromMysql();
		for (String kk :hashMapMysqlUrl.keySet()){
			try {
				//序列化JSON
				String vv = gson.toJson(hashMapMysqlUrl.get(kk));
				val.put(kk,vv);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		//endregion

		//region 添加缓存到Redis数据库
		Jedis redis = null;
		try {
			redis = jedisPool2.getResource();
			redis.hmset(ParamsConfig.CRM_URL_REDIS_KEY,val);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(redis != null){
				redis.close();
			}
		}
		//endregion
	}

}
