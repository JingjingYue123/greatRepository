package com.djcps.crm.integralmall.server;

import com.djcps.crm.commons.config.ParamsConfig;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import org.springframework.web.multipart.MultipartFile;
import retrofit2.http.*;
import rpc.plugin.http.HTTPResponse;
import rpc.plugin.http.RPCClientFields;

import java.util.List;
import java.util.Map;

/**
 * Created by L-wenbin on 2017/9/18.
 */
@RPCClientFields(urlfield ="FILE_SYSTEM_URL",urlbean = ParamsConfig.class)
public interface PhotosServer {
    /**
     * 图片上传
     * 返回图片id和url。url为临时，需要再调接口，把id传入，url保存为永久url
     * @param file 文件
     * @return
     */
    @Multipart
    @POST("file/public/upload.do")
    HTTPResponse addPhotos(@PartMap Map<String, RequestBody> params, @Part MultipartBody.Part file);

    /**
     * 确认上传图片
     * @param fileid 图片id
     * @return
     */
    @FormUrlEncoded
    @POST("file/upload/confirm.do")
    HTTPResponse confirm(@Field("fileid") List<String> fileid);
}
