package com.djcps.crm.address.service;

import com.djcps.crm.address.server.AddressHttpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rpc.plugin.http.HTTPResponse;

/**
 * Created by ysy on 2017/7/5 20:32.
 */
@Service
public class AddressService {
    @Autowired
    private AddressHttpService httpService;

    /**
     * 查询省份
     * @param parm
     * @return
     */
    public String findProvince(String parm) {

        HTTPResponse httpResponse = httpService.findProvince(parm);
        if (httpResponse.isSuccessful()) {
            return httpResponse.getBodyString();
        }
        return "";
    }

    /**
     * 查询城市   或查询已添加省/城市列表接口  或查询已添加省份下拉列表接口
     * @param parm
     * @return
     */
    public String findCity(String parm){
        HTTPResponse httpResponse = httpService.findCity(parm);
        if(httpResponse.isSuccessful()){
            return  httpResponse.getBodyString();
        }
        return "";
    }

    /**
     * 查询区域
     * @param parm
     * @return
     */
    public String findDistrictc(String parm){
        HTTPResponse httpResponse = httpService.findDistrictc(parm);
        if(httpResponse.isSuccessful()){
            return httpResponse.getBodyString();
        }
        return "";
    }

    /**
     * 查询街道
     * @param parm
     * @return
     */
    public String findStreet(String parm){
        HTTPResponse httpResponse = httpService.findStreet(parm);
        if(httpResponse.isSuccessful()){
            return httpResponse.getBodyString();
        }
        return "";
    }

    /**
     *  修改城市以下的所有地址状态    删除列表接口   或者    添加接口
     * @param parm
     * @return
     */
    public String updateAddressByCity(String parm) {
        HTTPResponse httpResponse = httpService.updateAddressByCity(parm);
        if (httpResponse.isSuccessful()) {
            return httpResponse.getBodyString();
        }
        return "";
    }
    /**
     * 查询已添加的省份和城市列表
     * @param parm
     * @return
             */
    public String listProvinceAndCity(String parm){
        HTTPResponse httpResponse = httpService.listProvinceAndCity(parm);
        if(httpResponse.isSuccessful()){
            return httpResponse.getBodyString();
        }
        return "";
    }
}
