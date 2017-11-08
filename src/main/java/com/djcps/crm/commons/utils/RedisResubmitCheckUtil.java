package com.djcps.crm.commons.utils;

import com.alibaba.fastjson.JSONObject;
import com.baidu.unbiz.fluentvalidator.ComplexResult;
import com.baidu.unbiz.fluentvalidator.FluentValidator;
import com.djcps.crm.commons.msg.MsgInterface;
import com.djcps.crm.commons.msg.MsgTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Map;
import java.util.UUID;

import static com.baidu.unbiz.fluentvalidator.ResultCollectors.toComplex;

/**
 * Created by jmb on 2017/9/22.
 */
@RestController
public class RedisResubmitCheckUtil {

    @Autowired
    private JedisCluster jedisCluster;

    /**
     * 该接口用于拿到接口唯一值
     * @return
     */
    @RequestMapping(name = "该接口用于得到唯一值",value = "crm/getUniqueKey",method = {RequestMethod.POST})
    public Map<String,Object> getUniqueKey(@RequestBody String json){
        RequestModel requestModel = JSONObject.parseObject(json, RequestModel.class);
        ComplexResult ret = FluentValidator.checkAll().failFast()
                .on(requestModel, new HibernateValidator<RequestModel>().Validator())
                .doValidate()
                .result(toComplex());
        if (ret.isSuccess()){
            JSONObject object=new JSONObject();
            object.put("key",setKey(requestModel.getAccessKey()));
           return MsgTemplate.successMsg(object);
        }else {
            return MsgTemplate.failureMsg(ret);
        }
    }

    @RequestMapping("test")
    public String test(String uuid){
        boolean aaa = checkResubmit("aaa", uuid);
        return aaa?"没有重复提交":"重复提交了";
    }

    /**
     * 校验重复提交
     *
     * @param accessKey 接口地址
     * @param uniqueKey 表单提交所携带的唯一值
     * @return 返回true：没有重复提交，返回false：重复提交了
     */
    public boolean checkResubmit(String accessKey,String uniqueKey){
        String uuidArr = jedisCluster.get("crm:" + accessKey);
        if (uuidArr==null){
            return false;
        }
        if (uuidArr.length()==32){
            if (uuidArr.contains(uniqueKey)){
                jedisCluster.set("crm:"+accessKey,uuidArr.replace(uniqueKey,""));
                return true;
            }
        }else if (uuidArr.length()>32){
            if (uuidArr.contains(uniqueKey)){
                jedisCluster.set("crm:"+accessKey,uuidArr.replace("-"+uniqueKey,""));
                return true;
            }
        }
        return false;
    }

    /**
     * 添加uuid到redis中以键值对的形式存在
     * 接口地址为键
     * @param accessKey 接口地址
     * @return 返回新增加的唯一值
     */
    private String setKey(String accessKey){
        String uuid=UUID.randomUUID().toString().replace("-","");
        String uuidArr = jedisCluster.get("crm:" + accessKey);
        if (uuidArr==null){
            jedisCluster.set("crm:"+accessKey,uuid);
        }
        jedisCluster.set("crm:"+accessKey,uuidArr+"-"+uuid);
        return uuid;
    }

    /**
     * 返回参数的枚举
     */
    enum RedisResubmitCheckEnum implements MsgInterface{
        FALSE(100001,"操作失败");
        private int code;
        private String msg;

        RedisResubmitCheckEnum(int code, String msg) {
            this.code = code;
            this.msg = msg;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }
    }

    /**
     * 获取唯一值请求Model
     */
    static class RequestModel{
        //版本号
        @NotNull(message = "版本号不能为空")
        @Pattern(regexp = "1.[0]",message = "目前版本号仅支持1.0")
        private String version;
        //接口地址
        @NotNull(message = "接口地址不能为空")
        private String accessKey;

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }

        public String getAccessKey() {
            return accessKey;
        }

        public void setAccessKey(String accessKey) {
            this.accessKey = accessKey;
        }
    }
}
