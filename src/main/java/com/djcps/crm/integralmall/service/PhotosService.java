package com.djcps.crm.integralmall.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.djcps.crm.integralmall.server.PhotosServer;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by L-wenbin on 2017/9/18.
 */
@Service
public class PhotosService {
    //日志文件
    private static final Logger logger = LoggerFactory.getLogger(PhotosService.class);

    @Autowired
    private PhotosServer photosServer;
    /**
     * 添加图片
     * @return
     */
    public Map<String,Object> addPhotos(MultipartFile file) throws IOException {
        Map<String, okhttp3.RequestBody> params = new HashMap<>();
        params.put("moduleid", okhttp3.RequestBody.create(null, "P12007"));
        MultipartBody.Part filePart = filesToMultipartBody(file);
        String result = photosServer.addPhotos(params,filePart).getBodyString();
        JSONObject jsonObject = JSON.parseObject(result,JSONObject.class);
        logger.debug("params: {}", jsonObject);
        return jsonObject;
    }

    private MultipartBody.Part filesToMultipartBody(MultipartFile file) throws IOException {
        MultipartBody.Builder builder = new MultipartBody.Builder();
        RequestBody requestBody = RequestBody.create(okhttp3.MediaType.parse(file.getContentType()), file.getBytes());
        builder.addFormDataPart("file", file.getOriginalFilename(), requestBody);
        builder.setType(MultipartBody.FORM);
        MultipartBody.Part part = MultipartBody.Part.createFormData(file.getName(), file.getOriginalFilename(), requestBody);
        return part;
    }
    /**
     * 确认上传图片
     * @param fileid 图片id
     * @return
     */
    public Map<String,Object> confirm(List<String> fileid) {
        String result = photosServer.confirm(fileid).getBodyString();
        JSONObject jsonObject = JSON.parseObject(result,JSONObject.class);
        logger.debug("params: {}", jsonObject);
        return jsonObject;
    }
}
