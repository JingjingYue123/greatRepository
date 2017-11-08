package com.djcps.crm.commons.utils;

/**
 * 校验工具类
 * Created by Lancher on 2017/8/4.
 */
public class CheckUtils {

    /**
     * 空判断
     * @param parameters 需要判断的list
     * @return 返回长度为0则表示没有空，大于0则表示有空
     */
    public static String parametersEmpty(Object[] parameters){
        for (int i = 0; i < parameters.length; i++){
            if (parameters[i] == null || parameters[i].toString().length() <= 0){
                return "缺少必要参数";
            }
        }
        return "";
    }
}
