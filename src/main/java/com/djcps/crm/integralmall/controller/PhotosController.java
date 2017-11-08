package com.djcps.crm.integralmall.controller;

import com.djcps.crm.commons.msg.MsgTemplate;
import com.djcps.crm.integralmall.enums.IntegralmallEnum;
import com.djcps.crm.integralmall.service.PhotosService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by L-wenbin on 2017/9/18.
 */
@RestController
public class PhotosController {
    @Autowired
    private PhotosService photosService;

    private Gson gson = new Gson();
    /**
     * 上传图片
     * 返回图片id和url。url为临时，需要再调接口，把id传入，url保存为永久url
     * @return
     */
    @RequestMapping(value = "photosController/addPhotos" , method = RequestMethod.POST)
    public Map<String,Object> addPhotos(@RequestParam(name = "file") MultipartFile file) {
        try {
            Map<String, Object> result = photosService.addPhotos(file);
            return result;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return MsgTemplate.failureMsg(IntegralmallEnum.ADD_FAILURE);
    }
    /**
     * 确认上传图片
     * @param json
     * @return
     */
    @RequestMapping(value = "photosController/confirm" , method = RequestMethod.POST)
    public Map<String,Object> confirm(@RequestBody String json) {
        List<String> list = gson.fromJson(json,new TypeToken<List<String>>() {}.getType());
        Map<String, Object> result = photosService.confirm(list);
        return result;
    }
}
