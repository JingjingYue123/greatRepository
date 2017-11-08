package com.djcps.crm.integralmall.controller;

import com.djcps.crm.commons.msg.MsgTemplate;
import com.djcps.crm.integralmall.enums.IntegralmallEnum;
import com.djcps.crm.integralmall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import javax.crypto.MacSpi;
import java.util.Map;

/**
 * Created by L-wenbin on 2017/8/29.
 */
@RestController
public class IntegralmallProductController {

    @Qualifier("integralmallProductService")
    @Autowired
    private ProductService productService;

    /**
     * 分类管理初期化
     *
     * @return
     */
    @RequestMapping(value = "productController/initClassify", method = RequestMethod.POST)
    public Map<String, Object> initClassify() {
        Map<String, Object> initClassify = productService.initClassify();
        return initClassify;
    }

    /**
     * 商品标签查询
     *
     * @return
     */
    @RequestMapping(value = "productController/initLabel", method = RequestMethod.POST)
    public Map<String, Object> initLabel() {
        Map<String, Object> initLabel = productService.initLabel();
        return initLabel;
    }

    /**
     * 商品规格查询
     *
     * @return
     */
    @RequestMapping(value = "productController/initSpec", method = RequestMethod.POST)
    public Map<String, Object> initSpec() {
        Map<String, Object> initSpec = productService.initSpec();
        return initSpec;
    }

    /**
     * 保存商品分类
     *
     * @return
     */
    @RequestMapping(value = "productController/saveClassify", method = RequestMethod.POST)
    public Map<String, Object> saveClassify(@RequestBody String json) {
        if (json != null) {
            Map<String, Object> saveClassify = productService.saveClassify(json);
            return saveClassify;
        }
        return MsgTemplate.failureMsg(IntegralmallEnum.ADD_FAILURE);
    }

    /**
     * 保存商品标签
     *
     * @return
     */
    @RequestMapping(value = "productController/saveLabel", method = RequestMethod.POST)
    public Map<String, Object> saveLabel(@RequestBody String json) {
        if (json != null) {
            Map<String, Object> saveLabel = productService.saveLabel(json);
            return saveLabel;
        }
        return MsgTemplate.failureMsg(IntegralmallEnum.ADD_FAILURE);
    }

    /**
     * 保存商品规格
     *
     * @return
     */
    @RequestMapping(value = "productController/saveSpec", method = RequestMethod.POST)
    public Map<String, Object> saveSpec(@RequestBody String json) {
        if (json != null) {
            Map<String, Object> saveSpec = productService.saveSpec(json);
            return saveSpec;
        }
        return MsgTemplate.failureMsg(IntegralmallEnum.ADD_FAILURE);
    }

    /**
     * 删除分类信息
     *
     * @return
     */
    @RequestMapping(value = "productController/delClassify", method = RequestMethod.POST)
    public Map<String, Object> delClassify(@RequestParam(name = "catId", required = false) String catId) {
        Map<String, Object> delClassify = productService.delClassify(catId);
        return delClassify;
    }

    /**
     * 删除标签信息
     *
     * @return
     */
    @RequestMapping(value = "productController/delLabel", method = RequestMethod.POST)
    public Map<String, Object> delLabel(@RequestParam(name = "id", required = false) String id) {
        Map<String, Object> delLabel = productService.delLabel(id);
        return delLabel;
    }

    /**
     * 删除规格信息
     *
     * @return
     */
    @RequestMapping(value = "productController/delSpec", method = RequestMethod.POST)
    public Map<String, Object> delSpec(@RequestParam(name = "id", required = false) String id) {
        Map<String, Object> delSpec = productService.delSpec(id);
        return delSpec;
    }

