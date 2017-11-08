package com.djcps.crm.commons.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.List;

/**
 * Created by jmb on 2017/9/28.
 * 以下方法都有可能返回一个null返回是null的时候就表示请求出错了
 */
public class ReturnInfoUtil {
    /**
     * 解析返回数据是list集合的 不带total
     * @param response
     * @param t
     * @param <T>
     * @return
     */
    public static<T> List<T> returnList(String response, Class<T> t){
        ReturnInfoModel returnInfoModel = JSONObject.parseObject(response, ReturnInfoModel.class);
        if (org.springframework.util.StringUtils.isEmpty(returnInfoModel.getData())){
            return null;
        }
        List<T> ts = JSON.parseArray(returnInfoModel.getData(), t);
        return ts;
    }

    /**
     * 解析返回数据是单个对象的
     * @param response
     * @param t
     * @param <T>
     * @return
     */
    public static <T> T returnObject(String response,Class<T> t){
        ReturnInfoModel returnInfoModel = JSONObject.parseObject(response, ReturnInfoModel.class);
        if (org.springframework.util.StringUtils.isEmpty(returnInfoModel.getData())){
            return null;
        }
        T t1 = JSONObject.parseObject(returnInfoModel.getData(), t);
        return t1;
    }

    /**
     * 返回一个装有total跟目标对象集合的object
     * 此方法没有经过验证，慎用
     * @param response
     * @param t
     * @param <T>
     * @return
     */
    public static<T> ReturnData<T> returnListWithTotal(String response,Class<T> t){
        ReturnInfoModel returnInfoModel = JSONObject.parseObject(response, ReturnInfoModel.class);
        if (org.springframework.util.StringUtils.isEmpty(returnInfoModel.getData())){
            return null;
        }
        ReturnData<T> returnData=new ReturnData<T>();
        return JSONObject.parseObject(returnInfoModel.getData(),returnData.getClass());
    }

}
