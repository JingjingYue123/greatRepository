package com.djcps.crm.banner.service;

import com.djcps.crm.banner.model.MultipartFileBean;
import com.djcps.crm.banner.model.ResultImgBean;
import com.djcps.crm.banner.server.DJBanner;
import com.djcps.crm.banner.server.DJBannerUpload;
import com.djcps.crm.banner.utils.BannerUtils;
import com.djcps.crm.commons.config.ParamsConfig;
import com.djcps.crm.commons.utils.DJGson;
import com.google.gson.*;
import okhttp3.MultipartBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rpc.plugin.http.HTTPResponse;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ZhangMingHui on 2017/7/5.
 */
@Service("bannerService")
public class BannerService {

    @Autowired
    private DJBanner djBanner;

    @Autowired
    private DJBannerUpload djBannerUpload;

    private static final Gson gson = new GsonBuilder().serializeNulls().create();
    private static final JsonParser parser = new JsonParser();

    /**
     * 删除轮播图
     * //* @param json
     *
     * @return json
     */
    public String deleteBanner(String json) {

        HTTPResponse httpResponse = djBanner.deleteBanner(json);
        if (httpResponse.isSuccessful()) {
            return httpResponse.getBodyString();
        }
        return "";
    }

    /**
     * 加载轮播图
     * //* @param json
     *
     * @return json
     */
    public String loadBanner(String json) {
        HTTPResponse httpResponse = djBanner.loadBanner(json);
        if (httpResponse.isSuccessful()) {
//            return parseJson(httpResponse.getBodyString());
            return httpResponse.getBodyString();
        }
        return "";
    }

    private JsonElement parseJson(String json) {
        JsonObject jsonObject = parser.parse(json).getAsJsonObject();
        if (jsonObject.has("success")) {
            boolean success = jsonObject.get("success").getAsBoolean();
            if (success && jsonObject.has("data")) {
                return jsonObject.get("data");
                //return gson.fromJson(object, Map.class);
            }
        }
        return null;
    }

    /**
     * 上下移动轮播图
     * //* @param json
     *
     * @return json
     */
    public String moveBanner(String json) {

        HTTPResponse httpResponse = djBanner.moveBanner(json);
        if (httpResponse.isSuccessful()) {
            return httpResponse.getBodyString();
        }
        return "";
    }

    /**
     * 获取轮播图时间
     *
     * @param json
     * @return json
     */
    public String loadBannerTime(String json) {
        HTTPResponse httpResponse = djBanner.loadBannerTime(json);
        if (httpResponse.isSuccessful()) {
            return httpResponse.getBodyString();
        }
        return "";
    }

    /**
     * 保存轮播图
     *
     * @param json
     * @return json
     */
    public String saveOrUpdate(String json) {
        logger.debug("url: {}", ParamsConfig.BANNER_URL + "picture/saveOrUpdate.do");
        logger.debug("json: {}", json);
        HTTPResponse httpResponse = djBanner.saveOrUpdate(json);
        if (null != httpResponse && httpResponse.isSuccessful()) {
            logger.debug("httpResponse: {}", httpResponse.getBodyString());
            return httpResponse.getBodyString();
        }
        return "";
    }

    /**
     * 获取轮播图时间
     *
     * @param json
     * @return json
     */
    public String updateBannerTime(String json) {
        HTTPResponse httpResponse = djBanner.updateBannerTime(json);
        if (httpResponse.isSuccessful()) {
            return httpResponse.getBodyString();
        }
        return "";
    }


    /**
     * 上传轮播图
     *
     * @param //json
     * @return json
     */
    public MultipartFileBean uploadBanner(MultipartFileBean bean) throws Exception {

        Map<String, okhttp3.RequestBody> params = new HashMap<>();
        params.put("moduleid", okhttp3.RequestBody.create(null, "" + bean.getModuleid()));
        MultipartBody.Part filePart = BannerUtils.filesToMultipartBody(bean.getFile());
        HTTPResponse httpResponse = djBannerUpload.uploadBanner(params, filePart);

        if (null != httpResponse && httpResponse.isSuccessful()) {
            try {
                ResultImgBean imgBean = DJGson.gson.fromJson("" + getMap(httpResponse.getBodyString()).get("data"), ResultImgBean.class);
                System.out.println("uploadBanner=======================================getFileid=" + imgBean.getFileid());
                System.out.println("uploadBanner=======================================getFileurl=" + imgBean.getFileurl());
                bean.setFfileid(imgBean.getFileid());
                bean.setFileurl(imgBean.getFileurl());
                bean.setFile(null);
                //bean.setModuleid("");
                return bean;
            } catch (Exception e) {
                return null;
            }
        }
        return null;
    }

    /**
     * 上传轮播图
     *
     * @param //json
     * @return json
     */
    public MultipartFileBean updateBanner(MultipartFileBean bean) throws Exception {

        Map<String, okhttp3.RequestBody> params = new HashMap<>();
        params.put("fileid", okhttp3.RequestBody.create(null, "" + bean.getFfileid()));
        MultipartBody.Part filePart = BannerUtils.filesToMultipartBody(bean.getFile());
        HTTPResponse httpResponse = djBannerUpload.updateBanner(params, filePart);
        logger.debug("update banner result: {}", httpResponse);
        if (null != httpResponse && httpResponse.isSuccessful()) {
            try {
                ResultImgBean imgBean = DJGson.gson.fromJson("" + getMap(httpResponse.getBodyString()).get("data"), ResultImgBean.class);
                logger.debug("imgBean: {}", imgBean);
                bean.setFfileid(imgBean.getFileid());
                bean.setFileurl(imgBean.getFileurl());
                bean.setFile(null);
                //bean.setModuleid("");
                return bean;
            } catch (Exception e) {
                return null;
            }
        }
        return null;
    }


    /**
     * 标准gson输出格式装换Map
     *
     * @param json 源数据
     * @return the map
     */
    public Map<String, Object> getMap(String json) {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("data", "");
        try {
            JsonParser parser = new JsonParser();
            JsonObject object = parser.parse("" + json).getAsJsonObject();
            result.put("data", object.has("data") ? object.get("data") : "");
            return result;
        } catch (Exception e) {
            return result;
        }
    }

    /**
     * 初始化广告位
     *
     * @param json
     * @return json
     */
    public String initializeAdPosition(String json) {
        HTTPResponse httpResponse = djBanner.initializeAdPosition(json);
        if (httpResponse.isSuccessful()) {
            return httpResponse.getBodyString();
        }
        return "";
    }

    /**
     * 根据产品id获取绑定该产品的广告位
     *
     * @param json
     * @return json
     */
    public String queryBannerOfproduct(String json) {
        HTTPResponse httpResponse = djBanner.queryBannerOfproduct(json);
        if (httpResponse.isSuccessful()) {
            return httpResponse.getBodyString();
        }
        return "";
    }

    private static final Logger logger = LoggerFactory.getLogger(BannerService.class);


}
