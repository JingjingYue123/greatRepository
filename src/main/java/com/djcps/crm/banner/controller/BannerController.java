package com.djcps.crm.banner.controller;

import com.djcps.crm.aop.log.AddLog;
import com.djcps.crm.banner.model.MultipartFileBean;
import com.djcps.crm.banner.model.MultipartFilePam;
import com.djcps.crm.banner.model.ResultImgBean;
import com.djcps.crm.banner.service.BannerService;
import com.djcps.crm.banner.utils.BannerUtils;
import com.djcps.crm.commons.msg.MsgTemplate;
import com.djcps.crm.commons.utils.DJGson;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.*;

import static com.djcps.crm.commons.msg.MsgTemplate.failureMsg;
import static com.djcps.crm.commons.msg.MsgTemplate.successMsg;
import static com.djcps.crm.banner.enums.BannerMsgEnum.OPS_FAILURE;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.enterprise.inject.Model;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Created by ZhangMingHui on 2017/7/5.
 */
@Controller
@ResponseBody
public class BannerController {

    @Autowired
    private BannerService bannerService;

    private static final Logger logger = LoggerFactory.getLogger(BannerController.class);

    /**
     * 删除轮播图
     *
     * @return json参数
     */
    @RequestMapping(name = "用于删除轮播图", value = "/banner/deleteBanner", method = {RequestMethod.POST})
    @AddLog(module = "轮播图",value = "用于删除轮播图")
    public Map<String, Object> deleteBanner(@RequestBody(required = false) String json) {
        try {
            String jsonStr = bannerService.deleteBanner(json);
            logger.debug("删除轮播图的参数 {}", json);
            logger.debug("删除轮播图的返回数据 {}", jsonStr);
            return successMsg(jsonStr);
        } catch (Exception e) {
            e.printStackTrace();
            return failureMsg(OPS_FAILURE);
        }
    }


    /**
     * 加载轮播图
     *
     * @return json参数
     */
    @RequestMapping(name = "用于加载轮播图", value = "/banner/loadBanner", method = {RequestMethod.POST})
    @AddLog(module = "轮播图",value = "用于加载轮播图")
    public Map<String, Object> loadBanner(@RequestBody(required = false) String json) {
        try {
            String JsonStr = bannerService.loadBanner(json);
            logger.debug("加载轮播图的参数 {}", json);
            logger.debug("加载轮播图的返回数据 {}", JsonStr);
            return successMsg(JsonStr);
        } catch (Exception e) {
            e.printStackTrace();
            return failureMsg(OPS_FAILURE);
        }
    }

    /**
     * 加载轮播图
     *
     * @return nicai
     */
    @RequestMapping(name = "用于上下移动轮播图", value = "/banner/moveBanner", method = {RequestMethod.POST})
    @AddLog(module = "轮播图",value = "用于上下移动轮播图")
    public Map<String, Object> moveBanner(@RequestBody(required = false) String json) {
        try {
            String jsonStr = bannerService.moveBanner(json);
            logger.debug("上下移动轮播图的参数 {}", json);
            logger.debug("上下移动轮播图的返回数据 {}", jsonStr);
            return successMsg(jsonStr);
        } catch (Exception e) {
            e.printStackTrace();
            return failureMsg(OPS_FAILURE);
        }
    }

    /**
     * 设置轮播时间接口
     *
     * @return
     */
    @RequestMapping(name = "设置轮播时间接口", value = "/banner/updateBannerTime", method = {RequestMethod.POST})
    @AddLog(module = "轮播图",value = "设置轮播时间接口")
    public Map<String, Object> updateBannerTime(@RequestBody(required = false) String json) {
        try {
            String jsonStr = bannerService.updateBannerTime(json);
            logger.debug("设置轮播时间的参数 {}", json);
            logger.debug("设置轮播时间的返回数据 {}", jsonStr);
            return successMsg(jsonStr);
        } catch (Exception e) {
            e.printStackTrace();
            return failureMsg(OPS_FAILURE);
        }
    }

