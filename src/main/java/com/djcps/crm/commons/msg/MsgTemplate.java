package com.djcps.crm.commons.msg;

import com.baidu.unbiz.fluentvalidator.ComplexResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.google.gson.*;
import org.springframework.http.converter.json.GsonFactoryBean;

import java.util.HashMap;
import java.util.Map;

/**
 * 统一输出规范
 * Created by truth on 2017-03-07.
 */
public class MsgTemplate {

    private static final Logger logger = LoggerFactory.getLogger(MsgTemplate.class);

    /**
     * 成功输出
     * @return
     */
    public static Map<String, Object> successMsg() {
        return successMsg(new Object());
    }

    /**
     * 成功输出
     * @param data 输出数据
     * @return
     */
    public static Map<String, Object> successMsg(Object data) {
        return customMsg(true, 10000, "", data);
    }

    /**
     * 用于服务输出对象转发输出
     * @param data 输出数据
     * @return
     */
    public static Map<String, Object> successMsg(String data) {
        return customMsg(data);
    }

    /**
     * 错误输出
     * @param message MsgInterface接口类型
     * @return
     */
    public static Map<String, Object> failureMsg(MsgInterface message) {
        return customMsg(false, message.getCode(), message.getMsg(), null);
    }

    /**
     * 错误输出
     * @param message MsgInterface接口类型
     * @return
     */
    public static Map<String, Object> failureMsg(MsgInterface message, Object data) {
        return customMsg(false, message.getCode(), message.getMsg(), data);
    }

    /**
     * 错误输出,不建议使用，违反统一输出规范
     * @param error 错误输出
     * @return
     */
    @Deprecated
    public static Map<String, Object> failureMsg(String error) {
        return customMsg(false, -10000, error, null);
    }

    /**
     * 错误输出,不建议使用，违反统一输出规范
     * @param error 错误输出
     * @return
     */
    @Deprecated
    public static Map<String, Object> failureMsg(int msgCode,String error) {
        return customMsg(false, msgCode, error, "");
    }

    /**
     * 错误输出
     * @param ret 错误输出
     * @return
     */
    public static Map<String, Object> failureMsg(ComplexResult ret) {
        if(ret.getErrors().size() > 0){

            return customMsg(false,
                    ret.getErrors().get(0).getErrorCode() > 0 ?  ret.getErrors().get(0).getErrorCode() : -10000,
                    ret.getErrors().get(0).getErrorMsg() + "，错误字段："+ret.getErrors().get(0).getField(), null);
        }
        return customMsg(false, -10000, null, null);
    }




    /**
     * Custom map.
     *
     * @param success the success
     * @param msgCode the enums code
     * @param message the message
     * @param data    the data
     * @return the map
     */
    private static Map<String, Object> customMsg(boolean success, int msgCode, String message, Object data) {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("success", success);
        result.put("code", msgCode);
        result.put("msg", message);
        result.put("data", data);
        logger.info("返回输出：",result);
        return result;
    }

    /**
     * 标准gson输出格式装换Map
     * @param data 源数据
     * @return the map
     */
    private static Map<String, Object> customMsg(String data) {
        Map<String, Object> result = new HashMap<String, Object>();
        try {
            JsonParser parser = new JsonParser();
            JsonObject object=parser.parse(data).getAsJsonObject();
            result.put("success", object.has("success") ? object.get("success") : false);
            result.put("code", object.has("code") ? object.get("code") : "");
            result.put("msg", object.has("msg") ? object.get("msg") : "");
            result.put("data", object.has("data") ? object.get("data") : "");
            if(object.has("total") ){
                result.put("total", object.has("total") ? object.get("total") : "");
            }
            if(object.has("totalCount") ){
                result.put("totalCount", object.has("totalCount") ? object.get("totalCount") : "");
            }
            return result;
        } catch (Exception e) {
            result = new HashMap<String, Object>();
            e.printStackTrace();
        }
        logger.info("返回输出：",result);
        return result;
    }




}