package com.djcps.crm.banner.server;


import com.djcps.crm.commons.config.ParamsConfig;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.*;
import rpc.plugin.http.HTTPResponse;
import rpc.plugin.http.RPCClientFields;

import java.util.List;
import java.util.Map;

/**
 * Created by ysy on 2017/6/5.
 */
@RPCClientFields(urlfield ="FILE_SYSTEM_URL",urlbean = ParamsConfig.class,readTimeout = 120)
public interface DJBannerUpload {

	/**
	 *上传公开图
	 */
	@Multipart
	@POST("file/public/upload.do")
	HTTPResponse uploadBanner(@PartMap Map<String, RequestBody> params, @Part MultipartBody.Part file);

	/**
	 *确认文件
	 */
	@FormUrlEncoded
	@POST("file/upload/confirm.do")
	HTTPResponse uploadConfirm(@Field("fileid") List<String> fileid);

	/**
	 *更新轮播图
	 */
	@Multipart
	@POST("file/public/update.do")
	HTTPResponse updateBanner(@PartMap Map<String, RequestBody> params, @Part MultipartBody.Part file);

	/**
	 *确认文件
	 */
	@FormUrlEncoded
	@POST("file/update/confirm.do")
	HTTPResponse updateConfirm(@Field("fileid") List<String> fileid);

}