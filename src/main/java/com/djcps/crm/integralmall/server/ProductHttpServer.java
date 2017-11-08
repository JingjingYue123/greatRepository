package com.djcps.crm.integralmall.server;

import com.djcps.crm.commons.config.ParamsConfig;
import retrofit2.http.*;
import rpc.plugin.http.HTTPResponse;
import rpc.plugin.http.RPCClientFields;

/**
 * Created by L-wenbin on 2017/8/29.
 */
@RPCClientFields(urlfield ="IntegralProduct_Mall",urlbean = ParamsConfig.class)
public interface ProductHttpServer {
    /**
     * 分类管理初期化
     * @return
     */
    @POST("mallBasicSetting/initClassify.do")
    HTTPResponse initClassify();
    /**
     * 商品标签查询
     * @return
     */
    @POST("mallBasicSetting/initLabel.do")
    HTTPResponse initLabel();
    /**
     * 商品规格查询
     * @return
     */
    @POST("mallBasicSetting/initSpec.do")
    HTTPResponse initSpec();
    /**
     * 保存商品分类
     * @return
     */
    @POST("mallBasicSetting/saveClassify.do")
    HTTPResponse saveClassify(@Body String json);
    /**
     * 保存商品标签
     * @return
     */
    @POST("mallBasicSetting/saveLabel.do")
    HTTPResponse saveLabel(@Body String json);
    /**
     * 保存商品规格
     * @return
     */
    @POST("mallBasicSetting/saveSpec.do")
    HTTPResponse saveSpec(@Body String json);
    /**
     * 删除分类信息
     * @return
     */
    @FormUrlEncoded
    @POST("mallBasicSetting/delClassify.do")
    HTTPResponse delClassify(@Field("catId") String catId);
    /**
     * 删除标签信息
     * @return
     */
    @FormUrlEncoded
    @POST("mallBasicSetting/delLabel.do")
    HTTPResponse delLabel(@Field("id")String id);
    /**
     * 删除规格信息
     * @return
     */
    @FormUrlEncoded
    @POST("mallBasicSetting/delSpec.do")
    HTTPResponse delSpec(@Field("id")String id);
    /**
     * 全部商品页面分页查询
     * @return
     */
    @FormUrlEncoded
    @POST("mallIntegralGoods/searchAllGoodsPage.do")
    HTTPResponse searchAllGoodsPage(@Field("goodName")String goodName,
                                    @Field("labelId")String labelId,
                                    @Field("status")Integer status,
                                    @Field("current")int current,
                                    @Field("size")int size);
    /**
     * 商品上下架删除服务
     * @return
     */
    @FormUrlEncoded
    @POST("mallIntegralGoods/editGoodsStatus.do")
    HTTPResponse editGoodsStatus(@Field("goodIds[]")String[] goodIds,
                                 @Field("status")Integer status,
                                 @Field("userName")String userName,
                                 @Field("userId")String userId);
    /**
     * 新增或修改商品详情页面
     * @return
     */
    @FormUrlEncoded
    @POST("mallIntegralGoods/editGoods.do")
    HTTPResponse editGoods(@Field("goodId")String goodId);
    /**
     * 保存或者保存上架商品
     * @return
     */
    @POST("mallIntegralGoods/saveGoods.do")
    HTTPResponse saveGoods(@Body String json);
    /**
     * 在售商品查询
     * @return
     */
    @FormUrlEncoded
    @POST("mallIntegralGoods/searchGoodsOnSalePage.do")
    HTTPResponse searchGoodsOnSalePage(@Field("goodName")String goodName,
                                       @Field("goodTypeId")String goodTypeId,
                                       @Field("goodTypel1")String goodTypel1,
                                       @Field("goodTypel2")String goodTypel2,
                                       @Field("page")int page,
                                       @Field("size")int size);
    /**
     * 保存商品顺序和热门推荐
     * @return
     */
    @POST("mallIntegralGoods/saveGoodsOnSale.do")
    HTTPResponse saveGoodsOnSale(@Body String json);
    /**
     * 发货类型
     * @return
     */
    @POST("mallIntegralGoods/shipType.do")
    HTTPResponse shipType();
    /**
     * 查询所有已上架商品
     * @return
     */
    @POST("mallIntegralGoods/searchAllGoods.do")
    HTTPResponse searchAllGoods();
}
