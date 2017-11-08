package com.djcps.crm.outeruser.service;

import com.djcps.crm.outeruser.server.OuteruserHttpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rpc.plugin.http.HTTPResponse;


/**
 * Created by Lancher on 2017/7/3.
 */
@Service("outerUserService")
public class OuterUserService {

    @Autowired
    private OuteruserHttpService outeruserHttpService;

    /**
     * 重置用户密码
     *
     //* @param version
     //* @param userId
     //* @param payPassword
     //* @param password
     * @return nicai
     */
    public String changeOuterUserPassword(String json) {

        HTTPResponse httpResponse = outeruserHttpService.resetPayPassword(json);
        if (httpResponse.isSuccessful()) {
            return httpResponse.getBodyString();
        }
        return "";
    }

    /**
     * 启用帐号
     * @param json
     * @return gg
     */
    public String enableAccount(String json){
        HTTPResponse httpResponse = outeruserHttpService.enableAccount(json);
        if(httpResponse.isSuccessful()){
            return  httpResponse.getBodyString();
        }
        return "";
    }

    /**
     * 禁用帐号
     * @param json
     * @return gg
     */
    public String disableAccount(String json){
        HTTPResponse httpResponse = outeruserHttpService.disableAccount(json);
        if(httpResponse.isSuccessful()){
            return httpResponse.getBodyString();
        }
        return "";
    }

    /**
     * 查看用戶詳情
     * @param json
     * @return 你猜
     */
    public String getUserInfo(String json){
        HTTPResponse httpResponse = outeruserHttpService.getDetailInfo(json);
        if(httpResponse.isSuccessful()){
            return httpResponse.getBodyString();
        }
        return "";
    }

    /**
     * 获取用户列表
     * @return nicai
     */
    public String getMainUserList(String param) {
        HTTPResponse httpResponse = outeruserHttpService.getMainUserList(param);
        if(httpResponse.isSuccessful()){
            return httpResponse.getBodyString();
        }
        return "";
    }

    /**
     * 获取认证列表
     * @param json
     * @return gg
     */
    public String getAuditList(String json){
        HTTPResponse httpResponse = outeruserHttpService.getAuditList(json);
        if(httpResponse.isSuccessful()){
            return httpResponse.getBodyString();
        }
        return "";
    }

    /**
     * 获取认证列表
     * @param json
     * @return gg
     */
    public String getAuditDetail(String json){
        HTTPResponse httpResponse = outeruserHttpService.getAuditDetail(json);
        if(httpResponse.isSuccessful()){
            return httpResponse.getBodyString();
        }
        return "";
    }

    /**
     * 用户认证审核
     */
    public String userAudit(String json){
        HTTPResponse httpResponse = outeruserHttpService.userAudit(json);
        if(httpResponse.isSuccessful()){
            return httpResponse.getBodyString();
        }
        return "";
    }

    /**
     * 认证图片临时URL获取接口
     * @param json
     * @return
     */
    public  String getPicture(String json){
        HTTPResponse httpResponse = outeruserHttpService.getPicture(json);
        if (httpResponse.isSuccessful()){
            return httpResponse.getBodyString();
        }
        return "";
    }
}
