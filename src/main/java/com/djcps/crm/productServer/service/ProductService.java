package com.djcps.crm.productServer.service;

import com.baidu.unbiz.fluentvalidator.ComplexResult;
import com.baidu.unbiz.fluentvalidator.FluentValidator;
import com.djcps.crm.banner.model.MultipartFileBean;
import com.djcps.crm.banner.model.ResultImgBean;
import com.djcps.crm.banner.utils.BannerUtils;
import com.djcps.crm.commons.utils.DJGson;
import com.djcps.crm.productServer.server.DJProduct;
import com.djcps.crm.productServer.server.ProductFigureUpload;
import com.google.gson.*;
import okhttp3.MultipartBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rpc.plugin.http.HTTPResponse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.baidu.unbiz.fluentvalidator.ResultCollectors.toComplex;
import static com.djcps.crm.commons.msg.MsgTemplate.failureMsg;
import static com.djcps.crm.commons.msg.MsgTemplate.successMsg;
import static com.djcps.crm.productServer.enums.ProductMsgEnum.OPS_FAILURE;

/**
 * Created by ZhangMingHui on 2017/7/5.
 */
@Service("productService")
public class ProductService {

    @Autowired
    private DJProduct djProduct;

    @Autowired
    private ProductFigureUpload pfUpload;
    /**
     * 管理产品列表
     * @return
     */
    public   String  loadProducts(String json) {
        HTTPResponse httpResponse = djProduct.loadProducts(json);
        if (httpResponse.isSuccessful()) {
            return httpResponse.getBodyString();
        }
        return "";
    }

    /**
     *添加/编辑产品
     *  日期 2017/7/24.
     */
    public   String  addOrEditProducts(String json) {
        HTTPResponse httpResponse = djProduct.addOrEditProducts(json);
        if (httpResponse.isSuccessful()) {
            return httpResponse.getBodyString();
        }
        return "";
    }

    /**
     *删除图片
     *  日期 2017/7/24.
     */
    public   String deleteFigure(String json) {
        HTTPResponse httpResponse = djProduct.deleteFigure(json);
        if (httpResponse.isSuccessful()) {
            return httpResponse.getBodyString();
        }
        return "";
    }

    /**
     *设置开团时间
     * 日期 2017/7/24.
     */
    public   String  setGroupOpenCycle(String json) {
        HTTPResponse httpResponse = djProduct.setGroupOpenCycle(json);
        if (httpResponse.isSuccessful()) {
            return httpResponse.getBodyString();
        }
        return "";
    }

    /**
     *删除产品
     *  日期 2017/7/24.
     */
    public   String  deleteProduct(String json) {
        HTTPResponse httpResponse = djProduct.deleteProduct(json);
        if (httpResponse.isSuccessful()) {
            return httpResponse.getBodyString();
        }
        return "";
    }

    /**
     *撤销申请===产品
     *  日期 2017/7/24.
     */
    public String revokeApplyProduct(String json) {
        HTTPResponse httpResponse = djProduct.revokeApplyProduct(json);
        if (httpResponse.isSuccessful()) {
            return httpResponse.getBodyString();
        }
        return "";
    }

    /**
     *撤销申请===方案
     *  日期 2017/7/24.
     */
    public String revokeApplyProgramme(String json) {
        HTTPResponse httpResponse = djProduct.revokeApplyProgramme(json);
        if (httpResponse.isSuccessful()) {
            return httpResponse.getBodyString();
        }
        return "";
    }

    /**
     *添加营销方案
     *  日期 2017/7/24.
     */
    public    String  addOrEditProgramme(String json) {
        HTTPResponse httpResponse = djProduct.addOrEditProgramme(json);
        if (httpResponse.isSuccessful()) {
            return httpResponse.getBodyString();
        }
        return "";
    }

    /**
     *编辑产品
     *  日期 2017/7/24.
     */
    public   String  updateProgramme(String json) {
        HTTPResponse httpResponse = djProduct.updateProgramme(json);
        if (httpResponse.isSuccessful()) {
            return httpResponse.getBodyString();
        }
        return "";
    }


    /**
     *以条件加载单产品营销方案列表
     *  日期 2017/7/24.
     */
    public    String  loadProgrammes(String json) {
        HTTPResponse httpResponse = djProduct.loadProgrammes(json);
        if (httpResponse.isSuccessful()) {
            return httpResponse.getBodyString();
        }
        return "";
    }


    /**
     *加载营销方案详情
     *  日期 2017/7/24.
     */
    public String loadProgrammesDetails(String json) {
        HTTPResponse httpResponse = djProduct.loadProgrammesDetails(json);
        if (httpResponse.isSuccessful()) {
            return httpResponse.getBodyString();
        }
        return "";
    }

