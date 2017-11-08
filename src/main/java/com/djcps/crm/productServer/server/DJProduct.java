package com.djcps.crm.productServer.server;

import com.djcps.crm.commons.config.ParamsConfig;
import retrofit2.http.Body;
import retrofit2.http.POST;
import rpc.plugin.http.HTTPResponse;
import rpc.plugin.http.RPCClientFields;

/**
 * Created by ZhangMingHui on 2017/8/8.
 */
@RPCClientFields(urlfield ="PRODUCT_URL",urlbean = ParamsConfig.class)
public interface DJProduct {

    /**
     * 管理产品列表
     * @return
     */
    @POST("product/loadProducts.do")
    HTTPResponse loadProducts(@Body String json);

    /**
     *添加/编辑产品
     *  日期 2017/7/24.
     */
    @POST("product/addOrEditProducts.do")
    HTTPResponse addOrEditProducts(@Body String json);

    /**
     *删除图片
     *  日期 2017/7/24.
     */
    @POST("product/deleteFigure.do")
    HTTPResponse deleteFigure(@Body String json);

//    /**
//     *设置开团时间
//     * 日期 2017/7/24.
//     */
//    @POST("product/saveOrUpdataTime.do")
//    HTTPResponse saveOrUpdataTime(@Body String json);

    /**
     *删除产品
     *  日期 2017/7/24.
     */
    @POST("product/deleteProduct.do")
    HTTPResponse deleteProduct(@Body String json);

    /**
     *撤销申请===产品
     *  日期 2017/7/24.
     */
    @POST("product/revokeApplyProduct.do")
    HTTPResponse revokeApplyProduct(@Body String json);

    /**
     *撤销申请===方案
     *  日期 2017/7/24.
     */
    @POST("product/revokeApplyProgramme.do")
    HTTPResponse revokeApplyProgramme(@Body String json);

    /**
     *添加营销方案
     *  日期 2017/7/24.
     */
    @POST("product/addOrEditProgramme.do")
    HTTPResponse addOrEditProgramme(@Body String json);

    /**
     *编辑产品
     *  日期 2017/7/24.
     */
    @POST("product/updateProgramme.do")
    HTTPResponse updateProgramme(@Body String json);


    /**
     *以条件加载单产品营销方案列表
     *  日期 2017/7/24.
     */
    @POST("product/loadProgrammes.do")
    HTTPResponse loadProgrammes(@Body String json);


    /**
     *加载营销方案详情
     *  日期 2017/7/24.
     */
    @POST("product/loadProgrammesDetails.do")
    HTTPResponse loadProgrammesDetails(@Body String json);

    /**
     *生效营销方案
     *  日期 2017/7/24.
     */
    @POST("product/activateProgrammes.do")
    HTTPResponse activateProgrammes(@Body String json);

    /**
     *申请审核方案
     *  日期 2017/7/24.
     */
    @POST("product/applyeExamineProgrammes.do")
    HTTPResponse applyeExamineProgrammes(@Body String json);

    /**
     * 审核方案
     *  日期 2017/7/24.
     */
    @POST("product/examineProgrammes.do")
    HTTPResponse  examineProgrammes(@Body String json);

    /**
     *删除营销方案
     *  日期 2017/7/24.
     */
    @POST("product/deleteProgrammes.do")
    HTTPResponse deleteProgrammes(@Body String json);


    /**
     * 更新订单排序权重
     *  日期 2017/7/24.
     */
    @POST("product/updateProductsForder.do")
    HTTPResponse updateProductsForder(@Body String json);

    /**
     *产品审核申请
     * 日期 2017/7/24.
     */
    @POST("product/applyeExamineProduct.do")
    HTTPResponse applyeExamineProduct(@Body String json);

    /**
     *产品审核操作
     * 日期 2017/7/24.
     */
    @POST("product/examineProduct.do")
    HTTPResponse examineProduct(@Body String json);

    /**
     *获取开团时间
     * 日期 2017/7/24.
     */
    @POST("product/getGroupOpenCycle.do")
    HTTPResponse getGroupOpenCycle(@Body String json);
    /**
     *设置开团时间
     * 日期 2017/7/24.
     */
    @POST("product/setGroupOpenCycle.do")
    HTTPResponse setGroupOpenCycle(@Body String json);


    /**
     *获取不开团时间
     * 日期 2017/7/24.
     */
    @POST("product/getGroupHugh.do")
    HTTPResponse getGroupHugh(@Body String json);

    /**
     *设置不开团时间
     * 日期 2017/7/24.
     */
    @POST("product/setGroupHugh.do")
    HTTPResponse setGroupHugh(@Body String json);


    /**
     *加载方案审核列表
     * 日期 2017/7/24.
     */
    @POST("product/loadListsPMarketing.do")
    HTTPResponse loadListsPMarketing(@Body String json);

    /**
     *区域检索产品
     * 日期 2017/7/24.
     */
    @POST("external/loadListsForAddres.do")
    HTTPResponse loadListsForAddres(@Body String json);


}
