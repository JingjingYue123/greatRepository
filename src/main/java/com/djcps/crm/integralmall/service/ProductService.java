package com.djcps.crm.integralmall.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.djcps.crm.integralmall.server.ProductHttpServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by L-wenbin on 2017/8/29.
 */
@Service("integralmallProductService")
public class ProductService {
    @Autowired
    private ProductHttpServer productHttpServer;

    private static final Logger logger = LoggerFactory.getLogger(ProductService.class);

    /**
     * 分类管理初期化
     * @return
     */
    public Map<String,Object> initClassify() {
        String result = productHttpServer.initClassify().getBodyString();
        JSONObject jsonObject = JSON.parseObject(result,JSONObject.class);
        logger.debug("params: {}", jsonObject);
        return jsonObject;
    }
    /**
     * 商品标签查询
     * @return
     */
    public Map<String,Object> initLabel() {
        String result = productHttpServer.initLabel().getBodyString();
        JSONObject jsonObject = JSON.parseObject(result,JSONObject.class);
        logger.debug("params: {}", jsonObject);
        return jsonObject;
    }
    /**
     * 商品规格查询
     * @return
     */
    public Map<String,Object> initSpec() {
        String result = productHttpServer.initSpec().getBodyString();
        JSONObject jsonObject = JSON.parseObject(result,JSONObject.class);
        logger.debug("params: {}", jsonObject);
        return jsonObject;
    }
    /**
     * 保存商品分类
     * @return
     */
    public Map<String,Object> saveClassify(String json) {
        String result = productHttpServer.saveClassify(json).getBodyString();
        JSONObject jsonObject = JSON.parseObject(result,JSONObject.class);
        logger.debug("params: {}", jsonObject);
        return jsonObject;
    }
    /**
     * 保存商品标签
     * @return
     */
    public Map<String,Object> saveLabel(String json) {
        String result = productHttpServer.saveLabel(json).getBodyString();
        JSONObject jsonObject = JSON.parseObject(result,JSONObject.class);
        logger.debug("params: {}", jsonObject);
        return jsonObject;
    }
    /**
     * 保存商品规格
     * @return
     */
    public Map<String,Object> saveSpec(String json) {
        String result = productHttpServer.saveSpec(json).getBodyString();
        JSONObject jsonObject = JSON.parseObject(result,JSONObject.class);
        logger.debug("params: {}", jsonObject);
        return jsonObject;
    }
    /**
     * 删除分类信息
     * @return
     */
    public Map<String,Object> delClassify(String catId) {
        String result = productHttpServer.delClassify(catId).getBodyString();
        JSONObject jsonObject = JSON.parseObject(result,JSONObject.class);
        logger.debug("params: {}", jsonObject);
        return jsonObject;
    }
    /**
     * 删除标签信息
     * @return
     */
    public Map<String,Object> delLabel(String id) {
        String result = productHttpServer.delLabel(id).getBodyString();
        JSONObject jsonObject = JSON.parseObject(result,JSONObject.class);
        logger.debug("params: {}", jsonObject);
        return jsonObject;
    }
    /**
     * 删除规格信息
     * @return
     */
    public Map<String,Object> delSpec(String id) {
        String result = productHttpServer.delSpec(id).getBodyString();
        JSONObject jsonObject = JSON.parseObject(result,JSONObject.class);
        logger.debug("params: {}", jsonObject);
        return jsonObject;
    }
    /**
     * 全部商品页面分页查询
     * @return
     */
    public Map<String,Object> searchAllGoodsPage(String goodName,String labelId,Integer status,int current,int size) {
        String result = productHttpServer.searchAllGoodsPage(goodName,labelId,status,current,size).getBodyString();
        JSONObject jsonObject = JSON.parseObject(result,JSONObject.class);
        logger.debug("params: {}", jsonObject);
        return jsonObject;
    }
    /**
     * 商品上下架删除服务
     * @return
     */
    public Map<String,Object> editGoodsStatus(String[] goodIds,Integer status,String userName,String userId) {
        String result = productHttpServer.editGoodsStatus(goodIds,status,userName,userId).getBodyString();
        JSONObject jsonObject = JSON.parseObject(result,JSONObject.class);
        logger.debug("params: {}", jsonObject);
        return jsonObject;
    }
    /**
     * 新增或修改商品详情页面
     * @return
     */
    public Map<String,Object> editGoods(String goodId) {
        String result = productHttpServer.editGoods(goodId).getBodyString();
        JSONObject jsonObject = JSON.parseObject(result,JSONObject.class);
        logger.debug("params: {}", jsonObject);
        return jsonObject;
    }
    /**
     * 保存或者保存上架商品
     * @return
     */
    public Map<String,Object> saveGoods(String json) {
        String result = productHttpServer.saveGoods(json).getBodyString();
        JSONObject jsonObject = JSON.parseObject(result,JSONObject.class);
        logger.debug("params: {}", jsonObject);
        return jsonObject;
    }
    /**
     * 在售商品查询
     * @return
     */
    public Map<String,Object> searchGoodsOnSalePage(String goodName,String goodTypeId,String goodTypel1,String goodTypel2,int page,int size) {
        String result = productHttpServer.searchGoodsOnSalePage(goodName,goodTypeId,goodTypel1,goodTypel2,page,size).getBodyString();
        JSONObject jsonObject = JSON.parseObject(result,JSONObject.class);
        logger.debug("params: {}", jsonObject);
        return jsonObject;
    }
    /**
     * 保存商品顺序和热门推荐
     * @return
     */
    public Map<String,Object> saveGoodsOnSale(String json) {
        String result = productHttpServer.saveGoodsOnSale(json).getBodyString();
        JSONObject jsonObject = JSON.parseObject(result,JSONObject.class);
        logger.debug("params: {}", jsonObject);
        return jsonObject;
    }
    /**
     * 发货类型
     * @return
     */
    public Map<String,Object> shipType() {
        String result = productHttpServer.shipType().getBodyString();
        JSONObject jsonObject = JSON.parseObject(result,JSONObject.class);
        logger.debug("params: {}", jsonObject);
        return jsonObject;
    }
    /**
     * 查询所有已上架商品
     * @return
     */
    public Map<String,Object> searchAllGoods() {
        String result = productHttpServer.searchAllGoods().getBodyString();
        JSONObject jsonObject = JSON.parseObject(result,JSONObject.class);
        logger.debug("params: {}", jsonObject);
        return jsonObject;
    }

}