    /**
     *生效营销方案
     *  日期 2017/7/24.
     */
    public  String activateProgrammes(String json) {
        HTTPResponse httpResponse = djProduct.activateProgrammes(json);
        if (httpResponse.isSuccessful()) {
            return httpResponse.getBodyString();
        }
        return "";
    }

    /**
     *申请审核方案
     *  日期 2017/7/24.
     */
    public   String  applyeExamineProgrammes(String json) {
        HTTPResponse httpResponse = djProduct.applyeExamineProgrammes(json);
        if (httpResponse.isSuccessful()) {
            return httpResponse.getBodyString();
        }
        return "";
    }

    /**
     * 审核方案
     *  日期 2017/7/24.
     */
    public String  examineProgrammes(String json) {
        HTTPResponse httpResponse = djProduct.examineProgrammes(json);
        if (httpResponse.isSuccessful()) {
            return httpResponse.getBodyString();
        }
        return "";
    }

    /**
     *删除营销方案
     *  日期 2017/7/24.
     */
    public String deleteProgrammes(String json) {
        HTTPResponse httpResponse = djProduct.deleteProgrammes(json);
        if (httpResponse.isSuccessful()) {
            return httpResponse.getBodyString();
        }
        return "";
    }


    /**
     * 更新订单排序权重
     *  日期 2017/7/24.
     */
    public  String updateProductsForder(String json) {
        HTTPResponse httpResponse = djProduct.updateProductsForder(json);
        if (httpResponse.isSuccessful()) {
            return httpResponse.getBodyString();
        }
        return "";
    }

    /**
     *产品审核申请
     * 日期 2017/7/24.
     */
    public    String  applyeExamineProduct(String json) {
        HTTPResponse httpResponse = djProduct.applyeExamineProduct(json);
        if (httpResponse.isSuccessful()) {
            return httpResponse.getBodyString();
        }
        return "";
    }

    /**
     *产品审核操作
     * 日期 2017/7/24.
     */
    public    String  examineProduct(String json) {
        HTTPResponse httpResponse = djProduct.examineProduct(json);
        if (httpResponse.isSuccessful()) {
            return httpResponse.getBodyString();
        }
        return "";
    }

    /**
     *获取开团时间
     * 日期 2017/7/24.
     */
    public    String  getGroupOpenCycle(String json) {
        HTTPResponse httpResponse = djProduct.getGroupOpenCycle(json);
        if (httpResponse.isSuccessful()) {
            return httpResponse.getBodyString();
        }
        return "";
    }

    /**
     *获取不开团时间
     * 日期 2017/7/24.
     */
    public    String  getGroupHugh(String json) {
        HTTPResponse httpResponse = djProduct.getGroupHugh(json);
        if (httpResponse.isSuccessful()) {
            return httpResponse.getBodyString();
        }
        return "";
    }
    /**
     *设置不开团时间
     * 日期 2017/7/24.
     */
    public    String  setGroupHugh(String json) {
        HTTPResponse httpResponse = djProduct.setGroupHugh(json);
        if (httpResponse.isSuccessful()) {
            return httpResponse.getBodyString();
        }
        return "";
    }



    /**
     * 上传图片
     * @return json
     */
    public Map<String, Object> uploadFigure(MultipartFileBean bean) throws Exception{

        Map<String, okhttp3.RequestBody> params=new HashMap<>();
        params.put("moduleid",okhttp3.RequestBody.create(null,""+bean.getModuleid()));
        MultipartBody.Part filePart= BannerUtils.filesToMultipartBody(bean.getFile());
        HTTPResponse httpResponse = pfUpload.uploadFigure(params,filePart);


        if (null!=httpResponse&&httpResponse.isSuccessful()) {
            try {
                ResultImgBean imgBean= DJGson.gson.fromJson(""+getMap(httpResponse.getBodyString()).get("data"),ResultImgBean.class);
                return successMsg(imgBean);
            } catch (Exception e) {
                return failureMsg(OPS_FAILURE);
            }
        }
        return failureMsg(OPS_FAILURE);
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
     *加载方案审核列表
     * 日期 2017/7/24.
     */
    public    String  loadListsPMarketing(String json) {
        HTTPResponse httpResponse = djProduct.loadListsPMarketing(json);
        if (httpResponse.isSuccessful()) {
            return httpResponse.getBodyString();
        }
        return "";
    }

    /**
     *区域检索产品
     * 日期 2017/7/24.
     */
    public    String  loadListsForAddres(String json) {
        HTTPResponse httpResponse = djProduct.loadListsForAddres(json);
        if (httpResponse.isSuccessful()) {
            return httpResponse.getBodyString();
        }
        return "";
    }





    private static final Logger logger = LoggerFactory.getLogger(ProductService.class);


}
