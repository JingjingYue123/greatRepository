package com.djcps.crm.finance.service;

import com.djcps.crm.finance.server.PayBankServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rpc.plugin.http.HTTPResponse;

/**
 * Created by zhangminghui on 2017/8/24.
 */
@Service("payBankService")
public class PayBankService {

    @Autowired
    private PayBankServer payBankServer;

    /**
     * 提现列表
     * @param json
     * @return
     */
    public String payBankwithdrawallist(String json){
        HTTPResponse response = payBankServer.payBankwithdrawallist(json);
        if (response.isSuccessful()){
            return response.getBodyString();
        }
        return "";
    }

}
