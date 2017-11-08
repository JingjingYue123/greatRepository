package com.djcps.crm.integralmall.controller;

import com.djcps.crm.integralmall.service.NumberService;
import com.djcps.crm.integralmall.service.PhotosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Created by L-wenbin on 2017/9/18.
 */
@RestController
public class NumberController {
    @Autowired
    private NumberService numberService;

    /**
     * 获取编号
     * @param count 获取多少个
     * @return
     */
    @RequestMapping(value = "numberController/getNumber" , method = RequestMethod.POST)
    public Map<String,Object> getNumber(@RequestParam(name = "count") int count) {
        Map<String, Object> result = numberService.getNumber(count);
        return result;
    }
}
