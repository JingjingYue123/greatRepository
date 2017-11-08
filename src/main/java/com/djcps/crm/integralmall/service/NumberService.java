package com.djcps.crm.integralmall.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.djcps.crm.integralmall.server.NumberServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by L-wenbin on 2017/9/18.
 */
@Service
public class NumberService {
    //日志文件
    private static final Logger logger = LoggerFactory.getLogger(ProductService.class);

    @Autowired
    private NumberServer numberServer;

    /**
     * 获取编号
     * @return
     */
    public Map<String,Object> getNumber(int count) {
        String result = numberServer.getNumber(count).getBodyString();
        JSONObject jsonObject = JSON.parseObject(result,JSONObject.class);
        logger.debug("params: {}", jsonObject);
        return jsonObject;
    }
}
