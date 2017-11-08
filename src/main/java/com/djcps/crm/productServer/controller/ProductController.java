package com.djcps.crm.productServer.controller;

import com.djcps.crm.aop.log.AddLog;
import com.djcps.crm.banner.enums.BannerMsgEnum;
import com.djcps.crm.banner.model.MultipartFileBean;
import com.djcps.crm.banner.model.MultipartFilePam;
import com.djcps.crm.banner.service.BannerService;
import com.djcps.crm.productServer.Utils.Utils;
import com.djcps.crm.productServer.model.UploadParam;
import com.djcps.crm.productServer.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

import static com.djcps.crm.commons.msg.MsgTemplate.failureMsg;
import static com.djcps.crm.commons.msg.MsgTemplate.successMsg;
import static com.djcps.crm.productServer.enums.ProductMsgEnum.OPS_FAILURE;
import static com.djcps.crm.productServer.enums.ProductMsgEnum.PARAMS_NULL;

/**
 * Created by ZhangMingHui on 2017/8/8.
 */
@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    /**
     * 以条件加载产品
     * @return
     */
    @RequestMapping(name = "加载产品管理列表", value ="/product/loadProducts", method = {RequestMethod.POST})
    @AddLog(module = "产商品",value = "加载产品管理列表")
    public Map<String, Object> loadProducts(@RequestBody(required = false)String json){
        try {
            String jsonStr = productService.loadProducts(json);
            return successMsg(jsonStr);
        } catch (Exception e) {
            e.printStackTrace();
            return failureMsg(OPS_FAILURE);
        }
    }

    /**
     *添加/编辑产品
     * 日期 2017/7/24.
     */
    @RequestMapping(name = "添加或者编辑产品", value ="/product/addOrEditProducts", method = {RequestMethod.POST})
    @AddLog(module = "产商品",value = "添加或者编辑产品")
    public  Map<String, Object>  addOrEditProducts(@RequestBody(required = false)String json){
        try {
            String jsonStr = productService.addOrEditProducts(json);
            return successMsg(jsonStr);
        } catch (Exception e) {
            e.printStackTrace();
            return failureMsg(OPS_FAILURE);
        }
    }

    /**
     *删除图片
     * 日期 2017/7/24.
     */
    @RequestMapping(name = "删除图片", value ="/product/deleteFigure", method = {RequestMethod.POST})
    @AddLog(module = "产商品",value = "删除图片")
    public  Map<String, Object>  deleteFigure(@RequestBody(required = false)String json){
        try {
            String jsonStr = productService.deleteFigure(json);
            return successMsg(jsonStr);
        } catch (Exception e) {
            e.printStackTrace();
            return failureMsg(OPS_FAILURE);
        }
    }

    /**
     *设置开团时间
     * 日期 2017/7/24.
     */
    @RequestMapping(name = "设置开团", value ="/product/setGroupOpenCycle", method = {RequestMethod.POST})
    @AddLog(module = "产商品",value = "设置开团")
    public Map<String, Object> setGroupOpenCycle(@RequestBody(required = false)String json){
        try {
            String jsonStr = productService.setGroupOpenCycle(json);
            return successMsg(jsonStr);
        } catch (Exception e) {
            e.printStackTrace();
            return failureMsg(OPS_FAILURE);
        }
    }

    /**
     * 更新订单排序权重
     *  日期 2017/7/24.
     */
    @RequestMapping(name = " 更新订单排序权重", value ="/product/updateForder", method = {RequestMethod.POST})
    @AddLog(module = "产商品",value = "更新订单排序权重")
    public  Map<String, Object>  updateProductsForder(@RequestBody(required = false)String json){
        try {
            String jsonStr = productService.updateProductsForder(json);
            return successMsg(jsonStr);
        } catch (Exception e) {
            e.printStackTrace();
            return failureMsg(OPS_FAILURE);
        }
    }

    /**
     * 提交和下架产品审核申请
     *  日期 2017/7/24.
     */
    @RequestMapping(name = "提交和下架产品审核申请", value ="/product/applyeExamineProduct", method = {RequestMethod.POST})
    @AddLog(module = "产商品",value = "提交和下架产品审核申请")
    public  Map<String, Object>  applyeExamineProduct(@RequestBody(required = false)String json){
        try {
            String jsonStr = productService.applyeExamineProduct(json);
            return successMsg(jsonStr);
        } catch (Exception e) {
            e.printStackTrace();
            return failureMsg(OPS_FAILURE);
        }
    }


    /**
     *产品审核  审核产品(驳回、审核通过产品)
     *  日期 2017/7/24.
     */
    @RequestMapping(name = "提交和下架产品审核", value ="/product/examineProduct", method = {RequestMethod.POST})
    @AddLog(module = "产商品",value = "提交和下架产品审核")
    public  Map<String, Object>  examineProduct(@RequestBody(required = false)String json){
        try {
            String jsonStr = productService.examineProduct(json);
            return successMsg(jsonStr);
        } catch (Exception e) {
            e.printStackTrace();
            return failureMsg(OPS_FAILURE);
        }
    }

    /**
     *删除产品
     *  日期 2017/7/24.
     */
    @RequestMapping(name = "删除产品", value ="/product/deleteProduct", method = {RequestMethod.POST})
    @AddLog(module = "产商品",value = "删除产品")
    public  Map<String, Object>  deleteProduct(@RequestBody(required = false)String json){
        try {
            String jsonStr = productService.deleteProduct(json);
            return successMsg(jsonStr);
        } catch (Exception e) {
            e.printStackTrace();
            return failureMsg(OPS_FAILURE);
        }
    }

    /**
     *撤销产品申请
     *  日期 2017/7/24.
     */
    @RequestMapping(name = "撤销产品申请", value ="/product/revokeApplyProduct", method = {RequestMethod.POST})
    @AddLog(module = "产商品",value = "撤销产品申请")
    public  Map<String, Object>  revokeApplyProduct(@RequestBody(required = false)String json){
        try {
            String jsonStr = productService.revokeApplyProduct(json);
            return successMsg(jsonStr);
        } catch (Exception e) {
            e.printStackTrace();
            return failureMsg(OPS_FAILURE);
        }
    }

    /**
     *撤销方案申请
     *  日期 2017/7/24.
     */
    @RequestMapping(name = "撤销方案申请", value ="/product/revokeApplyProgramme", method = {RequestMethod.POST})
    @AddLog(module = "产商品",value = "撤销方案申请")
    public  Map<String, Object>  revokeApplyProgramme(@RequestBody(required = false)String json){
        try {
            String jsonStr = productService.revokeApplyProgramme(json);
            return successMsg(jsonStr);
        } catch (Exception e) {
            e.printStackTrace();
            return failureMsg(OPS_FAILURE);
        }
    }


    /**
     *添加营销方案
     *  日期 2017/7/24.
     */
    @RequestMapping(name = "添加营销方案", value ="/product/addOrEditProgramme", method = {RequestMethod.POST})
    @AddLog(module = "产商品",value = "添加营销方案")
    public  Map<String, Object>  addOrEditProgramme(@RequestBody(required = false)String json){
        try {
            String jsonStr = productService.addOrEditProgramme(json);
            return successMsg(jsonStr);
        } catch (Exception e) {
            e.printStackTrace();
            return failureMsg(OPS_FAILURE);
        }
    }



    /**
     *以条件加载营销方案列表
     *  日期 2017/7/24.
     */
    @RequestMapping(name = "以条件加载营销方案列表", value ="/product/loadProgrammes", method = {RequestMethod.POST})
    @AddLog(module = "产商品",value = "以条件加载营销方案列表")
    public  Map<String, Object>  loadProgrammes(@RequestBody(required = false)String json){
        try {
            String jsonStr = productService.loadProgrammes(json);
            return successMsg(jsonStr);
        } catch (Exception e) {
            e.printStackTrace();
            return failureMsg(OPS_FAILURE);
        }
    }

    /**
     *生效营销方案
     *  日期 2017/7/24.
     */
    @RequestMapping(name = "生效营销方案", value ="/product/activateProgrammes", method = {RequestMethod.POST})
    @AddLog(module = "产商品",value = "生效营销方案")
    public  Map<String, Object>  activateProgrammes(@RequestBody(required = false)String json){
        try {
            String jsonStr = productService.activateProgrammes(json);
            return successMsg(jsonStr);
        } catch (Exception e) {
            e.printStackTrace();
            return failureMsg(OPS_FAILURE);
        }
    }

    /**
     *申请审核方案
     *  日期 2017/7/24.
     */
    @RequestMapping(name = "申请审核方案", value ="/product/applyeExamineProgrammes", method = {RequestMethod.POST})
    @AddLog(module = "产商品",value = "申请审核方案")
    public  Map<String, Object>  applyeExamineProgrammes(@RequestBody(required = false)String json){
        try {
            String jsonStr = productService.applyeExamineProgrammes(json);
            return successMsg(jsonStr);
        } catch (Exception e) {
            e.printStackTrace();
            return failureMsg(OPS_FAILURE);
        }
    }

    /**
     *审核方案
     *  日期 2017/7/24.
     */
    @RequestMapping(name = "审核方案", value ="/product/examineProgrammes", method = {RequestMethod.POST})
    @AddLog(module = "产商品",value = "审核方案")
    public  Map<String, Object>  examineProgrammes(@RequestBody(required = false)String json){
        try {
            String jsonStr = productService.examineProgrammes(json);
            return successMsg(jsonStr);
        } catch (Exception e) {
            e.printStackTrace();
            return failureMsg(OPS_FAILURE);
        }
    }

    /**
     *删除营销方案
     *  日期 2017/7/24.
     */
    @RequestMapping(name = "删除营销方案", value ="/product/deleteProgrammes", method = {RequestMethod.POST})
    @AddLog(module = "产商品",value = "删除营销方案")
    public  Map<String, Object>  deleteProgrammes(@RequestBody(required = false)String json){
        try {
            String jsonStr = productService.deleteProgrammes(json);
            return successMsg(jsonStr);
        } catch (Exception e) {
            e.printStackTrace();
            return failureMsg(OPS_FAILURE);
        }
    }

    /**
     *加载营销方案详情
     *  日期 2017/7/24.
     */
    @RequestMapping(name = "加载营销方案详情", value ="/product/loadProgrammesDetails", method = {RequestMethod.POST})
    @AddLog(module = "产商品",value = "加载营销方案详情")
    public  Map<String, Object>  loadProgrammesDetails(@RequestBody(required = false)String json) {
        try {
            String jsonStr = productService.loadProgrammesDetails(json);
            return successMsg(jsonStr);
        } catch (Exception e) {
            e.printStackTrace();
            return failureMsg(OPS_FAILURE);
        }
    }
    /**
     *获取开团时间
     *  日期 2017/7/24.
     */

    @RequestMapping(name = "获取开团时间", value ="/product/getGroupOpenCycle", method = {RequestMethod.POST})
    @AddLog(module = "产商品",value = "获取开团时间")
    public  Map<String, Object>  getGroupOpenCycle(@RequestBody(required = false)String json) {
        try {
            String jsonStr = productService.getGroupOpenCycle(json);
            return successMsg(jsonStr);
        } catch (Exception e) {
            e.printStackTrace();
            return failureMsg(OPS_FAILURE);
        }
    }

    /**
     *获取不开团时间
     *  日期 2017/7/24.
     */
    @RequestMapping(name = "获取不开团时间", value ="/product/getGroupHugh", method = {RequestMethod.POST})
    @AddLog(module = "产商品",value = "获取不开团时间")
    public  Map<String, Object>  getGroupHugh(@RequestBody(required = false)String json) {
        try {
            String jsonStr = productService.getGroupHugh(json);
            return successMsg(jsonStr);
        } catch (Exception e) {
            e.printStackTrace();
            return failureMsg(OPS_FAILURE);
        }
    }

    /**
     *设置不开团时间
     *  日期 2017/7/24.
     */
    @RequestMapping(name = "设置不开团时间", value ="/product/setGroupHugh", method = {RequestMethod.POST})
    @AddLog(module = "产商品",value = "设置不开团时间")
    public  Map<String, Object>  setGroupHugh(@RequestBody(required = false)String json) {
        try {
            String jsonStr = productService.setGroupHugh(json);
            return successMsg(jsonStr);
        } catch (Exception e) {
            e.printStackTrace();
            return failureMsg(OPS_FAILURE);
        }
    }

    /**
     *上传图片
     * @return nicai
     */
    @RequestMapping(name = "上传图片", value = "/product/uploadFigure", method = {RequestMethod.POST})
    @AddLog(module = "产商品",value = "上传图片")
    public  Map<String, Object>  uploadFigure(HttpServletRequest request, UploadParam pam) {
        try {
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest)request;

            Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
            MultipartFileBean beans=new MultipartFileBean();
            beans.setFkeyarea(Integer.valueOf(pam.getFkeyarea()));
            beans.setModuleid(pam.getModuleid());
            beans.setFile(fileMap.get("file"));
            return productService.uploadFigure(beans);
        } catch (Exception e) {
            e.printStackTrace();
            return failureMsg(OPS_FAILURE);
        }
    }

    /**
     *加载方案审核列表
     *  日期 2017/7/24.
     */
    @RequestMapping(name = "加载方案审核列表", value ="/product/loadListsPMarketing", method = {RequestMethod.POST})
    @AddLog(module = "产商品",value = "加载方案审核列表")
    public  Map<String, Object>  loadListsPMarketing(@RequestBody(required = false)String json) {
        try {
            String jsonStr = productService.loadListsPMarketing(json);
            return successMsg(jsonStr);
        } catch (Exception e) {
            e.printStackTrace();
            return failureMsg(OPS_FAILURE);
        }
    }


    /* 产商品轮播图信息
     *
     * @return
     */
    @RequestMapping(name = "获取产商品轮播图", value = "/product/loadListsForAddres", method = {RequestMethod.POST})
    @AddLog(module = "产商品",value = "获取产商品轮播图")
    public Map<String, Object> loadListsForAddres(@RequestBody(required = false) String jsonParm) {
        try {
            String jsonStr = productService.loadListsForAddres(jsonParm);
            return successMsg(jsonStr);
        } catch (Exception e) {
            e.printStackTrace();
            return failureMsg(BannerMsgEnum.OPS_FAILURE);
        }
    }



}
