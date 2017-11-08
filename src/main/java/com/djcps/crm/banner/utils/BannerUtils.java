package com.djcps.crm.banner.utils;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;

/**
 * Created by ZhangMingHui on 2017/7/7.
 */
public class BannerUtils {

    public static MultipartBody.Part filesToMultipartBody(MultipartFile file) throws IOException {
        MultipartBody.Builder builder = new MultipartBody.Builder();
        RequestBody requestBody = RequestBody.create(okhttp3.MediaType.parse(file.getContentType()), file.getBytes());
        builder.addFormDataPart("file", file.getOriginalFilename(), requestBody);
        builder.setType(MultipartBody.FORM);
        MultipartBody.Part part = MultipartBody.Part.createFormData(file.getName(), file.getOriginalFilename(), requestBody);
        return part;
    }


    public static  MultipartFile getMultipartFileforRequest(HttpServletRequest request) throws Exception {
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        if (multipartResolver.isMultipart(request)) {
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
            Iterator<String> iter = multiRequest.getFileNames();
            while (iter.hasNext()) {
                MultipartFile file = multiRequest.getFile(iter.next());
                if (file != null) {
                    String myFileName = file.getOriginalFilename();
                    if (myFileName.trim() != "") {
                        return file;
                    }
                }
            }
        }
        return null;
    }

}