    /**
     * 全部商品页面分页查询
     *
     * @return
     */
    @RequestMapping(value = "productController/searchAllGoodsPage", method = RequestMethod.POST)
    public Map<String, Object> searchAllGoodsPage(
            @RequestParam(name = "goodName", required = false) String goodName,
            @RequestParam(name = "status", required = false) Integer status,
            @RequestParam(name = "labelId", required = false) String labelId,
            @RequestParam(name = "current", required = false, defaultValue = "1") int current,
            @RequestParam(name = "size", required = false, defaultValue = "15") int size) {
        Map<String, Object> initClassify = productService.searchAllGoodsPage(goodName, labelId,status, current, size);
        return initClassify;
    }

    /**
     * 商品上下架删除服务
     *
     * @return
     */
    @RequestMapping(value = "productController/editGoodsStatus", method = RequestMethod.POST)
    public Map<String, Object> editGoodsStatus(
            @RequestParam(name = "goodIds[]", required = false) String[] goodIds,
            @RequestParam(name = "status", required = false) Integer status,
            @RequestParam(name = "userName", required = false) String userName,
            @RequestParam(name = "userId", required = false ) String userId) {
        Map<String, Object> editGoodsStatus = productService.editGoodsStatus(goodIds, status, userName,userId);
        return editGoodsStatus;
    }

    /**
     * 新增或修改商品详情页面
     *
     * @return
     */
    @RequestMapping(value = "productController/editGoods", method = RequestMethod.POST)
    public Map<String, Object> editGoods(@RequestParam(name = "goodId", required = false) String goodId) {
        Map<String, Object> editGoods = productService.editGoods(goodId);
        return editGoods;
    }

    /**
     * 保存或者保存上架商品
     *
     * @return
     */
    @RequestMapping(value = "productController/saveGoods", method = RequestMethod.POST)
    public Map<String, Object> saveGoods(@RequestBody String json) {
        if(json != null) {
            Map<String, Object> saveGoods = productService.saveGoods(json);
            return saveGoods;
        }
        return MsgTemplate.failureMsg(IntegralmallEnum.ADD_FAILURE);
    }

    /**
     * 在售商品查询
     *
     * @return
     */
    @RequestMapping(value = "productController/searchGoodsOnSalePage", method = RequestMethod.POST)
    public Map<String, Object> searchGoodsOnSalePage(
            @RequestParam(name = "goodName", required = false) String goodName,
            @RequestParam(name = "goodTypeId", required = false) String goodTypeId,
            @RequestParam(name = "goodTypel1", required = false) String goodTypel1,
            @RequestParam(name = "goodTypel2", required = false) String goodTypel2,
            @RequestParam(name = "page", required = false, defaultValue = "1") int page,
            @RequestParam(name = "size", required = false, defaultValue = "25") int size) {
        Map<String, Object> searchGoodsOnSalePage = productService.searchGoodsOnSalePage(goodName, goodTypeId, goodTypel1, goodTypel2, page, size);
        return searchGoodsOnSalePage;
    }

    /**
     * 保存商品顺序和热门推荐
     *
     * @return
     */
    @RequestMapping(value = "productController/saveGoodsOnSale", method = RequestMethod.POST)
    public Map<String, Object> saveGoodsOnSale(@RequestBody String json) {
        if(json != null){
            Map<String, Object> saveGoodsOnSale = productService.saveGoodsOnSale(json);
            return saveGoodsOnSale;
        }
        return MsgTemplate.failureMsg(IntegralmallEnum.ADD_FAILURE);
    }

    /**
     * 发货类型
     *
     * @return
     */
    @RequestMapping(value = "productController/shipType", method = RequestMethod.POST)
    public Map<String, Object> shipType() {
        Map<String, Object> saveGoodsOnSale = productService.shipType();
        return saveGoodsOnSale;
    }
    /**
     * 查询所所有已上架商品
     *
     * @return
     */
    @RequestMapping(value = "productController/searchAllGoods", method = RequestMethod.POST)
    public Map<String, Object> searchAllGoods() {
        Map<String, Object> result = productService.searchAllGoods();
        return result;
    }
}
