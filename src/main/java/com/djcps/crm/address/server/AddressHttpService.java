package com.djcps.crm.address.server;

import com.djcps.crm.commons.config.ParamsConfig;
import retrofit2.http.Body;
import retrofit2.http.POST;
import rpc.plugin.http.HTTPResponse;
import rpc.plugin.http.RPCClientFields;

/**
 * Created by ysy on 2017/7/5 20:35.
 */
@RPCClientFields(urlfield ="ADDRESS_SYS",urlbean = ParamsConfig.class)
public interface AddressHttpService {

    /**
     * 获取省份  code 默认为0 查询所有的省份
     * @param parm
     * @return
     */
    @POST("area/findProvince.do")
    HTTPResponse findProvince(@Body String parm);

    /**
     * 查询城市   或查询已添加省/城市列表接口  或查询已添加省份下拉列表接口
     * @param parm
     * @return
     */
    @POST("area/findCity.do")
    HTTPResponse findCity(@Body String parm);
    /**
     * 查询区域
     */
    @POST("area/findDistrictc.do")
    HTTPResponse findDistrictc(@Body String json);

    /**
     * 查询街道
     */
    @POST("area/findStreet.do")
    HTTPResponse findStreet(@Body String json);

    /**
     * 修改城市以下的所有地址状态    删除列表接口   或者    添加接口
     * @param json
     * @return
     */
    @POST("area/updateAddressByCity.do")
    HTTPResponse updateAddressByCity(@Body String json);

    /**
     * 查询已添加的省份和城市列表
     * @param json
     * @return
     */
    @POST("area/listProvinceAndCity.do")
    HTTPResponse listProvinceAndCity(@Body String json);
}