    /**
     * 获取轮播时间接口
     *
     * @return
     */
    @RequestMapping(name = "获取轮播图时间", value = "/banner/loadBannerTime", method = {RequestMethod.POST})
    @AddLog(module = "轮播图",value = "获取轮播图时间")
    public Map<String, Object> loadBannerTime(@RequestBody(required = false) String json) {
        try {
            String jsonStr = bannerService.loadBannerTime(json);
            logger.debug("获取轮播时间的参数 {}", json);
            logger.debug("获取轮播时间的返回数据 {}", jsonStr);
            return successMsg(jsonStr);
        } catch (Exception e) {
            e.printStackTrace();
            return failureMsg(OPS_FAILURE);
        }
    }

    /**
     * 上传轮播图
     *
     * @return nicai
     */
    @RequestMapping(name = "上传轮播图", value = "/banner/addAndUpdataBanner", method = {RequestMethod.POST})
    @AddLog(module = "轮播图",value = "上传轮播图")
    public Map<String, Object> uploadBanner(HttpServletRequest request, MultipartFilePam pam) {
        try {
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
            Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
            MultipartFileBean beans = new MultipartFileBean();
            beans.addMultipartFilePam(pam);
            logger.debug("上传轮播图 beans: {}", beans);
            beans.setFile(fileMap.get("file"));
            //如果图片ID不为空   就调用更新接口
            if (null != beans.getFfileid() && !"".equals(beans.getFfileid())
                    && beans.getFid() != null && !"".equals(beans.getFid())) {
                if (beans.getFile() != null && !beans.getFile().isEmpty()) {
                    beans = bannerService.updateBanner(beans);
                }
            } else {//否则就调用上传接口
                if(beans.getFile() != null && !beans.getFile().isEmpty()){
                    beans = bannerService.uploadBanner(beans);
                }
            }
            //Ffileid可以为空，表示无图片
            if (null != beans) {
                Map<String, Object> mapData = saveOrUpdate(beans);
                logger.debug("上传轮播图 返回: {}", mapData);
                return mapData;
            } else {
                return failureMsg(OPS_FAILURE);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return failureMsg(OPS_FAILURE);
        }
    }

    /**
     * 保存轮播图
     *
     * @return nicai
     */
    public Map<String, Object> saveOrUpdate(MultipartFileBean fileBean) {

        try {
            String jsonRequest = DJGson.gson.toJson(fileBean);
            String jsonResult = bannerService.saveOrUpdate(jsonRequest);
            logger.debug(jsonRequest);
            return successMsg(jsonResult);
        } catch (Exception e) {
            return failureMsg(OPS_FAILURE);
        }

    }

    /**
     * 初始化广告位
     *
     * @return
     */
    @RequestMapping(name = "初始化广告位", value = "/banner/initializeAdPosition", method = {RequestMethod.POST})
    public Map<String, Object> initializeAdPosition(@RequestBody(required = false) String json) {
        try {
            String jsonStr = bannerService.initializeAdPosition(json);
            logger.debug("初始化广告位的参数 {}", json);
            logger.debug("初始化广告位的返回数据 {}", jsonStr);
            return successMsg(jsonStr);
        } catch (Exception e) {
            e.printStackTrace();
            return failureMsg(OPS_FAILURE);
        }
    }


    /**
     * 根据产品id获取绑定该产品的广告位
     *
     * @return
     */
    @RequestMapping(name = "根据产品id获取绑定该产品的广告位", value = "/banner/queryBannerOfproduct", method = {RequestMethod.POST})
    public Map<String, Object> queryBannerOfproduct(@RequestBody(required = false) String json) {
        try {
            String jsonStr = bannerService.queryBannerOfproduct(json);
            logger.debug("根据产品id获取绑定该产品的广告位的参数 {}", json);
            logger.debug("根据产品id获取绑定该产品的广告位的返回数据 {}", jsonStr);
            return successMsg(jsonStr);
        } catch (Exception e) {
            e.printStackTrace();
            return failureMsg(OPS_FAILURE);
        }
    }
}
