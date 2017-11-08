package com.djcps.crm.banner.server;


import com.djcps.crm.commons.config.ParamsConfig;
import com.sun.javafx.css.CssError;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.*;
import rpc.plugin.http.HTTPResponse;
import rpc.plugin.http.RPCClientFields;

import java.util.Map;

/**
 * Created by ysy on 2017/6/5.
 */
@RPCClientFields(urlfield ="BANNER_URL",urlbean = ParamsConfig.class)
public interface DJBanner {

	/**
	 *加载轮播图
	 */
	@POST("picture/load.do")
	HTTPResponse loadBanner(@Body String json);

	/**
	 *删除轮播图
	 */
	@POST("picture/delete.do")
	HTTPResponse deleteBanner(@Body String json);


	/**
	 *上下移动轮播图
	 */
	@POST("picture/move.do")
	HTTPResponse moveBanner(@Body String json);

	/**
	 *新增/编辑图片接口
	 */
	@POST("picture/saveOrUpdate.do")
	HTTPResponse saveOrUpdate(@Body String json);


	/**
	 *设置轮播时间接口
	 */
	@POST("slideshowtime/updateTime.do")
	HTTPResponse updateBannerTime(@Body String json);

	/**
	 *获取轮播图时间
	 */
	@POST("slideshowtime/loadtime.do")
	HTTPResponse loadBannerTime(@Body String json);

	/**
	 *初始化广告位
	 */
	@POST("picture/initializeAdPosition.do")
	HTTPResponse initializeAdPosition(@Body String json);

	/**
	 * 根据产品id获取绑定该产品的广告位
	 */
	@POST("picture/queryBannerOfproduct.do")
	HTTPResponse queryBannerOfproduct(@Body String json);
}