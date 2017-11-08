package com.djcps.crm.finance.controller;

import com.djcps.crm.aop.log.AddLog;
import com.djcps.crm.finance.service.PayBankService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

import static com.djcps.crm.commons.msg.MsgTemplate.*;
import static com.djcps.crm.finance.enums.RechargeOrderEnum.*;

/**
 * Created by jmb on 2017/8/24.
 */
@RestController
public class PayBankController {

    @Autowired
    private PayBankService payBankService;

    private static final Logger logger = LoggerFactory.getLogger(RechargeOrderController.class);

    @RequestMapping(name = "提现列表",value="finance/payBankwithdrawallist",method = {RequestMethod.POST})
    @AddLog(module = "财务模块",value = "提现列表")
    public Map<String, Object> payBankwithdrawallist(@RequestBody String json){
        try {
            String jsonStr = payBankService.payBankwithdrawallist(json);
            logger.debug(jsonStr);
            return successMsg(jsonStr);
        }catch (Exception e){
            logger.error("获取提现列表失败"+e.getCause());
            return failureMsg(SYS_ERROR);

        }
    }
}
